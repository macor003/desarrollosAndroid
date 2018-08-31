/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa400.exceptions.IllegalOrphanException;
import crjpa400.exceptions.NonexistentEntityException;
import crjpa400.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class MonedadenominacionJpaController implements Serializable {

	/**
	 * Constructor for MonedadenominacionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public MonedadenominacionJpaController(EntityManagerFactory emf) {
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
	 * @param monedadenominacion
	 *            Monedadenominacion
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Monedadenominacion monedadenominacion)
			throws PreexistingEntityException, Exception {
		if (monedadenominacion.getEntregaformadepagoList() == null) {
			monedadenominacion
					.setEntregaformadepagoList(new ArrayList<Entregaformadepago>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Moneda idMoneda = monedadenominacion.getIdMoneda();
			if (idMoneda != null) {
				idMoneda = em.getReference(idMoneda.getClass(),
						idMoneda.getId());
				monedadenominacion.setIdMoneda(idMoneda);
			}
			List<Entregaformadepago> attachedEntregaformadepagoList = new ArrayList<Entregaformadepago>();
			for (Entregaformadepago entregaformadepagoListEntregaformadepagoToAttach : monedadenominacion
					.getEntregaformadepagoList()) {
				entregaformadepagoListEntregaformadepagoToAttach = em
						.getReference(
								entregaformadepagoListEntregaformadepagoToAttach
										.getClass(),
								entregaformadepagoListEntregaformadepagoToAttach
										.getId());
				attachedEntregaformadepagoList
						.add(entregaformadepagoListEntregaformadepagoToAttach);
			}
			monedadenominacion
					.setEntregaformadepagoList(attachedEntregaformadepagoList);
			em.persist(monedadenominacion);
			if (idMoneda != null) {
				idMoneda.getMonedadenominacionList().add(monedadenominacion);
				idMoneda = em.merge(idMoneda);
			}
			for (Entregaformadepago entregaformadepagoListEntregaformadepago : monedadenominacion
					.getEntregaformadepagoList()) {
				Monedadenominacion oldIdMonedadenominacionOfEntregaformadepagoListEntregaformadepago = entregaformadepagoListEntregaformadepago
						.getIdMonedadenominacion();
				entregaformadepagoListEntregaformadepago
						.setIdMonedadenominacion(monedadenominacion);
				entregaformadepagoListEntregaformadepago = em
						.merge(entregaformadepagoListEntregaformadepago);
				if (oldIdMonedadenominacionOfEntregaformadepagoListEntregaformadepago != null) {
					oldIdMonedadenominacionOfEntregaformadepagoListEntregaformadepago
							.getEntregaformadepagoList().remove(
									entregaformadepagoListEntregaformadepago);
					oldIdMonedadenominacionOfEntregaformadepagoListEntregaformadepago = em
							.merge(oldIdMonedadenominacionOfEntregaformadepagoListEntregaformadepago);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findMonedadenominacion(monedadenominacion.getId()) != null) {
				throw new PreexistingEntityException("Monedadenominacion "
						+ monedadenominacion + " already exists.", ex);
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
	 * @param monedadenominacion
	 *            Monedadenominacion
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Monedadenominacion monedadenominacion)
			throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Monedadenominacion persistentMonedadenominacion = em.find(
					Monedadenominacion.class, monedadenominacion.getId());
			Moneda idMonedaOld = persistentMonedadenominacion.getIdMoneda();
			Moneda idMonedaNew = monedadenominacion.getIdMoneda();
			List<Entregaformadepago> entregaformadepagoListOld = persistentMonedadenominacion
					.getEntregaformadepagoList();
			List<Entregaformadepago> entregaformadepagoListNew = monedadenominacion
					.getEntregaformadepagoList();
			List<String> illegalOrphanMessages = null;
			for (Entregaformadepago entregaformadepagoListOldEntregaformadepago : entregaformadepagoListOld) {
				if (!entregaformadepagoListNew
						.contains(entregaformadepagoListOldEntregaformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Entregaformadepago "
									+ entregaformadepagoListOldEntregaformadepago
									+ " since its idMonedadenominacion field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idMonedaNew != null) {
				idMonedaNew = em.getReference(idMonedaNew.getClass(),
						idMonedaNew.getId());
				monedadenominacion.setIdMoneda(idMonedaNew);
			}
			List<Entregaformadepago> attachedEntregaformadepagoListNew = new ArrayList<Entregaformadepago>();
			for (Entregaformadepago entregaformadepagoListNewEntregaformadepagoToAttach : entregaformadepagoListNew) {
				entregaformadepagoListNewEntregaformadepagoToAttach = em
						.getReference(
								entregaformadepagoListNewEntregaformadepagoToAttach
										.getClass(),
								entregaformadepagoListNewEntregaformadepagoToAttach
										.getId());
				attachedEntregaformadepagoListNew
						.add(entregaformadepagoListNewEntregaformadepagoToAttach);
			}
			entregaformadepagoListNew = attachedEntregaformadepagoListNew;
			monedadenominacion
					.setEntregaformadepagoList(entregaformadepagoListNew);
			monedadenominacion = em.merge(monedadenominacion);
			if (idMonedaOld != null && !idMonedaOld.equals(idMonedaNew)) {
				idMonedaOld.getMonedadenominacionList().remove(
						monedadenominacion);
				idMonedaOld = em.merge(idMonedaOld);
			}
			if (idMonedaNew != null && !idMonedaNew.equals(idMonedaOld)) {
				idMonedaNew.getMonedadenominacionList().add(monedadenominacion);
				idMonedaNew = em.merge(idMonedaNew);
			}
			for (Entregaformadepago entregaformadepagoListNewEntregaformadepago : entregaformadepagoListNew) {
				if (!entregaformadepagoListOld
						.contains(entregaformadepagoListNewEntregaformadepago)) {
					Monedadenominacion oldIdMonedadenominacionOfEntregaformadepagoListNewEntregaformadepago = entregaformadepagoListNewEntregaformadepago
							.getIdMonedadenominacion();
					entregaformadepagoListNewEntregaformadepago
							.setIdMonedadenominacion(monedadenominacion);
					entregaformadepagoListNewEntregaformadepago = em
							.merge(entregaformadepagoListNewEntregaformadepago);
					if (oldIdMonedadenominacionOfEntregaformadepagoListNewEntregaformadepago != null
							&& !oldIdMonedadenominacionOfEntregaformadepagoListNewEntregaformadepago
									.equals(monedadenominacion)) {
						oldIdMonedadenominacionOfEntregaformadepagoListNewEntregaformadepago
								.getEntregaformadepagoList()
								.remove(entregaformadepagoListNewEntregaformadepago);
						oldIdMonedadenominacionOfEntregaformadepagoListNewEntregaformadepago = em
								.merge(oldIdMonedadenominacionOfEntregaformadepagoListNewEntregaformadepago);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = monedadenominacion.getId();
				if (findMonedadenominacion(id) == null) {
					throw new NonexistentEntityException(
							"The monedadenominacion with id " + id
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
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 */
	public void destroy(Long id) throws IllegalOrphanException,
			NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Monedadenominacion monedadenominacion;
			try {
				monedadenominacion = em.getReference(Monedadenominacion.class,
						id);
				monedadenominacion.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The monedadenominacion with id " + id
								+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Entregaformadepago> entregaformadepagoListOrphanCheck = monedadenominacion
					.getEntregaformadepagoList();
			for (Entregaformadepago entregaformadepagoListOrphanCheckEntregaformadepago : entregaformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Monedadenominacion ("
								+ monedadenominacion
								+ ") cannot be destroyed since the Entregaformadepago "
								+ entregaformadepagoListOrphanCheckEntregaformadepago
								+ " in its entregaformadepagoList field has a non-nullable idMonedadenominacion field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Moneda idMoneda = monedadenominacion.getIdMoneda();
			if (idMoneda != null) {
				idMoneda.getMonedadenominacionList().remove(monedadenominacion);
				idMoneda = em.merge(idMoneda);
			}
			em.remove(monedadenominacion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findMonedadenominacionEntities.
	 * 
	 * @return List<Monedadenominacion>
	 */
	public List<Monedadenominacion> findMonedadenominacionEntities() {
		return findMonedadenominacionEntities(true, -1, -1);
	}

	/**
	 * Method findMonedadenominacionEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Monedadenominacion>
	 */
	public List<Monedadenominacion> findMonedadenominacionEntities(
			int maxResults, int firstResult) {
		return findMonedadenominacionEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findMonedadenominacionEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Monedadenominacion>
	 */
	private List<Monedadenominacion> findMonedadenominacionEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Monedadenominacion.class));
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
	 * Method findMonedadenominacion.
	 * 
	 * @param id
	 *            Long
	 * @return Monedadenominacion
	 */
	public Monedadenominacion findMonedadenominacion(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Monedadenominacion.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getMonedadenominacionCount.
	 * 
	 * @return int
	 */
	public int getMonedadenominacionCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Monedadenominacion> rt = cq.from(Monedadenominacion.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
