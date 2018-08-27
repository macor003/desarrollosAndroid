/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.sync.converters;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.DataModel;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

import crjpa.Formadepago;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class FormadepagoConverter implements ConverterInterface {
	// public static SimpleDateFormat sdf = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
	/**
	 * 
	 */
	public FormadepagoConverter() {

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
		crjpa400.Formadepago serverValue = (crjpa400.Formadepago) obj;
		crjpa.Formadepago posValue = new crjpa.Formadepago();

		FormadepagomonedaConverter fdpMonedaConverter = new FormadepagomonedaConverter();
		DetalletipoformadepagoConverter detalleTipoFDPConverter = new DetalletipoformadepagoConverter();

		posValue.setCodigo(serverValue.getCodigo());
		posValue.setDescripcion(serverValue.getDescripcion());
		posValue.setEstaactivo(String.valueOf(serverValue.getEstaactivo()));
		posValue.setFecha(serverValue.getFecha());
		posValue.setIconoFormaDePago(serverValue.getIconoFormaDePago());
		posValue.setId(serverValue.getId());
		posValue.setIdContrapartida(new Formadepago(serverValue.getIdContrapartida().getId()));

		dm.setId(serverValue.getId().toString());

		posValue.setMontoMaximo(serverValue.getMontoMaximo());
		posValue.setMontoMinimo(serverValue.getMontoMinimo());
		posValue.setNombre(serverValue.getNombre());
		posValue.setPermiteReasignarCliente(new Character(serverValue.getPermiteReasignarCliente()).toString());
		posValue.setPermiteReverso(new Character(serverValue.getPermiteReverso()).toString());
		posValue.setPermiteVuelto(String.valueOf(serverValue.getPermiteVuelto()));
		posValue.setPrioridadDonacion(serverValue.getPrioridadDonacion());
		posValue.setRequiereAutorizacion(String.valueOf(serverValue.getRequiereAutorizacion()));
		posValue.setTipoFormaDePago(serverValue.getTipoFormaDePago());
		if (serverValue.getTipoTarjeta() != null) {
			posValue.setTipoTarjeta(serverValue.getTipoTarjeta().toString());
		}

		ArrayList<crjpa.Formadepagomoneda> formadepagomonedaList = new ArrayList<crjpa.Formadepagomoneda>();
		for (crjpa400.Formadepagomoneda tmp2 : serverValue.getFormadepagomonedaList()) {
			formadepagomonedaList.add((crjpa.Formadepagomoneda) ((DataModel) fdpMonedaConverter.fromServer(tmp2, null))
					.getEntity());
		}
		posValue.setFormadepagomonedaList(formadepagomonedaList);

		ArrayList<crjpa.Detalletipoformadepago> detalletipofdpList = new ArrayList<crjpa.Detalletipoformadepago>();
		for (crjpa400.Detalletipoformadepago tmp2 : serverValue.getDetalletipoformadepagoList()) {
			detalletipofdpList.add((crjpa.Detalletipoformadepago) ((DataModel) detalleTipoFDPConverter.fromServer(tmp2,
					null)).getEntity());
		}
		posValue.setDetalletipoformadepagoList(detalletipofdpList);
		posValue.setPermiteReasignarCliente(String.valueOf(serverValue.getPermiteReasignarCliente()));

		dm.setEntity(posValue);

		// String fecha =
		// sdf.format(serverValue.getUltimasincronizacion().getTime());
		// logger.debug("---->Fecha encontrada: "+fecha+"<----");
		// logger.debug("---->Fecha encontrada: "+serverValue.getUltimasincronizacion().getTime()+"<----");

		dm.setSyncDate(serverValue.getUltimasincronizacion());
		dm.setTableName(crjpa.Formadepago.class.getName().substring(
				crjpa.Formadepago.class.getName().lastIndexOf(".") + 1));
		if (serverValue.getTipoCaja() != null) {
			if (posId == null || !serverValue.getTipoCaja().toString().equals(posId)) {
				dm = null;
			} else {
				if (serverValue.getIdOriginal() != null) {
					posValue.setId(Long.valueOf(serverValue.getIdOriginal().toString()));
					dm.setForSpecificPosId(true);
				}
			}
		}
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
