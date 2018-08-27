/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.DeliveryCondition;
import com.becoblohm.cr.models.Service;
import com.becoblohm.cr.models.Tax;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Articulo;
import crjpa.Articulounidadventa;
import crjpa.Servicio;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ArticuloJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field articleJpaController.
     */
    private crjpa.ArticuloJpaController articleJpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Articulo";

    /**
     * Constructor for ArticuloJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public ArticuloJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        this.articleJpaController = new crjpa.ArticuloJpaController(emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method fromJPA.
     * 
     * @param jpaArticle crjpa.Transaccionarticulo
     * @return Article
     */
    public Article fromJPA(crjpa.Transaccionarticulo jpaArticle) {
        Article article = fromJPA(jpaArticle.getIdArticulo());
        TipodescuentoJpaController tipoDescuentoJpaController = new TipodescuentoJpaController(this.emf);
        TasaimpuestotipoJpaController taxController = new TasaimpuestotipoJpaController(this.emf);
        article.setDeliveryCondition(DeliveryCondition.getDeliveryConditionsHash()
                .get(String.valueOf(jpaArticle.getCondicionEntrega())));
        article.setDiscountType(tipoDescuentoJpaController.fromJPA(jpaArticle.getIdTipodescuento()));
        article.setItems(new CRBigDecimal(jpaArticle.getCantidad().doubleValue()));
        article.setOriginalItemCost(new CRBigDecimal(jpaArticle.getMontoUnitario().doubleValue()));
        article.setItemCost(new CRBigDecimal(jpaArticle.getMontoBase().doubleValue()));

        Tax tax = taxController.fromJPA(jpaArticle.getIdTasaimpuestovalor().getIdTasaimpuestotipo());
        article.setTax(tax);

        if (jpaArticle.getId() > 0) {
            article.setTransactionArticleId(jpaArticle.getId());
        }

        return article;
    }

    /**
     * Method fromJPA.
     * 
     * @param jpaArticle crjpa.Articulo
     * @return Article
     */
    public Article fromJPA(crjpa.Articulo jpaArticle) {

        LineaJpaController lineController = new LineaJpaController(this.emf);
        // PromocionArticuloJpaController promoController=new
        // PromocionArticuloJpaController(this.emf);
        ServicioJpaController serviceController = new ServicioJpaController(this.emf);
        TasaimpuestotipoJpaController taxController = new TasaimpuestotipoJpaController(this.emf);
        // FamiliaJpaController familyController=new
        // FamiliaJpaController(this.emf);
        ArticulounidadventaJpaController saleUnitController = new ArticulounidadventaJpaController(this.emf);

        Article art = new Article();
        art.setId(jpaArticle.getId());
        art.setCode(String.valueOf(jpaArticle.getCodigo()));

        if (jpaArticle.getIdTasaimpuestotipo() == null) {
            art.setTax(null);
        } else {
            art.setTax(taxController.fromJPA(jpaArticle.getIdTasaimpuestotipo()));
        }

        Articulounidadventa saleUnit = jpaArticle.getArticulounidadventaList()
                .get(jpaArticle.getArticulounidadventaList().size() - 1);
        art.setCode(String.valueOf(jpaArticle.getCodigo()));
        art.setName(jpaArticle.getNombre());
        if (jpaArticle.getIdTasaimpuestotipo() == null) {
            art.setTax(null);
            art.setOriginalItemTax(null);
        } else {
            art.setTax(taxController.fromJPA(jpaArticle.getIdTasaimpuestotipo()));
            art.setOriginalItemTax(taxController.fromJPA(jpaArticle.getIdTasaimpuestotipo()));
        }

        art.setItemCost(new CRBigDecimal(saleUnit.getPrecio().doubleValue()));
        art.setSaleUnit(saleUnitController.fromJPA(saleUnit));
        art.setOriginalItemCost(art.getSaleUnit().getPrice());
        art.setItems(art.getSaleUnit().getDefaultQuantity());
        art.setLine(lineController.fromJPA(jpaArticle.getIdLinea()));
        HashMap<Long, Service> artServices = new HashMap<Long, Service>();
        for (Servicio jpaService : jpaArticle.getServicioList()) {
            artServices.put(jpaService.getId(), serviceController.fromJPA(jpaService));
        }
        art.setServices(artServices);
        PromocionarticuloJpaController promotionArticle = new PromocionarticuloJpaController(this.emf);
        art.setPromoArticle(promotionArticle.fromJPA(jpaArticle.getPromocionarticuloList()));

        art.setAmountMaxPeriod(new CRBigDecimal(jpaArticle.getCantidadMaximaPeriodo().doubleValue()));
        art.setAmountMaxTransaction(new CRBigDecimal(jpaArticle.getCantidadMaximaTransaccion().doubleValue()));
        art.setBuyPeriod(jpaArticle.getPeriodoCompra());
        return art;
    }

    /**
     * Method findArticle.
     * 
     * @param codigo String
     * @return Article
     */
    public Article findArticle(String codigo, boolean onlyActive) {
        System.out.println("com.becoblohm.cr.crjpa.controller.ArticuloJpaController");
        System.err.println("c.b.c.c.c.ArticuloJpaController - Article code to find: " + codigo);
        Article art = null;
        System.err.println("c.b.c.c.c.ArticuloJpaController - Searching article by external code: " + codigo);
        ArticulocodigoexternoJpaController externalCodeJpaController = new ArticulocodigoexternoJpaController(
                this.emf);
        Articulo tmp = externalCodeJpaController.findArticuloEntitiesByCodigoext(codigo, onlyActive);
        if (tmp == null) {
            System.out
                    .println("c.b.c.c.c.ArticuloJpaController - External code not found. Searching article by internal code: "
                            + codigo);
            if (StringUtils.isNumeric(codigo)) {
                Articulo jpaArticle = findJpaArticle(Long.valueOf(codigo), onlyActive);
                if (jpaArticle != null && jpaArticle.getIdArticulocategorizado() != null) {
                    jpaArticle = findCategorizedArticle(jpaArticle);
                    art = fromJPA(jpaArticle);
                } else if (jpaArticle != null) {
                    art = fromJPA(jpaArticle);
                }
            } else {
                System.out.println("c.b.c.c.c.ArticuloJpaController - Non numeric internal code:" + codigo);
            }
        } else {
            System.out.println("c.b.c.c.c.ArticuloJpaController - External code: " + codigo + " ----> " + tmp);
            tmp = findCategorizedArticle(tmp);
            art = fromJPA(tmp);
        }

        if (art != null && art.getItemCost().doubleValue() <= 0) {
            art = null;
        }
        return art;
    }

    /**
     * 
     * @param String codigo internal article code
     * @param boolean onlyActive Search only active articles
     * @return Article art
     */
    public Article findArticleByInternalCode(String codigo, boolean onlyActive) {
        // public Article findArticle(long codigo){
        System.out.println("com.becoblohm.cr.crjpa.controller.ArticuloJpaController");
        System.out.println("c.b.c.c.c.ArticuloJpaController - Article code to find: " + codigo);
        Article art = null;

        System.out.println("c.b.c.c.c.ArticuloJpaController - Searching article by internal code: " + codigo);
        if (StringUtils.isNumeric(codigo)) {
            Articulo jpaArticle = findJpaArticle(Long.valueOf(codigo), onlyActive);
            if (jpaArticle != null && jpaArticle.getIdArticulocategorizado() != null) {
                jpaArticle = findCategorizedArticle(jpaArticle);
                art = fromJPA(jpaArticle);
            } else if (jpaArticle != null) {
                art = fromJPA(jpaArticle);
            }
        } else {
            System.out.println("c.b.c.c.c.ArticuloJpaController - ERROR - Non numeric internal code:" + codigo);
        }

        if (art != null && art.getItemCost().doubleValue() <= 0) {
            art = null;
        }
        return art;
    }

    public Article findArticle(String codigo) {
        return findArticle(codigo, true);
    }

    /**
     * Method findCategorizedArticle.
     * 
     * @param art Articulo
     * @return Articulo
     */
    private Articulo findCategorizedArticle(Articulo art) {
        Articulo categorizedArticle = null;
        if (art.getIdArticulocategorizado() != null) {
            categorizedArticle = findCategorizedArticle(art.getIdArticulocategorizado());
        } else {
            categorizedArticle = art;
        }
        return categorizedArticle;
    }

    /**
     * Method findArticuloByCode.
     * 
     * @param codigo long
     * @return Article
     */
    public Article findArticuloByCode(long codigo, boolean onlyActive) {
        Articulo tmp = findJpaArticle(codigo, onlyActive);
        Article art = null;
        if (tmp != null) {
            art = fromJPA(tmp);
        }
        return art;
    }

    public Article findArticuloByCode(long codigo) {
        return findArticuloByCode(codigo, true);
    }

    /**
     * Method findJpaArticle.
     * 
     * @param code long
     * @return Articulo
     */
    private Articulo findJpaArticle(long code, boolean onlyActive) {
        EntityManager em = articleJpaController.getEntityManager();
        Articulo singleResult = null;
        try {
            Query query;
            if (onlyActive) {
                query = em
                        .createQuery("SELECT a FROM Articulo a WHERE a.codigo = :codigo and a.estaactivo=:estado");
                query.setParameter("codigo", code);
                query.setParameter("estado", ActiveValues.S.getString());
            } else {
                query = em.createQuery("SELECT a FROM Articulo a WHERE a.codigo = :codigo");
                query.setParameter("codigo", code);
            }
            query.setMaxResults(1);
            singleResult = (Articulo) query.getSingleResult();
        } catch (Exception ex) {
            singleResult = null;
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
        return singleResult;
    }

}
