/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.querydsl.jpa.impl.JPAQuery;

import crjpa400.Devolucion;
import crjpa400.QDevolucion;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class DevolucionJpaController {

    private static final Logger logger = LoggerFactory.getLogger(DevolucionJpaController.class);

    public DevolucionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private static final QDevolucion qDevolucion = QDevolucion.devolucion;

    public List<Devolucion> findTransactionToRefund(Integer storeId, Integer posId, Integer tr, Character status)
            throws SQLException {

        EntityManager em = getEntityManager();
        JPAQuery<Devolucion> query = new JPAQuery<Devolucion>(em);
        List<Devolucion> tmp = new ArrayList<Devolucion>();
        List<Devolucion> result = new ArrayList<Devolucion>();

        try {

            tmp = (List<Devolucion>) query.select(qDevolucion).from(qDevolucion)
                    .where(qDevolucion.tiendaVenta.eq(storeId).and(qDevolucion.cajaVenta.eq(posId)
                            .and(qDevolucion.transaccionVenta.eq(tr).and(qDevolucion.estatusDev.eq(status)))))
                    .fetch();

            if (!tmp.isEmpty()) {
                result = tmp;
            } else {
                result = Collections.emptyList();
            }

        } finally {
            em.close();
        }

        return result;

    }

    public List<Devolucion> findTransactionToRefundByClientAndDate(String client, Date startDate, Date endDate)
            throws SQLException {

        EntityManager em = getEntityManager();
        JPAQuery<Devolucion> query = new JPAQuery<Devolucion>(em);
        List<Devolucion> tmp = new ArrayList<Devolucion>();
        List<Devolucion> result = new ArrayList<Devolucion>();

        try {

            tmp = query.select(qDevolucion).from(qDevolucion).where(qDevolucion.numeroIdentificacionCliente
                    .eq(client).and(qDevolucion.fecha.between(startDate, endDate))).fetch();

            if (!tmp.isEmpty()) {
                result = tmp;
            } else {
                result = Collections.emptyList();
            }

        } finally {
            em.close();

        }

        return result;

    }

    public boolean updateStatusHeader(Character fromStatus, Character toStatus, Devolucion devHeaders) {

        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(devHeaders);
            em.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }

        return true;

    }

}
