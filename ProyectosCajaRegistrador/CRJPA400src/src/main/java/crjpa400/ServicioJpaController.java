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
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class ServicioJpaController implements Serializable {

	/**
	 * Constructor for ServicioJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ServicioJpaController(EntityManagerFactory emf) {
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
	 * @param servicio
	 *            Servicio
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Servicio servicio) throws PreexistingEntityException,
			Exception {
		if (servicio.getArticuloservicioList() == null) {
			servicio.setArticuloservicioList(new ArrayList<Articuloservicio>());
		}
		if (servicio.getTransaccionarticuloservicioList() == null) {
			servicio.setTransaccionarticuloservicioList(new ArrayList<Transaccionarticuloservicio>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Articuloservicio> attachedArticuloservicioList = new ArrayList<Articuloservicio>();
			for (Articuloservicio articuloservicioListArticuloservicioToAttach : servicio
					.getArticuloservicioList()) {
				articuloservicioListArticuloservicioToAttach = em
						.getReference(
								articuloservicioListArticuloservicioToAttach
										.getClass(),
								articuloservicioListArticuloservicioToAttach
										.getArticuloservicioPK());
				attachedArticuloservicioList
						.add(articuloservicioListArticuloservicioToAttach);
			}
			servicio.setArticuloservicioList(attachedArticuloservicioList);
			List<Transaccionarticuloservicio> attachedTransaccionarticuloservicioList = new ArrayList<Transaccionarticuloservicio>();
			for (Transaccionarticuloservicio transaccionarticuloservicioListTransaccionarticuloservicioToAttach : servicio
					.getTransaccionarticuloservicioList()) {
				transaccionarticuloservicioListTransaccionarticuloservicioToAttach = em
						.getReference(
								transaccionarticuloservicioListTransaccionarticuloservicioToAttach
										.getClass(),
								transaccionarticuloservicioListTransaccionarticuloservicioToAttach
										.getId());
				attachedTransaccionarticuloservicioList
						.add(transaccionarticuloservicioListTransaccionarticuloservicioToAttach);
			}
			servicio.setTransaccionarticuloservicioList(attachedTransaccionarticuloservicioList);
			em.persist(servicio);
			for (Articuloservicio articuloservicioListArticuloservicio : servicio
					.getArticuloservicioList()) {
				Servicio oldServicioOfArticuloservicioListArticuloservicio = articuloservicioListArticuloservicio
						.getServicio();
				articuloservicioListArticuloservicio.setServicio(servicio);
				articuloservicioListArticuloservicio = em
						.merge(articuloservicioListArticuloservicio);
				if (oldServicioOfArticuloservicioListArticuloservicio != null) {
					oldServicioOfArticuloservicioListArticuloservicio
							.getArticuloservicioList().remove(
									articuloservicioListArticuloservicio);
					oldServicioOfArticuloservicioListArticuloservicio = em
							.merge(oldServicioOfArticuloservicioListArticuloservicio);
				}
			}
			for (Transaccionarticuloservicio transaccionarticuloservicioListTransaccionarticuloservicio : servicio
					.getTransaccionarticuloservicioList()) {
				Servicio oldIdServicioOfTransaccionarticuloservicioListTransaccionarticuloservicio = transaccionarticuloservicioListTransaccionarticuloservicio
						.getIdServicio();
				transaccionarticuloservicioListTransaccionarticuloservicio
						.setIdServicio(servicio);
				transaccionarticuloservicioListTransaccionarticuloservicio = em
						.merge(transaccionarticuloservicioListTransaccionarticuloservicio);
				if (oldIdServicioOfTransaccionarticuloservicioListTransaccionarticuloservicio != null) {
					oldIdServicioOfTransaccionarticuloservicioListTransaccionarticuloservicio
							.getTransaccionarticuloservicioList()
							.remove(transaccionarticuloservicioListTransaccionarticuloservicio);
					oldIdServicioOfTransaccionarticuloservicioListTransaccionarticuloservicio = em
							.merge(oldIdServicioOfTransaccionarticuloservicioListTransaccionarticuloservicio);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findServicio(servicio.getId()) != null) {
				throw new PreexistingEntityException("Servicio " + servicio
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
	 * @param servicio
	 *            Servicio
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Servicio servicio) throws IllegalOrphanException,
			NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Servicio persistentServicio = em.find(Servicio.class,
					servicio.getId());
			List<Articuloservicio> articuloservicioListOld = persistentServicio
					.getArticuloservicioList();
			List<Articuloservicio> articuloservicioListNew = servicio
					.getArticuloservicioList();
			List<Transaccionarticuloservicio> transaccionarticuloservicioListOld = persistentServicio
					.getTransaccionarticuloservicioList();
			List<Transaccionarticuloservicio> transaccionarticuloservicioListNew = servicio
					.getTransaccionarticuloservicioList();
			List<String> illegalOrphanMessages = null;
			for (Articuloservicio articuloservicioListOldArticuloservicio : articuloservicioListOld) {
				if (!articuloservicioListNew
						.contains(articuloservicioListOldArticuloservicio)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Articuloservicio "
									+ articuloservicioListOldArticuloservicio
									+ " since its servicio field is not nullable.");
				}
			}
			for (Transaccionarticuloservicio transaccionarticuloservicioListOldTransaccionarticuloservicio : transaccionarticuloservicioListOld) {
				if (!transaccionarticuloservicioListNew
						.contains(transaccionarticuloservicioListOldTransaccionarticuloservicio)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Transaccionarticuloservicio "
									+ transaccionarticuloservicioListOldTransaccionarticuloservicio
									+ " since its idServicio field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Articuloservicio> attachedArticuloservicioListNew = new ArrayList<Articuloservicio>();
			for (Articuloservicio articuloservicioListNewArticuloservicioToAttach : articuloservicioListNew) {
				articuloservicioListNewArticuloservicioToAttach = em
						.getReference(
								articuloservicioListNewArticuloservicioToAttach
										.getClass(),
								articuloservicioListNewArticuloservicioToAttach
										.getArticuloservicioPK());
				attachedArticuloservicioListNew
						.add(articuloservicioListNewArticuloservicioToAttach);
			}
			articuloservicioListNew = attachedArticuloservicioListNew;
			servicio.setArticuloservicioList(articuloservicioListNew);
			List<Transaccionarticuloservicio> attachedTransaccionarticuloservicioListNew = new ArrayList<Transaccionarticuloservicio>();
			for (Transaccionarticuloservicio transaccionarticuloservicioListNewTransaccionarticuloservicioToAttach : transaccionarticuloservicioListNew) {
				transaccionarticuloservicioListNewTransaccionarticuloservicioToAttach = em
						.getReference(
								transaccionarticuloservicioListNewTransaccionarticuloservicioToAttach
										.getClass(),
								transaccionarticuloservicioListNewTransaccionarticuloservicioToAttach
										.getId());
				attachedTransaccionarticuloservicioListNew
						.add(transaccionarticuloservicioListNewTransaccionarticuloservicioToAttach);
			}
			transaccionarticuloservicioListNew = attachedTransaccionarticuloservicioListNew;
			servicio.setTransaccionarticuloservicioList(transaccionarticuloservicioListNew);
			servicio = em.merge(servicio);
			for (Articuloservicio articuloservicioListNewArticuloservicio : articuloservicioListNew) {
				if (!articuloservicioListOld
						.contains(articuloservicioListNewArticuloservicio)) {
					Servicio oldServicioOfArticuloservicioListNewArticuloservicio = articuloservicioListNewArticuloservicio
							.getServicio();
					articuloservicioListNewArticuloservicio
							.setServicio(servicio);
					articuloservicioListNewArticuloservicio = em
							.merge(articuloservicioListNewArticuloservicio);
					if (oldServicioOfArticuloservicioListNewArticuloservicio != null
							&& !oldServicioOfArticuloservicioListNewArticuloservicio
									.equals(servicio)) {
						oldServicioOfArticuloservicioListNewArticuloservicio
								.getArticuloservicioList()
								.remove(articuloservicioListNewArticuloservicio);
						oldServicioOfArticuloservicioListNewArticuloservicio = em
								.merge(oldServicioOfArticuloservicioListNewArticuloservicio);
					}
				}
			}
			for (Transaccionarticuloservicio transaccionarticuloservicioListNewTransaccionarticuloservicio : transaccionarticuloservicioListNew) {
				if (!transaccionarticuloservicioListOld
						.contains(transaccionarticuloservicioListNewTransaccionarticuloservicio)) {
					Servicio oldIdServicioOfTransaccionarticuloservicioListNewTransaccionarticuloservicio = transaccionarticuloservicioListNewTransaccionarticuloservicio
							.getIdServicio();
					transaccionarticuloservicioListNewTransaccionarticuloservicio
							.setIdServicio(servicio);
					transaccionarticuloservicioListNewTransaccionarticuloservicio = em
							.merge(transaccionarticuloservicioListNewTransaccionarticuloservicio);
					if (oldIdServicioOfTransaccionarticuloservicioListNewTransaccionarticuloservicio != null
							&& !oldIdServicioOfTransaccionarticuloservicioListNewTransaccionarticuloservicio
									.equals(servicio)) {
						oldIdServicioOfTransaccionarticuloservicioListNewTransaccionarticuloservicio
								.getTransaccionarticuloservicioList()
								.remove(transaccionarticuloservicioListNewTransaccionarticuloservicio);
						oldIdServicioOfTransaccionarticuloservicioListNewTransaccionarticuloservicio = em
								.merge(oldIdServicioOfTransaccionarticuloservicioListNewTransaccionarticuloservicio);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = servicio.getId();
				if (findServicio(id) == null) {
					throw new NonexistentEntityException(
							"The servicio with id " + id + " no longer exists.");
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
	public void destroy(Long id) throws IllegalOrphanException,
			NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Servicio servicio;
			try {
				servicio = em.getReference(Servicio.class, id);
				servicio.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The servicio with id "
						+ id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Articuloservicio> articuloservicioListOrphanCheck = servicio
					.getArticuloservicioList();
			for (Articuloservicio articuloservicioListOrphanCheckArticuloservicio : articuloservicioListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Servicio ("
								+ servicio
								+ ") cannot be destroyed since the Articuloservicio "
								+ articuloservicioListOrphanCheckArticuloservicio
								+ " in its articuloservicioList field has a non-nullable servicio field.");
			}
			List<Transaccionarticuloservicio> transaccionarticuloservicioListOrphanCheck = servicio
					.getTransaccionarticuloservicioList();
			for (Transaccionarticuloservicio transaccionarticuloservicioListOrphanCheckTransaccionarticuloservicio : transaccionarticuloservicioListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Servicio ("
								+ servicio
								+ ") cannot be destroyed since the Transaccionarticuloservicio "
								+ transaccionarticuloservicioListOrphanCheckTransaccionarticuloservicio
								+ " in its transaccionarticuloservicioList field has a non-nullable idServicio field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(servicio);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findServicioEntities.
	 * 
	 * @return List<Servicio>
	 */
	public List<Servicio> findServicioEntities() {
		return findServicioEntities(true, -1, -1);
	}

	/**
	 * Method findServicioEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Servicio>
	 */
	public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
		return findServicioEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findServicioEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Servicio>
	 */
	private List<Servicio> findServicioEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Servicio.class));
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
	 * Method findServicio.
	 * 
	 * @param id
	 *            Long
	 * @return Servicio
	 */
	public Servicio findServicio(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Servicio.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getServicioCount.
	 * 
	 * @return int
	 */
	public int getServicioCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Servicio> rt = cq.from(Servicio.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
