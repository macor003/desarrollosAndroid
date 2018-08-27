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
public class TransaccionarticuloservicioJpaController implements Serializable {

	/**
	 * Constructor for TransaccionarticuloservicioJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TransaccionarticuloservicioJpaController(EntityManagerFactory emf) {
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
	 * @param transaccionarticuloservicio
	 *            Transaccionarticuloservicio
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Transaccionarticuloservicio transaccionarticuloservicio)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Servicio idServicio = transaccionarticuloservicio.getIdServicio();
			if (idServicio != null) {
				idServicio = em.getReference(idServicio.getClass(),
						idServicio.getId());
				transaccionarticuloservicio.setIdServicio(idServicio);
			}
			Transaccionarticulo idTransaccionarticulo = transaccionarticuloservicio
					.getIdTransaccionarticulo();
			if (idTransaccionarticulo != null) {
				idTransaccionarticulo = em.getReference(
						idTransaccionarticulo.getClass(),
						idTransaccionarticulo.getId());
				transaccionarticuloservicio
						.setIdTransaccionarticulo(idTransaccionarticulo);
			}
			em.persist(transaccionarticuloservicio);
			if (idServicio != null) {
				idServicio.getTransaccionarticuloservicioList().add(
						transaccionarticuloservicio);
				idServicio = em.merge(idServicio);
			}
			if (idTransaccionarticulo != null) {
				idTransaccionarticulo.getTransaccionarticuloservicioList().add(
						transaccionarticuloservicio);
				idTransaccionarticulo = em.merge(idTransaccionarticulo);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTransaccionarticuloservicio(transaccionarticuloservicio
					.getId()) != null) {
				throw new PreexistingEntityException(
						"Transaccionarticuloservicio "
								+ transaccionarticuloservicio
								+ " already exists.", ex);
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
	 * @param transaccionarticuloservicio
	 *            Transaccionarticuloservicio
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Transaccionarticuloservicio transaccionarticuloservicio)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Transaccionarticuloservicio persistentTransaccionarticuloservicio = em
					.find(Transaccionarticuloservicio.class,
							transaccionarticuloservicio.getId());
			Servicio idServicioOld = persistentTransaccionarticuloservicio
					.getIdServicio();
			Servicio idServicioNew = transaccionarticuloservicio
					.getIdServicio();
			Transaccionarticulo idTransaccionarticuloOld = persistentTransaccionarticuloservicio
					.getIdTransaccionarticulo();
			Transaccionarticulo idTransaccionarticuloNew = transaccionarticuloservicio
					.getIdTransaccionarticulo();
			if (idServicioNew != null) {
				idServicioNew = em.getReference(idServicioNew.getClass(),
						idServicioNew.getId());
				transaccionarticuloservicio.setIdServicio(idServicioNew);
			}
			if (idTransaccionarticuloNew != null) {
				idTransaccionarticuloNew = em.getReference(
						idTransaccionarticuloNew.getClass(),
						idTransaccionarticuloNew.getId());
				transaccionarticuloservicio
						.setIdTransaccionarticulo(idTransaccionarticuloNew);
			}
			transaccionarticuloservicio = em.merge(transaccionarticuloservicio);
			if (idServicioOld != null && !idServicioOld.equals(idServicioNew)) {
				idServicioOld.getTransaccionarticuloservicioList().remove(
						transaccionarticuloservicio);
				idServicioOld = em.merge(idServicioOld);
			}
			if (idServicioNew != null && !idServicioNew.equals(idServicioOld)) {
				idServicioNew.getTransaccionarticuloservicioList().add(
						transaccionarticuloservicio);
				idServicioNew = em.merge(idServicioNew);
			}
			if (idTransaccionarticuloOld != null
					&& !idTransaccionarticuloOld
							.equals(idTransaccionarticuloNew)) {
				idTransaccionarticuloOld.getTransaccionarticuloservicioList()
						.remove(transaccionarticuloservicio);
				idTransaccionarticuloOld = em.merge(idTransaccionarticuloOld);
			}
			if (idTransaccionarticuloNew != null
					&& !idTransaccionarticuloNew
							.equals(idTransaccionarticuloOld)) {
				idTransaccionarticuloNew.getTransaccionarticuloservicioList()
						.add(transaccionarticuloservicio);
				idTransaccionarticuloNew = em.merge(idTransaccionarticuloNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = transaccionarticuloservicio.getId();
				if (findTransaccionarticuloservicio(id) == null) {
					throw new NonexistentEntityException(
							"The transaccionarticuloservicio with id " + id
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
			Transaccionarticuloservicio transaccionarticuloservicio;
			try {
				transaccionarticuloservicio = em.getReference(
						Transaccionarticuloservicio.class, id);
				transaccionarticuloservicio.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The transaccionarticuloservicio with id " + id
								+ " no longer exists.", enfe);
			}
			Servicio idServicio = transaccionarticuloservicio.getIdServicio();
			if (idServicio != null) {
				idServicio.getTransaccionarticuloservicioList().remove(
						transaccionarticuloservicio);
				idServicio = em.merge(idServicio);
			}
			Transaccionarticulo idTransaccionarticulo = transaccionarticuloservicio
					.getIdTransaccionarticulo();
			if (idTransaccionarticulo != null) {
				idTransaccionarticulo.getTransaccionarticuloservicioList()
						.remove(transaccionarticuloservicio);
				idTransaccionarticulo = em.merge(idTransaccionarticulo);
			}
			em.remove(transaccionarticuloservicio);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTransaccionarticuloservicioEntities.
	 * 
	 * @return List<Transaccionarticuloservicio>
	 */
	public List<Transaccionarticuloservicio> findTransaccionarticuloservicioEntities() {
		return findTransaccionarticuloservicioEntities(true, -1, -1);
	}

	/**
	 * Method findTransaccionarticuloservicioEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transaccionarticuloservicio>
	 */
	public List<Transaccionarticuloservicio> findTransaccionarticuloservicioEntities(
			int maxResults, int firstResult) {
		return findTransaccionarticuloservicioEntities(false, maxResults,
				firstResult);
	}

	/**
	 * Method findTransaccionarticuloservicioEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transaccionarticuloservicio>
	 */
	private List<Transaccionarticuloservicio> findTransaccionarticuloservicioEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Transaccionarticuloservicio.class));
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
	 * Method findTransaccionarticuloservicio.
	 * 
	 * @param id
	 *            Long
	 * @return Transaccionarticuloservicio
	 */
	public Transaccionarticuloservicio findTransaccionarticuloservicio(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Transaccionarticuloservicio.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTransaccionarticuloservicioCount.
	 * 
	 * @return int
	 */
	public int getTransaccionarticuloservicioCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Transaccionarticuloservicio> rt = cq
					.from(Transaccionarticuloservicio.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
