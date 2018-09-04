/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa.Tasaimpuestovalor;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TasaimpuestovalorJpaController extends AbstractJPAController {

    /**
     * Field jpaController.
     */
    private crjpa.TasaimpuestovalorJpaController jpaController;

    /**
     * Field entity.
     */
    private Class entity = crjpa.Tasaimpuestovalor.class;

    /**
     * Field entityName.
     */
    private static String entityName = "Tasaimpuestovalor";

    /**
     * Constructor for TasaimpuestovalorJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TasaimpuestovalorJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.TasaimpuestovalorJpaController(emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // public Tasaimpuestovalor findTasaivaValorByDateAndRate(Date
    // date,BigDecimal rate) {
    // EntityManager em = this.jpaController.getEntityManager();
    //
    //
    // Query query =
    // em.createQuery("SELECT t FROM "+entity.getSimpleName()+" t WHERE
    // t.fechaInicio=:fecha AND t.tasa=:tasa");
    // query.setParameter("fecha",date);
    // query.setParameter("tasa",rate);
    // query.setMaxResults(1);
    // Tasaimpuestovalor obj = (Tasaimpuestovalor) query.getSingleResult();
    // return obj;
    // }

    /**
     * Method findTasaivaValorByidTipo.
     * 
     * @param idivaTipo Long
     * @return Tasaimpuestovalor
     */
    public Tasaimpuestovalor findTasaivaValorByidTipo(Long idivaTipo) {
        EntityManager em = this.jpaController.getEntityManager();
        Query query = em
                .createQuery("SELECT t FROM Tasaimpuestovalor t WHERE t.idTasaimpuestotipo.id=:tasa order by t.fechaInicio DESC");
        // query.setParameter("fecha",new Date());
        query.setParameter("tasa", idivaTipo);
        query.setMaxResults(1);
        Tasaimpuestovalor obj = (Tasaimpuestovalor) query.getSingleResult();
        return obj;
    }

}
