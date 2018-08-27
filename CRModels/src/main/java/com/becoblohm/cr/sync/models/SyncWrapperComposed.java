package com.becoblohm.cr.sync.models;

import java.util.HashMap;

public class SyncWrapperComposed extends SyncWrapper {

    private static final long serialVersionUID = -6460687589159394779L;

    private HashMap<String, Object> composedKeySet;

    public HashMap<String, Object> getComposedKeySet() {
        return composedKeySet;
    }

    public void setComposedKeySet(HashMap<String, Object> composedKeySet) {
        this.composedKeySet = composedKeySet;
        super.setId(composedKeySet);
    }

}
