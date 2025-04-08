package com.example.todoappapispringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.todoappapispringboot.models")
@EnableJpaRepositories("com.example.todoappapispringboot.repositories")
public class TodoappApiSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoappApiSpringBootApplication.class, args);
	}

}
