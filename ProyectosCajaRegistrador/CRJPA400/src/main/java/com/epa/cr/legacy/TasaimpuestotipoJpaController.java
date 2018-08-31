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
import com.becoblohm.cr.models.Tax;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TasaimpuestotipoJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Tasaimpuestotipo";

	/**
	 * Constructor for TasaimpuestotipoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TasaimpuestotipoJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method fromJPA.
	 * 
	 * @param jpaTax
	 *            crjpa400.Tasaimpuestotipo
	 * @return Tax
	 */
	public Tax fromJPA(crjpa400.Tasaimpuestotipo jpaTax) {
		Tax tax = new Tax();
		tax.setActive(ActiveValues.valueOf(String.valueOf(jpaTax.getEstavigente())).getValue());
		tax.setName(jpaTax.getNombre());
		tax.setTaxDate(jpaTax.getFecha());
		tax.setCode(jpaTax.getCodigo());
		crjpa400.Tasaimpuestovalor jpaTaxValue = jpaTax.getTasaimpuestovalorList().get(
				jpaTax.getTasaimpuestovalorList().size() - 1);
		tax.setTaxRate(new CRBigDecimal(jpaTaxValue.getTasa().doubleValue()));
		tax.setId(jpaTaxValue.getId());
		return tax;
	}
}
