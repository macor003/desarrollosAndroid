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
public class CategorialinearetencionJpaController implements Serializable {

	/**
	 * Constructor for CategorialinearetencionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public CategorialinearetencionJpaController(EntityManagerFactory emf) {
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
	 * @param categorialinearetencion
	 *            Categorialinearetencion
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Categorialinearetencion categorialinearetencion)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
//			Formadepago idFormadepago = categorialinearetencion
//					.getIdFormadepago();
//			if (idFormadepago != null) {
//				idFormadepago = em.getReference(idFormadepago.getClass(),
//						idFormadepago.getId());
//				categorialinearetencion.setIdFormadepago(idFormadepago);
//			}
			Linea idLinea = categorialinearetencion.getIdLinea();
			if (idLinea != null) {
				idLinea = em.getReference(idLinea.getClass(), idLinea.getId());
				categorialinearetencion.setIdLinea(idLinea);
			}
			em.persist(categorialinearetencion);
//			if (idFormadepago != null) {
//				idFormadepago.getCategorialinearetencionList().add(
//						categorialinearetencion);
//				idFormadepago = em.merge(idFormadepago);
//			}
			if (idLinea != null) {
				idLinea.getCategorialinearetencionList().add(
						categorialinearetencion);
				idLinea = em.merge(idLinea);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findCategorialinearetencion(categorialinearetencion.getId()) != null) {
				throw new PreexistingEntityException("Categorialinearetencion "
						+ categorialinearetencion + " already exists.", ex);
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
	 * @param categorialinearetencion
	 *            Categorialinearetencion
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Categorialinearetencion categorialinearetencion)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Categorialinearetencion persistentCategorialinearetencion = em
					.find(Categorialinearetencion.class,
							categorialinearetencion.getId());
//			Formadepago idFormadepagoOld = persistentCategorialinearetencion
//					.getIdFormadepago();
//			Formadepago idFormadepagoNew = categorialinearetencion
//					.getIdFormadepago();
			Linea idLineaOld = persistentCategorialinearetencion.getIdLinea();
			Linea idLineaNew = categorialinearetencion.getIdLinea();
//			if (idFormadepagoNew != null) {
//				idFormadepagoNew = em.getReference(idFormadepagoNew.getClass(),
//						idFormadepagoNew.getId());
//				categorialinearetencion.setIdFormadepago(idFormadepagoNew);
//			}
			if (idLineaNew != null) {
				idLineaNew = em.getReference(idLineaNew.getClass(),
						idLineaNew.getId());
				categorialinearetencion.setIdLinea(idLineaNew);
			}
			categorialinearetencion = em.merge(categorialinearetencion);
//			if (idFormadepagoOld != null
//					&& !idFormadepagoOld.equals(idFormadepagoNew)) {
//				idFormadepagoOld.getCategorialinearetencionList().remove(
//						categorialinearetencion);
//				idFormadepagoOld = em.merge(idFormadepagoOld);
//			}
//			if (idFormadepagoNew != null
//					&& !idFormadepagoNew.equals(idFormadepagoOld)) {
//				idFormadepagoNew.getCategorialinearetencionList().add(
//						categorialinearetencion);
//				idFormadepagoNew = em.merge(idFormadepagoNew);
//			}
			if (idLineaOld != null && !idLineaOld.equals(idLineaNew)) {
				idLineaOld.getCategorialinearetencionList().remove(
						categorialinearetencion);
				idLineaOld = em.merge(idLineaOld);
			}
			if (idLineaNew != null && !idLineaNew.equals(idLineaOld)) {
				idLineaNew.getCategorialinearetencionList().add(
						categorialinearetencion);
				idLineaNew = em.merge(idLineaNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = categorialinearetencion.getId();
				if (findCategorialinearetencion(id) == null) {
					throw new NonexistentEntityException(
							"The categorialinearetencion with id " + id
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
			Categorialinearetencion categorialinearetencion;
			try {
				categorialinearetencion = em.getReference(
						Categorialinearetencion.class, id);
				categorialinearetencion.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The categorialinearetencion with id " + id
								+ " no longer exists.", enfe);
			}
//			Formadepago idFormadepago = categorialinearetencion
//					.getIdFormadepago();
//			if (idFormadepago != null) {
//				idFormadepago.getCategorialinearetencionList().remove(
//						categorialinearetencion);
//				idFormadepago = em.merge(idFormadepago);
//			}
			Linea idLinea = categorialinearetencion.getIdLinea();
			if (idLinea != null) {
				idLinea.getCategorialinearetencionList().remove(
						categorialinearetencion);
				idLinea = em.merge(idLinea);
			}
			em.remove(categorialinearetencion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findCategorialinearetencionEntities.
	 * 
	 * @return List<Categorialinearetencion>
	 */
	public List<Categorialinearetencion> findCategorialinearetencionEntities() {
		return findCategorialinearetencionEntities(true, -1, -1);
	}

	/**
	 * Method findCategorialinearetencionEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Categorialinearetencion>
	 */
	public List<Categorialinearetencion> findCategorialinearetencionEntities(
			int maxResults, int firstResult) {
		return findCategorialinearetencionEntities(false, maxResults,
				firstResult);
	}

	/**
	 * Method findCategorialinearetencionEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Categorialinearetencion>
	 */
	private List<Categorialinearetencion> findCategorialinearetencionEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Categorialinearetencion.class));
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
	 * Method findCategorialinearetencion.
	 * 
	 * @param id
	 *            Long
	 * @return Categorialinearetencion
	 */
	public Categorialinearetencion findCategorialinearetencion(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Categorialinearetencion.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getCategorialinearetencionCount.
	 * 
	 * @return int
	 */
	public int getCategorialinearetencionCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Categorialinearetencion> rt = cq
					.from(Categorialinearetencion.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
