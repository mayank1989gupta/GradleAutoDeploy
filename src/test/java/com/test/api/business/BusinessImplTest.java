/**
 * 
 */
package com.test.api.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;

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
		when(business.fetch()).thenReturn("Success");
		String result = business.fetch();
		assertEquals("Success", result);
	}
}
