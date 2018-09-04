/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.utils.report;

import com.becoblohm.cr.utils.Utils;

public class PrintingUtils {
	/**
	 * Method alinear.
	 * 
	 * @param texto
	 *            String
	 * @param separator
	 *            String
	 * @return String
	 */
	public static String alinear(String texto, String separator) {

		String[] linesplit = texto.split(separator);
		StringBuffer sbTmp = new StringBuffer();
		sbTmp.append(Utils.completarBlanco(linesplit[0], 19, 0));
		sbTmp.append(Utils.completarBlanco(linesplit[1], 19, 1));

		texto = sbTmp.toString();

		return texto;
	}
}
