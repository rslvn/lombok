/**
 * 
 */
package com.demo.lombok.withoutlombok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author resulav
 *
 */
@SpringBootApplication
@EnableScheduling
@EnableWebMvc
public class WithoutlombokApplication {

	public static void main(String[] args) {
		SpringApplication.run(WithoutlombokApplication.class, args);
	}

}
