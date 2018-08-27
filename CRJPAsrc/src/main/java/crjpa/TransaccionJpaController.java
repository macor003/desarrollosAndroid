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
public class TransaccionJpaController implements Serializable {

	/**
	 * Constructor for TransaccionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TransaccionJpaController(EntityManagerFactory emf) {
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
	 * @param transaccion
	 *            Transaccion
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Transaccion transaccion) throws PreexistingEntityException, Exception {
		if (transaccion.getTransacciondocumentoList() == null) {
			transaccion.setTransacciondocumentoList(new ArrayList<Transacciondocumento>());
		}
		// if (transaccion.getDevolucionList() == null) {
		// transaccion.setDevolucionList(new ArrayList<Devolucion>());
		// }
		if (transaccion.getTransaccionclienteList() == null) {
			transaccion.setTransaccionclienteList(new ArrayList<Transaccioncliente>());
		}
		if (transaccion.getTransaccionList() == null) {
			transaccion.setTransaccionList(new ArrayList<Transaccion>());
		}
		if (transaccion.getTransaccionformadepagoList() == null) {
			transaccion.setTransaccionformadepagoList(new ArrayList<Transaccionformadepago>());
		}
		if (transaccion.getPagocreditoList() == null) {
			transaccion.setPagocreditoList(new ArrayList<Pagocredito>());
		}
		if (transaccion.getTransaccionarticuloList() == null) {
			transaccion.setTransaccionarticuloList(new ArrayList<Transaccionarticulo>());
		}
		if (transaccion.getTransaccionfaseList() == null) {
			transaccion.setTransaccionfaseList(new ArrayList<Transaccionfase>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Sesion idSesion = transaccion.getIdSesion();
			if (idSesion != null) {
				idSesion = em.getReference(idSesion.getClass(), idSesion.getId());
				transaccion.setIdSesion(idSesion);
			}
			Cliente numeroIdentificacionCliente = transaccion.getNumeroIdentificacionCliente();
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente = em.getReference(numeroIdentificacionCliente.getClass(),
						numeroIdentificacionCliente.getNumeroIdentificacionCliente());
				transaccion.setNumeroIdentificacionCliente(numeroIdentificacionCliente);
			}
			Serialimpresora idSerialimpresora = transaccion.getIdSerialimpresora();
			if (idSerialimpresora != null) {
				idSerialimpresora = em.getReference(idSerialimpresora.getClass(), idSerialimpresora.getId());
				transaccion.setIdSerialimpresora(idSerialimpresora);
			}
			Usuario idUsuario = transaccion.getIdUsuario();
			if (idUsuario != null) {
				idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getId());
				transaccion.setIdUsuario(idUsuario);
			}
			Transaccion idAnulaciontransaccion = transaccion.getIdAnulaciontransaccion();
			if (idAnulaciontransaccion != null) {
				idAnulaciontransaccion = em.getReference(idAnulaciontransaccion.getClass(),
						idAnulaciontransaccion.getId());
				transaccion.setIdAnulaciontransaccion(idAnulaciontransaccion);
			}
			Rolloauditoria idRolloauditoria = transaccion.getIdRolloauditoria();
			if (idRolloauditoria != null) {
				idRolloauditoria = em.getReference(idRolloauditoria.getClass(), idRolloauditoria.getId());
				transaccion.setIdRolloauditoria(idRolloauditoria);
			}
			List<Transacciondocumento> attachedTransacciondocumentoList = new ArrayList<Transacciondocumento>();
			for (Transacciondocumento transacciondocumentoListTransacciondocumentoToAttach : transaccion
					.getTransacciondocumentoList()) {
				transacciondocumentoListTransacciondocumentoToAttach = em.getReference(
						transacciondocumentoListTransacciondocumentoToAttach.getClass(),
						transacciondocumentoListTransacciondocumentoToAttach.getId());
				attachedTransacciondocumentoList.add(transacciondocumentoListTransacciondocumentoToAttach);
			}
			transaccion.setTransacciondocumentoList(attachedTransacciondocumentoList);
			List<Devolucion> attachedDevolucionList = new ArrayList<Devolucion>();
			// for (Devolucion devolucionListDevolucionToAttach :
			// transaccion.getDevolucionList()) {
			// devolucionListDevolucionToAttach =
			// em.getReference(devolucionListDevolucionToAttach.getClass(),
			// devolucionListDevolucionToAttach.getId());
			// attachedDevolucionList.add(devolucionListDevolucionToAttach);
			// }
			// transaccion.setDevolucionList(attachedDevolucionList);
			List<Transaccioncliente> attachedTransaccionclienteList = new ArrayList<Transaccioncliente>();
			for (Transaccioncliente transaccionclienteListTransaccionclienteToAttach : transaccion
					.getTransaccionclienteList()) {
				transaccionclienteListTransaccionclienteToAttach = em.getReference(
						transaccionclienteListTransaccionclienteToAttach.getClass(),
						transaccionclienteListTransaccionclienteToAttach.getId());
				attachedTransaccionclienteList.add(transaccionclienteListTransaccionclienteToAttach);
			}
			transaccion.setTransaccionclienteList(attachedTransaccionclienteList);
			List<Transaccion> attachedTransaccionList = new ArrayList<Transaccion>();
			for (Transaccion transaccionListTransaccionToAttach : transaccion.getTransaccionList()) {
				transaccionListTransaccionToAttach = em.getReference(transaccionListTransaccionToAttach.getClass(),
						transaccionListTransaccionToAttach.getId());
				attachedTransaccionList.add(transaccionListTransaccionToAttach);
			}
			transaccion.setTransaccionList(attachedTransaccionList);
			List<Transaccionformadepago> attachedTransaccionformadepagoList = new ArrayList<Transaccionformadepago>();
			for (Transaccionformadepago transaccionformadepagoListTransaccionformadepagoToAttach : transaccion
					.getTransaccionformadepagoList()) {
				transaccionformadepagoListTransaccionformadepagoToAttach = em.getReference(
						transaccionformadepagoListTransaccionformadepagoToAttach.getClass(),
						transaccionformadepagoListTransaccionformadepagoToAttach.getId());
				attachedTransaccionformadepagoList.add(transaccionformadepagoListTransaccionformadepagoToAttach);
			}
			transaccion.setTransaccionformadepagoList(attachedTransaccionformadepagoList);
			List<Pagocredito> attachedPagocreditoList = new ArrayList<Pagocredito>();
			for (Pagocredito pagocreditoListPagocreditoToAttach : transaccion.getPagocreditoList()) {
				pagocreditoListPagocreditoToAttach = em.getReference(pagocreditoListPagocreditoToAttach.getClass(),
						pagocreditoListPagocreditoToAttach.getId());
				attachedPagocreditoList.add(pagocreditoListPagocreditoToAttach);
			}
			transaccion.setPagocreditoList(attachedPagocreditoList);
			List<Transaccionarticulo> attachedTransaccionarticuloList = new ArrayList<Transaccionarticulo>();
			for (Transaccionarticulo transaccionarticuloListTransaccionarticuloToAttach : transaccion
					.getTransaccionarticuloList()) {
				transaccionarticuloListTransaccionarticuloToAttach = em.getReference(
						transaccionarticuloListTransaccionarticuloToAttach.getClass(),
						transaccionarticuloListTransaccionarticuloToAttach.getId());
				attachedTransaccionarticuloList.add(transaccionarticuloListTransaccionarticuloToAttach);
			}
			transaccion.setTransaccionarticuloList(attachedTransaccionarticuloList);
			List<Transaccionfase> attachedTransaccionfaseList = new ArrayList<Transaccionfase>();
			for (Transaccionfase transaccionfaseListTransaccionfaseToAttach : transaccion.getTransaccionfaseList()) {
				transaccionfaseListTransaccionfaseToAttach = em.getReference(
						transaccionfaseListTransaccionfaseToAttach.getClass(),
						transaccionfaseListTransaccionfaseToAttach.getId());
				attachedTransaccionfaseList.add(transaccionfaseListTransaccionfaseToAttach);
			}
			transaccion.setTransaccionfaseList(attachedTransaccionfaseList);
			em.persist(transaccion);
			if (idSesion != null) {
				idSesion.getTransaccionList().add(transaccion);
				idSesion = em.merge(idSesion);
			}
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente.getTransaccionList().add(transaccion);
				numeroIdentificacionCliente = em.merge(numeroIdentificacionCliente);
			}
			if (idSerialimpresora != null) {
				idSerialimpresora.getTransaccionList().add(transaccion);
				idSerialimpresora = em.merge(idSerialimpresora);
			}
			if (idUsuario != null) {
				idUsuario.getTransaccionList().add(transaccion);
				idUsuario = em.merge(idUsuario);
			}
			if (idAnulaciontransaccion != null) {
				idAnulaciontransaccion.getTransaccionList().add(transaccion);
				idAnulaciontransaccion = em.merge(idAnulaciontransaccion);
			}
			if (idRolloauditoria != null) {
				idRolloauditoria.getTransaccionList().add(transaccion);
				idRolloauditoria = em.merge(idRolloauditoria);
			}
			for (Transacciondocumento transacciondocumentoListTransacciondocumento : transaccion
					.getTransacciondocumentoList()) {
				Transaccion oldIdTransaccionOfTransacciondocumentoListTransacciondocumento = transacciondocumentoListTransacciondocumento
						.getIdTransaccion();
				transacciondocumentoListTransacciondocumento.setIdTransaccion(transaccion);
				transacciondocumentoListTransacciondocumento = em.merge(transacciondocumentoListTransacciondocumento);
				if (oldIdTransaccionOfTransacciondocumentoListTransacciondocumento != null) {
					oldIdTransaccionOfTransacciondocumentoListTransacciondocumento.getTransacciondocumentoList()
							.remove(transacciondocumentoListTransacciondocumento);
					oldIdTransaccionOfTransacciondocumentoListTransacciondocumento = em
							.merge(oldIdTransaccionOfTransacciondocumentoListTransacciondocumento);
				}
			}
			// for (Devolucion devolucionListDevolucion : transaccion.getDevolucionList()) {
			// Transaccion oldIdTransaccionOfDevolucionListDevolucion =
			// devolucionListDevolucion.getIdTransaccion();
			// devolucionListDevolucion.setIdTransaccion(transaccion);
			// devolucionListDevolucion = em.merge(devolucionListDevolucion);
			// if (oldIdTransaccionOfDevolucionListDevolucion != null) {
			// oldIdTransaccionOfDevolucionListDevolucion.getDevolucionList().remove(devolucionListDevolucion);
			// oldIdTransaccionOfDevolucionListDevolucion =
			// em.merge(oldIdTransaccionOfDevolucionListDevolucion);
			// }
			// }
			for (Transaccioncliente transaccionclienteListTransaccioncliente : transaccion
					.getTransaccionclienteList()) {
				Transaccion oldIdTransaccionOfTransaccionclienteListTransaccioncliente = transaccionclienteListTransaccioncliente
						.getIdTransaccion();
				transaccionclienteListTransaccioncliente.setIdTransaccion(transaccion);
				transaccionclienteListTransaccioncliente = em.merge(transaccionclienteListTransaccioncliente);
				if (oldIdTransaccionOfTransaccionclienteListTransaccioncliente != null) {
					oldIdTransaccionOfTransaccionclienteListTransaccioncliente.getTransaccionclienteList()
							.remove(transaccionclienteListTransaccioncliente);
					oldIdTransaccionOfTransaccionclienteListTransaccioncliente = em
							.merge(oldIdTransaccionOfTransaccionclienteListTransaccioncliente);
				}
			}
			for (Transaccion transaccionListTransaccion : transaccion.getTransaccionList()) {
				Transaccion oldIdAnulaciontransaccionOfTransaccionListTransaccion = transaccionListTransaccion
						.getIdAnulaciontransaccion();
				transaccionListTransaccion.setIdAnulaciontransaccion(transaccion);
				transaccionListTransaccion = em.merge(transaccionListTransaccion);
				if (oldIdAnulaciontransaccionOfTransaccionListTransaccion != null) {
					oldIdAnulaciontransaccionOfTransaccionListTransaccion.getTransaccionList()
							.remove(transaccionListTransaccion);
					oldIdAnulaciontransaccionOfTransaccionListTransaccion = em
							.merge(oldIdAnulaciontransaccionOfTransaccionListTransaccion);
				}
			}
			for (Transaccionformadepago transaccionformadepagoListTransaccionformadepago : transaccion
					.getTransaccionformadepagoList()) {
				Transaccion oldIdTransaccionOfTransaccionformadepagoListTransaccionformadepago = transaccionformadepagoListTransaccionformadepago
						.getIdTransaccion();
				transaccionformadepagoListTransaccionformadepago.setIdTransaccion(transaccion);
				transaccionformadepagoListTransaccionformadepago = em
						.merge(transaccionformadepagoListTransaccionformadepago);
				if (oldIdTransaccionOfTransaccionformadepagoListTransaccionformadepago != null) {
					oldIdTransaccionOfTransaccionformadepagoListTransaccionformadepago.getTransaccionformadepagoList()
							.remove(transaccionformadepagoListTransaccionformadepago);
					oldIdTransaccionOfTransaccionformadepagoListTransaccionformadepago = em
							.merge(oldIdTransaccionOfTransaccionformadepagoListTransaccionformadepago);
				}
			}
			for (Pagocredito pagocreditoListPagocredito : transaccion.getPagocreditoList()) {
				Transaccion oldIdTransaccionOfPagocreditoListPagocredito = pagocreditoListPagocredito
						.getIdTransaccion();
				pagocreditoListPagocredito.setIdTransaccion(transaccion);
				pagocreditoListPagocredito = em.merge(pagocreditoListPagocredito);
				if (oldIdTransaccionOfPagocreditoListPagocredito != null) {
					oldIdTransaccionOfPagocreditoListPagocredito.getPagocreditoList()
							.remove(pagocreditoListPagocredito);
					oldIdTransaccionOfPagocreditoListPagocredito = em
							.merge(oldIdTransaccionOfPagocreditoListPagocredito);
				}
			}
			for (Transaccionarticulo transaccionarticuloListTransaccionarticulo : transaccion
					.getTransaccionarticuloList()) {
				Transaccion oldIdTransaccionOfTransaccionarticuloListTransaccionarticulo = transaccionarticuloListTransaccionarticulo
						.getIdTransaccion();
				transaccionarticuloListTransaccionarticulo.setIdTransaccion(transaccion);
				transaccionarticuloListTransaccionarticulo = em.merge(transaccionarticuloListTransaccionarticulo);
				if (oldIdTransaccionOfTransaccionarticuloListTransaccionarticulo != null) {
					oldIdTransaccionOfTransaccionarticuloListTransaccionarticulo.getTransaccionarticuloList()
							.remove(transaccionarticuloListTransaccionarticulo);
					oldIdTransaccionOfTransaccionarticuloListTransaccionarticulo = em
							.merge(oldIdTransaccionOfTransaccionarticuloListTransaccionarticulo);
				}
			}
			for (Transaccionfase transaccionfaseListTransaccionfase : transaccion.getTransaccionfaseList()) {
				Transaccion oldIdTransaccionOfTransaccionfaseListTransaccionfase = transaccionfaseListTransaccionfase
						.getIdTransaccion();
				transaccionfaseListTransaccionfase.setIdTransaccion(transaccion);
				transaccionfaseListTransaccionfase = em.merge(transaccionfaseListTransaccionfase);
				if (oldIdTransaccionOfTransaccionfaseListTransaccionfase != null) {
					oldIdTransaccionOfTransaccionfaseListTransaccionfase.getTransaccionfaseList()
							.remove(transaccionfaseListTransaccionfase);
					oldIdTransaccionOfTransaccionfaseListTransaccionfase = em
							.merge(oldIdTransaccionOfTransaccionfaseListTransaccionfase);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTransaccion(transaccion.getId()) != null) {
				throw new PreexistingEntityException("Transaccion " + transaccion + " already exists.", ex);
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
	 * @param transaccion
	 *            Transaccion
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Transaccion transaccion) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Transaccion persistentTransaccion = em.find(Transaccion.class, transaccion.getId());
			Sesion idSesionOld = persistentTransaccion.getIdSesion();
			Sesion idSesionNew = transaccion.getIdSesion();
			Cliente numeroIdentificacionClienteOld = persistentTransaccion.getNumeroIdentificacionCliente();
			Cliente numeroIdentificacionClienteNew = transaccion.getNumeroIdentificacionCliente();
			Serialimpresora idSerialimpresoraOld = persistentTransaccion.getIdSerialimpresora();
			Serialimpresora idSerialimpresoraNew = transaccion.getIdSerialimpresora();
			Usuario idUsuarioOld = persistentTransaccion.getIdUsuario();
			Usuario idUsuarioNew = transaccion.getIdUsuario();
			Transaccion idAnulaciontransaccionOld = persistentTransaccion.getIdAnulaciontransaccion();
			Transaccion idAnulaciontransaccionNew = transaccion.getIdAnulaciontransaccion();
			Rolloauditoria idRolloauditoriaOld = persistentTransaccion.getIdRolloauditoria();
			Rolloauditoria idRolloauditoriaNew = transaccion.getIdRolloauditoria();
			List<Transacciondocumento> transacciondocumentoListOld = persistentTransaccion
					.getTransacciondocumentoList();
			List<Transacciondocumento> transacciondocumentoListNew = transaccion.getTransacciondocumentoList();
			// List<Devolucion> devolucionListOld =
			// persistentTransaccion.getDevolucionList();
			// List<Devolucion> devolucionListNew = transaccion.getDevolucionList();
			List<Transaccioncliente> transaccionclienteListOld = persistentTransaccion.getTransaccionclienteList();
			List<Transaccioncliente> transaccionclienteListNew = transaccion.getTransaccionclienteList();
			List<Transaccion> transaccionListOld = persistentTransaccion.getTransaccionList();
			List<Transaccion> transaccionListNew = transaccion.getTransaccionList();
			List<Transaccionformadepago> transaccionformadepagoListOld = persistentTransaccion
					.getTransaccionformadepagoList();
			List<Transaccionformadepago> transaccionformadepagoListNew = transaccion.getTransaccionformadepagoList();
			List<Pagocredito> pagocreditoListOld = persistentTransaccion.getPagocreditoList();
			List<Pagocredito> pagocreditoListNew = transaccion.getPagocreditoList();
			List<Transaccionarticulo> transaccionarticuloListOld = persistentTransaccion.getTransaccionarticuloList();
			List<Transaccionarticulo> transaccionarticuloListNew = transaccion.getTransaccionarticuloList();
			List<Transaccionfase> transaccionfaseListOld = persistentTransaccion.getTransaccionfaseList();
			List<Transaccionfase> transaccionfaseListNew = transaccion.getTransaccionfaseList();
			List<String> illegalOrphanMessages = null;
			for (Transacciondocumento transacciondocumentoListOldTransacciondocumento : transacciondocumentoListOld) {
				if (!transacciondocumentoListNew.contains(transacciondocumentoListOldTransacciondocumento)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add(
							"You must retain Transacciondocumento " + transacciondocumentoListOldTransacciondocumento
									+ " since its idTransaccion field is not nullable.");
				}
			}
			// for (Devolucion devolucionListOldDevolucion : devolucionListOld) {
			// if (!devolucionListNew.contains(devolucionListOldDevolucion)) {
			// if (illegalOrphanMessages == null) {
			// illegalOrphanMessages = new ArrayList<String>();
			// }
			// illegalOrphanMessages.add("You must retain Devolucion " +
			// devolucionListOldDevolucion
			// + " since its idTransaccion field is not nullable.");
			// }
			// }
			for (Transaccioncliente transaccionclienteListOldTransaccioncliente : transaccionclienteListOld) {
				if (!transaccionclienteListNew.contains(transaccionclienteListOldTransaccioncliente)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Transaccioncliente " + transaccionclienteListOldTransaccioncliente
									+ " since its idTransaccion field is not nullable.");
				}
			}
			for (Transaccionformadepago transaccionformadepagoListOldTransaccionformadepago : transaccionformadepagoListOld) {
				if (!transaccionformadepagoListNew.contains(transaccionformadepagoListOldTransaccionformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Transaccionformadepago "
							+ transaccionformadepagoListOldTransaccionformadepago
							+ " since its idTransaccion field is not nullable.");
				}
			}
			for (Pagocredito pagocreditoListOldPagocredito : pagocreditoListOld) {
				if (!pagocreditoListNew.contains(pagocreditoListOldPagocredito)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Pagocredito " + pagocreditoListOldPagocredito
							+ " since its idTransaccion field is not nullable.");
				}
			}
			for (Transaccionarticulo transaccionarticuloListOldTransaccionarticulo : transaccionarticuloListOld) {
				if (!transaccionarticuloListNew.contains(transaccionarticuloListOldTransaccionarticulo)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Transaccionarticulo " + transaccionarticuloListOldTransaccionarticulo
									+ " since its idTransaccion field is not nullable.");
				}
			}
			for (Transaccionfase transaccionfaseListOldTransaccionfase : transaccionfaseListOld) {
				if (!transaccionfaseListNew.contains(transaccionfaseListOldTransaccionfase)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Transaccionfase " + transaccionfaseListOldTransaccionfase
							+ " since its idTransaccion field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idSesionNew != null) {
				idSesionNew = em.getReference(idSesionNew.getClass(), idSesionNew.getId());
				transaccion.setIdSesion(idSesionNew);
			}
			if (numeroIdentificacionClienteNew != null) {
				numeroIdentificacionClienteNew = em.getReference(numeroIdentificacionClienteNew.getClass(),
						numeroIdentificacionClienteNew.getNumeroIdentificacionCliente());
				transaccion.setNumeroIdentificacionCliente(numeroIdentificacionClienteNew);
			}
			if (idSerialimpresoraNew != null) {
				idSerialimpresoraNew = em.getReference(idSerialimpresoraNew.getClass(), idSerialimpresoraNew.getId());
				transaccion.setIdSerialimpresora(idSerialimpresoraNew);
			}
			if (idUsuarioNew != null) {
				idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getId());
				transaccion.setIdUsuario(idUsuarioNew);
			}
			if (idAnulaciontransaccionNew != null) {
				idAnulaciontransaccionNew = em.getReference(idAnulaciontransaccionNew.getClass(),
						idAnulaciontransaccionNew.getId());
				transaccion.setIdAnulaciontransaccion(idAnulaciontransaccionNew);
			}
			if (idRolloauditoriaNew != null) {
				idRolloauditoriaNew = em.getReference(idRolloauditoriaNew.getClass(), idRolloauditoriaNew.getId());
				transaccion.setIdRolloauditoria(idRolloauditoriaNew);
			}
			List<Transacciondocumento> attachedTransacciondocumentoListNew = new ArrayList<Transacciondocumento>();
			for (Transacciondocumento transacciondocumentoListNewTransacciondocumentoToAttach : transacciondocumentoListNew) {
				transacciondocumentoListNewTransacciondocumentoToAttach = em.getReference(
						transacciondocumentoListNewTransacciondocumentoToAttach.getClass(),
						transacciondocumentoListNewTransacciondocumentoToAttach.getId());
				attachedTransacciondocumentoListNew.add(transacciondocumentoListNewTransacciondocumentoToAttach);
			}
			transacciondocumentoListNew = attachedTransacciondocumentoListNew;
			transaccion.setTransacciondocumentoList(transacciondocumentoListNew);
			List<Devolucion> attachedDevolucionListNew = new ArrayList<Devolucion>();
			// for (Devolucion devolucionListNewDevolucionToAttach : devolucionListNew) {
			// devolucionListNewDevolucionToAttach =
			// em.getReference(devolucionListNewDevolucionToAttach.getClass(),
			// devolucionListNewDevolucionToAttach.getId());
			// attachedDevolucionListNew.add(devolucionListNewDevolucionToAttach);
			// }
			// devolucionListNew = attachedDevolucionListNew;
			// transaccion.setDevolucionList(devolucionListNew);
			List<Transaccioncliente> attachedTransaccionclienteListNew = new ArrayList<Transaccioncliente>();
			for (Transaccioncliente transaccionclienteListNewTransaccionclienteToAttach : transaccionclienteListNew) {
				transaccionclienteListNewTransaccionclienteToAttach = em.getReference(
						transaccionclienteListNewTransaccionclienteToAttach.getClass(),
						transaccionclienteListNewTransaccionclienteToAttach.getId());
				attachedTransaccionclienteListNew.add(transaccionclienteListNewTransaccionclienteToAttach);
			}
			transaccionclienteListNew = attachedTransaccionclienteListNew;
			transaccion.setTransaccionclienteList(transaccionclienteListNew);
			List<Transaccion> attachedTransaccionListNew = new ArrayList<Transaccion>();
			for (Transaccion transaccionListNewTransaccionToAttach : transaccionListNew) {
				transaccionListNewTransaccionToAttach = em.getReference(
						transaccionListNewTransaccionToAttach.getClass(),
						transaccionListNewTransaccionToAttach.getId());
				attachedTransaccionListNew.add(transaccionListNewTransaccionToAttach);
			}
			transaccionListNew = attachedTransaccionListNew;
			transaccion.setTransaccionList(transaccionListNew);
			List<Transaccionformadepago> attachedTransaccionformadepagoListNew = new ArrayList<Transaccionformadepago>();
			for (Transaccionformadepago transaccionformadepagoListNewTransaccionformadepagoToAttach : transaccionformadepagoListNew) {
				transaccionformadepagoListNewTransaccionformadepagoToAttach = em.getReference(
						transaccionformadepagoListNewTransaccionformadepagoToAttach.getClass(),
						transaccionformadepagoListNewTransaccionformadepagoToAttach.getId());
				attachedTransaccionformadepagoListNew.add(transaccionformadepagoListNewTransaccionformadepagoToAttach);
			}
			transaccionformadepagoListNew = attachedTransaccionformadepagoListNew;
			transaccion.setTransaccionformadepagoList(transaccionformadepagoListNew);
			List<Pagocredito> attachedPagocreditoListNew = new ArrayList<Pagocredito>();
			for (Pagocredito pagocreditoListNewPagocreditoToAttach : pagocreditoListNew) {
				pagocreditoListNewPagocreditoToAttach = em.getReference(
						pagocreditoListNewPagocreditoToAttach.getClass(),
						pagocreditoListNewPagocreditoToAttach.getId());
				attachedPagocreditoListNew.add(pagocreditoListNewPagocreditoToAttach);
			}
			pagocreditoListNew = attachedPagocreditoListNew;
			transaccion.setPagocreditoList(pagocreditoListNew);
			List<Transaccionarticulo> attachedTransaccionarticuloListNew = new ArrayList<Transaccionarticulo>();
			for (Transaccionarticulo transaccionarticuloListNewTransaccionarticuloToAttach : transaccionarticuloListNew) {
				transaccionarticuloListNewTransaccionarticuloToAttach = em.getReference(
						transaccionarticuloListNewTransaccionarticuloToAttach.getClass(),
						transaccionarticuloListNewTransaccionarticuloToAttach.getId());
				attachedTransaccionarticuloListNew.add(transaccionarticuloListNewTransaccionarticuloToAttach);
			}
			transaccionarticuloListNew = attachedTransaccionarticuloListNew;
			transaccion.setTransaccionarticuloList(transaccionarticuloListNew);
			List<Transaccionfase> attachedTransaccionfaseListNew = new ArrayList<Transaccionfase>();
			for (Transaccionfase transaccionfaseListNewTransaccionfaseToAttach : transaccionfaseListNew) {
				transaccionfaseListNewTransaccionfaseToAttach = em.getReference(
						transaccionfaseListNewTransaccionfaseToAttach.getClass(),
						transaccionfaseListNewTransaccionfaseToAttach.getId());
				attachedTransaccionfaseListNew.add(transaccionfaseListNewTransaccionfaseToAttach);
			}
			transaccionfaseListNew = attachedTransaccionfaseListNew;
			transaccion.setTransaccionfaseList(transaccionfaseListNew);
			transaccion = em.merge(transaccion);
			if (idSesionOld != null && !idSesionOld.equals(idSesionNew)) {
				idSesionOld.getTransaccionList().remove(transaccion);
				idSesionOld = em.merge(idSesionOld);
			}
			if (idSesionNew != null && !idSesionNew.equals(idSesionOld)) {
				idSesionNew.getTransaccionList().add(transaccion);
				idSesionNew = em.merge(idSesionNew);
			}
			if (numeroIdentificacionClienteOld != null
					&& !numeroIdentificacionClienteOld.equals(numeroIdentificacionClienteNew)) {
				numeroIdentificacionClienteOld.getTransaccionList().remove(transaccion);
				numeroIdentificacionClienteOld = em.merge(numeroIdentificacionClienteOld);
			}
			if (numeroIdentificacionClienteNew != null
					&& !numeroIdentificacionClienteNew.equals(numeroIdentificacionClienteOld)) {
				numeroIdentificacionClienteNew.getTransaccionList().add(transaccion);
				numeroIdentificacionClienteNew = em.merge(numeroIdentificacionClienteNew);
			}
			if (idSerialimpresoraOld != null && !idSerialimpresoraOld.equals(idSerialimpresoraNew)) {
				idSerialimpresoraOld.getTransaccionList().remove(transaccion);
				idSerialimpresoraOld = em.merge(idSerialimpresoraOld);
			}
			if (idSerialimpresoraNew != null && !idSerialimpresoraNew.equals(idSerialimpresoraOld)) {
				idSerialimpresoraNew.getTransaccionList().add(transaccion);
				idSerialimpresoraNew = em.merge(idSerialimpresoraNew);
			}
			if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
				idUsuarioOld.getTransaccionList().remove(transaccion);
				idUsuarioOld = em.merge(idUsuarioOld);
			}
			if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
				idUsuarioNew.getTransaccionList().add(transaccion);
				idUsuarioNew = em.merge(idUsuarioNew);
			}
			if (idAnulaciontransaccionOld != null && !idAnulaciontransaccionOld.equals(idAnulaciontransaccionNew)) {
				idAnulaciontransaccionOld.getTransaccionList().remove(transaccion);
				idAnulaciontransaccionOld = em.merge(idAnulaciontransaccionOld);
			}
			if (idAnulaciontransaccionNew != null && !idAnulaciontransaccionNew.equals(idAnulaciontransaccionOld)) {
				idAnulaciontransaccionNew.getTransaccionList().add(transaccion);
				idAnulaciontransaccionNew = em.merge(idAnulaciontransaccionNew);
			}
			if (idRolloauditoriaOld != null && !idRolloauditoriaOld.equals(idRolloauditoriaNew)) {
				idRolloauditoriaOld.getTransaccionList().remove(transaccion);
				idRolloauditoriaOld = em.merge(idRolloauditoriaOld);
			}
			if (idRolloauditoriaNew != null && !idRolloauditoriaNew.equals(idRolloauditoriaOld)) {
				idRolloauditoriaNew.getTransaccionList().add(transaccion);
				idRolloauditoriaNew = em.merge(idRolloauditoriaNew);
			}
			for (Transacciondocumento transacciondocumentoListNewTransacciondocumento : transacciondocumentoListNew) {
				if (!transacciondocumentoListOld.contains(transacciondocumentoListNewTransacciondocumento)) {
					Transaccion oldIdTransaccionOfTransacciondocumentoListNewTransacciondocumento = transacciondocumentoListNewTransacciondocumento
							.getIdTransaccion();
					transacciondocumentoListNewTransacciondocumento.setIdTransaccion(transaccion);
					transacciondocumentoListNewTransacciondocumento = em
							.merge(transacciondocumentoListNewTransacciondocumento);
					if (oldIdTransaccionOfTransacciondocumentoListNewTransacciondocumento != null
							&& !oldIdTransaccionOfTransacciondocumentoListNewTransacciondocumento.equals(transaccion)) {
						oldIdTransaccionOfTransacciondocumentoListNewTransacciondocumento.getTransacciondocumentoList()
								.remove(transacciondocumentoListNewTransacciondocumento);
						oldIdTransaccionOfTransacciondocumentoListNewTransacciondocumento = em
								.merge(oldIdTransaccionOfTransacciondocumentoListNewTransacciondocumento);
					}
				}
			}
			// for (Devolucion devolucionListNewDevolucion : devolucionListNew) {
			// if (!devolucionListOld.contains(devolucionListNewDevolucion)) {
			// Transaccion oldIdTransaccionOfDevolucionListNewDevolucion =
			// devolucionListNewDevolucion
			// .getIdTransaccion();
			// devolucionListNewDevolucion.setIdTransaccion(transaccion);
			// devolucionListNewDevolucion = em.merge(devolucionListNewDevolucion);
			// if (oldIdTransaccionOfDevolucionListNewDevolucion != null
			// && !oldIdTransaccionOfDevolucionListNewDevolucion.equals(transaccion)) {
			// oldIdTransaccionOfDevolucionListNewDevolucion.getDevolucionList().remove(
			// devolucionListNewDevolucion);
			// oldIdTransaccionOfDevolucionListNewDevolucion = em
			// .merge(oldIdTransaccionOfDevolucionListNewDevolucion);
			// }
			// }
			// }
			for (Transaccioncliente transaccionclienteListNewTransaccioncliente : transaccionclienteListNew) {
				if (!transaccionclienteListOld.contains(transaccionclienteListNewTransaccioncliente)) {
					Transaccion oldIdTransaccionOfTransaccionclienteListNewTransaccioncliente = transaccionclienteListNewTransaccioncliente
							.getIdTransaccion();
					transaccionclienteListNewTransaccioncliente.setIdTransaccion(transaccion);
					transaccionclienteListNewTransaccioncliente = em.merge(transaccionclienteListNewTransaccioncliente);
					if (oldIdTransaccionOfTransaccionclienteListNewTransaccioncliente != null
							&& !oldIdTransaccionOfTransaccionclienteListNewTransaccioncliente.equals(transaccion)) {
						oldIdTransaccionOfTransaccionclienteListNewTransaccioncliente.getTransaccionclienteList()
								.remove(transaccionclienteListNewTransaccioncliente);
						oldIdTransaccionOfTransaccionclienteListNewTransaccioncliente = em
								.merge(oldIdTransaccionOfTransaccionclienteListNewTransaccioncliente);
					}
				}
			}
			for (Transaccion transaccionListOldTransaccion : transaccionListOld) {
				if (!transaccionListNew.contains(transaccionListOldTransaccion)) {
					transaccionListOldTransaccion.setIdAnulaciontransaccion(null);
					transaccionListOldTransaccion = em.merge(transaccionListOldTransaccion);
				}
			}
			for (Transaccion transaccionListNewTransaccion : transaccionListNew) {
				if (!transaccionListOld.contains(transaccionListNewTransaccion)) {
					Transaccion oldIdAnulaciontransaccionOfTransaccionListNewTransaccion = transaccionListNewTransaccion
							.getIdAnulaciontransaccion();
					transaccionListNewTransaccion.setIdAnulaciontransaccion(transaccion);
					transaccionListNewTransaccion = em.merge(transaccionListNewTransaccion);
					if (oldIdAnulaciontransaccionOfTransaccionListNewTransaccion != null
							&& !oldIdAnulaciontransaccionOfTransaccionListNewTransaccion.equals(transaccion)) {
						oldIdAnulaciontransaccionOfTransaccionListNewTransaccion.getTransaccionList()
								.remove(transaccionListNewTransaccion);
						oldIdAnulaciontransaccionOfTransaccionListNewTransaccion = em
								.merge(oldIdAnulaciontransaccionOfTransaccionListNewTransaccion);
					}
				}
			}
			for (Transaccionformadepago transaccionformadepagoListNewTransaccionformadepago : transaccionformadepagoListNew) {
				if (!transaccionformadepagoListOld.contains(transaccionformadepagoListNewTransaccionformadepago)) {
					Transaccion oldIdTransaccionOfTransaccionformadepagoListNewTransaccionformadepago = transaccionformadepagoListNewTransaccionformadepago
							.getIdTransaccion();
					transaccionformadepagoListNewTransaccionformadepago.setIdTransaccion(transaccion);
					transaccionformadepagoListNewTransaccionformadepago = em
							.merge(transaccionformadepagoListNewTransaccionformadepago);
					if (oldIdTransaccionOfTransaccionformadepagoListNewTransaccionformadepago != null
							&& !oldIdTransaccionOfTransaccionformadepagoListNewTransaccionformadepago
									.equals(transaccion)) {
						oldIdTransaccionOfTransaccionformadepagoListNewTransaccionformadepago
								.getTransaccionformadepagoList()
								.remove(transaccionformadepagoListNewTransaccionformadepago);
						oldIdTransaccionOfTransaccionformadepagoListNewTransaccionformadepago = em
								.merge(oldIdTransaccionOfTransaccionformadepagoListNewTransaccionformadepago);
					}
				}
			}
			for (Pagocredito pagocreditoListNewPagocredito : pagocreditoListNew) {
				if (!pagocreditoListOld.contains(pagocreditoListNewPagocredito)) {
					Transaccion oldIdTransaccionOfPagocreditoListNewPagocredito = pagocreditoListNewPagocredito
							.getIdTransaccion();
					pagocreditoListNewPagocredito.setIdTransaccion(transaccion);
					pagocreditoListNewPagocredito = em.merge(pagocreditoListNewPagocredito);
					if (oldIdTransaccionOfPagocreditoListNewPagocredito != null
							&& !oldIdTransaccionOfPagocreditoListNewPagocredito.equals(transaccion)) {
						oldIdTransaccionOfPagocreditoListNewPagocredito.getPagocreditoList()
								.remove(pagocreditoListNewPagocredito);
						oldIdTransaccionOfPagocreditoListNewPagocredito = em
								.merge(oldIdTransaccionOfPagocreditoListNewPagocredito);
					}
				}
			}
			for (Transaccionarticulo transaccionarticuloListNewTransaccionarticulo : transaccionarticuloListNew) {
				if (!transaccionarticuloListOld.contains(transaccionarticuloListNewTransaccionarticulo)) {
					Transaccion oldIdTransaccionOfTransaccionarticuloListNewTransaccionarticulo = transaccionarticuloListNewTransaccionarticulo
							.getIdTransaccion();
					transaccionarticuloListNewTransaccionarticulo.setIdTransaccion(transaccion);
					transaccionarticuloListNewTransaccionarticulo = em
							.merge(transaccionarticuloListNewTransaccionarticulo);
					if (oldIdTransaccionOfTransaccionarticuloListNewTransaccionarticulo != null
							&& !oldIdTransaccionOfTransaccionarticuloListNewTransaccionarticulo.equals(transaccion)) {
						oldIdTransaccionOfTransaccionarticuloListNewTransaccionarticulo.getTransaccionarticuloList()
								.remove(transaccionarticuloListNewTransaccionarticulo);
						oldIdTransaccionOfTransaccionarticuloListNewTransaccionarticulo = em
								.merge(oldIdTransaccionOfTransaccionarticuloListNewTransaccionarticulo);
					}
				}
			}
			for (Transaccionfase transaccionfaseListNewTransaccionfase : transaccionfaseListNew) {
				if (!transaccionfaseListOld.contains(transaccionfaseListNewTransaccionfase)) {
					Transaccion oldIdTransaccionOfTransaccionfaseListNewTransaccionfase = transaccionfaseListNewTransaccionfase
							.getIdTransaccion();
					transaccionfaseListNewTransaccionfase.setIdTransaccion(transaccion);
					transaccionfaseListNewTransaccionfase = em.merge(transaccionfaseListNewTransaccionfase);
					if (oldIdTransaccionOfTransaccionfaseListNewTransaccionfase != null
							&& !oldIdTransaccionOfTransaccionfaseListNewTransaccionfase.equals(transaccion)) {
						oldIdTransaccionOfTransaccionfaseListNewTransaccionfase.getTransaccionfaseList()
								.remove(transaccionfaseListNewTransaccionfase);
						oldIdTransaccionOfTransaccionfaseListNewTransaccionfase = em
								.merge(oldIdTransaccionOfTransaccionfaseListNewTransaccionfase);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = transaccion.getId();
				if (findTransaccion(id) == null) {
					throw new NonexistentEntityException("The transaccion with id " + id + " no longer exists.");
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
			Transaccion transaccion;
			try {
				transaccion = em.getReference(Transaccion.class, id);
				transaccion.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The transaccion with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Transacciondocumento> transacciondocumentoListOrphanCheck = transaccion.getTransacciondocumentoList();
			for (Transacciondocumento transacciondocumentoListOrphanCheckTransacciondocumento : transacciondocumentoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add(
						"This Transaccion (" + transaccion + ") cannot be destroyed since the Transacciondocumento "
								+ transacciondocumentoListOrphanCheckTransacciondocumento
								+ " in its transacciondocumentoList field has a non-nullable idTransaccion field.");
			}
			// List<Devolucion> devolucionListOrphanCheck = transaccion.getDevolucionList();
			// for (Devolucion devolucionListOrphanCheckDevolucion :
			// devolucionListOrphanCheck) {
			// if (illegalOrphanMessages == null) {
			// illegalOrphanMessages = new ArrayList<String>();
			// }
			// illegalOrphanMessages.add("This Transaccion (" + transaccion
			// + ") cannot be destroyed since the Devolucion " +
			// devolucionListOrphanCheckDevolucion
			// + " in its devolucionList field has a non-nullable idTransaccion field.");
			// }
			List<Transaccioncliente> transaccionclienteListOrphanCheck = transaccion.getTransaccionclienteList();
			for (Transaccioncliente transaccionclienteListOrphanCheckTransaccioncliente : transaccionclienteListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Transaccion (" + transaccion + ") cannot be destroyed since the Transaccioncliente "
								+ transaccionclienteListOrphanCheckTransaccioncliente
								+ " in its transaccionclienteList field has a non-nullable idTransaccion field.");
			}
			List<Transaccionformadepago> transaccionformadepagoListOrphanCheck = transaccion
					.getTransaccionformadepagoList();
			for (Transaccionformadepago transaccionformadepagoListOrphanCheckTransaccionformadepago : transaccionformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add(
						"This Transaccion (" + transaccion + ") cannot be destroyed since the Transaccionformadepago "
								+ transaccionformadepagoListOrphanCheckTransaccionformadepago
								+ " in its transaccionformadepagoList field has a non-nullable idTransaccion field.");
			}
			List<Pagocredito> pagocreditoListOrphanCheck = transaccion.getPagocreditoList();
			for (Pagocredito pagocreditoListOrphanCheckPagocredito : pagocreditoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Transaccion (" + transaccion
						+ ") cannot be destroyed since the Pagocredito " + pagocreditoListOrphanCheckPagocredito
						+ " in its pagocreditoList field has a non-nullable idTransaccion field.");
			}
			List<Transaccionarticulo> transaccionarticuloListOrphanCheck = transaccion.getTransaccionarticuloList();
			for (Transaccionarticulo transaccionarticuloListOrphanCheckTransaccionarticulo : transaccionarticuloListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Transaccion (" + transaccion + ") cannot be destroyed since the Transaccionarticulo "
								+ transaccionarticuloListOrphanCheckTransaccionarticulo
								+ " in its transaccionarticuloList field has a non-nullable idTransaccion field.");
			}
			List<Transaccionfase> transaccionfaseListOrphanCheck = transaccion.getTransaccionfaseList();
			for (Transaccionfase transaccionfaseListOrphanCheckTransaccionfase : transaccionfaseListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Transaccion (" + transaccion + ") cannot be destroyed since the Transaccionfase "
								+ transaccionfaseListOrphanCheckTransaccionfase
								+ " in its transaccionfaseList field has a non-nullable idTransaccion field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Sesion idSesion = transaccion.getIdSesion();
			if (idSesion != null) {
				idSesion.getTransaccionList().remove(transaccion);
				idSesion = em.merge(idSesion);
			}
			Cliente numeroIdentificacionCliente = transaccion.getNumeroIdentificacionCliente();
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente.getTransaccionList().remove(transaccion);
				numeroIdentificacionCliente = em.merge(numeroIdentificacionCliente);
			}
			Serialimpresora idSerialimpresora = transaccion.getIdSerialimpresora();
			if (idSerialimpresora != null) {
				idSerialimpresora.getTransaccionList().remove(transaccion);
				idSerialimpresora = em.merge(idSerialimpresora);
			}
			Usuario idUsuario = transaccion.getIdUsuario();
			if (idUsuario != null) {
				idUsuario.getTransaccionList().remove(transaccion);
				idUsuario = em.merge(idUsuario);
			}
			Transaccion idAnulaciontransaccion = transaccion.getIdAnulaciontransaccion();
			if (idAnulaciontransaccion != null) {
				idAnulaciontransaccion.getTransaccionList().remove(transaccion);
				idAnulaciontransaccion = em.merge(idAnulaciontransaccion);
			}
			Rolloauditoria idRolloauditoria = transaccion.getIdRolloauditoria();
			if (idRolloauditoria != null) {
				idRolloauditoria.getTransaccionList().remove(transaccion);
				idRolloauditoria = em.merge(idRolloauditoria);
			}
			List<Transaccion> transaccionList = transaccion.getTransaccionList();
			for (Transaccion transaccionListTransaccion : transaccionList) {
				transaccionListTransaccion.setIdAnulaciontransaccion(null);
				transaccionListTransaccion = em.merge(transaccionListTransaccion);
			}
			em.remove(transaccion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTransaccionEntities.
	 * 
	 * @return List<Transaccion>
	 */
	public List<Transaccion> findTransaccionEntities() {
		return findTransaccionEntities(true, -1, -1);
	}

	/**
	 * Method findTransaccionEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transaccion>
	 */
	public List<Transaccion> findTransaccionEntities(int maxResults, int firstResult) {
		return findTransaccionEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTransaccionEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transaccion>
	 */
	private List<Transaccion> findTransaccionEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Transaccion.class));
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
	 * Method findTransaccion.
	 * 
	 * @param id
	 *            Long
	 * @return Transaccion
	 */
	public Transaccion findTransaccion(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Transaccion.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTransaccionCount.
	 * 
	 * @return int
	 */
	public int getTransaccionCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Transaccion> rt = cq.from(Transaccion.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
