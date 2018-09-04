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
public class UsuarioperfilConverter implements ConverterInterface {

	/**
	 * 
	 */
	public UsuarioperfilConverter() {

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

		crjpa400.Usuarioperfil usuarioPerfil400 = (crjpa400.Usuarioperfil) obj;
		crjpa.Usuarioperfil usuarioPerfilCaja = new crjpa.Usuarioperfil(usuarioPerfil400.getId());

		dm.setId(usuarioPerfil400.getId().toString());

		usuarioPerfilCaja.setFecha(usuarioPerfil400.getFecha());
		usuarioPerfilCaja.setIdPerfil(new crjpa.Perfil(usuarioPerfil400.getIdPerfil().getId()));
		usuarioPerfilCaja.setIdUsuario(new crjpa.Usuario(usuarioPerfil400.getIdUsuario().getId()));

		dm.setEntity(usuarioPerfilCaja);
		dm.setSyncDate(usuarioPerfil400.getUltimasincronizacion());
		dm.setTableName(crjpa.Usuarioperfil.class.getName().substring(
				crjpa.Usuarioperfil.class.getName().lastIndexOf(".") + 1));
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
		crjpa.Usuarioperfil usuarioPerfilCaja = (crjpa.Usuarioperfil) obj;
		crjpa400.Usuarioperfil usuarioPerfil400 = new crjpa400.Usuarioperfil(usuarioPerfilCaja.getId());
		
		SyncWrapper syncObject = new SyncWrapper();

		try{
		usuarioPerfil400.setFecha(usuarioPerfilCaja.getFecha());
		usuarioPerfil400.setIdPerfil(new crjpa400.Perfil(usuarioPerfilCaja.getIdPerfil().getId()));
		usuarioPerfil400.setIdUsuario(new crjpa400.Usuario(usuarioPerfilCaja.getIdUsuario().getId()));
		usuarioPerfil400.setUltimasincronizacion(Calendar.getInstance());
		
		syncObject.setEntity(usuarioPerfil400);
		syncObject.setId(usuarioPerfilCaja.getId());
		
		}catch(Exception e){
			throw new ConverterException("Usuarioperfil", e.getMessage(), usuarioPerfilCaja.getId().toString());
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
