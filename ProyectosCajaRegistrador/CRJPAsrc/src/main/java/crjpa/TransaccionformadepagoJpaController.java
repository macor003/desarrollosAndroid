/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
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
public class TransaccionformadepagoJpaController implements Serializable {

	/**
	 * Constructor for TransaccionformadepagoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TransaccionformadepagoJpaController(EntityManagerFactory emf) {
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
	 * @param transaccionformadepago
	 *            Transaccionformadepago
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Transaccionformadepago transaccionformadepago) throws PreexistingEntityException, Exception {
		if (transaccionformadepago.getFormadepagopuntoagilList() == null) {
			transaccionformadepago.setFormadepagopuntoagilList(new ArrayList<Formadepagopuntoagil>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Moneda idMoneda = transaccionformadepago.getIdMoneda();
			if (idMoneda != null) {
				idMoneda = em.getReference(idMoneda.getClass(), idMoneda.getId());
				transaccionformadepago.setIdMoneda(idMoneda);
			}
			Formadepago idFormadepago = transaccionformadepago.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago = em.getReference(idFormadepago.getClass(), idFormadepago.getId());
				transaccionformadepago.setIdFormadepago(idFormadepago);
			}
			Transaccion idTransaccion = transaccionformadepago.getIdTransaccion();
			if (idTransaccion != null) {
				idTransaccion = em.getReference(idTransaccion.getClass(), idTransaccion.getId());
				transaccionformadepago.setIdTransaccion(idTransaccion);
			}
			List<Formadepagopuntoagil> attachedFormadepagopuntoagilList = new ArrayList<Formadepagopuntoagil>();
			for (Formadepagopuntoagil formadepagopuntoagilListFormadepagopuntoagilToAttach : transaccionformadepago
					.getFormadepagopuntoagilList()) {
				formadepagopuntoagilListFormadepagopuntoagilToAttach = em.getReference(
						formadepagopuntoagilListFormadepagopuntoagilToAttach.getClass(),
						formadepagopuntoagilListFormadepagopuntoagilToAttach.getId());
				attachedFormadepagopuntoagilList.add(formadepagopuntoagilListFormadepagopuntoagilToAttach);
			}
			transaccionformadepago.setFormadepagopuntoagilList(attachedFormadepagopuntoagilList);
			em.persist(transaccionformadepago);
			if (idMoneda != null) {
				idMoneda.getTransaccionformadepagoList().add(transaccionformadepago);
				idMoneda = em.merge(idMoneda);
			}
			if (idFormadepago != null) {
				idFormadepago.getTransaccionformadepagoList().add(transaccionformadepago);
				idFormadepago = em.merge(idFormadepago);
			}
			if (idTransaccion != null) {
				idTransaccion.getTransaccionformadepagoList().add(transaccionformadepago);
				idTransaccion = em.merge(idTransaccion);
			}
			for (Formadepagopuntoagil formadepagopuntoagilListFormadepagopuntoagil : transaccionformadepago
					.getFormadepagopuntoagilList()) {
				Transaccionformadepago oldIdTransaccionformadepagoOfFormadepagopuntoagilListFormadepagopuntoagil = formadepagopuntoagilListFormadepagopuntoagil
						.getIdTransaccionformadepago();
				formadepagopuntoagilListFormadepagopuntoagil.setIdTransaccionformadepago(transaccionformadepago);
				formadepagopuntoagilListFormadepagopuntoagil = em.merge(formadepagopuntoagilListFormadepagopuntoagil);
				if (oldIdTransaccionformadepagoOfFormadepagopuntoagilListFormadepagopuntoagil != null) {
					oldIdTransaccionformadepagoOfFormadepagopuntoagilListFormadepagopuntoagil
							.getFormadepagopuntoagilList().remove(formadepagopuntoagilListFormadepagopuntoagil);
					oldIdTransaccionformadepagoOfFormadepagopuntoagilListFormadepagopuntoagil = em
							.merge(oldIdTransaccionformadepagoOfFormadepagopuntoagilListFormadepagopuntoagil);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTransaccionformadepago(transaccionformadepago.getId()) != null) {
				throw new PreexistingEntityException("Transaccionformadepago " + transaccionformadepago
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
	 * @param transaccionformadepago
	 *            Transaccionformadepago
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Transaccionformadepago transaccionformadepago) throws IllegalOrphanException,
			NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Transaccionformadepago persistentTransaccionformadepago = em.find(Transaccionformadepago.class,
					transaccionformadepago.getId());
			Moneda idMonedaOld = persistentTransaccionformadepago.getIdMoneda();
			Moneda idMonedaNew = transaccionformadepago.getIdMoneda();
			Formadepago idFormadepagoOld = persistentTransaccionformadepago.getIdFormadepago();
			Formadepago idFormadepagoNew = transaccionformadepago.getIdFormadepago();
			Transaccion idTransaccionOld = persistentTransaccionformadepago.getIdTransaccion();
			Transaccion idTransaccionNew = transaccionformadepago.getIdTransaccion();
			List<Formadepagopuntoagil> formadepagopuntoagilListOld = persistentTransaccionformadepago
					.getFormadepagopuntoagilList();
			List<Formadepagopuntoagil> formadepagopuntoagilListNew = transaccionformadepago
					.getFormadepagopuntoagilList();
			List<String> illegalOrphanMessages = null;
			for (Formadepagopuntoagil formadepagopuntoagilListOldFormadepagopuntoagil : formadepagopuntoagilListOld) {
				if (!formadepagopuntoagilListNew.contains(formadepagopuntoagilListOldFormadepagopuntoagil)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Formadepagopuntoagil "
							+ formadepagopuntoagilListOldFormadepagopuntoagil
							+ " since its idTransaccionformadepago field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idMonedaNew != null) {
				idMonedaNew = em.getReference(idMonedaNew.getClass(), idMonedaNew.getId());
				transaccionformadepago.setIdMoneda(idMonedaNew);
			}
			if (idFormadepagoNew != null) {
				idFormadepagoNew = em.getReference(idFormadepagoNew.getClass(), idFormadepagoNew.getId());
				transaccionformadepago.setIdFormadepago(idFormadepagoNew);
			}
			if (idTransaccionNew != null) {
				idTransaccionNew = em.getReference(idTransaccionNew.getClass(), idTransaccionNew.getId());
				transaccionformadepago.setIdTransaccion(idTransaccionNew);
			}
			List<Formadepagopuntoagil> attachedFormadepagopuntoagilListNew = new ArrayList<Formadepagopuntoagil>();
			for (Formadepagopuntoagil formadepagopuntoagilListNewFormadepagopuntoagilToAttach : formadepagopuntoagilListNew) {
				formadepagopuntoagilListNewFormadepagopuntoagilToAttach = em.getReference(
						formadepagopuntoagilListNewFormadepagopuntoagilToAttach.getClass(),
						formadepagopuntoagilListNewFormadepagopuntoagilToAttach.getId());
				attachedFormadepagopuntoagilListNew.add(formadepagopuntoagilListNewFormadepagopuntoagilToAttach);
			}
			formadepagopuntoagilListNew = attachedFormadepagopuntoagilListNew;
			transaccionformadepago.setFormadepagopuntoagilList(formadepagopuntoagilListNew);
			transaccionformadepago = em.merge(transaccionformadepago);
			if (idMonedaOld != null && !idMonedaOld.equals(idMonedaNew)) {
				idMonedaOld.getTransaccionformadepagoList().remove(transaccionformadepago);
				idMonedaOld = em.merge(idMonedaOld);
			}
			if (idMonedaNew != null && !idMonedaNew.equals(idMonedaOld)) {
				idMonedaNew.getTransaccionformadepagoList().add(transaccionformadepago);
				idMonedaNew = em.merge(idMonedaNew);
			}
			if (idFormadepagoOld != null && !idFormadepagoOld.equals(idFormadepagoNew)) {
				idFormadepagoOld.getTransaccionformadepagoList().remove(transaccionformadepago);
				idFormadepagoOld = em.merge(idFormadepagoOld);
			}
			if (idFormadepagoNew != null && !idFormadepagoNew.equals(idFormadepagoOld)) {
				idFormadepagoNew.getTransaccionformadepagoList().add(transaccionformadepago);
				idFormadepagoNew = em.merge(idFormadepagoNew);
			}
			if (idTransaccionOld != null && !idTransaccionOld.equals(idTransaccionNew)) {
				idTransaccionOld.getTransaccionformadepagoList().remove(transaccionformadepago);
				idTransaccionOld = em.merge(idTransaccionOld);
			}
			if (idTransaccionNew != null && !idTransaccionNew.equals(idTransaccionOld)) {
				idTransaccionNew.getTransaccionformadepagoList().add(transaccionformadepago);
				idTransaccionNew = em.merge(idTransaccionNew);
			}
			for (Formadepagopuntoagil formadepagopuntoagilListNewFormadepagopuntoagil : formadepagopuntoagilListNew) {
				if (!formadepagopuntoagilListOld.contains(formadepagopuntoagilListNewFormadepagopuntoagil)) {
					Transaccionformadepago oldIdTransaccionformadepagoOfFormadepagopuntoagilListNewFormadepagopuntoagil = formadepagopuntoagilListNewFormadepagopuntoagil
							.getIdTransaccionformadepago();
					formadepagopuntoagilListNewFormadepagopuntoagil.setIdTransaccionformadepago(transaccionformadepago);
					formadepagopuntoagilListNewFormadepagopuntoagil = em
							.merge(formadepagopuntoagilListNewFormadepagopuntoagil);
					if (oldIdTransaccionformadepagoOfFormadepagopuntoagilListNewFormadepagopuntoagil != null
							&& !oldIdTransaccionformadepagoOfFormadepagopuntoagilListNewFormadepagopuntoagil
									.equals(transaccionformadepago)) {
						oldIdTransaccionformadepagoOfFormadepagopuntoagilListNewFormadepagopuntoagil
								.getFormadepagopuntoagilList().remove(formadepagopuntoagilListNewFormadepagopuntoagil);
						oldIdTransaccionformadepagoOfFormadepagopuntoagilListNewFormadepagopuntoagil = em
								.merge(oldIdTransaccionformadepagoOfFormadepagopuntoagilListNewFormadepagopuntoagil);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = transaccionformadepago.getId();
				if (findTransaccionformadepago(id) == null) {
					throw new NonexistentEntityException("The transaccionformadepago with id " + id
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
	public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Transaccionformadepago transaccionformadepago;
			try {
				transaccionformadepago = em.getReference(Transaccionformadepago.class, id);
				transaccionformadepago.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The transaccionformadepago with id " + id + " no longer exists.",
						enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Formadepagopuntoagil> formadepagopuntoagilListOrphanCheck = transaccionformadepago
					.getFormadepagopuntoagilList();
			for (Formadepagopuntoagil formadepagopuntoagilListOrphanCheckFormadepagopuntoagil : formadepagopuntoagilListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Transaccionformadepago (" + transaccionformadepago
						+ ") cannot be destroyed since the Formadepagopuntoagil "
						+ formadepagopuntoagilListOrphanCheckFormadepagopuntoagil
						+ " in its formadepagopuntoagilList field has a non-nullable idTransaccionformadepago field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Moneda idMoneda = transaccionformadepago.getIdMoneda();
			if (idMoneda != null) {
				idMoneda.getTransaccionformadepagoList().remove(transaccionformadepago);
				idMoneda = em.merge(idMoneda);
			}
			Formadepago idFormadepago = transaccionformadepago.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago.getTransaccionformadepagoList().remove(transaccionformadepago);
				idFormadepago = em.merge(idFormadepago);
			}
			Transaccion idTransaccion = transaccionformadepago.getIdTransaccion();
			if (idTransaccion != null) {
				idTransaccion.getTransaccionformadepagoList().remove(transaccionformadepago);
				idTransaccion = em.merge(idTransaccion);
			}
			em.remove(transaccionformadepago);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTransaccionformadepagoEntities.
	 * 
	 * @return List<Transaccionformadepago>
	 */
	public List<Transaccionformadepago> findTransaccionformadepagoEntities() {
		return findTransaccionformadepagoEntities(true, -1, -1);
	}

	/**
	 * Method findTransaccionformadepagoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transaccionformadepago>
	 */
	public List<Transaccionformadepago> findTransaccionformadepagoEntities(int maxResults, int firstResult) {
		return findTransaccionformadepagoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTransaccionformadepagoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transaccionformadepago>
	 */
	private List<Transaccionformadepago> findTransaccionformadepagoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Transaccionformadepago.class));
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
	 * Method findTransaccionformadepago.
	 * 
	 * @param id
	 *            Long
	 * @return Transaccionformadepago
	 */
	public Transaccionformadepago findTransaccionformadepago(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Transaccionformadepago.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTransaccionformadepagoCount.
	 * 
	 * @return int
	 */
	public int getTransaccionformadepagoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Transaccionformadepago> rt = cq.from(Transaccionformadepago.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
