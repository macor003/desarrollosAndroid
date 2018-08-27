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
public class OperacionacreenciaJpaController implements Serializable {

	/**
	 * Constructor for OperacionacreenciaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public OperacionacreenciaJpaController(EntityManagerFactory emf) {
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
	 * @param operacionacreencia
	 *            Operacionacreencia
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Operacionacreencia operacionacreencia)
			throws PreexistingEntityException, Exception {
		if (operacionacreencia.getTipoacreenciaoperacionList() == null) {
			operacionacreencia
					.setTipoacreenciaoperacionList(new ArrayList<Tipoacreenciaoperacion>());
		}
		if (operacionacreencia.getAcreenciamovimientosaldoList() == null) {
			operacionacreencia
					.setAcreenciamovimientosaldoList(new ArrayList<Acreenciamovimientosaldo>());
		}
		if (operacionacreencia.getAcreenciamovimientoList() == null) {
			operacionacreencia
					.setAcreenciamovimientoList(new ArrayList<Acreenciamovimiento>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Tipoacreenciaoperacion> attachedTipoacreenciaoperacionList = new ArrayList<Tipoacreenciaoperacion>();
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListTipoacreenciaoperacionToAttach : operacionacreencia
					.getTipoacreenciaoperacionList()) {
				tipoacreenciaoperacionListTipoacreenciaoperacionToAttach = em
						.getReference(
								tipoacreenciaoperacionListTipoacreenciaoperacionToAttach
										.getClass(),
								tipoacreenciaoperacionListTipoacreenciaoperacionToAttach
										.getId());
				attachedTipoacreenciaoperacionList
						.add(tipoacreenciaoperacionListTipoacreenciaoperacionToAttach);
			}
			operacionacreencia
					.setTipoacreenciaoperacionList(attachedTipoacreenciaoperacionList);
			List<Acreenciamovimientosaldo> attachedAcreenciamovimientosaldoList = new ArrayList<Acreenciamovimientosaldo>();
			for (Acreenciamovimientosaldo acreenciamovimientosaldoListAcreenciamovimientosaldoToAttach : operacionacreencia
					.getAcreenciamovimientosaldoList()) {
				acreenciamovimientosaldoListAcreenciamovimientosaldoToAttach = em
						.getReference(
								acreenciamovimientosaldoListAcreenciamovimientosaldoToAttach
										.getClass(),
								acreenciamovimientosaldoListAcreenciamovimientosaldoToAttach
										.getId());
				attachedAcreenciamovimientosaldoList
						.add(acreenciamovimientosaldoListAcreenciamovimientosaldoToAttach);
			}
			operacionacreencia
					.setAcreenciamovimientosaldoList(attachedAcreenciamovimientosaldoList);
			List<Acreenciamovimiento> attachedAcreenciamovimientoList = new ArrayList<Acreenciamovimiento>();
			for (Acreenciamovimiento acreenciamovimientoListAcreenciamovimientoToAttach : operacionacreencia
					.getAcreenciamovimientoList()) {
				acreenciamovimientoListAcreenciamovimientoToAttach = em
						.getReference(
								acreenciamovimientoListAcreenciamovimientoToAttach
										.getClass(),
								acreenciamovimientoListAcreenciamovimientoToAttach
										.getIpaId());
				attachedAcreenciamovimientoList
						.add(acreenciamovimientoListAcreenciamovimientoToAttach);
			}
			operacionacreencia
					.setAcreenciamovimientoList(attachedAcreenciamovimientoList);
			em.persist(operacionacreencia);
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListTipoacreenciaoperacion : operacionacreencia
					.getTipoacreenciaoperacionList()) {
				Operacionacreencia oldIdOperacionacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion = tipoacreenciaoperacionListTipoacreenciaoperacion
						.getIdOperacionacreencia();
				tipoacreenciaoperacionListTipoacreenciaoperacion
						.setIdOperacionacreencia(operacionacreencia);
				tipoacreenciaoperacionListTipoacreenciaoperacion = em
						.merge(tipoacreenciaoperacionListTipoacreenciaoperacion);
				if (oldIdOperacionacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion != null) {
					oldIdOperacionacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion
							.getTipoacreenciaoperacionList()
							.remove(tipoacreenciaoperacionListTipoacreenciaoperacion);
					oldIdOperacionacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion = em
							.merge(oldIdOperacionacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion);
				}
			}
			for (Acreenciamovimientosaldo acreenciamovimientosaldoListAcreenciamovimientosaldo : operacionacreencia
					.getAcreenciamovimientosaldoList()) {
				Operacionacreencia oldIdOperacionacreenciaOfAcreenciamovimientosaldoListAcreenciamovimientosaldo = acreenciamovimientosaldoListAcreenciamovimientosaldo
						.getIdOperacionacreencia();
				acreenciamovimientosaldoListAcreenciamovimientosaldo
						.setIdOperacionacreencia(operacionacreencia);
				acreenciamovimientosaldoListAcreenciamovimientosaldo = em
						.merge(acreenciamovimientosaldoListAcreenciamovimientosaldo);
				if (oldIdOperacionacreenciaOfAcreenciamovimientosaldoListAcreenciamovimientosaldo != null) {
					oldIdOperacionacreenciaOfAcreenciamovimientosaldoListAcreenciamovimientosaldo
							.getAcreenciamovimientosaldoList()
							.remove(acreenciamovimientosaldoListAcreenciamovimientosaldo);
					oldIdOperacionacreenciaOfAcreenciamovimientosaldoListAcreenciamovimientosaldo = em
							.merge(oldIdOperacionacreenciaOfAcreenciamovimientosaldoListAcreenciamovimientosaldo);
				}
			}
			for (Acreenciamovimiento acreenciamovimientoListAcreenciamovimiento : operacionacreencia
					.getAcreenciamovimientoList()) {
				Operacionacreencia oldIdOperacionacreenciaOfAcreenciamovimientoListAcreenciamovimiento = acreenciamovimientoListAcreenciamovimiento
						.getIdOperacionacreencia();
				acreenciamovimientoListAcreenciamovimiento
						.setIdOperacionacreencia(operacionacreencia);
				acreenciamovimientoListAcreenciamovimiento = em
						.merge(acreenciamovimientoListAcreenciamovimiento);
				if (oldIdOperacionacreenciaOfAcreenciamovimientoListAcreenciamovimiento != null) {
					oldIdOperacionacreenciaOfAcreenciamovimientoListAcreenciamovimiento
							.getAcreenciamovimientoList().remove(
									acreenciamovimientoListAcreenciamovimiento);
					oldIdOperacionacreenciaOfAcreenciamovimientoListAcreenciamovimiento = em
							.merge(oldIdOperacionacreenciaOfAcreenciamovimientoListAcreenciamovimiento);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findOperacionacreencia(operacionacreencia.getId()) != null) {
				throw new PreexistingEntityException("Operacionacreencia "
						+ operacionacreencia + " already exists.", ex);
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
	 * @param operacionacreencia
	 *            Operacionacreencia
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Operacionacreencia operacionacreencia)
			throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Operacionacreencia persistentOperacionacreencia = em.find(
					Operacionacreencia.class, operacionacreencia.getId());
			List<Tipoacreenciaoperacion> tipoacreenciaoperacionListOld = persistentOperacionacreencia
					.getTipoacreenciaoperacionList();
			List<Tipoacreenciaoperacion> tipoacreenciaoperacionListNew = operacionacreencia
					.getTipoacreenciaoperacionList();
			List<Acreenciamovimientosaldo> acreenciamovimientosaldoListOld = persistentOperacionacreencia
					.getAcreenciamovimientosaldoList();
			List<Acreenciamovimientosaldo> acreenciamovimientosaldoListNew = operacionacreencia
					.getAcreenciamovimientosaldoList();
			List<Acreenciamovimiento> acreenciamovimientoListOld = persistentOperacionacreencia
					.getAcreenciamovimientoList();
			List<Acreenciamovimiento> acreenciamovimientoListNew = operacionacreencia
					.getAcreenciamovimientoList();
			List<String> illegalOrphanMessages = null;
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListOldTipoacreenciaoperacion : tipoacreenciaoperacionListOld) {
				if (!tipoacreenciaoperacionListNew
						.contains(tipoacreenciaoperacionListOldTipoacreenciaoperacion)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Tipoacreenciaoperacion "
									+ tipoacreenciaoperacionListOldTipoacreenciaoperacion
									+ " since its idOperacionacreencia field is not nullable.");
				}
			}
			for (Acreenciamovimientosaldo acreenciamovimientosaldoListOldAcreenciamovimientosaldo : acreenciamovimientosaldoListOld) {
				if (!acreenciamovimientosaldoListNew
						.contains(acreenciamovimientosaldoListOldAcreenciamovimientosaldo)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Acreenciamovimientosaldo "
									+ acreenciamovimientosaldoListOldAcreenciamovimientosaldo
									+ " since its idOperacionacreencia field is not nullable.");
				}
			}
			for (Acreenciamovimiento acreenciamovimientoListOldAcreenciamovimiento : acreenciamovimientoListOld) {
				if (!acreenciamovimientoListNew
						.contains(acreenciamovimientoListOldAcreenciamovimiento)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Acreenciamovimiento "
									+ acreenciamovimientoListOldAcreenciamovimiento
									+ " since its idOperacionacreencia field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Tipoacreenciaoperacion> attachedTipoacreenciaoperacionListNew = new ArrayList<Tipoacreenciaoperacion>();
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListNewTipoacreenciaoperacionToAttach : tipoacreenciaoperacionListNew) {
				tipoacreenciaoperacionListNewTipoacreenciaoperacionToAttach = em
						.getReference(
								tipoacreenciaoperacionListNewTipoacreenciaoperacionToAttach
										.getClass(),
								tipoacreenciaoperacionListNewTipoacreenciaoperacionToAttach
										.getId());
				attachedTipoacreenciaoperacionListNew
						.add(tipoacreenciaoperacionListNewTipoacreenciaoperacionToAttach);
			}
			tipoacreenciaoperacionListNew = attachedTipoacreenciaoperacionListNew;
			operacionacreencia
					.setTipoacreenciaoperacionList(tipoacreenciaoperacionListNew);
			List<Acreenciamovimientosaldo> attachedAcreenciamovimientosaldoListNew = new ArrayList<Acreenciamovimientosaldo>();
			for (Acreenciamovimientosaldo acreenciamovimientosaldoListNewAcreenciamovimientosaldoToAttach : acreenciamovimientosaldoListNew) {
				acreenciamovimientosaldoListNewAcreenciamovimientosaldoToAttach = em
						.getReference(
								acreenciamovimientosaldoListNewAcreenciamovimientosaldoToAttach
										.getClass(),
								acreenciamovimientosaldoListNewAcreenciamovimientosaldoToAttach
										.getId());
				attachedAcreenciamovimientosaldoListNew
						.add(acreenciamovimientosaldoListNewAcreenciamovimientosaldoToAttach);
			}
			acreenciamovimientosaldoListNew = attachedAcreenciamovimientosaldoListNew;
			operacionacreencia
					.setAcreenciamovimientosaldoList(acreenciamovimientosaldoListNew);
			List<Acreenciamovimiento> attachedAcreenciamovimientoListNew = new ArrayList<Acreenciamovimiento>();
			for (Acreenciamovimiento acreenciamovimientoListNewAcreenciamovimientoToAttach : acreenciamovimientoListNew) {
				acreenciamovimientoListNewAcreenciamovimientoToAttach = em
						.getReference(
								acreenciamovimientoListNewAcreenciamovimientoToAttach
										.getClass(),
								acreenciamovimientoListNewAcreenciamovimientoToAttach
										.getIpaId());
				attachedAcreenciamovimientoListNew
						.add(acreenciamovimientoListNewAcreenciamovimientoToAttach);
			}
			acreenciamovimientoListNew = attachedAcreenciamovimientoListNew;
			operacionacreencia
					.setAcreenciamovimientoList(acreenciamovimientoListNew);
			operacionacreencia = em.merge(operacionacreencia);
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListNewTipoacreenciaoperacion : tipoacreenciaoperacionListNew) {
				if (!tipoacreenciaoperacionListOld
						.contains(tipoacreenciaoperacionListNewTipoacreenciaoperacion)) {
					Operacionacreencia oldIdOperacionacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion = tipoacreenciaoperacionListNewTipoacreenciaoperacion
							.getIdOperacionacreencia();
					tipoacreenciaoperacionListNewTipoacreenciaoperacion
							.setIdOperacionacreencia(operacionacreencia);
					tipoacreenciaoperacionListNewTipoacreenciaoperacion = em
							.merge(tipoacreenciaoperacionListNewTipoacreenciaoperacion);
					if (oldIdOperacionacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion != null
							&& !oldIdOperacionacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion
									.equals(operacionacreencia)) {
						oldIdOperacionacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion
								.getTipoacreenciaoperacionList()
								.remove(tipoacreenciaoperacionListNewTipoacreenciaoperacion);
						oldIdOperacionacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion = em
								.merge(oldIdOperacionacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion);
					}
				}
			}
			for (Acreenciamovimientosaldo acreenciamovimientosaldoListNewAcreenciamovimientosaldo : acreenciamovimientosaldoListNew) {
				if (!acreenciamovimientosaldoListOld
						.contains(acreenciamovimientosaldoListNewAcreenciamovimientosaldo)) {
					Operacionacreencia oldIdOperacionacreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo = acreenciamovimientosaldoListNewAcreenciamovimientosaldo
							.getIdOperacionacreencia();
					acreenciamovimientosaldoListNewAcreenciamovimientosaldo
							.setIdOperacionacreencia(operacionacreencia);
					acreenciamovimientosaldoListNewAcreenciamovimientosaldo = em
							.merge(acreenciamovimientosaldoListNewAcreenciamovimientosaldo);
					if (oldIdOperacionacreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo != null
							&& !oldIdOperacionacreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo
									.equals(operacionacreencia)) {
						oldIdOperacionacreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo
								.getAcreenciamovimientosaldoList()
								.remove(acreenciamovimientosaldoListNewAcreenciamovimientosaldo);
						oldIdOperacionacreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo = em
								.merge(oldIdOperacionacreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo);
					}
				}
			}
			for (Acreenciamovimiento acreenciamovimientoListNewAcreenciamovimiento : acreenciamovimientoListNew) {
				if (!acreenciamovimientoListOld
						.contains(acreenciamovimientoListNewAcreenciamovimiento)) {
					Operacionacreencia oldIdOperacionacreenciaOfAcreenciamovimientoListNewAcreenciamovimiento = acreenciamovimientoListNewAcreenciamovimiento
							.getIdOperacionacreencia();
					acreenciamovimientoListNewAcreenciamovimiento
							.setIdOperacionacreencia(operacionacreencia);
					acreenciamovimientoListNewAcreenciamovimiento = em
							.merge(acreenciamovimientoListNewAcreenciamovimiento);
					if (oldIdOperacionacreenciaOfAcreenciamovimientoListNewAcreenciamovimiento != null
							&& !oldIdOperacionacreenciaOfAcreenciamovimientoListNewAcreenciamovimiento
									.equals(operacionacreencia)) {
						oldIdOperacionacreenciaOfAcreenciamovimientoListNewAcreenciamovimiento
								.getAcreenciamovimientoList()
								.remove(acreenciamovimientoListNewAcreenciamovimiento);
						oldIdOperacionacreenciaOfAcreenciamovimientoListNewAcreenciamovimiento = em
								.merge(oldIdOperacionacreenciaOfAcreenciamovimientoListNewAcreenciamovimiento);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = operacionacreencia.getId();
				if (findOperacionacreencia(id) == null) {
					throw new NonexistentEntityException(
							"The operacionacreencia with id " + id
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
			Operacionacreencia operacionacreencia;
			try {
				operacionacreencia = em.getReference(Operacionacreencia.class,
						id);
				operacionacreencia.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The operacionacreencia with id " + id
								+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Tipoacreenciaoperacion> tipoacreenciaoperacionListOrphanCheck = operacionacreencia
					.getTipoacreenciaoperacionList();
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListOrphanCheckTipoacreenciaoperacion : tipoacreenciaoperacionListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Operacionacreencia ("
								+ operacionacreencia
								+ ") cannot be destroyed since the Tipoacreenciaoperacion "
								+ tipoacreenciaoperacionListOrphanCheckTipoacreenciaoperacion
								+ " in its tipoacreenciaoperacionList field has a non-nullable idOperacionacreencia field.");
			}
			List<Acreenciamovimientosaldo> acreenciamovimientosaldoListOrphanCheck = operacionacreencia
					.getAcreenciamovimientosaldoList();
			for (Acreenciamovimientosaldo acreenciamovimientosaldoListOrphanCheckAcreenciamovimientosaldo : acreenciamovimientosaldoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Operacionacreencia ("
								+ operacionacreencia
								+ ") cannot be destroyed since the Acreenciamovimientosaldo "
								+ acreenciamovimientosaldoListOrphanCheckAcreenciamovimientosaldo
								+ " in its acreenciamovimientosaldoList field has a non-nullable idOperacionacreencia field.");
			}
			List<Acreenciamovimiento> acreenciamovimientoListOrphanCheck = operacionacreencia
					.getAcreenciamovimientoList();
			for (Acreenciamovimiento acreenciamovimientoListOrphanCheckAcreenciamovimiento : acreenciamovimientoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Operacionacreencia ("
								+ operacionacreencia
								+ ") cannot be destroyed since the Acreenciamovimiento "
								+ acreenciamovimientoListOrphanCheckAcreenciamovimiento
								+ " in its acreenciamovimientoList field has a non-nullable idOperacionacreencia field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(operacionacreencia);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findOperacionacreenciaEntities.
	 * 
	 * @return List<Operacionacreencia>
	 */
	public List<Operacionacreencia> findOperacionacreenciaEntities() {
		return findOperacionacreenciaEntities(true, -1, -1);
	}

	/**
	 * Method findOperacionacreenciaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Operacionacreencia>
	 */
	public List<Operacionacreencia> findOperacionacreenciaEntities(
			int maxResults, int firstResult) {
		return findOperacionacreenciaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findOperacionacreenciaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Operacionacreencia>
	 */
	private List<Operacionacreencia> findOperacionacreenciaEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Operacionacreencia.class));
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
	 * Method findOperacionacreencia.
	 * 
	 * @param id
	 *            Long
	 * @return Operacionacreencia
	 */
	public Operacionacreencia findOperacionacreencia(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Operacionacreencia.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getOperacionacreenciaCount.
	 * 
	 * @return int
	 */
	public int getOperacionacreenciaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Operacionacreencia> rt = cq.from(Operacionacreencia.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
