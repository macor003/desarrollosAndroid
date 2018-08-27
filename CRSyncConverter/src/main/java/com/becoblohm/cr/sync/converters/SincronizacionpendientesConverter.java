/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.sync.converters;

import java.util.Calendar;
import java.util.HashMap;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.ConverterException;
import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.DataModel;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.models.SyncWrapperComposed;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class SincronizacionpendientesConverter implements ConverterInterface {

	private String posNumber;

	/**
	 * 
	 */
	public SincronizacionpendientesConverter() {

	}

	/**
	 * Method fromServer.
	 * 
	 * @param obj Object
	 * @param posId String
	 * @return Object
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#fromServer(Object,
	 *      String)
	 */
	@Override
	public Object fromServer(Object obj, String posId) {
		DataModel dm = new DataModel();
		return dm;
	}

	/**
	 * Method toServer.
	 * 
	 * @param obj Object
	 * @return Object
	 * @throws ConverterException 
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#toServer(Object)
	 */
	@Override
	public SyncWrapper toServer(Object obj) throws ConverterException {
		crjpa.Sincronizacionpendientes posValue = (crjpa.Sincronizacionpendientes) obj;
		crjpa400.Sincronizacionpendientes serverValue = new crjpa400.Sincronizacionpendientes(posValue
				.getIdSincronziacion().getId(), Long.valueOf(posNumber), posValue.getIdTabla());
		
		SyncWrapperComposed syncObject = new SyncWrapperComposed();
		
		try{
			serverValue.setMensajeError(posValue.getMensajeError());

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(posValue.getFecha());

			serverValue.setFecha(calendar);
			
			syncObject.setEntity(serverValue);
			HashMap<String, Object> composedKeySet = new HashMap<String, Object>();
			composedKeySet.put("idSincronziacion.id", posValue.getIdSincronziacion().getId());
			composedKeySet.put("idTabla", posValue.getIdTabla());
			syncObject.setComposedKeySet(composedKeySet);
			
		}catch(Exception e){
			throw new ConverterException("Sincronizacionpendietes", e.getMessage(), "IdSincronizacion"+posValue.getIdSincronziacion().getId().toString() 
					+ "IdTabla"+posValue.getIdTabla());
		}
		return syncObject;
	}

	/**
	 * Method getPosEmf.
	 * @return EntityManagerFactory
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#getPosEmf()
	 */
	@Override
	public EntityManagerFactory getPosEmf() {
		return ConverterSingleton.getPosEMF();
	}

	/**
	 * Method getServerEmf.
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
	 * @param posNumber String
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#setPosNumber(String)
	 */
	@Override
	public void setPosNumber(String posNumber) {
		this.posNumber = posNumber;
	}
}
