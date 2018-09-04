/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.epa.plugins.core;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author programador6
 * @version $Revision: 1.0 $
 */
public class StandardPluginService implements PluginService {
	/**
	 * Field pluginService.
	 */
	private static StandardPluginService pluginService;

	/**
	 * Field serviceLoader.
	 */
	private final ServiceLoader<Plugin> serviceLoader;
	/**
	 * Field logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(StandardPluginService.class);
	/**
	 * Field pluginsClass.
	 */
	protected Class pluginsClass;

	/*
	 * El plugin service standard busca todos los jars que implementen la interface
	 * application plugin, cada plugin a su vez buscara los que implementen su
	 * respectiva interfaz
	 */
	/**
	 * Constructor for StandardPluginService.
	 */
	protected StandardPluginService() {
		// load all the classes in the classpath that have implemented the
		// interface
		logger.debug("Cargando plugins de interfaz " + this.pluginsClass.toString());
		this.serviceLoader = ServiceLoader.load(this.pluginsClass);

	}

	/**
	 * Constructor for StandardPluginService.
	 * 
	 * @param pluginsClass
	 *            Class
	 */
	protected StandardPluginService(Class pluginsClass) {
		// load all the classes in the classpath that have implemented the
		// interface
		logger.debug("Cargando plugins de interfaz " + pluginsClass.toString());
		this.serviceLoader = ServiceLoader.load(pluginsClass);

	}

	/**
	 * Method getInstance.
	 * 
	 * 
	 * @return StandardPluginService
	 */
	public static StandardPluginService getInstance() {
		if (pluginService == null) {
			pluginService = new StandardPluginService();
		}
		return pluginService;
	}

	/**
	 * Method getPlugins.
	 * 
	 * 
	 * 
	 * @return Iterator<Plugin> * @see
	 *         com.epa.plugins.core.PluginService#getPlugins() * @see
	 *         com.epa.plugins.core.PluginService#getPlugins() * @see
	 *         com.epa.plugins.core.PluginService#getPlugins()
	 */
	@Override
	public Iterator<Plugin> getPlugins() {
		return this.serviceLoader.iterator();
	}

	/**
	 * Method initPlugins.
	 * 
	 * 
	 * @see com.epa.plugins.core.PluginService#initPlugins()
	 */
	@Override
	public void initPlugins() {
		Iterator<Plugin> iterator = getPlugins();
		if (!iterator.hasNext()) {
			logger.info("No plugins were found!");
		}
		Plugin plugin;
		while (iterator.hasNext()) {
			plugin = iterator.next();
			logger.info("Initializing the plugin " + plugin.getName());
		}
	}

	/**
	 * Method getPluginsClass.
	 * 
	 * 
	 * @return Class
	 */
	public Class getPluginsClass() {
		return this.pluginsClass;
	}

	/**
	 * Method setPluginsClass.
	 * 
	 * @param pluginsClass
	 *            Class
	 */
	public void setPluginsClass(Class pluginsClass) {
		this.pluginsClass = pluginsClass;
	}

}
