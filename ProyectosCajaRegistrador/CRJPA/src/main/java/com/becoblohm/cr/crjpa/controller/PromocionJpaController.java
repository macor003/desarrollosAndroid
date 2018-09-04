/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.PromotionsMethod;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Promocion;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */

public class PromocionJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field jpacontroller.
     */
    private crjpa.PromocionJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Promocion";

    /**
     * Constructor for PromocionJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public PromocionJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpacontroller = new crjpa.PromocionJpaController(emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /*
     * public Map<Long,PromotionsMethod> findPromotion(String estatus,Date date,String
     * acumulative,Promocion idPromotion) { EntityManagerFactory em = this.emf; //
     * PromocionJpaController promocionJPA = new PromocionJpaController(em); //
     * List<Promocion> listAux =
     * promocionJPA.findPromociones(estatus,date,acumulative,idPromotion); TreeMap<Long,
     * PromotionsMethod> promotionList = new TreeMap<Long, PromotionsMethod>(); //
     * for(Promocion promo: idPromotion){ PromotionsMethod promotionMethod =
     * fromJPA(idPromotion); promotionList.put(idPromotion.getId(), promotionMethod); // }
     * return promotionList; }
     */
    /**
     * Method fromJPA.
     * 
     * @param promo crjpa.Promocion
     * @return PromotionsMethod
     */
    public PromotionsMethod fromJPA(crjpa.Promocion promo) {

        PromotionsMethod promotionsMethod = new PromotionsMethod();
        promotionsMethod.setActive(ActiveValues.get(promo.getEstaactivo()).getValue());
        promotionsMethod.setId(promo.getId());
        promotionsMethod.setInitDate(promo.getFechaInicio());
        promotionsMethod.setEndDate(promo.getFechaFin());
        promotionsMethod.setMaximumValue(new CRBigDecimal(promo.getValorMaximo()));
        promotionsMethod.setMinimumValue(new CRBigDecimal(promo.getValorMinimo()));
        promotionsMethod.setName(promo.getNombre());
        promotionsMethod.setNumberOfTransactions(new CRBigDecimal(promo.getCantidadTransacciones()));
        promotionsMethod.setCod(promo.getCodigo());
        promotionsMethod.setMessage(promo.getMensaje());
        promotionsMethod.setPaymentMethod(promo.getPromocionformadepagoList());
        promotionsMethod.setDiscountRate(new CRBigDecimal(promo.getPorcentajeDescuento().doubleValue()));
        promotionsMethod.setMutiple(ActiveValues.get(promo.getEsmultiple()).getValue());
        // promotionsMethod.setDiscountAmount(new
        // CRBigDecimal(promocion.getMontoDescuento()));

        return promotionsMethod;
    }

    /**
     * Method findPromocionEntities.
     * 
     * @return List<PromotionsMethod>
     */
    public List<PromotionsMethod> findPromocionEntities() {
        List<PromotionsMethod> result = null;

        List<crjpa.Promocion> tmp = this.jpacontroller.findPromocionEntities();
        // List<crjpa.Promocion> tmp = findPromociones();
        for (Iterator iterator = tmp.iterator(); iterator.hasNext();) {
            crjpa.Promocion promocion = (crjpa.Promocion) iterator.next();
            if (ActiveValues.get(promocion.getEstaactivo()).getValue()) {
                result.add(fromJPA(promocion));
            }
        }

        return result;
    }

    /**
     * Method findById.
     * 
     * @param id long
     * @return PromotionsMethod
     */
    public PromotionsMethod findById(long id) {
        return fromJPA(this.jpacontroller.findPromocion(id));
        // return fromJPA(this.jpacontroller.findPromociones(id));
    }

    /**
     * Method findPromociones.
     * 
     * @param estatus String
     * @param fecha Date
     * @param acumulative String
     * @param idPromotion Promocion
     * @return List<Promocion>
     */
    public List<Promocion> findPromociones(String estatus, Date fecha, String acumulative, Promocion idPromotion) {
        List<Promocion> result = null;
        EntityManager em = emf.createEntityManager();
        Query query = em
                .createQuery("SELECT c FROM Promocion c WHERE c.estaactivo = :estatus and c.fechaInicio  <= :fecha and c.fechaFin >= :fecha AND "
                        + " c.esmultiple=:acumulative and c.id=:idPromotion");
        query.setParameter("estatus", estatus);
        query.setParameter("fecha", fecha);
        query.setParameter("acumulative", acumulative);
        query.setParameter("idPromotion", idPromotion.getCodigo());
        try {
            result = query.getResultList();
            return result;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
    //
    // public List<Promocion> findPromociones() {
    // List<Promocion> result=null;
    // EntityManager em = emf.createEntityManager();
    // Query query =
    // em.createQuery("SELECT c FROM Promocion c WHERE c.estaactivo = 'S' and
    // c.fechaInicio <= :fecha and c.fechaFin >= :fecha");
    // query.setParameter("fecha", new Date());
    // try {
    // result = query.getResultList();
    // return result;
    // }finally {
    // em.close();
    // }
    // }
}
