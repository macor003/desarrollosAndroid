/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.epa.plugins.core;

import java.util.Iterator;

/**
 * @author programador6
 * @version $Revision: 1.0 $
 */
public interface PluginService {
	/**
	 * Method getPlugins.
	 * 
	 * 
	 * @return Iterator
	 */
	abstract Iterator getPlugins();

	/**
	 * Method initPlugins.
	 */
	abstract void initPlugins();

}
