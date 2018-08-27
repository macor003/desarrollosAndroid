/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.response;

import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.net.response.RMIServerResponse;

import java.util.List;

/**
 */
public class SpecialOrderResponse extends RMIServerResponse {

    private List<Order> data;

    public SpecialOrderResponse(String msg, List<Order> data) {
        this.data = data;
    }

    @Override
    public List<Order> getData() {
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 91587096569592811L;

    /**
     * Field OK. (value is 0)
     */
    public static final int OK = 0;

    /**
     * Field CLIENT_NOT_FOUND. (value is 1)
     */
    public static final int CLIENT_NOT_FOUND = 1;

    /**
     * Field SPECIAL_ORDERS_NOT_FOUND. (value is 2)
     */
    public static final int SPECIAL_ORDERS_NOT_FOUND = 2;

    /**
     * Field ERROR. (value is -1)
     */
    public static final int ERROR = -1;

    /**
     * Field PAYMENTSINCOMPLETE. (value is 3)
     */
    public static final int PAYMENTSINCOMPLETE = 3;

    /**
     * Field DEPOSITHEADERNOREGISTERED. (value is 4)
     */
    public static final int DEPOSITHEADERNOREGISTERED = 4;

    /**
     * Constructor for SpecialOrderResponse.
     * 
     * @param msg String
     * @param data Object
     */
    public SpecialOrderResponse(String msg, Object data) {
        super(msg, data);
    }

    /**
     * Constructor for SpecialOrderResponse.
     */
    public SpecialOrderResponse() {
        super();
    }

}
