/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.epa.plugins.core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epa.plugins.exception.PluginException;
import com.epa.properties.layer.SimpleProperties;
import com.epa.util.ClasspathUtils;

/**
 * @author programador6
 * @version $Revision: 1.0 $
 */
public class PluginServiceFactory {
	/**
	 * Field FILE_PROPERTIES.
	 */
	protected String FILE_PROPERTIES = "pluginservice.properties";
	/**
	 * Field logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(PluginServiceFactory.class);

	/**
	 * Method addPluginJarsToClasspath.
	 */
	public static void addPluginJarsToClasspath() {
		try {
			// add the plugin directory to classpath
			ClasspathUtils.addDirToClasspath(new File("plugins"));
		} catch (IOException ex) {
			logger.debug("problemas al agregar plugins al classpath");
		}
	}

	/**
	 * Method getInstance.
	 * 
	 * @param file
	 *            String
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @return PluginService * @throws ClassNotFoundException * @throws
	 *         InstantiationException * @throws IllegalAccessException * @throws
	 *         IOException * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	public static PluginService getInstance(String file)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		String pluginservice = "";
		SimpleProperties props = new SimpleProperties(file);
		pluginservice = props.getProperty("pluginservice");

		logger.debug("---------->" + pluginservice);
		PluginService service = (PluginService) Class.forName(pluginservice).newInstance();

		return service;
	}

	/**
	 * Method getInstances.
	 * 
	 * @param file
	 *            String
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @return HashMap<String,PluginService> * @throws PluginException * @throws
	 *         PluginException
	 */
	public static Map<String, PluginService> getInstances(String file) throws PluginException {
		Map<Object, Object> props;
		Map<String, PluginService> services = new HashMap<String, PluginService>();

		logger.debug("---------->" + file);
		try {
			props = new SimpleProperties(file);

			for (Iterator iterator = props.keySet().iterator(); iterator.hasNext();) {
				String serviceclass = (String) iterator.next();
				logger.debug("---------->" + serviceclass);
				PluginService service = (PluginService) Class.forName(serviceclass).newInstance();
				services.put(serviceclass, service);
			}

		} catch (IOException e) {

			logger.error("Exception getting plugins instance");
			e.printStackTrace();
			throw new PluginException();
		} catch (InstantiationException e) {

			logger.error("Exception getting plugins instance");
			throw new PluginException();
		} catch (IllegalAccessException e) {

			logger.error("Exception getting plugins instance");
			throw new PluginException();
		} catch (ClassNotFoundException e) {

			logger.error("Exception getting plugins instance");
			throw new PluginException();
		}

		return services;
	}
}
