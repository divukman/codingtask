package com.dimitar.searchmetrics.codingtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CodingtaskApplication {


	public static void main(String[] args) {
		SpringApplication.run(CodingtaskApplication.class, args);

		System.out.println("Welcome to blockchain currency monitor...");
	}

}
