/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.grid.ventas.cr.refundrestserver.conf;

import java.util.List;

import com.becoblohm.cr.models.StatusOrder;
import com.becoblohm.cr.models.Tax;
import com.grid.ventas.cr.refundrestclient.EPASalesServicesDispatcher;

/**
 * Created by eve0017280 on 11/02/16.
 */
public interface ServicesConf {

    public static int PENDING_FOR_FIRST_DEPOSIT = 1;

    public static int DEPOSITED = 2;

    public static int PENDING_FOR_CONFIRM_BILL = 3;

    public static int PENDING_FOR_CONDITIONALLY_BILL = 4;

    public static int BILLED = 5;

    public static int PENDING_FOR_CANCEL_WITH_CHARGE = 6;

    public static int PENDING_FOR_CANCEL_WITHOUT_CHARGE = 7;

    public static int CANCELED_WITH_CHARGE = 8;

    public static int CANCELED_WITHOUT_CHARGE = 9;

    public static int PENDING_FOR_BILL = 10;

    public static int LOGIC_CANCELATION = 11;

    public static int PENDING_FOR_BILL_QUOTATION = 12;

    public static int PENDING_FOR_RETRIEVE = 13;

    public static int DEPOSITED_BY_SALES_COMPANY = 14;

    public static int PENDING_FOR_BILL_SALES_COMPANY = 15;
    // tmpStatus =
    // buildMap(estadoOrdendeVentaJPAController.findEstadosOrdendeVentaEntities());
    // ServicesConf.PENDING_FOR_CONFIRM_BILL

    boolean isRefuntOnlyCurrentDate();

    public Tax getDefaultTax();

    boolean isClientRequired();

    List<String> getFdpCounterPartNotCash();

    boolean isUseRPC();

    public String getOrdersTypeSpecialOrders();

    public StatusOrder findStatusOrder(long id);

    public String getURLRPCByStore(String store);

    public Long getClientOrderTypeId();

    List<String> getServicesURLs();

    boolean isRemoteSearchActive();

    int getStoreNumber();

    public boolean isUpdateStatusRemoteRefundActive();

    int getRemoteServiceReadTimeout();

    int getRemoteServiceConnectionTimeout();

    EPASalesServicesDispatcher getDispatcher();

    public String getServerPort();

    public String getServerIp();

    boolean isUseLegacy();
    
    boolean isNewRefundTable();

}
