package com.lowes.assignment.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.lowes.assignment.model.Product;
import com.lowes.assignment.services.ProducerService;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KakfaConfiguration {
	 private static final Logger logger = LoggerFactory.getLogger(KakfaConfiguration.class);
	
	@Value("${kafka.server}")
	private String kafkaServer;

    @Bean
    public ProducerFactory<String, Product> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        logger.info("######Configured Kafka######");
        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, Product> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


}
