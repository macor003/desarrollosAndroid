/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Transaction;

import crjpa.Devolucion;
import crjpa.Transaccion;
import crjpa.exceptions.IllegalOrphanException;
import crjpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class DevolucionJpaController extends AbstractJPAController {

    /**
     * Field entityName.
     */
    private static String entityName = "Devolucion";

    /**
     * Constructor for DevolucionJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public DevolucionJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        jpaController = new crjpa.DevolucionJpaController(emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field jpaController.
     */
    private crjpa.DevolucionJpaController jpaController;

    /**
     * Method createOrEdit.
     * 
     * @param devolucion Devolucion
     * @throws IllegalOrphanException
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void createOrEdit(Devolucion devolucion)
            throws IllegalOrphanException, NonexistentEntityException, Exception {
        if (devolucion.getId() < 0) {
            devolucion.setId(null);
            this.jpaController.getEntityManager().persist(devolucion);
        } else {
            this.jpaController.getEntityManager().merge(devolucion);
        }
    }

    /**
     * Method toJPA.
     * 
     * @param refundTransaction Transaccion
     * @param original Transaction
     * @return Devolucion
     */
    public static Devolucion toJPA(Transaccion refundTransaction, Transaction original) {
        Devolucion refund = new Devolucion();
        refund.setCajaOrigen(Integer.parseInt(original.getPosId()));
        refund.setFecha(original.getDate());
        refund.setId((long) -1);
        refund.setIdTransaccion(refundTransaction);
        refund.setTiendaOrigen(Integer.parseInt(original.getStore()));
        refund.setTransaccionOrigen(Integer.parseInt(original.getNumber()));
        return refund;

    }
}
