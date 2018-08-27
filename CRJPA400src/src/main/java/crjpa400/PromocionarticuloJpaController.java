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
public class PromocionarticuloJpaController implements Serializable {

	/**
	 * Constructor for PromocionarticuloJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public PromocionarticuloJpaController(EntityManagerFactory emf) {
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
	 * @param promocionarticulo
	 *            Promocionarticulo
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Promocionarticulo promocionarticulo)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Articulo idArticulo = promocionarticulo.getIdArticulo();
			if (idArticulo != null) {
				idArticulo = em.getReference(idArticulo.getClass(),
						idArticulo.getId());
				promocionarticulo.setIdArticulo(idArticulo);
			}
			Promocion idPromocion = promocionarticulo.getIdPromocion();
			if (idPromocion != null) {
				idPromocion = em.getReference(idPromocion.getClass(),
						idPromocion.getId());
				promocionarticulo.setIdPromocion(idPromocion);
			}
			em.persist(promocionarticulo);
			if (idArticulo != null) {
				idArticulo.getPromocionarticuloList().add(promocionarticulo);
				idArticulo = em.merge(idArticulo);
			}
			if (idPromocion != null) {
				idPromocion.getPromocionarticuloList().add(promocionarticulo);
				idPromocion = em.merge(idPromocion);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findPromocionarticulo(promocionarticulo.getId()) != null) {
				throw new PreexistingEntityException("Promocionarticulo "
						+ promocionarticulo + " already exists.", ex);
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
	 * @param promocionarticulo
	 *            Promocionarticulo
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Promocionarticulo promocionarticulo)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Promocionarticulo persistentPromocionarticulo = em.find(
					Promocionarticulo.class, promocionarticulo.getId());
			Articulo idArticuloOld = persistentPromocionarticulo
					.getIdArticulo();
			Articulo idArticuloNew = promocionarticulo.getIdArticulo();
			Promocion idPromocionOld = persistentPromocionarticulo
					.getIdPromocion();
			Promocion idPromocionNew = promocionarticulo.getIdPromocion();
			if (idArticuloNew != null) {
				idArticuloNew = em.getReference(idArticuloNew.getClass(),
						idArticuloNew.getId());
				promocionarticulo.setIdArticulo(idArticuloNew);
			}
			if (idPromocionNew != null) {
				idPromocionNew = em.getReference(idPromocionNew.getClass(),
						idPromocionNew.getId());
				promocionarticulo.setIdPromocion(idPromocionNew);
			}
			promocionarticulo = em.merge(promocionarticulo);
			if (idArticuloOld != null && !idArticuloOld.equals(idArticuloNew)) {
				idArticuloOld.getPromocionarticuloList().remove(
						promocionarticulo);
				idArticuloOld = em.merge(idArticuloOld);
			}
			if (idArticuloNew != null && !idArticuloNew.equals(idArticuloOld)) {
				idArticuloNew.getPromocionarticuloList().add(promocionarticulo);
				idArticuloNew = em.merge(idArticuloNew);
			}
			if (idPromocionOld != null
					&& !idPromocionOld.equals(idPromocionNew)) {
				idPromocionOld.getPromocionarticuloList().remove(
						promocionarticulo);
				idPromocionOld = em.merge(idPromocionOld);
			}
			if (idPromocionNew != null
					&& !idPromocionNew.equals(idPromocionOld)) {
				idPromocionNew.getPromocionarticuloList()
						.add(promocionarticulo);
				idPromocionNew = em.merge(idPromocionNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = promocionarticulo.getId();
				if (findPromocionarticulo(id) == null) {
					throw new NonexistentEntityException(
							"The promocionarticulo with id " + id
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
			Promocionarticulo promocionarticulo;
			try {
				promocionarticulo = em
						.getReference(Promocionarticulo.class, id);
				promocionarticulo.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The promocionarticulo with id " + id
								+ " no longer exists.", enfe);
			}
			Articulo idArticulo = promocionarticulo.getIdArticulo();
			if (idArticulo != null) {
				idArticulo.getPromocionarticuloList().remove(promocionarticulo);
				idArticulo = em.merge(idArticulo);
			}
			Promocion idPromocion = promocionarticulo.getIdPromocion();
			if (idPromocion != null) {
				idPromocion.getPromocionarticuloList()
						.remove(promocionarticulo);
				idPromocion = em.merge(idPromocion);
			}
			em.remove(promocionarticulo);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findPromocionarticuloEntities.
	 * 
	 * @return List<Promocionarticulo>
	 */
	public List<Promocionarticulo> findPromocionarticuloEntities() {
		return findPromocionarticuloEntities(true, -1, -1);
	}

	/**
	 * Method findPromocionarticuloEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Promocionarticulo>
	 */
	public List<Promocionarticulo> findPromocionarticuloEntities(
			int maxResults, int firstResult) {
		return findPromocionarticuloEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findPromocionarticuloEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Promocionarticulo>
	 */
	private List<Promocionarticulo> findPromocionarticuloEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Promocionarticulo.class));
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
	 * Method findPromocionarticulo.
	 * 
	 * @param id
	 *            Long
	 * @return Promocionarticulo
	 */
	public Promocionarticulo findPromocionarticulo(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Promocionarticulo.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getPromocionarticuloCount.
	 * 
	 * @return int
	 */
	public int getPromocionarticuloCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Promocionarticulo> rt = cq.from(Promocionarticulo.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
