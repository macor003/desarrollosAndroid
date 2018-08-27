/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.epa.mvc.core;

/**
 * @author programador6
 * @version $Revision: 1.0 $
 */
public interface StateMachineInterface {

	/**
	 * Method handleEvent.
	 * 
	 * @param event
	 *            ActionEvent
	 */
	public void handleEvent(AbstractEvent event);

}
