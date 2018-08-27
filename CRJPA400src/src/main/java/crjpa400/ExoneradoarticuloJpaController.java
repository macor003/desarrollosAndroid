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
public class ExoneradoarticuloJpaController implements Serializable {

	/**
	 * Constructor for ExoneradoarticuloJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ExoneradoarticuloJpaController(EntityManagerFactory emf) {
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
	 * @param exoneradoarticulo
	 *            Exoneradoarticulo
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Exoneradoarticulo exoneradoarticulo)
			throws PreexistingEntityException, Exception {
		if (exoneradoarticulo.getExoneradoarticuloPK() == null) {
			exoneradoarticulo.setExoneradoarticuloPK(new ExoneradoarticuloPK());
		}
		exoneradoarticulo.getExoneradoarticuloPK().setIdExoneradotipo(
				exoneradoarticulo.getExoneradotipo().getId());
		exoneradoarticulo.getExoneradoarticuloPK().setIdArticulo(
				exoneradoarticulo.getArticulo().getId());
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Exoneradotipo exoneradotipo = exoneradoarticulo.getExoneradotipo();
			if (exoneradotipo != null) {
				exoneradotipo = em.getReference(exoneradotipo.getClass(),
						exoneradotipo.getId());
				exoneradoarticulo.setExoneradotipo(exoneradotipo);
			}
			Articulo articulo = exoneradoarticulo.getArticulo();
			if (articulo != null) {
				articulo = em.getReference(articulo.getClass(),
						articulo.getId());
				exoneradoarticulo.setArticulo(articulo);
			}
			em.persist(exoneradoarticulo);
			if (exoneradotipo != null) {
				exoneradotipo.getExoneradoarticuloList().add(exoneradoarticulo);
				exoneradotipo = em.merge(exoneradotipo);
			}
			if (articulo != null) {
				articulo.getExoneradoarticuloList().add(exoneradoarticulo);
				articulo = em.merge(articulo);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findExoneradoarticulo(exoneradoarticulo
					.getExoneradoarticuloPK()) != null) {
				throw new PreexistingEntityException("Exoneradoarticulo "
						+ exoneradoarticulo + " already exists.", ex);
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
	 * @param exoneradoarticulo
	 *            Exoneradoarticulo
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Exoneradoarticulo exoneradoarticulo)
			throws NonexistentEntityException, Exception {
		exoneradoarticulo.getExoneradoarticuloPK().setIdExoneradotipo(
				exoneradoarticulo.getExoneradotipo().getId());
		exoneradoarticulo.getExoneradoarticuloPK().setIdArticulo(
				exoneradoarticulo.getArticulo().getId());
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Exoneradoarticulo persistentExoneradoarticulo = em.find(
					Exoneradoarticulo.class,
					exoneradoarticulo.getExoneradoarticuloPK());
			Exoneradotipo exoneradotipoOld = persistentExoneradoarticulo
					.getExoneradotipo();
			Exoneradotipo exoneradotipoNew = exoneradoarticulo
					.getExoneradotipo();
			Articulo articuloOld = persistentExoneradoarticulo.getArticulo();
			Articulo articuloNew = exoneradoarticulo.getArticulo();
			if (exoneradotipoNew != null) {
				exoneradotipoNew = em.getReference(exoneradotipoNew.getClass(),
						exoneradotipoNew.getId());
				exoneradoarticulo.setExoneradotipo(exoneradotipoNew);
			}
			if (articuloNew != null) {
				articuloNew = em.getReference(articuloNew.getClass(),
						articuloNew.getId());
				exoneradoarticulo.setArticulo(articuloNew);
			}
			exoneradoarticulo = em.merge(exoneradoarticulo);
			if (exoneradotipoOld != null
					&& !exoneradotipoOld.equals(exoneradotipoNew)) {
				exoneradotipoOld.getExoneradoarticuloList().remove(
						exoneradoarticulo);
				exoneradotipoOld = em.merge(exoneradotipoOld);
			}
			if (exoneradotipoNew != null
					&& !exoneradotipoNew.equals(exoneradotipoOld)) {
				exoneradotipoNew.getExoneradoarticuloList().add(
						exoneradoarticulo);
				exoneradotipoNew = em.merge(exoneradotipoNew);
			}
			if (articuloOld != null && !articuloOld.equals(articuloNew)) {
				articuloOld.getExoneradoarticuloList()
						.remove(exoneradoarticulo);
				articuloOld = em.merge(articuloOld);
			}
			if (articuloNew != null && !articuloNew.equals(articuloOld)) {
				articuloNew.getExoneradoarticuloList().add(exoneradoarticulo);
				articuloNew = em.merge(articuloNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				ExoneradoarticuloPK id = exoneradoarticulo
						.getExoneradoarticuloPK();
				if (findExoneradoarticulo(id) == null) {
					throw new NonexistentEntityException(
							"The exoneradoarticulo with id " + id
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
	 *            ExoneradoarticuloPK
	 * @throws NonexistentEntityException
	 */
	public void destroy(ExoneradoarticuloPK id)
			throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Exoneradoarticulo exoneradoarticulo;
			try {
				exoneradoarticulo = em
						.getReference(Exoneradoarticulo.class, id);
				exoneradoarticulo.getExoneradoarticuloPK();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The exoneradoarticulo with id " + id
								+ " no longer exists.", enfe);
			}
			Exoneradotipo exoneradotipo = exoneradoarticulo.getExoneradotipo();
			if (exoneradotipo != null) {
				exoneradotipo.getExoneradoarticuloList().remove(
						exoneradoarticulo);
				exoneradotipo = em.merge(exoneradotipo);
			}
			Articulo articulo = exoneradoarticulo.getArticulo();
			if (articulo != null) {
				articulo.getExoneradoarticuloList().remove(exoneradoarticulo);
				articulo = em.merge(articulo);
			}
			em.remove(exoneradoarticulo);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findExoneradoarticuloEntities.
	 * 
	 * @return List<Exoneradoarticulo>
	 */
	public List<Exoneradoarticulo> findExoneradoarticuloEntities() {
		return findExoneradoarticuloEntities(true, -1, -1);
	}

	/**
	 * Method findExoneradoarticuloEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Exoneradoarticulo>
	 */
	public List<Exoneradoarticulo> findExoneradoarticuloEntities(
			int maxResults, int firstResult) {
		return findExoneradoarticuloEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findExoneradoarticuloEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Exoneradoarticulo>
	 */
	private List<Exoneradoarticulo> findExoneradoarticuloEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Exoneradoarticulo.class));
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
	 * Method findExoneradoarticulo.
	 * 
	 * @param id
	 *            ExoneradoarticuloPK
	 * @return Exoneradoarticulo
	 */
	public Exoneradoarticulo findExoneradoarticulo(ExoneradoarticuloPK id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Exoneradoarticulo.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getExoneradoarticuloCount.
	 * 
	 * @return int
	 */
	public int getExoneradoarticuloCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Exoneradoarticulo> rt = cq.from(Exoneradoarticulo.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
