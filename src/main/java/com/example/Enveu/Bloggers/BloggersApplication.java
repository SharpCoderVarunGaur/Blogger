package com.example.Enveu.Bloggers;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication

public class BloggersApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggersApplication.class, args);
		System.out.println("HEllo Blogger Api started");
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
