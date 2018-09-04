package com.epa.cr.legacy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.impl.JPAQuery;

import crjpa400.Devolucion;
import crjpa400.Devolucionarticulo;
import crjpa400.QDevolucionarticulo;

public class DevolucionarticuloJpaController {

    public DevolucionarticuloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    @PersistenceContext
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private static final QDevolucionarticulo qDevArticulo = QDevolucionarticulo.devolucionarticulo;

    public List<Devolucionarticulo> findRefundDetails(Devolucion id, Character status) throws SQLException {
        EntityManager em = getEntityManager();
        JPAQuery<Devolucionarticulo> query = new JPAQuery<Devolucionarticulo>(em);
        List<Devolucionarticulo> result = new ArrayList<Devolucionarticulo>();
        List<Devolucionarticulo> tmp = new ArrayList<Devolucionarticulo>();

        try {
            tmp = query.select(qDevArticulo).from(qDevArticulo)
                    .where(qDevArticulo.idDevolucion.eq(id).and(qDevArticulo.estatusDev.eq(status))).fetch();

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
}
