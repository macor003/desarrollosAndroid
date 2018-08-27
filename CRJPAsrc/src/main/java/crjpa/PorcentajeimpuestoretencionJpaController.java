/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa.exceptions.NonexistentEntityException;
import crjpa.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class PorcentajeimpuestoretencionJpaController implements Serializable {

	/**
	 * Constructor for PorcentajeimpuestoretencionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public PorcentajeimpuestoretencionJpaController(EntityManagerFactory emf) {
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
	 * @param porcentajeimpuestoretencion
	 *            Porcentajeimpuestoretencion
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Porcentajeimpuestoretencion porcentajeimpuestoretencion) throws PreexistingEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Formadepago idFormadepago = porcentajeimpuestoretencion.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago = em.getReference(idFormadepago.getClass(), idFormadepago.getId());
				porcentajeimpuestoretencion.setIdFormadepago(idFormadepago);
			}
			em.persist(porcentajeimpuestoretencion);
			if (idFormadepago != null) {
				idFormadepago.getPorcentajeimpuestoretencionList().add(porcentajeimpuestoretencion);
				idFormadepago = em.merge(idFormadepago);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findPorcentajeimpuestoretencion(porcentajeimpuestoretencion.getId()) != null) {
				throw new PreexistingEntityException("Porcentajeimpuestoretencion " + porcentajeimpuestoretencion
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
	 * @param porcentajeimpuestoretencion
	 *            Porcentajeimpuestoretencion
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Porcentajeimpuestoretencion porcentajeimpuestoretencion) throws NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Porcentajeimpuestoretencion persistentPorcentajeimpuestoretencion = em.find(
					Porcentajeimpuestoretencion.class, porcentajeimpuestoretencion.getId());
			Formadepago idFormadepagoOld = persistentPorcentajeimpuestoretencion.getIdFormadepago();
			Formadepago idFormadepagoNew = porcentajeimpuestoretencion.getIdFormadepago();
			if (idFormadepagoNew != null) {
				idFormadepagoNew = em.getReference(idFormadepagoNew.getClass(), idFormadepagoNew.getId());
				porcentajeimpuestoretencion.setIdFormadepago(idFormadepagoNew);
			}
			porcentajeimpuestoretencion = em.merge(porcentajeimpuestoretencion);
			if (idFormadepagoOld != null && !idFormadepagoOld.equals(idFormadepagoNew)) {
				idFormadepagoOld.getPorcentajeimpuestoretencionList().remove(porcentajeimpuestoretencion);
				idFormadepagoOld = em.merge(idFormadepagoOld);
			}
			if (idFormadepagoNew != null && !idFormadepagoNew.equals(idFormadepagoOld)) {
				idFormadepagoNew.getPorcentajeimpuestoretencionList().add(porcentajeimpuestoretencion);
				idFormadepagoNew = em.merge(idFormadepagoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = porcentajeimpuestoretencion.getId();
				if (findPorcentajeimpuestoretencion(id) == null) {
					throw new NonexistentEntityException("The porcentajeimpuestoretencion with id " + id
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
			Porcentajeimpuestoretencion porcentajeimpuestoretencion;
			try {
				porcentajeimpuestoretencion = em.getReference(Porcentajeimpuestoretencion.class, id);
				porcentajeimpuestoretencion.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The porcentajeimpuestoretencion with id " + id
						+ " no longer exists.", enfe);
			}
			Formadepago idFormadepago = porcentajeimpuestoretencion.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago.getPorcentajeimpuestoretencionList().remove(porcentajeimpuestoretencion);
				idFormadepago = em.merge(idFormadepago);
			}
			em.remove(porcentajeimpuestoretencion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findPorcentajeimpuestoretencionEntities.
	 * 
	 * @return List<Porcentajeimpuestoretencion>
	 */
	public List<Porcentajeimpuestoretencion> findPorcentajeimpuestoretencionEntities() {
		return findPorcentajeimpuestoretencionEntities(true, -1, -1);
	}

	/**
	 * Method findPorcentajeimpuestoretencionEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Porcentajeimpuestoretencion>
	 */
	public List<Porcentajeimpuestoretencion> findPorcentajeimpuestoretencionEntities(int maxResults, int firstResult) {
		return findPorcentajeimpuestoretencionEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findPorcentajeimpuestoretencionEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Porcentajeimpuestoretencion>
	 */
	private List<Porcentajeimpuestoretencion> findPorcentajeimpuestoretencionEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Porcentajeimpuestoretencion.class));
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
	 * Method findPorcentajeimpuestoretencion.
	 * 
	 * @param id
	 *            Long
	 * @return Porcentajeimpuestoretencion
	 */
	public Porcentajeimpuestoretencion findPorcentajeimpuestoretencion(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Porcentajeimpuestoretencion.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getPorcentajeimpuestoretencionCount.
	 * 
	 * @return int
	 */
	public int getPorcentajeimpuestoretencionCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Porcentajeimpuestoretencion> rt = cq.from(Porcentajeimpuestoretencion.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
