/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.crjpa400.controller.exceptions.NonexistentEntityException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.models.OrderArticle;
import com.becoblohm.cr.models.Service;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Articulo;
import crjpa400.Articuloservicio;
import crjpa400.Articulounidadventa;
import crjpa400.Ordendeventaarticulo;
import crjpa400.OrdendeventaarticuloJpaController;
import crjpa400.Unidadventa;

/**
 */
public class OrdendeventaArticuloJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Ordendeventaarticulo";

    /**
     * Field jpaController.
     */
    private crjpa400.OrdendeventaarticuloJpaController jpaController;

    /**
     * Field estadoordendeventaJpa.
     */
    EstadoordendeventaJpaController estadoordendeventaJpa = new EstadoordendeventaJpaController(this.emf);

    /**
     * Constructor for OrdendeventaArticuloJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public OrdendeventaArticuloJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.emf = emf;
        jpaController = new OrdendeventaarticuloJpaController(this.emf);
    }

    /**
     * Method fromJpa.
     * 
     * @param ordendeventaArticulo Ordendeventaarticulo
     * @return OrderArticle
     */
    public OrderArticle fromJpa(Ordendeventaarticulo ordendeventaArticulo) {
        OrderArticle art = new OrderArticle();
        art.setActive(ActiveValues.get(String.valueOf(ordendeventaArticulo.getIdArticulo().getEstaactivo()))
                .getValue());

        art.setId(ordendeventaArticulo.getIdArticulo().getId());
        art.setTransactionArticleId(ordendeventaArticulo.getId());

        ArticulounidadventaJpaController saleUnitController = new ArticulounidadventaJpaController(this.emf);
        TasaimpuestotipoJpaController taxController = new TasaimpuestotipoJpaController(this.emf);
        LineaJpaController lineController = new LineaJpaController(this.emf);
        PromocionarticuloJpaController promotionArticle = new PromocionarticuloJpaController(this.emf);

        Articulounidadventa saleUnit = ordendeventaArticulo.getIdArticulo().getArticulounidadventaList()
                .get(ordendeventaArticulo.getIdArticulo().getArticulounidadventaList().size() - 1);

        art.setCode(String.valueOf(ordendeventaArticulo.getIdArticulo().getCodigo()));
        art.setItemCost(new CRBigDecimal(ordendeventaArticulo.getMontoUnidad().doubleValue()));
        art.setName(ordendeventaArticulo.getIdArticulo().getNombre());
        art.setTax(TasaimpuestovalorJpaController.fromJpa(ordendeventaArticulo.getIdTasaimpuestovalor()));
        art.setOriginalItemTax(art.getTax());
        art.setSaleUnit(saleUnitController.fromJPA(saleUnit));
        art.setItems(new CRBigDecimal(ordendeventaArticulo.getCantidad().doubleValue()));
        art.setLine(lineController.fromJPA(ordendeventaArticulo.getIdArticulo().getIdLinea()));
        HashMap<Long, Service> artServices = new HashMap<Long, Service>();
        for (Articuloservicio jpaService : ordendeventaArticulo.getIdArticulo().getArticuloservicioList()) {
            artServices.put(jpaService.getServicio().getId(), ServicioJpaController.fromJPA(jpaService));
        }

        art.setServices(artServices);
        art.setPromoArticle(promotionArticle
                .fromJPA(ordendeventaArticulo.getIdArticulo().getPromocionarticuloList()));
        art.setStatus(estadoordendeventaJpa.fromJPA(ordendeventaArticulo.getIdEstadoordendenventa()));

        TipodescuentoJpaController tipoDescuentoJpaController = new TipodescuentoJpaController(this.emf);
        art.setDiscountType(tipoDescuentoJpaController.fromJpa(ordendeventaArticulo.getIdTipodescuento()));
        art.setOriginalItemCost(new CRBigDecimal(ordendeventaArticulo.getMontoUnidad().doubleValue()));

        if (ordendeventaArticulo.getOrdendeventaarticulocondicionentregaList().size() > 0) {
            OrdendeventaarticulocondicionentregaJpaController oadcJpa = new OrdendeventaarticulocondicionentregaJpaController(
                    emf);
            // se toma el primero que consiga
            art.setOrderArticleDeliveryCondition(oadcJpa
                    .fromJpa(ordendeventaArticulo.getOrdendeventaarticulocondicionentregaList().get(0)));
        }

        return art;
    }

    /**
     * Method toJpa.
     * 
     * @param article Article
     * @param isCreate boolean
     * @return Ordendeventaarticulo
     */
    public Ordendeventaarticulo toJpa(Article article, boolean isCreate) {
        Ordendeventaarticulo result = new Ordendeventaarticulo();

        if (isCreate) {
            result.setId(new Long(-1));
        } else {
            result.setId(article.getTransactionArticleId());
        }

        result.setCantidad(article.getItems().getValue());
        result.setIdEstadoordendenventa(estadoordendeventaJpa.toJPA(((OrderArticle) article).getStatus()));

        if (result.getId() > 0) {
            Ordendeventaarticulo oldArticle = jpaController.findOrdendeventaarticulo(result.getId());
            if (oldArticle != null) {
                result.setIdOrdendeventa(oldArticle.getIdOrdendeventa());
            }

        }

        ArticuloJpaController articuloJpaController = new ArticuloJpaController(this.emf);
        Articulo auxArt = articuloJpaController.findArticuloByCode(Long.valueOf(article.getCode()));

        if (auxArt != null) {
            result.setIdArticulo(auxArt);
            result.setIdUnidadventa(new Unidadventa(article.getSaleUnit().getId()));
        }

        TasaimpuestovalorJpaController tasaImpuestoValorJpaController = new TasaimpuestovalorJpaController(
                this.emf);
        result.setIdTasaimpuestovalor(tasaImpuestoValorJpaController.toJpa(article.getTax()));

        TipodescuentoJpaController tipoDescuentoJpaController = new TipodescuentoJpaController(this.emf);

        result.setIdTipodescuento(tipoDescuentoJpaController.toJpa(article.getDiscountType()));

        result.setMontoFinal(article.getTotalCost().getValue());
        result.setMontoImpuesto(article.getTaxAmount().getValue());
        result.setMontoUnidad(article.getItemCost().getValue());

        return result;
    }

    /**
     * Method edit.
     * 
     * @param articuloJpa Article
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Article articuloJpa) throws NonexistentEntityException, Exception {
        jpaController.edit(toJpa(articuloJpa, false));
    }

    /**
     * Method create.
     * 
     * @param art Article
     * @param order Order
     * @throws JpaException
     */
    public void create(Article art, Order order) throws JpaException {

        Ordendeventaarticulo articuloJpa = toJpa(art, true);

        OrdendeventaJpaController ordenJpaController = new OrdendeventaJpaController(this.emf);
        articuloJpa.setIdOrdendeventa(ordenJpaController.toJpa(order));
        articuloJpa.setIdEstadoordendenventa(estadoordendeventaJpa.toJPA(order.getStatusOrder()));

        try {
            jpaController.create(articuloJpa);
        } catch (Exception e) {
            System.out.println("Error art -> " + e.getMessage());
            throw new JpaException();
        }

        art.setId(articuloJpa.getId());
    }

    public void editWithQuery(Article articuloJpa) throws NonexistentEntityException, Exception {
        jpaController.editWithQuery(toJpa(articuloJpa, false));
    }

}
