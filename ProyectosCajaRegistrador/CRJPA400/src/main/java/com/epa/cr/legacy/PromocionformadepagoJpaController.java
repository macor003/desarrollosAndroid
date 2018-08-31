/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.PaymentMethod;

import crjpa400.Promocionformadepago;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class PromocionformadepagoJpaController extends AbstractJPAController {

	/**
     * 
     */
	private static String entityName = "Promocionformadepago";
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;

	/**
	
	 */
	EntityManagerFactory emf;

	/**
	 * Constructor for PromocionformadepagoJpaController.
	 * 
	 * @param p_emf
	 *            EntityManagerFactory
	 */
	public PromocionformadepagoJpaController(EntityManagerFactory p_emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = p_emf;
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param obj
	 *            List<Promocionformadepago>
	 * @return List<PaymentMethod>
	 */
	public List<PaymentMethod> fromJPA(List<Promocionformadepago> obj) {
		List<PaymentMethod> pmList = new ArrayList<PaymentMethod>();

		/*
		 * MonedaJpaController moneyJpaController = new
		 * MonedaJpaController(emf); Moneda money = new Moneda();
		 * money=moneyJpaController.findMoneda(2l);
		 */

		for (Promocionformadepago pmf : obj) {
			pmList.add(FormadepagoJpaController.fromJPA(pmf.getIdFormadepago(), null));
		}
		return pmList;

	}

}
