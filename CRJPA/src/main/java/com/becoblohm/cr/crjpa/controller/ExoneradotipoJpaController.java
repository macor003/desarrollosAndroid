/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.ExonerationType;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Articulo;
import crjpa.Entrega;
import crjpa.Exoneradotipo;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ExoneradotipoJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.ExoneradotipoJpaController jpacontroller;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Exoneradotipo";

    /**
     * @param p_emf
     */
    public ExoneradotipoJpaController(EntityManagerFactory p_emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        jpacontroller = new crjpa.ExoneradotipoJpaController(p_emf);
        this.emf = p_emf;
    }

    /**
     * Method fromJPA.
     * 
     * @param exonerationTypeJPA Exoneradotipo
     * @return ExonerationType
     */
    public ExonerationType fromJPA(Exoneradotipo exonerationTypeJPA) {
        ArticuloJpaController artController = new ArticuloJpaController(this.emf);
        ExonerationType exonerationType = new ExonerationType();
        exonerationType.setCalculateTax(ActiveValues.get(exonerationTypeJPA.getCalculaImpuesto()).getValue());
        exonerationType.setExonerationName(exonerationTypeJPA.getDescripcion());
        exonerationType.setId(exonerationType.getId());
        exonerationType.setPartial(ActiveValues.get(exonerationTypeJPA.getAplicaParcial()).getValue());
        ArrayList<Article> articles = new ArrayList<Article>();
        for (Articulo exoneratedArticle : exonerationTypeJPA.getArticuloList()) {
            articles.add(artController.fromJPA(exoneratedArticle));
        }
        exonerationType.setExoneratedArticles(articles);
        return exonerationType;
    }

    public Exoneradotipo findExoneradoTotal() {
        Exoneradotipo tmp;

        try {
            EntityManager em = jpacontroller.getEntityManager();
            Query query = em.createNamedQuery("Exoneradotipo.findByAplicaParcial");
            query.setParameter("aplicaParcial", "N");
            query.setMaxResults(1);
            tmp = (Exoneradotipo) query.getSingleResult();

        } catch (javax.persistence.NoResultException ex) {
            tmp = null;
        }

        return tmp;
    }

}
