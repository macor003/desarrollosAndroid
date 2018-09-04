/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.request;

import com.becoblohm.cr.net.models.Session;

/**
 */
public class ServicesRequest extends RMIServerRequest {

	
	/**
	 * Field REFUND.
	 * (value is 1)
	 */
	public final static int REFUND = 1;
	
	/**
	 * Field REFUND_IN_PROCESS.
	 * (value is 22)
	 */
	public final static int REFUND_IN_PROCESS = 22;
	
	/**
	 * Field REFUND_COMPLETED.
	 * (value is 6)
	 */
	public final static int REFUND_COMPLETED = 6;
	// public final static int FROM_SPECIAL_ORDER_VALIDATE=5;

	
	/**
	 * Field CANCELLATION.
	 * (value is 2)
	 */
	public final static int CANCELLATION = 2;

	/* COMANDA ELECTRONICA */
	/**
	 * Field CLIENT_ORDER.
	 * (value is 3)
	 */
	public final static int CLIENT_ORDER = 3;
	/**
	 * Field DELETE_CLIENTORDER.
	 * (value is 4)
	 */
	public final static int DELETE_CLIENTORDER = 4;

	/* VENTA EN ESPERA */
	/**
	 * Field WAITING_SALE_FIND.
	 * (value is 7)
	 */
	public final static int WAITING_SALE_FIND = 7;
	// public final static int WAITING_SALE_COMPLETED=8;
	/**
	 * Field WAITING_SALE_HOLD.
	 * (value is 9)
	 */
	public final static int WAITING_SALE_HOLD = 9;
	/**
	 * Field WAITING_SALE_RELEASE.
	 * (value is 8)
	 */
	public final static int WAITING_SALE_RELEASE = 8;
	/**
	 * Field WAITING_SALE_GETAUDIT.
	 * (value is 21)
	 */
	public static final int WAITING_SALE_GETAUDIT = 21;

	
	/**
	 * Field REGISTER_CREDIT_PAYMENT.
	 * (value is 11)
	 */
	public final static int REGISTER_CREDIT_PAYMENT = 11;
	/**
	 * Field ANUL_CREDIT_PAYMENT.
	 * (value is 12)
	 */
	public final static int ANUL_CREDIT_PAYMENT = 12;

	/* PEDIDO ESPECIAL */
	/**
	 * Field ORDERS_REQUEST.
	 * (value is 10)
	 */
	public final static int ORDERS_REQUEST = 10;
	/**
	 * Field ORDERS_REGIST_DEPOSIT.
	 * (value is 13)
	 */
	public final static int ORDERS_REGIST_DEPOSIT = 13;
	/**
	 * Field ORDERS_UPDATE_STATUS.
	 * (value is 14)
	 */
	public final static int ORDERS_UPDATE_STATUS = 14;
	/**
	 * Field ORDERS_GET_ID.
	 * (value is 17)
	 */
	public static final int ORDERS_GET_ID = 17;
	/**
	 * Field ORDERS_GET_TRANSACTION_NUMBERS.
	 * (value is 18)
	 */
	public final static int ORDERS_GET_TRANSACTION_NUMBERS = 18;

	/* ENTREGA TOTAL */
	/**
	 * Field TOTAL_DELIVERY_REGISTER.
	 * (value is 15)
	 */
	public final static int TOTAL_DELIVERY_REGISTER = 15;

	
	/**
	 * Field REPRINT_ORDERS.
	 * (value is 16)
	 */
	public final static int REPRINT_ORDERS = 16;

	/**
	 * Field GET_LAST_TR_NUMBER.
	 * (value is 19)
	 */
	public static final int GET_LAST_TR_NUMBER = 19;
	/**
	 * Field GET_LAST_PAY_CREDIT.
	 * (value is 20)
	 */
	public static final int GET_LAST_PAY_CREDIT = 20;
	/**
	 * Field IS_CANCELED.
	 * (value is -101)
	 */
	public static final int IS_CANCELED = -101;
	
	   /**
     * Field IS_CANCELED.
     * (value is 999)
     */
    public static final int PING = 1024;
    
    /**
     * Field IS_CANCELED.
     * (value is 999)
     */
    public static final int RESTART = 1016;

    /**
     * Field CHECK_SERVICES_REFUND.
     * (value is -102)
     */
    public static final int CHECK_SERVICES_REFUND = -102;
    /**
     * Field ORIGINAL_SALE.
     * (value is -103)
     */
    public static final int ORIGINAL_SALE = -103;
    /**
     * Field ORIGINAL_SALE peticion de devolucion remota.
     * (value is -104)
     */
    public static final int UPDATE_REMOTE_REFUND = -104;


	/**
	 * Constructor for ServicesRequest.
	 * @param serviceTypeClientOrder int
	 * @param model Object
	 * @param currentSession Session
	 */
	public ServicesRequest(int serviceTypeClientOrder, Object model, Session currentSession) {
		super(serviceTypeClientOrder, model, currentSession);
	}

	/**
	 * Constructor for ServicesRequest.
	 */
	public ServicesRequest() {
		super();
	}

}
