package com.grid.ventas.cr.refundrestserver.service;

import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.net.response.ServicesResponse;

/**
 * Created by eve0017280 on 12/02/16.
 */
public interface ClientOrderService {

    ServicesResponse checkServicesClientOrder(Client client);

    ServicesResponse deleteServicesClientOrder(Client client);
    
    ServicesResponse searchCommandsForCurrentDay();
}
