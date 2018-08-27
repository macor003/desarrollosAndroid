/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.Date;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Money;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Moneda;
import crjpa.Monedatasacambio;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class MonedaJpaController extends AbstractJPAController {

    /**
     * Field jpaController.
     */
    private crjpa.MonedaJpaController jpaController;

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Moneda";

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for MonedaJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public MonedaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        this.jpaController = new crjpa.MonedaJpaController(emf);
    }

    /**
     * 
     * @param currency long
     * @return Moneda
     */

    public Moneda findMoneda(long currency) {
        return jpaController.findMoneda(currency);
    }

    /**
     * Method fromJpa.
     * 
     * @param money Moneda
     * @return Money
     */
    protected Money fromJpa(Moneda money) {
        MonedatasacambioJpaController exchangeController = new MonedatasacambioJpaController(this.emf);
        Monedatasacambio exchangeTmp = exchangeController.findLastByDate(new Date(), money.getId());
        Money tmp = new Money();
        tmp.setName(money.getNombre());
        tmp.setCurrencyId(money.getId());
        tmp.setExchangeRate(new CRBigDecimal(exchangeTmp.getCambio().doubleValue(), 3));
        return tmp;
    }

    /**
     * Method findDefaultMoney.
     * 
     * @param id long
     * @return Money
     */
    public Money findDefaultMoney(long id) {
        return fromJpa(findMoneda(id));
    }

}
