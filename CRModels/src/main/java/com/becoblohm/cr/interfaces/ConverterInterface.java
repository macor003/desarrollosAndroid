/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.interfaces;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.sync.models.SyncWrapper;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public interface ConverterInterface {

    /**
     * Method fromServer.
     * 
     * @param obj Object
     * @param posType String
     * @return Object
     * @throws ConverterException
     */
    public Object fromServer(Object obj, String posType) throws ConverterException;

    /**
     * Method toServer.
     * 
     * @param obj Object
     * @return Object
     * @throws ConverterException
     */
    public SyncWrapper toServer(Object obj) throws ConverterException;

    /**
     * Method getPosEmf.
     * 
     * @return EntityManagerFactory
     */
    public EntityManagerFactory getPosEmf();

    /**
     * Method getServerEmf.
     * 
     * @return EntityManagerFactory
     */
    public EntityManagerFactory getServerEmf();

    /**
     * Method setPosNumber.
     * 
     * @param posNumber String
     */
    public void setPosNumber(String posNumber);
}
