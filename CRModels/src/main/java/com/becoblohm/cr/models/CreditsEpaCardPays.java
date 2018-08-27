/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class CreditsEpaCardPays extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = -7327814552477571606L;

    /**
     * Field id.
     */
    private Long id;

    /**
     * Field idCreditsEpaCard.
     */
    private Long idCreditsEpaCard;

    /**
     * Field idTransaction.
     */
    private Long idTransaction;

    /**
     * Constructor for CreditsEpaCardPays.
     * 
     * @param id Long
     * @param idCreditsEpaCard Long
     * @param idTransaction Long
     */
    public CreditsEpaCardPays(Long id, Long idCreditsEpaCard, Long idTransaction) {
        super();
        this.id = id;
        this.idCreditsEpaCard = idCreditsEpaCard;
        this.idTransaction = idTransaction;
    }

    /**
     * Constructor for CreditsEpaCardPays.
     */
    public CreditsEpaCardPays() {
        super();
    }

    /**
     * Constructor for CreditsEpaCardPays.
     * 
     * @param id2 Long
     * @param id3 long
     */
    public CreditsEpaCardPays(Long id2, long id3) {
        this.idCreditsEpaCard = id2;
        this.idTransaction = id3;
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
     * Method getIdCreditsEpaCard.
     * 
     * @return Long
     */
    public Long getIdCreditsEpaCard() {
        return idCreditsEpaCard;
    }

    /**
     * Method setIdCreditsEpaCard.
     * 
     * @param idCreditsEpaCard Long
     */
    public void setIdCreditsEpaCard(Long idCreditsEpaCard) {
        this.idCreditsEpaCard = idCreditsEpaCard;
    }

    /**
     * Method getIdTransaction.
     * 
     * @return Long
     */
    public Long getIdTransaction() {
        return idTransaction;
    }

    /**
     * Method setIdTransaction.
     * 
     * @param idTransaction Long
     */
    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

}
