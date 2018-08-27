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
public class AuditoriaConverter implements ConverterInterface {

	/**
	 * 
	 */
	public AuditoriaConverter() {
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

		crjpa.Auditoria posValue = (crjpa.Auditoria) obj;
		crjpa400.Auditoria serverValue = new crjpa400.Auditoria();

		SyncWrapper syncObject = new SyncWrapper();

		try {
			serverValue.setId(posValue.getId());
			serverValue.setCaja(posValue.getCaja());
			serverValue.setClase(posValue.getClase());
			serverValue.setDescripcion(posValue.getDescripcion());
			serverValue.setFecha(posValue.getFecha());
			serverValue.setFichaautorizante(posValue.getFichaautorizante());
			serverValue.setFichacajero(posValue.getFichacajero());
			serverValue.setHora(posValue.getHora());
			serverValue.setIdProceso(posValue.getIdProceso());
			serverValue.setIdTabla(posValue.getIdTabla());
			serverValue.setProceso(posValue.getProceso());
			serverValue.setTabla(posValue.getTabla());
			serverValue.setTienda(posValue.getTienda());
			serverValue.setTipoautorizacion(posValue.getTipoautorizacion());
			serverValue.setTipocaja(posValue.getTipocaja());
			serverValue.setTipolog(posValue.getTipolog());
			serverValue.setTipotransaccion(posValue.getTipotransaccion());
			syncObject.setEntity(serverValue);
			syncObject.setId(posValue.getId());

		} catch (Exception e) {
			throw new ConverterException("Auditoria", e.getMessage(), String.valueOf(posValue.getId()));
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
