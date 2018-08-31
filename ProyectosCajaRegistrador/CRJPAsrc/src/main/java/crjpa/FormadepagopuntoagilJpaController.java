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
public class FormadepagopuntoagilJpaController implements Serializable {

	/**
	 * Constructor for FormadepagopuntoagilJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public FormadepagopuntoagilJpaController(EntityManagerFactory emf) {
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
	 * @param formadepagopuntoagil
	 *            Formadepagopuntoagil
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Formadepagopuntoagil formadepagopuntoagil) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Transaccionformadepago idTransaccionformadepago = formadepagopuntoagil.getIdTransaccionformadepago();
			if (idTransaccionformadepago != null) {
				idTransaccionformadepago = em.getReference(idTransaccionformadepago.getClass(),
						idTransaccionformadepago.getId());
				formadepagopuntoagil.setIdTransaccionformadepago(idTransaccionformadepago);
			}
			em.persist(formadepagopuntoagil);
			if (idTransaccionformadepago != null) {
				idTransaccionformadepago.getFormadepagopuntoagilList().add(formadepagopuntoagil);
				idTransaccionformadepago = em.merge(idTransaccionformadepago);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findFormadepagopuntoagil(formadepagopuntoagil.getId()) != null) {
				throw new PreexistingEntityException("Formadepagopuntoagil " + formadepagopuntoagil
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
	 * @param formadepagopuntoagil
	 *            Formadepagopuntoagil
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Formadepagopuntoagil formadepagopuntoagil) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Formadepagopuntoagil persistentFormadepagopuntoagil = em.find(Formadepagopuntoagil.class,
					formadepagopuntoagil.getId());
			Transaccionformadepago idTransaccionformadepagoOld = persistentFormadepagopuntoagil
					.getIdTransaccionformadepago();
			Transaccionformadepago idTransaccionformadepagoNew = formadepagopuntoagil.getIdTransaccionformadepago();
			if (idTransaccionformadepagoNew != null) {
				idTransaccionformadepagoNew = em.getReference(idTransaccionformadepagoNew.getClass(),
						idTransaccionformadepagoNew.getId());
				formadepagopuntoagil.setIdTransaccionformadepago(idTransaccionformadepagoNew);
			}
			formadepagopuntoagil = em.merge(formadepagopuntoagil);
			if (idTransaccionformadepagoOld != null && !idTransaccionformadepagoOld.equals(idTransaccionformadepagoNew)) {
				idTransaccionformadepagoOld.getFormadepagopuntoagilList().remove(formadepagopuntoagil);
				idTransaccionformadepagoOld = em.merge(idTransaccionformadepagoOld);
			}
			if (idTransaccionformadepagoNew != null && !idTransaccionformadepagoNew.equals(idTransaccionformadepagoOld)) {
				idTransaccionformadepagoNew.getFormadepagopuntoagilList().add(formadepagopuntoagil);
				idTransaccionformadepagoNew = em.merge(idTransaccionformadepagoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = formadepagopuntoagil.getId();
				if (findFormadepagopuntoagil(id) == null) {
					throw new NonexistentEntityException("The formadepagopuntoagil with id " + id
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
			Formadepagopuntoagil formadepagopuntoagil;
			try {
				formadepagopuntoagil = em.getReference(Formadepagopuntoagil.class, id);
				formadepagopuntoagil.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The formadepagopuntoagil with id " + id + " no longer exists.",
						enfe);
			}
			Transaccionformadepago idTransaccionformadepago = formadepagopuntoagil.getIdTransaccionformadepago();
			if (idTransaccionformadepago != null) {
				idTransaccionformadepago.getFormadepagopuntoagilList().remove(formadepagopuntoagil);
				idTransaccionformadepago = em.merge(idTransaccionformadepago);
			}
			em.remove(formadepagopuntoagil);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findFormadepagopuntoagilEntities.
	 * 
	 * @return List<Formadepagopuntoagil>
	 */
	public List<Formadepagopuntoagil> findFormadepagopuntoagilEntities() {
		return findFormadepagopuntoagilEntities(true, -1, -1);
	}

	/**
	 * Method findFormadepagopuntoagilEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Formadepagopuntoagil>
	 */
	public List<Formadepagopuntoagil> findFormadepagopuntoagilEntities(int maxResults, int firstResult) {
		return findFormadepagopuntoagilEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findFormadepagopuntoagilEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Formadepagopuntoagil>
	 */
	private List<Formadepagopuntoagil> findFormadepagopuntoagilEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Formadepagopuntoagil.class));
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
	 * Method findFormadepagopuntoagil.
	 * 
	 * @param id
	 *            Long
	 * @return Formadepagopuntoagil
	 */
	public Formadepagopuntoagil findFormadepagopuntoagil(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Formadepagopuntoagil.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getFormadepagopuntoagilCount.
	 * 
	 * @return int
	 */
	public int getFormadepagopuntoagilCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Formadepagopuntoagil> rt = cq.from(Formadepagopuntoagil.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
