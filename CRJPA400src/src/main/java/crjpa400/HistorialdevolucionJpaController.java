/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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

/**
 *
 * @author eve0018536
 */
public class HistorialdevolucionJpaController implements Serializable {

	public HistorialdevolucionJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Historialdevolucion historialdevolucion) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(historialdevolucion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Historialdevolucion historialdevolucion)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			historialdevolucion = em.merge(historialdevolucion);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = historialdevolucion.getId();
				if (findHistorialdevolucion(id) == null) {
					throw new NonexistentEntityException(
							"The historialdevolucion with id " + id
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

	public void destroy(Long id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Historialdevolucion historialdevolucion;
			try {
				historialdevolucion = em.getReference(Historialdevolucion.class,
						id);
				historialdevolucion.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The historialdevolucion with id " + id
								+ " no longer exists.",
						enfe);
			}
			em.remove(historialdevolucion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Historialdevolucion> findHistorialdevolucionEntities() {
		return findHistorialdevolucionEntities(true, -1, -1);
	}

	public List<Historialdevolucion> findHistorialdevolucionEntities(
			int maxResults, int firstResult) {
		return findHistorialdevolucionEntities(false, maxResults, firstResult);
	}

	private List<Historialdevolucion> findHistorialdevolucionEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Historialdevolucion.class));
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

	public Historialdevolucion findHistorialdevolucion(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Historialdevolucion.class, id);
		} finally {
			em.close();
		}
	}

	public int getHistorialdevolucionCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Historialdevolucion> rt = cq.from(Historialdevolucion.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
