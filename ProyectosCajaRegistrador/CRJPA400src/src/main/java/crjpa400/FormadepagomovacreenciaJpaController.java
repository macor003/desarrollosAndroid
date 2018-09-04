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
public class FormadepagomovacreenciaJpaController implements Serializable {

	/**
	 * Constructor for FormadepagomovacreenciaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public FormadepagomovacreenciaJpaController(EntityManagerFactory emf) {
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
	 * @param formadepagomovacreencia
	 *            Formadepagomovacreencia
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Formadepagomovacreencia formadepagomovacreencia)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Moneda idMoneda = formadepagomovacreencia.getIdMoneda();
			if (idMoneda != null) {
				idMoneda = em.getReference(idMoneda.getClass(),
						idMoneda.getId());
				formadepagomovacreencia.setIdMoneda(idMoneda);
			}
			Movimientoacreencia idMovimientoacreencia = formadepagomovacreencia
					.getIdMovimientoacreencia();
			if (idMovimientoacreencia != null) {
				idMovimientoacreencia = em.getReference(
						idMovimientoacreencia.getClass(),
						idMovimientoacreencia.getId());
				formadepagomovacreencia
						.setIdMovimientoacreencia(idMovimientoacreencia);
			}
			Formadepago idFormadepago = formadepagomovacreencia
					.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago = em.getReference(idFormadepago.getClass(),
						idFormadepago.getId());
				formadepagomovacreencia.setIdFormadepago(idFormadepago);
			}
			em.persist(formadepagomovacreencia);
			if (idMoneda != null) {
				idMoneda.getFormadepagomovacreenciaList().add(
						formadepagomovacreencia);
				idMoneda = em.merge(idMoneda);
			}
			if (idMovimientoacreencia != null) {
				idMovimientoacreencia.getFormadepagomovacreenciaList().add(
						formadepagomovacreencia);
				idMovimientoacreencia = em.merge(idMovimientoacreencia);
			}
			if (idFormadepago != null) {
				idFormadepago.getFormadepagomovacreenciaList().add(
						formadepagomovacreencia);
				idFormadepago = em.merge(idFormadepago);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findFormadepagomovacreencia(formadepagomovacreencia.getId()) != null) {
				throw new PreexistingEntityException("Formadepagomovacreencia "
						+ formadepagomovacreencia + " already exists.", ex);
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
	 * @param formadepagomovacreencia
	 *            Formadepagomovacreencia
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Formadepagomovacreencia formadepagomovacreencia)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Formadepagomovacreencia persistentFormadepagomovacreencia = em
					.find(Formadepagomovacreencia.class,
							formadepagomovacreencia.getId());
			Moneda idMonedaOld = persistentFormadepagomovacreencia
					.getIdMoneda();
			Moneda idMonedaNew = formadepagomovacreencia.getIdMoneda();
			Movimientoacreencia idMovimientoacreenciaOld = persistentFormadepagomovacreencia
					.getIdMovimientoacreencia();
			Movimientoacreencia idMovimientoacreenciaNew = formadepagomovacreencia
					.getIdMovimientoacreencia();
			Formadepago idFormadepagoOld = persistentFormadepagomovacreencia
					.getIdFormadepago();
			Formadepago idFormadepagoNew = formadepagomovacreencia
					.getIdFormadepago();
			if (idMonedaNew != null) {
				idMonedaNew = em.getReference(idMonedaNew.getClass(),
						idMonedaNew.getId());
				formadepagomovacreencia.setIdMoneda(idMonedaNew);
			}
			if (idMovimientoacreenciaNew != null) {
				idMovimientoacreenciaNew = em.getReference(
						idMovimientoacreenciaNew.getClass(),
						idMovimientoacreenciaNew.getId());
				formadepagomovacreencia
						.setIdMovimientoacreencia(idMovimientoacreenciaNew);
			}
			if (idFormadepagoNew != null) {
				idFormadepagoNew = em.getReference(idFormadepagoNew.getClass(),
						idFormadepagoNew.getId());
				formadepagomovacreencia.setIdFormadepago(idFormadepagoNew);
			}
			formadepagomovacreencia = em.merge(formadepagomovacreencia);
			if (idMonedaOld != null && !idMonedaOld.equals(idMonedaNew)) {
				idMonedaOld.getFormadepagomovacreenciaList().remove(
						formadepagomovacreencia);
				idMonedaOld = em.merge(idMonedaOld);
			}
			if (idMonedaNew != null && !idMonedaNew.equals(idMonedaOld)) {
				idMonedaNew.getFormadepagomovacreenciaList().add(
						formadepagomovacreencia);
				idMonedaNew = em.merge(idMonedaNew);
			}
			if (idMovimientoacreenciaOld != null
					&& !idMovimientoacreenciaOld
							.equals(idMovimientoacreenciaNew)) {
				idMovimientoacreenciaOld.getFormadepagomovacreenciaList()
						.remove(formadepagomovacreencia);
				idMovimientoacreenciaOld = em.merge(idMovimientoacreenciaOld);
			}
			if (idMovimientoacreenciaNew != null
					&& !idMovimientoacreenciaNew
							.equals(idMovimientoacreenciaOld)) {
				idMovimientoacreenciaNew.getFormadepagomovacreenciaList().add(
						formadepagomovacreencia);
				idMovimientoacreenciaNew = em.merge(idMovimientoacreenciaNew);
			}
			if (idFormadepagoOld != null
					&& !idFormadepagoOld.equals(idFormadepagoNew)) {
				idFormadepagoOld.getFormadepagomovacreenciaList().remove(
						formadepagomovacreencia);
				idFormadepagoOld = em.merge(idFormadepagoOld);
			}
			if (idFormadepagoNew != null
					&& !idFormadepagoNew.equals(idFormadepagoOld)) {
				idFormadepagoNew.getFormadepagomovacreenciaList().add(
						formadepagomovacreencia);
				idFormadepagoNew = em.merge(idFormadepagoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = formadepagomovacreencia.getId();
				if (findFormadepagomovacreencia(id) == null) {
					throw new NonexistentEntityException(
							"The formadepagomovacreencia with id " + id
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
			Formadepagomovacreencia formadepagomovacreencia;
			try {
				formadepagomovacreencia = em.getReference(
						Formadepagomovacreencia.class, id);
				formadepagomovacreencia.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The formadepagomovacreencia with id " + id
								+ " no longer exists.", enfe);
			}
			Moneda idMoneda = formadepagomovacreencia.getIdMoneda();
			if (idMoneda != null) {
				idMoneda.getFormadepagomovacreenciaList().remove(
						formadepagomovacreencia);
				idMoneda = em.merge(idMoneda);
			}
			Movimientoacreencia idMovimientoacreencia = formadepagomovacreencia
					.getIdMovimientoacreencia();
			if (idMovimientoacreencia != null) {
				idMovimientoacreencia.getFormadepagomovacreenciaList().remove(
						formadepagomovacreencia);
				idMovimientoacreencia = em.merge(idMovimientoacreencia);
			}
			Formadepago idFormadepago = formadepagomovacreencia
					.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago.getFormadepagomovacreenciaList().remove(
						formadepagomovacreencia);
				idFormadepago = em.merge(idFormadepago);
			}
			em.remove(formadepagomovacreencia);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findFormadepagomovacreenciaEntities.
	 * 
	 * @return List<Formadepagomovacreencia>
	 */
	public List<Formadepagomovacreencia> findFormadepagomovacreenciaEntities() {
		return findFormadepagomovacreenciaEntities(true, -1, -1);
	}

	/**
	 * Method findFormadepagomovacreenciaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Formadepagomovacreencia>
	 */
	public List<Formadepagomovacreencia> findFormadepagomovacreenciaEntities(
			int maxResults, int firstResult) {
		return findFormadepagomovacreenciaEntities(false, maxResults,
				firstResult);
	}

	/**
	 * Method findFormadepagomovacreenciaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Formadepagomovacreencia>
	 */
	private List<Formadepagomovacreencia> findFormadepagomovacreenciaEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Formadepagomovacreencia.class));
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
	 * Method findFormadepagomovacreencia.
	 * 
	 * @param id
	 *            Long
	 * @return Formadepagomovacreencia
	 */
	public Formadepagomovacreencia findFormadepagomovacreencia(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Formadepagomovacreencia.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getFormadepagomovacreenciaCount.
	 * 
	 * @return int
	 */
	public int getFormadepagomovacreenciaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Formadepagomovacreencia> rt = cq
					.from(Formadepagomovacreencia.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
