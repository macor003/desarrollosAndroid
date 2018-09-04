/*******************************************************************************
 * © 2012 Global Retail Information Ltd.760
 ******************************************************************************/
package com.becoblohm.cr.conf.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.becoblohm.cr.conf.Global;
import com.becoblohm.cr.conf.model.Audit;
import com.becoblohm.cr.crjpa.controller.CorrelativoJpaController;
import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa.Auditoria;

/**
 * 
 * @author Magroberth Alvarado (malvarado@intelix.biz)
 * 
 * @version $Revision: 1.0 $
 */
public class AuditoriaJpaController extends AbstractJPAController {

    private final EntityManagerFactory emf;

    private static String entityName = "Auditoria";

    public AuditoriaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
    }

    public boolean addAudit(Audit auditModel) {
        boolean result = false;

        try {
            result = create(auditModel);
        } catch (JpaException e) {
            logger.error("Error", e);
        }
        return result;
    }

    private Auditoria toJPA(Audit auditModel) {

        Auditoria auditoria = new Auditoria();

        auditoria.setClase(auditModel.getClasse());
        auditoria.setDescripcion(auditModel.getDescription());
        auditoria.setEstasincronizado("N");
        auditoria.setFecha(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
        auditoria.setHora(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
        auditoria.setFichaautorizante(auditModel.getAuthId());
        auditoria.setFichacajero(auditModel.getCashierId());
        auditoria.setIdProceso(auditModel.getProcessId());
        auditoria.setIdTabla(auditModel.getTableId());
        auditoria.setProceso(auditModel.getProcess());
        auditoria.setTabla(auditModel.getTable());
        auditoria.setTipoautorizacion(auditModel.getAuthType());
        auditoria.setTipolog(auditModel.getLogType());
        auditoria.setTipotransaccion(auditModel.getTransactionType());
        auditoria.setTienda(auditModel.getStoreNumber());
        auditoria.setCaja(auditModel.getPosNumber());

        switch (Integer.parseInt(auditModel.getPosType())) {
            case 0:
                auditoria.setTipocaja("ASISTIDA");
                break;
            case 1:
                auditoria.setTipocaja("AUTOPAGO");
                break;
            case 2:
                auditoria.setTipocaja("ASISTIDA-PILOTO");
                break;
            case 3:
                auditoria.setTipocaja("AUTOPAGO-PILOTO");
                break;
            default:
                break;
        }

        return auditoria;
    }

    public boolean create(Audit auditModel) throws JpaException {
        EntityManager em = null;
        boolean result = false;

        CorrelativoJpaController correlativoJPA = new CorrelativoJpaController(emf);
        long id_auditoria = correlativoJPA.findCorrelativoById(Global.IDAUDITORIA);

        Auditoria auditoria = toJPA(auditModel);
        auditoria.setId(id_auditoria + 1);

        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(auditoria);
            em.getTransaction().commit();

            correlativoJPA.storeCorrelativoInDb(Global.IDAUDITORIA, auditoria.getId());

            result = true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new JpaException(e.getMessage(), e);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }

        return result;
    }

    public void updateAudit(long oldTransactionId, long idtransaccioncorrelativo) throws JpaException {
        // EntityManager em = this.emf.createEntityManager();
        // EntityTransaction et = em.getTransaction();
        // try {
        // et.begin();
        // Correlativo newTransactionId = em.find(Correlativo.class,
        // idtransaccioncorrelativo);
        // Query query = em
        // .createQuery("UPDATE Auditoria SET arg1 = :newId, estasincronizado = 'N' WHERE
        // arg1 = :idTr");
        // query.setParameter("idTr", String.valueOf(oldTransactionId))
        // .setParameter("newId",
        // String.valueOf(newTransactionId.getValor())).executeUpdate();
        // et.commit();
        // } catch (Exception ex) {
        // if (et.isActive()) {
        // et.rollback();
        // }
        // throw new JpaException(ex.getMessage(), ex);
        // } finally {
        // em.close();
        // }

    }

    public long findLastAuditId(String tienda, String caja) {
        long idCorrelative;
        try {
            EntityManager em = emf.createEntityManager();
            String queryString = "SELECT MAX(a.id) FROM " + entityName
                    + " a WHERE a.caja = :caja AND a.tienda = :tienda";
            Query query = em.createQuery(queryString);
            query.setParameter("tienda", tienda);
            query.setParameter("caja", caja);
            idCorrelative = (Long) query.getSingleResult();
        } catch (NoResultException ex) {
            idCorrelative = 0;
        } catch (Exception ex) {
            idCorrelative = 0;
        }
        return idCorrelative;
    }

    // public void persistFromWaitingSale(List<Object> data, long transactionId) {
    // EntityManager em = this.emf.createEntityManager();
    // Auditoria temp;
    // EntityTransaction tr = em.getTransaction();
    // tr.begin();
    // for (Object obj : data) {
    // temp = (Auditoria) obj;
    // temp.setArg1(String.valueOf(transactionId));
    // em.persist(temp);
    // }
    // tr.commit();
    // }

    // public void updateTransactionAudit(long oldTransactionId, long
    // idtransaccioncorrelativo) throws JpaException {
    // EntityManager em = this.emf.createEntityManager();
    // EntityTransaction et = em.getTransaction();
    // try {
    // et.begin();
    // Correlativo newTransactionId = em.find(Correlativo.class,
    // idtransaccioncorrelativo);
    // Query query = em
    // .createQuery("UPDATE Auditoria SET arg1 = :newId, estasincronizado = 'N' WHERE arg1
    // = :idTr");
    // query.setParameter("idTr", String.valueOf(oldTransactionId))
    // .setParameter("newId",
    // String.valueOf(newTransactionId.getValor())).executeUpdate();
    // et.commit();
    // } catch (Exception ex) {
    // if (et.isActive()) {
    // et.rollback();
    // }
    // throw new JpaException(ex.getMessage(), ex);
    // } finally {
    // em.close();
    // }
    //
    // }

}
