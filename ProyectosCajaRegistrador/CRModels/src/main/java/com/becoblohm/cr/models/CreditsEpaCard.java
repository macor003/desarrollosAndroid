/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.Date;
import java.util.List;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 */
public class CreditsEpaCard extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = -7327814552477571606L;

    /**
     * Field id.
     */
    private Long id;

    /**
     * Field clientId.
     */
    private String clientId;

    /**
     * Field clientName.
     */
    private String clientName;

    /**
     * Field type.
     */
    private String type;

    /**
     * Field status.
     */
    private char status;

    /**
     * Field creationDate.
     */
    private Date creationDate;

    /**
     * Field updateDate.
     */
    private Date updateDate;

    /**
     * Field expirationDate.
     */
    private Date expirationDate;

    /**
     * Field delayPayDate.
     */
    private Date delayPayDate;

    /**
     * Field lastPayDate.
     */
    private Date lastPayDate;

    /**
     * Field creditLine.
     */
    private CRBigDecimal creditLine;

    /**
     * Field outstandingDebt.
     */
    private CRBigDecimal outstandingDebt;

    /**
     * Field creditsEpaCardPays.
     */
    private List<CreditsEpaCardPays> creditsEpaCardPays;

    /**
     * Constructor for CreditsEpaCard.
     * 
     * @param id Long
     * @param clientId String
     * @param clientName String
     * @param type String
     * @param status char
     * @param creationDate Date
     * @param updateDate Date
     * @param expirationDate Date
     * @param delayPayDate Date
     * @param lastPayDate Date
     * @param creditLine CRBigDecimal
     * @param outstandingDebt CRBigDecimal
     */
    public CreditsEpaCard(Long id, String clientId, String clientName, String type, char status, Date creationDate,
                          Date updateDate, Date expirationDate, Date delayPayDate, Date lastPayDate,
                          CRBigDecimal creditLine, CRBigDecimal outstandingDebt) {
        super();
        this.id = id;
        this.clientId = clientId;
        this.clientName = clientName;
        this.type = type;
        this.status = status;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.expirationDate = expirationDate;
        this.delayPayDate = delayPayDate;
        this.lastPayDate = lastPayDate;
        this.creditLine = creditLine;
        this.outstandingDebt = outstandingDebt;
    }

    /**
     * Constructor for CreditsEpaCard.
     */
    public CreditsEpaCard() {
        super();
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
     * Method getClientId.
     * 
     * @return String
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Method setClientId.
     * 
     * @param clientId String
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Method getClientName.
     * 
     * @return String
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Method setClientName.
     * 
     * @param clientName String
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Method getClientType.
     * 
     * @return String
     */
    public String getClientType() {
        return type;
    }

    /**
     * Method setClientType.
     * 
     * @param type String
     */
    public void setClientType(String type) {
        this.type = type;
    }

    /**
     * Method getStatus.
     * 
     * @return char
     */
    public char getStatus() {
        return status;
    }

    /**
     * Method setStatus.
     * 
     * @param status char
     */
    public void setStatus(char status) {
        this.status = status;
    }

    /**
     * Method getCreationDate.
     * 
     * @return Date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Method setCreationDate.
     * 
     * @param creationDate Date
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Method getUpdateDate.
     * 
     * @return Date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Method setUpdateDate.
     * 
     * @param updateDate Date
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * Method getExpirationDate.
     * 
     * @return Date
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Method setExpirationDate.
     * 
     * @param expirationDate Date
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Method getDelayPayDate.
     * 
     * @return Date
     */
    public Date getDelayPayDate() {
        return delayPayDate;
    }

    /**
     * Method setDelayPayDate.
     * 
     * @param delayPayDate Date
     */
    public void setDelayPayDate(Date delayPayDate) {
        this.delayPayDate = delayPayDate;
    }

    /**
     * Method getLastPayDate.
     * 
     * @return Date
     */
    public Date getLastPayDate() {
        return lastPayDate;
    }

    /**
     * Method setLastPayDate.
     * 
     * @param lastPayDate Date
     */
    public void setLastPayDate(Date lastPayDate) {
        this.lastPayDate = lastPayDate;
    }

    /**
     * Method getCreditLine.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getCreditLine() {
        return creditLine;
    }

    /**
     * Method setCreditLine.
     * 
     * @param creditLine CRBigDecimal
     */
    public void setCreditLine(CRBigDecimal creditLine) {
        this.creditLine = creditLine;
    }

    /**
     * Method getOutstandingDebt.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getOutstandingDebt() {
        return outstandingDebt;
    }

    /**
     * Method setOutstandingDebt.
     * 
     * @param outstandingDebt CRBigDecimal
     */
    public void setOutstandingDebt(CRBigDecimal outstandingDebt) {
        this.outstandingDebt = outstandingDebt;
    }

    /**
     * Method getCreditsEpaCardPays.
     * 
     * @return List<CreditsEpaCardPays>
     */
    public List<CreditsEpaCardPays> getCreditsEpaCardPays() {
        return creditsEpaCardPays;
    }

    /**
     * Method setCreditsEpaCardPays.
     * 
     * @param creditsEpaCardPays List<CreditsEpaCardPays>
     */
    public void setCreditsEpaCardPays(List<CreditsEpaCardPays> creditsEpaCardPays) {
        this.creditsEpaCardPays = creditsEpaCardPays;
    }

}
