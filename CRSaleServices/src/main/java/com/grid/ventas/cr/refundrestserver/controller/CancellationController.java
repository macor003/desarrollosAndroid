package com.grid.ventas.cr.refundrestserver.controller;

import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.grid.ventas.cr.refundrestserver.service.CancellationService;
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
@RequestMapping("/cancellation")
public class CancellationController {

    final static Logger logger = LoggerFactory.getLogger(CancellationController.class);

    @Autowired
    private CancellationService cancellationService;

    @RequestMapping("/searchCancel")
    public ServicesResponse searchCancel(@RequestBody Transaction data) {
        logger.debug("Recibiendo peticion de /searchCancel");
        return cancellationService.searchCancel(data);
    }
}
