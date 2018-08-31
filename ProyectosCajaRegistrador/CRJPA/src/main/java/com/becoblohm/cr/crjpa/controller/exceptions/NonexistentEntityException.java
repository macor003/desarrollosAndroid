/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller.exceptions;

/**
 */
public class NonexistentEntityException extends Exception {

    /**
     * Constructor for NonexistentEntityException.
     * 
     * @param message String
     * @param cause Throwable
     */
    public NonexistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for NonexistentEntityException.
     * 
     * @param message String
     */
    public NonexistentEntityException(String message) {
        super(message);
    }
}
