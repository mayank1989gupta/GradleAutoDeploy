/**
 * 
 */
package com.test.api.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.test.api.data.DataService;
import com.test.root.AbstractTest;

/**
 * @author MGupta
 *
 */
public class BusinessImplTest extends AbstractTest {

	@Mock
	private Business business;


	@Test
	public void testTester() throws Exception {
		when(business.tester()).thenReturn("Success");
		String result = business.tester();
		assertEquals("Success", result);
	}
}
