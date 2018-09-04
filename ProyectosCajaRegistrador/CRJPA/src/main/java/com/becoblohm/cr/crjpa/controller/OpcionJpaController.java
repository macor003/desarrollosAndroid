/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa.Opcion;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class OpcionJpaController extends AbstractJPAController {

    /**
     * Field jpaController.
     */
    private crjpa.OpcionJpaController jpaController;

    /**
     * Field opciones.
     */
    private static Properties opciones;

    /**
     * Field entityName.
     */
    private static String entityName = "Opcion";

    /**
     * Constructor for OpcionJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public OpcionJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.OpcionJpaController(emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method findOpcionEntities.
     * 
     * @return List<Opcion>
     */
    private List<Opcion> findOpcionEntities() {
        System.out.println("cargando opciones");
        return this.jpaController.findOpcionEntities();
    }

    /**
     * Method loadOptions.
     * 
     * @return Properties
     */
    public Properties loadOptions() {
        List<Opcion> opciones = findOpcionEntities();
        Iterator<Opcion> it = opciones.iterator();
        Properties props = new Properties();
        while (it.hasNext()) {
            Opcion op = it.next();
            props.put(op.getId().toString().trim(), op.getValor().trim());
        }
        this.setOpciones(props);
        return props;
    }

    /**
     * @param opciones the opciones to set
     */
    public static void setOpciones(Properties opciones) {
        OpcionJpaController.opciones = opciones;
    }

    /**
     * 
     * @return the opciones
     */
    public static Properties getOpciones() {
        return opciones;
    }

    /**
     * Given an option ID, it returns 'S' if is active or 'N' otherwise
     * 
     * @param id
     * @return String
     */
    public String isActive(Long id) {
        Opcion option = null;
        String active;
        try {
            option = jpaController.findOpcion(id);
            active = option.getEstaactivo();
        } catch (Exception e) {
            active = Character.toString('N');
        }
        return active;
    }

}
