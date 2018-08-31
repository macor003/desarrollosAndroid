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
import crjpa.Tipocliente;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class FormadepagotipoclienteConverter implements ConverterInterface {

	/**
	 * 
	 */
	public FormadepagotipoclienteConverter() {

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
		crjpa400.Formadepagotipocliente fdptipocliente400 = (crjpa400.Formadepagotipocliente) obj;
		crjpa.Formadepagotipocliente fdptipoclienteCaja = new crjpa.Formadepagotipocliente(fdptipocliente400.getId());

		dm.setId(fdptipocliente400.getId().toString());

		fdptipoclienteCaja.setEstaactivo(String.valueOf(fdptipocliente400.getEstaactivo()));
		fdptipoclienteCaja.setIdFormadepago(new Formadepago(fdptipocliente400.getIdFormadepago().getId()));
		fdptipoclienteCaja.setIdTipocliente(new Tipocliente(fdptipocliente400.getIdTipocliente().getId()));
		
		dm.setEntity(fdptipoclienteCaja);
		dm.setSyncDate(fdptipocliente400.getUltimasincronizacion());
		dm.setTableName(crjpa.Formadepagotipocliente.class.getName().substring(crjpa.Formadepagotipocliente.class.getName().lastIndexOf(".") + 1));
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
