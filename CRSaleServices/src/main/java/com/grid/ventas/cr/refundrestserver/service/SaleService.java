package com.grid.ventas.cr.refundrestserver.service;

import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Tax;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.models.Session;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.becoblohm.cr.response.CRServiceResponse;

import java.util.Date;

/**
 * Created by eve0017280 on 03/02/16.
 */
public interface SaleService {

    CRServiceResponse<Transaction> searchOriginSale(Transaction subject, Session session);

    CRServiceResponse<Transaction> searchRemoteOriginSale(Transaction data, Session session);

    Transaction searchOriginSaleOnCR400(Transaction data);

    Transaction searchOriginSaleOnLegacy(Transaction data);

    RMIServerResponse searchLastTransactionTrcmov(String storeId, String posId);

    Tax getTax(String store, Article auxArticle, Date date);
}
