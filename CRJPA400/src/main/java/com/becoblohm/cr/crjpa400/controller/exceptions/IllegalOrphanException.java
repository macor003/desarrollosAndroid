/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa400.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class IllegalOrphanException extends Exception {
	/**
	 * Field messages.
	 */
	private List<String> messages;

	/**
	 * Constructor for IllegalOrphanException.
	 * 
	 * @param messages
	 *            List<String>
	 */
	public IllegalOrphanException(List<String> messages) {
		super((messages != null && messages.size() > 0 ? messages.get(0) : null));
		if (messages == null) {
			this.messages = new ArrayList<String>();
		} else {
			this.messages = messages;
		}
	}

	/**
	 * Method getMessages.
	 * 
	 * @return List<String>
	 */
	public List<String> getMessages() {
		return messages;
	}
}
