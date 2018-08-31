/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class SincronizacionpendientesJpaController extends AbstractJPAController {

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
    private static String entityName = "Sincronizacionpendientes";

    /**
     * Constructor for SincronizacionpendientesJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public SincronizacionpendientesJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        this.jpaController = new crjpa.SincronizacionJpaController(emf);
    }

    // public Collection<Synchronize> queryEntities() {
    // Collection<Synchronize> result = new ArrayList<Synchronize>();
    //
    // Synchronize singleResult = null;
    // List<Sincronizacion> resultList =
    // jpaController.findSincronizacionEntities();
    //
    // for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
    // Sincronizacion sincronizacion = (Sincronizacion) iterator.next();
    //
    // singleResult = fromJPA(sincronizacion);
    // result.add(singleResult);
    // }
    //
    // return result;
    // }

    // private Synchronize fromJPA(Sincronizacion sync) {
    // CRBigDecimal date = new
    // CRBigDecimal(sync.getUltimasincronizacion().doubleValue(), 3);
    // Synchronize result = new Synchronize(sync.getId(), sync.getNombre(),
    // sync.getEscarga(), sync.getEsdescarga(),
    // date);
    //
    // return result;
    // }

}
