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
import com.becoblohm.cr.models.SaleUnit;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Articulounidadventa;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ArticulounidadventaJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Articulounidadventa";

	/**
	 * Constructor for ArticulounidadventaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ArticulounidadventaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method fromJPA.
	 * 
	 * @param jpaSaleUnit
	 *            crjpa400.Articulounidadventa
	 * @return SaleUnit
	 */
	public SaleUnit fromJPA(crjpa400.Articulounidadventa jpaSaleUnit) {
		CRBigDecimal defaultQuantity = CRBigDecimal.ONE;
		SaleUnit saleUnit = new SaleUnit();
		saleUnit.setAllowFraction(ActiveValues.valueOf(String.valueOf(jpaSaleUnit.getIdUnidadventa().getEsfraccion()))
				.getValue());
		saleUnit.setDefaultQuantity(defaultQuantity);
		saleUnit.setId(jpaSaleUnit.getIdUnidadventa().getId());
		saleUnit.setPrice(new CRBigDecimal(jpaSaleUnit.getPrecio().doubleValue()));
		return saleUnit;
	}

	/**
	 * Method toJpa.
	 * 
	 * @param saleUnit
	 *            SaleUnit
	 * @return Articulounidadventa
	 */
	public Articulounidadventa toJpa(SaleUnit saleUnit) {

		Articulounidadventa result = new Articulounidadventa();

		result.setId(saleUnit.getId());
		result.setFecha(saleUnit.getDate());
		result.setPrecio(saleUnit.getPrice().getValue());

		return result;
	}

}
