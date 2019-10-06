package com.lowes.assignment.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowes.assignment.config.KakfaConfiguration;
import com.lowes.assignment.model.Product;
import com.lowes.assignment.services.ConsumerService;
import com.lowes.assignment.services.ProducerService;

@RestController
@RequestMapping(value = "/lowes")
public class LowesController {

	 private static final Logger logger = LoggerFactory.getLogger(LowesController.class);

    private final ProducerService producerService;
    
    private final ConsumerService consumerService;

    @Autowired
    LowesController(ProducerService producerService, ConsumerService consumerService) {
        this.producerService = producerService;
        this.consumerService = consumerService;
    }

    @PostMapping("/publish")
    public String sendMessageToKafkaTopic(@RequestBody Product product) {
        String id = this.producerService.sendMessage(product).toString();
        logger.info("Published record");
        return "Published record for "+id;
    }
    
    @RequestMapping({"/product/showAll"})
    public List<Product> listProducts(){
        return consumerService.listAll();
    }
    
    @RequestMapping({"/product/find/{id}"})
    public Product productById(@PathVariable String id) {
    	return consumerService.getById(UUID.fromString(id));
    }

    @RequestMapping("/product/delete/{id}")
    public String deleteByID(@PathVariable String id) {
    	consumerService.deleteById(UUID.fromString(id));
    	 return "Deleted Successfully";
    }
}
