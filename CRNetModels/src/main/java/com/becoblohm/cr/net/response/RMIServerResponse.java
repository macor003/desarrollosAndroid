/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.response;

import java.io.Serializable;

/**
 */
public class RMIServerResponse implements Serializable {

	/**
	 * Field msg.
	 */
	private String msg;

	/**
	 * Field data.
	 */
	private Object data;

	/**
	 * Field code.
	 */
	private int code;

	/**
	 * Constructor for RMIServerResponse.
	 * @param msg String
	 * @param data Object
	 */
	public RMIServerResponse(String msg, Object data) {
		super();
		this.msg = msg;
		this.data = data;
		this.code = -1;
	}

	/**
	 * Constructor for RMIServerResponse.
	 * @param msg String
	 * @param data Object
	 * @param code int
	 */
	public RMIServerResponse(String msg, Object data, int code) {
		super();
		this.msg = msg;
		this.data = data;
		this.code = code;
	}

	/**
	 * Constructor for RMIServerResponse.
	 * @param code int
	 */
	public RMIServerResponse(int code) {
		super();
		this.code = code;
	}

	/**
	 * Constructor for RMIServerResponse.
	 */
	public RMIServerResponse() {
		super();
		this.msg = "";
		this.data = null;
		this.code = -1;
	}

	/**
	 * Method isOk.
	 * 
	
	 * @return boolean */
	public boolean isOk() {

		return (code == 0);
	}

	/**
	 * Method getMsg.
	 * 
	
	 * @return String */
	public String getMsg() {
		return msg;
	}

	/**
	 * Method setMsg.
	 * 
	 * @param msg
	 *            String
	 */
	public void setMsg(String msg) {
		this.msg = msg;
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
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	
	 * @return the code */
	public int getCode() {
		return code;
	}

}
