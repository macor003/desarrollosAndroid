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

import crjpa.Tipoacreencia;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TipoacreenciaoperacionConverter implements ConverterInterface {

	/**
	 * 
	 */
	public TipoacreenciaoperacionConverter() {

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
		DataModel dm = new DataModel();
		crjpa400.Tipoacreenciaoperacion tipo400 = (crjpa400.Tipoacreenciaoperacion) obj;
		crjpa.Tipoacreenciaoperacion tipoCaja = new crjpa.Tipoacreenciaoperacion();

		tipoCaja.setId(tipo400.getId());

		dm.setId(tipo400.getId().toString());

		tipoCaja.setEsvisible(String.valueOf(tipo400.getEsvisible()));
		crjpa.Operacionacreencia tmp1 = new crjpa.Operacionacreencia(tipo400.getIdOperacionacreencia().getId());
		tipoCaja.setIdOperacionacreencia(tmp1);
		crjpa.Tipoacreencia tmp2 = new Tipoacreencia(tipo400.getIdTipoacreencia().getId());
		tipoCaja.setIdTipoacreencia(tmp2);

		dm.setEntity(tipoCaja);
		dm.setSyncDate(tipo400.getUltimasincronizacion());
		dm.setTableName(crjpa.Tipoacreenciaoperacion.class.getName().substring(
				crjpa.Tipoacreenciaoperacion.class.getName().lastIndexOf(".") + 1));
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