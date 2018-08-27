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

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class UnidadventaConverter implements ConverterInterface {

	/**
	 * 
	 */
	public UnidadventaConverter() {

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
		crjpa400.Unidadventa unidad400 = (crjpa400.Unidadventa) obj;
		crjpa.Unidadventa unidadCaja = new crjpa.Unidadventa();
		unidadCaja.setId(unidad400.getId());

		dm.setId(unidad400.getId().toString());

		unidadCaja.setEsfraccion(String.valueOf(unidad400.getEsfraccion()));
		unidadCaja.setEstaactivo(String.valueOf(unidad400.getEstaactivo()));
		unidadCaja.setFecha(unidad400.getFecha());
		unidadCaja.setNombre(unidad400.getNombre());
		unidadCaja.setSimbolo(unidad400.getSimbolo());
		dm.setEntity(unidadCaja);
		dm.setSyncDate(unidad400.getUltimasincronizacion());
		dm.setTableName(crjpa.Unidadventa.class.getName().substring(
				crjpa.Unidadventa.class.getName().lastIndexOf(".") + 1));// TODO
																			// obtener
																			// el
																			// nombre
																			// de
																			// la
																			// clase
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