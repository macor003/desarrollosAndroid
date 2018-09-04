/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.mvc.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;

/**
 * La clase <b>AbstractController</b> debe heredarse para representar un
 * <b>controlador</b> en el esquema <b>MVC</b>. Esta contiene los metodos
 * necesarios para acoplar modelos (Objetos que hereden {@link AbstractModel}) y
 * vistas (Objetos que hereden {@link AbstractView}).
 * <p>
 * El controlador se registra, como ActionListener y PropertyChangeListener, con
 * los modelos y vistas que se agreguen a este, y se encarga de propagar los
 * cambios de propiedad entre los modelos y las vistas.
 * <p>
 * Si se le asigna una <b>Maquina de Estado</b> ({@link StateMachineInterface}),
 * el controlador se encarga de pasar los eventos no manejados localmente hacia
 * dicha <b>Maquina de Estado</b>.
 * 
 * @author Jesus Aguilera (programador10), Eduardo Lugo (programador6)
 * @version 0.1
 */

public abstract class AbstractController implements PropertyChangeListener {
	/**
	 * Field views.
	 */
	protected Collection<AbstractView> views = new ArrayList<AbstractView>();
	/**
	 * Field models.
	 */
	protected Collection<AbstractModel> models = new ArrayList<AbstractModel>();
	/**
	 * Field me.
	 */
	protected StateMachineInterface stateMachine = null;
	/**
	 * Field lastEvent.
	 */
	protected AbstractEvent lastEvent = null;

	/**
	 * Field logger.
	 * 
	 * @param view
	 *            AbstractView
	 */

	/**
	 * Method addViews.
	 * 
	 * @param view
	 *            AbstractView
	 */
	public void addViews(AbstractView view) {
		this.views.add(view);
	}

	/**
	 * Method addModels.
	 * 
	 * @param model
	 *            AbstractModel
	 */
	public void addModels(AbstractModel model) {
		this.models.add(model);

	}

	/**
	 * Method propertyChange.
	 * 
	 * @param evt
	 *            PropertyChangeEvent
	 * 
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// logger.debug("model changed!");
		for (AbstractView view : this.views) {
			view.modelChanged(evt);
		}

	}

	/**
	 * Method setStateMachine.
	 * 
	 * @param sm
	 *            StateMachineInterface
	 */
	public void setStateMachine(StateMachineInterface sm) {
		this.stateMachine = sm;
	}

	/**
	 * Method fireEvent.
	 * 
	 * @param event
	 *            ActionEvent
	 */
	public void fireEvent(AbstractEvent event) {
		if (this.stateMachine != null) {
			this.stateMachine.handleEvent(event);
		}
	}

	/**
	 * Method getModels.
	 * 
	 * 
	 * @return Collection<AbstractModel>
	 */
	public Collection<AbstractModel> getModels() {
		return this.models;
	}

	/**
	 * Method getMainView.
	 * 
	 * 
	 * @return AbstractView
	 */
	public abstract AbstractView getMainView();

	/**
	 * Method initProcess.
	 * 
	 * @param o
	 *            Object
	 */
	public abstract void initProcess(Object o);

	/**
	 * Method executeProcess.
	 * 
	 * @param o
	 *            Object
	 * 
	 * @return int
	 */
	public abstract int executeProcess(Object o);

	/**
	 * Method finishProcess.
	 * 
	 * @param o
	 *            Object
	 */
	public abstract void finishProcess(Object o);

	/**
	 * Method cancelProcess.
	 * 
	 * @param o
	 *            Object
	 */
	public abstract void cancelProcess(Object o);

	/**
	 * Method handleDelete.
	 * 
	 * @param m
	 *            AbstractModel
	 * 
	 * 
	 * @return boolean
	 */
	public boolean handleDelete(AbstractModel m) {
		return false;
	}

	/**
	 * Method handleUpdate.
	 * 
	 * @param m
	 *            AbstractModel
	 * 
	 * 
	 * @return AbstractModel
	 */
	public AbstractModel handleUpdate(AbstractModel m) {
		return null;
	}

	/**
	 * Method handleRead.
	 * 
	 * 
	 * @return Collection<AbstractModel>
	 */
	public Collection<AbstractModel> handleRead() {
		return null;
	}

	/**
	 * Method handleCreate.
	 * 
	 * @param m
	 *            AbstractModel
	 * 
	 * 
	 * @return AbstractModel
	 */
	public AbstractModel handleCreate(AbstractModel m) {
		return null;
	}

	/**
	 * Method handleValidate.
	 * 
	 * @param m
	 *            AbstractModel
	 * 
	 * 
	 * @return boolean
	 */
	public boolean handleValidate(AbstractModel m) {
		return false;
	}

	/**
	 * Method getLastEvent.
	 * 
	 * 
	 * @return AbstractEvent
	 */
	public AbstractEvent getLastEvent() {
		return lastEvent;
	}

	/**
	 * Method setLastEvent.
	 * 
	 * @param lastEvent
	 *            AbstractEvent
	 */
	public void setLastEvent(AbstractEvent lastEvent) {
		this.lastEvent = lastEvent;
	}

}
