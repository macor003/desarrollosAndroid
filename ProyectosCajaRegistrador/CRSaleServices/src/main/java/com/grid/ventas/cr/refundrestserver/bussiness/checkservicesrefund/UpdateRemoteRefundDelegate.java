package com.grid.ventas.cr.refundrestserver.bussiness.checkservicesrefund;

import biz.epa.ruleengine.AbstractRule;
import biz.epa.ruleengine.RuleEngineContext;
import com.grid.ventas.cr.refundrestserver.service.RemoteSaleService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by eve0017280 on 05/04/16.
 */
public class UpdateRemoteRefundDelegate extends AbstractRule {

    @Autowired
    private RemoteSaleService remoteSaleService;

    @Override
    protected boolean makeDecision(RuleEngineContext ruleEngineContext) throws Exception {
        /*
         * Transaction data = (Transaction) ruleEngineContext.get("data"); AnulDev anulDev
         * = (AnulDev) ruleEngineContext.get("anulDev"); RemoteRefund refundToRegister =
         * new RemoteRefund(data.getStore(), Long.parseLong(data.getPosId()),
         * Long.parseLong(data.getNumber())); RemoteRefund registerBeforeRefund =
         * remoteSaleService.searchOldRefunds(refundToRegister);
         * List<RemoteRefundArticleInfo> originalArticles =
         * remoteSaleService.extractArticlesInfo(data.getArticles());
         * List<RemoteRefundArticleInfo> returnedArticles =
         * registerBeforeRefund.getRemoteRefundArticleInfoList();
         * List<RemoteRefundArticleInfo> availiableArticles =
         * remoteSaleService.discardArticles(originalArticles, returnedArticles);
         * remoteSaleService.discardArticles(availiableArticles,
         * remoteSaleService.extractArticlesInfo(anulDev.getArticles())); RemoteRefund
         * registerAfterRefund = remoteSaleService.registerRemoteRefund(refundToRegister);
         * return registerAfterRefund.getMsgCode() == RemoteRefund.REGISTERED;
         */
        return false;
    }

}
