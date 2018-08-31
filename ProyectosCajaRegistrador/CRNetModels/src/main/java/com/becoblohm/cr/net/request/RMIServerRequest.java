/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.request;

import java.io.Serializable;

import com.becoblohm.cr.net.models.Session;

/**
 */
public class RMIServerRequest implements Serializable {

	/**
	 * Field type.
	 */
	int type;

	/**
	 * Field data.
	 */
	Object data;

	/**
	 * Field session.
	 */
	Session session;

	/**
	 * 
	
	 * @param data
	 * @param type int
	 * @param session Session
	 */
	public RMIServerRequest(int type, Object data, Session session) {
		super();
		this.type = type;
		this.data = data;
		this.session = session;

	}

	/**
	 * 
	
	
	 */
	/*
	 * public ServicesRequest(String msg, int type, Object data, Session
	 * session) { super(); this.msg = msg; this.type = type; this.data = data;
	 * this.session = session;
	 * 
	 * }
	 */

	/**
	 * 
	 * @param msg
	 * @param data
	 */
	public RMIServerRequest() {
		super();
		this.type = 0;
		this.data = null;
		this.session = null;
	}

	/**
	 * Method isOk.
	 * 
	
	 * @return boolean */
	public boolean isOk() {

		return (this.data != null);
	}

	/**
	 * Method getData.
	 * 
	
	 * @return Object */
	public Object getData() {
		return data;
	}

	/**
	 * Method setData.
	 * 
	 * @param data
	 *            Object
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * Method getType.
	 * @return int
	 */
	public int getType() {
		return type;
	}

	/**
	 * Method setType.
	 * @param type int
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Method getSession.
	 * @return Session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * Method setSession.
	 * @param session Session
	 */
	public void setSession(Session session) {
		this.session = session;
	}

}
