package com.grid.ventas.cr.refundrestserver.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.becoblohm.cr.models.AnulDev;
import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.models.Session;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.epa.ventas.dto.CABVDEV;
import com.epa.ventas.dto.DETVDEV;

import crjpa400.Devolucionarticulo;

/**
 * Created by eve0017280 on 02/02/16.
 */

public interface RefundService {

    public AnulDev changeDevStatus(AnulDev dev, String fromStatus, String toStatus);

    public ServicesResponse checkServicesRefundCompleted(AnulDev data);

    public ServicesResponse checkServicesRefund(Transaction subject, com.becoblohm.cr.net.models.Session session);

    public RMIServerResponse checkServicesRefundInProcess(AnulDev data);

    public AnulDev executeDevCompleted(AnulDev dev);

    public AnulDev executeDevInProcess(AnulDev dev);

    AnulDev buildAnulDev(Transaction originalSale, CABVDEV devHeaders, List devDetails,
                         HashMap<Integer, String> type);

    AnulDev buildAnulDevFromNewTable(Transaction originalSale, List<Devolucionarticulo> devDetails,
                                     Map<Integer, String> type);

    CABVDEV searchDevCabvdev(String storeId, String posId, String id, String status);

    List<DETVDEV> searchDevDetvdev(String storeId, String posId, String id, String corrc, boolean isFinishProcess,
                                   Transaction.SourceTransactionType source);

    public Collection<AnulDev> getRefunds(Client client, Session session);

    public Collection<AnulDev> getRefundsFromNewTables(Client client, Session session);
}
