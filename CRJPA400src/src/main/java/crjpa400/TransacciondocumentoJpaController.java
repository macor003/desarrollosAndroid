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
public class TransacciondocumentoJpaController implements Serializable {

	/**
	 * Constructor for TransacciondocumentoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TransacciondocumentoJpaController(EntityManagerFactory emf) {
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
	 * @param transacciondocumento
	 *            Transacciondocumento
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Transacciondocumento transacciondocumento)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Comprobantefiscalpreimpreso idComprobantefiscalpreimpreso = transacciondocumento
					.getIdComprobantefiscalpreimpreso();
			if (idComprobantefiscalpreimpreso != null) {
				idComprobantefiscalpreimpreso = em.getReference(
						idComprobantefiscalpreimpreso.getClass(),
						idComprobantefiscalpreimpreso.getId());
				transacciondocumento.setIdComprobantefiscalpreimpreso(
						idComprobantefiscalpreimpreso);
			}
			Transaccion idTransaccion = transacciondocumento.getIdTransaccion();
			if (idTransaccion != null) {
				idTransaccion = em.getReference(idTransaccion.getClass(),
						idTransaccion.getId());
				transacciondocumento.setIdTransaccion(idTransaccion);
			}
			Tipodocumento idTipodocumento = transacciondocumento
					.getIdTipodocumento();
			if (idTipodocumento != null) {
				idTipodocumento = em.getReference(idTipodocumento.getClass(),
						idTipodocumento.getId());
				transacciondocumento.setIdTipodocumento(idTipodocumento);
			}
			em.persist(transacciondocumento);
			if (idComprobantefiscalpreimpreso != null) {
				idComprobantefiscalpreimpreso.getTransacciondocumentoList()
						.add(transacciondocumento);
				idComprobantefiscalpreimpreso = em
						.merge(idComprobantefiscalpreimpreso);
			}
			if (idTransaccion != null) {
				idTransaccion.getTransacciondocumentoList()
						.add(transacciondocumento);
				idTransaccion = em.merge(idTransaccion);
			}
			if (idTipodocumento != null) {
				idTipodocumento.getTransacciondocumentoList()
						.add(transacciondocumento);
				idTipodocumento = em.merge(idTipodocumento);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTransacciondocumento(
					transacciondocumento.getId()) != null) {
				throw new PreexistingEntityException("Transacciondocumento "
						+ transacciondocumento + " already exists.", ex);
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
	 * @param transacciondocumento
	 *            Transacciondocumento
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Transacciondocumento transacciondocumento)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Transacciondocumento persistentTransacciondocumento = em.find(
					Transacciondocumento.class, transacciondocumento.getId());
			Comprobantefiscalpreimpreso idComprobantefiscalpreimpresoOld = persistentTransacciondocumento
					.getIdComprobantefiscalpreimpreso();
			Comprobantefiscalpreimpreso idComprobantefiscalpreimpresoNew = transacciondocumento
					.getIdComprobantefiscalpreimpreso();
			Transaccion idTransaccionOld = persistentTransacciondocumento
					.getIdTransaccion();
			Transaccion idTransaccionNew = transacciondocumento
					.getIdTransaccion();
			Tipodocumento idTipodocumentoOld = persistentTransacciondocumento
					.getIdTipodocumento();
			Tipodocumento idTipodocumentoNew = transacciondocumento
					.getIdTipodocumento();
			if (idComprobantefiscalpreimpresoNew != null) {
				idComprobantefiscalpreimpresoNew = em.getReference(
						idComprobantefiscalpreimpresoNew.getClass(),
						idComprobantefiscalpreimpresoNew.getId());
				transacciondocumento.setIdComprobantefiscalpreimpreso(
						idComprobantefiscalpreimpresoNew);
			}
			if (idTransaccionNew != null) {
				idTransaccionNew = em.getReference(idTransaccionNew.getClass(),
						idTransaccionNew.getId());
				transacciondocumento.setIdTransaccion(idTransaccionNew);
			}
			if (idTipodocumentoNew != null) {
				idTipodocumentoNew = em.getReference(
						idTipodocumentoNew.getClass(),
						idTipodocumentoNew.getId());
				transacciondocumento.setIdTipodocumento(idTipodocumentoNew);
			}
			transacciondocumento = em.merge(transacciondocumento);
			if (idComprobantefiscalpreimpresoOld != null
					&& !idComprobantefiscalpreimpresoOld
							.equals(idComprobantefiscalpreimpresoNew)) {
				idComprobantefiscalpreimpresoOld.getTransacciondocumentoList()
						.remove(transacciondocumento);
				idComprobantefiscalpreimpresoOld = em
						.merge(idComprobantefiscalpreimpresoOld);
			}
			if (idComprobantefiscalpreimpresoNew != null
					&& !idComprobantefiscalpreimpresoNew
							.equals(idComprobantefiscalpreimpresoOld)) {
				idComprobantefiscalpreimpresoNew.getTransacciondocumentoList()
						.add(transacciondocumento);
				idComprobantefiscalpreimpresoNew = em
						.merge(idComprobantefiscalpreimpresoNew);
			}
			if (idTransaccionOld != null
					&& !idTransaccionOld.equals(idTransaccionNew)) {
				idTransaccionOld.getTransacciondocumentoList()
						.remove(transacciondocumento);
				idTransaccionOld = em.merge(idTransaccionOld);
			}
			if (idTransaccionNew != null
					&& !idTransaccionNew.equals(idTransaccionOld)) {
				idTransaccionNew.getTransacciondocumentoList()
						.add(transacciondocumento);
				idTransaccionNew = em.merge(idTransaccionNew);
			}
			if (idTipodocumentoOld != null
					&& !idTipodocumentoOld.equals(idTipodocumentoNew)) {
				idTipodocumentoOld.getTransacciondocumentoList()
						.remove(transacciondocumento);
				idTipodocumentoOld = em.merge(idTipodocumentoOld);
			}
			if (idTipodocumentoNew != null
					&& !idTipodocumentoNew.equals(idTipodocumentoOld)) {
				idTipodocumentoNew.getTransacciondocumentoList()
						.add(transacciondocumento);
				idTipodocumentoNew = em.merge(idTipodocumentoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = transacciondocumento.getId();
				if (findTransacciondocumento(id) == null) {
					throw new NonexistentEntityException(
							"The transacciondocumento with id " + id
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
			Transacciondocumento transacciondocumento;
			try {
				transacciondocumento = em
						.getReference(Transacciondocumento.class, id);
				transacciondocumento.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The transacciondocumento with id " + id
								+ " no longer exists.",
						enfe);
			}
			Comprobantefiscalpreimpreso idComprobantefiscalpreimpreso = transacciondocumento
					.getIdComprobantefiscalpreimpreso();
			if (idComprobantefiscalpreimpreso != null) {
				idComprobantefiscalpreimpreso.getTransacciondocumentoList()
						.remove(transacciondocumento);
				idComprobantefiscalpreimpreso = em
						.merge(idComprobantefiscalpreimpreso);
			}
			Transaccion idTransaccion = transacciondocumento.getIdTransaccion();
			if (idTransaccion != null) {
				idTransaccion.getTransacciondocumentoList()
						.remove(transacciondocumento);
				idTransaccion = em.merge(idTransaccion);
			}
			Tipodocumento idTipodocumento = transacciondocumento
					.getIdTipodocumento();
			if (idTipodocumento != null) {
				idTipodocumento.getTransacciondocumentoList()
						.remove(transacciondocumento);
				idTipodocumento = em.merge(idTipodocumento);
			}
			em.remove(transacciondocumento);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTransacciondocumentoEntities.
	 * 
	 * @return List<Transacciondocumento>
	 */
	public List<Transacciondocumento> findTransacciondocumentoEntities() {
		return findTransacciondocumentoEntities(true, -1, -1);
	}

	/**
	 * Method findTransacciondocumentoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transacciondocumento>
	 */
	public List<Transacciondocumento> findTransacciondocumentoEntities(
			int maxResults, int firstResult) {
		return findTransacciondocumentoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTransacciondocumentoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transacciondocumento>
	 */
	private List<Transacciondocumento> findTransacciondocumentoEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Transacciondocumento.class));
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
	 * Method findTransacciondocumento.
	 * 
	 * @param id
	 *            Long
	 * @return Transacciondocumento
	 */
	public Transacciondocumento findTransacciondocumento(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Transacciondocumento.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTransacciondocumentoCount.
	 * 
	 * @return int
	 */
	public int getTransacciondocumentoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Transacciondocumento> rt = cq.from(Transacciondocumento.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
