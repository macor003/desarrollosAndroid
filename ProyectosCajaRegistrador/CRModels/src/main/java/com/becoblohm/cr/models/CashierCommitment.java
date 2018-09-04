/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.becoblohm.cr.interfaces.AbstractAuthorizableModel;
import com.becoblohm.cr.types.AuditTables;
import com.becoblohm.cr.types.CRBigDecimal;

/**
 */
public class CashierCommitment extends AbstractAuthorizableModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field id.
     */
    private long id;

    /**
     * Field id_sesion.
     */
    private long id_sesion;

    /**
     * Field number.
     */
    private long number;

    /**
     * Field date.
     */
    private String date;

    /**
     * Field time.
     */
    private String time;

    /**
     * Field monto_recaudado.
     */
    private CRBigDecimal monto_recaudado = CRBigDecimal.ZERO;

    /**
     * Field monto_fondo.
     */
    private CRBigDecimal monto_fondo = CRBigDecimal.ZERO;

    /**
     * Field idCashier.
     */
    private long idCashier;

    /**
     * Field idAuth.
     */
    private String idAuth;

    /**
     * Field totalDifferenceQuantity.
     */
    private String totalDifferenceQuantity;

    /**
     * Field totalDifferenceAmount.
     */
    private String totalDifferenceAmount;

    /**
     * Field pos.
     */
    private String pos;

    /**
     * Field onLine.
     */
    private boolean onLine;

    /**
     * Field messageOffLine.
     */
    private String messageOffLine;

    /**
     * Field idTransExceeded.
     */
    private String idTransExceeded;

    /**
     * Field authCashierFundExceeded.
     */
    private boolean authCashierFundExceeded;

    /**
     * Field partialHeader.
     */
    private String partialHeader;

    /**
     * Field isSync.
     */
    private String isSync;

    /**
     * Field paymentMethodList.
     */
    private Collection<CommitmentPayment> paymentMethodList = new ArrayList<CommitmentPayment>();

    /**
     * Field commitmentCurrencyList.
     */
    private Collection<CommitmentCurrency> commitmentCurrencyList = new ArrayList<CommitmentCurrency>();

    /**
     * Field deliveryType.
     */
    private String deliveryType;

    /**
     * Field paymentCategories
     */
    private HashMap<String, PaymentMethodCategory> paymentCategories;

    /**
     * Constructor for CashierCommitment.
     */
    public CashierCommitment() {
        super(AuditTables.DELIVERY);
    }

    /**
     * Constructor for CashierCommitment.
     * 
     * @param id long
     * @param id_sesion long
     * @param number long
     * @param date String
     * @param monto_recaudado CRBigDecimal
     * @param monto_fondo CRBigDecimal
     * @param process String
     */
    public CashierCommitment(long id, long id_sesion, long number, String date, CRBigDecimal monto_recaudado,
                             CRBigDecimal monto_fondo, String process) {
        super(AuditTables.DELIVERY);
        this.id = id;
        this.id_sesion = id_sesion;
        this.number = number;
        this.date = date;
        this.monto_recaudado = monto_recaudado;
        this.monto_fondo = monto_fondo;
    }

    /**
     * Constructor for CashierCommitment.
     * 
     * @param process String
     */
    public CashierCommitment(String process) {
        super(AuditTables.DELIVERY);
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
     * Method getId_sesion.
     * 
     * @return long
     */
    public long getId_sesion() {
        return id_sesion;
    }

    /**
     * Method setId_sesion.
     * 
     * @param id_sesion long
     */
    public void setId_sesion(long id_sesion) {
        this.id_sesion = id_sesion;
    }

    /**
     * Method getNumber.
     * 
     * @return long
     */
    public long getNumber() {
        return number;
    }

    /**
     * Method setNumber.
     * 
     * @param number long
     */
    public void setNumber(long number) {
        this.number = number;
    }

    /**
     * Method getDate.
     * 
     * @return String
     */
    public String getDate() {
        return date;
    }

    /**
     * Method setDate.
     * 
     * @param date String
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Method getMonto_recaudado.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getMonto_recaudado() {
        return monto_recaudado;
    }

    /**
     * Method setMonto_recaudado.
     * 
     * @param monto_recaudado CRBigDecimal
     */
    public void setMonto_recaudado(CRBigDecimal monto_recaudado) {

        CRBigDecimal tmp = this.monto_recaudado;
        this.monto_recaudado = monto_recaudado;
        this.fire("monto_recaudado", tmp, monto_recaudado);
    }

    /**
     * Method getMonto_fondo.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getMonto_fondo() {
        return monto_fondo;
    }

    /**
     * Method setMonto_fondo.
     * 
     * @param monto_fondo CRBigDecimal
     */
    public void setMonto_fondo(CRBigDecimal monto_fondo) {
        this.monto_fondo = monto_fondo;
    }

    /**
     * 
     * @return the paymentMethodList
     */
    public Collection<CommitmentPayment> getPaymentMethodList() {
        return paymentMethodList;
    }

    /**
     * @param paymentMethodList the paymentMethodList to set
     */
    public void setPaymentMethodList(Collection<CommitmentPayment> paymentMethodList) {
        this.paymentMethodList = paymentMethodList;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param idAuth the idAuth to set
     */
    public void setIdAuth(String idAuth) {
        this.idAuth = idAuth;
    }

    /**
     * 
     * @return the idAuth
     */
    public String getIdAuth() {
        return idAuth;
    }

    /**
     * @param idCashier the idCashier to set
     */
    public void setIdCashier(long idCashier) {
        this.idCashier = idCashier;
    }

    /**
     * 
     * @return the idCashier
     */
    public long getIdCashier() {
        return idCashier;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(String pos) {
        this.pos = pos;
    }

    /**
     * 
     * @return the pos
     */
    public String getPos() {
        return pos;
    }

    /**
     * 
     * @param onLine boolean
     */
    public void setOnLine(boolean onLine) {
        this.onLine = onLine;
    }

    /**
     * 
     * @return the outOfLine
     */
    public boolean isOnLine() {
        return onLine;
    }

    /**
     * @param messageOffLine the messageOffLine to set
     */
    public void setMessageOffLine(String messageOffLine) {
        this.messageOffLine = messageOffLine;
    }

    /**
     * 
     * @return the messageOffLine
     */
    public String getMessageOffLine() {
        return messageOffLine;
    }

    /**
     * @param idTransExceeded the idTransExceeded to set
     */
    public void setIdTransExceeded(String idTransExceeded) {
        this.idTransExceeded = idTransExceeded;
    }

    /**
     * 
     * @return the idTransExceeded
     */
    public String getIdTransExceeded() {
        return idTransExceeded;
    }

    /**
     * 
     * @param deliveryType String
     */
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    /**
     * 
     * @return the deliveryTyspe
     */
    public String getDeliveryType() {
        return deliveryType;
    }

    /**
     * @param partialHeader the partialHeader to set
     */
    public void setPartialHeader(String partialHeader) {
        this.partialHeader = partialHeader;
    }

    /**
     * 
     * @return the partialHeader
     */
    public String getPartialHeader() {
        return partialHeader;
    }

    public HashMap<String, PaymentMethodCategory> getPaymentCategories() {
        return paymentCategories;
    }

    public void setPaymentCategories(HashMap<String, PaymentMethodCategory> paymentCategories) {
        this.paymentCategories = paymentCategories;
    }

    /**
     * Method getCommitmentCurrencyList.
     * 
     * @return Collection<CommitmentCurrency>
     */
    public Collection<CommitmentCurrency> getCommitmentCurrencyList() {
        return commitmentCurrencyList;
    }

    /**
     * Method setCommitmentCurrencyList.
     * 
     * @param commitmentCurrencyList Collection<CommitmentCurrency>
     */
    public void setCommitmentCurrencyList(Collection<CommitmentCurrency> commitmentCurrencyList) {
        this.commitmentCurrencyList = commitmentCurrencyList;
    }

    /**
     * Method isAuthCashierFundExceeded.
     * 
     * @return boolean
     */
    public boolean isAuthCashierFundExceeded() {
        return authCashierFundExceeded;
    }

    /**
     * Method setAuthCashierFundExceeded.
     * 
     * @param authCashierFundExceeded boolean
     */
    public void setAuthCashierFundExceeded(boolean authCashierFundExceeded) {
        this.authCashierFundExceeded = authCashierFundExceeded;
    }

    /**
     * Method getIsSync.
     * 
     * @return String
     */
    public String getIsSync() {
        return isSync;
    }

    /**
     * Method setIsSync.
     * 
     * @param isSync String
     */
    public void setIsSync(String isSync) {
        this.isSync = isSync;
    }

    @Override
    public String getAuditID() {
        return String.valueOf(this.id);
    }

    public String getTotalDifferenceQuantity() {
        return totalDifferenceQuantity;
    }

    public void setTotalDifferenceQuantity(String totalDifferenceQuantity) {
        this.totalDifferenceQuantity = totalDifferenceQuantity;
    }

    public String getTotalDifferenceAmount() {
        return totalDifferenceAmount;
    }

    public void setTotalDifferenceAmount(String totalDifferenceAmount) {
        this.totalDifferenceAmount = totalDifferenceAmount;
    }
}
