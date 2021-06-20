package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.errorhandler.ErrorHandler;

@SpringBootApplication
public class SpringBootHelloWorldApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(SpringBootHelloWorldApplication.class, args);   
	}
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ErrorHandler());
		return restTemplate;
	}
	
}