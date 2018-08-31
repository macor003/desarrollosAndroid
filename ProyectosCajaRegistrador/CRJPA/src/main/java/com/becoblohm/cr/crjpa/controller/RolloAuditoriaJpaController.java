/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.PaperRoll;
import com.becoblohm.cr.models.SerialPrint;

import crjpa.Rolloauditoria;
import crjpa.exceptions.PreexistingEntityException;

/**
 */
public class RolloAuditoriaJpaController extends AbstractJPAController {

    /**
     * Field jpaController.
     */
    private crjpa.RolloauditoriaJpaController jpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Rolloauditoria";

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field emf.
     */
    EntityManagerFactory emf;

    /**
     * Constructor for RolloAuditoriaJpaController.
     * 
     * @param p_emf EntityManagerFactory
     */
    public RolloAuditoriaJpaController(EntityManagerFactory p_emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.RolloauditoriaJpaController(p_emf);
        this.emf = p_emf;
    }

    /**
     * Method findPromocionEntities.
     * 
     * @return List<PaperRoll>
     */
    public List<PaperRoll> findPromocionEntities() {

        List<PaperRoll> result = null;
        List<crjpa.Rolloauditoria> tmp = this.jpaController.findRolloauditoriaEntities();
        for (Iterator iterator = tmp.iterator(); iterator.hasNext();) {
            crjpa.Rolloauditoria rollo = (crjpa.Rolloauditoria) iterator.next();
            result.add(fromJPA(rollo));
        }
        return result;
    }

    /**
     * Method fromJPA.
     * 
     * @param rollo crjpa.Rolloauditoria
     * @return PaperRoll
     */
    protected PaperRoll fromJPA(crjpa.Rolloauditoria rollo) {

        SerialimpresoraJpaController serial = new SerialimpresoraJpaController(emf);
        PaperRoll paperRollMethod = new PaperRoll();
        paperRollMethod.setId(rollo.getId());
        paperRollMethod
                .setSerialPrint(serial.findSerialImpresoraBySerial(rollo.getIdSerialimpresora().getSerial()));
        /* paperRollMethod.setUser(rollo.getIdUsuario()); */
        paperRollMethod.setPos(rollo.getCaja());
        paperRollMethod.setStore(rollo.getTienda());
        paperRollMethod.setFirsTransaction(rollo.getPrimeraTransaccion());
        paperRollMethod.setLastTransaction(rollo.getUltimaTransaccion());

        return paperRollMethod;
    }

    /**
     * Method create.
     * 
     * @param rollo PaperRoll
     * @return boolean
     */
    public boolean create(PaperRoll rollo) {
        boolean value = true;
        try {
            Rolloauditoria tmp = toJPA(rollo);
            tmp.setEstasincronizado("N");
            // tmp.setId(tmp.getId()+1);
            this.jpaController.create(tmp);
        } catch (PreexistingEntityException e) {
            value = false;
            e.printStackTrace();
        } catch (Exception e) {
            value = false;
            e.printStackTrace();
        }
        return value;
    }

    /**
     * Method update.
     * 
     * @param rollo PaperRoll
     * @throws JpaException
     */
    public void update(PaperRoll rollo) throws JpaException {

        try {
            RolloAuditoriaJpaController roll = new RolloAuditoriaJpaController(emf);
            Rolloauditoria tmp = roll.findById(rollo.getId());
            tmp.setEstasincronizado("N");
            this.jpaController.edit(tmp);
        } catch (PreexistingEntityException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
            throw new JpaException();
        }
    }

    /**
     * Method toJPA.
     * 
     * @param rollo PaperRoll
     * @return Rolloauditoria
     */
    public Rolloauditoria toJPA(PaperRoll rollo) {

        Rolloauditoria rolloAuditoria = new Rolloauditoria();
        SerialimpresoraJpaController printerSerialController = new SerialimpresoraJpaController(emf);
        UsuarioJpaController user = new UsuarioJpaController(emf);
        rolloAuditoria.setCaja(rollo.getPos());
        rolloAuditoria.setFechaCambio(rollo.getChangeDate());
        // rolloAuditoria.setId(rollo.getId());
        rolloAuditoria.setId(null);

        if (rollo.getSerialPrint() != null) {
            SerialPrint tmp = printerSerialController
                    .findSerialImpresoraBySerial(rollo.getSerialPrint().getSerial());
            if (tmp == null) {
                rollo = null;
            } else {
                rollo.setSerialPrint(tmp);
            }

        }

        rolloAuditoria.setIdSerialimpresora(printerSerialController.toJPA(rollo.getSerialPrint()));
        rolloAuditoria.setIdUsuario(user.toJPA(rollo.getUser()));
        rolloAuditoria.setPrimeraTransaccion((int) rollo.getFirsTransaction());
        rolloAuditoria.setTienda((int) rollo.getStore());
        rolloAuditoria.setUltimaTransaccion((int) rollo.getLastTransaction());

        return rolloAuditoria;

    }

    /**
     * Method findLast.
     * 
     * @return PaperRoll
     */
    public PaperRoll findLast() {
        PaperRoll result = null;

        try {

            EntityManager em = jpaController.getEntityManager();
            Query query = em
                    .createQuery("select t from Rolloauditoria t where t.id = (select max(te.id) from Rolloauditoria te)");
            query.setMaxResults(1);
            Rolloauditoria tmp = (Rolloauditoria) query.getSingleResult();
            result = fromJPA(tmp);
        } catch (Exception ex) {
            result = null;
        }

        return result;
    }

    /**
     * Method findById.
     * 
     * @param id Long
     * @return Rolloauditoria
     */
    public Rolloauditoria findById(Long id) {

        return jpaController.findRolloauditoria(id);

    }
}
