/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.conf;

import com.epa.mvc.core.AbstractEvent;

public class Events {

    /**
     * Field EVENT_HOME.
     */
    public static final AbstractEvent ACTION_EVENT_HOME = new AbstractEvent(AbstractEvent.class, 0, "Home");

    /**
     * Field EVENT_HOME. (value is 0)
     */
    public static final int EVENT_HOME = 0;

    /**
     * Field EVENT_LOGIN_OK.
     */
    public static final AbstractEvent ACTION_EVENT_LOGIN_OK = new AbstractEvent(AbstractEvent.class, 1,
            "Login Ok");

    /**
     * Field EVENT_LOGIN_OK. (value is 1)
     */
    public static final int EVENT_LOGIN_OK = 1;

    /**
     * Field ACTION_EVENT_CLIENT.
     */
    public static final AbstractEvent ACTION_EVENT_CLIENT = new AbstractEvent(AbstractEvent.class, 2,
            "Search Client");

    /**
     * Field EVENT_CLIENT. (value is 2)
     */
    public static final int EVENT_CLIENT = 2;

    /**
     * Field ACTION_EVENT_DOCUMENTTYPE.
     */
    public static final AbstractEvent ACTION_EVENT_DOCUMENTTYPE = new AbstractEvent(AbstractEvent.class, 4,
            "Set Document Type");

    /**
     * Field EVENT_DOCUMENTTYPE. (value is 4)
     */
    public static final int EVENT_DOCUMENTTYPE = 4;

    /**
     * Field ACTION_EVENT_DOCUMENTTYPE_SELECTED.
     */
    public static final AbstractEvent ACTION_EVENT_DOCUMENTTYPE_SELECTED = new AbstractEvent(AbstractEvent.class,
            5, "Document Type Selected");

    /**
     * Field EVENT_DOCUMENTTYPE_SELECTED. (value is 5)
     */
    public static final int EVENT_DOCUMENTTYPE_SELECTED = 5;

    /**
     * Field ACTION_EVENT_SHOWMENU.
     */
    public static final AbstractEvent ACTION_EVENT_SHOWMENU = new AbstractEvent(AbstractEvent.class, 6,
            "Self Checkout Menu");

    /**
     * Field EVENT_SHOWMENU. (value is 6)
     */
    public static final int EVENT_SHOWMENU = 6;

    /**
     * Field ACTION_EVENT_ARTICLE_ADDED.
     */
    public static final AbstractEvent ACTION_EVENT_ARTICLE_ADDED = new AbstractEvent(AbstractEvent.class, 7,
            "Article Added");

    /**
     * Field EVENT_ARTICLE_ADDED. (value is 7)
     */
    public static final int EVENT_ARTICLE_ADDED = 7;

    /**
     * Field ACTION_EVENT_WAITING_SALE.
     */
    public static final AbstractEvent ACTION_EVENT_WAITING_SALE = new AbstractEvent(AbstractEvent.class, 8,
            "Venta en Espera");

    /**
     * Field EVENT_WAITING_SALE. (value is 8)
     */
    public static final int EVENT_WAITING_SALE = 8;

    /**
     * Field ACTION_EVENT_CANCELLATION.
     */
    public static final AbstractEvent ACTION_EVENT_CANCELLATION = new AbstractEvent(AbstractEvent.class, 9,
            "Anulacion");

    /**
     * Field EVENT_CANCELLATION. (value is 9)
     */
    public static final int EVENT_CANCELLATION = 9;

    /**
     * Field ACTION_EVENT_WAITING_SALE_RECOVER.
     */
    public static final AbstractEvent ACTION_EVENT_WAITING_SALE_RECOVER = new AbstractEvent(AbstractEvent.class,
            10, "Venta en Espera Recuperada");

    /**
     * Field EVENT_WAITING_SALE_RECOVER. (value is 10)
     */
    public static final int EVENT_WAITING_SALE_RECOVER = 10;

    /**
     * Field ACTION_EVENT_PAY.
     */
    public static final AbstractEvent ACTION_EVENT_PAY = new AbstractEvent(AbstractEvent.class, 11, "Pay Called");

    /**
     * Field EVENT_PAY. (value is 11)
     */
    public static final int EVENT_PAY = 11;

    /**
     * Field ACTION_EVENT_ARTICLE_QUANTITY_UPDATED.
     */
    public static final AbstractEvent ACTION_EVENT_ARTICLE_QUANTITY_UPDATED = new AbstractEvent(
            AbstractEvent.class, 12, "Article Quantity");

    /**
     * Field EVENT_ARTICLE_QUANTITY_UPDATED. (value is 12)
     */
    public static final int EVENT_ARTICLE_QUANTITY_UPDATED = 12;

    /**
     * Field ACTION_EVENT_ARTICLE_REMOVED.
     */
    public static final AbstractEvent ACTION_EVENT_ARTICLE_REMOVED = new AbstractEvent(AbstractEvent.class, 13,
            "Article removed");

    /**
     * Field EVENT_ARTICLE_REMOVED. (value is 13)
     */
    public static final int EVENT_ARTICLE_REMOVED = 13;

    /**
     * Field ACTION_EVENT_REBATE.
     */
    public static final AbstractEvent ACTION_EVENT_REBATE = new AbstractEvent(AbstractEvent.class, 14, "Rebate");

    /**
     * Field EVENT_REBATE. (value is 14)
     */
    public static final int EVENT_REBATE = 14;

    /**
     * Field ACTION_EVENT_CUSTOMER_DONATION.
     */
    public static final AbstractEvent ACTION_EVENT_CUSTOMER_DONATION = new AbstractEvent(AbstractEvent.class, 15,
            "Donation");

    /**
     * Field EVENT_DONATION. (value is 15)
     */
    public static final int EVENT_DONATION = 15;

    /**
     * Field ACTION_EVENT_DELIVERY_CONDITION.
     */
    public static final AbstractEvent ACTION_EVENT_DELIVERY_CONDITION = new AbstractEvent(AbstractEvent.class, 16,
            "Condición de Entrega");

    /**
     * Field EVENT_DELIVERY_CONDITION. (value is 16)
     */
    public static final int EVENT_DELIVERY_CONDITION = 16;

    /**
     * Field ACTION_EVENT_PAY_DONE.
     */
    public static final AbstractEvent ACTION_EVENT_PAY_DONE = new AbstractEvent(AbstractEvent.class, 17,
            "Pay done");

    /**
     * Field EVENT_PAY_DONE. (value is 17)
     */
    public static final int EVENT_PAY_DONE = 17;

    /**
     * Field ACTION_EVENT_PAY_FAIL.
     */
    public static final AbstractEvent ACTION_EVENT_PAY_FAIL = new AbstractEvent(AbstractEvent.class, 18,
            "Pay fail");

    /**
     * Field EVENT_PAY_FAIL. (value is 18)
     */
    public static final int EVENT_PAY_FAIL = 18;

    /**
     * Field ACTION_EVENT_REVERSE_DONE.
     */
    public static final AbstractEvent ACTION_EVENT_REVERSE_DONE = new AbstractEvent(AbstractEvent.class, 19,
            "Reverse done");

    /**
     * Field EVENT_REVERSE_DONE. (value is 19)
     */
    public static final int EVENT_REVERSE_DONE = 19;

    /**
     * Field ACTION_EVENT_REVERSE_FAIL.
     */
    public static final AbstractEvent ACTION_EVENT_REVERSE_FAIL = new AbstractEvent(AbstractEvent.class, 20,
            "Reverse fail");

    /**
     * Field EVENT_REVERSE_FAIL. (value is 20)
     */
    public static final int EVENT_REVERSE_FAIL = 20;

    /**
     * Field ACTION_EVENT_PAY_WINDOW.
     */
    public static final AbstractEvent ACTION_EVENT_PAY_WINDOW = new AbstractEvent(AbstractEvent.class, 21,
            "Pay windows called");

    /**
     * Field EVENT_PAY_WINDOW. (value is 21)
     */
    public static final int EVENT_PAY_WINDOW = 21;

    /**
     * Field ACTION_EVENT_PAY_READY.
     */
    public static final AbstractEvent ACTION_EVENT_PAY_READY = new AbstractEvent(AbstractEvent.class, 22,
            "Pago listo");

    /**
     * Field EVENT_PAY_READY. (value is 22)
     */
    public static final int EVENT_PAY_READY = 22;

    /**
     * Field ACTION_EVENT_SELFCHECKOUT_CLIENT_FOUND.
     */
    public static final AbstractEvent ACTION_EVENT_SELFCHECKOUT_CLIENT_FOUND = new AbstractEvent(
            AbstractEvent.class, 23, " SelfCheckout Client Found");

    /**
     * Field EVENT_SELFCHECKOUT_CLIENT_FOUND. (value is 23)
     */
    public static final int EVENT_SELFCHECKOUT_CLIENT_FOUND = 23;

    /**
     * Field ACTION_EVENT_FOCUS_ARTICLE.
     */
    public static final AbstractEvent ACTION_EVENT_FOCUS_ARTICLE = new AbstractEvent(AbstractEvent.class, 24,
            "Buscar Articulo");

    /**
     * Field EVENT_SEARCH_ARTICLE. (value is 24)
     */
    public static final int EVENT_SEARCH_ARTICLE = 24;

    /**
     * Field ACTION_EVENT_INIT_SALE.
     */
    public static final AbstractEvent ACTION_EVENT_INIT_SALE = new AbstractEvent(AbstractEvent.class, 25,
            "Inicia venta");

    /**
     * Field EVENT_INIT_SALE. (value is 25)
     */
    public static final int EVENT_INIT_SALE = 25;

    /**
     * Field ACTION_EVENT_CASHIER.
     */
    public static final AbstractEvent ACTION_EVENT_CASHIER = new AbstractEvent(AbstractEvent.class, 26,
            "Entrega parcial de cajero");

    /**
     * Field EVENT_CASHIER. (value is 26)
     */
    public static final int EVENT_CASHIER = 26;

    /**
     * Field ACTION_EVENT_DRAWER.
     */
    public static final AbstractEvent ACTION_EVENT_DRAWER = new AbstractEvent(AbstractEvent.class, 27,
            "Abrir Gaveta");

    /**
     * Field EVENT_DRAWER. (value is 27)
     */
    public static final int EVENT_DRAWER = 27;

    /**
     * Field ACTION_EVENT_REFUND.
     */
    public static final int EVENT_REFUND = 28;

    public static final String EVENT_REFUND_COMMAND = "Refund";

    public static final AbstractEvent ACTION_EVENT_REFUND = new AbstractEvent(AbstractEvent.class, EVENT_REFUND,
            EVENT_REFUND_COMMAND);

    /**
     * Field ACTION_EVENT_REFUND_RECOVER.
     */
    public static final AbstractEvent ACTION_EVENT_REFUND_RECOVER = new AbstractEvent(AbstractEvent.class, 29,
            "Refund Found");

    /**
     * Field EVENT_REFUND_RECOVER. (value is 29)
     */
    public static final int EVENT_REFUND_RECOVER = 29;

    /**
     * Field ACTION_EVENT_CANCELLATION_RECOVER.
     */
    public static final AbstractEvent ACTION_EVENT_CANCELLATION_RECOVER = new AbstractEvent(AbstractEvent.class,
            30, "cargar Anulacion");

    /**
     * Field EVENT_CANCELLATION_RECOVER. (value is 30)
     */
    public static final int EVENT_CANCELLATION_RECOVER = 30;

    /**
     * Field ACTION_EVENT_VPOSOPERATIONS_CALL.
     */
    public static final AbstractEvent ACTION_EVENT_VPOSOPERATIONS_CALL = new AbstractEvent(AbstractEvent.class, 31,
            "Inicia Operaciones de Punto Agil");

    /**
     * Field EVENT_VPOSOPERATIONS_CALL. (value is 31)
     */
    public static final int EVENT_VPOSOPERATIONS_CALL = 31;

    /**
     * Field ACTION_EVENT_ARTICLE_SELECTED.
     */
    public static final AbstractEvent ACTION_EVENT_ARTICLE_SELECTED = new AbstractEvent(AbstractEvent.class, 32,
            "Article Selected");

    /**
     * Field EVENT_ARTICLE_SELECTED. (value is 32)
     */
    public static final int EVENT_ARTICLE_SELECTED = 32;

    /**
     * Field ACTION_EVENT_VPOS_CALL.
     */
    public static final AbstractEvent ACTION_EVENT_VPOS_CALL = new AbstractEvent(AbstractEvent.class, 33,
            "Punto Agil");

    /**
     * Field EVENT_VPOS_CALL. (value is 33)
     */
    public static final int EVENT_VPOS_CALL = 33;

    /**
     * Field ACTION_EVENT_CANCELLATION_PRINT.
     */
    public static final int EVENT_CANCELLATION_PRINT = 34;

    public static final String EVENT_CANCELLATION_PRINT_COMMAND = "Imprimir Devolucion";

    public static final AbstractEvent ACTION_EVENT_CANCELLATION_PRINT = new AbstractEvent(AbstractEvent.class,
            EVENT_CANCELLATION_PRINT, EVENT_CANCELLATION_PRINT_COMMAND);

    /**
     * Field ACTION_EVENT_REFUND_FINISH.
     */
    public static final AbstractEvent ACTION_EVENT_REFUND_FINISH = new AbstractEvent(AbstractEvent.class, 35,
            "Finish Refund");

    /**
     * Field EVENT_REFUND_FINISH. (value is 35)
     */
    public static final int EVENT_REFUND_FINISH = 35;

    /**
     * Field ACTION_EVENT_QUERY.
     */
    public static final AbstractEvent ACTION_EVENT_QUERY = new AbstractEvent(AbstractEvent.class, 36,
            "Event Query");

    /**
     * Field EVENT_QUERY. (value is 36)
     */
    public static final int EVENT_QUERY = 36;

    /**
     * Field ACTION_EVENT_QUERY_ARTICLE_ADDED.
     */
    public static final AbstractEvent ACTION_EVENT_QUERY_ARTICLE_ADDED = new AbstractEvent(AbstractEvent.class, 37,
            "Event Query Added");

    /**
     * Field EVENT_QUERY_ARTICLE_ADDED. (value is 37)
     */
    public static final int EVENT_QUERY_ARTICLE_ADDED = 37;

    /**
     * Field ACTION_EVENT_TAXDEDUCTION.
     */
    public static final AbstractEvent ACTION_EVENT_TAXDEDUCTION = new AbstractEvent(AbstractEvent.class, 215,
            "TaxDeduction Called");

    /**
     * Field EVENT_TAXDEDUCTION. (value is 215)
     */
    public static final int EVENT_TAXDEDUCTION = 215;

    public static final AbstractEvent ACTION_EVENT_PROCESS_TAXDEDUCTION = new AbstractEvent(AbstractEvent.class,
            216, "Process TaxDeduction");

    /**
     * Field EVENT_PROCESS_TAXDEDUCTION. (value is 216)
     */
    public static final int EVENT_PROCESS_TAXDEDUCTION = 216;

    /**
     * Field ACTION_EVENT_TAXDEDUCTION_READY.
     */
    public static final AbstractEvent ACTION_EVENT_TAXDEDUCTION_READY = new AbstractEvent(AbstractEvent.class, 217,
            "TaxDeduction Finished");

    /**
     * Field EVENT_TAXDEDUCTION_READY. (value is 217)
     */
    public static final int EVENT_TAXDEDUCTION_READY = 217;

    /**
     * Field ACTION_EVENT_RETENTION.
     */
    public static final AbstractEvent ACTION_EVENT_RETENTION = new AbstractEvent(AbstractEvent.class, 38,
            "Retention Called");

    /**
     * Field EVENT_RETENTION. (value is 38)
     */
    public static final int EVENT_RETENTION = 38;

    /**
     * Field ACTION_EVENT_RETENTION_DEL.
     */
    public static final AbstractEvent ACTION_EVENT_RETENTION_DEL = new AbstractEvent(AbstractEvent.class, 40,
            "Retention Deleted");

    /**
     * Field EVENT_RETENTION_DEL. (value is 40)
     */
    public static final int EVENT_RETENTION_DEL = 40;

    /**
     * Field ACTION_EVENT_RETENTION_READY.
     */
    public static final AbstractEvent ACTION_EVENT_RETENTION_READY = new AbstractEvent(AbstractEvent.class, 41,
            "Retention Finished");

    /**
     * Field EVENT_RETENTION_READY. (value is 41)
     */
    public static final int EVENT_RETENTION_READY = 41;

    /**
     * Field ACTION_EVENT_REPRINT_CALL.
     */
    public static final int EVENT_REPRINT_CALL = 42;

    public static final String EVENT_REPRINT_CALL_COMMAND = "Reprint Called";

    public static final AbstractEvent ACTION_EVENT_REPRINT_CALL = new AbstractEvent(AbstractEvent.class,
            EVENT_REPRINT_CALL, EVENT_REPRINT_CALL_COMMAND);

    /**
     * Field ACTION_EVENT_REBIND.
     */
    public static final AbstractEvent ACTION_EVENT_REBIND = new AbstractEvent(AbstractEvent.class, 43,
            "Rebind Retail");

    /**
     * Field EVENT_REBIND. (value is 43)
     */
    public static final int EVENT_REBIND = 43;

    /**
     * Field ACTION_EVENT_QUERY_CLIENT_ADDED.
     */
    public static final AbstractEvent ACTION_EVENT_QUERY_CLIENT_ADDED = new AbstractEvent(AbstractEvent.class, 44,
            "Event Query Client Added");

    /**
     * Field EVENT_QUERY_CLIENT_ADDED. (value is 44)
     */
    public static final int EVENT_QUERY_CLIENT_ADDED = 44;

    /**
     * Field ACTION_EVENT_REFUND_PRINT.
     */
    public static final AbstractEvent ACTION_EVENT_REFUND_PRINT = new AbstractEvent(AbstractEvent.class, 45,
            "Event Print Refund Sale");

    /**
     * Field EVENT_REFUND_PRINT. (value is 45)
     */
    public static final int EVENT_REFUND_PRINT = 45;

    /**
     * Field ACTION_EVENT_REFUND_CHECK_MOTIVE.
     */
    public static final AbstractEvent ACTION_EVENT_REFUND_CHECK_MOTIVE = new AbstractEvent(AbstractEvent.class, 46,
            "Event Refund Check Motive");

    /**
     * Field EVENT_REFUND_CHECK_MOTIVE. (value is 46)
     */
    public static final int EVENT_REFUND_CHECK_MOTIVE = 46;

    /**
     * Field ACTION_EVENT_PAYMENT_BUTTON.
     */
    public static final AbstractEvent ACTION_EVENT_PAYMENT_BUTTON = new AbstractEvent(AbstractEvent.class, 48,
            "Payment Buttom");

    /**
     * Field EVENT_PAYMENT_BUTTON. (value is 48)
     */
    public static final int EVENT_PAYMENT_BUTTON = 48;

    /**
     * Field ACTION_EVENT_CREDITS_DEPOSIT.
     */
    public static final AbstractEvent ACTION_EVENT_CREDITS_DEPOSIT = new AbstractEvent(AbstractEvent.class, 50,
            "Event Credits Deposit");

    /**
     * Field EVENT_CREDITS_DEPOSIT. (value is 50)
     */
    public static final int EVENT_CREDITS_DEPOSIT = 50;

    /**
     * Field ACTION_EVENT_ASK_FOR_NEWSALE.
     */
    public static final AbstractEvent ACTION_EVENT_ASK_FOR_NEWSALE = new AbstractEvent(AbstractEvent.class, 51,
            "Event ask For New Sale");

    /**
     * Field EVENT_ASK_FOR_NEWSALE. (value is 51)
     */
    public static final int EVENT_ASK_FOR_NEWSALE = 51;

    /**
     * Field ACTION_EVENT_NEWSALE_CHANGE_DELIVERY_CONDITION.
     */
    public static final AbstractEvent ACTION_EVENT_NEWSALE_CHANGE_DELIVERY_CONDITION = new AbstractEvent(
            AbstractEvent.class, 52, "Event new Sale with Change Delivery Condition");

    /**
     * Field EVENT_NEWSALE_CHANGE_DELIVERY_CONDITION. (value is 52)
     */
    public static final int EVENT_NEWSALE_CHANGE_DELIVERY_CONDITION = 52;

    /**
     * Field ACTION_EVENT_NEWSALE_CHANGE_CLIENT.
     */
    public static final AbstractEvent ACTION_EVENT_NEWSALE_CHANGE_CLIENT = new AbstractEvent(AbstractEvent.class,
            53, "Event new Sale with Change Delivery Condition");

    /**
     * Field EVENT_NEWSALE_CHANGE_CLIENT. (value is 53)
     */
    public static final int EVENT_NEWSALE_CHANGE_CLIENT = 53;

    /**
     * Field ACTION_EVENT_FINISH_CALL.
     */
    public static final AbstractEvent ACTION_EVENT_FINISH_CALL = new AbstractEvent(AbstractEvent.class, 318,
            "Call Finish Process");

    /**
     * Field EVENT_FINISH_CALL. (value is 318)
     */
    public static final int EVENT_FINISH_CALL = 318;

    /**
     * Field ACTION_EVENT_REFUND_CANCEL_PROCESS.
     */
    public static final AbstractEvent ACTION_EVENT_REFUND_CANCEL_PROCESS = new AbstractEvent(AbstractEvent.class,
            54, "Event Refund Cancell Process");

    /**
     * Field EVENT_REFUND_CANCEL_PROCESS. (value is 54)
     */
    public static final int EVENT_REFUND_CANCEL_PROCESS = 54;

    /**
     * Field ACTION_EVENT_CHARGE_FOR_SERVICE.
     */
    public static final int EVENT_CHARGE_FOR_SERVICE = 55;

    public static final String EVENT_CHARGE_FOR_SERVICE_COMMAND = "Event Charge for service";

    public static final AbstractEvent ACTION_EVENT_CHARGE_FOR_SERVICE = new AbstractEvent(AbstractEvent.class, 55,
            EVENT_CHARGE_FOR_SERVICE_COMMAND);

    /**
     * Field ACTION_EVENT_UPDATE_REFUND_COMPLETED.
     */
    public static final AbstractEvent ACTION_EVENT_UPDATE_REFUND_COMPLETED = new AbstractEvent(AbstractEvent.class,
            56, "Event Update Refund Completed");

    /**
     * Field EVENT_UPDATE_REFUND_COMPLETED. (value is 56)
     */
    public static final int EVENT_UPDATE_REFUND_COMPLETED = 56;

    /**
     * Field ACTION_EVENT_CLOSURE_DAY.
     */
    public static final int EVENT_CLOSURE_DAY = 57;

    public static final String EVENT_CLOSURE_DAY_COMMAND = "Event Closure Day";

    public static final AbstractEvent ACTION_EVENT_CLOSURE_DAY = new AbstractEvent(AbstractEvent.class,
            EVENT_CLOSURE_DAY, EVENT_CLOSURE_DAY_COMMAND);

    /**
     * Field ACTION_EVENT_UPDATE_SESSION.
     */
    public static final AbstractEvent ACTION_EVENT_UPDATE_SESSION = new AbstractEvent(AbstractEvent.class, 58,
            "Event Update Session");

    /**
     * Field EVENT_UPDATE_SESSION. (value is 58)
     */
    public static final int EVENT_UPDATE_SESSION = 58;

    /**
     * Field ACTION_EVENT_TOTAL_CASHIER.
     */
    public static final AbstractEvent ACTION_EVENT_TOTAL_CASHIER = new AbstractEvent(AbstractEvent.class, 59,
            "Event Total Delivery");

    /**
     * Field EVENT_TOTAL_CASHIER. (value is 59)
     */
    public static final int EVENT_TOTAL_CASHIER = 59;

    /**
     * Field ACTION_EVENT_SELECTION_DELIVERY.
     */
    public static final AbstractEvent ACTION_EVENT_SELECTION_DELIVERY = new AbstractEvent(AbstractEvent.class, 60,
            "Event Selection Delivery");

    /**
     * Field EVENT_SELECTION_DELIVERY. (value is 60)
     */
    public static final int EVENT_SELECTION_DELIVERY = 60;

    /**
     * Field ACTION_EVENT_LOCKPOS.
     */
    public static final AbstractEvent ACTION_EVENT_LOCKPOS = new AbstractEvent(AbstractEvent.class, 61,
            "Event Lock Pos Called");

    /**
     * Field EVENT_LOCKPOS. (value is 61)
     */
    public static final int EVENT_LOCKPOS = 61;

    /**
     * Field ACTION_EVENT_REPRINT_CANCEL_PROCESS.
     */
    public static final AbstractEvent ACTION_EVENT_REPRINT_CANCEL_PROCESS = new AbstractEvent(AbstractEvent.class,
            62, "Event Cancel Reprint");

    /**
     * Field EVENT_REPRINT_CANCEL_PROCESS. (value is 62)
     */
    public static final int EVENT_REPRINT_CANCEL_PROCESS = 62;

    /**
     * Field ACTION_EVENT_CREDITS.
     */
    public static final AbstractEvent ACTION_EVENT_CREDITS = new AbstractEvent(AbstractEvent.class, 63,
            "Event Credits");

    /**
     * Field EVENT_CREDITS. (value is 63)
     */
    public static final int EVENT_CREDITS = 63;

    /**
     * Field ACTION_EVENT_CREDITS_CONSUME.
     */
    public static final AbstractEvent ACTION_EVENT_CREDITS_CONSUME = new AbstractEvent(AbstractEvent.class, 64,
            "Event Credits Consume");

    /**
     * Field EVENT_CREDITS_CONSUME. (value is 64)
     */
    public static final int EVENT_CREDITS_CONSUME = 64;

    /**
     * Field ACTION_EVENT_VOUCHER_QUERY.
     */
    public static final AbstractEvent ACTION_EVENT_VOUCHER_QUERY = new AbstractEvent(AbstractEvent.class, 65,
            "Event Voucher Query");

    /**
     * Field EVENT_VOUCHER_QUERY. (value is 65)
     */
    public static final int EVENT_VOUCHER_QUERY = 65;

    /**
     * Field ACTION_EVENT_REPRINT_SALE_DOC.
     */
    public static final AbstractEvent ACTION_EVENT_REPRINT_SALE_DOC = new AbstractEvent(AbstractEvent.class, 66,
            "Reprint Sale Doc");

    /**
     * Field EVENT_REPRINT_SALE_DOC. (value is 66)
     */
    public static final int EVENT_REPRINT_SALE_DOC = 66;

    /**
     * Field ACTION_EVENT_CREDITS_PAYMENTS.
     */
    public static final AbstractEvent ACTION_EVENT_CREDITS_PAYMENTS = new AbstractEvent(AbstractEvent.class, 67,
            "Event Credits Payments");

    /**
     * Field EVENT_CREDITS_PAYMENTS. (value is 67)
     */
    public static final int EVENT_CREDITS_PAYMENTS = 67;

    /**
     * Field ACTION_EVENT_CREDITS_SET_CUSTOMER.
     */
    public static final AbstractEvent ACTION_EVENT_CREDITS_SET_CUSTOMER = new AbstractEvent(AbstractEvent.class,
            68, "Event Credits Set Customer");

    /**
     * Field EVENT_CREDITS_SET_CUSTOMER. (value is 68)
     */
    public static final int EVENT_CREDITS_SET_CUSTOMER = 68;

    /**
     * Field ACTION_EVENT_VPOS_CLOSURE_CALL.
     */
    public static final int EVENT_VPOS_CLOSURE_CALL = 70;

    public static final String EVENT_VPOS_CLOSURE_CALL_COMMAND = "Call Closure VPos";

    public static final AbstractEvent ACTION_EVENT_VPOS_CLOSURE_CALL = new AbstractEvent(AbstractEvent.class,
            EVENT_VPOS_CLOSURE_CALL, EVENT_VPOS_CLOSURE_CALL_COMMAND);

    /**
     * Field ACTION_EVENT_REPRINT_SALE_DOC_CALL.
     */
    public static final AbstractEvent ACTION_EVENT_REPRINT_SALE_DOC_CALL = new AbstractEvent(AbstractEvent.class,
            72, "Reprint Sale Doc");

    /**
     * Field EVENT_REPRINT_SALE_DOC_CALL. (value is 72)
     */
    public static final int EVENT_REPRINT_SALE_DOC_CALL = 72;

    /**
     * Field ACTION_EVENT_CREDITS_CANCELLATION.
     */
    public static final AbstractEvent ACTION_EVENT_CREDITS_CANCELLATION = new AbstractEvent(AbstractEvent.class,
            69, "Event Credits Cancellation");

    /**
     * Field EVENT_CREDITS_CANCELLATION. (value is 69)
     */
    public static final int EVENT_CREDITS_CANCELLATION = 69;

    /**
     * Field ACTION_EVENT_SELFCHECKOUT_CHANGE_CARD.
     */
    public static final AbstractEvent ACTION_EVENT_SELFCHECKOUT_CHANGE_CARD = new AbstractEvent(
            AbstractEvent.class, 71, "Change Retail Self-Checkout Panel");

    /**
     * Field EVENT_SELFCHECKOUT_CHANGE_CARD. (value is 71)
     */
    public static final int EVENT_SELFCHECKOUT_CHANGE_CARD = 71;

    /**
     * Field ACTION_EVENT_CREDITS_PASSWORD_AUTH_BY_OTHER.
     */
    public static final int EVENT_CREDITS_PASSWORD_AUTH_BY_OTHER = 72;

    public static final String EVENT_CREDITS_PASSWORD_AUTH_BY_OTHER_COMMAND = "Credits Autorized Password by Other";

    public static final AbstractEvent ACTION_EVENT_CREDITS_PASSWORD_AUTH_BY_OTHER = new AbstractEvent(
            AbstractEvent.class, EVENT_CREDITS_PASSWORD_AUTH_BY_OTHER,
            EVENT_CREDITS_PASSWORD_AUTH_BY_OTHER_COMMAND);

    /**
     * Field ACTION_EVENT_REPRINT_CUSTOMER_SERVING.
     */
    public static final AbstractEvent ACTION_EVENT_REPRINT_CUSTOMER_SERVING = new AbstractEvent(
            AbstractEvent.class, 73, "Reprint Customer Serving");

    /**
     * Field EVENT_REPRINT_CUSTOMER_SERVING. (value is 73)
     */
    public static final int EVENT_REPRINT_CUSTOMER_SERVING = 73;

    /**
     * Field ACTION_EVENT_REPRINT_PARTIAL_DELIVERY.
     */
    public static final AbstractEvent ACTION_EVENT_REPRINT_PARTIAL_DELIVERY = new AbstractEvent(
            AbstractEvent.class, 74, "Reprint Partial Delivery");

    /**
     * Field EVENT_REPRINT_PARTIAL_DELIVERY. (value is 74)
     */
    public static final int EVENT_REPRINT_PARTIAL_DELIVERY = 74;

    /**
     * Field ACTION_EVENT_PROMOTION_PAYMENT.
     */
    public static final AbstractEvent ACTION_EVENT_PROMOTION_PAYMENT = new AbstractEvent(AbstractEvent.class, 75,
            "Promotion Payment ");

    /**
     * Field EVENT_PROMOTION_PAYMENT. (value is 75)
     */
    public static final int EVENT_PROMOTION_PAYMENT = 75;

    /**
     * Field ACTION_EVENT_CREDITSEPA.
     */
    public static final AbstractEvent ACTION_EVENT_CREDITSEPA = new AbstractEvent(AbstractEvent.class, 76,
            "Credits EPA");

    /**
     * Field EVENT_CREDITSEPA. (value is 76)
     */
    public static final int EVENT_CREDITSEPA = 76;

    /**
     * Field ACTION_EVENT_CREDITSEPA_PAYMENTS.
     */
    public static final AbstractEvent ACTION_EVENT_CREDITSEPA_PAYMENTS = new AbstractEvent(AbstractEvent.class, 77,
            "Credits EPA Payment");

    /**
     * Field EVENT_CREDITSEPA_PAYMENTS. (value is 77)
     */
    public static final int EVENT_CREDITSEPA_PAYMENTS = 77;

    /**
     * Field ACTION_EVENT_CREDITSEPA_FINISH.
     */
    public static final AbstractEvent ACTION_EVENT_CREDITSEPA_FINISH = new AbstractEvent(AbstractEvent.class, 78,
            "Credits EPA Payment Finish");

    /**
     * Field EVENT_CREDITSEPA_FINISH. (value is 78)
     */
    public static final int EVENT_CREDITSEPA_FINISH = 78;

    /**
     * Field ACTION_EVENT_UPDATE_SALE.
     */
    public static final AbstractEvent ACTION_EVENT_UPDATE_SALE = new AbstractEvent(AbstractEvent.class, 79,
            "Save new Sale");

    /**
     * Field EVENT_UPDATE_SALE. (value is 79)
     */
    public static final int EVENT_UPDATE_SALE = 79;

    /**
     * Field ACTION_EVENT_AUDITROLL.
     */
    public static final AbstractEvent ACTION_EVENT_AUDITROLL = new AbstractEvent(AbstractEvent.class, 80,
            "Audit paper roll");

    /**
     * Field EVENT_AUDITROLL. (value is 80)
     */
    public static final int EVENT_AUDITROLL = 80;

    /**
     * Field ACTION_EVENT_CREDITSEPA_CANCELLATION.
     */
    public static final AbstractEvent ACTION_EVENT_CREDITSEPA_CANCELLATION = new AbstractEvent(AbstractEvent.class,
            81, "Credits EPA Cancellation");

    /**
     * Field EVENT_CREDITSEPA_CANCELLATION. (value is 81)
     */
    public static final int EVENT_CREDITSEPA_CANCELLATION = 81;

    /**
     * Field ACTION_EVENT_AUDITROLL_AUTH.
     */
    public static final AbstractEvent ACTION_EVENT_AUDITROLL_AUTH = new AbstractEvent(AbstractEvent.class, 82,
            "Print Audit paper roll");

    /**
     * Field EVENT_AUDITROLL_AUTH. (value is 82)
     */
    public static final int EVENT_AUDITROLL_AUTH = 82;

    /**
     * Field ACTION_EVENT_PROMOTION_VALIDATOR.
     */
    public static final AbstractEvent ACTION_EVENT_PROMOTION_VALIDATOR = new AbstractEvent(AbstractEvent.class, 83,
            "Promotion Validator");

    /**
     * Field EVENT_PROMOTION_VALIDATOR. (value is 83)
     */
    public static final int EVENT_PROMOTION_VALIDATOR = 83;

    /**
     * Field ACTION_EVENT_ORDER_RECOVER.
     */
    public static final AbstractEvent ACTION_EVENT_ORDER_RECOVER = new AbstractEvent(AbstractEvent.class, 84,
            "Order found");

    /**
     * Field EVENT_ORDER_RECOVER. (value is 84)
     */
    public static final int EVENT_ORDER_RECOVER = 84;

    /**
     * Field ACTION_EVENT_SET_DELIVERY_CONDITION.
     */
    public static final AbstractEvent ACTION_EVENT_SET_DELIVERY_CONDITION = new AbstractEvent(AbstractEvent.class,
            90, "set delivery");

    /**
     * Field EVENT_SET_DELIVERY_CONDITION. (value is 90)
     */
    public static final int EVENT_SET_DELIVERY_CONDITION = 90;

    /**
     * Field ACTION_EVENT_REPRINT_DISPATCH_NOTE.
     */
    public static final AbstractEvent ACTION_EVENT_REPRINT_DISPATCH_NOTE = new AbstractEvent(AbstractEvent.class,
            91, "Reprint Dispatch Note");

    /**
     * Field EVENT_SET_REPRINT_DISPATCH_NOTE. (value is 91)
     */
    public static final int EVENT_SET_REPRINT_DISPATCH_NOTE = 91;

    /**
     * Field ACTION_EVENT_REPRINT_EPACREDITS.
     */
    public static final AbstractEvent ACTION_EVENT_REPRINT_EPACREDITS = new AbstractEvent(AbstractEvent.class, 92,
            "Reprint EPA Credits");

    /**
     * Field EVENT_REPRINT_EPACREDITS. (value is 92)
     */
    public static final int EVENT_REPRINT_EPACREDITS = 92;

    /**
     * Field ACTION_EVENT_DEPOSIT_SHOW_LIST.
     */
    public static final AbstractEvent ACTION_EVENT_DEPOSIT_SHOW_LIST = new AbstractEvent(AbstractEvent.class, 93,
            "Deposti Show List");

    /**
     * Field EVENT_DEPOSIT_SHOW_LIST. (value is 93)
     */
    public static final int EVENT_DEPOSIT_SHOW_LIST = 93;

    /**
     * Field ACTION_EVENT_DEPOSIT_READY.
     */
    public static final AbstractEvent ACTION_EVENT_DEPOSIT_READY = new AbstractEvent(AbstractEvent.class, 94,
            "Deposit Ready");

    /**
     * Field EVENT_DEPOSIT_READY. (value is 94)
     */
    public static final int EVENT_DEPOSIT_READY = 94;

    /**
     * Field ACTION_EVENT_REPRINT_CREDITS_CLIENT.
     */
    public static final AbstractEvent ACTION_EVENT_REPRINT_CREDITS_CLIENT = new AbstractEvent(AbstractEvent.class,
            95, "Reprint Credits Client");

    /**
     * Field EVENT_REPRINT_CREDITS_CLIENT. (value is 95)
     */
    public static final int EVENT_REPRINT_CREDITS_CLIENT = 95;

    /**
     * Field ACTION_EVENT_ORDER_REQUEST.
     */
    public static final AbstractEvent ACTION_EVENT_ORDER_REQUEST = new AbstractEvent(AbstractEvent.class, 96,
            "Orders Request");

    /**
     * Field EVENT_ORDER_REQUEST. (value is 96)
     */
    public static final int EVENT_ORDER_REQUEST = 96;

    /**
     * Field ACTION_EVENT_CUSTOMER_AUTH.
     */
    public static final int EVENT_CUSTOMER_AUTH = 97;

    public static final String EVENT_CUSTOMER_AUTH_COMMAND = "Finalizar transaccion Proveniende de devolucion";

    public static final AbstractEvent ACTION_EVENT_CUSTOMER_AUTH = new AbstractEvent(AbstractEvent.class,
            EVENT_CUSTOMER_AUTH, EVENT_CUSTOMER_AUTH_COMMAND);

    /**
     * Field ACTION_EVENT_PRINT_ORDER.
     */
    public static final AbstractEvent ACTION_EVENT_PRINT_ORDER = new AbstractEvent(AbstractEvent.class, 98,
            "Print Order");

    /**
     * Field EVENT_PRINT_ORDER. (value is 98)
     */
    public static final int EVENT_PRINT_ORDER = 98;

    /**
     * Field ACTION_EVENT_CANCELLATION_BUTTON.
     */
    public static final AbstractEvent ACTION_EVENT_CANCELLATION_BUTTON = new AbstractEvent(AbstractEvent.class, 99,
            "Cancellation Button");

    /**
     * Field EVENT_CANCELLATION_BUTTON. (value is 99)
     */
    public static final int EVENT_CANCELLATION_BUTTON = 99;

    /**
     * Field ACTION_EVENT_CANCELLATION_ORDER.
     */
    public static final int EVENT_CANCELLATION_ORDER = 100;

    public static final String EVENT_CANCELLATION_ORDER_COMMAND = "Cancellation Order";

    public static final AbstractEvent ACTION_EVENT_CANCELLATION_ORDER = new AbstractEvent(AbstractEvent.class,
            EVENT_CANCELLATION_ORDER, EVENT_CANCELLATION_ORDER_COMMAND);

    /**
     * Field ACTION_EVENT_CANCELLATION_DEPOSIT_ORDER.
     */
    public static final AbstractEvent ACTION_EVENT_CANCELLATION_DEPOSIT_ORDER = new AbstractEvent(
            AbstractEvent.class, 101, "Cancellation Deposit Order");

    /**
     * Field EVENT_CANCELLATION_DEPOSIT_ORDER. (value is 101)
     */
    public static final int EVENT_CANCELLATION_DEPOSIT_ORDER = 101;

    /**
     * Field ACTION_EVENT_CANCELLATION_DEPOSIT_ORDER_FROM_CANCELLATION_ORDER.
     */
    public static final AbstractEvent ACTION_EVENT_CANCELLATION_DEPOSIT_ORDER_FROM_CANCELLATION_ORDER = new AbstractEvent(
            AbstractEvent.class, 302, "Cancellation Deposit Order From Cancellation Order");

    /**
     * Field EVENT_CANCELLATION_DEPOSIT_ORDER_FROM_CANCELLATION_ORDER. (value is 302)
     */
    public static final int EVENT_CANCELLATION_DEPOSIT_ORDER_FROM_CANCELLATION_ORDER = 302;

    /**
     * Field ACTION_EVENT_UPDATE_ORDER_STATUS_REVERSED.
     */
    public static final AbstractEvent ACTION_EVENT_UPDATE_ORDER_STATUS_REVERSED = new AbstractEvent(
            AbstractEvent.class, 102, "Update Status Order Reversed");

    /**
     * Field EVENT_UPDATE_ORDER_STATUS_REVERSED. (value is 102)
     */
    public static final int EVENT_UPDATE_ORDER_STATUS_REVERSED = 102;

    /**
     * Field ACTION_EVENT_CREDITS_DEPOSIT_FROM_ORDER.
     */
    public static final AbstractEvent ACTION_EVENT_CREDITS_DEPOSIT_FROM_ORDER = new AbstractEvent(
            AbstractEvent.class, 103, "Credits Deposit From Order");

    /**
     * Field EVENT_CREDITS_DEPOSIT_FROM_ORDER. (value is 103)
     */
    public static final int EVENT_CREDITS_DEPOSIT_FROM_ORDER = 103;

    /**
     * Field ACTION_EVENT_CREDITS_REFUND_FROM_ORDER.
     */
    public static final AbstractEvent ACTION_EVENT_CREDITS_REFUND_FROM_ORDER = new AbstractEvent(
            AbstractEvent.class, 104, "Credits Refund From Order");

    /**
     * Field EVENT_CREDITS_REFUND_FROM_ORDER. (value is 104)
     */
    public static final int EVENT_CREDITS_REFUND_FROM_ORDER = 104;

    /**
     * Field ACTION_EVENT_CREDITSEPA_PAY.
     */
    public static final AbstractEvent ACTION_EVENT_CREDITSEPA_PAY = new AbstractEvent(AbstractEvent.class, 105,
            "Pay Credits Epa");

    /**
     * Field EVENT_CREDITSEPA_PAY. (value is 105)
     */
    public static final int EVENT_CREDITSEPA_PAY = 105;

    /**
     * Field ACTION_EVENT_CORPORATE_PAY.
     */
    public static final AbstractEvent ACTION_EVENT_CORPORATE_PAY = new AbstractEvent(AbstractEvent.class, 106,
            "Pay Corporate Epa");

    /**
     * Field EVENT_CORPORATE_PAY. (value is 106)
     */
    public static final int EVENT_CORPORATE_PAY = 106;

    /**
     * Field ACTION_EVENT_PROMOTION_AUTH.
     */
    public static final AbstractEvent ACTION_EVENT_PROMOTION_AUTH = new AbstractEvent(AbstractEvent.class, 107,
            "Promotion Auth");

    /**
     * Field EVENT_PROMOTION_AUTH. (value is 107)
     */
    public static final int EVENT_PROMOTION_AUTH = 107;

    /**
     * Field ACTION_EVENT_REQUEST_CHANGE_AUTHINFO.
     */
    public static final AbstractEvent ACTION_EVENT_REQUEST_CHANGE_AUTHINFO = new AbstractEvent(AbstractEvent.class,
            109, "Change Auth");

    /**
     * Field EVENT_REQUEST_CHANGE_AUTHINFO. (value is 109)
     */
    public static final int EVENT_REQUEST_CHANGE_AUTHINFO = 109;

    /**
     * Field ACTION_EVENT_CUSTOMER_AUTH_DIPLOMATIC.
     */
    public static final int EVENT_CUSTOMER_AUTH_DIPLOMATIC = 110;

    public static final String EVENT_CUSTOMER_AUTH_DIPLOMATIC_COMMAND = "CUSTOMER Auth";

    public static final AbstractEvent ACTION_EVENT_CUSTOMER_AUTH_DIPLOMATIC = new AbstractEvent(
            AbstractEvent.class, EVENT_CUSTOMER_AUTH_DIPLOMATIC, EVENT_CUSTOMER_AUTH_DIPLOMATIC_COMMAND);

    /**
     * Field ACTION_EVENT_CUSTOMER_AUTH_WEBSERVICES.
     */
    public static final int EVENT_CUSTOMER_AUTH_WEBSERVICES = 111;

    public static final String EVENT_CUSTOMER_AUTH_WEBSERVICES_COMMAND = "CUSTOMER Auth";

    public static final AbstractEvent ACTION_EVENT_CUSTOMER_AUTH_WEBSERVICES = new AbstractEvent(
            AbstractEvent.class, EVENT_CUSTOMER_AUTH_WEBSERVICES, EVENT_CUSTOMER_AUTH_WEBSERVICES_COMMAND);

    /**
     * Field ACTION_EVENT_SELECT_ARTICLES.
     */
    public static final AbstractEvent ACTION_EVENT_SELECT_ARTICLES = new AbstractEvent(AbstractEvent.class, 113,
            "Select Articles");

    /**
     * Field EVENT_SELECT_ARTICLES. (value is 113)
     */
    public static final int EVENT_SELECT_ARTICLES = 113;

    /**
     * Field ACTION_EVENT_REPRINT_ZREPORT.
     */
    public static final AbstractEvent ACTION_EVENT_REPRINT_ZREPORT = new AbstractEvent(AbstractEvent.class, 114,
            "Reprint Z Report");

    /**
     * Field EVENT_REPRINT_ZREPORT. (value is 114)
     */
    public static final int EVENT_REPRINT_ZREPORT = 114;

    /**
     * Field ACTION_EVENT_REPRINT_CLIENTORDER.
     */
    public static final AbstractEvent ACTION_EVENT_REPRINT_CLIENTORDER = new AbstractEvent(AbstractEvent.class,
            115, "Reprint Last Client Order Movement");

    /**
     * Field EVENT_REPRINT_CLIENTORDER. (value is 115)
     */
    public static final int EVENT_REPRINT_CLIENTORDER = 115;

    /**
     * Field ACTION_EVENT_PAY_AUTH.
     */
    public static final int EVENT_PAY_AUTH = 116;

    public static final String EVENT_PAY_AUTH_COMMAND = "FDP con Autorizacion";

    public static final AbstractEvent ACTION_EVENT_PAY_AUTH = new AbstractEvent(AbstractEvent.class,
            EVENT_PAY_AUTH, EVENT_PAY_AUTH_COMMAND);

    /**
     * Field ACTION_EVENT_REBATE_AUTH.
     */
    public static final int EVENT_REBATE_AUTH = 117;

    public static final String EVENT_REBATE_AUTH_COMMAND = "REBAJAS Autorizacion";

    public static final AbstractEvent ACTION_EVENT_REBATE_AUTH = new AbstractEvent(AbstractEvent.class,
            EVENT_REBATE_AUTH, EVENT_REBATE_AUTH_COMMAND);

    /**
     * Field ACTION_EVENT_REPRINT_CHARGE_TO_ACCOUNT.
     */
    public static final AbstractEvent ACTION_EVENT_REPRINT_CHARGE_TO_ACCOUNT = new AbstractEvent(
            AbstractEvent.class, 118, "Reprint Charge to Account");

    /**
     * Field EVENT_REPRINT_CHARGE_TO_ACCOUNT. (value is 118)
     */
    public static final int EVENT_REPRINT_CHARGE_TO_ACCOUNT = 118;

    /**
     * Field ACTION_EVENT_XREPORT_AUTH.
     */
    public static final int EVENT_XREPORT_AUTH = 119;

    public static final String EVENT_XREPORT_AUTH_COMMAND = "Autorizacion para reporte X";

    public static final AbstractEvent ACTION_EVENT_XREPORT_AUTH = new AbstractEvent(AbstractEvent.class,
            EVENT_XREPORT_AUTH, EVENT_XREPORT_AUTH_COMMAND);

    /**
     * Field ACTION_EVENT_DEPOSITS_PAYMENTS.
     */
    public static final AbstractEvent ACTION_EVENT_DEPOSITS_PAYMENTS = new AbstractEvent(AbstractEvent.class, 120,
            "Pago de abono");

    /**
     * Field EVENT_DEPOSITS_PAYMENTS. (value is 120)
     */
    public static final int EVENT_DEPOSITS_PAYMENTS = 120;

    /**
     * Field ACTION_EVENT_UNLOCKPOS.
     */
    public static final int EVENT_UNLOCKPOS = 200;

    public static final String EVENT_UNLOCKPOS_COMMAND = "Event UnLock Pos Called";

    public static final AbstractEvent ACTION_EVENT_UNLOCKPOS = new AbstractEvent(AbstractEvent.class,
            EVENT_UNLOCKPOS, EVENT_UNLOCKPOS_COMMAND);

    /**
     * Field ACTION_EVENT_PRINT_VPOS.
     */
    public static final AbstractEvent ACTION_EVENT_PRINT_VPOS = new AbstractEvent(AbstractEvent.class, 201,
            "Event UnLock Pos Called");

    /**
     * Field EVENT_PRINT_VPOS. (value is 201)
     */
    public static final int EVENT_PRINT_VPOS = 201;

    /**
     * Field ACTION_EVENT_ELECTRONIC_ORDER.
     */
    public static final int EVENT_ELECTRONIC_ORDER = 202;

    public static final String EVENT_ELECTRONIC_ORDER_COMMAND = "Event Electronic order";

    public static final AbstractEvent ACTION_EVENT_ELECTRONIC_ORDER = new AbstractEvent(AbstractEvent.class,
            EVENT_ELECTRONIC_ORDER, EVENT_ELECTRONIC_ORDER_COMMAND);

    /**
     * Field ACTION_EVENT_FINISH_CREDITS.
     */
    public static final AbstractEvent ACTION_EVENT_FINISH_CREDITS = new AbstractEvent(AbstractEvent.class, 203,
            "Event Finish Credits");

    /**
     * Field EVENT_FINISH_CREDITS. (value is 203)
     */
    public static final int EVENT_FINISH_CREDITS = 203;

    /**
     * Field ACTION_EVENT_NEWSALE.
     */
    public static final AbstractEvent ACTION_EVENT_NEWSALE = new AbstractEvent(AbstractEvent.class, 204,
            "Event New Sale from Cancellation");

    /**
     * Field EVENT_NEWSALE. (value is 204)
     */
    public static final int EVENT_NEWSALE = 204;

    /**
     * Field ACTION_EVENT_CREDITS_CREATE_PASSWORD.
     */
    public static final int EVENT_CREDITS_CREATE_PASSWORD = 205;

    public static final String EVENT_CREDITS_CREATE_PASSWORD_COMMAND = "Event Credits Create Password";

    public static final AbstractEvent ACTION_EVENT_CREDITS_CREATE_PASSWORD = new AbstractEvent(AbstractEvent.class,
            EVENT_CREDITS_CREATE_PASSWORD, EVENT_CREDITS_CREATE_PASSWORD_COMMAND);

    /**
     * Field ACTION_EVENT_VALIDATE_RIF_MODEL.
     */
    public static final AbstractEvent ACTION_EVENT_VALIDATE_RIF_MODEL = new AbstractEvent(AbstractEvent.class, 206,
            "Validate Rif Model");

    /**
     * Field EVENT_VALIDATE_RIF_MODEL. (value is 206)
     */
    public static final int EVENT_VALIDATE_RIF_MODEL = 206;

    /**
     * Field ACTION_EVENT_ADDITIONAL_DATA_WINDOW.
     */
    public static final AbstractEvent ACTION_EVENT_ADDITIONAL_DATA_WINDOW = new AbstractEvent(AbstractEvent.class,
            207, "Ventana de Datos Adicionales");

    /**
     * Field EVENT_ADDITIONAL_DATA_WINDOW. (value is 207)
     */
    public static final int EVENT_ADDITIONAL_DATA_WINDOW = 207;

    /**
     * Field ACTION_EVENT_CASHIER_AUTH.
     */
    public static final AbstractEvent ACTION_EVENT_CASHIER_AUTH = new AbstractEvent(AbstractEvent.class, 208,
            "Entrega Parcial por Tercero");

    /**
     * Field EVENT_CASHIER_AUTH. (value is 208)
     */
    public static final int EVENT_CASHIER_AUTH = 208;

    /**
     * Field ACTION_EVENT_CHARGE_FOR_SERVICE_PRINT.
     */
    public static final AbstractEvent ACTION_EVENT_CHARGE_FOR_SERVICE_PRINT = new AbstractEvent(
            AbstractEvent.class, 209, "Event Print Carge For Service");

    /**
     * Field EVENT_CHARGE_FOR_SERVICE_PRINT. (value is 209)
     */
    public static final int EVENT_CHARGE_FOR_SERVICE_PRINT = 209;

    /**
     * Field ACTION_EVENT_PAY_TELEMARKETING.
     */
    public static final AbstractEvent ACTION_EVENT_PAY_TELEMARKETING = new AbstractEvent(AbstractEvent.class, 210,
            "Pago de venta telefonica");

    /**
     * Field EVENT_PAY_TELEMARKETING. (value is 210)
     */
    public static final int EVENT_PAY_TELEMARKETING = 210;

    /**
     * Field ACTION_EVENT_TELEMARKETING_AUTH.
     */
    public static final int EVENT_TELEMARKETING_AUTH = 211;

    public static final String EVENT_TELEMARKETING_AUTH_COMMAND = "Autorizacion de venta telefonica";

    public static final AbstractEvent ACTION_EVENT_TELEMARKETING_AUTH = new AbstractEvent(AbstractEvent.class,
            EVENT_TELEMARKETING_AUTH, EVENT_TELEMARKETING_AUTH_COMMAND);

    public static final AbstractEvent ACTION_EVENT_REGISTER_AGENT_RETENTION_CLIENT = new AbstractEvent(
            AbstractEvent.class, 212, "Evento registro agente de retencion");

    public static final int EVENT_REGISTER_AGENT_RETENTION_CLIENT = 212;

    public static final AbstractEvent ACTION_EVENT_REGISTER_EXONERATE_CLIENT = new AbstractEvent(
            AbstractEvent.class, 213, "Event registro cliente exonerado");

    public static final int EVENT_REGISTER_EXONERATE_CLIENT = 213;

    /**
     * Field ACTION_EVENT_SYNCDATE.
     */
    public static final int EVENT_SYNCDATE = 300;

    public static final String EVENT_SYNCDATE_COMMAND = "Event Sync Date";

    public static final AbstractEvent ACTION_EVENT_SYNCDATE = new AbstractEvent(AbstractEvent.class,
            EVENT_SYNCDATE, EVENT_SYNCDATE_COMMAND);

    /**
     * Field ACTION_EVENT_UPDATE_ORDER_STATUS_PAYED.
     */
    public static final int EVENT_UPDATE_ORDER_STATUS_PAYED = 301;

    public static final String EVENT_UPDATE_ORDER_STATUS_PAYED_COMMAND = "Update Status Order Payed";

    public static final AbstractEvent ACTION_EVENT_UPDATE_ORDER_STATUS_PAYED = new AbstractEvent(
            AbstractEvent.class, EVENT_UPDATE_ORDER_STATUS_PAYED, EVENT_UPDATE_ORDER_STATUS_PAYED_COMMAND);

    /**
     * Field ACTION_EVENT_CLIENT_FOUND_BY_REFUND.
     */
    public static final AbstractEvent ACTION_EVENT_CLIENT_FOUND_BY_REFUND = new AbstractEvent(AbstractEvent.class,
            302, "Client Found By Refund");

    /**
     * Field EVENT_CLIENT_FOUND_BY_REFUND. (value is 302)
     */
    public static final int EVENT_CLIENT_FOUND_BY_REFUND = 302;

    /**
     * Field ACTION_EVENT_PAY_READY_BY_REFUND.
     */
    public static final AbstractEvent ACTION_EVENT_PAY_READY_BY_REFUND = new AbstractEvent(AbstractEvent.class,
            303, "Pago listo by Refund");

    /**
     * Field EVENT_PAY_READY_BY_REFUND. (value is 303)
     */
    public static final int EVENT_PAY_READY_BY_REFUND = 303;

    /**
     * Field ACTION_EVENT_CORPORATEPAY_AUTH.
     */
    public static final AbstractEvent ACTION_EVENT_CORPORATEPAY_AUTH = new AbstractEvent(AbstractEvent.class, 304,
            "Autorizacion FDP Credito Corporativo");

    /**
     * Field EVENT_CORPORATEPAY_AUTH. (value is 304)
     */
    public static final int EVENT_CORPORATEPAY_AUTH = 304;

    /**
     * Field ACTION_EVENT_PAYMENT_REPRINT_AUTH.
     */
    public static final int EVENT_PAYMENT_REPRINT_AUTH = 305;

    public static final String EVENT_PAYMENT_REPRINT_AUTH_COMMAND = "Autorizacion Reimpresion FDP";

    public static final AbstractEvent ACTION_EVENT_PAYMENT_REPRINT_AUTH = new AbstractEvent(AbstractEvent.class,
            EVENT_PAYMENT_REPRINT_AUTH, EVENT_PAYMENT_REPRINT_AUTH_COMMAND);

    /**
     * Field ACTION_EVENT_PAY_READY_FROM_REFUND.
     */
    public static final AbstractEvent ACTION_EVENT_PAY_READY_FROM_REFUND = new AbstractEvent(AbstractEvent.class,
            306, "Finalizar transaccion Proveniende de devolucion");

    /**
     * Field EVENT_PAY_READY_FROM_REFUND. (value is 306)
     */
    public static final int EVENT_PAY_READY_FROM_REFUND = 306;

    /**
     * Field ACTION_EVENT_DO_NOT_IN_BACKGROUND.
     */
    public static final AbstractEvent ACTION_EVENT_DO_NOT_IN_BACKGROUND = new AbstractEvent(AbstractEvent.class,
            307, "No Hacer updateJpa en Blackground");

    /**
     * Field EVENT_DO_NOT_IN_BACKGROUND. (value is 307)
     */
    public static final int EVENT_DO_NOT_IN_BACKGROUND = 307;

    /**
     * Field ACTION_EVENT_UPDATE_SALE_AND_PRINT.
     */
    public static final AbstractEvent ACTION_EVENT_UPDATE_SALE_AND_PRINT = new AbstractEvent(AbstractEvent.class,
            308, "Actualizar las ventas antes de imprimir");

    /**
     * Field EVENT_UPDATE_SALE_AND_PRINT. (value is 308)
     */
    public static final int EVENT_UPDATE_SALE_AND_PRINT = 308;

    /**
     * Field ACTION_EVENT_DISABLE_ARTICLE_FIELD.
     */
    public static final AbstractEvent ACTION_EVENT_DISABLE_ARTICLE_FIELD = new AbstractEvent(AbstractEvent.class,
            309, "Deshabilita el campo de articulo en la pantalla principal");

    /**
     * Field EVENT_DISABLE_ARTICLE_FIELD. (value is 309)
     */
    public static final int EVENT_DISABLE_ARTICLE_FIELD = 309;

    /**
     * Field ACTION_EVENT_RECOVER_QUOTATION.
     */
    public static final int EVENT_RECOVER_QUOTATION = 310;

    public static final String EVENT_RECOVER_QUOTATION_COMMAND = "Event Electronic order";

    public static final AbstractEvent ACTION_EVENT_RECOVER_QUOTATION = new AbstractEvent(AbstractEvent.class,
            EVENT_RECOVER_QUOTATION, EVENT_RECOVER_QUOTATION_COMMAND);

    /**
     * Field ACTION_EVENT_CREDITS_REFUNDBYOPERATION.
     */
    public static final AbstractEvent ACTION_EVENT_CREDITS_REFUNDBYOPERATION = new AbstractEvent(
            AbstractEvent.class, 312, "Event Credits Refund By Operation");

    /**
     * Field EVENT_CREDITS_REFUNDBYOPERATION. (value is 312)
     */
    public static final int EVENT_CREDITS_REFUNDBYOPERATION = 312;

    /**
     * Field ACTION_EVENT_VPOS_PRECLOSURE_CALL.
     */
    public static final int EVENT_VPOS_PRECLOSURE_CALL = 311;

    public static final String EVENT_VPOS_PRECLOSURE_CALL_COMMAND = "Event Electronic order";

    public static final AbstractEvent ACTION_EVENT_VPOS_PRECLOSURE_CALL = new AbstractEvent(AbstractEvent.class,
            EVENT_VPOS_PRECLOSURE_CALL, EVENT_VPOS_PRECLOSURE_CALL_COMMAND);

    /**
     * Field ACTION_EVENT_VPOS_REVERSE_CALL.
     */
    public static final int EVENT_VPOS_REVERSE_CALL = 313;

    public static final String EVENT_VPOS_REVERSE_CALL_COMMAND = "Reverso de Punto Ágil";

    public static final AbstractEvent ACTION_EVENT_VPOS_REVERSE_CALL = new AbstractEvent(AbstractEvent.class,
            EVENT_VPOS_REVERSE_CALL, EVENT_VPOS_REVERSE_CALL_COMMAND);

    /**
     * Field ACTION_EVENT_PAY_ORDER.
     */
    public static final AbstractEvent ACTION_EVENT_PAY_ORDER = new AbstractEvent(AbstractEvent.class, 314,
            "ActionPagar Pedido Especial");

    /**
     * Field EVENT_PAY_ORDER. (value is 314)
     */
    public static final int EVENT_PAY_ORDER = 314;

    /**
     * Field ACTION_EVENT_REFUND_PRINT_ANULDEV.
     */
    public static final AbstractEvent ACTION_EVENT_REFUND_PRINT_ANULDEV = new AbstractEvent(AbstractEvent.class,
            315, "Event Print Refund Anuldev");

    /**
     * Field EVENT_REFUND_PRINT_ANULDEV. (value is 315)
     */
    public static final int EVENT_REFUND_PRINT_ANULDEV = 315;

    /**
     * Field ACTION_EVENT_PAYMENT_REVERSE_AUTH.
     */

    public static final int EVENT_PAYMENT_REVERSE_AUTH = 316;

    public static final String EVENT_PAYMENT_REVERSE_AUTH_COMMAND = "Finalizar transaccion Proveniende de devolucion";

    public static final AbstractEvent ACTION_EVENT_PAYMENT_REVERSE_AUTH = new AbstractEvent(AbstractEvent.class,
            EVENT_PAYMENT_REVERSE_AUTH, EVENT_PAYMENT_REVERSE_AUTH_COMMAND);

    /**
     * Field ACTION_EVENT_CANCELLATION_MULTIPLEPRINT.
     */
    public static final AbstractEvent ACTION_EVENT_CANCELLATION_MULTIPLEPRINT = new AbstractEvent(
            AbstractEvent.class, 317, "Anulacion de pedidos con multiples condiciones de entrega");

    /**
     * Field EVENT_CANCELLATION_MULTIPLEPRINT. (value is 317)
     */
    public static final int EVENT_CANCELLATION_MULTIPLEPRINT = 317;

    /**
     * Field ACTION_EVENT_CREDITS_REFUNDBYAMOUNT.
     */
    public static final AbstractEvent ACTION_EVENT_CREDITS_REFUNDBYAMOUNT = new AbstractEvent(AbstractEvent.class,
            318, "Event Credits Refund by amount");

    /**
     * Field EVENT_CREDITS_REFUNDBYAMOUNT. (value is 318)
     */
    public static final int EVENT_CREDITS_REFUNDBYAMOUNT = 318;

    /**
     * Field ACTION_EVENT_CREDITS_PASSWORD_AUTH_SALE_BETWEEN_STORE.
     */
    public static final int EVENT_CREDITS_PASSWORD_AUTH_SALE_BETWEEN_STORE = 319;

    public static final String EVENT_CREDITS_PASSWORD_AUTH_SALE_BETWEEN_STORE_COMMAND = "Credits Autorized Password Sale Between Stores";

    public static final AbstractEvent ACTION_EVENT_CREDITS_PASSWORD_AUTH_SALE_BETWEEN_STORE = new AbstractEvent(
            AbstractEvent.class, EVENT_CREDITS_PASSWORD_AUTH_SALE_BETWEEN_STORE,
            EVENT_CREDITS_PASSWORD_AUTH_SALE_BETWEEN_STORE_COMMAND);

    /**
     * Field ACTION_EVENT_CLIENT_FOUND.
     */
    public static final AbstractEvent ACTION_EVENT_CLIENT_FOUND = new AbstractEvent(AbstractEvent.class, 320,
            "Client Found");

    /**
     * Field EVENT_CLIENT_FOUND. (value is 320)
     */
    public static final int EVENT_CLIENT_FOUND = 320;

    /**
     * Field ACTION_EVENT_REPRINT_CORPORATE_CREDIT.
     */
    public static final AbstractEvent ACTION_EVENT_REPRINT_CORPORATE_CREDIT = new AbstractEvent(
            AbstractEvent.class, 321, "Reprint Corporate Credit");

    /**
     * Field EVENT_REPRINT_CORPORATE_CREDIT. (value is 321)
     */
    public static final int EVENT_REPRINT_CORPORATE_CREDIT = 321;

    /**
     * Field ACTION_EVENT_CREDIT_DEV_AUTH_WITH_CHECK.
     */
    public static final int EVENT_CREDIT_DEV_AUTH_WITH_CHECK = 322;

    public static final String EVENT_CREDIT_DEV_AUTH_WITH_CHECK_COMMAND = "Autorizacion para devolucion de acreencia con cheque";

    public static final AbstractEvent ACTION_EVENT_CREDIT_DEV_AUTH_WITH_CHECK = new AbstractEvent(
            AbstractEvent.class, EVENT_CREDIT_DEV_AUTH_WITH_CHECK, EVENT_CREDIT_DEV_AUTH_WITH_CHECK_COMMAND);

    /**
     * Field ACTION_EVENT_PROCESS_RETENTION.
     */
    public static final AbstractEvent ACTION_EVENT_PROCESS_RETENTION = new AbstractEvent(AbstractEvent.class, 323,
            "Process retention");

    /**
     * Field EVENT_PROCESS_RETENTION. (value is 323)
     */
    public static final int EVENT_PROCESS_RETENTION = 323;

    /**
     * Field ACTION_EVENT_REFUND_FROM_CUSTOMER.
     */
    public static final AbstractEvent ACTION_EVENT_REFUND_FROM_CUSTOMER = new AbstractEvent(AbstractEvent.class,
            324, "Refund from customer");

    /**
     * Field EVENT_REFUND_FROM_CUSTOMER. (value is 324)
     */
    public static final int EVENT_REFUND_FROM_CUSTOMER = 324;

    /**
     * Field ACTION_EVENT_REPRINT_CANCELLATION_DOC.
     */
    public static final AbstractEvent ACTION_EVENT_REPRINT_CANCELLATION_DOC = new AbstractEvent(
            AbstractEvent.class, 326, "Reprint Cancellation Document");

    /**
     * Field EVENT_REPRINT_CANCELLATION_DOC. (value is 326)
     */
    public static final int EVENT_REPRINT_CANCELLATION_DOC = 326;

    /**
     * Field ACTION_EVENT_REFUND_CREDIT_PAYMENTS.
     */
    public static final int EVENT_REFUND_CREDIT_PAYMENTS = 327;

    public static final String EVENT_REFUND_CREDIT_PAYMENTS_COMMAND = "Refund Credit Payments";

    public static final AbstractEvent ACTION_EVENT_REFUND_CREDIT_PAYMENTS = new AbstractEvent(AbstractEvent.class,
            EVENT_REFUND_CREDIT_PAYMENTS, EVENT_REFUND_CREDIT_PAYMENTS_COMMAND);

    /**
     * Field ACTION_EVENT_REPRINT_CLOSURE_VPOS.
     */
    public static final AbstractEvent ACTION_EVENT_REPRINT_CLOSURE_VPOS = new AbstractEvent(AbstractEvent.class,
            328, "Reimpresion cierre vpos");

    /**
     * Field EVENT_REPRINT_CLOSURE_VPOS. (value is 328)
     */
    public static final int EVENT_REPRINT_CLOSURE_VPOS = 328;

    /**
     * Field ACTION_EVENT_ARTICLE_ADDED_QUANTITY.
     */
    public static final AbstractEvent ACTION_EVENT_ARTICLE_ADDED_QUANTITY = new AbstractEvent(AbstractEvent.class,
            329, "Article Added Quantity");

    /**
     * Field EVENT_ARTICLE_ADDED_QUANTITY. (value is 329)
     */
    public static final int EVENT_ARTICLE_ADDED_QUANTITY = 329;

    /**
     * Field ACTION_EVENT_REFUND_RECOVER_BY_CLIENT.
     */
    public static final int EVENT_REFUNDRECOVER_BY_CLIENT = 330;

    public static final String EVENT_REFUNDRECOVER_BY_CLIENT_COMMAND = "Refund Recover by Client";

    public static final AbstractEvent ACTION_EVENT_REFUND_RECOVER_BY_CLIENT = new AbstractEvent(
            AbstractEvent.class, EVENT_REFUNDRECOVER_BY_CLIENT, EVENT_REFUNDRECOVER_BY_CLIENT_COMMAND);

    /**
     * Field ACTION_EVENT_CASHIER_REPORT_AUTH.
     */
    public static final int EVENT_CASHIER_REPORT_AUTH = 331;

    public static final String EVENT_CASHIER_REPORT_AUTH_COMMAND = "Autorizacion para reporte de cajero";

    public static final AbstractEvent ACTION_EVENT_CASHIER_REPORT_AUTH = new AbstractEvent(AbstractEvent.class,
            EVENT_CASHIER_REPORT_AUTH, EVENT_CASHIER_REPORT_AUTH_COMMAND);

    /**
     * Field ACTION_EVENT_UPDATE_ORDER_STATUS_CANCELLED.
     */
    public static final int EVENT_UPDATE_ORDER_STATUS_CANCELLED = 332;

    public static final String EVENT_UPDATE_ORDER_STATUS_CANCELLED_COMMAND = "Update Status Order Cancelled";

    public static final AbstractEvent ACTION_EVENT_UPDATE_ORDER_STATUS_CANCELLED = new AbstractEvent(
            AbstractEvent.class, EVENT_UPDATE_ORDER_STATUS_CANCELLED, EVENT_UPDATE_ORDER_STATUS_CANCELLED_COMMAND);

    /**
     * Field ACTION_EVENT_MENU_CALL.
     */
    public static final AbstractEvent ACTION_EVENT_MENU_CALL = new AbstractEvent(AbstractEvent.class, 999, "Menu");

    /**
     * Field EVENT_MENU_CALL. (value is 999)
     */
    public static final int EVENT_MENU_CALL = 999;

    /**
     * Field ACTION_EVENT_LOGOUT.
     */
    public static final AbstractEvent ACTION_EVENT_LOGOUT = new AbstractEvent(AbstractEvent.class, 998,
            "Event Logout");

    /**
     * Field EVENT_LOGOUT. (value is 998)
     */
    public static final int EVENT_LOGOUT = 998;

    /**
     * Field ACTION_EVENT_VERIFY_RETENTIONS.
     */
    public static final AbstractEvent ACTION_EVENT_VERIFY_RETENTIONS = new AbstractEvent(AbstractEvent.class, 667,
            "Transaccion tiene retenciones");

    /**
     * Field EVENT_VERIFY_RETENTIONS. (value is 667)
     */
    public static final int EVENT_VERIFY_RETENTIONS = 667;

    /**
     * Field ACTION_EVENT_REMOVE_RETENTIONS.
     */
    public static final AbstractEvent ACTION_EVENT_REMOVE_RETENTIONS = new AbstractEvent(AbstractEvent.class, 668,
            "Elimina retenciones de la transaccion");

    /**
     * Field EVENT_REMOVE_RETENTIONS. (value is 668)
     */
    public static final int EVENT_REMOVE_RETENTIONS = 668;

    /**
     * Field ACTION_EVENT_REQUEST_APP_STATUS.
     */
    public static final AbstractEvent ACTION_EVENT_REQUEST_APP_STATUS = new AbstractEvent(AbstractEvent.class, 669,
            "App Status");

    /**
     * Field EVENT_REQUEST_APP_STATUS. (value is 669)
     */
    public static final int EVENT_REQUEST_APP_STATUS = 669;

    /**
     * Evento para llevar a un estado que solo puede tener como reaccion borrar la
     * transaccion y reversar porque la transaccion no se puede procesar
     */
    public static final AbstractEvent ACTION_EVENT_NON_PRINTABLE_TRANSACTION = new AbstractEvent(
            AbstractEvent.class, 700, "Estado final");

    public static final AbstractEvent ACTION_EVENT_CHECKARTICLE_LIMIT = new AbstractEvent(AbstractEvent.class, 671,
            "Solicitud limite de articulo");

    public static final int EVENT_CHECKARTICLE_LIMIT = 671;

}