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
public class CajaJpaController implements Serializable {

	/**
	 * Constructor for CajaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public CajaJpaController(EntityManagerFactory emf) {
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
	 * @param caja
	 *            Caja
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Caja caja) throws PreexistingEntityException, Exception {
		if (caja.getSesionList() == null) {
			caja.setSesionList(new ArrayList<Sesion>());
		}
		if (caja.getComprobantefiscalpreimpresoList() == null) {
			caja.setComprobantefiscalpreimpresoList(new ArrayList<Comprobantefiscalpreimpreso>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Sesion> attachedSesionList = new ArrayList<Sesion>();
			for (Sesion sesionListSesionToAttach : caja.getSesionList()) {
				sesionListSesionToAttach = em.getReference(
						sesionListSesionToAttach.getClass(),
						sesionListSesionToAttach.getId());
				attachedSesionList.add(sesionListSesionToAttach);
			}
			caja.setSesionList(attachedSesionList);
			List<Comprobantefiscalpreimpreso> attachedComprobantefiscalpreimpresoList = new ArrayList<Comprobantefiscalpreimpreso>();
			for (Comprobantefiscalpreimpreso comprobantefiscalpreimpresoListComprobantefiscalpreimpresoToAttach : caja
					.getComprobantefiscalpreimpresoList()) {
				comprobantefiscalpreimpresoListComprobantefiscalpreimpresoToAttach = em
						.getReference(
								comprobantefiscalpreimpresoListComprobantefiscalpreimpresoToAttach
										.getClass(),
								comprobantefiscalpreimpresoListComprobantefiscalpreimpresoToAttach
										.getId());
				attachedComprobantefiscalpreimpresoList
						.add(comprobantefiscalpreimpresoListComprobantefiscalpreimpresoToAttach);
			}
			caja.setComprobantefiscalpreimpresoList(attachedComprobantefiscalpreimpresoList);
			em.persist(caja);
			for (Sesion sesionListSesion : caja.getSesionList()) {
				Caja oldIdCajaOfSesionListSesion = sesionListSesion.getIdCaja();
				sesionListSesion.setIdCaja(caja);
				sesionListSesion = em.merge(sesionListSesion);
				if (oldIdCajaOfSesionListSesion != null) {
					oldIdCajaOfSesionListSesion.getSesionList().remove(
							sesionListSesion);
					oldIdCajaOfSesionListSesion = em
							.merge(oldIdCajaOfSesionListSesion);
				}
			}
			for (Comprobantefiscalpreimpreso comprobantefiscalpreimpresoListComprobantefiscalpreimpreso : caja
					.getComprobantefiscalpreimpresoList()) {
				Caja oldIdCajaOfComprobantefiscalpreimpresoListComprobantefiscalpreimpreso = comprobantefiscalpreimpresoListComprobantefiscalpreimpreso
						.getIdCaja();
				comprobantefiscalpreimpresoListComprobantefiscalpreimpreso
						.setIdCaja(caja);
				comprobantefiscalpreimpresoListComprobantefiscalpreimpreso = em
						.merge(comprobantefiscalpreimpresoListComprobantefiscalpreimpreso);
				if (oldIdCajaOfComprobantefiscalpreimpresoListComprobantefiscalpreimpreso != null) {
					oldIdCajaOfComprobantefiscalpreimpresoListComprobantefiscalpreimpreso
							.getComprobantefiscalpreimpresoList()
							.remove(comprobantefiscalpreimpresoListComprobantefiscalpreimpreso);
					oldIdCajaOfComprobantefiscalpreimpresoListComprobantefiscalpreimpreso = em
							.merge(oldIdCajaOfComprobantefiscalpreimpresoListComprobantefiscalpreimpreso);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findCaja(caja.getId()) != null) {
				throw new PreexistingEntityException("Caja " + caja
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
	 * @param caja
	 *            Caja
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Caja caja) throws IllegalOrphanException,
			NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Caja persistentCaja = em.find(Caja.class, caja.getId());
			List<Sesion> sesionListOld = persistentCaja.getSesionList();
			List<Sesion> sesionListNew = caja.getSesionList();
			List<Comprobantefiscalpreimpreso> comprobantefiscalpreimpresoListOld = persistentCaja
					.getComprobantefiscalpreimpresoList();
			List<Comprobantefiscalpreimpreso> comprobantefiscalpreimpresoListNew = caja
					.getComprobantefiscalpreimpresoList();
			List<String> illegalOrphanMessages = null;
			for (Sesion sesionListOldSesion : sesionListOld) {
				if (!sesionListNew.contains(sesionListOldSesion)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Sesion "
							+ sesionListOldSesion
							+ " since its idCaja field is not nullable.");
				}
			}
			for (Comprobantefiscalpreimpreso comprobantefiscalpreimpresoListOldComprobantefiscalpreimpreso : comprobantefiscalpreimpresoListOld) {
				if (!comprobantefiscalpreimpresoListNew
						.contains(comprobantefiscalpreimpresoListOldComprobantefiscalpreimpreso)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Comprobantefiscalpreimpreso "
									+ comprobantefiscalpreimpresoListOldComprobantefiscalpreimpreso
									+ " since its idCaja field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Sesion> attachedSesionListNew = new ArrayList<Sesion>();
			for (Sesion sesionListNewSesionToAttach : sesionListNew) {
				sesionListNewSesionToAttach = em.getReference(
						sesionListNewSesionToAttach.getClass(),
						sesionListNewSesionToAttach.getId());
				attachedSesionListNew.add(sesionListNewSesionToAttach);
			}
			sesionListNew = attachedSesionListNew;
			caja.setSesionList(sesionListNew);
			List<Comprobantefiscalpreimpreso> attachedComprobantefiscalpreimpresoListNew = new ArrayList<Comprobantefiscalpreimpreso>();
			for (Comprobantefiscalpreimpreso comprobantefiscalpreimpresoListNewComprobantefiscalpreimpresoToAttach : comprobantefiscalpreimpresoListNew) {
				comprobantefiscalpreimpresoListNewComprobantefiscalpreimpresoToAttach = em
						.getReference(
								comprobantefiscalpreimpresoListNewComprobantefiscalpreimpresoToAttach
										.getClass(),
								comprobantefiscalpreimpresoListNewComprobantefiscalpreimpresoToAttach
										.getId());
				attachedComprobantefiscalpreimpresoListNew
						.add(comprobantefiscalpreimpresoListNewComprobantefiscalpreimpresoToAttach);
			}
			comprobantefiscalpreimpresoListNew = attachedComprobantefiscalpreimpresoListNew;
			caja.setComprobantefiscalpreimpresoList(comprobantefiscalpreimpresoListNew);
			caja = em.merge(caja);
			for (Sesion sesionListNewSesion : sesionListNew) {
				if (!sesionListOld.contains(sesionListNewSesion)) {
					Caja oldIdCajaOfSesionListNewSesion = sesionListNewSesion
							.getIdCaja();
					sesionListNewSesion.setIdCaja(caja);
					sesionListNewSesion = em.merge(sesionListNewSesion);
					if (oldIdCajaOfSesionListNewSesion != null
							&& !oldIdCajaOfSesionListNewSesion.equals(caja)) {
						oldIdCajaOfSesionListNewSesion.getSesionList().remove(
								sesionListNewSesion);
						oldIdCajaOfSesionListNewSesion = em
								.merge(oldIdCajaOfSesionListNewSesion);
					}
				}
			}
			for (Comprobantefiscalpreimpreso comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso : comprobantefiscalpreimpresoListNew) {
				if (!comprobantefiscalpreimpresoListOld
						.contains(comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso)) {
					Caja oldIdCajaOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso = comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso
							.getIdCaja();
					comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso
							.setIdCaja(caja);
					comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso = em
							.merge(comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso);
					if (oldIdCajaOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso != null
							&& !oldIdCajaOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso
									.equals(caja)) {
						oldIdCajaOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso
								.getComprobantefiscalpreimpresoList()
								.remove(comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso);
						oldIdCajaOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso = em
								.merge(oldIdCajaOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = caja.getId();
				if (findCaja(id) == null) {
					throw new NonexistentEntityException("The caja with id "
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
			Caja caja;
			try {
				caja = em.getReference(Caja.class, id);
				caja.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The caja with id " + id
						+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Sesion> sesionListOrphanCheck = caja.getSesionList();
			for (Sesion sesionListOrphanCheckSesion : sesionListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Caja ("
								+ caja
								+ ") cannot be destroyed since the Sesion "
								+ sesionListOrphanCheckSesion
								+ " in its sesionList field has a non-nullable idCaja field.");
			}
			List<Comprobantefiscalpreimpreso> comprobantefiscalpreimpresoListOrphanCheck = caja
					.getComprobantefiscalpreimpresoList();
			for (Comprobantefiscalpreimpreso comprobantefiscalpreimpresoListOrphanCheckComprobantefiscalpreimpreso : comprobantefiscalpreimpresoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Caja ("
								+ caja
								+ ") cannot be destroyed since the Comprobantefiscalpreimpreso "
								+ comprobantefiscalpreimpresoListOrphanCheckComprobantefiscalpreimpreso
								+ " in its comprobantefiscalpreimpresoList field has a non-nullable idCaja field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(caja);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findCajaEntities.
	 * 
	 * @return List<Caja>
	 */
	public List<Caja> findCajaEntities() {
		return findCajaEntities(true, -1, -1);
	}

	/**
	 * Method findCajaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Caja>
	 */
	public List<Caja> findCajaEntities(int maxResults, int firstResult) {
		return findCajaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findCajaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Caja>
	 */
	private List<Caja> findCajaEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Caja.class));
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
	 * Method findCaja.
	 * 
	 * @param id
	 *            Long
	 * @return Caja
	 */
	public Caja findCaja(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Caja.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getCajaCount.
	 * 
	 * @return int
	 */
	public int getCajaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Caja> rt = cq.from(Caja.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
