package com.grid.ventas.cr.refundrestserver.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.becoblohm.cr.models.Tax;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.models.TransactionSessionWrapper;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.becoblohm.cr.response.CRServiceResponse;
import com.grid.ventas.cr.refundrestclient.client.dao.SearchLastNumberTCCabvptcDao;
import com.grid.ventas.cr.refundrestserver.conf.RestFormatter;
import com.grid.ventas.cr.refundrestserver.service.SaleService;

/**
 * Created by eve0017280 on 12/02/16.
 */
@RestController
@RequestMapping("/sale")
public class SaleController {

    final static Logger logger = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private SaleService saleService;

    @Autowired
    private RestFormatter restFormatter;

    @RequestMapping("/searchOriginSale")
    public CRServiceResponse<Transaction> searchOriginSale(@RequestBody TransactionSessionWrapper transactionSessionWrapper) {
        CRServiceResponse<Transaction> response = saleService
                .searchOriginSale(transactionSessionWrapper.getTransaction(),
                                  transactionSessionWrapper.getSession());
        response.setPayload(restFormatter.cleanOrder(response.getPayload()));
        return response;
    }

    @RequestMapping("/searchLastTransactionTrcmov")
    RMIServerResponse searchLastTransactionTrcmov(@RequestBody SearchLastNumberTCCabvptcDao searchLastNumberTCCabvptcDao) {
        String storeId = searchLastNumberTCCabvptcDao.getStoreId();
        String posId = searchLastNumberTCCabvptcDao.getPosId();
        return saleService.searchLastTransactionTrcmov(storeId, posId);
    }

    @RequestMapping("/f")
    public String t() {
        Tax tax = saleService.getTax("", null, new Date());
        long id = tax.getId();
        return String.valueOf(id);
    }
}
