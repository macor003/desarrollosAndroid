/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.models.Transaction.TransactionPhase;

import crjpa.Transaccion;
import crjpa.Transaccionfase;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TransaccionFaseJpaController extends AbstractJPAController {

    /**
     * Field jpaController.
     */
    private crjpa.TransaccionfaseJpaController jpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Transaccionfase";

    /**
     * Constructor for TransaccionFaseJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TransaccionFaseJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.TransaccionfaseJpaController(emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method toJPA.
     * 
     * @param transaction Transaction
     * @param jpatr Transaccion
     * @return List<Transaccionfase>
     */
    public List<Transaccionfase> toJPA(Transaction transaction, Transaccion jpatr) {
        List<Transaccionfase> transList = new ArrayList<Transaccionfase>();

        for (Iterator iterator = transaction.getPhases().keySet().iterator(); iterator.hasNext();) {
            TransactionPhase transaccionfase = (TransactionPhase) iterator.next();

            Transaccionfase transStage = new Transaccionfase();
            transStage.setDuracion(transaction.getPhaseTime(transaccionfase));
            transStage.setFase(transaccionfase.getValue());
            long id = transaction.getPhaseId(transaccionfase);
            if (id > 0) {
                transStage.setId(id);
            } else {
                transStage.setId(null);
            }
            transStage.setIdTransaccion(jpatr);
            transStage.setEstasincronizado("N");
            transList.add(transStage);

        }

        return transList;
    }

    // public void createOrEdit(Transaccionfase transaccionFase) throws
    // IllegalOrphanException, NonexistentEntityException, Exception{
    // if(transaccionFase.getId()==null){
    // this.jpaController.create(transaccionFase);
    // }else{
    // this.jpaController.edit(transaccionFase);
    // }
    // }
}
