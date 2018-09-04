/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.ArticleLine;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Categorialineaincluye;
import crjpa.Linea;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class LineaJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.LineaJpaController jpacontroller;

    /**
     * Field emf.
     */
    EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Linea";

    /**
     * Constructor for LineaJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public LineaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpacontroller = new crjpa.LineaJpaController(emf);
        this.emf = emf;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method fromJPA.
     * 
     * @param articleLine Linea
     * @return ArticleLine
     */
    public ArticleLine fromJPA(Linea articleLine) {
        CategoriaJpaController categoryController = new CategoriaJpaController(emf);
        PromocionlineaJpaController promotionLine = new PromocionlineaJpaController(emf);
        // ArticuloJpaController artjpa = new ArticuloJpaController(emf);

        ArticleLine artLine = new ArticleLine();
        artLine.setCode(articleLine.getCodigo());
        artLine.setDate(articleLine.getFecha());
        artLine.setId(articleLine.getId());
        artLine.setIdCategory(categoryController.fromJPA(articleLine.getIdCategoria()));
        artLine.setIsActive(ActiveValues.get(articleLine.getEstaactivo()).getValue());
        artLine.setName(articleLine.getNombre());
        artLine.setRebateLine(ActiveValues.get(articleLine.getPermiterebaja()).getValue());
        artLine.setPromoLine(promotionLine.fromJPA(articleLine.getPromocionlineaList()));

        ArrayList<Long> economicActivities = new ArrayList<Long>();
        for (Categorialineaincluye includedLine : articleLine.getCategorialineaincluyeList()) {
            economicActivities.add(includedLine.getIdGiroactividadeconomica().getId());
        }
        artLine.setEconomicActivitiesList(economicActivities);

        // ArrayList<Article>articulos= new ArrayList<Article>();
        // for(Articulo art:articleLine.getArticuloList()){
        //
        // Article tmpart = new Article();
        // tmpart.setCode(Long.valueOf(art.getCodigo()).toString());
        // tmpart.setId(art.getId());
        // articulos.add(tmpart );
        // }
        // artLine.setArticuloList(articulos);

        return artLine;
    }

}
