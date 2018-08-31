/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.sync.converters;

import java.util.Calendar;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.conf.Global;
import com.becoblohm.cr.interfaces.ConverterException;
import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.DataModel;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

import crjpa.Comprobantefiscalpreimpreso;
import crjpa.Tipodocumento;
import crjpa400.Caja;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ComprobantefiscalpreimpresoConverter implements ConverterInterface {

	/**
	 * 
	 */
	public ComprobantefiscalpreimpresoConverter() {

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
		String idCaja = Global.getPosId();
		Long prePrintedFiscalReceiptPosID = 0l;
		DataModel dm = new DataModel();
		crjpa400.Comprobantefiscalpreimpreso serverValue = (crjpa400.Comprobantefiscalpreimpreso) obj;
		crjpa.Comprobantefiscalpreimpreso posValue = new Comprobantefiscalpreimpreso();

		try {
			prePrintedFiscalReceiptPosID = serverValue.getIdCaja().getId();			
		} catch (NullPointerException e) {
			System.out.println("Pre Printed Fiscal Receipt ID_CAJA field:  NULL");
			return null;
		}

		// PrePrintedFiscalReceipt ppfr = cfpJpac.findById(serverValue.getId());

		posValue.setFinalSerie(serverValue.getFinalSerie());
		posValue.setId(serverValue.getId());
		posValue.setTipoDocumento(serverValue.getTipoDocumento());
		posValue.setInicioSerie(serverValue.getInicioSerie());
		posValue.setNumCompactual(serverValue.getNumCompactual());
		posValue.setEstado(serverValue.getEstado());
		posValue.setObservacion(serverValue.getObservacion());
		posValue.setEstasincronizado("S");

		dm.setId(serverValue.getId().toString());

		crjpa.Tipodocumento docType = new Tipodocumento(serverValue.getIdTipodocumento().getId());
		posValue.setIdTipodocumento(docType);
		posValue.setSerie(serverValue.getSerie());
		posValue.setResolucion(serverValue.getResolucion());
		posValue.setFechaAutorizacion(serverValue.getFechaAutorizacion());
		posValue.setFechaIngreso(serverValue.getFechaIngreso());
		posValue.setFechaVencimiento(serverValue.getFechaVencimiento());
		posValue.setPorcentajeAlertaConsumo(serverValue.getPorcentajeAlertaConsumo());

		
		dm.setEntity(posValue);
		dm.setSyncDate(serverValue.getUltimasincronizacion());
		dm.setTableName(crjpa.Comprobantefiscalpreimpreso.class.getName()
				.substring(crjpa.Comprobantefiscalpreimpreso.class.getName().lastIndexOf(".") + 1));

		if (Integer.valueOf(prePrintedFiscalReceiptPosID.toString()) == Integer.valueOf(idCaja)) {
			return dm;
		} else {
			return null;
		}

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
		crjpa400.Comprobantefiscalpreimpreso comprobantefiscalpreimpreso400 = new crjpa400.Comprobantefiscalpreimpreso();
		crjpa.Comprobantefiscalpreimpreso comprobantefiscalpreimpreso = (Comprobantefiscalpreimpreso) obj;

		SyncWrapper syncObject = new SyncWrapper();

		try {

			comprobantefiscalpreimpreso400.setId(comprobantefiscalpreimpreso.getId());
			comprobantefiscalpreimpreso400.setNumCompactual(comprobantefiscalpreimpreso.getNumCompactual());
			comprobantefiscalpreimpreso400.setEstado(comprobantefiscalpreimpreso.getEstado());

			comprobantefiscalpreimpreso400.setSerie(comprobantefiscalpreimpreso.getSerie());
			comprobantefiscalpreimpreso400.setUltimasincronizacion(Calendar.getInstance());
			comprobantefiscalpreimpreso400.setIdCaja(new Caja(new Integer(Global.getPosId()).longValue()));
			comprobantefiscalpreimpreso400.setIdTipodocumento(
					new crjpa400.Tipodocumento(comprobantefiscalpreimpreso.getIdTipodocumento().getId()));
			comprobantefiscalpreimpreso400.setTipoDocumento(comprobantefiscalpreimpreso.getTipoDocumento());
			comprobantefiscalpreimpreso400.setInicioSerie(comprobantefiscalpreimpreso.getInicioSerie());
			comprobantefiscalpreimpreso400.setFinalSerie(comprobantefiscalpreimpreso.getFinalSerie());
			comprobantefiscalpreimpreso400.setObservacion(comprobantefiscalpreimpreso.getObservacion());
			comprobantefiscalpreimpreso400.setEstreplica(' ');
			comprobantefiscalpreimpreso400.setResolucion(comprobantefiscalpreimpreso.getResolucion());
			comprobantefiscalpreimpreso400.setFechaAutorizacion(comprobantefiscalpreimpreso.getFechaAutorizacion());
			comprobantefiscalpreimpreso400.setFechaIngreso(comprobantefiscalpreimpreso.getFechaIngreso());
			comprobantefiscalpreimpreso400.setFechaVencimiento(comprobantefiscalpreimpreso.getFechaVencimiento());
			comprobantefiscalpreimpreso400.setPorcentajeAlertaConsumo(comprobantefiscalpreimpreso.getPorcentajeAlertaConsumo());
		
			
			
			
			syncObject.setEntity(comprobantefiscalpreimpreso400);
			syncObject.setId(comprobantefiscalpreimpreso.getId());

		} catch (Exception e) {
			comprobantefiscalpreimpreso400 = null;
			throw new ConverterException("Comprobantefiscalpreimpreso", e.getMessage(),
					comprobantefiscalpreimpreso.getId().toString());
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
