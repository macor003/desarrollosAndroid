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

import crjpa.exceptions.IllegalOrphanException;
import crjpa.exceptions.NonexistentEntityException;
import crjpa.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class UnidadventaJpaController implements Serializable {

	/**
	 * Constructor for UnidadventaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public UnidadventaJpaController(EntityManagerFactory emf) {
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
	 * @param unidadventa
	 *            Unidadventa
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Unidadventa unidadventa) throws PreexistingEntityException, Exception {
		// if (unidadventa.getTransaccionarticuloList() == null) {
		// unidadventa.setTransaccionarticuloList(new
		// ArrayList<Transaccionarticulo>());
		// }
		// if (unidadventa.getArticulounidadventaList() == null) {
		// unidadventa.setArticulounidadventaList(new
		// ArrayList<Articulounidadventa>());
		// }
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			// List<Transaccionarticulo> attachedTransaccionarticuloList = new
			// ArrayList<Transaccionarticulo>();
			// for (Transaccionarticulo
			// transaccionarticuloListTransaccionarticuloToAttach :
			// unidadventa.getTransaccionarticuloList()) {
			// transaccionarticuloListTransaccionarticuloToAttach =
			// em.getReference(transaccionarticuloListTransaccionarticuloToAttach.getClass(),
			// transaccionarticuloListTransaccionarticuloToAttach.getId());
			// attachedTransaccionarticuloList.add(transaccionarticuloListTransaccionarticuloToAttach);
			// }
			// unidadventa.setTransaccionarticuloList(attachedTransaccionarticuloList);
			// List<Articulounidadventa> attachedArticulounidadventaList = new
			// ArrayList<Articulounidadventa>();
			// for (Articulounidadventa
			// articulounidadventaListArticulounidadventaToAttach :
			// unidadventa.getArticulounidadventaList()) {
			// articulounidadventaListArticulounidadventaToAttach =
			// em.getReference(articulounidadventaListArticulounidadventaToAttach.getClass(),
			// articulounidadventaListArticulounidadventaToAttach.getId());
			// attachedArticulounidadventaList.add(articulounidadventaListArticulounidadventaToAttach);
			// }
			// unidadventa.setArticulounidadventaList(attachedArticulounidadventaList);
			em.persist(unidadventa);
			// for (Transaccionarticulo
			// transaccionarticuloListTransaccionarticulo :
			// unidadventa.getTransaccionarticuloList()) {
			// Unidadventa
			// oldIdUnidadventaOfTransaccionarticuloListTransaccionarticulo =
			// transaccionarticuloListTransaccionarticulo.getIdUnidadventa();
			// transaccionarticuloListTransaccionarticulo.setIdUnidadventa(unidadventa);
			// transaccionarticuloListTransaccionarticulo =
			// em.merge(transaccionarticuloListTransaccionarticulo);
			// if (oldIdUnidadventaOfTransaccionarticuloListTransaccionarticulo
			// != null) {
			// oldIdUnidadventaOfTransaccionarticuloListTransaccionarticulo.getTransaccionarticuloList().remove(transaccionarticuloListTransaccionarticulo);
			// oldIdUnidadventaOfTransaccionarticuloListTransaccionarticulo =
			// em.merge(oldIdUnidadventaOfTransaccionarticuloListTransaccionarticulo);
			// }
			// }
			// for (Articulounidadventa
			// articulounidadventaListArticulounidadventa :
			// unidadventa.getArticulounidadventaList()) {
			// Unidadventa
			// oldIdUnidadventaOfArticulounidadventaListArticulounidadventa =
			// articulounidadventaListArticulounidadventa.getIdUnidadventa();
			// articulounidadventaListArticulounidadventa.setIdUnidadventa(unidadventa);
			// articulounidadventaListArticulounidadventa =
			// em.merge(articulounidadventaListArticulounidadventa);
			// if (oldIdUnidadventaOfArticulounidadventaListArticulounidadventa
			// != null) {
			// oldIdUnidadventaOfArticulounidadventaListArticulounidadventa.getArticulounidadventaList().remove(articulounidadventaListArticulounidadventa);
			// oldIdUnidadventaOfArticulounidadventaListArticulounidadventa =
			// em.merge(oldIdUnidadventaOfArticulounidadventaListArticulounidadventa);
			// }
			// }
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findUnidadventa(unidadventa.getId()) != null) {
				throw new PreexistingEntityException("Unidadventa " + unidadventa + " already exists.", ex);
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
	 * @param unidadventa
	 *            Unidadventa
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Unidadventa unidadventa) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			// Unidadventa persistentUnidadventa = em.find(Unidadventa.class,
			// unidadventa.getId());
			// List<Transaccionarticulo> transaccionarticuloListOld =
			// persistentUnidadventa.getTransaccionarticuloList();
			// List<Transaccionarticulo> transaccionarticuloListNew =
			// unidadventa.getTransaccionarticuloList();
			// List<Articulounidadventa> articulounidadventaListOld =
			// persistentUnidadventa.getArticulounidadventaList();
			// List<Articulounidadventa> articulounidadventaListNew =
			// unidadventa.getArticulounidadventaList();
			// List<String> illegalOrphanMessages = null;
			// for (Transaccionarticulo
			// transaccionarticuloListOldTransaccionarticulo :
			// transaccionarticuloListOld) {
			// if
			// (!transaccionarticuloListNew.contains(transaccionarticuloListOldTransaccionarticulo))
			// {
			// if (illegalOrphanMessages == null) {
			// illegalOrphanMessages = new ArrayList<String>();
			// }
			// illegalOrphanMessages.add("You must retain Transaccionarticulo "
			// + transaccionarticuloListOldTransaccionarticulo +
			// " since its idUnidadventa field is not nullable.");
			// }
			// }
			// for (Articulounidadventa
			// articulounidadventaListOldArticulounidadventa :
			// articulounidadventaListOld) {
			// if
			// (!articulounidadventaListNew.contains(articulounidadventaListOldArticulounidadventa))
			// {
			// if (illegalOrphanMessages == null) {
			// illegalOrphanMessages = new ArrayList<String>();
			// }
			// illegalOrphanMessages.add("You must retain Articulounidadventa "
			// + articulounidadventaListOldArticulounidadventa +
			// " since its idUnidadventa field is not nullable.");
			// }
			// }
			// if (illegalOrphanMessages != null) {
			// throw new IllegalOrphanException(illegalOrphanMessages);
			// }
			// List<Transaccionarticulo> attachedTransaccionarticuloListNew =
			// new ArrayList<Transaccionarticulo>();
			// for (Transaccionarticulo
			// transaccionarticuloListNewTransaccionarticuloToAttach :
			// transaccionarticuloListNew) {
			// transaccionarticuloListNewTransaccionarticuloToAttach =
			// em.getReference(transaccionarticuloListNewTransaccionarticuloToAttach.getClass(),
			// transaccionarticuloListNewTransaccionarticuloToAttach.getId());
			// attachedTransaccionarticuloListNew.add(transaccionarticuloListNewTransaccionarticuloToAttach);
			// }
			// transaccionarticuloListNew = attachedTransaccionarticuloListNew;
			// unidadventa.setTransaccionarticuloList(transaccionarticuloListNew);
			// List<Articulounidadventa> attachedArticulounidadventaListNew =
			// new ArrayList<Articulounidadventa>();
			// for (Articulounidadventa
			// articulounidadventaListNewArticulounidadventaToAttach :
			// articulounidadventaListNew) {
			// articulounidadventaListNewArticulounidadventaToAttach =
			// em.getReference(articulounidadventaListNewArticulounidadventaToAttach.getClass(),
			// articulounidadventaListNewArticulounidadventaToAttach.getId());
			// attachedArticulounidadventaListNew.add(articulounidadventaListNewArticulounidadventaToAttach);
			// }
			// articulounidadventaListNew = attachedArticulounidadventaListNew;
			// unidadventa.setArticulounidadventaList(articulounidadventaListNew);
			unidadventa = em.merge(unidadventa);
			// for (Transaccionarticulo
			// transaccionarticuloListNewTransaccionarticulo :
			// transaccionarticuloListNew) {
			// if
			// (!transaccionarticuloListOld.contains(transaccionarticuloListNewTransaccionarticulo))
			// {
			// Unidadventa
			// oldIdUnidadventaOfTransaccionarticuloListNewTransaccionarticulo =
			// transaccionarticuloListNewTransaccionarticulo.getIdUnidadventa();
			// transaccionarticuloListNewTransaccionarticulo.setIdUnidadventa(unidadventa);
			// transaccionarticuloListNewTransaccionarticulo =
			// em.merge(transaccionarticuloListNewTransaccionarticulo);
			// if
			// (oldIdUnidadventaOfTransaccionarticuloListNewTransaccionarticulo
			// != null &&
			// !oldIdUnidadventaOfTransaccionarticuloListNewTransaccionarticulo.equals(unidadventa))
			// {
			// oldIdUnidadventaOfTransaccionarticuloListNewTransaccionarticulo.getTransaccionarticuloList().remove(transaccionarticuloListNewTransaccionarticulo);
			// oldIdUnidadventaOfTransaccionarticuloListNewTransaccionarticulo =
			// em.merge(oldIdUnidadventaOfTransaccionarticuloListNewTransaccionarticulo);
			// }
			// }
			// }
			// for (Articulounidadventa
			// articulounidadventaListNewArticulounidadventa :
			// articulounidadventaListNew) {
			// if
			// (!articulounidadventaListOld.contains(articulounidadventaListNewArticulounidadventa))
			// {
			// Unidadventa
			// oldIdUnidadventaOfArticulounidadventaListNewArticulounidadventa =
			// articulounidadventaListNewArticulounidadventa.getIdUnidadventa();
			// articulounidadventaListNewArticulounidadventa.setIdUnidadventa(unidadventa);
			// articulounidadventaListNewArticulounidadventa =
			// em.merge(articulounidadventaListNewArticulounidadventa);
			// if
			// (oldIdUnidadventaOfArticulounidadventaListNewArticulounidadventa
			// != null &&
			// !oldIdUnidadventaOfArticulounidadventaListNewArticulounidadventa.equals(unidadventa))
			// {
			// oldIdUnidadventaOfArticulounidadventaListNewArticulounidadventa.getArticulounidadventaList().remove(articulounidadventaListNewArticulounidadventa);
			// oldIdUnidadventaOfArticulounidadventaListNewArticulounidadventa =
			// em.merge(oldIdUnidadventaOfArticulounidadventaListNewArticulounidadventa);
			// }
			// }
			// }
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = unidadventa.getId();
				if (findUnidadventa(id) == null) {
					throw new NonexistentEntityException("The unidadventa with id " + id + " no longer exists.");
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
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 */
	public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Unidadventa unidadventa;
			try {
				unidadventa = em.getReference(Unidadventa.class, id);
				unidadventa.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The unidadventa with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			// List<Transaccionarticulo> transaccionarticuloListOrphanCheck =
			// unidadventa.getTransaccionarticuloList();
			// for (Transaccionarticulo
			// transaccionarticuloListOrphanCheckTransaccionarticulo :
			// transaccionarticuloListOrphanCheck) {
			// if (illegalOrphanMessages == null) {
			// illegalOrphanMessages = new ArrayList<String>();
			// }
			// illegalOrphanMessages.add("This Unidadventa (" + unidadventa +
			// ") cannot be destroyed since the Transaccionarticulo " +
			// transaccionarticuloListOrphanCheckTransaccionarticulo +
			// " in its transaccionarticuloList field has a non-nullable idUnidadventa field.");
			// }
			// List<Articulounidadventa> articulounidadventaListOrphanCheck =
			// unidadventa.getArticulounidadventaList();
			// for (Articulounidadventa
			// articulounidadventaListOrphanCheckArticulounidadventa :
			// articulounidadventaListOrphanCheck) {
			// if (illegalOrphanMessages == null) {
			// illegalOrphanMessages = new ArrayList<String>();
			// }
			// illegalOrphanMessages.add("This Unidadventa (" + unidadventa +
			// ") cannot be destroyed since the Articulounidadventa " +
			// articulounidadventaListOrphanCheckArticulounidadventa +
			// " in its articulounidadventaList field has a non-nullable idUnidadventa field.");
			// }
			// if (illegalOrphanMessages != null) {
			// throw new IllegalOrphanException(illegalOrphanMessages);
			// }
			em.remove(unidadventa);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findUnidadventaEntities.
	 * 
	 * @return List<Unidadventa>
	 */
	public List<Unidadventa> findUnidadventaEntities() {
		return findUnidadventaEntities(true, -1, -1);
	}

	/**
	 * Method findUnidadventaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Unidadventa>
	 */
	public List<Unidadventa> findUnidadventaEntities(int maxResults, int firstResult) {
		return findUnidadventaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findUnidadventaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Unidadventa>
	 */
	private List<Unidadventa> findUnidadventaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Unidadventa.class));
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
	 * Method findUnidadventa.
	 * 
	 * @param id
	 *            Long
	 * @return Unidadventa
	 */
	public Unidadventa findUnidadventa(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Unidadventa.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getUnidadventaCount.
	 * 
	 * @return int
	 */
	public int getUnidadventaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Unidadventa> rt = cq.from(Unidadventa.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
