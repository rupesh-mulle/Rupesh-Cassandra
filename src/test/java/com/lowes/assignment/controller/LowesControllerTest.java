package com.lowes.assignment.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.lowes.assignment.model.GeneralResponse;
import com.lowes.assignment.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LowesControllerTest {

	 @Mock
	    private LowesController lowesController;
	 
	 private Product product;
	 
	 private String id;
	 
	 private List<Product> productList;
	 
	 private ResponseEntity<GeneralResponse> response;
	 
	 private GeneralResponse result;
	 
	    @Before
	    public void setup() {
	        product = new Product();
	    	product.setCategory("Outdoor");
	    	product.setName("test");
	    	
	    	id= "e0f1a7e4-260e-4872-b5fa-9f895337e679";
	    	
	    	productList = new ArrayList<>();
	    	productList.add(product);
	        
	    }
	 
	    @Test
	    public void sendMessageToKafkaTopic() throws Exception {
	    	result = new GeneralResponse();
	    	result.setResponseMessage("Published Record successfully");
	    	when(lowesController.sendMessageToKafkaTopic(product)).thenReturn(new ResponseEntity<GeneralResponse>(result, HttpStatus.OK));
	    	response = lowesController.sendMessageToKafkaTopic(product);
	    	 assertEquals(HttpStatus.OK, response.getStatusCode());
	         JSONAssert.assertEquals("Published Record successfully", response.getBody().getResponseMessage(), true);
	    }
	    
	    @Test
	    public void productById() throws Exception{
	    	result = new GeneralResponse();
	    	result.setResponseMessage("Request processed");
	    	result.setResult(product);
	    	when(lowesController.productById(id)).thenReturn(new ResponseEntity<GeneralResponse>(result, HttpStatus.OK));
	    	response = lowesController.productById(id);
	    	assertEquals(HttpStatus.OK, response.getStatusCode());
	    	assertThat(response.getBody().getResult()).extracting("name").isNotEmpty();
	    }
	    
	    @Test
	    public void listProducts() throws Exception{
	    	result = new GeneralResponse();
	    	result.setResponseMessage("Request processed");
	    	result.setResult(product);
	    	when(lowesController.listProducts()).thenReturn(new ResponseEntity<GeneralResponse>(result, HttpStatus.OK));
	    	
	    	response = lowesController.listProducts();
	    	assertEquals(HttpStatus.OK, response.getStatusCode());
	    	assertThat(response.getBody()).isNotNull();
	    }

}
