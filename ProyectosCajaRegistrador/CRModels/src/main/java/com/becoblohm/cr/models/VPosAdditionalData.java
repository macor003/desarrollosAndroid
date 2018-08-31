/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.models;

import java.util.Date;

import com.becoblohm.cr.interfaces.AbstractAuthorizableModel;
import com.becoblohm.cr.types.AuditTables;

/**
 * @author programador11
 * 
 * @version $Revision: 1.0 $
 */
public class VPosAdditionalData extends AbstractAuthorizableModel {

    private static final long serialVersionUID = -1177182877513349039L;

    /**
     * Field id.
     */
    private Long id;

    /**
     * Field idTransactionPaymentMethod.
     */
    private Long idTransactionPaymentMethod;

    /**
     * Field seqNum.
     */
    private Integer seqNum;

    /**
     * Field vtid.
     */
    private String vtid;

    /**
     * Field ownerId.
     */
    private String ownerId;

    /**
     * Field cardNumber.
     */
    private String cardNumber;

    /**
     * Field responseId.
     */
    private String responseId;

    /**
     * Field responseMessage.
     */
    private String responseMessage;

    /**
     * Field file.
     */
    private String file;

    /**
     * Field date.
     */
    private Date date;

    /**
     * Field pmPermitReverse.
     */
    private boolean pmPermitReverse;

    /**
     * Field isValid.
     */
    private boolean isValid;

    /**
     * Field cashierId.
     */
    private long cashierId;

    public VPosAdditionalData() {
        super(AuditTables.VPOSADITIONALDATA);
    }

    /**
     * Method getId.
     * 
     * @return Long
     */
    public Long getId() {
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
     * Method getIdTransactionPaymentMethod.
     * 
     * @return Long
     */
    public Long getIdTransactionPaymentMethod() {
        return idTransactionPaymentMethod;
    }

    /**
     * Method setIdTransactionPaymentMethod.
     * 
     * @param idTransactionPaymentMethod Long
     */
    public void setIdTransactionPaymentMethod(Long idTransactionPaymentMethod) {
        this.idTransactionPaymentMethod = idTransactionPaymentMethod;
    }

    /**
     * Method getSeqNum.
     * 
     * @return Integer
     */
    public Integer getSeqNum() {
        return seqNum;
    }

    /**
     * Method setSeqNum.
     * 
     * @param seqNum Integer
     */
    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    /**
     * Method getVtid.
     * 
     * @return String
     */
    public String getVtid() {
        return vtid;
    }

    /**
     * Method setVtid.
     * 
     * @param vtid String
     */
    public void setVtid(String vtid) {
        this.vtid = vtid;
    }

    /**
     * Method getOwnerId.
     * 
     * @return String
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * Method setOwnerId.
     * 
     * @param ownerId String
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Method getCardNumber.
     * 
     * @return String
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Method setCardNumber.
     * 
     * @param cardNumber String
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Method getResponseId.
     * 
     * @return String
     */
    public String getResponseId() {
        return responseId;
    }

    /**
     * Method setResponseId.
     * 
     * @param responseId String
     */
    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    /**
     * Method getResponseMessage.
     * 
     * @return String
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Method setResponseMessage.
     * 
     * @param responseMessage String
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    /**
     * Method getFile.
     * 
     * @return String
     */
    public String getFile() {
        return file;
    }

    /**
     * Method setFile.
     * 
     * @param file String
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * Method getDate.
     * 
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Method setDate.
     * 
     * @param date Date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @param pmPermitReverse the pmPermitReverse to set
     */
    public void setPmPermitReverse(boolean pmPermitReverse) {
        this.pmPermitReverse = pmPermitReverse;
    }

    /**
     * 
     * @return the pmPermitReverse
     */
    public boolean isPmPermitReverse() {
        return pmPermitReverse;
    }

    /**
     * @param isValid the isValid to set
     */
    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * 
     * @return the isValid
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * @param cashierId the cashierId to set
     */
    public void setCashierId(long cashierId) {
        this.cashierId = cashierId;
    }

    /**
     * 
     * @return the cashierId
     */
    public long getCashierId() {
        return cashierId;
    }

    @Override
    public String getAuditID() {
        return String.valueOf(this.id);
    }

}
