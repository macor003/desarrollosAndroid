/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa.exceptions.NonexistentEntityException;
import crjpa.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class UsuarioperfilJpaController implements Serializable {

	/**
	 * Constructor for UsuarioperfilJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public UsuarioperfilJpaController(EntityManagerFactory emf) {
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
	 * @param usuarioperfil
	 *            Usuarioperfil
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Usuarioperfil usuarioperfil) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Usuario idUsuario = usuarioperfil.getIdUsuario();
			if (idUsuario != null) {
				idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getId());
				usuarioperfil.setIdUsuario(idUsuario);
			}
			Perfil idPerfil = usuarioperfil.getIdPerfil();
			if (idPerfil != null) {
				idPerfil = em.getReference(idPerfil.getClass(), idPerfil.getId());
				usuarioperfil.setIdPerfil(idPerfil);
			}
			em.persist(usuarioperfil);
			if (idUsuario != null) {
				idUsuario.getUsuarioperfilList().add(usuarioperfil);
				idUsuario = em.merge(idUsuario);
			}
			if (idPerfil != null) {
				idPerfil.getUsuarioperfilList().add(usuarioperfil);
				idPerfil = em.merge(idPerfil);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findUsuarioperfil(usuarioperfil.getId()) != null) {
				throw new PreexistingEntityException("Usuarioperfil " + usuarioperfil + " already exists.", ex);
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
	 * @param usuarioperfil
	 *            Usuarioperfil
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Usuarioperfil usuarioperfil) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Usuarioperfil persistentUsuarioperfil = em.find(Usuarioperfil.class, usuarioperfil.getId());
			Usuario idUsuarioOld = persistentUsuarioperfil.getIdUsuario();
			Usuario idUsuarioNew = usuarioperfil.getIdUsuario();
			Perfil idPerfilOld = persistentUsuarioperfil.getIdPerfil();
			Perfil idPerfilNew = usuarioperfil.getIdPerfil();
			if (idUsuarioNew != null) {
				idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getId());
				usuarioperfil.setIdUsuario(idUsuarioNew);
			}
			if (idPerfilNew != null) {
				idPerfilNew = em.getReference(idPerfilNew.getClass(), idPerfilNew.getId());
				usuarioperfil.setIdPerfil(idPerfilNew);
			}
			usuarioperfil = em.merge(usuarioperfil);
			if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
				idUsuarioOld.getUsuarioperfilList().remove(usuarioperfil);
				idUsuarioOld = em.merge(idUsuarioOld);
			}
			if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
				idUsuarioNew.getUsuarioperfilList().add(usuarioperfil);
				idUsuarioNew = em.merge(idUsuarioNew);
			}
			if (idPerfilOld != null && !idPerfilOld.equals(idPerfilNew)) {
				idPerfilOld.getUsuarioperfilList().remove(usuarioperfil);
				idPerfilOld = em.merge(idPerfilOld);
			}
			if (idPerfilNew != null && !idPerfilNew.equals(idPerfilOld)) {
				idPerfilNew.getUsuarioperfilList().add(usuarioperfil);
				idPerfilNew = em.merge(idPerfilNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = usuarioperfil.getId();
				if (findUsuarioperfil(id) == null) {
					throw new NonexistentEntityException("The usuarioperfil with id " + id + " no longer exists.");
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
			Usuarioperfil usuarioperfil;
			try {
				usuarioperfil = em.getReference(Usuarioperfil.class, id);
				usuarioperfil.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The usuarioperfil with id " + id + " no longer exists.", enfe);
			}
			Usuario idUsuario = usuarioperfil.getIdUsuario();
			if (idUsuario != null) {
				idUsuario.getUsuarioperfilList().remove(usuarioperfil);
				idUsuario = em.merge(idUsuario);
			}
			Perfil idPerfil = usuarioperfil.getIdPerfil();
			if (idPerfil != null) {
				idPerfil.getUsuarioperfilList().remove(usuarioperfil);
				idPerfil = em.merge(idPerfil);
			}
			em.remove(usuarioperfil);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findUsuarioperfilEntities.
	 * 
	 * @return List<Usuarioperfil>
	 */
	public List<Usuarioperfil> findUsuarioperfilEntities() {
		return findUsuarioperfilEntities(true, -1, -1);
	}

	/**
	 * Method findUsuarioperfilEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Usuarioperfil>
	 */
	public List<Usuarioperfil> findUsuarioperfilEntities(int maxResults, int firstResult) {
		return findUsuarioperfilEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findUsuarioperfilEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Usuarioperfil>
	 */
	private List<Usuarioperfil> findUsuarioperfilEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Usuarioperfil.class));
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
	 * Method findUsuarioperfil.
	 * 
	 * @param id
	 *            Long
	 * @return Usuarioperfil
	 */
	public Usuarioperfil findUsuarioperfil(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Usuarioperfil.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getUsuarioperfilCount.
	 * 
	 * @return int
	 */
	public int getUsuarioperfilCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Usuarioperfil> rt = cq.from(Usuarioperfil.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
