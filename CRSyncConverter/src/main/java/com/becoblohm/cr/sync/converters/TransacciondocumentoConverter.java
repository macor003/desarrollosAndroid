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

import crjpa400.Transaccion;
import crjpa400.Transacciondocumento;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TransacciondocumentoConverter implements ConverterInterface {

	/**
	 * 
	 */
	public TransacciondocumentoConverter() {

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

		return new crjpa.Transacciondocumento();
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
		crjpa.Transacciondocumento transaccionCaja = (crjpa.Transacciondocumento) obj;
		crjpa400.Transacciondocumento transaccion400 = new Transacciondocumento();

		SyncWrapper syncObject = new SyncWrapper();
		
		try{
		transaccion400.setEsimpreso(transaccionCaja.getEsimpreso().charAt(0));
		transaccion400.setIdTipodocumento(new crjpa400.Tipodocumento(transaccionCaja.getIdTipodocumento().getId()));
		transaccion400.setIdTransaccion(new Transaccion(transaccionCaja.getIdTransaccion().getId()));
		transaccion400.setDocumento(transaccionCaja.getDocumento());
		transaccion400.setNumeroDocumento(transaccionCaja.getNumeroDocumento());
		transaccion400.setCliente(transaccionCaja.getCliente());
		
		
		syncObject.setEntity(transaccion400);
		syncObject.setId(transaccionCaja.getId());
		
		}catch(Exception e){
			throw new ConverterException("Transacciondocumento", e.getMessage(), transaccionCaja.getId().toString());
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
