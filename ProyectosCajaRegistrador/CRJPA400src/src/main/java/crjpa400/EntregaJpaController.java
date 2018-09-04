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
public class EntregaJpaController implements Serializable {

	/**
	 * Constructor for EntregaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public EntregaJpaController(EntityManagerFactory emf) {
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
	 * @param entrega
	 *            Entrega
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Entrega entrega) throws PreexistingEntityException,
			Exception {
		if (entrega.getEntregaformadepagoList() == null) {
			entrega.setEntregaformadepagoList(new ArrayList<Entregaformadepago>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Usuario idUsuarioautorizante = entrega.getIdUsuarioautorizante();
			if (idUsuarioautorizante != null) {
				idUsuarioautorizante = em.getReference(
						idUsuarioautorizante.getClass(),
						idUsuarioautorizante.getId());
				entrega.setIdUsuarioautorizante(idUsuarioautorizante);
			}
			Sesion idSesion = entrega.getIdSesion();
			if (idSesion != null) {
				idSesion = em.getReference(idSesion.getClass(),
						idSesion.getId());
				entrega.setIdSesion(idSesion);
			}
			List<Entregaformadepago> attachedEntregaformadepagoList = new ArrayList<Entregaformadepago>();
			for (Entregaformadepago entregaformadepagoListEntregaformadepagoToAttach : entrega
					.getEntregaformadepagoList()) {
				entregaformadepagoListEntregaformadepagoToAttach = em
						.getReference(
								entregaformadepagoListEntregaformadepagoToAttach
										.getClass(),
								entregaformadepagoListEntregaformadepagoToAttach
										.getId());
				attachedEntregaformadepagoList
						.add(entregaformadepagoListEntregaformadepagoToAttach);
			}
			entrega.setEntregaformadepagoList(attachedEntregaformadepagoList);
			em.persist(entrega);
			if (idUsuarioautorizante != null) {
				idUsuarioautorizante.getEntregaList().add(entrega);
				idUsuarioautorizante = em.merge(idUsuarioautorizante);
			}
			if (idSesion != null) {
				idSesion.getEntregaList().add(entrega);
				idSesion = em.merge(idSesion);
			}
			for (Entregaformadepago entregaformadepagoListEntregaformadepago : entrega
					.getEntregaformadepagoList()) {
				Entrega oldIdEntregaOfEntregaformadepagoListEntregaformadepago = entregaformadepagoListEntregaformadepago
						.getIdEntrega();
				entregaformadepagoListEntregaformadepago.setIdEntrega(entrega);
				entregaformadepagoListEntregaformadepago = em
						.merge(entregaformadepagoListEntregaformadepago);
				if (oldIdEntregaOfEntregaformadepagoListEntregaformadepago != null) {
					oldIdEntregaOfEntregaformadepagoListEntregaformadepago
							.getEntregaformadepagoList().remove(
									entregaformadepagoListEntregaformadepago);
					oldIdEntregaOfEntregaformadepagoListEntregaformadepago = em
							.merge(oldIdEntregaOfEntregaformadepagoListEntregaformadepago);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findEntrega(entrega.getId()) != null) {
				throw new PreexistingEntityException("Entrega " + entrega
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
	 * @param entrega
	 *            Entrega
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Entrega entrega) throws IllegalOrphanException,
			NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Entrega persistentEntrega = em.find(Entrega.class, entrega.getId());
			Usuario idUsuarioautorizanteOld = persistentEntrega
					.getIdUsuarioautorizante();
			Usuario idUsuarioautorizanteNew = entrega.getIdUsuarioautorizante();
			Sesion idSesionOld = persistentEntrega.getIdSesion();
			Sesion idSesionNew = entrega.getIdSesion();
			List<Entregaformadepago> entregaformadepagoListOld = persistentEntrega
					.getEntregaformadepagoList();
			List<Entregaformadepago> entregaformadepagoListNew = entrega
					.getEntregaformadepagoList();
			List<String> illegalOrphanMessages = null;
			for (Entregaformadepago entregaformadepagoListOldEntregaformadepago : entregaformadepagoListOld) {
				if (!entregaformadepagoListNew
						.contains(entregaformadepagoListOldEntregaformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Entregaformadepago "
									+ entregaformadepagoListOldEntregaformadepago
									+ " since its idEntrega field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idUsuarioautorizanteNew != null) {
				idUsuarioautorizanteNew = em.getReference(
						idUsuarioautorizanteNew.getClass(),
						idUsuarioautorizanteNew.getId());
				entrega.setIdUsuarioautorizante(idUsuarioautorizanteNew);
			}
			if (idSesionNew != null) {
				idSesionNew = em.getReference(idSesionNew.getClass(),
						idSesionNew.getId());
				entrega.setIdSesion(idSesionNew);
			}
			List<Entregaformadepago> attachedEntregaformadepagoListNew = new ArrayList<Entregaformadepago>();
			for (Entregaformadepago entregaformadepagoListNewEntregaformadepagoToAttach : entregaformadepagoListNew) {
				entregaformadepagoListNewEntregaformadepagoToAttach = em
						.getReference(
								entregaformadepagoListNewEntregaformadepagoToAttach
										.getClass(),
								entregaformadepagoListNewEntregaformadepagoToAttach
										.getId());
				attachedEntregaformadepagoListNew
						.add(entregaformadepagoListNewEntregaformadepagoToAttach);
			}
			entregaformadepagoListNew = attachedEntregaformadepagoListNew;
			entrega.setEntregaformadepagoList(entregaformadepagoListNew);
			entrega = em.merge(entrega);
			if (idUsuarioautorizanteOld != null
					&& !idUsuarioautorizanteOld.equals(idUsuarioautorizanteNew)) {
				idUsuarioautorizanteOld.getEntregaList().remove(entrega);
				idUsuarioautorizanteOld = em.merge(idUsuarioautorizanteOld);
			}
			if (idUsuarioautorizanteNew != null
					&& !idUsuarioautorizanteNew.equals(idUsuarioautorizanteOld)) {
				idUsuarioautorizanteNew.getEntregaList().add(entrega);
				idUsuarioautorizanteNew = em.merge(idUsuarioautorizanteNew);
			}
			if (idSesionOld != null && !idSesionOld.equals(idSesionNew)) {
				idSesionOld.getEntregaList().remove(entrega);
				idSesionOld = em.merge(idSesionOld);
			}
			if (idSesionNew != null && !idSesionNew.equals(idSesionOld)) {
				idSesionNew.getEntregaList().add(entrega);
				idSesionNew = em.merge(idSesionNew);
			}
			for (Entregaformadepago entregaformadepagoListNewEntregaformadepago : entregaformadepagoListNew) {
				if (!entregaformadepagoListOld
						.contains(entregaformadepagoListNewEntregaformadepago)) {
					Entrega oldIdEntregaOfEntregaformadepagoListNewEntregaformadepago = entregaformadepagoListNewEntregaformadepago
							.getIdEntrega();
					entregaformadepagoListNewEntregaformadepago
							.setIdEntrega(entrega);
					entregaformadepagoListNewEntregaformadepago = em
							.merge(entregaformadepagoListNewEntregaformadepago);
					if (oldIdEntregaOfEntregaformadepagoListNewEntregaformadepago != null
							&& !oldIdEntregaOfEntregaformadepagoListNewEntregaformadepago
									.equals(entrega)) {
						oldIdEntregaOfEntregaformadepagoListNewEntregaformadepago
								.getEntregaformadepagoList()
								.remove(entregaformadepagoListNewEntregaformadepago);
						oldIdEntregaOfEntregaformadepagoListNewEntregaformadepago = em
								.merge(oldIdEntregaOfEntregaformadepagoListNewEntregaformadepago);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = entrega.getId();
				if (findEntrega(id) == null) {
					throw new NonexistentEntityException("The entrega with id "
							+ id + " no longer exists.");
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
			Entrega entrega;
			try {
				entrega = em.getReference(Entrega.class, id);
				entrega.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The entrega with id "
						+ id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Entregaformadepago> entregaformadepagoListOrphanCheck = entrega
					.getEntregaformadepagoList();
			for (Entregaformadepago entregaformadepagoListOrphanCheckEntregaformadepago : entregaformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Entrega ("
								+ entrega
								+ ") cannot be destroyed since the Entregaformadepago "
								+ entregaformadepagoListOrphanCheckEntregaformadepago
								+ " in its entregaformadepagoList field has a non-nullable idEntrega field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Usuario idUsuarioautorizante = entrega.getIdUsuarioautorizante();
			if (idUsuarioautorizante != null) {
				idUsuarioautorizante.getEntregaList().remove(entrega);
				idUsuarioautorizante = em.merge(idUsuarioautorizante);
			}
			Sesion idSesion = entrega.getIdSesion();
			if (idSesion != null) {
				idSesion.getEntregaList().remove(entrega);
				idSesion = em.merge(idSesion);
			}
			em.remove(entrega);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findEntregaEntities.
	 * 
	 * @return List<Entrega>
	 */
	public List<Entrega> findEntregaEntities() {
		return findEntregaEntities(true, -1, -1);
	}

	/**
	 * Method findEntregaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Entrega>
	 */
	public List<Entrega> findEntregaEntities(int maxResults, int firstResult) {
		return findEntregaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findEntregaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Entrega>
	 */
	private List<Entrega> findEntregaEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Entrega.class));
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
	 * Method findEntrega.
	 * 
	 * @param id
	 *            Long
	 * @return Entrega
	 */
	public Entrega findEntrega(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Entrega.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getEntregaCount.
	 * 
	 * @return int
	 */
	public int getEntregaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Entrega> rt = cq.from(Entrega.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
