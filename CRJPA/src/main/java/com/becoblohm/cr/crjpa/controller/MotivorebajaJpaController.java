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

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.RebateMotive;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Motivorebaja;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class MotivorebajaJpaController extends AbstractJPAController {

    /**
     * Field jpaController.
     */
    private crjpa.MotivorebajaJpaController jpaController;

    /**
     * Field emf.
     */
    EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Motivorebaja";

    /**
     * Constructor for MotivorebajaJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public MotivorebajaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        this.jpaController = new crjpa.MotivorebajaJpaController(emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /*
     * public List<RebateMotive> findMotivoEntities() { return
     * this.fromJPA(this.jpaController.findMotivorebajaEntities()); }
     */

    /**
     * Method findMotivoEntities.
     * 
     * @return List<RebateMotive>
     */
    public List<RebateMotive> findMotivoEntities() {
        EntityManager em = jpaController.getEntityManager();
        try {
            Query query = em.createQuery("SELECT m FROM Motivorebaja m WHERE m.estaactivo = 'S'");
            List<Motivorebaja> rebateList = query.getResultList();
            return this.fromJPA(rebateList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method findMotivobyIdDiscount.
     * 
     * @param idDiscountType long
     * @return List<RebateMotive>
     */
    public List<RebateMotive> findMotivobyIdDiscount(long idDiscountType) {
        try {
            EntityManager em = jpaController.getEntityManager();
            Query query = em
                    .createQuery("SELECT m FROM Motivorebaja m WHERE m.estaactivo = 'S' and m.idTipodescuento.id=:idDiscountType");
            query.setParameter("idDiscountType", idDiscountType);
            List<Motivorebaja> rebateList = query.getResultList();
            return this.fromJPA(rebateList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method findMotivobyId.
     * 
     * @param id long
     * @return RebateMotive
     */
    public RebateMotive findMotivobyId(long id) {
        return this.fromJPA(this.jpaController.findMotivorebaja(id));
    }

    /**
     * Method fromJPA.
     * 
     * @param motivoRebaja Motivorebaja
     * @return RebateMotive
     */
    public RebateMotive fromJPA(Motivorebaja motivoRebaja) {

        TipodescuentoJpaController tipoDescuento = new TipodescuentoJpaController(this.emf);
        RebateMotive rebateMotive = new RebateMotive();

        RebateMotive mRebaja = new RebateMotive();
        mRebaja.setId(motivoRebaja.getId());
        mRebaja.setActive(ActiveValues.get(motivoRebaja.getEstaactivo()).getValue());
        mRebaja.setDiscountType(tipoDescuento.findTipodescuento(motivoRebaja.getIdTipodescuento().getId()));
        mRebaja.setDescription(motivoRebaja.getDescripcion());

        return rebateMotive;

    }

    /**
     * Method fromJPA.
     * 
     * @param motivoRebaja List<Motivorebaja>
     * @return List<RebateMotive>
     */
    public List<RebateMotive> fromJPA(List<Motivorebaja> motivoRebaja) {

        TipodescuentoJpaController tipoDescuento = new TipodescuentoJpaController(this.emf);
        List<RebateMotive> rebateMotive = new ArrayList<RebateMotive>();
        for (Motivorebaja motivoR : motivoRebaja) {
            RebateMotive mRebaja = new RebateMotive();
            mRebaja.setId(motivoR.getId());
            mRebaja.setActive(ActiveValues.get(motivoR.getEstaactivo()).getValue());
            mRebaja.setDiscountType(tipoDescuento.findTipodescuento(motivoR.getIdTipodescuento().getId()));
            mRebaja.setDescription(motivoR.getDescripcion());
            mRebaja.setMaxPercent(new CRBigDecimal(motivoR.getPorcentajeMaximo().doubleValue()));
            rebateMotive.add(mRebaja);
        }

        return rebateMotive;

    }

}
