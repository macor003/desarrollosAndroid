package com.becoblohm.cr.sync.models;

import java.io.Serializable;

public class SyncWrapper implements Serializable {

    private static final long serialVersionUID = 6940682154976294883L;

    private Object entity;

    private Object id;

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

}
