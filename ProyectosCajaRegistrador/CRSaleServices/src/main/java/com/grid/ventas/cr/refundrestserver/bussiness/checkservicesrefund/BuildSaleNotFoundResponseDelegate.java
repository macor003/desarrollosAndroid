package com.grid.ventas.cr.refundrestserver.bussiness.checkservicesrefund;

import biz.epa.ruleengine.AbstractAction;
import biz.epa.ruleengine.AbstractRule;
import biz.epa.ruleengine.RuleEngineContext;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.response.ServicesResponse;

/**
 * Created by eve0017280 on 30/03/16.
 */
public class BuildSaleNotFoundResponseDelegate extends AbstractAction {

    @Override
    protected void doExecute(RuleEngineContext ruleEngineContext) throws Exception {
        ServicesResponse responseOriginalSale = (ServicesResponse) ruleEngineContext.get("sale");

        ServicesResponse response = new ServicesResponse(responseOriginalSale.getMsg(), null);
        ruleEngineContext.put("response", response);
    }
}
