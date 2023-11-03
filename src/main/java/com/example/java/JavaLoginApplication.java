package com.example.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class JavaLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaLoginApplication.class, args);
	}

}
