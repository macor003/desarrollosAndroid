/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.types;

import javax.swing.JButton;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class AbstractModelwBtn extends AbstractModel {

    /**
     * Field btn.
     */
    protected JButton btn;

    /**
     * Field model.
     */
    protected AbstractModel model;

    /**
     * Method getBtn.
     * 
     * @return JButton
     */
    public JButton getBtn() {
        return btn;
    }

    /**
     * Method setBtn.
     * 
     * @param btn JButton
     */
    public void setBtn(JButton btn) {
        this.btn = btn;
    }

    /**
     * Method getModel.
     * 
     * @return AbstractModel
     */
    public AbstractModel getModel() {
        return model;
    }

    /**
     * Method setModel.
     * 
     * @param model AbstractModel
     */
    public void setModel(AbstractModel model) {
        this.model = model;
    }
}
