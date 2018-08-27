/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.sync.converters;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.DataModel;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

import crjpa.Perfil;
import crjpa.Perfilproceso;
import crjpa.Proceso;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class PerfilprocesoConverter implements ConverterInterface {

	/**
	 * 
	 */
	public PerfilprocesoConverter() {

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
	 * @param posType
	 *            String
	 * @return Object
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#fromServer(Object,
	 *      String)
	 */
	@Override
	public Object fromServer(Object obj, String posType) {
		DataModel dm = new DataModel();
		crjpa400.Perfilproceso serverValue = (crjpa400.Perfilproceso) obj;
		crjpa.Perfilproceso posValue = new Perfilproceso();
		posValue.setEstaactivo(String.valueOf(serverValue.getEstaactivo()));
		posValue.setFecha(serverValue.getFecha());
		posValue.setId(serverValue.getId());

		dm.setId(serverValue.getId().toString());

		crjpa.Perfil tmp = new Perfil(serverValue.getIdPerfil().getId());
		posValue.setIdPerfil(tmp);
		crjpa.Proceso tmp2 = new Proceso(serverValue.getIdProceso().getId());
		posValue.setIdProceso(tmp2);
		dm.setEntity(posValue);
		dm.setSyncDate(serverValue.getUltimasincronizacion());
		dm.setTableName(crjpa.Perfilproceso.class.getName().substring(
				crjpa.Perfilproceso.class.getName().lastIndexOf(".") + 1));

		if (serverValue.getTipoCaja() != null) {
			if (posType == null || !serverValue.getTipoCaja().toString().equals(posType)) {
				dm = null;
			} else {
				if (serverValue.getIdOriginal() != null) {
					posValue.setId(Long.valueOf(serverValue.getIdOriginal().toString()));
					dm.setForSpecificPosId(true);
				}
			}
		}
		return dm;
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
	public SyncWrapper toServer(Object obj) {

		return new SyncWrapper();
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
