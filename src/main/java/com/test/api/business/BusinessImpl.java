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
@Deprecated
public class BusinessImpl implements Business {

	@Autowired
	DataService dataService;
	
	
	public BusinessImpl(DataService dataService) {
        super();
        this.dataService = dataService;
    }
	
	@Override
	public String tester() {
		String result = dataService.data();
		
		return result;
	}

}
