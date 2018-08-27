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
public class TipoacreenciaoperacionJpaController implements Serializable {

	/**
	 * Constructor for TipoacreenciaoperacionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TipoacreenciaoperacionJpaController(EntityManagerFactory emf) {
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
	 * @param tipoacreenciaoperacion
	 *            Tipoacreenciaoperacion
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Tipoacreenciaoperacion tipoacreenciaoperacion)
			throws PreexistingEntityException, Exception {
		if (tipoacreenciaoperacion.getMovimientoacreenciaList() == null) {
			tipoacreenciaoperacion
					.setMovimientoacreenciaList(new ArrayList<Movimientoacreencia>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipoacreencia idTipoacreencia = tipoacreenciaoperacion
					.getIdTipoacreencia();
			if (idTipoacreencia != null) {
				idTipoacreencia = em.getReference(idTipoacreencia.getClass(),
						idTipoacreencia.getId());
				tipoacreenciaoperacion.setIdTipoacreencia(idTipoacreencia);
			}
			Operacionacreencia idOperacionacreencia = tipoacreenciaoperacion
					.getIdOperacionacreencia();
			if (idOperacionacreencia != null) {
				idOperacionacreencia = em.getReference(
						idOperacionacreencia.getClass(),
						idOperacionacreencia.getId());
				tipoacreenciaoperacion
						.setIdOperacionacreencia(idOperacionacreencia);
			}
			List<Movimientoacreencia> attachedMovimientoacreenciaList = new ArrayList<Movimientoacreencia>();
			for (Movimientoacreencia movimientoacreenciaListMovimientoacreenciaToAttach : tipoacreenciaoperacion
					.getMovimientoacreenciaList()) {
				movimientoacreenciaListMovimientoacreenciaToAttach = em
						.getReference(
								movimientoacreenciaListMovimientoacreenciaToAttach
										.getClass(),
								movimientoacreenciaListMovimientoacreenciaToAttach
										.getId());
				attachedMovimientoacreenciaList
						.add(movimientoacreenciaListMovimientoacreenciaToAttach);
			}
			tipoacreenciaoperacion
					.setMovimientoacreenciaList(attachedMovimientoacreenciaList);
			em.persist(tipoacreenciaoperacion);
			if (idTipoacreencia != null) {
				idTipoacreencia.getTipoacreenciaoperacionList().add(
						tipoacreenciaoperacion);
				idTipoacreencia = em.merge(idTipoacreencia);
			}
			if (idOperacionacreencia != null) {
				idOperacionacreencia.getTipoacreenciaoperacionList().add(
						tipoacreenciaoperacion);
				idOperacionacreencia = em.merge(idOperacionacreencia);
			}
			for (Movimientoacreencia movimientoacreenciaListMovimientoacreencia : tipoacreenciaoperacion
					.getMovimientoacreenciaList()) {
				Tipoacreenciaoperacion oldIdTipoacreenciaoperacionOfMovimientoacreenciaListMovimientoacreencia = movimientoacreenciaListMovimientoacreencia
						.getIdTipoacreenciaoperacion();
				movimientoacreenciaListMovimientoacreencia
						.setIdTipoacreenciaoperacion(tipoacreenciaoperacion);
				movimientoacreenciaListMovimientoacreencia = em
						.merge(movimientoacreenciaListMovimientoacreencia);
				if (oldIdTipoacreenciaoperacionOfMovimientoacreenciaListMovimientoacreencia != null) {
					oldIdTipoacreenciaoperacionOfMovimientoacreenciaListMovimientoacreencia
							.getMovimientoacreenciaList().remove(
									movimientoacreenciaListMovimientoacreencia);
					oldIdTipoacreenciaoperacionOfMovimientoacreenciaListMovimientoacreencia = em
							.merge(oldIdTipoacreenciaoperacionOfMovimientoacreenciaListMovimientoacreencia);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTipoacreenciaoperacion(tipoacreenciaoperacion.getId()) != null) {
				throw new PreexistingEntityException("Tipoacreenciaoperacion "
						+ tipoacreenciaoperacion + " already exists.", ex);
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
	 * @param tipoacreenciaoperacion
	 *            Tipoacreenciaoperacion
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Tipoacreenciaoperacion tipoacreenciaoperacion)
			throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipoacreenciaoperacion persistentTipoacreenciaoperacion = em.find(
					Tipoacreenciaoperacion.class,
					tipoacreenciaoperacion.getId());
			Tipoacreencia idTipoacreenciaOld = persistentTipoacreenciaoperacion
					.getIdTipoacreencia();
			Tipoacreencia idTipoacreenciaNew = tipoacreenciaoperacion
					.getIdTipoacreencia();
			Operacionacreencia idOperacionacreenciaOld = persistentTipoacreenciaoperacion
					.getIdOperacionacreencia();
			Operacionacreencia idOperacionacreenciaNew = tipoacreenciaoperacion
					.getIdOperacionacreencia();
			List<Movimientoacreencia> movimientoacreenciaListOld = persistentTipoacreenciaoperacion
					.getMovimientoacreenciaList();
			List<Movimientoacreencia> movimientoacreenciaListNew = tipoacreenciaoperacion
					.getMovimientoacreenciaList();
			List<String> illegalOrphanMessages = null;
			for (Movimientoacreencia movimientoacreenciaListOldMovimientoacreencia : movimientoacreenciaListOld) {
				if (!movimientoacreenciaListNew
						.contains(movimientoacreenciaListOldMovimientoacreencia)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Movimientoacreencia "
									+ movimientoacreenciaListOldMovimientoacreencia
									+ " since its idTipoacreenciaoperacion field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idTipoacreenciaNew != null) {
				idTipoacreenciaNew = em.getReference(
						idTipoacreenciaNew.getClass(),
						idTipoacreenciaNew.getId());
				tipoacreenciaoperacion.setIdTipoacreencia(idTipoacreenciaNew);
			}
			if (idOperacionacreenciaNew != null) {
				idOperacionacreenciaNew = em.getReference(
						idOperacionacreenciaNew.getClass(),
						idOperacionacreenciaNew.getId());
				tipoacreenciaoperacion
						.setIdOperacionacreencia(idOperacionacreenciaNew);
			}
			List<Movimientoacreencia> attachedMovimientoacreenciaListNew = new ArrayList<Movimientoacreencia>();
			for (Movimientoacreencia movimientoacreenciaListNewMovimientoacreenciaToAttach : movimientoacreenciaListNew) {
				movimientoacreenciaListNewMovimientoacreenciaToAttach = em
						.getReference(
								movimientoacreenciaListNewMovimientoacreenciaToAttach
										.getClass(),
								movimientoacreenciaListNewMovimientoacreenciaToAttach
										.getId());
				attachedMovimientoacreenciaListNew
						.add(movimientoacreenciaListNewMovimientoacreenciaToAttach);
			}
			movimientoacreenciaListNew = attachedMovimientoacreenciaListNew;
			tipoacreenciaoperacion
					.setMovimientoacreenciaList(movimientoacreenciaListNew);
			tipoacreenciaoperacion = em.merge(tipoacreenciaoperacion);
			if (idTipoacreenciaOld != null
					&& !idTipoacreenciaOld.equals(idTipoacreenciaNew)) {
				idTipoacreenciaOld.getTipoacreenciaoperacionList().remove(
						tipoacreenciaoperacion);
				idTipoacreenciaOld = em.merge(idTipoacreenciaOld);
			}
			if (idTipoacreenciaNew != null
					&& !idTipoacreenciaNew.equals(idTipoacreenciaOld)) {
				idTipoacreenciaNew.getTipoacreenciaoperacionList().add(
						tipoacreenciaoperacion);
				idTipoacreenciaNew = em.merge(idTipoacreenciaNew);
			}
			if (idOperacionacreenciaOld != null
					&& !idOperacionacreenciaOld.equals(idOperacionacreenciaNew)) {
				idOperacionacreenciaOld.getTipoacreenciaoperacionList().remove(
						tipoacreenciaoperacion);
				idOperacionacreenciaOld = em.merge(idOperacionacreenciaOld);
			}
			if (idOperacionacreenciaNew != null
					&& !idOperacionacreenciaNew.equals(idOperacionacreenciaOld)) {
				idOperacionacreenciaNew.getTipoacreenciaoperacionList().add(
						tipoacreenciaoperacion);
				idOperacionacreenciaNew = em.merge(idOperacionacreenciaNew);
			}
			for (Movimientoacreencia movimientoacreenciaListNewMovimientoacreencia : movimientoacreenciaListNew) {
				if (!movimientoacreenciaListOld
						.contains(movimientoacreenciaListNewMovimientoacreencia)) {
					Tipoacreenciaoperacion oldIdTipoacreenciaoperacionOfMovimientoacreenciaListNewMovimientoacreencia = movimientoacreenciaListNewMovimientoacreencia
							.getIdTipoacreenciaoperacion();
					movimientoacreenciaListNewMovimientoacreencia
							.setIdTipoacreenciaoperacion(tipoacreenciaoperacion);
					movimientoacreenciaListNewMovimientoacreencia = em
							.merge(movimientoacreenciaListNewMovimientoacreencia);
					if (oldIdTipoacreenciaoperacionOfMovimientoacreenciaListNewMovimientoacreencia != null
							&& !oldIdTipoacreenciaoperacionOfMovimientoacreenciaListNewMovimientoacreencia
									.equals(tipoacreenciaoperacion)) {
						oldIdTipoacreenciaoperacionOfMovimientoacreenciaListNewMovimientoacreencia
								.getMovimientoacreenciaList()
								.remove(movimientoacreenciaListNewMovimientoacreencia);
						oldIdTipoacreenciaoperacionOfMovimientoacreenciaListNewMovimientoacreencia = em
								.merge(oldIdTipoacreenciaoperacionOfMovimientoacreenciaListNewMovimientoacreencia);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = tipoacreenciaoperacion.getId();
				if (findTipoacreenciaoperacion(id) == null) {
					throw new NonexistentEntityException(
							"The tipoacreenciaoperacion with id " + id
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
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 */
	public void destroy(Long id) throws IllegalOrphanException,
			NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipoacreenciaoperacion tipoacreenciaoperacion;
			try {
				tipoacreenciaoperacion = em.getReference(
						Tipoacreenciaoperacion.class, id);
				tipoacreenciaoperacion.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The tipoacreenciaoperacion with id " + id
								+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Movimientoacreencia> movimientoacreenciaListOrphanCheck = tipoacreenciaoperacion
					.getMovimientoacreenciaList();
			for (Movimientoacreencia movimientoacreenciaListOrphanCheckMovimientoacreencia : movimientoacreenciaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Tipoacreenciaoperacion ("
								+ tipoacreenciaoperacion
								+ ") cannot be destroyed since the Movimientoacreencia "
								+ movimientoacreenciaListOrphanCheckMovimientoacreencia
								+ " in its movimientoacreenciaList field has a non-nullable idTipoacreenciaoperacion field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Tipoacreencia idTipoacreencia = tipoacreenciaoperacion
					.getIdTipoacreencia();
			if (idTipoacreencia != null) {
				idTipoacreencia.getTipoacreenciaoperacionList().remove(
						tipoacreenciaoperacion);
				idTipoacreencia = em.merge(idTipoacreencia);
			}
			Operacionacreencia idOperacionacreencia = tipoacreenciaoperacion
					.getIdOperacionacreencia();
			if (idOperacionacreencia != null) {
				idOperacionacreencia.getTipoacreenciaoperacionList().remove(
						tipoacreenciaoperacion);
				idOperacionacreencia = em.merge(idOperacionacreencia);
			}
			em.remove(tipoacreenciaoperacion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTipoacreenciaoperacionEntities.
	 * 
	 * @return List<Tipoacreenciaoperacion>
	 */
	public List<Tipoacreenciaoperacion> findTipoacreenciaoperacionEntities() {
		return findTipoacreenciaoperacionEntities(true, -1, -1);
	}

	/**
	 * Method findTipoacreenciaoperacionEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipoacreenciaoperacion>
	 */
	public List<Tipoacreenciaoperacion> findTipoacreenciaoperacionEntities(
			int maxResults, int firstResult) {
		return findTipoacreenciaoperacionEntities(false, maxResults,
				firstResult);
	}

	/**
	 * Method findTipoacreenciaoperacionEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipoacreenciaoperacion>
	 */
	private List<Tipoacreenciaoperacion> findTipoacreenciaoperacionEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Tipoacreenciaoperacion.class));
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
	 * Method findTipoacreenciaoperacion.
	 * 
	 * @param id
	 *            Long
	 * @return Tipoacreenciaoperacion
	 */
	public Tipoacreenciaoperacion findTipoacreenciaoperacion(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Tipoacreenciaoperacion.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTipoacreenciaoperacionCount.
	 * 
	 * @return int
	 */
	public int getTipoacreenciaoperacionCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Tipoacreenciaoperacion> rt = cq
					.from(Tipoacreenciaoperacion.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
