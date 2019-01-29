/**
 * 
 */
package com.test.api.business;

import static org.junit.Assert.assertEquals;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.test.api.data.DataService;

/**
 * @author MGupta
 *
 */
public class BusinessImplTest {

	/*
	 * @Override
	 * 
	 * @Before public void setUp() { super.setUp();
	 * MockitoAnnotations.initMocks(BusinessImplTest.class); }
	 */
	
	@Mock
	Business business;
	
	@Mock
	DataService dataService;
	
	@InjectMocks
	BusinessImpl businessImpl;
	
	
	public void testerTest() throws Exception {
		String result = businessImpl.tester();
		assertEquals("Success", result);
	}
}
