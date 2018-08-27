/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.util.ArrayList;
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
public class TasaimpuestotipoJpaController implements Serializable {

	/**
	 * Constructor for TasaimpuestotipoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TasaimpuestotipoJpaController(EntityManagerFactory emf) {
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
	 * @param tasaimpuestotipo
	 *            Tasaimpuestotipo
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Tasaimpuestotipo tasaimpuestotipo) throws PreexistingEntityException, Exception {
		if (tasaimpuestotipo.getArticuloList() == null) {
			tasaimpuestotipo.setArticuloList(new ArrayList<Articulo>());
		}
		if (tasaimpuestotipo.getTasaimpuestovalorList() == null) {
			tasaimpuestotipo.setTasaimpuestovalorList(new ArrayList<Tasaimpuestovalor>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Articulo> attachedArticuloList = new ArrayList<Articulo>();
			for (Articulo articuloListArticuloToAttach : tasaimpuestotipo.getArticuloList()) {
				articuloListArticuloToAttach = em.getReference(articuloListArticuloToAttach.getClass(),
						articuloListArticuloToAttach.getId());
				attachedArticuloList.add(articuloListArticuloToAttach);
			}
			tasaimpuestotipo.setArticuloList(attachedArticuloList);
			List<Tasaimpuestovalor> attachedTasaimpuestovalorList = new ArrayList<Tasaimpuestovalor>();
			for (Tasaimpuestovalor tasaimpuestovalorListTasaimpuestovalorToAttach : tasaimpuestotipo
					.getTasaimpuestovalorList()) {
				tasaimpuestovalorListTasaimpuestovalorToAttach = em.getReference(
						tasaimpuestovalorListTasaimpuestovalorToAttach.getClass(),
						tasaimpuestovalorListTasaimpuestovalorToAttach.getId());
				attachedTasaimpuestovalorList.add(tasaimpuestovalorListTasaimpuestovalorToAttach);
			}
			tasaimpuestotipo.setTasaimpuestovalorList(attachedTasaimpuestovalorList);
			em.persist(tasaimpuestotipo);
			for (Articulo articuloListArticulo : tasaimpuestotipo.getArticuloList()) {
				Tasaimpuestotipo oldIdTasaimpuestotipoOfArticuloListArticulo = articuloListArticulo
						.getIdTasaimpuestotipo();
				articuloListArticulo.setIdTasaimpuestotipo(tasaimpuestotipo);
				articuloListArticulo = em.merge(articuloListArticulo);
				if (oldIdTasaimpuestotipoOfArticuloListArticulo != null) {
					oldIdTasaimpuestotipoOfArticuloListArticulo.getArticuloList().remove(articuloListArticulo);
					oldIdTasaimpuestotipoOfArticuloListArticulo = em.merge(oldIdTasaimpuestotipoOfArticuloListArticulo);
				}
			}
			for (Tasaimpuestovalor tasaimpuestovalorListTasaimpuestovalor : tasaimpuestotipo.getTasaimpuestovalorList()) {
				Tasaimpuestotipo oldIdTasaimpuestotipoOfTasaimpuestovalorListTasaimpuestovalor = tasaimpuestovalorListTasaimpuestovalor
						.getIdTasaimpuestotipo();
				tasaimpuestovalorListTasaimpuestovalor.setIdTasaimpuestotipo(tasaimpuestotipo);
				tasaimpuestovalorListTasaimpuestovalor = em.merge(tasaimpuestovalorListTasaimpuestovalor);
				if (oldIdTasaimpuestotipoOfTasaimpuestovalorListTasaimpuestovalor != null) {
					oldIdTasaimpuestotipoOfTasaimpuestovalorListTasaimpuestovalor.getTasaimpuestovalorList().remove(
							tasaimpuestovalorListTasaimpuestovalor);
					oldIdTasaimpuestotipoOfTasaimpuestovalorListTasaimpuestovalor = em
							.merge(oldIdTasaimpuestotipoOfTasaimpuestovalorListTasaimpuestovalor);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTasaimpuestotipo(tasaimpuestotipo.getId()) != null) {
				throw new PreexistingEntityException("Tasaimpuestotipo " + tasaimpuestotipo + " already exists.", ex);
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
	 * @param tasaimpuestotipo
	 *            Tasaimpuestotipo
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Tasaimpuestotipo tasaimpuestotipo) throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tasaimpuestotipo persistentTasaimpuestotipo = em.find(Tasaimpuestotipo.class, tasaimpuestotipo.getId());
			List<Articulo> articuloListOld = persistentTasaimpuestotipo.getArticuloList();
			List<Articulo> articuloListNew = tasaimpuestotipo.getArticuloList();
			List<Tasaimpuestovalor> tasaimpuestovalorListOld = persistentTasaimpuestotipo.getTasaimpuestovalorList();
			List<Tasaimpuestovalor> tasaimpuestovalorListNew = tasaimpuestotipo.getTasaimpuestovalorList();
			List<String> illegalOrphanMessages = null;
			for (Articulo articuloListOldArticulo : articuloListOld) {
				if (!articuloListNew.contains(articuloListOldArticulo)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Articulo " + articuloListOldArticulo
							+ " since its idTasaimpuestotipo field is not nullable.");
				}
			}
			for (Tasaimpuestovalor tasaimpuestovalorListOldTasaimpuestovalor : tasaimpuestovalorListOld) {
				if (!tasaimpuestovalorListNew.contains(tasaimpuestovalorListOldTasaimpuestovalor)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Tasaimpuestovalor "
							+ tasaimpuestovalorListOldTasaimpuestovalor
							+ " since its idTasaimpuestotipo field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Articulo> attachedArticuloListNew = new ArrayList<Articulo>();
			for (Articulo articuloListNewArticuloToAttach : articuloListNew) {
				articuloListNewArticuloToAttach = em.getReference(articuloListNewArticuloToAttach.getClass(),
						articuloListNewArticuloToAttach.getId());
				attachedArticuloListNew.add(articuloListNewArticuloToAttach);
			}
			articuloListNew = attachedArticuloListNew;
			tasaimpuestotipo.setArticuloList(articuloListNew);
			List<Tasaimpuestovalor> attachedTasaimpuestovalorListNew = new ArrayList<Tasaimpuestovalor>();
			for (Tasaimpuestovalor tasaimpuestovalorListNewTasaimpuestovalorToAttach : tasaimpuestovalorListNew) {
				tasaimpuestovalorListNewTasaimpuestovalorToAttach = em.getReference(
						tasaimpuestovalorListNewTasaimpuestovalorToAttach.getClass(),
						tasaimpuestovalorListNewTasaimpuestovalorToAttach.getId());
				attachedTasaimpuestovalorListNew.add(tasaimpuestovalorListNewTasaimpuestovalorToAttach);
			}
			tasaimpuestovalorListNew = attachedTasaimpuestovalorListNew;
			tasaimpuestotipo.setTasaimpuestovalorList(tasaimpuestovalorListNew);
			tasaimpuestotipo = em.merge(tasaimpuestotipo);
			for (Articulo articuloListNewArticulo : articuloListNew) {
				if (!articuloListOld.contains(articuloListNewArticulo)) {
					Tasaimpuestotipo oldIdTasaimpuestotipoOfArticuloListNewArticulo = articuloListNewArticulo
							.getIdTasaimpuestotipo();
					articuloListNewArticulo.setIdTasaimpuestotipo(tasaimpuestotipo);
					articuloListNewArticulo = em.merge(articuloListNewArticulo);
					if (oldIdTasaimpuestotipoOfArticuloListNewArticulo != null
							&& !oldIdTasaimpuestotipoOfArticuloListNewArticulo.equals(tasaimpuestotipo)) {
						oldIdTasaimpuestotipoOfArticuloListNewArticulo.getArticuloList()
								.remove(articuloListNewArticulo);
						oldIdTasaimpuestotipoOfArticuloListNewArticulo = em
								.merge(oldIdTasaimpuestotipoOfArticuloListNewArticulo);
					}
				}
			}
			for (Tasaimpuestovalor tasaimpuestovalorListNewTasaimpuestovalor : tasaimpuestovalorListNew) {
				if (!tasaimpuestovalorListOld.contains(tasaimpuestovalorListNewTasaimpuestovalor)) {
					Tasaimpuestotipo oldIdTasaimpuestotipoOfTasaimpuestovalorListNewTasaimpuestovalor = tasaimpuestovalorListNewTasaimpuestovalor
							.getIdTasaimpuestotipo();
					tasaimpuestovalorListNewTasaimpuestovalor.setIdTasaimpuestotipo(tasaimpuestotipo);
					tasaimpuestovalorListNewTasaimpuestovalor = em.merge(tasaimpuestovalorListNewTasaimpuestovalor);
					if (oldIdTasaimpuestotipoOfTasaimpuestovalorListNewTasaimpuestovalor != null
							&& !oldIdTasaimpuestotipoOfTasaimpuestovalorListNewTasaimpuestovalor
									.equals(tasaimpuestotipo)) {
						oldIdTasaimpuestotipoOfTasaimpuestovalorListNewTasaimpuestovalor.getTasaimpuestovalorList()
								.remove(tasaimpuestovalorListNewTasaimpuestovalor);
						oldIdTasaimpuestotipoOfTasaimpuestovalorListNewTasaimpuestovalor = em
								.merge(oldIdTasaimpuestotipoOfTasaimpuestovalorListNewTasaimpuestovalor);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = tasaimpuestotipo.getId();
				if (findTasaimpuestotipo(id) == null) {
					throw new NonexistentEntityException("The tasaimpuestotipo with id " + id + " no longer exists.");
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
			Tasaimpuestotipo tasaimpuestotipo;
			try {
				tasaimpuestotipo = em.getReference(Tasaimpuestotipo.class, id);
				tasaimpuestotipo.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The tasaimpuestotipo with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Articulo> articuloListOrphanCheck = tasaimpuestotipo.getArticuloList();
			for (Articulo articuloListOrphanCheckArticulo : articuloListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Tasaimpuestotipo (" + tasaimpuestotipo
						+ ") cannot be destroyed since the Articulo " + articuloListOrphanCheckArticulo
						+ " in its articuloList field has a non-nullable idTasaimpuestotipo field.");
			}
			List<Tasaimpuestovalor> tasaimpuestovalorListOrphanCheck = tasaimpuestotipo.getTasaimpuestovalorList();
			for (Tasaimpuestovalor tasaimpuestovalorListOrphanCheckTasaimpuestovalor : tasaimpuestovalorListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Tasaimpuestotipo (" + tasaimpuestotipo
						+ ") cannot be destroyed since the Tasaimpuestovalor "
						+ tasaimpuestovalorListOrphanCheckTasaimpuestovalor
						+ " in its tasaimpuestovalorList field has a non-nullable idTasaimpuestotipo field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(tasaimpuestotipo);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTasaimpuestotipoEntities.
	 * 
	 * @return List<Tasaimpuestotipo>
	 */
	public List<Tasaimpuestotipo> findTasaimpuestotipoEntities() {
		return findTasaimpuestotipoEntities(true, -1, -1);
	}

	/**
	 * Method findTasaimpuestotipoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tasaimpuestotipo>
	 */
	public List<Tasaimpuestotipo> findTasaimpuestotipoEntities(int maxResults, int firstResult) {
		return findTasaimpuestotipoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTasaimpuestotipoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tasaimpuestotipo>
	 */
	private List<Tasaimpuestotipo> findTasaimpuestotipoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Tasaimpuestotipo.class));
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
	 * Method findTasaimpuestotipo.
	 * 
	 * @param id
	 *            Long
	 * @return Tasaimpuestotipo
	 */
	public Tasaimpuestotipo findTasaimpuestotipo(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Tasaimpuestotipo.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTasaimpuestotipoCount.
	 * 
	 * @return int
	 */
	public int getTasaimpuestotipoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Tasaimpuestotipo> rt = cq.from(Tasaimpuestotipo.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
