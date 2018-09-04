/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
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
public class SesionJpaController implements Serializable {

	/**
	 * Constructor for SesionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public SesionJpaController(EntityManagerFactory emf) {
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
	 * @param sesion
	 *            Sesion
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Sesion sesion) throws PreexistingEntityException, Exception {
		if (sesion.getEntregaList() == null) {
			sesion.setEntregaList(new ArrayList<Entrega>());
		}
		if (sesion.getReportezList() == null) {
			sesion.setReportezList(new ArrayList<Reportez>());
		}
		if (sesion.getTransaccionList() == null) {
			sesion.setTransaccionList(new ArrayList<Transaccion>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Usuario idUsuario = sesion.getIdUsuario();
			if (idUsuario != null) {
				idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getId());
				sesion.setIdUsuario(idUsuario);
			}
			List<Entrega> attachedEntregaList = new ArrayList<Entrega>();
			for (Entrega entregaListEntregaToAttach : sesion.getEntregaList()) {
				entregaListEntregaToAttach = em.getReference(entregaListEntregaToAttach.getClass(),
						entregaListEntregaToAttach.getId());
				attachedEntregaList.add(entregaListEntregaToAttach);
			}
			sesion.setEntregaList(attachedEntregaList);
			List<Reportez> attachedReportezList = new ArrayList<Reportez>();
			for (Reportez reportezListReportezToAttach : sesion.getReportezList()) {
				reportezListReportezToAttach = em.getReference(reportezListReportezToAttach.getClass(),
						reportezListReportezToAttach.getId());
				attachedReportezList.add(reportezListReportezToAttach);
			}
			sesion.setReportezList(attachedReportezList);
			List<Transaccion> attachedTransaccionList = new ArrayList<Transaccion>();
			for (Transaccion transaccionListTransaccionToAttach : sesion.getTransaccionList()) {
				transaccionListTransaccionToAttach = em.getReference(transaccionListTransaccionToAttach.getClass(),
						transaccionListTransaccionToAttach.getId());
				attachedTransaccionList.add(transaccionListTransaccionToAttach);
			}
			sesion.setTransaccionList(attachedTransaccionList);
			em.persist(sesion);
			if (idUsuario != null) {
				idUsuario.getSesionList().add(sesion);
				idUsuario = em.merge(idUsuario);
			}
			for (Entrega entregaListEntrega : sesion.getEntregaList()) {
				Sesion oldIdSesionOfEntregaListEntrega = entregaListEntrega.getIdSesion();
				entregaListEntrega.setIdSesion(sesion);
				entregaListEntrega = em.merge(entregaListEntrega);
				if (oldIdSesionOfEntregaListEntrega != null) {
					oldIdSesionOfEntregaListEntrega.getEntregaList().remove(entregaListEntrega);
					oldIdSesionOfEntregaListEntrega = em.merge(oldIdSesionOfEntregaListEntrega);
				}
			}
			for (Reportez reportezListReportez : sesion.getReportezList()) {
				Sesion oldIdSesionOfReportezListReportez = reportezListReportez.getIdSesion();
				reportezListReportez.setIdSesion(sesion);
				reportezListReportez = em.merge(reportezListReportez);
				if (oldIdSesionOfReportezListReportez != null) {
					oldIdSesionOfReportezListReportez.getReportezList().remove(reportezListReportez);
					oldIdSesionOfReportezListReportez = em.merge(oldIdSesionOfReportezListReportez);
				}
			}
			for (Transaccion transaccionListTransaccion : sesion.getTransaccionList()) {
				Sesion oldIdSesionOfTransaccionListTransaccion = transaccionListTransaccion.getIdSesion();
				transaccionListTransaccion.setIdSesion(sesion);
				transaccionListTransaccion = em.merge(transaccionListTransaccion);
				if (oldIdSesionOfTransaccionListTransaccion != null) {
					oldIdSesionOfTransaccionListTransaccion.getTransaccionList().remove(transaccionListTransaccion);
					oldIdSesionOfTransaccionListTransaccion = em.merge(oldIdSesionOfTransaccionListTransaccion);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findSesion(sesion.getId()) != null) {
				throw new PreexistingEntityException("Sesion " + sesion + " already exists.", ex);
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
	 * @param sesion
	 *            Sesion
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Sesion sesion) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Sesion persistentSesion = em.find(Sesion.class, sesion.getId());
			Usuario idUsuarioOld = persistentSesion.getIdUsuario();
			Usuario idUsuarioNew = sesion.getIdUsuario();
			List<Entrega> entregaListOld = persistentSesion.getEntregaList();
			List<Entrega> entregaListNew = sesion.getEntregaList();
			List<Reportez> reportezListOld = persistentSesion.getReportezList();
			List<Reportez> reportezListNew = sesion.getReportezList();
			List<Transaccion> transaccionListOld = persistentSesion.getTransaccionList();
			List<Transaccion> transaccionListNew = sesion.getTransaccionList();
			List<String> illegalOrphanMessages = null;
			for (Entrega entregaListOldEntrega : entregaListOld) {
				if (!entregaListNew.contains(entregaListOldEntrega)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Entrega " + entregaListOldEntrega
							+ " since its idSesion field is not nullable.");
				}
			}
			for (Reportez reportezListOldReportez : reportezListOld) {
				if (!reportezListNew.contains(reportezListOldReportez)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Reportez " + reportezListOldReportez
							+ " since its idSesion field is not nullable.");
				}
			}
			for (Transaccion transaccionListOldTransaccion : transaccionListOld) {
				if (!transaccionListNew.contains(transaccionListOldTransaccion)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Transaccion " + transaccionListOldTransaccion
							+ " since its idSesion field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idUsuarioNew != null) {
				idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getId());
				sesion.setIdUsuario(idUsuarioNew);
			}
			List<Entrega> attachedEntregaListNew = new ArrayList<Entrega>();
			for (Entrega entregaListNewEntregaToAttach : entregaListNew) {
				entregaListNewEntregaToAttach = em.getReference(entregaListNewEntregaToAttach.getClass(),
						entregaListNewEntregaToAttach.getId());
				attachedEntregaListNew.add(entregaListNewEntregaToAttach);
			}
			entregaListNew = attachedEntregaListNew;
			sesion.setEntregaList(entregaListNew);
			List<Reportez> attachedReportezListNew = new ArrayList<Reportez>();
			for (Reportez reportezListNewReportezToAttach : reportezListNew) {
				reportezListNewReportezToAttach = em.getReference(reportezListNewReportezToAttach.getClass(),
						reportezListNewReportezToAttach.getId());
				attachedReportezListNew.add(reportezListNewReportezToAttach);
			}
			reportezListNew = attachedReportezListNew;
			sesion.setReportezList(reportezListNew);
			List<Transaccion> attachedTransaccionListNew = new ArrayList<Transaccion>();
			for (Transaccion transaccionListNewTransaccionToAttach : transaccionListNew) {
				transaccionListNewTransaccionToAttach = em
						.getReference(transaccionListNewTransaccionToAttach.getClass(),
								transaccionListNewTransaccionToAttach.getId());
				attachedTransaccionListNew.add(transaccionListNewTransaccionToAttach);
			}
			transaccionListNew = attachedTransaccionListNew;
			sesion.setTransaccionList(transaccionListNew);
			sesion = em.merge(sesion);
			if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
				idUsuarioOld.getSesionList().remove(sesion);
				idUsuarioOld = em.merge(idUsuarioOld);
			}
			if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
				idUsuarioNew.getSesionList().add(sesion);
				idUsuarioNew = em.merge(idUsuarioNew);
			}
			for (Entrega entregaListNewEntrega : entregaListNew) {
				if (!entregaListOld.contains(entregaListNewEntrega)) {
					Sesion oldIdSesionOfEntregaListNewEntrega = entregaListNewEntrega.getIdSesion();
					entregaListNewEntrega.setIdSesion(sesion);
					entregaListNewEntrega = em.merge(entregaListNewEntrega);
					if (oldIdSesionOfEntregaListNewEntrega != null
							&& !oldIdSesionOfEntregaListNewEntrega.equals(sesion)) {
						oldIdSesionOfEntregaListNewEntrega.getEntregaList().remove(entregaListNewEntrega);
						oldIdSesionOfEntregaListNewEntrega = em.merge(oldIdSesionOfEntregaListNewEntrega);
					}
				}
			}
			for (Reportez reportezListNewReportez : reportezListNew) {
				if (!reportezListOld.contains(reportezListNewReportez)) {
					Sesion oldIdSesionOfReportezListNewReportez = reportezListNewReportez.getIdSesion();
					reportezListNewReportez.setIdSesion(sesion);
					reportezListNewReportez = em.merge(reportezListNewReportez);
					if (oldIdSesionOfReportezListNewReportez != null
							&& !oldIdSesionOfReportezListNewReportez.equals(sesion)) {
						oldIdSesionOfReportezListNewReportez.getReportezList().remove(reportezListNewReportez);
						oldIdSesionOfReportezListNewReportez = em.merge(oldIdSesionOfReportezListNewReportez);
					}
				}
			}
			for (Transaccion transaccionListNewTransaccion : transaccionListNew) {
				if (!transaccionListOld.contains(transaccionListNewTransaccion)) {
					Sesion oldIdSesionOfTransaccionListNewTransaccion = transaccionListNewTransaccion.getIdSesion();
					transaccionListNewTransaccion.setIdSesion(sesion);
					transaccionListNewTransaccion = em.merge(transaccionListNewTransaccion);
					if (oldIdSesionOfTransaccionListNewTransaccion != null
							&& !oldIdSesionOfTransaccionListNewTransaccion.equals(sesion)) {
						oldIdSesionOfTransaccionListNewTransaccion.getTransaccionList().remove(
								transaccionListNewTransaccion);
						oldIdSesionOfTransaccionListNewTransaccion = em
								.merge(oldIdSesionOfTransaccionListNewTransaccion);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = sesion.getId();
				if (findSesion(id) == null) {
					throw new NonexistentEntityException("The sesion with id " + id + " no longer exists.");
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
			Sesion sesion;
			try {
				sesion = em.getReference(Sesion.class, id);
				sesion.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The sesion with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Entrega> entregaListOrphanCheck = sesion.getEntregaList();
			for (Entrega entregaListOrphanCheckEntrega : entregaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Sesion (" + sesion + ") cannot be destroyed since the Entrega "
						+ entregaListOrphanCheckEntrega
						+ " in its entregaList field has a non-nullable idSesion field.");
			}
			List<Reportez> reportezListOrphanCheck = sesion.getReportezList();
			for (Reportez reportezListOrphanCheckReportez : reportezListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Sesion (" + sesion + ") cannot be destroyed since the Reportez "
						+ reportezListOrphanCheckReportez
						+ " in its reportezList field has a non-nullable idSesion field.");
			}
			List<Transaccion> transaccionListOrphanCheck = sesion.getTransaccionList();
			for (Transaccion transaccionListOrphanCheckTransaccion : transaccionListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Sesion (" + sesion + ") cannot be destroyed since the Transaccion "
						+ transaccionListOrphanCheckTransaccion
						+ " in its transaccionList field has a non-nullable idSesion field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Usuario idUsuario = sesion.getIdUsuario();
			if (idUsuario != null) {
				idUsuario.getSesionList().remove(sesion);
				idUsuario = em.merge(idUsuario);
			}
			em.remove(sesion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findSesionEntities.
	 * 
	 * @return List<Sesion>
	 */
	public List<Sesion> findSesionEntities() {
		return findSesionEntities(true, -1, -1);
	}

	/**
	 * Method findSesionEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Sesion>
	 */
	public List<Sesion> findSesionEntities(int maxResults, int firstResult) {
		return findSesionEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findSesionEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Sesion>
	 */
	private List<Sesion> findSesionEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Sesion.class));
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
	 * Method findSesion.
	 * 
	 * @param id
	 *            Long
	 * @return Sesion
	 */
	public Sesion findSesion(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Sesion.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getSesionCount.
	 * 
	 * @return int
	 */
	public int getSesionCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Sesion> rt = cq.from(Sesion.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
