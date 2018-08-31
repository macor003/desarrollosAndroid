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
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Entregaformadepago;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class EntregaConverter implements ConverterInterface {

	/**
	 * Field converterError.
	 */
	private String converterError;

	/**
	 * Constructor for EntregaConverter.
	 */
	public EntregaConverter() {

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

		return new crjpa.Entrega();
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
		EntregaformadepagoConverter entregaFDPConverter = new EntregaformadepagoConverter();

		crjpa400.Entrega entrega400 = new crjpa400.Entrega();
		crjpa.Entrega entregaCaja = (crjpa.Entrega) obj;
		
		SyncWrapper syncObject = new SyncWrapper();
		
		try{
			entrega400.setFecha(entregaCaja.getFecha());
			entrega400.setEstaactivo(entregaCaja.getEstaactivo().charAt(0));
			entrega400.setEnlinea(entregaCaja.getEnlinea().charAt(0));

			entrega400.setIdSesion(new crjpa400.Sesion(entregaCaja.getIdSesion().getId()));
			entrega400.setIdUsuarioautorizante(new crjpa400.Usuario(entregaCaja.getIdUsuarioautorizante().getId()));
			entrega400.setMontoFondo(entregaCaja.getMontoFondo());
			entrega400.setMontoRecaudado(entregaCaja.getMontoRecaudado());
			entrega400.setNumero(entregaCaja.getNumero());

			ArrayList<crjpa400.Entregaformadepago> entregaList = new ArrayList<crjpa400.Entregaformadepago>();

			Entregaformadepago tmp400 = null;
			for (crjpa.Entregaformadepago tmp : entregaCaja.getEntregaformadepagoList()) {
				tmp400 = (crjpa400.Entregaformadepago) entregaFDPConverter.toServer(tmp).getEntity();
				tmp400.setIdEntrega(entrega400);
				entregaList.add(tmp400);
			}
			entrega400.setEntregaformadepagoList(entregaList);

			entrega400.setId(entregaCaja.getId());
			entrega400.setTipoEntrega(entregaCaja.getTipoEntrega().charAt(0));
			entrega400.setTransaccionExcedida(entregaCaja.getTransaccionExcedida());
			if (!validateDelivery(entrega400)) {
				entrega400 = null;
				throw new ConverterException("Entrega", converterError, entregaCaja.getId().toString());
			}
			
			syncObject.setEntity(entrega400);
			syncObject.setId(entregaCaja.getId());
			
		}catch(Exception e){
			entrega400 = null;
			throw new ConverterException("Entrega", e.getMessage(), entregaCaja.getId().toString());
		}
		return syncObject;
	}

	/**
	 * Method validateDelivery.
	 * 
	 * @param delivery
	 *            crjpa400.Entrega
	 * @return boolean
	 */
	private boolean validateDelivery(crjpa400.Entrega delivery) {
		boolean isValid = false;

		if (!isValid) {
			if ((delivery.getEntregaformadepagoList() == null || (delivery.getEntregaformadepagoList() != null && delivery
					.getEntregaformadepagoList().size() < 1))) {
				converterError = "No tiene formas de pago";
				isValid = false;
			} else {
				CRBigDecimal totalDetailFDPAmmount = CRBigDecimal.ZERO;
				for (crjpa400.Entregaformadepago entregaFDP : delivery.getEntregaformadepagoList()) {
					if (String.valueOf(entregaFDP.getEstaactivo()).equals(ActiveValues.S.getString())) {
						totalDetailFDPAmmount = totalDetailFDPAmmount.add(new CRBigDecimal(entregaFDP.getMonto()
								.doubleValue()));
					}

				}

				CRBigDecimal deliveryHeaderAmount = new CRBigDecimal(delivery.getMontoRecaudado().doubleValue());

				if (totalDetailFDPAmmount.compareTo(deliveryHeaderAmount) == 0) {
					isValid = true;
				} else {
					converterError = "Los montos de la entrega no son correctos (formas de pago vs total de entrega)";
					isValid = false;
				}
			}
		}

		return isValid;
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
