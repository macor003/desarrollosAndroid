package com.grid.ventas.cr.refundrestserver.controller;

import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.grid.ventas.cr.refundrestserver.service.CreditEpaService;
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
@RequestMapping("/creditEpa")
public class CreditEpaController {

    final static Logger logger = LoggerFactory.getLogger(CreditEpaController.class);

    @Autowired
    private CreditEpaService creditEpaService;

    @RequestMapping("/checkServicesRefundCompleted")
    public ServicesResponse checkServicesRefundCompleted(@RequestBody Transaction data) {
        logger.debug("Recibiendo peticion de /checkServicesRefundCompleted");
        return creditEpaService.checkServicesRefundCompleted(data);
    }

}
