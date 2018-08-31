/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa.exceptions.IllegalOrphanException;
import crjpa.exceptions.NonexistentEntityException;
import crjpa.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class MonedaJpaController implements Serializable {

	/**
	 * Constructor for MonedaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public MonedaJpaController(EntityManagerFactory emf) {
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
	 * @param moneda
	 *            Moneda
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Moneda moneda) throws PreexistingEntityException, Exception {
		if (moneda.getMonedadenominacionList() == null) {
			moneda.setMonedadenominacionList(new ArrayList<Monedadenominacion>());
		}
		if (moneda.getMonedatasacambioList() == null) {
			moneda.setMonedatasacambioList(new ArrayList<Monedatasacambio>());
		}
		if (moneda.getFormadepagomonedaList() == null) {
			moneda.setFormadepagomonedaList(new ArrayList<Formadepagomoneda>());
		}
		if (moneda.getTransaccionformadepagoList() == null) {
			moneda.setTransaccionformadepagoList(new ArrayList<Transaccionformadepago>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Monedadenominacion> attachedMonedadenominacionList = new ArrayList<Monedadenominacion>();
			for (Monedadenominacion monedadenominacionListMonedadenominacionToAttach : moneda
					.getMonedadenominacionList()) {
				monedadenominacionListMonedadenominacionToAttach = em.getReference(
						monedadenominacionListMonedadenominacionToAttach.getClass(),
						monedadenominacionListMonedadenominacionToAttach.getId());
				attachedMonedadenominacionList.add(monedadenominacionListMonedadenominacionToAttach);
			}
			moneda.setMonedadenominacionList(attachedMonedadenominacionList);
			List<Monedatasacambio> attachedMonedatasacambioList = new ArrayList<Monedatasacambio>();
			for (Monedatasacambio monedatasacambioListMonedatasacambioToAttach : moneda.getMonedatasacambioList()) {
				monedatasacambioListMonedatasacambioToAttach = em.getReference(
						monedatasacambioListMonedatasacambioToAttach.getClass(),
						monedatasacambioListMonedatasacambioToAttach.getId());
				attachedMonedatasacambioList.add(monedatasacambioListMonedatasacambioToAttach);
			}
			moneda.setMonedatasacambioList(attachedMonedatasacambioList);
			List<Formadepagomoneda> attachedFormadepagomonedaList = new ArrayList<Formadepagomoneda>();
			for (Formadepagomoneda formadepagomonedaListFormadepagomonedaToAttach : moneda.getFormadepagomonedaList()) {
				formadepagomonedaListFormadepagomonedaToAttach = em.getReference(
						formadepagomonedaListFormadepagomonedaToAttach.getClass(),
						formadepagomonedaListFormadepagomonedaToAttach.getId());
				attachedFormadepagomonedaList.add(formadepagomonedaListFormadepagomonedaToAttach);
			}
			moneda.setFormadepagomonedaList(attachedFormadepagomonedaList);
			List<Transaccionformadepago> attachedTransaccionformadepagoList = new ArrayList<Transaccionformadepago>();
			for (Transaccionformadepago transaccionformadepagoListTransaccionformadepagoToAttach : moneda
					.getTransaccionformadepagoList()) {
				transaccionformadepagoListTransaccionformadepagoToAttach = em.getReference(
						transaccionformadepagoListTransaccionformadepagoToAttach.getClass(),
						transaccionformadepagoListTransaccionformadepagoToAttach.getId());
				attachedTransaccionformadepagoList.add(transaccionformadepagoListTransaccionformadepagoToAttach);
			}
			moneda.setTransaccionformadepagoList(attachedTransaccionformadepagoList);
			em.persist(moneda);
			for (Monedadenominacion monedadenominacionListMonedadenominacion : moneda.getMonedadenominacionList()) {
				Moneda oldIdMonedaOfMonedadenominacionListMonedadenominacion = monedadenominacionListMonedadenominacion
						.getIdMoneda();
				monedadenominacionListMonedadenominacion.setIdMoneda(moneda);
				monedadenominacionListMonedadenominacion = em.merge(monedadenominacionListMonedadenominacion);
				if (oldIdMonedaOfMonedadenominacionListMonedadenominacion != null) {
					oldIdMonedaOfMonedadenominacionListMonedadenominacion.getMonedadenominacionList().remove(
							monedadenominacionListMonedadenominacion);
					oldIdMonedaOfMonedadenominacionListMonedadenominacion = em
							.merge(oldIdMonedaOfMonedadenominacionListMonedadenominacion);
				}
			}
			for (Monedatasacambio monedatasacambioListMonedatasacambio : moneda.getMonedatasacambioList()) {
				Moneda oldIdMonedaOfMonedatasacambioListMonedatasacambio = monedatasacambioListMonedatasacambio
						.getIdMoneda();
				monedatasacambioListMonedatasacambio.setIdMoneda(moneda);
				monedatasacambioListMonedatasacambio = em.merge(monedatasacambioListMonedatasacambio);
				if (oldIdMonedaOfMonedatasacambioListMonedatasacambio != null) {
					oldIdMonedaOfMonedatasacambioListMonedatasacambio.getMonedatasacambioList().remove(
							monedatasacambioListMonedatasacambio);
					oldIdMonedaOfMonedatasacambioListMonedatasacambio = em
							.merge(oldIdMonedaOfMonedatasacambioListMonedatasacambio);
				}
			}
			for (Formadepagomoneda formadepagomonedaListFormadepagomoneda : moneda.getFormadepagomonedaList()) {
				Moneda oldIdMonedaOfFormadepagomonedaListFormadepagomoneda = formadepagomonedaListFormadepagomoneda
						.getIdMoneda();
				formadepagomonedaListFormadepagomoneda.setIdMoneda(moneda);
				formadepagomonedaListFormadepagomoneda = em.merge(formadepagomonedaListFormadepagomoneda);
				if (oldIdMonedaOfFormadepagomonedaListFormadepagomoneda != null) {
					oldIdMonedaOfFormadepagomonedaListFormadepagomoneda.getFormadepagomonedaList().remove(
							formadepagomonedaListFormadepagomoneda);
					oldIdMonedaOfFormadepagomonedaListFormadepagomoneda = em
							.merge(oldIdMonedaOfFormadepagomonedaListFormadepagomoneda);
				}
			}
			for (Transaccionformadepago transaccionformadepagoListTransaccionformadepago : moneda
					.getTransaccionformadepagoList()) {
				Moneda oldIdMonedaOfTransaccionformadepagoListTransaccionformadepago = transaccionformadepagoListTransaccionformadepago
						.getIdMoneda();
				transaccionformadepagoListTransaccionformadepago.setIdMoneda(moneda);
				transaccionformadepagoListTransaccionformadepago = em
						.merge(transaccionformadepagoListTransaccionformadepago);
				if (oldIdMonedaOfTransaccionformadepagoListTransaccionformadepago != null) {
					oldIdMonedaOfTransaccionformadepagoListTransaccionformadepago.getTransaccionformadepagoList()
							.remove(transaccionformadepagoListTransaccionformadepago);
					oldIdMonedaOfTransaccionformadepagoListTransaccionformadepago = em
							.merge(oldIdMonedaOfTransaccionformadepagoListTransaccionformadepago);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findMoneda(moneda.getId()) != null) {
				throw new PreexistingEntityException("Moneda " + moneda + " already exists.", ex);
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
	 * @param moneda
	 *            Moneda
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Moneda moneda) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Moneda persistentMoneda = em.find(Moneda.class, moneda.getId());
			List<Monedadenominacion> monedadenominacionListOld = persistentMoneda.getMonedadenominacionList();
			List<Monedadenominacion> monedadenominacionListNew = moneda.getMonedadenominacionList();
			List<Monedatasacambio> monedatasacambioListOld = persistentMoneda.getMonedatasacambioList();
			List<Monedatasacambio> monedatasacambioListNew = moneda.getMonedatasacambioList();
			List<Formadepagomoneda> formadepagomonedaListOld = persistentMoneda.getFormadepagomonedaList();
			List<Formadepagomoneda> formadepagomonedaListNew = moneda.getFormadepagomonedaList();
			List<Transaccionformadepago> transaccionformadepagoListOld = persistentMoneda
					.getTransaccionformadepagoList();
			List<Transaccionformadepago> transaccionformadepagoListNew = moneda.getTransaccionformadepagoList();
			List<String> illegalOrphanMessages = null;
			for (Monedadenominacion monedadenominacionListOldMonedadenominacion : monedadenominacionListOld) {
				if (!monedadenominacionListNew.contains(monedadenominacionListOldMonedadenominacion)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Monedadenominacion "
							+ monedadenominacionListOldMonedadenominacion
							+ " since its idMoneda field is not nullable.");
				}
			}
			for (Monedatasacambio monedatasacambioListOldMonedatasacambio : monedatasacambioListOld) {
				if (!monedatasacambioListNew.contains(monedatasacambioListOldMonedatasacambio)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Monedatasacambio "
							+ monedatasacambioListOldMonedatasacambio + " since its idMoneda field is not nullable.");
				}
			}
			for (Formadepagomoneda formadepagomonedaListOldFormadepagomoneda : formadepagomonedaListOld) {
				if (!formadepagomonedaListNew.contains(formadepagomonedaListOldFormadepagomoneda)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Formadepagomoneda "
							+ formadepagomonedaListOldFormadepagomoneda + " since its idMoneda field is not nullable.");
				}
			}
			for (Transaccionformadepago transaccionformadepagoListOldTransaccionformadepago : transaccionformadepagoListOld) {
				if (!transaccionformadepagoListNew.contains(transaccionformadepagoListOldTransaccionformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Transaccionformadepago "
							+ transaccionformadepagoListOldTransaccionformadepago
							+ " since its idMoneda field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Monedadenominacion> attachedMonedadenominacionListNew = new ArrayList<Monedadenominacion>();
			for (Monedadenominacion monedadenominacionListNewMonedadenominacionToAttach : monedadenominacionListNew) {
				monedadenominacionListNewMonedadenominacionToAttach = em.getReference(
						monedadenominacionListNewMonedadenominacionToAttach.getClass(),
						monedadenominacionListNewMonedadenominacionToAttach.getId());
				attachedMonedadenominacionListNew.add(monedadenominacionListNewMonedadenominacionToAttach);
			}
			monedadenominacionListNew = attachedMonedadenominacionListNew;
			moneda.setMonedadenominacionList(monedadenominacionListNew);
			List<Monedatasacambio> attachedMonedatasacambioListNew = new ArrayList<Monedatasacambio>();
			for (Monedatasacambio monedatasacambioListNewMonedatasacambioToAttach : monedatasacambioListNew) {
				monedatasacambioListNewMonedatasacambioToAttach = em.getReference(
						monedatasacambioListNewMonedatasacambioToAttach.getClass(),
						monedatasacambioListNewMonedatasacambioToAttach.getId());
				attachedMonedatasacambioListNew.add(monedatasacambioListNewMonedatasacambioToAttach);
			}
			monedatasacambioListNew = attachedMonedatasacambioListNew;
			moneda.setMonedatasacambioList(monedatasacambioListNew);
			List<Formadepagomoneda> attachedFormadepagomonedaListNew = new ArrayList<Formadepagomoneda>();
			for (Formadepagomoneda formadepagomonedaListNewFormadepagomonedaToAttach : formadepagomonedaListNew) {
				formadepagomonedaListNewFormadepagomonedaToAttach = em.getReference(
						formadepagomonedaListNewFormadepagomonedaToAttach.getClass(),
						formadepagomonedaListNewFormadepagomonedaToAttach.getId());
				attachedFormadepagomonedaListNew.add(formadepagomonedaListNewFormadepagomonedaToAttach);
			}
			formadepagomonedaListNew = attachedFormadepagomonedaListNew;
			moneda.setFormadepagomonedaList(formadepagomonedaListNew);
			List<Transaccionformadepago> attachedTransaccionformadepagoListNew = new ArrayList<Transaccionformadepago>();
			for (Transaccionformadepago transaccionformadepagoListNewTransaccionformadepagoToAttach : transaccionformadepagoListNew) {
				transaccionformadepagoListNewTransaccionformadepagoToAttach = em.getReference(
						transaccionformadepagoListNewTransaccionformadepagoToAttach.getClass(),
						transaccionformadepagoListNewTransaccionformadepagoToAttach.getId());
				attachedTransaccionformadepagoListNew.add(transaccionformadepagoListNewTransaccionformadepagoToAttach);
			}
			transaccionformadepagoListNew = attachedTransaccionformadepagoListNew;
			moneda.setTransaccionformadepagoList(transaccionformadepagoListNew);
			moneda = em.merge(moneda);
			for (Monedadenominacion monedadenominacionListNewMonedadenominacion : monedadenominacionListNew) {
				if (!monedadenominacionListOld.contains(monedadenominacionListNewMonedadenominacion)) {
					Moneda oldIdMonedaOfMonedadenominacionListNewMonedadenominacion = monedadenominacionListNewMonedadenominacion
							.getIdMoneda();
					monedadenominacionListNewMonedadenominacion.setIdMoneda(moneda);
					monedadenominacionListNewMonedadenominacion = em.merge(monedadenominacionListNewMonedadenominacion);
					if (oldIdMonedaOfMonedadenominacionListNewMonedadenominacion != null
							&& !oldIdMonedaOfMonedadenominacionListNewMonedadenominacion.equals(moneda)) {
						oldIdMonedaOfMonedadenominacionListNewMonedadenominacion.getMonedadenominacionList().remove(
								monedadenominacionListNewMonedadenominacion);
						oldIdMonedaOfMonedadenominacionListNewMonedadenominacion = em
								.merge(oldIdMonedaOfMonedadenominacionListNewMonedadenominacion);
					}
				}
			}
			for (Monedatasacambio monedatasacambioListNewMonedatasacambio : monedatasacambioListNew) {
				if (!monedatasacambioListOld.contains(monedatasacambioListNewMonedatasacambio)) {
					Moneda oldIdMonedaOfMonedatasacambioListNewMonedatasacambio = monedatasacambioListNewMonedatasacambio
							.getIdMoneda();
					monedatasacambioListNewMonedatasacambio.setIdMoneda(moneda);
					monedatasacambioListNewMonedatasacambio = em.merge(monedatasacambioListNewMonedatasacambio);
					if (oldIdMonedaOfMonedatasacambioListNewMonedatasacambio != null
							&& !oldIdMonedaOfMonedatasacambioListNewMonedatasacambio.equals(moneda)) {
						oldIdMonedaOfMonedatasacambioListNewMonedatasacambio.getMonedatasacambioList().remove(
								monedatasacambioListNewMonedatasacambio);
						oldIdMonedaOfMonedatasacambioListNewMonedatasacambio = em
								.merge(oldIdMonedaOfMonedatasacambioListNewMonedatasacambio);
					}
				}
			}
			for (Formadepagomoneda formadepagomonedaListNewFormadepagomoneda : formadepagomonedaListNew) {
				if (!formadepagomonedaListOld.contains(formadepagomonedaListNewFormadepagomoneda)) {
					Moneda oldIdMonedaOfFormadepagomonedaListNewFormadepagomoneda = formadepagomonedaListNewFormadepagomoneda
							.getIdMoneda();
					formadepagomonedaListNewFormadepagomoneda.setIdMoneda(moneda);
					formadepagomonedaListNewFormadepagomoneda = em.merge(formadepagomonedaListNewFormadepagomoneda);
					if (oldIdMonedaOfFormadepagomonedaListNewFormadepagomoneda != null
							&& !oldIdMonedaOfFormadepagomonedaListNewFormadepagomoneda.equals(moneda)) {
						oldIdMonedaOfFormadepagomonedaListNewFormadepagomoneda.getFormadepagomonedaList().remove(
								formadepagomonedaListNewFormadepagomoneda);
						oldIdMonedaOfFormadepagomonedaListNewFormadepagomoneda = em
								.merge(oldIdMonedaOfFormadepagomonedaListNewFormadepagomoneda);
					}
				}
			}
			for (Transaccionformadepago transaccionformadepagoListNewTransaccionformadepago : transaccionformadepagoListNew) {
				if (!transaccionformadepagoListOld.contains(transaccionformadepagoListNewTransaccionformadepago)) {
					Moneda oldIdMonedaOfTransaccionformadepagoListNewTransaccionformadepago = transaccionformadepagoListNewTransaccionformadepago
							.getIdMoneda();
					transaccionformadepagoListNewTransaccionformadepago.setIdMoneda(moneda);
					transaccionformadepagoListNewTransaccionformadepago = em
							.merge(transaccionformadepagoListNewTransaccionformadepago);
					if (oldIdMonedaOfTransaccionformadepagoListNewTransaccionformadepago != null
							&& !oldIdMonedaOfTransaccionformadepagoListNewTransaccionformadepago.equals(moneda)) {
						oldIdMonedaOfTransaccionformadepagoListNewTransaccionformadepago
								.getTransaccionformadepagoList().remove(
										transaccionformadepagoListNewTransaccionformadepago);
						oldIdMonedaOfTransaccionformadepagoListNewTransaccionformadepago = em
								.merge(oldIdMonedaOfTransaccionformadepagoListNewTransaccionformadepago);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = moneda.getId();
				if (findMoneda(id) == null) {
					throw new NonexistentEntityException("The moneda with id " + id + " no longer exists.");
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
	public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Moneda moneda;
			try {
				moneda = em.getReference(Moneda.class, id);
				moneda.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The moneda with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Monedadenominacion> monedadenominacionListOrphanCheck = moneda.getMonedadenominacionList();
			for (Monedadenominacion monedadenominacionListOrphanCheckMonedadenominacion : monedadenominacionListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Moneda (" + moneda
						+ ") cannot be destroyed since the Monedadenominacion "
						+ monedadenominacionListOrphanCheckMonedadenominacion
						+ " in its monedadenominacionList field has a non-nullable idMoneda field.");
			}
			List<Monedatasacambio> monedatasacambioListOrphanCheck = moneda.getMonedatasacambioList();
			for (Monedatasacambio monedatasacambioListOrphanCheckMonedatasacambio : monedatasacambioListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Moneda (" + moneda
						+ ") cannot be destroyed since the Monedatasacambio "
						+ monedatasacambioListOrphanCheckMonedatasacambio
						+ " in its monedatasacambioList field has a non-nullable idMoneda field.");
			}
			List<Formadepagomoneda> formadepagomonedaListOrphanCheck = moneda.getFormadepagomonedaList();
			for (Formadepagomoneda formadepagomonedaListOrphanCheckFormadepagomoneda : formadepagomonedaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Moneda (" + moneda
						+ ") cannot be destroyed since the Formadepagomoneda "
						+ formadepagomonedaListOrphanCheckFormadepagomoneda
						+ " in its formadepagomonedaList field has a non-nullable idMoneda field.");
			}
			List<Transaccionformadepago> transaccionformadepagoListOrphanCheck = moneda.getTransaccionformadepagoList();
			for (Transaccionformadepago transaccionformadepagoListOrphanCheckTransaccionformadepago : transaccionformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Moneda (" + moneda
						+ ") cannot be destroyed since the Transaccionformadepago "
						+ transaccionformadepagoListOrphanCheckTransaccionformadepago
						+ " in its transaccionformadepagoList field has a non-nullable idMoneda field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(moneda);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findMonedaEntities.
	 * 
	 * @return List<Moneda>
	 */
	public List<Moneda> findMonedaEntities() {
		return findMonedaEntities(true, -1, -1);
	}

	/**
	 * Method findMonedaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Moneda>
	 */
	public List<Moneda> findMonedaEntities(int maxResults, int firstResult) {
		return findMonedaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findMonedaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Moneda>
	 */
	private List<Moneda> findMonedaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Moneda.class));
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
	 * Method findMoneda.
	 * 
	 * @param id
	 *            Long
	 * @return Moneda
	 */
	public Moneda findMoneda(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Moneda.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getMonedaCount.
	 * 
	 * @return int
	 */
	public int getMonedaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Moneda> rt = cq.from(Moneda.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
