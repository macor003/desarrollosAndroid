/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.epa.mvc.core;

import java.awt.event.ActionEvent;

/**
 * @author programador6
 * @version $Revision: 1.0 $
 */
public class AbstractEvent extends ActionEvent {

	/**
	 * Constructor for AbstractEvent.
	 * 
	 * @param source
	 *            Object
	 * @param id
	 *            int
	 * @param command
	 *            java.lang.String
	 */
	public AbstractEvent(Object source, int id, java.lang.String command) {
		super(source, id, command);

	}

}
