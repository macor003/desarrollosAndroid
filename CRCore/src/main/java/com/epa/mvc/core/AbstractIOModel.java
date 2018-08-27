/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.mvc.core;

import com.epa.plugins.exception.DeviceIOException;

/**
 * @author programador6
 * @version $Revision: 1.0 $
 */
public abstract class AbstractIOModel extends AbstractModel {

	/**
	 * Method checkConn.
	 * 
	 * 
	 * @return boolean
	 */
	public abstract boolean checkConn();

	/**
	 * Method openConn.
	 * 
	 * 
	 * @throws DeviceIOException
	 */
	protected abstract void openConn() throws DeviceIOException;

	/**
	 * Method closeConn.
	 * 
	 * 
	 * @throws DeviceIOException
	 */
	protected abstract void closeConn() throws DeviceIOException;

	/**
	 * Method getName.
	 * 
	 * 
	 * @return String
	 */
	public String getName() {
		return "Dispositivo";
	}

}
