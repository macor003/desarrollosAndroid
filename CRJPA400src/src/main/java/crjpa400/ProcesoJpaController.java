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
public class ProcesoJpaController implements Serializable {

	/**
	 * Constructor for ProcesoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ProcesoJpaController(EntityManagerFactory emf) {
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
	 * @param proceso
	 *            Proceso
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Proceso proceso) throws PreexistingEntityException,
			Exception {
		if (proceso.getProcesopropertyList() == null) {
			proceso.setProcesopropertyList(new ArrayList<Procesoproperty>());
		}
		if (proceso.getPerfilprocesoList() == null) {
			proceso.setPerfilprocesoList(new ArrayList<Perfilproceso>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Procesoproperty> attachedProcesopropertyList = new ArrayList<Procesoproperty>();
			for (Procesoproperty procesopropertyListProcesopropertyToAttach : proceso
					.getProcesopropertyList()) {
				procesopropertyListProcesopropertyToAttach = em.getReference(
						procesopropertyListProcesopropertyToAttach.getClass(),
						procesopropertyListProcesopropertyToAttach.getId());
				attachedProcesopropertyList
						.add(procesopropertyListProcesopropertyToAttach);
			}
			proceso.setProcesopropertyList(attachedProcesopropertyList);
			List<Perfilproceso> attachedPerfilprocesoList = new ArrayList<Perfilproceso>();
			for (Perfilproceso perfilprocesoListPerfilprocesoToAttach : proceso
					.getPerfilprocesoList()) {
				perfilprocesoListPerfilprocesoToAttach = em.getReference(
						perfilprocesoListPerfilprocesoToAttach.getClass(),
						perfilprocesoListPerfilprocesoToAttach.getId());
				attachedPerfilprocesoList
						.add(perfilprocesoListPerfilprocesoToAttach);
			}
			proceso.setPerfilprocesoList(attachedPerfilprocesoList);
			em.persist(proceso);
			for (Procesoproperty procesopropertyListProcesoproperty : proceso
					.getProcesopropertyList()) {
				Proceso oldIdProcesoOfProcesopropertyListProcesoproperty = procesopropertyListProcesoproperty
						.getIdProceso();
				procesopropertyListProcesoproperty.setIdProceso(proceso);
				procesopropertyListProcesoproperty = em
						.merge(procesopropertyListProcesoproperty);
				if (oldIdProcesoOfProcesopropertyListProcesoproperty != null) {
					oldIdProcesoOfProcesopropertyListProcesoproperty
							.getProcesopropertyList().remove(
									procesopropertyListProcesoproperty);
					oldIdProcesoOfProcesopropertyListProcesoproperty = em
							.merge(oldIdProcesoOfProcesopropertyListProcesoproperty);
				}
			}
			for (Perfilproceso perfilprocesoListPerfilproceso : proceso
					.getPerfilprocesoList()) {
				Proceso oldIdProcesoOfPerfilprocesoListPerfilproceso = perfilprocesoListPerfilproceso
						.getIdProceso();
				perfilprocesoListPerfilproceso.setIdProceso(proceso);
				perfilprocesoListPerfilproceso = em
						.merge(perfilprocesoListPerfilproceso);
				if (oldIdProcesoOfPerfilprocesoListPerfilproceso != null) {
					oldIdProcesoOfPerfilprocesoListPerfilproceso
							.getPerfilprocesoList().remove(
									perfilprocesoListPerfilproceso);
					oldIdProcesoOfPerfilprocesoListPerfilproceso = em
							.merge(oldIdProcesoOfPerfilprocesoListPerfilproceso);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findProceso(proceso.getId()) != null) {
				throw new PreexistingEntityException("Proceso " + proceso
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
	 * @param proceso
	 *            Proceso
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Proceso proceso) throws IllegalOrphanException,
			NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Proceso persistentProceso = em.find(Proceso.class, proceso.getId());
			List<Procesoproperty> procesopropertyListOld = persistentProceso
					.getProcesopropertyList();
			List<Procesoproperty> procesopropertyListNew = proceso
					.getProcesopropertyList();
			List<Perfilproceso> perfilprocesoListOld = persistentProceso
					.getPerfilprocesoList();
			List<Perfilproceso> perfilprocesoListNew = proceso
					.getPerfilprocesoList();
			List<String> illegalOrphanMessages = null;
			for (Procesoproperty procesopropertyListOldProcesoproperty : procesopropertyListOld) {
				if (!procesopropertyListNew
						.contains(procesopropertyListOldProcesoproperty)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Procesoproperty "
									+ procesopropertyListOldProcesoproperty
									+ " since its idProceso field is not nullable.");
				}
			}
			for (Perfilproceso perfilprocesoListOldPerfilproceso : perfilprocesoListOld) {
				if (!perfilprocesoListNew
						.contains(perfilprocesoListOldPerfilproceso)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Perfilproceso "
							+ perfilprocesoListOldPerfilproceso
							+ " since its idProceso field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Procesoproperty> attachedProcesopropertyListNew = new ArrayList<Procesoproperty>();
			for (Procesoproperty procesopropertyListNewProcesopropertyToAttach : procesopropertyListNew) {
				procesopropertyListNewProcesopropertyToAttach = em
						.getReference(
								procesopropertyListNewProcesopropertyToAttach
										.getClass(),
								procesopropertyListNewProcesopropertyToAttach
										.getId());
				attachedProcesopropertyListNew
						.add(procesopropertyListNewProcesopropertyToAttach);
			}
			procesopropertyListNew = attachedProcesopropertyListNew;
			proceso.setProcesopropertyList(procesopropertyListNew);
			List<Perfilproceso> attachedPerfilprocesoListNew = new ArrayList<Perfilproceso>();
			for (Perfilproceso perfilprocesoListNewPerfilprocesoToAttach : perfilprocesoListNew) {
				perfilprocesoListNewPerfilprocesoToAttach = em.getReference(
						perfilprocesoListNewPerfilprocesoToAttach.getClass(),
						perfilprocesoListNewPerfilprocesoToAttach.getId());
				attachedPerfilprocesoListNew
						.add(perfilprocesoListNewPerfilprocesoToAttach);
			}
			perfilprocesoListNew = attachedPerfilprocesoListNew;
			proceso.setPerfilprocesoList(perfilprocesoListNew);
			proceso = em.merge(proceso);
			for (Procesoproperty procesopropertyListNewProcesoproperty : procesopropertyListNew) {
				if (!procesopropertyListOld
						.contains(procesopropertyListNewProcesoproperty)) {
					Proceso oldIdProcesoOfProcesopropertyListNewProcesoproperty = procesopropertyListNewProcesoproperty
							.getIdProceso();
					procesopropertyListNewProcesoproperty.setIdProceso(proceso);
					procesopropertyListNewProcesoproperty = em
							.merge(procesopropertyListNewProcesoproperty);
					if (oldIdProcesoOfProcesopropertyListNewProcesoproperty != null
							&& !oldIdProcesoOfProcesopropertyListNewProcesoproperty
									.equals(proceso)) {
						oldIdProcesoOfProcesopropertyListNewProcesoproperty
								.getProcesopropertyList().remove(
										procesopropertyListNewProcesoproperty);
						oldIdProcesoOfProcesopropertyListNewProcesoproperty = em
								.merge(oldIdProcesoOfProcesopropertyListNewProcesoproperty);
					}
				}
			}
			for (Perfilproceso perfilprocesoListNewPerfilproceso : perfilprocesoListNew) {
				if (!perfilprocesoListOld
						.contains(perfilprocesoListNewPerfilproceso)) {
					Proceso oldIdProcesoOfPerfilprocesoListNewPerfilproceso = perfilprocesoListNewPerfilproceso
							.getIdProceso();
					perfilprocesoListNewPerfilproceso.setIdProceso(proceso);
					perfilprocesoListNewPerfilproceso = em
							.merge(perfilprocesoListNewPerfilproceso);
					if (oldIdProcesoOfPerfilprocesoListNewPerfilproceso != null
							&& !oldIdProcesoOfPerfilprocesoListNewPerfilproceso
									.equals(proceso)) {
						oldIdProcesoOfPerfilprocesoListNewPerfilproceso
								.getPerfilprocesoList().remove(
										perfilprocesoListNewPerfilproceso);
						oldIdProcesoOfPerfilprocesoListNewPerfilproceso = em
								.merge(oldIdProcesoOfPerfilprocesoListNewPerfilproceso);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = proceso.getId();
				if (findProceso(id) == null) {
					throw new NonexistentEntityException("The proceso with id "
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
			Proceso proceso;
			try {
				proceso = em.getReference(Proceso.class, id);
				proceso.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The proceso with id "
						+ id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Procesoproperty> procesopropertyListOrphanCheck = proceso
					.getProcesopropertyList();
			for (Procesoproperty procesopropertyListOrphanCheckProcesoproperty : procesopropertyListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Proceso ("
								+ proceso
								+ ") cannot be destroyed since the Procesoproperty "
								+ procesopropertyListOrphanCheckProcesoproperty
								+ " in its procesopropertyList field has a non-nullable idProceso field.");
			}
			List<Perfilproceso> perfilprocesoListOrphanCheck = proceso
					.getPerfilprocesoList();
			for (Perfilproceso perfilprocesoListOrphanCheckPerfilproceso : perfilprocesoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Proceso ("
								+ proceso
								+ ") cannot be destroyed since the Perfilproceso "
								+ perfilprocesoListOrphanCheckPerfilproceso
								+ " in its perfilprocesoList field has a non-nullable idProceso field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(proceso);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findProcesoEntities.
	 * 
	 * @return List<Proceso>
	 */
	public List<Proceso> findProcesoEntities() {
		return findProcesoEntities(true, -1, -1);
	}

	/**
	 * Method findProcesoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Proceso>
	 */
	public List<Proceso> findProcesoEntities(int maxResults, int firstResult) {
		return findProcesoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findProcesoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Proceso>
	 */
	private List<Proceso> findProcesoEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Proceso.class));
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
	 * Method findProceso.
	 * 
	 * @param id
	 *            Long
	 * @return Proceso
	 */
	public Proceso findProceso(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Proceso.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getProcesoCount.
	 * 
	 * @return int
	 */
	public int getProcesoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Proceso> rt = cq.from(Proceso.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
