/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.util.Date;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Money;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Moneda;
import crjpa400.Monedatasacambio;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class MonedaJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Moneda";

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;
	/**
	 * Field jpaController.
	 */
	private crjpa400.MonedaJpaController jpaController;

	/**
	 * Constructor for MonedaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public MonedaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
		this.jpaController = new crjpa400.MonedaJpaController(emf);
	}

	/**
	 * Method toJpa.
	 * 
	 * @param money
	 *            Money
	 * @param symbol
	 *            String
	 * @return Moneda
	 */
	public Moneda toJpa(Money money, String symbol) {

		Moneda result = new Moneda();
		result.setNombre(money.getName());
		result.setSimbolo(symbol);
		return result;
	}

	/**
	 * Method fromJpa.
	 * 
	 * @param money
	 *            Moneda
	 * @return Money
	 */
	protected Money fromJpa(Moneda money) {
		MonedatasacambioJpaController exchangeController = new MonedatasacambioJpaController(this.emf);
		Monedatasacambio exchangeTmp = exchangeController.findLastByDate(new Date(), money.getId());
		Money tmp = new Money();
		tmp.setName(money.getNombre());
		tmp.setCurrencyId(money.getId());
		tmp.setExchangeRate(new CRBigDecimal(exchangeTmp.getCambio().doubleValue(), 3));
		return tmp;
	}

	/**
	 * Method findSymbolById.
	 * 
	 * @param defaultMoneyId
	 *            Long
	 * @return String
	 */
	public String findSymbolById(Long defaultMoneyId) {

		Moneda moneda = jpaController.findMoneda(defaultMoneyId);

		return moneda.getSimbolo();
	}

	/**
	 * Method findDefaultMoney.
	 * 
	 * @param id
	 *            long
	 * @return Money
	 */
	public Money findDefaultMoney(long id) {
		Money value = fromJpa(jpaController.findMoneda(id));
		return value;
	}

}
