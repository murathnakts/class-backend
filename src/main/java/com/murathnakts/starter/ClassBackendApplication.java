package com.murathnakts.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.murathnakts"})
@ComponentScan(basePackages = {"com.murathnakts"})
@EnableJpaRepositories(basePackages = {"com.murathnakts"})
@SpringBootApplication
public class ClassBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassBackendApplication.class, args);
	}

}
