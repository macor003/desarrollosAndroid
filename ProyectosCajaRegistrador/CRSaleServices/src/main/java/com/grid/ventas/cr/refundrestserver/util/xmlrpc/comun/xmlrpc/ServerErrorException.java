/*******************************************************************************
 * (c) 2014 Global Retail Information Ltd
 ******************************************************************************/
package com.grid.ventas.cr.refundrestserver.util.xmlrpc.comun.xmlrpc;

public class ServerErrorException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 2713102586544696293L;

    public ServerErrorException() {
    }

    public ServerErrorException(String message) {
        super(message);
    }

    public ServerErrorException(Throwable cause) {
        super(cause);
    }

    public ServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

}
