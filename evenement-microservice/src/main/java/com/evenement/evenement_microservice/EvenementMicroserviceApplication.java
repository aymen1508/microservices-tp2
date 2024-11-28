package com.evenement.evenement_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EvenementMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvenementMicroserviceApplication.class, args);
	}

}
