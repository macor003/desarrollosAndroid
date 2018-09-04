/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CashierCommitment;
import com.becoblohm.cr.models.CommitmentCurrency;
import com.becoblohm.cr.models.CommitmentCurrencyDetails;
import com.becoblohm.cr.models.CommitmentPayment;
import com.becoblohm.cr.models.PaymentMethod;
import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.models.User;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Entrega;
import crjpa.Entregaformadepago;
import crjpa.Formadepago;
import crjpa.Monedadenominacion;
import crjpa.Sesion;
import crjpa.Usuario;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */

public class EntregaJpaController extends AbstractJPAController {

    /**
     * Field SELECTENTREGA. (value is ""SELECT e FROM Entrega e WHERE e.id = (SELECT
     * max(ep.id) FROM Entrega ep WHERE ep.tipoEntrega = :tipoentrega)"")
     */
    private static final String SELECTENTREGA = "SELECT e FROM Entrega e WHERE e.id = (SELECT max(ep.id) FROM Entrega ep WHERE ep.tipoEntrega = :tipoentrega)";

    /**
     * Field SELECTPARTIALDELIVERY. (value is ""SELECT e FROM Entrega e WHERE e.fecha
     * >=:date AND e.tipo_entrega = :tipoentrega"; ")
     */
    private static final String SELECTPARTIALDELIVERY = "SELECT e FROM Entrega e WHERE e.fecha >=:date AND e.tipoEntrega = :tipoentrega";

    /**
     * Field SELECTALLPARTIALDELIVERY. (value is ""SELECT e FROM Entrega WHERE
     * e.tipo_entrega = :tipoentrega"; ")
     */
    private static final String SELECTALLPARTIALDELIVERY = "SELECT e FROM Entrega e WHERE e.tipoEntrega = :tipoentrega";

    /**
     * Field emf.
     */
    private final EntityManagerFactory emf;

    /**
     * Field jpacontroller.
     */
    private final crjpa.EntregaJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Entrega";

    /**
     * Constructor for EntregaJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public EntregaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        jpacontroller = new crjpa.EntregaJpaController(emf);
        this.emf = emf;
    }

    /**
     * Method toJPA.
     * 
     * @param cashierModel CashierCommitment
     * @return crjpa.Entrega
     */
    private crjpa.Entrega toJPA(CashierCommitment cashierModel) {

        Entrega entrega = new Entrega();
        entrega.setId(cashierModel.getId());
        entrega.setIdSesion(new Sesion(cashierModel.getId_sesion()));
        // entrega.getIdSesion().setId(cashierModel.getId_sesion());
        entrega.setMontoRecaudado(cashierModel.getMonto_recaudado().getValue());
        entrega.setMontoFondo(cashierModel.getMonto_fondo().getValue());
        entrega.setEstaactivo("S");
        entrega.setEstasincronizado(cashierModel.getIsSync());
        if (cashierModel.isOnLine()) {
            entrega.setEnlinea("S");
        } else {
            entrega.setEnlinea("N");
        }

        UsuarioJpaController jpau = new UsuarioJpaController(emf);

        User user = jpau.findUsuarioById(Long.valueOf(cashierModel.getIdAuth()));

        entrega.setIdUsuarioautorizante(new Usuario(Long.valueOf(user.getId())));

        entrega.setTipoEntrega(cashierModel.getDeliveryType());
        entrega.setEntregaformadepagoList(new ArrayList<Entregaformadepago>());
        Long idTransExceed = 0L;
        if (cashierModel.getIdTransExceeded() != "") {
            String cadena = cashierModel.getIdTransExceeded();
            idTransExceed = Long.valueOf(cadena);
        }
        entrega.setTransaccionExcedida(BigInteger.valueOf(idTransExceed));

        return entrega;
    }

    /**
     * Method toJPA.
     * 
     * @param entrega Entrega
     * @param paymentMethod CommitmentPayment
     * @param tmpCurrencyDetails CommitmentCurrencyDetails
     * @return crjpa.Entregaformadepago
     */
    private crjpa.Entregaformadepago toJPA(Entrega entrega, CommitmentPayment paymentMethod,
                                           CommitmentCurrencyDetails tmpCurrencyDetails) {

        Entregaformadepago entregaFDP;
        Monedadenominacion monedaDenominacion;

        // Monedadenominacion(Long id, int codigo, String nombre, BigDecimal
        // multiplo, Date fecha)
        monedaDenominacion = new Monedadenominacion(tmpCurrencyDetails.getIdCurrencyDetail(),
                (int) tmpCurrencyDetails.getCodeCurrency(), tmpCurrencyDetails.getName().toString(),
                CRBigDecimal.ONE.getValue(), "S", null);

        entregaFDP = new Entregaformadepago();
        // entregaFDP.setId(null);
        entregaFDP.setIdEntrega(entrega);
        entregaFDP.setIdMonedadenominacion(monedaDenominacion);
        entregaFDP.setBilletes((int) tmpCurrencyDetails.getBills());
        entregaFDP.setCodigoMonedaDenominacion((int) tmpCurrencyDetails.getCodeCurrency());
        entregaFDP.setIdFormadepago(new Formadepago(Long.valueOf(paymentMethod.getCode())));
        entregaFDP.setEstaactivo("S");
        entregaFDP.setEstasincronizado("N");
        entregaFDP.setMonto(tmpCurrencyDetails.getAmount().getValue());

        return entregaFDP;
    }

    /**
     * Method fromJPA.
     * 
     * @param entrega Entrega
     * @return CashierCommitment
     */
    public CashierCommitment fromJPA(Entrega entrega) {

        FormadepagoJpaController jpaPMController = new FormadepagoJpaController(this.emf);
        MonedaJpaController jpamoneda = new MonedaJpaController(emf);
        CashierCommitment cashierModel = new CashierCommitment();

        // cashierModel.setPaymentMethodList(null);
        ArrayList<CommitmentPayment> paymentsList = new ArrayList<CommitmentPayment>();
        TreeMap<BigDecimal, CommitmentCurrency> currencyMap = new TreeMap<BigDecimal, CommitmentCurrency>();
        for (Entregaformadepago commitment : entrega.getEntregaformadepagoList()) {
            BigDecimal currencyIndex = new BigDecimal(commitment.getIdFormadepago().getId());
            CommitmentPayment payment = new CommitmentPayment();
            payment.setCashierDeliveryParent(cashierModel);
            PaymentMethod jpaPaymentMethod = jpaPMController
                    .findFormadepagoById(commitment.getIdFormadepago().getId());
            payment.setName(jpaPaymentMethod.getDescription());
            payment.setPaymentType(jpaPaymentMethod.getPaymentType());
            CRBigDecimal paymentTotal = CRBigDecimal.ZERO;
            payment.setTotalCurrency(paymentTotal);

            // ArrayList<CommitmentCurrency> tmp =
            // currenciesFromJPA(commitment,commitment.getIdFormadepago().getId(),payment);

            ArrayList<CommitmentCurrency> currenciesList = new ArrayList<CommitmentCurrency>();
            CommitmentCurrency currency = new CommitmentCurrency();
            currency.setIdCurrency(commitment.getIdMonedadenominacion().getId());
            // currency.setNameCurrency(commitment.getIdMonedadenominacion().getNombre());
            currency.setParentModel(payment.getCashierDeliveryParent());
            currency.setSymbolCurrency(commitment.getIdMonedadenominacion().getIdMoneda().getSimbolo());
            currency.setCurrencyAmount(new CRBigDecimal(commitment.getMonto().doubleValue()));
            TreeMap<BigDecimal, CommitmentCurrencyDetails> detailsList = new TreeMap<BigDecimal, CommitmentCurrencyDetails>();
            CommitmentCurrencyDetails detail = new CommitmentCurrencyDetails();
            detail.setParent(currency);
            detail.setIdCurrencyDetail(commitment.getIdMonedadenominacion().getId());
            detail.setAmount(new CRBigDecimal(commitment.getMonto().doubleValue()));
            detail.setMoney(jpamoneda.fromJpa(commitment.getIdMonedadenominacion().getIdMoneda()));
            detail.setName(CRBigDecimal.valueOf(Double.valueOf(commitment.getIdMonedadenominacion().getNombre())));
            // CRBigDecimal.valueOf(Long.valueOf(commitment.getIdMonedadenominacion().getNombre()))
            detail.setBills(commitment.getBilletes());
            detail.setCodeCurrency(commitment.getIdMonedadenominacion().getCodigo());
            detailsList.put(new BigDecimal(commitment.getIdMonedadenominacion().getNombre().trim()), detail);

            currency.setCurrencyDetails(detailsList);
            currency.setCurrencyAmount(detail.getName().multiply(new CRBigDecimal(detail.getBills())));
            currenciesList.add(currency);
            if (currencyMap.containsKey(currencyIndex)) {
                CommitmentCurrency temporalCurrency = currencyMap.get(currencyIndex);
                temporalCurrency
                        .setCurrencyAmount(temporalCurrency.getCurrencyAmount().add(currency.getCurrencyAmount()));
                currencyMap.put(currencyIndex, temporalCurrency);
            } else {
                currencyMap.put(currencyIndex, currency);
            }
            payment.setCurrencies(currenciesList);

            paymentsList.add(payment);

        }
        cashierModel.setPaymentMethodList(paymentsList);
        cashierModel.setId(entrega.getId());
        cashierModel.setId_sesion(entrega.getIdSesion().getId());
        cashierModel.setNumber(entrega.getNumero());
        cashierModel.setDate(new SimpleDateFormat("dd/MM/yyyy").format(entrega.getFecha()));
        cashierModel.setTime(new SimpleDateFormat("HH:mm").format(entrega.getFecha()));
        cashierModel.setMonto_recaudado(new CRBigDecimal(entrega.getMontoRecaudado().doubleValue()));
        cashierModel.setMonto_fondo(new CRBigDecimal(entrega.getMontoFondo().doubleValue()));
        cashierModel.setIdCashier(entrega.getIdSesion().getIdUsuario().getFicha());
        cashierModel.setIdAuth(String.valueOf(entrega.getIdUsuarioautorizante().getFicha()));
        cashierModel.setPos(String.valueOf(entrega.getIdSesion().getIdCaja()));
        Collection<CommitmentCurrency> commiments = new ArrayList<CommitmentCurrency>();
        for (BigDecimal key : currencyMap.keySet()) {
            commiments.add(currencyMap.get(key));
        }
        cashierModel.setCommitmentCurrencyList(commiments);
        if (entrega.getEnlinea().equalsIgnoreCase("S")) {
            cashierModel.setOnLine(true);
        } else {
            cashierModel.setOnLine(false);
        }
        cashierModel.setDeliveryType(entrega.getTipoEntrega());

        if (!entrega.getTransaccionExcedida().equals("0")) {
            /*
             * Se excedio en el monto para la entrega
             */
            cashierModel.setIdTransExceeded("" + entrega.getTransaccionExcedida());
        }

        return cashierModel;

    }

    /**
     * Method getLastDelivery.
     * 
     * @param deliveryType String
     * @return CashierCommitment
     */
    public CashierCommitment getLastDelivery(String deliveryType) {
        Entrega tmp = null;
        CashierCommitment result = null;
        try {

            EntityManager em = jpacontroller.getEntityManager();
            Query query = em.createQuery(SELECTENTREGA);
            query.setParameter("tipoentrega", deliveryType);
            query.setMaxResults(1);
            tmp = (Entrega) query.getSingleResult();

        } catch (javax.persistence.NoResultException ex) {
            tmp = null;
        }
        if (tmp != null) {
            result = fromJPA(tmp);
        }

        return result;
    }

    /**
     * Method getCashierCashPartialDelivery
     */

    public List<Entrega> getAllPartialDelivery() {

        List<Entrega> resp = new ArrayList<Entrega>();

        try {
            EntityManager em = jpacontroller.getEntityManager();
            Query query = em.createQuery(SELECTALLPARTIALDELIVERY);
            query.setParameter("tipoentrega", "P");
            resp = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public List<Entrega> getPartialDeliveryByDate(String date) {

        List<Entrega> resp = new ArrayList<Entrega>();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            EntityManager em = jpacontroller.getEntityManager();
            Query query = em.createQuery(SELECTPARTIALDELIVERY);
            query.setParameter("date", formatter.parse(date));
            query.setParameter("tipoentrega", "P");
            resp = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    /**
     * Method create.
     * 
     * @param cashierModel CashierCommitment
     * @return long
     * @throws JpaException
     */
    public long create(CashierCommitment cashierModel) throws JpaException {
        EntityManager em = null;

        crjpa.Entrega entrega = toJPA(cashierModel);

        List<Entregaformadepago> entregaFDPList = new ArrayList<Entregaformadepago>();

        for (Iterator<CommitmentPayment> iteratorPM = cashierModel.getPaymentMethodList().iterator(); iteratorPM
                .hasNext();) {
            CommitmentPayment paymentMethod = iteratorPM.next();

            for (Iterator<CommitmentCurrency> iterator = paymentMethod.getCurrencies().iterator(); iterator
                    .hasNext();) {
                CommitmentCurrency tmpCurrency = iterator.next();

                for (Iterator<CommitmentCurrencyDetails> iterator2 = tmpCurrency.getCurrencyDetails().values()
                        .iterator(); iterator2.hasNext();) {
                    CommitmentCurrencyDetails tmpCurrencyDetails = iterator2.next();

                    Entregaformadepago entregaFDP = toJPA(entrega, paymentMethod, tmpCurrencyDetails);
                    entregaFDP.setIdEntrega(entrega);
                    entregaFDPList.add(entregaFDP);
                }
            }
        }

        if (entregaFDPList.size() != 0) {
            entrega.setEntregaformadepagoList(entregaFDPList);
        } else {
            return -1;
        }

        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entrega);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new JpaException();
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }

        return entrega.getId();
    }

    /**
     * Method getDeliveredAmmountByCurrentSession.
     * 
     * @param currentSession Session
     * @return CRBigDecimal
     * @throws Exception
     */
    public CRBigDecimal getDeliveredAmmountByCurrentCashier(Session currentSession) throws Exception {
        CRBigDecimal deliveredAmmount = CRBigDecimal.ZERO;
        EntityManager em = jpacontroller.getEntityManager();
        int cashierId = currentSession.getCashier().getIdEPA();

        String qSesionesDeCajero = "SELECT DISTINCT(s.id) " + "FROM CRPOS.sesion AS s "
                + "INNER JOIN CRPOS.usuario AS u ON s.id_usuario = u.id "
                + "WHERE s.fecha_inicio BETWEEN TIMESTAMP(CURDATE()) AND TIMESTAMP(DATE_ADD(CURDATE(), INTERVAL 1 DAY)) "
                + "AND u.ficha = " + cashierId;

        try {

            String q = "SELECT SUM(monto_recaudado) " + "FROM CRPOS.entrega " + "WHERE id_sesion IN ("
                    + qSesionesDeCajero + ");";
            Query query = em.createNativeQuery(q);
            Object result = query.getSingleResult();
            if (result != null) {
                deliveredAmmount = CRBigDecimal.valueOf(((BigDecimal) result).doubleValue());
            }
        } catch (Exception ex) {
            throw ex;
        }
        return deliveredAmmount;
    }

    /**
     * Method getDeliveredAmmountByCurrentSession.
     * 
     * @param currentSession Session
     * @return CRBigDecimal
     * @throws Exception
     */
    @Deprecated
    public CRBigDecimal getDeliveredAmmountByCurrentSession(Session currentSession) throws Exception {
        CRBigDecimal deliveredAmmount = CRBigDecimal.ZERO;
        try {
            EntityManager em = jpacontroller.getEntityManager();
            Query query = em.createNativeQuery("SELECT SUM(monto_recaudado) FROm CRPOS.entrega WHERE id_sesion="
                    + currentSession.getId() + ";");
            Object result = query.getSingleResult();
            if (result != null) {
                deliveredAmmount = CRBigDecimal.valueOf(((BigDecimal) result).doubleValue());
            }
        } catch (Exception ex) {
            throw ex;
            // ex.printStackTrace();
        }
        return deliveredAmmount;
    }
}
