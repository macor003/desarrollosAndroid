/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa.Morosidad;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class MorosidadJpaController extends AbstractJPAController {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field jpaController.
     */
    private crjpa.MorosidadJpaController jpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Morosidad";

    /**
     * Constructor for MorosidadJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public MorosidadJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.MorosidadJpaController(emf);
    }

    /*
     * Implementaciones propias
     */
    /**
     * Method findMorosidadEntities.
     * 
     * @param idCliente String
     * @return List<Morosidad>
     */
    public List<Morosidad> findMorosidadEntities(String idCliente) {
        EntityManager em = jpaController.getEntityManager();
        try {
            Query query = em
                    .createQuery("SELECT m FROM Morosidad m WHERE m.idCliente.numeroIdentificacion = :idCliente AND m.estaactiva = :estaactiva");
            query.setParameter("idCliente", idCliente);
            query.setParameter("estaactiva", "S");
            List list = query.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

}
