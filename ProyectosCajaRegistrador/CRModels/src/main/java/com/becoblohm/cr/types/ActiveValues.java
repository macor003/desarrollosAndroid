/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.types;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 */
public enum ActiveValues {
    /**
     * Field S.
     */
    S(true),
    /**
     * Field N.
     */
    N(false);

    /**
     * Field forward.
     */
    private static final Map<String, ActiveValues> forward = new HashMap<String, ActiveValues>();

    /**
     * Field backwards.
     */
    private static final Map<Boolean, ActiveValues> backwards = new HashMap<Boolean, ActiveValues>();

    static {
        for (ActiveValues s : EnumSet.allOf(ActiveValues.class)) {
            forward.put(s.name(), s);
            backwards.put(s.value, s);
        }
    }

    /**
     * Field value.
     */
    private boolean value;

    /**
     * Field desc.
     */
    private String desc;

    /**
     * Constructor for ActiveValues.
     * 
     * @param value boolean
     */
    ActiveValues(boolean value) {
        this.value = value;
        this.desc = value ? "S" : "N";
    }

    /**
     * Method getValue.
     * 
     * @return boolean
     */
    public boolean getValue() {
        return this.value;
    }

    /**
     * Method getString.
     * 
     * @return String
     */
    public String getString() {
        return this.desc;
    }

    /**
     * Method get.
     * 
     * @param value String
     * @return ActiveValues
     */
    public static ActiveValues get(String value) {
        ActiveValues res = forward.get(value);
        if (res == null) {
            res = ActiveValues.N;
        }
        return res;
    }

    /**
     * Method get.
     * 
     * @param value boolean
     * @return ActiveValues
     */
    public static ActiveValues get(boolean value) {
        return backwards.get(value);
    }

}
