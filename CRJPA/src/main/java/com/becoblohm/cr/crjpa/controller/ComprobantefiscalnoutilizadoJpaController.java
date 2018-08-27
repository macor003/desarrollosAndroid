/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.FiscalReceiptNotUsed;

import crjpa.Comprobantefiscalnoutilizado;
import crjpa.Comprobantefiscalpreimpreso;

/**
 */
public class ComprobantefiscalnoutilizadoJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.ComprobantefiscalnoutilizadoJpaController jpacontroller;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field entityName.
     */
    private static String entityName = "Comprobantefiscalnoutilizado";

    /**
     * Field emf.
     */
    EntityManagerFactory emf;

    /**
     * Constructor for ComprobantefiscalnoutilizadoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public ComprobantefiscalnoutilizadoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        jpacontroller = new crjpa.ComprobantefiscalnoutilizadoJpaController(emf);
        this.emf = emf;

    }

    /**
     * Method fromJPA.
     * 
     * @param type Comprobantefiscalnoutilizado
     * @return FiscalReceiptNotUsed
     */
    public FiscalReceiptNotUsed fromJPA(Comprobantefiscalnoutilizado type) {
        ComprobantefiscalpreimpresoJpaController jpa = new ComprobantefiscalpreimpresoJpaController(emf);
        FiscalReceiptNotUsed result = new FiscalReceiptNotUsed();
        result.setEstasincronizado(type.getEstasincronizado());
        result.setFecha(type.getFecha());
        result.setId(type.getId());
        result.setNumeroComprobante(BigInteger.valueOf(type.getNumeroComprobante()));

        return result;
    }

    /**
     * Method create.
     * 
     * @param nonUsedSerialNumber FiscalReceiptNotUsed
     * @throws JpaException
     */
    public void create(FiscalReceiptNotUsed nonUsedSerialNumber) throws JpaException {

        Comprobantefiscalnoutilizado noutilizado = toJPA(nonUsedSerialNumber);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(noutilizado);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new JpaException();
        } finally {
            if (em.isOpen()) {
                em.clear();
                em.close();
            }
        }
        nonUsedSerialNumber.setId(noutilizado.getId());

    }

    /**
     * Method toJPA.
     * 
     * @param nonUsedSerialNumber FiscalReceiptNotUsed
     * @return Comprobantefiscalnoutilizado
     */
    private Comprobantefiscalnoutilizado toJPA(FiscalReceiptNotUsed nonUsedSerialNumber) {

        Comprobantefiscalnoutilizado result = new Comprobantefiscalnoutilizado();
        ComprobantefiscalpreimpresoJpaController jpa = new ComprobantefiscalpreimpresoJpaController(emf);

        result.setIdComprobantefiscalpreimpreso(new Comprobantefiscalpreimpreso(
                nonUsedSerialNumber.getIdComprobantefiscalpreimpreso().getId()));
        result.setEstasincronizado(nonUsedSerialNumber.getEstasincronizado());
        result.setFecha(nonUsedSerialNumber.getFecha());
        result.setId(nonUsedSerialNumber.getId());
        result.setNumeroComprobante(nonUsedSerialNumber.getNumeroComprobante().longValue());

        return result;
    }

    // public SerialComprobantefiscal findSerialEntities(String serial) {
    // EntityManager em = getEntityManager();
    // try{
    // Query query =
    // em.createQuery("SELECT s FROM SerialComprobantefiscal s WHERE
    // CONCAT(s.serie,s.tipoDocumento,s.numeroDocumento) = :serial");
    // query.setParameter("serial",serial);
    // query.setMaxResults(1);
    // SerialComprobantefiscal list = (SerialComprobantefiscal)
    // query.getSingleResult();
    // return list;
    // }catch(Exception ex){
    // return null;
    // }
    // finally{
    // em.close();
    // }
    // }

}
