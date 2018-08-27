/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Synchronize;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Sincronizacion;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class SincronizacionJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private final EntityManagerFactory emf;

    /**
     * Field jpaController.
     */

    private final crjpa.SincronizacionJpaController jpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Sincronizacion";

    /**
     * Constructor for SincronizacionJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public SincronizacionJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        this.jpaController = new crjpa.SincronizacionJpaController(emf);
    }

    /**
     * Method queryEntities.
     * 
     * @return Collection<Synchronize>
     */
    public Collection<Synchronize> queryEntities() {
        Collection<Synchronize> result = new ArrayList<Synchronize>();

        Synchronize singleResult = null;
        List<Sincronizacion> resultList = jpaController.findSincronizacionEntities();

        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
            Sincronizacion sincronizacion = (Sincronizacion) iterator.next();

            singleResult = fromJPA(sincronizacion);
            result.add(singleResult);
        }

        return result;
    }

    /**
     * Method fromJPA.
     * 
     * @param sync Sincronizacion
     * @return Synchronize
     */
    private Synchronize fromJPA(Sincronizacion sync) {
        CRBigDecimal date = new CRBigDecimal(sync.getUltimasincronizacion().doubleValue(), 3);
        Synchronize result = new Synchronize(sync.getId(), sync.getNombre(), sync.getEscarga(),
                sync.getEsdescarga(), date);

        return result;
    }

    public Synchronize findByEntityToUpload(String tableName) {
        crjpa.Sincronizacion singleResult = null;
        EntityManager em = jpaController.getEntityManager();
        Query query = em
                .createQuery("SELECT s FROM Sincronizacion s WHERE s.nombre = :nombre and s.escarga = 'S' ");
        Synchronize result = null;

        try {
            query.setParameter("nombre", tableName);
            query.setMaxResults(1);
            singleResult = (Sincronizacion) query.getSingleResult();
            result = fromJPA(singleResult);
        } catch (javax.persistence.NoResultException ex) {
            result = null;
        } finally {
            em.clear();
            em.close();
        }
        return result;
    }

    private Sincronizacion getConciliationEntity(String entityName, EntityManager em) throws Exception {
        Sincronizacion result = new Sincronizacion();
        Query query = em.createQuery("SELECT s from Sincronizacion s WHERE s.nombre = :nombre and s.tipo = :tipo");
        query.setParameter("nombre", entityName);
        query.setParameter("tipo", crjpa.SincronizacionJpaController.TIPO_CONCILIACION);
        result = (Sincronizacion) query.getSingleResult();
        return result;
    }

    /**
     * 
     * @param entity entidad de la que se extrae la fecha
     * @return Date fecha que representa el valor de ultimasincronizacion para la entidad
     *         suministrada
     * @throws ParseException
     */
    public Date getLastConciliationSync(String entity) throws ParseException {
        Sincronizacion result = new Sincronizacion();
        EntityManager em = emf.createEntityManager();
        Date fecha = null;
        try {
            fecha = new Date();
            result = this.getConciliationEntity(entity, em);
            fecha = AbstractJPAController.sdf.parse(result.getUltimasincronizacion().toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.clear();
            em.close();
        }
        return fecha;
    }

    /**
     * 
     * @param entity entidad a actualizar, se compara contra la columna nombre
     * @param lastDate fecha a la que se actualiza el valor de ultimasincronizacion
     * @return int la cantidad de registros actualizados
     */
    public int updateLastConciliationSync(String entity, Date lastDate) {
        int updateCount = 0;
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Query query = em
                    .createQuery("UPDATE Sincronizacion s SET s.ultimasincronizacion = :fecha WHERE s.nombre = :nombre AND s.tipo = :tipo");
            query.setParameter("fecha", new BigDecimal(AbstractJPAController.sdf.format(lastDate)));
            query.setParameter("nombre", entity);
            query.setParameter("tipo", crjpa.SincronizacionJpaController.TIPO_CONCILIACION);
            updateCount = query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.clear();
            em.close();
        }
        return updateCount;
    }
}
