/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.mvc.core;

import java.beans.PropertyChangeEvent;

/**
 * <b>AbstractView</b> es la clase abstracta que se debe heredar para
 * representar una <b>vista</b> en el esquema <b>MVC</b> planteado. Esta clase
 * contiene los metodos necesarios para recibir los cambios ejecutados en el
 * modelo y mantener la vista sincronizada con dichos cambios, para esto se
 * acopla a un controlador de tipo {@link AbstractController} para completar el
 * modelo <b>MVC</b>.
 * 
 * @author Eduardo Lugo (programador6), Jesus Aguilera (programador10)
 * @version $Revision: 1.0 $
 */
public abstract class AbstractView {
	/**
	 * Field parentController.
	 */
	protected AbstractController parentController;
	/**
	 * Field model.
	 */
	protected AbstractModel model;

	/**
	 * Field logger.
	 */

	/*
	 * protected Collection<AbstractPanel> panels=new
	 * ArrayList<AbstractPanel>(); protected JPanel mainPanel;
	 */

	/**
	 * Constructor for AbstractView.
	 */
	public AbstractView() {

	}

	/**
	 * Method getModel.
	 * 
	 * 
	 * @return AbstractModel
	 */
	public AbstractModel getModel() {
		return this.model;
	}

	/**
	 * Method setModel.
	 * 
	 * @param model
	 *            AbstractModel
	 */
	public void setModel(AbstractModel model) {
		this.model = model;
	}

	// public abstract void actionPerformed(ActionEvent arg0);
	//

	/**
	 * Method getParentController.
	 * 
	 * 
	 * @return AbstractController
	 */
	public AbstractController getParentController() {
		return this.parentController;
	}

	/**
	 * Method setParentController.
	 * 
	 * @param parentController
	 *            AbstractController
	 */
	public void setParentController(AbstractController parentController) {
		this.parentController = parentController;
	}

	/**
	 * Method modelChanged.
	 * 
	 * @param evt
	 *            PropertyChangeEvent
	 */
	public abstract void modelChanged(PropertyChangeEvent evt);

	/**
	 * Method show.
	 */
	abstract public void show();

	/**
	 * Method remove.
	 */
	abstract public void remove();

	/**
	 * Method init.
	 */
	abstract public void init();

	/**
	 * Method refresh.
	 */
	abstract public void refresh();

	/**
	 * Method getView.
	 * 
	 * 
	 * @return Object
	 */
	abstract public Object getView();

	/*
	 * public JPanel getMainPanel() { return mainPanel; }
	 * 
	 * public void setMainPanel(JPanel mainPanel) { this.mainPanel = mainPanel;
	 * }
	 * 
	 * public Collection<AbstractPanel> getPanels() { return panels; }
	 * 
	 * public void setPanels(Collection<AbstractPanel> panels) { this.panels =
	 * panels; }
	 * 
	 * 
	 * public void addPanels(AbstractPanel panel){ panels.add(panel); }
	 */

	/*
	 * class createListener implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * 
	 * parentController.create(model); }
	 * 
	 * }
	 * 
	 * class readListener implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * parentController.read(); }
	 * 
	 * }
	 * 
	 * class deleteListener implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * 
	 * parentController.delete(model); }
	 * 
	 * }
	 * 
	 * class updateListener implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * 
	 * parentController.handleUpdate(); }
	 * 
	 * }
	 * 
	 * class validateListener implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * 
	 * parentController.handleValidate(AbstractView.this.getModel()); }
	 * 
	 * }
	 */

}
