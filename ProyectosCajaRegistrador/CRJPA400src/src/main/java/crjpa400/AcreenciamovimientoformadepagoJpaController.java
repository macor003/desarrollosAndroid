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
public class AcreenciamovimientoformadepagoJpaController implements
		Serializable {

	/**
	 * Constructor for AcreenciamovimientoformadepagoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public AcreenciamovimientoformadepagoJpaController(EntityManagerFactory emf) {
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
	 * @param acreenciamovimientoformadepago
	 *            Acreenciamovimientoformadepago
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(
			Acreenciamovimientoformadepago acreenciamovimientoformadepago)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Acreenciamovimientosaldo idAcreenciamovimientosaldo = acreenciamovimientoformadepago
					.getIdAcreenciamovimientosaldo();
			if (idAcreenciamovimientosaldo != null) {
				idAcreenciamovimientosaldo = em.getReference(
						idAcreenciamovimientosaldo.getClass(),
						idAcreenciamovimientosaldo.getId());
				acreenciamovimientoformadepago
						.setIdAcreenciamovimientosaldo(idAcreenciamovimientosaldo);
			}
			Formadepago idFormadepago = acreenciamovimientoformadepago
					.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago = em.getReference(idFormadepago.getClass(),
						idFormadepago.getId());
				acreenciamovimientoformadepago.setIdFormadepago(idFormadepago);
			}
			Moneda idMoneda = acreenciamovimientoformadepago.getIdMoneda();
			if (idMoneda != null) {
				idMoneda = em.getReference(idMoneda.getClass(),
						idMoneda.getId());
				acreenciamovimientoformadepago.setIdMoneda(idMoneda);
			}
			em.persist(acreenciamovimientoformadepago);
			if (idAcreenciamovimientosaldo != null) {
				idAcreenciamovimientosaldo
						.getAcreenciamovimientoformadepagoList().add(
								acreenciamovimientoformadepago);
				idAcreenciamovimientosaldo = em
						.merge(idAcreenciamovimientosaldo);
			}
			if (idFormadepago != null) {
				idFormadepago.getAcreenciamovimientoformadepagoList().add(
						acreenciamovimientoformadepago);
				idFormadepago = em.merge(idFormadepago);
			}
			if (idMoneda != null) {
				idMoneda.getAcreenciamovimientoformadepagoList().add(
						acreenciamovimientoformadepago);
				idMoneda = em.merge(idMoneda);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findAcreenciamovimientoformadepago(acreenciamovimientoformadepago
					.getId()) != null) {
				throw new PreexistingEntityException(
						"Acreenciamovimientoformadepago "
								+ acreenciamovimientoformadepago
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
	 * @param acreenciamovimientoformadepago
	 *            Acreenciamovimientoformadepago
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(
			Acreenciamovimientoformadepago acreenciamovimientoformadepago)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Acreenciamovimientoformadepago persistentAcreenciamovimientoformadepago = em
					.find(Acreenciamovimientoformadepago.class,
							acreenciamovimientoformadepago.getId());
			Acreenciamovimientosaldo idAcreenciamovimientosaldoOld = persistentAcreenciamovimientoformadepago
					.getIdAcreenciamovimientosaldo();
			Acreenciamovimientosaldo idAcreenciamovimientosaldoNew = acreenciamovimientoformadepago
					.getIdAcreenciamovimientosaldo();
			Formadepago idFormadepagoOld = persistentAcreenciamovimientoformadepago
					.getIdFormadepago();
			Formadepago idFormadepagoNew = acreenciamovimientoformadepago
					.getIdFormadepago();
			Moneda idMonedaOld = persistentAcreenciamovimientoformadepago
					.getIdMoneda();
			Moneda idMonedaNew = acreenciamovimientoformadepago.getIdMoneda();
			if (idAcreenciamovimientosaldoNew != null) {
				idAcreenciamovimientosaldoNew = em.getReference(
						idAcreenciamovimientosaldoNew.getClass(),
						idAcreenciamovimientosaldoNew.getId());
				acreenciamovimientoformadepago
						.setIdAcreenciamovimientosaldo(idAcreenciamovimientosaldoNew);
			}
			if (idFormadepagoNew != null) {
				idFormadepagoNew = em.getReference(idFormadepagoNew.getClass(),
						idFormadepagoNew.getId());
				acreenciamovimientoformadepago
						.setIdFormadepago(idFormadepagoNew);
			}
			if (idMonedaNew != null) {
				idMonedaNew = em.getReference(idMonedaNew.getClass(),
						idMonedaNew.getId());
				acreenciamovimientoformadepago.setIdMoneda(idMonedaNew);
			}
			acreenciamovimientoformadepago = em
					.merge(acreenciamovimientoformadepago);
			if (idAcreenciamovimientosaldoOld != null
					&& !idAcreenciamovimientosaldoOld
							.equals(idAcreenciamovimientosaldoNew)) {
				idAcreenciamovimientosaldoOld
						.getAcreenciamovimientoformadepagoList().remove(
								acreenciamovimientoformadepago);
				idAcreenciamovimientosaldoOld = em
						.merge(idAcreenciamovimientosaldoOld);
			}
			if (idAcreenciamovimientosaldoNew != null
					&& !idAcreenciamovimientosaldoNew
							.equals(idAcreenciamovimientosaldoOld)) {
				idAcreenciamovimientosaldoNew
						.getAcreenciamovimientoformadepagoList().add(
								acreenciamovimientoformadepago);
				idAcreenciamovimientosaldoNew = em
						.merge(idAcreenciamovimientosaldoNew);
			}
			if (idFormadepagoOld != null
					&& !idFormadepagoOld.equals(idFormadepagoNew)) {
				idFormadepagoOld.getAcreenciamovimientoformadepagoList()
						.remove(acreenciamovimientoformadepago);
				idFormadepagoOld = em.merge(idFormadepagoOld);
			}
			if (idFormadepagoNew != null
					&& !idFormadepagoNew.equals(idFormadepagoOld)) {
				idFormadepagoNew.getAcreenciamovimientoformadepagoList().add(
						acreenciamovimientoformadepago);
				idFormadepagoNew = em.merge(idFormadepagoNew);
			}
			if (idMonedaOld != null && !idMonedaOld.equals(idMonedaNew)) {
				idMonedaOld.getAcreenciamovimientoformadepagoList().remove(
						acreenciamovimientoformadepago);
				idMonedaOld = em.merge(idMonedaOld);
			}
			if (idMonedaNew != null && !idMonedaNew.equals(idMonedaOld)) {
				idMonedaNew.getAcreenciamovimientoformadepagoList().add(
						acreenciamovimientoformadepago);
				idMonedaNew = em.merge(idMonedaNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = acreenciamovimientoformadepago.getId();
				if (findAcreenciamovimientoformadepago(id) == null) {
					throw new NonexistentEntityException(
							"The acreenciamovimientoformadepago with id " + id
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
			Acreenciamovimientoformadepago acreenciamovimientoformadepago;
			try {
				acreenciamovimientoformadepago = em.getReference(
						Acreenciamovimientoformadepago.class, id);
				acreenciamovimientoformadepago.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The acreenciamovimientoformadepago with id " + id
								+ " no longer exists.", enfe);
			}
			Acreenciamovimientosaldo idAcreenciamovimientosaldo = acreenciamovimientoformadepago
					.getIdAcreenciamovimientosaldo();
			if (idAcreenciamovimientosaldo != null) {
				idAcreenciamovimientosaldo
						.getAcreenciamovimientoformadepagoList().remove(
								acreenciamovimientoformadepago);
				idAcreenciamovimientosaldo = em
						.merge(idAcreenciamovimientosaldo);
			}
			Formadepago idFormadepago = acreenciamovimientoformadepago
					.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago.getAcreenciamovimientoformadepagoList().remove(
						acreenciamovimientoformadepago);
				idFormadepago = em.merge(idFormadepago);
			}
			Moneda idMoneda = acreenciamovimientoformadepago.getIdMoneda();
			if (idMoneda != null) {
				idMoneda.getAcreenciamovimientoformadepagoList().remove(
						acreenciamovimientoformadepago);
				idMoneda = em.merge(idMoneda);
			}
			em.remove(acreenciamovimientoformadepago);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findAcreenciamovimientoformadepagoEntities.
	 * 
	 * @return List<Acreenciamovimientoformadepago>
	 */
	public List<Acreenciamovimientoformadepago> findAcreenciamovimientoformadepagoEntities() {
		return findAcreenciamovimientoformadepagoEntities(true, -1, -1);
	}

	/**
	 * Method findAcreenciamovimientoformadepagoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Acreenciamovimientoformadepago>
	 */
	public List<Acreenciamovimientoformadepago> findAcreenciamovimientoformadepagoEntities(
			int maxResults, int firstResult) {
		return findAcreenciamovimientoformadepagoEntities(false, maxResults,
				firstResult);
	}

	/**
	 * Method findAcreenciamovimientoformadepagoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Acreenciamovimientoformadepago>
	 */
	private List<Acreenciamovimientoformadepago> findAcreenciamovimientoformadepagoEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Acreenciamovimientoformadepago.class));
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
	 * Method findAcreenciamovimientoformadepago.
	 * 
	 * @param id
	 *            Long
	 * @return Acreenciamovimientoformadepago
	 */
	public Acreenciamovimientoformadepago findAcreenciamovimientoformadepago(
			Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Acreenciamovimientoformadepago.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getAcreenciamovimientoformadepagoCount.
	 * 
	 * @return int
	 */
	public int getAcreenciamovimientoformadepagoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Acreenciamovimientoformadepago> rt = cq
					.from(Acreenciamovimientoformadepago.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
