/*******************************************************************************
 * (c) 2014 Global Retail Information Ltd
 ******************************************************************************/
package com.grid.ventas.cr.refundrestserver.util.xmlrpc.comun.xmlrpc;

import org.apache.xmlrpc.XmlRpcException;

public class XmlRpcCode {

    /**
     * Sin permiso de acceso (autenticacion requerida )
     */
    public final static int UNAUTHORIZED = 401;

    /**
     * Acceso no permitido
     */
    public final static int FORBIDDEN = 403;

    /**
     * Recurso no encontrado
     */
    public final static int NOT_FOUND = 404;

    /**
     * Error de servidor
     */
    public final static int SERVER_ERROR = 500;

    public static void evaluateExceptionCode(XmlRpcException e) throws Exception {

        switch (e.code) {
            case UNAUTHORIZED:
                throw new UnauthorizedException("UNAUTHORIZED", e);
            case FORBIDDEN:
                throw new ForbiddenException("FORBIDDEN", e);
            case NOT_FOUND:
                throw new NotFoundException("NOT_FOUND", e);
            case SERVER_ERROR:
                throw new ServerErrorException("SERVER_ERROR", e);
            default:
                throw e;
        }
    }

}
