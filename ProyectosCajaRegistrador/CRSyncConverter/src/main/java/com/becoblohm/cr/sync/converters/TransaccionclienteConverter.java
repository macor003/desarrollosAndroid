/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.sync.converters;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.ConverterException;
import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

import crjpa.Transaccioncliente;
import crjpa400.Giroactividadeconomica;
import crjpa400.Transaccion;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TransaccionclienteConverter implements ConverterInterface {

	/**
	 * 
	 */
	public TransaccionclienteConverter() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.becoblohm.cr.sync.converters.ConverterInterface#fromServer()
	 */
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
		return new crjpa.Transaccioncliente();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.becoblohm.cr.sync.converters.ConverterInterface#toServer()
	 */
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

		crjpa.Transaccioncliente posValue = (Transaccioncliente) obj;
		crjpa400.Transaccioncliente serverValue = new crjpa400.Transaccioncliente();
		
		SyncWrapper syncObject = new SyncWrapper();

		try{
		serverValue.setDireccion(posValue.getDireccion());
		serverValue.setDireccionFiscal(posValue.getDireccionFiscal());
		serverValue.setNumeroIdentificacionCliente(new crjpa400.Cliente(posValue.getNumeroIdentificacionCliente()
				.getNumeroIdentificacionCliente()));
		if (posValue.getIdGiroactividadeconomica() != null) {
			serverValue.setIdGiroactividadeconomica(new Giroactividadeconomica(posValue.getIdGiroactividadeconomica()
					.getId()));
		}
		serverValue.setIdTransaccion(new Transaccion(posValue.getIdTransaccion().getId()));
		serverValue.setNombre(posValue.getNombre());
		serverValue.setTelefono(posValue.getTelefono());
		
		syncObject.setEntity(serverValue);
		syncObject.setId(posValue.getId());
		
		}catch(Exception e){
			throw new ConverterException("Transaccioncliente", e.getMessage(), posValue.getId().toString());
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

}
