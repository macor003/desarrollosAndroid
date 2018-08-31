package com.grid.ventas.cr.refundrestserver.bussiness.checkservicesrefund;

import biz.epa.ruleengine.AbstractRule;
import biz.epa.ruleengine.RuleEngineContext;
import com.becoblohm.cr.models.Transaction;
import com.epa.ventas.dto.CABVDEV;
import com.grid.ventas.cr.refundrestserver.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by eve0017280 on 01/04/16.
 */
public class SearchRefundDetailsDelegate extends AbstractRule {

    @Autowired
    RefundService refundService;

    @Override
    protected boolean makeDecision(RuleEngineContext ruleEngineContext) throws Exception {
        CABVDEV devHeaders = (CABVDEV) ruleEngineContext.get("devHeaders");
        Transaction originalSale = (Transaction) ruleEngineContext.get("originalSale");
        Transaction.SourceTransactionType sourceTransactionType = (Transaction.SourceTransactionType) ruleEngineContext
                .get("sourceTransactionType");

        List devDetails = refundService
                .searchDevDetvdev(originalSale.getStore(), originalSale.getPosId(), originalSale.getNumber(),
                                  devHeaders.getCorrcdev().toString().trim(), false, sourceTransactionType);
        ruleEngineContext.put("devDetails", devDetails);
        return devDetails != null;
    }
}
