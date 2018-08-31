package com.grid.ventas.cr.refundrestserver.service;

import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.models.Deposit;
import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.becoblohm.cr.response.SpecialOrderResponse;

import java.util.ArrayList;

/**
 * Created by eve0017280 on 10/02/16.
 */
public interface SpecialOrderService {

    public ServicesResponse ValidateIfTransactionisFromSpecialOrder(Transaction data);

    public Transaction searchTransactionInCabvpea(Transaction originalSale);

    public Deposit getLastDeposit(ArrayList<Deposit> depositsList);

    public SpecialOrderResponse requestLastOrderDeposit(Order order, Session session);

    public SpecialOrderResponse requestSpecialOrder(Client client, com.becoblohm.cr.net.models.Session session);

    public SpecialOrderResponse registerOperationDeposit(Deposit deposit);

    public SpecialOrderResponse updateSpecialOrderStatus(Order order);

    public SpecialOrderResponse requestOrderId(Transaction data, Session session);

    public RMIServerResponse requestTransactionNumbers(Long orderId, Session session);
}
