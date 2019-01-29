/**
 * 
 */
package com.test.api;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.api.business.Business;

/**
 * @author MGupta
 *
 */
@RestController
public class Controller {

	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);
    
    @Autowired
    Business business;

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	LOGGER.debug("this is service greeting for name: " + name);
    	LOGGER.debug("Test completed");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name + ", " + business.tester()));
    }
}