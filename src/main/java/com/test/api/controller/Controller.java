/**
 * 
 */
package com.test.api.controller;

import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.api.business.Business;
import com.test.api.data.DataService;
import com.test.api.dto.Greeting;

/**
 * @author MGupta
 *
 */
@RestController
public class Controller {

	private static final String TEMPLATE = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());

	@Autowired
	Business business;

	/**
	 * Rest Service for testing boot app.</br>
	 * 
	 * @param name
	 * @return
	 */
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue="ABC  XYZ") String name) {
		LOGGER.info(() -> "this is service greeting for name: " + name);
		//Lambda --> for functional interface
		Business businessData = () -> {
			//functional Interface --> definition
			Supplier<String> data = () -> {
				DataService dataService = () -> "Testing Auto Deploy - Github Web-Hook & Docker Container Testing!!!";

				return dataService.data();//Return the response
			};

			return data.get();//from supplier	
		};

		//Use of supplier to build the Greeting object
		Supplier<Greeting> supplier = () -> 
		new Greeting(counter.incrementAndGet(),
				String.format(TEMPLATE, new StringJoiner(",")
						.add(name)
						.add(businessData.fetch())));

		return supplier.get();//return the greeting object.
	}

	//Test Service
	@GetMapping("/greetingTest")
	public String greetingTest(@RequestParam(value="name", defaultValue="World") String name) {
		LOGGER.info(() -> "this is service greeting for name: " + name);
		return business.fetch() + ", Image deployed on docker!";//return the greeting object.
	}	
}
//End of file