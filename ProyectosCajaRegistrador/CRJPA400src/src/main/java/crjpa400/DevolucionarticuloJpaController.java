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
public class DevolucionarticuloJpaController implements Serializable {

	public DevolucionarticuloJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Devolucionarticulo devolucionarticulo)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Devolucion idDevolucion = devolucionarticulo.getIdDevolucion();
			if (idDevolucion != null) {
				idDevolucion = em.getReference(idDevolucion.getClass(),
						idDevolucion.getId());
				devolucionarticulo.setIdDevolucion(idDevolucion);
			}
			em.persist(devolucionarticulo);
			if (idDevolucion != null) {
				idDevolucion.getDevolucionarticuloList()
						.add(devolucionarticulo);
				idDevolucion = em.merge(idDevolucion);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findDevolucionarticulo(devolucionarticulo.getId()) != null) {
				throw new PreexistingEntityException("Devolucionarticulo "
						+ devolucionarticulo + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Devolucionarticulo devolucionarticulo)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Devolucionarticulo persistentDevolucionarticulo = em
					.find(Devolucionarticulo.class, devolucionarticulo.getId());
			Devolucion idDevolucionOld = persistentDevolucionarticulo
					.getIdDevolucion();
			Devolucion idDevolucionNew = devolucionarticulo.getIdDevolucion();
			if (idDevolucionNew != null) {
				idDevolucionNew = em.getReference(idDevolucionNew.getClass(),
						idDevolucionNew.getId());
				devolucionarticulo.setIdDevolucion(idDevolucionNew);
			}
			devolucionarticulo = em.merge(devolucionarticulo);
			if (idDevolucionOld != null
					&& !idDevolucionOld.equals(idDevolucionNew)) {
				idDevolucionOld.getDevolucionarticuloList()
						.remove(devolucionarticulo);
				idDevolucionOld = em.merge(idDevolucionOld);
			}
			if (idDevolucionNew != null
					&& !idDevolucionNew.equals(idDevolucionOld)) {
				idDevolucionNew.getDevolucionarticuloList()
						.add(devolucionarticulo);
				idDevolucionNew = em.merge(idDevolucionNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = devolucionarticulo.getId();
				if (findDevolucionarticulo(id) == null) {
					throw new NonexistentEntityException(
							"The devolucionarticulo with id " + id
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
			Devolucionarticulo devolucionarticulo;
			try {
				devolucionarticulo = em.getReference(Devolucionarticulo.class,
						id);
				devolucionarticulo.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The devolucionarticulo with id " + id
								+ " no longer exists.",
						enfe);
			}
			Devolucion idDevolucion = devolucionarticulo.getIdDevolucion();
			if (idDevolucion != null) {
				idDevolucion.getDevolucionarticuloList()
						.remove(devolucionarticulo);
				idDevolucion = em.merge(idDevolucion);
			}
			em.remove(devolucionarticulo);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Devolucionarticulo> findDevolucionarticuloEntities() {
		return findDevolucionarticuloEntities(true, -1, -1);
	}

	public List<Devolucionarticulo> findDevolucionarticuloEntities(
			int maxResults, int firstResult) {
		return findDevolucionarticuloEntities(false, maxResults, firstResult);
	}

	private List<Devolucionarticulo> findDevolucionarticuloEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Devolucionarticulo.class));
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

	public Devolucionarticulo findDevolucionarticulo(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Devolucionarticulo.class, id);
		} finally {
			em.close();
		}
	}

	public int getDevolucionarticuloCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Devolucionarticulo> rt = cq.from(Devolucionarticulo.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
