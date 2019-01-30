/**
 * 
 */
package com.test.api.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.test.api.AbstractTest;
import com.test.api.data.DataService;

/**
 * @author MGupta
 *
 */
public class BusinessImplTest extends AbstractTest {

	@Override
	@Before public void setUp() { super.setUp();
	MockitoAnnotations.initMocks(BusinessImplTest.class); }


	@Mock
	DataService dataService;

	@Mock
	Business business;


	@Test
	public void testerTest() throws Exception {
		when(business.tester()).thenReturn("Success");
		String result = business.tester();
		assertEquals("Success", result);
	}
}
