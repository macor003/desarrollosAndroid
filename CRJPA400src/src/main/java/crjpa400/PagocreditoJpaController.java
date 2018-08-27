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
public class PagocreditoJpaController implements Serializable {

	/**
	 * Constructor for PagocreditoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public PagocreditoJpaController(EntityManagerFactory emf) {
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
	 * @param pagocredito
	 *            Pagocredito
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Pagocredito pagocredito)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Transaccion idTransaccion = pagocredito.getIdTransaccion();
			if (idTransaccion != null) {
				idTransaccion = em.getReference(idTransaccion.getClass(),
						idTransaccion.getId());
				pagocredito.setIdTransaccion(idTransaccion);
			}
			Cuentacredito idCuentacredito = pagocredito.getIdCuentacredito();
			if (idCuentacredito != null) {
				idCuentacredito = em.getReference(idCuentacredito.getClass(),
						idCuentacredito.getId());
				pagocredito.setIdCuentacredito(idCuentacredito);
			}
			em.persist(pagocredito);
			if (idTransaccion != null) {
				idTransaccion.getPagocreditoList().add(pagocredito);
				idTransaccion = em.merge(idTransaccion);
			}
			if (idCuentacredito != null) {
				idCuentacredito.getPagocreditoList().add(pagocredito);
				idCuentacredito = em.merge(idCuentacredito);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findPagocredito(pagocredito.getId()) != null) {
				throw new PreexistingEntityException("Pagocredito "
						+ pagocredito + " already exists.", ex);
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
	 * @param pagocredito
	 *            Pagocredito
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Pagocredito pagocredito)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Pagocredito persistentPagocredito = em.find(Pagocredito.class,
					pagocredito.getId());
			Transaccion idTransaccionOld = persistentPagocredito
					.getIdTransaccion();
			Transaccion idTransaccionNew = pagocredito.getIdTransaccion();
			Cuentacredito idCuentacreditoOld = persistentPagocredito
					.getIdCuentacredito();
			Cuentacredito idCuentacreditoNew = pagocredito.getIdCuentacredito();
			if (idTransaccionNew != null) {
				idTransaccionNew = em.getReference(idTransaccionNew.getClass(),
						idTransaccionNew.getId());
				pagocredito.setIdTransaccion(idTransaccionNew);
			}
			if (idCuentacreditoNew != null) {
				idCuentacreditoNew = em.getReference(
						idCuentacreditoNew.getClass(),
						idCuentacreditoNew.getId());
				pagocredito.setIdCuentacredito(idCuentacreditoNew);
			}
			pagocredito = em.merge(pagocredito);
			if (idTransaccionOld != null
					&& !idTransaccionOld.equals(idTransaccionNew)) {
				idTransaccionOld.getPagocreditoList().remove(pagocredito);
				idTransaccionOld = em.merge(idTransaccionOld);
			}
			if (idTransaccionNew != null
					&& !idTransaccionNew.equals(idTransaccionOld)) {
				idTransaccionNew.getPagocreditoList().add(pagocredito);
				idTransaccionNew = em.merge(idTransaccionNew);
			}
			if (idCuentacreditoOld != null
					&& !idCuentacreditoOld.equals(idCuentacreditoNew)) {
				idCuentacreditoOld.getPagocreditoList().remove(pagocredito);
				idCuentacreditoOld = em.merge(idCuentacreditoOld);
			}
			if (idCuentacreditoNew != null
					&& !idCuentacreditoNew.equals(idCuentacreditoOld)) {
				idCuentacreditoNew.getPagocreditoList().add(pagocredito);
				idCuentacreditoNew = em.merge(idCuentacreditoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = pagocredito.getId();
				if (findPagocredito(id) == null) {
					throw new NonexistentEntityException(
							"The pagocredito with id " + id
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
			Pagocredito pagocredito;
			try {
				pagocredito = em.getReference(Pagocredito.class, id);
				pagocredito.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The pagocredito with id "
						+ id + " no longer exists.", enfe);
			}
			Transaccion idTransaccion = pagocredito.getIdTransaccion();
			if (idTransaccion != null) {
				idTransaccion.getPagocreditoList().remove(pagocredito);
				idTransaccion = em.merge(idTransaccion);
			}
			Cuentacredito idCuentacredito = pagocredito.getIdCuentacredito();
			if (idCuentacredito != null) {
				idCuentacredito.getPagocreditoList().remove(pagocredito);
				idCuentacredito = em.merge(idCuentacredito);
			}
			em.remove(pagocredito);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findPagocreditoEntities.
	 * 
	 * @return List<Pagocredito>
	 */
	public List<Pagocredito> findPagocreditoEntities() {
		return findPagocreditoEntities(true, -1, -1);
	}

	/**
	 * Method findPagocreditoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Pagocredito>
	 */
	public List<Pagocredito> findPagocreditoEntities(int maxResults,
			int firstResult) {
		return findPagocreditoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findPagocreditoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Pagocredito>
	 */
	private List<Pagocredito> findPagocreditoEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Pagocredito.class));
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
	 * Method findPagocredito.
	 * 
	 * @param id
	 *            Long
	 * @return Pagocredito
	 */
	public Pagocredito findPagocredito(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Pagocredito.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getPagocreditoCount.
	 * 
	 * @return int
	 */
	public int getPagocreditoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Pagocredito> rt = cq.from(Pagocredito.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
