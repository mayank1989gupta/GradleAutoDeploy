/**
 * 
 */
package com.test.api;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author MGupta
 *
 */
@SpringBootApplication
public class Application implements ApplicationRunner {

	private static final Logger LOGGER = Logger.getLogger(Application.class.getName()); 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Bean public RestTemplate getRestTemplate() { 
		return new RestTemplate(); 
	} 

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		LOGGER.log(Level.FINE, () -> "Debugging log");
		LOGGER.info("Info log");
		LOGGER.log(Level.WARNING, () -> "Hey, This is a warning!");
		LOGGER.log(Level.SEVERE, "Oops! We have an Error. OK");
	}

}
