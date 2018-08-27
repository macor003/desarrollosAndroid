/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Money;
import com.becoblohm.cr.models.Payment;
import com.becoblohm.cr.models.PaymentMethod;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Formadepago;
import crjpa.Formadepagopuntoagil;
import crjpa.Monedatasacambio;
import crjpa.Porcentajeimpuestoretencion;
import crjpa.Transaccion;
import crjpa.Transaccionformadepago;
import crjpa.exceptions.IllegalOrphanException;
import crjpa.exceptions.NonexistentEntityException;

/**
 * @version $Revision: 1.0 $
 */
public class TransaccionFormadepagoJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private final EntityManagerFactory emf;

    /**
     * Field jpaFDPController.
     */
    private final FormadepagoJpaController jpaFDPController;

    /**
     * Field jpaController.
     */
    private final crjpa.TransaccionformadepagoJpaController jpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Transaccionformadepago";

    /**
     * Constructor for TransaccionFormadepagoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TransaccionFormadepagoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        jpaFDPController = new FormadepagoJpaController(this.emf);
        jpaController = new crjpa.TransaccionformadepagoJpaController(this.emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method createOrEdit.
     * 
     * @param transaccionFormadepago Transaccionformadepago
     * @throws IllegalOrphanException
     * @throws NonexistentEntityException
     * @throws Exception
     */
    @Deprecated
    public void createOrEdit(Transaccionformadepago transaccionFormadepago)
            throws IllegalOrphanException, NonexistentEntityException, Exception {
        if (transaccionFormadepago.getId() < 0) {
            transaccionFormadepago.setId(null);
            this.jpaController.create(transaccionFormadepago);
        } else {
            this.jpaController.edit(transaccionFormadepago);
        }
    }

    /**
     * Method toJPA.
     * 
     * @param payment Payment
     * @param jpaTransaction Transaccion
     * @return Transaccionformadepago
     */
    public Transaccionformadepago toJPA(Payment payment, Transaccion jpaTransaction) {

        crjpa.MonedaJpaController jpaMonedaController = new crjpa.MonedaJpaController(this.emf);

        PaymentMethod formadepago = this.jpaFDPController
                .findFormadepagoById((int) payment.getPayMethod().getId());
        crjpa.Moneda moneda = jpaMonedaController.findMoneda(payment.getMoney().getCurrencyId());

        crjpa.Transaccionformadepago jpaTransaccionFormadepago = new crjpa.Transaccionformadepago();
        if (payment.getTransactionPaymentId() >= 1) {
            jpaTransaccionFormadepago.setId(payment.getTransactionPaymentId());
        }
        Formadepago jpaPaymentMethod = new Formadepago();
        jpaPaymentMethod.setId(formadepago.getId());
        jpaTransaccionFormadepago.setIdFormadepago(jpaPaymentMethod);
        jpaTransaccionFormadepago.setIdMoneda(moneda);
        jpaTransaccionFormadepago.setEstasincronizado("N");
        jpaTransaccionFormadepago.setEstaactivo(ActiveValues.get(payment.isActive()).getString());

        jpaTransaccionFormadepago.setItem(payment.getItem());
        jpaTransaccionFormadepago.setMontoMoneda(payment.getMoney().getCurrencyAmmount().getValue());
        jpaTransaccionFormadepago.setMontoMonedaLocal(payment.getMoney().getLocalAmmount().getValue());
        jpaTransaccionFormadepago.setDocumento(String.valueOf(payment.getDocumentNumber()));
        jpaTransaccionFormadepago.setCuenta(String.valueOf(payment.getAccountNumber()));
        jpaTransaccionFormadepago.setConformacion(String.valueOf(payment.getConformationNumber()));
        jpaTransaccionFormadepago.setTitular(payment.getCardHolder());
        jpaTransaccionFormadepago.setIdTransaccion(jpaTransaction);
        jpaTransaccionFormadepago.setFormadepagopuntoagilList(new ArrayList<Formadepagopuntoagil>());
        if (payment.getRetentionPercentage() == null) {
            jpaTransaccionFormadepago.setPorcentajeRetencion(new BigDecimal(0));
        } else {
            jpaTransaccionFormadepago.setPorcentajeRetencion(payment.getRetentionPercentage().getValue());
        }

        if (payment.getRetencion() != null) {
            jpaTransaccionFormadepago.setIdPorcentajeimpuestoretencion(new Porcentajeimpuestoretencion(
                    payment.getRetencion().getId()));
        }

        return jpaTransaccionFormadepago;
    }

    /**
     * Method fromJPA.
     * 
     * @param jpaPaymentMethod Transaccionformadepago
     * @return Payment
     */
    public Payment fromJPA(Transaccionformadepago jpaPaymentMethod) {
        Payment payment = new Payment();
        payment.setDonation(CRBigDecimal.ZERO);
        MonedatasacambioJpaController exchangeController = new MonedatasacambioJpaController(this.emf);
        PorcentajeimpuestoretencionJpaController retentionpercentController = new PorcentajeimpuestoretencionJpaController(
                this.emf);
        Monedatasacambio exchange = exchangeController.findLastByDate(
                                                                      jpaPaymentMethod.getIdTransaccion()
                                                                              .getFecha(),
                                                                      jpaPaymentMethod.getIdMoneda().getId());
        Money money = new Money();
        money.setExchangeRate(new CRBigDecimal(exchange.getCambio().doubleValue(), 3));
        money.setCurrencyAmmount(new CRBigDecimal(jpaPaymentMethod.getMontoMoneda().doubleValue()));
        money.setLocalAmmount(new CRBigDecimal(jpaPaymentMethod.getMontoMonedaLocal().doubleValue()));
        money.setCurrencyId(jpaPaymentMethod.getIdMoneda().getId());
        money.setName(jpaPaymentMethod.getIdMoneda().getNombre());
        payment.setMoney(money);
        payment.setId(String.valueOf(jpaPaymentMethod.getId()));
        payment.setPayMethod(jpaFDPController.fromJPA(jpaPaymentMethod.getIdFormadepago(),
                                                      jpaPaymentMethod.getIdMoneda()));
        payment.setTransactionPaymentId(jpaPaymentMethod.getId());
        payment.setActive(ActiveValues.get(jpaPaymentMethod.getIdFormadepago().getEstaactivo()).getValue());
        payment.setDocumentNumber(jpaPaymentMethod.getDocumento());
        payment.setCardHolder(jpaPaymentMethod.getTitular());
        payment.setAccountNumber(jpaPaymentMethod.getCuenta());
        payment.setConformationNumber(jpaPaymentMethod.getConformacion());
        payment.setRetentionPercentage(new CRBigDecimal(jpaPaymentMethod.getPorcentajeRetencion().doubleValue()));
        if (jpaPaymentMethod.getIdPorcentajeimpuestoretencion() != null) {
            payment.setRetencion(retentionpercentController
                    .fromJPA(jpaPaymentMethod.getIdPorcentajeimpuestoretencion()));
        } else {
            payment.setRetencion(null);
        }

        return payment;
    }

    public Payment findPayment(long id) {

        return fromJPA(jpaController.findTransaccionformadepago(id));
    }

    /**
     * Method edit.
     * 
     * @param payment Payment
     * @param id long
     * @throws JpaException
     */
    public void edit(Payment payment, long id) throws JpaException {
        Transaccion tmp = new Transaccion();
        tmp.setId(id);
        Transaccionformadepago jpaPaymentMethod = toJPA(payment, tmp);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(jpaPaymentMethod);
            // this.jpaController.edit(jpaPaymentMethod);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new JpaException();
        }
    }

    /**
     * Method create.
     * 
     * @param payment Payment
     * @param id long
     * @throws JpaException
     */
    public void create(Payment payment, long id) throws JpaException {
        Transaccion tmp = new Transaccion();
        tmp.setId(id);
        Transaccionformadepago jpaPaymentMethod = toJPA(payment, tmp);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(jpaPaymentMethod);
            em.getTransaction().commit();
            // this.jpaController.create(jpaPaymentMethod);
            payment.setTransactionPaymentId(jpaPaymentMethod.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new JpaException();
        }
    }

    /**
     * Method deactivateFromJPA.
     * 
     * @param transactionPaymentId long
     * @throws JpaException
     */
    public void deactivateFromJPA(long transactionPaymentId) throws JpaException {
        try {
            EntityManager em = jpaController.getEntityManager();
            em.getTransaction().begin();
            Query query = em
                    .createQuery("UPDATE Transaccionformadepago t SET t.estaactivo='N',t.item=-1 WHERE t.id = :id");
            query.setParameter("id", transactionPaymentId);
            query.executeUpdate();
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            throw new JpaException();
        }
    }

    /**
     * Method existPayment.
     * 
     * @param idTFDP long
     * @param pm PaymentMethod
     * @return boolean
     */
    public boolean existPayment(long idTFDP, PaymentMethod pm) {

        Transaccionformadepago result = jpaController.findTransaccionformadepago(idTFDP);
        boolean exist;
        if (result != null) {
            if (result.getIdFormadepago().getId().equals(pm.getId())
                    && result.getIdMoneda().getId().equals(pm.getMoneyId())) {
                exist = true;
            } else {
                exist = false;
            }
        } else {
            exist = false;
        }

        return exist;
    }
}
