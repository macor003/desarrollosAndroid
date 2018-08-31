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
public class DetalletipoformadepagolineaJpaController implements Serializable {

	/**
	 * Constructor for DetalletipoformadepagolineaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public DetalletipoformadepagolineaJpaController(EntityManagerFactory emf) {
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
	 * @param detalletipoformadepagolinea
	 *            Detalletipoformadepagolinea
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Detalletipoformadepagolinea detalletipoformadepagolinea) throws PreexistingEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Detalletipoformadepago idDetalletipoformadepago = detalletipoformadepagolinea.getIdDetalletipoformadepago();
			if (idDetalletipoformadepago != null) {
				idDetalletipoformadepago = em.getReference(idDetalletipoformadepago.getClass(),
						idDetalletipoformadepago.getId());
				detalletipoformadepagolinea.setIdDetalletipoformadepago(idDetalletipoformadepago);
			}
			em.persist(detalletipoformadepagolinea);
			if (idDetalletipoformadepago != null) {
				idDetalletipoformadepago.getDetalletipoformadepagolineaList().add(detalletipoformadepagolinea);
				idDetalletipoformadepago = em.merge(idDetalletipoformadepago);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findDetalletipoformadepagolinea(detalletipoformadepagolinea.getId()) != null) {
				throw new PreexistingEntityException("Detalletipoformadepagolinea " + detalletipoformadepagolinea
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
	 * @param detalletipoformadepagolinea
	 *            Detalletipoformadepagolinea
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Detalletipoformadepagolinea detalletipoformadepagolinea) throws NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Detalletipoformadepagolinea persistentDetalletipoformadepagolinea = em.find(
					Detalletipoformadepagolinea.class, detalletipoformadepagolinea.getId());
			Detalletipoformadepago idDetalletipoformadepagoOld = persistentDetalletipoformadepagolinea
					.getIdDetalletipoformadepago();
			Detalletipoformadepago idDetalletipoformadepagoNew = detalletipoformadepagolinea
					.getIdDetalletipoformadepago();
			if (idDetalletipoformadepagoNew != null) {
				idDetalletipoformadepagoNew = em.getReference(idDetalletipoformadepagoNew.getClass(),
						idDetalletipoformadepagoNew.getId());
				detalletipoformadepagolinea.setIdDetalletipoformadepago(idDetalletipoformadepagoNew);
			}
			detalletipoformadepagolinea = em.merge(detalletipoformadepagolinea);
			if (idDetalletipoformadepagoOld != null && !idDetalletipoformadepagoOld.equals(idDetalletipoformadepagoNew)) {
				idDetalletipoformadepagoOld.getDetalletipoformadepagolineaList().remove(detalletipoformadepagolinea);
				idDetalletipoformadepagoOld = em.merge(idDetalletipoformadepagoOld);
			}
			if (idDetalletipoformadepagoNew != null && !idDetalletipoformadepagoNew.equals(idDetalletipoformadepagoOld)) {
				idDetalletipoformadepagoNew.getDetalletipoformadepagolineaList().add(detalletipoformadepagolinea);
				idDetalletipoformadepagoNew = em.merge(idDetalletipoformadepagoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = detalletipoformadepagolinea.getId();
				if (findDetalletipoformadepagolinea(id) == null) {
					throw new NonexistentEntityException("The detalletipoformadepagolinea with id " + id
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
			Detalletipoformadepagolinea detalletipoformadepagolinea;
			try {
				detalletipoformadepagolinea = em.getReference(Detalletipoformadepagolinea.class, id);
				detalletipoformadepagolinea.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The detalletipoformadepagolinea with id " + id
						+ " no longer exists.", enfe);
			}
			Detalletipoformadepago idDetalletipoformadepago = detalletipoformadepagolinea.getIdDetalletipoformadepago();
			if (idDetalletipoformadepago != null) {
				idDetalletipoformadepago.getDetalletipoformadepagolineaList().remove(detalletipoformadepagolinea);
				idDetalletipoformadepago = em.merge(idDetalletipoformadepago);
			}
			em.remove(detalletipoformadepagolinea);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findDetalletipoformadepagolineaEntities.
	 * 
	 * @return List<Detalletipoformadepagolinea>
	 */
	public List<Detalletipoformadepagolinea> findDetalletipoformadepagolineaEntities() {
		return findDetalletipoformadepagolineaEntities(true, -1, -1);
	}

	/**
	 * Method findDetalletipoformadepagolineaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Detalletipoformadepagolinea>
	 */
	public List<Detalletipoformadepagolinea> findDetalletipoformadepagolineaEntities(int maxResults, int firstResult) {
		return findDetalletipoformadepagolineaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findDetalletipoformadepagolineaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Detalletipoformadepagolinea>
	 */
	private List<Detalletipoformadepagolinea> findDetalletipoformadepagolineaEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Detalletipoformadepagolinea.class));
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
	 * Method findDetalletipoformadepagolinea.
	 * 
	 * @param id
	 *            Long
	 * @return Detalletipoformadepagolinea
	 */
	public Detalletipoformadepagolinea findDetalletipoformadepagolinea(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Detalletipoformadepagolinea.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getDetalletipoformadepagolineaCount.
	 * 
	 * @return int
	 */
	public int getDetalletipoformadepagolineaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Detalletipoformadepagolinea> rt = cq.from(Detalletipoformadepagolinea.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
