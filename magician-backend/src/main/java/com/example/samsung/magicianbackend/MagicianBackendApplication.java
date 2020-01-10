package com.example.samsung.magicianbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication
@ConfigurationProperties("spring.datasource.hikari")
//@EnableSwagger2
public class MagicianBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagicianBackendApplication.class, args);
	}

}
