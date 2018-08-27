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
public class OrdendeventaarticuloJpaController implements Serializable {

	/**
	 * Constructor for OrdendeventaarticuloJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public OrdendeventaarticuloJpaController(EntityManagerFactory emf) {
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
	 * @param ordendeventaarticulo
	 *            Ordendeventaarticulo
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Ordendeventaarticulo ordendeventaarticulo)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipodescuento idTipodescuento = ordendeventaarticulo
					.getIdTipodescuento();
			if (idTipodescuento != null) {
				idTipodescuento = em.getReference(idTipodescuento.getClass(),
						idTipodescuento.getId());
				ordendeventaarticulo.setIdTipodescuento(idTipodescuento);
			}
			Tasaimpuestovalor idTasaimpuestovalor = ordendeventaarticulo
					.getIdTasaimpuestovalor();
			if (idTasaimpuestovalor != null) {
				idTasaimpuestovalor = em.getReference(
						idTasaimpuestovalor.getClass(),
						idTasaimpuestovalor.getId());
				ordendeventaarticulo
						.setIdTasaimpuestovalor(idTasaimpuestovalor);
			}
			Unidadventa idUnidadventa = ordendeventaarticulo.getIdUnidadventa();
			if (idUnidadventa != null) {
				idUnidadventa = em.getReference(idUnidadventa.getClass(),
						idUnidadventa.getId());
				ordendeventaarticulo.setIdUnidadventa(idUnidadventa);
			}
			Ordendeventa idOrdendeventa = ordendeventaarticulo
					.getIdOrdendeventa();
			if (idOrdendeventa != null) {
				idOrdendeventa = em.getReference(idOrdendeventa.getClass(),
						idOrdendeventa.getId());
				ordendeventaarticulo.setIdOrdendeventa(idOrdendeventa);
			}
			Articulo idArticulo = ordendeventaarticulo.getIdArticulo();
			if (idArticulo != null) {
				idArticulo = em.getReference(idArticulo.getClass(),
						idArticulo.getId());
				ordendeventaarticulo.setIdArticulo(idArticulo);
			}
			em.persist(ordendeventaarticulo);
			if (idTipodescuento != null) {
				idTipodescuento.getOrdendeventaarticuloList()
						.add(ordendeventaarticulo);
				idTipodescuento = em.merge(idTipodescuento);
			}
			if (idTasaimpuestovalor != null) {
				idTasaimpuestovalor.getOrdendeventaarticuloList()
						.add(ordendeventaarticulo);
				idTasaimpuestovalor = em.merge(idTasaimpuestovalor);
			}
			if (idUnidadventa != null) {
				idUnidadventa.getOrdendeventaarticuloList()
						.add(ordendeventaarticulo);
				idUnidadventa = em.merge(idUnidadventa);
			}
			if (idOrdendeventa != null) {
				idOrdendeventa.getOrdendeventaarticuloList()
						.add(ordendeventaarticulo);
				idOrdendeventa = em.merge(idOrdendeventa);
			}
			if (idArticulo != null) {
				idArticulo.getOrdendeventaarticuloList()
						.add(ordendeventaarticulo);
				idArticulo = em.merge(idArticulo);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findOrdendeventaarticulo(
					ordendeventaarticulo.getId()) != null) {
				throw new PreexistingEntityException("Ordendeventaarticulo "
						+ ordendeventaarticulo + " already exists.", ex);
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
	 * @param ordendeventaarticulo
	 *            Ordendeventaarticulo
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Ordendeventaarticulo ordendeventaarticulo)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Ordendeventaarticulo persistentOrdendeventaarticulo = em.find(
					Ordendeventaarticulo.class, ordendeventaarticulo.getId());
			Tipodescuento idTipodescuentoOld = persistentOrdendeventaarticulo
					.getIdTipodescuento();
			Tipodescuento idTipodescuentoNew = ordendeventaarticulo
					.getIdTipodescuento();
			Tasaimpuestovalor idTasaimpuestovalorOld = persistentOrdendeventaarticulo
					.getIdTasaimpuestovalor();
			Tasaimpuestovalor idTasaimpuestovalorNew = ordendeventaarticulo
					.getIdTasaimpuestovalor();
			Unidadventa idUnidadventaOld = persistentOrdendeventaarticulo
					.getIdUnidadventa();
			Unidadventa idUnidadventaNew = ordendeventaarticulo
					.getIdUnidadventa();
			Ordendeventa idOrdendeventaOld = persistentOrdendeventaarticulo
					.getIdOrdendeventa();
			Ordendeventa idOrdendeventaNew = ordendeventaarticulo
					.getIdOrdendeventa();
			Articulo idArticuloOld = persistentOrdendeventaarticulo
					.getIdArticulo();
			Articulo idArticuloNew = ordendeventaarticulo.getIdArticulo();
			if (idTipodescuentoNew != null) {
				idTipodescuentoNew = em.getReference(
						idTipodescuentoNew.getClass(),
						idTipodescuentoNew.getId());
				ordendeventaarticulo.setIdTipodescuento(idTipodescuentoNew);
			}
			if (idTasaimpuestovalorNew != null) {
				idTasaimpuestovalorNew = em.getReference(
						idTasaimpuestovalorNew.getClass(),
						idTasaimpuestovalorNew.getId());
				ordendeventaarticulo
						.setIdTasaimpuestovalor(idTasaimpuestovalorNew);
			}
			if (idUnidadventaNew != null) {
				idUnidadventaNew = em.getReference(idUnidadventaNew.getClass(),
						idUnidadventaNew.getId());
				ordendeventaarticulo.setIdUnidadventa(idUnidadventaNew);
			}
			if (idOrdendeventaNew != null) {
				idOrdendeventaNew = em.getReference(
						idOrdendeventaNew.getClass(),
						idOrdendeventaNew.getId());
				ordendeventaarticulo.setIdOrdendeventa(idOrdendeventaNew);
			}
			if (idArticuloNew != null) {
				idArticuloNew = em.getReference(idArticuloNew.getClass(),
						idArticuloNew.getId());
				ordendeventaarticulo.setIdArticulo(idArticuloNew);
			}
			ordendeventaarticulo = em.merge(ordendeventaarticulo);
			if (idTipodescuentoOld != null
					&& !idTipodescuentoOld.equals(idTipodescuentoNew)) {
				idTipodescuentoOld.getOrdendeventaarticuloList()
						.remove(ordendeventaarticulo);
				idTipodescuentoOld = em.merge(idTipodescuentoOld);
			}
			if (idTipodescuentoNew != null
					&& !idTipodescuentoNew.equals(idTipodescuentoOld)) {
				idTipodescuentoNew.getOrdendeventaarticuloList()
						.add(ordendeventaarticulo);
				idTipodescuentoNew = em.merge(idTipodescuentoNew);
			}
			if (idTasaimpuestovalorOld != null
					&& !idTasaimpuestovalorOld.equals(idTasaimpuestovalorNew)) {
				idTasaimpuestovalorOld.getOrdendeventaarticuloList()
						.remove(ordendeventaarticulo);
				idTasaimpuestovalorOld = em.merge(idTasaimpuestovalorOld);
			}
			if (idTasaimpuestovalorNew != null
					&& !idTasaimpuestovalorNew.equals(idTasaimpuestovalorOld)) {
				idTasaimpuestovalorNew.getOrdendeventaarticuloList()
						.add(ordendeventaarticulo);
				idTasaimpuestovalorNew = em.merge(idTasaimpuestovalorNew);
			}
			if (idUnidadventaOld != null
					&& !idUnidadventaOld.equals(idUnidadventaNew)) {
				idUnidadventaOld.getOrdendeventaarticuloList()
						.remove(ordendeventaarticulo);
				idUnidadventaOld = em.merge(idUnidadventaOld);
			}
			if (idUnidadventaNew != null
					&& !idUnidadventaNew.equals(idUnidadventaOld)) {
				idUnidadventaNew.getOrdendeventaarticuloList()
						.add(ordendeventaarticulo);
				idUnidadventaNew = em.merge(idUnidadventaNew);
			}
			if (idOrdendeventaOld != null
					&& !idOrdendeventaOld.equals(idOrdendeventaNew)) {
				idOrdendeventaOld.getOrdendeventaarticuloList()
						.remove(ordendeventaarticulo);
				idOrdendeventaOld = em.merge(idOrdendeventaOld);
			}
			if (idOrdendeventaNew != null
					&& !idOrdendeventaNew.equals(idOrdendeventaOld)) {
				idOrdendeventaNew.getOrdendeventaarticuloList()
						.add(ordendeventaarticulo);
				idOrdendeventaNew = em.merge(idOrdendeventaNew);
			}
			if (idArticuloOld != null && !idArticuloOld.equals(idArticuloNew)) {
				idArticuloOld.getOrdendeventaarticuloList()
						.remove(ordendeventaarticulo);
				idArticuloOld = em.merge(idArticuloOld);
			}
			if (idArticuloNew != null && !idArticuloNew.equals(idArticuloOld)) {
				idArticuloNew.getOrdendeventaarticuloList()
						.add(ordendeventaarticulo);
				idArticuloNew = em.merge(idArticuloNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = ordendeventaarticulo.getId();
				if (findOrdendeventaarticulo(id) == null) {
					throw new NonexistentEntityException(
							"The ordendeventaarticulo with id " + id
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
			Ordendeventaarticulo ordendeventaarticulo;
			try {
				ordendeventaarticulo = em
						.getReference(Ordendeventaarticulo.class, id);
				ordendeventaarticulo.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The ordendeventaarticulo with id " + id
								+ " no longer exists.",
						enfe);
			}
			Tipodescuento idTipodescuento = ordendeventaarticulo
					.getIdTipodescuento();
			if (idTipodescuento != null) {
				idTipodescuento.getOrdendeventaarticuloList()
						.remove(ordendeventaarticulo);
				idTipodescuento = em.merge(idTipodescuento);
			}
			Tasaimpuestovalor idTasaimpuestovalor = ordendeventaarticulo
					.getIdTasaimpuestovalor();
			if (idTasaimpuestovalor != null) {
				idTasaimpuestovalor.getOrdendeventaarticuloList()
						.remove(ordendeventaarticulo);
				idTasaimpuestovalor = em.merge(idTasaimpuestovalor);
			}
			Unidadventa idUnidadventa = ordendeventaarticulo.getIdUnidadventa();
			if (idUnidadventa != null) {
				idUnidadventa.getOrdendeventaarticuloList()
						.remove(ordendeventaarticulo);
				idUnidadventa = em.merge(idUnidadventa);
			}
			Ordendeventa idOrdendeventa = ordendeventaarticulo
					.getIdOrdendeventa();
			if (idOrdendeventa != null) {
				idOrdendeventa.getOrdendeventaarticuloList()
						.remove(ordendeventaarticulo);
				idOrdendeventa = em.merge(idOrdendeventa);
			}
			Articulo idArticulo = ordendeventaarticulo.getIdArticulo();
			if (idArticulo != null) {
				idArticulo.getOrdendeventaarticuloList()
						.remove(ordendeventaarticulo);
				idArticulo = em.merge(idArticulo);
			}
			em.remove(ordendeventaarticulo);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findOrdendeventaarticuloEntities.
	 * 
	 * @return List<Ordendeventaarticulo>
	 */
	public List<Ordendeventaarticulo> findOrdendeventaarticuloEntities() {
		return findOrdendeventaarticuloEntities(true, -1, -1);
	}

	/**
	 * Method findOrdendeventaarticuloEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Ordendeventaarticulo>
	 */
	public List<Ordendeventaarticulo> findOrdendeventaarticuloEntities(
			int maxResults, int firstResult) {
		return findOrdendeventaarticuloEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findOrdendeventaarticuloEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Ordendeventaarticulo>
	 */
	private List<Ordendeventaarticulo> findOrdendeventaarticuloEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Ordendeventaarticulo.class));
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
	 * Method findOrdendeventaarticulo.
	 * 
	 * @param id
	 *            Long
	 * @return Ordendeventaarticulo
	 */
	public Ordendeventaarticulo findOrdendeventaarticulo(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Ordendeventaarticulo.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getOrdendeventaarticuloCount.
	 * 
	 * @return int
	 */
	public int getOrdendeventaarticuloCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Ordendeventaarticulo> rt = cq.from(Ordendeventaarticulo.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

	public void editWithQuery(Ordendeventaarticulo ordendeventaarticulo)
			throws Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();

			// Articulo articulo = em.find(Articulo.class, );
			Ordendeventa ordendeventa = ordendeventaarticulo
					.getIdOrdendeventa();
			Estadoordendeventa estadoordendeventa = em
					.find(Estadoordendeventa.class, 5l);
			Long id = ordendeventaarticulo.getId();

			Query query = em.createQuery(
					"UPDATE Ordendeventaarticulo SET idEstadoordendenventa = :ID_ESTADOORDENDEVENTA "
							+ "WHERE id = :ID AND idOrdendeventa = :ORDENDEVENTA");

			query.setParameter("ID_ESTADOORDENDEVENTA", estadoordendeventa);
			query.setParameter("ID", id);
			// query.setParameter("ARTICULO", articulo);
			query.setParameter("ORDENDEVENTA", ordendeventa);
			int i = query.executeUpdate();

			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			System.out.println("msg = " + msg);
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

}
