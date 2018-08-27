/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class StatusOrder extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 4728611938604616333L;

    /**
     * Field id.
     */
    private long id;

    /**
     * Field description.
     */
    private String description;

    /**
     * Field shortDescription.
     */
    private String shortDescription;

    /**
     * Field orderVisible.
     */
    private boolean orderVisible;

    /**
     * Field receivesDeposits.
     */
    private boolean receivesDeposits;

    /**
     * Field receivesCancellationDeposits.
     */
    private boolean receivesCancellationDeposits;

    /**
     * Field orderCanBeCancelled.
     */
    private boolean orderCanBeCancelled;

    /**
     * Field orderCanBeBilled.
     */
    private boolean orderCanBeBilled;

    /**
     * Field taxRetentionPermited.
     */
    private boolean taxRetentionPermited;

    /**
     * Field changeDeliveryConditionPermited.
     */
    private boolean changeDeliveryConditionPermited;

    /**
     * Constructor for StatusOrder.
     * 
     * @param id int
     * @param desc String
     */
    public StatusOrder(int id, String desc) {
        this.id = id;
        this.description = desc;
    }

    /**
     * Constructor for StatusOrder.
     */
    public StatusOrder() {
    }

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
     * @param id long
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Method getDescription.
     * 
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method isOrderVisible.
     * 
     * @return boolean
     */
    public boolean isOrderVisible() {
        return orderVisible;
    }

    /**
     * Method setOrderVisible.
     * 
     * @param orderVisible boolean
     */
    public void setOrderVisible(boolean orderVisible) {
        this.orderVisible = orderVisible;
    }

    /**
     * Method setDescription.
     * 
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method isReceivesDeposits.
     * 
     * @return boolean
     */
    public boolean isReceivesDeposits() {
        return receivesDeposits;
    }

    /**
     * Method setReceivesDeposits.
     * 
     * @param receivesDeposits boolean
     */
    public void setReceivesDeposits(boolean receivesDeposits) {
        this.receivesDeposits = receivesDeposits;
    }

    /**
     * Method isReceivesCancellationDeposits.
     * 
     * @return boolean
     */
    public boolean isReceivesCancellationDeposits() {
        return receivesCancellationDeposits;
    }

    /**
     * Method setReceivesCancellationDeposits.
     * 
     * @param receivesCancellationDeposits boolean
     */
    public void setReceivesCancellationDeposits(boolean receivesCancellationDeposits) {
        this.receivesCancellationDeposits = receivesCancellationDeposits;
    }

    /**
     * Method isOrderCanBeCancelled.
     * 
     * @return boolean
     */
    public boolean isOrderCanBeCancelled() {
        return orderCanBeCancelled;
    }

    /**
     * Method setOrderCanBeCancelled.
     * 
     * @param orderCanBeCancelled boolean
     */
    public void setOrderCanBeCancelled(boolean orderCanBeCancelled) {
        this.orderCanBeCancelled = orderCanBeCancelled;
    }

    /**
     * Method isOrderCanBeBilled.
     * 
     * @return boolean
     */
    public boolean isOrderCanBeBilled() {
        return orderCanBeBilled;
    }

    /**
     * Method setOrderCanBeBilled.
     * 
     * @param orderCanBeBilled boolean
     */
    public void setOrderCanBeBilled(boolean orderCanBeBilled) {
        this.orderCanBeBilled = orderCanBeBilled;
    }

    /**
     * Method isTaxRetentionPermited.
     * 
     * @return boolean
     */
    public boolean isTaxRetentionPermited() {
        return taxRetentionPermited;
    }

    /**
     * Method setTaxRetentionPermited.
     * 
     * @param taxRetentionPermited boolean
     */
    public void setTaxRetentionPermited(boolean taxRetentionPermited) {
        this.taxRetentionPermited = taxRetentionPermited;
    }

    /**
     * Method isChangeDeliveryConditionPermited.
     * 
     * @return boolean
     */
    public boolean isChangeDeliveryConditionPermited() {
        return changeDeliveryConditionPermited;
    }

    /**
     * Method setChangeDeliveryConditionPermited.
     * 
     * @param changeDeliveryConditionPermited boolean
     */
    public void setChangeDeliveryConditionPermited(boolean changeDeliveryConditionPermited) {
        this.changeDeliveryConditionPermited = changeDeliveryConditionPermited;
    }

    /**
     * Method isCanDoAnyAction.
     * 
     * @return boolean
     */
    public boolean isCanDoAnyAction() {
        return orderVisible || receivesDeposits || receivesCancellationDeposits || orderCanBeCancelled
                || orderCanBeBilled || taxRetentionPermited || changeDeliveryConditionPermited;
    }

    /**
     * Method equals.
     * 
     * @param tmp StatusOrder
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof StatusOrder))
            return false;
        StatusOrder tmp = (StatusOrder) o;
        if (this.id == tmp.id)
            return true;
        else
            return false;
    }

    /**
     * 
     * @return the shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @param shortDescription the shortDescription to set
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

}
