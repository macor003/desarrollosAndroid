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

import crjpa.Formadepago;
import crjpa.Porcentajeimpuestoretencion;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class PorcentajeimpuestoretencionConverter implements ConverterInterface {

	/**
	 * 
	 */
	public PorcentajeimpuestoretencionConverter() {

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
		crjpa400.Porcentajeimpuestoretencion serverValue = (crjpa400.Porcentajeimpuestoretencion) obj;
		crjpa.Porcentajeimpuestoretencion posValue = new Porcentajeimpuestoretencion();
		posValue.setEstaactivo(new Character(serverValue.getEstaactivo()).toString());
		posValue.setFecha(serverValue.getFecha());
		posValue.setId(serverValue.getId());

		dm.setId(serverValue.getId().toString());

		crjpa.Formadepago tmp1 = new Formadepago(serverValue.getIdFormadepago().getId());
		posValue.setIdFormadepago(tmp1);
		posValue.setPorcentajeMaxRetencion(serverValue.getPorcentajeMaxRetencion());
		posValue.setPorcentajeMinRetencion(serverValue.getPorcentajeMinRetencion());
		posValue.setTipoArtRetencion(new Character(serverValue.getTipoArtRetencion()).toString());
		posValue.setGrupo(serverValue.getGrupo());

		dm.setEntity(posValue);
		dm.setSyncDate(serverValue.getUltimasincronizacion());
		dm.setTableName(crjpa.Porcentajeimpuestoretencion.class.getName().substring(
				crjpa.Porcentajeimpuestoretencion.class.getName().lastIndexOf(".") + 1));
		
		
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
