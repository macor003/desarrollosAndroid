/*******************************************************************************
 * (c) 2014 Global Retail Information Ltd
 ******************************************************************************/
package com.grid.ventas.cr.refundrestserver.util.xmlrpc.comun.xmlrpc;

public class UnauthorizedException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 4584412383507258466L;

    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

}
