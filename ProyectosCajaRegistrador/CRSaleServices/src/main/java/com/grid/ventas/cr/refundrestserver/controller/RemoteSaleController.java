package com.grid.ventas.cr.refundrestserver.controller;

import com.becoblohm.cr.models.AnulDev;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.grid.ventas.cr.refundrestserver.service.RemoteSaleService;
import com.grid.ventas.cr.refundrestserver.util.RemoteSaleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * Created by eve0017280 on 27/02/16.
 */
@RestController
@RequestMapping("/remotesale")
public class RemoteSaleController {

    @Autowired
    RemoteSaleService remoteSaleService;

    final static Logger logger = LoggerFactory.getLogger(SaleController.class);

    @RequestMapping(path = "/",
                    method = RequestMethod.GET)
    public Map<String, Object> getVenta(@RequestParam String storeId, @RequestParam String posId,
                                        @RequestParam String id, @RequestParam(required = false) Date date) {

        Map<String, Object> venta = null;
        try {
            venta = remoteSaleService.getVenta(storeId, posId, id, date);
        } catch (RemoteSaleException e) {
            e.printStackTrace();
        }
        return venta;

    }

    @RequestMapping("/updateRemoteDefund")
    public ServicesResponse updateRemoteDefund(@RequestBody AnulDev data) {

        return remoteSaleService.updateRemoteRefund(data);
    }

}
