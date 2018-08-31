/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa.Monedatasacambio;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class MonedatasacambioJpaController extends AbstractJPAController {

    /**
     * Field jpaController.
     */
    private crjpa.MonedadenominacionJpaController jpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Monedatasacambio";

    /**
     * Constructor for MonedatasacambioJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public MonedatasacambioJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.MonedadenominacionJpaController(emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method findMonedaTasacambioEntities.
     * 
     * @param idMoneda long
     * @return Monedatasacambio
     */
    protected Monedatasacambio findMonedaTasacambioEntities(long idMoneda) {
        EntityManager em = this.jpaController.getEntityManager();
        Query query = em
                .createQuery("SELECT m FROM Monedatasacambio m WHERE m.idMoneda.id = :idMoneda ORDER BY m.fecha DESC");
        query.setParameter("idMoneda", idMoneda);
        query.setMaxResults(1);
        Monedatasacambio singleResult = (Monedatasacambio) query.getSingleResult();
        return singleResult;
    }

    /**
     * Method findLastByDate.
     * 
     * @param date Date
     * @param id Long
     * @return Monedatasacambio
     */
    protected Monedatasacambio findLastByDate(Date date, Long id) {
        Monedatasacambio exchange = new Monedatasacambio();
        try {
            EntityManager em = this.jpaController.getEntityManager();
            Query query = em
                    .createQuery("select m from Monedatasacambio m where m.fecha <=:fecha and m.idMoneda.id=:id order by m.fecha desc");
            query.setParameter("fecha", date);
            query.setParameter("id", id);
            query.setMaxResults(1);
            exchange = (Monedatasacambio) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            exchange.setCambio(new BigDecimal(1));
        }
        return exchange;
    }

}
