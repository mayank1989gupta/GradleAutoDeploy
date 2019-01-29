/**
 * 
 */
package com.test.api.business;

import org.springframework.stereotype.Service;

import com.test.api.data.DataService;

/**
 * @author MGupta
 *
 */
@Service
public class BusinessImpl implements Business {

	
	@Override
	public String tester() {
		DataService dataService = () -> {
			return "Success, Testing the layers!!";
		};
		
		return dataService.data();
	}

}
