package com.libreria.clientemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClienteMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteMicroserviceApplication.class, args);
	}

}
