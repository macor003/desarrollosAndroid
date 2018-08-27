/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.conf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Font;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.becoblohm.cr.conf.Global;
import com.epa.mvc.core.AbstractController;

/**
 * The class <code>GlobalTest</code> contains tests for the class
 * <code>{@link Global}</code>.
 * 
 * @generatedBy CodePro at 30/03/11 08:55 AM
 * @author "Eduardo Lugo (programador6)"
 * @version $Revision: 1.0 $
 */
public class GlobalTest {
	/**
	 * Run the Global() constructor test.
	 * 
	 * @generatedBy CodePro at 30/03/11 08:55 AM
	 */
	@Test
	public void testGlobal_1() throws Exception {
		// Global result = new Global();
		// assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the Font getActivityFont() method test.
	 * 
	 * @throws Exception
	 * 
	 * @generatedBy CodePro at 30/03/11 08:55 AM
	 */
	@Test
	public void testGetActivityFont_1() throws Exception {

		Font result = Global.getBigFont();

		// add additional test code here
		assertNotNull(result);
		assertEquals("java.awt.Font[family=Verdana,name=Verdana,style=bold,size=18]", result.toString());
		assertEquals("Verdana", result.getName());
		assertEquals(18, result.getSize());
		assertEquals("Verdana", result.getFamily());
		assertEquals("Verdana Bold", result.getFontName());
		assertEquals(1, result.getStyle());
		assertEquals(0.0f, result.getItalicAngle(), 1.0f);
		assertEquals(0, result.getMissingGlyphCode());
		assertEquals(893, result.getNumGlyphs());
		assertEquals(18.0f, result.getSize2D(), 1.0f);
		assertEquals(false, result.isTransformed());
		assertEquals("Verdana-Bold", result.getPSName());
		assertEquals(false, result.hasLayoutAttributes());
		assertEquals(false, result.hasUniformLineMetrics());
		assertEquals(true, result.isBold());
		assertEquals(false, result.isItalic());
		assertEquals(false, result.isPlain());
	}

	/**
	 * Run the AbstractController getDevController() method test.
	 * 
	 * @throws Exception
	 * 
	 * @generatedBy CodePro at 30/03/11 08:55 AM
	 */
	@Test
	public void testGetDevController_1() throws Exception {

		AbstractController result = Global.getDevController();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the ResourceBundle getErrorsResourceBundle() method test.
	 * 
	 * @throws Exception
	 * 
	 * @generatedBy CodePro at 30/03/11 08:55 AM
	 */
	@Test
	public void testGetErrorsResourceBundle_1() throws Exception {

		ResourceBundle result = Global.getErrorsResourceBundle();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the ResourceBundle getLabelsResourceBundle() method test.
	 * 
	 * @throws Exception
	 * 
	 * @generatedBy CodePro at 30/03/11 08:55 AM
	 */
	@Test
	public void testGetLabelsResourceBundle_1() throws Exception {

		ResourceBundle result = Global.getLabelsResourceBundle();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Locale getLocale() method test.
	 * 
	 * @throws Exception
	 * 
	 * @generatedBy CodePro at 30/03/11 08:55 AM
	 */
	@Test
	public void testGetLocale_1() throws Exception {

		Locale result = Global.getLocale();

		// add additional test code here
		assertNotNull(result);
		assertEquals("en_US", result.toString());
		assertEquals("en", result.getLanguage());
		assertEquals("US", result.getCountry());
		assertEquals("Estados Unidos", result.getDisplayCountry());
		assertEquals("inglés", result.getDisplayLanguage());
		assertEquals("inglés (Estados Unidos)", result.getDisplayName());
		assertEquals("", result.getDisplayVariant());
		assertEquals("USA", result.getISO3Country());
		assertEquals("eng", result.getISO3Language());
		assertEquals("", result.getVariant());
	}

	/**
	 * Run the ResourceBundle getMessagesResourceBundle() method test.
	 * 
	 * @throws Exception
	 * 
	 * @generatedBy CodePro at 30/03/11 08:55 AM
	 */
	@Test
	public void testGetMessagesResourceBundle_1() throws Exception {

		ResourceBundle result = Global.getMessagesResourceBundle();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the AbstractController getMwController() method test.
	 * 
	 * @throws Exception
	 * 
	 * @generatedBy CodePro at 30/03/11 08:55 AM
	 */
	@Test
	public void testGetMwController_1() throws Exception {

		// AbstractController result = Global.getMwController();

		// add additional test code here
		// assertEquals(null, result);
	}

	/**
	 * Run the boolean isTesting() method test.
	 * 
	 * @throws Exception
	 * 
	 * @generatedBy CodePro at 30/03/11 08:55 AM
	 */
	@Test
	public void testIsTesting_1() throws Exception {

		boolean result = Global.isBetaPos();

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the boolean isTesting() method test.
	 * 
	 * @throws Exception
	 * 
	 * @generatedBy CodePro at 30/03/11 08:55 AM
	 */
	@Test
	public void testIsTesting_2() throws Exception {

		boolean result = Global.isBetaPos();

		// add additional test code here
		assertEquals(true, result);
	}

	/**
	 * Run the void setDevController(AbstractController) method test.
	 * 
	 * @throws Exception
	 * 
	 * @generatedBy CodePro at 30/03/11 08:55 AM
	 */
	@Test
	public void testSetDevController_1() throws Exception {
		AbstractController devices = null;

		Global.setDevController(devices);

		// add additional test code here
	}

	/**
	 * Run the void setMwController(AbstractController) method test.
	 * 
	 * @throws Exception
	 * 
	 * @generatedBy CodePro at 30/03/11 08:55 AM
	 */

	/**
	 * Perform pre-test initialization.
	 * 
	 * @throws Exception
	 *             if the initialization fails for some reason
	 * 
	 * @generatedBy CodePro at 30/03/11 08:55 AM
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
	 * @generatedBy CodePro at 30/03/11 08:55 AM
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
	 * @generatedBy CodePro at 30/03/11 08:55 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GlobalTest.class);
	}
}
