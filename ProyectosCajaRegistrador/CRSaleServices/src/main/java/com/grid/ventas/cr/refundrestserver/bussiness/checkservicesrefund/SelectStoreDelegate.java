package com.grid.ventas.cr.refundrestserver.bussiness.checkservicesrefund;

import biz.epa.ruleengine.AbstractRule;
import biz.epa.ruleengine.RuleEngineContext;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.models.Session;

/**
 * Created by eve0017280 on 29/03/16.
 */
public class SelectStoreDelegate extends AbstractRule {

    @Override
    protected boolean makeDecision(RuleEngineContext ruleEngineContext) throws Exception {

        Transaction data = (Transaction) ruleEngineContext.get("data");
        Session session = (Session) ruleEngineContext.get("session");
        return (data.getStore().equalsIgnoreCase(session.getStoreId()));
    }
}
