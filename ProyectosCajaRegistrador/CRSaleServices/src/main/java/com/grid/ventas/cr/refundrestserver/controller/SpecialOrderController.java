package com.grid.ventas.cr.refundrestserver.controller;

import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.models.Deposit;
import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.becoblohm.cr.response.SpecialOrderResponse;
import com.grid.ventas.cr.refundrestclient.client.dao.RequestLastOrderDepositDao;
import com.grid.ventas.cr.refundrestclient.client.dao.RequestOrderIdDao;
import com.grid.ventas.cr.refundrestclient.client.dao.RequestSpecialOrderDao;
import com.grid.ventas.cr.refundrestclient.client.dao.RequestTransactionNumbersDao;
import com.grid.ventas.cr.refundrestserver.conf.RestFormatter;
import com.grid.ventas.cr.refundrestserver.service.SpecialOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eve0017280 on 12/02/16.
 */
@RestController
@RequestMapping("/specialOrder")
public class SpecialOrderController {

    final static Logger logger = LoggerFactory.getLogger(SpecialOrderController.class);

    @Autowired
    private SpecialOrderService specialOrderService;

    @Autowired
    private RestFormatter restFormatter;

    @RequestMapping("/requestSpecialOrder")
    public SpecialOrderResponse requestSpecialOrder(@RequestBody RequestSpecialOrderDao requestSpecialOrderDao) {
        Client client = requestSpecialOrderDao.getData();
        com.becoblohm.cr.net.models.Session session = requestSpecialOrderDao.getSession();
        SpecialOrderResponse specialOrderResponse = specialOrderService.requestSpecialOrder(client, session);
        avoidRecursionspecialOrderResponse(specialOrderResponse);
        return specialOrderResponse;
    }

    private void avoidRecursionspecialOrderResponse(SpecialOrderResponse specialOrderResponse) {
        List<Order> data = specialOrderResponse.getData();
        if (data != null) {
            for (Order order : data) {
                if (order != null) {
                    restFormatter.cleanOrder(order);
                }
            }
        }
    }

    @RequestMapping("/validateIfTransactionisFromSpecialOrder")
    public ServicesResponse validateIfTransactionisFromSpecialOrder(@RequestBody Transaction data) {
        return specialOrderService.ValidateIfTransactionisFromSpecialOrder(data);
    }

    @RequestMapping("/searchTransactionInCabvpea")
    public Transaction searchTransactionInCabvpea(@RequestBody Transaction originalSale) {
        return specialOrderService.searchTransactionInCabvpea(originalSale);
    }

    @RequestMapping("/getLastDeposit")
    public Deposit getLastDeposit(@RequestBody ArrayList<Deposit> depositsList) {
        return specialOrderService.getLastDeposit(depositsList);
    }

    @RequestMapping("/requestLastOrderDeposit")
    public SpecialOrderResponse requestLastOrderDeposit(@RequestBody RequestLastOrderDepositDao requestLastOrderDepositDao) {
        Order order = requestLastOrderDepositDao.getData();
        Session session = requestLastOrderDepositDao.getSession();
        return specialOrderService.requestLastOrderDeposit(order, session);
    }

    @RequestMapping("/registerOperationDeposit")
    public SpecialOrderResponse registerOperationDeposit(@RequestBody Deposit deposit) {
        return specialOrderService.registerOperationDeposit(deposit);
    }

    @RequestMapping("/updateSpecialOrderStatus")
    public SpecialOrderResponse updateSpecialOrderStatus(@RequestBody Order order) {
        return specialOrderService.updateSpecialOrderStatus(order);
    }

    @RequestMapping("/requestOrderId")
    public SpecialOrderResponse requestOrderId(@RequestBody RequestOrderIdDao requestOrderIdDao) {
        Transaction transaction = requestOrderIdDao.getData();
        Session session = requestOrderIdDao.getSession();
        SpecialOrderResponse rmiServerResponse = specialOrderService.requestOrderId(transaction, session);

        return rmiServerResponse;
    }

    @RequestMapping("/requestTransactionNumbers")
    public RMIServerResponse requestTransactionNumbers(@RequestBody RequestTransactionNumbersDao requestTransactionNumbersDao) {
        Long orderId = requestTransactionNumbersDao.getData();
        Session session = requestTransactionNumbersDao.getSession();
        RMIServerResponse rmiServerResponse = specialOrderService.requestTransactionNumbers(orderId, session);
        return rmiServerResponse;
    }
}
