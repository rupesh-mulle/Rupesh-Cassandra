package com.lowes.assignment.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.lowes.assignment.model.Product;
import com.lowes.assignment.repository.ProductRepository;

@Service
public class ConsumerService {
	
	 @Autowired
	 ProductRepository productRepository;

    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics = "EXAMPLE_TOPIC",group = "test-consumer-group")
    public void consume(String prodString) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", prodString));
        Gson g = new Gson(); 
        Product product = g.fromJson(prodString, Product.class);
         Product prod = new Product();
         prod.setName(product.getName());
         prod.setId(product.getId());
         prod.setCategory(product.getCategory());
         prod = productRepository.save(prod);
         logger.info("Inserted following record into Cassandra", prodString);
    }
    
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add); 
        logger.info("retrieved all the products");
        return products;
    }
    
    public Product getById(UUID id) {
    	logger.info("Call to delete a product");
        return productRepository.findOne(id);
    }
    
    public void deleteById(UUID id) {
    	logger.info("request to delete a record");
    	productRepository.delete(id);
    }
    
}