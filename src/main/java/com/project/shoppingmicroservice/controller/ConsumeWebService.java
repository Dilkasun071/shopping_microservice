package com.project.shoppingmicroservice.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.inventory.entity.Product;


@RestController
public class ConsumeWebService {
   
   @Autowired
   RestTemplate restTemplate;

   @RequestMapping(value = "/shopping/getAllProducts")
   public String getProductList() {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity <String> entity = new HttpEntity<String>(headers);
      
      return restTemplate.exchange("http://localhost:8082/inventory/getAllProducts", HttpMethod.GET, entity, String.class).getBody();
   }
   
   @RequestMapping(value = "/shopping/addProduct", method = RequestMethod.POST)
   public String addProduct(@RequestBody Product product) {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
      
      return restTemplate.exchange(
         "http://localhost:8082/inventory/addProduct", HttpMethod.POST, entity, String.class).getBody();
   }
   
}
