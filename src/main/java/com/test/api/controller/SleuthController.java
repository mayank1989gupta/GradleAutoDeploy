/**
 * 
 */
package com.test.api.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author MGupta
 *
 * Class have the test API's to test sleuth.
 * App Name, Trace ID, Span ID, Send to Zipkin Flag
 */
@RestController
public class SleuthController {

	private static final Logger LOGGER = Logger.getLogger(SleuthController.class.getName());

	@Autowired
	RestTemplate restTemplate;

	/**
	 * API to test the sleuth logging.</br>
	 * 
	 * @return
	 */
	@GetMapping("/sleuth")
	public String sleuth() {
		LOGGER.info("*****************************************************************");
		LOGGER.info("Testing sleuth");
		return restTemplate.getForObject("http://localhost:8000/sleuth1", String.class); 
	}

	@GetMapping("/sleuth1")
	public String sleuth1() {
		LOGGER.info("Testing sleuth1");
		return restTemplate.getForObject("http://localhost:8000/sleuth2", String.class); 
	}

	@GetMapping("/sleuth2")
	public String sleuth2() {
		LOGGER.info("Testing sleuth2");
		return restTemplate.getForObject("http://localhost:8000/greetingTest", String.class); 
	}
}
