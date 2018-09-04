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
public class TipodocumentoJpaController implements Serializable {

	/**
	 * Constructor for TipodocumentoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TipodocumentoJpaController(EntityManagerFactory emf) {
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
	 * @param tipodocumento
	 *            Tipodocumento
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Tipodocumento tipodocumento)
			throws PreexistingEntityException, Exception {
		if (tipodocumento.getCategorialineaincluyeList() == null) {
			tipodocumento
					.setCategorialineaincluyeList(new ArrayList<Categorialineaincluye>());
		}
		if (tipodocumento.getTipodocumentoList() == null) {
			tipodocumento.setTipodocumentoList(new ArrayList<Tipodocumento>());
		}
		if (tipodocumento.getTipodocumentoList1() == null) {
			tipodocumento.setTipodocumentoList1(new ArrayList<Tipodocumento>());
		}
		if (tipodocumento.getTipoclientetipodocumentoList() == null) {
			tipodocumento
					.setTipoclientetipodocumentoList(new ArrayList<Tipoclientetipodocumento>());
		}
		if (tipodocumento.getTransacciondocumentoList() == null) {
			tipodocumento
					.setTransacciondocumentoList(new ArrayList<Transacciondocumento>());
		}
		if (tipodocumento.getComprobantefiscalpreimpresoList() == null) {
			tipodocumento
					.setComprobantefiscalpreimpresoList(new ArrayList<Comprobantefiscalpreimpreso>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipodocumento idContrapartidadevolucion = tipodocumento
					.getIdContrapartidadevolucion();
			if (idContrapartidadevolucion != null) {
				idContrapartidadevolucion = em.getReference(
						idContrapartidadevolucion.getClass(),
						idContrapartidadevolucion.getId());
				tipodocumento
						.setIdContrapartidadevolucion(idContrapartidadevolucion);
			}
			Tipodocumento idContrapartida = tipodocumento.getIdContrapartida();
			if (idContrapartida != null) {
				idContrapartida = em.getReference(idContrapartida.getClass(),
						idContrapartida.getId());
				tipodocumento.setIdContrapartida(idContrapartida);
			}
			List<Categorialineaincluye> attachedCategorialineaincluyeList = new ArrayList<Categorialineaincluye>();
			for (Categorialineaincluye categorialineaincluyeListCategorialineaincluyeToAttach : tipodocumento
					.getCategorialineaincluyeList()) {
				categorialineaincluyeListCategorialineaincluyeToAttach = em
						.getReference(
								categorialineaincluyeListCategorialineaincluyeToAttach
										.getClass(),
								categorialineaincluyeListCategorialineaincluyeToAttach
										.getId());
				attachedCategorialineaincluyeList
						.add(categorialineaincluyeListCategorialineaincluyeToAttach);
			}
			tipodocumento
					.setCategorialineaincluyeList(attachedCategorialineaincluyeList);
			List<Tipodocumento> attachedTipodocumentoList = new ArrayList<Tipodocumento>();
			for (Tipodocumento tipodocumentoListTipodocumentoToAttach : tipodocumento
					.getTipodocumentoList()) {
				tipodocumentoListTipodocumentoToAttach = em.getReference(
						tipodocumentoListTipodocumentoToAttach.getClass(),
						tipodocumentoListTipodocumentoToAttach.getId());
				attachedTipodocumentoList
						.add(tipodocumentoListTipodocumentoToAttach);
			}
			tipodocumento.setTipodocumentoList(attachedTipodocumentoList);
			List<Tipodocumento> attachedTipodocumentoList1 = new ArrayList<Tipodocumento>();
			for (Tipodocumento tipodocumentoList1TipodocumentoToAttach : tipodocumento
					.getTipodocumentoList1()) {
				tipodocumentoList1TipodocumentoToAttach = em.getReference(
						tipodocumentoList1TipodocumentoToAttach.getClass(),
						tipodocumentoList1TipodocumentoToAttach.getId());
				attachedTipodocumentoList1
						.add(tipodocumentoList1TipodocumentoToAttach);
			}
			tipodocumento.setTipodocumentoList1(attachedTipodocumentoList1);
			List<Tipoclientetipodocumento> attachedTipoclientetipodocumentoList = new ArrayList<Tipoclientetipodocumento>();
			for (Tipoclientetipodocumento tipoclientetipodocumentoListTipoclientetipodocumentoToAttach : tipodocumento
					.getTipoclientetipodocumentoList()) {
				tipoclientetipodocumentoListTipoclientetipodocumentoToAttach = em
						.getReference(
								tipoclientetipodocumentoListTipoclientetipodocumentoToAttach
										.getClass(),
								tipoclientetipodocumentoListTipoclientetipodocumentoToAttach
										.getId());
				attachedTipoclientetipodocumentoList
						.add(tipoclientetipodocumentoListTipoclientetipodocumentoToAttach);
			}
			tipodocumento
					.setTipoclientetipodocumentoList(attachedTipoclientetipodocumentoList);
			List<Transacciondocumento> attachedTransacciondocumentoList = new ArrayList<Transacciondocumento>();
			for (Transacciondocumento transacciondocumentoListTransacciondocumentoToAttach : tipodocumento
					.getTransacciondocumentoList()) {
				transacciondocumentoListTransacciondocumentoToAttach = em
						.getReference(
								transacciondocumentoListTransacciondocumentoToAttach
										.getClass(),
								transacciondocumentoListTransacciondocumentoToAttach
										.getId());
				attachedTransacciondocumentoList
						.add(transacciondocumentoListTransacciondocumentoToAttach);
			}
			tipodocumento
					.setTransacciondocumentoList(attachedTransacciondocumentoList);
			List<Comprobantefiscalpreimpreso> attachedComprobantefiscalpreimpresoList = new ArrayList<Comprobantefiscalpreimpreso>();
			for (Comprobantefiscalpreimpreso comprobantefiscalpreimpresoListComprobantefiscalpreimpresoToAttach : tipodocumento
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
			tipodocumento
					.setComprobantefiscalpreimpresoList(attachedComprobantefiscalpreimpresoList);
			em.persist(tipodocumento);
			if (idContrapartidadevolucion != null) {
				idContrapartidadevolucion.getTipodocumentoList().add(
						tipodocumento);
				idContrapartidadevolucion = em.merge(idContrapartidadevolucion);
			}
			if (idContrapartida != null) {
				idContrapartida.getTipodocumentoList().add(tipodocumento);
				idContrapartida = em.merge(idContrapartida);
			}
			for (Categorialineaincluye categorialineaincluyeListCategorialineaincluye : tipodocumento
					.getCategorialineaincluyeList()) {
				Tipodocumento oldIdTipodocumentoOfCategorialineaincluyeListCategorialineaincluye = categorialineaincluyeListCategorialineaincluye
						.getIdTipodocumento();
				categorialineaincluyeListCategorialineaincluye
						.setIdTipodocumento(tipodocumento);
				categorialineaincluyeListCategorialineaincluye = em
						.merge(categorialineaincluyeListCategorialineaincluye);
				if (oldIdTipodocumentoOfCategorialineaincluyeListCategorialineaincluye != null) {
					oldIdTipodocumentoOfCategorialineaincluyeListCategorialineaincluye
							.getCategorialineaincluyeList()
							.remove(categorialineaincluyeListCategorialineaincluye);
					oldIdTipodocumentoOfCategorialineaincluyeListCategorialineaincluye = em
							.merge(oldIdTipodocumentoOfCategorialineaincluyeListCategorialineaincluye);
				}
			}
			for (Tipodocumento tipodocumentoListTipodocumento : tipodocumento
					.getTipodocumentoList()) {
				Tipodocumento oldIdContrapartidadevolucionOfTipodocumentoListTipodocumento = tipodocumentoListTipodocumento
						.getIdContrapartidadevolucion();
				tipodocumentoListTipodocumento
						.setIdContrapartidadevolucion(tipodocumento);
				tipodocumentoListTipodocumento = em
						.merge(tipodocumentoListTipodocumento);
				if (oldIdContrapartidadevolucionOfTipodocumentoListTipodocumento != null) {
					oldIdContrapartidadevolucionOfTipodocumentoListTipodocumento
							.getTipodocumentoList().remove(
									tipodocumentoListTipodocumento);
					oldIdContrapartidadevolucionOfTipodocumentoListTipodocumento = em
							.merge(oldIdContrapartidadevolucionOfTipodocumentoListTipodocumento);
				}
			}
			for (Tipodocumento tipodocumentoList1Tipodocumento : tipodocumento
					.getTipodocumentoList1()) {
				Tipodocumento oldIdContrapartidaOfTipodocumentoList1Tipodocumento = tipodocumentoList1Tipodocumento
						.getIdContrapartida();
				tipodocumentoList1Tipodocumento
						.setIdContrapartida(tipodocumento);
				tipodocumentoList1Tipodocumento = em
						.merge(tipodocumentoList1Tipodocumento);
				if (oldIdContrapartidaOfTipodocumentoList1Tipodocumento != null) {
					oldIdContrapartidaOfTipodocumentoList1Tipodocumento
							.getTipodocumentoList1().remove(
									tipodocumentoList1Tipodocumento);
					oldIdContrapartidaOfTipodocumentoList1Tipodocumento = em
							.merge(oldIdContrapartidaOfTipodocumentoList1Tipodocumento);
				}
			}
			for (Tipoclientetipodocumento tipoclientetipodocumentoListTipoclientetipodocumento : tipodocumento
					.getTipoclientetipodocumentoList()) {
				Tipodocumento oldIdTipodocumentoOfTipoclientetipodocumentoListTipoclientetipodocumento = tipoclientetipodocumentoListTipoclientetipodocumento
						.getIdTipodocumento();
				tipoclientetipodocumentoListTipoclientetipodocumento
						.setIdTipodocumento(tipodocumento);
				tipoclientetipodocumentoListTipoclientetipodocumento = em
						.merge(tipoclientetipodocumentoListTipoclientetipodocumento);
				if (oldIdTipodocumentoOfTipoclientetipodocumentoListTipoclientetipodocumento != null) {
					oldIdTipodocumentoOfTipoclientetipodocumentoListTipoclientetipodocumento
							.getTipoclientetipodocumentoList()
							.remove(tipoclientetipodocumentoListTipoclientetipodocumento);
					oldIdTipodocumentoOfTipoclientetipodocumentoListTipoclientetipodocumento = em
							.merge(oldIdTipodocumentoOfTipoclientetipodocumentoListTipoclientetipodocumento);
				}
			}
			for (Transacciondocumento transacciondocumentoListTransacciondocumento : tipodocumento
					.getTransacciondocumentoList()) {
				Tipodocumento oldIdTipodocumentoOfTransacciondocumentoListTransacciondocumento = transacciondocumentoListTransacciondocumento
						.getIdTipodocumento();
				transacciondocumentoListTransacciondocumento
						.setIdTipodocumento(tipodocumento);
				transacciondocumentoListTransacciondocumento = em
						.merge(transacciondocumentoListTransacciondocumento);
				if (oldIdTipodocumentoOfTransacciondocumentoListTransacciondocumento != null) {
					oldIdTipodocumentoOfTransacciondocumentoListTransacciondocumento
							.getTransacciondocumentoList()
							.remove(transacciondocumentoListTransacciondocumento);
					oldIdTipodocumentoOfTransacciondocumentoListTransacciondocumento = em
							.merge(oldIdTipodocumentoOfTransacciondocumentoListTransacciondocumento);
				}
			}
			for (Comprobantefiscalpreimpreso comprobantefiscalpreimpresoListComprobantefiscalpreimpreso : tipodocumento
					.getComprobantefiscalpreimpresoList()) {
				Tipodocumento oldIdTipodocumentoOfComprobantefiscalpreimpresoListComprobantefiscalpreimpreso = comprobantefiscalpreimpresoListComprobantefiscalpreimpreso
						.getIdTipodocumento();
				comprobantefiscalpreimpresoListComprobantefiscalpreimpreso
						.setIdTipodocumento(tipodocumento);
				comprobantefiscalpreimpresoListComprobantefiscalpreimpreso = em
						.merge(comprobantefiscalpreimpresoListComprobantefiscalpreimpreso);
				if (oldIdTipodocumentoOfComprobantefiscalpreimpresoListComprobantefiscalpreimpreso != null) {
					oldIdTipodocumentoOfComprobantefiscalpreimpresoListComprobantefiscalpreimpreso
							.getComprobantefiscalpreimpresoList()
							.remove(comprobantefiscalpreimpresoListComprobantefiscalpreimpreso);
					oldIdTipodocumentoOfComprobantefiscalpreimpresoListComprobantefiscalpreimpreso = em
							.merge(oldIdTipodocumentoOfComprobantefiscalpreimpresoListComprobantefiscalpreimpreso);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTipodocumento(tipodocumento.getId()) != null) {
				throw new PreexistingEntityException("Tipodocumento "
						+ tipodocumento + " already exists.", ex);
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
	 * @param tipodocumento
	 *            Tipodocumento
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Tipodocumento tipodocumento)
			throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipodocumento persistentTipodocumento = em.find(
					Tipodocumento.class, tipodocumento.getId());
			Tipodocumento idContrapartidadevolucionOld = persistentTipodocumento
					.getIdContrapartidadevolucion();
			Tipodocumento idContrapartidadevolucionNew = tipodocumento
					.getIdContrapartidadevolucion();
			Tipodocumento idContrapartidaOld = persistentTipodocumento
					.getIdContrapartida();
			Tipodocumento idContrapartidaNew = tipodocumento
					.getIdContrapartida();
			List<Categorialineaincluye> categorialineaincluyeListOld = persistentTipodocumento
					.getCategorialineaincluyeList();
			List<Categorialineaincluye> categorialineaincluyeListNew = tipodocumento
					.getCategorialineaincluyeList();
			List<Tipodocumento> tipodocumentoListOld = persistentTipodocumento
					.getTipodocumentoList();
			List<Tipodocumento> tipodocumentoListNew = tipodocumento
					.getTipodocumentoList();
			List<Tipodocumento> tipodocumentoList1Old = persistentTipodocumento
					.getTipodocumentoList1();
			List<Tipodocumento> tipodocumentoList1New = tipodocumento
					.getTipodocumentoList1();
			List<Tipoclientetipodocumento> tipoclientetipodocumentoListOld = persistentTipodocumento
					.getTipoclientetipodocumentoList();
			List<Tipoclientetipodocumento> tipoclientetipodocumentoListNew = tipodocumento
					.getTipoclientetipodocumentoList();
			List<Transacciondocumento> transacciondocumentoListOld = persistentTipodocumento
					.getTransacciondocumentoList();
			List<Transacciondocumento> transacciondocumentoListNew = tipodocumento
					.getTransacciondocumentoList();
			List<Comprobantefiscalpreimpreso> comprobantefiscalpreimpresoListOld = persistentTipodocumento
					.getComprobantefiscalpreimpresoList();
			List<Comprobantefiscalpreimpreso> comprobantefiscalpreimpresoListNew = tipodocumento
					.getComprobantefiscalpreimpresoList();
			List<String> illegalOrphanMessages = null;
			for (Categorialineaincluye categorialineaincluyeListOldCategorialineaincluye : categorialineaincluyeListOld) {
				if (!categorialineaincluyeListNew
						.contains(categorialineaincluyeListOldCategorialineaincluye)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Categorialineaincluye "
									+ categorialineaincluyeListOldCategorialineaincluye
									+ " since its idTipodocumento field is not nullable.");
				}
			}
			for (Tipoclientetipodocumento tipoclientetipodocumentoListOldTipoclientetipodocumento : tipoclientetipodocumentoListOld) {
				if (!tipoclientetipodocumentoListNew
						.contains(tipoclientetipodocumentoListOldTipoclientetipodocumento)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Tipoclientetipodocumento "
									+ tipoclientetipodocumentoListOldTipoclientetipodocumento
									+ " since its idTipodocumento field is not nullable.");
				}
			}
			for (Transacciondocumento transacciondocumentoListOldTransacciondocumento : transacciondocumentoListOld) {
				if (!transacciondocumentoListNew
						.contains(transacciondocumentoListOldTransacciondocumento)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Transacciondocumento "
									+ transacciondocumentoListOldTransacciondocumento
									+ " since its idTipodocumento field is not nullable.");
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
									+ " since its idTipodocumento field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idContrapartidadevolucionNew != null) {
				idContrapartidadevolucionNew = em.getReference(
						idContrapartidadevolucionNew.getClass(),
						idContrapartidadevolucionNew.getId());
				tipodocumento
						.setIdContrapartidadevolucion(idContrapartidadevolucionNew);
			}
			if (idContrapartidaNew != null) {
				idContrapartidaNew = em.getReference(
						idContrapartidaNew.getClass(),
						idContrapartidaNew.getId());
				tipodocumento.setIdContrapartida(idContrapartidaNew);
			}
			List<Categorialineaincluye> attachedCategorialineaincluyeListNew = new ArrayList<Categorialineaincluye>();
			for (Categorialineaincluye categorialineaincluyeListNewCategorialineaincluyeToAttach : categorialineaincluyeListNew) {
				categorialineaincluyeListNewCategorialineaincluyeToAttach = em
						.getReference(
								categorialineaincluyeListNewCategorialineaincluyeToAttach
										.getClass(),
								categorialineaincluyeListNewCategorialineaincluyeToAttach
										.getId());
				attachedCategorialineaincluyeListNew
						.add(categorialineaincluyeListNewCategorialineaincluyeToAttach);
			}
			categorialineaincluyeListNew = attachedCategorialineaincluyeListNew;
			tipodocumento
					.setCategorialineaincluyeList(categorialineaincluyeListNew);
			List<Tipodocumento> attachedTipodocumentoListNew = new ArrayList<Tipodocumento>();
			for (Tipodocumento tipodocumentoListNewTipodocumentoToAttach : tipodocumentoListNew) {
				tipodocumentoListNewTipodocumentoToAttach = em.getReference(
						tipodocumentoListNewTipodocumentoToAttach.getClass(),
						tipodocumentoListNewTipodocumentoToAttach.getId());
				attachedTipodocumentoListNew
						.add(tipodocumentoListNewTipodocumentoToAttach);
			}
			tipodocumentoListNew = attachedTipodocumentoListNew;
			tipodocumento.setTipodocumentoList(tipodocumentoListNew);
			List<Tipodocumento> attachedTipodocumentoList1New = new ArrayList<Tipodocumento>();
			for (Tipodocumento tipodocumentoList1NewTipodocumentoToAttach : tipodocumentoList1New) {
				tipodocumentoList1NewTipodocumentoToAttach = em.getReference(
						tipodocumentoList1NewTipodocumentoToAttach.getClass(),
						tipodocumentoList1NewTipodocumentoToAttach.getId());
				attachedTipodocumentoList1New
						.add(tipodocumentoList1NewTipodocumentoToAttach);
			}
			tipodocumentoList1New = attachedTipodocumentoList1New;
			tipodocumento.setTipodocumentoList1(tipodocumentoList1New);
			List<Tipoclientetipodocumento> attachedTipoclientetipodocumentoListNew = new ArrayList<Tipoclientetipodocumento>();
			for (Tipoclientetipodocumento tipoclientetipodocumentoListNewTipoclientetipodocumentoToAttach : tipoclientetipodocumentoListNew) {
				tipoclientetipodocumentoListNewTipoclientetipodocumentoToAttach = em
						.getReference(
								tipoclientetipodocumentoListNewTipoclientetipodocumentoToAttach
										.getClass(),
								tipoclientetipodocumentoListNewTipoclientetipodocumentoToAttach
										.getId());
				attachedTipoclientetipodocumentoListNew
						.add(tipoclientetipodocumentoListNewTipoclientetipodocumentoToAttach);
			}
			tipoclientetipodocumentoListNew = attachedTipoclientetipodocumentoListNew;
			tipodocumento
					.setTipoclientetipodocumentoList(tipoclientetipodocumentoListNew);
			List<Transacciondocumento> attachedTransacciondocumentoListNew = new ArrayList<Transacciondocumento>();
			for (Transacciondocumento transacciondocumentoListNewTransacciondocumentoToAttach : transacciondocumentoListNew) {
				transacciondocumentoListNewTransacciondocumentoToAttach = em
						.getReference(
								transacciondocumentoListNewTransacciondocumentoToAttach
										.getClass(),
								transacciondocumentoListNewTransacciondocumentoToAttach
										.getId());
				attachedTransacciondocumentoListNew
						.add(transacciondocumentoListNewTransacciondocumentoToAttach);
			}
			transacciondocumentoListNew = attachedTransacciondocumentoListNew;
			tipodocumento
					.setTransacciondocumentoList(transacciondocumentoListNew);
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
			tipodocumento
					.setComprobantefiscalpreimpresoList(comprobantefiscalpreimpresoListNew);
			tipodocumento = em.merge(tipodocumento);
			if (idContrapartidadevolucionOld != null
					&& !idContrapartidadevolucionOld
							.equals(idContrapartidadevolucionNew)) {
				idContrapartidadevolucionOld.getTipodocumentoList().remove(
						tipodocumento);
				idContrapartidadevolucionOld = em
						.merge(idContrapartidadevolucionOld);
			}
			if (idContrapartidadevolucionNew != null
					&& !idContrapartidadevolucionNew
							.equals(idContrapartidadevolucionOld)) {
				idContrapartidadevolucionNew.getTipodocumentoList().add(
						tipodocumento);
				idContrapartidadevolucionNew = em
						.merge(idContrapartidadevolucionNew);
			}
			if (idContrapartidaOld != null
					&& !idContrapartidaOld.equals(idContrapartidaNew)) {
				idContrapartidaOld.getTipodocumentoList().remove(tipodocumento);
				idContrapartidaOld = em.merge(idContrapartidaOld);
			}
			if (idContrapartidaNew != null
					&& !idContrapartidaNew.equals(idContrapartidaOld)) {
				idContrapartidaNew.getTipodocumentoList().add(tipodocumento);
				idContrapartidaNew = em.merge(idContrapartidaNew);
			}
			for (Categorialineaincluye categorialineaincluyeListNewCategorialineaincluye : categorialineaincluyeListNew) {
				if (!categorialineaincluyeListOld
						.contains(categorialineaincluyeListNewCategorialineaincluye)) {
					Tipodocumento oldIdTipodocumentoOfCategorialineaincluyeListNewCategorialineaincluye = categorialineaincluyeListNewCategorialineaincluye
							.getIdTipodocumento();
					categorialineaincluyeListNewCategorialineaincluye
							.setIdTipodocumento(tipodocumento);
					categorialineaincluyeListNewCategorialineaincluye = em
							.merge(categorialineaincluyeListNewCategorialineaincluye);
					if (oldIdTipodocumentoOfCategorialineaincluyeListNewCategorialineaincluye != null
							&& !oldIdTipodocumentoOfCategorialineaincluyeListNewCategorialineaincluye
									.equals(tipodocumento)) {
						oldIdTipodocumentoOfCategorialineaincluyeListNewCategorialineaincluye
								.getCategorialineaincluyeList()
								.remove(categorialineaincluyeListNewCategorialineaincluye);
						oldIdTipodocumentoOfCategorialineaincluyeListNewCategorialineaincluye = em
								.merge(oldIdTipodocumentoOfCategorialineaincluyeListNewCategorialineaincluye);
					}
				}
			}
			for (Tipodocumento tipodocumentoListOldTipodocumento : tipodocumentoListOld) {
				if (!tipodocumentoListNew
						.contains(tipodocumentoListOldTipodocumento)) {
					tipodocumentoListOldTipodocumento
							.setIdContrapartidadevolucion(null);
					tipodocumentoListOldTipodocumento = em
							.merge(tipodocumentoListOldTipodocumento);
				}
			}
			for (Tipodocumento tipodocumentoListNewTipodocumento : tipodocumentoListNew) {
				if (!tipodocumentoListOld
						.contains(tipodocumentoListNewTipodocumento)) {
					Tipodocumento oldIdContrapartidadevolucionOfTipodocumentoListNewTipodocumento = tipodocumentoListNewTipodocumento
							.getIdContrapartidadevolucion();
					tipodocumentoListNewTipodocumento
							.setIdContrapartidadevolucion(tipodocumento);
					tipodocumentoListNewTipodocumento = em
							.merge(tipodocumentoListNewTipodocumento);
					if (oldIdContrapartidadevolucionOfTipodocumentoListNewTipodocumento != null
							&& !oldIdContrapartidadevolucionOfTipodocumentoListNewTipodocumento
									.equals(tipodocumento)) {
						oldIdContrapartidadevolucionOfTipodocumentoListNewTipodocumento
								.getTipodocumentoList().remove(
										tipodocumentoListNewTipodocumento);
						oldIdContrapartidadevolucionOfTipodocumentoListNewTipodocumento = em
								.merge(oldIdContrapartidadevolucionOfTipodocumentoListNewTipodocumento);
					}
				}
			}
			for (Tipodocumento tipodocumentoList1OldTipodocumento : tipodocumentoList1Old) {
				if (!tipodocumentoList1New
						.contains(tipodocumentoList1OldTipodocumento)) {
					tipodocumentoList1OldTipodocumento.setIdContrapartida(null);
					tipodocumentoList1OldTipodocumento = em
							.merge(tipodocumentoList1OldTipodocumento);
				}
			}
			for (Tipodocumento tipodocumentoList1NewTipodocumento : tipodocumentoList1New) {
				if (!tipodocumentoList1Old
						.contains(tipodocumentoList1NewTipodocumento)) {
					Tipodocumento oldIdContrapartidaOfTipodocumentoList1NewTipodocumento = tipodocumentoList1NewTipodocumento
							.getIdContrapartida();
					tipodocumentoList1NewTipodocumento
							.setIdContrapartida(tipodocumento);
					tipodocumentoList1NewTipodocumento = em
							.merge(tipodocumentoList1NewTipodocumento);
					if (oldIdContrapartidaOfTipodocumentoList1NewTipodocumento != null
							&& !oldIdContrapartidaOfTipodocumentoList1NewTipodocumento
									.equals(tipodocumento)) {
						oldIdContrapartidaOfTipodocumentoList1NewTipodocumento
								.getTipodocumentoList1().remove(
										tipodocumentoList1NewTipodocumento);
						oldIdContrapartidaOfTipodocumentoList1NewTipodocumento = em
								.merge(oldIdContrapartidaOfTipodocumentoList1NewTipodocumento);
					}
				}
			}
			for (Tipoclientetipodocumento tipoclientetipodocumentoListNewTipoclientetipodocumento : tipoclientetipodocumentoListNew) {
				if (!tipoclientetipodocumentoListOld
						.contains(tipoclientetipodocumentoListNewTipoclientetipodocumento)) {
					Tipodocumento oldIdTipodocumentoOfTipoclientetipodocumentoListNewTipoclientetipodocumento = tipoclientetipodocumentoListNewTipoclientetipodocumento
							.getIdTipodocumento();
					tipoclientetipodocumentoListNewTipoclientetipodocumento
							.setIdTipodocumento(tipodocumento);
					tipoclientetipodocumentoListNewTipoclientetipodocumento = em
							.merge(tipoclientetipodocumentoListNewTipoclientetipodocumento);
					if (oldIdTipodocumentoOfTipoclientetipodocumentoListNewTipoclientetipodocumento != null
							&& !oldIdTipodocumentoOfTipoclientetipodocumentoListNewTipoclientetipodocumento
									.equals(tipodocumento)) {
						oldIdTipodocumentoOfTipoclientetipodocumentoListNewTipoclientetipodocumento
								.getTipoclientetipodocumentoList()
								.remove(tipoclientetipodocumentoListNewTipoclientetipodocumento);
						oldIdTipodocumentoOfTipoclientetipodocumentoListNewTipoclientetipodocumento = em
								.merge(oldIdTipodocumentoOfTipoclientetipodocumentoListNewTipoclientetipodocumento);
					}
				}
			}
			for (Transacciondocumento transacciondocumentoListNewTransacciondocumento : transacciondocumentoListNew) {
				if (!transacciondocumentoListOld
						.contains(transacciondocumentoListNewTransacciondocumento)) {
					Tipodocumento oldIdTipodocumentoOfTransacciondocumentoListNewTransacciondocumento = transacciondocumentoListNewTransacciondocumento
							.getIdTipodocumento();
					transacciondocumentoListNewTransacciondocumento
							.setIdTipodocumento(tipodocumento);
					transacciondocumentoListNewTransacciondocumento = em
							.merge(transacciondocumentoListNewTransacciondocumento);
					if (oldIdTipodocumentoOfTransacciondocumentoListNewTransacciondocumento != null
							&& !oldIdTipodocumentoOfTransacciondocumentoListNewTransacciondocumento
									.equals(tipodocumento)) {
						oldIdTipodocumentoOfTransacciondocumentoListNewTransacciondocumento
								.getTransacciondocumentoList()
								.remove(transacciondocumentoListNewTransacciondocumento);
						oldIdTipodocumentoOfTransacciondocumentoListNewTransacciondocumento = em
								.merge(oldIdTipodocumentoOfTransacciondocumentoListNewTransacciondocumento);
					}
				}
			}
			for (Comprobantefiscalpreimpreso comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso : comprobantefiscalpreimpresoListNew) {
				if (!comprobantefiscalpreimpresoListOld
						.contains(comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso)) {
					Tipodocumento oldIdTipodocumentoOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso = comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso
							.getIdTipodocumento();
					comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso
							.setIdTipodocumento(tipodocumento);
					comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso = em
							.merge(comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso);
					if (oldIdTipodocumentoOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso != null
							&& !oldIdTipodocumentoOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso
									.equals(tipodocumento)) {
						oldIdTipodocumentoOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso
								.getComprobantefiscalpreimpresoList()
								.remove(comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso);
						oldIdTipodocumentoOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso = em
								.merge(oldIdTipodocumentoOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = tipodocumento.getId();
				if (findTipodocumento(id) == null) {
					throw new NonexistentEntityException(
							"The tipodocumento with id " + id
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
			Tipodocumento tipodocumento;
			try {
				tipodocumento = em.getReference(Tipodocumento.class, id);
				tipodocumento.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The tipodocumento with id " + id
								+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Categorialineaincluye> categorialineaincluyeListOrphanCheck = tipodocumento
					.getCategorialineaincluyeList();
			for (Categorialineaincluye categorialineaincluyeListOrphanCheckCategorialineaincluye : categorialineaincluyeListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Tipodocumento ("
								+ tipodocumento
								+ ") cannot be destroyed since the Categorialineaincluye "
								+ categorialineaincluyeListOrphanCheckCategorialineaincluye
								+ " in its categorialineaincluyeList field has a non-nullable idTipodocumento field.");
			}
			List<Tipoclientetipodocumento> tipoclientetipodocumentoListOrphanCheck = tipodocumento
					.getTipoclientetipodocumentoList();
			for (Tipoclientetipodocumento tipoclientetipodocumentoListOrphanCheckTipoclientetipodocumento : tipoclientetipodocumentoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Tipodocumento ("
								+ tipodocumento
								+ ") cannot be destroyed since the Tipoclientetipodocumento "
								+ tipoclientetipodocumentoListOrphanCheckTipoclientetipodocumento
								+ " in its tipoclientetipodocumentoList field has a non-nullable idTipodocumento field.");
			}
			List<Transacciondocumento> transacciondocumentoListOrphanCheck = tipodocumento
					.getTransacciondocumentoList();
			for (Transacciondocumento transacciondocumentoListOrphanCheckTransacciondocumento : transacciondocumentoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Tipodocumento ("
								+ tipodocumento
								+ ") cannot be destroyed since the Transacciondocumento "
								+ transacciondocumentoListOrphanCheckTransacciondocumento
								+ " in its transacciondocumentoList field has a non-nullable idTipodocumento field.");
			}
			List<Comprobantefiscalpreimpreso> comprobantefiscalpreimpresoListOrphanCheck = tipodocumento
					.getComprobantefiscalpreimpresoList();
			for (Comprobantefiscalpreimpreso comprobantefiscalpreimpresoListOrphanCheckComprobantefiscalpreimpreso : comprobantefiscalpreimpresoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Tipodocumento ("
								+ tipodocumento
								+ ") cannot be destroyed since the Comprobantefiscalpreimpreso "
								+ comprobantefiscalpreimpresoListOrphanCheckComprobantefiscalpreimpreso
								+ " in its comprobantefiscalpreimpresoList field has a non-nullable idTipodocumento field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Tipodocumento idContrapartidadevolucion = tipodocumento
					.getIdContrapartidadevolucion();
			if (idContrapartidadevolucion != null) {
				idContrapartidadevolucion.getTipodocumentoList().remove(
						tipodocumento);
				idContrapartidadevolucion = em.merge(idContrapartidadevolucion);
			}
			Tipodocumento idContrapartida = tipodocumento.getIdContrapartida();
			if (idContrapartida != null) {
				idContrapartida.getTipodocumentoList().remove(tipodocumento);
				idContrapartida = em.merge(idContrapartida);
			}
			List<Tipodocumento> tipodocumentoList = tipodocumento
					.getTipodocumentoList();
			for (Tipodocumento tipodocumentoListTipodocumento : tipodocumentoList) {
				tipodocumentoListTipodocumento
						.setIdContrapartidadevolucion(null);
				tipodocumentoListTipodocumento = em
						.merge(tipodocumentoListTipodocumento);
			}
			List<Tipodocumento> tipodocumentoList1 = tipodocumento
					.getTipodocumentoList1();
			for (Tipodocumento tipodocumentoList1Tipodocumento : tipodocumentoList1) {
				tipodocumentoList1Tipodocumento.setIdContrapartida(null);
				tipodocumentoList1Tipodocumento = em
						.merge(tipodocumentoList1Tipodocumento);
			}
			em.remove(tipodocumento);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTipodocumentoEntities.
	 * 
	 * @return List<Tipodocumento>
	 */
	public List<Tipodocumento> findTipodocumentoEntities() {
		return findTipodocumentoEntities(true, -1, -1);
	}

	/**
	 * Method findTipodocumentoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipodocumento>
	 */
	public List<Tipodocumento> findTipodocumentoEntities(int maxResults,
			int firstResult) {
		return findTipodocumentoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTipodocumentoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipodocumento>
	 */
	private List<Tipodocumento> findTipodocumentoEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Tipodocumento.class));
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
	 * Method findTipodocumento.
	 * 
	 * @param id
	 *            Long
	 * @return Tipodocumento
	 */
	public Tipodocumento findTipodocumento(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Tipodocumento.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTipodocumentoCount.
	 * 
	 * @return int
	 */
	public int getTipodocumentoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Tipodocumento> rt = cq.from(Tipodocumento.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
