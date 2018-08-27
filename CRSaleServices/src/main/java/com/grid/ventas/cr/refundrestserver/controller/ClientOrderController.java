package com.grid.ventas.cr.refundrestserver.controller;

import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.grid.ventas.cr.refundrestserver.service.ClientOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by eve0017280 on 12/02/16.
 */
@RestController
@RequestMapping("/clientOrder")
public class ClientOrderController {

    final static Logger logger = LoggerFactory.getLogger(ClientOrderController.class);

    @Autowired
    private ClientOrderService clientOrderService;

    // Revisa comandas
    @RequestMapping("/checkServicesClientOrder")
    public ServicesResponse checkServicesClientOrder(@RequestBody Client client) {
        logger.debug("Recibiendo peticion de /checkServicesClientOrder");
        ServicesResponse response = clientOrderService.checkServicesClientOrder(client);
        return response;
    }

    @RequestMapping("/deleteServicesClientOrder")
    public ServicesResponse deleteServicesClientOrder(@RequestBody Client client) {
        logger.debug("Recibiendo peticion de /deleteServicesClientOrder");
        return clientOrderService.deleteServicesClientOrder(client);
    }

}
