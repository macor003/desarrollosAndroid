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
public class RolloAuditoriaConverter implements ConverterInterface {

	/**
	 * 
	 */
	public RolloAuditoriaConverter() {

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

		return new crjpa.Rolloauditoria();
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
		crjpa.Rolloauditoria valueCaja = (crjpa.Rolloauditoria) obj;
		crjpa400.Rolloauditoria value400 = new crjpa400.Rolloauditoria();
		
		SyncWrapper syncObject = new SyncWrapper();
		
		try{
			value400.setCaja(valueCaja.getCaja());
			value400.setFechaCambio(valueCaja.getFechaCambio());
			value400.setId(valueCaja.getId());
			value400.setIdSerialimpresora(new crjpa400.Serialimpresora(valueCaja.getIdSerialimpresora().getId()));
			value400.setIdUsuario(new crjpa400.Usuario(valueCaja.getIdUsuario().getId()));
			value400.setPrimeraTransaccion(valueCaja.getPrimeraTransaccion());
			value400.setTienda(valueCaja.getTienda());
			value400.setUltimaTransaccion(valueCaja.getUltimaTransaccion());
			value400.setId(valueCaja.getId());
			
			syncObject.setEntity(value400);
			syncObject.setId(valueCaja.getId());
			
		}catch(Exception e){
			throw new ConverterException("Rolloauditoria", e.getMessage(), valueCaja.getId().toString());
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
