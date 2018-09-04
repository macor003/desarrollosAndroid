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

import crjpa.Formadepagopuntoagil;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class FormadepagopuntoagilConverter implements ConverterInterface {

	/**
	 * 
	 */
	public FormadepagopuntoagilConverter() {

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
		return new crjpa.Formadepagopuntoagil();
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
		crjpa.Formadepagopuntoagil posValue = (Formadepagopuntoagil) obj;
		crjpa400.Formadepagopuntoagil serverValue = new crjpa400.Formadepagopuntoagil();
		
		SyncWrapper syncObject = new SyncWrapper();
		
		try{
		serverValue.setArchivo(posValue.getArchivo());
		serverValue.setCodigoRespuesta(posValue.getCodigoRespuesta());
		serverValue.setFecha(posValue.getFecha());
		serverValue.setMensajeRespuesta(posValue.getMensajeRespuesta());
		serverValue.setSecuencia(posValue.getSecuencia());
		serverValue.setTarjeta(posValue.getTarjeta());
		serverValue.setTitular(posValue.getTitular());
		serverValue.setVtid(posValue.getVtid());
		
		syncObject.setEntity(serverValue);
		syncObject.setId(posValue.getId());
		
		}catch (Exception e){
			throw new ConverterException("Formadepagopuntoagil", e.getMessage(), posValue.getId().toString());
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
