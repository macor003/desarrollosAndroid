/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CreditsBalance;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Acreencia;
import crjpa400.Saldoacreencia;
import crjpa400.SaldoacreenciaPK;
import crjpa400.Tipoacreencia;

public class SaldoacreenciaJpaController extends AbstractJPAController {

    private EntityManagerFactory emf;

    private static String entityName = "Saldoacreencia";

    private crjpa400.SaldoacreenciaJpaController controller;

    private static final long serialVersionUID = -6498715595045980811L;

    private static final Logger logger = LoggerFactory.getLogger(SaldoacreenciaJpaController.class);

    /**
     * Constructor for SaldoacreenciaJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public SaldoacreenciaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.emf = emf;
        this.controller = new crjpa400.SaldoacreenciaJpaController(emf);
    }

    /**
     * Method findSaldoacreencia.
     * 
     * @param creditsTypeId Long
     * @param creditsId Long
     * @return CreditsBalance
     */
    public CreditsBalance findSaldoacreencia(Long creditsTypeId, Long creditsId) {

        EntityManager em = getEntityManager();
        Saldoacreencia saldo = null;
        CreditsBalance balance = null;
        logger.info("Searching credits balance type " + creditsTypeId + " for the credits ID: " + creditsId);
        try {

            Query query = em
                    .createQuery("SELECT s FROM Saldoacreencia s WHERE s.tipoacreencia.id = :tipoacreencia AND s.acreencia.id =:acreencia");
            query.setParameter("tipoacreencia", creditsTypeId);
            query.setParameter("acreencia", creditsId);
            saldo = (Saldoacreencia) query.getSingleResult();
            balance = fromJPA(saldo);

        } catch (Exception e) {
            logger.error("An error occurred trying to get the credit balance for the ID " + creditsId
                    + " and creditsTypeId " + creditsTypeId);
            logger.error(e.getMessage());
        } finally {
            em.close();
        }

        return balance;
    }

    /**
     * Method for obtaining the balance of a credit account without the casting to
     * CreditsBalance
     * 
     * @param creditsTypeId
     * @param creditsId
     * @return
     */
    public Saldoacreencia findSaldoacreenciaJPA(Long creditsTypeId, Long creditsId) {
        EntityManager em = getEntityManager();
        Saldoacreencia saldo = null;
        try {
            Query query = em
                    .createQuery("SELECT s FROM Saldoacreencia s WHERE s.tipoacreencia.id = :tipoacreencia AND s.acreencia.id =:acreencia");
            query.setParameter("tipoacreencia", creditsTypeId);
            query.setParameter("acreencia", creditsId);
            saldo = (Saldoacreencia) query.getSingleResult();

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            em.close();
        }

        return saldo;
    }

    /**
     * Method fromJPA.
     * 
     * @param saldo Saldoacreencia
     * @return CreditsBalance
     */
    protected CreditsBalance fromJPA(Saldoacreencia saldo) {
        CreditsBalance creditsBalance = new CreditsBalance();
        AcreenciaJpaController controllerAcr = new AcreenciaJpaController(emf);
        TipoacreenciaJpaController tipoAcr = new TipoacreenciaJpaController(emf);

        if (saldo != null) {
            creditsBalance.setAmount(new CRBigDecimal(saldo.getMontoDisponible().doubleValue()));
            creditsBalance.setBlocked(new CRBigDecimal(saldo.getMontoBloqueado().doubleValue()));
            if (saldo.getAcreencia() != null) {
                creditsBalance.setCreditsNumber(controllerAcr.fromJPA(saldo.getAcreencia()));
            } else {
                creditsBalance.setCreditsNumber(null);
            }
            if (saldo.getTipoacreencia() != null) {
                creditsBalance.setCreditsTypeId(tipoAcr.fromJPA(saldo.getTipoacreencia()));
            } else {
                creditsBalance.setCreditsTypeId(null);
            }
        } else {
            creditsBalance = null;
        }
        return creditsBalance;
    }

    /**
     * Method getEntityManager.
     * 
     * @return EntityManager
     */
    public EntityManager getEntityManager() {
        return this.controller.getEntityManager();
    }

    /**
     * Method create.
     * 
     * @param tmp CreditsBalance
     * @throws JpaException
     */
    public void create(CreditsBalance tmp) throws JpaException {
        Saldoacreencia saldoacreencia = toJPA(tmp);
        try {
            this.controller.create(saldoacreencia);

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new JpaException("ERROR trying to create the credits balance");
        }

    }

    /**
     * Method createjpa.
     * 
     * @param tmp Saldoacreencia
     * @throws JpaException
     */
    protected void createjpa(Saldoacreencia tmp) throws JpaException {
        try {
            this.controller.create(tmp);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new JpaException("ERROR trying to create the credits balance");
        }
    }

    /**
     * Method toJPA.
     * 
     * @param tmp CreditsBalance
     * @return Saldoacreencia
     */
    protected Saldoacreencia toJPA(CreditsBalance tmp) {
        AcreenciaJpaController controller = new AcreenciaJpaController(emf);
        TipoacreenciaJpaController tacontroller = new TipoacreenciaJpaController(emf);
        Tipoacreencia ta = tacontroller.toJPA(tmp.getCreditsTypeId());
        Acreencia ac = controller.toJPA(tmp.getCreditsNumber());
        Saldoacreencia saldoacreencia = new Saldoacreencia();
        saldoacreencia.setTipoacreencia(ta);
        saldoacreencia.setAcreencia(ac);
        saldoacreencia.setMontoBloqueado(tmp.getBlocked().getValue());
        saldoacreencia.setMontoDisponible(tmp.getAmount().getValue());
        saldoacreencia.setSaldoacreenciaPK(new SaldoacreenciaPK(ac.getId(), ta.getId()));

        return saldoacreencia;
    }

    /**
     * Method merge.
     * 
     * @param tmp CreditsBalance
     * @throws JpaException
     */
    public void merge(CreditsBalance tmp) throws JpaException {
        try {
            EntityManager em = this.emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(toJPA(tmp));
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new JpaException("ERROR trying to merge the credits balance");
        }
    }
}
