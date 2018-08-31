/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CategoryLineRetention;
import com.becoblohm.cr.models.PaymentMethod;
import com.becoblohm.cr.models.Retention;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Categorialinearetencion;
import crjpa.Porcentajeimpuestoretencion;

/**
 * @version $Revision: 1.0 $
 */
public class PorcentajeimpuestoretencionJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.PorcentajeimpuestoretencionJpaController jpacontroller;

    private EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Porcentajeimpuestoretencion";

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for PorcentajeimpuestoretencionJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public PorcentajeimpuestoretencionJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        this.jpacontroller = new crjpa.PorcentajeimpuestoretencionJpaController(emf);
    }

    /**
     * Method findPorcImpRetencion.
     * 
     * @param value Long
     * @return Retention
     */
    public Retention findPorcImpRetencionByID(Long value) {
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em.createQuery("SELECT p FROM " + entityName + " p WHERE p.id = :id");
        query.setParameter("id", value);
        try {
            return fromJPA((Porcentajeimpuestoretencion) query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * Method findByEstaActiva.
     * 
     * @param value String
     * @return List<Retention>
     */
    public List<Retention> findByEstaActiva(String value) {
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em.createNamedQuery("Porcentajeimpuestoretencion.findByEstaactivo");
        query.setParameter("estaactivo", value);
        try {
            List<Retention> result = new ArrayList<Retention>();
            List<Porcentajeimpuestoretencion> res = query.getResultList();
            for (Iterator iterator = res.iterator(); iterator.hasNext();) {
                Porcentajeimpuestoretencion porcentajeimpuestoretencion = (Porcentajeimpuestoretencion) iterator
                        .next();
                result.add(fromJPA(porcentajeimpuestoretencion));
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * Method fromJPA.
     * 
     * @param affected Object
     * @return Retention
     */
    public Retention fromJPA(Porcentajeimpuestoretencion porcRetention) {
        CategoriaLinearetencionJpaController categoryLineController = new CategoriaLinearetencionJpaController(
                this.emf);
        PaymentMethod paymentMethod = new PaymentMethod();
        Retention retention = new Retention();

        paymentMethod.setId(porcRetention.getIdFormadepago().getId().intValue());
        paymentMethod.setDescription(porcRetention.getIdFormadepago().getDescripcion());
        paymentMethod.setCode(porcRetention.getIdFormadepago().getCodigo());
        paymentMethod.setName(porcRetention.getIdFormadepago().getNombre());
        paymentMethod.setPaymentType(porcRetention.getIdFormadepago().getId().intValue());
        paymentMethod.setPaymentType(porcRetention.getIdFormadepago().getTipoFormaDePago());

        retention.setPorcemaxret(new CRBigDecimal(porcRetention.getPorcentajeMaxRetencion().doubleValue()));
        retention.setPorcenminret(new CRBigDecimal(porcRetention.getPorcentajeMinRetencion().doubleValue()));
        retention.setTipoartretencion(porcRetention.getTipoArtRetencion());
        retention.setFormadepago(paymentMethod);
        ArrayList<CategoryLineRetention> retentionLineList = new ArrayList<CategoryLineRetention>();
        for (Categorialinearetencion temp : porcRetention.getCategorialinearetencionList()) {
            retentionLineList.add(categoryLineController.fromJPA(temp));
        }
        retention.setCategoryLineRetencion(retentionLineList);

        if (porcRetention.getId() != null && porcRetention.getId() > 0) {
            retention.setId(porcRetention.getId());
        }
        retention.setGroup(porcRetention.getGrupo());

        return retention;
    }
}
