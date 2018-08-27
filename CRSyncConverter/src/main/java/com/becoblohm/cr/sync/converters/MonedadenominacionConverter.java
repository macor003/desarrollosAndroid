/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.sync.converters;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Vector;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.conf.Global;
import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.DataModel;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

import crjpa.Moneda;
import crjpa.Monedadenominacion;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class MonedadenominacionConverter implements ConverterInterface {

	/**
	 * Field APP_LOCALE_LANG.
	 */
	protected static Locale APP_LOCALE_LANG = new Locale("es_VE");

	/**
	 * 
	 */
	public MonedadenominacionConverter() {

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
		crjpa400.Monedadenominacion serverValue = (crjpa400.Monedadenominacion) obj;
		crjpa.Monedadenominacion posValue = new Monedadenominacion();
		posValue.setCodigo(serverValue.getCodigo());
		posValue.setEstaactivo(String.valueOf(serverValue.getEstaactivo()));
		posValue.setFecha(serverValue.getFecha());
		posValue.setId(serverValue.getId());

		dm.setId(serverValue.getId().toString());

		crjpa.Moneda tmp1 = new Moneda(serverValue.getIdMoneda().getId());
		posValue.setIdMoneda(tmp1);
		posValue.setMultiplo(serverValue.getMultiplo());
		// posValue.setNombre(serverValue.getNombre().replaceAll("[a-zA-Z]",
		// ""));

		Vector<String> cashierCommitmentFDP = Global.getFDPCashierCommitment();
		
		if (cashierCommitmentFDP.contains(String.valueOf(serverValue.getIdFormadepago().getId()))) {
			String value;
			value = serverValue.getNombre().replaceAll("[a-zA-Z]+[\\.]|[^\\w\\.@-]+[\\.]", "");
			Number tmp = null;
			try {
				tmp = getDecimalFormat().parse(value.trim());

			} catch (ParseException e) {
				e.printStackTrace();
			}

			if (tmp != null) {
				posValue.setNombre(tmp.toString());
			} else {
				posValue.setNombre("0");
			}
		} else {
			posValue.setNombre(serverValue.getNombre());
		}

		posValue.setTienda(serverValue.getTienda());
		posValue.setPorcentajeComision(serverValue.getPorcentajeComision());
		posValue.setPorcentajeImpuesto(serverValue.getPorcentajeImpuesto());
		posValue.setCuentaContableImpuesto(serverValue.getCuentaContableImpuesto());
		posValue.setCuentaContable(serverValue.getCuentaContable());
		posValue.setCuentaContableGastos(serverValue.getCuentaContableGastos());
		posValue.setEstadoRegistro(serverValue.getEstadoRegistro());
		posValue.setAuxiliar(serverValue.getAuxiliar());

		dm.setEntity(posValue);
		dm.setSyncDate(serverValue.getUltimasincronizacion());
		dm.setTableName(crjpa.Monedadenominacion.class.getName()
				.substring(crjpa.Monedadenominacion.class.getName().lastIndexOf(".") + 1));
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

	/**
	 * Este metodo esta copiado de Global pero tuvo que ser repetido debido que no
	 * se tiene acceso a Global desde aca
	 * 
	 * 
	 * @return DecimalFormat
	 */
	public static DecimalFormat getDecimalFormat() {
		// return (DecimalFormat)
		// DecimalFormat.getInstance(Locale.getDefault());
		// return new DecimalFormat("##,###,##0.00", new
		// DecimalFormatSymbols(getLocale()));
		return new DecimalFormat("########,###");

	}

	/**
	 * Method getLocale.
	 * 
	 * @return Locale
	 */
	public static Locale getLocale() {
		return APP_LOCALE_LANG;
	}
}
