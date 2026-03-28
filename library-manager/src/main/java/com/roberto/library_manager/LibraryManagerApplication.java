package com.roberto.library_manager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Library Manager API",
				version = "1.0.0",
				description = "REST API for managing a personal book library",
				contact = @Contact(
						name = "Roberto Carai",
						email = "roberto.carai@outlook.com"
				)
		)
)
public class LibraryManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagerApplication.class, args);
	}

}
