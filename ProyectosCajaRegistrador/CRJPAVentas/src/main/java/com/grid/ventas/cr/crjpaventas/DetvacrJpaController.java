package com.grid.ventas.cr.crjpaventas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CreditsMovement;
import com.becoblohm.cr.models.CreditsOperationType;
import com.becoblohm.cr.models.Payment;
import com.becoblohm.cr.types.CRBigDecimal;
import com.grid.ventas.cr.crjpaventas.exceptions.NonexistentEntityException;
import com.grid.ventas.cr.crjpaventas.exceptions.PreexistingEntityException;
import com.grid.ventas.cr.crjpaventassrc.Detvacr;
import com.grid.ventas.cr.crjpaventassrc.DetvacrPK;

/**
 *
 * @author eve0017909
 */
public class DetvacrJpaController extends AbstractJPAController {

    /*
     * FIELDS DECLARATION
     */
    private final EntityManagerFactory emf;

    private static String entityName = "Detvacr";

    /*
     * METHODS DECLARATION
     */
    public DetvacrJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detvacr detvacr) throws PreexistingEntityException, Exception {
        if (detvacr.getDetvacrPK() == null) {
            detvacr.setDetvacrPK(new DetvacrPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detvacr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetvacr(detvacr.getDetvacrPK()) != null) {
                throw new PreexistingEntityException("Detvacr " + detvacr + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detvacr detvacr) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detvacr = em.merge(detvacr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetvacrPK id = detvacr.getDetvacrPK();
                if (findDetvacr(id) == null) {
                    throw new NonexistentEntityException("The detvacr with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetvacrPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detvacr detvacr;
            try {
                detvacr = em.getReference(Detvacr.class, id);
                detvacr.getDetvacrPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detvacr with id " + id + " no longer exists.", enfe);
            }
            em.remove(detvacr);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detvacr> findDetvacrEntities() {
        return findDetvacrEntities(true, -1, -1);
    }

    public List<Detvacr> findDetvacrEntities(int maxResults, int firstResult) {
        return findDetvacrEntities(false, maxResults, firstResult);
    }

    private List<Detvacr> findDetvacrEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detvacr.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Detvacr findDetvacr(DetvacrPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detvacr.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetvacrCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detvacr> rt = cq.from(Detvacr.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /**
     * <p>
     * It takes the credits movement generated for CR400.MOVIMIENTOACREENCIA and translate
     * it to VENTAS.DETVACR language in order to insert it in DETVACR, eliminating the
     * need to run the SP.
     * </p>
     * <p>
     * Setting the primary key:<br />
     * - short tdaadacr (EPAStore number)<br />
     * - long numadacr (Credits Account ID)<br />
     * - Date fecadacr (Credits Movement date)<br />
     * - String clientadacr (Clients ID)<br />
     * - long numopvdacr (Operation process number)<br />
     * - long crtvovacr (Is always 1 but I don't know why)<br />
     * </p>
     * <p>
     * Setting the others DETVACR columns:<br />
     * - DetvacrPK detvacrPK (The PK set previously)<br />
     * - BigDecimal saldadacr (Credits movement amount)<br />
     * - Character edoadacr (Always A for applied)<br />
     * - Character rgadacr <br />
     * - Character confrvacr (Confirmation)<br />
     * - Character tipvdacr (Credits movement type. In Legacy are different from
     * CR400)<br />
     * - String fdpvdacr (Payment method from CR.FORMADEPAGO)<br />
     * - Character condcdacr (V: Deposit,Spending - A:Annulment - D:Withdraw)<br />
     * - long nummcpvdacr (Always 0 until a Stored Proc set this number)<br />
     * - long numtrdacr (Sale Transaction number - Zero default)<br />
     * - short cajadacr (POS number)<br />
     * - Character repldacr (Always empty)<br />
     * </p>
     * 
     * @param creditsMov - The current credit movement
     * @param payment - The current credits payment
     * @param paymentMethodCode - CR.FORMADEPAGO.CODFORMADEPAGO
     * @param paymentNumber - The payment method correlative
     * @param checkPaymentID - The ID for the check payment method from CR400.FORMADEPAGO
     * @return Detvacr movement - The credits movement turned into VENTAS.DETVACR values
     */
    public Detvacr toJPA(CreditsMovement creditsMov, Payment payment, String paymentMethodCode, int paymentNumber,
                         long checkPaymentID) {
        boolean checkPayment = false;
        DetvacrPK primaryKey = new DetvacrPK();
        Detvacr movement = new Detvacr();
        CreditsOperationType creditsOpType = creditsMov.getCreditsOperationTypeId();

        CRBigDecimal saldadacr = (creditsMov.getCreditsOperationTypeId().getCreditsOperation().getId() != 1L
                && !creditsMov.isRefundByCheck()) ? payment.getMoney().getLocalAmmount().negate()
                        : payment.getMoney().getLocalAmmount();

        checkPayment = (payment.getPayMethod().getId() == checkPaymentID);

        primaryKey.setTdaadacr((short) creditsMov.getStore());
        primaryKey.setNumadacr(Long.parseLong(creditsMov.getCreditsAccount().getCreditsId()));
        primaryKey.setFecadacr(creditsMov.getDate());
        primaryKey.setClientadacr(creditsMov.getCreditsAccount().getClient().getIdNumber());
        primaryKey.setNumopvdacr(creditsMov.getProcessId());
        primaryKey.setCrtvovacr(paymentNumber); // CR400.ACREENCIAMOVIMIENTO.CORRELATIVO.
                                                // The credits movement's payments
                                                // sequence number
        movement.setDetvacrPK(primaryKey);
        movement.setSaldadacr(saldadacr.getValue());
        movement.setEdoadacr('A');
        movement.setRgadacr((creditsMov.isRefundByCheck()) ? '3' : '2');
        movement.setConfrvacr((checkPayment) ? 'N' : 'S'); // IF
                                                           // CR400.ACREENCIAMOVIMIENTO.ESTADO
                                                           // == BLOQUEADO => There is a
                                                           // check payment method active
        movement.setTipvdacr(Long.toString(creditsOpType.getCreditsTypesCR().getType()).toCharArray()[0]);
        movement.setFdpvdacr(paymentMethodCode); // CR.FORMADEPAGO.CODFORMADEPAGO
        movement.setCondcdacr(creditsOpType.getCondition());
        movement.setNummcpvdacr(0L); // A Store Procedure change its value later
        if (creditsMov.getTransactionId() != null) {
            movement.setNumtrdacr(Long.parseLong(creditsMov.getTransactionId()));
        }
        movement.setCajadacr((short) creditsMov.getPos());
        movement.setRepldacr(' ');

        return movement;
    }
}
