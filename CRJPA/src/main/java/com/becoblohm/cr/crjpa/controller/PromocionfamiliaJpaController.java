/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.List;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.PromotionsMethod;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Promocionfamilia;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class PromocionfamiliaJpaController extends AbstractJPAController {

    /**
     * 
     */
    private crjpa.PromocionfamiliaJpaController jpaController;

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field emf.
     */
    EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Promocioncategoria";

    /**
     * @param p_emf
     */
    public PromocionfamiliaJpaController(EntityManagerFactory p_emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.PromocionfamiliaJpaController(p_emf);
        this.emf = p_emf;
    }

    /**
     * Method findPromocionesFamilia.
     * 
     * @param idFam long
     * @param idPromoc long
     * @return List<Promocionfamilia>
     */
    public List<Promocionfamilia> findPromocionesFamilia(long idFam, long idPromoc) {
        List<Promocionfamilia> result = null;
        EntityManager em = this.jpaController.getEntityManager();
        Query query = em
                .createQuery("SELECT c FROM PromocionFamilia c WHERE c.idFamilia = :idFam and c.idPromocion  = idPromoc");
        query.setParameter("idFamilia", idFam);
        query.setParameter("idPromocion", idPromoc);
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

    /**
     * Method fromJPA.
     * 
     * @param promocionFamilia List<Promocionfamilia>
     * @return TreeMap<CRBigDecimal,PromotionsMethod>
     */
    public TreeMap<CRBigDecimal, PromotionsMethod> fromJPA(List<Promocionfamilia> promocionFamilia) {

        TreeMap<CRBigDecimal, PromotionsMethod> arrayPomotion = new TreeMap<CRBigDecimal, PromotionsMethod>();
        for (Promocionfamilia pa : promocionFamilia) {
            if (ActiveValues.get(pa.getIdPromocion().getEstaactivo()).getValue()) {
                PromotionsMethod pmTmp = this.fromJPA(pa);
                if (pmTmp != null) {
                    arrayPomotion.put(pmTmp.getDiscountAmount(), pmTmp);
                }
            }
        }

        return arrayPomotion;

    }

    /**
     * Method fromJPA.
     * 
     * @param PromocionFamilia Promocionfamilia
     * @return PromotionsMethod
     */
    public PromotionsMethod fromJPA(Promocionfamilia PromocionFamilia) {

        PromotionsMethod pm = new PromotionsMethod();
        PromocionformadepagoJpaController promPaymentMethodJPA = new PromocionformadepagoJpaController(emf);
        PromociontipodescuentoJpaController promRebateTypeJPA = new PromociontipodescuentoJpaController(emf);

        pm.setActive(ActiveValues.get(PromocionFamilia.getEstaactivo()).getValue());
        pm.setCod(PromocionFamilia.getIdPromocion().getCodigo());
        pm.setDiscountAmount(new CRBigDecimal(PromocionFamilia.getPorcentajeDescuento().doubleValue()));
        pm.setDiscountRate(new CRBigDecimal(PromocionFamilia.getPorcentajeDescuento().doubleValue()));
        pm.setEndDate(PromocionFamilia.getIdPromocion().getFechaFin());
        pm.setId(PromocionFamilia.getId());
        pm.setInitDate(PromocionFamilia.getIdPromocion().getFechaInicio());
        pm.setMaximumValue(new CRBigDecimal(PromocionFamilia.getIdPromocion().getValorMaximo()));
        pm.setMessage(PromocionFamilia.getIdPromocion().getMensaje());
        pm.setMinimumValue(new CRBigDecimal(PromocionFamilia.getIdPromocion().getValorMinimo()));
        pm.setName(PromocionFamilia.getIdPromocion().getNombre());
        pm.setNumberOfTransactions(new CRBigDecimal(PromocionFamilia.getIdPromocion().getCantidadTransacciones()));
        pm.setIsAcumulative(PromocionFamilia.getIdPromocion().getEsmultiple());
        pm.setPaymentMethod(promPaymentMethodJPA
                .fromJPA(PromocionFamilia.getIdPromocion().getPromocionformadepagoList()));

        pm.setDiscountType(promRebateTypeJPA
                .fromJPA(PromocionFamilia.getIdPromocion().getPromociontipodescuentoList()));

        if (pm.getActive() == true) {
            return pm;
        } else
            return null;

    }

}
