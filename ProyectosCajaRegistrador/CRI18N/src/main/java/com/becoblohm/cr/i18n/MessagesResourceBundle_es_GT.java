/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.i18n;

import java.util.ListResourceBundle;

/**
 * @author programador6
 * @version $Revision: 1.0 $
 */
public class MessagesResourceBundle_es_GT extends ListResourceBundle {

    /**
     * Field contents.
     */
    private static final Object[][] contents = {
            // LOCALIZE THIS

            {"retention.existdelete",
                    "<html>Existen pagos de tipo retenci&oacute;n o exenci&oacute;n.<br/>Las retenciones o exenciones se reversar&aacute;n.<html/>"},
            // {"retention.existdelete","<html>Existen pagos de tipo
            // retenci&oacute;n.<br/>Si modifica los articulos estos pagos se
            // reversaran.<html/>"},
            {"check.amount.length.exceeded",
                    "<html>El monto del cheque en palabras excede la longitud m&aacute;xima</html>"}
            /**************************************/

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
