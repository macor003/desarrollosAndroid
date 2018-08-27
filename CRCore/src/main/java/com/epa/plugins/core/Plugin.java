/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.epa.plugins.core;

import java.util.Collection;

import com.epa.mvc.core.AbstractController;
import com.epa.mvc.core.AbstractEvent;
import com.epa.mvc.core.StateMachineInterface;

/**
 * @author programador6
 * @version $Revision: 1.0 $
 */
public interface Plugin {

	/**
	 * Method getName.
	 * 
	 * 
	 * @return String
	 */
	public String getName();

	/**
	 * Method setStateMachine.
	 * 
	 * @param sm
	 *            StateMachineInterface
	 */
	public void setStateMachine(StateMachineInterface sm);

	/**
	 * Method setLastEvent.
	 * 
	 * @param event
	 *            AbstractEvent
	 */
	public void setLastEvent(AbstractEvent event);

	/**
	 * Method getController.
	 * 
	 * 
	 * @return AbstractController
	 */
	public AbstractController getController();

	/**
	 * Method StartUp.
	 */
	public void init();

	/**
	 * Method ShutDown.
	 */
	public void finish();

	/**
	 * Method IsActive.
	 * 
	 * 
	 * @return boolean
	 */
	public boolean isActive();

	/**
	 * Method getEvents.
	 * 
	 * 
	 * @return Collection<AbstractEvent>
	 */
	public Collection<AbstractEvent> getEvents();

	/**
	 * Method getActions.
	 * 
	 * @param event
	 *            AbstractEvent
	 * 
	 * @return Collection
	 */
	public Collection getActions(AbstractEvent event);

}
