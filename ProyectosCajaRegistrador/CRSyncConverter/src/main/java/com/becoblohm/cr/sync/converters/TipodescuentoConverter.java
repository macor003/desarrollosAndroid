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
public class TipodescuentoConverter implements ConverterInterface {

	/**
	 * 
	 */
	public TipodescuentoConverter() {

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
		crjpa400.Tipodescuento tipoDescuento400 = (crjpa400.Tipodescuento) obj;
		crjpa.Tipodescuento tipoDescuentoCaja = new crjpa.Tipodescuento(tipoDescuento400.getId());

		dm.setId(tipoDescuento400.getId().toString());

		tipoDescuentoCaja.setCodigo(tipoDescuento400.getCodigo());
		tipoDescuentoCaja.setDescripcion(tipoDescuento400.getDescripcion());
		tipoDescuentoCaja.setEsporcentaje(String.valueOf(tipoDescuento400.getEsporcentaje()));
		tipoDescuentoCaja.setEstaactivo(String.valueOf(tipoDescuento400.getEstaactivo()));
		tipoDescuentoCaja.setFacturacionAutomatica(String.valueOf(tipoDescuento400.getFacturacionAutomatica()));
		tipoDescuentoCaja.setFecha(tipoDescuento400.getFecha());
		tipoDescuentoCaja.setNombre(tipoDescuento400.getNombre());

		dm.setEntity(tipoDescuentoCaja);
		dm.setSyncDate(tipoDescuento400.getUltimasincronizacion());
		dm.setTableName(crjpa.Tipodescuento.class.getName().substring(
				crjpa.Tipodescuento.class.getName().lastIndexOf(".") + 1));
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
