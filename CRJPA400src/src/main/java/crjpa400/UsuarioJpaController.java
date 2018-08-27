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
public class UsuarioJpaController implements Serializable {

	/**
	 * Constructor for UsuarioJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public UsuarioJpaController(EntityManagerFactory emf) {
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
	 * @param usuario
	 *            Usuario
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Usuario usuario) throws PreexistingEntityException,
			Exception {
		if (usuario.getRolloauditoriaList() == null) {
			usuario.setRolloauditoriaList(new ArrayList<Rolloauditoria>());
		}
		if (usuario.getEntregaList() == null) {
			usuario.setEntregaList(new ArrayList<Entrega>());
		}
		if (usuario.getTransaccionList() == null) {
			usuario.setTransaccionList(new ArrayList<Transaccion>());
		}
		if (usuario.getSesionList() == null) {
			usuario.setSesionList(new ArrayList<Sesion>());
		}
		if (usuario.getAbonoList() == null) {
			usuario.setAbonoList(new ArrayList<Abono>());
		}
		if (usuario.getUsuarioperfilList() == null) {
			usuario.setUsuarioperfilList(new ArrayList<Usuarioperfil>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Rolloauditoria> attachedRolloauditoriaList = new ArrayList<Rolloauditoria>();
			for (Rolloauditoria rolloauditoriaListRolloauditoriaToAttach : usuario
					.getRolloauditoriaList()) {
				rolloauditoriaListRolloauditoriaToAttach = em.getReference(
						rolloauditoriaListRolloauditoriaToAttach.getClass(),
						rolloauditoriaListRolloauditoriaToAttach.getId());
				attachedRolloauditoriaList
						.add(rolloauditoriaListRolloauditoriaToAttach);
			}
			usuario.setRolloauditoriaList(attachedRolloauditoriaList);
			List<Entrega> attachedEntregaList = new ArrayList<Entrega>();
			for (Entrega entregaListEntregaToAttach : usuario.getEntregaList()) {
				entregaListEntregaToAttach = em.getReference(
						entregaListEntregaToAttach.getClass(),
						entregaListEntregaToAttach.getId());
				attachedEntregaList.add(entregaListEntregaToAttach);
			}
			usuario.setEntregaList(attachedEntregaList);
			List<Transaccion> attachedTransaccionList = new ArrayList<Transaccion>();
			for (Transaccion transaccionListTransaccionToAttach : usuario
					.getTransaccionList()) {
				transaccionListTransaccionToAttach = em.getReference(
						transaccionListTransaccionToAttach.getClass(),
						transaccionListTransaccionToAttach.getId());
				attachedTransaccionList.add(transaccionListTransaccionToAttach);
			}
			usuario.setTransaccionList(attachedTransaccionList);
			List<Sesion> attachedSesionList = new ArrayList<Sesion>();
			for (Sesion sesionListSesionToAttach : usuario.getSesionList()) {
				sesionListSesionToAttach = em.getReference(
						sesionListSesionToAttach.getClass(),
						sesionListSesionToAttach.getId());
				attachedSesionList.add(sesionListSesionToAttach);
			}
			usuario.setSesionList(attachedSesionList);
			List<Abono> attachedAbonoList = new ArrayList<Abono>();
			for (Abono abonoListAbonoToAttach : usuario.getAbonoList()) {
				abonoListAbonoToAttach = em.getReference(
						abonoListAbonoToAttach.getClass(),
						abonoListAbonoToAttach.getId());
				attachedAbonoList.add(abonoListAbonoToAttach);
			}
			usuario.setAbonoList(attachedAbonoList);
			List<Usuarioperfil> attachedUsuarioperfilList = new ArrayList<Usuarioperfil>();
			for (Usuarioperfil usuarioperfilListUsuarioperfilToAttach : usuario
					.getUsuarioperfilList()) {
				usuarioperfilListUsuarioperfilToAttach = em.getReference(
						usuarioperfilListUsuarioperfilToAttach.getClass(),
						usuarioperfilListUsuarioperfilToAttach.getId());
				attachedUsuarioperfilList
						.add(usuarioperfilListUsuarioperfilToAttach);
			}
			usuario.setUsuarioperfilList(attachedUsuarioperfilList);
			em.persist(usuario);
			for (Rolloauditoria rolloauditoriaListRolloauditoria : usuario
					.getRolloauditoriaList()) {
				Usuario oldIdUsuarioOfRolloauditoriaListRolloauditoria = rolloauditoriaListRolloauditoria
						.getIdUsuario();
				rolloauditoriaListRolloauditoria.setIdUsuario(usuario);
				rolloauditoriaListRolloauditoria = em
						.merge(rolloauditoriaListRolloauditoria);
				if (oldIdUsuarioOfRolloauditoriaListRolloauditoria != null) {
					oldIdUsuarioOfRolloauditoriaListRolloauditoria
							.getRolloauditoriaList().remove(
									rolloauditoriaListRolloauditoria);
					oldIdUsuarioOfRolloauditoriaListRolloauditoria = em
							.merge(oldIdUsuarioOfRolloauditoriaListRolloauditoria);
				}
			}
			for (Entrega entregaListEntrega : usuario.getEntregaList()) {
				Usuario oldIdUsuarioautorizanteOfEntregaListEntrega = entregaListEntrega
						.getIdUsuarioautorizante();
				entregaListEntrega.setIdUsuarioautorizante(usuario);
				entregaListEntrega = em.merge(entregaListEntrega);
				if (oldIdUsuarioautorizanteOfEntregaListEntrega != null) {
					oldIdUsuarioautorizanteOfEntregaListEntrega
							.getEntregaList().remove(entregaListEntrega);
					oldIdUsuarioautorizanteOfEntregaListEntrega = em
							.merge(oldIdUsuarioautorizanteOfEntregaListEntrega);
				}
			}
			for (Transaccion transaccionListTransaccion : usuario
					.getTransaccionList()) {
				Usuario oldIdUsuarioOfTransaccionListTransaccion = transaccionListTransaccion
						.getIdUsuario();
				transaccionListTransaccion.setIdUsuario(usuario);
				transaccionListTransaccion = em
						.merge(transaccionListTransaccion);
				if (oldIdUsuarioOfTransaccionListTransaccion != null) {
					oldIdUsuarioOfTransaccionListTransaccion
							.getTransaccionList().remove(
									transaccionListTransaccion);
					oldIdUsuarioOfTransaccionListTransaccion = em
							.merge(oldIdUsuarioOfTransaccionListTransaccion);
				}
			}
			for (Sesion sesionListSesion : usuario.getSesionList()) {
				Usuario oldIdUsuarioOfSesionListSesion = sesionListSesion
						.getIdUsuario();
				sesionListSesion.setIdUsuario(usuario);
				sesionListSesion = em.merge(sesionListSesion);
				if (oldIdUsuarioOfSesionListSesion != null) {
					oldIdUsuarioOfSesionListSesion.getSesionList().remove(
							sesionListSesion);
					oldIdUsuarioOfSesionListSesion = em
							.merge(oldIdUsuarioOfSesionListSesion);
				}
			}
			for (Abono abonoListAbono : usuario.getAbonoList()) {
				Usuario oldIdUsuarioOfAbonoListAbono = abonoListAbono
						.getIdUsuario();
				abonoListAbono.setIdUsuario(usuario);
				abonoListAbono = em.merge(abonoListAbono);
				if (oldIdUsuarioOfAbonoListAbono != null) {
					oldIdUsuarioOfAbonoListAbono.getAbonoList().remove(
							abonoListAbono);
					oldIdUsuarioOfAbonoListAbono = em
							.merge(oldIdUsuarioOfAbonoListAbono);
				}
			}
			for (Usuarioperfil usuarioperfilListUsuarioperfil : usuario
					.getUsuarioperfilList()) {
				Usuario oldIdUsuarioOfUsuarioperfilListUsuarioperfil = usuarioperfilListUsuarioperfil
						.getIdUsuario();
				usuarioperfilListUsuarioperfil.setIdUsuario(usuario);
				usuarioperfilListUsuarioperfil = em
						.merge(usuarioperfilListUsuarioperfil);
				if (oldIdUsuarioOfUsuarioperfilListUsuarioperfil != null) {
					oldIdUsuarioOfUsuarioperfilListUsuarioperfil
							.getUsuarioperfilList().remove(
									usuarioperfilListUsuarioperfil);
					oldIdUsuarioOfUsuarioperfilListUsuarioperfil = em
							.merge(oldIdUsuarioOfUsuarioperfilListUsuarioperfil);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findUsuario(usuario.getId()) != null) {
				throw new PreexistingEntityException("Usuario " + usuario
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
	 * @param usuario
	 *            Usuario
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Usuario usuario) throws IllegalOrphanException,
			NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
			List<Rolloauditoria> rolloauditoriaListOld = persistentUsuario
					.getRolloauditoriaList();
			List<Rolloauditoria> rolloauditoriaListNew = usuario
					.getRolloauditoriaList();
			List<Entrega> entregaListOld = persistentUsuario.getEntregaList();
			List<Entrega> entregaListNew = usuario.getEntregaList();
			List<Transaccion> transaccionListOld = persistentUsuario
					.getTransaccionList();
			List<Transaccion> transaccionListNew = usuario.getTransaccionList();
			List<Sesion> sesionListOld = persistentUsuario.getSesionList();
			List<Sesion> sesionListNew = usuario.getSesionList();
			List<Abono> abonoListOld = persistentUsuario.getAbonoList();
			List<Abono> abonoListNew = usuario.getAbonoList();
			List<Usuarioperfil> usuarioperfilListOld = persistentUsuario
					.getUsuarioperfilList();
			List<Usuarioperfil> usuarioperfilListNew = usuario
					.getUsuarioperfilList();
			List<String> illegalOrphanMessages = null;
			for (Rolloauditoria rolloauditoriaListOldRolloauditoria : rolloauditoriaListOld) {
				if (!rolloauditoriaListNew
						.contains(rolloauditoriaListOldRolloauditoria)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Rolloauditoria "
							+ rolloauditoriaListOldRolloauditoria
							+ " since its idUsuario field is not nullable.");
				}
			}
			for (Entrega entregaListOldEntrega : entregaListOld) {
				if (!entregaListNew.contains(entregaListOldEntrega)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Entrega "
									+ entregaListOldEntrega
									+ " since its idUsuarioautorizante field is not nullable.");
				}
			}
			for (Transaccion transaccionListOldTransaccion : transaccionListOld) {
				if (!transaccionListNew.contains(transaccionListOldTransaccion)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Transaccion "
							+ transaccionListOldTransaccion
							+ " since its idUsuario field is not nullable.");
				}
			}
			for (Sesion sesionListOldSesion : sesionListOld) {
				if (!sesionListNew.contains(sesionListOldSesion)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Sesion "
							+ sesionListOldSesion
							+ " since its idUsuario field is not nullable.");
				}
			}
			for (Abono abonoListOldAbono : abonoListOld) {
				if (!abonoListNew.contains(abonoListOldAbono)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Abono "
							+ abonoListOldAbono
							+ " since its idUsuario field is not nullable.");
				}
			}
			for (Usuarioperfil usuarioperfilListOldUsuarioperfil : usuarioperfilListOld) {
				if (!usuarioperfilListNew
						.contains(usuarioperfilListOldUsuarioperfil)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Usuarioperfil "
							+ usuarioperfilListOldUsuarioperfil
							+ " since its idUsuario field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Rolloauditoria> attachedRolloauditoriaListNew = new ArrayList<Rolloauditoria>();
			for (Rolloauditoria rolloauditoriaListNewRolloauditoriaToAttach : rolloauditoriaListNew) {
				rolloauditoriaListNewRolloauditoriaToAttach = em.getReference(
						rolloauditoriaListNewRolloauditoriaToAttach.getClass(),
						rolloauditoriaListNewRolloauditoriaToAttach.getId());
				attachedRolloauditoriaListNew
						.add(rolloauditoriaListNewRolloauditoriaToAttach);
			}
			rolloauditoriaListNew = attachedRolloauditoriaListNew;
			usuario.setRolloauditoriaList(rolloauditoriaListNew);
			List<Entrega> attachedEntregaListNew = new ArrayList<Entrega>();
			for (Entrega entregaListNewEntregaToAttach : entregaListNew) {
				entregaListNewEntregaToAttach = em.getReference(
						entregaListNewEntregaToAttach.getClass(),
						entregaListNewEntregaToAttach.getId());
				attachedEntregaListNew.add(entregaListNewEntregaToAttach);
			}
			entregaListNew = attachedEntregaListNew;
			usuario.setEntregaList(entregaListNew);
			List<Transaccion> attachedTransaccionListNew = new ArrayList<Transaccion>();
			for (Transaccion transaccionListNewTransaccionToAttach : transaccionListNew) {
				transaccionListNewTransaccionToAttach = em.getReference(
						transaccionListNewTransaccionToAttach.getClass(),
						transaccionListNewTransaccionToAttach.getId());
				attachedTransaccionListNew
						.add(transaccionListNewTransaccionToAttach);
			}
			transaccionListNew = attachedTransaccionListNew;
			usuario.setTransaccionList(transaccionListNew);
			List<Sesion> attachedSesionListNew = new ArrayList<Sesion>();
			for (Sesion sesionListNewSesionToAttach : sesionListNew) {
				sesionListNewSesionToAttach = em.getReference(
						sesionListNewSesionToAttach.getClass(),
						sesionListNewSesionToAttach.getId());
				attachedSesionListNew.add(sesionListNewSesionToAttach);
			}
			sesionListNew = attachedSesionListNew;
			usuario.setSesionList(sesionListNew);
			List<Abono> attachedAbonoListNew = new ArrayList<Abono>();
			for (Abono abonoListNewAbonoToAttach : abonoListNew) {
				abonoListNewAbonoToAttach = em.getReference(
						abonoListNewAbonoToAttach.getClass(),
						abonoListNewAbonoToAttach.getId());
				attachedAbonoListNew.add(abonoListNewAbonoToAttach);
			}
			abonoListNew = attachedAbonoListNew;
			usuario.setAbonoList(abonoListNew);
			List<Usuarioperfil> attachedUsuarioperfilListNew = new ArrayList<Usuarioperfil>();
			for (Usuarioperfil usuarioperfilListNewUsuarioperfilToAttach : usuarioperfilListNew) {
				usuarioperfilListNewUsuarioperfilToAttach = em.getReference(
						usuarioperfilListNewUsuarioperfilToAttach.getClass(),
						usuarioperfilListNewUsuarioperfilToAttach.getId());
				attachedUsuarioperfilListNew
						.add(usuarioperfilListNewUsuarioperfilToAttach);
			}
			usuarioperfilListNew = attachedUsuarioperfilListNew;
			usuario.setUsuarioperfilList(usuarioperfilListNew);
			usuario = em.merge(usuario);
			for (Rolloauditoria rolloauditoriaListNewRolloauditoria : rolloauditoriaListNew) {
				if (!rolloauditoriaListOld
						.contains(rolloauditoriaListNewRolloauditoria)) {
					Usuario oldIdUsuarioOfRolloauditoriaListNewRolloauditoria = rolloauditoriaListNewRolloauditoria
							.getIdUsuario();
					rolloauditoriaListNewRolloauditoria.setIdUsuario(usuario);
					rolloauditoriaListNewRolloauditoria = em
							.merge(rolloauditoriaListNewRolloauditoria);
					if (oldIdUsuarioOfRolloauditoriaListNewRolloauditoria != null
							&& !oldIdUsuarioOfRolloauditoriaListNewRolloauditoria
									.equals(usuario)) {
						oldIdUsuarioOfRolloauditoriaListNewRolloauditoria
								.getRolloauditoriaList().remove(
										rolloauditoriaListNewRolloauditoria);
						oldIdUsuarioOfRolloauditoriaListNewRolloauditoria = em
								.merge(oldIdUsuarioOfRolloauditoriaListNewRolloauditoria);
					}
				}
			}
			for (Entrega entregaListNewEntrega : entregaListNew) {
				if (!entregaListOld.contains(entregaListNewEntrega)) {
					Usuario oldIdUsuarioautorizanteOfEntregaListNewEntrega = entregaListNewEntrega
							.getIdUsuarioautorizante();
					entregaListNewEntrega.setIdUsuarioautorizante(usuario);
					entregaListNewEntrega = em.merge(entregaListNewEntrega);
					if (oldIdUsuarioautorizanteOfEntregaListNewEntrega != null
							&& !oldIdUsuarioautorizanteOfEntregaListNewEntrega
									.equals(usuario)) {
						oldIdUsuarioautorizanteOfEntregaListNewEntrega
								.getEntregaList().remove(entregaListNewEntrega);
						oldIdUsuarioautorizanteOfEntregaListNewEntrega = em
								.merge(oldIdUsuarioautorizanteOfEntregaListNewEntrega);
					}
				}
			}
			for (Transaccion transaccionListNewTransaccion : transaccionListNew) {
				if (!transaccionListOld.contains(transaccionListNewTransaccion)) {
					Usuario oldIdUsuarioOfTransaccionListNewTransaccion = transaccionListNewTransaccion
							.getIdUsuario();
					transaccionListNewTransaccion.setIdUsuario(usuario);
					transaccionListNewTransaccion = em
							.merge(transaccionListNewTransaccion);
					if (oldIdUsuarioOfTransaccionListNewTransaccion != null
							&& !oldIdUsuarioOfTransaccionListNewTransaccion
									.equals(usuario)) {
						oldIdUsuarioOfTransaccionListNewTransaccion
								.getTransaccionList().remove(
										transaccionListNewTransaccion);
						oldIdUsuarioOfTransaccionListNewTransaccion = em
								.merge(oldIdUsuarioOfTransaccionListNewTransaccion);
					}
				}
			}
			for (Sesion sesionListNewSesion : sesionListNew) {
				if (!sesionListOld.contains(sesionListNewSesion)) {
					Usuario oldIdUsuarioOfSesionListNewSesion = sesionListNewSesion
							.getIdUsuario();
					sesionListNewSesion.setIdUsuario(usuario);
					sesionListNewSesion = em.merge(sesionListNewSesion);
					if (oldIdUsuarioOfSesionListNewSesion != null
							&& !oldIdUsuarioOfSesionListNewSesion
									.equals(usuario)) {
						oldIdUsuarioOfSesionListNewSesion.getSesionList()
								.remove(sesionListNewSesion);
						oldIdUsuarioOfSesionListNewSesion = em
								.merge(oldIdUsuarioOfSesionListNewSesion);
					}
				}
			}
			for (Abono abonoListNewAbono : abonoListNew) {
				if (!abonoListOld.contains(abonoListNewAbono)) {
					Usuario oldIdUsuarioOfAbonoListNewAbono = abonoListNewAbono
							.getIdUsuario();
					abonoListNewAbono.setIdUsuario(usuario);
					abonoListNewAbono = em.merge(abonoListNewAbono);
					if (oldIdUsuarioOfAbonoListNewAbono != null
							&& !oldIdUsuarioOfAbonoListNewAbono.equals(usuario)) {
						oldIdUsuarioOfAbonoListNewAbono.getAbonoList().remove(
								abonoListNewAbono);
						oldIdUsuarioOfAbonoListNewAbono = em
								.merge(oldIdUsuarioOfAbonoListNewAbono);
					}
				}
			}
			for (Usuarioperfil usuarioperfilListNewUsuarioperfil : usuarioperfilListNew) {
				if (!usuarioperfilListOld
						.contains(usuarioperfilListNewUsuarioperfil)) {
					Usuario oldIdUsuarioOfUsuarioperfilListNewUsuarioperfil = usuarioperfilListNewUsuarioperfil
							.getIdUsuario();
					usuarioperfilListNewUsuarioperfil.setIdUsuario(usuario);
					usuarioperfilListNewUsuarioperfil = em
							.merge(usuarioperfilListNewUsuarioperfil);
					if (oldIdUsuarioOfUsuarioperfilListNewUsuarioperfil != null
							&& !oldIdUsuarioOfUsuarioperfilListNewUsuarioperfil
									.equals(usuario)) {
						oldIdUsuarioOfUsuarioperfilListNewUsuarioperfil
								.getUsuarioperfilList().remove(
										usuarioperfilListNewUsuarioperfil);
						oldIdUsuarioOfUsuarioperfilListNewUsuarioperfil = em
								.merge(oldIdUsuarioOfUsuarioperfilListNewUsuarioperfil);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = usuario.getId();
				if (findUsuario(id) == null) {
					throw new NonexistentEntityException("The usuario with id "
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
			Usuario usuario;
			try {
				usuario = em.getReference(Usuario.class, id);
				usuario.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The usuario with id "
						+ id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Rolloauditoria> rolloauditoriaListOrphanCheck = usuario
					.getRolloauditoriaList();
			for (Rolloauditoria rolloauditoriaListOrphanCheckRolloauditoria : rolloauditoriaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Usuario ("
								+ usuario
								+ ") cannot be destroyed since the Rolloauditoria "
								+ rolloauditoriaListOrphanCheckRolloauditoria
								+ " in its rolloauditoriaList field has a non-nullable idUsuario field.");
			}
			List<Entrega> entregaListOrphanCheck = usuario.getEntregaList();
			for (Entrega entregaListOrphanCheckEntrega : entregaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Usuario ("
								+ usuario
								+ ") cannot be destroyed since the Entrega "
								+ entregaListOrphanCheckEntrega
								+ " in its entregaList field has a non-nullable idUsuarioautorizante field.");
			}
			List<Transaccion> transaccionListOrphanCheck = usuario
					.getTransaccionList();
			for (Transaccion transaccionListOrphanCheckTransaccion : transaccionListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Usuario ("
								+ usuario
								+ ") cannot be destroyed since the Transaccion "
								+ transaccionListOrphanCheckTransaccion
								+ " in its transaccionList field has a non-nullable idUsuario field.");
			}
			List<Sesion> sesionListOrphanCheck = usuario.getSesionList();
			for (Sesion sesionListOrphanCheckSesion : sesionListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Usuario ("
								+ usuario
								+ ") cannot be destroyed since the Sesion "
								+ sesionListOrphanCheckSesion
								+ " in its sesionList field has a non-nullable idUsuario field.");
			}
			List<Abono> abonoListOrphanCheck = usuario.getAbonoList();
			for (Abono abonoListOrphanCheckAbono : abonoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Usuario ("
								+ usuario
								+ ") cannot be destroyed since the Abono "
								+ abonoListOrphanCheckAbono
								+ " in its abonoList field has a non-nullable idUsuario field.");
			}
			List<Usuarioperfil> usuarioperfilListOrphanCheck = usuario
					.getUsuarioperfilList();
			for (Usuarioperfil usuarioperfilListOrphanCheckUsuarioperfil : usuarioperfilListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Usuario ("
								+ usuario
								+ ") cannot be destroyed since the Usuarioperfil "
								+ usuarioperfilListOrphanCheckUsuarioperfil
								+ " in its usuarioperfilList field has a non-nullable idUsuario field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(usuario);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findUsuarioEntities.
	 * 
	 * @return List<Usuario>
	 */
	public List<Usuario> findUsuarioEntities() {
		return findUsuarioEntities(true, -1, -1);
	}

	/**
	 * Method findUsuarioEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Usuario>
	 */
	public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
		return findUsuarioEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findUsuarioEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Usuario>
	 */
	private List<Usuario> findUsuarioEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Usuario.class));
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
	 * Method findUsuario.
	 * 
	 * @param id
	 *            Long
	 * @return Usuario
	 */
	public Usuario findUsuario(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Usuario.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getUsuarioCount.
	 * 
	 * @return int
	 */
	public int getUsuarioCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Usuario> rt = cq.from(Usuario.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
