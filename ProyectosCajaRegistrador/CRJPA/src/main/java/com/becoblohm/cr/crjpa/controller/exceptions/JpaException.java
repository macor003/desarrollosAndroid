package com.becoblohm.cr.crjpa.controller.exceptions;

/**
 */
public class JpaException extends Exception {

    /**
     * Constructor for JpaException.
     */
    public JpaException() {
    }

    /**
     * Constructor for JpaException.
     * 
     * @param e Throwable
     */
    public JpaException(Throwable e) {
        super(e);
    }

    /**
     * Constructor for JpaException.
     * 
     * @param message String
     * @param e Throwable
     */
    public JpaException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * Constructor for JpaException.
     * 
     * @param message String
     */
    public JpaException(String message) {
        super(message);
    }

}
