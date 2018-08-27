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
public class PerfilJpaController implements Serializable {

	/**
	 * Constructor for PerfilJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public PerfilJpaController(EntityManagerFactory emf) {
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
	 * @param perfil
	 *            Perfil
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Perfil perfil) throws PreexistingEntityException, Exception {
		if (perfil.getUsuarioperfilList() == null) {
			perfil.setUsuarioperfilList(new ArrayList<Usuarioperfil>());
		}
		if (perfil.getPerfilprocesoList() == null) {
			perfil.setPerfilprocesoList(new ArrayList<Perfilproceso>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Usuarioperfil> attachedUsuarioperfilList = new ArrayList<Usuarioperfil>();
			for (Usuarioperfil usuarioperfilListUsuarioperfilToAttach : perfil.getUsuarioperfilList()) {
				usuarioperfilListUsuarioperfilToAttach = em.getReference(
						usuarioperfilListUsuarioperfilToAttach.getClass(),
						usuarioperfilListUsuarioperfilToAttach.getId());
				attachedUsuarioperfilList.add(usuarioperfilListUsuarioperfilToAttach);
			}
			perfil.setUsuarioperfilList(attachedUsuarioperfilList);
			List<Perfilproceso> attachedPerfilprocesoList = new ArrayList<Perfilproceso>();
			for (Perfilproceso perfilprocesoListPerfilprocesoToAttach : perfil.getPerfilprocesoList()) {
				perfilprocesoListPerfilprocesoToAttach = em.getReference(
						perfilprocesoListPerfilprocesoToAttach.getClass(),
						perfilprocesoListPerfilprocesoToAttach.getId());
				attachedPerfilprocesoList.add(perfilprocesoListPerfilprocesoToAttach);
			}
			perfil.setPerfilprocesoList(attachedPerfilprocesoList);
			em.persist(perfil);
			for (Usuarioperfil usuarioperfilListUsuarioperfil : perfil.getUsuarioperfilList()) {
				Perfil oldIdPerfilOfUsuarioperfilListUsuarioperfil = usuarioperfilListUsuarioperfil.getIdPerfil();
				usuarioperfilListUsuarioperfil.setIdPerfil(perfil);
				usuarioperfilListUsuarioperfil = em.merge(usuarioperfilListUsuarioperfil);
				if (oldIdPerfilOfUsuarioperfilListUsuarioperfil != null) {
					oldIdPerfilOfUsuarioperfilListUsuarioperfil.getUsuarioperfilList().remove(
							usuarioperfilListUsuarioperfil);
					oldIdPerfilOfUsuarioperfilListUsuarioperfil = em.merge(oldIdPerfilOfUsuarioperfilListUsuarioperfil);
				}
			}
			for (Perfilproceso perfilprocesoListPerfilproceso : perfil.getPerfilprocesoList()) {
				Perfil oldIdPerfilOfPerfilprocesoListPerfilproceso = perfilprocesoListPerfilproceso.getIdPerfil();
				perfilprocesoListPerfilproceso.setIdPerfil(perfil);
				perfilprocesoListPerfilproceso = em.merge(perfilprocesoListPerfilproceso);
				if (oldIdPerfilOfPerfilprocesoListPerfilproceso != null) {
					oldIdPerfilOfPerfilprocesoListPerfilproceso.getPerfilprocesoList().remove(
							perfilprocesoListPerfilproceso);
					oldIdPerfilOfPerfilprocesoListPerfilproceso = em.merge(oldIdPerfilOfPerfilprocesoListPerfilproceso);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findPerfil(perfil.getId()) != null) {
				throw new PreexistingEntityException("Perfil " + perfil + " already exists.", ex);
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
	 * @param perfil
	 *            Perfil
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Perfil perfil) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Perfil persistentPerfil = em.find(Perfil.class, perfil.getId());
			List<Usuarioperfil> usuarioperfilListOld = persistentPerfil.getUsuarioperfilList();
			List<Usuarioperfil> usuarioperfilListNew = perfil.getUsuarioperfilList();
			List<Perfilproceso> perfilprocesoListOld = persistentPerfil.getPerfilprocesoList();
			List<Perfilproceso> perfilprocesoListNew = perfil.getPerfilprocesoList();
			List<String> illegalOrphanMessages = null;
			for (Usuarioperfil usuarioperfilListOldUsuarioperfil : usuarioperfilListOld) {
				if (!usuarioperfilListNew.contains(usuarioperfilListOldUsuarioperfil)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Usuarioperfil " + usuarioperfilListOldUsuarioperfil
							+ " since its idPerfil field is not nullable.");
				}
			}
			for (Perfilproceso perfilprocesoListOldPerfilproceso : perfilprocesoListOld) {
				if (!perfilprocesoListNew.contains(perfilprocesoListOldPerfilproceso)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Perfilproceso " + perfilprocesoListOldPerfilproceso
							+ " since its idPerfil field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Usuarioperfil> attachedUsuarioperfilListNew = new ArrayList<Usuarioperfil>();
			for (Usuarioperfil usuarioperfilListNewUsuarioperfilToAttach : usuarioperfilListNew) {
				usuarioperfilListNewUsuarioperfilToAttach = em.getReference(
						usuarioperfilListNewUsuarioperfilToAttach.getClass(),
						usuarioperfilListNewUsuarioperfilToAttach.getId());
				attachedUsuarioperfilListNew.add(usuarioperfilListNewUsuarioperfilToAttach);
			}
			usuarioperfilListNew = attachedUsuarioperfilListNew;
			perfil.setUsuarioperfilList(usuarioperfilListNew);
			List<Perfilproceso> attachedPerfilprocesoListNew = new ArrayList<Perfilproceso>();
			for (Perfilproceso perfilprocesoListNewPerfilprocesoToAttach : perfilprocesoListNew) {
				perfilprocesoListNewPerfilprocesoToAttach = em.getReference(
						perfilprocesoListNewPerfilprocesoToAttach.getClass(),
						perfilprocesoListNewPerfilprocesoToAttach.getId());
				attachedPerfilprocesoListNew.add(perfilprocesoListNewPerfilprocesoToAttach);
			}
			perfilprocesoListNew = attachedPerfilprocesoListNew;
			perfil.setPerfilprocesoList(perfilprocesoListNew);
			perfil = em.merge(perfil);
			for (Usuarioperfil usuarioperfilListNewUsuarioperfil : usuarioperfilListNew) {
				if (!usuarioperfilListOld.contains(usuarioperfilListNewUsuarioperfil)) {
					Perfil oldIdPerfilOfUsuarioperfilListNewUsuarioperfil = usuarioperfilListNewUsuarioperfil
							.getIdPerfil();
					usuarioperfilListNewUsuarioperfil.setIdPerfil(perfil);
					usuarioperfilListNewUsuarioperfil = em.merge(usuarioperfilListNewUsuarioperfil);
					if (oldIdPerfilOfUsuarioperfilListNewUsuarioperfil != null
							&& !oldIdPerfilOfUsuarioperfilListNewUsuarioperfil.equals(perfil)) {
						oldIdPerfilOfUsuarioperfilListNewUsuarioperfil.getUsuarioperfilList().remove(
								usuarioperfilListNewUsuarioperfil);
						oldIdPerfilOfUsuarioperfilListNewUsuarioperfil = em
								.merge(oldIdPerfilOfUsuarioperfilListNewUsuarioperfil);
					}
				}
			}
			for (Perfilproceso perfilprocesoListNewPerfilproceso : perfilprocesoListNew) {
				if (!perfilprocesoListOld.contains(perfilprocesoListNewPerfilproceso)) {
					Perfil oldIdPerfilOfPerfilprocesoListNewPerfilproceso = perfilprocesoListNewPerfilproceso
							.getIdPerfil();
					perfilprocesoListNewPerfilproceso.setIdPerfil(perfil);
					perfilprocesoListNewPerfilproceso = em.merge(perfilprocesoListNewPerfilproceso);
					if (oldIdPerfilOfPerfilprocesoListNewPerfilproceso != null
							&& !oldIdPerfilOfPerfilprocesoListNewPerfilproceso.equals(perfil)) {
						oldIdPerfilOfPerfilprocesoListNewPerfilproceso.getPerfilprocesoList().remove(
								perfilprocesoListNewPerfilproceso);
						oldIdPerfilOfPerfilprocesoListNewPerfilproceso = em
								.merge(oldIdPerfilOfPerfilprocesoListNewPerfilproceso);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = perfil.getId();
				if (findPerfil(id) == null) {
					throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.");
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
			Perfil perfil;
			try {
				perfil = em.getReference(Perfil.class, id);
				perfil.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Usuarioperfil> usuarioperfilListOrphanCheck = perfil.getUsuarioperfilList();
			for (Usuarioperfil usuarioperfilListOrphanCheckUsuarioperfil : usuarioperfilListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Perfil (" + perfil + ") cannot be destroyed since the Usuarioperfil "
						+ usuarioperfilListOrphanCheckUsuarioperfil
						+ " in its usuarioperfilList field has a non-nullable idPerfil field.");
			}
			List<Perfilproceso> perfilprocesoListOrphanCheck = perfil.getPerfilprocesoList();
			for (Perfilproceso perfilprocesoListOrphanCheckPerfilproceso : perfilprocesoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Perfil (" + perfil + ") cannot be destroyed since the Perfilproceso "
						+ perfilprocesoListOrphanCheckPerfilproceso
						+ " in its perfilprocesoList field has a non-nullable idPerfil field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(perfil);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findPerfilEntities.
	 * 
	 * @return List<Perfil>
	 */
	public List<Perfil> findPerfilEntities() {
		return findPerfilEntities(true, -1, -1);
	}

	/**
	 * Method findPerfilEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Perfil>
	 */
	public List<Perfil> findPerfilEntities(int maxResults, int firstResult) {
		return findPerfilEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findPerfilEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Perfil>
	 */
	private List<Perfil> findPerfilEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Perfil.class));
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
	 * Method findPerfil.
	 * 
	 * @param id
	 *            Long
	 * @return Perfil
	 */
	public Perfil findPerfil(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Perfil.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getPerfilCount.
	 * 
	 * @return int
	 */
	public int getPerfilCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Perfil> rt = cq.from(Perfil.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
