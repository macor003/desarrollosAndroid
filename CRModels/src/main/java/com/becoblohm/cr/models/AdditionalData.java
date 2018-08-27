/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 * @author programador11
 * 
 * @version $Revision: 1.0 $
 */
public class AdditionalData extends AbstractModel {

    /**
     * Field clientId.
     */
    private String clientId;

    /**
     * Field documentNumber.
     */
    private String documentNumber;

    /**
     * Field bank.
     */
    private String bank;

    /**
     * Field accountNumber.
     */
    private String accountNumber;

    /**
     * Field approval.
     */
    private String approval;

    /**
     * Field phoneNumber.
     */
    private String phoneNumber;

    /**
     * 
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * 
     * @return the documentNumber
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * @param documentNumber the documentNumber to set
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * 
     * @return the bank
     */
    public String getBank() {
        return bank;
    }

    /**
     * @param bank the bank to set
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * 
     * @return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * 
     * @return the approval
     */
    public String getApproval() {
        return approval;
    }

    /**
     * @param approval the approval to set
     */
    public void setApproval(String approval) {
        this.approval = approval;
    }

    /**
     * 
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
