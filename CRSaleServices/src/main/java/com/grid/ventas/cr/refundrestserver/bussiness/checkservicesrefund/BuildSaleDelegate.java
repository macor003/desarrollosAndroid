package com.grid.ventas.cr.refundrestserver.bussiness.checkservicesrefund;

import biz.epa.ruleengine.AbstractAction;
import biz.epa.ruleengine.AbstractRule;
import biz.epa.ruleengine.RuleEngineContext;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.response.ServicesResponse;

/**
 * Created by eve0017280 on 30/03/16.
 */
public class BuildSaleDelegate extends AbstractRule {

    @Override
    protected boolean makeDecision(RuleEngineContext ruleEngineContext) throws Exception {
        Transaction transaction = (Transaction) ruleEngineContext.get("transaction");
        ServicesResponse result = new ServicesResponse("", null);
        result.setCode(-1);

        if (transaction != null) {
            result = new ServicesResponse("", transaction);
            result.setCode(0);
        }
        ruleEngineContext.put("sale", result);
        return result.getCode() == 0;
    }
}
