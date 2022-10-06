package com.loopco.pricesolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class PriceSolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceSolutionApplication.class, args);
	}

}
