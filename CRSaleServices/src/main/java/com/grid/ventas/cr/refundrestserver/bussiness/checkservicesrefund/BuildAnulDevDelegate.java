package com.grid.ventas.cr.refundrestserver.bussiness.checkservicesrefund;

import biz.epa.ruleengine.AbstractRule;
import biz.epa.ruleengine.RuleEngineContext;
import com.becoblohm.cr.models.AnulDev;
import com.becoblohm.cr.models.Transaction;
import com.epa.ventas.dto.CABVDEV;
import com.grid.ventas.cr.refundrestserver.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * Created by eve0017280 on 01/04/16.
 */
public class BuildAnulDevDelegate extends AbstractRule {

    @Autowired
    RefundService refundService;

    @Override
    protected boolean makeDecision(RuleEngineContext ruleEngineContext) throws Exception {
        CABVDEV devHeaders = (CABVDEV) ruleEngineContext.get("devHeaders");
        Transaction originalSale = (Transaction) ruleEngineContext.get("originalSale");
        List devDetails = (List) ruleEngineContext.get("devDetails");

        HashMap<Integer, String> type = new HashMap<Integer, String>();

        AnulDev anulDev = refundService.buildAnulDev(originalSale, devHeaders, devDetails, type);
        ruleEngineContext.put("anulDev", anulDev);
        return anulDev != null;
    }
}
