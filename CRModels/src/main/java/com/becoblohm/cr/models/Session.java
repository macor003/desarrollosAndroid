/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.Date;

import com.becoblohm.cr.interfaces.AbstractAuthorizableModel;
import com.becoblohm.cr.types.AuditTables;
import com.becoblohm.cr.types.CRBigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Representa una sesión de usuario donde se mantiene información de las transacciones
 * realizadas. Permanece abierta hasta el cierre de sesión o cierre de día.
 */
public class Session extends AbstractAuthorizableModel {

    private static final long serialVersionUID = 8855974786372792372L;

    /**
     * Constructor for Session.
     */
    public Session() {
        super(AuditTables.SESSION);
        salesCount = 0;
        anulCount = 0;
        devCount = 0;
        setCashMoney(CRBigDecimal.ZERO);
        setDeliveredExceeded(false);
    }

    /**
     * Constructor for Session.
     * 
     * @param s com.becoblohm.cr.net.models.Session
     */
    public Session(com.becoblohm.cr.net.models.Session s) {
        super(AuditTables.SESSION);
        this.anulCount = s.getAnulCount();
        this.begin = s.getBegin();

        this.cashier = new User(s.getCashier());

        this.cashMoney = new CRBigDecimal(s.getCashMoney().doubleValue());
        this.vposClosureDate = s.getVposClosureDate();
        this.deliveredExceeded = s.isDeliveredExceeded();
        this.devCount = s.getDevCount();
        this.end = s.getEnd();
        this.id = s.getId();
        this.idTransactionExceeded = s.getIdTransactionExceeded();
        this.posId = s.getPosId();
        this.salesCount = s.getSalesCount();
        this.storeId = s.getStoreId();
        this.zReport_printDate = s.getzReport_printDate();
    }

    /**
     * Field deliveredExceeded.
     */
    private boolean deliveredExceeded;

    /**
     * Field id.
     */
    private long id;

    /**
     * Field salesCount.
     */
    private int salesCount;

    /**
     * Field anulCount.
     */
    private int anulCount;

    /**
     * Field devCount.
     */
    private int devCount;

    /**
     * Field end.
     */
    /**
     * Field begin.
     */
    private Date begin, end;

    /**
     * Field cashier.
     */
    private User cashier;

    /**
     * Field storeId.
     */
    protected String storeId;

    protected Long storeAgilId;

    /**
     * Field posId.
     */
    protected String posId;

    /**
     * Field cashMoney.
     */
    private CRBigDecimal cashMoney = CRBigDecimal.ZERO;

    /**
     * Field vposClosureDate.
     */
    private Date vposClosureDate;

    /**
     * Field zReport_printDate.
     */
    private Date zReport_printDate;

    /**
     * Field idTransactionExceeded.
     */
    private String idTransactionExceeded;

    /**
     * Field loggedIn.
     */
    private boolean loggedIn = false;

    /**
     * Field posVersion.
     */
    private String posVersion;

    /**
     * Field currentTransactionId.
     */
    private long currentTransactionId;

    /**
     * Field currentProcess.
     */
    private Process currentProcess;

    /**
     * Field lastTransaction.
     */
    private Transaction lastTransaction;

    /**
     * 
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 
     * @return the salesCount
     */
    public int getSalesCount() {
        return salesCount;
    }

    /**
     * @param salesCount the salesCount to set
     */
    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    /**
     * 
     * @return the anulCount
     */
    public int getAnulCount() {
        return anulCount;
    }

    /**
     * @param anulCount the anulCount to set
     */
    public void setAnulCount(int anulCount) {
        this.anulCount = anulCount;
    }

    /**
     * 
     * @return the devCount
     */
    public int getDevCount() {
        return devCount;
    }

    /**
     * @param devCount the devCount to set
     */
    public void setDevCount(int devCount) {
        this.devCount = devCount;
    }

    /**
     * 
     * @return the begin
     */
    public Date getBegin() {
        return begin;
    }

    /**
     * @param begin the begin to set
     */
    public void setBegin(Date begin) {
        this.begin = begin;
    }

    /**
     * 
     * @return the end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * 
     * @return the cashier
     */
    public User getCashier() {
        return cashier;
    }

    /**
     * @param cashier the cashier to set
     */
    public void setCashier(User cashier) {
        this.cashier = cashier;
    }

    /**
     * Method getStoreId.
     * 
     * @return String
     */
    public String getStoreId() {
        return storeId;
    }

    /**
     * Method setStoreId.
     * 
     * @param storeId String
     */
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    /**
     * Method getPosId.
     * 
     * @return String
     */
    public String getPosId() {
        return posId;
    }

    /**
     * Method setPosId.
     * 
     * @param posId String
     */
    public void setPosId(String posId) {
        String tmp = this.posId;
        this.posId = posId;
        fire("posId", tmp, this.posId);
    }

    /**
     * @param cashMoney the cashMoney to set
     */
    public void setCashMoney(CRBigDecimal cashMoney) {
        this.cashMoney = cashMoney;
    }

    /**
     * 
     * @return the cashMoney
     */
    public CRBigDecimal getCashMoney() {
        return cashMoney;
    }

    /**
     * @param deliveredExceeded the deliveredExceeded to set
     */
    public void setDeliveredExceeded(boolean deliveredExceeded) {
        this.deliveredExceeded = deliveredExceeded;
    }

    /**
     * 
     * @return the deliveredExceeded
     */
    public boolean isDeliveredExceeded() {
        return deliveredExceeded;
    }

    /**
     * @param vposClosureDate the vposClosureDate to set
     */
    public void setVposClosureDate(Date vposClosureDate) {
        this.vposClosureDate = vposClosureDate;
    }

    /**
     * 
     * @return the vposClosureDate
     */
    public Date getVposClosureDate() {
        return vposClosureDate;
    }

    /* Fecha y hora de la Sesion */
    /**
     * Method getDateTime.
     * 
     * @return Date
     */
    public Date getDateTime() {
        return new Date();
    }

    /**
     * @param idTransactionExceeded the idTransactionExceeded to set
     */
    public void setIdTransactionExceeded(String idTransactionExceeded) {
        this.idTransactionExceeded = idTransactionExceeded;
    }

    /**
     * 
     * @return the idTransactionExceeded
     */
    public String getIdTransactionExceeded() {
        return idTransactionExceeded;
    }

    /**
     * Method getzReport_printDate.
     * 
     * @return Date
     */
    public Date getzReport_printDate() {
        return zReport_printDate;
    }

    /**
     * Method setzReport_printDate.
     * 
     * @param zReport_printDate Date
     */
    public void setzReport_printDate(Date zReport_printDate) {
        this.zReport_printDate = zReport_printDate;
    }

    // public void setClosureDate(Date closureDate) {
    // this.closureDate = closureDate;
    // }
    // public Date getClosureDate() {
    // return closureDate;
    // }
    /**
     * Method getServerSessionId.
     * 
     * @return long
     */
    @Deprecated
    @JsonIgnore
    public long getServerSessionId() {
        return Long.valueOf(this.getPosId() + "" + this.getId());
    }

    /**
     * Method clear.
     */
    public void clear() {

        id = 0;
        salesCount = 0;
        anulCount = 0;
        devCount = 0;
        begin = end = vposClosureDate = zReport_printDate = null;
        cashier.setId(null);
        cashier.setIdDocument(" ");
        cashier.setIdEPA(0);
        cashier.setName(" ");
        cashier.setPass(" ");

        loggedIn = false;

        idTransactionExceeded = "";
        setCashMoney(CRBigDecimal.ZERO);
        setDeliveredExceeded(false);

    }

    /**
     * Method isLoggedIn.
     * 
     * @return boolean
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * Method toNetModel.
     * 
     * @return com.becoblohm.cr.net.models.Session
     */
    public com.becoblohm.cr.net.models.Session toNetModel() {

        com.becoblohm.cr.net.models.Session tmpSession = new com.becoblohm.cr.net.models.Session();

        tmpSession.setAnulCount(anulCount);
        tmpSession.setBegin(begin);
        tmpSession.setCashier(cashier.toNetModel());
        tmpSession.setCashMoney(new com.becoblohm.cr.net.types.CRBigDecimal(cashMoney.doubleValue()));
        tmpSession.setDeliveredExceeded(deliveredExceeded);
        tmpSession.setDevCount(devCount);
        tmpSession.setEnd(end);
        tmpSession.setId(id);
        tmpSession.setIdTransactionExceeded(idTransactionExceeded);
        tmpSession.setPosId(posId);
        tmpSession.setSalesCount(salesCount);
        tmpSession.setStoreId(storeId);
        tmpSession.setVposClosureDate(vposClosureDate);
        tmpSession.setzReport_printDate(zReport_printDate);

        return tmpSession;
    }

    /**
     * Method getPosVersion.
     * 
     * @return String
     */
    public String getPosVersion() {
        return posVersion;
    }

    /**
     * Method setPosVersion.
     * 
     * @param posVersion String
     */
    public void setPosVersion(String posVersion) {
        this.posVersion = posVersion;
    }

    /**
     * Method setCurrentTransactionId.
     * 
     * @param currentTransactionId long
     */
    public void setCurrentTransactionId(long currentTransactionId) {
        this.currentTransactionId = currentTransactionId;
    }

    /**
     * Method getCurrentTransactionId.
     * 
     * @return long
     */
    public long getCurrentTransactionId() {
        return currentTransactionId;
    }

    /**
     * Method getLastTransactionNumber.
     * 
     * @return long
     */
    /*
     * public long getLastTransactionNumber() { return lastTransactionNumber; }
     */

    /**
     * Method setLastTransactionNumber.
     * 
     * @param lastTransactionNumber long
     */
    /*
     * public void setLastTransactionNumber(long lastTransactionNumber) {
     * this.lastTransactionNumber = lastTransactionNumber; }
     */

    /**
     * Method setCurrentProcess.
     * 
     * @param currentProcess Process
     */
    public void setCurrentProcess(Process currentProcess) {
        this.currentProcess = currentProcess;

    }

    /**
     * Method getCurrentProcess.
     * 
     * @return Process
     */
    public Process getCurrentProcess() {
        return currentProcess;
    }

    /**
     * Method getLastTransaction.
     * 
     * @return Transaction
     */
    public Transaction getLastTransaction() {
        return lastTransaction;
    }

    /**
     * Method setLastTransaction.
     * 
     * @param lastTransaction Transaction
     */
    public void setLastTransaction(Transaction lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

    @Override
    public String getAuditID() {
        return String.valueOf(this.getId());
    }

    public Long getStoreAgilId() {
        return storeAgilId;
    }

    public void setStoreAgilId(Long storeIdAgil) {
        this.storeAgilId = storeIdAgil;
    }

}
