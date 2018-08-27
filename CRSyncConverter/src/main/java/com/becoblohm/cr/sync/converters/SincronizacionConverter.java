/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.sync.converters;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.conf.Global;
import com.becoblohm.cr.interfaces.ConverterException;
import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.DataModel;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

import crjpa.Sincronizacion;
import crjpa.SincronizacionJpaController;
import crjpa400.Sincronizacionporcaja;

/**
 * @version $Revision: 1.0 $
 */
public class SincronizacionConverter implements ConverterInterface {

	/**
	 * Field FECHA_1970. (value is ""19700101010101"")
	 */
	private static final String FECHA_1970 = "19700101010101";
	/**
	 * Field sdf.
	 */
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
	private String posNumber;

	/**
	 * 
	 */
	public SincronizacionConverter() {

	}

	/**
	 * Metodo fromServer.
	 * <p>
	 * Convierte el objeto CRJPA400.Sincronizacion en un objeto CRJPA.Sincronizacion
	 * 
	 * @param obj
	 *            Object El objeto CRJPA400.Sincronizacion a convertir
	 * @param posId
	 *            String El tipo de caja que esta sincronizando
	 * @return Object El objeto CRJPA.Sincronizacion convertido
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#fromServer(Object,
	 *      String)
	 */
	@Override
	public Object fromServer(Object obj, String posId) {
		DataModel dm = new DataModel();
		crjpa400.Sincronizacion serverValue = (crjpa400.Sincronizacion) obj;
		crjpa.Sincronizacion posValue = new crjpa.Sincronizacion();
		posValue.setCantidad(serverValue.getCantidad());
		posValue.setEscarga(String.valueOf(serverValue.getEscarga()));
		posValue.setEsdescarga(String.valueOf(serverValue.getEsdescarga()));
		posValue.setEspera(serverValue.getEspera());
		posValue.setId(serverValue.getId());

		dm.setId(serverValue.getId().toString());

		posValue.setIdGrupo(serverValue.getIdGrupo());
		posValue.setNombre(serverValue.getNombre());

		SincronizacionJpaController syncJpaController = new SincronizacionJpaController(
				Global.getEntityManagerFactory());

		Sincronizacion sincronizacion = syncJpaController.findSincronizacion(serverValue.getId());

		if (sincronizacion != null) {
			posValue.setUltimasincronizacion(sincronizacion.getUltimasincronizacion());
		} else {
			posValue.setUltimasincronizacion(new BigDecimal(FECHA_1970));
		}

		posValue.setPrioridad(serverValue.getPrioridad());
		posValue.setTipo(String.valueOf(serverValue.getTipo()));

		posValue.setJpqlQuery(serverValue.getJpqlQuery());

		posValue.setEstasincronizado("S");

		dm.setEntity(posValue);
		dm.setSyncDate(serverValue.getUltimasincronizacion());
		dm.setTableName(crjpa.Sincronizacion.class.getName()
				.substring(crjpa.Sincronizacion.class.getName().lastIndexOf(".") + 1));

		// EMF.close();

		return dm;
	}

	/**
	 * Metodo toServer.
	 * <p>
	 * Convierte el objeto CRJPA.sincronizacion en un objeto
	 * CRJPA400.sincronizacionpendientes
	 * 
	 * @param obj
	 *            Object La entidad de CRJPA.Sincronizacion a convertir
	 * @return Object La entidad de CRJPA400.Sincronizacionpendientes convertida
	 * @throws ConverterException
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#toServer(Object)
	 */
	@Override
	public SyncWrapper toServer(Object obj) throws ConverterException {
		crjpa.Sincronizacion posValue = (crjpa.Sincronizacion) obj;
		crjpa400.Sincronizacionporcaja serverValue = new Sincronizacionporcaja(Long.valueOf(this.posNumber),
				posValue.getId());

		SyncWrapper syncObject = new SyncWrapper();

		try {
			Calendar calendar = Calendar.getInstance();
			String dateStr = String.valueOf(posValue.getUltimasincronizacion());
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

			serverValue.setFecha(calendar);

			syncObject.setEntity(serverValue);
			syncObject.setId(posValue.getId());

		} catch (Exception e) {
			throw new ConverterException("Sincronizacion", e.getMessage(), posValue.getId().toString());
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
		this.posNumber = posNumber;
	}
}
