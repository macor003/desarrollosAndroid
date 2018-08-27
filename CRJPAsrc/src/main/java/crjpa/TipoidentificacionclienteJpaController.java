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
public class TipoidentificacionclienteJpaController implements Serializable {

	/**
	 * Constructor for TipoidentificacionclienteJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TipoidentificacionclienteJpaController(EntityManagerFactory emf) {
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
	 * @param tipoidentificacioncliente
	 *            Tipoidentificacioncliente
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Tipoidentificacioncliente tipoidentificacioncliente) throws PreexistingEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipocliente idTipocliente = tipoidentificacioncliente.getIdTipocliente();
			if (idTipocliente != null) {
				idTipocliente = em.getReference(idTipocliente.getClass(), idTipocliente.getId());
				tipoidentificacioncliente.setIdTipocliente(idTipocliente);
			}
			em.persist(tipoidentificacioncliente);
			if (idTipocliente != null) {
				idTipocliente.getTipoidentificacionclienteList().add(tipoidentificacioncliente);
				idTipocliente = em.merge(idTipocliente);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTipoidentificacioncliente(tipoidentificacioncliente.getId()) != null) {
				throw new PreexistingEntityException("Tipoidentificacioncliente " + tipoidentificacioncliente
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
	 * @param tipoidentificacioncliente
	 *            Tipoidentificacioncliente
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Tipoidentificacioncliente tipoidentificacioncliente) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipoidentificacioncliente persistentTipoidentificacioncliente = em.find(Tipoidentificacioncliente.class,
					tipoidentificacioncliente.getId());
			Tipocliente idTipoclienteOld = persistentTipoidentificacioncliente.getIdTipocliente();
			Tipocliente idTipoclienteNew = tipoidentificacioncliente.getIdTipocliente();
			if (idTipoclienteNew != null) {
				idTipoclienteNew = em.getReference(idTipoclienteNew.getClass(), idTipoclienteNew.getId());
				tipoidentificacioncliente.setIdTipocliente(idTipoclienteNew);
			}
			tipoidentificacioncliente = em.merge(tipoidentificacioncliente);
			if (idTipoclienteOld != null && !idTipoclienteOld.equals(idTipoclienteNew)) {
				idTipoclienteOld.getTipoidentificacionclienteList().remove(tipoidentificacioncliente);
				idTipoclienteOld = em.merge(idTipoclienteOld);
			}
			if (idTipoclienteNew != null && !idTipoclienteNew.equals(idTipoclienteOld)) {
				idTipoclienteNew.getTipoidentificacionclienteList().add(tipoidentificacioncliente);
				idTipoclienteNew = em.merge(idTipoclienteNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = tipoidentificacioncliente.getId();
				if (findTipoidentificacioncliente(id) == null) {
					throw new NonexistentEntityException("The tipoidentificacioncliente with id " + id
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
			Tipoidentificacioncliente tipoidentificacioncliente;
			try {
				tipoidentificacioncliente = em.getReference(Tipoidentificacioncliente.class, id);
				tipoidentificacioncliente.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The tipoidentificacioncliente with id " + id
						+ " no longer exists.", enfe);
			}
			Tipocliente idTipocliente = tipoidentificacioncliente.getIdTipocliente();
			if (idTipocliente != null) {
				idTipocliente.getTipoidentificacionclienteList().remove(tipoidentificacioncliente);
				idTipocliente = em.merge(idTipocliente);
			}
			em.remove(tipoidentificacioncliente);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTipoidentificacionclienteEntities.
	 * 
	 * @return List<Tipoidentificacioncliente>
	 */
	public List<Tipoidentificacioncliente> findTipoidentificacionclienteEntities() {
		return findTipoidentificacionclienteEntities(true, -1, -1);
	}

	/**
	 * Method findTipoidentificacionclienteEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipoidentificacioncliente>
	 */
	public List<Tipoidentificacioncliente> findTipoidentificacionclienteEntities(int maxResults, int firstResult) {
		return findTipoidentificacionclienteEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTipoidentificacionclienteEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipoidentificacioncliente>
	 */
	private List<Tipoidentificacioncliente> findTipoidentificacionclienteEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Tipoidentificacioncliente.class));
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
	 * Method findTipoidentificacioncliente.
	 * 
	 * @param id
	 *            Long
	 * @return Tipoidentificacioncliente
	 */
	public Tipoidentificacioncliente findTipoidentificacioncliente(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Tipoidentificacioncliente.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTipoidentificacionclienteCount.
	 * 
	 * @return int
	 */
	public int getTipoidentificacionclienteCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Tipoidentificacioncliente> rt = cq.from(Tipoidentificacioncliente.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
