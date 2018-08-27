/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package test;

import com.becoblohm.cr.sync.converters.MonedadenominacionConverter;
import com.becoblohm.cr.types.CRBigDecimal;

/**
 */
public class Pruebas {

	/**
	 * Method test1.
	 * 
	 * @throws Exception
	 */
	public void test1() throws Exception {

		String value = "10.000,92 $.";
		String value1;
		value1 = value.replaceAll("[a-zA-Z]+[\\.]", "");
		// [a-zA-Z]+[\\.]|[^\\w\\.@-]+[\\.]
		// DecimalFormat format = new DecimalFormat("########,###");
		// Number valor = format.parse(value1);

		// NumberFormat nf =
		// NumberFormat.getNumberInstance(MonedadenominacionConverter.getLocale());
		// DecimalFormat df = (DecimalFormat)nf;
		// df.applyPattern("########,###");
		// String output = df.format(Double.valueOf(value1));
		// System.out.println("########,###" + "  " + output + "  " +
		// MonedadenominacionConverter.getLocale().toString());
		//
		// DecimalFormat formateador = new DecimalFormat("########,###");
		//
		// DecimalFormatSymbols dfs = formateador.getDecimalFormatSymbols();
		// dfs.setDecimalSeparator(',');
		// formateador.setDecimalFormatSymbols(dfs);
		//
		//
		// String value2=
		// String.valueOf(Double.valueOf(formateador.format(value1.trim())));
		Number tmp = MonedadenominacionConverter.getDecimalFormat().parse(value1);
		// Number tmp = Global.getDecimalFormat().parse(value1);
		System.out.println(tmp.toString());
		System.out.println(new CRBigDecimal(tmp.doubleValue()));
	}

	/**
	 * Method setUp.
	 * 
	 * @throws Exception
	 */
	public void setUp() throws Exception {

	}

	/**
	 * Perform post-test clean-up.
	 * 
	 * 
	 * 
	 * @generatedBy CodePro at 14/04/11 10:19 AM
	 * 
	 * @throws Exception
	 *             if the clean-up fails for some reason
	 */
	public void tearDown() throws Exception {

	}

}
