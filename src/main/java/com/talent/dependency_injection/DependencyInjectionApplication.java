package com.talent.dependency_injection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.talent.dependency_injection.entities")
public class DependencyInjectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DependencyInjectionApplication.class, args);
	}

}
