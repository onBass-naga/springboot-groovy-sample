package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class Hajiboot2Application {

	@GetMapping("/")
	String home() {
	    return 'Hello Spring Boot!'
	}

	static void main(String[] args) {
		SpringApplication.run Hajiboot2Application, args
	}
}
