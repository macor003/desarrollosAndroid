/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.sync.converters;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.crjpa.controller.ClienteJpaController;
import com.becoblohm.cr.interfaces.ConverterException;
import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.sync.models.DataModel;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;
import com.becoblohm.cr.utils.CRUtils;

import crjpa.Cliente;

/**
 * 
 * @version $Revision: 1.0 $
 */
public class ClienteConverter implements ConverterInterface {

	/**
	 * 
	 */
	public ClienteConverter() {

	}

	/**
	 * Method fromServer.
	 * 
	 * @param obj
	 *            Client
	 * @return Client
	 */
	public Client fromServer(Client obj) {

		com.epa.cr.legacy.ClienteJpaController jpa400 = new com.epa.cr.legacy.ClienteJpaController(getServerEmf());
		ClienteJpaController jpa = new ClienteJpaController(getPosEmf());

		DataModel fs = (DataModel) this.fromServer(jpa400.toJpa(obj, true), null);
		crjpa.Cliente client = (Cliente) fs.getEntity();
		Client result = jpa.fromJPA(client);
		return result;
	}

	/**
	 * Method fromServer.
	 * 
	 * @param obj
	 *            Client
     *  @param emf  EntityManagerFactory
	 * @return Client
	 */
	public Client fromServer(Client obj, EntityManagerFactory emf) {

		com.epa.cr.legacy.ClienteJpaController jpa400 = new com.epa.cr.legacy.ClienteJpaController(emf);
		ClienteJpaController jpa = new ClienteJpaController(emf);

		DataModel fs = (DataModel) this.fromServer(jpa400.toJpa(obj, true), null);
		crjpa.Cliente client = (Cliente) fs.getEntity();
		Client result = jpa.fromJPA(client);
		return result;
	}
	
	/**
	 * Method fromServer.
	 * 
	 * @param obj
	 *            Object
	 * @param posId
	 *            String
	 * @return Object
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#fromServer(Object,
	 *      String)
	 */
	@Override
	public Object fromServer(Object obj, String posId) {

		MorosidadConverter debtsConverter = new MorosidadConverter();

		DataModel dm = new DataModel();
		crjpa400.Cliente serverValue = (crjpa400.Cliente) obj;
		crjpa.Cliente posvalue = new Cliente();

		posvalue.setCorreoElectronico(serverValue.getCorreoElectronico());
		posvalue.setDireccion(serverValue.getDireccion());
		posvalue.setDireccionFiscal(serverValue.getDireccionFiscal());
		posvalue.setEsagenteretencion(serverValue.getEsagenteretencion().toString());
		posvalue.setFecha(serverValue.getFecha());
		posvalue.setFechaActualizacion(serverValue.getFechaActualizacion());

		if (serverValue.getFicha() == null) {
			posvalue.setFicha(BigInteger.ZERO);
		} else {
			posvalue.setFicha(serverValue.getFicha());
		}

		posvalue.setNombre(CRUtils.removeSpecialChars(serverValue.getNombre()));
		posvalue.setNumeroIdentificacionCliente(serverValue.getNumeroIdentificacionCliente());

		dm.setId(serverValue.getNumeroIdentificacionCliente());

		if (serverValue.getNumeroIdentificacionTributario() == null) {
			posvalue.setNumeroIdentificacionTributario(serverValue.getNumeroIdentificacionCliente());
		} else {
			posvalue.setNumeroIdentificacionTributario(serverValue.getNumeroIdentificacionTributario());
		}

		posvalue.setTelefono(serverValue.getTelefono());
		posvalue.setEsdiplomatico(String.valueOf(serverValue.getEsdiplomatico()));
		posvalue.setEstasincronizado("S");
		posvalue.setEsgrande("" + serverValue.getEsgrande());

		posvalue.setIdTipocliente(new crjpa.Tipocliente(serverValue.getIdTipocliente().getId()));
		if (serverValue.getIdExoneradotipo() != null
				&& (serverValue.getIdExoneradotipo().getId() > 0 && serverValue.getIdExoneradotipo().getId() < 999)) {
			posvalue.setIdExoneradotipo(new crjpa.Exoneradotipo(serverValue.getIdExoneradotipo().getId()));
		}
		if (serverValue.getIdGiroactividadeconomica() != null) {
			if (serverValue.getIdGiroactividadeconomica().getId() > 0) {
				posvalue.setIdGiroactividadeconomica(new crjpa.Giroactividadeconomica(serverValue
						.getIdGiroactividadeconomica().getId()));
			}
		}


		ArrayList<crjpa.Morosidad> debtsList = new ArrayList<crjpa.Morosidad>();
		if (serverValue.getMorosidadList() != null) {
			for (crjpa400.Morosidad tmp2 : serverValue.getMorosidadList()) {
				debtsList.add((crjpa.Morosidad) debtsConverter.fromServer(tmp2, null));
			}
		}

		posvalue.setMorosidadList(debtsList);

		dm.setEntity(posvalue);
		dm.setSyncDate(serverValue.getUltimasincronizacion());
		dm.setTableName(crjpa.Cliente.class.getName().substring(crjpa.Cliente.class.getName().lastIndexOf(".") + 1));

		return dm;
	}

	/**
	 * Method toServer.
	 * 
	 * @param obj
	 *            Object
	 * @return Object
	 * @throws ConverterException 
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#toServer(Object)
	 */
	@Override
	public SyncWrapper toServer(Object obj) throws ConverterException {

		ClientemensajeConverter messageConverter = new ClientemensajeConverter();
		MorosidadConverter debtsConverter = new MorosidadConverter();

		crjpa.Cliente clienteCaja = (crjpa.Cliente) obj;
		crjpa400.Cliente cliente400 = new crjpa400.Cliente();
		
		SyncWrapper syncObject = new SyncWrapper();
		
		try{
			if (clienteCaja.getIdExoneradotipo() != null) {
				crjpa400.Exoneradotipo exoneradoTipo = new crjpa400.Exoneradotipo(clienteCaja.getIdExoneradotipo().getId());
				cliente400.setIdExoneradotipo(exoneradoTipo);
			} else {
				crjpa400.Exoneradotipo exoneradoTipo = new crjpa400.Exoneradotipo(999l);
				cliente400.setIdExoneradotipo(exoneradoTipo);
			}

			if (clienteCaja.getIdGiroactividadeconomica() != null) {
				crjpa400.Giroactividadeconomica giroActividadeconomica = new crjpa400.Giroactividadeconomica(clienteCaja
						.getIdGiroactividadeconomica().getId());
				cliente400.setIdGiroactividadeconomica(giroActividadeconomica);
			} else {
				crjpa400.Giroactividadeconomica giroActividadeconomica = new crjpa400.Giroactividadeconomica(9999999l);
				cliente400.setIdGiroactividadeconomica(giroActividadeconomica);
			}

			cliente400.setIdTipocliente(new crjpa400.Tipocliente(clienteCaja.getIdTipocliente().getId()));

			cliente400.setNumeroIdentificacionTributario(clienteCaja.getNumeroIdentificacionTributario());
			cliente400.setCorreoElectronico(clienteCaja.getCorreoElectronico());
			cliente400.setDireccion(clienteCaja.getDireccion());
			cliente400.setDireccionFiscal(clienteCaja.getDireccionFiscal());
			if (clienteCaja.getEsagenteretencion() != null) {
				cliente400.setEsagenteretencion(clienteCaja.getEsagenteretencion().charAt(0));
			}
			cliente400.setFecha(clienteCaja.getFecha());
			cliente400.setFechaActualizacion(clienteCaja.getFechaActualizacion());

			if (clienteCaja.getFicha() != null && clienteCaja.getFicha().compareTo(BigInteger.ZERO) > 0) {
				cliente400.setFicha(clienteCaja.getFicha());
			} else {
				cliente400.setFicha(null);
			}

			cliente400.setNombre(clienteCaja.getNombre());
			cliente400.setNumeroIdentificacionCliente(clienteCaja.getNumeroIdentificacionCliente());
			cliente400.setTelefono(clienteCaja.getTelefono());
			cliente400.setEsdiplomatico(clienteCaja.getEsdiplomatico().charAt(0));
			cliente400.setEsgrande(clienteCaja.getEsgrande().charAt(0));

			ArrayList<crjpa400.Clientemensaje> externalCodeList = new ArrayList<crjpa400.Clientemensaje>();
			for (crjpa.Clientemensaje tmp2 : clienteCaja.getClientemensajeList()) {
				externalCodeList.add((crjpa400.Clientemensaje) messageConverter.toServer(tmp2).getEntity());
			}
			cliente400.setClientemensajeList(externalCodeList);

			ArrayList<crjpa400.Morosidad> debtsList = new ArrayList<crjpa400.Morosidad>();
			for (crjpa.Morosidad tmp2 : clienteCaja.getMorosidadList()) {
				debtsList.add((crjpa400.Morosidad) debtsConverter.toServer(tmp2).getEntity());
			}
			cliente400.setMorosidadList(debtsList);
			Calendar calTmp = Calendar.getInstance();
			calTmp.setTime(new Date());
			cliente400.setUltimasincronizacion(calTmp);
			
			syncObject.setEntity(cliente400);
			syncObject.setId(clienteCaja.getNumeroIdentificacionCliente());
			
		}catch(Exception e){
			throw new ConverterException("Cliente", e.getMessage(), clienteCaja.getNumeroIdentificacionCliente());
		}
		return syncObject;
	}

	/**
	 * Method getPosEmf.
	 * 
	 * @return EntityManagerFactory
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#getPosEmf()
	 */
	@Override
	public EntityManagerFactory getPosEmf() {
		return ConverterSingleton.getPosEMF();
	}

	/**
	 * Method getServerEmf.
	 * 
	 * @return EntityManagerFactory
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#getServerEmf()
	 */
	@Override
	public EntityManagerFactory getServerEmf() {
		return ConverterSingleton.getServerEMF();
	}

	/**
	 * Method setPosNumber.
	 * 
	 * @param posNumber
	 *            String
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#setPosNumber(String)
	 */
	@Override
	public void setPosNumber(String posNumber) {
	}
	
	public Client fromServer(Client obj, EntityManagerFactory emfServer, EntityManagerFactory emfPos) {
        com.epa.cr.legacy.ClienteJpaController jpa400 = new com.epa.cr.legacy.ClienteJpaController(emfServer);
        ClienteJpaController jpa = new ClienteJpaController(emfPos);

        DataModel fs = (DataModel) this.fromServer(jpa400.toJpa(obj, true), null);
        crjpa.Cliente client = (Cliente) fs.getEntity();
        Client result = jpa.fromJPA(client);
        return result;
    }
}
