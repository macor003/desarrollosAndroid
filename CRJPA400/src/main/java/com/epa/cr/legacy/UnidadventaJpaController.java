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

import crjpa400.Unidadventa;

/**
 */
public class UnidadventaJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Unidadventa";

	/**
	 * Constructor for UnidadventaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public UnidadventaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method fromJpa.
	 * 
	 * @param unidadDeVenta
	 *            Unidadventa
	 * @return SaleUnit
	 */
	public static SaleUnit fromJpa(Unidadventa unidadDeVenta) {
		SaleUnit result = new SaleUnit();

		result.setId(unidadDeVenta.getId());
		result.setAllowFraction(ActiveValues.get(String.valueOf(unidadDeVenta.getEsfraccion())).getValue());

		return result;
	}

	/**
	 * Method toJpa.
	 * 
	 * @param saleUnit
	 *            SaleUnit
	 * @return Unidadventa
	 */
	public Unidadventa toJpa(SaleUnit saleUnit) {
		Unidadventa unidadVenta = new Unidadventa();
		// TODO hace falta modificar el codigo duro 'S' y 'N'
		if (saleUnit.isAllowFraction()) {
			unidadVenta.setEsfraccion('S');
		} else {
			unidadVenta.setEsfraccion('N');
		}
		// TODO idem
		if (saleUnit.isActive()) {
			unidadVenta.setEstaactivo('S');
		} else {
			unidadVenta.setEstaactivo('N');
		}
		unidadVenta.setFecha(saleUnit.getDate());
		unidadVenta.setId(saleUnit.getId());
		unidadVenta.setNombre(saleUnit.getName());
		unidadVenta.setSimbolo(saleUnit.getSymbol());

		return unidadVenta;
	}
}
