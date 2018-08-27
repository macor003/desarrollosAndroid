/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class SincronizacionJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;
	/**
	 * Field jpaController.
	 */
	private crjpa400.SincronizacionJpaController jpaController;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Sincronizacion";

	/**
	 * Constructor for SincronizacionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public SincronizacionJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		jpaController = new crjpa400.SincronizacionJpaController(emf);
		this.emf = emf;
	}

	/**
	 * Method findGroups.
	 * 
	 * @return HashMap<BigInteger,Integer>
	 */
	public HashMap<BigInteger, Integer> findGroups() {
		HashMap<BigInteger, Integer> groups = new HashMap<BigInteger, Integer>();
		List<crjpa400.Sincronizacion> tmp = jpaController.findSincronizacionEntities();
		for (crjpa400.Sincronizacion sync : tmp) {
			groups.put(BigInteger.valueOf(sync.getIdGrupo()), (int) sync.getEspera());
		}
		return groups;
	}
}
