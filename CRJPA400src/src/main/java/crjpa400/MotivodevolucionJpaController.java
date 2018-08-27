/**
 * 
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
 * @author eve0018536@epa.com
 */
public class MotivodevolucionJpaController implements Serializable {

	public MotivodevolucionJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Motivodevolucion motivodevolucion)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(motivodevolucion);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findMotivodevolucion(motivodevolucion.getId()) != null) {
				throw new PreexistingEntityException("Motivodevolucion "
						+ motivodevolucion + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Motivodevolucion motivodevolucion)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			motivodevolucion = em.merge(motivodevolucion);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = motivodevolucion.getId();
				if (findMotivodevolucion(id) == null) {
					throw new NonexistentEntityException(
							"The motivodevolucion with id " + id
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
			Motivodevolucion motivodevolucion;
			try {
				motivodevolucion = em.getReference(Motivodevolucion.class, id);
				motivodevolucion.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The motivodevolucion with id " + id
								+ " no longer exists.",
						enfe);
			}
			em.remove(motivodevolucion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Motivodevolucion> findMotivodevolucionEntities() {
		return findMotivodevolucionEntities(true, -1, -1);
	}

	public List<Motivodevolucion> findMotivodevolucionEntities(int maxResults,
			int firstResult) {
		return findMotivodevolucionEntities(false, maxResults, firstResult);
	}

	private List<Motivodevolucion> findMotivodevolucionEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Motivodevolucion.class));
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

	public Motivodevolucion findMotivodevolucion(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Motivodevolucion.class, id);
		} finally {
			em.close();
		}
	}

	public int getMotivodevolucionCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Motivodevolucion> rt = cq.from(Motivodevolucion.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
