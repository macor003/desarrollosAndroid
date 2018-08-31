/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa400.exceptions.NonexistentEntityException;
import crjpa400.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class MorosidadJpaController implements Serializable {

	/**
	 * Constructor for MorosidadJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public MorosidadJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;

	/**
	 * Method getEntityManager.
	 * 
	 * @return EntityManager
	 */
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	/**
	 * Method create.
	 * 
	 * @param morosidad
	 *            Morosidad
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Morosidad morosidad) throws PreexistingEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Cliente numeroIdentificacionCliente = morosidad
					.getNumeroIdentificacionCliente();
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente = em.getReference(
						numeroIdentificacionCliente.getClass(),
						numeroIdentificacionCliente
								.getNumeroIdentificacionCliente());
				morosidad
						.setNumeroIdentificacionCliente(numeroIdentificacionCliente);
			}
			Formadepago idFormadepago = morosidad.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago = em.getReference(idFormadepago.getClass(),
						idFormadepago.getId());
				morosidad.setIdFormadepago(idFormadepago);
			}
			em.persist(morosidad);
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente.getMorosidadList().add(morosidad);
				numeroIdentificacionCliente = em
						.merge(numeroIdentificacionCliente);
			}
			if (idFormadepago != null) {
				idFormadepago.getMorosidadList().add(morosidad);
				idFormadepago = em.merge(idFormadepago);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findMorosidad(morosidad.getId()) != null) {
				throw new PreexistingEntityException("Morosidad " + morosidad
						+ " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method edit.
	 * 
	 * @param morosidad
	 *            Morosidad
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Morosidad morosidad) throws NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Morosidad persistentMorosidad = em.find(Morosidad.class,
					morosidad.getId());
			Cliente numeroIdentificacionClienteOld = persistentMorosidad
					.getNumeroIdentificacionCliente();
			Cliente numeroIdentificacionClienteNew = morosidad
					.getNumeroIdentificacionCliente();
			Formadepago idFormadepagoOld = persistentMorosidad
					.getIdFormadepago();
			Formadepago idFormadepagoNew = morosidad.getIdFormadepago();
			if (numeroIdentificacionClienteNew != null) {
				numeroIdentificacionClienteNew = em.getReference(
						numeroIdentificacionClienteNew.getClass(),
						numeroIdentificacionClienteNew
								.getNumeroIdentificacionCliente());
				morosidad
						.setNumeroIdentificacionCliente(numeroIdentificacionClienteNew);
			}
			if (idFormadepagoNew != null) {
				idFormadepagoNew = em.getReference(idFormadepagoNew.getClass(),
						idFormadepagoNew.getId());
				morosidad.setIdFormadepago(idFormadepagoNew);
			}
			morosidad = em.merge(morosidad);
			if (numeroIdentificacionClienteOld != null
					&& !numeroIdentificacionClienteOld
							.equals(numeroIdentificacionClienteNew)) {
				numeroIdentificacionClienteOld.getMorosidadList().remove(
						morosidad);
				numeroIdentificacionClienteOld = em
						.merge(numeroIdentificacionClienteOld);
			}
			if (numeroIdentificacionClienteNew != null
					&& !numeroIdentificacionClienteNew
							.equals(numeroIdentificacionClienteOld)) {
				numeroIdentificacionClienteNew.getMorosidadList()
						.add(morosidad);
				numeroIdentificacionClienteNew = em
						.merge(numeroIdentificacionClienteNew);
			}
			if (idFormadepagoOld != null
					&& !idFormadepagoOld.equals(idFormadepagoNew)) {
				idFormadepagoOld.getMorosidadList().remove(morosidad);
				idFormadepagoOld = em.merge(idFormadepagoOld);
			}
			if (idFormadepagoNew != null
					&& !idFormadepagoNew.equals(idFormadepagoOld)) {
				idFormadepagoNew.getMorosidadList().add(morosidad);
				idFormadepagoNew = em.merge(idFormadepagoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = morosidad.getId();
				if (findMorosidad(id) == null) {
					throw new NonexistentEntityException(
							"The morosidad with id " + id
									+ " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method destroy.
	 * 
	 * @param id
	 *            Long
	 * @throws NonexistentEntityException
	 */
	public void destroy(Long id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Morosidad morosidad;
			try {
				morosidad = em.getReference(Morosidad.class, id);
				morosidad.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The morosidad with id "
						+ id + " no longer exists.", enfe);
			}
			Cliente numeroIdentificacionCliente = morosidad
					.getNumeroIdentificacionCliente();
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente.getMorosidadList()
						.remove(morosidad);
				numeroIdentificacionCliente = em
						.merge(numeroIdentificacionCliente);
			}
			Formadepago idFormadepago = morosidad.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago.getMorosidadList().remove(morosidad);
				idFormadepago = em.merge(idFormadepago);
			}
			em.remove(morosidad);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findMorosidadEntities.
	 * 
	 * @return List<Morosidad>
	 */
	public List<Morosidad> findMorosidadEntities() {
		return findMorosidadEntities(true, -1, -1);
	}

	/**
	 * Method findMorosidadEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Morosidad>
	 */
	public List<Morosidad> findMorosidadEntities(int maxResults, int firstResult) {
		return findMorosidadEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findMorosidadEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Morosidad>
	 */
	private List<Morosidad> findMorosidadEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Morosidad.class));
			Query q = em.createQuery(cq);
			if (!all) {
				q.setMaxResults(maxResults);
				q.setFirstResult(firstResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	/**
	 * Method findMorosidad.
	 * 
	 * @param id
	 *            Long
	 * @return Morosidad
	 */
	public Morosidad findMorosidad(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Morosidad.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getMorosidadCount.
	 * 
	 * @return int
	 */
	public int getMorosidadCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Morosidad> rt = cq.from(Morosidad.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
