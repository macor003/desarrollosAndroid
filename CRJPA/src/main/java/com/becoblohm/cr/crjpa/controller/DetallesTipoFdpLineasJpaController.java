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
import com.becoblohm.cr.models.CommitmentPaymentDetailsLines;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Detalletipoformadepagolinea;
import crjpa.DetalletipoformadepagolineaJpaController;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class DetallesTipoFdpLineasJpaController extends AbstractJPAController {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    // private final EntityManagerFactory emf;
    /**
     * Field jpaController.
     */
    private crjpa.DetalletipoformadepagolineaJpaController jpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Detalletipoformadepagolinea";

    /**
     * Constructor for DetallesTipoFdpLineasJpaController.
     * 
     * @param p_emf EntityManagerFactory
     */
    public DetallesTipoFdpLineasJpaController(EntityManagerFactory p_emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new DetalletipoformadepagolineaJpaController(p_emf);
    }

    /**
     * Method fromJPA.
     * 
     * @param idDetailsPaymentDelivery long
     * @return Collection<CommitmentPaymentDetailsLines>
     */
    public Collection<CommitmentPaymentDetailsLines> fromJPA(long idDetailsPaymentDelivery) {
        // DetallesTipoFdpLineasJpaController controller = new
        // DetallesTipoFdpLineasJpaController(this.emf);
        CommitmentPaymentDetailsLines tmp;
        Collection<CommitmentPaymentDetailsLines> resultList = new ArrayList<CommitmentPaymentDetailsLines>();
        List<Detalletipoformadepagolinea> jpaList = findEntitiesByIdDetalle(idDetailsPaymentDelivery);

        for (Iterator iterator = jpaList.iterator(); iterator.hasNext();) {
            Detalletipoformadepagolinea detallesTipoFpdLineas = (Detalletipoformadepagolinea) iterator.next();
            tmp = new CommitmentPaymentDetailsLines(detallesTipoFpdLineas.getId(),
                    detallesTipoFpdLineas.getDescripcion(),
                    new CRBigDecimal(detallesTipoFpdLineas.getMonto().doubleValue()));
            resultList.add(tmp);
        }

        return resultList;
    }

    /**
     * Method fromJPA.
     * 
     * @param detallesTipoFpdLineas Detalletipoformadepagolinea
     * @return CommitmentPaymentDetailsLines
     */
    public CommitmentPaymentDetailsLines fromJPA(Detalletipoformadepagolinea detallesTipoFpdLineas) {

        CommitmentPaymentDetailsLines tmp = new CommitmentPaymentDetailsLines(detallesTipoFpdLineas.getId(),
                detallesTipoFpdLineas.getDescripcion(),
                new CRBigDecimal(detallesTipoFpdLineas.getMonto().doubleValue()));
        return tmp;
    }

    /**
     * Method findEntitiesByIdDetalle.
     * 
     * @param idTipoFDP long
     * @return List<Detalletipoformadepagolinea>
     */
    public List<Detalletipoformadepagolinea> findEntitiesByIdDetalle(long idTipoFDP) {
        EntityManager em = this.jpaController.getEntityManager();
        try {

            Query query = em
                    .createQuery("SELECT d FROM Detalletipoformadepagolinea d WHERE d.idDtfpd.id = :idTipoFDP");
            query.setParameter("idTipoFDP", idTipoFDP);
            List list = query.getResultList();
            return list;
        } finally {
            em.close();
        }
    }
}
