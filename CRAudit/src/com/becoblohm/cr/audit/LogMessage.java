/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author programador11
 * @version 01
 */
public class LogMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7582857005922969521L;
	/**
	 * Field logger.
	 */
	private Logger logger;

	/**
	 * The Logger of the clas * @param sender Object s
	 * 
	 * @param sender
	 *            Object
	 */

	/**
	 * Constructor for LogMessage. Receives the caller's name class
	 * 
	 * @param sender
	 *            Object
	 */
	public LogMessage(Object sender) {
		this.logger = LoggerFactory.getLogger(sender.getClass());
	}

	/**
	 * Constructor for LogMessage. Receives the caller's name class
	 * 
	 * @param sender
	 *            String
	 */
	public LogMessage(String sender) {

		this.logger = LoggerFactory.getLogger(sender);
	}

	/**
	 * Method sendError. Sends an error message to the logger
	 * 
	 * @param message
	 *            String
	 */
	public void error(String message) {

		this.logger.error(message);

	}

	/**
	 * Method sendInfo. Sends an info message to the logger
	 * 
	 * @param message
	 *            String
	 */
	public void info(String message) {

		this.logger.info(message);

	}

	/**
	 * Method sendDebug. Sends a debug message to the logger
	 * 
	 * @param message
	 *            String
	 */
	public void debug(String message) {

		this.logger.debug(message);
	}

	/**
	 * Method debug.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Object
	 */
	public void debug(String arg0, Object arg1) {

		this.logger.debug(arg0, arg1);

	}

	/**
	 * Method debug.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Object[]
	 */
	public void debug(String arg0, Object[] arg1) {
		this.logger.debug(arg0, arg1);
	}

	/**
	 * Method debug.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Throwable
	 */
	public void debug(String arg0, Throwable arg1) {
		this.logger.debug(arg0, arg1);
	}

	/**
	 * Method debug.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Object
	 * @param arg2
	 *            Object
	 */
	public void debug(String arg0, Object arg1, Object arg2) {
		this.logger.debug(arg0, arg1, arg2);
	}

	/**
	 * Method error.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Object
	 */
	public void error(String arg0, Object arg1) {
		this.logger.error(arg0, arg1);

	}

	/**
	 * Method error.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Object[]
	 */
	public void error(String arg0, Object[] arg1) {
		this.logger.error(arg0, arg1);

	}

	/**
	 * Method error.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Throwable
	 */
	public void error(String arg0, Throwable arg1) {
		this.logger.error(arg0, arg1);

	}

	/**
	 * Method error.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Object
	 * @param arg2
	 *            Object
	 */
	public void error(String arg0, Object arg1, Object arg2) {
		this.logger.error(arg0, arg1, arg2);

	}

	/**
	 * Method getName.
	 * 
	 * 
	 * @return String
	 */
	public String getName() {
		return this.logger.getName();

	}

	/**
	 * Method info.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Object
	 */
	public void info(String arg0, Object arg1) {
		this.logger.info(arg0, arg1);

	}

	/**
	 * Method info.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Object[]
	 */
	public void info(String arg0, Object[] arg1) {
		this.logger.info(arg0, arg1);

	}

	/**
	 * Method info.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Throwable
	 */
	public void info(String arg0, Throwable arg1) {
		this.logger.info(arg0, arg1);

	}

	/**
	 * Method info.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Object
	 * @param arg2
	 *            Object
	 */
	public void info(String arg0, Object arg1, Object arg2) {
		this.logger.info(arg0, arg1);

	}

	/**
	 * Method isDebugEnabled.
	 * 
	 * 
	 * @return boolean
	 */
	public boolean isDebugEnabled() {

		return this.logger.isDebugEnabled();
	}

	/**
	 * Method isErrorEnabled.
	 * 
	 * 
	 * @return boolean
	 */
	public boolean isErrorEnabled() {
		return this.logger.isErrorEnabled();
	}

	/**
	 * Method isInfoEnabled.
	 * 
	 * 
	 * @return boolean
	 */
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	/**
	 * Method isTraceEnabled.
	 * 
	 * 
	 * @return boolean
	 */
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	/**
	 * Method isWarnEnabled.
	 * 
	 * 
	 * @return boolean
	 */
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	/**
	 * Method trace.
	 * 
	 * @param arg0
	 *            String
	 */
	public void trace(String arg0) {
		logger.trace(arg0);

	}

	/**
	 * Method trace.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Object
	 */
	public void trace(String arg0, Object arg1) {
		logger.trace(arg0, arg1);

	}

	/**
	 * Method trace.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Object[]
	 */
	public void trace(String arg0, Object[] arg1) {
		logger.trace(arg0, arg1);

	}

	/**
	 * Method trace.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Throwable
	 */
	public void trace(String arg0, Throwable arg1) {
		logger.trace(arg0, arg1);

	}

	/**
	 * Method trace.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Object
	 * @param arg2
	 *            Object
	 */
	public void trace(String arg0, Object arg1, Object arg2) {
		logger.trace(arg0, arg1, arg2);

	}

	/**
	 * Method warn.
	 * 
	 * @param arg0
	 *            String
	 */
	public void warn(String arg0) {
		logger.warn(arg0);

	}

	/**
	 * Method warn.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Object
	 */
	public void warn(String arg0, Object arg1) {
		logger.warn(arg0, arg1);

	}

	/**
	 * Method warn.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Object[]
	 */
	public void warn(String arg0, Object[] arg1) {
		logger.warn(arg0, arg1);

	}

	/**
	 * Method warn.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Throwable
	 */
	public void warn(String arg0, Throwable arg1) {
		logger.warn(arg0, arg1);

	}

	/**
	 * Method warn.
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Object
	 * @param arg2
	 *            Object
	 */
	public void warn(String arg0, Object arg1, Object arg2) {
		logger.warn(arg0, arg1, arg2);

	}

}
