/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.ArticleCategory;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Categoria;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class CategoriaJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.CategoriaJpaController jpacontroller;

    /**
     * Field emf.
     */
    EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Categoria";

    /**
     * Constructor for CategoriaJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public CategoriaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpacontroller = new crjpa.CategoriaJpaController(emf);
        this.emf = emf;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method fromJPA.
     * 
     * @param jpaCategory Categoria
     * @return ArticleCategory
     */
    public ArticleCategory fromJPA(Categoria jpaCategory) {
        ArticleCategory category = new ArticleCategory();
        PromocioncategoriaJpaController promotionCategory = new PromocioncategoriaJpaController(emf);
        category.setCode(jpaCategory.getCodigo());
        category.setDate(jpaCategory.getFecha());
        category.setId(jpaCategory.getId());
        category.setIsActive(ActiveValues.get(jpaCategory.getEstaactivo()).getValue());
        category.setName(jpaCategory.getNombre());
        category.setPromoCategory(promotionCategory.fromJPA(jpaCategory.getPromocioncategoriaList()));
        return category;
    }

}
