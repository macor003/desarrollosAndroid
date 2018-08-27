/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.PromotionsMethod;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class PromocionJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Promocion";

	/**
	 * Constructor for PromocionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public PromocionJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param promo
	 *            crjpa400.Promocion
	 * @return PromotionsMethod
	 */
	public PromotionsMethod fromJPA(crjpa400.Promocion promo) {

		PromotionsMethod promotionsMethod = new PromotionsMethod();
		promotionsMethod.setActive(ActiveValues.get(String.valueOf(promo.getEstaactivo())).getValue());
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
		promotionsMethod.setMutiple(ActiveValues.get(String.valueOf(promo.getEsmultiple())).getValue());
		// promotionsMethod.setDiscountAmount(new
		// CRBigDecimal(promocion.getMontoDescuento()));

		return promotionsMethod;
	}
}
