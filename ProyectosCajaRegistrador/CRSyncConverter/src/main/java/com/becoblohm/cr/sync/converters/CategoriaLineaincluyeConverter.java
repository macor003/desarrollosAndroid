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

import crjpa.Categorialineaincluye;
import crjpa.Giroactividadeconomica;
import crjpa.Linea;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class CategoriaLineaincluyeConverter implements ConverterInterface {

	/**
	 * 
	 */
	public CategoriaLineaincluyeConverter() {

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
		crjpa400.Categorialineaincluye serverIncludedline = (crjpa400.Categorialineaincluye) obj;
		crjpa.Categorialineaincluye posIncludedLine = new Categorialineaincluye();

		posIncludedLine.setFecha(serverIncludedline.getFecha());
		posIncludedLine.setId(serverIncludedline.getId());

		dm.setId(serverIncludedline.getId().toString());

		posIncludedLine.setIdGiroactividadeconomica(new Giroactividadeconomica(serverIncludedline
				.getIdGiroactividadeconomica().getId()));
		posIncludedLine.setIdLinea(new Linea(serverIncludedline.getIdLinea().getId()));
		posIncludedLine.setIdTipodocumento(new crjpa.Tipodocumento(serverIncludedline.getIdTipodocumento().getId()));

		dm.setEntity(posIncludedLine);
		dm.setSyncDate(serverIncludedline.getUltimasincronizacion());
		dm.setTableName(crjpa.Categorialineaincluye.class.getName().substring(
				crjpa.Categorialineaincluye.class.getName().lastIndexOf(".") + 1));

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
