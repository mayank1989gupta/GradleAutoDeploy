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
@Deprecated
public class DataServiceImpl  implements DataService {

	@Override
	public String data() {
		// TODO Auto-generated method stub
		return "Success";
	}
	
	

}
