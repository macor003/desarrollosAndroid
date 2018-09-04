/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Service;

import crjpa.Articulo;
import crjpa.Promocion;
import crjpa.Transaccion;
import crjpa.Transaccionarticulo;
import crjpa.TransaccionarticuloJpaController;
import crjpa.Transaccionarticuloservicio;
import crjpa.Unidadventa;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TransaccionArticuloJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private final EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Transaccionarticulo";

    /**
     * Field jpaController.
     */
    private final crjpa.TransaccionarticuloJpaController jpaController;

    /**
     * Constructor for TransaccionArticuloJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TransaccionArticuloJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        jpaController = new TransaccionarticuloJpaController(this.emf);
    }

    /**
     * Method toJPA.
     * 
     * @param article Article
     * @param transaction Transaccion
     * @return Transaccionarticulo
     */
    public Transaccionarticulo toJPA(Article article, Transaccion transaction) {
        TasaimpuestovalorJpaController taxValuecontroller = new TasaimpuestovalorJpaController(this.emf);
        TipodescuentoJpaController tipoDescuentoJpaController = new TipodescuentoJpaController(this.emf);
        // PromocionJpaController promJpaController=new
        // PromocionJpaController(this.emf);
        // UnidadventaJpaController saleUnitjpaController=new
        // UnidadventaJpaController(this.emf);
        TransaccionarticuloServicioJpaController serviceController = new TransaccionarticuloServicioJpaController(
                this.emf);

        Transaccionarticulo transArt;
        transArt = new Transaccionarticulo();
        transArt.setId(article.getTransactionArticleId());
        if (transaction.getNumero() == 0) {
            transArt.setId(-1l);
        }
        transArt.setCantidad(article.getItems().getValue());
        transArt.setCaptura(null);// TODO definir si la definicion de captura
                                  // del articulo se va a implementar
        transArt.setCondicionEntrega(article.getDeliveryCondition().getType());
        transArt.setFechaInicioGarantia(new Date());
        transArt.setFechaVenta(new Date());
        Articulo tmpArt = new Articulo();
        tmpArt.setId(article.getId());
        transArt.setIdArticulo(tmpArt);
        if (article.getSelectedPromo() != null) {
            Promocion selectedPromo = new Promocion();
            selectedPromo.setId(article.getSelectedPromo().getId());
            transArt.setIdPromocion(selectedPromo);
        }
        // transArt.setIdTasaimpuestovalor(taxValuecontroller.findTasaivaValorByDateAndRate(article.getTax().getTaxDate(),
        // article.getTax().getTaxRate().getValue()));
        transArt.setIdTasaimpuestovalor(taxValuecontroller.findTasaivaValorByidTipo(article.getTax().getId()));
        transArt.setIdTipodescuento(tipoDescuentoJpaController.toJPA(article.getDiscountType()));
        transArt.setIdTransaccion(transaction);
        Unidadventa saleUnit = new Unidadventa();
        saleUnit.setId(article.getSaleUnit().getId());
        transArt.setIdUnidadventa(saleUnit);
        ArrayList<Transaccionarticuloservicio> servicesList = new ArrayList<Transaccionarticuloservicio>();
        for (Entry<Long, Service> entry : article.getServices().entrySet()) {
            Transaccionarticuloservicio tmp = serviceController.toJPA(entry.getValue());
            if (tmp != null) {
                if (transArt.getId() < 0) {
                    tmp.setId(null);
                }
                tmp.setIdTransaccionarticulo(transArt);
                servicesList.add(tmp);
            }
        }
        transArt.setTransaccionarticuloservicioList(servicesList);// TODO setear
                                                                  // los
                                                                  // servicios

        transArt.setMontoImpuesto(article.getTaxAmount().getValue());
        transArt.setTotalRebaja(article.getTotalRebateAmount().getValue());
        transArt.setMontoUnitario(article.getOriginalItemCost().getValue());
        // transArt.setMontoBase(transArt.getMontoUnitario().subtract(transArt.getTotalRebaja()));
        transArt.setMontoBase(article.getItemCost().getValue());
        transArt.setTotalArticulo(article.getTotalCost().getValue());
        transArt.setTotalBase(article.getNonTaxedTotalCost().getValue());
        transArt.setTotalImpuesto(article.getTotalTaxAmount().getValue());
        transArt.setEstasincronizado("N");
        if (article.getCaptureMode() == null) {
            transArt.setCaptura(Article.manualCapture);
        } else {
            transArt.setCaptura(article.getCaptureMode());
        }
        // transArt.setMontoFinal(article.getTotalCost().getValue());
        // transArt.setMontoUnidadOriginal(article.getItemCost().getValue());
        // transArt.setMontoFinalSinimpuesto(article.getTotalCost().getValue());
        return transArt;
    }

    // private static Unidadventa findUnidadVenta(Articulo art,long code){
    // Unidadventa saleUnit=new Unidadventa();
    // for(Articulounidadventa obj:art.getArticulounidadventaList()){
    // if(obj.getIdUnidadventa().getId()==code){
    // saleUnit=obj.getIdUnidadventa();
    // break;
    // }
    // }
    // return saleUnit;
    // }
    //
    // private static Tasaimpuestovalor findTasaIvaValor(Articulo art,long
    // code){
    // Tasaimpuestovalor tax=new Tasaimpuestovalor();
    // for(Tasaimpuestovalor
    // obj:art.getIdTasaimpuestotipo().getTasaimpuestovalorList()){
    // if(obj.getId()==code){
    // tax=obj;
    // break;
    // }
    // }
    // return tax;
    // }

    // private static Promocion findPromocion(Articulo art,long code){
    // Promocion prom=new Promocion();
    // for(ArticuloPromocion obj:art.getArticuloPromocionList()){
    // if(obj.getId()==code){
    // prom=obj.getIdPromocion();
    // break;
    // }
    // }
    // return tax;
    // }

    // public void createOrEdit(Transaccionarticulo transaccionArticulo) throws
    // IllegalOrphanException, NonexistentEntityException, Exception{
    // if(transaccionArticulo.getId()<0){
    // this.create(transaccionArticulo);
    // }else{
    // this.edit(transaccionArticulo);
    // }
    // }

    // private void edit(Transaccionarticulo transaccionArticulo){
    // try {
    // this.jpaController.edit(transaccionArticulo);
    // } catch (NonexistentEntityException e) {
    // e.printStackTrace();// TODO manejar excepciones
    // } catch (Exception e) {
    // e.printStackTrace();// TODO manejar excepciones
    // }
    // }

    // private void create(Transaccionarticulo transaccionArticulo){
    // try {
    // this.jpaController.create(transaccionArticulo);
    // } catch (PreexistingEntityException e) {
    // e.printStackTrace(); // TODO manejar excepciones
    // } catch (Exception e) {
    // e.printStackTrace(); // TODO manejar excepciones
    // }
    // }

    // public void destroy(long articleCode){
    // try {
    // this.jpaController.destroy(articleCode);
    // } catch (NonexistentEntityException e) {
    // e.printStackTrace();// TODO manejar excepciones
    // } catch (IllegalOrphanException e) {
    // e.printStackTrace();
    // }
    // }

}
