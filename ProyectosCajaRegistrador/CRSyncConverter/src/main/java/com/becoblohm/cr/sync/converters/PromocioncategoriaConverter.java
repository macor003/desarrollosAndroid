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

import crjpa.Categoria;
import crjpa.Promocion;
import crjpa.Promocioncategoria;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class PromocioncategoriaConverter implements ConverterInterface {

	/**
	 * 
	 */
	public PromocioncategoriaConverter() {

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

		crjpa400.Promocioncategoria serverValue = (crjpa400.Promocioncategoria) obj;
		crjpa.Promocioncategoria posvalue = new Promocioncategoria();
		posvalue.setEstaactivo(String.valueOf(serverValue.getEstaactivo()));
		posvalue.setId(serverValue.getId());

		dm.setId(serverValue.getId().toString());

		crjpa.Categoria tmp = new Categoria(serverValue.getIdCategoria().getId());
		posvalue.setIdCategoria(tmp);
		crjpa.Promocion tmp2 = new Promocion(serverValue.getIdPromocion().getId());
		posvalue.setIdPromocion(tmp2);
		posvalue.setPorcentajeDescuento(serverValue.getPorcentajeDescuento());

		dm.setEntity(posvalue);
		dm.setSyncDate(serverValue.getUltimasincronizacion());
		dm.setTableName(crjpa.Promocioncategoria.class.getName().substring(
				crjpa.Promocioncategoria.class.getName().lastIndexOf(".") + 1));
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
