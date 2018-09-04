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
public class PromocionformadepagoJpaController implements Serializable {

	/**
	 * Constructor for PromocionformadepagoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public PromocionformadepagoJpaController(EntityManagerFactory emf) {
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
	 * @param promocionformadepago
	 *            Promocionformadepago
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Promocionformadepago promocionformadepago) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Promocion idPromocion = promocionformadepago.getIdPromocion();
			if (idPromocion != null) {
				idPromocion = em.getReference(idPromocion.getClass(), idPromocion.getId());
				promocionformadepago.setIdPromocion(idPromocion);
			}
			Formadepago idFormaDePago = promocionformadepago.getIdFormaDePago();
			if (idFormaDePago != null) {
				idFormaDePago = em.getReference(idFormaDePago.getClass(), idFormaDePago.getId());
				promocionformadepago.setIdFormaDePago(idFormaDePago);
			}
			em.persist(promocionformadepago);
			if (idPromocion != null) {
				idPromocion.getPromocionformadepagoList().add(promocionformadepago);
				idPromocion = em.merge(idPromocion);
			}
			if (idFormaDePago != null) {
				idFormaDePago.getPromocionformadepagoList().add(promocionformadepago);
				idFormaDePago = em.merge(idFormaDePago);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findPromocionformadepago(promocionformadepago.getId()) != null) {
				throw new PreexistingEntityException("Promocionformadepago " + promocionformadepago
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
	 * @param promocionformadepago
	 *            Promocionformadepago
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Promocionformadepago promocionformadepago) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Promocionformadepago persistentPromocionformadepago = em.find(Promocionformadepago.class,
					promocionformadepago.getId());
			Promocion idPromocionOld = persistentPromocionformadepago.getIdPromocion();
			Promocion idPromocionNew = promocionformadepago.getIdPromocion();
			Formadepago idFormaDePagoOld = persistentPromocionformadepago.getIdFormaDePago();
			Formadepago idFormaDePagoNew = promocionformadepago.getIdFormaDePago();
			if (idPromocionNew != null) {
				idPromocionNew = em.getReference(idPromocionNew.getClass(), idPromocionNew.getId());
				promocionformadepago.setIdPromocion(idPromocionNew);
			}
			if (idFormaDePagoNew != null) {
				idFormaDePagoNew = em.getReference(idFormaDePagoNew.getClass(), idFormaDePagoNew.getId());
				promocionformadepago.setIdFormaDePago(idFormaDePagoNew);
			}
			promocionformadepago = em.merge(promocionformadepago);
			if (idPromocionOld != null && !idPromocionOld.equals(idPromocionNew)) {
				idPromocionOld.getPromocionformadepagoList().remove(promocionformadepago);
				idPromocionOld = em.merge(idPromocionOld);
			}
			if (idPromocionNew != null && !idPromocionNew.equals(idPromocionOld)) {
				idPromocionNew.getPromocionformadepagoList().add(promocionformadepago);
				idPromocionNew = em.merge(idPromocionNew);
			}
			if (idFormaDePagoOld != null && !idFormaDePagoOld.equals(idFormaDePagoNew)) {
				idFormaDePagoOld.getPromocionformadepagoList().remove(promocionformadepago);
				idFormaDePagoOld = em.merge(idFormaDePagoOld);
			}
			if (idFormaDePagoNew != null && !idFormaDePagoNew.equals(idFormaDePagoOld)) {
				idFormaDePagoNew.getPromocionformadepagoList().add(promocionformadepago);
				idFormaDePagoNew = em.merge(idFormaDePagoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = promocionformadepago.getId();
				if (findPromocionformadepago(id) == null) {
					throw new NonexistentEntityException("The promocionformadepago with id " + id
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
			Promocionformadepago promocionformadepago;
			try {
				promocionformadepago = em.getReference(Promocionformadepago.class, id);
				promocionformadepago.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The promocionformadepago with id " + id + " no longer exists.",
						enfe);
			}
			Promocion idPromocion = promocionformadepago.getIdPromocion();
			if (idPromocion != null) {
				idPromocion.getPromocionformadepagoList().remove(promocionformadepago);
				idPromocion = em.merge(idPromocion);
			}
			Formadepago idFormaDePago = promocionformadepago.getIdFormaDePago();
			if (idFormaDePago != null) {
				idFormaDePago.getPromocionformadepagoList().remove(promocionformadepago);
				idFormaDePago = em.merge(idFormaDePago);
			}
			em.remove(promocionformadepago);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findPromocionformadepagoEntities.
	 * 
	 * @return List<Promocionformadepago>
	 */
	public List<Promocionformadepago> findPromocionformadepagoEntities() {
		return findPromocionformadepagoEntities(true, -1, -1);
	}

	/**
	 * Method findPromocionformadepagoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Promocionformadepago>
	 */
	public List<Promocionformadepago> findPromocionformadepagoEntities(int maxResults, int firstResult) {
		return findPromocionformadepagoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findPromocionformadepagoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Promocionformadepago>
	 */
	private List<Promocionformadepago> findPromocionformadepagoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Promocionformadepago.class));
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
	 * Method findPromocionformadepago.
	 * 
	 * @param id
	 *            Long
	 * @return Promocionformadepago
	 */
	public Promocionformadepago findPromocionformadepago(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Promocionformadepago.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getPromocionformadepagoCount.
	 * 
	 * @return int
	 */
	public int getPromocionformadepagoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Promocionformadepago> rt = cq.from(Promocionformadepago.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
