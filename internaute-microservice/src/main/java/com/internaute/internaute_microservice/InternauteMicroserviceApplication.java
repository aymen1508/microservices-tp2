package com.internaute.internaute_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDiscoveryClient
@SpringBootApplication
public class InternauteMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternauteMicroserviceApplication.class, args);
	}

}
