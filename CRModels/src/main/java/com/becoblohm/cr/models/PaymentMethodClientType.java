/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 * @author Magroberth Alvarado (programador18)
 *
 */
public class PaymentMethodClientType extends AbstractModel {

    private static final long serialVersionUID = 2384632981616062622L;

    private Long id;

    private Long idPaymentMethod;

    private Long idClientType;

    private boolean active;

    public PaymentMethodClientType() {
        super();
    }

    public PaymentMethodClientType(Long id, Long idPaymentMethod, Long idClientType, boolean active) {
        super();
        this.id = id;
        this.idPaymentMethod = idPaymentMethod;
        this.idClientType = idClientType;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(Long idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    public Long getIdClientType() {
        return idClientType;
    }

    public void setIdClientType(Long idClientType) {
        this.idClientType = idClientType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
