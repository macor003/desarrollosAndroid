package com.epa.cr.legacy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.querydsl.jpa.impl.JPAQuery;

import crjpa400.Motivodevolucion;
import crjpa400.QMotivodevolucion;

public class MotivodevolucionJpaController {

    private static final QMotivodevolucion qMotivoDevolucion = QMotivodevolucion.motivodevolucion;

    public Motivodevolucion findMotiveDescription(int id, EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        JPAQuery<Motivodevolucion> query = new JPAQuery<Motivodevolucion>(em);

        try {
            return query.select(qMotivoDevolucion).from(qMotivoDevolucion)
                    .where(qMotivoDevolucion.codigoMotivo.eq(id)).fetchOne();
        } finally {
            em.close();
        }

    }
}
