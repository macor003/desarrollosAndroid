package com.grid.ventas.cr.refundrestserver.util.xmlrpc.comun.proxy;

import org.apache.xmlrpc.XmlRpcException;

import java.util.Date;
import java.util.Map;

public interface VentaInterface {

    /**
     * Consultar de manera remota una transaccion de venta para las TRs
     *
     * @param storeId Tienda (tres caracteres) ej "003"
     * @param posId Caja
     * @param id Transaccion
     * @param date Feacha de la transaccion
     * @return {@code Map} con la informacion de TRCMOV, TRDMOV, TRCTOV, MAECEC, OPEVTSC
     * @throws org.apache.xmlrpc.XmlRpcException
     */
    Map<String, Object> getVenta(String storeId, String posId, String id, Date date) throws XmlRpcException;

}
