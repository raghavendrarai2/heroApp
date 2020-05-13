package com.project.heroApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.project.*"})
public class HeroAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroAppApplication.class, args);
	}

}
