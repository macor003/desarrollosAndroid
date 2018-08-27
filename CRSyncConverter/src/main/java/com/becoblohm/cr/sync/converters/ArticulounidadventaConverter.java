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

import crjpa.Articulounidadventa;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ArticulounidadventaConverter implements ConverterInterface {

	/**
	 * 
	 */
	public ArticulounidadventaConverter() {

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

		crjpa400.Articulounidadventa serverSaleunit = (crjpa400.Articulounidadventa) obj;
		crjpa.Articulounidadventa posSaleunit = new Articulounidadventa();

		posSaleunit.setFecha(serverSaleunit.getFecha());
		posSaleunit.setId(serverSaleunit.getId());

		dm.setId(serverSaleunit.getId().toString());

		posSaleunit.setIdArticulo(new crjpa.Articulo(serverSaleunit.getIdArticulo().getId()));
		posSaleunit.setIdUnidadventa(new crjpa.Unidadventa(serverSaleunit.getIdUnidadventa().getId()));
		posSaleunit.setPrecio(serverSaleunit.getPrecio());
		posSaleunit.setEstaactivo(String.valueOf(serverSaleunit.getEstaactivo()));

		dm.setEntity(posSaleunit);
		dm.setSyncDate(serverSaleunit.getUltimasincronizacion());
		dm.setTableName(crjpa.Articulounidadventa.class.getName().substring(
				crjpa.Articulounidadventa.class.getName().lastIndexOf(".") + 1));

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
