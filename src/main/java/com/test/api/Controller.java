/**
 * 
 */
package com.test.api;

import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.api.business.Business;
import com.test.api.data.DataService;

/**
 * @author MGupta
 *
 */
@RestController
public class Controller {

	private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    @Autowired
    Business business;
    
    /**
     * Rest Service for testing boot app.</br>
     * 
     * @param name
     * @return
     */
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	LOGGER.debug("this is service greeting for name: " + name);
    	LOGGER.debug("Test completed");
    	//Lambda --> for functional interface
    	Business business = () -> {
    		//functional Interface --> definition
    		DataService dataService = () -> {
    			return "Testing Auto Deploy - Github Web-Hook & Docker Container!!";
    		};
    		
    		return dataService.data();//Return the response
    	};
    	
    	//Use of supplier to build the Greeting object
    	Supplier<Greeting> supplier = () -> {
    		return new Greeting(counter.incrementAndGet(),
                    String.format(TEMPLATE, new StringJoiner(",")
                    		.add(name)
                    		.add(business.tester())));
    	};
    	
        return supplier.get();//return the greeting object.
    }

	//Test Service
    @GetMapping("/greetingTest")
    public String greetingTest(@RequestParam(value="name", defaultValue="World") String name) {
    	LOGGER.debug("this is service greeting for name: " + name);
    	LOGGER.debug("Test completed");
    	
        return business.tester();//return the greeting object.
    }
}
//End of file