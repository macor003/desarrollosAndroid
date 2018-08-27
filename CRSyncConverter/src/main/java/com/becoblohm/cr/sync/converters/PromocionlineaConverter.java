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
import crjpa.Promocion;
import crjpa.Promocionlinea;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class PromocionlineaConverter implements ConverterInterface {

	/**
	 * 
	 */
	public PromocionlineaConverter() {

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

		crjpa400.Promocionlinea serverValue = (crjpa400.Promocionlinea) obj;
		crjpa.Promocionlinea posValue = new Promocionlinea();
		posValue.setEstaactivo(String.valueOf(serverValue.getEstaactivo()));
		posValue.setId(serverValue.getId());

		dm.setId(serverValue.getId().toString());

		crjpa.Linea tmp = new Linea(serverValue.getIdLinea().getId());
		posValue.setIdLinea(tmp);
		crjpa.Promocion tmp2 = new Promocion(serverValue.getIdPromocion().getId());
		posValue.setIdPromocion(tmp2);
		posValue.setPorcentajeDescuento(serverValue.getPorcentajeDescuento());

		dm.setEntity(posValue);
		dm.setSyncDate(serverValue.getUltimasincronizacion());
		dm.setTableName(crjpa.Promocionlinea.class.getName().substring(
				crjpa.Promocionlinea.class.getName().lastIndexOf(".") + 1));
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