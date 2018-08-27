/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.sync.converters;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.ConverterException;
import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.DataModel;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;
import com.becoblohm.cr.utils.CRUtils;

import crjpa.Linea;
import crjpa.Servicio;
import crjpa.Tasaimpuestotipo;
import crjpa400.Articulo;
import crjpa400.Articuloservicio;
import crjpa400.Exoneradoarticulo;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ArticuloConverter implements ConverterInterface {

	/**
	 * 
	 */
	public ArticuloConverter() {
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

		ArticulounidadventaConverter saleUnitConverter = new ArticulounidadventaConverter();
		ArticuloServicioConverter serviceConverter = new ArticuloServicioConverter();
		ExoneradotipoConverter exonerationConverter = new ExoneradotipoConverter();

		DataModel dm = new DataModel();

		crjpa400.Articulo serverArticle = (Articulo) obj;
		crjpa.Articulo posArticle = new crjpa.Articulo();

		posArticle.setCodigo(serverArticle.getCodigo().longValue());
		posArticle.setEstaactivo(String.valueOf(serverArticle.getEstaactivo()));
		posArticle.setFecha(serverArticle.getFecha());
		posArticle.setId(serverArticle.getId());

		dm.setId(serverArticle.getId().toString());

		Articulo articulocategorizado = serverArticle.getIdArticulocategorizado();
		if (articulocategorizado != null) {
			if (articulocategorizado.getId().longValue() != serverArticle.getId().longValue()) {
				DataModel dmTmp = (DataModel) fromServer(articulocategorizado, null);
				posArticle.setIdArticulocategorizado((crjpa.Articulo) dmTmp.getEntity());
			}
		}
		posArticle.setNombre(CRUtils.removeSpecialChars(serverArticle.getNombre()));
		if (serverArticle.getIdFamilia() != null) {
			posArticle.setIdFamilia(new crjpa.Familia(serverArticle.getIdFamilia().getId()));
		}
		posArticle.setIdLinea(new Linea(serverArticle.getIdLinea().getId()));
		if (serverArticle.getIdTasaimpuestotipo() != null) {
			posArticle.setIdTasaimpuestotipo(new Tasaimpuestotipo(serverArticle.getIdTasaimpuestotipo().getId()));
		}

		ArrayList<crjpa.Articulounidadventa> saleUnitList = new ArrayList<crjpa.Articulounidadventa>();
		DataModel dmTmp;
		for (crjpa400.Articulounidadventa tmp2 : serverArticle.getArticulounidadventaList()) {
			dmTmp = (DataModel) saleUnitConverter.fromServer(tmp2, null);
			saleUnitList.add((crjpa.Articulounidadventa) dmTmp.getEntity());
		}
		posArticle.setArticulounidadventaList(saleUnitList);

		ArrayList<crjpa.Servicio> servicesList = new ArrayList<crjpa.Servicio>();
		if (serverArticle.getArticuloservicioList() != null) {
			for (Articuloservicio artServ : serverArticle.getArticuloservicioList()) {
				servicesList.add((Servicio) serviceConverter.fromServer(artServ, null));
			}
		}
		posArticle.setServicioList(servicesList);
		ArrayList<crjpa.Exoneradotipo> exonerationList = new ArrayList<crjpa.Exoneradotipo>();
		if (serverArticle.getExoneradoarticuloList() != null) {
			for (Exoneradoarticulo exoneration : serverArticle.getExoneradoarticuloList()) {
				crjpa.Exoneradotipo tmp = (crjpa.Exoneradotipo) ((DataModel) exonerationConverter.fromServer(
						exoneration.getExoneradotipo(), null)).getEntity();
				exonerationList.add(tmp);
			}
		}

		posArticle.setExoneradotipoList(exonerationList);
		dm.setEntity(posArticle);
		dm.setSyncDate(serverArticle.getUltimasincronizacion());
		dm.setTableName(crjpa.Articulo.class.getName().substring(crjpa.Articulo.class.getName().lastIndexOf(".") + 1));

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
