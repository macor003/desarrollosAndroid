/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class OrderTransaction extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field id.
     */
    private Long id;

    /**
     * Field order.
     */
    private Order order;

    /**
     * Field idTransaccion.
     */
    private Long idTransaccion;

    /**
     * Field posId.
     */
    private int posId;

    /**
     * Method getId.
     * 
     * @return long
     */
    public long getId() {
        return id;
    }

    /**
     * Method setId.
     * 
     * @param id Long
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method getOrder.
     * 
     * @return Order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Method setOrder.
     * 
     * @param order Order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Method getIdTransaccion.
     * 
     * @return Long
     */
    public Long getIdTransaccion() {
        return idTransaccion;
    }

    /**
     * Method setIdTransaccion.
     * 
     * @param idTransaccion Long
     */
    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    /**
     * Method getPosId.
     * 
     * @return int
     */
    public int getPosId() {
        return posId;
    }

    /**
     * Method setStoreId.
     * 
     * @param storeId int
     */
    public void setStoreId(int storeId) {
        this.posId = storeId;
    }

}
