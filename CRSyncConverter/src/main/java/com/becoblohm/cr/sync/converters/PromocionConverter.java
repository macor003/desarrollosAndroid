/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.sync.converters;

import java.math.BigDecimal;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.DataModel;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

import crjpa.Promocion;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class PromocionConverter implements ConverterInterface {

	/**
	 * 
	 */
	public PromocionConverter() {

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

		crjpa400.Promocion serverValue = (crjpa400.Promocion) obj;
		crjpa.Promocion posValue = new Promocion();
		posValue.setAplicaDescuento(String.valueOf(serverValue.getAplicaDescuento()));
		posValue.setCantidadTransacciones(serverValue.getCantidadTransacciones());
		posValue.setCodigo(serverValue.getCodigo());
		posValue.setEsmultiple(String.valueOf(serverValue.getEsmultiple()));
		posValue.setEstaactivo(new Character(serverValue.getEstaactivo()).toString());
		posValue.setFechaFin(serverValue.getFechaFin());
		posValue.setFechaInicio(serverValue.getFechaInicio());
		posValue.setId(serverValue.getId());

		dm.setId(serverValue.getId().toString());

		posValue.setMensaje(serverValue.getMensaje());
		posValue.setNombre(serverValue.getNombre());
		posValue.setPorcentajeDescuento(new BigDecimal(serverValue.getPorcentajeDescuento().doubleValue()));
		posValue.setPrioridad(serverValue.getPrioridad());
		posValue.setValorMaximo(serverValue.getValorMaximo());
		posValue.setValorMinimo(serverValue.getValorMinimo());

		dm.setEntity(posValue);
		dm.setSyncDate(serverValue.getUltimasincronizacion());
		dm.setTableName(crjpa.Promocion.class.getName().substring(crjpa.Promocion.class.getName().lastIndexOf(".") + 1));
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
