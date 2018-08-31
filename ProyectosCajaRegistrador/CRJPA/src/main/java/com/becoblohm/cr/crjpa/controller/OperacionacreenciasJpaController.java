/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CreditsOperation;

import crjpa.Operacionacreencia;
import crjpa.OperacionacreenciaJpaController;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class OperacionacreenciasJpaController extends AbstractJPAController {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field entityName.
     */
    private static String entityName = "Operacionacreencia";

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * @param p_emf
     */
    public OperacionacreenciasJpaController(EntityManagerFactory p_emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = p_emf;
    }

    /**
     * Method fromJPA.
     * 
     * @param jpaOperacionAcreencias Operacionacreencia
     * @return CreditsOperation
     */
    public CreditsOperation fromJPA(Operacionacreencia jpaOperacionAcreencias) {

        CreditsOperation result = new CreditsOperation();

        result.setId(jpaOperacionAcreencias.getId().intValue());
        result.setDescription(jpaOperacionAcreencias.getDescripcion());

        return result;

    }

    /**
     * Method getCreditsOperationList.
     * 
     * @return Collection<CreditsOperation>
     */
    public Collection<CreditsOperation> getCreditsOperationList() {

        Collection<CreditsOperation> result = new ArrayList<CreditsOperation>();

        EntityManagerFactory emf = this.emf;
        OperacionacreenciaJpaController jpaController = new OperacionacreenciaJpaController(emf);

        List<Operacionacreencia> creditsOperationList = jpaController.findOperacionacreenciaEntities();

        for (Operacionacreencia jpaCreditsOperation : creditsOperationList) {

            CreditsOperation co = fromJPA(jpaCreditsOperation);
            result.add(co);

        }

        return result;

    }
}
