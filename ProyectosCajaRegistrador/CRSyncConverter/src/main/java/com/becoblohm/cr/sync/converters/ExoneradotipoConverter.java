/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.sync.converters;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.DataModel;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

/**
 */
public class ExoneradotipoConverter implements ConverterInterface {

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
		crjpa400.Exoneradotipo serverValue = (crjpa400.Exoneradotipo) obj;
		crjpa.Exoneradotipo posValue = new crjpa.Exoneradotipo();
		posValue.setAplicaParcial(String.valueOf(serverValue.getAplicaParcial()));
		// ArrayList<crjpa.Articulo> tmp=new ArrayList<crjpa.Articulo>();
		// for(Exoneradoarticulo art:serverValue.getExoneradoarticuloList()) {
		// tmp.add(new crjpa.Articulo(art.getArticulo().getId()));
		// }
		// posValue.setArticuloList(tmp);
		posValue.setCalculaImpuesto(String.valueOf(serverValue.getCalculaImpuesto()));
		posValue.setDescripcion(serverValue.getDescripcion());
		posValue.setId(serverValue.getId());

		dm.setId(serverValue.getId().toString());

		dm.setEntity(posValue);
		dm.setSyncDate(serverValue.getUltimasincronizacion());
		dm.setTableName(crjpa.Exoneradotipo.class.getName().substring(
				crjpa.Exoneradotipo.class.getName().lastIndexOf(".") + 1));

		return dm;
	}

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
