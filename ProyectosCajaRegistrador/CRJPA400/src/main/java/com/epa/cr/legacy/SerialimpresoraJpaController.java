/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.SerialPrint;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Serialimpresora;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class SerialimpresoraJpaController extends AbstractJPAController {

    /**
     * Field entityName.
     */
    private static String entityName = "Serialimpresora";

    /**
     * Field jpaController.
     */
    private crjpa400.SerialimpresoraJpaController jpaController;

    /**
     * Constructor for SerialimpresoraJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public SerialimpresoraJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.jpaController = new crjpa400.SerialimpresoraJpaController(emf);
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
            System.out.println(serial);
            // Query query =
            // em.createNamedQuery("Serialimpresoras.findBySerial");
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
        serialPrint.setActive(ActiveValues.get(String.valueOf(singleResult.getEstaactivo())).getValue());
        serialPrint.setId(singleResult.getId());
        serialPrint.setSerial(singleResult.getSerial());
        // Rolloauditoria
        // lastAuditRoll=singleResult.getRolloauditoriaList().get(singleResult.getRolloauditoriaList().size()-1);
        // serialPrint.setCurrentAuditRoll(lastAuditRoll);

        return serialPrint;
    }

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
