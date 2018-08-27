/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.epa.properties.layer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @author programador6
 * @version $Revision: 1.0 $
 */
public class SimpleProperties extends Properties {

	/**
	 * Constructor for SimpleProperties.
	 */
	public SimpleProperties() {
		super();

	}

	/**
	 * Constructor for SimpleProperties.
	 * 
	 * @param arg0
	 *            Properties
	 */
	public SimpleProperties(Properties arg0) {
		super(arg0);

	}

	/**
	 * Constructor for SimpleProperties.
	 * 
	 * @param fileprop
	 *            String
	 * 
	 * 
	 * @throws IOException
	 */
	public SimpleProperties(String fileprop) throws IOException {
		super();
		cargarDesdeArchivo(fileprop, null);
	}

	/**
	 * Method cargarDesdeArchivo.
	 * 
	 * @param propFileName
	 *            String
	 * @param defaultPath
	 *            String
	 * 
	 * 
	 * @throws IOException
	 */
	protected final void cargarDesdeArchivo(String propFileName, String defaultPath) throws IOException {

		URL propURL = Thread.currentThread().getContextClassLoader().getResource(propFileName);
		if (propURL != null) {
			InputStream propIS = propURL.openStream();

			if (propIS != null) {
				try {
					load(propIS);
				} catch (IOException e) {

					throw e;
				}
			} else {

				throw new FileNotFoundException("Falta archivo de propiedades: " + propFileName);
			}
		} else {

			throw new FileNotFoundException("Falta archivo de propiedades: " + propFileName);
		}

	}

}
