
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
public class FormadepagomonedaJpaController implements Serializable {

	/**
	 * Constructor for FormadepagomonedaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public FormadepagomonedaJpaController(EntityManagerFactory emf) {
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
	 * @param formadepagomoneda
	 *            Formadepagomoneda
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Formadepagomoneda formadepagomoneda) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Moneda idMoneda = formadepagomoneda.getIdMoneda();
			if (idMoneda != null) {
				idMoneda = em.getReference(idMoneda.getClass(), idMoneda.getId());
				formadepagomoneda.setIdMoneda(idMoneda);
			}
			Formadepago idFormadepago = formadepagomoneda.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago = em.getReference(idFormadepago.getClass(), idFormadepago.getId());
				formadepagomoneda.setIdFormadepago(idFormadepago);
			}
			em.persist(formadepagomoneda);
			if (idMoneda != null) {
				idMoneda.getFormadepagomonedaList().add(formadepagomoneda);
				idMoneda = em.merge(idMoneda);
			}
			if (idFormadepago != null) {
				idFormadepago.getFormadepagomonedaList().add(formadepagomoneda);
				idFormadepago = em.merge(idFormadepago);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findFormadepagomoneda(formadepagomoneda.getId()) != null) {
				throw new PreexistingEntityException("Formadepagomoneda " + formadepagomoneda + " already exists.", ex);
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
	 * @param formadepagomoneda
	 *            Formadepagomoneda
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Formadepagomoneda formadepagomoneda) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Formadepagomoneda persistentFormadepagomoneda = em.find(Formadepagomoneda.class, formadepagomoneda.getId());
			Moneda idMonedaOld = persistentFormadepagomoneda.getIdMoneda();
			Moneda idMonedaNew = formadepagomoneda.getIdMoneda();
			Formadepago idFormadepagoOld = persistentFormadepagomoneda.getIdFormadepago();
			Formadepago idFormadepagoNew = formadepagomoneda.getIdFormadepago();
			if (idMonedaNew != null) {
				idMonedaNew = em.getReference(idMonedaNew.getClass(), idMonedaNew.getId());
				formadepagomoneda.setIdMoneda(idMonedaNew);
			}
			if (idFormadepagoNew != null) {
				idFormadepagoNew = em.getReference(idFormadepagoNew.getClass(), idFormadepagoNew.getId());
				formadepagomoneda.setIdFormadepago(idFormadepagoNew);
			}
			formadepagomoneda = em.merge(formadepagomoneda);
			if (idMonedaOld != null && !idMonedaOld.equals(idMonedaNew)) {
				idMonedaOld.getFormadepagomonedaList().remove(formadepagomoneda);
				idMonedaOld = em.merge(idMonedaOld);
			}
			if (idMonedaNew != null && !idMonedaNew.equals(idMonedaOld)) {
				idMonedaNew.getFormadepagomonedaList().add(formadepagomoneda);
				idMonedaNew = em.merge(idMonedaNew);
			}
			if (idFormadepagoOld != null && !idFormadepagoOld.equals(idFormadepagoNew)) {
				idFormadepagoOld.getFormadepagomonedaList().remove(formadepagomoneda);
				idFormadepagoOld = em.merge(idFormadepagoOld);
			}
			if (idFormadepagoNew != null && !idFormadepagoNew.equals(idFormadepagoOld)) {
				idFormadepagoNew.getFormadepagomonedaList().add(formadepagomoneda);
				idFormadepagoNew = em.merge(idFormadepagoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = formadepagomoneda.getId();
				if (findFormadepagomoneda(id) == null) {
					throw new NonexistentEntityException("The formadepagomoneda with id " + id + " no longer exists.");
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
			Formadepagomoneda formadepagomoneda;
			try {
				formadepagomoneda = em.getReference(Formadepagomoneda.class, id);
				formadepagomoneda.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The formadepagomoneda with id " + id + " no longer exists.", enfe);
			}
			Moneda idMoneda = formadepagomoneda.getIdMoneda();
			if (idMoneda != null) {
				idMoneda.getFormadepagomonedaList().remove(formadepagomoneda);
				idMoneda = em.merge(idMoneda);
			}
			Formadepago idFormadepago = formadepagomoneda.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago.getFormadepagomonedaList().remove(formadepagomoneda);
				idFormadepago = em.merge(idFormadepago);
			}
			em.remove(formadepagomoneda);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findFormadepagomonedaEntities.
	 * 
	 * @return List<Formadepagomoneda>
	 */
	public List<Formadepagomoneda> findFormadepagomonedaEntities() {
		return findFormadepagomonedaEntities(true, -1, -1);
	}

	/**
	 * Method findFormadepagomonedaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Formadepagomoneda>
	 */
	public List<Formadepagomoneda> findFormadepagomonedaEntities(int maxResults, int firstResult) {
		return findFormadepagomonedaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findFormadepagomonedaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Formadepagomoneda>
	 */
	private List<Formadepagomoneda> findFormadepagomonedaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Formadepagomoneda.class));
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
	 * Method findFormadepagomoneda.
	 * 
	 * @param id
	 *            Long
	 * @return Formadepagomoneda
	 */
	public Formadepagomoneda findFormadepagomoneda(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Formadepagomoneda.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getFormadepagomonedaCount.
	 * 
	 * @return int
	 */
	public int getFormadepagomonedaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Formadepagomoneda> rt = cq.from(Formadepagomoneda.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
