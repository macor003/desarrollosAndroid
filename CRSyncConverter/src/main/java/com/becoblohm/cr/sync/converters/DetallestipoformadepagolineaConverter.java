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

import crjpa.Detalletipoformadepago;
import crjpa.Detalletipoformadepagolinea;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class DetallestipoformadepagolineaConverter implements ConverterInterface {

	/**
	 * 
	 */
	public DetallestipoformadepagolineaConverter() {

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
		crjpa400.Detalletipoformadepagolinea serverValue = (crjpa400.Detalletipoformadepagolinea) obj;
		crjpa.Detalletipoformadepagolinea posValue = new Detalletipoformadepagolinea();
		posValue.setDescripcion(serverValue.getDescripcion());
		posValue.setId(serverValue.getId());
		crjpa.Detalletipoformadepago tmp = new Detalletipoformadepago(serverValue.getIdDetalletipoformadepago().getId());
		posValue.setIdDetalletipoformadepago(tmp);
		posValue.setMonto(serverValue.getMonto());

		return posValue;
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

		crjpa.Detalletipoformadepagolinea posValue = (Detalletipoformadepagolinea) obj;
		crjpa400.Detalletipoformadepagolinea serverValue = new crjpa400.Detalletipoformadepagolinea();
		
		SyncWrapper syncObject = new SyncWrapper();
		
		try{
		serverValue.setDescripcion(posValue.getDescripcion());
		serverValue.setId(posValue.getId());
		crjpa400.Detalletipoformadepago tmp = new crjpa400.Detalletipoformadepago(posValue
				.getIdDetalletipoformadepago().getId());
		serverValue.setIdDetalletipoformadepago(tmp);
		serverValue.setMonto(posValue.getMonto());
		
		syncObject.setEntity(serverValue);
		syncObject.setId(posValue.getId());
		
		}catch (Exception e){
			throw new ConverterException("Detalletipoformadepagolinea", e.getMessage(), posValue.getId().toString());
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
