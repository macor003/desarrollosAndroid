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
public class ClienteJpaController implements Serializable {

	/**
	 * Constructor for ClienteJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ClienteJpaController(EntityManagerFactory emf) {
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
	 * @param cliente
	 *            Cliente
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Cliente cliente) throws PreexistingEntityException, Exception {
		if (cliente.getTransaccionclienteList() == null) {
			cliente.setTransaccionclienteList(new ArrayList<Transaccioncliente>());
		}
		if (cliente.getTransaccionList() == null) {
			cliente.setTransaccionList(new ArrayList<Transaccion>());
		}
		if (cliente.getMorosidadList() == null) {
			cliente.setMorosidadList(new ArrayList<Morosidad>());
		}
		if (cliente.getClientemensajeList() == null) {
			cliente.setClientemensajeList(new ArrayList<Clientemensaje>());
		}
		if (cliente.getCuentacreditoList() == null) {
			cliente.setCuentacreditoList(new ArrayList<Cuentacredito>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Exoneradotipo idExoneradotipo = cliente.getIdExoneradotipo();
			if (idExoneradotipo != null) {
				idExoneradotipo = em.getReference(idExoneradotipo.getClass(), idExoneradotipo.getId());
				cliente.setIdExoneradotipo(idExoneradotipo);
			}
			Tipocliente idTipocliente = cliente.getIdTipocliente();
			if (idTipocliente != null) {
				idTipocliente = em.getReference(idTipocliente.getClass(), idTipocliente.getId());
				cliente.setIdTipocliente(idTipocliente);
			}
			Giroactividadeconomica idGiroactividadeconomica = cliente.getIdGiroactividadeconomica();
			if (idGiroactividadeconomica != null) {
				idGiroactividadeconomica = em.getReference(idGiroactividadeconomica.getClass(),
						idGiroactividadeconomica.getId());
				cliente.setIdGiroactividadeconomica(idGiroactividadeconomica);
			}
			List<Transaccioncliente> attachedTransaccionclienteList = new ArrayList<Transaccioncliente>();
			for (Transaccioncliente transaccionclienteListTransaccionclienteToAttach : cliente
					.getTransaccionclienteList()) {
				transaccionclienteListTransaccionclienteToAttach = em.getReference(
						transaccionclienteListTransaccionclienteToAttach.getClass(),
						transaccionclienteListTransaccionclienteToAttach.getId());
				attachedTransaccionclienteList.add(transaccionclienteListTransaccionclienteToAttach);
			}
			cliente.setTransaccionclienteList(attachedTransaccionclienteList);
			List<Transaccion> attachedTransaccionList = new ArrayList<Transaccion>();
			for (Transaccion transaccionListTransaccionToAttach : cliente.getTransaccionList()) {
				transaccionListTransaccionToAttach = em.getReference(transaccionListTransaccionToAttach.getClass(),
						transaccionListTransaccionToAttach.getId());
				attachedTransaccionList.add(transaccionListTransaccionToAttach);
			}
			cliente.setTransaccionList(attachedTransaccionList);
			List<Morosidad> attachedMorosidadList = new ArrayList<Morosidad>();
			for (Morosidad morosidadListMorosidadToAttach : cliente.getMorosidadList()) {
				morosidadListMorosidadToAttach = em.getReference(morosidadListMorosidadToAttach.getClass(),
						morosidadListMorosidadToAttach.getId());
				attachedMorosidadList.add(morosidadListMorosidadToAttach);
			}
			cliente.setMorosidadList(attachedMorosidadList);
			List<Clientemensaje> attachedClientemensajeList = new ArrayList<Clientemensaje>();
			for (Clientemensaje clientemensajeListClientemensajeToAttach : cliente.getClientemensajeList()) {
				clientemensajeListClientemensajeToAttach = em.getReference(
						clientemensajeListClientemensajeToAttach.getClass(),
						clientemensajeListClientemensajeToAttach.getId());
				attachedClientemensajeList.add(clientemensajeListClientemensajeToAttach);
			}
			cliente.setClientemensajeList(attachedClientemensajeList);
			List<Cuentacredito> attachedCuentacreditoList = new ArrayList<Cuentacredito>();
			for (Cuentacredito cuentacreditoListCuentacreditoToAttach : cliente.getCuentacreditoList()) {
				cuentacreditoListCuentacreditoToAttach = em.getReference(
						cuentacreditoListCuentacreditoToAttach.getClass(),
						cuentacreditoListCuentacreditoToAttach.getId());
				attachedCuentacreditoList.add(cuentacreditoListCuentacreditoToAttach);
			}
			cliente.setCuentacreditoList(attachedCuentacreditoList);
			em.persist(cliente);
			if (idExoneradotipo != null) {
				idExoneradotipo.getClienteList().add(cliente);
				idExoneradotipo = em.merge(idExoneradotipo);
			}
			// if (idTipocliente != null) {
			// idTipocliente.getClienteList().add(cliente);
			// idTipocliente = em.merge(idTipocliente);
			// }
			if (idGiroactividadeconomica != null) {
				idGiroactividadeconomica.getClienteList().add(cliente);
				idGiroactividadeconomica = em.merge(idGiroactividadeconomica);
			}
			for (Transaccioncliente transaccionclienteListTransaccioncliente : cliente.getTransaccionclienteList()) {
				Cliente oldNumeroIdentificacionClienteOfTransaccionclienteListTransaccioncliente = transaccionclienteListTransaccioncliente
						.getNumeroIdentificacionCliente();
				transaccionclienteListTransaccioncliente.setNumeroIdentificacionCliente(cliente);
				transaccionclienteListTransaccioncliente = em.merge(transaccionclienteListTransaccioncliente);
				if (oldNumeroIdentificacionClienteOfTransaccionclienteListTransaccioncliente != null) {
					oldNumeroIdentificacionClienteOfTransaccionclienteListTransaccioncliente
							.getTransaccionclienteList().remove(transaccionclienteListTransaccioncliente);
					oldNumeroIdentificacionClienteOfTransaccionclienteListTransaccioncliente = em
							.merge(oldNumeroIdentificacionClienteOfTransaccionclienteListTransaccioncliente);
				}
			}
			for (Transaccion transaccionListTransaccion : cliente.getTransaccionList()) {
				Cliente oldNumeroIdentificacionClienteOfTransaccionListTransaccion = transaccionListTransaccion
						.getNumeroIdentificacionCliente();
				transaccionListTransaccion.setNumeroIdentificacionCliente(cliente);
				transaccionListTransaccion = em.merge(transaccionListTransaccion);
				if (oldNumeroIdentificacionClienteOfTransaccionListTransaccion != null) {
					oldNumeroIdentificacionClienteOfTransaccionListTransaccion.getTransaccionList().remove(
							transaccionListTransaccion);
					oldNumeroIdentificacionClienteOfTransaccionListTransaccion = em
							.merge(oldNumeroIdentificacionClienteOfTransaccionListTransaccion);
				}
			}
			for (Morosidad morosidadListMorosidad : cliente.getMorosidadList()) {
				Cliente oldNumeroIdentificacionClienteOfMorosidadListMorosidad = morosidadListMorosidad
						.getNumeroIdentificacionCliente();
				morosidadListMorosidad.setNumeroIdentificacionCliente(cliente);
				morosidadListMorosidad = em.merge(morosidadListMorosidad);
				if (oldNumeroIdentificacionClienteOfMorosidadListMorosidad != null) {
					oldNumeroIdentificacionClienteOfMorosidadListMorosidad.getMorosidadList().remove(
							morosidadListMorosidad);
					oldNumeroIdentificacionClienteOfMorosidadListMorosidad = em
							.merge(oldNumeroIdentificacionClienteOfMorosidadListMorosidad);
				}
			}
			for (Clientemensaje clientemensajeListClientemensaje : cliente.getClientemensajeList()) {
				Cliente oldNumeroIdentificacionClienteOfClientemensajeListClientemensaje = clientemensajeListClientemensaje
						.getNumeroIdentificacionCliente();
				clientemensajeListClientemensaje.setNumeroIdentificacionCliente(cliente);
				clientemensajeListClientemensaje = em.merge(clientemensajeListClientemensaje);
				if (oldNumeroIdentificacionClienteOfClientemensajeListClientemensaje != null) {
					oldNumeroIdentificacionClienteOfClientemensajeListClientemensaje.getClientemensajeList().remove(
							clientemensajeListClientemensaje);
					oldNumeroIdentificacionClienteOfClientemensajeListClientemensaje = em
							.merge(oldNumeroIdentificacionClienteOfClientemensajeListClientemensaje);
				}
			}
			for (Cuentacredito cuentacreditoListCuentacredito : cliente.getCuentacreditoList()) {
				Cliente oldNumeroIdentificacionClienteOfCuentacreditoListCuentacredito = cuentacreditoListCuentacredito
						.getNumeroIdentificacionCliente();
				cuentacreditoListCuentacredito.setNumeroIdentificacionCliente(cliente);
				cuentacreditoListCuentacredito = em.merge(cuentacreditoListCuentacredito);
				if (oldNumeroIdentificacionClienteOfCuentacreditoListCuentacredito != null) {
					oldNumeroIdentificacionClienteOfCuentacreditoListCuentacredito.getCuentacreditoList().remove(
							cuentacreditoListCuentacredito);
					oldNumeroIdentificacionClienteOfCuentacreditoListCuentacredito = em
							.merge(oldNumeroIdentificacionClienteOfCuentacreditoListCuentacredito);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findCliente(cliente.getNumeroIdentificacionCliente()) != null) {
				throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
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
	 * @param cliente
	 *            Cliente
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Cliente persistentCliente = em.find(Cliente.class, cliente.getNumeroIdentificacionCliente());
			Exoneradotipo idExoneradotipoOld = persistentCliente.getIdExoneradotipo();
			Exoneradotipo idExoneradotipoNew = cliente.getIdExoneradotipo();
			Tipocliente idTipoclienteOld = persistentCliente.getIdTipocliente();
			Tipocliente idTipoclienteNew = cliente.getIdTipocliente();
			Giroactividadeconomica idGiroactividadeconomicaOld = persistentCliente.getIdGiroactividadeconomica();
			Giroactividadeconomica idGiroactividadeconomicaNew = cliente.getIdGiroactividadeconomica();
			List<Transaccioncliente> transaccionclienteListOld = persistentCliente.getTransaccionclienteList();
			List<Transaccioncliente> transaccionclienteListNew = cliente.getTransaccionclienteList();
			List<Transaccion> transaccionListOld = persistentCliente.getTransaccionList();
			List<Transaccion> transaccionListNew = cliente.getTransaccionList();
			List<Morosidad> morosidadListOld = persistentCliente.getMorosidadList();
			List<Morosidad> morosidadListNew = cliente.getMorosidadList();
			List<Clientemensaje> clientemensajeListOld = persistentCliente.getClientemensajeList();
			List<Clientemensaje> clientemensajeListNew = cliente.getClientemensajeList();
			List<Cuentacredito> cuentacreditoListOld = persistentCliente.getCuentacreditoList();
			List<Cuentacredito> cuentacreditoListNew = cliente.getCuentacreditoList();
			List<String> illegalOrphanMessages = null;
			for (Transaccioncliente transaccionclienteListOldTransaccioncliente : transaccionclienteListOld) {
				if (!transaccionclienteListNew.contains(transaccionclienteListOldTransaccioncliente)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Transaccioncliente "
							+ transaccionclienteListOldTransaccioncliente
							+ " since its numeroIdentificacionCliente field is not nullable.");
				}
			}
			for (Morosidad morosidadListOldMorosidad : morosidadListOld) {
				if (!morosidadListNew.contains(morosidadListOldMorosidad)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Morosidad " + morosidadListOldMorosidad
							+ " since its numeroIdentificacionCliente field is not nullable.");
				}
			}
			for (Clientemensaje clientemensajeListOldClientemensaje : clientemensajeListOld) {
				if (!clientemensajeListNew.contains(clientemensajeListOldClientemensaje)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Clientemensaje " + clientemensajeListOldClientemensaje
							+ " since its numeroIdentificacionCliente field is not nullable.");
				}
			}
			for (Cuentacredito cuentacreditoListOldCuentacredito : cuentacreditoListOld) {
				if (!cuentacreditoListNew.contains(cuentacreditoListOldCuentacredito)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Cuentacredito " + cuentacreditoListOldCuentacredito
							+ " since its numeroIdentificacionCliente field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idExoneradotipoNew != null) {
				idExoneradotipoNew = em.getReference(idExoneradotipoNew.getClass(), idExoneradotipoNew.getId());
				cliente.setIdExoneradotipo(idExoneradotipoNew);
			}
			if (idTipoclienteNew != null) {
				idTipoclienteNew = em.getReference(idTipoclienteNew.getClass(), idTipoclienteNew.getId());
				cliente.setIdTipocliente(idTipoclienteNew);
			}
			if (idGiroactividadeconomicaNew != null) {
				idGiroactividadeconomicaNew = em.getReference(idGiroactividadeconomicaNew.getClass(),
						idGiroactividadeconomicaNew.getId());
				cliente.setIdGiroactividadeconomica(idGiroactividadeconomicaNew);
			}
			List<Transaccioncliente> attachedTransaccionclienteListNew = new ArrayList<Transaccioncliente>();
			for (Transaccioncliente transaccionclienteListNewTransaccionclienteToAttach : transaccionclienteListNew) {
				transaccionclienteListNewTransaccionclienteToAttach = em.getReference(
						transaccionclienteListNewTransaccionclienteToAttach.getClass(),
						transaccionclienteListNewTransaccionclienteToAttach.getId());
				attachedTransaccionclienteListNew.add(transaccionclienteListNewTransaccionclienteToAttach);
			}
			transaccionclienteListNew = attachedTransaccionclienteListNew;
			cliente.setTransaccionclienteList(transaccionclienteListNew);
			List<Transaccion> attachedTransaccionListNew = new ArrayList<Transaccion>();
			for (Transaccion transaccionListNewTransaccionToAttach : transaccionListNew) {
				transaccionListNewTransaccionToAttach = em
						.getReference(transaccionListNewTransaccionToAttach.getClass(),
								transaccionListNewTransaccionToAttach.getId());
				attachedTransaccionListNew.add(transaccionListNewTransaccionToAttach);
			}
			transaccionListNew = attachedTransaccionListNew;
			cliente.setTransaccionList(transaccionListNew);
			List<Morosidad> attachedMorosidadListNew = new ArrayList<Morosidad>();
			for (Morosidad morosidadListNewMorosidadToAttach : morosidadListNew) {
				morosidadListNewMorosidadToAttach = em.getReference(morosidadListNewMorosidadToAttach.getClass(),
						morosidadListNewMorosidadToAttach.getId());
				attachedMorosidadListNew.add(morosidadListNewMorosidadToAttach);
			}
			morosidadListNew = attachedMorosidadListNew;
			cliente.setMorosidadList(morosidadListNew);
			List<Clientemensaje> attachedClientemensajeListNew = new ArrayList<Clientemensaje>();
			for (Clientemensaje clientemensajeListNewClientemensajeToAttach : clientemensajeListNew) {
				clientemensajeListNewClientemensajeToAttach = em.getReference(
						clientemensajeListNewClientemensajeToAttach.getClass(),
						clientemensajeListNewClientemensajeToAttach.getId());
				attachedClientemensajeListNew.add(clientemensajeListNewClientemensajeToAttach);
			}
			clientemensajeListNew = attachedClientemensajeListNew;
			cliente.setClientemensajeList(clientemensajeListNew);
			List<Cuentacredito> attachedCuentacreditoListNew = new ArrayList<Cuentacredito>();
			for (Cuentacredito cuentacreditoListNewCuentacreditoToAttach : cuentacreditoListNew) {
				cuentacreditoListNewCuentacreditoToAttach = em.getReference(
						cuentacreditoListNewCuentacreditoToAttach.getClass(),
						cuentacreditoListNewCuentacreditoToAttach.getId());
				attachedCuentacreditoListNew.add(cuentacreditoListNewCuentacreditoToAttach);
			}
			cuentacreditoListNew = attachedCuentacreditoListNew;
			cliente.setCuentacreditoList(cuentacreditoListNew);
			cliente = em.merge(cliente);
			if (idExoneradotipoOld != null && !idExoneradotipoOld.equals(idExoneradotipoNew)) {
				idExoneradotipoOld.getClienteList().remove(cliente);
				idExoneradotipoOld = em.merge(idExoneradotipoOld);
			}
			if (idExoneradotipoNew != null && !idExoneradotipoNew.equals(idExoneradotipoOld)) {
				idExoneradotipoNew.getClienteList().add(cliente);
				idExoneradotipoNew = em.merge(idExoneradotipoNew);
			}
			// if (idTipoclienteOld != null &&
			// !idTipoclienteOld.equals(idTipoclienteNew)) {
			// idTipoclienteOld.getClienteList().remove(cliente);
			// idTipoclienteOld = em.merge(idTipoclienteOld);
			// }
			// if (idTipoclienteNew != null &&
			// !idTipoclienteNew.equals(idTipoclienteOld)) {
			// idTipoclienteNew.getClienteList().add(cliente);
			// idTipoclienteNew = em.merge(idTipoclienteNew);
			// }
			if (idGiroactividadeconomicaOld != null && !idGiroactividadeconomicaOld.equals(idGiroactividadeconomicaNew)) {
				idGiroactividadeconomicaOld.getClienteList().remove(cliente);
				idGiroactividadeconomicaOld = em.merge(idGiroactividadeconomicaOld);
			}
			if (idGiroactividadeconomicaNew != null && !idGiroactividadeconomicaNew.equals(idGiroactividadeconomicaOld)) {
				idGiroactividadeconomicaNew.getClienteList().add(cliente);
				idGiroactividadeconomicaNew = em.merge(idGiroactividadeconomicaNew);
			}
			for (Transaccioncliente transaccionclienteListNewTransaccioncliente : transaccionclienteListNew) {
				if (!transaccionclienteListOld.contains(transaccionclienteListNewTransaccioncliente)) {
					Cliente oldNumeroIdentificacionClienteOfTransaccionclienteListNewTransaccioncliente = transaccionclienteListNewTransaccioncliente
							.getNumeroIdentificacionCliente();
					transaccionclienteListNewTransaccioncliente.setNumeroIdentificacionCliente(cliente);
					transaccionclienteListNewTransaccioncliente = em.merge(transaccionclienteListNewTransaccioncliente);
					if (oldNumeroIdentificacionClienteOfTransaccionclienteListNewTransaccioncliente != null
							&& !oldNumeroIdentificacionClienteOfTransaccionclienteListNewTransaccioncliente
									.equals(cliente)) {
						oldNumeroIdentificacionClienteOfTransaccionclienteListNewTransaccioncliente
								.getTransaccionclienteList().remove(transaccionclienteListNewTransaccioncliente);
						oldNumeroIdentificacionClienteOfTransaccionclienteListNewTransaccioncliente = em
								.merge(oldNumeroIdentificacionClienteOfTransaccionclienteListNewTransaccioncliente);
					}
				}
			}
			for (Transaccion transaccionListOldTransaccion : transaccionListOld) {
				if (!transaccionListNew.contains(transaccionListOldTransaccion)) {
					transaccionListOldTransaccion.setNumeroIdentificacionCliente(null);
					transaccionListOldTransaccion = em.merge(transaccionListOldTransaccion);
				}
			}
			for (Transaccion transaccionListNewTransaccion : transaccionListNew) {
				if (!transaccionListOld.contains(transaccionListNewTransaccion)) {
					Cliente oldNumeroIdentificacionClienteOfTransaccionListNewTransaccion = transaccionListNewTransaccion
							.getNumeroIdentificacionCliente();
					transaccionListNewTransaccion.setNumeroIdentificacionCliente(cliente);
					transaccionListNewTransaccion = em.merge(transaccionListNewTransaccion);
					if (oldNumeroIdentificacionClienteOfTransaccionListNewTransaccion != null
							&& !oldNumeroIdentificacionClienteOfTransaccionListNewTransaccion.equals(cliente)) {
						oldNumeroIdentificacionClienteOfTransaccionListNewTransaccion.getTransaccionList().remove(
								transaccionListNewTransaccion);
						oldNumeroIdentificacionClienteOfTransaccionListNewTransaccion = em
								.merge(oldNumeroIdentificacionClienteOfTransaccionListNewTransaccion);
					}
				}
			}
			for (Morosidad morosidadListNewMorosidad : morosidadListNew) {
				if (!morosidadListOld.contains(morosidadListNewMorosidad)) {
					Cliente oldNumeroIdentificacionClienteOfMorosidadListNewMorosidad = morosidadListNewMorosidad
							.getNumeroIdentificacionCliente();
					morosidadListNewMorosidad.setNumeroIdentificacionCliente(cliente);
					morosidadListNewMorosidad = em.merge(morosidadListNewMorosidad);
					if (oldNumeroIdentificacionClienteOfMorosidadListNewMorosidad != null
							&& !oldNumeroIdentificacionClienteOfMorosidadListNewMorosidad.equals(cliente)) {
						oldNumeroIdentificacionClienteOfMorosidadListNewMorosidad.getMorosidadList().remove(
								morosidadListNewMorosidad);
						oldNumeroIdentificacionClienteOfMorosidadListNewMorosidad = em
								.merge(oldNumeroIdentificacionClienteOfMorosidadListNewMorosidad);
					}
				}
			}
			for (Clientemensaje clientemensajeListNewClientemensaje : clientemensajeListNew) {
				if (!clientemensajeListOld.contains(clientemensajeListNewClientemensaje)) {
					Cliente oldNumeroIdentificacionClienteOfClientemensajeListNewClientemensaje = clientemensajeListNewClientemensaje
							.getNumeroIdentificacionCliente();
					clientemensajeListNewClientemensaje.setNumeroIdentificacionCliente(cliente);
					clientemensajeListNewClientemensaje = em.merge(clientemensajeListNewClientemensaje);
					if (oldNumeroIdentificacionClienteOfClientemensajeListNewClientemensaje != null
							&& !oldNumeroIdentificacionClienteOfClientemensajeListNewClientemensaje.equals(cliente)) {
						oldNumeroIdentificacionClienteOfClientemensajeListNewClientemensaje.getClientemensajeList()
								.remove(clientemensajeListNewClientemensaje);
						oldNumeroIdentificacionClienteOfClientemensajeListNewClientemensaje = em
								.merge(oldNumeroIdentificacionClienteOfClientemensajeListNewClientemensaje);
					}
				}
			}
			for (Cuentacredito cuentacreditoListNewCuentacredito : cuentacreditoListNew) {
				if (!cuentacreditoListOld.contains(cuentacreditoListNewCuentacredito)) {
					Cliente oldNumeroIdentificacionClienteOfCuentacreditoListNewCuentacredito = cuentacreditoListNewCuentacredito
							.getNumeroIdentificacionCliente();
					cuentacreditoListNewCuentacredito.setNumeroIdentificacionCliente(cliente);
					cuentacreditoListNewCuentacredito = em.merge(cuentacreditoListNewCuentacredito);
					if (oldNumeroIdentificacionClienteOfCuentacreditoListNewCuentacredito != null
							&& !oldNumeroIdentificacionClienteOfCuentacreditoListNewCuentacredito.equals(cliente)) {
						oldNumeroIdentificacionClienteOfCuentacreditoListNewCuentacredito.getCuentacreditoList()
								.remove(cuentacreditoListNewCuentacredito);
						oldNumeroIdentificacionClienteOfCuentacreditoListNewCuentacredito = em
								.merge(oldNumeroIdentificacionClienteOfCuentacreditoListNewCuentacredito);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				String id = cliente.getNumeroIdentificacionCliente();
				if (findCliente(id) == null) {
					throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	// public void destroy(String id) throws IllegalOrphanException,
	// NonexistentEntityException {
	// EntityManager em = null;
	// try {
	// em = getEntityManager();
	// em.getTransaction().begin();
	// Cliente cliente;
	// try {
	// cliente = em.getReference(Cliente.class, id);
	// cliente.getNumeroIdentificacionCliente();
	// } catch (EntityNotFoundException enfe) {
	// throw new NonexistentEntityException("The cliente with id " + id +
	// " no longer exists.", enfe);
	// }
	// List<String> illegalOrphanMessages = null;
	// List<Transaccioncliente> transaccionclienteListOrphanCheck =
	// cliente.getTransaccionclienteList();
	// for (Transaccioncliente
	// transaccionclienteListOrphanCheckTransaccioncliente :
	// transaccionclienteListOrphanCheck) {
	// if (illegalOrphanMessages == null) {
	// illegalOrphanMessages = new ArrayList<String>();
	// }
	// illegalOrphanMessages.add("This Cliente (" + cliente +
	// ") cannot be destroyed since the Transaccioncliente " +
	// transaccionclienteListOrphanCheckTransaccioncliente +
	// " in its transaccionclienteList field has a non-nullable numeroIdentificacionCliente field.");
	// }
	// List<Morosidad> morosidadListOrphanCheck = cliente.getMorosidadList();
	// for (Morosidad morosidadListOrphanCheckMorosidad :
	// morosidadListOrphanCheck) {
	// if (illegalOrphanMessages == null) {
	// illegalOrphanMessages = new ArrayList<String>();
	// }
	// illegalOrphanMessages.add("This Cliente (" + cliente +
	// ") cannot be destroyed since the Morosidad " +
	// morosidadListOrphanCheckMorosidad +
	// " in its morosidadList field has a non-nullable numeroIdentificacionCliente field.");
	// }
	// List<Clientemensaje> clientemensajeListOrphanCheck =
	// cliente.getClientemensajeList();
	// for (Clientemensaje clientemensajeListOrphanCheckClientemensaje :
	// clientemensajeListOrphanCheck) {
	// if (illegalOrphanMessages == null) {
	// illegalOrphanMessages = new ArrayList<String>();
	// }
	// illegalOrphanMessages.add("This Cliente (" + cliente +
	// ") cannot be destroyed since the Clientemensaje " +
	// clientemensajeListOrphanCheckClientemensaje +
	// " in its clientemensajeList field has a non-nullable numeroIdentificacionCliente field.");
	// }
	// List<Cuentacredito> cuentacreditoListOrphanCheck =
	// cliente.getCuentacreditoList();
	// for (Cuentacredito cuentacreditoListOrphanCheckCuentacredito :
	// cuentacreditoListOrphanCheck) {
	// if (illegalOrphanMessages == null) {
	// illegalOrphanMessages = new ArrayList<String>();
	// }
	// illegalOrphanMessages.add("This Cliente (" + cliente +
	// ") cannot be destroyed since the Cuentacredito " +
	// cuentacreditoListOrphanCheckCuentacredito +
	// " in its cuentacreditoList field has a non-nullable numeroIdentificacionCliente field.");
	// }
	// if (illegalOrphanMessages != null) {
	// throw new IllegalOrphanException(illegalOrphanMessages);
	// }
	// Exoneradotipo idExoneradotipo = cliente.getIdExoneradotipo();
	// if (idExoneradotipo != null) {
	// idExoneradotipo.getClienteList().remove(cliente);
	// idExoneradotipo = em.merge(idExoneradotipo);
	// }
	// Tipocliente idTipocliente = cliente.getIdTipocliente();
	// // if (idTipocliente != null) {
	// // idTipocliente.getClienteList().remove(cliente);
	// // idTipocliente = em.merge(idTipocliente);
	// // }
	// Giroactividadeconomica idGiroactividadeconomica =
	// cliente.getIdGiroactividadeconomica();
	// if (idGiroactividadeconomica != null) {
	// idGiroactividadeconomica.getClienteList().remove(cliente);
	// idGiroactividadeconomica = em.merge(idGiroactividadeconomica);
	// }
	// List<Transaccion> transaccionList = cliente.getTransaccionList();
	// for (Transaccion transaccionListTransaccion : transaccionList) {
	// transaccionListTransaccion.setNumeroIdentificacionCliente(null);
	// transaccionListTransaccion = em.merge(transaccionListTransaccion);
	// }
	// em.remove(cliente);
	// em.getTransaction().commit();
	// } finally {
	// if (em != null) {
	// em.close();
	// }
	// }
	// }

	/**
	 * Method findClienteEntities.
	 * 
	 * @return List<Cliente>
	 */
	public List<Cliente> findClienteEntities() {
		return findClienteEntities(true, -1, -1);
	}

	/**
	 * Method findClienteEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Cliente>
	 */
	public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
		return findClienteEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findClienteEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Cliente>
	 */
	private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Cliente.class));
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
	 * Method findCliente.
	 * 
	 * @param id
	 *            String
	 * @return Cliente
	 */
	public Cliente findCliente(String id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Cliente.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getClienteCount.
	 * 
	 * @return int
	 */
	public int getClienteCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Cliente> rt = cq.from(Cliente.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
