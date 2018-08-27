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

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa.Correlativo;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class CorrelativoJpaController extends AbstractJPAController {

    /**
     * Field jpaController.
     */
    private crjpa.CorrelativoJpaController jpaController;

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Correlativo";

    /**
     * Constructor for CorrelativoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public CorrelativoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        jpaController = new crjpa.CorrelativoJpaController(this.emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method findCorrelativoById.
     * 
     * @param id long
     * @return long
     */
    public long findCorrelativoById(long id) {
        Correlativo result = jpaController.findCorrelativo(id);
        return result.getValor();
    }

    /**
     * Method storeCorrelativoInDb.
     * 
     * @param id long
     * @param valor long
     * @return boolean
     * @throws JpaException
     */
    public boolean storeCorrelativoInDb(long id, long valor) throws JpaException {
        boolean isStored = false;
        EntityManager em = this.emf.createEntityManager();
        Correlativo correlativo = jpaController.findCorrelativo(id);
        correlativo.setValor(valor);
        try {
            em.getTransaction().begin();
            em.merge(correlativo);
            em.getTransaction().commit();
            isStored = true;
        } catch (Exception ex) {
            isStored = false;
            ex.printStackTrace();
            throw new JpaException();
        }
        return isStored;
    }

    /**
     * Method findCorrelativeEntities.
     * 
     * @return List<Correlativo>
     */
    public List<Correlativo> findCorrelativeEntities() {
        return jpaController.findCorrelativoEntities();
    }
}
