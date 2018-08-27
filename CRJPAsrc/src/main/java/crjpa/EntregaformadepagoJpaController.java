/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
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
public class EntregaformadepagoJpaController implements Serializable {

	/**
	 * Constructor for EntregaformadepagoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public EntregaformadepagoJpaController(EntityManagerFactory emf) {
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
	 * @param entregaformadepago
	 *            Entregaformadepago
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Entregaformadepago entregaformadepago) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Formadepago idFormadepago = entregaformadepago.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago = em.getReference(idFormadepago.getClass(), idFormadepago.getId());
				entregaformadepago.setIdFormadepago(idFormadepago);
			}
			Entrega idEntrega = entregaformadepago.getIdEntrega();
			if (idEntrega != null) {
				idEntrega = em.getReference(idEntrega.getClass(), idEntrega.getId());
				entregaformadepago.setIdEntrega(idEntrega);
			}
			Monedadenominacion idMonedadenominacion = entregaformadepago.getIdMonedadenominacion();
			if (idMonedadenominacion != null) {
				idMonedadenominacion = em.getReference(idMonedadenominacion.getClass(), idMonedadenominacion.getId());
				entregaformadepago.setIdMonedadenominacion(idMonedadenominacion);
			}
			em.persist(entregaformadepago);
			if (idFormadepago != null) {
				idFormadepago.getEntregaformadepagoList().add(entregaformadepago);
				idFormadepago = em.merge(idFormadepago);
			}
			if (idEntrega != null) {
				idEntrega.getEntregaformadepagoList().add(entregaformadepago);
				idEntrega = em.merge(idEntrega);
			}
			if (idMonedadenominacion != null) {
				idMonedadenominacion.getEntregaformadepagoList().add(entregaformadepago);
				idMonedadenominacion = em.merge(idMonedadenominacion);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findEntregaformadepago(entregaformadepago.getId()) != null) {
				throw new PreexistingEntityException("Entregaformadepago " + entregaformadepago + " already exists.",
						ex);
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
	 * @param entregaformadepago
	 *            Entregaformadepago
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Entregaformadepago entregaformadepago) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Entregaformadepago persistentEntregaformadepago = em.find(Entregaformadepago.class,
					entregaformadepago.getId());
			Formadepago idFormadepagoOld = persistentEntregaformadepago.getIdFormadepago();
			Formadepago idFormadepagoNew = entregaformadepago.getIdFormadepago();
			Entrega idEntregaOld = persistentEntregaformadepago.getIdEntrega();
			Entrega idEntregaNew = entregaformadepago.getIdEntrega();
			Monedadenominacion idMonedadenominacionOld = persistentEntregaformadepago.getIdMonedadenominacion();
			Monedadenominacion idMonedadenominacionNew = entregaformadepago.getIdMonedadenominacion();
			if (idFormadepagoNew != null) {
				idFormadepagoNew = em.getReference(idFormadepagoNew.getClass(), idFormadepagoNew.getId());
				entregaformadepago.setIdFormadepago(idFormadepagoNew);
			}
			if (idEntregaNew != null) {
				idEntregaNew = em.getReference(idEntregaNew.getClass(), idEntregaNew.getId());
				entregaformadepago.setIdEntrega(idEntregaNew);
			}
			if (idMonedadenominacionNew != null) {
				idMonedadenominacionNew = em.getReference(idMonedadenominacionNew.getClass(),
						idMonedadenominacionNew.getId());
				entregaformadepago.setIdMonedadenominacion(idMonedadenominacionNew);
			}
			entregaformadepago = em.merge(entregaformadepago);
			if (idFormadepagoOld != null && !idFormadepagoOld.equals(idFormadepagoNew)) {
				idFormadepagoOld.getEntregaformadepagoList().remove(entregaformadepago);
				idFormadepagoOld = em.merge(idFormadepagoOld);
			}
			if (idFormadepagoNew != null && !idFormadepagoNew.equals(idFormadepagoOld)) {
				idFormadepagoNew.getEntregaformadepagoList().add(entregaformadepago);
				idFormadepagoNew = em.merge(idFormadepagoNew);
			}
			if (idEntregaOld != null && !idEntregaOld.equals(idEntregaNew)) {
				idEntregaOld.getEntregaformadepagoList().remove(entregaformadepago);
				idEntregaOld = em.merge(idEntregaOld);
			}
			if (idEntregaNew != null && !idEntregaNew.equals(idEntregaOld)) {
				idEntregaNew.getEntregaformadepagoList().add(entregaformadepago);
				idEntregaNew = em.merge(idEntregaNew);
			}
			if (idMonedadenominacionOld != null && !idMonedadenominacionOld.equals(idMonedadenominacionNew)) {
				idMonedadenominacionOld.getEntregaformadepagoList().remove(entregaformadepago);
				idMonedadenominacionOld = em.merge(idMonedadenominacionOld);
			}
			if (idMonedadenominacionNew != null && !idMonedadenominacionNew.equals(idMonedadenominacionOld)) {
				idMonedadenominacionNew.getEntregaformadepagoList().add(entregaformadepago);
				idMonedadenominacionNew = em.merge(idMonedadenominacionNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = entregaformadepago.getId();
				if (findEntregaformadepago(id) == null) {
					throw new NonexistentEntityException("The entregaformadepago with id " + id + " no longer exists.");
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
			Entregaformadepago entregaformadepago;
			try {
				entregaformadepago = em.getReference(Entregaformadepago.class, id);
				entregaformadepago.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The entregaformadepago with id " + id + " no longer exists.",
						enfe);
			}
			Formadepago idFormadepago = entregaformadepago.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago.getEntregaformadepagoList().remove(entregaformadepago);
				idFormadepago = em.merge(idFormadepago);
			}
			Entrega idEntrega = entregaformadepago.getIdEntrega();
			if (idEntrega != null) {
				idEntrega.getEntregaformadepagoList().remove(entregaformadepago);
				idEntrega = em.merge(idEntrega);
			}
			Monedadenominacion idMonedadenominacion = entregaformadepago.getIdMonedadenominacion();
			if (idMonedadenominacion != null) {
				idMonedadenominacion.getEntregaformadepagoList().remove(entregaformadepago);
				idMonedadenominacion = em.merge(idMonedadenominacion);
			}
			em.remove(entregaformadepago);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findEntregaformadepagoEntities.
	 * 
	 * @return List<Entregaformadepago>
	 */
	public List<Entregaformadepago> findEntregaformadepagoEntities() {
		return findEntregaformadepagoEntities(true, -1, -1);
	}

	/**
	 * Method findEntregaformadepagoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Entregaformadepago>
	 */
	public List<Entregaformadepago> findEntregaformadepagoEntities(int maxResults, int firstResult) {
		return findEntregaformadepagoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findEntregaformadepagoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Entregaformadepago>
	 */
	private List<Entregaformadepago> findEntregaformadepagoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Entregaformadepago.class));
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
	 * Method findEntregaformadepago.
	 * 
	 * @param id
	 *            Long
	 * @return Entregaformadepago
	 */
	public Entregaformadepago findEntregaformadepago(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Entregaformadepago.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getEntregaformadepagoCount.
	 * 
	 * @return int
	 */
	public int getEntregaformadepagoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Entregaformadepago> rt = cq.from(Entregaformadepago.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
