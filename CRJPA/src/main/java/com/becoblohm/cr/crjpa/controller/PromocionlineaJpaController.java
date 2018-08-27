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

import crjpa.Promocionlinea;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class PromocionlineaJpaController extends AbstractJPAController {

    /**
     * 
     */
    private crjpa.PromocionlineaJpaController jpaController;

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field entityName.
     */
    private static String entityName = "Promocionlinea";

    /**
     * Field emf.
     */
    EntityManagerFactory emf = null;

    /**
     * @param p_emf
     */
    public PromocionlineaJpaController(EntityManagerFactory p_emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.PromocionlineaJpaController(p_emf);
        this.emf = p_emf;
    }

    /**
     * Method findPromocionesLineas.
     * 
     * @param idLin long
     * @param idPromoc long
     * @return List<Promocionlinea>
     */
    public List<Promocionlinea> findPromocionesLineas(long idLin, long idPromoc) {
        List<Promocionlinea> result = null;
        EntityManager em = this.jpaController.getEntityManager();
        Query query = em
                .createQuery("SELECT c FROM PromocionLinea c WHERE c.idLinea = :idLin and c.idPromocion  = idPromoc");
        query.setParameter("idCategoria", idLin);
        query.setParameter("idPromocion", idPromoc);
        try {
            result = query.getResultList();
            return result;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * Method fromJPA.
     * 
     * @param promocionLinea List<Promocionlinea>
     * @return TreeMap<CRBigDecimal,PromotionsMethod>
     */
    public TreeMap<CRBigDecimal, PromotionsMethod> fromJPA(List<Promocionlinea> promocionLinea) {

        TreeMap<CRBigDecimal, PromotionsMethod> arrayPomotion = new TreeMap<CRBigDecimal, PromotionsMethod>();
        for (Promocionlinea pa : promocionLinea) {
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
     * @param promocionLinea Promocionlinea
     * @return PromotionsMethod
     */
    public PromotionsMethod fromJPA(Promocionlinea promocionLinea) {

        PromotionsMethod pm = new PromotionsMethod();
        PromocionformadepagoJpaController promPaymentMethodJPA = new PromocionformadepagoJpaController(emf);
        PromociontipodescuentoJpaController promRebateTypeJPA = new PromociontipodescuentoJpaController(emf);

        pm.setActive(ActiveValues.get(promocionLinea.getEstaactivo()).getValue());
        pm.setCod(promocionLinea.getIdPromocion().getCodigo());
        pm.setDiscountAmount(new CRBigDecimal(promocionLinea.getPorcentajeDescuento().doubleValue()));
        pm.setDiscountRate(new CRBigDecimal(promocionLinea.getPorcentajeDescuento().doubleValue()));
        pm.setEndDate(promocionLinea.getIdPromocion().getFechaFin());
        pm.setId(promocionLinea.getId());
        pm.setInitDate(promocionLinea.getIdPromocion().getFechaInicio());
        pm.setMaximumValue(new CRBigDecimal(promocionLinea.getIdPromocion().getValorMaximo()));
        pm.setMessage(promocionLinea.getIdPromocion().getMensaje());
        pm.setMinimumValue(new CRBigDecimal(promocionLinea.getIdPromocion().getValorMinimo()));
        pm.setName(promocionLinea.getIdPromocion().getNombre());
        pm.setNumberOfTransactions(new CRBigDecimal(promocionLinea.getIdPromocion().getCantidadTransacciones()));
        pm.setIsAcumulative(promocionLinea.getIdPromocion().getEsmultiple());
        pm.setPaymentMethod(promPaymentMethodJPA
                .fromJPA(promocionLinea.getIdPromocion().getPromocionformadepagoList()));

        pm.setDiscountType(promRebateTypeJPA
                .fromJPA(promocionLinea.getIdPromocion().getPromociontipodescuentoList()));

        if (pm.getActive() == true) {
            return pm;
        } else {
            return null;
        }

    }

}
