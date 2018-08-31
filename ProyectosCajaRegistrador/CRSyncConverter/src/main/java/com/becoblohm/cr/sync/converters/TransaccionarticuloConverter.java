/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.sync.converters;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.ConverterException;
import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

import crjpa.Transaccionarticulo;
import crjpa400.Articulo;
import crjpa400.Promocion;
import crjpa400.Tasaimpuestovalor;
import crjpa400.Tipodescuento;
import crjpa400.Transaccion;
import crjpa400.Transaccionarticuloservicio;
import crjpa400.Unidadventa;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TransaccionarticuloConverter implements ConverterInterface {

	/**
	 * 
	 */
	public TransaccionarticuloConverter() {

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

		return new crjpa.Transaccionarticulo();
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
	 * @throws ConverterException 
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#toServer(Object)
	 */
	@Override
	public SyncWrapper toServer(Object obj) throws ConverterException {

		TransaccionarticuloservicioConverter serviceConverter = new TransaccionarticuloservicioConverter();

		crjpa.Transaccionarticulo posValue = (Transaccionarticulo) obj;
		crjpa400.Transaccionarticulo serverValue = new crjpa400.Transaccionarticulo();

		SyncWrapper syncObject = new SyncWrapper();

		try{
		serverValue.setCantidad(posValue.getCantidad());
		serverValue.setCaptura(posValue.getCaptura());
		serverValue.setCondicionEntrega(posValue.getCondicionEntrega().charAt(0));
		serverValue.setFechaInicioGarantia(posValue.getFechaInicioGarantia());
		serverValue.setFechaVenta(posValue.getFechaInicioGarantia());
		serverValue.setIdArticulo(new Articulo(posValue.getIdArticulo().getId()));
		if (posValue.getIdPromocion() != null) {
			serverValue.setIdPromocion(new Promocion(posValue.getIdPromocion().getId()));
		}
		serverValue.setIdTasaimpuestovalor(new Tasaimpuestovalor(posValue.getIdTasaimpuestovalor().getId()));
		serverValue.setIdTipodescuento(new Tipodescuento(posValue.getIdTipodescuento().getId()));
		serverValue.setIdTransaccion(new Transaccion(Long.valueOf(posValue.getIdTransaccion().getId())));
		serverValue.setIdUnidadventa(new Unidadventa(posValue.getIdUnidadventa().getId()));
		serverValue.setMontoImpuesto(posValue.getMontoImpuesto());
		serverValue.setMontoUnitario(posValue.getMontoUnitario());
		serverValue.setMontoBase(posValue.getMontoBase());
		serverValue.setTotalRebaja(posValue.getTotalRebaja());
		serverValue.setTotalArticulo(posValue.getTotalArticulo());
		serverValue.setTotalBase(posValue.getTotalBase());
		serverValue.setTotalImpuesto(posValue.getTotalImpuesto());
		ArrayList<crjpa400.Transaccionarticuloservicio> servicesList = new ArrayList<crjpa400.Transaccionarticuloservicio>();
		Transaccionarticuloservicio serviceTmp;
		for (crjpa.Transaccionarticuloservicio service : posValue.getTransaccionarticuloservicioList()) {
			serviceTmp = (crjpa400.Transaccionarticuloservicio) serviceConverter.toServer(service).getEntity();
			serviceTmp.setIdTransaccionarticulo(serverValue);
			servicesList.add(serviceTmp);
		}
		serverValue.setTransaccionarticuloservicioList(servicesList);
		
		syncObject.setEntity(serverValue);
		syncObject.setId(posValue.getId());
		
		}catch(Exception e){
			throw new ConverterException("Transaccionarticulo", e.getMessage(), posValue.getId().toString());
		}

		return syncObject;
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
