package com.alura.forum.config.documentation;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alura.forum.model.Usuario;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ParameterType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket forumApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.alura.forum"))
				.paths(PathSelectors.any())
				.build()
				.ignoredParameterTypes(Usuario.class)
				.globalRequestParameters(Arrays.asList(
						new RequestParameterBuilder()
						.name("Authorization")
						.in(ParameterType.HEADER)
						.description("Header para el token JWT")
						.required(false)
						.build()));
	}
}
