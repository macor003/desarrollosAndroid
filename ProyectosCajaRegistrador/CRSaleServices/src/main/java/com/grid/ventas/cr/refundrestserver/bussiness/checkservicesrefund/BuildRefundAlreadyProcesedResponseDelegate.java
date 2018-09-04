package com.grid.ventas.cr.refundrestserver.bussiness.checkservicesrefund;

import biz.epa.ruleengine.AbstractAction;
import biz.epa.ruleengine.RuleEngineContext;
import com.becoblohm.cr.net.response.ServicesResponse;

/**
 * Created by eve0017280 on 07/04/16.
 */
public class BuildRefundAlreadyProcesedResponseDelegate extends AbstractAction {

    @Override
    protected void doExecute(RuleEngineContext ruleEngineContext) throws Exception {
        // AnulDev anulDev = (AnulDev) ruleEngineContext.get("anulDev");
        ServicesResponse response = null;
        response.setCode(-1);
        response.setMsg("Transaccion ya devuelta en otra tienda.");
        ruleEngineContext.put("response", response);

    }
}
