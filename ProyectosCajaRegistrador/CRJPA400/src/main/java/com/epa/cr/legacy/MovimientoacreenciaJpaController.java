/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CreditsAccount;
import com.becoblohm.cr.models.CreditsBalance;
import com.becoblohm.cr.models.CreditsMovement;
import com.becoblohm.cr.models.CreditsOperationType;
import com.becoblohm.cr.models.Payment;
import com.becoblohm.cr.models.PaymentMethod;
import com.becoblohm.cr.net.request.CreditsRequest;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Acreencia;
import crjpa400.Formadepagomovacreencia;
import crjpa400.Movimientoacreencia;
import crjpa400.Saldoacreencia;
import crjpa400.Tipoacreenciaoperacion;

public class MovimientoacreenciaJpaController extends AbstractJPAController {

    private static final long DEPOSITO = 1;

    private static final long REINTEGRO = 3;

    private static final long REINTEGRO_OP = 4;

    private static final long CONSUMO = 5;

    private static final long CHECKPAYMENTMETHODCODE = 96L;

    private static final long LOCALECURRENCYOPTION = 6L;

    private final EntityManagerFactory emf;

    private final crjpa400.MovimientoacreenciaJpaController controller;

    private static String entityName = "Movimientoacreencia";

    private static final Logger logger = LoggerFactory.getLogger(MovimientoacreenciaJpaController.class);

    /**
     * Constructor for MovimientoacreenciaJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public MovimientoacreenciaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.emf = emf;
        controller = new crjpa400.MovimientoacreenciaJpaController(emf);
    }

    private static final long serialVersionUID = 7213573188831492029L;

    /**
     * Method toJPA.
     * 
     * @param info CreditsMovement
     * @return Movimientoacreencia
     */
    public Movimientoacreencia toJPA(CreditsMovement info) {
        Movimientoacreencia mov = new Movimientoacreencia();
        TipoacreenciaoperacionJpaController jpacontroller = new TipoacreenciaoperacionJpaController(emf);
        Tipoacreenciaoperacion tipoacr = jpacontroller.toJPA(info.getCreditsOperationTypeId());
        if (info.isRefundByCheck()) {
            mov.setDevolucioncheque('S');
        } else {
            mov.setDevolucioncheque('N');
        }

        mov.setCaja(info.getPos());
        mov.setCajero(info.getCashier());
        mov.setFecha(new Date());
        mov.setIdTipoacreenciaoperacion(tipoacr);
        mov.setMonto(info.getAmount().abs().getValue());
        mov.setIdAcreencia(new Acreencia(Long.parseLong(info.getCreditsAccount().getCreditsId())));
        mov.setNumeroop(info.getProcessId());
        mov.setAnulaoperacion(info.getProcessIdToAnul());
        mov.setTienda(info.getStore());
        mov.setEstado(info.getStatus()); // V, D, A, B
        mov.setIpaStatus(info.getIpaStatus());
        mov.setIpaId(info.getIpaid());
        mov.setVuelto(info.getChange().abs().getValue());

        if (info.getId() > 0) {
            mov.setFormadepagomovacreenciaList(new ArrayList<Formadepagomovacreencia>());
            for (Payment pay : info.getPayments()) {
                mov.getFormadepagomovacreenciaList().add(FormadepagomovacreenciaJpaController.toJPA(pay));
            }
        }
        if (info.getTransactionId() != null) {
            mov.setIdTransaccion(new BigInteger(info.getTransactionId()));
        }

        return mov;
    }

    /**
     * Method findLastMov.
     * 
     * @param creditsId long
     * @return CreditsMovement
     */
    public CreditsMovement findLastMov(long creditsId) {
        EntityManager em = emf.createEntityManager();
        MovimientoacreenciaJpaController movController = new MovimientoacreenciaJpaController(emf);
        Movimientoacreencia singleResult = null;
        CreditsMovement result = null;

        try {
            Query query = em
                    .createQuery("SELECT m FROM Movimientoacreencia m WHERE m.numeroop = (SELECT max(b.numeroop) from Movimientoacreencia b where b.idAcreencia = m.idAcreencia AND b.ipaStatus = 'A') and m.idAcreencia = :numeroacreencia");
            query.setParameter("numeroacreencia", new Acreencia(creditsId));
            query.setMaxResults(1);
            singleResult = (Movimientoacreencia) query.getSingleResult();
            result = movController.fromJPA(singleResult);
        } catch (Exception ex) {
            singleResult = null;
            logger.error("No result exception, there's no credit movement registered for the creditID " + creditsId
                    + " in the database");
        } finally {
            em.close();
        }
        return result;

    }

    /**
     * Method findLastMovByStore.
     * 
     * @param creditsId long
     * @param store int
     * @return CreditsMovement
     */
    public CreditsMovement findLastMovByStore(long creditsId, int store) {
        EntityManager em = emf.createEntityManager();
        MovimientoacreenciaJpaController movController = new MovimientoacreenciaJpaController(emf);
        Movimientoacreencia singleResult = null;
        CreditsMovement result = null;

        try {
            Query query = em
                    .createQuery("SELECT m FROM Movimientoacreencia m WHERE m.numeroop = (SELECT max(b.numeroop) from Movimientoacreencia b where b.idAcreencia = m.idAcreencia and b.tienda = m.tienda AND b.ipaStatus = 'A') and m.idAcreencia = :numeroacreencia and m.tienda = :tienda");
            query.setParameter("numeroacreencia", new Acreencia(creditsId));
            query.setParameter("tienda", new Integer(store));
            query.setMaxResults(1);
            singleResult = (Movimientoacreencia) query.getSingleResult();
            result = movController.fromJPA(singleResult);
        } catch (Exception ex) {
            singleResult = null;
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return result;

    }

    /**
     * Method findLastMov.
     * 
     * @param creditsId long
     * @param creditsTypeId long
     * @return CreditsMovement
     */
    public CreditsMovement findLastMov(long creditsId, long creditsTypeId) {
        EntityManager em = emf.createEntityManager();
        MovimientoacreenciaJpaController movController = new MovimientoacreenciaJpaController(emf);
        Movimientoacreencia singleResult = null;
        CreditsMovement result = null;

        try {
            Query query = em
                    .createQuery("SELECT m FROM Movimientoacreencia m WHERE m.numeroop = (SELECT max(b.numeroop) from Movimientoacreencia b where b.ipaStatus = 'A' and  b.idAcreencia = m.idAcreencia and b.idTipoacreenciaoperacion.idTipoacreencia.id = m.idTipoacreenciaoperacion.idTipoacreencia.id) and m.idAcreencia = :numeroacreencia and m.idTipoacreenciaoperacion.idTipoacreencia.id = :creditsTypeId");
            query.setParameter("numeroacreencia", new Acreencia(creditsId));
            query.setParameter("creditsTypeId", creditsTypeId);
            query.setMaxResults(1);
            singleResult = (Movimientoacreencia) query.getSingleResult();
            result = movController.fromJPA(singleResult);
        } catch (Exception ex) {
            singleResult = null;
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return result;

    }

    /**
     * Method findLastMovByStore.
     * 
     * @param creditsId long
     * @param creditsTypeId long
     * @param store int
     * @return CreditsMovement
     */
    public CreditsMovement findLastMovByStoreActive(long creditsId, long creditsTypeId, int store) {
        EntityManager em = emf.createEntityManager();
        MovimientoacreenciaJpaController movController = new MovimientoacreenciaJpaController(emf);
        Movimientoacreencia singleResult = null;
        CreditsMovement result = null;

        try {
            Query query = em
                    .createQuery("SELECT m FROM Movimientoacreencia m WHERE m.numeroop = (SELECT max(b.numeroop) from Movimientoacreencia b where b.ipaStatus = 'A' and b.idAcreencia = m.idAcreencia and b.idTipoacreenciaoperacion.idTipoacreencia.id = m.idTipoacreenciaoperacion.idTipoacreencia.id and b.tienda = m.tienda) and m.idAcreencia = :numeroacreencia and m.idTipoacreenciaoperacion.idTipoacreencia.id = :creditsTypeId and m.tienda = :tienda");
            query.setParameter("numeroacreencia", new Acreencia(creditsId));
            query.setParameter("creditsTypeId", creditsTypeId);
            query.setParameter("tienda", new Integer(store));
            query.setMaxResults(1);
            singleResult = (Movimientoacreencia) query.getSingleResult();
            result = movController.fromJPA(singleResult);
        } catch (Exception ex) {
            singleResult = null;
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return result;

    }

    /**
     * Method findLastMovToJPA.
     * 
     * @param creditsId long
     * @return Movimientoacreencia
     */
    public Movimientoacreencia findLastMovToJPA(long creditsId) {
        EntityManager em = emf.createEntityManager();
        Movimientoacreencia singleResult = null;
        try {
            Query query = em
                    .createQuery("SELECT m FROM Movimientoacreencia m WHERE m.numeroop = (SELECT max(b.numeroop) from Movimientoacreencia b where b.idAcreencia = m.idAcreencia AND b.ipaStatus = 'A') and m.idAcreencia = :idAcreencia");
            query.setParameter("idAcreencia", new Acreencia(creditsId));
            query.setMaxResults(1);
            singleResult = (Movimientoacreencia) query.getSingleResult();
        } catch (Exception ex) {
            singleResult = null;
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return singleResult;

    }

    private boolean onTime(Long timeout, long elapsedTime) {
        logger.info("Timeout: " + timeout + ", ElapsedTime: " + (elapsedTime * 2) + " | Is on time?: "
                + (timeout > (elapsedTime * 2)));
        return timeout > (elapsedTime * 2);
    }

    /**
     * Method saveCreditsMovementCancellation.
     * 
     *
     * @param info CreditsMovement
     * @param balance CreditsBalance
     * @param idPaymentToBlock Long
     * @param startTime
     * @throws JpaException
     */
    public void saveCreditsMovementCancellation(CreditsMovement info, CreditsBalance balance,
                                                Long idPaymentToBlock, long startTime)
            throws JpaException {
        try {

            long processingRequestElapsedTime = System.currentTimeMillis();
            char tipoProceso = 'N';
            final Long requestTimeout = info.getTimeout();
            final Long ipaId = info.getIpaid();
            MovimientoacreenciaJpaController movimientoacreenciaJpaController = new MovimientoacreenciaJpaController(
                    this.emf);
            CreditsMovement bdCreditMovement = movimientoacreenciaJpaController.findMovByIpaId(ipaId);
            boolean esRollback = false;
            Character ipaStatusAsChar = info.getIpaStatus();

            if (ipaStatusAsChar != null) {
                esRollback = CreditsRequest.REQUEST_ROLLBACK == ipaStatusAsChar;
            }

            if (esRollback && bdCreditMovement != null) {
                logger.info("A ROLLBACK request has been received for the IPAID: " + ipaId);
                tipoProceso = bdCreditMovement.getIpaStatus();

                if (tipoProceso == 'N') {
                    logger.info("The ROLLBACK operation doesn't exist.");
                } else if (tipoProceso == 'P') {
                    logger.info("ROLLBACK. Updating movement with IPAID: " + ipaId);

                    EntityManager em = this.getEntityManager();
                    em.getTransaction().begin();
                    Query updateStatusQuery = em.createNamedQuery("Movimientoacreencia.updateStatus");
                    updateStatusQuery.setParameter(1, info.getIpaStatus());
                    updateStatusQuery.setParameter(2, ipaId);
                    int updateData = updateStatusQuery.executeUpdate();

                    em.getTransaction().commit();
                } else if (tipoProceso == 'A') {
                    logger.info("ROLLBACK. Updating balance and movement with IPAID: " + ipaId);
                    EntityManager em = this.getEntityManager();
                    em.getTransaction().begin();
                    Query updateStatusQuery = em.createNamedQuery("Movimientoacreencia.updateStatus");
                    updateStatusQuery.setParameter(1, info.getIpaStatus());
                    updateStatusQuery.setParameter(2, ipaId);
                    int updateData = updateStatusQuery.executeUpdate();

                    CreditsMovement mov = findMovimientoacreencia(Integer
                            .valueOf(info.getCreditsAccount().getCreditsId()), info.getProcessIdToAnul(), 'V');
                    Iterator<Payment> it = mov.getPayments().iterator();

                    while (it.hasNext()) {
                        Payment type = it.next();
                        Formadepagomovacreencia pago = new Formadepagomovacreencia();

                        if (type.getPayMethod().getId() > 0) {
                            pago = FormadepagomovacreenciaJpaController.toJPA(type);
                        } else {
                            FormadepagoJpaController fdpJpaController = new FormadepagoJpaController(emf);
                            PaymentMethod tmpPaymentMethod = fdpJpaController
                                    .findFormadepagoById(type.getPayMethod().getCode(),
                                                         type.getMoney().getCurrencyId());
                            type.setPayMethod(tmpPaymentMethod);
                            pago = FormadepagomovacreenciaJpaController.toJPA(type);
                        }
                        // Adding payments
                        balance.setAmount(balance.getAmount()
                                .add(new CRBigDecimal(pago.getMonto().abs().doubleValue())));
                    }

                    logger.info("ROLLBACK. New balance: " + balance.getAmount());
                    SaldoacreenciaJpaController saldoController = new SaldoacreenciaJpaController(this.emf);
                    Saldoacreencia saldoJPA = saldoController.toJPA(balance);
                    em.merge(saldoJPA);
                    em.getTransaction().commit();
                }

                info.setProcessId(movimientoacreenciaJpaController.findNumeroOpByIpaId(ipaId));
                info.setStatus('V');
            } else {
                if (bdCreditMovement != null) {
                    tipoProceso = bdCreditMovement.getIpaStatus();
                }
                if (tipoProceso == 'N') {
                    info.setIpaStatus('P');
                }
                info.setIpaid(ipaId);

                CreditsMovement mov = findMovimientoacreencia(Integer
                        .valueOf(info.getCreditsAccount().getCreditsId()), info.getProcessIdToAnul(), 'V');

                if (mov != null) {
                    CreditsMovement anu = new CreditsMovement();
                    anu.setIpaid(ipaId);
                    anu.setIpaStatus(info.getIpaStatus());
                    anu.setAmount(mov.getAmount());
                    anu.setBalanceId(mov.getBalanceId());
                    anu.setCashier(mov.getCashier());
                    anu.setChange(CRBigDecimal.ZERO);
                    anu.setCreditsAccount(mov.getCreditsAccount());
                    TipoacreenciaoperacionJpaController taoJpaController = new TipoacreenciaoperacionJpaController(
                            this.emf);
                    CreditsOperationType cot = taoJpaController
                            .findTipoacreenciasoperacion(mov.getCreditsOperationTypeId().getCreditsType().getId(),
                                                         info.getOperationId().getId());
                    anu.setCreditsOperationTypeId(cot);
                    anu.setDate(new Date());
                    anu.setOperationId(info.getOperationId());

                    AcreenciaJpaController acreenciaJpaController = new AcreenciaJpaController(this.emf);
                    CreditsAccount acr = acreenciaJpaController.findAcreenciasByClientId(String
                            .valueOf(info.getCreditsAccount().getClient().getIdNumber()));

                    anu.setProcessId(getLastMovProcessId(acr));
                    anu.setPos(mov.getPos());
                    anu.setProcessIdToAnul(mov.getProcessId());
                    anu.setRefundByCheck(false);
                    anu.setStatus('V');
                    anu.setStore(mov.getStore());
                    anu.setTransactionId(mov.getTransactionId());

                    EntityManager em = this.getEntityManager();
                    em.getTransaction().begin();
                    Movimientoacreencia anuJPA = toJPA(anu);

                    if (tipoProceso == 'N' || tipoProceso == 'P') {
                        List<Formadepagomovacreencia> pagos = new ArrayList<Formadepagomovacreencia>();
                        Iterator<Payment> it = mov.getPayments().iterator();
                        while (it.hasNext()) {
                            Payment type = it.next();
                            Formadepagomovacreencia pago = new Formadepagomovacreencia();
                            CRBigDecimal subtract = type.getMoney().getLocalAmmount();
                            if (type.getPayMethod().getId() > 0) {
                                pago = FormadepagomovacreenciaJpaController.toJPA(type);
                            } else {
                                FormadepagoJpaController fdpJpaController = new FormadepagoJpaController(emf);
                                PaymentMethod tmpPaymentMethod = fdpJpaController
                                        .findFormadepagoById(type.getPayMethod().getCode(),
                                                             type.getMoney().getCurrencyId());
                                type.setPayMethod(tmpPaymentMethod);
                                pago = FormadepagomovacreenciaJpaController.toJPA(type);
                            }

                            pago.setIdMovimientoacreencia(anuJPA);

                            /*
                             * If the payment method is CASH and there's is change,
                             * subtract it from the payment method amount and sets the
                             * credits movement change to zero to avoid subtraction again
                             */
                            if (mov.getChange().greaterThan(CRBigDecimal.ZERO)
                                    && type.getPayMethod().getId() == 1) {
                                subtract = type.getMoney().getLocalAmmount().abs().subtract(mov.getChange());
                                mov.setChange(CRBigDecimal.ZERO);
                            }

                            balance.setAmount(balance.getAmount().subtract(subtract));
                            pagos.add(pago);
                            if (tipoProceso == 'N') {
                                em.persist(pago);
                            }
                        }
                    }

                    anuJPA.setSaldo(new BigDecimal(balance.getAmount().doubleValue()));
                    anuJPA.setBloqueado(new BigDecimal(balance.getBlocked().doubleValue()));

                    if (tipoProceso == 'N') {
                        em.persist(anuJPA);
                    }

                    processingRequestElapsedTime = System.currentTimeMillis() - startTime;

                    if ((tipoProceso == 'N' || tipoProceso == 'P')
                            && onTime(requestTimeout, processingRequestElapsedTime)) {
                        SaldoacreenciaJpaController saldoController = new SaldoacreenciaJpaController(this.emf);
                        Saldoacreencia saldoJPA = saldoController.toJPA(balance);
                        em.merge(saldoJPA);
                        info.setIpaStatus('A');
                        anuJPA.setIpaStatus(info.getIpaStatus());
                        Query updateStatusQuery = em.createNamedQuery("Movimientoacreencia.updateStatus");
                        updateStatusQuery.setParameter(1, anuJPA.getIpaStatus());
                        updateStatusQuery.setParameter(2, anu.getIpaid());
                        int updateData = updateStatusQuery.executeUpdate();
                    }
                    if (!onTime(requestTimeout, processingRequestElapsedTime)) {
                        logger.info("The operation won't get finish due is not on time");
                    }

                    em.getTransaction().commit();
                    info.setDate(anuJPA.getFecha());
                    info.setProcessId(anu.getProcessId());
                    info.setStatus('A');
                    if (anuJPA.getId() != null) {
                        info.setId(Integer.parseInt(String.valueOf(anuJPA.getId())));
                    }

                    Collection<Payment> payments = new ArrayList<Payment>();
                    payments.addAll(mov.getPayments());
                    info.setPayments(payments);
                    info.setChange(CRBigDecimal.ZERO);
                    if (info.getIpaStatus() == 'A') {
                        acreenciaJpaController.incrementarNumeroOperacion(acr);
                    }

                } else {
                    throw new JpaException("Null credits movement");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new JpaException(e.getMessage(), e);
        }

    }

    private Integer findNumeroOpByIpaId(Long ipaId) {
        EntityManager em = emf.createEntityManager();
        Movimientoacreencia singleResult;
        Integer result = null;
        try {
            Query query = em.createQuery("SELECT m FROM Movimientoacreencia m WHERE m.ipaId = :ipaId");
            query.setParameter("ipaId", ipaId);
            query.setMaxResults(1);
            singleResult = (Movimientoacreencia) query.getSingleResult();
            if (singleResult != null) {
                logger.info("A credits movement with IPAID " + ipaId + " is already registered");
                result = singleResult.getNumeroop();
            }
        } catch (NoResultException nre) {
            logger.error("A movement with IPAID " + ipaId + " couldn't be found");
            result = null;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            em.close();
        }
        return result;
    }

    /**
     * Method getLastMovProcessId.
     * 
     * @param acr CreditsAccount
     * @return int
     */
    private int getLastMovProcessId(CreditsAccount acr) {
        int result = 1;
        int creditAccProcessNumber = 0;
        int creditMovProcessNumber = 0;
        if (acr == null) {
            logger.error("The Credit Account was not fount on the database");
        } else {
            creditAccProcessNumber = acr.getProcessNumber();
            CreditsMovement mov = findLastMov(Long.valueOf(acr.getCreditsId()));
            if (mov != null) {
                creditMovProcessNumber = mov.getProcessId();
                logger.info("Credit movement process number: " + creditMovProcessNumber);
            }
            if (creditAccProcessNumber > creditMovProcessNumber) {
                result = creditAccProcessNumber + 1;
            } else {
                result = creditMovProcessNumber + 1;
            }
            logger.info("Setting process number to the current movement: " + result);
        }
        return result;
    }

    /**
     * <p> Method used to find out if the number to add to the Credits Movement's or
     * Account's process ID is 3 because there's a check payment method involve or is 1.
     * </p>
     * 
     * @param CreditsMovement The current credit movement
     * @return processNumberToAdd the number of jumps necessary to add to the credit
     *         movement number process
     */
    @Deprecated
    private int getProcessNumberToAdd(CreditsMovement cm) {
        /*
         * Getting the check payment method ID for adding a jump in the credit's process
         * number if the credits payment is a check
         */
        OpcionJpaController opcionJpaController = new OpcionJpaController(emf);
        String checkCode = opcionJpaController.findById(CHECKPAYMENTMETHODCODE);
        String localCurrency = opcionJpaController.findById(LOCALECURRENCYOPTION);
        FormadepagoJpaController fdpJpaController = new FormadepagoJpaController(emf);
        PaymentMethod checkPaymentMethod = fdpJpaController.findFormadepagoByCode(Long.parseLong(checkCode),
                                                                                  Long.parseLong(localCurrency));
        int processNumberToAdd = 1;
        boolean isCheck = false;
        boolean isRefundByCheck = false;

        if (cm != null) {
            isRefundByCheck = cm.isRefundByCheck();
            for (Payment pay : cm.getPayments()) {
                isCheck = (pay.getPayMethod().getId() == checkPaymentMethod.getId());
                if (isCheck) {
                    break;
                }
            }
        }
        if (isCheck || isRefundByCheck) {
            processNumberToAdd = 3;
        }
        return processNumberToAdd;
    }

    /**
     * Method saveCreditsMovementRefundOperation.
     * 
     * @param movement CreditsMovement
     * @param balance CreditsBalance
     * @throws JpaException
     */
    public void saveCreditsMovementRefundOperation(CreditsMovement movement, CreditsBalance balance)
            throws JpaException {
        try {
            CreditsMovement dev = findMovimientoacreencia(Integer
                    .valueOf(movement.getCreditsAccount().getCreditsId()), movement.getProcessIdToAnul(), 'V');
            dev.setStatus('D');
            EntityManager em = this.getEntityManager();
            em.getTransaction().begin();
            Movimientoacreencia devjpa = toJPA(dev);
            devjpa.setSaldo(new BigDecimal(balance.getAmount().doubleValue()));
            devjpa.setBloqueado(new BigDecimal(balance.getBlocked().doubleValue()));
            em.merge(devjpa);
            em.getTransaction().commit();
            movement.setDate(devjpa.getFecha());
        } catch (Exception e) {
            e.printStackTrace();
            throw new JpaException(e.getMessage(), e);
        }
    }

    /**
     * Method saveCreditsMovementDeposit.
     * 
     *
     * @param info CreditsMovement
     * @param balance CreditsBalance
     * @param idPaymentToBlock Long
     * @param startTime
     * @throws JpaException
     */
    public void saveCreditsMovementDeposit(CreditsMovement info, CreditsBalance balance, Long idPaymentToBlock,
                                           long startTime)
            throws JpaException {
        try {
            long processingRequestElapsedTime = System.currentTimeMillis();
            char tipoProceso = 'N';

            final Long requestTimeout = info.getTimeout();
            final Long ipaId = info.getIpaid();
            MovimientoacreenciaJpaController movimientoacreenciaJpaController = new MovimientoacreenciaJpaController(
                    this.emf);
            CreditsMovement bdCreditMovement = movimientoacreenciaJpaController.findMovByIpaId(ipaId);
            boolean esRollback = false;
            Character ipaStatusAsChar = info.getIpaStatus();
            if (ipaStatusAsChar != null) {
                esRollback = CreditsRequest.REQUEST_ROLLBACK == ipaStatusAsChar;
            }

            if (esRollback && bdCreditMovement != null) {
                logger.info("A ROLLBACK request has been received for the IPAID: " + ipaId);
                tipoProceso = bdCreditMovement.getIpaStatus();

                if (tipoProceso == 'N') {
                    logger.info("The ROLLBACK operation doesn't exist");
                } else if (tipoProceso == 'P') {
                    logger.info("ROLLBACK. Movement with IPAID " + ipaId + " updated");
                    EntityManager em = this.getEntityManager();
                    em.getTransaction().begin();
                    Query updateStatusQuery = em.createNamedQuery("Movimientoacreencia.updateStatus");
                    updateStatusQuery.setParameter(1, info.getIpaStatus());
                    updateStatusQuery.setParameter(2, ipaId);
                    int updateData = updateStatusQuery.executeUpdate();

                    em.getTransaction().commit();
                } else if (tipoProceso == 'A') {
                    logger.info("ROLLBACK. Balance and movement updated. " + ipaId);
                    EntityManager em = this.getEntityManager();
                    em.getTransaction().begin();
                    Query updateStatusQuery = em.createNamedQuery("Movimientoacreencia.updateStatus");
                    updateStatusQuery.setParameter(1, info.getIpaStatus());
                    updateStatusQuery.setParameter(2, ipaId);
                    int updateData = updateStatusQuery.executeUpdate();

                    CRBigDecimal tmpAmount = bdCreditMovement.getAmount();
                    long tipo = bdCreditMovement.getCreditsOperationTypeId().getCreditsOperation().getId();

                    if (tipo == DEPOSITO) {
                        balance.setAmount(balance.getAmount().subtract(tmpAmount));
                    } else if (tipo == REINTEGRO || tipo == REINTEGRO_OP || tipo == CONSUMO) {
                        balance.setAmount(balance.getAmount().add(tmpAmount));
                    }
                    logger.info("ROLLBACK. New Balance: " + balance.getAmount());
                    SaldoacreenciaJpaController saldoController = new SaldoacreenciaJpaController(this.emf);
                    Saldoacreencia saldoJPA = saldoController.toJPA(balance);
                    em.merge(saldoJPA);
                    em.getTransaction().commit();
                }
                info.setProcessId(movimientoacreenciaJpaController.findNumeroOpByIpaId(ipaId));
                info.setStatus('V');
            } else {
                if (bdCreditMovement != null) {
                    tipoProceso = bdCreditMovement.getIpaStatus();
                }
                if (tipoProceso == 'N') {
                    info.setIpaStatus('P');
                }
                info.setIpaid(ipaId);

                SaldoacreenciaJpaController jpasaldo = new SaldoacreenciaJpaController(emf);
                Saldoacreencia saldo = null;

                EntityManager em = getEntityManager();

                info.setStatus('V');
                Movimientoacreencia movimientoacreencia = new Movimientoacreencia();

                AcreenciaJpaController acreenciaJpaController = new AcreenciaJpaController(this.emf);
                CreditsAccount acr = acreenciaJpaController.findAcreenciasByClientId(String
                        .valueOf(info.getCreditsAccount().getClient().getIdNumber()));

                if (tipoProceso == 'N') {
                    logger.info("RefundByCheck: " + info.isRefundByCheck());
                    info.setProcessId(getLastMovProcessId(acr));
                } else {
                    CreditsMovement lastMov = findMovByIpaId(info.getIpaid());
                    info.setProcessId(lastMov.getProcessId());
                }

                movimientoacreencia = toJPA(info);
                em.getTransaction().begin();
                info.setDate(movimientoacreencia.getFecha());

                List<Formadepagomovacreencia> pagos = new ArrayList<Formadepagomovacreencia>();
                Iterator<Payment> it = info.getPayments().iterator();
                CRBigDecimal blocked = CRBigDecimal.ZERO;
                while (it.hasNext()) {
                    Payment type = it.next();
                    Formadepagomovacreencia pago = new Formadepagomovacreencia();

                    if (type.getPayMethod().getId() > 0) {
                        pago = FormadepagomovacreenciaJpaController.toJPA(type);
                    } else {
                        FormadepagoJpaController fdpJpaController = new FormadepagoJpaController(emf);
                        PaymentMethod tmpPaymentMethod = fdpJpaController
                                .findFormadepagoById(type.getPayMethod().getCode(),
                                                     type.getMoney().getCurrencyId());
                        type.setPayMethod(tmpPaymentMethod);
                        pago = FormadepagomovacreenciaJpaController.toJPA(type);
                    }

                    pago.setIdMovimientoacreencia(movimientoacreencia);

                    if (idPaymentToBlock.equals(pago.getIdFormadepago().getId()) && info.isBlockCheck()) {
                        blocked = blocked.add(type.getMoney().getLocalAmmount());
                        pago.setEstaactivo('N');
                        logger.info("Blocked amount: " + blocked.doubleValue());
                    } else {
                        pago.setEstaactivo('S');
                    }
                    pagos.add(pago);
                    if (tipoProceso == 'N') {
                        em.persist(pago);
                    }
                }

                if (tipoProceso == 'N' || tipoProceso == 'P') {
                    CRBigDecimal tmpAmount = info.getAmount();

                    if (blocked.compareTo(CRBigDecimal.ZERO) > 0) {
                        balance.setBlocked(balance.getBlocked().add(blocked));
                        tmpAmount = tmpAmount.subtract(blocked);
                    }

                    balance.setAmount(balance.getAmount().add(tmpAmount));
                    saldo = jpasaldo.toJPA(balance);

                    logger.info("Balance after the credit movement: " + saldo.getMontoDisponible()
                            + ". Blocked amount: " + saldo.getMontoBloqueado());

                }
                movimientoacreencia.setSaldo(new BigDecimal(balance.getAmount().doubleValue()));
                movimientoacreencia.setBloqueado(new BigDecimal(balance.getBlocked().doubleValue()));

                if (tipoProceso == 'N') {
                    em.persist(movimientoacreencia);
                }

                processingRequestElapsedTime = System.currentTimeMillis() - startTime;
                if ((tipoProceso == 'N' || tipoProceso == 'P')
                        && onTime(requestTimeout, processingRequestElapsedTime)) {
                    em.merge(saldo);
                    info.setIpaStatus('A');
                    movimientoacreencia.setIpaStatus(info.getIpaStatus());
                    Query updateStatusQuery = em.createNamedQuery("Movimientoacreencia.updateStatus");
                    updateStatusQuery.setParameter(1, info.getIpaStatus());
                    updateStatusQuery.setParameter(2, ipaId);
                    int updateData = updateStatusQuery.executeUpdate();
                }
                if (!onTime(requestTimeout, processingRequestElapsedTime)) {
                    logger.info("The operation timed out and won't be finished");
                }

                em.getTransaction().commit();

                if (movimientoacreencia.getId() != null) {
                    info.setId(movimientoacreencia.getId().intValue());
                }
                if (info.getIpaStatus() == 'A') {
                    acreenciaJpaController.incrementarNumeroOperacion(acr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new JpaException(e.getMessage(), e);
        }
    }

    /**
     * Method findLastMovToAnul.
     * 
     * @param creditsId String
     * @param cajero int
     * @param creditsTypeOpId Long
     * @param creditsOperationId Long
     * @return CreditsMovement
     */
    public CreditsMovement findLastMovToAnul(String creditsId, int cajero, Long creditsTypeOpId,
                                             Long creditsOperationId) {
        EntityManager em = emf.createEntityManager();
        Movimientoacreencia singleResult = null;
        MovimientoacreenciaJpaController jpacontroller = new MovimientoacreenciaJpaController(emf);
        CreditsMovement result = null;

        try {
            Query query = em.createQuery("SELECT m FROM Movimientoacreencia m "
                    + "WHERE m.id = (SELECT max(b.id) from Movimientoacreencia b where "
                    + "b.ipaStatus = 'A' and b.idAcreencia.id = :idAcreencia and b.idTipoacreenciaoperacion.idTipoacreencia.id = :creditsTypeOpId and b.idTipoacreenciaoperacion.idOperacionacreencia.id = :creditsOperationId and b.cajero=:cajero )");

            query.setParameter("idAcreencia", new Long(creditsId));
            query.setParameter("cajero", cajero);
            query.setParameter("creditsTypeOpId", creditsTypeOpId);
            query.setParameter("creditsOperationId", creditsOperationId);
            query.setMaxResults(1);
            singleResult = (Movimientoacreencia) query.getSingleResult();
            result = jpacontroller.fromJPA(singleResult);
        } catch (Exception ex) {
            singleResult = null;
            logger.error("ERROR trying to find the last deposit movement for cancelation in CR400.MOVIMIENTOACREENCIA. "
                    + ex.getMessage());
            logger.debug("CreditsID: " + creditsId + " | Cashier EPAID: " + cajero + " | Credits type op ID: "
                    + creditsTypeOpId);
        } finally {
            em.close();
        }
        return result;

    }

    /**
     * Method findLastMovToAnulByStore.
     * 
     * @param creditsId String
     * @param cajero int
     * @param creditsTypeOpId Long
     * @param creditsOperationId Long
     * @param store int
     * @return CreditsMovement
     */
    public CreditsMovement findLastMovToAnulByStoreActive(String creditsId, int cajero, Long creditsTypeOpId,
                                                          Long creditsOperationId, int store) {
        EntityManager em = emf.createEntityManager();
        Movimientoacreencia singleResult = null;
        MovimientoacreenciaJpaController jpacontroller = new MovimientoacreenciaJpaController(emf);
        CreditsMovement result = null;

        try {
            Query query = em.createQuery("SELECT m FROM Movimientoacreencia m "
                    + "WHERE m.id = (SELECT max(b.id) from Movimientoacreencia b where "
                    + "b.ipaStatus = 'A' and b.idAcreencia.id = :idAcreencia and b.idTipoacreenciaoperacion.idTipoacreencia.id = :creditsTypeOpId and b.idTipoacreenciaoperacion.idOperacionacreencia.id = :creditsOperationId and b.cajero=:cajero  and b.tienda = :tienda)");

            query.setParameter("idAcreencia", new Long(creditsId));
            query.setParameter("cajero", cajero);
            query.setParameter("creditsTypeOpId", creditsTypeOpId);
            query.setParameter("creditsOperationId", creditsOperationId);
            query.setParameter("tienda", new Integer(store));
            query.setMaxResults(1);
            singleResult = (Movimientoacreencia) query.getSingleResult();
            result = jpacontroller.fromJPA(singleResult);
        } catch (Exception ex) {
            singleResult = null;
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return result;

    }

    /**
     * Method findMovToRefund.
     * 
     * @param creditsId String
     * @param operationTypeId int
     * @param saldo BigDecimal
     * @return List<CreditsMovement>
     */
    public List<CreditsMovement> findMovToRefund(String creditsId, int operationTypeId, BigDecimal saldo) {
        EntityManager em = emf.createEntityManager();
        List<Movimientoacreencia> result = new ArrayList<Movimientoacreencia>();
        List<CreditsMovement> resultList = new ArrayList<CreditsMovement>();

        try {
            Query query = em
                    .createQuery("SELECT m FROM Movimientoacreencia m WHERE m.ipaStatus = 'A' and m.idAcreencia.id = :idAcreencia and m.estado = 'V' and m.idTipoacreenciaoperacion.id=:idTipoacreenciaoperacion ORDER BY m.numeroop");
            query.setParameter("idAcreencia", Long.parseLong(creditsId));
            query.setParameter("idTipoacreenciaoperacion", (long) operationTypeId);

            result = query.getResultList();

            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                resultList.add(fromJPA((Movimientoacreencia) iterator.next()));

            }
        } catch (Exception ex) {
            result = null;
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return resultList;

    }

    /**
     * Method findMovToRefundbyStore.
     * 
     * @param creditsId String
     * @param operationTypeId int
     * @param saldo BigDecimal
     * @param store int
     * @return List<CreditsMovement>
     */
    public List<CreditsMovement> findMovToRefundbyStore(String creditsId, int operationTypeId, BigDecimal saldo,
                                                        int store) {
        EntityManager em = emf.createEntityManager();
        List<Movimientoacreencia> result = new ArrayList<Movimientoacreencia>();
        List<CreditsMovement> resultList = new ArrayList<CreditsMovement>();

        try {
            Query query = em
                    .createQuery("SELECT m FROM Movimientoacreencia m WHERE m.ipaStatus = 'A' and m.idAcreencia.id = :idAcreencia and m.estado = 'V' and m.idTipoacreenciaoperacion.id=:idTipoacreenciaoperacion  and m.tienda = :tienda ORDER BY m.numeroop");
            query.setParameter("idAcreencia", Long.parseLong(creditsId));
            query.setParameter("idTipoacreenciaoperacion", (long) operationTypeId);
            query.setParameter("tienda", new Integer(store));

            result = query.getResultList();

            for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                resultList.add(fromJPA((Movimientoacreencia) iterator.next()));

            }
        } catch (Exception ex) {
            result = null;
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return resultList;

    }

    /**
     * Method fromJPA.
     * 
     * @param mov Movimientoacreencia
     * @return CreditsMovement
     */
    public CreditsMovement fromJPA(Movimientoacreencia mov) {
        CreditsMovement res;
        try {
            AcreenciaJpaController depositsJpaController = new AcreenciaJpaController(this.emf);
            FormadepagomovacreenciaJpaController fdpJpaController = new FormadepagomovacreenciaJpaController(
                    this.emf);
            res = new CreditsMovement();
            res.setStore(mov.getTienda());
            res.setPos(mov.getCaja());
            res.setCashier(mov.getCajero());
            res.setStatus(mov.getEstado());
            res.setAmount(new CRBigDecimal(mov.getMonto().doubleValue()));
            res.setCreditsAccount(depositsJpaController.fromJPA(mov.getIdAcreencia()));
            res.setDate(mov.getFecha());
            res.setId(mov.getId().intValue());
            res.setIpaid(mov.getIpaId());
            res.setIpaStatus(mov.getIpaStatus());
            res.setChange(new CRBigDecimal(mov.getVuelto().doubleValue()));

            TipoacreenciaoperacionJpaController tacontroller = new TipoacreenciaoperacionJpaController(this.emf);
            CreditsOperationType taop = tacontroller.fromJPA(mov.getIdTipoacreenciaoperacion());
            res.setCreditsOperationTypeId(taop);

            SaldoacreenciaJpaController saldoController = new SaldoacreenciaJpaController(this.emf);
            CreditsBalance saldo = saldoController
                    .findSaldoacreencia(mov.getIdTipoacreenciaoperacion().getIdTipoacreencia().getId(),
                                        mov.getIdAcreencia().getId());
            res.setOperationId(taop.getCreditsOperation());
            res.setBalanceId(saldo);

            Collection<Payment> pagos = new ArrayList<Payment>();
            for (Iterator iterator = mov.getFormadepagomovacreenciaList().iterator(); iterator.hasNext();) {
                Formadepagomovacreencia payment = (Formadepagomovacreencia) iterator.next();
                pagos.add(fdpJpaController.fromJPA(payment));
                logger.info("Adding payment method: " + payment.getIdFormadepago().getDescripcion());
            }

            if (pagos.size() == 0) {
                FormadepagomovacreenciaJpaController fdpAcreenciaJpa = new FormadepagomovacreenciaJpaController(
                        this.emf);
                List<Formadepagomovacreencia> fdpList = fdpAcreenciaJpa
                        .findFormadePagoMovacreenciaByIdMovimiento(mov.getId());

                if (fdpList != null) {
                    for (Formadepagomovacreencia formadepagoMovacreencia : fdpList) {
                        pagos.add(fdpJpaController.fromJPA(formadepagoMovacreencia));
                        logger.info("Adding payment method: "
                                + formadepagoMovacreencia.getIdFormadepago().getDescripcion());
                    }
                }
            }

            res.setPayments(pagos);
            res.setProcessId(mov.getNumeroop());
            if (mov.getAnulaoperacion() != null) {
                res.setProcessIdToAnul(mov.getAnulaoperacion());
            }

            res.setRefundByCheck(ActiveValues.get(String.valueOf(mov.getDevolucioncheque())).getValue());
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Method findMovimientoacreencia.
     * 
     * @param numeroacreencia long
     * @param numeroop int
     * @param estado char
     * @return CreditsMovement
     */
    public CreditsMovement findMovimientoacreencia(long numeroacreencia, int numeroop, char estado) {
        EntityManager em = emf.createEntityManager();
        Movimientoacreencia singleResult = null;
        CreditsMovement result = null;
        MovimientoacreenciaJpaController controller = new MovimientoacreenciaJpaController(emf);
        try {
            Query query = em
                    .createQuery("SELECT m FROM Movimientoacreencia m WHERE m.ipaStatus = 'A' AND m.numeroop = :numeroop and m.idAcreencia = :idAcreencia and m.estado = :estado");
            query.setParameter("numeroop", numeroop);
            query.setParameter("idAcreencia", new Acreencia(numeroacreencia));
            query.setParameter("estado", estado);
            query.setMaxResults(1);
            singleResult = (Movimientoacreencia) query.getSingleResult();
            result = controller.fromJPA(singleResult);
        } catch (Exception ex) {
            singleResult = null;
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return result;
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
     * Method saveCreditsMovementDevop.
     * 
     *
     * @param info CreditsMovement
     * @param balance CreditsBalance
     * @param startTime
     * @throws JpaException
     */
    public void saveCreditsMovementDevop(CreditsMovement info, CreditsBalance balance, long startTime)
            throws JpaException {
        try {

            long processingRequestElapsedTime = System.currentTimeMillis();
            char tipoProceso = 'N';
            final Long requestTimeout = info.getTimeout();
            final Long ipaId = info.getIpaid();
            MovimientoacreenciaJpaController movimientoacreenciaJpaController = new MovimientoacreenciaJpaController(
                    this.emf);
            CreditsMovement bdCreditMovement = movimientoacreenciaJpaController.findMovByIpaId(ipaId);
            boolean esRollback = false;
            Character ipaStatusAsChar = info.getIpaStatus();

            if (ipaStatusAsChar != null) {
                esRollback = CreditsRequest.REQUEST_ROLLBACK == ipaStatusAsChar;
            }

            if (esRollback && bdCreditMovement != null) {
                logger.info("A ROLLBACK request has been received for the IPAID: " + ipaId);

                info.setIpaStatus('R');
                tipoProceso = bdCreditMovement.getIpaStatus();

                if (tipoProceso == 'N') {
                    logger.info("The ROLLBACK operation doesn't exist");
                }

                else if (tipoProceso == 'P') {
                    logger.info("ROLLBACK. Updating credits movement " + ipaId);
                    EntityManager em = this.getEntityManager();
                    em.getTransaction().begin();
                    Query updateStatusQuery = em.createNamedQuery("Movimientoacreencia.updateStatus");
                    updateStatusQuery.setParameter(1, info.getIpaStatus());
                    updateStatusQuery.setParameter(2, ipaId);
                    int updateData = updateStatusQuery.executeUpdate();

                    em.getTransaction().commit();
                } else if (tipoProceso == 'A') {
                    logger.info("ROLLBACK. Updating movment and balance for the IPAID " + ipaId);
                    EntityManager em = this.getEntityManager();
                    em.getTransaction().begin();
                    Query updateStatusQuery = em.createNamedQuery("Movimientoacreencia.updateStatus");
                    updateStatusQuery.setParameter(1, info.getIpaStatus());
                    updateStatusQuery.setParameter(2, ipaId);
                    int updateData = updateStatusQuery.executeUpdate();

                    CreditsMovement mov = findMovimientoacreencia(Integer
                            .valueOf(info.getCreditsAccount().getCreditsId()), info.getProcessIdToAnul(), 'V');

                    balance.setAmount(balance.getAmount().add(new CRBigDecimal(mov.getAmount().doubleValue())));
                    SaldoacreenciaJpaController saldoController = new SaldoacreenciaJpaController(this.emf);
                    Saldoacreencia saldoJPA = saldoController.toJPA(balance);
                    em.merge(saldoJPA);
                    em.getTransaction().commit();
                }

                info.setProcessId(movimientoacreenciaJpaController.findNumeroOpByIpaId(ipaId));
                info.setStatus('V');

            } else {
                if (bdCreditMovement != null) {
                    tipoProceso = bdCreditMovement.getIpaStatus();
                }
                if (tipoProceso == 'N') {
                    info.setIpaStatus('P');
                }
                info.setIpaid(ipaId);

                CreditsMovement mov = findMovimientoacreencia(Integer
                        .valueOf(info.getCreditsAccount().getCreditsId()), info.getProcessIdToAnul(), 'V');

                if (mov != null) {
                    CreditsMovement dev = new CreditsMovement();
                    dev.setIpaid(info.getIpaid());
                    dev.setIpaStatus(info.getIpaStatus());
                    dev.setAmount(mov.getAmount());
                    dev.setBalanceId(mov.getBalanceId());
                    dev.setCashier(info.getCashier());
                    dev.setChange(CRBigDecimal.ZERO);
                    dev.setCreditsAccount(mov.getCreditsAccount());
                    TipoacreenciaoperacionJpaController taoJpaController = new TipoacreenciaoperacionJpaController(
                            this.emf);
                    CreditsOperationType cot = taoJpaController
                            .findTipoacreenciasoperacion(mov.getCreditsOperationTypeId().getCreditsType().getId(),
                                                         info.getOperationId().getId());
                    dev.setCreditsOperationTypeId(cot);
                    dev.setDate(new Date());
                    dev.setOperationId(info.getOperationId());
                    AcreenciaJpaController acreenciaJpaController = new AcreenciaJpaController(this.emf);
                    CreditsAccount acr = acreenciaJpaController.findAcreenciasByClientId(String
                            .valueOf(info.getCreditsAccount().getClient().getIdNumber()));
                    dev.setProcessId(getLastMovProcessId(acr));
                    dev.setPos(info.getPos());
                    dev.setProcessIdToAnul(mov.getProcessId());
                    dev.setRefundByCheck(false);
                    dev.setStatus('V');
                    dev.setStore(info.getStore());
                    dev.setTransactionId(mov.getTransactionId());

                    EntityManager em = this.getEntityManager();
                    em.getTransaction().begin();

                    Movimientoacreencia anuJPA = toJPA(dev);
                    balance.setAmount(balance.getAmount()
                            .subtract(new CRBigDecimal(mov.getAmount().doubleValue())));

                    anuJPA.setSaldo(new BigDecimal(balance.getAmount().doubleValue()));
                    anuJPA.setBloqueado(new BigDecimal(balance.getBlocked().doubleValue()));
                    if (tipoProceso == 'N') {
                        em.persist(anuJPA);
                    }

                    processingRequestElapsedTime = System.currentTimeMillis() - startTime;
                    if ((tipoProceso == 'N' || tipoProceso == 'P')
                            && onTime(requestTimeout, processingRequestElapsedTime)) {
                        info.setIpaStatus('A');
                        SaldoacreenciaJpaController saldoController = new SaldoacreenciaJpaController(this.emf);
                        Saldoacreencia saldoJPA = saldoController.toJPA(balance);
                        em.merge(saldoJPA);
                        anuJPA.setIpaStatus(info.getIpaStatus());

                        Query updateStatusQuery = em.createNamedQuery("Movimientoacreencia.updateStatus");
                        updateStatusQuery.setParameter(1, info.getIpaStatus());
                        updateStatusQuery.setParameter(2, ipaId);
                        int updateData = updateStatusQuery.executeUpdate();
                    }
                    if (!onTime(requestTimeout, processingRequestElapsedTime)) {
                        logger.info("The operation timed out and won't finish");
                    }

                    em.getTransaction().commit();
                    info.setDate(anuJPA.getFecha());
                    info.setProcessId(dev.getProcessId());
                    info.setStatus('D');
                    if (anuJPA.getId() != null) {
                        info.setId(Integer.parseInt(String.valueOf(anuJPA.getId())));
                    }
                    if (info.getIpaStatus() == 'A') {
                        acreenciaJpaController.incrementarNumeroOperacion(acr);
                    }

                } else {
                    throw new JpaException("Null credits movement");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new JpaException(e.getMessage(), e);
        }

    }

    /**
     * Method findLastMov.
     * 
     * @param storeId String
     * @param posId String
     * @param idEPA int
     * @return CreditsMovement
     */
    public CreditsMovement findLastMov(String storeId, String posId, int idEPA) {
        EntityManager em = emf.createEntityManager();
        MovimientoacreenciaJpaController movController = new MovimientoacreenciaJpaController(emf);
        Movimientoacreencia singleResult = null;
        CreditsMovement result = null;
        try {
            Query query = em
                    .createQuery("SELECT m FROM Movimientoacreencia m WHERE m.numeroop = (SELECT max(b.numeroop) from Movimientoacreencia b where b.tienda = :tienda and b.caja = :caja and b.cajero = :cajero AND b.ipaStatus = 'A') and b.tienda = :tienda and b.caja = :caja and b.cajero = :cajero");
            query.setParameter("tienda", Integer.parseInt(storeId));
            query.setParameter("caja", Integer.parseInt(posId));
            query.setParameter("cajero", idEPA);
            query.setMaxResults(1);
            singleResult = (Movimientoacreencia) query.getSingleResult();
            result = movController.fromJPA(singleResult);
        } catch (Exception ex) {
            singleResult = null;
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return result;

    }

    /**
     * Method isMovCancelled.
     * 
     * @param creditsMovement CreditsMovement
     * @return boolean
     */
    public boolean isMovCancelled(CreditsMovement creditsMovement) {
        EntityManager em = emf.createEntityManager();

        Movimientoacreencia singleResult = null;

        try {
            Query query = em
                    .createQuery("SELECT m FROM Movimientoacreencia m WHERE m.ipaStatus = 'A' and m.anulaoperacion =  :anulaOperacion and m.idAcreencia.id = :idAcreencia");
            query.setParameter("idAcreencia", new Long(creditsMovement.getCreditsAccount().getCreditsId()));
            query.setParameter("anulaOperacion", creditsMovement.getProcessId());
            query.setMaxResults(1);
            singleResult = (Movimientoacreencia) query.getSingleResult();
            if (singleResult == null) {
                logger.info("The movement is active. It hasn't been canceled before.");
                return false;
            } else {
                logger.error("ERROR. The process has been canceled previously");
                return true;
            }

        } catch (Exception ex) {
            logger.info("There was an error trying to find a movement in CR400.MOVIMIENTOACREENCIA with ANULAOPERACION="
                    + creditsMovement.getProcessId() + ", ID_ACREENCIA="
                    + creditsMovement.getCreditsAccount().getCreditsId() + ", IPA_STATUS=A");
            logger.error(ex.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    /**
     * Validate whether the movement was previously registered.
     *
     * @param ipaId id generated by CR
     * @return CreditsMovement movement's data
     */
    public CreditsMovement findMovByIpaId(Long ipaId) {
        logger.info("Searching in CR400.MOVIMIENTOACREENCIA a credit movement with IPAID: " + ipaId);
        MovimientoacreenciaJpaController movController = new MovimientoacreenciaJpaController(emf);
        EntityManager em = emf.createEntityManager();
        Movimientoacreencia singleResult;
        CreditsMovement result = null;
        try {
            Query query = em.createQuery("SELECT m FROM Movimientoacreencia m WHERE m.ipaId = :ipaId");
            query.setParameter("ipaId", ipaId);
            query.setMaxResults(1);
            singleResult = (Movimientoacreencia) query.getSingleResult();
            if (singleResult != null) {
                logger.info("A movement with IPAID " + ipaId
                        + " is already inserted in CR400.MOVIMIENTOACREENCIA");
                result = movController.fromJPA(singleResult);
            }
        } catch (NoResultException nre) {
            logger.error("A movement with IPAID " + ipaId + " could not be found in CR400.MOVIMIENTOACREENCIA");
            logger.error(nre.getMessage());
            result = null;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            em.close();
        }
        return result;
    }

    public int updateIpaStatus(Long ipaId, char ipaStatus) {
        EntityManager em = emf.createEntityManager();
        int updateCount = 0;

        try {
            em.getTransaction().begin();
            Query query = em
                    .createQuery("UPDATE Movimientoacreencia m SET m.ipaStatus = :ipaStatus WHERE m.ipaId = :ipaId");
            query.setParameter("ipaId", ipaId);
            query.setParameter("ipaStatus", ipaStatus);

            updateCount = query.executeUpdate();
            em.getTransaction().commit();
            return updateCount;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return updateCount;
    }

    /**
     * Metodo que dado pares(ipaId:'ipaStatus') de movimientos de acreencia para una caja
     * en un intervalo de tiempo retorna los movimientos que no coinciden o que no estan
     * estan en la lista suministrada
     * 
     * @param pairs
     * @param storeId
     * @param pos
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<Movimientoacreencia> findInconsistentByIpaIds(List<String> pairs, int storeId, int pos,
                                                              Date beginDate, Date endDate) {
        EntityManager em = emf.createEntityManager();
        List<Movimientoacreencia> result = new ArrayList<Movimientoacreencia>();

        try {
            Query query = em
                    .createQuery("SELECT m FROM Movimientoacreencia m WHERE CONCAT(m.ipaId,':',m.ipaStatus) NOT IN :pairs AND m.fecha BETWEEN :desde AND :hasta AND m.caja = :caja AND m.tienda = :tienda and m.ipaId NOT LIKE '%00'");
            query.setParameter("pairs", pairs);
            query.setParameter("caja", pos);
            query.setParameter("tienda", storeId);
            query.setParameter("desde", beginDate);
            query.setParameter("hasta", endDate);

            result = query.getResultList();
            if (result != null) {
                logger.info(result.size() + " movement has been found for credit conciliation");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.clear();
            em.close();
        }
        return result;
    }
}
