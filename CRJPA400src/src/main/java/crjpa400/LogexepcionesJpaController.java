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
public class LogexepcionesJpaController implements Serializable {

	/**
	 * Constructor for LogexepcionesJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public LogexepcionesJpaController(EntityManagerFactory emf) {
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
	 * @param logexepciones
	 *            Logexepciones
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Logexepciones logexepciones)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(logexepciones);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findLogexepciones(logexepciones.getId()) != null) {
				throw new PreexistingEntityException("Logexepciones "
						+ logexepciones + " already exists.", ex);
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
	 * @param logexepciones
	 *            Logexepciones
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Logexepciones logexepciones)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			logexepciones = em.merge(logexepciones);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = logexepciones.getId();
				if (findLogexepciones(id) == null) {
					throw new NonexistentEntityException(
							"The logexepciones with id " + id
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
			Logexepciones logexepciones;
			try {
				logexepciones = em.getReference(Logexepciones.class, id);
				logexepciones.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The logexepciones with id " + id
								+ " no longer exists.", enfe);
			}
			em.remove(logexepciones);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findLogexepcionesEntities.
	 * 
	 * @return List<Logexepciones>
	 */
	public List<Logexepciones> findLogexepcionesEntities() {
		return findLogexepcionesEntities(true, -1, -1);
	}

	/**
	 * Method findLogexepcionesEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Logexepciones>
	 */
	public List<Logexepciones> findLogexepcionesEntities(int maxResults,
			int firstResult) {
		return findLogexepcionesEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findLogexepcionesEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Logexepciones>
	 */
	private List<Logexepciones> findLogexepcionesEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Logexepciones.class));
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
	 * Method findLogexepciones.
	 * 
	 * @param id
	 *            Long
	 * @return Logexepciones
	 */
	public Logexepciones findLogexepciones(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Logexepciones.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getLogexepcionesCount.
	 * 
	 * @return int
	 */
	public int getLogexepcionesCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Logexepciones> rt = cq.from(Logexepciones.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
