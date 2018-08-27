package com.grid.ventas.cr.refundrestserver.bussiness.checkservicesrefund;

import biz.epa.ruleengine.AbstractRule;
import biz.epa.ruleengine.RuleEngineContext;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.epa.ventas.dto.CABVDEV;
import com.grid.ventas.cr.refundrestserver.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by eve0017280 on 01/04/16.
 */
public class SearchRefundHeaderDelegate extends AbstractRule {

    @Autowired
    RefundService refundService;

    @Override
    protected boolean makeDecision(RuleEngineContext ruleEngineContext) throws Exception {
        ServicesResponse result = (ServicesResponse) ruleEngineContext.get("result");
        Transaction data = (Transaction) ruleEngineContext.get("data");

        Transaction originalSale = (Transaction) result.getData();
        Transaction.SourceTransactionType sourceTransactionType = data.getTransactionType();

        ruleEngineContext.put("originalSale", originalSale);
        ruleEngineContext.put("sourceTransactionType", sourceTransactionType);

        String status = "X";
        if (sourceTransactionType.equals(Transaction.SourceTransactionType.Cancellation)) {
            status = null;
        }

        CABVDEV devHeaders = refundService.searchDevCabvdev(originalSale.getStore(), originalSale.getPosId(),
                                                            originalSale.getNumber(), status);
        ruleEngineContext.put("devHeaders", devHeaders);
        return devHeaders != null;
    }
}
