package com.grid.ventas.cr.refundrestserver.service;

import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.becoblohm.cr.response.FindWaitingSaleResponse;

/**
 * Created by eve0017280 on 12/02/16.
 */
public interface WaitingSaleService {

    String SALE_ON_HOLD = "E";

    char SALE_IN_PROCESS = 'I';

    FindWaitingSaleResponse findWaitingSale();

    RMIServerResponse releaseWaitingSale(Transaction tr);

    RMIServerResponse holdWaitingSale(Transaction tr, Session session);

    RMIServerResponse getTransactionAudit(Long tr);
}
