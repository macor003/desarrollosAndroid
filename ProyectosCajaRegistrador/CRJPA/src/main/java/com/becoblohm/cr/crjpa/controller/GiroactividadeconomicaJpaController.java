/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.EconomicActivity;

import crjpa.Giroactividadeconomica;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class GiroactividadeconomicaJpaController extends AbstractJPAController {

    /**
     * Field jpaController.
     */
    private crjpa.GiroactividadeconomicaJpaController jpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Giroactividadeconomica";

    /**
     * Constructor for GiroactividadeconomicaJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public GiroactividadeconomicaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.GiroactividadeconomicaJpaController(emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method getGiroActividad.
     * 
     * @return ArrayList
     */
    public ArrayList getGiroActividad() {

        ArrayList strings = new ArrayList();

        List<crjpa.Giroactividadeconomica> cLista = this.jpaController.findGiroactividadeconomicaEntities();
        for (crjpa.Giroactividadeconomica transaccion : cLista) {
            strings.add(transaccion.getDescripcion());
        }
        return strings;
    }

    /**
     * Method findGiroactividadeconomicaByCode.
     * 
     * @param id long
     * @return Giroactividadeconomica
     */
    protected Giroactividadeconomica findGiroactividadeconomicaByCode(long id) {
        this.jpaController.findGiroactividadeconomica(id);
        return null;

    }

    /**
     * Method fromJPA.
     * 
     * @param idGiroactividadeconomica Giroactividadeconomica
     * @return EconomicActivity
     */
    public EconomicActivity fromJPA(Giroactividadeconomica idGiroactividadeconomica) {
        EconomicActivity result = new EconomicActivity(idGiroactividadeconomica.getId());

        result.setName(idGiroactividadeconomica.getDescripcion());
        result.setCode(idGiroactividadeconomica.getCodigo());
        result.setCategory(idGiroactividadeconomica.getCategoria());

        return result;
    }

    /**
     * Method toJPA.
     * 
     * @param idGiroactividadeconomica EconomicActivity
     * @return Giroactividadeconomica
     */
    public Giroactividadeconomica toJPA(EconomicActivity idGiroactividadeconomica) {
        Giroactividadeconomica result = new Giroactividadeconomica();

        result.setId(idGiroactividadeconomica.getId());
        result.setCategoria(idGiroactividadeconomica.getCategory());
        result.setCodigo(idGiroactividadeconomica.getCode());
        result.setDescripcion(idGiroactividadeconomica.getName());

        return result;
    }
}
