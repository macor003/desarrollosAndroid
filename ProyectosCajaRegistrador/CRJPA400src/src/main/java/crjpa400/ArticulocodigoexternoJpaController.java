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
public class ArticulocodigoexternoJpaController implements Serializable {

	/**
	 * Constructor for ArticulocodigoexternoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ArticulocodigoexternoJpaController(EntityManagerFactory emf) {
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
	 * @param articulocodigoexterno
	 *            Articulocodigoexterno
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Articulocodigoexterno articulocodigoexterno)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Articulo idArticulo = articulocodigoexterno.getIdArticulo();
			if (idArticulo != null) {
				idArticulo = em.getReference(idArticulo.getClass(),
						idArticulo.getId());
				articulocodigoexterno.setIdArticulo(idArticulo);
			}
			em.persist(articulocodigoexterno);
			if (idArticulo != null) {
				idArticulo.getArticulocodigoexternoList().add(
						articulocodigoexterno);
				idArticulo = em.merge(idArticulo);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findArticulocodigoexterno(articulocodigoexterno.getId()) != null) {
				throw new PreexistingEntityException("Articulocodigoexterno "
						+ articulocodigoexterno + " already exists.", ex);
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
	 * @param articulocodigoexterno
	 *            Articulocodigoexterno
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Articulocodigoexterno articulocodigoexterno)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Articulocodigoexterno persistentArticulocodigoexterno = em.find(
					Articulocodigoexterno.class, articulocodigoexterno.getId());
			Articulo idArticuloOld = persistentArticulocodigoexterno
					.getIdArticulo();
			Articulo idArticuloNew = articulocodigoexterno.getIdArticulo();
			if (idArticuloNew != null) {
				idArticuloNew = em.getReference(idArticuloNew.getClass(),
						idArticuloNew.getId());
				articulocodigoexterno.setIdArticulo(idArticuloNew);
			}
			articulocodigoexterno = em.merge(articulocodigoexterno);
			if (idArticuloOld != null && !idArticuloOld.equals(idArticuloNew)) {
				idArticuloOld.getArticulocodigoexternoList().remove(
						articulocodigoexterno);
				idArticuloOld = em.merge(idArticuloOld);
			}
			if (idArticuloNew != null && !idArticuloNew.equals(idArticuloOld)) {
				idArticuloNew.getArticulocodigoexternoList().add(
						articulocodigoexterno);
				idArticuloNew = em.merge(idArticuloNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = articulocodigoexterno.getId();
				if (findArticulocodigoexterno(id) == null) {
					throw new NonexistentEntityException(
							"The articulocodigoexterno with id " + id
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
			Articulocodigoexterno articulocodigoexterno;
			try {
				articulocodigoexterno = em.getReference(
						Articulocodigoexterno.class, id);
				articulocodigoexterno.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The articulocodigoexterno with id " + id
								+ " no longer exists.", enfe);
			}
			Articulo idArticulo = articulocodigoexterno.getIdArticulo();
			if (idArticulo != null) {
				idArticulo.getArticulocodigoexternoList().remove(
						articulocodigoexterno);
				idArticulo = em.merge(idArticulo);
			}
			em.remove(articulocodigoexterno);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findArticulocodigoexternoEntities.
	 * 
	 * @return List<Articulocodigoexterno>
	 */
	public List<Articulocodigoexterno> findArticulocodigoexternoEntities() {
		return findArticulocodigoexternoEntities(true, -1, -1);
	}

	/**
	 * Method findArticulocodigoexternoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Articulocodigoexterno>
	 */
	public List<Articulocodigoexterno> findArticulocodigoexternoEntities(
			int maxResults, int firstResult) {
		return findArticulocodigoexternoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findArticulocodigoexternoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Articulocodigoexterno>
	 */
	private List<Articulocodigoexterno> findArticulocodigoexternoEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Articulocodigoexterno.class));
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
	 * Method findArticulocodigoexterno.
	 * 
	 * @param id
	 *            Long
	 * @return Articulocodigoexterno
	 */
	public Articulocodigoexterno findArticulocodigoexterno(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Articulocodigoexterno.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getArticulocodigoexternoCount.
	 * 
	 * @return int
	 */
	public int getArticulocodigoexternoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Articulocodigoexterno> rt = cq
					.from(Articulocodigoexterno.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
