package com.example.artspectrum.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
		name = "Bearer Authentication",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer",
		in = SecuritySchemeIn.HEADER
)
@OpenAPIDefinition(
		info = @Info (
				contact = @Contact(
						name = "Yurieva Yulia",
						email = "cryinghostt.56@gmail.com"
				),
				title = "ArtSpectrum Project",
				description = "OpenApi specification for web-service",
				version = "1.0.0"
		),
		servers = {
				@Server(
						url = "http://localhost:8080/api/v1/artspectrum",
						description = "Local DEV"
				)
		}
)
public class OpenApiConfig {

}


