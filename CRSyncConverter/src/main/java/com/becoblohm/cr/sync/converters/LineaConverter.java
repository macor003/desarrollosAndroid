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

import crjpa.Linea;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class LineaConverter implements ConverterInterface {

	/**
	 * 
	 */
	public LineaConverter() {

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
		crjpa400.Linea serverLine = (crjpa400.Linea) obj;
		crjpa.Linea posLine = new Linea();

		// CategoriaConverter categoryConverter=new CategoriaConverter();

		posLine.setFecha(serverLine.getFecha());
		posLine.setCodigo(serverLine.getCodigo());
		posLine.setEstaactivo(String.valueOf(serverLine.getEstaactivo()));
		posLine.setId(serverLine.getId());

		dm.setId(serverLine.getId().toString());

		posLine.setNombre(serverLine.getNombre());
		posLine.setPermiterebaja(new Character(serverLine.getPermiterebaja()).toString());
		// posLine.setIdCategoria((Categoria)
		// categoryConverter.fromServer(serverLine.getIdCategoria()));
		posLine.setIdCategoria(new crjpa.Categoria(serverLine.getIdCategoria().getId()));

		dm.setEntity(posLine);
		dm.setSyncDate(serverLine.getUltimasincronizacion());
		dm.setTableName(crjpa.Linea.class.getName().substring(crjpa.Linea.class.getName().lastIndexOf(".") + 1));

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
