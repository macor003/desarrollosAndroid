package com.grid.ventas.cr.refundrestserver.bussiness.checkservicesrefund;

import biz.epa.ruleengine.AbstractAction;
import biz.epa.ruleengine.RuleEngineContext;
import com.becoblohm.cr.models.AnulDev;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.response.ServicesResponse;

/**
 * Created by eve0017280 on 30/03/16.
 */
public class BuildSuccessResponseDelegate extends AbstractAction {

    @Override
    protected void doExecute(RuleEngineContext ruleEngineContext) throws Exception {

        AnulDev anulDev = (AnulDev) ruleEngineContext.get("anulDev");
        ServicesResponse response = new ServicesResponse("", anulDev);
        ruleEngineContext.put("response", response);
    }
}
