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
public class TipodocumentoConverter implements ConverterInterface {

	/**
	 * 
	 */
	public TipodocumentoConverter() {

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

		crjpa400.Tipodocumento tipodocumento400 = (crjpa400.Tipodocumento) obj;
		crjpa.Tipodocumento tipodocumentoCaja = new crjpa.Tipodocumento(tipodocumento400.getId());

		dm.setId(tipodocumento400.getId().toString());

		tipodocumentoCaja.setActividadEconomica(String.valueOf(tipodocumento400.getActividadEconomica()));
		tipodocumentoCaja.setDuplicadoAud(String.valueOf(tipodocumento400.getDuplicadoAud()));
		tipodocumentoCaja.setEsdevolucionparcial(String.valueOf(tipodocumento400.getEsdevolucionparcial()));
		tipodocumentoCaja.setEsfiscal(String.valueOf(tipodocumento400.getEsfiscal()));
		tipodocumentoCaja.setEssoporteventa(String.valueOf(tipodocumento400.getEssoporteventa()));
		tipodocumentoCaja.setEstaactivo(String.valueOf(tipodocumento400.getEstaactivo()));
		tipodocumentoCaja.setEstacionImpresion(new Character(tipodocumento400.getEstacionImpresion()).toString());
		tipodocumentoCaja.setFecha(tipodocumento400.getFecha());
		if (tipodocumento400.getIdContrapartida() != null) {
			tipodocumentoCaja
					.setIdContrapartida(new crjpa.Tipodocumento(tipodocumento400.getIdContrapartida().getId()));
		}
		if (tipodocumento400.getIdContrapartidadevolucion() != null) {
			tipodocumentoCaja.setIdContrapartidadevolucion(new crjpa.Tipodocumento(tipodocumento400
					.getIdContrapartidadevolucion().getId()));
		}
		tipodocumentoCaja.setMuestraImpuesto(String.valueOf(tipodocumento400.getMuestraImpuesto()));
		tipodocumentoCaja.setNombre(tipodocumento400.getNombre());
		tipodocumentoCaja.setPermiteReimpresion(String.valueOf(tipodocumento400.getPermiteReimpresion()));
		tipodocumentoCaja.setRequiereCliente(String.valueOf(tipodocumento400.getRequiereCliente()));
		tipodocumentoCaja.setRequiereNuevoDoc(String.valueOf(tipodocumento400.getRequiereNuevoDoc()));
		tipodocumentoCaja.setValidaSerial(new Character(tipodocumento400.getValidaSerial()).toString());

		dm.setEntity(tipodocumentoCaja);
		dm.setSyncDate(tipodocumento400.getUltimasincronizacion());
		dm.setTableName(crjpa.Tipodocumento.class.getName().substring(
				crjpa.Tipodocumento.class.getName().lastIndexOf(".") + 1));
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
