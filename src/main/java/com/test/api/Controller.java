/**
 * 
 */
package com.test.api;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
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
    			return "Testing Auto Deploy using Github Web-Hook!!";
    		};
    		
    		return dataService.data();
    	};
    	
    	//Use of supplier to build the Greeting object
    	Supplier<Greeting> supplier = () -> {
    		return new Greeting(counter.incrementAndGet(),
                    String.format(TEMPLATE, name + ", " + business.tester()));
    	};
    	
    	consumer.accept(supplier.get());//Updating content via consumer object
    	
        return supplier.get();//return the greeting object.
    }

	//Test Service
    @GetMapping("/greetingTest")
    public String greetingTest(@RequestParam(value="name", defaultValue="World") String name) {
    	LOGGER.debug("this is service greeting for name: " + name);
    	LOGGER.debug("Test completed");
    	
        return business.tester();//return the greeting object.
    }
    
    //Test Service
    @GetMapping("/hello")
    public String hello(@RequestParam(value="name", defaultValue="ABCXYZ") String name) {
    	Supplier<String> supplier = () -> {
    		return (counter.incrementAndGet() + ", " +
                    String.format(TEMPLATE, name) + "!!!");
    	};
    	
    	return supplier.get();//return
    }
}
//End of file