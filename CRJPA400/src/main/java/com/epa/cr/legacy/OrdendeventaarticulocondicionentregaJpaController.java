/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.OrderArticleDeliveryCondition;

import crjpa400.Ordendeventaarticulocondicionentrega;

/**
 */
public class OrdendeventaarticulocondicionentregaJpaController extends AbstractJPAController {

    /**
     * 
     */
    private static String entityName = "Ordendeventaarituclocondicionentrega";

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Constructor for OrdendeventaarticulocondicionentregaJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public OrdendeventaarticulocondicionentregaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.emf = emf;
    }

    /**
     * Method fromJpa.
     * 
     * @param ordendeventaaritculocondicionentrega Ordendeventaarticulocondicionentrega
     * @return OrderArticleDeliveryCondition
     */
    public OrderArticleDeliveryCondition fromJpa(Ordendeventaarticulocondicionentrega ordendeventaaritculocondicionentrega) {
        OrderArticleDeliveryCondition result = new OrderArticleDeliveryCondition();

        CondicionentregaJpaController condicionEntregajpa = new CondicionentregaJpaController(emf);
        result.setDeliveryCondition(condicionEntregajpa
                .fromJPA(ordendeventaaritculocondicionentrega.getIdCondicionentrega()));
        OrdendeventaArticuloJpaController ordendeventaarticuloJpa = new OrdendeventaArticuloJpaController(emf);
        // result.setOrderArticle(ordendeventaarticuloJpa.fromJpa(ordendeventaaritculocondicionentrega.getIdOrdendeventaarticulo()));
        result.setClientIdNumber(ordendeventaaritculocondicionentrega.getNumeroIdentificacionCliente());
        result.setClientName(ordendeventaaritculocondicionentrega.getNombreCliente());
        result.setClientPhone(ordendeventaaritculocondicionentrega.getTelefono());
        result.setClientAddress(ordendeventaaritculocondicionentrega.getDireccion());
        return result;

    }

    /**
     * Method toJpa.
     * 
     * @param ordenDeVentaArticuloDeliveryCondition OrderArticleDeliveryCondition
     * @return Ordendeventaarticulocondicionentrega
     */
    public Ordendeventaarticulocondicionentrega toJpa(OrderArticleDeliveryCondition ordenDeVentaArticuloDeliveryCondition) {

        Ordendeventaarticulocondicionentrega result = new Ordendeventaarticulocondicionentrega();

        OrdendeventaArticuloJpaController ordendeventaarticuloJpa = new OrdendeventaArticuloJpaController(emf);
        result.setIdOrdendeventaarticulo(ordendeventaarticuloJpa
                .toJpa(ordenDeVentaArticuloDeliveryCondition.getOrderArticle(), false));
        CondicionentregaJpaController condicionEntregajpa = new CondicionentregaJpaController(emf);
        result.setIdCondicionentrega(condicionEntregajpa
                .toJPA(ordenDeVentaArticuloDeliveryCondition.getDeliveryCondition()));
        result.setNumeroIdentificacionCliente(ordenDeVentaArticuloDeliveryCondition.getClientIdNumber());
        result.setNombreCliente(ordenDeVentaArticuloDeliveryCondition.getClientName());
        result.setTelefono(ordenDeVentaArticuloDeliveryCondition.getClientPhone());
        result.setDireccion(ordenDeVentaArticuloDeliveryCondition.getClientAddress());

        return result;

    }

}
