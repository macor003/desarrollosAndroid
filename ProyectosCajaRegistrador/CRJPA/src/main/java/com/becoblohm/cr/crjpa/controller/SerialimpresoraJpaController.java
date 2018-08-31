/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.SerialPrint;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Serialimpresora;

/**
 */
public class SerialimpresoraJpaController extends AbstractJPAController {

    /**
     * Field jpaController.
     */
    private crjpa.SerialimpresoraJpaController jpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Serialimpresora";

    /**
     * Constructor for SerialimpresoraJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public SerialimpresoraJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.SerialimpresoraJpaController(emf);
    }

    /**
     * Method findSerialImpresoraBySerial.
     * 
     * @param serial String
     * @return SerialPrint
     */
    public SerialPrint findSerialImpresoraBySerial(String serial) {
        EntityManager em = this.jpaController.getEntityManager();
        Serialimpresora singleResult = null;
        SerialPrint serialPrint = null;
        try {
            Query query = em.createNamedQuery("Serialimpresora.findBySerial");
            query.setParameter("serial", serial);
            query.setMaxResults(1);
            singleResult = (Serialimpresora) query.getSingleResult();
            serialPrint = fromJPA(singleResult);
        } catch (Exception ex) {
            singleResult = null;
            serialPrint = null;
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return serialPrint;
    }

    /**
     * Method fromJPA.
     * 
     * @param singleResult Serialimpresora
     * @return SerialPrint
     */
    private SerialPrint fromJPA(Serialimpresora singleResult) {
        SerialPrint serialPrint = new SerialPrint();
        serialPrint.setActive(ActiveValues.get(singleResult.getEstaactivo()).getValue());
        serialPrint.setId(singleResult.getId());
        serialPrint.setSerial(singleResult.getSerial());
        serialPrint.setLastinvoice(singleResult.getUltimafactura().intValue());
        serialPrint.setLastcreditnote(singleResult.getUltimanotacredito().intValue());
        // Rolloauditoria
        // lastAuditRoll=singleResult.getRolloauditoriaList().get(singleResult.getRolloauditoriaList().size()-1);
        // serialPrint.setCurrentAuditRoll(lastAuditRoll);

        return serialPrint;
    }

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method toJPA.
     * 
     * @param tmpserial SerialPrint
     * @return Serialimpresora
     */
    public Serialimpresora toJPA(SerialPrint tmpserial) {
        Serialimpresora result = new Serialimpresora();

        result.setId(tmpserial.getId());
        result.setSerial(tmpserial.getSerial());

        return result;
    }
}
