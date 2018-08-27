/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.sync.converters;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.DataModel;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

import crjpa.Categorialinearetencion;
import crjpa.Formadepago;
import crjpa.Linea;

/**
 * @version $Revision: 1.0 $
 */
public class CategoriaLinearetencionConverter implements ConverterInterface {

	/**
	 * 
	 */
	public CategoriaLinearetencionConverter() {

	}

	/**
	 * Method fromServer.
	 * 
	 * @param obj Object
	 * @param posId String
	 * @return Object
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#fromServer(Object,String)
	 */
	@Override
	public Object fromServer(Object obj, String posId) {
		DataModel dm = new DataModel();
		crjpa400.Categorialinearetencion serverRetentionLine = (crjpa400.Categorialinearetencion) obj;
		crjpa.Categorialinearetencion posRetentionLine = new Categorialinearetencion();

		posRetentionLine.setFecha(serverRetentionLine.getFecha());
		posRetentionLine.setId(serverRetentionLine.getId());

		dm.setId(serverRetentionLine.getId().toString());

		crjpa.Linea lineTmp = new Linea(serverRetentionLine.getIdLinea().getId());
		posRetentionLine.setIdPorcentajeimpuestoretencion(new crjpa.Porcentajeimpuestoretencion(serverRetentionLine.getIdPorcentajeimpuestoretencion().getId()));
		posRetentionLine.setIdLinea(lineTmp);

		dm.setEntity(posRetentionLine);
		dm.setSyncDate(serverRetentionLine.getUltimasincronizacion());
		dm.setTableName(crjpa.Categorialinearetencion.class.getName().substring(
				crjpa.Categorialinearetencion.class.getName().lastIndexOf(".") + 1));

		return dm;
	}

	/**
	 * Method toServer.
	 * 
	 * @param obj Object
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
	 * @param posNumber String
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#setPosNumber(String)
	 */
	@Override
	public void setPosNumber(String posNumber) {
	}
}
