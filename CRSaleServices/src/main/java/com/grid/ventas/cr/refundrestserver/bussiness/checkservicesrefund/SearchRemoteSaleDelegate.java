package com.grid.ventas.cr.refundrestserver.bussiness.checkservicesrefund;

import biz.epa.ruleengine.AbstractAction;
import biz.epa.ruleengine.RuleEngineContext;

import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.models.Session;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.becoblohm.cr.response.CRServiceResponse;
import com.grid.ventas.cr.refundrestserver.conf.ServicesConf;
import com.grid.ventas.cr.refundrestserver.service.SaleService;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by eve0017280 on 30/03/16.
 */
public class SearchRemoteSaleDelegate extends AbstractAction {

    @Autowired
    private ServicesConf serviceConf;

    @Autowired
    private SaleService saleService;

    @Override
    protected void doExecute(RuleEngineContext ruleEngineContext) throws Exception {
        if (serviceConf.isRemoteSearchActive()) {
            Transaction data = (Transaction) ruleEngineContext.get("data");
            Session session = (Session) ruleEngineContext.get("session");
            CRServiceResponse<Transaction> crResponse = saleService.searchRemoteOriginSale(data, session);
            ServicesResponse result = new ServicesResponse(crResponse.getMessage(), crResponse.getPayload());
            result.setCode(crResponse.getCode());
            ruleEngineContext.put("result", result);
        }
    }
}
