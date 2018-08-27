/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CreditsEpaCard;
import com.becoblohm.cr.models.CreditsEpaCardPays;
import com.becoblohm.cr.models.Transaction.Source;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Cliente;
import crjpa.Cuentacredito;
import crjpa.CuentacreditoJpaController;
import crjpa.Pagocredito;
import crjpa.exceptions.NonexistentEntityException;

/***
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class MaestroTarjetasJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.CuentacreditoJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Cuentacredito";

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
    public MaestroTarjetasJpaController(EntityManagerFactory p_emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = p_emf;
        jpacontroller = new CuentacreditoJpaController(p_emf);
    }

    /**
     * Method findMaestroTarjetasByIdTarjetahabienteType.
     * 
     * @param idNumber String
     * @param source Source
     * @return List<CreditsEpaCard>
     */
    public List<CreditsEpaCard> findMaestroTarjetasByIdTarjetahabienteType(String idNumber, Source source) {

        ArrayList<CreditsEpaCard> creditsCards = new ArrayList<CreditsEpaCard>();
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em
                .createQuery("SELECT m FROM Cuentacredito m WHERE m.numeroIdentificacionCliente.numeroIdentificacionCliente = :numeroIdentificacionCliente and m.tipo = :tipo");
        query.setParameter("numeroIdentificacionCliente", idNumber);
        query.setParameter("tipo", source.getValue());
        try {
            List<Cuentacredito> tmp = (List<Cuentacredito>) query.getResultList();
            for (Cuentacredito aux : tmp) {
                // if(aux.getFechaExpiracion().after(new Date())){
                creditsCards.add(fromJpa(aux));
                // }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return creditsCards;
    }

    /**
     * Method fromJpa.
     * 
     * @param tarjeta Cuentacredito
     * @return CreditsEpaCard
     */
    public CreditsEpaCard fromJpa(Cuentacredito tarjeta) {
        double deudaPendiente = 0d;
        if (tarjeta.getDeudaPendiente() != null) {
            deudaPendiente = tarjeta.getDeudaPendiente().doubleValue();
        }

        CreditsEpaCard result = new CreditsEpaCard(tarjeta.getId(),
                tarjeta.getNumeroIdentificacionCliente().getNumeroIdentificacionCliente(),
                tarjeta.getNumeroIdentificacionCliente().getNombre(), tarjeta.getTipo(),
                tarjeta.getEstadoTarjeta().charAt(0), tarjeta.getFechaCreacion(), tarjeta.getFechaActualizacion(),
                tarjeta.getFechaExpiracion(), tarjeta.getFechaMora(), tarjeta.getFechaUltimopago(),
                new CRBigDecimal(tarjeta.getLineaCredito().doubleValue()), new CRBigDecimal(deudaPendiente));

        result.setCreditsEpaCardPays(new ArrayList<CreditsEpaCardPays>());

        PagoCreditoEpaJpaController pagoCreditoEpaJpaController = new PagoCreditoEpaJpaController(this.emf);

        if (tarjeta.getPagocreditoList() != null & tarjeta.getPagocreditoList().size() > 0) {
            for (Pagocredito pago : tarjeta.getPagocreditoList()) {
                result.getCreditsEpaCardPays().add(pagoCreditoEpaJpaController.fromJpa(pago));
            }
        }

        return result;
    }

    /**
     * Method findMaestroTarjetas.
     * 
     * @param numero Long
     * @param idNumber String
     * @return CreditsEpaCard
     */
    public CreditsEpaCard findMaestroTarjetas(Long numero, String idNumber) {

        CreditsEpaCard creditsCards = new CreditsEpaCard();
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em
                .createQuery("SELECT m FROM Cuentacredito m WHERE m.numeroIdentificacionCliente.numeroIdentificacionCliente = :idTarjetahabiente and m.id = :id");
        query.setParameter("idTarjetahabiente", idNumber);
        query.setParameter("id", numero);
        query.setMaxResults(1);
        try {
            Cuentacredito tmp = (Cuentacredito) query.getSingleResult();
            creditsCards = fromJpa(tmp);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return creditsCards;
    }

    /**
     * Method findMaestroTarjetasById.
     * 
     * @param idNumber Long
     * @return CreditsEpaCard
     */
    public CreditsEpaCard findMaestroTarjetasById(Long idNumber) {

        CreditsEpaCard creditsCards = new CreditsEpaCard();
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em.createNamedQuery("Cuentacredito.findById");
        query.setParameter("id", idNumber);
        query.setMaxResults(1);
        try {
            Cuentacredito tmp = (Cuentacredito) query.getSingleResult();
            creditsCards = fromJpa(tmp);

        } catch (Exception e) {
            e.printStackTrace();
            creditsCards = null;
        } finally {
            em.close();
        }

        return creditsCards;
    }

    /**
     * Method edit.
     * 
     * @param tarjeta CreditsEpaCard
     * @throws JpaException
     */
    public void edit(CreditsEpaCard tarjeta) throws JpaException {
        try {
            jpacontroller.edit(toJPA(tarjeta));
        } catch (NonexistentEntityException e) {

            e.printStackTrace();
            throw new JpaException();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    /**
     * Method toJPA.
     * 
     * @param tarjeta CreditsEpaCard
     * @return Cuentacredito
     */
    private Cuentacredito toJPA(CreditsEpaCard tarjeta) {
        Cuentacredito result = new Cuentacredito();
        result.setDeudaPendiente(tarjeta.getOutstandingDebt().getValue());
        result.setEstadoTarjeta(String.valueOf(tarjeta.getStatus()));
        result.setFechaActualizacion(tarjeta.getUpdateDate());
        result.setFechaCreacion(tarjeta.getCreationDate());
        result.setFechaExpiracion(tarjeta.getExpirationDate());
        result.setFechaMora(tarjeta.getDelayPayDate());
        result.setFechaUltimopago(tarjeta.getLastPayDate());
        result.setId(tarjeta.getId());
        result.setLineaCredito(tarjeta.getCreditLine().getValue());
        result.setNumeroIdentificacionCliente(new Cliente(tarjeta.getClientId()));
        result.setTipo(String.valueOf(tarjeta.getClientType()));
        result.setEstasincronizado("N");
        result.setPagocreditoList(new ArrayList<Pagocredito>());

        if (tarjeta.getCreditsEpaCardPays() != null && tarjeta.getCreditsEpaCardPays().size() > 0) {
            PagoCreditoEpaJpaController pagoCreditoEpaJpaController = new PagoCreditoEpaJpaController(this.emf);
            for (CreditsEpaCardPays pays : tarjeta.getCreditsEpaCardPays()) {
                result.getPagocreditoList().add(pagoCreditoEpaJpaController.toJpa(pays));
            }
        }

        return result;
    }

    /**
     * Method create.
     * 
     * @param creditsEpaCard CreditsEpaCard
     * @return boolean
     */
    public boolean create(CreditsEpaCard creditsEpaCard) {

        try {
            jpacontroller.create(toJPA(creditsEpaCard));
            return true;
        } catch (NonexistentEntityException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
