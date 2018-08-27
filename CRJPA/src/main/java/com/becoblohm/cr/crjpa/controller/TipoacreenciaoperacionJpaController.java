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
import com.becoblohm.cr.models.CreditsOperationType;
import com.becoblohm.cr.models.CreditsType;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Tipoacreenciaoperacion;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TipoacreenciaoperacionJpaController extends AbstractJPAController {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field jpacontroller.
     */
    private crjpa.TipoacreenciaoperacionJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Tipoacreenciaoperacion";

    /**
     * Constructor for TipoacreenciaoperacionJpaController.
     * 
     * @param p_emf EntityManagerFactory
     */
    public TipoacreenciaoperacionJpaController(EntityManagerFactory p_emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = p_emf;
        this.jpacontroller = new crjpa.TipoacreenciaoperacionJpaController(emf);
    }

    /**
     * Method fromJPA.
     * 
     * @param jpaTipoacreenciaoperacion Tipoacreenciaoperacion
     * @return CreditsOperationType
     */
    public CreditsOperationType fromJPA(Tipoacreenciaoperacion jpaTipoacreenciaoperacion) {

        CreditsOperationType result = new CreditsOperationType();

        result.setId(jpaTipoacreenciaoperacion.getId());
        result.setCreditsOperation(new CreditsOperation(
                jpaTipoacreenciaoperacion.getIdOperacionacreencia().getId(),
                jpaTipoacreenciaoperacion.getIdOperacionacreencia().getDescripcion()));
        result.setCreditsType(new CreditsType(jpaTipoacreenciaoperacion.getIdTipoacreencia().getId(),
                jpaTipoacreenciaoperacion.getIdTipoacreencia().getDescripcion()));
        result.setVisible(ActiveValues.valueOf(jpaTipoacreenciaoperacion.getEsvisible()).getValue());
        result.setRequirePassword(ActiveValues
                .valueOf(jpaTipoacreenciaoperacion.getIdTipoacreencia().getRequiereclave()).getValue());

        return result;

    }

    /**
     * Method getCreditsOperationTypeList.
     * 
     * @return Collection<CreditsOperationType>
     */
    public Collection<CreditsOperationType> getCreditsOperationTypeList() {

        Collection<CreditsOperationType> result = new ArrayList<CreditsOperationType>();

        List<Tipoacreenciaoperacion> creditsOperationTypeList = jpacontroller.findTipoacreenciaoperacionEntities();

        for (Tipoacreenciaoperacion jpaCreditsOperationType : creditsOperationTypeList) {

            CreditsOperationType co = fromJPA(jpaCreditsOperationType);
            result.add(co);

        }

        return result;

    }

    /**
     * Method findTipoacreenciasoperacion.
     * 
     * @param creditsOperationTypeId int
     * @return CreditsOperationType
     */
    public CreditsOperationType findTipoacreenciasoperacion(int creditsOperationTypeId) {

        return fromJPA(jpacontroller
                .findTipoacreenciaoperacion(Integer.valueOf(creditsOperationTypeId).longValue()));
    }

}
