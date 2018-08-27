/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2010, QOS.ch. All rights reserved.
 * 
 * This program and the accompanying materials are dual-licensed under either
 * the terms of the Eclipse Public License v1.0 as published by the Eclipse
 * Foundation
 * 
 * or (per the licensee's choosing)
 * 
 * under the terms of the GNU Lesser General Public License version 2.1 as
 * published by the Free Software Foundation.
 */
//package ch.qos.logback.classic.db.names;
package com.becoblohm.cr.audit;

import ch.qos.logback.classic.db.names.DBNameResolver;

/**
 */
public class CustomDBNameResolver implements DBNameResolver {
	/**
	 * Method getTableName.
	 * 
	 * @param tableName
	 *            N
	 * @return String
	 * @see ch.qos.logback.classic.db.names.DBNameResolver#getTableName(N)
	 */
	@Override
	public <N extends Enum<?>> String getTableName(N tableName) {
		if (tableName.toString().equalsIgnoreCase("logging_event")) {
			// return "loge";
			return "auditoria";
		}
		if (tableName.toString().equalsIgnoreCase("logging_event_property")) {
			// return "logep";
			return "auditoriadetalle";
		}
		if (tableName.toString().equalsIgnoreCase("logging_event_exception")) {
			// return "logee";
			return "auditoriaexcepcion";
		}
		return tableName.toString().toLowerCase();
	}

	/**
	 * Method getColumnName.
	 * 
	 * @param columnName
	 *            N
	 * @return String
	 * @see ch.qos.logback.classic.db.names.DBNameResolver#getColumnName(N)
	 */
	@Override
	public <N extends Enum<?>> String getColumnName(N columnName) {
		return columnName.toString().toLowerCase();
	}

}
