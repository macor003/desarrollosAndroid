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
public class TipoacreenciaJpaController implements Serializable {

	/**
	 * Constructor for TipoacreenciaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TipoacreenciaJpaController(EntityManagerFactory emf) {
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
	 * @param tipoacreencia
	 *            Tipoacreencia
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Tipoacreencia tipoacreencia) throws PreexistingEntityException, Exception {
		if (tipoacreencia.getTipoacreenciaoperacionList() == null) {
			tipoacreencia.setTipoacreenciaoperacionList(new ArrayList<Tipoacreenciaoperacion>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Tipoacreenciaoperacion> attachedTipoacreenciaoperacionList = new ArrayList<Tipoacreenciaoperacion>();
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListTipoacreenciaoperacionToAttach : tipoacreencia
					.getTipoacreenciaoperacionList()) {
				tipoacreenciaoperacionListTipoacreenciaoperacionToAttach = em.getReference(
						tipoacreenciaoperacionListTipoacreenciaoperacionToAttach.getClass(),
						tipoacreenciaoperacionListTipoacreenciaoperacionToAttach.getId());
				attachedTipoacreenciaoperacionList.add(tipoacreenciaoperacionListTipoacreenciaoperacionToAttach);
			}
			tipoacreencia.setTipoacreenciaoperacionList(attachedTipoacreenciaoperacionList);
			em.persist(tipoacreencia);
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListTipoacreenciaoperacion : tipoacreencia
					.getTipoacreenciaoperacionList()) {
				Tipoacreencia oldIdTipoacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion = tipoacreenciaoperacionListTipoacreenciaoperacion
						.getIdTipoacreencia();
				tipoacreenciaoperacionListTipoacreenciaoperacion.setIdTipoacreencia(tipoacreencia);
				tipoacreenciaoperacionListTipoacreenciaoperacion = em
						.merge(tipoacreenciaoperacionListTipoacreenciaoperacion);
				if (oldIdTipoacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion != null) {
					oldIdTipoacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion
							.getTipoacreenciaoperacionList().remove(tipoacreenciaoperacionListTipoacreenciaoperacion);
					oldIdTipoacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion = em
							.merge(oldIdTipoacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTipoacreencia(tipoacreencia.getId()) != null) {
				throw new PreexistingEntityException("Tipoacreencia " + tipoacreencia + " already exists.", ex);
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
	 * @param tipoacreencia
	 *            Tipoacreencia
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Tipoacreencia tipoacreencia) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipoacreencia persistentTipoacreencia = em.find(Tipoacreencia.class, tipoacreencia.getId());
			List<Tipoacreenciaoperacion> tipoacreenciaoperacionListOld = persistentTipoacreencia
					.getTipoacreenciaoperacionList();
			List<Tipoacreenciaoperacion> tipoacreenciaoperacionListNew = tipoacreencia.getTipoacreenciaoperacionList();
			List<String> illegalOrphanMessages = null;
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListOldTipoacreenciaoperacion : tipoacreenciaoperacionListOld) {
				if (!tipoacreenciaoperacionListNew.contains(tipoacreenciaoperacionListOldTipoacreenciaoperacion)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Tipoacreenciaoperacion "
							+ tipoacreenciaoperacionListOldTipoacreenciaoperacion
							+ " since its idTipoacreencia field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Tipoacreenciaoperacion> attachedTipoacreenciaoperacionListNew = new ArrayList<Tipoacreenciaoperacion>();
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListNewTipoacreenciaoperacionToAttach : tipoacreenciaoperacionListNew) {
				tipoacreenciaoperacionListNewTipoacreenciaoperacionToAttach = em.getReference(
						tipoacreenciaoperacionListNewTipoacreenciaoperacionToAttach.getClass(),
						tipoacreenciaoperacionListNewTipoacreenciaoperacionToAttach.getId());
				attachedTipoacreenciaoperacionListNew.add(tipoacreenciaoperacionListNewTipoacreenciaoperacionToAttach);
			}
			tipoacreenciaoperacionListNew = attachedTipoacreenciaoperacionListNew;
			tipoacreencia.setTipoacreenciaoperacionList(tipoacreenciaoperacionListNew);
			tipoacreencia = em.merge(tipoacreencia);
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListNewTipoacreenciaoperacion : tipoacreenciaoperacionListNew) {
				if (!tipoacreenciaoperacionListOld.contains(tipoacreenciaoperacionListNewTipoacreenciaoperacion)) {
					Tipoacreencia oldIdTipoacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion = tipoacreenciaoperacionListNewTipoacreenciaoperacion
							.getIdTipoacreencia();
					tipoacreenciaoperacionListNewTipoacreenciaoperacion.setIdTipoacreencia(tipoacreencia);
					tipoacreenciaoperacionListNewTipoacreenciaoperacion = em
							.merge(tipoacreenciaoperacionListNewTipoacreenciaoperacion);
					if (oldIdTipoacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion != null
							&& !oldIdTipoacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion
									.equals(tipoacreencia)) {
						oldIdTipoacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion
								.getTipoacreenciaoperacionList().remove(
										tipoacreenciaoperacionListNewTipoacreenciaoperacion);
						oldIdTipoacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion = em
								.merge(oldIdTipoacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = tipoacreencia.getId();
				if (findTipoacreencia(id) == null) {
					throw new NonexistentEntityException("The tipoacreencia with id " + id + " no longer exists.");
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
			Tipoacreencia tipoacreencia;
			try {
				tipoacreencia = em.getReference(Tipoacreencia.class, id);
				tipoacreencia.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The tipoacreencia with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Tipoacreenciaoperacion> tipoacreenciaoperacionListOrphanCheck = tipoacreencia
					.getTipoacreenciaoperacionList();
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListOrphanCheckTipoacreenciaoperacion : tipoacreenciaoperacionListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Tipoacreencia (" + tipoacreencia
						+ ") cannot be destroyed since the Tipoacreenciaoperacion "
						+ tipoacreenciaoperacionListOrphanCheckTipoacreenciaoperacion
						+ " in its tipoacreenciaoperacionList field has a non-nullable idTipoacreencia field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(tipoacreencia);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTipoacreenciaEntities.
	 * 
	 * @return List<Tipoacreencia>
	 */
	public List<Tipoacreencia> findTipoacreenciaEntities() {
		return findTipoacreenciaEntities(true, -1, -1);
	}

	/**
	 * Method findTipoacreenciaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipoacreencia>
	 */
	public List<Tipoacreencia> findTipoacreenciaEntities(int maxResults, int firstResult) {
		return findTipoacreenciaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTipoacreenciaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipoacreencia>
	 */
	private List<Tipoacreencia> findTipoacreenciaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Tipoacreencia.class));
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
	 * Method findTipoacreencia.
	 * 
	 * @param id
	 *            Long
	 * @return Tipoacreencia
	 */
	public Tipoacreencia findTipoacreencia(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Tipoacreencia.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTipoacreenciaCount.
	 * 
	 * @return int
	 */
	public int getTipoacreenciaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Tipoacreencia> rt = cq.from(Tipoacreencia.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
