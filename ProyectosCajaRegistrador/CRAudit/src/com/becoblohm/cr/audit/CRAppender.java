/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.audit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ch.qos.logback.classic.db.DBAppender;
import ch.qos.logback.classic.db.DBHelper;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author programador11
 * @version 01
 * 
 *          This class extends from ch.qos.logback.classic.db.DBAppender and
 *          writes on the DB information of the caller class
 */
public class CRAppender extends DBAppender {

	/**
	 * Field caller.
	 */
	private CallerData caller;
	/**
	 * Field TIMESTMP_INDEX. (value is 1)
	 */
	static final int TIMESTMP_INDEX = 1;
	/**
	 * Field FORMATTED_MESSAGE_INDEX. (value is 2)
	 */
	static final int FORMATTED_MESSAGE_INDEX = 2;
	/**
	 * Field LOGGER_NAME_INDEX. (value is 3)
	 */
	static final int LOGGER_NAME_INDEX = 3;
	/**
	 * Field LEVEL_STRING_INDEX. (value is 4)
	 */
	static final int LEVEL_STRING_INDEX = 4;
	/**
	 * Field THREAD_NAME_INDEX. (value is 5)
	 */
	static final int THREAD_NAME_INDEX = 5;
	/**
	 * Field REFERENCE_FLAG_INDEX. (value is 6)
	 */
	static final int REFERENCE_FLAG_INDEX = 6;
	/**
	 * Field ARG0_INDEX. (value is 7)
	 */
	static final int ARG0_INDEX = 7;
	/**
	 * Field ARG1_INDEX. (value is 8)
	 */
	static final int ARG1_INDEX = 8;
	/**
	 * Field ARG2_INDEX. (value is 9)
	 */
	static final int ARG2_INDEX = 9;
	/**
	 * Field ARG3_INDEX. (value is 10)
	 */
	static final int ARG3_INDEX = 10;
	/**
	 * Field CALLER_FILENAME_INDEX. (value is 11)
	 */
	static final int CALLER_FILENAME_INDEX = 11;
	/**
	 * Field CALLER_CLASS_INDEX. (value is 12)
	 */
	static final int CALLER_CLASS_INDEX = 12;
	/**
	 * Field CALLER_METHOD_INDEX. (value is 13)
	 */
	static final int CALLER_METHOD_INDEX = 13;
	/**
	 * Field CALLER_LINE_INDEX. (value is 14)
	 */
	static final int CALLER_LINE_INDEX = 14;
	/**
	 * Field EVENT_ID_INDEX. (value is 15)
	 */
	static final int EVENT_ID_INDEX = 15;
	/**
	 * Field MAX_PARAMETERS. (value is 4)
	 */
	private static final int MAX_PARAMETERS = 4;
	/**
	 * Field MAX_SIZE_STRING. (value is 254)
	 */
	private static final int MAX_SIZE_STRING = 254;

	/**
	 * Method setCaller.
	 * 
	 * @param caller
	 *            CallerData
	 */
	public void setCaller(CallerData caller) {
		this.caller = caller;
	}

	/**
	 * Method getLastStackTraceElement. Obtains the caller of the logger through
	 * the LogMessage class
	 * 
	 * @param stackTrace
	 *            StackTraceElement[]
	 * 
	 * 
	 * 
	 * @return StackTraceElement
	 */
	public StackTraceElement getLastStackTraceElement(StackTraceElement stackTrace[]) {

		StackTraceElement lastElement = stackTrace[0];

		if (stackTrace != null) {

			for (int i = 2; i < stackTrace.length; i++) {
				// logger.debug(i+" "+stackTrace[i].getClassName());
				if (stackTrace[i].getClassName().startsWith("com.becoblohm.cr")
						&& !stackTrace[i].getClassName().equalsIgnoreCase(this.getClass().getName())
						&& !stackTrace[i].getClassName().equalsIgnoreCase("com.becoblohm.cr.audit.LogMessage")
						&& !stackTrace[i].getClassName().equalsIgnoreCase("com.becoblohm.cr.conf.Log")) {
					/*
					 * +2 para saltar logmessage y log en conf y que quede
					 * realmente la clase que llamo
					 */
					lastElement = stackTrace[i];
					// logger.debug(lastElement.getClassName());

					return lastElement;
				}

			}

		}
		// No debería suceder
		return null;

	}

	/**
	 * Method subAppend. Creates the prepared statements append to the database
	 * 
	 * @param event
	 *            ILoggingEvent
	 * @param connection
	 *            Connection
	 * @param insertStatement
	 *            PreparedStatement
	 * 
	 * 
	 * @throws Throwable
	 */
	@Override
	protected void subAppend(ILoggingEvent event, Connection connection, PreparedStatement insertStatement)
			throws Throwable {

		bindLoggingEventWithInsertStmt(insertStatement, event);
		bindLoggingEventArgumentsWithPreparedStmt(insertStatement, event.getArgumentArray());

		// This is expensive... should we do it every time?
		setCallerData(insertStatement, Thread.currentThread().getStackTrace());

		int updateCount = insertStatement.executeUpdate();
		if (updateCount != 1) {
			addWarn("Failed to insert loggingEvent");
		}
	}

	/**
	 * Method bindLoggingEventArgumentsWithPreparedStmt.
	 * 
	 * @param stmt
	 *            PreparedStatement
	 * @param argArray
	 *            Object[]
	 * 
	 * 
	 * @throws SQLException
	 */
	void bindLoggingEventArgumentsWithPreparedStmt(PreparedStatement stmt, Object[] argArray) throws SQLException {

		int arrayLen = 0;
		if (argArray != null) {
			arrayLen = argArray.length;
		}

		for (int i = 0; i < arrayLen && i < MAX_PARAMETERS; i++) {
			stmt.setString(ARG0_INDEX + i, stringTruncatedTo254(argArray[i]));
		}
		if (arrayLen < MAX_PARAMETERS) {
			for (int i = arrayLen; i < MAX_PARAMETERS; i++) {
				stmt.setString(ARG0_INDEX + i, null);
			}
		}
	}

	/**
	 * Method asStringTruncatedTo254.
	 * 
	 * @param object
	 *            Object
	 * 
	 * 
	 * @return String
	 */
	String stringTruncatedTo254(Object object) {
		String result = null;
		if (object != null) {
			result = object.toString();
		}

		if (result == null) {
			return null;
		}
		if (result.length() <= MAX_SIZE_STRING) {
			return result;
		} else {
			return result.substring(0, MAX_SIZE_STRING);
		}
	}

	/**
	 * Method bindLoggingEventWithInsertStmt. Binds the logging event with the
	 * prepared statement
	 * 
	 * @param stmt
	 *            PreparedStatement
	 * @param event
	 *            ILoggingEvent
	 * 
	 * 
	 * @throws SQLException
	 */
	public void bindLoggingEventWithInsertStmt(PreparedStatement stmt, ILoggingEvent event) throws SQLException {
		stmt.setLong(TIMESTMP_INDEX, event.getTimeStamp());
		stmt.setString(FORMATTED_MESSAGE_INDEX, event.getFormattedMessage() == null ? "" : event.getFormattedMessage());
		stmt.setString(LOGGER_NAME_INDEX, event.getLoggerName());
		stmt.setString(LEVEL_STRING_INDEX, event.getLevel().toString());
		stmt.setString(THREAD_NAME_INDEX, event.getThreadName());
		stmt.setShort(REFERENCE_FLAG_INDEX, DBHelper.computeReferenceMask(event));

	}

	/**
	 * Method setCallerData. Binds the data from the caller to the prepared
	 * statement
	 * 
	 * @param stmt
	 *            PreparedStatement
	 * @param callerDataArray
	 *            StackTraceElement[]
	 * 
	 * 
	 * @throws SQLException
	 */
	public void setCallerData(PreparedStatement stmt, StackTraceElement[] callerDataArray) throws SQLException {

		StackTraceElement callerData = getLastStackTraceElement(callerDataArray);
		if (callerData != null) {
			stmt.setString(CALLER_FILENAME_INDEX, callerData.getFileName());
			stmt.setString(CALLER_CLASS_INDEX, callerData.getClassName());
			stmt.setString(CALLER_METHOD_INDEX, callerData.getMethodName());
			stmt.setString(CALLER_LINE_INDEX, Integer.toString(callerData.getLineNumber()));
		}
	}

}
