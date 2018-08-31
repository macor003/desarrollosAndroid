/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CreditsAccount;

import crjpa400.Acreencia;
import crjpa400.Acreenciamovimientosaldo;
import crjpa400.Cliente;

/**
 */
public class AcreenciaJpaController extends AbstractJPAController {

    private final EntityManagerFactory emf;

    private final crjpa400.AcreenciaJpaController jpaController;

    private static String entityName = "Acreencia";

    private static final Logger logger = LoggerFactory.getLogger(AcreenciaJpaController.class);

    /**
     * Constructor for AcreenciaJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public AcreenciaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.emf = emf;
        this.jpaController = new crjpa400.AcreenciaJpaController(emf);
    }

    /**
     * Method findAcreenciasByClientId.
     * 
     * @param customer String
     * @return CreditsAccount
     */
    public CreditsAccount findAcreenciasByClientId(String customer) {
        EntityManager em = emf.createEntityManager();
        Acreencia singleResult = null;
        CreditsAccount result = null;
        try {
            Query query = em
                    .createQuery("SELECT a FROM Acreencia a WHERE a.numeroIdentificacionCliente.numeroIdentificacionCliente = :numeroIdentificacionCliente");
            query.setParameter("numeroIdentificacionCliente", customer.toUpperCase());
            query.setMaxResults(1);
            singleResult = (Acreencia) query.getSingleResult();
            result = fromJPA(singleResult);
        } catch (Exception ex) {
            singleResult = null;
        } finally {
            em.close();
        }
        return result;
    }

    /**
     * Method findLastAcreencia.
     * 
     * @return Acreencia
     */
    public Acreencia findLastAcreencia() {
        EntityManager em = emf.createEntityManager();
        Acreencia singleResult = null;
        try {
            Query query = em
                    .createQuery("SELECT a FROM Acreencia a WHERE a.id = (SELECT max(b.id) from Acreencia b)");
            query.setMaxResults(1);
            singleResult = (Acreencia) query.getSingleResult();
        } catch (Exception ex) {
            singleResult = null;
        } finally {
            em.close();
        }
        return singleResult;
    }

    /**
     * Method toJPA.
     * 
     * @param account CreditsAccount
     * @return Acreencia
     */
    protected Acreencia toJPA(CreditsAccount account) {
        Acreencia acr = new Acreencia();

        if (account != null) {
            acr.setEstado('V');
            acr.setNumeroIdentificacionCliente(new Cliente(account.getClient().getIdNumber()));
            if (account.getPassword() != null) {
                acr.setPassword(account.getPassword());
            }

            if (account.getCreditsId() != null) {
                acr.setId(Long.valueOf(account.getCreditsId()));
                if (Long.parseLong(account.getCreditsId()) > 0) {
                    crjpa400.AcreenciaJpaController contr = new crjpa400.AcreenciaJpaController(emf);
                    Acreencia tmp = contr.findAcreencia(Long.parseLong(account.getCreditsId()));
                    acr.setMovimientoacreenciaList(tmp.getMovimientoacreenciaList());
                    acr.setSaldoacreenciaList(tmp.getSaldoacreenciaList());

                }
            } else {
                logger.info("The credits account does not exist");
                Acreencia last = this.findLastAcreencia();
                if (last != null) {
                    acr.setId(last.getId() + 1);
                } else {
                    acr.setId(010000001L);// FIXME verificar este dato
                }
            }

            if (account.getProcessNumber() > 0) {
                acr.setOperacion(account.getProcessNumber());
            } else {
                acr.setOperacion(0);
            }
        }

        acr.setAcreenciamovimientosaldoList(new ArrayList<Acreenciamovimientosaldo>());

        return acr;
    }

    /**
     * Method updateBalance.
     * 
     * @param numeroacr Integer
     * @param tipoid Integer
     * @throws JpaException
     */
    public void updateBalance(Integer numeroacr, Integer tipoid) throws JpaException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction entr = em.getTransaction();
        entr.begin();
        try {
            Query query = em.createNativeQuery("update CR400.SALDOACREENCIA as a "
                    + " set a.MONTO = ( select COALESCE(sum(p.MONTO),0)"
                    + "                        from CR400.MOVIMIENTOACREENCIA as m"
                    + "                            inner join CR400.FORMADEPAGO_MOVACREENCIA as p"
                    + "                                on p.IDMOVIMIENTO = m.ID"
                    + "                                and p.ESTADO = 'A'"
                    + "                        where m.NUMEROACREENCIA = a.NUMEROACREENCIA"
                    + "                        and m.IDOPERACIONTIPO IN ("
                    + "                                    select ot.ID"
                    + "                                    from CR400.TIPOAcreencia as t"
                    + "                                        inner join CR400.TIPOAcreenciaOPERACION as ot"
                    + "                                            on t.ID = ot.IDTIPO"
                    + "                                        inner join CR400.OPERACIONAcreencia as o"
                    + "                                            on o.ID = ot.IDOPERACION"
                    + "                                    where t.ID = a.IDTIPO ) ) "
                    + " where a.NUMEROACREENCIA = " + numeroacr + " AND a.IDTIPO = " + tipoid);
            int tmp = query.executeUpdate();

            Query query2 = em.createNativeQuery("update CR400.SALDOACREENCIA as a "
                    + " set a.BLOQUEADO = ( select COALESCE(sum(p.MONTO),0)"
                    + "                        from CR400.MOVIMIENTOACREENCIA as m"
                    + "                            inner join CR400.FORMADEPAGO_MOVACREENCIA as p"
                    + "                                on p.IDMOVIMIENTO = m.ID"
                    + "                                and p.ESTADO = 'B'"
                    + "                        where m.NUMEROACREENCIA = a.NUMEROACREENCIA"
                    + "                        and m.IDOPERACIONTIPO IN ("
                    + "                                    select ot.ID"
                    + "                                    from CR400.TIPOAcreencia as t"
                    + "                                        inner join CR400.TIPOAcreenciaOPERACION as ot"
                    + "                                            on t.ID = ot.IDTIPO"
                    + "                                        inner join CR400.OPERACIONAcreencia as o"
                    + "                                            on o.ID = ot.IDOPERACION"
                    + "                                    where t.ID = a.IDTIPO ) ) "
                    + " where a.NUMEROACREENCIA = " + numeroacr + " AND a.IDTIPO = " + tipoid);
            int tmp2 = query2.executeUpdate();

            entr.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entr.rollback();
            throw new JpaException();
        } finally {
            em.close();
        }

    }

    /**
     * Method fromJPA.
     * 
     * @param acr Acreencia
     * @return CreditsAccount
     */
    public CreditsAccount fromJPA(Acreencia acr) {

        ClienteJpaController clienteJpaController = new ClienteJpaController(this.emf);

        CreditsAccount account = new CreditsAccount();
        account.setChangePassword((acr.getPassword() == null || !(acr.getPassword().length() > 0)));
        account.setPassword(acr.getPassword());
        account.setCreditsId(String.valueOf(acr.getId()));
        account.setClient(clienteJpaController.fromJpa(acr.getNumeroIdentificacionCliente()));
        account.setStatus(String.valueOf(acr.getEstado()));
        account.setProcessNumber(acr.getOperacion());

        return account;
    }

    /**
     * Method findAcreenciaByClientId.
     * 
     * @param customer String
     * @param balanceId int
     * @return Acreencia
     */
    public Acreencia findAcreenciaByClientId(String customer, int balanceId) {
        EntityManager em = emf.createEntityManager();
        Acreencia singleResult = null;
        try {
            Query query = em
                    .createQuery("SELECT a FROM Acreencia a inner join Saldoacreencia b on  a.numero = b.numeroacreencia and a.idcliente = :idcliente and b.idtipo = :balanceid");
            query.setParameter("idcliente", customer);
            query.setParameter("balanceid", balanceId);
            query.setMaxResults(1);
            singleResult = (Acreencia) query.getSingleResult();
        } catch (Exception ex) {
            singleResult = null;
        } finally {
            em.close();
        }
        return singleResult;
    }

    /**
     * Method edit.
     * 
     * @param obj CreditsAccount
     * @throws JpaException
     */
    public void edit(CreditsAccount obj) throws JpaException {
        Acreencia acr = toJPA(obj);
        try {
            jpaController.edit(acr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JpaException();
        }
    }

    /**
     * Method create.
     * 
     * @param obj CreditsAccount
     * @throws JpaException
     */
    public void create(CreditsAccount obj) throws JpaException {
        Acreencia acr = toJPA(obj);
        try {
            jpaController.create(acr);
            obj.setCreditsId(String.valueOf(acr.getId()));

        } catch (Exception e) {
            e.printStackTrace();
            throw new JpaException();
        }
    }

    /**
     * Method incrementarNumeroOperacion.
     * 
     * @param acr CreditsAccount
     * @throws JpaException
     */
    @Deprecated
    public void incrementarNumeroOperacion(CreditsAccount acr, int processNumberToAdd) throws JpaException {
        int processNumber = 0;
        if (acr.getProcessNumber() != 0 || acr.getProcessNumber() > 0) {
            processNumber = acr.getProcessNumber();
        }
        acr.setProcessNumber(processNumber + processNumberToAdd);
        try {
            jpaController.edit(toJPA(acr));
        } catch (Exception e) {
            throw new JpaException(
                    "c.e.c.l.AcreenciaJpaController - System crashed trying to increasing the Credit Account number process",
                    e);
        }
    }

    /**
     * Method incrementarNumeroOperacion.
     * 
     * @param acr CreditsAccount
     * @throws JpaException
     */
    public void incrementarNumeroOperacion(CreditsAccount acr) throws JpaException {
        acr.setProcessNumber(acr.getProcessNumber() + 1);
        try {
            jpaController.edit(toJPA(acr));
        } catch (Exception e) {
            throw new JpaException(
                    "c.e.c.l.AcreenciaJpaController - System crashed trying to increasing the Credit Account number process",
                    e);
        }
    }
}
