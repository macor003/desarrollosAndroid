/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.ArrayList;
import java.util.Collection;

import com.becoblohm.cr.models.Deposit.SourceStatus;
import com.becoblohm.cr.models.Deposit.SourceType;
import com.becoblohm.cr.types.CRBigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 */
public class Order extends Transaction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field orderType.
     */
    private OrderType orderType;

    /**
     * Field posIdSale.
     */
    private long posIdSale;

    /**
     * Field storeIdSale.
     */
    private long storeIdSale;

    /**
     * Field transactionsSale.
     */
    private ArrayList<Transaction> transactionsSale;

    /**
     * Field statusOrder.
     */
    private StatusOrder statusOrder;

    /**
     * Field deposits.
     */
    private ArrayList<Deposit> deposits;

    /**
     * Field orderUser.
     */
    private String orderUser;

    /**
     * Field orderNumber.
     */
    private String orderNumber;

    /**
     * Field retentionAmount.
     */
    private CRBigDecimal retentionAmount;

    /**
     * Field serverOrderId.
     */
    private long serverOrderId;

    /**
     * Field purchaseOrderId.
     */
    private long purchaseOrderId;

    /**
     * Field creditsDays.
     */
    private int creditsDays;

    /**
     * Field EPALiableId.
     */
    private long EPALiableId;

    /**
     * Field EPALiableName.
     */
    private String EPALiableName;

    /**
     * Field fatherOrder.
     */
    private Order fatherOrder;

    /**
     * Field penaltyAmount.
     */
    private CRBigDecimal penaltyAmount;

    /**
     * Field firstDepositAmount.
     */
    private CRBigDecimal firstDepositAmount;

    /**
     * Field articlesInStore.
     */
    private boolean articlesInStore;

    /**
     * Field validity.
     */
    private int validity;

    /**
     * <p>
     * Metodo que permite adicionar una nueva transaccion a la orden.
     * 
     * @param transaction la nueva transacion a adjuntar
     */
    public void addSale(Transaction transaction) {
        this.transactionsSale.add(transaction);
    }

    /**
     * Method getOrderUser.
     * 
     * @return String
     */
    public String getOrderUser() {
        return this.orderUser;
    }

    /**
     * Method setOrderUser.
     * 
     * @param user String
     */
    public void setOrderUser(String user) {
        this.orderUser = user;
    }

    /**
     * Method getPosIdSale.
     * 
     * @return long
     */
    public long getPosIdSale() {
        return posIdSale;
    }

    /**
     * Method setPosIdSale.
     * 
     * @param posIdSale long
     */
    public void setPosIdSale(long posIdSale) {
        this.posIdSale = posIdSale;
    }

    /**
     * Method getStoreIdSale.
     * 
     * @return long
     */
    public long getStoreIdSale() {
        return storeIdSale;
    }

    /**
     * Method setStoreIdSale.
     * 
     * @param storeIdSale long
     */
    public void setStoreIdSale(long storeIdSale) {
        this.storeIdSale = storeIdSale;
    }

    /**
     * Method getDeposits.
     * 
     * @return ArrayList<Deposit>
     */
    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    /**
     * Method getLastDeposits.
     * 
     * @return Deposit
     */
    public Deposit getLastDeposits() {
        if (deposits != null && deposits.size() > 0) {
            return deposits.get(deposits.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * Method setDeposits.
     * 
     * @param deposits ArrayList<Deposit>
     */
    public void setDeposits(ArrayList<Deposit> deposits) {
        ArrayList<Deposit> tmp = new ArrayList<Deposit>();
        if (this.deposits != null) {
            tmp.addAll(this.deposits);
        }
        this.deposits = deposits;
        this.fire("deposits", tmp, deposits);
    }

    /**
     * Method getOrderType.
     * 
     * @return OrderType
     */
    public OrderType getOrderType() {
        return orderType;
    }

    /**
     * Method setOrderType.
     * 
     * @param orderType OrderType
     */
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    /**
     * Method addDeposit.
     * 
     * @param deposit Deposit
     */
    public void addDeposit(Deposit deposit) {
        Collection<Deposit> tmp = new ArrayList<Deposit>();
        tmp.addAll(this.deposits);
        this.deposits.add(deposit);

        if (deposit.getType() == SourceType.Deposit) {
            this.setTotalPay(this.getTotalPay().add(deposit.getAmount()));
        } else {
            this.setTotalPay(this.getTotalPay().subtract(deposit.getAmount()));
        }

        this.fire("deposits", tmp, deposits);
    }

    /**
     * Method removeDeposit.
     * 
     * @param depositToRemove Deposit
     */
    public void removeDeposit(Deposit depositToRemove) {
        Collection<Deposit> tmp = new ArrayList<Deposit>();
        tmp.addAll(this.deposits);

        for (Deposit deposit : this.deposits) {
            if (deposit.getNumber() == depositToRemove.getNumber()) {
                this.deposits.remove(deposit);
                this.setTotalPay(this.getTotalPay().subtract(deposit.getAmount()));
            }
        }

        this.fire("deposits", tmp, deposits);
    }

    /**
     * Method getStatusOrder.
     * 
     * @return StatusOrder
     */
    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    /**
     * Method setStatusOrder.
     * 
     * @param statusOrder StatusOrder
     */
    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    /**
     * Method getTransactionsSale.
     * 
     * @return ArrayList<Transaction>
     */
    public ArrayList<Transaction> getTransactionsSale() {
        return transactionsSale;
    }

    /**
     * Method setTransactionsSale.
     * 
     * @param transactionsSale ArrayList<Transaction>
     */
    public void setTransactionsSale(ArrayList<Transaction> transactionsSale) {
        this.transactionsSale = transactionsSale;
    }

    /**
     * Method getDepositsActive.
     * 
     * @return ArrayList<Deposit>
     */
    @JsonIgnore
    public ArrayList<Deposit> getDepositsActive() {
        ArrayList<Deposit> result = new ArrayList<Deposit>();
        for (Deposit deposit : this.deposits) {
            if (deposit.getStatus() == SourceStatus.Active) {
                result.add(deposit);
            }
        }
        return result;
    }

    /**
     * Method getOrderNumber.
     * 
     * @return String
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Method setOrderNumber.
     * 
     * @param orderNumber String
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Method getRetentionAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getRetentionAmount() {
        return retentionAmount;
    }

    /**
     * Method setRetentionAmount.
     * 
     * @param retentionAmount CRBigDecimal
     */
    public void setRetentionAmount(CRBigDecimal retentionAmount) {
        this.retentionAmount = retentionAmount;
    }

    /**
     * Method getServerOrderId.
     * 
     * @return long
     */
    public long getServerOrderId() {
        return serverOrderId;
    }

    /**
     * Method setServerOrderId.
     * 
     * @param serverOrderId long
     */
    public void setServerOrderId(long serverOrderId) {
        this.serverOrderId = serverOrderId;
    }

    /**
     * Method getPurchaseOrderId.
     * 
     * @return long
     */
    public long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    /**
     * Method setPurchaseOrderId.
     * 
     * @param purchaseOrderId long
     */
    public void setPurchaseOrderId(long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    /**
     * Method getCreditsDays.
     * 
     * @return int
     */
    public int getCreditsDays() {
        return creditsDays;
    }

    /**
     * Method setCreditsDays.
     * 
     * @param creditsDays int
     */
    public void setCreditsDays(int creditsDays) {
        this.creditsDays = creditsDays;
    }

    /**
     * 
     * @return the ePALiableId
     */
    public long getEPALiableId() {
        return EPALiableId;
    }

    /**
     * @param ePALiableId the ePALiableId to set
     */
    public void setEPALiableId(long ePALiableId) {
        EPALiableId = ePALiableId;
    }

    /**
     * 
     * @return the ePALiableName
     */
    public String getEPALiableName() {
        return EPALiableName;
    }

    /**
     * @param ePALiableName the ePALiableName to set
     */
    public void setEPALiableName(String ePALiableName) {
        EPALiableName = ePALiableName;
    }

    /**
     * Method getFatherOrder.
     * 
     * @return Order
     */
    public Order getFatherOrder() {
        return fatherOrder;
    }

    /**
     * Method setFatherOrder.
     * 
     * @param fatherOrder Order
     */
    public void setFatherOrder(Order fatherOrder) {
        this.fatherOrder = fatherOrder;
    }

    /**
     * Method getPenaltyAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }

    /**
     * Method setPenaltyAmount.
     * 
     * @param penaltyAmount CRBigDecimal
     */
    public void setPenaltyAmount(CRBigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    /**
     * Method isArticlesInStore.
     * 
     * @return boolean
     */
    public boolean isArticlesInStore() {
        return articlesInStore;
    }

    /**
     * Method setArticlesInStore.
     * 
     * @param articlesInStore boolean
     */
    public void setArticlesInStore(boolean articlesInStore) {
        this.articlesInStore = articlesInStore;
    }

    /**
     * Method getFirstDepositAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getFirstDepositAmount() {
        return firstDepositAmount;
    }

    /**
     * Method setFirstDepositAmount.
     * 
     * @param firstDepositAmount CRBigDecimal
     */
    public void setFirstDepositAmount(CRBigDecimal firstDepositAmount) {
        this.firstDepositAmount = firstDepositAmount;
    }

    /**
     * 
     * @return the validity
     */
    public int getValidity() {
        return validity;
    }

    /**
     * @param validity the validity to set
     */
    public void setValidity(int validity) {
        this.validity = validity;
    }

}
