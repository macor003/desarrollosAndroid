/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa.Procesoproperty;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ProcesopropertyJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.ProcesopropertyJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Procesoproperty";

    /**
     * Constructor for ProcesopropertyJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public ProcesopropertyJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        jpacontroller = new crjpa.ProcesopropertyJpaController(emf);
    }

    /**
     * Method loadProperties.
     * 
     * @param processId Long
     * @return Properties
     */
    public Properties loadProperties(Long processId) {
        Properties props = new Properties();
        Procesoproperty process = jpacontroller.findProcesoproperty(processId);

        props.put(process.getId().toString().trim(), process.getValor().trim());

        return props;
    }

}
