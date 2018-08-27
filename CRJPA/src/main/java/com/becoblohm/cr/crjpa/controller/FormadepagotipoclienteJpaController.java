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
import java.util.ListIterator;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.ClientType;
import com.becoblohm.cr.models.ExonerationType;
import com.becoblohm.cr.models.PaymentMethod;
import com.becoblohm.cr.models.PaymentMethodClientType;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Formadepagotipocliente;
import crjpa.Tipocliente;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class FormadepagotipoclienteJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.FormadepagotipoclienteJpaController jpacontroller;

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
    public FormadepagotipoclienteJpaController(EntityManagerFactory p_emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        jpacontroller = new crjpa.FormadepagotipoclienteJpaController(p_emf);
        this.emf = p_emf;
    }

    private PaymentMethodClientType fromJPA(Formadepagotipocliente fdptipoclienteJPA) {
        PaymentMethodClientType paymentMethodClientType = new PaymentMethodClientType();

        paymentMethodClientType.setId(fdptipoclienteJPA.getId());
        paymentMethodClientType.setIdClientType(fdptipoclienteJPA.getIdTipocliente().getId());
        paymentMethodClientType.setIdPaymentMethod(fdptipoclienteJPA.getIdFormadepago().getId());
        paymentMethodClientType.setActive(ActiveValues.valueOf(fdptipoclienteJPA.getEstaactivo()).getValue());
        return paymentMethodClientType;
    }

    @SuppressWarnings("unchecked")
    public List<PaymentMethodClientType> findFormadepagotipoclienteByIdTipoCliente(Long id) {
        List<Formadepagotipocliente> tmp;
        List<PaymentMethodClientType> returnList = new ArrayList<PaymentMethodClientType>();
        String q = "SELECT f FROM Formadepagotipocliente f WHERE f.idTipocliente = :id_tipocliente AND f.estaactivo = :estaactivo";

        try {
            EntityManager em = jpacontroller.getEntityManager();
            Query query = em.createQuery(q);
            query.setParameter("id_tipocliente", new Tipocliente(id));
            query.setParameter("estaactivo", "S");
            tmp = (List<Formadepagotipocliente>) query.getResultList();
            for (Formadepagotipocliente value : tmp) {
                returnList.add(fromJPA(value));
            }
        } catch (javax.persistence.NoResultException ex) {
            tmp = null;
        }

        return returnList;
    }

}
