/**
 * 
 */
package com.test.api.data;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.test.root.AbstractTest;

/**
 * @author MGupta
 * Tester class for Data Service Impl.</br>
 */
public class DataServiceImplTest extends AbstractTest {

	@Override
	@Before public void setUp() { 
		super.setUp();
		MockitoAnnotations.initMocks(this);
	}

	@Mock
	private DataService dataService;//Mocking data service

	@Test
	public void testDataService() throws Exception {
		when(dataService.data()).thenReturn("Success");

		String result = dataService.data();
		assertEquals("Success", result);
	}
}
