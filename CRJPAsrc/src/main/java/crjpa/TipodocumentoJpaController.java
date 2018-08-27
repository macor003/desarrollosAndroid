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
	public void create(Tipodocumento tipodocumento) throws PreexistingEntityException, Exception {
		if (tipodocumento.getTransacciondocumentoList() == null) {
			tipodocumento.setTransacciondocumentoList(new ArrayList<Transacciondocumento>());
		}
		if (tipodocumento.getComprobantefiscalpreimpresoList() == null) {
			tipodocumento.setComprobantefiscalpreimpresoList(new ArrayList<Comprobantefiscalpreimpreso>());
		}
		if (tipodocumento.getTipoclientetipodocumentoList() == null) {
			tipodocumento.setTipoclientetipodocumentoList(new ArrayList<Tipoclientetipodocumento>());
		}
		if (tipodocumento.getCategorialineaincluyeList() == null) {
			tipodocumento.setCategorialineaincluyeList(new ArrayList<Categorialineaincluye>());
		}
		if (tipodocumento.getTipodocumentoList() == null) {
			tipodocumento.setTipodocumentoList(new ArrayList<Tipodocumento>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipodocumento idContrapartida = tipodocumento.getIdContrapartida();
			if (idContrapartida != null) {
				idContrapartida = em.getReference(idContrapartida.getClass(), idContrapartida.getId());
				tipodocumento.setIdContrapartida(idContrapartida);
			}
			List<Transacciondocumento> attachedTransacciondocumentoList = new ArrayList<Transacciondocumento>();
			for (Transacciondocumento transacciondocumentoListTransacciondocumentoToAttach : tipodocumento
					.getTransacciondocumentoList()) {
				transacciondocumentoListTransacciondocumentoToAttach = em.getReference(
						transacciondocumentoListTransacciondocumentoToAttach.getClass(),
						transacciondocumentoListTransacciondocumentoToAttach.getId());
				attachedTransacciondocumentoList.add(transacciondocumentoListTransacciondocumentoToAttach);
			}
			tipodocumento.setTransacciondocumentoList(attachedTransacciondocumentoList);
			List<Comprobantefiscalpreimpreso> attachedComprobantefiscalpreimpresoList = new ArrayList<Comprobantefiscalpreimpreso>();
			for (Comprobantefiscalpreimpreso comprobantefiscalpreimpresoListComprobantefiscalpreimpresoToAttach : tipodocumento
					.getComprobantefiscalpreimpresoList()) {
				comprobantefiscalpreimpresoListComprobantefiscalpreimpresoToAttach = em.getReference(
						comprobantefiscalpreimpresoListComprobantefiscalpreimpresoToAttach.getClass(),
						comprobantefiscalpreimpresoListComprobantefiscalpreimpresoToAttach.getId());
				attachedComprobantefiscalpreimpresoList
						.add(comprobantefiscalpreimpresoListComprobantefiscalpreimpresoToAttach);
			}
			tipodocumento.setComprobantefiscalpreimpresoList(attachedComprobantefiscalpreimpresoList);
			List<Tipoclientetipodocumento> attachedTipoclientetipodocumentoList = new ArrayList<Tipoclientetipodocumento>();
			for (Tipoclientetipodocumento tipoclientetipodocumentoListTipoclientetipodocumentoToAttach : tipodocumento
					.getTipoclientetipodocumentoList()) {
				tipoclientetipodocumentoListTipoclientetipodocumentoToAttach = em.getReference(
						tipoclientetipodocumentoListTipoclientetipodocumentoToAttach.getClass(),
						tipoclientetipodocumentoListTipoclientetipodocumentoToAttach.getId());
				attachedTipoclientetipodocumentoList.add(tipoclientetipodocumentoListTipoclientetipodocumentoToAttach);
			}
			tipodocumento.setTipoclientetipodocumentoList(attachedTipoclientetipodocumentoList);
			List<Categorialineaincluye> attachedCategorialineaincluyeList = new ArrayList<Categorialineaincluye>();
			for (Categorialineaincluye categorialineaincluyeListCategorialineaincluyeToAttach : tipodocumento
					.getCategorialineaincluyeList()) {
				categorialineaincluyeListCategorialineaincluyeToAttach = em.getReference(
						categorialineaincluyeListCategorialineaincluyeToAttach.getClass(),
						categorialineaincluyeListCategorialineaincluyeToAttach.getId());
				attachedCategorialineaincluyeList.add(categorialineaincluyeListCategorialineaincluyeToAttach);
			}
			tipodocumento.setCategorialineaincluyeList(attachedCategorialineaincluyeList);
			List<Tipodocumento> attachedTipodocumentoList = new ArrayList<Tipodocumento>();
			for (Tipodocumento tipodocumentoListTipodocumentoToAttach : tipodocumento.getTipodocumentoList()) {
				tipodocumentoListTipodocumentoToAttach = em.getReference(
						tipodocumentoListTipodocumentoToAttach.getClass(),
						tipodocumentoListTipodocumentoToAttach.getId());
				attachedTipodocumentoList.add(tipodocumentoListTipodocumentoToAttach);
			}
			tipodocumento.setTipodocumentoList(attachedTipodocumentoList);
			em.persist(tipodocumento);
			if (idContrapartida != null) {
				idContrapartida.getTipodocumentoList().add(tipodocumento);
				idContrapartida = em.merge(idContrapartida);
			}
			for (Transacciondocumento transacciondocumentoListTransacciondocumento : tipodocumento
					.getTransacciondocumentoList()) {
				Tipodocumento oldIdTipodocumentoOfTransacciondocumentoListTransacciondocumento = transacciondocumentoListTransacciondocumento
						.getIdTipodocumento();
				transacciondocumentoListTransacciondocumento.setIdTipodocumento(tipodocumento);
				transacciondocumentoListTransacciondocumento = em.merge(transacciondocumentoListTransacciondocumento);
				if (oldIdTipodocumentoOfTransacciondocumentoListTransacciondocumento != null) {
					oldIdTipodocumentoOfTransacciondocumentoListTransacciondocumento.getTransacciondocumentoList()
							.remove(transacciondocumentoListTransacciondocumento);
					oldIdTipodocumentoOfTransacciondocumentoListTransacciondocumento = em
							.merge(oldIdTipodocumentoOfTransacciondocumentoListTransacciondocumento);
				}
			}
			for (Comprobantefiscalpreimpreso comprobantefiscalpreimpresoListComprobantefiscalpreimpreso : tipodocumento
					.getComprobantefiscalpreimpresoList()) {
				Tipodocumento oldIdTipodocumentoOfComprobantefiscalpreimpresoListComprobantefiscalpreimpreso = comprobantefiscalpreimpresoListComprobantefiscalpreimpreso
						.getIdTipodocumento();
				comprobantefiscalpreimpresoListComprobantefiscalpreimpreso.setIdTipodocumento(tipodocumento);
				comprobantefiscalpreimpresoListComprobantefiscalpreimpreso = em
						.merge(comprobantefiscalpreimpresoListComprobantefiscalpreimpreso);
				if (oldIdTipodocumentoOfComprobantefiscalpreimpresoListComprobantefiscalpreimpreso != null) {
					oldIdTipodocumentoOfComprobantefiscalpreimpresoListComprobantefiscalpreimpreso
							.getComprobantefiscalpreimpresoList().remove(
									comprobantefiscalpreimpresoListComprobantefiscalpreimpreso);
					oldIdTipodocumentoOfComprobantefiscalpreimpresoListComprobantefiscalpreimpreso = em
							.merge(oldIdTipodocumentoOfComprobantefiscalpreimpresoListComprobantefiscalpreimpreso);
				}
			}
			for (Tipoclientetipodocumento tipoclientetipodocumentoListTipoclientetipodocumento : tipodocumento
					.getTipoclientetipodocumentoList()) {
				Tipodocumento oldIdTipodocumentoOfTipoclientetipodocumentoListTipoclientetipodocumento = tipoclientetipodocumentoListTipoclientetipodocumento
						.getIdTipodocumento();
				tipoclientetipodocumentoListTipoclientetipodocumento.setIdTipodocumento(tipodocumento);
				tipoclientetipodocumentoListTipoclientetipodocumento = em
						.merge(tipoclientetipodocumentoListTipoclientetipodocumento);
				if (oldIdTipodocumentoOfTipoclientetipodocumentoListTipoclientetipodocumento != null) {
					oldIdTipodocumentoOfTipoclientetipodocumentoListTipoclientetipodocumento
							.getTipoclientetipodocumentoList().remove(
									tipoclientetipodocumentoListTipoclientetipodocumento);
					oldIdTipodocumentoOfTipoclientetipodocumentoListTipoclientetipodocumento = em
							.merge(oldIdTipodocumentoOfTipoclientetipodocumentoListTipoclientetipodocumento);
				}
			}
			for (Categorialineaincluye categorialineaincluyeListCategorialineaincluye : tipodocumento
					.getCategorialineaincluyeList()) {
				Tipodocumento oldIdTipodocumentoOfCategorialineaincluyeListCategorialineaincluye = categorialineaincluyeListCategorialineaincluye
						.getIdTipodocumento();
				categorialineaincluyeListCategorialineaincluye.setIdTipodocumento(tipodocumento);
				categorialineaincluyeListCategorialineaincluye = em
						.merge(categorialineaincluyeListCategorialineaincluye);
				if (oldIdTipodocumentoOfCategorialineaincluyeListCategorialineaincluye != null) {
					oldIdTipodocumentoOfCategorialineaincluyeListCategorialineaincluye.getCategorialineaincluyeList()
							.remove(categorialineaincluyeListCategorialineaincluye);
					oldIdTipodocumentoOfCategorialineaincluyeListCategorialineaincluye = em
							.merge(oldIdTipodocumentoOfCategorialineaincluyeListCategorialineaincluye);
				}
			}
			for (Tipodocumento tipodocumentoListTipodocumento : tipodocumento.getTipodocumentoList()) {
				Tipodocumento oldIdContrapartidaOfTipodocumentoListTipodocumento = tipodocumentoListTipodocumento
						.getIdContrapartida();
				tipodocumentoListTipodocumento.setIdContrapartida(tipodocumento);
				tipodocumentoListTipodocumento = em.merge(tipodocumentoListTipodocumento);
				if (oldIdContrapartidaOfTipodocumentoListTipodocumento != null) {
					oldIdContrapartidaOfTipodocumentoListTipodocumento.getTipodocumentoList().remove(
							tipodocumentoListTipodocumento);
					oldIdContrapartidaOfTipodocumentoListTipodocumento = em
							.merge(oldIdContrapartidaOfTipodocumentoListTipodocumento);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTipodocumento(tipodocumento.getId()) != null) {
				throw new PreexistingEntityException("Tipodocumento " + tipodocumento + " already exists.", ex);
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
	public void edit(Tipodocumento tipodocumento) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipodocumento persistentTipodocumento = em.find(Tipodocumento.class, tipodocumento.getId());
			Tipodocumento idContrapartidaOld = persistentTipodocumento.getIdContrapartida();
			Tipodocumento idContrapartidaNew = tipodocumento.getIdContrapartida();
			List<Transacciondocumento> transacciondocumentoListOld = persistentTipodocumento
					.getTransacciondocumentoList();
			List<Transacciondocumento> transacciondocumentoListNew = tipodocumento.getTransacciondocumentoList();
			List<Comprobantefiscalpreimpreso> comprobantefiscalpreimpresoListOld = persistentTipodocumento
					.getComprobantefiscalpreimpresoList();
			List<Comprobantefiscalpreimpreso> comprobantefiscalpreimpresoListNew = tipodocumento
					.getComprobantefiscalpreimpresoList();
			List<Tipoclientetipodocumento> tipoclientetipodocumentoListOld = persistentTipodocumento
					.getTipoclientetipodocumentoList();
			List<Tipoclientetipodocumento> tipoclientetipodocumentoListNew = tipodocumento
					.getTipoclientetipodocumentoList();
			List<Categorialineaincluye> categorialineaincluyeListOld = persistentTipodocumento
					.getCategorialineaincluyeList();
			List<Categorialineaincluye> categorialineaincluyeListNew = tipodocumento.getCategorialineaincluyeList();
			List<Tipodocumento> tipodocumentoListOld = persistentTipodocumento.getTipodocumentoList();
			List<Tipodocumento> tipodocumentoListNew = tipodocumento.getTipodocumentoList();
			List<String> illegalOrphanMessages = null;
			for (Transacciondocumento transacciondocumentoListOldTransacciondocumento : transacciondocumentoListOld) {
				if (!transacciondocumentoListNew.contains(transacciondocumentoListOldTransacciondocumento)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Transacciondocumento "
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
					illegalOrphanMessages.add("You must retain Comprobantefiscalpreimpreso "
							+ comprobantefiscalpreimpresoListOldComprobantefiscalpreimpreso
							+ " since its idTipodocumento field is not nullable.");
				}
			}
			for (Tipoclientetipodocumento tipoclientetipodocumentoListOldTipoclientetipodocumento : tipoclientetipodocumentoListOld) {
				if (!tipoclientetipodocumentoListNew.contains(tipoclientetipodocumentoListOldTipoclientetipodocumento)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Tipoclientetipodocumento "
							+ tipoclientetipodocumentoListOldTipoclientetipodocumento
							+ " since its idTipodocumento field is not nullable.");
				}
			}
			for (Categorialineaincluye categorialineaincluyeListOldCategorialineaincluye : categorialineaincluyeListOld) {
				if (!categorialineaincluyeListNew.contains(categorialineaincluyeListOldCategorialineaincluye)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Categorialineaincluye "
							+ categorialineaincluyeListOldCategorialineaincluye
							+ " since its idTipodocumento field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idContrapartidaNew != null) {
				idContrapartidaNew = em.getReference(idContrapartidaNew.getClass(), idContrapartidaNew.getId());
				tipodocumento.setIdContrapartida(idContrapartidaNew);
			}
			List<Transacciondocumento> attachedTransacciondocumentoListNew = new ArrayList<Transacciondocumento>();
			for (Transacciondocumento transacciondocumentoListNewTransacciondocumentoToAttach : transacciondocumentoListNew) {
				transacciondocumentoListNewTransacciondocumentoToAttach = em.getReference(
						transacciondocumentoListNewTransacciondocumentoToAttach.getClass(),
						transacciondocumentoListNewTransacciondocumentoToAttach.getId());
				attachedTransacciondocumentoListNew.add(transacciondocumentoListNewTransacciondocumentoToAttach);
			}
			transacciondocumentoListNew = attachedTransacciondocumentoListNew;
			tipodocumento.setTransacciondocumentoList(transacciondocumentoListNew);
			List<Comprobantefiscalpreimpreso> attachedComprobantefiscalpreimpresoListNew = new ArrayList<Comprobantefiscalpreimpreso>();
			for (Comprobantefiscalpreimpreso comprobantefiscalpreimpresoListNewComprobantefiscalpreimpresoToAttach : comprobantefiscalpreimpresoListNew) {
				comprobantefiscalpreimpresoListNewComprobantefiscalpreimpresoToAttach = em.getReference(
						comprobantefiscalpreimpresoListNewComprobantefiscalpreimpresoToAttach.getClass(),
						comprobantefiscalpreimpresoListNewComprobantefiscalpreimpresoToAttach.getId());
				attachedComprobantefiscalpreimpresoListNew
						.add(comprobantefiscalpreimpresoListNewComprobantefiscalpreimpresoToAttach);
			}
			comprobantefiscalpreimpresoListNew = attachedComprobantefiscalpreimpresoListNew;
			tipodocumento.setComprobantefiscalpreimpresoList(comprobantefiscalpreimpresoListNew);
			List<Tipoclientetipodocumento> attachedTipoclientetipodocumentoListNew = new ArrayList<Tipoclientetipodocumento>();
			for (Tipoclientetipodocumento tipoclientetipodocumentoListNewTipoclientetipodocumentoToAttach : tipoclientetipodocumentoListNew) {
				tipoclientetipodocumentoListNewTipoclientetipodocumentoToAttach = em.getReference(
						tipoclientetipodocumentoListNewTipoclientetipodocumentoToAttach.getClass(),
						tipoclientetipodocumentoListNewTipoclientetipodocumentoToAttach.getId());
				attachedTipoclientetipodocumentoListNew
						.add(tipoclientetipodocumentoListNewTipoclientetipodocumentoToAttach);
			}
			tipoclientetipodocumentoListNew = attachedTipoclientetipodocumentoListNew;
			tipodocumento.setTipoclientetipodocumentoList(tipoclientetipodocumentoListNew);
			List<Categorialineaincluye> attachedCategorialineaincluyeListNew = new ArrayList<Categorialineaincluye>();
			for (Categorialineaincluye categorialineaincluyeListNewCategorialineaincluyeToAttach : categorialineaincluyeListNew) {
				categorialineaincluyeListNewCategorialineaincluyeToAttach = em.getReference(
						categorialineaincluyeListNewCategorialineaincluyeToAttach.getClass(),
						categorialineaincluyeListNewCategorialineaincluyeToAttach.getId());
				attachedCategorialineaincluyeListNew.add(categorialineaincluyeListNewCategorialineaincluyeToAttach);
			}
			categorialineaincluyeListNew = attachedCategorialineaincluyeListNew;
			tipodocumento.setCategorialineaincluyeList(categorialineaincluyeListNew);
			List<Tipodocumento> attachedTipodocumentoListNew = new ArrayList<Tipodocumento>();
			for (Tipodocumento tipodocumentoListNewTipodocumentoToAttach : tipodocumentoListNew) {
				tipodocumentoListNewTipodocumentoToAttach = em.getReference(
						tipodocumentoListNewTipodocumentoToAttach.getClass(),
						tipodocumentoListNewTipodocumentoToAttach.getId());
				attachedTipodocumentoListNew.add(tipodocumentoListNewTipodocumentoToAttach);
			}
			tipodocumentoListNew = attachedTipodocumentoListNew;
			tipodocumento.setTipodocumentoList(tipodocumentoListNew);
			tipodocumento = em.merge(tipodocumento);
			if (idContrapartidaOld != null && !idContrapartidaOld.equals(idContrapartidaNew)) {
				idContrapartidaOld.getTipodocumentoList().remove(tipodocumento);
				idContrapartidaOld = em.merge(idContrapartidaOld);
			}
			if (idContrapartidaNew != null && !idContrapartidaNew.equals(idContrapartidaOld)) {
				idContrapartidaNew.getTipodocumentoList().add(tipodocumento);
				idContrapartidaNew = em.merge(idContrapartidaNew);
			}
			for (Transacciondocumento transacciondocumentoListNewTransacciondocumento : transacciondocumentoListNew) {
				if (!transacciondocumentoListOld.contains(transacciondocumentoListNewTransacciondocumento)) {
					Tipodocumento oldIdTipodocumentoOfTransacciondocumentoListNewTransacciondocumento = transacciondocumentoListNewTransacciondocumento
							.getIdTipodocumento();
					transacciondocumentoListNewTransacciondocumento.setIdTipodocumento(tipodocumento);
					transacciondocumentoListNewTransacciondocumento = em
							.merge(transacciondocumentoListNewTransacciondocumento);
					if (oldIdTipodocumentoOfTransacciondocumentoListNewTransacciondocumento != null
							&& !oldIdTipodocumentoOfTransacciondocumentoListNewTransacciondocumento
									.equals(tipodocumento)) {
						oldIdTipodocumentoOfTransacciondocumentoListNewTransacciondocumento
								.getTransacciondocumentoList().remove(transacciondocumentoListNewTransacciondocumento);
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
					comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso.setIdTipodocumento(tipodocumento);
					comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso = em
							.merge(comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso);
					if (oldIdTipodocumentoOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso != null
							&& !oldIdTipodocumentoOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso
									.equals(tipodocumento)) {
						oldIdTipodocumentoOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso
								.getComprobantefiscalpreimpresoList().remove(
										comprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso);
						oldIdTipodocumentoOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso = em
								.merge(oldIdTipodocumentoOfComprobantefiscalpreimpresoListNewComprobantefiscalpreimpreso);
					}
				}
			}
			for (Tipoclientetipodocumento tipoclientetipodocumentoListNewTipoclientetipodocumento : tipoclientetipodocumentoListNew) {
				if (!tipoclientetipodocumentoListOld.contains(tipoclientetipodocumentoListNewTipoclientetipodocumento)) {
					Tipodocumento oldIdTipodocumentoOfTipoclientetipodocumentoListNewTipoclientetipodocumento = tipoclientetipodocumentoListNewTipoclientetipodocumento
							.getIdTipodocumento();
					tipoclientetipodocumentoListNewTipoclientetipodocumento.setIdTipodocumento(tipodocumento);
					tipoclientetipodocumentoListNewTipoclientetipodocumento = em
							.merge(tipoclientetipodocumentoListNewTipoclientetipodocumento);
					if (oldIdTipodocumentoOfTipoclientetipodocumentoListNewTipoclientetipodocumento != null
							&& !oldIdTipodocumentoOfTipoclientetipodocumentoListNewTipoclientetipodocumento
									.equals(tipodocumento)) {
						oldIdTipodocumentoOfTipoclientetipodocumentoListNewTipoclientetipodocumento
								.getTipoclientetipodocumentoList().remove(
										tipoclientetipodocumentoListNewTipoclientetipodocumento);
						oldIdTipodocumentoOfTipoclientetipodocumentoListNewTipoclientetipodocumento = em
								.merge(oldIdTipodocumentoOfTipoclientetipodocumentoListNewTipoclientetipodocumento);
					}
				}
			}
			for (Categorialineaincluye categorialineaincluyeListNewCategorialineaincluye : categorialineaincluyeListNew) {
				if (!categorialineaincluyeListOld.contains(categorialineaincluyeListNewCategorialineaincluye)) {
					Tipodocumento oldIdTipodocumentoOfCategorialineaincluyeListNewCategorialineaincluye = categorialineaincluyeListNewCategorialineaincluye
							.getIdTipodocumento();
					categorialineaincluyeListNewCategorialineaincluye.setIdTipodocumento(tipodocumento);
					categorialineaincluyeListNewCategorialineaincluye = em
							.merge(categorialineaincluyeListNewCategorialineaincluye);
					if (oldIdTipodocumentoOfCategorialineaincluyeListNewCategorialineaincluye != null
							&& !oldIdTipodocumentoOfCategorialineaincluyeListNewCategorialineaincluye
									.equals(tipodocumento)) {
						oldIdTipodocumentoOfCategorialineaincluyeListNewCategorialineaincluye
								.getCategorialineaincluyeList().remove(
										categorialineaincluyeListNewCategorialineaincluye);
						oldIdTipodocumentoOfCategorialineaincluyeListNewCategorialineaincluye = em
								.merge(oldIdTipodocumentoOfCategorialineaincluyeListNewCategorialineaincluye);
					}
				}
			}
			for (Tipodocumento tipodocumentoListOldTipodocumento : tipodocumentoListOld) {
				if (!tipodocumentoListNew.contains(tipodocumentoListOldTipodocumento)) {
					tipodocumentoListOldTipodocumento.setIdContrapartida(null);
					tipodocumentoListOldTipodocumento = em.merge(tipodocumentoListOldTipodocumento);
				}
			}
			for (Tipodocumento tipodocumentoListNewTipodocumento : tipodocumentoListNew) {
				if (!tipodocumentoListOld.contains(tipodocumentoListNewTipodocumento)) {
					Tipodocumento oldIdContrapartidaOfTipodocumentoListNewTipodocumento = tipodocumentoListNewTipodocumento
							.getIdContrapartida();
					tipodocumentoListNewTipodocumento.setIdContrapartida(tipodocumento);
					tipodocumentoListNewTipodocumento = em.merge(tipodocumentoListNewTipodocumento);
					if (oldIdContrapartidaOfTipodocumentoListNewTipodocumento != null
							&& !oldIdContrapartidaOfTipodocumentoListNewTipodocumento.equals(tipodocumento)) {
						oldIdContrapartidaOfTipodocumentoListNewTipodocumento.getTipodocumentoList().remove(
								tipodocumentoListNewTipodocumento);
						oldIdContrapartidaOfTipodocumentoListNewTipodocumento = em
								.merge(oldIdContrapartidaOfTipodocumentoListNewTipodocumento);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = tipodocumento.getId();
				if (findTipodocumento(id) == null) {
					throw new NonexistentEntityException("The tipodocumento with id " + id + " no longer exists.");
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
			Tipodocumento tipodocumento;
			try {
				tipodocumento = em.getReference(Tipodocumento.class, id);
				tipodocumento.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The tipodocumento with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Transacciondocumento> transacciondocumentoListOrphanCheck = tipodocumento
					.getTransacciondocumentoList();
			for (Transacciondocumento transacciondocumentoListOrphanCheckTransacciondocumento : transacciondocumentoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Tipodocumento (" + tipodocumento
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
				illegalOrphanMessages.add("This Tipodocumento (" + tipodocumento
						+ ") cannot be destroyed since the Comprobantefiscalpreimpreso "
						+ comprobantefiscalpreimpresoListOrphanCheckComprobantefiscalpreimpreso
						+ " in its comprobantefiscalpreimpresoList field has a non-nullable idTipodocumento field.");
			}
			List<Tipoclientetipodocumento> tipoclientetipodocumentoListOrphanCheck = tipodocumento
					.getTipoclientetipodocumentoList();
			for (Tipoclientetipodocumento tipoclientetipodocumentoListOrphanCheckTipoclientetipodocumento : tipoclientetipodocumentoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Tipodocumento (" + tipodocumento
						+ ") cannot be destroyed since the Tipoclientetipodocumento "
						+ tipoclientetipodocumentoListOrphanCheckTipoclientetipodocumento
						+ " in its tipoclientetipodocumentoList field has a non-nullable idTipodocumento field.");
			}
			List<Categorialineaincluye> categorialineaincluyeListOrphanCheck = tipodocumento
					.getCategorialineaincluyeList();
			for (Categorialineaincluye categorialineaincluyeListOrphanCheckCategorialineaincluye : categorialineaincluyeListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Tipodocumento (" + tipodocumento
						+ ") cannot be destroyed since the Categorialineaincluye "
						+ categorialineaincluyeListOrphanCheckCategorialineaincluye
						+ " in its categorialineaincluyeList field has a non-nullable idTipodocumento field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Tipodocumento idContrapartida = tipodocumento.getIdContrapartida();
			if (idContrapartida != null) {
				idContrapartida.getTipodocumentoList().remove(tipodocumento);
				idContrapartida = em.merge(idContrapartida);
			}
			List<Tipodocumento> tipodocumentoList = tipodocumento.getTipodocumentoList();
			for (Tipodocumento tipodocumentoListTipodocumento : tipodocumentoList) {
				tipodocumentoListTipodocumento.setIdContrapartida(null);
				tipodocumentoListTipodocumento = em.merge(tipodocumentoListTipodocumento);
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
	public List<Tipodocumento> findTipodocumentoEntities(int maxResults, int firstResult) {
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
	private List<Tipodocumento> findTipodocumentoEntities(boolean all, int maxResults, int firstResult) {
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
