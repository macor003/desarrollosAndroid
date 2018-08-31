/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa.Entrega;
import crjpa.Entregaformadepago;
import crjpa.Formadepago;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */

public class EntregaformadepagoJpaController extends AbstractJPAController {

    private static final String FINDBYIDANDFDP = "SELECT e FROM Entregaformadepago e WHERE e.idEntrega =:identrega AND e.idFormadepago = :fdp";

    /**
     * Field entityName.
     */
    private static String entityName = "Entregaformadepago";

    /**
     * Field jpaController.
     */
    private crjpa.EntregaformadepagoJpaController jpaController;

    /**
     * Constructor for EntregaformadepagoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public EntregaformadepagoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.EntregaformadepagoJpaController(emf);
    }

    public List<Entregaformadepago> findByDateAndType(Entrega delivery, Formadepago type) {

        List<Entregaformadepago> resp = new ArrayList<Entregaformadepago>();

        try {
            EntityManager em = jpaController.getEntityManager();
            Query query = em.createQuery(FINDBYIDANDFDP);
            query.setParameter("identrega", delivery);
            query.setParameter("fdp", type);
            resp = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
