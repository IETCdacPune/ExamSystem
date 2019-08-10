package com.ietpune;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class ExamSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamSystemApplication.class, args);
	}
	@Bean
	public Map<String, String> myMap() {
	    final Map<String, String> myMap = new HashMap<>();
	    myMap.put("isRegistrationAvailable", "false");
	    myMap.put("isAddAvailable", "false");	    
	    return myMap;
	}
}
