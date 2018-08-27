/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa.Cuentacredito;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class CuentacreditoJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.CuentacreditoJpaController jpacontroller;

    /**
     * Field emf.
     */
    EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Cuentacredito";

    /**
     * Field cuentaCredito.
     */
    Cuentacredito cuentaCredito;

    /**
     * Constructor for CuentacreditoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public CuentacreditoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpacontroller = new crjpa.CuentacreditoJpaController(emf);
        this.emf = emf;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method findLastPayCreditEPA.
     * 
     * @param clientID String
     * @return boolean
     * @throws JpaException
     */
    public boolean findLastPayCreditEPA(String clientID) throws JpaException {
        EntityManager em = jpacontroller.getEntityManager();

        Query query = em
                .createQuery("SELECT c FROM Cuentacredito c WHERE c.numeroIdentificacionCliente.numeroIdentificacionCliente = :numeroIdentificacionCliente ORDER BY c.fechaUltimopago DESC");
        query.setParameter("numeroIdentificacionCliente", clientID);
        query.setMaxResults(1);

        Calendar today = Calendar.getInstance();
        Calendar lastPay = Calendar.getInstance();

        today.setTime(new Date());

        boolean result = false;
        try {
            cuentaCredito = (Cuentacredito) query.getSingleResult();
            if (cuentaCredito != null && cuentaCredito.getFechaUltimopago() != null) {
                lastPay.setTime(cuentaCredito.getFechaUltimopago());

                long milis1 = lastPay.getTimeInMillis();
                long milis2 = today.getTimeInMillis();
                long diff = milis2 - milis1;

                long diffDays = diff / (24 * 60 * 60 * 1000);

                System.out.println("Dias desde el ultimo abono: " + diffDays + " dias.");
                if (diffDays > 90) {
                    result = true;
                }
            }
        } catch (Exception e) {
            throw new JpaException(e);
        } finally {
            em.close();
        }
        return result;
    }

}
