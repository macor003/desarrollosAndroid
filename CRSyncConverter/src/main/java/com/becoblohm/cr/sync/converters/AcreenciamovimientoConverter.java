/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.sync.converters;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.ConverterException;
import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;

import crjpa.Acreenciamovimiento;
import crjpa400.AcreenciamovimientoPK;
import crjpa400.Formadepago;
import crjpa400.Moneda;
import crjpa400.Operacionacreencia;

/**
 * @version $Revision: 1.0 $
 */
public class AcreenciamovimientoConverter implements ConverterInterface {

	/**
	 * 
	 */
	public AcreenciamovimientoConverter() {

	}

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
		return new crjpa.Acreenciamovimiento();
	}

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
		crjpa400.Acreenciamovimiento mov = new crjpa400.Acreenciamovimiento();
		crjpa.Acreenciamovimiento tmp = (Acreenciamovimiento) obj;
		
		SyncWrapper syncObject = new SyncWrapper();

		try{
			mov.setAnulaoperacion(tmp.getAnulaoperacion());
			mov.setCaja(tmp.getCaja());
			mov.setCajero(tmp.getCajero());
			if (tmp.getControlreplicacion().length() > 0) {
				mov.setControlreplicacion(tmp.getControlreplicacion().charAt(0));
			} else {
				mov.setControlreplicacion('\u0020');
			}

			mov.setEstreplica('\u0020');

			mov.setCorrelativo(tmp.getCorrelativo());
			mov.setDocumentoformadepago(tmp.getDocumentoformadepago());
			mov.setEstado(tmp.getEstado());

			// Modificacion Timeout Acreencia
			// seteo de la nueva PK
			mov.setIpaId(tmp.getIpaId());
			// Seteo directamente de los campos que formaban la PK anterior
			mov.setIdAcreenciamovimientosaldo(tmp.getIdAcreenciamovimientosaldo());
			mov.setIdAcreenciamovimientoformadepago(tmp.getIdAcreenciamovimientoformadepago());		
			// Seteo de los nuevos campos
			mov.setIpaStatus(tmp.getIpaStatus());
			// Se elimina la creacion de la PK anterior	
			//		mov.setAcreenciamovimientoPK(new AcreenciamovimientoPK(tmp.getAcreenciamovimientoPK()
			//				.getIdAcreenciamovimientosaldo(), tmp.getAcreenciamovimientoPK().getIdAcreenciamovimientoformadepago()));
			mov.setFecha(tmp.getFecha());
			mov.setIdAcreencia(tmp.getIdAcreencia());
			mov.setIdFormadepago(new Formadepago(tmp.getIdFormadepago().getId()));
			mov.setIdMoneda(new Moneda(tmp.getIdMoneda().getId()));
			mov.setIdOperacionacreencia(new Operacionacreencia(tmp.getIdOperacionacreencia().getId()));
			mov.setIdTipoacreencia(new crjpa400.Tipoacreencia(tmp.getIdTipoacreencia().getId()));
			mov.setMontoMoneda(tmp.getMontoMoneda());
			mov.setMontoMonedaLocal(tmp.getMontoMonedaLocal());
			mov.setNombreunidadnegocio(tmp.getNombreunidadnegocio());
			mov.setNombreunidadoperativa(tmp.getNombreunidadoperativa());
			mov.setNumeroIdentificacionCliente(tmp.getNumeroIdentificacionCliente());
			mov.setOperacion(tmp.getOperacion());
			mov.setRecibodecaja(tmp.getRecibodecaja());
			mov.setTienda(tmp.getTienda());
			mov.setTransaccion(tmp.getTransaccion());
			mov.setVuelto(tmp.getVuelto());
			
			syncObject.setEntity(mov);
			syncObject.setId(tmp.getIpaId());
			
		}catch(Exception e){
			throw new ConverterException("", e.getMessage(), tmp.getIpaId()+"");
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
