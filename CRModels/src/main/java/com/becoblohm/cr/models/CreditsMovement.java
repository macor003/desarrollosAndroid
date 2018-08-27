/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.becoblohm.cr.interfaces.AbstractAuthorizableModel;
import com.becoblohm.cr.legacy.models.CreditsTypes;
import com.becoblohm.cr.types.AuditTables;
import com.becoblohm.cr.types.CRBigDecimal;

public class CreditsMovement extends AbstractAuthorizableModel implements Serializable {

    private static final long serialVersionUID = 2993635584737824068L;

    public enum CreditMovementType {
        Deposit(1), Cancelation(2), Refund(3), RefundOp(4), Consumo(5), Query(6);

        private static final Map<Integer, CreditMovementType> lookup = new HashMap<Integer, CreditMovementType>();

        static {
            for (CreditMovementType s : EnumSet.allOf(CreditMovementType.class))
                lookup.put(s.getValue(), s);
        }

        private int value;

        CreditMovementType(int value) {
            this.setValue(value);
        }

        public static CreditMovementType get(int value) {
            return lookup.get(value);
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private CreditsAccount creditsAccount;

    private CRBigDecimal amount;

    private int processId;

    private int processIdToAnul;

    private long sessionId;

    private CreditsOperationType creditsOperationTypeId;

    private Collection<Payment> payments;

    private boolean refundByCheck;

    private char status;

    private CreditsOperation operationId;

    private CreditsBalance balanceId;

    private int pos;

    private int store;

    private int cashier;

    private Date date;

    private List<TransactionDocument> documents;

    private int id;

    private boolean allowsAuthorizedThirdParty = true;

    private boolean onlyAuthorizedThirdParty = false;

    private boolean onlySaleBetweenStore = false;

    private boolean onlyCreditsClient = false;

    private boolean allowSaleBetweenStore = true;

    private String transactionId;

    private CRBigDecimal change = CRBigDecimal.ZERO;

    private boolean blockCheck = true;

    private boolean changeWarning;

    /**
     * Field start. Hold the starting timestamp of the request
     */
    private Long start;

    /**
     * Field timeout. Hold the response time available to the server
     */
    private Long timeout;

    private Long ipaid;

    private Character ipaStatus;

    private boolean inLegacy = false;

    private CRBigDecimal totalPaid = CRBigDecimal.ZERO;

    private CRBigDecimal lastPaidAmount = CRBigDecimal.ZERO;

    /**
     * Method setChangeWarning.
     * 
     * @param showwarning boolean
     */
    public void setChangeWarning(boolean showwarning) {
        this.changeWarning = showwarning;
    }

    /**
     * Method isOnlyCreditsClient.
     * 
     * @return boolean
     */
    public boolean isOnlyCreditsClient() {
        return onlyCreditsClient;
    }

    /**
     * Method setOnlyCreditsClient.
     * 
     * @param onlyCreditsClient boolean
     */
    public void setOnlyCreditsClient(boolean onlyCreditsClient) {
        this.onlyCreditsClient = onlyCreditsClient;
    }

    /**
     * Method isOnlyAuthorizedThirdParty.
     * 
     * @return boolean
     */
    public boolean isOnlyAuthorizedThirdParty() {
        return onlyAuthorizedThirdParty;
    }

    /**
     * Method setOnlyAuthorizedThirdParty.
     * 
     * @param onlyAuthorizedThirdParty boolean
     */
    public void setOnlyAuthorizedThirdParty(boolean onlyAuthorizedThirdParty) {
        this.onlyAuthorizedThirdParty = onlyAuthorizedThirdParty;
    }

    /**
     * Method isOnlySaleBetweenStore.
     * 
     * @return boolean
     */
    public boolean isOnlySaleBetweenStore() {
        return onlySaleBetweenStore;
    }

    /**
     * Method setOnlySaleBetweenStore.
     * 
     * @param onlySaleBetweenStore boolean
     */
    public void setOnlySaleBetweenStore(boolean onlySaleBetweenStore) {
        this.onlySaleBetweenStore = onlySaleBetweenStore;
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
        super(AuditTables.CREDITSMOVEMENT);
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
        super(AuditTables.CREDITSMOVEMENT);
        this.setCreditsAccount(creditsAccount);
        this.processId = processId;
        this.creditsOperationTypeId = creditsOperationTypeId;
        this.amount = CRBigDecimal.ZERO;
    }

    /**
     * Constructor for CreditsMovement.
     * 
     * @param toNetModel com.becoblohm.cr.net.models.CreditsMovement
     */
    public CreditsMovement(com.becoblohm.cr.net.models.CreditsMovement toNetModel) {
        super(AuditTables.CREDITSMOVEMENT);
        this.setAllowsAuthorizedThirdParty(toNetModel.isAllowsAuthorizedThirdParty());
        if (toNetModel.getAmount() != null) {
            this.setAmount(new CRBigDecimal(toNetModel.getAmount().getValue().doubleValue()));
        }
        this.setBalanceId(new CreditsBalance(toNetModel.getBalanceId()));
        this.setCashier(toNetModel.getCashier());
        this.setCreditsAccount(new CreditsAccount(toNetModel.getCreditsAccount()));

        CreditsTypes creditsTypesCR = new CreditsTypes(
                toNetModel.getCreditsOperationTypeId().getCreditsTypesCR().getType());
        CreditsOperationType cot = new CreditsOperationType(toNetModel.getCreditsOperationTypeId().getId(),
                creditsTypesCR, toNetModel.getCreditsOperationTypeId().getCondition());
        CreditsType ct = new CreditsType(toNetModel.getCreditsOperationTypeId().getCreditsType().getId(),
                toNetModel.getCreditsOperationTypeId().getCreditsType().getDescription());
        CreditsOperation co = new CreditsOperation(
                toNetModel.getCreditsOperationTypeId().getCreditsOperation().getId(),
                toNetModel.getCreditsOperationTypeId().getCreditsOperation().getDescription());

        cot.setCreditsType(ct);
        cot.setCreditsOperation(co);
        this.setCreditsOperationTypeId(cot);
        this.setDate(toNetModel.getDate());
        ArrayList<TransactionDocument> docs = new ArrayList<TransactionDocument>();

        if (toNetModel.getDocuments() != null) {
            for (Object netDoc : toNetModel.getDocuments()) {
                docs.add(new TransactionDocument((com.becoblohm.cr.net.models.TransactionDocument) netDoc));
            }
            this.setDocuments(docs);
        }

        this.setId(toNetModel.getId());
        if (toNetModel.getOperationId() != null) {
            this.setOperationId(new CreditsOperation(toNetModel.getOperationId().getId(),
                    toNetModel.getOperationId().getDescription()));
        }

        ArrayList<Payment> payments = new ArrayList<Payment>();
        for (Object netPayment : toNetModel.getPayments()) {
            payments.add(new Payment((com.becoblohm.cr.net.models.Payment) netPayment));
        }

        this.setPayments(payments);
        this.setPos(toNetModel.getPos());
        this.setProcessId(toNetModel.getProcessId());
        this.setProcessIdToAnul(toNetModel.getProcessIdToAnul());
        this.setRefundByCheck(toNetModel.isRefundByCheck());
        this.setStatus(toNetModel.getStatus());
        this.setStore(toNetModel.getStore());
        if (toNetModel.getTransactionId() != null && toNetModel.getTransactionId().trim().length() > 0) {
            this.setTransactionId(toNetModel.getTransactionId());
        }
        this.setBlockCheck(toNetModel.isBlockCheck());
        this.setIpaid(toNetModel.getIpaid());
        this.setIpaStatus(toNetModel.getIpaStatus());
        this.setTimeout(toNetModel.getTimeout());
        this.setChange(new CRBigDecimal(toNetModel.getChange().getValue().doubleValue()));
    }

    /**
     * Constructor for CreditsMovement.
     */
    public CreditsMovement() {
        super(AuditTables.CREDITSMOVEMENT);
        this.amount = CRBigDecimal.ZERO;
    }

    /**
     * Constructor for CreditsMovement.
     * 
     * @param creditsAccount2 CreditsAccount
     * @param amount CRBigDecimal
     */
    public CreditsMovement(CreditsAccount creditsAccount2, CRBigDecimal amount) {
        super(AuditTables.CREDITSMOVEMENT);
        this.creditsAccount = creditsAccount2;
        this.amount = amount;

    }

    /**
     * Constructor for CreditsMovement.
     * 
     * @param creditsAccount CreditsAccount
     * @param creditsOperationTypeId CreditsOperationType
     * @param payments Collection<Payment>
     * @param amount CRBigDecimal
     */
    public CreditsMovement(CreditsAccount creditsAccount, CreditsOperationType creditsOperationTypeId,
                           Collection<Payment> payments, CRBigDecimal amount) {
        super(AuditTables.CREDITSMOVEMENT);
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
     * @return CreditsOperationType - the operationType from CR400.TIPOACREENCIAOPERACION
     */
    public CreditsOperationType getCreditsOperationTypeId() {
        return creditsOperationTypeId;
    }

    /**
     * @param payments the payments to set
     */
    public void setPayments(Collection<Payment> payments) {
        this.payments = payments;
    }

    /**
     * 
     * @return the payments
     */
    public Collection<Payment> getPayments() {
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
     * @param documents List<TransactionDocument>
     */
    public void setDocuments(List<TransactionDocument> documents) {
        this.documents = documents;
    }

    /**
     * Method getDocuments.
     * 
     * @return List<TransactionDocument>
     */
    public List<TransactionDocument> getDocuments() {
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
     * Method toNetModel.
     * 
     * @return com.becoblohm.cr.net.models.CreditsMovement
     */
    public com.becoblohm.cr.net.models.CreditsMovement toNetModel(boolean isReprint) {
        com.becoblohm.cr.net.models.CreditsMovement mov = new com.becoblohm.cr.net.models.CreditsMovement();
        mov.setAllowsAuthorizedThirdParty(this.isAllowsAuthorizedThirdParty());
        mov.setAmount(new com.becoblohm.cr.net.types.CRBigDecimal(this.getAmount().getValue().doubleValue()));
        if (this.getBalanceId() != null) {
            mov.setBalanceId(this.getBalanceId().toNetModel());
        }
        mov.setCashier(this.getCashier());
        mov.setCreditsAccount(this.getCreditsAccount().toNetModel());
        if (!isReprint) {
            mov.setCreditsOperationTypeId(this.getCreditsOperationTypeId().toNetModel());
        }
        mov.setDate(this.getDate());
        ArrayList docs = new ArrayList();
        if (this.getDocuments() != null) {
            for (TransactionDocument doc : this.getDocuments()) {
                docs.add(doc.toNetTransactionDocument());
            }
        }
        mov.setDocuments(docs);
        mov.setId(this.getId());
        if (this.getOperationId() != null) {
            mov.setOperationId(this.getOperationId().toNetModel());
        }
        if (this.getPayments() != null) {
            ArrayList payments = new ArrayList();
            for (Payment pay : this.getPayments()) {
                payments.add(pay.toNetModel());

            }
            mov.setPayments(payments);
        }

        if (this.getTransactionId() != null && this.getTransactionId().trim().length() > 0) {
            mov.setTransactionId(this.getTransactionId());
        }

        mov.setPos(this.getPos());
        mov.setProcessId(this.getProcessId());
        mov.setProcessIdToAnul(this.getProcessIdToAnul());
        mov.setRefundByCheck(this.isRefundByCheck());
        mov.setStatus(this.status);
        mov.setStore(this.getStore());
        mov.setBlockCheck(this.isBlockCheck());
        mov.setIpaid(this.getIpaid());
        mov.setTimeout(this.getTimeout());
        mov.setStart(this.getStart());
        mov.setChange(new com.becoblohm.cr.net.types.CRBigDecimal(this.getChange().doubleValue()));
        return mov;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * 
     * @return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * 
     * @param value CRBigDecimal
     */
    public void setChange(CRBigDecimal value) {
        this.change = value;
    }

    /**
     * 
     * @return the change
     */
    public CRBigDecimal getChange() {
        return change;
    }

    /**
     * Method isChangeWarning.
     * 
     * @return boolean
     */
    public boolean isChangeWarning() {

        return changeWarning;
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
     * Method isAllowSaleBetweenStore.
     * 
     * @return boolean
     */
    public boolean isAllowSaleBetweenStore() {
        return allowSaleBetweenStore;
    }

    /**
     * Method setAllowSaleBetweenStore.
     * 
     * @param allowSaleBetweenStore boolean
     */
    public void setAllowSaleBetweenStore(boolean allowSaleBetweenStore) {
        this.allowSaleBetweenStore = allowSaleBetweenStore;
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
     * Method getSessionId.
     * 
     * @return long
     */
    public long getSessionId() {
        return sessionId;
    }

    /**
     * Method setSessionId.
     * 
     * @param sessionId long
     */
    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
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
     * @return ipaStatus.
     */
    public Character getIpaStatus() {
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
    public void setIpaStatus(Character ipaStatus) {
        this.ipaStatus = ipaStatus;
    }

    @Override
    public String getAuditID() {
        return String.valueOf(this.id);
    }

    public boolean isInLegacy() {
        return inLegacy;
    }

    public void setInLegacy(boolean inLegacy) {
        this.inLegacy = inLegacy;
    }

    public CRBigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(CRBigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public CRBigDecimal getLastPaidAmount() {
        return lastPaidAmount;
    }

    public void setLastPaidAmount(CRBigDecimal lastPaidAmount) {
        this.lastPaidAmount = lastPaidAmount;
    }
}
