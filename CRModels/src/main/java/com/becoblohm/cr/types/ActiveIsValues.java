/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.types;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 */
public enum ActiveIsValues {
    /**
     * Field A.
     */
    A(true),
    /**
     * Field I.
     */
    I(false);

    /**
     * Field lookup.
     */
    private static final Map<String, ActiveIsValues> lookup = new HashMap<String, ActiveIsValues>();

    static {
        for (ActiveIsValues s : EnumSet.allOf(ActiveIsValues.class))
            lookup.put(s.name(), s);
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
     * Constructor for ActiveIsValues.
     * 
     * @param value boolean
     */
    ActiveIsValues(boolean value) {
        this.value = value;
        this.desc = value ? "A" : "I";
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
     * @return ActiveIsValues
     */
    public static ActiveIsValues get(String value) {
        return lookup.get(value);
    }

    /**
     * Method get.
     * 
     * @param value boolean
     * @return ActiveIsValues
     */
    public static ActiveIsValues get(boolean value) {
        return lookup.get(value);
    }
}
