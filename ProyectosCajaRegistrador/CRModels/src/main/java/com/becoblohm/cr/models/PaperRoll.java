/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.Date;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class PaperRoll extends AbstractModel {

    /**
     * Field id.
     */
    protected long id;

    /**
     * Field serialPrint.
     */
    protected SerialPrint serialPrint;

    /**
     * Field user.
     */
    protected User user;

    /**
     * Field pos.
     */
    protected int pos;

    /**
     * Field store.
     */
    protected int store;

    /**
     * Field firsTransaction.
     */
    protected long firsTransaction;

    /**
     * Field lastTransaction.
     */
    protected long lastTransaction;

    /**
     * Field firsSale.
     */
    protected long firsSale;

    /**
     * Field lastSale.
     */
    protected long lastSale;

    /**
     * Field firsCancellation.
     */
    protected long firsCancellation;

    /**
     * Field lastCancellation.
     */
    protected long lastCancellation;

    /**
     * Field firsRefund.
     */
    protected long firsRefund;

    /**
     * Field lastRefund.
     */
    protected long lastRefund;

    /**
     * Field changeDate.
     */
    protected Date changeDate;

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
     * Method getFirsTransaction.
     * 
     * @return long
     */
    public long getFirsTransaction() {
        return firsTransaction;
    }

    /**
     * Method setFirsTransaction.
     * 
     * @param firsTransaction long
     */
    public void setFirsTransaction(long firsTransaction) {
        this.firsTransaction = firsTransaction;
    }

    /**
     * Method getLastTransaction.
     * 
     * @return long
     */
    public long getLastTransaction() {
        return lastTransaction;
    }

    /**
     * Method setLastTransaction.
     * 
     * @param lastTransaction long
     */
    public void setLastTransaction(long lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

    /**
     * Method getFirsSale.
     * 
     * @return long
     */
    public long getFirsSale() {
        return firsSale;
    }

    /**
     * Method setFirsSale.
     * 
     * @param firsSale long
     */
    public void setFirsSale(long firsSale) {
        this.firsSale = firsSale;
    }

    /**
     * Method getLastSale.
     * 
     * @return long
     */
    public long getLastSale() {
        return lastSale;
    }

    /**
     * Method setLastSale.
     * 
     * @param lastSale long
     */
    public void setLastSale(long lastSale) {
        this.lastSale = lastSale;
    }

    /**
     * Method getFirsCancellation.
     * 
     * @return long
     */
    public long getFirsCancellation() {
        return firsCancellation;
    }

    /**
     * Method setFirsCancellation.
     * 
     * @param firsCancellation long
     */
    public void setFirsCancellation(long firsCancellation) {
        this.firsCancellation = firsCancellation;
    }

    /**
     * Method getLastCancellation.
     * 
     * @return long
     */
    public long getLastCancellation() {
        return lastCancellation;
    }

    /**
     * Method setLastCancellation.
     * 
     * @param lastCancellation long
     */
    public void setLastCancellation(long lastCancellation) {
        this.lastCancellation = lastCancellation;
    }

    /**
     * Method getFirsRefund.
     * 
     * @return long
     */
    public long getFirsRefund() {
        return firsRefund;
    }

    /**
     * Method setFirsRefund.
     * 
     * @param firsRefund long
     */
    public void setFirsRefund(long firsRefund) {
        this.firsRefund = firsRefund;
    }

    /**
     * Method getLastRefund.
     * 
     * @return long
     */
    public long getLastRefund() {
        return lastRefund;
    }

    /**
     * Method setLastRefund.
     * 
     * @param lastRefund long
     */
    public void setLastRefund(long lastRefund) {
        this.lastRefund = lastRefund;
    }

    /**
     * Method getSerialPrint.
     * 
     * @return SerialPrint
     */
    public SerialPrint getSerialPrint() {
        return serialPrint;
    }

    /**
     * Method setSerialPrint.
     * 
     * @param serialPrint SerialPrint
     */
    public void setSerialPrint(SerialPrint serialPrint) {
        this.serialPrint = serialPrint;
    }

    /**
     * Method getUser.
     * 
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Method setUser.
     * 
     * @param user User
     */
    public void setUser(User user) {
        this.user = user;
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
     * Method getChangeDate.
     * 
     * @return Date
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * Method setChangeDate.
     * 
     * @param changeDate Date
     */
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

}
