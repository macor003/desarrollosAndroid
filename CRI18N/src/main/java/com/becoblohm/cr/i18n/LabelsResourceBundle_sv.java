/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.i18n;

import java.util.ListResourceBundle;

/**
 * @author programador6
 * @version $Revision: 1.0 $
 */
public class LabelsResourceBundle_sv extends ListResourceBundle {

    /**
     * Field contents.
     */
    private static final Object[][] contents = {
            // LOCALIZE THIS
            {"global.ok", "Aceptar"}, {"global.cancel", "Cancelar"}, {"global.id", "Identificador"},
            {"global.trans", "Transaccion"}, {"global.name", "Nombre"}, {"global.pass", "Contraseña"},
            {"global.repeatpass", "Repita Contraseña"}, {"global.customer", "Cliente"},
            {"global.direccion", "Dirección"}, {"global.cashregister", "Caja"}, {"global.cashier", "Cajero"},
            {"global.reason", "Motivo"}, {"global.cancellation", "Anulacion"}, {"global.rebate", "Rebaja"},
            {"global.refound", "Devolucion"}, {"global.credits", "Acreencias"},
            // {"global.creditsepa","Credito Epa"},
            {"global.creditsepa", "Operaciones Crédito"}, {"global.ccf", "CCF"}, {"global.bill", "Factura"},
            {"global.number", "Numero"}, {"global.amount", "Monto"}, {"global.date", "Fecha"},
            {"global.status", "Estado"}, {"global.description", "Descripcion"}, {"global.active", "Activo"},
            {"global.pendingForCancellation", "Descripcion"}, {"global.yes", "Si"}, {"global.no", "No"},
            {"global.deposit", "Deposito"}, {"global.cancellationNumber", "Anula "}, {"global.type", "Tipo"},
            {"global.exit", "Salir"}, {"global.document", "N° Documento"}, {"global.user", "Usuario"},

            {"auth.title", "Autorización"}, {"auth.subtitle", "Modulo Autorizante"},
            {"auth.fingerprint", "Presente Huella"},

            {"auth.changepass", "Nueva Contraseña (5-9 digitos)"}, {"auth.changepassconfirm", "Confirmar"},
            {"auth.nomatch", "Confirmar"}, {"auth.passusedbefore", "Confirmar"},

            {"waitingsale.button", "Venta en Espera"}, {"doctype.button", "Tipo Documento"},
            {"queries.button", "Consultas"},

            {"action.ok", "Aceptar"}, {"action.cancel", "Cancelar"}, {"action.print", "Imprimir"},
            {"action.delete", "Eliminar"}, {"action.pay", "Pagar"}, {"action.delivery", "Condicion de Entrega"},
            {"action.cashier", "Cajero"}, {"action.auth", "Autorizante"},

            /* Etiquetas del modulo de cajero */
            {"cashier.tienda", "Tienda: "}, {"cashier.fecha", "Fecha: "}, {"cashier.datos", "Cajero: "},
            {"cashier.parcial", "Entrega parcial de Cajero"}, {"cashier.total", "Entrega total de Cajero"},
            {"cashier.totalmoney", "Total"}, {"cashier.totalcash", "Total Entrega"},
            {"cashier.currency", "Denominación"}, {"cashier.nominal", "Valor Nominal"},
            {"cashier.billsquantity", "Cantidad Billetes"}, {"cashier.totalbills", "Total Billetes"},
            {"cashier.entrega", "Entrega autorizada por"}, {"cashier.entregaoffline", "Entrega fuera de linea"},
            {"cashier.transexceeded", "Transaccion excedida: # "}, {"cashier.fund", "Fondo de Cajero: "},
            {"cashier.monto", "Monto"}, {"cashier.editar", "Editar"}, {"cashier.detalles", "Detalles"},
            {"cashier.maslineas", "Más líneas"}, {"cashier.totalbutton", "Entrega Total"},
            {"cashier.fund0", "El fondo del cajero debe ser mayor a 0"},
            {"cashier.deliverythird", "¿Entrega realizada por tercero?"},
            {"cashier.drawerproblem", "Problemas con la gaveta"},

            /*
             * Mensajes de impresora
             */
            {"printer.problem", "<html>Problemas con la impresora<br/>Verifique e intente de nuevo.</html>"},

            /*
             * Etiquetas para reporte X
             */
            {"report.x", "Reporte X"}, {"report.title", "Reporte de Venta Diarios Parciales"},
            {"report.type", "(Reporte X)"},

            /*
             * Etiquetas para pedidos especiales
             */

            {"orders.title", "Servicios"}, {"orders.depositTitle", "Abonos"},
            {"orders.depositnew", "Agregar Abono"}, {"orders.depositCancellation", "Anular Abono"},
            {"orders.amountToDeposit", "Monto a Abonar"},

            {"query.type", "Tipo de Consulta"}, {"query.attribute", "Atributo"},

            {"donation.title", "Aportes de Clientes"}, {"donation.addRound", "Adicional a Redondeo"},
            {"donation.round", "Redondeo"},

            /* Etiquetas del Modulo Retencion */
            {"retention.name", "Retención de impuesto"},
            {"retention.amountretention", "¿Cuanto dinero desea retener?"},
            {"retention.maxamount", "¡Monto de retención mayor al permitido!"},
            {"retention.minamount", "¡Monto de retención menor al permitido!"},
            {"retention.errortyperetention", "No se puede aplicar este tipo de retención"},
            {"retention.erroramountretention",
                    "No se puede aplicar retención de impuesto porque" + System.getProperty("line.separator")
                            + "la suma del monto de los artículos es menor a 100$"},
            {"retention.errorpayprocess",
                    "Todos los pagos han sido procesados para esta factura," + System.getProperty("line.separator")
                            + "por tanto no pueden aplicarse retenciones."},

            /* Etiquetas del Modulo Pagar */
            {"payment.verify", "Verifique Monto: "}, {"payment.client", "Nombre del cliente: "},
            {"payment.process", "Tipo de proceso: "}, {"payment.totalpay", "Total Pagado : "},
            {"payment.totalcost", "Monto a Pagar : "}, {"payment.diference", "Restan : "},
            {"payment.description", "Descripci\u00F3n"}, {"payment.amount", "Monto"},
            {"payment.maxamount", "Monto de la forma de pago no permitido."},
            {"payment.minamount", "Monto de la forma de pago no permitido."},
            {"payment.payrequired", "Debe seleccionar un pago."},
            /* Etiquetas Modulo Reimpresión */
            {"reprint.reprint", "Reimpresion"}, {"reprint.doreprint", "Reimprimir"},
            {"reprint.voucherquery", "Consulta de Voucher"}, {"reprint.vouchernum", "Número de Secuencia"},
            {"reprint.reverse", "Reversar"}, {"reprint.saledoc", "Documento soporte de la venta"},
            {"reprint.chargetoaccount", "Cargo a cuenta"},
            {"reprint.cantreprint",
                    "La operación no se puede reimprimir porque no corresponde a la fecha o cajero actual"},
            {"reprint.zreport", "Reporte Z"},

            /* Etiquetas de impresion */
            {"print.cashierDoc", "Original Cajero"}, {"print.clientDoc", "Copia Cliente"},
            {"print.cancellation", "*** Anulacion ***"},
            /* Etiquetas Datos Adicionales */

            {"paymentmeth.bank", "Seleccione el Banco"}, {"paymentmeth.accounttype", "Seleccione Tipo de Cuenta"},
            {"paymentmeth.cvv", "Ingrese Código de Seguridad"},
            {"paymentmeth.cardnumber", "Ingrese los dígitos de la Tarjeta"},
            {"paymentmeth.accountnumber", "Ingrese Número de Cuenta"},
            {"paymentmeth.phonenumber", "Número de Teléfono"}, {"paymentmeth.numdoc", "Número de Documento"},
            {"paymentmeth.numcomform", "Número de Conformación"}, {"paymentmeth.vposclosure", "Cierre"},
            {"paymentmeth.vpospreclosure", "Pre-Cierre"},
            {"paymentmeth.closureerror", "Fallo en el Cierre de Punto Ágil"},
            {"paymentmeth.preclosureerror", "Fallo en el Pre-Cierre de Punto Ágil"},
            {"paymentmeth.closuremsg", "Se emitirá un cierre a la fecha "},
            {"paymentmeth.preclosuremsg", "Se emitirá un pre-cierre a la fecha "},
            {"paymentmeth.operations", "Operaciones de Punto Ágil"},
            {"paymentmeth.vposuse", "¿Desea usar el Punto Ágil?"},
            {"paymentmeth.approved", "Transacción Aprobada"}, {"paymentmeth.rejected", "Transacción Rechazada"},

            /**
             * Etiquetas para los tipos de documentos
             */
            {"documenttype.customerserving", "Cliente retira"}, {"documenttype.dispatchnote", "Nota de despacho"},

            /* Etiquetas del Modulo Cliente */
            {"customer.contrib", "Contribuyente"}, {"customer.nocontrib", "<html>No<br/>Contribuyente</html>"},
            {"customer.nit", "Nit"}, {"customer.rif", "NRC"}, /* Rif en SV */
            {"customer.giro", "Giro"},

            {"customer.tlf", "Telefono"}, {"customer.email", "Correo Electrónico"},
            {"customer.retencion", "Agente de Retención"},

            {"customer.id", "DUI o NIT"}, {"customer.diplomatico", "Diplomatico"},
            {"customer.pasaporte", "Pasaporte"}, {"customer.retentionagent", "Es Agente de Retención"},

            /**
             * Modulo de auditoria
             */
            {"audit.name", "Registro de auditoria"}, {"audit.module", "Modulo"}, {"audit.funtion", "Funcion"},
            {"audit.postype", "Tipo de caja"}, {"audit.fingerid", "IdHuella"}, {"audit.password", "Clave"},
            {"audit.deletedrecords", "Registros Depurados"}, {"audit.idauth", "Id Autorizante"},
            {"audit.idfdp", "Id Forma de Pago"}, {"audit.idtrans", "Id Transaccion"}, {"audit.amount", "Monto"},

            /**
             * Etiquetas del modulo Acreencias
             */
            {"credits.deposit", "Deposito de Acreencias"}, {"credits.requestProcessId", "Número de Proceso:"},
            {"credits.refund", "Devolver"}, {"credits.credit", "Acreditar"}, {"credits.cancellation", "Anular"},
            {"credits.requestProcessId", "Número de Proceso:"},

            /**
             * Etiquetas del modulo de Credito Epa
             */
            {"creditsepa.pay", "Pago de Tarjeta"}, {"creditsepa.payCorporate", "Pago Crédito Corporativo"},
            {"creditsepa.cancellation", "Anulación último pago tarjeta"},
            {"creditsepa.cancellationCorporate", "Anulación último pago Crédito Corporativo"},
            {"creditsepa.cardNumber", "N° tarjeta"},
            {"creditsepa.cardNumberCorporate", "Numero de identificación"},
            {"creditsepa.voucherheader", "Pago Credito Epa"},
            {"creditsepa.voucherheadercorporate", "Pago Credito Corporativo"},
            /**
             * Etiquetas del modulo cierre de dia
             */
            {"closureday.closure", "Cierre de día en proceso"},
            {"closureday.printigZReport", "Emitiendo reporte Z"},
            {"closureday.closingvpos", "Cerrando punto ágil"}, {"closureday.closingsession", "Cerrando sesión"},
            {"closureday.cleaningdata", "Depurando data"},

            /*
             * Etiquetas para titulos de ventanas
             */
            {"window.pay", "Pagar"}, {"window.creditepa", "Crédito Epa"}, {"window.credit", "Acreencia"},

            /*
             * Etiquetas para los abstractdialog
             */
            {"messages.printerproblem",
                    "Problemas con la impresora" + System.getProperty("line.separator")
                            + "Verifique e intente de nuevo."},
            {"messages.validatecashier", "El cliente no debe ser igual al cajero actual"},

            /**
             * Etiqueta del modulo de Rebajas
             */
            {"rebate.totalrebate", "Rebaja Total"},
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
