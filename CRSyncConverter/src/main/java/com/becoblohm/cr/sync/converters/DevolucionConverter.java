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

import crjpa400.Devolucion;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class DevolucionConverter implements ConverterInterface {

    /**
     * 
     */
    public DevolucionConverter() {

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

        return new crjpa.Devolucion();
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

    @Override
    public SyncWrapper toServer(Object obj) throws ConverterException {
        // TODO Auto-generated method stub
        return null;
    }
}
