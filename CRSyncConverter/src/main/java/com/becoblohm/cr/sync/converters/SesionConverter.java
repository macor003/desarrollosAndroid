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

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class SesionConverter implements ConverterInterface {

    /**
     * 
     */
    public SesionConverter() {

    }

    /*
     * (non-Javadoc)
     * @see com.becoblohm.cr.sync.converters.ConverterInterface#fromServer()
     */
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

        return new crjpa.Sesion();
    }

    /*
     * (non-Javadoc)
     * @see com.becoblohm.cr.sync.converters.ConverterInterface#toServer()
     */
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
        crjpa.Sesion sesionCaja = (crjpa.Sesion) obj;
        crjpa400.Sesion sesion400 = new crjpa400.Sesion();

        SyncWrapper syncObject = new SyncWrapper();

        try {
            sesion400.setAnulaciones(sesionCaja.getAnulaciones());
            sesion400.setDevoluciones(sesionCaja.getDevoluciones());
            sesion400.setFechaCierre(sesionCaja.getFechaCierre());
            sesion400.setFechaCierreVpos(sesionCaja.getFechaCierreVpos());
            sesion400.setFechaInicio(sesionCaja.getFechaInicio());
            sesion400.setFechaUltimoReportez(sesionCaja.getFechaUltimoReportez());
            sesion400.setIdCaja(new crjpa400.Caja((long) sesionCaja.getIdCaja()));
            sesion400.setIdUsuario(new crjpa400.Usuario(sesionCaja.getIdUsuario().getId()));
            sesion400.setVentas(sesionCaja.getVentas());
            sesion400.setVersioncr(sesionCaja.getVersioncr());
            sesion400.setIdTienda(sesionCaja.getIdTienda());
            sesion400.setId(sesionCaja.getId());

            syncObject.setEntity(sesion400);
            syncObject.setId(sesionCaja.getId());

        } catch (Exception e) {
            throw new ConverterException("Sesion", e.getMessage(), sesionCaja.getId().toString());
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
