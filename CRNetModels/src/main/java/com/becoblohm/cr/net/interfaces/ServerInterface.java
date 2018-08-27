/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.becoblohm.cr.net.request.RMIServerRequest;
import com.becoblohm.cr.net.response.RMIServerResponse;

/**
 */
public interface ServerInterface extends Remote {
	/**
	 * Method isOnline.
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean isOnline() throws RemoteException;

	/**
	 * Method checkServices.
	 * @param request RMIServerRequest
	 * @return RMIServerResponse
	 * @throws RemoteException
	 */
	public RMIServerResponse checkServices(RMIServerRequest request) throws RemoteException;

}
