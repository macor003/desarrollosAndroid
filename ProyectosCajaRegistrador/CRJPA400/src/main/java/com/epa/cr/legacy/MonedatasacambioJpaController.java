/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa400.Monedatasacambio;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class MonedatasacambioJpaController extends AbstractJPAController {
	/**
	 * Field jpaController.
	 */
	private crjpa400.MonedadenominacionJpaController jpaController;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Monedatasacambio";

	/**
	 * Constructor for MonedatasacambioJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public MonedatasacambioJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.jpaController = new crjpa400.MonedadenominacionJpaController(emf);
	}

	/**
	 * Method findLastByDate.
	 * 
	 * @param date
	 *            Date
	 * @param id
	 *            Long
	 * @return Monedatasacambio
	 */
	protected Monedatasacambio findLastByDate(Date date, Long id) {
		Monedatasacambio exchange = new Monedatasacambio();
		try {
			EntityManager em = this.jpaController.getEntityManager();
			Query query = em
					.createQuery("select m from Monedatasacambio m where m.fecha <=:fecha and m.idMoneda.id=:id order by m.fecha desc");
			query.setParameter("fecha", date);
			query.setParameter("id", id);
			query.setMaxResults(1);
			exchange = (Monedatasacambio) query.getSingleResult();
		} catch (javax.persistence.NoResultException ex) {
			exchange.setCambio(new BigDecimal(1));
		}
		return exchange;
	}
}
