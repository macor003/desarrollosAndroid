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
public class AcreenciamovimientosaldoJpaController implements Serializable {

	/**
	 * Constructor for AcreenciamovimientosaldoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public AcreenciamovimientosaldoJpaController(EntityManagerFactory emf) {
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
	 * @param acreenciamovimientosaldo
	 *            Acreenciamovimientosaldo
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Acreenciamovimientosaldo acreenciamovimientosaldo)
			throws PreexistingEntityException, Exception {
		if (acreenciamovimientosaldo.getAcreenciamovimientoformadepagoList() == null) {
			acreenciamovimientosaldo
					.setAcreenciamovimientoformadepagoList(new ArrayList<Acreenciamovimientoformadepago>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipoacreencia idTipoacreencia = acreenciamovimientosaldo
					.getIdTipoacreencia();
			if (idTipoacreencia != null) {
				idTipoacreencia = em.getReference(idTipoacreencia.getClass(),
						idTipoacreencia.getId());
				acreenciamovimientosaldo.setIdTipoacreencia(idTipoacreencia);
			}
			Operacionacreencia idOperacionacreencia = acreenciamovimientosaldo
					.getIdOperacionacreencia();
			if (idOperacionacreencia != null) {
				idOperacionacreencia = em.getReference(
						idOperacionacreencia.getClass(),
						idOperacionacreencia.getId());
				acreenciamovimientosaldo
						.setIdOperacionacreencia(idOperacionacreencia);
			}
			Acreencia idAcreencia = acreenciamovimientosaldo.getIdAcreencia();
			if (idAcreencia != null) {
				idAcreencia = em.getReference(idAcreencia.getClass(),
						idAcreencia.getId());
				acreenciamovimientosaldo.setIdAcreencia(idAcreencia);
			}
			List<Acreenciamovimientoformadepago> attachedAcreenciamovimientoformadepagoList = new ArrayList<Acreenciamovimientoformadepago>();
			for (Acreenciamovimientoformadepago acreenciamovimientoformadepagoListAcreenciamovimientoformadepagoToAttach : acreenciamovimientosaldo
					.getAcreenciamovimientoformadepagoList()) {
				acreenciamovimientoformadepagoListAcreenciamovimientoformadepagoToAttach = em
						.getReference(
								acreenciamovimientoformadepagoListAcreenciamovimientoformadepagoToAttach
										.getClass(),
								acreenciamovimientoformadepagoListAcreenciamovimientoformadepagoToAttach
										.getId());
				attachedAcreenciamovimientoformadepagoList
						.add(acreenciamovimientoformadepagoListAcreenciamovimientoformadepagoToAttach);
			}
			acreenciamovimientosaldo
					.setAcreenciamovimientoformadepagoList(attachedAcreenciamovimientoformadepagoList);
			em.persist(acreenciamovimientosaldo);
			if (idTipoacreencia != null) {
				idTipoacreencia.getAcreenciamovimientosaldoList().add(
						acreenciamovimientosaldo);
				idTipoacreencia = em.merge(idTipoacreencia);
			}
			if (idOperacionacreencia != null) {
				idOperacionacreencia.getAcreenciamovimientosaldoList().add(
						acreenciamovimientosaldo);
				idOperacionacreencia = em.merge(idOperacionacreencia);
			}
			if (idAcreencia != null) {
				idAcreencia.getAcreenciamovimientosaldoList().add(
						acreenciamovimientosaldo);
				idAcreencia = em.merge(idAcreencia);
			}
			for (Acreenciamovimientoformadepago acreenciamovimientoformadepagoListAcreenciamovimientoformadepago : acreenciamovimientosaldo
					.getAcreenciamovimientoformadepagoList()) {
				Acreenciamovimientosaldo oldIdAcreenciamovimientosaldoOfAcreenciamovimientoformadepagoListAcreenciamovimientoformadepago = acreenciamovimientoformadepagoListAcreenciamovimientoformadepago
						.getIdAcreenciamovimientosaldo();
				acreenciamovimientoformadepagoListAcreenciamovimientoformadepago
						.setIdAcreenciamovimientosaldo(acreenciamovimientosaldo);
				acreenciamovimientoformadepagoListAcreenciamovimientoformadepago = em
						.merge(acreenciamovimientoformadepagoListAcreenciamovimientoformadepago);
				if (oldIdAcreenciamovimientosaldoOfAcreenciamovimientoformadepagoListAcreenciamovimientoformadepago != null) {
					oldIdAcreenciamovimientosaldoOfAcreenciamovimientoformadepagoListAcreenciamovimientoformadepago
							.getAcreenciamovimientoformadepagoList()
							.remove(acreenciamovimientoformadepagoListAcreenciamovimientoformadepago);
					oldIdAcreenciamovimientosaldoOfAcreenciamovimientoformadepagoListAcreenciamovimientoformadepago = em
							.merge(oldIdAcreenciamovimientosaldoOfAcreenciamovimientoformadepagoListAcreenciamovimientoformadepago);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findAcreenciamovimientosaldo(acreenciamovimientosaldo.getId()) != null) {
				throw new PreexistingEntityException(
						"Acreenciamovimientosaldo " + acreenciamovimientosaldo
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
	 * @param acreenciamovimientosaldo
	 *            Acreenciamovimientosaldo
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Acreenciamovimientosaldo acreenciamovimientosaldo)
			throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Acreenciamovimientosaldo persistentAcreenciamovimientosaldo = em
					.find(Acreenciamovimientosaldo.class,
							acreenciamovimientosaldo.getId());
			Tipoacreencia idTipoacreenciaOld = persistentAcreenciamovimientosaldo
					.getIdTipoacreencia();
			Tipoacreencia idTipoacreenciaNew = acreenciamovimientosaldo
					.getIdTipoacreencia();
			Operacionacreencia idOperacionacreenciaOld = persistentAcreenciamovimientosaldo
					.getIdOperacionacreencia();
			Operacionacreencia idOperacionacreenciaNew = acreenciamovimientosaldo
					.getIdOperacionacreencia();
			Acreencia idAcreenciaOld = persistentAcreenciamovimientosaldo
					.getIdAcreencia();
			Acreencia idAcreenciaNew = acreenciamovimientosaldo
					.getIdAcreencia();
			List<Acreenciamovimientoformadepago> acreenciamovimientoformadepagoListOld = persistentAcreenciamovimientosaldo
					.getAcreenciamovimientoformadepagoList();
			List<Acreenciamovimientoformadepago> acreenciamovimientoformadepagoListNew = acreenciamovimientosaldo
					.getAcreenciamovimientoformadepagoList();
			List<String> illegalOrphanMessages = null;
			for (Acreenciamovimientoformadepago acreenciamovimientoformadepagoListOldAcreenciamovimientoformadepago : acreenciamovimientoformadepagoListOld) {
				if (!acreenciamovimientoformadepagoListNew
						.contains(acreenciamovimientoformadepagoListOldAcreenciamovimientoformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Acreenciamovimientoformadepago "
									+ acreenciamovimientoformadepagoListOldAcreenciamovimientoformadepago
									+ " since its idAcreenciamovimientosaldo field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idTipoacreenciaNew != null) {
				idTipoacreenciaNew = em.getReference(
						idTipoacreenciaNew.getClass(),
						idTipoacreenciaNew.getId());
				acreenciamovimientosaldo.setIdTipoacreencia(idTipoacreenciaNew);
			}
			if (idOperacionacreenciaNew != null) {
				idOperacionacreenciaNew = em.getReference(
						idOperacionacreenciaNew.getClass(),
						idOperacionacreenciaNew.getId());
				acreenciamovimientosaldo
						.setIdOperacionacreencia(idOperacionacreenciaNew);
			}
			if (idAcreenciaNew != null) {
				idAcreenciaNew = em.getReference(idAcreenciaNew.getClass(),
						idAcreenciaNew.getId());
				acreenciamovimientosaldo.setIdAcreencia(idAcreenciaNew);
			}
			List<Acreenciamovimientoformadepago> attachedAcreenciamovimientoformadepagoListNew = new ArrayList<Acreenciamovimientoformadepago>();
			for (Acreenciamovimientoformadepago acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepagoToAttach : acreenciamovimientoformadepagoListNew) {
				acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepagoToAttach = em
						.getReference(
								acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepagoToAttach
										.getClass(),
								acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepagoToAttach
										.getId());
				attachedAcreenciamovimientoformadepagoListNew
						.add(acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepagoToAttach);
			}
			acreenciamovimientoformadepagoListNew = attachedAcreenciamovimientoformadepagoListNew;
			acreenciamovimientosaldo
					.setAcreenciamovimientoformadepagoList(acreenciamovimientoformadepagoListNew);
			acreenciamovimientosaldo = em.merge(acreenciamovimientosaldo);
			if (idTipoacreenciaOld != null
					&& !idTipoacreenciaOld.equals(idTipoacreenciaNew)) {
				idTipoacreenciaOld.getAcreenciamovimientosaldoList().remove(
						acreenciamovimientosaldo);
				idTipoacreenciaOld = em.merge(idTipoacreenciaOld);
			}
			if (idTipoacreenciaNew != null
					&& !idTipoacreenciaNew.equals(idTipoacreenciaOld)) {
				idTipoacreenciaNew.getAcreenciamovimientosaldoList().add(
						acreenciamovimientosaldo);
				idTipoacreenciaNew = em.merge(idTipoacreenciaNew);
			}
			if (idOperacionacreenciaOld != null
					&& !idOperacionacreenciaOld.equals(idOperacionacreenciaNew)) {
				idOperacionacreenciaOld.getAcreenciamovimientosaldoList()
						.remove(acreenciamovimientosaldo);
				idOperacionacreenciaOld = em.merge(idOperacionacreenciaOld);
			}
			if (idOperacionacreenciaNew != null
					&& !idOperacionacreenciaNew.equals(idOperacionacreenciaOld)) {
				idOperacionacreenciaNew.getAcreenciamovimientosaldoList().add(
						acreenciamovimientosaldo);
				idOperacionacreenciaNew = em.merge(idOperacionacreenciaNew);
			}
			if (idAcreenciaOld != null
					&& !idAcreenciaOld.equals(idAcreenciaNew)) {
				idAcreenciaOld.getAcreenciamovimientosaldoList().remove(
						acreenciamovimientosaldo);
				idAcreenciaOld = em.merge(idAcreenciaOld);
			}
			if (idAcreenciaNew != null
					&& !idAcreenciaNew.equals(idAcreenciaOld)) {
				idAcreenciaNew.getAcreenciamovimientosaldoList().add(
						acreenciamovimientosaldo);
				idAcreenciaNew = em.merge(idAcreenciaNew);
			}
			for (Acreenciamovimientoformadepago acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago : acreenciamovimientoformadepagoListNew) {
				if (!acreenciamovimientoformadepagoListOld
						.contains(acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago)) {
					Acreenciamovimientosaldo oldIdAcreenciamovimientosaldoOfAcreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago = acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago
							.getIdAcreenciamovimientosaldo();
					acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago
							.setIdAcreenciamovimientosaldo(acreenciamovimientosaldo);
					acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago = em
							.merge(acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago);
					if (oldIdAcreenciamovimientosaldoOfAcreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago != null
							&& !oldIdAcreenciamovimientosaldoOfAcreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago
									.equals(acreenciamovimientosaldo)) {
						oldIdAcreenciamovimientosaldoOfAcreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago
								.getAcreenciamovimientoformadepagoList()
								.remove(acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago);
						oldIdAcreenciamovimientosaldoOfAcreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago = em
								.merge(oldIdAcreenciamovimientosaldoOfAcreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = acreenciamovimientosaldo.getId();
				if (findAcreenciamovimientosaldo(id) == null) {
					throw new NonexistentEntityException(
							"The acreenciamovimientosaldo with id " + id
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
			Acreenciamovimientosaldo acreenciamovimientosaldo;
			try {
				acreenciamovimientosaldo = em.getReference(
						Acreenciamovimientosaldo.class, id);
				acreenciamovimientosaldo.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The acreenciamovimientosaldo with id " + id
								+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Acreenciamovimientoformadepago> acreenciamovimientoformadepagoListOrphanCheck = acreenciamovimientosaldo
					.getAcreenciamovimientoformadepagoList();
			for (Acreenciamovimientoformadepago acreenciamovimientoformadepagoListOrphanCheckAcreenciamovimientoformadepago : acreenciamovimientoformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Acreenciamovimientosaldo ("
								+ acreenciamovimientosaldo
								+ ") cannot be destroyed since the Acreenciamovimientoformadepago "
								+ acreenciamovimientoformadepagoListOrphanCheckAcreenciamovimientoformadepago
								+ " in its acreenciamovimientoformadepagoList field has a non-nullable idAcreenciamovimientosaldo field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Tipoacreencia idTipoacreencia = acreenciamovimientosaldo
					.getIdTipoacreencia();
			if (idTipoacreencia != null) {
				idTipoacreencia.getAcreenciamovimientosaldoList().remove(
						acreenciamovimientosaldo);
				idTipoacreencia = em.merge(idTipoacreencia);
			}
			Operacionacreencia idOperacionacreencia = acreenciamovimientosaldo
					.getIdOperacionacreencia();
			if (idOperacionacreencia != null) {
				idOperacionacreencia.getAcreenciamovimientosaldoList().remove(
						acreenciamovimientosaldo);
				idOperacionacreencia = em.merge(idOperacionacreencia);
			}
			Acreencia idAcreencia = acreenciamovimientosaldo.getIdAcreencia();
			if (idAcreencia != null) {
				idAcreencia.getAcreenciamovimientosaldoList().remove(
						acreenciamovimientosaldo);
				idAcreencia = em.merge(idAcreencia);
			}
			em.remove(acreenciamovimientosaldo);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findAcreenciamovimientosaldoEntities.
	 * 
	 * @return List<Acreenciamovimientosaldo>
	 */
	public List<Acreenciamovimientosaldo> findAcreenciamovimientosaldoEntities() {
		return findAcreenciamovimientosaldoEntities(true, -1, -1);
	}

	/**
	 * Method findAcreenciamovimientosaldoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Acreenciamovimientosaldo>
	 */
	public List<Acreenciamovimientosaldo> findAcreenciamovimientosaldoEntities(
			int maxResults, int firstResult) {
		return findAcreenciamovimientosaldoEntities(false, maxResults,
				firstResult);
	}

	/**
	 * Method findAcreenciamovimientosaldoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Acreenciamovimientosaldo>
	 */
	private List<Acreenciamovimientosaldo> findAcreenciamovimientosaldoEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Acreenciamovimientosaldo.class));
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
	 * Method findAcreenciamovimientosaldo.
	 * 
	 * @param id
	 *            Long
	 * @return Acreenciamovimientosaldo
	 */
	public Acreenciamovimientosaldo findAcreenciamovimientosaldo(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Acreenciamovimientosaldo.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getAcreenciamovimientosaldoCount.
	 * 
	 * @return int
	 */
	public int getAcreenciamovimientosaldoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Acreenciamovimientosaldo> rt = cq
					.from(Acreenciamovimientosaldo.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
