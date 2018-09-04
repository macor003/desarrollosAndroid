package com.grid.ventas.cr.refundrestserver.bussiness.checkservicesrefund;

import biz.epa.ruleengine.AbstractRule;
import biz.epa.ruleengine.RuleEngineContext;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.models.Session;
import com.grid.ventas.cr.refundrestserver.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by eve0017280 on 30/03/16.
 */
public class SearchSaleOnLegacyDelegate extends AbstractRule {

    @Autowired
    private SaleService saleService;

    @Override
    protected boolean makeDecision(RuleEngineContext ruleEngineContext) throws Exception {
        Transaction data = (Transaction) ruleEngineContext.get("data");
        Session session = (Session) ruleEngineContext.get("session");
        Transaction transaction = saleService.searchOriginSaleOnLegacy(data);
        ruleEngineContext.put("transaction", transaction);
        return transaction != null;
    }
}
