/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.sync.converters;

import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.ConverterException;
import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.DataModel;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

/**
 * 
 * @version $Revision: 1.0 $
 */
public class UsuarioConverter implements ConverterInterface {

	/**
	 * 
	 */
	public UsuarioConverter() {

	}

	/**
	 * Method fromServer.
	 * 
	 * @param obj Object
	 * @param posId String
	 * @return Object
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#fromServer(Object, String)
	 */
	@Override
	public Object fromServer(Object obj, String posId) {
		UsuarioperfilConverter converter = new UsuarioperfilConverter();
		ArrayList<crjpa.Usuarioperfil> lista = new ArrayList<crjpa.Usuarioperfil>();

		DataModel dm = new DataModel();
		crjpa400.Usuario usuario400 = (crjpa400.Usuario) obj;
		crjpa.Usuario usuarioCaja = new crjpa.Usuario(usuario400.getId());

		dm.setId(usuario400.getId().toString());

		usuarioCaja.setClave(usuario400.getClave());
		usuarioCaja.setCodigoBarra(usuario400.getCodigoBarra());
		usuarioCaja.setFecha(usuarioCaja.getFecha());
		usuarioCaja.setFechaCambioClave(usuario400.getFechaCambioclave());
		usuarioCaja.setFicha(usuario400.getFicha());
		usuarioCaja.setHuella((byte[]) usuario400.getHuella());
		usuarioCaja.setNombre(usuario400.getNombre());

		usuarioCaja.setUsuarioperfilList(lista);
		usuarioCaja.setEstasincronizado("S");

		dm.setEntity(usuarioCaja);
		dm.setSyncDate(usuario400.getUltimasincronizacion());
		dm.setTableName(crjpa.Usuario.class.getName().substring(crjpa.Usuario.class.getName().lastIndexOf(".") + 1));
		return dm;
	}

	/**
	 * Method toServer.
	 * 
	 * @param obj Object
	 * @return Object
	 * @throws ConverterException 
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#toServer(Object)
	 */
	@Override
	public SyncWrapper toServer(Object obj) throws ConverterException {
		UsuarioperfilConverter converter = new UsuarioperfilConverter();
		ArrayList<crjpa400.Usuarioperfil> lista = new ArrayList<crjpa400.Usuarioperfil>();

		crjpa.Usuario usuarioCaja = (crjpa.Usuario) obj;
		crjpa400.Usuario usuario400 = new crjpa400.Usuario(usuarioCaja.getId());
		
		SyncWrapper syncObject = new SyncWrapper();

		try{
			usuario400.setClave(usuarioCaja.getClave());
			usuario400.setCodigoBarra(usuarioCaja.getCodigoBarra());
			usuario400.setFecha(usuarioCaja.getFecha());
			usuario400.setFechaCambioclave(usuarioCaja.getFechaCambioClave());
			usuario400.setFicha(usuarioCaja.getFicha());
			usuario400.setHuella(usuarioCaja.getHuella());
			usuario400.setNombre(usuarioCaja.getNombre());
			usuario400.setUltimasincronizacion(Calendar.getInstance());

			for (crjpa.Usuarioperfil usuarioperfil : usuarioCaja.getUsuarioperfilList()) {
				lista.add((crjpa400.Usuarioperfil) converter.toServer(usuarioperfil).getEntity());
			}
			usuario400.setUsuarioperfilList(lista);
			
			syncObject.setEntity(usuario400);
			syncObject.setId(usuarioCaja.getId());			
			
		}catch(Exception e){
			throw new ConverterException("Usuario", e.getMessage(), usuarioCaja.getId().toString());
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
	 * @param posNumber String
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#setPosNumber(String)
	 */
	@Override
	public void setPosNumber(String posNumber) {
	}
}
