/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.sync.converters;

import java.util.Calendar;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.ConverterException;
import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.DataModel;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class CuentacreditoConverter implements ConverterInterface {

	/**
	 * 
	 */
	public CuentacreditoConverter() {

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
		crjpa400.Cuentacredito serverValue = (crjpa400.Cuentacredito) obj;
		crjpa.Cuentacredito posValue = new crjpa.Cuentacredito(serverValue.getId().longValue());

		dm.setId(serverValue.getId().toString());

		posValue.setDeudaPendiente(serverValue.getDeudaPendiente());
		if (serverValue.getEstadoTarjeta().compareTo('S') == 0) {
			posValue.setEstadoTarjeta("A");
		} else if (serverValue.getEstadoTarjeta().compareTo('N') == 0) {
			posValue.setEstadoTarjeta("I");
		} else {
			posValue.setEstadoTarjeta(serverValue.getEstadoTarjeta().toString());
		}
		posValue.setFechaActualizacion(serverValue.getFechaActualizacion());
		posValue.setFechaCreacion(serverValue.getFechaCreacion());

		posValue.setFechaExpiracion(serverValue.getFechaExpiracion());

		posValue.setFechaMora(serverValue.getFechaMora());
		posValue.setFechaUltimopago(serverValue.getFechaUltimopago());
		posValue.setLineaCredito(serverValue.getLineaCredito());

		// Se seta porque Ceunta credito tambien sube y tiene la columna
		// estasincronizado
		posValue.setEstasincronizado("S");

		posValue.setNumeroIdentificacionCliente(new crjpa.Cliente(serverValue.getNumeroIdentificacionCliente()
				.getNumeroIdentificacionCliente()));
		posValue.setTipo(serverValue.getTipo());
		dm.setEntity(posValue);
		dm.setSyncDate(serverValue.getUltimasincronizacion());
		dm.setTableName(crjpa.Cuentacredito.class.getName().substring(
				crjpa.Cuentacredito.class.getName().lastIndexOf(".") + 1));

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
	 * @throws ConverterException 
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#toServer(Object)
	 */
	@Override
	public SyncWrapper toServer(Object obj) throws ConverterException {
		crjpa.Cuentacredito cuentaCreditoCaja = (crjpa.Cuentacredito) obj;
		crjpa400.Cuentacredito cuentaCredito400 = new crjpa400.Cuentacredito();
		
		SyncWrapper syncObject = new SyncWrapper();

		try{
		Calendar cal = bigdecimalToCalendar("19700101010101.001");

		cuentaCredito400.setId(cuentaCreditoCaja.getId());
		if (cuentaCreditoCaja.getNumeroIdentificacionCliente() != null) {
			cuentaCredito400.setNumeroIdentificacionCliente(new crjpa400.Cliente(cuentaCreditoCaja
					.getNumeroIdentificacionCliente().getNumeroIdentificacionCliente()));
		}
		cuentaCredito400.setEstadoTarjeta(cuentaCreditoCaja.getEstadoTarjeta().charAt(0));
		cuentaCredito400.setFechaCreacion(cuentaCreditoCaja.getFechaCreacion());
		cuentaCredito400.setFechaActualizacion(cuentaCreditoCaja.getFechaActualizacion());
		cuentaCredito400.setFechaExpiracion(cuentaCreditoCaja.getFechaExpiracion());
		cuentaCredito400.setFechaMora(cuentaCreditoCaja.getFechaMora());
		cuentaCredito400.setFechaUltimopago(cuentaCreditoCaja.getFechaUltimopago());
		cuentaCredito400.setLineaCredito(cuentaCreditoCaja.getLineaCredito());
		cuentaCredito400.setDeudaPendiente(cuentaCreditoCaja.getDeudaPendiente());
		cuentaCredito400.setTipo(cuentaCreditoCaja.getTipo());
		cuentaCredito400.setUltimasincronizacion(cal);
		
		syncObject.setEntity(cuentaCredito400);
		syncObject.setId(cuentaCreditoCaja.getId());
		
		}catch(Exception e){
			throw new ConverterException("Cuentacredito", e.getMessage(), cuentaCreditoCaja.getId().toString());
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

	/**
	 * Method bigdecimalToCalendar.
	 * 
	 * @param date
	 *            String
	 * @return Calendar
	 */
	private Calendar bigdecimalToCalendar(String date) {

		Calendar calendar = Calendar.getInstance();
		String dateStr = date;
		String year = dateStr.substring(0, 4);
		String month = dateStr.substring(4, 6);
		String day = dateStr.substring(6, 8);
		String hour = dateStr.substring(8, 10);
		String minute = dateStr.substring(10, 12);
		String second = dateStr.substring(12, 14);
		String milli = dateStr.substring(15, dateStr.length());

		calendar.set(Calendar.YEAR, Integer.parseInt(year));
		calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
		calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
		calendar.set(Calendar.SECOND, Integer.parseInt(second));
		calendar.set(Calendar.MILLISECOND, Integer.parseInt(milli));

		return calendar;
	}
}
