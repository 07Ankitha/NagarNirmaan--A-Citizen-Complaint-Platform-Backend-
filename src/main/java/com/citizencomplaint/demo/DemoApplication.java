package com.citizencomplaint.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("hello");
		SpringApplication.run(DemoApplication.class, args);
	}

}
