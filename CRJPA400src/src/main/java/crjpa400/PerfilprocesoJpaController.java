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
public class PerfilprocesoJpaController implements Serializable {

	/**
	 * Constructor for PerfilprocesoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public PerfilprocesoJpaController(EntityManagerFactory emf) {
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
	 * @param perfilproceso
	 *            Perfilproceso
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Perfilproceso perfilproceso)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Proceso idProceso = perfilproceso.getIdProceso();
			if (idProceso != null) {
				idProceso = em.getReference(idProceso.getClass(),
						idProceso.getId());
				perfilproceso.setIdProceso(idProceso);
			}
			Perfil idPerfil = perfilproceso.getIdPerfil();
			if (idPerfil != null) {
				idPerfil = em.getReference(idPerfil.getClass(),
						idPerfil.getId());
				perfilproceso.setIdPerfil(idPerfil);
			}
			em.persist(perfilproceso);
			if (idProceso != null) {
				idProceso.getPerfilprocesoList().add(perfilproceso);
				idProceso = em.merge(idProceso);
			}
			if (idPerfil != null) {
				idPerfil.getPerfilprocesoList().add(perfilproceso);
				idPerfil = em.merge(idPerfil);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findPerfilproceso(perfilproceso.getId()) != null) {
				throw new PreexistingEntityException("Perfilproceso "
						+ perfilproceso + " already exists.", ex);
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
	 * @param perfilproceso
	 *            Perfilproceso
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Perfilproceso perfilproceso)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Perfilproceso persistentPerfilproceso = em.find(
					Perfilproceso.class, perfilproceso.getId());
			Proceso idProcesoOld = persistentPerfilproceso.getIdProceso();
			Proceso idProcesoNew = perfilproceso.getIdProceso();
			Perfil idPerfilOld = persistentPerfilproceso.getIdPerfil();
			Perfil idPerfilNew = perfilproceso.getIdPerfil();
			if (idProcesoNew != null) {
				idProcesoNew = em.getReference(idProcesoNew.getClass(),
						idProcesoNew.getId());
				perfilproceso.setIdProceso(idProcesoNew);
			}
			if (idPerfilNew != null) {
				idPerfilNew = em.getReference(idPerfilNew.getClass(),
						idPerfilNew.getId());
				perfilproceso.setIdPerfil(idPerfilNew);
			}
			perfilproceso = em.merge(perfilproceso);
			if (idProcesoOld != null && !idProcesoOld.equals(idProcesoNew)) {
				idProcesoOld.getPerfilprocesoList().remove(perfilproceso);
				idProcesoOld = em.merge(idProcesoOld);
			}
			if (idProcesoNew != null && !idProcesoNew.equals(idProcesoOld)) {
				idProcesoNew.getPerfilprocesoList().add(perfilproceso);
				idProcesoNew = em.merge(idProcesoNew);
			}
			if (idPerfilOld != null && !idPerfilOld.equals(idPerfilNew)) {
				idPerfilOld.getPerfilprocesoList().remove(perfilproceso);
				idPerfilOld = em.merge(idPerfilOld);
			}
			if (idPerfilNew != null && !idPerfilNew.equals(idPerfilOld)) {
				idPerfilNew.getPerfilprocesoList().add(perfilproceso);
				idPerfilNew = em.merge(idPerfilNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = perfilproceso.getId();
				if (findPerfilproceso(id) == null) {
					throw new NonexistentEntityException(
							"The perfilproceso with id " + id
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
			Perfilproceso perfilproceso;
			try {
				perfilproceso = em.getReference(Perfilproceso.class, id);
				perfilproceso.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The perfilproceso with id " + id
								+ " no longer exists.", enfe);
			}
			Proceso idProceso = perfilproceso.getIdProceso();
			if (idProceso != null) {
				idProceso.getPerfilprocesoList().remove(perfilproceso);
				idProceso = em.merge(idProceso);
			}
			Perfil idPerfil = perfilproceso.getIdPerfil();
			if (idPerfil != null) {
				idPerfil.getPerfilprocesoList().remove(perfilproceso);
				idPerfil = em.merge(idPerfil);
			}
			em.remove(perfilproceso);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findPerfilprocesoEntities.
	 * 
	 * @return List<Perfilproceso>
	 */
	public List<Perfilproceso> findPerfilprocesoEntities() {
		return findPerfilprocesoEntities(true, -1, -1);
	}

	/**
	 * Method findPerfilprocesoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Perfilproceso>
	 */
	public List<Perfilproceso> findPerfilprocesoEntities(int maxResults,
			int firstResult) {
		return findPerfilprocesoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findPerfilprocesoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Perfilproceso>
	 */
	private List<Perfilproceso> findPerfilprocesoEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Perfilproceso.class));
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
	 * Method findPerfilproceso.
	 * 
	 * @param id
	 *            Long
	 * @return Perfilproceso
	 */
	public Perfilproceso findPerfilproceso(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Perfilproceso.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getPerfilprocesoCount.
	 * 
	 * @return int
	 */
	public int getPerfilprocesoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Perfilproceso> rt = cq.from(Perfilproceso.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
