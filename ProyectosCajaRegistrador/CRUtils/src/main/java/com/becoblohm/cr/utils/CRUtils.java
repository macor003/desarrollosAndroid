/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.types.CRBigDecimal;

/**
 */
public class CRUtils {

	/**
	 * Field logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(CRUtils.class);

	/**
	 * Method buildPropertiesVector.
	 * 
	 * @param properties
	 *            String
	 * @return Vector
	 */
	public static Vector buildPropertiesVector(String properties) {
		Vector result = new Vector();
		StringTokenizer tk = new StringTokenizer(properties, ",");

		while (tk.hasMoreTokens()) {
			String tmp = tk.nextToken();
			result.add(tmp.trim());
		}

		return result;
	}

	/**
	 * Method buildPropertiesHashVectorDecision.
	 * 
	 * @param properties
	 *            String
	 * @return HashMap
	 */
	public static HashMap buildPropertiesHashVectorDecision(String properties) {
		StringTokenizer tk = new StringTokenizer(properties, ":");
		HashMap result = new HashMap();

		String key = null;
		String value = null;
		Vector decision = null;
		while (tk.hasMoreTokens()) {
			String tmp = tk.nextToken();
			StringTokenizer keys = new StringTokenizer(tmp, "@");
			key = null;
			value = null;
			decision = null;

			if (keys.hasMoreTokens()) {
				key = keys.nextToken();
			}

			if (keys.hasMoreTokens()) {
				value = keys.nextToken();
				decision = new Vector();
				StringTokenizer des = new StringTokenizer(value, ",");
				while (des.hasMoreTokens()) {
					String val = des.nextToken();
					decision.add(val);

				}
			}

			if (key != null && decision != null) {
				result.put(key.trim(), decision);

			}

		}

		return result;
	}

	/**
	 * Method buildPropertiesHashDecision.
	 * 
	 * @param properties
	 *            String
	 * @return HashMap
	 */
	public static HashMap buildPropertiesHashDecision(String properties) {
		StringTokenizer tk = new StringTokenizer(properties, ",");
		HashMap result = new HashMap();

		while (tk.hasMoreTokens()) {
			String tmp = tk.nextToken();
			StringTokenizer keys = new StringTokenizer(tmp, "@");
			String key = null;
			String value = null;
			if (keys.hasMoreTokens()) {
				key = keys.nextToken();
			}
			if (keys.hasMoreTokens()) {
				value = keys.nextToken();
			}
			if (key != null && value != null) {
				result.put(key.trim(), value.trim());
			}

		}

		return result;
	}

	/**
	 * Method findResource.
	 * 
	 * @param resource
	 *            String
	 * @param defaultPath
	 *            String
	 * @param clase
	 *            Class
	 * 
	 * 
	 * 
	 * @return URL * @throws MalformedURLException
	 */
	public static URL findResource(final String resource, String defaultPath, final Class clase) {
		URL propURL = null;

		propURL = Thread.currentThread().getContextClassLoader().getResource(resource);
		if (propURL != null) {
			logger.debug("Encontrado recurso: " + resource + " en ContextClassLoader del Thread");
		}

		if (propURL == null) {
			String propsFile = System.getProperty("user.home") + File.separator + resource;
			File file = new File(propsFile);
			if (file.exists() && file.isFile()) {
				try {
					propURL = file.toURL();
				} catch (MalformedURLException e) {

					e.printStackTrace();
				}
			} else {
				propURL = null;
			}
			if (propURL != null) {
				logger.debug("Encontrado recurso en user.home: " + propsFile);
			}
		}

		// Busco en la ruta por defecto
		if (propURL == null && defaultPath != null) {
			if (defaultPath.length() > 0 && defaultPath.charAt(defaultPath.length() - 1) != '/'
					&& defaultPath.charAt(defaultPath.length() - 1) != '\\') {
				defaultPath = defaultPath.concat(File.separator);
			}
			File propsFile = new File(defaultPath + resource);
			if (propsFile.exists() && propsFile.isFile()) {
				try {
					propURL = propsFile.toURL();
				} catch (MalformedURLException e) {

					e.printStackTrace();
				}
			} else {
				propURL = null;
			}
			if (propURL != null) {
				logger.debug("Encontrado recurso en defaultPath: " + propsFile);
			}
		}

		if (propURL == null) {
			File propsFile = new File(resource);
			if (propsFile.exists() && propsFile.isFile()) {
				try {
					propURL = propsFile.toURL();
				} catch (MalformedURLException e) {

					e.printStackTrace();
				}
			} else {
				propURL = null;
			}
			if (propURL != null) {
				logger.debug("Encontrado recurso en user.dir: " + propsFile);
			}
		}

		// Busco como recurso del sistema en el classLoader de esta clase
		if (clase != null) {
			if (propURL == null) {
				propURL = clase.getClassLoader().getResource(resource);
				if (propURL != null) {
					logger.debug("Encontrado recurso en ClassLoader de la Clase '" + clase.getPackage().getName() + "."
							+ clase.getName() + "': " + propURL);
				}
			}

			// Busco como recurso del sistema en el classpath
			if (propURL == null) {
				propURL = clase.getResource(resource);
				if (propURL != null) {
					logger.debug("Encontrado recurso en ClassPath de la Clase '" + clase.getPackage().getName() + "."
							+ clase.getName() + "': " + propURL);
				}
			}
		}
		// Busco como recurso del sistema en el classLoader de esta clase
		if (propURL == null) {
			propURL = CRUtils.class.getClassLoader().getResource(resource);
			if (propURL != null) {
				logger.debug("Encontrado recurso en ClassLoader del EpaComun: " + propURL);
			}
		}

		// Busco como recurso del sistema en el classpath
		if (propURL == null) {
			propURL = CRUtils.class.getResource(resource);
			if (propURL != null) {
				logger.debug("Encontrado recurso en Class del EpaComun: " + propURL);
			}
		}
		// Busco como recurso del sistema en el classpath
		if (propURL == null) {
			propURL = ClassLoader.getSystemResource(resource);
			if (propURL != null) {
				logger.debug("Encontrado recurso en  ClassLoader.getSystemResource: " + propURL);
			}
		}

		if (propURL == null) {
			logger.debug("NO SE ENCONTRO RECURSO: " + resource);
		}
		return propURL;
	}

	/**
	 * Method removeSpecialChars.
	 * 
	 * @param text
	 *            String
	 * @return String
	 */
	public static String removeSpecialChars(String text) {

		return removeSpecialChars(text, "");

	}

	/**
	 * Compares if two dates belong to the same day
	 * 
	 * @param date1
	 *            Date
	 * @param date2
	 *            Date
	 * @return boolean
	 */
	public static boolean isSameDay(Date date1, Date date2) {

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);

		return sameDay;

	}

	/**
	 * Method bigdecimalToCalendar.
	 * 
	 * @param date
	 *            String
	 * @return Calendar
	 */
	public static Calendar bigdecimalToCalendar(String date) {

		Calendar calendar = Calendar.getInstance();
		String dateStr = date;
		String year = dateStr.substring(0, 4);
		String month = dateStr.substring(4, 6);
		String day = dateStr.substring(6, 8);
		String hour = dateStr.substring(8, 10);
		String minute = dateStr.substring(10, 12);
		String second = dateStr.substring(12, 14);
		String milli = dateStr.substring(15, dateStr.length());

		calendar.set(Calendar.YEAR, Integer.parseInt(year));
		calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
		calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
		calendar.set(Calendar.SECOND, Integer.parseInt(second));
		// calendar.set(Calendar.MILLISECOND, Integer.parseInt(milli));

		// SimpleDateFormat sdf = new
		// SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SS");
		// String fecha = sdf.format(calendar.getTime());
		// logger.debug(fecha);

		return calendar;
	}

	/**
	 * Compares if two dates belong to the same month
	 * 
	 * @param date1
	 *            Date
	 * @param date2
	 *            Date
	 * @return int
	 */
	public static int compareMonths(Date date1, Date date2) {
		int response;
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);

		if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
			if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
				response = 0;
			} else if (cal1.get(Calendar.MONTH) > cal2.get(Calendar.MONTH)) {
				response = 1;
			} else {
				response = -1;
			}
		} else if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) {
			response = 1;
		} else {
			response = -1;
		}

		return response;

	}

	/*
	 * public static String getEncoded(String texto, String algoritmo) { String
	 * output=""; try { byte[] textBytes = texto.getBytes(); MessageDigest md =
	 * MessageDigest.getInstance(algoritmo); md.update(textBytes); byte[] codigo =
	 * md.digest(); output = new String(codigo); } catch (NoSuchAlgorithmException
	 * ex) { logger.warn(ex.toString()); } logger.debug("encoded "+output); return
	 * output; }
	 */

	/**
	 * Field HEXADECIMAL.
	 */
	private static final char[] HEXADECIMAL = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	/**
	 * Method encode.
	 * 
	 * @param stringToHash
	 *            String
	 * @return String
	 */
	public static String encode(String stringToHash) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(stringToHash.getBytes());
			StringBuilder sb = new StringBuilder(2 * bytes.length);
			int low = 0;
			int high = 0;
			for (int i = 0; i < bytes.length; i++) {
				low = (bytes[i] & 0x0f);
				high = ((bytes[i] & 0xf0) >> 4);
				sb.append(HEXADECIMAL[high]);
				sb.append(HEXADECIMAL[low]);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// exception handling goes here
			return null;
		}
	}

	/**
	 * Method removeSpecialChars.
	 * 
	 * @param text
	 *            String
	 * @param regex
	 *            String
	 * @return String
	 */
	public static String removeSpecialChars(String text, String regex) {
		String alphaOnly = "[^a-zA-Z]+";
		String alphaAndDigits = "[^a-zA-Z0-9]+";
		String ascii = "[^\\p{ASCII}" + regex + "]";

		String strippedString = new String();
		strippedString = Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll(ascii, "");
		// logger.debug("Texto normalizado" + strippedString);
		strippedString = strippedString.replaceAll(ascii, "");
		// logger.debug("Texto con ascii remplazado" + strippedString);

		return strippedString;

	}

	/**
	 * Method getDaysUntilToday.
	 * 
	 * @param date
	 *            Date
	 * @return long
	 */
	public static long getDaysUntilToday(Date date) {

		logger.debug("Validando diferencia en dias desde: " + date);

		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; // Milisegundos al
															// día

		long diferencia = (new Date().getTime() - date.getTime()) / MILLSECS_PER_DAY;

		logger.debug("diferencia: " + diferencia);

		return diferencia;
	}

	/**
	 * Method generateIpaId.
	 * 
	 * @param store
	 *            Long
	 * @param pos
	 *            Long
	 * @return long
	 */
	public static long generateIpaId(Integer store, Integer pos) {
		if (store == null)
			store = 0;
		if (pos == null)
			pos = 0;
		return Long
				.valueOf("" + System.currentTimeMillis() + String.format("%02d", store) + String.format("%02d", pos));
	}

	/**
	 * Method generateIpaId.
	 * 
	 * @return long
	 */
	public static long generateIpaId() {
		return generateIpaId(null, null);
	}

	public static long generateIpaIdComparison(Integer store, Integer pos) {
		return Long.valueOf("" + generateIpaId(store, pos) + String.format("%02d", 0));
	}

	/**
	 * Valida la cantidad que la cantidad a agregar de una articulo no sobrepase el
	 * maximo pertido por trasaccion
	 * 
	 * @param article
	 *            Article articulo al que se le va a aunmentar la cantidad en la
	 *            venta
	 * @param quantity
	 *            CRBigDecimal cantidad
	 * @return boolean true si la contidad de articulos es menor o igual que la
	 *         cantidad configurada por transaccion y false en caso contrario
	 */
	public static boolean validateTransaccionItems(Article article, CRBigDecimal quantity) {
		logger.debug("validando cantiad de articulos por transaccion ");
		if (article.getBuyPeriod() != 0) {
			logger.debug("periodo es distinto de cero");

			if (article.getAmountMaxTransaction().compareTo(quantity) < 0) {
				return false;
			}
		}

		return true;
	}

	public static boolean dateIsBetween(Date datetoBeCompared, Date from, Date to) {
		return from.compareTo(datetoBeCompared) * datetoBeCompared.compareTo(to) > 0;

	}
}
