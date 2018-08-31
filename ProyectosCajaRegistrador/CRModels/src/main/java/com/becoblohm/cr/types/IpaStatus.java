/*******************************************************************************
 * © 2014 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * @author eve0017820
 */
package com.becoblohm.cr.types;

public enum IpaStatus {

    /**
     * Status de pendiente para acreencias en proceso
     */
    PENDING('P'),
    /**
     * Status para acreencias que ya fueron aplicadas en central
     */
    APPLIED('A'),
    /**
     * Status para acreencias que fueron reversadas
     */
    REVERSED('R');

    private char type;

    IpaStatus(char s) {
        this.type = s;
    }

    /**
     * 
     * @param status a verificar y determinar si es valido
     * @return true si es valido, false en caso contrario
     */
    public static boolean checkValidStatus(final char status) {
        for (IpaStatus validStatus : IpaStatus.values()) {
            if (validStatus.type == status) {
                return true;
            }
        }
        return false;
    }
}
