/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.i18n;

import java.util.ListResourceBundle;

/**
 */
public class ErrorsResourceBundle extends ListResourceBundle {

    /**
     * Field contents.
     */
    private static final Object[][] contents = {
            // LOCALIZE THIS
            {"error", "error desconocido"}, {"error.cancel", "Accion Cancelada"},
            {"error.auth.fingerprintread", "Problemas leyendo la Huella"},
            {"error.invalidprice", "Precio de producto invalido"},

            {"error.prnnocomplete", "La impresion de {0} ha fallado, ¿desea reintentar?"},
            {"ERRXXX", "Estado inesperado, Contactar al EA"}, {"ERR602", "Contactar al EA [ERR-602]"}
            // END OF MATERIAL TO LOCALIZE
    };

    /**
     * Method getContents.
     * 
     * 
     * @return Object[][]
     */
    @Override
    public Object[][] getContents() {

        return contents;
    }

}
