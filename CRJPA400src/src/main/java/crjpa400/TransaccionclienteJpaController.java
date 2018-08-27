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
public class TransaccionclienteJpaController implements Serializable {

	/**
	 * Constructor for TransaccionclienteJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TransaccionclienteJpaController(EntityManagerFactory emf) {
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
	 * @param transaccioncliente
	 *            Transaccioncliente
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Transaccioncliente transaccioncliente)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Cliente numeroIdentificacionCliente = transaccioncliente
					.getNumeroIdentificacionCliente();
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente = em.getReference(
						numeroIdentificacionCliente.getClass(),
						numeroIdentificacionCliente
								.getNumeroIdentificacionCliente());
				transaccioncliente
						.setNumeroIdentificacionCliente(numeroIdentificacionCliente);
			}
			Transaccion idTransaccion = transaccioncliente.getIdTransaccion();
			if (idTransaccion != null) {
				idTransaccion = em.getReference(idTransaccion.getClass(),
						idTransaccion.getId());
				transaccioncliente.setIdTransaccion(idTransaccion);
			}
			Giroactividadeconomica idGiroactividadeconomica = transaccioncliente
					.getIdGiroactividadeconomica();
			if (idGiroactividadeconomica != null) {
				idGiroactividadeconomica = em.getReference(
						idGiroactividadeconomica.getClass(),
						idGiroactividadeconomica.getId());
				transaccioncliente
						.setIdGiroactividadeconomica(idGiroactividadeconomica);
			}
			em.persist(transaccioncliente);
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente.getTransaccionclienteList().add(
						transaccioncliente);
				numeroIdentificacionCliente = em
						.merge(numeroIdentificacionCliente);
			}
			if (idTransaccion != null) {
				idTransaccion.getTransaccionclienteList().add(
						transaccioncliente);
				idTransaccion = em.merge(idTransaccion);
			}
			if (idGiroactividadeconomica != null) {
				idGiroactividadeconomica.getTransaccionclienteList().add(
						transaccioncliente);
				idGiroactividadeconomica = em.merge(idGiroactividadeconomica);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTransaccioncliente(transaccioncliente.getId()) != null) {
				throw new PreexistingEntityException("Transaccioncliente "
						+ transaccioncliente + " already exists.", ex);
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
	 * @param transaccioncliente
	 *            Transaccioncliente
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Transaccioncliente transaccioncliente)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Transaccioncliente persistentTransaccioncliente = em.find(
					Transaccioncliente.class, transaccioncliente.getId());
			Cliente numeroIdentificacionClienteOld = persistentTransaccioncliente
					.getNumeroIdentificacionCliente();
			Cliente numeroIdentificacionClienteNew = transaccioncliente
					.getNumeroIdentificacionCliente();
			Transaccion idTransaccionOld = persistentTransaccioncliente
					.getIdTransaccion();
			Transaccion idTransaccionNew = transaccioncliente
					.getIdTransaccion();
			Giroactividadeconomica idGiroactividadeconomicaOld = persistentTransaccioncliente
					.getIdGiroactividadeconomica();
			Giroactividadeconomica idGiroactividadeconomicaNew = transaccioncliente
					.getIdGiroactividadeconomica();
			if (numeroIdentificacionClienteNew != null) {
				numeroIdentificacionClienteNew = em.getReference(
						numeroIdentificacionClienteNew.getClass(),
						numeroIdentificacionClienteNew
								.getNumeroIdentificacionCliente());
				transaccioncliente
						.setNumeroIdentificacionCliente(numeroIdentificacionClienteNew);
			}
			if (idTransaccionNew != null) {
				idTransaccionNew = em.getReference(idTransaccionNew.getClass(),
						idTransaccionNew.getId());
				transaccioncliente.setIdTransaccion(idTransaccionNew);
			}
			if (idGiroactividadeconomicaNew != null) {
				idGiroactividadeconomicaNew = em.getReference(
						idGiroactividadeconomicaNew.getClass(),
						idGiroactividadeconomicaNew.getId());
				transaccioncliente
						.setIdGiroactividadeconomica(idGiroactividadeconomicaNew);
			}
			transaccioncliente = em.merge(transaccioncliente);
			if (numeroIdentificacionClienteOld != null
					&& !numeroIdentificacionClienteOld
							.equals(numeroIdentificacionClienteNew)) {
				numeroIdentificacionClienteOld.getTransaccionclienteList()
						.remove(transaccioncliente);
				numeroIdentificacionClienteOld = em
						.merge(numeroIdentificacionClienteOld);
			}
			if (numeroIdentificacionClienteNew != null
					&& !numeroIdentificacionClienteNew
							.equals(numeroIdentificacionClienteOld)) {
				numeroIdentificacionClienteNew.getTransaccionclienteList().add(
						transaccioncliente);
				numeroIdentificacionClienteNew = em
						.merge(numeroIdentificacionClienteNew);
			}
			if (idTransaccionOld != null
					&& !idTransaccionOld.equals(idTransaccionNew)) {
				idTransaccionOld.getTransaccionclienteList().remove(
						transaccioncliente);
				idTransaccionOld = em.merge(idTransaccionOld);
			}
			if (idTransaccionNew != null
					&& !idTransaccionNew.equals(idTransaccionOld)) {
				idTransaccionNew.getTransaccionclienteList().add(
						transaccioncliente);
				idTransaccionNew = em.merge(idTransaccionNew);
			}
			if (idGiroactividadeconomicaOld != null
					&& !idGiroactividadeconomicaOld
							.equals(idGiroactividadeconomicaNew)) {
				idGiroactividadeconomicaOld.getTransaccionclienteList().remove(
						transaccioncliente);
				idGiroactividadeconomicaOld = em
						.merge(idGiroactividadeconomicaOld);
			}
			if (idGiroactividadeconomicaNew != null
					&& !idGiroactividadeconomicaNew
							.equals(idGiroactividadeconomicaOld)) {
				idGiroactividadeconomicaNew.getTransaccionclienteList().add(
						transaccioncliente);
				idGiroactividadeconomicaNew = em
						.merge(idGiroactividadeconomicaNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = transaccioncliente.getId();
				if (findTransaccioncliente(id) == null) {
					throw new NonexistentEntityException(
							"The transaccioncliente with id " + id
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
			Transaccioncliente transaccioncliente;
			try {
				transaccioncliente = em.getReference(Transaccioncliente.class,
						id);
				transaccioncliente.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The transaccioncliente with id " + id
								+ " no longer exists.", enfe);
			}
			Cliente numeroIdentificacionCliente = transaccioncliente
					.getNumeroIdentificacionCliente();
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente.getTransaccionclienteList().remove(
						transaccioncliente);
				numeroIdentificacionCliente = em
						.merge(numeroIdentificacionCliente);
			}
			Transaccion idTransaccion = transaccioncliente.getIdTransaccion();
			if (idTransaccion != null) {
				idTransaccion.getTransaccionclienteList().remove(
						transaccioncliente);
				idTransaccion = em.merge(idTransaccion);
			}
			Giroactividadeconomica idGiroactividadeconomica = transaccioncliente
					.getIdGiroactividadeconomica();
			if (idGiroactividadeconomica != null) {
				idGiroactividadeconomica.getTransaccionclienteList().remove(
						transaccioncliente);
				idGiroactividadeconomica = em.merge(idGiroactividadeconomica);
			}
			em.remove(transaccioncliente);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTransaccionclienteEntities.
	 * 
	 * @return List<Transaccioncliente>
	 */
	public List<Transaccioncliente> findTransaccionclienteEntities() {
		return findTransaccionclienteEntities(true, -1, -1);
	}

	/**
	 * Method findTransaccionclienteEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transaccioncliente>
	 */
	public List<Transaccioncliente> findTransaccionclienteEntities(
			int maxResults, int firstResult) {
		return findTransaccionclienteEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTransaccionclienteEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transaccioncliente>
	 */
	private List<Transaccioncliente> findTransaccionclienteEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Transaccioncliente.class));
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
	 * Method findTransaccioncliente.
	 * 
	 * @param id
	 *            Long
	 * @return Transaccioncliente
	 */
	public Transaccioncliente findTransaccioncliente(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Transaccioncliente.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTransaccionclienteCount.
	 * 
	 * @return int
	 */
	public int getTransaccionclienteCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Transaccioncliente> rt = cq.from(Transaccioncliente.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
