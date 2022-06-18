package com.example.tesis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.tesis.controller","com.example.tesis.service","com.example.tesis.process", "com.example.tesis.repository"})
@ComponentScan(basePackages = {"com.example.tesis.service"})
public class TesisApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesisApplication.class, args);
	}

}
