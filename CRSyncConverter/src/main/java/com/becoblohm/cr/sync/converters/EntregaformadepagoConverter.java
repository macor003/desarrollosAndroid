/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.sync.converters;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.ConverterException;
import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class EntregaformadepagoConverter implements ConverterInterface {

	/**
	 * 
	 */
	public EntregaformadepagoConverter() {

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

		return new crjpa.Entregaformadepago();
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
		crjpa.Entregaformadepago entregaCaja = (crjpa.Entregaformadepago) obj;
		crjpa400.Entregaformadepago entrega400 = new crjpa400.Entregaformadepago();

		SyncWrapper syncObject = new SyncWrapper();
		
		try{
		entrega400.setBilletes(entregaCaja.getBilletes());
		entrega400.setIdFormadepago(new crjpa400.Formadepago(entregaCaja.getIdFormadepago().getId()));
		entrega400.setEstaactivo(entregaCaja.getEstaactivo().charAt(0));
		entrega400.setFecha(entregaCaja.getFecha());
		crjpa400.Monedadenominacion monedaDenominacion = new crjpa400.Monedadenominacion(entregaCaja
				.getIdMonedadenominacion().getId());
		entrega400.setIdMonedadenominacion(monedaDenominacion);
		entrega400.setCodigoMonedaDenominacion(entregaCaja.getCodigoMonedaDenominacion());
		entrega400.setMonedas(entregaCaja.getMonedas());
		entrega400.setMonto(entregaCaja.getMonto());
		
		syncObject.setEntity(entrega400);
		syncObject.setId(entregaCaja.getId());
		
		}catch(Exception e){
			throw new ConverterException("Entregaformadepago", e.getMessage(), entregaCaja.getId().toString());
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
