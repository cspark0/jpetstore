package com.example.jpetstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JpetstoreBootApplication { 
//public class JpetstoreBootApplication extends SpringBootServletInitializer {
	/*
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JpetstoreBootApplication.class);
	}
	*/
    public static void main(String[] args) throws Exception {
        SpringApplication.run(JpetstoreBootApplication.class, args);
    }
}