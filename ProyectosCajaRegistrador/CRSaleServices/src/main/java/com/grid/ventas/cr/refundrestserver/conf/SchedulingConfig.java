/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.grid.ventas.cr.refundrestserver.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

/**
 * Created by eve0017280 on 11/06/16.
 */

@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Autowired
    ServicesConf servicesConf;

    final static Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);

    private String shutdownUrl;

    // segundo minuto hora
    @Scheduled(cron = "${cron.shutdown}")
    public void shutdown() {
        shutdownUrl = "http://" + servicesConf.getServerIp() + ":" + servicesConf.getServerPort() + "/shutdown";
        logger.info("Scheduled shutdown. Sending request to " + shutdownUrl);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(shutdownUrl, "");
    }
}
