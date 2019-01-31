/**
 * 
 */
package com.test.api.controller;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MGupta
 *
 */
@RestController
public class TestController {

	private static final String TEMPLATE = "Test Controller, %s!";
    private final AtomicLong counter = new AtomicLong();
	
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
