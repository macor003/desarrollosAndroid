/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.i18n;

import java.util.ListResourceBundle;

/**
 * @author programador6
 * @version $Revision: 1.0 $
 */
public class LabelsResourceBundle_es_GT extends ListResourceBundle {

    /**
     * Field contents.
     */
    private static final Object[][] contents = {
            // LOCALIZE THIS
            {"global.ok", "Aceptar"}, {"global.cancel", "Cancelar"}, {"global.id", "Doc. Identidad"},
            {"global.trans", "Transacción"}, {"global.name", "Nombre"}, {"global.pass", "Clave"},
            {"global.repeatpass", "Confirme su clave"}, {"global.customer", "Cliente"},
            {"global.direccion", "Dirección"}, {"global.cashregister", "Caja"}, {"global.cashier", "Cajero"},
            {"global.reason", "Motivo"}, {"global.cancellation", "Anulación"}, {"global.rebate", "Rebaja"},
            {"global.refound", "Devolución"}, {"global.credits", "Acreencias"},
            {"global.customerserving", "Cliente retira"}, {"global.creditsepa", "Operaciones Crédito"},
            {"global.ccf", "CCF"}, {"global.bill", "Factura"}, {"global.number", "Número"},
            {"global.amount", "Monto"}, {"global.date", "Fecha"}, {"global.hour", "Hora"},
            {"global.status", "Estado"}, {"global.description", "Descripción"}, {"global.active", "Activo"},
            {"global.pendingForCancellation", "Descripción"}, {"global.yes", "Si"}, {"global.no", "No"},
            {"global.pendint", "Pendiente"}, {"global.iherited", "Heredado"}, {"global.deposit", "Depósito"},
            {"global.cancellationNumber", "Anula "}, {"global.type", "Tipo"}, {"global.exit", "Salir"},
            {"global.document", "N° Documento"}, {"global.user", "Usuario"},
            {"global.transactionid", "Id de Transaccion"},
            {"global.originaltransactionid", "Id de transaccion original"},

            {"global.sesionid", "Id de sesion"},

            {"auth.title", "Autorización"}, {"auth.subtitle", "Modulo Autorizante"},
            {"auth.fingerprint", "Presente Huella"},

            {"auth.changepass", "Nueva Contraseña (5-9 digitos)"}, {"auth.changepassconfirm", "Confirmar"},
            {"auth.nomatch", "Los campos contraseña y confirmación deben ser iguales"},
            {"auth.passusedbefore", "Contraseña coincide con la actual"},
            {"auth.changepasstitle", "Cambio de Contraseña"},

            {"waitingsale.button", "Venta en Espera"}, {"doctype.button", "Tipo Documento"},
            {"queries.button", "Consultas"},

            /* Texto de botones de acción */

            {"action.ok", "Aceptar"}, {"action.cancel", "Cancelar"}, {"action.print", "Imprimir"},
            {"action.delete", "Eliminar"}, {"action.pay", "Pagar"}, {"action.delivery", "Condición de Entrega"},
            {"action.cashier", "Cajero"}, {"action.auth", "Autorizante"}, {"action.return", "Regresar"},

            /* Etiquetas del modulo de cajero */
            {"cashier.tienda", "Tienda: "}, {"cashier.fecha", "Fecha: "}, {"cashier.datos", "Cajero: "},
            {"cashier.parcial", "Entrega parcial de Cajero"}, {"cashier.partialheader", "Entrega Parcial  "},
            {"cashier.total", "Entrega total de Cajero"}, {"cashier.totalmoney", "Total"},
            {"cashier.totalcash", "Total Entrega"}, {"cashier.currency", "Denominación"},
            {"cashier.nominal", "Valor Nominal"}, {"cashier.billsquantity", "Cantidad Billetes"},
            {"cashier.totalbills", "Total Billetes"}, {"cashier.entrega", "Entrega autorizada por"},
            {"cashier.entregaoffline", "Entrega fuera de línea"},
            {"cashier.transexceeded", "Transaccion excedida: # "}, {"cashier.fund", "Fondo de Cajero: "},
            {"cashier.monto", "Monto"}, {"cashier.editar", "Editar"}, {"cashier.detalles", "Detalles"},
            {"cashier.maslineas", "Más líneas"}, {"cashier.totalbutton", "Entrega Total"},
            {"cashier.fund0", "El fondo del cajero debe ser mayor a 0"},
            {"cashier.delivery", "<html>Reporte X</html>"}, {"cashier.deliverytitle", "Reporte X"},
            {"cashier.deliverythird", "¿Entrega realizada por tercero?"},
            {"cashier.drawerproblem", "Problemas con la gaveta"}, {"cashier.cashier", "Cajero"},
            {"cashier.third", "Tercero"},

            /*
             * Mensajes de impresora
             */
            {"printer.problem", "<html>Problemas con la impresora<br/>Verifique e intente de nuevo.</html>"},

            /*
             * Etiquetas para pedidos especiales
             */
            {"orders.id", "Id de pedido"}, {"orders.type", "Tipo de pedido"}, {"orders.title", "Servicios"},
            {"orders.depositTitle", "Abono de pedido"}, {"orders.depositnew", "Abonar"},
            {"orders.depositCancellation", "Anular abono"}, {"orders.amountToDeposit", "Monto a Abonar"},
            {"orders.amountToDepositTitle", "Abono de pedido"}, {"orders.penalty", "Cobro por servicio"},

            {"query.type", "Tipo de Consulta"}, {"query.attribute", "Atributo"},

            {"donation.title", "Ayudar es Sencillo"}, {"donation.addRound", "Adicional a Redondeo"},
            {"donation.round", "Redondeo"},

            /* Etiquetas del Modulo Retencion */
            {"retention.name", "Retencion de Impuestos"}, {"retention.number", "Número de comprobante"},
            {"retention.amountretention", "¿Qué Monto desea retener?"},
            {"retention.maxamount", "Monto de retención mayor al permitido"},
            {"retention.minamount", "Monto de retención menor al permitido"},
            {"retention.errortyperetention", "No se puede aplicar este tipo de retención"},
            {"retention.erroramountretention",
                    "No se puede aplicar retención de impuesto porque" + System.getProperty("line.separator")
                            + "la suma del monto de los artículos es menor a "},
            {"retention.errorpayprocess",
                    "Todos los pagos han sido procesados para esta factura," + System.getProperty("line.separator")
                            + "por tanto no pueden aplicarse retenciones."},

            /* Etiquetas del Modulo Pagar */
            {"payment.name", "Forma de pago"}, {"payment.verify", "Verifique monto: "},
            {"payment.docnumber", "Numero de documento"}, {"payment.client", "Nombre del cliente: "},
            {"payment.process", "Tipo de proceso: "}, {"payment.totalpay", "Total pagado : "},
            {"payment.totalcost", "Monto a pagar : "}, {"payment.diference", "Restan : "},
            {"payment.description", "Descripci\u00F3n"}, {"payment.amount", "Monto"},
            {"payment.maxamount", "Monto de la forma de pago no permitido."},
            {"payment.minamount", "Monto de la forma de pago no permitido."},
            {"payment.payrequired", "Debe seleccionar un pago."}, {"payment.teleMarketing", "No presencial"},
            {"payment.pagingfdp", "Más FDP"},

            /* Etiquetas Modulo Reimpresión */
            {"reprint.reprint", "Reimpresión"}, {"reprint.doreprint", "Reimprimir"},
            {"reprint.voucherquery", "Voucher"}, {"reprint.closurevpos", "Cierre Punto Ágil"},
            {"reprint.vouchernum", "Número de secuencia"}, {"reprint.reverse", "Reversar"},
            {"reprint.saledoc", "Documento soporte de la venta"},
            {"reprint.cancellationdoc", "Documento ajuste de la venta"},
            {"reprint.chargetoaccount", "Cargo a cuenta"}, {"reprint.corporatecredit", "Crédito Corporativo"},
            {"reprint.zreport", "Reporte Z"}, {"reprint.partialDelivery", "Entrega parcial"},
            {"reprint.depositOrder", "Abono de pedido"},

            /* Etiquetas de impresion */
            {"print.original", "ORIGINAL"}, {"print.copy", "COPIA"}, {"print.cashierDoc", "ORIGINAL CAJERO"},
            {"print.clientDoc", "COPIA CLIENTE"}, {"print.principal", "CAJA PRINCIPAL"},
            {"print.cancellation", "*** ANULACION ***"},
            /* Etiquetas Datos Adicionales */

            {"paymentmeth.bank", "Seleccione el banco"}, {"paymentmeth.accounttype", "Seleccione Tipo de cuenta"},
            {"paymentmeth.cvv", "Ingrese Código de seguridad"},
            {"paymentmeth.cardnumber", "Ingrese los dígitos de la tarjeta (4-5 dígitos)"},
            {"paymentmeth.accountnumber", "Ingrese número de cuenta"},
            {"paymentmeth.phonenumber", "Número de teléfono (7 - 8 dígitos)"},
            {"paymentmeth.numdoc", "Número de documento"},
            {"paymentmeth.numcomform", "Número de Conformación (Max 7 dígitos)"},
            {"paymentmeth.vposclosure", "Cierre"}, {"paymentmeth.vpospreclosure", "Pre-Cierre"},
            {"paymentmeth.closureerror", "Fallo en el Cierre de Punto Ágil"},
            {"paymentmeth.preclosureerror", "Fallo en el Pre-Cierre de Punto Ágil"},
            {"paymentmeth.closuremsg", "Se emitirá un cierre a la fecha "},
            {"paymentmeth.preclosuremsg", "Se emitirá un pre-cierre a la fecha "},
            {"paymentmeth.operations", "Operaciones de Punto Ágil"},
            {"paymentmeth.vposuse", "¿Desea usar el Punto Ágil?"},
            {"paymentmeth.approved", "Transacción Aprobada"}, {"paymentmeth.rejected", "Transacción Rechazada"},
            {"paymentmeth.selectpayment", "Seleccione la forma de pago"},

            {"epayment.novpos", "FDP Electronica - Punto de Respaldo"},
            /**
             * Etiquetas para los tipos de documentos
             */
            {"documenttype.title", "Tipos de Documento"}, {"documenttype.customerserving", "Cliente retira"},
            {"documenttype.dispatchnote", "Nota de entrega"},

            /* Etiquetas del Modulo Cliente */
            // { "customer.contrib", "Jurídico" },
            // { "customer.nocontrib", "Natural" },
            {"customer.contrib", "Contribuyente"}, {"customer.nocontrib", "<html>Consumidor<br/>Final</html>"},
            {"customer.nit", "Nit"}, {"customer.rif", "NRC"}, /* Rif en SV */
            {"customer.giro", "Giro"},

            {"customer.tlf", "Teléfono"}, {"customer.email", "Correo electrónico"},
            {"customer.retencion", "Agente de retención"},

            {"customer.id.input", "Ingrese N° Identidad"}, {"customer.id", "N° Doc Identidad"},
            {"customer.diplomatico", "Diplomático"}, {"customer.pasaporte", "Pasaporte"},
            {"customer.retentionagent", "Es agente de retención"}, {"customer.exonerado", "Es exonerado"},

            /**
             * Modulo de auditoria
             */
            {"audit.name", "Registro de auditoría"}, {"audit.module", "Módulo"}, {"audit.processid", "ID Proceso"},
            {"audit.funtion", "Función"}, {"audit.postype", "Tipo de caja"}, {"audit.fingerid", "IdHuella"},
            {"audit.password", "Clave"}, {"audit.deletedrecords", "Registros Depurados"},
            {"audit.idauth", "Id Autorizante"}, {"audit.idfdp", "Id Forma de Pago"},
            {"audit.idtrans", "Id Transacción"}, {"audit.amount", "Monto"},

            /**
             * Etiquetas del modulo Acreencias
             */
            {"credits.deposit", "Depósito de Acreencias"}, {"credits.requestProcessId", "Número de Proceso:"},
            {"credits.refund", "Reintegrar"}, {"credits.refundTitle", "Reintegro"},
            {"credits.credit", "Acreditar"}, {"credits.cancellation", "Anular"},
            {"credits.cancellationTitle", "Anulación"}, {"credits.requestProcessId", "Número de Proceso:"},
            {"credits.paswordTitle", "Clave de Acreencia"},
            {"credits.newpaswordTitle", "Creación de clave acreencia"},
            {"credits.otherAutorized", "Tercero Autorizado"}, {"credits.saleBetweenStore", "Venta entre tiendas"},
            {"credits.pass", "Clave (4 dígitos)"}, {"credits.repeatpass", "Confirme su clave (4 dígitos)"},

            {"refund.paymentsToRefund", "Acreditación"},

            {"check.number", "Nro Cheque (hasta 20 digitos)"},
            {"check.accountnumber", "Nro Cuenta (hasta 28 digitos)"},
            {"check.confirm", "Nro Confirmación (4 digitos)"},

            /**
             * Etiquetas del modulo de Credito Epa
             */
            {"creditsepa.creditsepa", "Crédito EPA"}, {"creditsepa.pay", "Pago de tarjeta"},
            {"creditsepa.payCorporate", "Pago crédito Corporativo"},
            {"creditsepa.cancellation", "<html> Anulación de último <br/>    pago de tarjeta</html>"},
            {"creditsepa.cancellationLittle",
                    "<html><font size=5> Anulación de último <br/>    pago de tarjeta</font></html>"},
            {"creditsepa.cancellationCorporate", "Anulación crédito corporativo"},
            {"creditsepa.cardNumber", "N° tarjeta"},
            {"creditsepa.cardNumberCorporate", "Número de identificación"},
            {"creditsepa.voucherheader", "Pago crédito Epa"},
            {"creditsepa.voucherheadercorporate", "Pago crédito Corporativo"},
            {"creditsepa.selectCard", "Seleccione tarjeta"},
            /**
             * Etiquetas del modulo cierre de dia
             */
            {"closureday.closure", "Cierre de día en proceso"},
            {"closureday.printigZReport", "Emitiendo reporte Z"},
            {"closureday.closingvpos", "Cerrando punto ágil"}, {"closureday.closingsession", "Cerrando sesión"},
            {"closureday.cleaningdata", "Depurando data"},
            {"closureday.transactionsync", "Sincronizando transacciones"},
            {"closureday.clientsync", "Sincronizando clientes"}, {"closureday.finalsync", "Sincronizando"},

            /**
             * Etiquetas para titulos de ventanas
             */
            {"window.pay", "Seleccione la Forma de Pago"}, {"window.dispatchnote", "Datos de Entrega"},
            {"window.creditepa", "Crédito Epa"}, {"window.credit", "Acreencia"},

            /**
             * Etiquetas para los abstractdialog
             */
            {"messages.printerproblem",
                    "Problemas con la impresora" + System.getProperty("line.separator")
                            + "Verifique e intente de nuevo."},
            {"messages.validatecashier", "El cliente no debe ser igual al cajero actual"},
            {"messages.poscantstart", "La Caja Registradora no puede iniciar debido a los siguientes errores:"},
            {"messages.retry", "¿Desea reintentar?"},
            {"messages.nullclient", "Identificador no encontrado o con formato invalido"},

            /**
             * Etiqueta del modulo de Rebajas
             */
            {"article.code", "Código"}, {"rebate.totalrebate", "Rebaja total"},
            {"rebate.motive", "Motivo de la rebaja"}, {"rebate.priceplustax", "Precio + Impuesto"},
            {"rebate.quantity", "Cantidad"}, {"rebate.finalprice", "Precio final"}, {"rebate.reason", "Motivo: "},
            {"rebate.percent", "Porcentaje (%): "},

            /**
             * Pantalla Articulo
             */
            {"retail.article", "Artículo"}, {"retail.description", "Descripción"}, {"retail.quantity", "Cantidad"},
            {"retail.preciofinal", "Precio final"}, {"retail.subtotal", "Sub Total"},
            {"retail.discount", "Descuento"}, {"retail.entrega", "Entrega"},

            /**
             * Pantalla Principal (main)
             */
            {"main.activity", "Actividad"}, {"main.version", "Versión"}, {"main.sync", "Sincronización"},
            {"main.scanner", "Escaner"}, {"main.printer", "Impresora"}

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
