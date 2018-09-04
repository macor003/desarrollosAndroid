/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.i18n;

import java.util.ListResourceBundle;

/**
 */
public class IconsResourceBundle_es_GT extends ListResourceBundle {

    /**
     * Field contents.
     */
    private static final Object[][] contents = {
            // LOCALIZE THIS
            // cuando no hay imagen colocar "sin-foto.png"
            {"up", "up.png"}, {"down", "down.png"}, {"vtaespera", "ventaespera.png"}, {"customer", "cliente.png"},
            {"payment", "payment.png"}, {"op_payment", "op_payment.png"}, {"delivery", "entrega.png"},
            {"typedoc", "tipo_doc.png"}, {"refound", "refound.png"},

            {"rebate", "rebate.png"}, {"cancellation", "cancellation.png"},

            {"auth", "auth.png"}, {"info", "info.png"},

            {"ce_c", "ce_caja.png"}, {"ce_d", "ce_despacho.png"}, {"ce_r", "ce_cliente.png"},
            {"ce_e", "ce_domicilio.png"},

            {"add", "add.png"}, {"sub", "sub.png"}, {"remove", "remove.png"},

            {"addself", "add_self.png"}, {"subself", "sub_self.png"}, {"removeself", "remove_self.png"},

            {"lockpos", "bloquear_caja.png"},

            {"partialcashier", "entrega_parcial.png"}, {"totalcashier", "cierre_cajero.png"},
            {"opendrawer", "abrir_gaveta.png"}, {"retention", "retencion.png"}, {"queries", "consultas.png"},
            {"canceltrans", "borrar_trans.png"}, {"op_canceltrans", "op_borrar_trans.png"},

            {"clientenatural", "cf.png"}, {"clientejuridico", "contribuyente.png"},

            // {"login.img", "login.jpg"},
            // {"main.img", "main.jpg"},
            // {"logo.img", "epa.jpg"},

            {"vposclosure", "closurevpos.png"}, {"vpospreclosure", "preclosurevpos.png"}, {"vpos", "vpos.png"},

            {"closureday", "cierre_dia.png"}, {"credits", "acreencia.png"}, {"creditsepa", "creditoepa.png"},
            {"cepa", "cepa.png"}, {"reprint", "reimprimir.png"},

            {"reprintpay", "reprintpay.png"},

            {"exencion", "exencion.png"}, {"exencionx", "exencionx.png"},

            {"paypreorder", "abonoazul.png"},

            {"paperroll", "auditoria.png"}

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
