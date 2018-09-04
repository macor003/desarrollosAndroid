/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.sync.converters;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.ConverterException;
import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

import crjpa400.Formadepagopuntoagil;
import crjpa400.Porcentajeimpuestoretencion;
import crjpa400.Transaccion;
import crjpa400.Transaccionformadepago;

/**
 * Transforma la entidad de Transaccionformadepago 
 * para que pueda ser usada en la caja {@link #fromServer(Object, String)} 
 * o en el servidor {@link #toServer(Object)}
 * 
 * @version $Revision: 1.0 $
 */
public class TransaccionformadepagoConverter implements ConverterInterface {

	/**
	 * 
	 */
	public TransaccionformadepagoConverter() {

	}

	/**
	 * Convierte el objeto crjpa.Transaccionformadepago proveniente 
	 * de caja, a un objeto crjpa400.Transaccionformadepago, el cual 
	 * es usado por el servidor de sincronizacion para guardarlo en 
	 * el servidor de CR400.
	 * <p>
	 * (No implementado)
	 * @param obj Object
	 * @param posId String
	 * @return Object
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#fromServer(Object,String)
	 */
	@Override
	public Object fromServer(Object obj, String posId) {
		return new crjpa.Transaccionformadepago();
	}

	/**
	 * Convierte el objeto crjpa.Transaccionformadepago proveniente 
	 * de caja, a un objeto crjpa400.Transaccionformadepago, el cual 
	 * es usado por el servidor de sincronizacion para guardarlo en 
	 * el servidor de CR400
	 * @param obj Object
	 * @return Object
	 * @throws ConverterException 
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#toServer(Object)
	 */
	@Override
	public SyncWrapper toServer(Object obj) throws ConverterException {

		FormadepagopuntoagilConverter vposConverter = new FormadepagopuntoagilConverter();

		crjpa.Transaccionformadepago transaccionCaja = (crjpa.Transaccionformadepago) obj;
		crjpa400.Transaccionformadepago transaccion400 = new Transaccionformadepago();

		SyncWrapper syncObject = new SyncWrapper();
		
		try{
		
		transaccion400.setConformacion(transaccionCaja.getConformacion());
		transaccion400.setCuenta(transaccionCaja.getCuenta());
		if (transaccionCaja.getDocumento() != null || transaccionCaja.getDocumento() != "") {
			transaccion400.setDocumento(transaccionCaja.getDocumento());
		} else {
			transaccion400.setDocumento("d");
		}
		if (transaccionCaja.getCuenta() != null || transaccionCaja.getCuenta() != "") {
			transaccion400.setCuenta(transaccionCaja.getCuenta());
		} else {
			transaccion400.setCuenta("c");
		}
		if (transaccionCaja.getConformacion() != null || transaccionCaja.getConformacion() != "") {
			transaccion400.setConformacion(transaccionCaja.getConformacion());
		} else {
			transaccion400.setConformacion("c");
		}
		if(transaccionCaja.getIdPorcentajeimpuestoretencion()!=null) {
			transaccion400.setIdPorcentajeimpuestoretencion(new Porcentajeimpuestoretencion(transaccionCaja.getIdPorcentajeimpuestoretencion().getId()));
		}
		transaccion400.setIdTransaccion(new Transaccion(transaccionCaja.getIdTransaccion().getId()));
		transaccion400.setIdFormadepago(new crjpa400.Formadepago(transaccionCaja.getIdFormadepago().getId()));
		transaccion400.setIdMoneda(new crjpa400.Moneda(transaccionCaja.getIdMoneda().getId()));
		transaccion400.setEstaactivo('S');
		transaccion400.setItem(transaccionCaja.getItem());
		transaccion400.setMontoMoneda(transaccionCaja.getMontoMoneda());
		transaccion400.setMontoMonedaLocal(transaccionCaja.getMontoMonedaLocal());
		transaccion400.setTitular(transaccionCaja.getTitular());
		ArrayList<crjpa400.Formadepagopuntoagil> vposList = new ArrayList<crjpa400.Formadepagopuntoagil>();
		for (crjpa.Formadepagopuntoagil vpos : transaccionCaja.getFormadepagopuntoagilList()) {
			crjpa400.Formadepagopuntoagil vposTmp = (Formadepagopuntoagil) vposConverter.toServer(vpos).getEntity();
			vposTmp.setIdTransaccionformadepago(transaccion400);
			vposList.add(vposTmp);
		}
		transaccion400.setFormadepagopuntoagilList(vposList);
		transaccion400.setPorcentajeRetencion(transaccionCaja.getPorcentajeRetencion());
		
		syncObject.setEntity(transaccion400);
		syncObject.setId(transaccionCaja.getId());
		
		}catch(Exception e){
			throw new ConverterException("Transaccionformadepago", e.getMessage(), transaccionCaja.getId().toString());
		}

		return syncObject;
	}

	/**
	 * Method getPosEmf.
	 * @return EntityManagerFactory
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#getPosEmf()
	 */
	@Override
	public EntityManagerFactory getPosEmf() {
		return ConverterSingleton.getPosEMF();
	}

	/**
	 * Method getServerEmf.
	 * @return EntityManagerFactory
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#getServerEmf()
	 */
	@Override
	public EntityManagerFactory getServerEmf() {
		return ConverterSingleton.getServerEMF();
	}

	/**
	 * Asigna el numero de caja a este controlador.
	 * <p>
	 * (No implementado)
	 * @param posNumber String
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#setPosNumber(String)
	 */
	@Override
	public void setPosNumber(String posNumber) {
	}
}