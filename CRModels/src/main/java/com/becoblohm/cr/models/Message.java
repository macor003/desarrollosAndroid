/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class Message extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field messageText.
     */
    private String messageText;

    /**
     * Method getMessageText.
     * 
     * @return String
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * Method setMessageText.
     * 
     * @param messageText String
     */
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

}
