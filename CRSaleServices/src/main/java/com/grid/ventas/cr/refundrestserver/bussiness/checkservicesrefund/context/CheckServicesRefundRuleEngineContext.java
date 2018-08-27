package com.grid.ventas.cr.refundrestserver.bussiness.checkservicesrefund.context;

import biz.epa.ruleengine.RuleEngineContext;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.models.Session;

/**
 * Created by eve0017280 on 29/03/16.
 */
public class CheckServicesRefundRuleEngineContext extends RuleEngineContext {

    private final Transaction transaction;

    private final Session session;

    public CheckServicesRefundRuleEngineContext(Transaction transaction, Session session) {
        this.transaction = transaction;
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
