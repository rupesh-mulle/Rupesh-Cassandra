package com.lowes.assignment.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
	    	when(lowesController.sendMessageToKafkaTopic(product)).thenReturn("Published record for ");
	    	String result = lowesController.sendMessageToKafkaTopic(product);
	    	assertEquals("Published record for ", result);
	    }
	    
	    @Test
	    public void productById() throws Exception{
	    	when(lowesController.productById(id)).thenReturn(product);
	    	assertEquals("test", lowesController.productById(id).getName());
	    }
	    
	    @Test
	    public void listProducts() throws Exception{
	    	when(lowesController.listProducts()).thenReturn(productList);
	    	assertEquals(true,lowesController.listProducts().size()>0);
	    }

}
