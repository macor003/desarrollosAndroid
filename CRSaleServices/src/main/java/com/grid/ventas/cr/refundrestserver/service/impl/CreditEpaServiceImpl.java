/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.grid.ventas.cr.refundrestserver.service.impl;

import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.grid.ventas.cr.refundrestserver.service.CreditEpaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreditEpaServiceImpl implements CreditEpaService {

    private static final Logger logger = LoggerFactory.getLogger(CreditEpaServiceImpl.class);

    @Override
    public ServicesResponse checkServicesRefundCompleted(Transaction data) {
        debug("credito epa");
        return new ServicesResponse("", null);
    }

    private void debug(String msg) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        }
    }
}
