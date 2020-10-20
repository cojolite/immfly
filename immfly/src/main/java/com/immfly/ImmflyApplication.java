package com.immfly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ImmflyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImmflyApplication.class, args);
	}
	
}
