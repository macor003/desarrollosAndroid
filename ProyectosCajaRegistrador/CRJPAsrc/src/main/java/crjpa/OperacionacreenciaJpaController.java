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
public class OperacionacreenciaJpaController implements Serializable {

	/**
	 * Constructor for OperacionacreenciaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public OperacionacreenciaJpaController(EntityManagerFactory emf) {
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
	 * @param operacionacreencia
	 *            Operacionacreencia
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Operacionacreencia operacionacreencia) throws PreexistingEntityException, Exception {
		if (operacionacreencia.getTipoacreenciaoperacionList() == null) {
			operacionacreencia.setTipoacreenciaoperacionList(new ArrayList<Tipoacreenciaoperacion>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Tipoacreenciaoperacion> attachedTipoacreenciaoperacionList = new ArrayList<Tipoacreenciaoperacion>();
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListTipoacreenciaoperacionToAttach : operacionacreencia
					.getTipoacreenciaoperacionList()) {
				tipoacreenciaoperacionListTipoacreenciaoperacionToAttach = em.getReference(
						tipoacreenciaoperacionListTipoacreenciaoperacionToAttach.getClass(),
						tipoacreenciaoperacionListTipoacreenciaoperacionToAttach.getId());
				attachedTipoacreenciaoperacionList.add(tipoacreenciaoperacionListTipoacreenciaoperacionToAttach);
			}
			operacionacreencia.setTipoacreenciaoperacionList(attachedTipoacreenciaoperacionList);
			em.persist(operacionacreencia);
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListTipoacreenciaoperacion : operacionacreencia
					.getTipoacreenciaoperacionList()) {
				Operacionacreencia oldIdOperacionacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion = tipoacreenciaoperacionListTipoacreenciaoperacion
						.getIdOperacionacreencia();
				tipoacreenciaoperacionListTipoacreenciaoperacion.setIdOperacionacreencia(operacionacreencia);
				tipoacreenciaoperacionListTipoacreenciaoperacion = em
						.merge(tipoacreenciaoperacionListTipoacreenciaoperacion);
				if (oldIdOperacionacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion != null) {
					oldIdOperacionacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion
							.getTipoacreenciaoperacionList().remove(tipoacreenciaoperacionListTipoacreenciaoperacion);
					oldIdOperacionacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion = em
							.merge(oldIdOperacionacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findOperacionacreencia(operacionacreencia.getId()) != null) {
				throw new PreexistingEntityException("Operacionacreencia " + operacionacreencia + " already exists.",
						ex);
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
	 * @param operacionacreencia
	 *            Operacionacreencia
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Operacionacreencia operacionacreencia) throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Operacionacreencia persistentOperacionacreencia = em.find(Operacionacreencia.class,
					operacionacreencia.getId());
			List<Tipoacreenciaoperacion> tipoacreenciaoperacionListOld = persistentOperacionacreencia
					.getTipoacreenciaoperacionList();
			List<Tipoacreenciaoperacion> tipoacreenciaoperacionListNew = operacionacreencia
					.getTipoacreenciaoperacionList();
			List<String> illegalOrphanMessages = null;
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListOldTipoacreenciaoperacion : tipoacreenciaoperacionListOld) {
				if (!tipoacreenciaoperacionListNew.contains(tipoacreenciaoperacionListOldTipoacreenciaoperacion)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Tipoacreenciaoperacion "
							+ tipoacreenciaoperacionListOldTipoacreenciaoperacion
							+ " since its idOperacionacreencia field is not nullable.");
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
			operacionacreencia.setTipoacreenciaoperacionList(tipoacreenciaoperacionListNew);
			operacionacreencia = em.merge(operacionacreencia);
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListNewTipoacreenciaoperacion : tipoacreenciaoperacionListNew) {
				if (!tipoacreenciaoperacionListOld.contains(tipoacreenciaoperacionListNewTipoacreenciaoperacion)) {
					Operacionacreencia oldIdOperacionacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion = tipoacreenciaoperacionListNewTipoacreenciaoperacion
							.getIdOperacionacreencia();
					tipoacreenciaoperacionListNewTipoacreenciaoperacion.setIdOperacionacreencia(operacionacreencia);
					tipoacreenciaoperacionListNewTipoacreenciaoperacion = em
							.merge(tipoacreenciaoperacionListNewTipoacreenciaoperacion);
					if (oldIdOperacionacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion != null
							&& !oldIdOperacionacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion
									.equals(operacionacreencia)) {
						oldIdOperacionacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion
								.getTipoacreenciaoperacionList().remove(
										tipoacreenciaoperacionListNewTipoacreenciaoperacion);
						oldIdOperacionacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion = em
								.merge(oldIdOperacionacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = operacionacreencia.getId();
				if (findOperacionacreencia(id) == null) {
					throw new NonexistentEntityException("The operacionacreencia with id " + id + " no longer exists.");
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
			Operacionacreencia operacionacreencia;
			try {
				operacionacreencia = em.getReference(Operacionacreencia.class, id);
				operacionacreencia.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The operacionacreencia with id " + id + " no longer exists.",
						enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Tipoacreenciaoperacion> tipoacreenciaoperacionListOrphanCheck = operacionacreencia
					.getTipoacreenciaoperacionList();
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListOrphanCheckTipoacreenciaoperacion : tipoacreenciaoperacionListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Operacionacreencia (" + operacionacreencia
						+ ") cannot be destroyed since the Tipoacreenciaoperacion "
						+ tipoacreenciaoperacionListOrphanCheckTipoacreenciaoperacion
						+ " in its tipoacreenciaoperacionList field has a non-nullable idOperacionacreencia field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(operacionacreencia);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findOperacionacreenciaEntities.
	 * 
	 * @return List<Operacionacreencia>
	 */
	public List<Operacionacreencia> findOperacionacreenciaEntities() {
		return findOperacionacreenciaEntities(true, -1, -1);
	}

	/**
	 * Method findOperacionacreenciaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Operacionacreencia>
	 */
	public List<Operacionacreencia> findOperacionacreenciaEntities(int maxResults, int firstResult) {
		return findOperacionacreenciaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findOperacionacreenciaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Operacionacreencia>
	 */
	private List<Operacionacreencia> findOperacionacreenciaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Operacionacreencia.class));
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
	 * Method findOperacionacreencia.
	 * 
	 * @param id
	 *            Long
	 * @return Operacionacreencia
	 */
	public Operacionacreencia findOperacionacreencia(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Operacionacreencia.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getOperacionacreenciaCount.
	 * 
	 * @return int
	 */
	public int getOperacionacreenciaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Operacionacreencia> rt = cq.from(Operacionacreencia.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
