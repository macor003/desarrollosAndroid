/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.becoblohm.cr.net.types.CRBigDecimal;

public class CreditsMovement implements Serializable {

    private static final long serialVersionUID = 2993635584737824068L;

    private CreditsAccount creditsAccount;

    private CRBigDecimal amount;

    private int processId;

    private int processIdToAnul;

    private CreditsOperationType creditsOperationTypeId;

    private Collection payments;

    private boolean refundByCheck;

    private char status;

    private CreditsOperation operationId;

    private CreditsBalance balanceId;

    private int pos;

    private int store;

    private int cashier;

    private Date date;

    private List documents;

    private int id;

    private boolean allowsAuthorizedThirdParty = true;

    private String transactionId;

    private boolean blockCheck = true;

    private Long start;

    private Long timeout;

    private Long ipaid;

    private char ipaStatus;

    private CRBigDecimal change = CRBigDecimal.ZERO;

    /**
     * Method getTransactionId.
     * 
     * @return String
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Method setTransactionId.
     * 
     * @param transactionId String
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Method getPos.
     * 
     * @return int
     */
    public int getPos() {
        return pos;
    }

    /**
     * Method setPos.
     * 
     * @param pos int
     */
    public void setPos(int pos) {
        this.pos = pos;
    }

    /**
     * Method getStore.
     * 
     * @return int
     */
    public int getStore() {
        return store;
    }

    /**
     * Method setStore.
     * 
     * @param store int
     */
    public void setStore(int store) {
        this.store = store;
    }

    /**
     * Method getBalanceId.
     * 
     * @return CreditsBalance
     */
    public CreditsBalance getBalanceId() {
        return balanceId;
    }

    /**
     * Method setBalanceId.
     * 
     * @param balanceId CreditsBalance
     */
    public void setBalanceId(CreditsBalance balanceId) {
        this.balanceId = balanceId;
    }

    /**
     * Constructor for CreditsMovement.
     * 
     * @param creditsAccount CreditsAccount
     * @param creditsOperationTypeId CreditsOperationType
     * @param amount CRBigDecimal
     */
    public CreditsMovement(CreditsAccount creditsAccount, CreditsOperationType creditsOperationTypeId,
                           CRBigDecimal amount) {
        super();
        this.setCreditsAccount(creditsAccount);
        this.amount = amount;
        this.creditsOperationTypeId = creditsOperationTypeId;
    }

    /**
     * Constructor for CreditsMovement.
     * 
     * @param creditsAccount CreditsAccount
     * @param creditsOperationTypeId CreditsOperationType
     * @param processId int
     */
    public CreditsMovement(CreditsAccount creditsAccount, CreditsOperationType creditsOperationTypeId,
                           int processId) {
        super();
        this.setCreditsAccount(creditsAccount);
        this.processId = processId;
        this.creditsOperationTypeId = creditsOperationTypeId;
        this.amount = CRBigDecimal.ZERO;
    }

    /**
     * Constructor for CreditsMovement.
     */
    public CreditsMovement() {
        super();
        this.amount = CRBigDecimal.ZERO;
    }

    /**
     * Constructor for CreditsMovement.
     * 
     * @param creditsAccount2 CreditsAccount
     * @param amount CRBigDecimal
     */
    public CreditsMovement(CreditsAccount creditsAccount2, CRBigDecimal amount) {

        this.creditsAccount = creditsAccount2;
        this.amount = amount;

    }

    /**
     * Constructor for CreditsMovement.
     * 
     * @param creditsAccount CreditsAccount
     * @param creditsOperationTypeId CreditsOperationType
     * @param payments Collection
     * @param amount CRBigDecimal
     */
    public CreditsMovement(CreditsAccount creditsAccount, CreditsOperationType creditsOperationTypeId,
                           Collection payments, CRBigDecimal amount) {
        this.creditsAccount = creditsAccount;
        this.creditsOperationTypeId = creditsOperationTypeId;
        this.payments = payments;
        this.amount = amount;
    }

    /**
     * Method getAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getAmount() {
        return amount;
    }

    /**
     * Method setAmount.
     * 
     * @param amount CRBigDecimal
     */
    public void setAmount(CRBigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Method getProcessId.
     * 
     * @return int
     */
    public int getProcessId() {
        return processId;
    }

    /**
     * Method setProcessId.
     * 
     * @param processId int
     */
    public void setProcessId(int processId) {
        this.processId = processId;
    }

    /**
     * 
     * @param creditsOperationTypeId CreditsOperationType
     */
    public void setCreditsOperationTypeId(CreditsOperationType creditsOperationTypeId) {
        this.creditsOperationTypeId = creditsOperationTypeId;
    }

    /**
     * 
     * @return the operationType
     */
    public CreditsOperationType getCreditsOperationTypeId() {
        return creditsOperationTypeId;
    }

    /**
     * @param payments the payments to set
     */
    public void setPayments(Collection payments) {
        this.payments = payments;
    }

    /**
     * 
     * @return the payments
     */
    public Collection getPayments() {
        return payments;
    }

    /**
     * Method getCreditsAccount.
     * 
     * @return CreditsAccount
     */
    public CreditsAccount getCreditsAccount() {
        return creditsAccount;
    }

    /**
     * Method setCreditsAccount.
     * 
     * @param creditsAccount CreditsAccount
     */
    public void setCreditsAccount(CreditsAccount creditsAccount) {
        this.creditsAccount = creditsAccount;
    }

    /**
     * 
     * @param status char
     */
    public void setStatus(char status) {
        this.status = status;
    }

    /**
     * 
     * @return the estado
     */
    public char getStatus() {
        return status;
    }

    /**
     * @param cashier the cashier to set
     */
    public void setCashier(int cashier) {
        this.cashier = cashier;
    }

    /**
     * 
     * @return the cashier
     */
    public int getCashier() {
        return cashier;
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
     * Method isRefundByCheck.
     * 
     * @return boolean
     */
    public boolean isRefundByCheck() {
        return refundByCheck;
    }

    /**
     * Method setRefundByCheck.
     * 
     * @param set boolean
     */
    public void setRefundByCheck(boolean set) {
        refundByCheck = set;
    }

    /**
     * Method setDocuments.
     * 
     * @param documents List
     */
    public void setDocuments(List documents) {
        this.documents = documents;
    }

    /**
     * Method getDocuments.
     * 
     * @return List
     */
    public List getDocuments() {
        return documents;
    }

    /**
     * Method addDocument.
     * 
     * @param document TransactionDocument
     */
    public void addDocument(TransactionDocument document) {
        this.documents.add(document);
    }

    /**
     * Method setId.
     * 
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method getId.
     * 
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Method setOperationId.
     * 
     * @param operationId CreditsOperation
     */
    public void setOperationId(CreditsOperation operationId) {
        this.operationId = operationId;
    }

    /**
     * Method getOperationId.
     * 
     * @return CreditsOperation
     */
    public CreditsOperation getOperationId() {
        return operationId;
    }

    /**
     * Method isAllowsAuthorizedThirdParty.
     * 
     * @return boolean
     */
    public boolean isAllowsAuthorizedThirdParty() {
        return allowsAuthorizedThirdParty;
    }

    /**
     * Method setAllowsAuthorizedThirdParty.
     * 
     * @param allowsAuthorizedThirdParty boolean
     */
    public void setAllowsAuthorizedThirdParty(boolean allowsAuthorizedThirdParty) {
        this.allowsAuthorizedThirdParty = allowsAuthorizedThirdParty;
    }

    /**
     * Method addPayment.
     * 
     * @param tmpPayment Payment
     */
    public void addPayment(Payment tmpPayment) {
        getPayments().add(tmpPayment);

    }

    /**
     * Method getProcessIdToAnul.
     * 
     * @return int
     */
    public int getProcessIdToAnul() {
        return processIdToAnul;
    }

    /**
     * Method setProcessIdToAnul.
     * 
     * @param processIdToAnul int
     */
    public void setProcessIdToAnul(int processIdToAnul) {
        this.processIdToAnul = processIdToAnul;
    }

    /**
     * Method isBlockCheck.
     * 
     * @return boolean
     */
    public boolean isBlockCheck() {
        return blockCheck;
    }

    /**
     * Method setBlockCheck.
     * 
     * @param blockCheck boolean
     */
    public void setBlockCheck(boolean blockCheck) {
        this.blockCheck = blockCheck;
    }

    /**
     * Getter of start.
     * 
     * @return start.
     */
    public Long getStart() {
        return start;
    }

    /**
     * Getter of timeout.
     * 
     * @return timeout
     */
    public Long getTimeout() {
        return timeout;
    }

    /**
     * Getter of ipaid.
     * 
     * @return ipaid
     */
    public Long getIpaid() {
        return ipaid;
    }

    /**
     * Getter of ipaStatus.
     * 
     * @return ipaStatus
     */
    public char getIpaStatus() {
        return ipaStatus;
    }

    /**
     * Setter of start.
     * 
     * @param start
     */
    public void setStart(Long start) {
        this.start = start;
    }

    /**
     * Setter of timeout.
     * 
     * @param timeout
     */
    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    /**
     * Setter of ipaid.
     * 
     * @param ipaid
     */
    public void setIpaid(Long ipaid) {
        this.ipaid = ipaid;
    }

    /**
     * Setter of ipaStatus.
     * 
     * @param ipaStatus
     */
    public void setIpaStatus(char ipaStatus) {
        this.ipaStatus = ipaStatus;
    }

    public CRBigDecimal getChange() {
        return change;
    }

    public void setChange(CRBigDecimal change) {
        this.change = change;
    }

}
