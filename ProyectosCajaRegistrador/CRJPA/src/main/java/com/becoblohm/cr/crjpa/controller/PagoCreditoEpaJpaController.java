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

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CreditsEpaCardPays;
import com.becoblohm.cr.models.Transaction.Source;

import crjpa.Cuentacredito;
import crjpa.Pagocredito;
import crjpa.PagocreditoJpaController;
import crjpa.Transaccion;
import crjpa.exceptions.NonexistentEntityException;

/***
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class PagoCreditoEpaJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.PagocreditoJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Pagocredito";

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * 
     */
    private static final long serialVersionUID = -6883400234878251778L;

    /**
     * @param p_emf
     */
    public PagoCreditoEpaJpaController(EntityManagerFactory p_emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = p_emf;
        jpacontroller = new PagocreditoJpaController(p_emf);
    }

    /**
     * Method toJpa.
     * 
     * @param pays CreditsEpaCardPays
     * @return Pagocredito
     */
    public Pagocredito toJpa(CreditsEpaCardPays pays) {

        Pagocredito result = new Pagocredito();

        result.setId(pays.getId());
        result.setIdCuentacredito(new Cuentacredito(pays.getIdCreditsEpaCard()));
        result.setIdTransaccion(new Transaccion(pays.getIdTransaction()));
        result.setEstasincronizado("N");

        return result;
    }

    /**
     * Method create.
     * 
     * @param creditsEpaCardPays CreditsEpaCardPays
     * @throws JpaException
     */
    public void create(CreditsEpaCardPays creditsEpaCardPays) throws JpaException {

        try {
            Pagocredito pagoCredito = toJpa(creditsEpaCardPays);
            jpacontroller.create(pagoCredito);
            creditsEpaCardPays.setId(pagoCredito.getId());

        } catch (NonexistentEntityException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
            throw new JpaException();
        }

    }

    /**
     * Method fromJpa.
     * 
     * @param pago Pagocredito
     * @return CreditsEpaCardPays
     */
    public CreditsEpaCardPays fromJpa(Pagocredito pago) {

        CreditsEpaCardPays result = new CreditsEpaCardPays();

        result.setId(pago.getId());
        result.setIdCreditsEpaCard(pago.getIdCuentacredito().getId());
        result.setIdTransaction(pago.getIdTransaccion().getId());

        return result;
    }

    /**
     * Method findLastPay.
     * 
     * @return CreditsEpaCardPays
     */
    public CreditsEpaCardPays findLastPay() {

        CreditsEpaCardPays result = null;

        try {

            EntityManager em = jpacontroller.getEntityManager();
            Query query = em
                    .createQuery("select t from Pagocredito t where t.id = (select max(te.id) from Pagocredito te)");
            query.setMaxResults(1);
            Pagocredito tmp = (Pagocredito) query.getSingleResult();
            result = fromJpa(tmp);

        } catch (Exception ex) {
            ex.printStackTrace();
            result = null;
        }

        return result;
    }

    /**
     * Method findLastPay.
     * 
     * @param source Source
     * @return CreditsEpaCardPays
     */
    public CreditsEpaCardPays findLastPay(Source source) {

        CreditsEpaCardPays result = null;

        try {

            EntityManager em = jpacontroller.getEntityManager();
            Query query = em
                    .createQuery("select t from Pagocredito t where t.id = (select max(te.id) from Pagocredito te where te.idCuentacredito.tipo = :tipo)");
            query.setMaxResults(1);
            query.setParameter("tipo", source.getValue());
            Pagocredito tmp = (Pagocredito) query.getSingleResult();
            result = fromJpa(tmp);

        } catch (Exception ex) {
            ex.printStackTrace();
            result = null;
        }

        return result;
    }

}
