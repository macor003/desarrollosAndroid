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
public class AbonoformadepagoJpaController implements Serializable {

	/**
	 * Constructor for AbonoformadepagoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public AbonoformadepagoJpaController(EntityManagerFactory emf) {
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
	 * @param abonoformadepago
	 *            Abonoformadepago
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Abonoformadepago abonoformadepago)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Moneda idMoneda = abonoformadepago.getIdMoneda();
			if (idMoneda != null) {
				idMoneda = em.getReference(idMoneda.getClass(),
						idMoneda.getId());
				abonoformadepago.setIdMoneda(idMoneda);
			}
			Formadepago idFormadepago = abonoformadepago.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago = em.getReference(idFormadepago.getClass(),
						idFormadepago.getId());
				abonoformadepago.setIdFormadepago(idFormadepago);
			}
			Abono idAbono = abonoformadepago.getIdAbono();
			if (idAbono != null) {
				idAbono = em.getReference(idAbono.getClass(), idAbono.getId());
				abonoformadepago.setIdAbono(idAbono);
			}
			em.persist(abonoformadepago);
			if (idMoneda != null) {
				idMoneda.getAbonoformadepagoList().add(abonoformadepago);
				idMoneda = em.merge(idMoneda);
			}
			if (idFormadepago != null) {
				idFormadepago.getAbonoformadepagoList().add(abonoformadepago);
				idFormadepago = em.merge(idFormadepago);
			}
			if (idAbono != null) {
				idAbono.getAbonoformadepagoList().add(abonoformadepago);
				idAbono = em.merge(idAbono);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findAbonoformadepago(abonoformadepago.getId()) != null) {
				throw new PreexistingEntityException("Abonoformadepago "
						+ abonoformadepago + " already exists.", ex);
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
	 * @param abonoformadepago
	 *            Abonoformadepago
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Abonoformadepago abonoformadepago)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Abonoformadepago persistentAbonoformadepago = em.find(
					Abonoformadepago.class, abonoformadepago.getId());
			Moneda idMonedaOld = persistentAbonoformadepago.getIdMoneda();
			Moneda idMonedaNew = abonoformadepago.getIdMoneda();
			Formadepago idFormadepagoOld = persistentAbonoformadepago
					.getIdFormadepago();
			Formadepago idFormadepagoNew = abonoformadepago.getIdFormadepago();
			Abono idAbonoOld = persistentAbonoformadepago.getIdAbono();
			Abono idAbonoNew = abonoformadepago.getIdAbono();
			if (idMonedaNew != null) {
				idMonedaNew = em.getReference(idMonedaNew.getClass(),
						idMonedaNew.getId());
				abonoformadepago.setIdMoneda(idMonedaNew);
			}
			if (idFormadepagoNew != null) {
				idFormadepagoNew = em.getReference(idFormadepagoNew.getClass(),
						idFormadepagoNew.getId());
				abonoformadepago.setIdFormadepago(idFormadepagoNew);
			}
			if (idAbonoNew != null) {
				idAbonoNew = em.getReference(idAbonoNew.getClass(),
						idAbonoNew.getId());
				abonoformadepago.setIdAbono(idAbonoNew);
			}
			abonoformadepago = em.merge(abonoformadepago);
			if (idMonedaOld != null && !idMonedaOld.equals(idMonedaNew)) {
				idMonedaOld.getAbonoformadepagoList().remove(abonoformadepago);
				idMonedaOld = em.merge(idMonedaOld);
			}
			if (idMonedaNew != null && !idMonedaNew.equals(idMonedaOld)) {
				idMonedaNew.getAbonoformadepagoList().add(abonoformadepago);
				idMonedaNew = em.merge(idMonedaNew);
			}
			if (idFormadepagoOld != null
					&& !idFormadepagoOld.equals(idFormadepagoNew)) {
				idFormadepagoOld.getAbonoformadepagoList().remove(
						abonoformadepago);
				idFormadepagoOld = em.merge(idFormadepagoOld);
			}
			if (idFormadepagoNew != null
					&& !idFormadepagoNew.equals(idFormadepagoOld)) {
				idFormadepagoNew.getAbonoformadepagoList()
						.add(abonoformadepago);
				idFormadepagoNew = em.merge(idFormadepagoNew);
			}
			if (idAbonoOld != null && !idAbonoOld.equals(idAbonoNew)) {
				idAbonoOld.getAbonoformadepagoList().remove(abonoformadepago);
				idAbonoOld = em.merge(idAbonoOld);
			}
			if (idAbonoNew != null && !idAbonoNew.equals(idAbonoOld)) {
				idAbonoNew.getAbonoformadepagoList().add(abonoformadepago);
				idAbonoNew = em.merge(idAbonoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = abonoformadepago.getId();
				if (findAbonoformadepago(id) == null) {
					throw new NonexistentEntityException(
							"The abonoformadepago with id " + id
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
			Abonoformadepago abonoformadepago;
			try {
				abonoformadepago = em.getReference(Abonoformadepago.class, id);
				abonoformadepago.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The abonoformadepago with id " + id
								+ " no longer exists.", enfe);
			}
			Moneda idMoneda = abonoformadepago.getIdMoneda();
			if (idMoneda != null) {
				idMoneda.getAbonoformadepagoList().remove(abonoformadepago);
				idMoneda = em.merge(idMoneda);
			}
			Formadepago idFormadepago = abonoformadepago.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago.getAbonoformadepagoList()
						.remove(abonoformadepago);
				idFormadepago = em.merge(idFormadepago);
			}
			Abono idAbono = abonoformadepago.getIdAbono();
			if (idAbono != null) {
				idAbono.getAbonoformadepagoList().remove(abonoformadepago);
				idAbono = em.merge(idAbono);
			}
			em.remove(abonoformadepago);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findAbonoformadepagoEntities.
	 * 
	 * @return List<Abonoformadepago>
	 */
	public List<Abonoformadepago> findAbonoformadepagoEntities() {
		return findAbonoformadepagoEntities(true, -1, -1);
	}

	/**
	 * Method findAbonoformadepagoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Abonoformadepago>
	 */
	public List<Abonoformadepago> findAbonoformadepagoEntities(int maxResults,
			int firstResult) {
		return findAbonoformadepagoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findAbonoformadepagoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Abonoformadepago>
	 */
	private List<Abonoformadepago> findAbonoformadepagoEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Abonoformadepago.class));
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
	 * Method findAbonoformadepago.
	 * 
	 * @param id
	 *            Long
	 * @return Abonoformadepago
	 */
	public Abonoformadepago findAbonoformadepago(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Abonoformadepago.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getAbonoformadepagoCount.
	 * 
	 * @return int
	 */
	public int getAbonoformadepagoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Abonoformadepago> rt = cq.from(Abonoformadepago.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
