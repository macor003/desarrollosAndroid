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
public class PromocionlineaJpaController implements Serializable {

	/**
	 * Constructor for PromocionlineaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public PromocionlineaJpaController(EntityManagerFactory emf) {
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
	 * @param promocionlinea
	 *            Promocionlinea
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Promocionlinea promocionlinea)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Promocion idPromocion = promocionlinea.getIdPromocion();
			if (idPromocion != null) {
				idPromocion = em.getReference(idPromocion.getClass(),
						idPromocion.getId());
				promocionlinea.setIdPromocion(idPromocion);
			}
			Linea idLinea = promocionlinea.getIdLinea();
			if (idLinea != null) {
				idLinea = em.getReference(idLinea.getClass(), idLinea.getId());
				promocionlinea.setIdLinea(idLinea);
			}
			em.persist(promocionlinea);
			if (idPromocion != null) {
				idPromocion.getPromocionlineaList().add(promocionlinea);
				idPromocion = em.merge(idPromocion);
			}
			if (idLinea != null) {
				idLinea.getPromocionlineaList().add(promocionlinea);
				idLinea = em.merge(idLinea);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findPromocionlinea(promocionlinea.getId()) != null) {
				throw new PreexistingEntityException("Promocionlinea "
						+ promocionlinea + " already exists.", ex);
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
	 * @param promocionlinea
	 *            Promocionlinea
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Promocionlinea promocionlinea)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Promocionlinea persistentPromocionlinea = em.find(
					Promocionlinea.class, promocionlinea.getId());
			Promocion idPromocionOld = persistentPromocionlinea
					.getIdPromocion();
			Promocion idPromocionNew = promocionlinea.getIdPromocion();
			Linea idLineaOld = persistentPromocionlinea.getIdLinea();
			Linea idLineaNew = promocionlinea.getIdLinea();
			if (idPromocionNew != null) {
				idPromocionNew = em.getReference(idPromocionNew.getClass(),
						idPromocionNew.getId());
				promocionlinea.setIdPromocion(idPromocionNew);
			}
			if (idLineaNew != null) {
				idLineaNew = em.getReference(idLineaNew.getClass(),
						idLineaNew.getId());
				promocionlinea.setIdLinea(idLineaNew);
			}
			promocionlinea = em.merge(promocionlinea);
			if (idPromocionOld != null
					&& !idPromocionOld.equals(idPromocionNew)) {
				idPromocionOld.getPromocionlineaList().remove(promocionlinea);
				idPromocionOld = em.merge(idPromocionOld);
			}
			if (idPromocionNew != null
					&& !idPromocionNew.equals(idPromocionOld)) {
				idPromocionNew.getPromocionlineaList().add(promocionlinea);
				idPromocionNew = em.merge(idPromocionNew);
			}
			if (idLineaOld != null && !idLineaOld.equals(idLineaNew)) {
				idLineaOld.getPromocionlineaList().remove(promocionlinea);
				idLineaOld = em.merge(idLineaOld);
			}
			if (idLineaNew != null && !idLineaNew.equals(idLineaOld)) {
				idLineaNew.getPromocionlineaList().add(promocionlinea);
				idLineaNew = em.merge(idLineaNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = promocionlinea.getId();
				if (findPromocionlinea(id) == null) {
					throw new NonexistentEntityException(
							"The promocionlinea with id " + id
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
			Promocionlinea promocionlinea;
			try {
				promocionlinea = em.getReference(Promocionlinea.class, id);
				promocionlinea.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The promocionlinea with id " + id
								+ " no longer exists.", enfe);
			}
			Promocion idPromocion = promocionlinea.getIdPromocion();
			if (idPromocion != null) {
				idPromocion.getPromocionlineaList().remove(promocionlinea);
				idPromocion = em.merge(idPromocion);
			}
			Linea idLinea = promocionlinea.getIdLinea();
			if (idLinea != null) {
				idLinea.getPromocionlineaList().remove(promocionlinea);
				idLinea = em.merge(idLinea);
			}
			em.remove(promocionlinea);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findPromocionlineaEntities.
	 * 
	 * @return List<Promocionlinea>
	 */
	public List<Promocionlinea> findPromocionlineaEntities() {
		return findPromocionlineaEntities(true, -1, -1);
	}

	/**
	 * Method findPromocionlineaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Promocionlinea>
	 */
	public List<Promocionlinea> findPromocionlineaEntities(int maxResults,
			int firstResult) {
		return findPromocionlineaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findPromocionlineaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Promocionlinea>
	 */
	private List<Promocionlinea> findPromocionlineaEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Promocionlinea.class));
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
	 * Method findPromocionlinea.
	 * 
	 * @param id
	 *            Long
	 * @return Promocionlinea
	 */
	public Promocionlinea findPromocionlinea(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Promocionlinea.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getPromocionlineaCount.
	 * 
	 * @return int
	 */
	public int getPromocionlineaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Promocionlinea> rt = cq.from(Promocionlinea.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
