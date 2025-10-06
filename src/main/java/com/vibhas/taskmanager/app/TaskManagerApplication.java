package com.vibhas.taskmanager.app;

import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.vibhas.taskmanager")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.vibhas.taskmanager.repository")
@EntityScan(basePackages = "com.vibhas.taskmanager.model")
@OpenAPI30
public class TaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

}
