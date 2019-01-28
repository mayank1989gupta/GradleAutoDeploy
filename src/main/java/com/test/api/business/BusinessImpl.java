/**
 * 
 */
package com.test.api.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.api.data.DataService;

/**
 * @author MGupta
 *
 */
@Service
public class BusinessImpl implements Business {

	@Autowired
	DataService dataService;
	
	@Override
	public String tester() {
		
		return dataService.data();
	}

}
