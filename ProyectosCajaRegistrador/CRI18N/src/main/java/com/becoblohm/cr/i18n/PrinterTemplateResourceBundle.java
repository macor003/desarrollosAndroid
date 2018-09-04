/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.i18n;

import java.util.ListResourceBundle;

/**
 */
public class PrinterTemplateResourceBundle extends ListResourceBundle {

    /**
     * Field contents.
     */
    private static final Object[][] contents = {
            // LOCALIZE THIS
            {"abonopedido", "document_abonodepedido.xml"}, {"anul2", "document_anul.xml"},
            {"anulabonopedido", "document_anulabonodepedido.xml"},
            {"anulacorporativo", "document_anulacorporativo.xml"},
            {"anulacreditoepa", "document_anulacreditoepa.xml"}, {"cargocuenta", "document_cargocuenta.xml"},
            {"dev2", "document_dev.xml"}, {"eparcial", "document_eparcial.xml"}, {"etotal", "document_etotal.xml"},
            {"nentrega", "document_nentrega.xml"}, {"pagocorporativo", "document_pagocorporativo.xml"},
            {"pagocreditoepa", "document_pagocreditoepa.xml"}, {"primerabono", "document_primerAbonodepedido.xml"},
            {"audithpaperdoc", ""}, {"retira", "document_retira.xml"}, {"sale1", "document_sale.xml"},
            {"headernf", "document_nofiscal.xml"}, {"creditocorporativo", "document_creditoCorporativo.xml"},
            {"report_header", "document_report_header.xml"}
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
