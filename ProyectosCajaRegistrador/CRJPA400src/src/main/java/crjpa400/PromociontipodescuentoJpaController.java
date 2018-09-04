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
public class PromociontipodescuentoJpaController implements Serializable {

	/**
	 * Constructor for PromociontipodescuentoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public PromociontipodescuentoJpaController(EntityManagerFactory emf) {
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
	 * @param promociontipodescuento
	 *            Promociontipodescuento
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Promociontipodescuento promociontipodescuento)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipodescuento idTipodescuento = promociontipodescuento
					.getIdTipodescuento();
			if (idTipodescuento != null) {
				idTipodescuento = em.getReference(idTipodescuento.getClass(),
						idTipodescuento.getId());
				promociontipodescuento.setIdTipodescuento(idTipodescuento);
			}
			Promocion idPromocion = promociontipodescuento.getIdPromocion();
			if (idPromocion != null) {
				idPromocion = em.getReference(idPromocion.getClass(),
						idPromocion.getId());
				promociontipodescuento.setIdPromocion(idPromocion);
			}
			em.persist(promociontipodescuento);
			if (idTipodescuento != null) {
				idTipodescuento.getPromociontipodescuentoList().add(
						promociontipodescuento);
				idTipodescuento = em.merge(idTipodescuento);
			}
			if (idPromocion != null) {
				idPromocion.getPromociontipodescuentoList().add(
						promociontipodescuento);
				idPromocion = em.merge(idPromocion);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findPromociontipodescuento(promociontipodescuento.getId()) != null) {
				throw new PreexistingEntityException("Promociontipodescuento "
						+ promociontipodescuento + " already exists.", ex);
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
	 * @param promociontipodescuento
	 *            Promociontipodescuento
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Promociontipodescuento promociontipodescuento)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Promociontipodescuento persistentPromociontipodescuento = em.find(
					Promociontipodescuento.class,
					promociontipodescuento.getId());
			Tipodescuento idTipodescuentoOld = persistentPromociontipodescuento
					.getIdTipodescuento();
			Tipodescuento idTipodescuentoNew = promociontipodescuento
					.getIdTipodescuento();
			Promocion idPromocionOld = persistentPromociontipodescuento
					.getIdPromocion();
			Promocion idPromocionNew = promociontipodescuento.getIdPromocion();
			if (idTipodescuentoNew != null) {
				idTipodescuentoNew = em.getReference(
						idTipodescuentoNew.getClass(),
						idTipodescuentoNew.getId());
				promociontipodescuento.setIdTipodescuento(idTipodescuentoNew);
			}
			if (idPromocionNew != null) {
				idPromocionNew = em.getReference(idPromocionNew.getClass(),
						idPromocionNew.getId());
				promociontipodescuento.setIdPromocion(idPromocionNew);
			}
			promociontipodescuento = em.merge(promociontipodescuento);
			if (idTipodescuentoOld != null
					&& !idTipodescuentoOld.equals(idTipodescuentoNew)) {
				idTipodescuentoOld.getPromociontipodescuentoList().remove(
						promociontipodescuento);
				idTipodescuentoOld = em.merge(idTipodescuentoOld);
			}
			if (idTipodescuentoNew != null
					&& !idTipodescuentoNew.equals(idTipodescuentoOld)) {
				idTipodescuentoNew.getPromociontipodescuentoList().add(
						promociontipodescuento);
				idTipodescuentoNew = em.merge(idTipodescuentoNew);
			}
			if (idPromocionOld != null
					&& !idPromocionOld.equals(idPromocionNew)) {
				idPromocionOld.getPromociontipodescuentoList().remove(
						promociontipodescuento);
				idPromocionOld = em.merge(idPromocionOld);
			}
			if (idPromocionNew != null
					&& !idPromocionNew.equals(idPromocionOld)) {
				idPromocionNew.getPromociontipodescuentoList().add(
						promociontipodescuento);
				idPromocionNew = em.merge(idPromocionNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = promociontipodescuento.getId();
				if (findPromociontipodescuento(id) == null) {
					throw new NonexistentEntityException(
							"The promociontipodescuento with id " + id
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
			Promociontipodescuento promociontipodescuento;
			try {
				promociontipodescuento = em.getReference(
						Promociontipodescuento.class, id);
				promociontipodescuento.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The promociontipodescuento with id " + id
								+ " no longer exists.", enfe);
			}
			Tipodescuento idTipodescuento = promociontipodescuento
					.getIdTipodescuento();
			if (idTipodescuento != null) {
				idTipodescuento.getPromociontipodescuentoList().remove(
						promociontipodescuento);
				idTipodescuento = em.merge(idTipodescuento);
			}
			Promocion idPromocion = promociontipodescuento.getIdPromocion();
			if (idPromocion != null) {
				idPromocion.getPromociontipodescuentoList().remove(
						promociontipodescuento);
				idPromocion = em.merge(idPromocion);
			}
			em.remove(promociontipodescuento);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findPromociontipodescuentoEntities.
	 * 
	 * @return List<Promociontipodescuento>
	 */
	public List<Promociontipodescuento> findPromociontipodescuentoEntities() {
		return findPromociontipodescuentoEntities(true, -1, -1);
	}

	/**
	 * Method findPromociontipodescuentoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Promociontipodescuento>
	 */
	public List<Promociontipodescuento> findPromociontipodescuentoEntities(
			int maxResults, int firstResult) {
		return findPromociontipodescuentoEntities(false, maxResults,
				firstResult);
	}

	/**
	 * Method findPromociontipodescuentoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Promociontipodescuento>
	 */
	private List<Promociontipodescuento> findPromociontipodescuentoEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Promociontipodescuento.class));
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
	 * Method findPromociontipodescuento.
	 * 
	 * @param id
	 *            Long
	 * @return Promociontipodescuento
	 */
	public Promociontipodescuento findPromociontipodescuento(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Promociontipodescuento.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getPromociontipodescuentoCount.
	 * 
	 * @return int
	 */
	public int getPromociontipodescuentoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Promociontipodescuento> rt = cq
					.from(Promociontipodescuento.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
