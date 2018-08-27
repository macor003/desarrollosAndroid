/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Cliente;
import crjpa400.Exoneradotipo;
import crjpa400.Giroactividadeconomica;
import crjpa400.Morosidad;
import crjpa400.Opcion;
import crjpa400.OpcionJpaController;
import crjpa400.Tipocliente;

/**
 */
public class ClienteJpaController extends AbstractJPAController {

	/**
	 * Field ID_REGISTRO_NULL_EXO_TIPO_400. (value is 142)
	 */
	private static final long ID_REGISTRO_NULL_EXO_TIPO_400 = 142L;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6989915668252884848L;

	/**
	 * Field emf.
	 */
	private final EntityManagerFactory emf;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Cliente";
	/**
	 * Field jpacontroller.
	 */
	private final crjpa400.ClienteJpaController jpacontroller;

	/**
	 * Constructor for ClienteJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ClienteJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
		this.jpacontroller = new crjpa400.ClienteJpaController(emf);
	}

	/**
	 * Method findClienteByNumberIdJPA.
	 * 
	 * @param numberId
	 *            String
	 * @return Cliente
	 */
	protected Cliente findClienteByNumberIdJPA(String numberId) {

		EntityManager em = emf.createEntityManager();
		Cliente result = null;
		try {
			Query query = em.createNamedQuery("Cliente.findByNumeroIdentificacion");
			query.setParameter("numeroIdentificacion", numberId);
			query.setMaxResults(1);
			result = (Cliente) query.getSingleResult();
		} catch (Exception ex) {
			result = null;
		} finally {
			em.close();
		}
		return result;

	}

	/**
	 * Method findClienteByNumberId.
	 * 
	 * @param numberId
	 *            String
	 * @return Client
	 */
	public Client findClienteByNumberId(String numberId) {

		Client customer = null;
		EntityManager em = emf.createEntityManager();
		Cliente result = null;
		try {

			logger.info("Buscando cliente en servidor =  " + numberId);
			Query query = em.createNamedQuery("Cliente.findByNumeroIdentificacionCliente");
			query.setParameter("numeroIdentificacionCliente", numberId);
			query.setMaxResults(1);
			result = (Cliente) query.getSingleResult();
			customer = fromJpa(result);
		} catch (NoResultException nre) {
			logger.error("Error -> ", nre);
		} catch (Exception ex) {
			logger.error("Error -> ", ex);
			result = null;
		} finally {
			em.close();
		}
		return customer;

	}

	/**
	 * Method findClienteByCashierId.
	 * 
	 * @param cashierId
	 *            String
	 * @return Client
	 */
	public Client findClienteByCashierId(String cashierId) {

		Client customer = null;
		EntityManager em = emf.createEntityManager();
		Cliente result = null;
		try {

			System.out.println("Buscando colaborador  =  " + cashierId);
			Query query = em.createNamedQuery("Cliente.findByFicha");
			query.setParameter("ficha", new BigInteger(cashierId));
			query.setMaxResults(1);
			result = (Cliente) query.getSingleResult();
			customer = fromJpa(result);
		} catch (Exception ex) {
			System.out.println("Error buscando cliente ");
			ex.printStackTrace();
			result = null;
		} finally {
			em.close();
		}
		return customer;

	}

	/**
	 * Method fromJpa.
	 * 
	 * @param clientJpa
	 *            Cliente
	 * @return Client
	 */
	public Client fromJpa(Cliente clientJpa) {
		ExoneradotipoJpaController exonerationController = new ExoneradotipoJpaController(this.emf);
		TipoclienteJpaController clientTypeController = new TipoclienteJpaController(this.emf);
		ClientemensajeJpaController messageController = new ClientemensajeJpaController(this.emf);
		GiroactividadeconomicaJpaController giro = new GiroactividadeconomicaJpaController(this.emf);
		Client client = new Client();
		if (clientJpa != null) {
			client.setAddress(clientJpa.getDireccion());
			client.setClientType(clientTypeController.toJPA(clientJpa.getIdTipocliente()));
			if (clientJpa.getIdGiroactividadeconomica() != null) {
				client.setEconomicActivity(giro.fromJPA(clientJpa.getIdGiroactividadeconomica()));
			}
			client.setEmail(clientJpa.getCorreoElectronico());
			if (clientJpa.getFicha() != null) {
				client.setEmployeeId(clientJpa.getFicha().longValue());
			}

			if (clientJpa.getIdExoneradotipo() != null
					&& clientJpa.getIdExoneradotipo().getId().longValue() != getIdExoneradotipoNull()) {
				client.setExonerationType(exonerationController.fromJPA(clientJpa.getIdExoneradotipo()));
			}
			client.setFiscalAddress(clientJpa.getDireccionFiscal());
			client.setFiscalId(clientJpa.getNumeroIdentificacionTributario());
			// client.setId(Long.valueOf(clientJpa.getNumeroIdentificacionCliente()));
			client.setIdNumber(clientJpa.getNumeroIdentificacionCliente());
			client.setName(clientJpa.getNombre());
			client.setPhone(clientJpa.getTelefono());
			client.setRegisterDate(clientJpa.getFecha());
			client.setRetentionAgent(clientJpa.getEsagenteretencion().toString());
			client.setTaxPayer(ActiveValues
					.valueOf(new Character(clientJpa.getIdTipocliente().getEscontribuyente()).toString()).getValue());
			client.setUpdateDate(clientJpa.getFechaActualizacion());
			client.setMessages(messageController.fromJPA(clientJpa.getClientemensajeList()));
			ArrayList<Long> nonAllowedPayments = new ArrayList<Long>();
			if (clientJpa.getMorosidadList() != null) {
				for (Morosidad m : clientJpa.getMorosidadList()) {
					// nonAllowedPayments.add(m.getIdPagonopermitido().getId());
				}
			}
			client.setNonAllowedPayments(nonAllowedPayments);
			client.setDiplomatic(ActiveValues.get(String.valueOf(clientJpa.getEsdiplomatico())).getValue());
		}
		return client;
	}

	public Client fromJpa(Cliente clientJpa, long getIdExoneradotipoNull) {
		ExoneradotipoJpaController exonerationController = new ExoneradotipoJpaController(this.emf);
		TipoclienteJpaController clientTypeController = new TipoclienteJpaController(this.emf);
		ClientemensajeJpaController messageController = new ClientemensajeJpaController(this.emf);
		GiroactividadeconomicaJpaController giro = new GiroactividadeconomicaJpaController(this.emf);
		Client client = new Client();
		if (clientJpa != null) {
			client.setAddress(clientJpa.getDireccion());
			client.setClientType(clientTypeController.toJPA(clientJpa.getIdTipocliente()));
			if (clientJpa.getIdGiroactividadeconomica() != null) {
				client.setEconomicActivity(giro.fromJPA(clientJpa.getIdGiroactividadeconomica()));
			}
			client.setEmail(clientJpa.getCorreoElectronico());
			if (clientJpa.getFicha() != null) {
				client.setEmployeeId(clientJpa.getFicha().longValue());
			}

			if (clientJpa.getIdExoneradotipo() != null
					&& clientJpa.getIdExoneradotipo().getId().longValue() != getIdExoneradotipoNull) {
				client.setExonerationType(exonerationController.fromJPA(clientJpa.getIdExoneradotipo()));
			}
			client.setFiscalAddress(clientJpa.getDireccionFiscal());
			client.setFiscalId(clientJpa.getNumeroIdentificacionTributario());
			// client.setId(Long.valueOf(clientJpa.getNumeroIdentificacionCliente()));
			client.setIdNumber(clientJpa.getNumeroIdentificacionCliente());
			client.setName(clientJpa.getNombre());
			client.setPhone(clientJpa.getTelefono());
			client.setRegisterDate(clientJpa.getFecha());
			client.setRetentionAgent(clientJpa.getEsagenteretencion().toString());
			client.setTaxPayer(ActiveValues
					.valueOf(new Character(clientJpa.getIdTipocliente().getEscontribuyente()).toString()).getValue());
			client.setUpdateDate(clientJpa.getFechaActualizacion());
			client.setMessages(messageController.fromJPA(clientJpa.getClientemensajeList()));
			ArrayList<Long> nonAllowedPayments = new ArrayList<Long>();
			if (clientJpa.getMorosidadList() != null) {
				for (Morosidad m : clientJpa.getMorosidadList()) {
					// nonAllowedPayments.add(m.getIdPagonopermitido().getId());
				}
			}
			client.setNonAllowedPayments(nonAllowedPayments);
			client.setDiplomatic(ActiveValues.get(String.valueOf(clientJpa.getEsdiplomatico())).getValue());
		}
		return client;
	}

	/**
	 * Method getIdExoneradotipoNull.
	 * 
	 * @return long
	 */
	private long getIdExoneradotipoNull() {
		OpcionJpaController opcionJpaController = new OpcionJpaController(emf);
		Opcion idExoneradoNull = opcionJpaController.findOpcion(ID_REGISTRO_NULL_EXO_TIPO_400);
		long valor = 0L;
		if (idExoneradoNull != null) {
			valor = Long.valueOf(idExoneradoNull.getValor().trim());
		}
		return valor;
	}

	/**
	 * Method toJpa.
	 * 
	 * @param client
	 *            Client
	 * @param isFromCreate
	 *            boolean
	 * @return Cliente
	 */
	public Cliente toJpa(Client client, boolean isFromCreate) {
		Cliente cl = new Cliente();
		TipoclienteJpaController tipoCliente = new TipoclienteJpaController(this.emf);
		GiroactividadeconomicaJpaController giroActividad = new GiroactividadeconomicaJpaController(this.emf);

		// cl.setContribuyente("");
		cl.setCorreoElectronico(client.getEmail());
		cl.setDireccion(client.getAddress());
		cl.setDireccionFiscal(client.getFiscalAddress());
		// cl.setEsagenteretencion(client.isRetentionAgent());
		if (isFromCreate) {
			cl.setFecha(new Date());
			cl.setFechaActualizacion(new Date());
		} else {
			cl.setFecha(client.getRegisterDate());
			cl.setFechaActualizacion(client.getUpdateDate());
		}
		cl.setUltimasincronizacion(Calendar.getInstance());
		if (client.getBigClient() != null && client.getBigClient().trim().length() > 0) {
			cl.setEsgrande(client.getBigClient().charAt(0));
		} else {
			cl.setEsgrande('N');
		}

		cl.setFicha(BigInteger.valueOf(client.getEmployeeId()));

		// if (client.getExonerationType() != null) {
		// ExoneradotipoJpaController exjpa = new
		// ExoneradotipoJpaController(this.emf);
		// client.setExonerationType(exjpa.findByCode(client.getExonerationType().getId()));
		//
		// }

		if (client.getExonerationType() != null) {
			cl.setIdExoneradotipo(new Exoneradotipo(client.getExonerationType().getId()));
		} else {
			cl.setIdExoneradotipo(new Exoneradotipo(999l));
		}

		// if (client.getClientType().getId() > 0) {
		// cl.setIdGiroactividadeconomica(giroActividad.findGiroactividadeconomicaByCode(client.getClientType()
		// .getId()));
		// }

		if (client.getEconomicActivity() == null || client.getEconomicActivity().getId() < 1) {
			cl.setIdGiroactividadeconomica(new Giroactividadeconomica(new Long(9999999)));
		} else {
			cl.setIdGiroactividadeconomica(new Giroactividadeconomica(client.getEconomicActivity().getId()));
		}

		cl.setIdTipocliente(new Tipocliente(new Long(client.getClientType().getId())));
		cl.setNumeroIdentificacionTributario(client.getFiscalId());
		cl.setNombre(client.getName());
		cl.setNumeroIdentificacionCliente(client.getIdNumber());
		cl.setTelefono(client.getPhone());
		cl.setEsagenteretencion(client.getRetentionAgent().charAt(0));
		ActiveValues tmp = ActiveValues.get(client.getDiplomatic());
		cl.setEsdiplomatico(tmp.getString().charAt(0));

		return cl;

	}

	/**
	 * Method create.
	 * 
	 * @param client
	 *            Client
	 * @throws JpaException
	 */
	public void create(Client client) throws JpaException {

		try {
			jpacontroller.create(toJpa(client, true));

		} catch (Exception e) {
			e.printStackTrace();
			throw new JpaException();
		}
	}

	/**
	 * Method createFromPos.
	 * 
	 * @param client
	 *            Client
	 * @throws JpaException
	 */
	public void createFromPos(Client client) throws JpaException {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Cliente cl = toJpa(client, true);
			cl.setFicha(null);
			em.persist(cl);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new JpaException(e);
		}
	}

	/**
	 * Method createClientNativeQuery.
	 * 
	 * @param client
	 *            Client
	 * @param esquema
	 *            String
	 * @throws JpaException
	 */
	public void createClientNativeQuery(Client client, String esquema) throws JpaException {
		boolean created = false;
		ClienteJpaController clienteJpaController = new ClienteJpaController(this.emf);
		Cliente clientJpa = clienteJpaController.toJpa(client, true);

		EntityManager em = emf.createEntityManager();

		try {
			System.out.println("creando cliente con nativequery");
			Query query = em.createNativeQuery("INSERT INTO " + esquema + ".CLIENTE "
					+ "(NUMERO_IDENTIFICACION_CLIENTE, FICHA, DIRECCION, ESGRANDE, FECHA, "
					+ "NUMERO_IDENTIFICACION_TRIBUTARIO, ESAGENTERETENCION, TELEFONO, "
					+ "ULTIMASINCRONIZACION, DIRECCION_FISCAL, ESDIPLOMATICO, NOMBRE, "
					+ "CORREO_ELECTRONICO, FECHA_ACTUALIZACION, ID_EXONERADOTIPO, "
					+ "ID_GIROACTIVIDADECONOMICA, ID_TIPOCLIENTE) " + "VALUES " + "(?, ?, ?, ?, ?, " + "?, ?, ?, "
					+ "?, ?, ?, ?, " + "?, ?, ?, " + "?, ?)");
			query.setParameter(1, clientJpa.getNumeroIdentificacionCliente());
			if (clientJpa.getFicha() == null || clientJpa.getFicha().compareTo(BigInteger.ZERO) == 0) {
				query.setParameter(2, null);
			} else {
				query.setParameter(2, clientJpa.getFicha());
			}
			query.setParameter(3, clientJpa.getDireccion());
			query.setParameter(4, clientJpa.getEsgrande());
			query.setParameter(5, clientJpa.getFecha());
			query.setParameter(6, clientJpa.getNumeroIdentificacionTributario());
			if (clientJpa.getEsagenteretencion() != null) {
				query.setParameter(7, clientJpa.getEsagenteretencion());
			} else {
				query.setParameter(7, "");
			}
			if (clientJpa.getTelefono() != null) {
				query.setParameter(8, clientJpa.getTelefono());
			} else {
				query.setParameter(8, "");
			}

			query.setParameter(9, clientJpa.getUltimasincronizacion());

			if (clientJpa.getDireccionFiscal() != null) {
				query.setParameter(10, clientJpa.getDireccionFiscal());
			} else {
				query.setParameter(10, "");
			}
			query.setParameter(11, clientJpa.getEsdiplomatico());
			query.setParameter(12, clientJpa.getNombre());
			if (clientJpa.getCorreoElectronico() != null) {
				query.setParameter(13, clientJpa.getCorreoElectronico());
			} else {
				query.setParameter(13, "");
			}
			query.setParameter(14, clientJpa.getFechaActualizacion());
			if (clientJpa.getIdExoneradotipo() != null) {
				query.setParameter(15, clientJpa.getIdExoneradotipo().getId());
			} else {
				query.setParameter(15, null);
			}
			if (clientJpa.getIdGiroactividadeconomica() != null) {
				query.setParameter(16, clientJpa.getIdGiroactividadeconomica().getId());
			} else {
				query.setParameter(16, null);
			}
			query.setParameter(17, clientJpa.getIdTipocliente().getId());
			em.getTransaction().begin();
			int tmp = query.executeUpdate();
			System.out.println("resultado: " + tmp);
			em.getTransaction().commit();
			created = true;
		} catch (Exception ex) {
			created = false;
			ex.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			throw new JpaException();
		} finally {
			em.close();
		}

	}

	/**
	 * Method findClienteByCashierId.
	 *
	 * @param cashierId
	 *            String
	 * @param getIdExoneradotipoNull
	 * @return Client
	 */
	public Client findClienteByCashierId(String cashierId, long getIdExoneradotipoNull) {

		Client customer = null;
		EntityManager em = emf.createEntityManager();
		Cliente result = null;
		try {

			System.out.println("Buscando colaborador  =  " + cashierId);
			Query query = em.createNamedQuery("Cliente.findByFicha");
			query.setParameter("ficha", new BigInteger(cashierId));
			query.setMaxResults(1);
			result = (Cliente) query.getSingleResult();
			customer = fromJpa(result, getIdExoneradotipoNull);
		} catch (Exception ex) {
			System.out.println("Error buscando cliente ");
			ex.printStackTrace();
			result = null;
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return customer;

	}

	/**
	 * Method findClienteByNumberId.
	 *
	 * @param numberId
	 *            String
	 * @param getIdExoneradotipoNull
	 * @return Client
	 */
	public Client findClienteByNumberId(String numberId, Long getIdExoneradotipoNull) {

		Client customer = null;
		EntityManager em = emf.createEntityManager();
		Cliente result = null;
		try {

			System.out.println("Buscando cliente  =  " + numberId);
			Query query = em.createNamedQuery("Cliente.findByNumeroIdentificacionCliente");
			query.setParameter("numeroIdentificacionCliente", numberId);
			query.setMaxResults(1);
			result = (Cliente) query.getSingleResult();
			customer = fromJpa(result, getIdExoneradotipoNull);
		} catch (NoResultException nre) {
			System.out.println("NoResultException -> Cliente no encontrado en el servidor");
		} catch (Exception ex) {
			System.out.println("Error buscando cliente ");
			ex.printStackTrace();
			result = null;
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return customer;

	}

}
