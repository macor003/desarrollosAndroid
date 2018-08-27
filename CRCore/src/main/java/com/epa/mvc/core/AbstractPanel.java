/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.epa.mvc.core;

import java.beans.PropertyChangeEvent;

/**
 * @author programador6
 * @version $Revision: 1.0 $
 */
public interface AbstractPanel {
	/**
	 * Field logger.
	 * 
	 * @param arg0
	 *            PropertyChangeEvent
	 */

	/**
	 * Method modelChanged.
	 * 
	 * @param arg0
	 *            PropertyChangeEvent
	 */
	public abstract void modelChanged(PropertyChangeEvent arg0);

	/**
	 * Method getPanel.
	 * 
	 * 
	 * @return AbstractPanel
	 */
	public abstract AbstractPanel getPanel();

	/**
	 * Method setParentController.
	 * 
	 * @param parentController
	 *            AbstractController
	 */
	public abstract void setParentController(AbstractController parentController);

	/**
	 * Method setModel.
	 * 
	 * @param model
	 *            AbstractModel
	 */
	public abstract void setModel(AbstractModel model);

	/**
	 * Method init.
	 */
	abstract public void init();

	/**
	 * Method refresh.
	 */
	abstract public void refresh();

}
