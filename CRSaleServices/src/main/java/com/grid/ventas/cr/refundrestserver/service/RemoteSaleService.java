package com.grid.ventas.cr.refundrestserver.service;

import com.becoblohm.cr.models.AnulDev;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.grid.ventas.cr.refundrestserver.util.RemoteSaleException;

import java.util.Date;
import java.util.Map;

/**
 * Created by eve0017280 on 27/02/16.
 */
public interface RemoteSaleService {

    Map<String, Object> getVenta(String storeId, String posId, String id, Date date) throws RemoteSaleException;

    /*
     * RemoteRefund registerRemoteRefund(RemoteRefund remoteRefundRequest); RemoteRefund
     * searchOldRefunds(RemoteRefund remoteRefundRequest) throws Exception;
     * List<RemoteRefundArticleInfo> extractArticlesInfo(Collection<Article> articles);
     * List<RemoteRefundArticleInfo> discardArticles(List<RemoteRefundArticleInfo>
     * originalArticles, List<RemoteRefundArticleInfo> returnedArticles) throws Exception;
     */
    // ServicesResponse getVentaRemota(String storeId, String posId, String id, Date
    // date);

    public boolean sendUpdateRemoteRefundRequest(AnulDev data);

    public ServicesResponse updateRemoteRefund(AnulDev data);
}
