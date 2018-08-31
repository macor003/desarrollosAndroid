/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

/**
 */
public class OrderArticle extends Article {

    /**
     * 
     */
    private static final long serialVersionUID = 4728611938604616333L;

    /**
     * Field orderArticleDeliveryCondition.
     */
    private OrderArticleDeliveryCondition orderArticleDeliveryCondition;

    /**
     * Field status.
     */
    private StatusOrder status;

    /*
     * Agregado por mnunez2 para validar el status de un articulo en orden de venta
     */
    /**
     * Field isActive.
     */
    private boolean isActive;

    /**
     * Method getOrderArticleDeliveryCondition.
     * 
     * @return OrderArticleDeliveryCondition
     */
    public OrderArticleDeliveryCondition getOrderArticleDeliveryCondition() {
        return orderArticleDeliveryCondition;
    }

    /**
     * Method setOrderArticleDeliveryCondition.
     * 
     * @param orderArticleDeliveryCondition OrderArticleDeliveryCondition
     */
    public void setOrderArticleDeliveryCondition(OrderArticleDeliveryCondition orderArticleDeliveryCondition) {
        this.orderArticleDeliveryCondition = orderArticleDeliveryCondition;
    }

    /**
     * Method getStatus.
     * 
     * @return StatusOrder
     */
    public StatusOrder getStatus() {
        return status;
    }

    /**
     * Method setStatus.
     * 
     * @param status StatusOrder
     */
    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    /**
     * Method isActive.
     * 
     * @return boolean
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Method setActive.
     * 
     * @param isActive boolean
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

}
