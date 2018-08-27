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
public class MonedatasacambioJpaController implements Serializable {

	/**
	 * Constructor for MonedatasacambioJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public MonedatasacambioJpaController(EntityManagerFactory emf) {
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
	 * @param monedatasacambio
	 *            Monedatasacambio
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Monedatasacambio monedatasacambio) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Moneda idMoneda = monedatasacambio.getIdMoneda();
			if (idMoneda != null) {
				idMoneda = em.getReference(idMoneda.getClass(), idMoneda.getId());
				monedatasacambio.setIdMoneda(idMoneda);
			}
			em.persist(monedatasacambio);
			if (idMoneda != null) {
				idMoneda.getMonedatasacambioList().add(monedatasacambio);
				idMoneda = em.merge(idMoneda);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findMonedatasacambio(monedatasacambio.getId()) != null) {
				throw new PreexistingEntityException("Monedatasacambio " + monedatasacambio + " already exists.", ex);
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
	 * @param monedatasacambio
	 *            Monedatasacambio
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Monedatasacambio monedatasacambio) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Monedatasacambio persistentMonedatasacambio = em.find(Monedatasacambio.class, monedatasacambio.getId());
			Moneda idMonedaOld = persistentMonedatasacambio.getIdMoneda();
			Moneda idMonedaNew = monedatasacambio.getIdMoneda();
			if (idMonedaNew != null) {
				idMonedaNew = em.getReference(idMonedaNew.getClass(), idMonedaNew.getId());
				monedatasacambio.setIdMoneda(idMonedaNew);
			}
			monedatasacambio = em.merge(monedatasacambio);
			if (idMonedaOld != null && !idMonedaOld.equals(idMonedaNew)) {
				idMonedaOld.getMonedatasacambioList().remove(monedatasacambio);
				idMonedaOld = em.merge(idMonedaOld);
			}
			if (idMonedaNew != null && !idMonedaNew.equals(idMonedaOld)) {
				idMonedaNew.getMonedatasacambioList().add(monedatasacambio);
				idMonedaNew = em.merge(idMonedaNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = monedatasacambio.getId();
				if (findMonedatasacambio(id) == null) {
					throw new NonexistentEntityException("The monedatasacambio with id " + id + " no longer exists.");
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
			Monedatasacambio monedatasacambio;
			try {
				monedatasacambio = em.getReference(Monedatasacambio.class, id);
				monedatasacambio.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The monedatasacambio with id " + id + " no longer exists.", enfe);
			}
			Moneda idMoneda = monedatasacambio.getIdMoneda();
			if (idMoneda != null) {
				idMoneda.getMonedatasacambioList().remove(monedatasacambio);
				idMoneda = em.merge(idMoneda);
			}
			em.remove(monedatasacambio);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findMonedatasacambioEntities.
	 * 
	 * @return List<Monedatasacambio>
	 */
	public List<Monedatasacambio> findMonedatasacambioEntities() {
		return findMonedatasacambioEntities(true, -1, -1);
	}

	/**
	 * Method findMonedatasacambioEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Monedatasacambio>
	 */
	public List<Monedatasacambio> findMonedatasacambioEntities(int maxResults, int firstResult) {
		return findMonedatasacambioEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findMonedatasacambioEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Monedatasacambio>
	 */
	private List<Monedatasacambio> findMonedatasacambioEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Monedatasacambio.class));
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
	 * Method findMonedatasacambio.
	 * 
	 * @param id
	 *            Long
	 * @return Monedatasacambio
	 */
	public Monedatasacambio findMonedatasacambio(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Monedatasacambio.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getMonedatasacambioCount.
	 * 
	 * @return int
	 */
	public int getMonedatasacambioCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Monedatasacambio> rt = cq.from(Monedatasacambio.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
