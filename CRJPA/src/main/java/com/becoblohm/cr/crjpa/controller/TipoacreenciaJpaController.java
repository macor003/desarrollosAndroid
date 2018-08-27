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
import com.becoblohm.cr.models.CreditsType;

import crjpa.Tipoacreencia;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class TipoacreenciaJpaController extends AbstractJPAController {

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
    private crjpa.TipoacreenciaJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Tipoacreencia";

    /**
     * Constructor for TipoacreenciaJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TipoacreenciaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpacontroller = new crjpa.TipoacreenciaJpaController(emf);
    }

    /**
     * Method fromJPA.
     * 
     * @param jpaTipoacreencia Tipoacreencia
     * @return CreditsType
     */
    public CreditsType fromJPA(Tipoacreencia jpaTipoacreencia) {

        CreditsType result = new CreditsType();

        result.setId(jpaTipoacreencia.getId());
        result.setDescription(jpaTipoacreencia.getDescripcion());

        return result;

    }

    /**
     * Method getCreditsTypeList.
     * 
     * @return Collection<CreditsType>
     */
    public Collection<CreditsType> getCreditsTypeList() {

        Collection<CreditsType> result = new ArrayList<CreditsType>();

        List<Tipoacreencia> creditsTypeList = jpacontroller.findTipoacreenciaEntities();

        for (Tipoacreencia jpaCreditsType : creditsTypeList) {

            CreditsType ct = fromJPA(jpaCreditsType);
            result.add(ct);

        }

        return result;

    }

}
