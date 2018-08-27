/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.epa.mvc.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * <b>AbstractModel</b> es la clase abstracta que se debe heredar para
 * representar un <b>modelo</b> en el esquema <b>MVC</b> planteado. Esta clase
 * contiene los metodos necesarios para mantener el modelo sincronizado con una
 * o mas vistas de tipo {@link AbstractView} y se acopla a un controlador de
 * tipo {@link AbstractController_swing} para completar el modelo <b>MVC</b>.
 * <p>
 * La clase que vaya a representar el modelo debe ser un Bean, con sus metodos
 * <code>get</code> y <code>set</code> respectivos, y ademas debe incorporar dos
 * lineas extra de codigo en cada uno de los metodos <code>set</code>
 * correspondientes a las propiedades que se quieran mantener sincronizadas con
 * la vista.
 * <p>
 * Por ejemplo, si tenemos el siguiente metodo:
 * <p>
 * <code>
&nbsp;&nbsp;&nbsp; public setName(String name){<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; this.name = name;<br />
&nbsp;&nbsp;&nbsp;}
 * </code>
 * <p>
 * Debe escribirse como:
 * <p>
 * <code>
&nbsp;&nbsp;&nbsp; public setName(String name){<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b>String old = this.name;</b><br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; this.name = name;<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b>this.firePropertyChange("name", old, name);</b<br />
&nbsp;&nbsp;&nbsp; }
 * </code>
 * <p>
 * Con esto se dispara un evento cada vez que se modifica una propiedad mediante
 * su metodo <code>set</code>.
 * 
 * @author Jesus Aguilera (programador10), Eduardo Lugo (programador6)
 * @version $Revision: 1.0 $
 */

public abstract class AbstractModel implements Serializable, Cloneable {

	/**
	 * Field logger.
	 */

	/**
	 * Constructor for AbstractModel.
	 */
	public AbstractModel() {

	}

	// databinding support for javabuilders
	/**
	 * Field support.
	 */
	private PropertyChangeSupport support = new PropertyChangeSupport(this);

	/**
	 * Method addPropertyChangeListener.
	 * 
	 * @param listener
	 *            PropertyChangeListener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.support.addPropertyChangeListener(listener);
	}

	/**
	 * Method addPropertyChangeListener.
	 * 
	 * @param propertyName
	 *            String
	 * @param listener
	 *            PropertyChangeListener
	 */
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		this.support.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * Method removePropertyChangeListener.
	 * 
	 * @param listener
	 *            PropertyChangeListener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.support.removePropertyChangeListener(listener);
	}

	/**
	 * Method removePropertyChangeListener.
	 * 
	 * @param propertyName
	 *            String
	 * @param listener
	 *            PropertyChangeListener
	 */
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		this.support.removePropertyChangeListener(propertyName, listener);
	}

	/**
	 * Method fire.
	 * 
	 * @param field
	 *            String
	 * @param oldValue
	 *            Object
	 * @param newValue
	 *            Object
	 */
	protected void fire(String field, Object oldValue, Object newValue) {
		/*
		 * logger.debug("pname: "+field); logger.debug("    old: "+oldValue);
		 * logger.debug("    new: "+newValue); logger.debug("    this: "+ ((this
		 * != null) ? this.getClass().getName() : "null"));
		 */
		this.support.firePropertyChange(field, (oldValue != null) ? oldValue : new Object(), newValue);
	}

	/**
	 * Method delete.
	 * 
	 * 
	 * @return boolean
	 */
	protected boolean delete() {
		return false;
	};

	/**
	 * Method update.
	 * 
	 * 
	 * @return boolean
	 */
	protected boolean update() {
		return false;
	}

	/**
	 * Method read.
	 * 
	 * 
	 * @return AbstractModel
	 */
	protected AbstractModel read() {
		return null;
	}

	/**
	 * Method create.
	 * 
	 * 
	 * @return boolean
	 */
	protected boolean create() {
		return false;
	}

}
