package com.yuvaan.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration class to configure Swagger documentation for the application.
 * @author saurav mehta
 *
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	final Contact contact = new Contact("Saurav Mehta", "mywebsite.com", "myemail@email.com");
	final ApiInfo apiInfo = new ApiInfoBuilder().title("User Management Service")
			.description("This consists of all available REST endpoints to manage users.").version("1.0")
			.contact(contact).build();

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo);
	}
}
