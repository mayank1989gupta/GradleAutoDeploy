/**
 * 
 */
package com.test.api.data;

import org.springframework.stereotype.Service;

/**
 * @author MGupta
 *
 */
@Service
public class DataServiceImpl implements DataService {

	@Override
	public String data() {
		
		return "Success, Testing the layers!!";
	}

}
