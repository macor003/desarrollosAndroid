package com.grid.ventas.cr.refundrestserver.service;

import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.response.ServicesResponse;

/**
 * Created by eve0017280 on 12/02/16.
 */
public interface CreditEpaService {

    ServicesResponse checkServicesRefundCompleted(Transaction data);
}
