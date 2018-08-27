/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.grid.ventas.cr.refundrestserver.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.becoblohm.cr.crjpa400.controller.exceptions.IllegalOrphanException;
import com.becoblohm.cr.crjpa400.controller.exceptions.NonexistentEntityException;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.models.OrderArticle;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.becoblohm.cr.response.SpecialOrderResponse;
import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.cr.legacy.OrdendeventaArticuloJpaController;
import com.epa.cr.legacy.OrdendeventaJpaController;
import com.grid.ventas.cr.refundrestserver.conf.ServicesConf;
import com.grid.ventas.cr.refundrestserver.service.ClientOrderService;

@Service
public class ClientOrderServiceImpl implements ClientOrderService {

    private static final Logger logger = LoggerFactory.getLogger(ClientOrderServiceImpl.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private ServicesConf servicesConf;

    @Autowired
    private Long idExoneradotipoNull;

    @Override
    public ServicesResponse checkServicesClientOrder(Client client) {
        ServicesResponse result = new ServicesResponse();

        debug("Buscando los datos del cliente idNumero: " + client.getIdNumber());

        if (client != null) {
            debug("Buscando comandas electronicas en base de datos para el cliente: " + client.getIdNumber());
            OrdendeventaJpaController ordenJpa = new OrdendeventaJpaController(entityManagerFactory);
            List<Order> ordenDeVentaList = ordenJpa
                    .findOrdenDeVentaActivaByClientId_TypeOrder(client, servicesConf.getClientOrderTypeId(),
                                                                new Date(), idExoneradotipoNull);
            if (ordenDeVentaList != null && ordenDeVentaList.size() > 0) {
                debug("comandas encontradas");
                ArrayList<Article> products = new ArrayList<Article>();
                for (Order ordendeventa : ordenDeVentaList) {
                    if (ordendeventa.getStatusOrder()
                            .equals(this.servicesConf.findStatusOrder(ServicesConf.PENDING_FOR_RETRIEVE))
                            && ordendeventa.getArticles() != null
                            && ordendeventa.getArticlesCount().compareTo(CRBigDecimal.ZERO) > 0) {
                        for (Article article : ordendeventa.getArticles()) {
                            products.add(article);
                        }
                    }
                }

                result.setData(products);
                result.setCode(SpecialOrderResponse.OK);
            } else {
                debug("comandas no encontradas");
                result.setCode(SpecialOrderResponse.SPECIAL_ORDERS_NOT_FOUND);
            }
        }
        return result;
    }

    @Override
    public ServicesResponse deleteServicesClientOrder(Client client) {

        ServicesResponse result = new ServicesResponse();
        debug("Buscando los datos del cliente idNumero: " + client.getIdNumber());

        if (client != null) {
            debug("Buscando comandas electronicas en base de datos para el cliente: " + client.getIdNumber());
            OrdendeventaJpaController ordenJpa = new OrdendeventaJpaController(entityManagerFactory);

            List<Order> ordenDeVentaList = ordenJpa
                    .findOrdenDeVentaActivaByClientId_TypeOrder(client, servicesConf.getClientOrderTypeId(),
                                                                new Date(), idExoneradotipoNull);

            if (ordenDeVentaList != null && ordenDeVentaList.size() > 0) {

                debug("comandas encontradas");
                OrdendeventaJpaController ordenDeVentaJpaController = new OrdendeventaJpaController(
                        entityManagerFactory);

                for (Order ordendeventa : ordenDeVentaList) {
                    ordendeventa.setStatusOrder(this.servicesConf.findStatusOrder(ServicesConf.BILLED));
                    try {
                        ordenDeVentaJpaController.edit(ordendeventa);

                        if (ordendeventa.getArticlesCount().compareTo(CRBigDecimal.ZERO) > 0) {

                            OrdendeventaArticuloJpaController ordenDeVentaArticuloJpaController = new OrdendeventaArticuloJpaController(
                                    entityManagerFactory);

                            for (Article article : ordendeventa.getArticles()) {
                                OrderArticle orderArticle = (OrderArticle) article;
                                if (orderArticle.getStatus() != this.servicesConf
                                        .findStatusOrder(ServicesConf.CANCELED_WITH_CHARGE)
                                        || orderArticle.getStatus() != this.servicesConf
                                                .findStatusOrder(ServicesConf.CANCELED_WITHOUT_CHARGE)) {
                                    orderArticle.setStatus(ordendeventa.getStatusOrder());
                                    try {
                                        ordenDeVentaArticuloJpaController.edit(orderArticle);
                                    } catch (NonexistentEntityException e) {
                                        debug("Error al intentar actualizar status del articulo");
                                        debug(e.getMessage());
                                    } catch (Exception e) {
                                        debug("Error al intentar actualizar status del articulo");
                                        debug(e.getMessage());
                                    }
                                }
                            }
                        }

                    } catch (IllegalOrphanException e) {
                        debug("Error al intentar actualizar comanda electronica " + e.getMessage());
                    } catch (NonexistentEntityException e) {
                        debug("Error al intentar actualizar comanda electronica " + e.getMessage());
                    } catch (Exception e) {
                        debug("Error al intentar actualizar comanda electronica " + e.getMessage());
                    }
                }
                result.setCode(SpecialOrderResponse.OK);
            } else {
                debug("comandas no encontradas");
                result.setCode(SpecialOrderResponse.SPECIAL_ORDERS_NOT_FOUND);
            }
        }
        return result;
    }

    @Override
    public ServicesResponse searchCommandsForCurrentDay() {

        debug("Buscando comandas electronicas en base de datos del día: ");

        ServicesResponse result = new ServicesResponse();
        OrdendeventaJpaController ordenJpa = new OrdendeventaJpaController(entityManagerFactory);

        List<Order> ordenDeVentaList = ordenJpa.findOrdenDeVentaByType_Status(servicesConf.getClientOrderTypeId(),
                                                                              new Date());
        if (ordenDeVentaList != null && ordenDeVentaList.size() > 0) {
            result.setData(ordenDeVentaList);
            result.setCode(SpecialOrderResponse.OK);
        } else {
            debug("Comandas no encontradas");
            result.setCode(SpecialOrderResponse.SPECIAL_ORDERS_NOT_FOUND);
        }

        return result;
    }

    private void debug(String msg) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        }
    }

    private void info(String msg) {
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }
    }

}
