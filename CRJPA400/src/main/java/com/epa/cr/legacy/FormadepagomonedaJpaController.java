/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa400.Formadepagomoneda;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class FormadepagomonedaJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Formadepagomoneda";

	/**
	 * Constructor for FormadepagomonedaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public FormadepagomonedaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Metodos propios
	 */

	/**
	 * Method findFormadepagoMonedaEntities.
	 * 
	 * @param idFDP
	 *            Long
	 * @return List<Formadepagomoneda>
	 */
	public List<Formadepagomoneda> findFormadepagoMonedaEntities(Long idFDP) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em
					.createQuery("SELECT fdpm FROM FormadepagoMoneda fdpm WHERE fdpm.idFormadepago.id = :idFDP");
			query.setParameter("idFDP", idFDP);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	/**
	 * 
	 * 
	 * @param status
	 *            String
	 * @return List<Formadepagomoneda>
	 */
	public List<Formadepagomoneda> findFormadepagoMonedaEntitiesByStatus(String status) {
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery("FormadepagoMoneda.findByEstaactiva");
			query.setParameter("estaactiva", status);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
}
