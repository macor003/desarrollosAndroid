package com.grid.ventas.cr.refundrestserver.bussiness.checkservicesrefund;

import biz.epa.ruleengine.AbstractAction;
import biz.epa.ruleengine.RuleEngineContext;
import com.becoblohm.cr.models.AnulDev;
import com.grid.ventas.cr.refundrestserver.service.SpecialOrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by eve0017280 on 01/04/16.
 */
public class SearchSpecialOrderDelegate extends AbstractAction {

    @Autowired
    private SpecialOrderService specialOrderService;

    @Override
    protected void doExecute(RuleEngineContext ruleEngineContext) throws Exception {
        AnulDev anulDev = (AnulDev) ruleEngineContext.get("anulDev");

        if (this.specialOrderService.searchTransactionInCabvpea(anulDev.getOriginalSale()) != null) {
            anulDev.setFromSpecialOrder(true);
        } else {
            anulDev.setFromSpecialOrder(false);
        }
    }
}
