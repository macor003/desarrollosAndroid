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
public class CorrelativoJpaController implements Serializable {

	/**
	 * Constructor for CorrelativoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public CorrelativoJpaController(EntityManagerFactory emf) {
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
	 * @param correlativo
	 *            Correlativo
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Correlativo correlativo) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(correlativo);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findCorrelativo(correlativo.getId()) != null) {
				throw new PreexistingEntityException("Correlativo " + correlativo + " already exists.", ex);
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
	 * @param correlativo
	 *            Correlativo
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Correlativo correlativo) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			correlativo = em.merge(correlativo);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = correlativo.getId();
				if (findCorrelativo(id) == null) {
					throw new NonexistentEntityException("The correlativo with id " + id + " no longer exists.");
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
			Correlativo correlativo;
			try {
				correlativo = em.getReference(Correlativo.class, id);
				correlativo.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The correlativo with id " + id + " no longer exists.", enfe);
			}
			em.remove(correlativo);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findCorrelativoEntities.
	 * 
	 * @return List<Correlativo>
	 */
	public List<Correlativo> findCorrelativoEntities() {
		return findCorrelativoEntities(true, -1, -1);
	}

	/**
	 * Method findCorrelativoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Correlativo>
	 */
	public List<Correlativo> findCorrelativoEntities(int maxResults, int firstResult) {
		return findCorrelativoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findCorrelativoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Correlativo>
	 */
	private List<Correlativo> findCorrelativoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Correlativo.class));
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
	 * Method findCorrelativo.
	 * 
	 * @param id
	 *            Long
	 * @return Correlativo
	 */
	public Correlativo findCorrelativo(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Correlativo.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getCorrelativoCount.
	 * 
	 * @return int
	 */
	public int getCorrelativoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Correlativo> rt = cq.from(Correlativo.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
