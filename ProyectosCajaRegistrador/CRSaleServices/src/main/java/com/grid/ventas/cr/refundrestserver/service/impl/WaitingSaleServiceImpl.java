/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.grid.ventas.cr.refundrestserver.service.impl;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.becoblohm.cr.sync.converters.AuditoriaConverter;
import com.becoblohm.cr.sync.converters.ClienteConverter;
import com.epa.cr.legacy.AuditoriaJpaController;
import com.epa.cr.legacy.TransaccionJpaController;
import com.grid.ventas.cr.refundrestserver.service.WaitingSaleService;
import com.becoblohm.cr.response.FindWaitingSaleResponse;
import crjpa400.Articulo;
import crjpa400.Auditoria;
import crjpa400.Tipodescuento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WaitingSaleServiceImpl implements WaitingSaleService {

    final static Logger logger = LoggerFactory.getLogger(WaitingSaleServiceImpl.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private Long idExoneradotipoNull;

    @Override
    public FindWaitingSaleResponse findWaitingSale() {

        debug("Recuperando venta en espera");
        TransaccionJpaController jpaController = new TransaccionJpaController(entityManagerFactory);
        List<Transaction> tmp = jpaController.findTransaccionWatingSale(new Date(), SALE_ON_HOLD,
                                                                        idExoneradotipoNull);

        if (tmp != null) {
            debug("Se encontraron " + tmp.size() + " ventas en espera");

            // 14-01-2013 Se agrega implementacion de metodos SynConverter para
            // mandar la data homologada a la caja
            for (Transaction transaction : tmp) {
                ClienteConverter converter = new ClienteConverter();
                try {
                    transaction.setClient(converter.fromServer(transaction.getClient(), entityManagerFactory));
                } catch (Exception e) {
                    logger.error("Problemas al convertir cliente con SyncConverter->", e);
                }
            }

        } else {
            debug("no se encontraron ventas en espera");
        }

        return new FindWaitingSaleResponse(0, tmp);
    }

    @Override
    public RMIServerResponse releaseWaitingSale(Transaction tr) {
        debug("Liberando venta en espera " + tr.getId());

        TransaccionJpaController jpaController = new TransaccionJpaController(entityManagerFactory);
        boolean response;
        try {
            response = jpaController.releaseWaitingSale(tr);// ,
            // SALE_IN_PROCESS);
        } catch (JpaException e) {
            logger.error("Error liberando venta en espera", e);
            response = false;
        }

        debug("Liberada venta en espera " + tr.getId());

        return new ServicesResponse("", response);
    }

    @Override
    public RMIServerResponse holdWaitingSale(Transaction tr, Session session) {
        debug("Registrando venta en espera");

        TransaccionJpaController jpaController = new TransaccionJpaController(entityManagerFactory);
        tr.setStatus(SALE_ON_HOLD);
        tr.setId(new Long(tr.getId()));
        boolean response = false;
        try {
            response = jpaController.persist(tr, session);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
            response = false;
        }

        if (!response) {
            debug("Error -->  Respondiendo falso al cliente");
        } else {
            debug("Respondiendo true al cliente");
        }

        // boolean response=jpaController.create(tr,session);
        return new ServicesResponse("", response);
    }

    @Override
    public RMIServerResponse getTransactionAudit(Long tr) {
        debug("Buscando auditoria de venta en espera " + tr);

        AuditoriaJpaController auditController = new AuditoriaJpaController(entityManagerFactory);
        List<Object> auditList = new ArrayList<Object>();
        try {
            List<Auditoria> tmp = auditController.findTransactionAudit(tr);
            AuditoriaConverter converter = new AuditoriaConverter();
            for (Auditoria audit : tmp) {
                auditList.add(converter.fromServer(audit, null));
            }
        } catch (Exception e) {
            logger.error("Error buscando auditoria de venta en espera" + e.getMessage(), e);
        }
        return new ServicesResponse("", auditList);
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
