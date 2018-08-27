/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa400.exceptions.IllegalOrphanException;
import crjpa400.exceptions.NonexistentEntityException;
import crjpa400.exceptions.PreexistingEntityException;

/**
 *
 * @author eve0018536@epa.com
 */
public class DevolucionJpaController implements Serializable {

	public DevolucionJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Devolucion devolucion)
			throws PreexistingEntityException, Exception {
		if (devolucion.getDevolucionarticuloList() == null) {
			devolucion.setDevolucionarticuloList(
					new ArrayList<Devolucionarticulo>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Devolucionarticulo> attachedDevolucionarticuloList = new ArrayList<Devolucionarticulo>();
			for (Devolucionarticulo devolucionarticuloListDevolucionarticuloToAttach : devolucion
					.getDevolucionarticuloList()) {
				devolucionarticuloListDevolucionarticuloToAttach = em
						.getReference(
								devolucionarticuloListDevolucionarticuloToAttach
										.getClass(),
								devolucionarticuloListDevolucionarticuloToAttach
										.getId());
				attachedDevolucionarticuloList
						.add(devolucionarticuloListDevolucionarticuloToAttach);
			}
			devolucion
					.setDevolucionarticuloList(attachedDevolucionarticuloList);
			em.persist(devolucion);
			for (Devolucionarticulo devolucionarticuloListDevolucionarticulo : devolucion
					.getDevolucionarticuloList()) {
				Devolucion oldIdDevolucionOfDevolucionarticuloListDevolucionarticulo = devolucionarticuloListDevolucionarticulo
						.getIdDevolucion();
				devolucionarticuloListDevolucionarticulo
						.setIdDevolucion(devolucion);
				devolucionarticuloListDevolucionarticulo = em
						.merge(devolucionarticuloListDevolucionarticulo);
				if (oldIdDevolucionOfDevolucionarticuloListDevolucionarticulo != null) {
					oldIdDevolucionOfDevolucionarticuloListDevolucionarticulo
							.getDevolucionarticuloList()
							.remove(devolucionarticuloListDevolucionarticulo);
					oldIdDevolucionOfDevolucionarticuloListDevolucionarticulo = em
							.merge(oldIdDevolucionOfDevolucionarticuloListDevolucionarticulo);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findDevolucion(devolucion.getId()) != null) {
				throw new PreexistingEntityException(
						"Devolucion " + devolucion + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Devolucion devolucion) throws IllegalOrphanException,
			NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Devolucion persistentDevolucion = em.find(Devolucion.class,
					devolucion.getId());
			List<Devolucionarticulo> devolucionarticuloListOld = persistentDevolucion
					.getDevolucionarticuloList();
			List<Devolucionarticulo> devolucionarticuloListNew = devolucion
					.getDevolucionarticuloList();
			List<String> illegalOrphanMessages = null;
			for (Devolucionarticulo devolucionarticuloListOldDevolucionarticulo : devolucionarticuloListOld) {
				if (!devolucionarticuloListNew.contains(
						devolucionarticuloListOldDevolucionarticulo)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Devolucionarticulo "
									+ devolucionarticuloListOldDevolucionarticulo
									+ " since its idDevolucion field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Devolucionarticulo> attachedDevolucionarticuloListNew = new ArrayList<Devolucionarticulo>();
			for (Devolucionarticulo devolucionarticuloListNewDevolucionarticuloToAttach : devolucionarticuloListNew) {
				devolucionarticuloListNewDevolucionarticuloToAttach = em
						.getReference(
								devolucionarticuloListNewDevolucionarticuloToAttach
										.getClass(),
								devolucionarticuloListNewDevolucionarticuloToAttach
										.getId());
				attachedDevolucionarticuloListNew.add(
						devolucionarticuloListNewDevolucionarticuloToAttach);
			}
			devolucionarticuloListNew = attachedDevolucionarticuloListNew;
			devolucion.setDevolucionarticuloList(devolucionarticuloListNew);
			devolucion = em.merge(devolucion);
			for (Devolucionarticulo devolucionarticuloListNewDevolucionarticulo : devolucionarticuloListNew) {
				if (!devolucionarticuloListOld.contains(
						devolucionarticuloListNewDevolucionarticulo)) {
					Devolucion oldIdDevolucionOfDevolucionarticuloListNewDevolucionarticulo = devolucionarticuloListNewDevolucionarticulo
							.getIdDevolucion();
					devolucionarticuloListNewDevolucionarticulo
							.setIdDevolucion(devolucion);
					devolucionarticuloListNewDevolucionarticulo = em
							.merge(devolucionarticuloListNewDevolucionarticulo);
					if (oldIdDevolucionOfDevolucionarticuloListNewDevolucionarticulo != null
							&& !oldIdDevolucionOfDevolucionarticuloListNewDevolucionarticulo
									.equals(devolucion)) {
						oldIdDevolucionOfDevolucionarticuloListNewDevolucionarticulo
								.getDevolucionarticuloList()
								.remove(devolucionarticuloListNewDevolucionarticulo);
						oldIdDevolucionOfDevolucionarticuloListNewDevolucionarticulo = em
								.merge(oldIdDevolucionOfDevolucionarticuloListNewDevolucionarticulo);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = devolucion.getId();
				if (findDevolucion(id) == null) {
					throw new NonexistentEntityException(
							"The devolucion with id " + id
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

	public void destroy(Long id)
			throws IllegalOrphanException, NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Devolucion devolucion;
			try {
				devolucion = em.getReference(Devolucion.class, id);
				devolucion.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The devolucion with id " + id + " no longer exists.",
						enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Devolucionarticulo> devolucionarticuloListOrphanCheck = devolucion
					.getDevolucionarticuloList();
			for (Devolucionarticulo devolucionarticuloListOrphanCheckDevolucionarticulo : devolucionarticuloListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Devolucion (" + devolucion
						+ ") cannot be destroyed since the Devolucionarticulo "
						+ devolucionarticuloListOrphanCheckDevolucionarticulo
						+ " in its devolucionarticuloList field has a non-nullable idDevolucion field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(devolucion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Devolucion> findDevolucionEntities() {
		return findDevolucionEntities(true, -1, -1);
	}

	public List<Devolucion> findDevolucionEntities(int maxResults,
			int firstResult) {
		return findDevolucionEntities(false, maxResults, firstResult);
	}

	private List<Devolucion> findDevolucionEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Devolucion.class));
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

	public Devolucion findDevolucion(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Devolucion.class, id);
		} finally {
			em.close();
		}
	}

	public int getDevolucionCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Devolucion> rt = cq.from(Devolucion.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
