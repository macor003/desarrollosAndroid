/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CreditsOperation;

import crjpa400.Operacionacreencia;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class OperacionacreenciaJpaController extends AbstractJPAController {

    private static String entityName = "Operacionacreencia";

    crjpa400.OperacionacreenciaJpaController controller;

    public OperacionacreenciaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.controller = new crjpa400.OperacionacreenciaJpaController(emf);
    }

    /**
     * Method findOperacionacreencias.
     * 
     * @param id Integer
     * @return Operacionacreencia
     */
    public Operacionacreencia findOperacionacreencias(Integer id) {
        return controller.findOperacionacreencia(id.longValue());
    }

    /**
     * Method findOperacionacreencia.
     * 
     * @param id Long
     * @return Operacionacreencia
     */
    public Operacionacreencia findOperacionacreencia(Long id) {
        return controller.findOperacionacreencia(id);
    }

    public List<Operacionacreencia> findOperacionacreenciaEntities() {
        return controller.findOperacionacreenciaEntities();
    }

    /**
     * Method fromJPA.
     * 
     * @param op Operacionacreencia
     * @return CreditsOperation
     */
    public CreditsOperation fromJPA(Operacionacreencia op) {

        CreditsOperation obj = new CreditsOperation();
        obj.setId(op.getId());
        obj.setDescription(op.getDescripcion());

        return obj;
    }

}
