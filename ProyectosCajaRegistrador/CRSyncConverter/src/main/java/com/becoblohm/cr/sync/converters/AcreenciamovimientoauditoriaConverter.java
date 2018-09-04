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
 * @version $Revision: 1.0 $
 */
public class AcreenciamovimientoauditoriaConverter implements ConverterInterface {

	/**
	 * 
	 */
	public AcreenciamovimientoauditoriaConverter() {
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

		return new crjpa.Auditoria();
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

		crjpa.Acreenciamovimientoauditoria posValue = (crjpa.Acreenciamovimientoauditoria) obj;
		crjpa400.Acreenciamovimientoauditoria serverValue = new crjpa400.Acreenciamovimientoauditoria();
		
		SyncWrapper syncObject = new SyncWrapper();
		
		try{
		serverValue.setFecha(posValue.getFecha());
		serverValue.setIdProceso(new crjpa400.Proceso(posValue.getIdProceso().getId()));
		serverValue.setIdTransaccion(new crjpa400.Acreenciamovimiento(posValue.getIdAcreenciamovimiento().getIpaId()));
		serverValue.setIdUsuario(new crjpa400.Usuario(posValue.getIdUsuario().getId()));
		
		syncObject.setEntity(serverValue);
		syncObject.getId();
		
		}catch (Exception e){
			throw new ConverterException("Acreenciamovimientoauditoria", e.getMessage(), posValue.getId().toString());
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
