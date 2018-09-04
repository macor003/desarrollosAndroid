/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import java.util.TreeMap;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa400.Depuracion;

/**
 */
public class DepuracionJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;
	/**
	 * Field cleanController.
	 */
	private crjpa400.DepuracionJpaController cleanController;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Depuracion";

	/**
	 * Constructor for DepuracionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public DepuracionJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.POSSOURCE, entityName);
		this.emf = emf;
		cleanController = new crjpa400.DepuracionJpaController(this.emf);
	}

	/**
	 * Method getCleanQueries.
	 * 
	 * @return TreeMap<Long,String>
	 */
	public TreeMap<Long, String> getCleanQueries() {
		TreeMap<Long, String> queries = new TreeMap<Long, String>();
		for (Depuracion tmp : cleanController.findDepuracionEntities()) {
			queries.put(tmp.getId(), tmp.getQuery().toString());
		}
		return queries;
	}
}
