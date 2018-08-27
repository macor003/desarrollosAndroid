/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.CategoryLineRetention;

import crjpa.Articulo;
import crjpa.Categorialinearetencion;
import crjpa.CategorialinearetencionJpaController;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class CategoriaLinearetencionJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.CategorialinearetencionJpaController jpacontroller;

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field entityName.
     */
    private static String entityName = "Categorialinearetencion";

    /**
     * Field emf.
     */
    EntityManagerFactory emf;

    /**
     * Constructor for CategoriaLinearetencionJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public CategoriaLinearetencionJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpacontroller = new CategorialinearetencionJpaController(emf);
        this.emf = emf;
    }

    /**
     * Method findCatLineaRetencionByIdFDP.
     * 
     * @param value Long
     * @return List<CategoryLineRetention>
     */
    @Deprecated
    public List<CategoryLineRetention> findCatLineaRetencionByIdFDP(Long value) {
        List<Categorialinearetencion> result = null;
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em
                .createQuery("SELECT c FROM " + entityName + " c WHERE c.idFormadepago.id = :id_formadepago");
        query.setParameter("id_formadepago", value);
        List<CategoryLineRetention> res = new ArrayList<CategoryLineRetention>();
        try {
            result = query.getResultList();

            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                Categorialinearetencion categorialinearetencion = (Categorialinearetencion) iterator.next();
                CategoryLineRetention cat = fromJPA(categorialinearetencion);
                res.add(cat);
            }

            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            return res;
        } finally {
            em.close();
        }
    }

    /**
     * Method fromJPA.
     * 
     * @param categorialinearetencion Categorialinearetencion
     * @return CategoryLineRetention
     */
    public CategoryLineRetention fromJPA(Categorialinearetencion categorialinearetencion) {
        CategoriaJpaController catjpa = new CategoriaJpaController(emf);
        LineaJpaController linejpa = new LineaJpaController(emf);
        CategoryLineRetention catlineret = new CategoryLineRetention();
        catlineret.setFecha(categorialinearetencion.getFecha());
        catlineret.setId(categorialinearetencion.getId().intValue());
        // catlineret.setIdCategoria(catjpa.fromJPA(categorialinearetencion.getIdCategoria()));
        // catlineret.setIdFormadepago(FormadepagoJpaController.fromJPA(idFormadepago));
        catlineret.setIdLinea(linejpa.fromJPA(categorialinearetencion.getIdLinea()));

        return catlineret;
    }

    /**
     * Method jpaToCRArticleList.
     * 
     * @param list List<Articulo>
     * @return ArrayList<Article>
     */
    public ArrayList<Article> jpaToCRArticleList(List<Articulo> list) {
        ArrayList<Article> articleList = new ArrayList<Article>();
        LineaJpaController lineController = new LineaJpaController(this.emf);
        /*
         * Itero sobre la lista JPA
         */
        for (Iterator<Articulo> itList = list.iterator(); itList.hasNext();) {
            Articulo jpaArticle = (Articulo) itList.next();
            Article tmpArt = new Article();
            tmpArt.setLine(lineController.fromJPA(jpaArticle.getIdLinea()));
            tmpArt.setName(jpaArticle.getNombre());
            tmpArt.setCode(String.valueOf(jpaArticle.getCodigo()));
            articleList.add(tmpArt);
        }
        return articleList;
    }
}
