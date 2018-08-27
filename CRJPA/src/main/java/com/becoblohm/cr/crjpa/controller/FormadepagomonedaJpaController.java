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

import crjpa.Formadepagomoneda;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class FormadepagomonedaJpaController extends AbstractJPAController {// extends
                                                                           // crjpa.FormadepagoMonedaJpaController
                                                                           // {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field jpacontroller.
     */
    private crjpa.FormadepagomonedaJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Formadepagomoneda";

    /**
     * Constructor for FormadepagomonedaJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public FormadepagomonedaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpacontroller = new crjpa.FormadepagomonedaJpaController(emf);
    }

    /*
     * Metodos propios
     */

    /**
     * Method findFormadepagoMonedaEntities.
     * 
     * @param idFDP Long
     * @return List<Formadepagomoneda>
     */
    public List<Formadepagomoneda> findFormadepagoMonedaEntities(Long idFDP) {
        EntityManager em = jpacontroller.getEntityManager();
        try {
            Query query = em
                    .createQuery("SELECT fdpm FROM FormadepagoMoneda fdpm WHERE fdpm.idFormadepago.id = :idFDP");
            query.setParameter("idFDP", idFDP);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * 
     * 
     * @param status String
     * @return List<Formadepagomoneda>
     */
    public List<Formadepagomoneda> findFormadepagoMonedaEntitiesByStatus(String status) {
        EntityManager em = jpacontroller.getEntityManager();
        try {
            Query query = em.createNamedQuery("Formadepagomoneda.findByEstaactivo");
            query.setParameter("estaactivo", status);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
