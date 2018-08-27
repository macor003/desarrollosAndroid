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
public class PromocionfamiliaJpaController implements Serializable {

	/**
	 * Constructor for PromocionfamiliaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public PromocionfamiliaJpaController(EntityManagerFactory emf) {
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
	 * @param promocionfamilia
	 *            Promocionfamilia
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Promocionfamilia promocionfamilia) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Familia idFamilia = promocionfamilia.getIdFamilia();
			if (idFamilia != null) {
				idFamilia = em.getReference(idFamilia.getClass(), idFamilia.getId());
				promocionfamilia.setIdFamilia(idFamilia);
			}
			Promocion idPromocion = promocionfamilia.getIdPromocion();
			if (idPromocion != null) {
				idPromocion = em.getReference(idPromocion.getClass(), idPromocion.getId());
				promocionfamilia.setIdPromocion(idPromocion);
			}
			em.persist(promocionfamilia);
			if (idFamilia != null) {
				idFamilia.getPromocionfamiliaList().add(promocionfamilia);
				idFamilia = em.merge(idFamilia);
			}
			if (idPromocion != null) {
				idPromocion.getPromocionfamiliaList().add(promocionfamilia);
				idPromocion = em.merge(idPromocion);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findPromocionfamilia(promocionfamilia.getId()) != null) {
				throw new PreexistingEntityException("Promocionfamilia " + promocionfamilia + " already exists.", ex);
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
	 * @param promocionfamilia
	 *            Promocionfamilia
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Promocionfamilia promocionfamilia) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Promocionfamilia persistentPromocionfamilia = em.find(Promocionfamilia.class, promocionfamilia.getId());
			Familia idFamiliaOld = persistentPromocionfamilia.getIdFamilia();
			Familia idFamiliaNew = promocionfamilia.getIdFamilia();
			Promocion idPromocionOld = persistentPromocionfamilia.getIdPromocion();
			Promocion idPromocionNew = promocionfamilia.getIdPromocion();
			if (idFamiliaNew != null) {
				idFamiliaNew = em.getReference(idFamiliaNew.getClass(), idFamiliaNew.getId());
				promocionfamilia.setIdFamilia(idFamiliaNew);
			}
			if (idPromocionNew != null) {
				idPromocionNew = em.getReference(idPromocionNew.getClass(), idPromocionNew.getId());
				promocionfamilia.setIdPromocion(idPromocionNew);
			}
			promocionfamilia = em.merge(promocionfamilia);
			if (idFamiliaOld != null && !idFamiliaOld.equals(idFamiliaNew)) {
				idFamiliaOld.getPromocionfamiliaList().remove(promocionfamilia);
				idFamiliaOld = em.merge(idFamiliaOld);
			}
			if (idFamiliaNew != null && !idFamiliaNew.equals(idFamiliaOld)) {
				idFamiliaNew.getPromocionfamiliaList().add(promocionfamilia);
				idFamiliaNew = em.merge(idFamiliaNew);
			}
			if (idPromocionOld != null && !idPromocionOld.equals(idPromocionNew)) {
				idPromocionOld.getPromocionfamiliaList().remove(promocionfamilia);
				idPromocionOld = em.merge(idPromocionOld);
			}
			if (idPromocionNew != null && !idPromocionNew.equals(idPromocionOld)) {
				idPromocionNew.getPromocionfamiliaList().add(promocionfamilia);
				idPromocionNew = em.merge(idPromocionNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = promocionfamilia.getId();
				if (findPromocionfamilia(id) == null) {
					throw new NonexistentEntityException("The promocionfamilia with id " + id + " no longer exists.");
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
			Promocionfamilia promocionfamilia;
			try {
				promocionfamilia = em.getReference(Promocionfamilia.class, id);
				promocionfamilia.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The promocionfamilia with id " + id + " no longer exists.", enfe);
			}
			Familia idFamilia = promocionfamilia.getIdFamilia();
			if (idFamilia != null) {
				idFamilia.getPromocionfamiliaList().remove(promocionfamilia);
				idFamilia = em.merge(idFamilia);
			}
			Promocion idPromocion = promocionfamilia.getIdPromocion();
			if (idPromocion != null) {
				idPromocion.getPromocionfamiliaList().remove(promocionfamilia);
				idPromocion = em.merge(idPromocion);
			}
			em.remove(promocionfamilia);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findPromocionfamiliaEntities.
	 * 
	 * @return List<Promocionfamilia>
	 */
	public List<Promocionfamilia> findPromocionfamiliaEntities() {
		return findPromocionfamiliaEntities(true, -1, -1);
	}

	/**
	 * Method findPromocionfamiliaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Promocionfamilia>
	 */
	public List<Promocionfamilia> findPromocionfamiliaEntities(int maxResults, int firstResult) {
		return findPromocionfamiliaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findPromocionfamiliaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Promocionfamilia>
	 */
	private List<Promocionfamilia> findPromocionfamiliaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Promocionfamilia.class));
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
	 * Method findPromocionfamilia.
	 * 
	 * @param id
	 *            Long
	 * @return Promocionfamilia
	 */
	public Promocionfamilia findPromocionfamilia(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Promocionfamilia.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getPromocionfamiliaCount.
	 * 
	 * @return int
	 */
	public int getPromocionfamiliaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Promocionfamilia> rt = cq.from(Promocionfamilia.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
