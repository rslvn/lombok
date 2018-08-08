package com.demo.lombok.withlombok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WithlombokApplication {

	public static void main(String[] args) {
		SpringApplication.run(WithlombokApplication.class, args);
	}
}
