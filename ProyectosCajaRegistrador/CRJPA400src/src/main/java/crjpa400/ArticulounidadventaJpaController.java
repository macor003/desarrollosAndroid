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
public class ArticulounidadventaJpaController implements Serializable {

	/**
	 * Constructor for ArticulounidadventaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ArticulounidadventaJpaController(EntityManagerFactory emf) {
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
	 * @param articulounidadventa
	 *            Articulounidadventa
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Articulounidadventa articulounidadventa)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Unidadventa idUnidadventa = articulounidadventa.getIdUnidadventa();
			if (idUnidadventa != null) {
				idUnidadventa = em.getReference(idUnidadventa.getClass(),
						idUnidadventa.getId());
				articulounidadventa.setIdUnidadventa(idUnidadventa);
			}
			Articulo idArticulo = articulounidadventa.getIdArticulo();
			if (idArticulo != null) {
				idArticulo = em.getReference(idArticulo.getClass(),
						idArticulo.getId());
				articulounidadventa.setIdArticulo(idArticulo);
			}
			em.persist(articulounidadventa);
			if (idUnidadventa != null) {
				idUnidadventa.getArticulounidadventaList().add(
						articulounidadventa);
				idUnidadventa = em.merge(idUnidadventa);
			}
			if (idArticulo != null) {
				idArticulo.getArticulounidadventaList()
						.add(articulounidadventa);
				idArticulo = em.merge(idArticulo);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findArticulounidadventa(articulounidadventa.getId()) != null) {
				throw new PreexistingEntityException("Articulounidadventa "
						+ articulounidadventa + " already exists.", ex);
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
	 * @param articulounidadventa
	 *            Articulounidadventa
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Articulounidadventa articulounidadventa)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Articulounidadventa persistentArticulounidadventa = em.find(
					Articulounidadventa.class, articulounidadventa.getId());
			Unidadventa idUnidadventaOld = persistentArticulounidadventa
					.getIdUnidadventa();
			Unidadventa idUnidadventaNew = articulounidadventa
					.getIdUnidadventa();
			Articulo idArticuloOld = persistentArticulounidadventa
					.getIdArticulo();
			Articulo idArticuloNew = articulounidadventa.getIdArticulo();
			if (idUnidadventaNew != null) {
				idUnidadventaNew = em.getReference(idUnidadventaNew.getClass(),
						idUnidadventaNew.getId());
				articulounidadventa.setIdUnidadventa(idUnidadventaNew);
			}
			if (idArticuloNew != null) {
				idArticuloNew = em.getReference(idArticuloNew.getClass(),
						idArticuloNew.getId());
				articulounidadventa.setIdArticulo(idArticuloNew);
			}
			articulounidadventa = em.merge(articulounidadventa);
			if (idUnidadventaOld != null
					&& !idUnidadventaOld.equals(idUnidadventaNew)) {
				idUnidadventaOld.getArticulounidadventaList().remove(
						articulounidadventa);
				idUnidadventaOld = em.merge(idUnidadventaOld);
			}
			if (idUnidadventaNew != null
					&& !idUnidadventaNew.equals(idUnidadventaOld)) {
				idUnidadventaNew.getArticulounidadventaList().add(
						articulounidadventa);
				idUnidadventaNew = em.merge(idUnidadventaNew);
			}
			if (idArticuloOld != null && !idArticuloOld.equals(idArticuloNew)) {
				idArticuloOld.getArticulounidadventaList().remove(
						articulounidadventa);
				idArticuloOld = em.merge(idArticuloOld);
			}
			if (idArticuloNew != null && !idArticuloNew.equals(idArticuloOld)) {
				idArticuloNew.getArticulounidadventaList().add(
						articulounidadventa);
				idArticuloNew = em.merge(idArticuloNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = articulounidadventa.getId();
				if (findArticulounidadventa(id) == null) {
					throw new NonexistentEntityException(
							"The articulounidadventa with id " + id
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
			Articulounidadventa articulounidadventa;
			try {
				articulounidadventa = em.getReference(
						Articulounidadventa.class, id);
				articulounidadventa.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The articulounidadventa with id " + id
								+ " no longer exists.", enfe);
			}
			Unidadventa idUnidadventa = articulounidadventa.getIdUnidadventa();
			if (idUnidadventa != null) {
				idUnidadventa.getArticulounidadventaList().remove(
						articulounidadventa);
				idUnidadventa = em.merge(idUnidadventa);
			}
			Articulo idArticulo = articulounidadventa.getIdArticulo();
			if (idArticulo != null) {
				idArticulo.getArticulounidadventaList().remove(
						articulounidadventa);
				idArticulo = em.merge(idArticulo);
			}
			em.remove(articulounidadventa);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findArticulounidadventaEntities.
	 * 
	 * @return List<Articulounidadventa>
	 */
	public List<Articulounidadventa> findArticulounidadventaEntities() {
		return findArticulounidadventaEntities(true, -1, -1);
	}

	/**
	 * Method findArticulounidadventaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Articulounidadventa>
	 */
	public List<Articulounidadventa> findArticulounidadventaEntities(
			int maxResults, int firstResult) {
		return findArticulounidadventaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findArticulounidadventaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Articulounidadventa>
	 */
	private List<Articulounidadventa> findArticulounidadventaEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Articulounidadventa.class));
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
	 * Method findArticulounidadventa.
	 * 
	 * @param id
	 *            Long
	 * @return Articulounidadventa
	 */
	public Articulounidadventa findArticulounidadventa(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Articulounidadventa.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getArticulounidadventaCount.
	 * 
	 * @return int
	 */
	public int getArticulounidadventaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Articulounidadventa> rt = cq.from(Articulounidadventa.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
