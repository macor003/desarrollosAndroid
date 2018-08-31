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
public class ReportezConverter implements ConverterInterface {

	/**
	 * 
	 */
	public ReportezConverter() {

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

		return new crjpa.Reportez();
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
	public SyncWrapper toServer(Object obj) throws ConverterException {
		crjpa400.Reportez serverValue = new crjpa400.Reportez();
		crjpa.Reportez posValue = (crjpa.Reportez) obj;
		
		SyncWrapper syncObject = new SyncWrapper();
		
		try {

			if (posValue.getBaseImponibleAFacturas() != null) {
				serverValue.setBaseImponibleAFacturas(posValue.getBaseImponibleAFacturas());
			}
			if (posValue.getBaseImponibleANc() != null) {
				serverValue.setBaseImponibleANc(posValue.getBaseImponibleANc());
			}
			if (posValue.getBaseImponibleGFacturas() != null) {
				serverValue.setBaseImponibleGFacturas(posValue.getBaseImponibleGFacturas());
			}
			if (posValue.getBaseImponibleGNc() != null) {
				serverValue.setBaseImponibleGNc(posValue.getBaseImponibleGNc());
			}
			if (posValue.getBaseImponibleRFacturas() != null) {
				serverValue.setBaseImponibleRFacturas(posValue.getBaseImponibleRFacturas());
			}
			if (posValue.getBaseImponibleRNc() != null) {
				serverValue.setBaseImponibleRNc(posValue.getBaseImponibleRNc());
			}
			serverValue.setFacturasEmitidas(posValue.getFacturasEmitidas());
			if (posValue.getFecha() != null) {
				serverValue.setFecha(posValue.getFecha());
			}
			if (posValue.getFechaCreacion() != null) {
				serverValue.setFechaCreacion(posValue.getFechaCreacion());
			}
			if (posValue.getFechaUltimaFacturaEmitida() != null) {
				serverValue.setFechaUltimaFacturaEmitida(posValue.getFechaUltimaFacturaEmitida());
			}
			if (posValue.getHoraUltimaNotaCreditoEmitida() != null) {
				serverValue.setFechaUltimaNotaCreditoEmitida(posValue.getHoraUltimaNotaCreditoEmitida());
			}
			if (posValue.getHoraCreacion() != null) {
				serverValue.setHoraCreacion(posValue.getHoraCreacion());
			}
			if (posValue.getHoraUltimaFacturaEmitida() != null) {
				serverValue.setHoraUltimaFacturaEmitida(posValue.getHoraUltimaFacturaEmitida());
			}
			if (posValue.getHoraUltimaNotaCreditoEmitida() != null) {
				serverValue.setHoraUltimaNotaCreditoEmitida(posValue.getHoraUltimaNotaCreditoEmitida());
			}

			serverValue.setId(posValue.getId());

			serverValue.setIdSesion(new crjpa400.Sesion(posValue.getIdSesion().getId()));
			if (posValue.getImpuestoAFacturas() != null) {
				serverValue.setImpuestoAFacturas(posValue.getImpuestoAFacturas());
			}
			if (posValue.getImpuestoANc() != null) {
				serverValue.setImpuestoANc(posValue.getImpuestoANc());
			}
			if (posValue.getImpuestoGFacturas() != null) {
				serverValue.setImpuestoGFacturas(posValue.getImpuestoGFacturas());
			}
			if (posValue.getImpuestoGNc() != null) {
				serverValue.setImpuestoGNc(posValue.getImpuestoGNc());
			}
			if (posValue.getImpuestoRFacturas() != null) {
				serverValue.setImpuestoRFacturas(posValue.getImpuestoRFacturas());
			}
			if (posValue.getImpuestoRNc() != null) {
				serverValue.setImpuestoRNc(posValue.getImpuestoRNc());
			}
			if (posValue.getMontoExcentoFactura() != null) {
				serverValue.setMontoExentoFactura(posValue.getMontoExcentoFactura());
			}
			if (posValue.getMontoExcentoNc() != null) {
				serverValue.setMontoExentoNc(posValue.getMontoExcentoNc());
			}
			serverValue.setNotaCreditoEmitida(posValue.getNotaCreditoEmitida());
			serverValue.setNumeroReporte(posValue.getNumeroReporte());
			if (posValue.getPorcentajeAFacturas() != null) {
				serverValue.setPorcentajeAFacturas(posValue.getPorcentajeAFacturas());
			}
			if (posValue.getPorcentajeANc() != null) {
				serverValue.setPorcentajeANc(posValue.getPorcentajeANc());
			}
			if (posValue.getPorcentajeGFacturas() != null) {
				serverValue.setPorcentajeGFacturas(posValue.getPorcentajeGFacturas());
			}
			if (posValue.getPorcentajeGNc() != null) {
				serverValue.setPorcentajeGNc(posValue.getPorcentajeGNc());
			}
			if (posValue.getPorcentajeRFacturas() != null) {
				serverValue.setPorcentajeRFacturas(posValue.getPorcentajeRFacturas());
			}
			if (posValue.getPorcentajeRNc() != null) {
				serverValue.setPorcentajeRNc(posValue.getPorcentajeRNc());
			}
			if (posValue.getSerialImpresora() != null) {
				serverValue.setSerialImpresora(posValue.getSerialImpresora());
			}
			if (posValue.getElectronic_journal() != null) {
				serverValue.setElectronic_journal(posValue.getElectronic_journal());
				System.out.println(serverValue.getElectronic_journal());
			} else {
				System.out.println("no EJ");
			}
			serverValue.setTienda(posValue.getTienda());
			serverValue.setUltimaFacturaEmitida(posValue.getUltimaFacturaEmitida());
			serverValue.setUltimaNotaCreditoEmitida(posValue.getUltimaNotaCreditoEmitida());
			serverValue.setTipo(posValue.getTipo());
			
			syncObject.setEntity(serverValue);
			syncObject.setId(posValue.getId());
		}
		catch(Exception ex) {
			throw new ConverterException("Reportez", ex.getMessage(), posValue.getId().toString());
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
