/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.grid.ventas.cr.refundrestserver.conf;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.becoblohm.cr.models.StatusOrder;
import com.becoblohm.cr.models.Tax;
import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.cr.legacy.EstadoordendeventaJpaController;
import com.epa.cr.legacy.OpcionJpaController;
import com.grid.ventas.cr.refundrestclient.EPASalesServicesDispatcher;

/**
 * Created by eve0017280 on 03/02/16.
 */

@Service
public class ServicesConfImpl implements ServicesConf {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    private static final Logger logger = LoggerFactory.getLogger(ServicesConfImpl.class);

    @Value("#{'${servicesURLs}'.split(',')}")
    private List<String> servicesURLs;

    @Value("${Order.DefaultTaxRate}")
    private double defaultTaxRateAsString = 12;

    @Value("${Refund.getRefundOnlyCurrentDate}")
    private boolean refuntOnlyCurrentDate = true;

    @Value("${Refund.isClientRequired}")
    private boolean clientRequired = true;

    @Value("#{'${Refund.fdpCounterPartNotCash}'.split(',')}")
    private List<String> fdpCounterPartNotCash;

    @Value("${server.port}")
    private String serverPort = "8080";

    @Value("${server.ip}")
    private String serverIp = "localhost";

    @Value("${server.useRPC}")
    private boolean useRPC = false;

    @Value("${server.newRefundTable}")
    private boolean newRefundTable = true;

    @Value("${server.useLegacy}")
    private boolean useLegacy = false;

    @Value("${Order.ClientOrderTypeId}")
    private String ordersTypeSpecialOrders = "2";

    @Value("${server.URLRPC}")
    private String urlRPC = "http://aplicaciones.t#.ve.epa.com:9080/serverRPC/xmlrpc";

    private Map<Long, StatusOrder> statusOrderMap;

    @Value("${Order.ClientOrderTypeId}")
    private Long clientOrderTypeId = 2l;

    @Value("${remoteSearchActive}")
    private boolean remoteSearchActive = false;

    @Value("${Refund.updateStatusRemoteRefundActive}")
    private boolean updateStatusRemoteRefundActive = false;

    @Value("${remote.service.read.timeout}")
    private int remoteServiceReadTimeout = 180000;

    @Value("${remote.service.connection.timeout}")
    private int remoteServiceConnectionTimeout = 180000;

    @Override
    public boolean isRefuntOnlyCurrentDate() {
        return refuntOnlyCurrentDate;
    }

    @Override
    public Tax getDefaultTax() {
        CRBigDecimal crBigDecimal = new CRBigDecimal(defaultTaxRateAsString);
        logger.debug("generando tax por defecto por " + crBigDecimal);
        Tax tax = new Tax();
        tax.setId(new Long(1));
        tax.setActive(true);
        tax.setName("test");
        tax.setTaxDate(new Date());
        tax.setTaxRate(crBigDecimal);
        return tax;
    }

    @Override
    public boolean isClientRequired() {
        return clientRequired;
    }

    @Override
    public boolean isUseRPC() {
        return useRPC;
    }

    @Override
    public String getOrdersTypeSpecialOrders() {
        return ordersTypeSpecialOrders;
    }

    @PostConstruct
    private void loadStatusOrderMap() {
        EstadoordendeventaJpaController estadoOrdendeVentaJPAController = new EstadoordendeventaJpaController(
                entityManagerFactory);
        Map<Long, StatusOrder> result = new HashMap<Long, StatusOrder>();
        List<StatusOrder> estadosOrdendeVentaEntities = estadoOrdendeVentaJPAController
                .findEstadosOrdendeVentaEntities();
        if (estadosOrdendeVentaEntities != null) {
            for (StatusOrder statusOrder : estadosOrdendeVentaEntities) {
                result.put(statusOrder.getId(), statusOrder);
            }
        }
        statusOrderMap = result;
    }

    @Override
    public StatusOrder findStatusOrder(long id) {
        return statusOrderMap.get(id);
    }

    @Override
    public String getURLRPCByStore(String store) {
        return urlRPC.replaceAll("#", store);
    }

    @Override
    public Long getClientOrderTypeId() {
        return clientOrderTypeId;
    }

    @Override
    public List<String> getServicesURLs() {
        return servicesURLs;
    }

    @Override
    public boolean isRemoteSearchActive() {
        return remoteSearchActive;
    }

    @Override
    public int getStoreNumber() {
        OpcionJpaController opcionJpaController = new OpcionJpaController(entityManagerFactory);
        String id = opcionJpaController.findById(1l);
        return Integer.parseInt(id);
    }

    @Override
    public boolean isUpdateStatusRemoteRefundActive() {
        return updateStatusRemoteRefundActive;
    }

    @Override
    public int getRemoteServiceReadTimeout() {
        return remoteServiceReadTimeout;
    }

    @Override
    public int getRemoteServiceConnectionTimeout() {
        return remoteServiceConnectionTimeout;
    }

    @Bean
    @Override
    public EPASalesServicesDispatcher getDispatcher() {
        return new EPASalesServicesDispatcher(false, getRemoteServiceReadTimeout(),
                getRemoteServiceConnectionTimeout());
    }

    @Override
    public List<String> getFdpCounterPartNotCash() {
        return fdpCounterPartNotCash;
    }

    @Override
    public String getServerPort() {
        return this.serverPort;
    }

    @Override
    public String getServerIp() {
        return this.serverIp;
    }

    @Override
    public boolean isUseLegacy() {
        return this.useLegacy;
    }

    @Override
    public boolean isNewRefundTable() {
        return this.newRefundTable;
    }

}
