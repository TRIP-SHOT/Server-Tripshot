package com.tripshot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@SpringBootApplication
public class TripshotApplication {
	public static void main(String[] args) {
		SpringApplication.run(TripshotApplication.class, args);
	}
	
	
	 @Bean
	  public OpenAPI api() {
	    SecurityScheme apiKey = new SecurityScheme()
	      .type(SecurityScheme.Type.APIKEY)
	      .in(SecurityScheme.In.HEADER)
	      .name("Authorization");

	    SecurityRequirement securityRequirement = new SecurityRequirement()
	      .addList("Bearer Token");

	    return new OpenAPI()
	      .components(new Components().addSecuritySchemes("Bearer Token", apiKey))
	      .addSecurityItem(securityRequirement);
	  }

}
