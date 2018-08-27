/*******************************************************************************
 * (c) 2014 Global Retail Information Ltd
 ******************************************************************************/
package com.grid.ventas.cr.refundrestserver.util.xmlrpc.comun.xmlrpc;

public class NotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 419568390716249775L;

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
