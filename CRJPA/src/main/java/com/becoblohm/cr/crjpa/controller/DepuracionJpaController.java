/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import java.util.TreeMap;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa.Depuracion;

/**
 */
public class DepuracionJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field cleanController.
     */
    private crjpa.DepuracionJpaController cleanController;

    /**
     * Field entityName.
     */
    private static String entityName = "Depuracion";

    /**
     * Constructor for DepuracionJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public DepuracionJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        cleanController = new crjpa.DepuracionJpaController(this.emf);
    }

    /**
     * Method getCleanQueries.
     * 
     * @return TreeMap<Integer,String>
     */
    public TreeMap<Integer, String> getCleanQueries() {
        TreeMap<Integer, String> queries = new TreeMap<Integer, String>();
        for (Depuracion tmp : cleanController.findDepuracionEntities()) {
            queries.put(tmp.getPrioridad(), tmp.getQuery());
        }
        return queries;
    }
}
