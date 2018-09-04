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
public class ArticuloservicioJpaController implements Serializable {

	/**
	 * Constructor for ArticuloservicioJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ArticuloservicioJpaController(EntityManagerFactory emf) {
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
	 * @param articuloservicio
	 *            Articuloservicio
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Articuloservicio articuloservicio)
			throws PreexistingEntityException, Exception {
		if (articuloservicio.getArticuloservicioPK() == null) {
			articuloservicio.setArticuloservicioPK(new ArticuloservicioPK());
		}
		articuloservicio.getArticuloservicioPK().setIdServicio(
				articuloservicio.getServicio().getId());
		articuloservicio.getArticuloservicioPK().setIdArticulo(
				articuloservicio.getArticulo().getId());
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Servicio servicio = articuloservicio.getServicio();
			if (servicio != null) {
				servicio = em.getReference(servicio.getClass(),
						servicio.getId());
				articuloservicio.setServicio(servicio);
			}
			Articulo articulo = articuloservicio.getArticulo();
			if (articulo != null) {
				articulo = em.getReference(articulo.getClass(),
						articulo.getId());
				articuloservicio.setArticulo(articulo);
			}
			em.persist(articuloservicio);
			if (servicio != null) {
				servicio.getArticuloservicioList().add(articuloservicio);
				servicio = em.merge(servicio);
			}
			if (articulo != null) {
				articulo.getArticuloservicioList().add(articuloservicio);
				articulo = em.merge(articulo);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findArticuloservicio(articuloservicio.getArticuloservicioPK()) != null) {
				throw new PreexistingEntityException("Articuloservicio "
						+ articuloservicio + " already exists.", ex);
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
	 * @param articuloservicio
	 *            Articuloservicio
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Articuloservicio articuloservicio)
			throws NonexistentEntityException, Exception {
		articuloservicio.getArticuloservicioPK().setIdServicio(
				articuloservicio.getServicio().getId());
		articuloservicio.getArticuloservicioPK().setIdArticulo(
				articuloservicio.getArticulo().getId());
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Articuloservicio persistentArticuloservicio = em.find(
					Articuloservicio.class,
					articuloservicio.getArticuloservicioPK());
			Servicio servicioOld = persistentArticuloservicio.getServicio();
			Servicio servicioNew = articuloservicio.getServicio();
			Articulo articuloOld = persistentArticuloservicio.getArticulo();
			Articulo articuloNew = articuloservicio.getArticulo();
			if (servicioNew != null) {
				servicioNew = em.getReference(servicioNew.getClass(),
						servicioNew.getId());
				articuloservicio.setServicio(servicioNew);
			}
			if (articuloNew != null) {
				articuloNew = em.getReference(articuloNew.getClass(),
						articuloNew.getId());
				articuloservicio.setArticulo(articuloNew);
			}
			articuloservicio = em.merge(articuloservicio);
			if (servicioOld != null && !servicioOld.equals(servicioNew)) {
				servicioOld.getArticuloservicioList().remove(articuloservicio);
				servicioOld = em.merge(servicioOld);
			}
			if (servicioNew != null && !servicioNew.equals(servicioOld)) {
				servicioNew.getArticuloservicioList().add(articuloservicio);
				servicioNew = em.merge(servicioNew);
			}
			if (articuloOld != null && !articuloOld.equals(articuloNew)) {
				articuloOld.getArticuloservicioList().remove(articuloservicio);
				articuloOld = em.merge(articuloOld);
			}
			if (articuloNew != null && !articuloNew.equals(articuloOld)) {
				articuloNew.getArticuloservicioList().add(articuloservicio);
				articuloNew = em.merge(articuloNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				ArticuloservicioPK id = articuloservicio
						.getArticuloservicioPK();
				if (findArticuloservicio(id) == null) {
					throw new NonexistentEntityException(
							"The articuloservicio with id " + id
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
	 *            ArticuloservicioPK
	 * @throws NonexistentEntityException
	 */
	public void destroy(ArticuloservicioPK id)
			throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Articuloservicio articuloservicio;
			try {
				articuloservicio = em.getReference(Articuloservicio.class, id);
				articuloservicio.getArticuloservicioPK();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The articuloservicio with id " + id
								+ " no longer exists.", enfe);
			}
			Servicio servicio = articuloservicio.getServicio();
			if (servicio != null) {
				servicio.getArticuloservicioList().remove(articuloservicio);
				servicio = em.merge(servicio);
			}
			Articulo articulo = articuloservicio.getArticulo();
			if (articulo != null) {
				articulo.getArticuloservicioList().remove(articuloservicio);
				articulo = em.merge(articulo);
			}
			em.remove(articuloservicio);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findArticuloservicioEntities.
	 * 
	 * @return List<Articuloservicio>
	 */
	public List<Articuloservicio> findArticuloservicioEntities() {
		return findArticuloservicioEntities(true, -1, -1);
	}

	/**
	 * Method findArticuloservicioEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Articuloservicio>
	 */
	public List<Articuloservicio> findArticuloservicioEntities(int maxResults,
			int firstResult) {
		return findArticuloservicioEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findArticuloservicioEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Articuloservicio>
	 */
	private List<Articuloservicio> findArticuloservicioEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Articuloservicio.class));
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
	 * Method findArticuloservicio.
	 * 
	 * @param id
	 *            ArticuloservicioPK
	 * @return Articuloservicio
	 */
	public Articuloservicio findArticuloservicio(ArticuloservicioPK id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Articuloservicio.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getArticuloservicioCount.
	 * 
	 * @return int
	 */
	public int getArticuloservicioCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Articuloservicio> rt = cq.from(Articuloservicio.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
