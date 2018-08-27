/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class OrderArticleDeliveryCondition extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 4728611938604616333L;

    /**
     * Field orderArticle.
     */
    private OrderArticle orderArticle;

    /**
     * Field deliveryCondition.
     */
    private DeliveryConditionCR400 deliveryCondition;

    /**
     * Field clientIdNumber.
     */
    private String clientIdNumber;

    /**
     * Field clientName.
     */
    private String clientName;

    /**
     * Field clientPhone.
     */
    private String clientPhone;

    /**
     * Field clientAddress.
     */
    private String clientAddress;

    /**
     * Method getDeliveryCondition.
     * 
     * @return DeliveryConditionCR400
     */
    public DeliveryConditionCR400 getDeliveryCondition() {
        return deliveryCondition;
    }

    /**
     * Method setDeliveryCondition.
     * 
     * @param deliveryCondition DeliveryConditionCR400
     */
    public void setDeliveryCondition(DeliveryConditionCR400 deliveryCondition) {
        this.deliveryCondition = deliveryCondition;
    }

    /**
     * Method getClientIdNumber.
     * 
     * @return String
     */
    public String getClientIdNumber() {
        return clientIdNumber;
    }

    /**
     * Method setClientIdNumber.
     * 
     * @param clientIdNumber String
     */
    public void setClientIdNumber(String clientIdNumber) {
        this.clientIdNumber = clientIdNumber;
    }

    /**
     * Method getClientName.
     * 
     * @return String
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Method setClientName.
     * 
     * @param clientName String
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Method getClientPhone.
     * 
     * @return String
     */
    public String getClientPhone() {
        return clientPhone;
    }

    /**
     * Method setClientPhone.
     * 
     * @param clientPhone String
     */
    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    /**
     * Method getClientAddress.
     * 
     * @return String
     */
    public String getClientAddress() {
        return clientAddress;
    }

    /**
     * Method setClientAddress.
     * 
     * @param clientAddress String
     */
    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    /**
     * Method getOrderArticle.
     * 
     * @return OrderArticle
     */
    public OrderArticle getOrderArticle() {
        return orderArticle;
    }

    /**
     * Method setOrderArticle.
     * 
     * @param orderArticle OrderArticle
     */
    public void setOrderArticle(OrderArticle orderArticle) {
        this.orderArticle = orderArticle;
    }

}
