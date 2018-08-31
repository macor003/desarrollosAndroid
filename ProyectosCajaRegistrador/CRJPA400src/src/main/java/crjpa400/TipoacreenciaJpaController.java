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
public class TipoacreenciaJpaController implements Serializable {

	/**
	 * Constructor for TipoacreenciaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TipoacreenciaJpaController(EntityManagerFactory emf) {
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
	 * @param tipoacreencia
	 *            Tipoacreencia
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Tipoacreencia tipoacreencia)
			throws PreexistingEntityException, Exception {
		if (tipoacreencia.getTipoacreenciaoperacionList() == null) {
			tipoacreencia
					.setTipoacreenciaoperacionList(new ArrayList<Tipoacreenciaoperacion>());
		}
		if (tipoacreencia.getAcreenciamovimientosaldoList() == null) {
			tipoacreencia
					.setAcreenciamovimientosaldoList(new ArrayList<Acreenciamovimientosaldo>());
		}
		if (tipoacreencia.getAcreenciamovimientoList() == null) {
			tipoacreencia
					.setAcreenciamovimientoList(new ArrayList<Acreenciamovimiento>());
		}
		if (tipoacreencia.getSaldoacreenciaList() == null) {
			tipoacreencia
					.setSaldoacreenciaList(new ArrayList<Saldoacreencia>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Tipoacreenciaoperacion> attachedTipoacreenciaoperacionList = new ArrayList<Tipoacreenciaoperacion>();
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListTipoacreenciaoperacionToAttach : tipoacreencia
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
			tipoacreencia
					.setTipoacreenciaoperacionList(attachedTipoacreenciaoperacionList);
			List<Acreenciamovimientosaldo> attachedAcreenciamovimientosaldoList = new ArrayList<Acreenciamovimientosaldo>();
			for (Acreenciamovimientosaldo acreenciamovimientosaldoListAcreenciamovimientosaldoToAttach : tipoacreencia
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
			tipoacreencia
					.setAcreenciamovimientosaldoList(attachedAcreenciamovimientosaldoList);
			List<Acreenciamovimiento> attachedAcreenciamovimientoList = new ArrayList<Acreenciamovimiento>();
			for (Acreenciamovimiento acreenciamovimientoListAcreenciamovimientoToAttach : tipoacreencia
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
			tipoacreencia
					.setAcreenciamovimientoList(attachedAcreenciamovimientoList);
			List<Saldoacreencia> attachedSaldoacreenciaList = new ArrayList<Saldoacreencia>();
			for (Saldoacreencia saldoacreenciaListSaldoacreenciaToAttach : tipoacreencia
					.getSaldoacreenciaList()) {
				saldoacreenciaListSaldoacreenciaToAttach = em.getReference(
						saldoacreenciaListSaldoacreenciaToAttach.getClass(),
						saldoacreenciaListSaldoacreenciaToAttach
								.getSaldoacreenciaPK());
				attachedSaldoacreenciaList
						.add(saldoacreenciaListSaldoacreenciaToAttach);
			}
			tipoacreencia.setSaldoacreenciaList(attachedSaldoacreenciaList);
			em.persist(tipoacreencia);
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListTipoacreenciaoperacion : tipoacreencia
					.getTipoacreenciaoperacionList()) {
				Tipoacreencia oldIdTipoacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion = tipoacreenciaoperacionListTipoacreenciaoperacion
						.getIdTipoacreencia();
				tipoacreenciaoperacionListTipoacreenciaoperacion
						.setIdTipoacreencia(tipoacreencia);
				tipoacreenciaoperacionListTipoacreenciaoperacion = em
						.merge(tipoacreenciaoperacionListTipoacreenciaoperacion);
				if (oldIdTipoacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion != null) {
					oldIdTipoacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion
							.getTipoacreenciaoperacionList()
							.remove(tipoacreenciaoperacionListTipoacreenciaoperacion);
					oldIdTipoacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion = em
							.merge(oldIdTipoacreenciaOfTipoacreenciaoperacionListTipoacreenciaoperacion);
				}
			}
			for (Acreenciamovimientosaldo acreenciamovimientosaldoListAcreenciamovimientosaldo : tipoacreencia
					.getAcreenciamovimientosaldoList()) {
				Tipoacreencia oldIdTipoacreenciaOfAcreenciamovimientosaldoListAcreenciamovimientosaldo = acreenciamovimientosaldoListAcreenciamovimientosaldo
						.getIdTipoacreencia();
				acreenciamovimientosaldoListAcreenciamovimientosaldo
						.setIdTipoacreencia(tipoacreencia);
				acreenciamovimientosaldoListAcreenciamovimientosaldo = em
						.merge(acreenciamovimientosaldoListAcreenciamovimientosaldo);
				if (oldIdTipoacreenciaOfAcreenciamovimientosaldoListAcreenciamovimientosaldo != null) {
					oldIdTipoacreenciaOfAcreenciamovimientosaldoListAcreenciamovimientosaldo
							.getAcreenciamovimientosaldoList()
							.remove(acreenciamovimientosaldoListAcreenciamovimientosaldo);
					oldIdTipoacreenciaOfAcreenciamovimientosaldoListAcreenciamovimientosaldo = em
							.merge(oldIdTipoacreenciaOfAcreenciamovimientosaldoListAcreenciamovimientosaldo);
				}
			}
			for (Acreenciamovimiento acreenciamovimientoListAcreenciamovimiento : tipoacreencia
					.getAcreenciamovimientoList()) {
				Tipoacreencia oldIdTipoacreenciaOfAcreenciamovimientoListAcreenciamovimiento = acreenciamovimientoListAcreenciamovimiento
						.getIdTipoacreencia();
				acreenciamovimientoListAcreenciamovimiento
						.setIdTipoacreencia(tipoacreencia);
				acreenciamovimientoListAcreenciamovimiento = em
						.merge(acreenciamovimientoListAcreenciamovimiento);
				if (oldIdTipoacreenciaOfAcreenciamovimientoListAcreenciamovimiento != null) {
					oldIdTipoacreenciaOfAcreenciamovimientoListAcreenciamovimiento
							.getAcreenciamovimientoList().remove(
									acreenciamovimientoListAcreenciamovimiento);
					oldIdTipoacreenciaOfAcreenciamovimientoListAcreenciamovimiento = em
							.merge(oldIdTipoacreenciaOfAcreenciamovimientoListAcreenciamovimiento);
				}
			}
			for (Saldoacreencia saldoacreenciaListSaldoacreencia : tipoacreencia
					.getSaldoacreenciaList()) {
				Tipoacreencia oldTipoacreenciaOfSaldoacreenciaListSaldoacreencia = saldoacreenciaListSaldoacreencia
						.getTipoacreencia();
				saldoacreenciaListSaldoacreencia
						.setTipoacreencia(tipoacreencia);
				saldoacreenciaListSaldoacreencia = em
						.merge(saldoacreenciaListSaldoacreencia);
				if (oldTipoacreenciaOfSaldoacreenciaListSaldoacreencia != null) {
					oldTipoacreenciaOfSaldoacreenciaListSaldoacreencia
							.getSaldoacreenciaList().remove(
									saldoacreenciaListSaldoacreencia);
					oldTipoacreenciaOfSaldoacreenciaListSaldoacreencia = em
							.merge(oldTipoacreenciaOfSaldoacreenciaListSaldoacreencia);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTipoacreencia(tipoacreencia.getId()) != null) {
				throw new PreexistingEntityException("Tipoacreencia "
						+ tipoacreencia + " already exists.", ex);
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
	 * @param tipoacreencia
	 *            Tipoacreencia
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Tipoacreencia tipoacreencia)
			throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipoacreencia persistentTipoacreencia = em.find(
					Tipoacreencia.class, tipoacreencia.getId());
			List<Tipoacreenciaoperacion> tipoacreenciaoperacionListOld = persistentTipoacreencia
					.getTipoacreenciaoperacionList();
			List<Tipoacreenciaoperacion> tipoacreenciaoperacionListNew = tipoacreencia
					.getTipoacreenciaoperacionList();
			List<Acreenciamovimientosaldo> acreenciamovimientosaldoListOld = persistentTipoacreencia
					.getAcreenciamovimientosaldoList();
			List<Acreenciamovimientosaldo> acreenciamovimientosaldoListNew = tipoacreencia
					.getAcreenciamovimientosaldoList();
			List<Acreenciamovimiento> acreenciamovimientoListOld = persistentTipoacreencia
					.getAcreenciamovimientoList();
			List<Acreenciamovimiento> acreenciamovimientoListNew = tipoacreencia
					.getAcreenciamovimientoList();
			List<Saldoacreencia> saldoacreenciaListOld = persistentTipoacreencia
					.getSaldoacreenciaList();
			List<Saldoacreencia> saldoacreenciaListNew = tipoacreencia
					.getSaldoacreenciaList();
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
									+ " since its idTipoacreencia field is not nullable.");
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
									+ " since its idTipoacreencia field is not nullable.");
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
									+ " since its idTipoacreencia field is not nullable.");
				}
			}
			for (Saldoacreencia saldoacreenciaListOldSaldoacreencia : saldoacreenciaListOld) {
				if (!saldoacreenciaListNew
						.contains(saldoacreenciaListOldSaldoacreencia)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Saldoacreencia "
									+ saldoacreenciaListOldSaldoacreencia
									+ " since its tipoacreencia field is not nullable.");
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
			tipoacreencia
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
			tipoacreencia
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
			tipoacreencia
					.setAcreenciamovimientoList(acreenciamovimientoListNew);
			List<Saldoacreencia> attachedSaldoacreenciaListNew = new ArrayList<Saldoacreencia>();
			for (Saldoacreencia saldoacreenciaListNewSaldoacreenciaToAttach : saldoacreenciaListNew) {
				saldoacreenciaListNewSaldoacreenciaToAttach = em.getReference(
						saldoacreenciaListNewSaldoacreenciaToAttach.getClass(),
						saldoacreenciaListNewSaldoacreenciaToAttach
								.getSaldoacreenciaPK());
				attachedSaldoacreenciaListNew
						.add(saldoacreenciaListNewSaldoacreenciaToAttach);
			}
			saldoacreenciaListNew = attachedSaldoacreenciaListNew;
			tipoacreencia.setSaldoacreenciaList(saldoacreenciaListNew);
			tipoacreencia = em.merge(tipoacreencia);
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListNewTipoacreenciaoperacion : tipoacreenciaoperacionListNew) {
				if (!tipoacreenciaoperacionListOld
						.contains(tipoacreenciaoperacionListNewTipoacreenciaoperacion)) {
					Tipoacreencia oldIdTipoacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion = tipoacreenciaoperacionListNewTipoacreenciaoperacion
							.getIdTipoacreencia();
					tipoacreenciaoperacionListNewTipoacreenciaoperacion
							.setIdTipoacreencia(tipoacreencia);
					tipoacreenciaoperacionListNewTipoacreenciaoperacion = em
							.merge(tipoacreenciaoperacionListNewTipoacreenciaoperacion);
					if (oldIdTipoacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion != null
							&& !oldIdTipoacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion
									.equals(tipoacreencia)) {
						oldIdTipoacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion
								.getTipoacreenciaoperacionList()
								.remove(tipoacreenciaoperacionListNewTipoacreenciaoperacion);
						oldIdTipoacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion = em
								.merge(oldIdTipoacreenciaOfTipoacreenciaoperacionListNewTipoacreenciaoperacion);
					}
				}
			}
			for (Acreenciamovimientosaldo acreenciamovimientosaldoListNewAcreenciamovimientosaldo : acreenciamovimientosaldoListNew) {
				if (!acreenciamovimientosaldoListOld
						.contains(acreenciamovimientosaldoListNewAcreenciamovimientosaldo)) {
					Tipoacreencia oldIdTipoacreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo = acreenciamovimientosaldoListNewAcreenciamovimientosaldo
							.getIdTipoacreencia();
					acreenciamovimientosaldoListNewAcreenciamovimientosaldo
							.setIdTipoacreencia(tipoacreencia);
					acreenciamovimientosaldoListNewAcreenciamovimientosaldo = em
							.merge(acreenciamovimientosaldoListNewAcreenciamovimientosaldo);
					if (oldIdTipoacreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo != null
							&& !oldIdTipoacreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo
									.equals(tipoacreencia)) {
						oldIdTipoacreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo
								.getAcreenciamovimientosaldoList()
								.remove(acreenciamovimientosaldoListNewAcreenciamovimientosaldo);
						oldIdTipoacreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo = em
								.merge(oldIdTipoacreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo);
					}
				}
			}
			for (Acreenciamovimiento acreenciamovimientoListNewAcreenciamovimiento : acreenciamovimientoListNew) {
				if (!acreenciamovimientoListOld
						.contains(acreenciamovimientoListNewAcreenciamovimiento)) {
					Tipoacreencia oldIdTipoacreenciaOfAcreenciamovimientoListNewAcreenciamovimiento = acreenciamovimientoListNewAcreenciamovimiento
							.getIdTipoacreencia();
					acreenciamovimientoListNewAcreenciamovimiento
							.setIdTipoacreencia(tipoacreencia);
					acreenciamovimientoListNewAcreenciamovimiento = em
							.merge(acreenciamovimientoListNewAcreenciamovimiento);
					if (oldIdTipoacreenciaOfAcreenciamovimientoListNewAcreenciamovimiento != null
							&& !oldIdTipoacreenciaOfAcreenciamovimientoListNewAcreenciamovimiento
									.equals(tipoacreencia)) {
						oldIdTipoacreenciaOfAcreenciamovimientoListNewAcreenciamovimiento
								.getAcreenciamovimientoList()
								.remove(acreenciamovimientoListNewAcreenciamovimiento);
						oldIdTipoacreenciaOfAcreenciamovimientoListNewAcreenciamovimiento = em
								.merge(oldIdTipoacreenciaOfAcreenciamovimientoListNewAcreenciamovimiento);
					}
				}
			}
			for (Saldoacreencia saldoacreenciaListNewSaldoacreencia : saldoacreenciaListNew) {
				if (!saldoacreenciaListOld
						.contains(saldoacreenciaListNewSaldoacreencia)) {
					Tipoacreencia oldTipoacreenciaOfSaldoacreenciaListNewSaldoacreencia = saldoacreenciaListNewSaldoacreencia
							.getTipoacreencia();
					saldoacreenciaListNewSaldoacreencia
							.setTipoacreencia(tipoacreencia);
					saldoacreenciaListNewSaldoacreencia = em
							.merge(saldoacreenciaListNewSaldoacreencia);
					if (oldTipoacreenciaOfSaldoacreenciaListNewSaldoacreencia != null
							&& !oldTipoacreenciaOfSaldoacreenciaListNewSaldoacreencia
									.equals(tipoacreencia)) {
						oldTipoacreenciaOfSaldoacreenciaListNewSaldoacreencia
								.getSaldoacreenciaList().remove(
										saldoacreenciaListNewSaldoacreencia);
						oldTipoacreenciaOfSaldoacreenciaListNewSaldoacreencia = em
								.merge(oldTipoacreenciaOfSaldoacreenciaListNewSaldoacreencia);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = tipoacreencia.getId();
				if (findTipoacreencia(id) == null) {
					throw new NonexistentEntityException(
							"The tipoacreencia with id " + id
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
			Tipoacreencia tipoacreencia;
			try {
				tipoacreencia = em.getReference(Tipoacreencia.class, id);
				tipoacreencia.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The tipoacreencia with id " + id
								+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Tipoacreenciaoperacion> tipoacreenciaoperacionListOrphanCheck = tipoacreencia
					.getTipoacreenciaoperacionList();
			for (Tipoacreenciaoperacion tipoacreenciaoperacionListOrphanCheckTipoacreenciaoperacion : tipoacreenciaoperacionListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Tipoacreencia ("
								+ tipoacreencia
								+ ") cannot be destroyed since the Tipoacreenciaoperacion "
								+ tipoacreenciaoperacionListOrphanCheckTipoacreenciaoperacion
								+ " in its tipoacreenciaoperacionList field has a non-nullable idTipoacreencia field.");
			}
			List<Acreenciamovimientosaldo> acreenciamovimientosaldoListOrphanCheck = tipoacreencia
					.getAcreenciamovimientosaldoList();
			for (Acreenciamovimientosaldo acreenciamovimientosaldoListOrphanCheckAcreenciamovimientosaldo : acreenciamovimientosaldoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Tipoacreencia ("
								+ tipoacreencia
								+ ") cannot be destroyed since the Acreenciamovimientosaldo "
								+ acreenciamovimientosaldoListOrphanCheckAcreenciamovimientosaldo
								+ " in its acreenciamovimientosaldoList field has a non-nullable idTipoacreencia field.");
			}
			List<Acreenciamovimiento> acreenciamovimientoListOrphanCheck = tipoacreencia
					.getAcreenciamovimientoList();
			for (Acreenciamovimiento acreenciamovimientoListOrphanCheckAcreenciamovimiento : acreenciamovimientoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Tipoacreencia ("
								+ tipoacreencia
								+ ") cannot be destroyed since the Acreenciamovimiento "
								+ acreenciamovimientoListOrphanCheckAcreenciamovimiento
								+ " in its acreenciamovimientoList field has a non-nullable idTipoacreencia field.");
			}
			List<Saldoacreencia> saldoacreenciaListOrphanCheck = tipoacreencia
					.getSaldoacreenciaList();
			for (Saldoacreencia saldoacreenciaListOrphanCheckSaldoacreencia : saldoacreenciaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Tipoacreencia ("
								+ tipoacreencia
								+ ") cannot be destroyed since the Saldoacreencia "
								+ saldoacreenciaListOrphanCheckSaldoacreencia
								+ " in its saldoacreenciaList field has a non-nullable tipoacreencia field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(tipoacreencia);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTipoacreenciaEntities.
	 * 
	 * @return List<Tipoacreencia>
	 */
	public List<Tipoacreencia> findTipoacreenciaEntities() {
		return findTipoacreenciaEntities(true, -1, -1);
	}

	/**
	 * Method findTipoacreenciaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipoacreencia>
	 */
	public List<Tipoacreencia> findTipoacreenciaEntities(int maxResults,
			int firstResult) {
		return findTipoacreenciaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTipoacreenciaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipoacreencia>
	 */
	private List<Tipoacreencia> findTipoacreenciaEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Tipoacreencia.class));
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
	 * Method findTipoacreencia.
	 * 
	 * @param id
	 *            Long
	 * @return Tipoacreencia
	 */
	public Tipoacreencia findTipoacreencia(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Tipoacreencia.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTipoacreenciaCount.
	 * 
	 * @return int
	 */
	public int getTipoacreenciaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Tipoacreencia> rt = cq.from(Tipoacreencia.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
