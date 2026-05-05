package com.stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.stage.model")
public class StageSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(StageSpringApplication.class, args);
	}

}
