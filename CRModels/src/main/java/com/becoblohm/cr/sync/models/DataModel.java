/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.sync.models;

import java.io.Serializable;
import java.util.Calendar;

/**
 */
public class DataModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field entity.
     */
    private Object entity;

    /**
     * Field id.
     */
    private String id;

    /**
     * Field syncDate.
     */
    private Calendar syncDate;

    /**
     * Field tableName.
     */
    private String tableName;

    /**
     * Field canStore.
     */
    private boolean canStore = true;

    /**
     * Field forSpecificPosId.
     */
    private boolean forSpecificPosId = false;

    /**
     * Method getId.
     * 
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Method setId.
     * 
     * @param id String
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method getEntity.
     * 
     * @return Object
     */
    public Object getEntity() {
        return entity;
    }

    /**
     * Method setEntity.
     * 
     * @param entity Object
     */
    public void setEntity(Object entity) {
        this.entity = entity;
    }

    /**
     * Method getSyncDate.
     * 
     * @return Calendar
     */
    public Calendar getSyncDate() {
        return syncDate;
    }

    /**
     * Method setSyncDate.
     * 
     * @param syncDate Calendar
     */
    public void setSyncDate(Calendar syncDate) {
        this.syncDate = syncDate;
    }

    /**
     * Method getTableName.
     * 
     * @return String
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Method setTableName.
     * 
     * @param tableName String
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Method setCanStore.
     * 
     * @param canStore boolean
     */
    public void setCanStore(boolean canStore) {
        this.canStore = canStore;
    }

    /**
     * Method isCanStore.
     * 
     * @return boolean
     */
    public boolean isCanStore() {
        return canStore;
    }

    /**
     * Method isForSpecificPosId.
     * 
     * @return boolean
     */
    public boolean isForSpecificPosId() {
        return forSpecificPosId;
    }

    /**
     * Method setForSpecificPosId.
     * 
     * @param forSpecificPosId boolean
     */
    public void setForSpecificPosId(boolean forSpecificPosId) {
        this.forSpecificPosId = forSpecificPosId;
    }

}
