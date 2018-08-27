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
public class ProcesopropertyJpaController implements Serializable {

	/**
	 * Constructor for ProcesopropertyJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ProcesopropertyJpaController(EntityManagerFactory emf) {
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
	 * @param procesoproperty
	 *            Procesoproperty
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Procesoproperty procesoproperty)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Proceso idProceso = procesoproperty.getIdProceso();
			if (idProceso != null) {
				idProceso = em.getReference(idProceso.getClass(),
						idProceso.getId());
				procesoproperty.setIdProceso(idProceso);
			}
			em.persist(procesoproperty);
			if (idProceso != null) {
				idProceso.getProcesopropertyList().add(procesoproperty);
				idProceso = em.merge(idProceso);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findProcesoproperty(procesoproperty.getId()) != null) {
				throw new PreexistingEntityException("Procesoproperty "
						+ procesoproperty + " already exists.", ex);
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
	 * @param procesoproperty
	 *            Procesoproperty
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Procesoproperty procesoproperty)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Procesoproperty persistentProcesoproperty = em.find(
					Procesoproperty.class, procesoproperty.getId());
			Proceso idProcesoOld = persistentProcesoproperty.getIdProceso();
			Proceso idProcesoNew = procesoproperty.getIdProceso();
			if (idProcesoNew != null) {
				idProcesoNew = em.getReference(idProcesoNew.getClass(),
						idProcesoNew.getId());
				procesoproperty.setIdProceso(idProcesoNew);
			}
			procesoproperty = em.merge(procesoproperty);
			if (idProcesoOld != null && !idProcesoOld.equals(idProcesoNew)) {
				idProcesoOld.getProcesopropertyList().remove(procesoproperty);
				idProcesoOld = em.merge(idProcesoOld);
			}
			if (idProcesoNew != null && !idProcesoNew.equals(idProcesoOld)) {
				idProcesoNew.getProcesopropertyList().add(procesoproperty);
				idProcesoNew = em.merge(idProcesoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = procesoproperty.getId();
				if (findProcesoproperty(id) == null) {
					throw new NonexistentEntityException(
							"The procesoproperty with id " + id
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
			Procesoproperty procesoproperty;
			try {
				procesoproperty = em.getReference(Procesoproperty.class, id);
				procesoproperty.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The procesoproperty with id " + id
								+ " no longer exists.", enfe);
			}
			Proceso idProceso = procesoproperty.getIdProceso();
			if (idProceso != null) {
				idProceso.getProcesopropertyList().remove(procesoproperty);
				idProceso = em.merge(idProceso);
			}
			em.remove(procesoproperty);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findProcesopropertyEntities.
	 * 
	 * @return List<Procesoproperty>
	 */
	public List<Procesoproperty> findProcesopropertyEntities() {
		return findProcesopropertyEntities(true, -1, -1);
	}

	/**
	 * Method findProcesopropertyEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Procesoproperty>
	 */
	public List<Procesoproperty> findProcesopropertyEntities(int maxResults,
			int firstResult) {
		return findProcesopropertyEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findProcesopropertyEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Procesoproperty>
	 */
	private List<Procesoproperty> findProcesopropertyEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Procesoproperty.class));
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
	 * Method findProcesoproperty.
	 * 
	 * @param id
	 *            Long
	 * @return Procesoproperty
	 */
	public Procesoproperty findProcesoproperty(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Procesoproperty.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getProcesopropertyCount.
	 * 
	 * @return int
	 */
	public int getProcesopropertyCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Procesoproperty> rt = cq.from(Procesoproperty.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
