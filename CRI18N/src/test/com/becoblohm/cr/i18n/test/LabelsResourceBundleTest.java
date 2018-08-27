/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.i18n.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.becoblohm.cr.i18n.LabelsResourceBundle;

/**
 * The class <code>LabelsResourceBundleTest</code> contains tests for the class
 * <code>{@link LabelsResourceBundle}</code>.
 * 
 * @generatedBy CodePro at 3/30/11 9:34 AM
 * @author "Eduardo Lugo (programador6)"
 * @version $Revision: 1.0 $
 */
public class LabelsResourceBundleTest {
	/**
	 * Run the LabelsResourceBundle() constructor test.
	 * 
	 * @generatedBy CodePro at 3/30/11 9:34 AM
	 */
	@Test
	public void testLabelsResourceBundle_1() throws Exception {
		LabelsResourceBundle result = new LabelsResourceBundle();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the Object[][] getContents() method test.
	 * 
	 * @throws Exception
	 * 
	 * @generatedBy CodePro at 3/30/11 9:34 AM
	 */
	@Test
	public void testGetContents_1() throws Exception {
		LabelsResourceBundle fixture = new LabelsResourceBundle();

		Object[][] result = fixture.getContents();

		// add additional test code here
		assertNotNull(result);
		assertEquals(2, result.length);
		assertNotNull(result[0]);
		assertEquals(2, result[0].length);
		assertNotNull(result[0][0]);
		assertNotNull(result[0][1]);
		assertNotNull(result[1]);
		assertEquals(2, result[1].length);
		assertNotNull(result[1][0]);
		assertNotNull(result[1][1]);
	}

	/**
	 * Perform pre-test initialization.
	 * 
	 * @throws Exception
	 *             if the initialization fails for some reason
	 * 
	 * @generatedBy CodePro at 3/30/11 9:34 AM
	 */
	@Before
	public void setUp() throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 * 
	 * @throws Exception
	 *             if the clean-up fails for some reason
	 * 
	 * @generatedBy CodePro at 3/30/11 9:34 AM
	 */
	@After
	public void tearDown() throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 * 
	 * @param args
	 *            the command line arguments
	 * 
	 * @generatedBy CodePro at 3/30/11 9:34 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(LabelsResourceBundleTest.class);
	}
}
