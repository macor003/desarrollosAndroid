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
public class ComprobantefiscalnoutilizadoJpaController implements Serializable {

	/**
	 * Constructor for ComprobantefiscalnoutilizadoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ComprobantefiscalnoutilizadoJpaController(EntityManagerFactory emf) {
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
	 * @param comprobantefiscalnoutilizado
	 *            Comprobantefiscalnoutilizado
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Comprobantefiscalnoutilizado comprobantefiscalnoutilizado) throws PreexistingEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Comprobantefiscalpreimpreso idComprobantefiscalpreimpreso = comprobantefiscalnoutilizado
					.getIdComprobantefiscalpreimpreso();
			if (idComprobantefiscalpreimpreso != null) {
				idComprobantefiscalpreimpreso = em.getReference(idComprobantefiscalpreimpreso.getClass(),
						idComprobantefiscalpreimpreso.getId());
				comprobantefiscalnoutilizado.setIdComprobantefiscalpreimpreso(idComprobantefiscalpreimpreso);
			}
			em.persist(comprobantefiscalnoutilizado);
			if (idComprobantefiscalpreimpreso != null) {
				idComprobantefiscalpreimpreso.getComprobantefiscalnoutilizadoList().add(comprobantefiscalnoutilizado);
				idComprobantefiscalpreimpreso = em.merge(idComprobantefiscalpreimpreso);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findComprobantefiscalnoutilizado(comprobantefiscalnoutilizado.getId()) != null) {
				throw new PreexistingEntityException("Comprobantefiscalnoutilizado " + comprobantefiscalnoutilizado
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
	 * @param comprobantefiscalnoutilizado
	 *            Comprobantefiscalnoutilizado
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Comprobantefiscalnoutilizado comprobantefiscalnoutilizado) throws NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Comprobantefiscalnoutilizado persistentComprobantefiscalnoutilizado = em.find(
					Comprobantefiscalnoutilizado.class, comprobantefiscalnoutilizado.getId());
			Comprobantefiscalpreimpreso idComprobantefiscalpreimpresoOld = persistentComprobantefiscalnoutilizado
					.getIdComprobantefiscalpreimpreso();
			Comprobantefiscalpreimpreso idComprobantefiscalpreimpresoNew = comprobantefiscalnoutilizado
					.getIdComprobantefiscalpreimpreso();
			if (idComprobantefiscalpreimpresoNew != null) {
				idComprobantefiscalpreimpresoNew = em.getReference(idComprobantefiscalpreimpresoNew.getClass(),
						idComprobantefiscalpreimpresoNew.getId());
				comprobantefiscalnoutilizado.setIdComprobantefiscalpreimpreso(idComprobantefiscalpreimpresoNew);
			}
			comprobantefiscalnoutilizado = em.merge(comprobantefiscalnoutilizado);
			if (idComprobantefiscalpreimpresoOld != null
					&& !idComprobantefiscalpreimpresoOld.equals(idComprobantefiscalpreimpresoNew)) {
				idComprobantefiscalpreimpresoOld.getComprobantefiscalnoutilizadoList().remove(
						comprobantefiscalnoutilizado);
				idComprobantefiscalpreimpresoOld = em.merge(idComprobantefiscalpreimpresoOld);
			}
			if (idComprobantefiscalpreimpresoNew != null
					&& !idComprobantefiscalpreimpresoNew.equals(idComprobantefiscalpreimpresoOld)) {
				idComprobantefiscalpreimpresoNew.getComprobantefiscalnoutilizadoList()
						.add(comprobantefiscalnoutilizado);
				idComprobantefiscalpreimpresoNew = em.merge(idComprobantefiscalpreimpresoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = comprobantefiscalnoutilizado.getId();
				if (findComprobantefiscalnoutilizado(id) == null) {
					throw new NonexistentEntityException("The comprobantefiscalnoutilizado with id " + id
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
			Comprobantefiscalnoutilizado comprobantefiscalnoutilizado;
			try {
				comprobantefiscalnoutilizado = em.getReference(Comprobantefiscalnoutilizado.class, id);
				comprobantefiscalnoutilizado.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The comprobantefiscalnoutilizado with id " + id
						+ " no longer exists.", enfe);
			}
			Comprobantefiscalpreimpreso idComprobantefiscalpreimpreso = comprobantefiscalnoutilizado
					.getIdComprobantefiscalpreimpreso();
			if (idComprobantefiscalpreimpreso != null) {
				idComprobantefiscalpreimpreso.getComprobantefiscalnoutilizadoList()
						.remove(comprobantefiscalnoutilizado);
				idComprobantefiscalpreimpreso = em.merge(idComprobantefiscalpreimpreso);
			}
			em.remove(comprobantefiscalnoutilizado);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findComprobantefiscalnoutilizadoEntities.
	 * 
	 * @return List<Comprobantefiscalnoutilizado>
	 */
	public List<Comprobantefiscalnoutilizado> findComprobantefiscalnoutilizadoEntities() {
		return findComprobantefiscalnoutilizadoEntities(true, -1, -1);
	}

	/**
	 * Method findComprobantefiscalnoutilizadoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Comprobantefiscalnoutilizado>
	 */
	public List<Comprobantefiscalnoutilizado> findComprobantefiscalnoutilizadoEntities(int maxResults, int firstResult) {
		return findComprobantefiscalnoutilizadoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findComprobantefiscalnoutilizadoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Comprobantefiscalnoutilizado>
	 */
	private List<Comprobantefiscalnoutilizado> findComprobantefiscalnoutilizadoEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Comprobantefiscalnoutilizado.class));
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
	 * Method findComprobantefiscalnoutilizado.
	 * 
	 * @param id
	 *            Long
	 * @return Comprobantefiscalnoutilizado
	 */
	public Comprobantefiscalnoutilizado findComprobantefiscalnoutilizado(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Comprobantefiscalnoutilizado.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getComprobantefiscalnoutilizadoCount.
	 * 
	 * @return int
	 */
	public int getComprobantefiscalnoutilizadoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Comprobantefiscalnoutilizado> rt = cq.from(Comprobantefiscalnoutilizado.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
