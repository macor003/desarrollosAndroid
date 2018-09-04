/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.grid.ventas.cr.refundrestserver.controller;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.becoblohm.cr.models.Order;
import com.epa.cr.legacy.CacheEPA;
import com.grid.ventas.cr.refundrestserver.conf.DataSourceConfig;
import com.grid.ventas.cr.refundrestserver.conf.ServicesConf;

import crjpa400.Opcion;
import crjpa400.OpcionJpaController;

/**
 * Created by eve0017280 on 15/02/16.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    ServicesConf servicesConf;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @RequestMapping("/ping")
    public String ping(@RequestParam String ping) {
        return ping;
    }

    @RequestMapping("/restart")
    public String restart(@RequestParam String restart, @RequestHeader Map<String, String> headers) {

        logger.info("=========================");
        logger.info("Creando EMF con los datos");
        logger.info("=========================");
        logger.info(DataSourceConfig.getProperties400().toString());

        if ("1".equals(restart)) {
            if (entityManagerFactory.isOpen()) {
                entityManagerFactory.close();
            }
            entityManagerFactory = Persistence.createEntityManagerFactory("CR400PU",
                                                                          DataSourceConfig.getProperties400());

        }
        return restart;
    }

    @RequestMapping("/cache")
    public void cache() {
        CacheEPA.reset();
    }

    @RequestMapping("/cc")
    public List<Order> cc(@RequestParam String id) {
        return CacheEPA.get(id);
    }

    @Bean
    public Long idExoneradotipoNull() {
        OpcionJpaController opcionJpaController = new OpcionJpaController(entityManagerFactory);
        Opcion opcion = opcionJpaController.findOpcion(142L);
        if (opcion == null) {
            return 0L;
        } else {
            return Long.valueOf(opcion.getValor().trim());
        }
    }
}
