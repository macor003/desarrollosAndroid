/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.DiscountType;

import crjpa400.Promociontipodescuento;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class PromociontipodescuentoJpaController extends AbstractJPAController {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Promociontipodescuento";

	/**
	
	 */
	EntityManagerFactory emf;

	/**
	 * Constructor for PromociontipodescuentoJpaController.
	 * 
	 * @param p_emf
	 *            EntityManagerFactory
	 */
	public PromociontipodescuentoJpaController(EntityManagerFactory p_emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param obj
	 *            List<Promociontipodescuento>
	 * @return DiscountType
	 */
	public DiscountType fromJPA(List<Promociontipodescuento> obj) {
		DiscountType pmList = new DiscountType();
		for (Promociontipodescuento ptd : obj) {
			pmList.setName(ptd.getIdTipodescuento().getNombre());
			pmList.setDescription(ptd.getIdTipodescuento().getDescripcion());
			pmList.setId(ptd.getIdTipodescuento().getId());
		}
		return pmList;
	}
}
