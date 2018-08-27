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
public class ClientemensajeJpaController implements Serializable {

	/**
	 * Constructor for ClientemensajeJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ClientemensajeJpaController(EntityManagerFactory emf) {
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
	 * @param clientemensaje
	 *            Clientemensaje
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Clientemensaje clientemensaje) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Cliente numeroIdentificacionCliente = clientemensaje.getNumeroIdentificacionCliente();
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente = em.getReference(numeroIdentificacionCliente.getClass(),
						numeroIdentificacionCliente.getNumeroIdentificacionCliente());
				clientemensaje.setNumeroIdentificacionCliente(numeroIdentificacionCliente);
			}
			em.persist(clientemensaje);
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente.getClientemensajeList().add(clientemensaje);
				numeroIdentificacionCliente = em.merge(numeroIdentificacionCliente);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findClientemensaje(clientemensaje.getId()) != null) {
				throw new PreexistingEntityException("Clientemensaje " + clientemensaje + " already exists.", ex);
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
	 * @param clientemensaje
	 *            Clientemensaje
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Clientemensaje clientemensaje) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Clientemensaje persistentClientemensaje = em.find(Clientemensaje.class, clientemensaje.getId());
			Cliente numeroIdentificacionClienteOld = persistentClientemensaje.getNumeroIdentificacionCliente();
			Cliente numeroIdentificacionClienteNew = clientemensaje.getNumeroIdentificacionCliente();
			if (numeroIdentificacionClienteNew != null) {
				numeroIdentificacionClienteNew = em.getReference(numeroIdentificacionClienteNew.getClass(),
						numeroIdentificacionClienteNew.getNumeroIdentificacionCliente());
				clientemensaje.setNumeroIdentificacionCliente(numeroIdentificacionClienteNew);
			}
			clientemensaje = em.merge(clientemensaje);
			if (numeroIdentificacionClienteOld != null
					&& !numeroIdentificacionClienteOld.equals(numeroIdentificacionClienteNew)) {
				numeroIdentificacionClienteOld.getClientemensajeList().remove(clientemensaje);
				numeroIdentificacionClienteOld = em.merge(numeroIdentificacionClienteOld);
			}
			if (numeroIdentificacionClienteNew != null
					&& !numeroIdentificacionClienteNew.equals(numeroIdentificacionClienteOld)) {
				numeroIdentificacionClienteNew.getClientemensajeList().add(clientemensaje);
				numeroIdentificacionClienteNew = em.merge(numeroIdentificacionClienteNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = clientemensaje.getId();
				if (findClientemensaje(id) == null) {
					throw new NonexistentEntityException("The clientemensaje with id " + id + " no longer exists.");
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
			Clientemensaje clientemensaje;
			try {
				clientemensaje = em.getReference(Clientemensaje.class, id);
				clientemensaje.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The clientemensaje with id " + id + " no longer exists.", enfe);
			}
			Cliente numeroIdentificacionCliente = clientemensaje.getNumeroIdentificacionCliente();
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente.getClientemensajeList().remove(clientemensaje);
				numeroIdentificacionCliente = em.merge(numeroIdentificacionCliente);
			}
			em.remove(clientemensaje);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findClientemensajeEntities.
	 * 
	 * @return List<Clientemensaje>
	 */
	public List<Clientemensaje> findClientemensajeEntities() {
		return findClientemensajeEntities(true, -1, -1);
	}

	/**
	 * Method findClientemensajeEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Clientemensaje>
	 */
	public List<Clientemensaje> findClientemensajeEntities(int maxResults, int firstResult) {
		return findClientemensajeEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findClientemensajeEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Clientemensaje>
	 */
	private List<Clientemensaje> findClientemensajeEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Clientemensaje.class));
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
	 * Method findClientemensaje.
	 * 
	 * @param id
	 *            Long
	 * @return Clientemensaje
	 */
	public Clientemensaje findClientemensaje(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Clientemensaje.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getClientemensajeCount.
	 * 
	 * @return int
	 */
	public int getClientemensajeCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Clientemensaje> rt = cq.from(Clientemensaje.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
