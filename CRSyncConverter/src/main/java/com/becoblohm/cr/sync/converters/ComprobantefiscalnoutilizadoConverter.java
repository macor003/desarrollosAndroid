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

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ComprobantefiscalnoutilizadoConverter implements ConverterInterface {

	/**
	 * 
	 */
	public ComprobantefiscalnoutilizadoConverter() {

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
		return new crjpa.Comprobantefiscalnoutilizado();
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
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#toServer(Object)
	 */
	@Override
	public SyncWrapper toServer(Object obj) throws ConverterException {
		crjpa.Comprobantefiscalnoutilizado posValue = (crjpa.Comprobantefiscalnoutilizado) obj;
		crjpa400.Comprobantefiscalnoutilizado serverValue = new crjpa400.Comprobantefiscalnoutilizado();
		
		SyncWrapper syncObject = new SyncWrapper();
		
		try{
			serverValue.setFecha(posValue.getFecha());
			serverValue.setId(posValue.getId());
			serverValue.setNumeroComprobante(posValue.getNumeroComprobante());
			serverValue.setIdSerialimpresora(posValue.getIdSerialimpresora());
			serverValue.setIdSesion(posValue.getIdSesion());
			serverValue.setIdComprobantefiscalpreimpreso(new crjpa400.Comprobantefiscalpreimpreso(posValue
					.getIdComprobantefiscalpreimpreso().getId()));
			serverValue.setAutorizante(posValue.getAutorizante());
			
			syncObject.setEntity(serverValue);
			syncObject.setId(posValue.getId());
			
		}catch(Exception e){
			throw new ConverterException("Comprobantefiscalnoutilizado", e.getMessage(), posValue.getId().toString());
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
