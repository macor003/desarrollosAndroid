/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.sync.models;

import java.io.Serializable;

/**
 */
public class IdContainer implements Serializable {

    /**
     * Field className.
     */
    private String className;

    /**
     * Field idValue.
     */
    private Object idValue;

    /**
     * Field idName.
     */
    private String idName;

    /**
     * Field upIdValue.
     */
    private Object upIdValue;

    /**
     * Field upIdName.
     */
    private String upIdName;

    /**
     * Constructor for IdContainer.
     */
    public IdContainer() {
    }

    /**
     * Method getIdName.
     * 
     * @return String
     */
    public String getIdName() {
        return idName;
    }

    /**
     * Method setClassName.
     * 
     * @param className String
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Method setIdValue.
     * 
     * @param idValue Object
     */
    public void setIdValue(Object idValue) {
        this.idValue = idValue;
    }

    /**
     * Method getClassName.
     * 
     * @return String
     */
    public String getClassName() {
        return className;
    }

    /**
     * Method getIdValue.
     * 
     * @return Object
     */
    public Object getIdValue() {
        return idValue;
    }

    /**
     * Method setIdName.
     * 
     * @param idName String
     */
    public void setIdName(String idName) {
        this.idName = idName;
    }

    /**
     * Method toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Clase->" + className + "	Nombre->" + idName + "	Valor->" + idValue;
    }

    /**
     * Method setUpIdName.
     * 
     * @param upIdName String
     */
    public void setUpIdName(String upIdName) {
        this.upIdName = upIdName;
    }

    /**
     * Method getUpIdName.
     * 
     * @return String
     */
    public String getUpIdName() {
        return upIdName;
    }

    /**
     * Method setUpIdValue.
     * 
     * @param upIdValue Object
     */
    public void setUpIdValue(Object upIdValue) {
        this.upIdValue = upIdValue;
    }

    /**
     * Method getUpIdValue.
     * 
     * @return Object
     */
    public Object getUpIdValue() {
        return upIdValue;
    }

}
