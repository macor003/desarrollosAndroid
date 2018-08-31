/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CommitmentPaymentDetails;
import com.becoblohm.cr.models.CommitmentPaymentDetailsLines;
import com.becoblohm.cr.models.PaymentMethod;

import crjpa.Detalletipoformadepago;
import crjpa.Formadepago;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class DetallesTipoFdpJpaController extends AbstractJPAController {// extends
                                                                         // crjpa.DetallesTipoFdpJpaController
                                                                         // {

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
    private crjpa.DetalletipoformadepagoJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Detalletipoformadepago";

    /**
     * Constructor for DetallesTipoFdpJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public DetallesTipoFdpJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpacontroller = new crjpa.DetalletipoformadepagoJpaController(emf);
    }

    /*
     * Implementaciones propias
     */
    /**
     * Method fromJPA.
     * 
     * @param payType PaymentMethod
     * @return Collection<CommitmentPaymentDetails>
     */
    public Collection<CommitmentPaymentDetails> fromJPA(PaymentMethod payType) {
        Collection<CommitmentPaymentDetails> paymentReturnList = new ArrayList<CommitmentPaymentDetails>();
        CommitmentPaymentDetails detailsPaymentDelivery;
        Collection<CommitmentPaymentDetailsLines> listaDetalles = new ArrayList<CommitmentPaymentDetailsLines>();
        DetallesTipoFdpLineasJpaController linecontroller = new DetallesTipoFdpLineasJpaController(this.emf);

        List<Detalletipoformadepago> lista = findEntitiesByTipoFDP(payType.getId());
        for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
            Detalletipoformadepago tmp = (Detalletipoformadepago) iterator.next();

            detailsPaymentDelivery = new CommitmentPaymentDetails();
            detailsPaymentDelivery.setId(tmp.getId());
            detailsPaymentDelivery.setDescripcion(tmp.getDescripcion());
            detailsPaymentDelivery.setEstaactivo(tmp.getEstaactivo());
            detailsPaymentDelivery.setTipoFormadepago(payType);
            /*
             * Ahora le cargo las lineas desde la tabla DetallesTipoFpdLineas
             */

            for (Iterator iterator2 = tmp.getDetalletipoformadepagolineaList().iterator(); iterator2.hasNext();) {
                crjpa.Detalletipoformadepagolinea detalletipoformadepago = (crjpa.Detalletipoformadepagolinea) iterator2
                        .next();
                listaDetalles.add(linecontroller.fromJPA(detalletipoformadepago));

            }

            // listaDetalles =
            // linecontroller.fromJPA(detailsPaymentDelivery.getId());
            // if (listaDetalles.size()==0) {
            // for (int i = 1; i <= 10; i++) {
            // CommitmentPaymentDetailsLines tmpDetail = new
            // CommitmentPaymentDetailsLines((long)i+1, "Detalle_"+i,
            // CRBigDecimal.ZERO);
            // listaDetalles.add(tmpDetail);
            // }
            // }
            detailsPaymentDelivery.setDetailsPDLines(listaDetalles);

            paymentReturnList.add(detailsPaymentDelivery);
        }

        return paymentReturnList;
    }

    /**
     * Method findEntitiesByTipoFDP.
     * 
     * @param tipoFDP long
     * @return List<Detalletipoformadepago>
     */
    public List<Detalletipoformadepago> findEntitiesByTipoFDP(long tipoFDP) {
        EntityManager em = jpacontroller.getEntityManager();
        try {
            // Query query =
            // em.createNamedQuery("Detalletipoformadepago.findByTipoFormaDePago");
            // query.setParameter("tipoFormaDePago",tipoFDP);
            // List list = query.getResultList();

            Query query = em.createQuery("SELECT t FROM Detalletipoformadepago t WHERE t.idFormadepago= :idFDP ");

            query.setParameter("idFDP", new Formadepago(tipoFDP));

            List list = query.getResultList();

            return list;
        } finally {
            em.close();
        }
    }
}
