/*******************************************************************************
 * (c) 2014 Global Retail Information Ltd
 ******************************************************************************/
package com.grid.ventas.cr.refundrestserver.util.xmlrpc.comun.xmlrpc;

public class ForbiddenException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -6219534551233922525L;

    public ForbiddenException() {
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

}
