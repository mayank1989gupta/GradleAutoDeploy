/**
 * 
 */
package com.test.api.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author MGupta
 *
 */
@Configuration
@EnableSwagger2
@RefreshScope
public class SpringFoxConfig {

	@Value("${application.version}")
	private String version;

	@Bean
	public Docket apiDocket() {
		//To select all the controllers

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());


		//To select specific packages
		/*
		 * return new Docket(DocumentationType.SWAGGER_2) .select()
		 * .apis(RequestHandlerSelectors.basePackage("com.test.api"))
		 * .paths(PathSelectors.ant("/greeting**")) .build() .apiInfo(getApiInfo());
		 */
	}

	/**
	 * Method to populate the API Info data.</br>
	 * Configured with sample values for now.</br>
	 * 
	 * @return
	 */
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Gradle Auto Deploy",
				"App to test Gradle Jenkins Github Auto Deploy",
				version,
				"https://github.com/mayank1989gupta/GradleAutoDeploy",
				new Contact("Mayank Gupta", 
						"https://github.com/mayank1989gupta/GradleAutoDeploy", 
						"mayank1989gupta@gmail.com"),
				null,//License
				null,//License URL
				Collections.emptyList()
				);
	}
}