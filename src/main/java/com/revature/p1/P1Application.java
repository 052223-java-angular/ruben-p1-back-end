package com.revature.p1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@SpringBootApplication
public class P1Application {


	public static void main(String[] args) {


		SpringApplication.run(P1Application.class, args);
		System.out.println("Application is running...");
	}


}
