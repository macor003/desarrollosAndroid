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

import crjpa400.exceptions.NonexistentEntityException;
import crjpa400.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class RolloauditoriaJpaController implements Serializable {

	/**
	 * Constructor for RolloauditoriaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public RolloauditoriaJpaController(EntityManagerFactory emf) {
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
	 * @param rolloauditoria
	 *            Rolloauditoria
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Rolloauditoria rolloauditoria)
			throws PreexistingEntityException, Exception {
		if (rolloauditoria.getTransaccionList() == null) {
			rolloauditoria.setTransaccionList(new ArrayList<Transaccion>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Serialimpresora idSerialimpresora = rolloauditoria
					.getIdSerialimpresora();
			if (idSerialimpresora != null) {
				idSerialimpresora = em
						.getReference(idSerialimpresora.getClass(),
								idSerialimpresora.getId());
				rolloauditoria.setIdSerialimpresora(idSerialimpresora);
			}
			Usuario idUsuario = rolloauditoria.getIdUsuario();
			if (idUsuario != null) {
				idUsuario = em.getReference(idUsuario.getClass(),
						idUsuario.getId());
				rolloauditoria.setIdUsuario(idUsuario);
			}
			List<Transaccion> attachedTransaccionList = new ArrayList<Transaccion>();
			for (Transaccion transaccionListTransaccionToAttach : rolloauditoria
					.getTransaccionList()) {
				transaccionListTransaccionToAttach = em.getReference(
						transaccionListTransaccionToAttach.getClass(),
						transaccionListTransaccionToAttach.getId());
				attachedTransaccionList.add(transaccionListTransaccionToAttach);
			}
			rolloauditoria.setTransaccionList(attachedTransaccionList);
			em.persist(rolloauditoria);
			if (idSerialimpresora != null) {
				idSerialimpresora.getRolloauditoriaList().add(rolloauditoria);
				idSerialimpresora = em.merge(idSerialimpresora);
			}
			if (idUsuario != null) {
				idUsuario.getRolloauditoriaList().add(rolloauditoria);
				idUsuario = em.merge(idUsuario);
			}
			for (Transaccion transaccionListTransaccion : rolloauditoria
					.getTransaccionList()) {
				Rolloauditoria oldIdRolloauditoriaOfTransaccionListTransaccion = transaccionListTransaccion
						.getIdRolloauditoria();
				transaccionListTransaccion.setIdRolloauditoria(rolloauditoria);
				transaccionListTransaccion = em
						.merge(transaccionListTransaccion);
				if (oldIdRolloauditoriaOfTransaccionListTransaccion != null) {
					oldIdRolloauditoriaOfTransaccionListTransaccion
							.getTransaccionList().remove(
									transaccionListTransaccion);
					oldIdRolloauditoriaOfTransaccionListTransaccion = em
							.merge(oldIdRolloauditoriaOfTransaccionListTransaccion);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findRolloauditoria(rolloauditoria.getId()) != null) {
				throw new PreexistingEntityException("Rolloauditoria "
						+ rolloauditoria + " already exists.", ex);
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
	 * @param rolloauditoria
	 *            Rolloauditoria
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Rolloauditoria rolloauditoria)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Rolloauditoria persistentRolloauditoria = em.find(
					Rolloauditoria.class, rolloauditoria.getId());
			Serialimpresora idSerialimpresoraOld = persistentRolloauditoria
					.getIdSerialimpresora();
			Serialimpresora idSerialimpresoraNew = rolloauditoria
					.getIdSerialimpresora();
			Usuario idUsuarioOld = persistentRolloauditoria.getIdUsuario();
			Usuario idUsuarioNew = rolloauditoria.getIdUsuario();
			List<Transaccion> transaccionListOld = persistentRolloauditoria
					.getTransaccionList();
			List<Transaccion> transaccionListNew = rolloauditoria
					.getTransaccionList();
			if (idSerialimpresoraNew != null) {
				idSerialimpresoraNew = em.getReference(
						idSerialimpresoraNew.getClass(),
						idSerialimpresoraNew.getId());
				rolloauditoria.setIdSerialimpresora(idSerialimpresoraNew);
			}
			if (idUsuarioNew != null) {
				idUsuarioNew = em.getReference(idUsuarioNew.getClass(),
						idUsuarioNew.getId());
				rolloauditoria.setIdUsuario(idUsuarioNew);
			}
			List<Transaccion> attachedTransaccionListNew = new ArrayList<Transaccion>();
			for (Transaccion transaccionListNewTransaccionToAttach : transaccionListNew) {
				transaccionListNewTransaccionToAttach = em.getReference(
						transaccionListNewTransaccionToAttach.getClass(),
						transaccionListNewTransaccionToAttach.getId());
				attachedTransaccionListNew
						.add(transaccionListNewTransaccionToAttach);
			}
			transaccionListNew = attachedTransaccionListNew;
			rolloauditoria.setTransaccionList(transaccionListNew);
			rolloauditoria = em.merge(rolloauditoria);
			if (idSerialimpresoraOld != null
					&& !idSerialimpresoraOld.equals(idSerialimpresoraNew)) {
				idSerialimpresoraOld.getRolloauditoriaList().remove(
						rolloauditoria);
				idSerialimpresoraOld = em.merge(idSerialimpresoraOld);
			}
			if (idSerialimpresoraNew != null
					&& !idSerialimpresoraNew.equals(idSerialimpresoraOld)) {
				idSerialimpresoraNew.getRolloauditoriaList()
						.add(rolloauditoria);
				idSerialimpresoraNew = em.merge(idSerialimpresoraNew);
			}
			if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
				idUsuarioOld.getRolloauditoriaList().remove(rolloauditoria);
				idUsuarioOld = em.merge(idUsuarioOld);
			}
			if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
				idUsuarioNew.getRolloauditoriaList().add(rolloauditoria);
				idUsuarioNew = em.merge(idUsuarioNew);
			}
			for (Transaccion transaccionListOldTransaccion : transaccionListOld) {
				if (!transaccionListNew.contains(transaccionListOldTransaccion)) {
					transaccionListOldTransaccion.setIdRolloauditoria(null);
					transaccionListOldTransaccion = em
							.merge(transaccionListOldTransaccion);
				}
			}
			for (Transaccion transaccionListNewTransaccion : transaccionListNew) {
				if (!transaccionListOld.contains(transaccionListNewTransaccion)) {
					Rolloauditoria oldIdRolloauditoriaOfTransaccionListNewTransaccion = transaccionListNewTransaccion
							.getIdRolloauditoria();
					transaccionListNewTransaccion
							.setIdRolloauditoria(rolloauditoria);
					transaccionListNewTransaccion = em
							.merge(transaccionListNewTransaccion);
					if (oldIdRolloauditoriaOfTransaccionListNewTransaccion != null
							&& !oldIdRolloauditoriaOfTransaccionListNewTransaccion
									.equals(rolloauditoria)) {
						oldIdRolloauditoriaOfTransaccionListNewTransaccion
								.getTransaccionList().remove(
										transaccionListNewTransaccion);
						oldIdRolloauditoriaOfTransaccionListNewTransaccion = em
								.merge(oldIdRolloauditoriaOfTransaccionListNewTransaccion);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = rolloauditoria.getId();
				if (findRolloauditoria(id) == null) {
					throw new NonexistentEntityException(
							"The rolloauditoria with id " + id
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
			Rolloauditoria rolloauditoria;
			try {
				rolloauditoria = em.getReference(Rolloauditoria.class, id);
				rolloauditoria.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The rolloauditoria with id " + id
								+ " no longer exists.", enfe);
			}
			Serialimpresora idSerialimpresora = rolloauditoria
					.getIdSerialimpresora();
			if (idSerialimpresora != null) {
				idSerialimpresora.getRolloauditoriaList()
						.remove(rolloauditoria);
				idSerialimpresora = em.merge(idSerialimpresora);
			}
			Usuario idUsuario = rolloauditoria.getIdUsuario();
			if (idUsuario != null) {
				idUsuario.getRolloauditoriaList().remove(rolloauditoria);
				idUsuario = em.merge(idUsuario);
			}
			List<Transaccion> transaccionList = rolloauditoria
					.getTransaccionList();
			for (Transaccion transaccionListTransaccion : transaccionList) {
				transaccionListTransaccion.setIdRolloauditoria(null);
				transaccionListTransaccion = em
						.merge(transaccionListTransaccion);
			}
			em.remove(rolloauditoria);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findRolloauditoriaEntities.
	 * 
	 * @return List<Rolloauditoria>
	 */
	public List<Rolloauditoria> findRolloauditoriaEntities() {
		return findRolloauditoriaEntities(true, -1, -1);
	}

	/**
	 * Method findRolloauditoriaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Rolloauditoria>
	 */
	public List<Rolloauditoria> findRolloauditoriaEntities(int maxResults,
			int firstResult) {
		return findRolloauditoriaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findRolloauditoriaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Rolloauditoria>
	 */
	private List<Rolloauditoria> findRolloauditoriaEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Rolloauditoria.class));
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
	 * Method findRolloauditoria.
	 * 
	 * @param id
	 *            Long
	 * @return Rolloauditoria
	 */
	public Rolloauditoria findRolloauditoria(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Rolloauditoria.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getRolloauditoriaCount.
	 * 
	 * @return int
	 */
	public int getRolloauditoriaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Rolloauditoria> rt = cq.from(Rolloauditoria.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
