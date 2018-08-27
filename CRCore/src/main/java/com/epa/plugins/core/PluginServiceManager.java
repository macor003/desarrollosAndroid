/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.epa.plugins.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epa.plugins.exception.PluginException;

/**
 * @author programador6
 * @version $Revision: 1.0 $ TODO hacer refactor a esta clase para poder iterar
 *          usando el nuevo for loop en getPlugins.
 */
public class PluginServiceManager {

	/**
	 * Field logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(PluginServiceManager.class);
	// protected static PluginService pluginService;
	/**
	 * Field services.
	 */
	protected Map services;

	/**
	 * Constructor for PluginServiceManager.
	 * 
	 * 
	 * @throws PluginException
	 */
	public PluginServiceManager() {

		// PluginServiceFactory.addPluginJarsToClasspath();
	}

	/*
	 * public PluginService getPluginService() { return pluginService; }
	 * 
	 * public void setPluginService(PluginService pluginService) {
	 * PluginServiceManager.pluginService = pluginService; }
	 */

	/**
	 * Method loadPlugins.
	 * 
	 * @param file
	 *            String
	 * 
	 * 
	 * @throws PluginException
	 */
	public void loadPlugins(String file) throws PluginException {
		if (services == null) {
			services = new HashMap();
		}
		// pluginService = createPluginService();

		services = createPluginServices(file);

	}

	/**
	 * Method createPluginServices.
	 * 
	 * @param file
	 *            String
	 * 
	 * 
	 * 
	 * @return HashMap<String,PluginService> * @throws PluginException * @throws
	 *         PluginException * @throws PluginException
	 */
	protected Map<String, PluginService> createPluginServices(String file) throws PluginException {

		return PluginServiceFactory.getInstances(file);
	}

	/**
	 * Cuando se haga el refactor mencionado en la cabecera de la clase este metodo
	 * se podra usar dentro de los for loops nuevos
	 */
	public Iterator getPlugins(String pluginClass) {

		logger.debug("clase plugin a buscar " + pluginClass);
		PluginService service = (PluginService) services.get(pluginClass);

		if (service == null || service.getPlugins() == null) {
			logger.debug("plugins de servicio " + pluginClass + " no encontrados");
			return new Vector(0).iterator();
		}
		logger.debug("resultado " + service.getPlugins().toString());

		return service.getPlugins();
	}

}
