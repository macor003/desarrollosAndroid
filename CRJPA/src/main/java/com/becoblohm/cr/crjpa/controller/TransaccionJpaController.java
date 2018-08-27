/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.AnulDev;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.CreditsMovement.CreditMovementType;
import com.becoblohm.cr.models.DeliveryCondition;
import com.becoblohm.cr.models.DocumentType;
import com.becoblohm.cr.models.Donation;
import com.becoblohm.cr.models.Payment;
import com.becoblohm.cr.models.PrePrintedFiscalReceipt;
import com.becoblohm.cr.models.SerialPrint;
import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.models.Transaction.Source;
import com.becoblohm.cr.models.Transaction.SourceTransactionType;
import com.becoblohm.cr.models.Transaction.TransactionPhase;
import com.becoblohm.cr.models.Transaction.TransactionState;
import com.becoblohm.cr.models.TransactionAudit;
import com.becoblohm.cr.models.TransactionDocument;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Cliente;
import crjpa.Comprobantefiscalpreimpreso;
import crjpa.Devolucion;
import crjpa.Rolloauditoria;
import crjpa.RolloauditoriaJpaController;
import crjpa.Tipodocumento;
import crjpa.Transaccion;
import crjpa.Transaccionarticulo;
import crjpa.Transaccionarticuloservicio;
import crjpa.Transaccionauditoria;
import crjpa.Transaccioncliente;
import crjpa.Transacciondocumento;
import crjpa.Transaccionfase;
import crjpa.Transaccionformadepago;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TransaccionJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private final EntityManagerFactory emf;

    /**
     * Field jpaArticleController.
     */
    private final ArticuloJpaController jpaArticleController;

    /**
     * Field jpaTransactionDocument.
     */
    private final TransaccionDocumentoJpaController jpaTransactionDocument;

    /**
     * Field jpaPaymentMethod.
     */
    private final TransaccionFormadepagoJpaController jpaPaymentMethod;

    /**
     * Field jpaClientController.
     */
    private final ClienteJpaController jpaClientController;

    /**
     * Field jpaTransactionController.
     */
    private final crjpa.TransaccionJpaController jpaTransactionController;

    /**
     * Field jpaUserController.
     */
    private final UsuarioJpaController jpaUserController;

    /**
     * Field entityName.
     */
    private static String entityName = "Transaccion";

    /**
     * Field TR. (value is 2)
     */
    static final int TR = 2;

    /**
     * Field PAYCREDIT. (value is 3)
     */
    static final int PAYCREDIT = 3;

    /**
     * Constructor for TransaccionJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TransaccionJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        jpaArticleController = new ArticuloJpaController(this.emf);
        jpaPaymentMethod = new TransaccionFormadepagoJpaController(this.emf);
        jpaClientController = new ClienteJpaController(this.emf);
        jpaTransactionDocument = new TransaccionDocumentoJpaController(this.emf);
        jpaTransactionController = new crjpa.TransaccionJpaController(this.emf);
        jpaUserController = new UsuarioJpaController(this.emf);
    }

    /**
     * Method findTransaccionEntities.
     * 
     * @return List<Transaction>
     */
    public List<Transaction> findTransaccionEntities() {
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
        for (Transaccion tmp : jpaTransactionController.findTransaccionEntities()) {
            transactionList.add(fromJPA(tmp));
        }
        return transactionList;
    }

    public List<Transaccion> findTrEntities() {
        ArrayList<Transaccion> transactionList = new ArrayList<Transaccion>();
        for (Transaccion tmp : jpaTransactionController.findTransaccionEntities()) {
            transactionList.add(tmp);
        }
        return transactionList;
    }

    public Transaccion findTransactionById(long id) {
        Transaccion transaction = null;
        for (Transaccion tmp : jpaTransactionController.findTransaccionEntities()) {
            if (tmp.getId() == id) {
                transaction = tmp;
                break;
            }
        }
        return transaction;
    }

    /**
     * Method findTransaccionWatingSale.
     * 
     * @param value Date
     * @param estado String
     * @return List<Transaction>
     */
    public List<Transaction> findTransaccionWatingSale(Date value, String estado) {
        Date fechaInicial = JPAUtils.cleanDate(value, true);
        Date fechaFinal = JPAUtils.cleanDate(value, false);// cal.getTime();
        EntityManager em = null;
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
        try {
            em = jpaTransactionController.getEntityManager();
            Query query = em
                    .createQuery("select t from Transaccion t where t.estado = :estado and t.fecha >= :fechaInicial AND t.fecha <= :fechaFinal");

            query.setParameter("fechaInicial", fechaInicial);
            query.setParameter("fechaFinal", fechaFinal);
            query.setParameter("estado", estado);

            List<Transaccion> transList = query.getResultList();
            for (Transaccion tmp : transList) {
                transactionList.add(fromJPA(tmp));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            transactionList = new ArrayList<Transaction>();

        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
        return transactionList;
    }

    /**
     * Method getLastTransaction.
     * 
     * @return Transaction
     */
    public Transaction getLastTransaction() {
        Transaction result = null;
        EntityManager em = null;
        try {
            em = jpaTransactionController.getEntityManager();
            Query query = em
                    .createQuery("select t from Transaccion t where t.numero = (select max(te.numero) from Transaccion te where te.estado = '"
                            + TransactionState.COMPLETE.getValue() + "') and t.estado='"
                            + TransactionState.COMPLETE.getValue() + "'");
            query.setMaxResults(1);
            result = fromJPA((Transaccion) query.getSingleResult());

        } catch (Exception ex) {
            ex.printStackTrace();
            result = null;
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
        return result;
    }

    /**
     * Method fromJPA.
     * 
     * @param obj Transaccion
     * @return Transaction
     */
    public Transaction fromJPA(Transaccion obj) {

        TipodocumentoJpaController docJpaController = new TipodocumentoJpaController(emf);
        Transaction trans = new Transaction();
        TipodescuentoJpaController discountJpaController = new TipodescuentoJpaController(this.emf);
        String rbtdsc = "";
        if (obj != null && obj.getId() > 0) {
            trans.setDate(obj.getFecha());
            trans.setPosId(String.valueOf(obj.getIdSesion().getIdCaja()));
            trans.setDeliveryInfo(null);
            Tipodocumento supportSaleDocType = null;
            for (Transacciondocumento doc : obj.getTransacciondocumentoList()) {
                if (ActiveValues.get(doc.getIdTipodocumento().getEssoporteventa()).getValue()) {
                    supportSaleDocType = doc.getIdTipodocumento();
                    break;
                }
            }

            if (supportSaleDocType != null) {
                trans.setDocument(docJpaController.fromJPA(supportSaleDocType));
            } else if (obj.getTransacciondocumentoList().size() > 0) {
                trans.setDocument(docJpaController
                        .fromJPA(obj.getTransacciondocumentoList().get(0).getIdTipodocumento()));
            }

            trans.setDonation(new Donation(new CRBigDecimal(obj.getDonacion().doubleValue())));
            trans.setFiscalId(obj.getNumeroFiscal());
            trans.setNumber(String.valueOf(obj.getNumero()));
            trans.setId(obj.getId());
            ArrayList<Payment> paymentsList = new ArrayList<Payment>();
            BigDecimal totalpagado = BigDecimal.ZERO;
            for (Transaccionformadepago paymentMethod : obj.getTransaccionformadepagoList()) {
                if (ActiveValues.get(paymentMethod.getEstaactivo()).getValue()) {
                    totalpagado = totalpagado.add(paymentMethod.getMontoMonedaLocal());
                    paymentsList.add(jpaPaymentMethod.fromJPA(paymentMethod));
                }
            }
            trans.setPayments(paymentsList);
            trans.setPosId(String.valueOf(obj.getIdSesion().getIdCaja()));
            if (obj.getIdSerialimpresora() != null) {
                trans.setPrinterSerial(obj.getIdSerialimpresora().getSerial());
            }
            if (obj.getIdRolloauditoria() != null) {
                trans.setIdPaperRoll(obj.getIdRolloauditoria().getId());
            }
            trans.setSaleOrigin(Source.get(obj.getOrigenTransaccion()));
            trans.setStatus(obj.getEstado());

            trans.setTransactionType(SourceTransactionType.get(obj.getTipo()));
            if (obj.getNumeroIdentificacionCliente() != null) {
                trans.setClient(jpaClientController.fromJPA(obj.getNumeroIdentificacionCliente()));
            }
            trans.setUser(jpaUserController.fromJPA(obj.getIdUsuario()));

            HashMap<Integer, DeliveryCondition> deliveryInfo = new HashMap<Integer, DeliveryCondition>();
            TipodescuentoJpaController tipoDescuentoJpaController = new TipodescuentoJpaController(this.emf);

            Article tmpArticle = null;
            for (Transaccionarticulo transaccionArticulo : obj.getTransaccionarticuloList()) {

                Article article = jpaArticleController.fromJPA(transaccionArticulo);
                tmpArticle = article;
                trans.addArticle(article);
                if (article.getDeliveryCondition() != null) {
                    deliveryInfo.put(article.getDeliveryCondition().getId(), article.getDeliveryCondition());
                }
            }
            trans.setDeliveryInfo(deliveryInfo);
            List<Transacciondocumento> documentList = obj.getTransacciondocumentoList();
            for (Transacciondocumento transaccionDocumento : documentList) {
                TransactionDocument transDocument = jpaTransactionDocument.fromJPA(transaccionDocumento);
                trans.getDocuments().add(transDocument);
            }
            TransaccionAuditoriaJpaController auditController = new TransaccionAuditoriaJpaController(emf);
            for (Transaccionauditoria jpaAudit : obj.getTransaccionauditoriaList()) {
                TransactionAudit audit = auditController.fromJPA(jpaAudit);
                trans.addTransactionAudit(audit);
            }

            trans.setTaxAmount(new CRBigDecimal(obj.getTotalImpuesto().doubleValue()));
            trans.setTaxes(new HashMap());
            trans.setTotalCost(new CRBigDecimal(obj.getTotalTransaccion().doubleValue()));
            trans.setTotalPay(new CRBigDecimal(totalpagado.doubleValue()));
            if (obj.getIdAnulaciontransaccion() != null) {
                trans.setIdCancellation(obj.getIdAnulaciontransaccion().getId());
            }
            if (tmpArticle != null) {
                trans.setDeliveryCondition(tmpArticle.getDeliveryCondition());
            } else {
                trans.setDeliveryCondition(new DeliveryCondition(1, "CAJA", "C"));
            }
            if (obj.getIdComprobantefiscalpreimpreso() != null) {
                ComprobantefiscalpreimpresoJpaController controller = new ComprobantefiscalpreimpresoJpaController(
                        emf);
                PrePrintedFiscalReceipt tmp = controller.findById(obj.getIdComprobantefiscalpreimpreso().getId());
                if (tmp != null) {
                    trans.setResolution(tmp);
                }
            }
        } else {
            trans = null;
        }
        return trans;
    }

    /**
     * Method findByNumero.
     * 
     * @param value long
     * @return Transaction
     */
    public Transaction findByNumero(long value) {
        Transaction tr = null;
        EntityManager em = jpaTransactionController.getEntityManager();
        Query query = em.createQuery("SELECT t FROM Transaccion t WHERE t.numero = :numero AND t.estado = 'F'");
        query.setParameter("numero", value);
        query.setMaxResults(1);
        try {
            Transaccion tmp = (Transaccion) query.getSingleResult();
            if (tmp != null) {
                tr = fromJPA(tmp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            tr = null;
        } finally {
            em.clear();
            em.close();
        }
        return tr;
    }

    /**
     * Method toJPA.
     * 
     * @param obj Transaction
     * @param session Session
     * @return Transaccion
     */
    private Transaccion toJPA(Transaction obj, Session session) {
        Rolloauditoria auditRollId = null;

        Transaccion trans = new Transaccion();

        if (obj.getNumber() != null && !obj.getNumber().equalsIgnoreCase("0")) {
            trans.setNumero(Long.valueOf(obj.getNumber()));
        }

        ClienteJpaController clientJpaController = new ClienteJpaController(this.emf);
        TransaccionArticuloJpaController transArtcontroller = new TransaccionArticuloJpaController(this.emf);
        SesionJpaController sesionjpa = new SesionJpaController(emf);
        RolloauditoriaJpaController jpa = new RolloauditoriaJpaController(emf);
        if (jpa.getRolloauditoriaCount() > 0) {
            auditRollId = jpa.findRolloauditoriaEntities().get(jpa.getRolloauditoriaCount() - 1);
        }

        if (obj.getPrinterSerial() != null) {
            trans.setIdRolloauditoria(auditRollId);
        }

        Cliente client = null;
        if (obj.getClient().getIdNumber() != null) {
            client = clientJpaController.toJPA(obj.getClient());
        }
        if (obj.getId() >= 0) {
            trans.setId(obj.getId());
        } else {
            trans.setId(null);
        }
        // //System.err.println("TOJPA TRANS:"+trans.getId());

        trans.setNumeroIdentificacionCliente(client);
        trans.setIdSesion(sesionjpa.toJPA(session));

        // if(obj instanceof Transaction){
        trans.setIdAnulaciontransaccion(null);
        // }else{
        // trans.setIdAnulaciontransaccion(jpaTransactionController.findTransaccion(new
        // Long(obj.getId())));
        // }
        trans.setIdUsuario(trans.getIdSesion().getIdUsuario());

        if (obj.getFiscalId() > 0) {
            trans.setNumeroFiscal(obj.getFiscalId());
        } else {
            trans.setNumeroFiscal(-1);
        }

        // //System.err.println(obj.getFiscalId()+":"+trans.getNumeroFiscal());

        trans.setCajero(trans.getIdUsuario().getFicha());
        trans.setTipo(obj.getTransactionType().getValue());
        trans.setEstado(obj.getStatus());
        trans.setFecha(obj.getDate());
        trans.setTotalBase(obj.getBaseAmount().getValue());
        trans.setTotalImpuesto(obj.getTaxAmount().getValue());

        if (obj.getTotalRebate() != null) {
            trans.setTotalRebaja(obj.getTotalRebate().getValue());
        }

        if (obj.getIdPaperRoll() >= 0) {
            trans.setIdRolloauditoria(auditRollId);
        }

        trans.setVuelto(obj.getChange().getValue());
        trans.setDonacion(obj.getDonation().getAmount().getValue());
        if (obj.getPrinterSerial() == null) {
            trans.setIdSerialimpresora(null);
        } else {
            SerialimpresoraJpaController serial = new SerialimpresoraJpaController(emf);
            SerialPrint tmpserial = serial.findSerialImpresoraBySerial(obj.getPrinterSerial());
            if (tmpserial != null) {
                trans.setIdSerialimpresora(serial.toJPA(tmpserial));
            } else {
                trans.setIdSerialimpresora(null);
            }
        }
        trans.setOrigenTransaccion(obj.getSaleOrigin().getValue());
        trans.setEstasincronizado("N");
        trans.setTotalTransaccion(trans.getTotalBase().add(trans.getTotalImpuesto()));
        trans.setDonacion(obj.getDonation().getAmount().getValue());
        trans.setRenglones(obj.getArticlesIndex().size());

        if (obj.getId() > 0) {
            Transaccion oldTransaction = jpaTransactionController.findTransaccion(obj.getId());

            if (oldTransaction != null) {
                // trans.setDevolucionList(oldTransaction.getDevolucionList());
                // trans.setTransaccionarticuloList(oldTransaction.getTransaccionarticuloList());
                trans.setTransaccionclienteList(oldTransaction.getTransaccionclienteList());
                trans.setTransacciondocumentoList(oldTransaction.getTransacciondocumentoList());
                trans.setTransaccionfaseList(oldTransaction.getTransaccionfaseList());
                trans.setTransaccionformadepagoList(oldTransaction.getTransaccionformadepagoList());
                trans.setTransaccionList(oldTransaction.getTransaccionList());
                trans.setPagocreditoList(oldTransaction.getPagocreditoList());
                ArrayList<Transaccionarticulo> tmp = new ArrayList<Transaccionarticulo>();
                for (Article art : obj.getArticles()) {
                    if (art.getTransactionArticleId() > 0) {
                        tmp.add(transArtcontroller.toJPA(art, trans));
                    }
                }
                trans.setTransaccionarticuloList(tmp);
                trans.setIdAnulaciontransaccion(oldTransaction.getIdAnulaciontransaccion());
            }
        }
        if (obj.getResolution() == null) {
            trans.setIdComprobantefiscalpreimpreso(null);
        } else {
            ComprobantefiscalpreimpresoJpaController resolucion = new ComprobantefiscalpreimpresoJpaController(
                    emf);
            PrePrintedFiscalReceipt tmpResolution = resolucion.findById(obj.getResolution().getId());
            if (tmpResolution != null) {
                trans.setIdComprobantefiscalpreimpreso(resolucion.toJPA(tmpResolution));
            } else {
                trans.setIdSerialimpresora(null);
            }
        }
        return trans;
    }

    /**
     * Method findTransaccion.
     * 
     * @param long1 Long
     * @return Transaction
     */
    public Transaction findTransaccion(Long long1) {

        Transaccion result = jpaTransactionController.findTransaccion(long1);

        if (result == null) {
            return null;
        } else {
            return fromJPA(result);
        }
    }

    /**
     * Method findByDate.
     * 
     * @param fechaInicial Date
     * @param fechaFinal Date
     * @return List<Transaction>
     */
    public List<Transaction> findByDate(Date fechaInicial, Date fechaFinal) {

        fechaInicial = JPAUtils.cleanDate(fechaInicial, true);
        fechaFinal = JPAUtils.cleanDate(fechaFinal, false);

        List<Transaction> trList = new ArrayList<Transaction>();
        EntityManager em = jpaTransactionController.getEntityManager();
        try {
            List<Transaccion> tmp = new ArrayList<Transaccion>();
            Query query = emf.createEntityManager()
                    .createQuery("SELECT t FROM Transaccion t WHERE t.fecha >= :fechaInicial AND t.fecha <= :fechaFinal AND t.estado = '"
                            + TransactionState.COMPLETE.getValue() + "'");
            query.setParameter("fechaInicial", fechaInicial);
            query.setParameter("fechaFinal", fechaFinal);
            tmp = query.getResultList();

            for (Transaccion transTmp : tmp) {
                trList.add(fromJPA(transTmp));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.clear();
            em.close();
        }
        return trList;
    }

    /**
     * Method findByDate.
     * 
     * @param fechaInicial String
     * @param fechaFinal String
     * @return List<Transaction>
     */
    public List<Transaction> findByDate(String fechaInicial) {

        List<Transaction> trList = new ArrayList<Transaction>();
        EntityManager em = jpaTransactionController.getEntityManager();
        try {
            String date = fechaInicial.substring(6) + "-" + fechaInicial.substring(3, 5) + "-"
                    + fechaInicial.substring(0, 2);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            List<Transaccion> tmp = new ArrayList<Transaccion>();
            Query query = emf.createEntityManager()
                    .createQuery("SELECT t FROM Transaccion t WHERE t.fecha < :fechaInicial AND t.estado = '"
                            + TransactionState.COMPLETE.getValue() + "'");
            Date initialDate = JPAUtils.cleanDate(formatter.parse(date), true);
            query.setParameter("fechaInicial", initialDate);
            tmp = query.getResultList();

            for (Transaccion transTmp : tmp) {
                trList.add(fromJPA(transTmp));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.clear();
            em.close();
        }
        return trList;
    }

    /**
     * 
     * 
     * 
     * 
     * @param fechaBuscada Date
     * @param ficha long
     * @return List<Transaction>
     * @throws JpaException
     */
    public List<Transaction> findByDate(Date fechaBuscada, long ficha) {
        return findByDate(fechaBuscada, fechaBuscada, ficha);
    }

    /**
     * Method findByDate.
     * 
     * @param fechaInicio Date
     * @param fechaFin Date
     * @param ficha long
     * @return List<Transaction>
     */
    public List<Transaction> findByDate(Date fechaInicio, Date fechaFin, long ficha) {

        Date fechaInicial = JPAUtils.cleanDate(fechaInicio, true);
        Date fechaFinal = JPAUtils.cleanDate(fechaFin, false);

        EntityManager em = jpaTransactionController.getEntityManager();
        List<Transaction> trList = new ArrayList<Transaction>();
        try {
            List<Transaccion> tmp = new ArrayList<Transaccion>();
            String q = "SELECT t FROM Transaccion t WHERE t.fecha >= :fechaInicial AND t.fecha <= :fechaFinal AND t.idUsuario.ficha =:ficha AND t.estado = '"
                    + TransactionState.COMPLETE.getValue() + "'";
            Query query = em.createQuery(q);
            query.setParameter("fechaInicial", fechaInicial);
            query.setParameter("fechaFinal", fechaFinal);
            query.setParameter("ficha", ficha);
            tmp = query.getResultList();
            for (Transaccion transTmp : tmp) {
                trList.add(fromJPA(transTmp));
            }
            return trList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return trList;
        } finally {
            em.clear();
            em.close();
        }
    }

    /**
     * Method findByDate.
     * 
     * @param value Date
     * @param tDoc DocumentType
     * @param ficha long
     * @return List<Transaction>
     */
    public List<Transaction> findByDate(Date value, DocumentType tDoc, long ficha) {

        Date fechaInicial = JPAUtils.cleanDate(value, true);
        Date fechaFinal = JPAUtils.cleanDate(value, false);
        List<Transaction> trList = new ArrayList<Transaction>();
        EntityManager em = jpaTransactionController.getEntityManager();
        try {
            Query query = em
                    .createQuery("SELECT p FROM Transacciondocumento t JOIN t.idTransaccion p WHERE t.idTipodocumento = :idTipoDoc AND t.idTransaccion.fecha >= :fechaInicial AND t.idTransaccion.fecha <= :fechaFinal AND t.idTransaccion.idUsuario.ficha =:ficha AND t.idTransaccion.estado = '"
                            + TransactionState.COMPLETE.getValue() + "'");
            query.setParameter("fechaInicial", fechaInicial);
            query.setParameter("fechaFinal", fechaFinal);
            query.setParameter("idTipoDoc", new Tipodocumento(tDoc.getIdTipoDoc()));
            query.setParameter("ficha", ficha);

            List<Transaccion> tmp = query.getResultList();
            for (Transaccion transTmp : tmp) {
                trList.add(fromJPA(transTmp));
            }
            return trList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return trList;
        } finally {
            em.clear();
            em.close();
        }
    }

    /**
     * Method findByDate.
     * 
     * @param date1 Date
     * @param date2 Date
     * @param ficha long
     * @param reporteZ boolean
     * @return List<Transaccion>
     */
    public List<Transaccion> findByDate(Date date1, Date date2, long ficha, boolean reporteZ) {

        Date fechaInicial = JPAUtils.cleanDate(date1, true);
        Date fechaFinal = JPAUtils.cleanDate(date2, false);
        EntityManager em = jpaTransactionController.getEntityManager();
        try {
            String zReportQuery = "SELECT t FROM Transaccion t WHERE t.fecha >= :fechaInicial AND t.fecha <= :fechaFinal AND t.estado = '"
                    + TransactionState.COMPLETE.getValue() + "'";
            String xReportQuery = "SELECT t FROM Transaccion t WHERE t.fecha >= :fechaInicial AND t.fecha <= :fechaFinal AND t.idUsuario.ficha =:ficha AND t.estado = '"
                    + TransactionState.COMPLETE.getValue() + "'";
            Query query = null;

            if (reporteZ) {
                query = em.createQuery(zReportQuery);
            } else {
                fechaFinal = JPAUtils.cleanDate(date1, false);
                query = em.createQuery(xReportQuery);
                query.setParameter("ficha", ficha);

            }
            query.setParameter("fechaInicial", fechaInicial);
            query.setParameter("fechaFinal", fechaFinal);

            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<Transaccion>();
        } finally {
            em.clear();
            em.close();
        }
    }

    /**
     * Method findLastTransactionBySource_TransactionType.
     * 
     * @param source Source
     * @param transactionType SourceTransactionType
     * @return Transaction
     */
    public Transaction findLastTransactionBySource_TransactionType(Source source,
                                                                   SourceTransactionType transactionType) {
        Transaction result = null;

        try {

            EntityManager em = jpaTransactionController.getEntityManager();
            Query query = em
                    .createQuery("select t from Transaccion t where t.id = (select max(te.id) from Transaccion te where te.tipo = :tipo and te.origenTransaccion = :origen  and te.estado = '"
                            + TransactionState.COMPLETE.getValue() + "')");
            query.setParameter("origen", source.getValue());
            query.setParameter("tipo", transactionType.getValue());
            query.setMaxResults(1);
            Transaccion tmp = (Transaccion) query.getSingleResult();
            result = fromJPA(tmp);
            em.clear();
        } catch (javax.persistence.NoResultException ex) {
            ex.printStackTrace();
            result = null;

        } catch (Exception ex) {
            ex.printStackTrace();
            result = null;

        }

        return result;
    }

    /**
     * Method findLastTransactionByTransactionType.
     * 
     * @param transactionType SourceTransactionType
     * @return Transaction
     */
    public Transaction findLastTransactionByTransactionType(SourceTransactionType transactionType) {
        Transaction result = null;

        try {

            EntityManager em = jpaTransactionController.getEntityManager();
            Query query = em
                    .createQuery("select t from Transaccion t where t.id = (select max(te.id) from Transaccion te where te.tipo = :tipo and te.estado = '"
                            + TransactionState.COMPLETE.getValue() + "')");
            query.setParameter("tipo", transactionType.getValue());
            query.setMaxResults(1);
            Transaccion tmp = (Transaccion) query.getSingleResult();
            result = fromJPA(tmp);
            em.clear();
        } catch (javax.persistence.NoResultException ex) {
            result = null;
        }

        return result;
    }

    /**
     * Method findLastTransactionByTransactionType_Client.
     * 
     * @param client String
     * @param transactionType SourceTransactionType
     * @return Transaction
     */
    public Transaction findLastTransactionByTransactionType_Client(String client,
                                                                   SourceTransactionType transactionType) {
        Transaction result = null;

        try {

            EntityManager em = jpaTransactionController.getEntityManager();
            Query query = em
                    .createQuery("select t from Transaccion t where t.id = (select max(te.id) from Transaccion te where te.tipo = :tipo and te.estado = '"
                            + TransactionState.COMPLETE.getValue()
                            + "' and te.numeroIdentificacionCliente.numeroIdentificacionCliente = :cliente)");
            query.setParameter("tipo", transactionType.getValue());
            query.setParameter("cliente", client);
            query.setMaxResults(1);
            Transaccion tmp = (Transaccion) query.getSingleResult();
            result = fromJPA(tmp);
            em.clear();
        } catch (javax.persistence.NoResultException ex) {
            result = null;
        }

        return result;
    }

    /**
     * Method findLastTransactionToReprint.
     * 
     * @return Transaction
     */
    public Transaction findLastTransactionToReprint() {
        Transaction result = null;

        try {

            EntityManager em = jpaTransactionController.getEntityManager();
            Query query = em
                    .createQuery("select t from Transaccion t where t.id = (select max(te.id) from Transaccion te where (te.tipo =:nopresencial or te.tipo =:venta or te.tipo =:anulacion or te.tipo =:devolucion) and te.estado = '"
                            + TransactionState.COMPLETE.getValue() + "')");
            query.setParameter("nopresencial", SourceTransactionType.NonAttendance.getValue());
            query.setParameter("venta", SourceTransactionType.Sale.getValue());
            query.setParameter("anulacion", SourceTransactionType.Cancellation.getValue());
            query.setParameter("devolucion", SourceTransactionType.Refund.getValue());
            query.setMaxResults(1);
            Transaccion tmp = (Transaccion) query.getSingleResult();
            result = fromJPA(tmp);
            em.clear();
        } catch (javax.persistence.NoResultException ex) {
            result = null;
        }

        return result;
    }

    /**
     * Method findLastTransactionCompleteByNumber.
     * 
     * @param numero long
     * @return Transaction
     */
    public Transaction findLastTransactionCompleteByNumber(long numero) {
        Transaction result = null;

        try {

            EntityManager em = jpaTransactionController.getEntityManager();
            Query query = em
                    .createQuery("select t from Transaccion t where t.numero = :numero and (t.tipo =:venta or t.tipo =:anulacion or t.tipo =:devolucion) and t.estado = '"
                            + TransactionState.COMPLETE.getValue() + "'");
            query.setParameter("venta", SourceTransactionType.Sale.getValue());
            query.setParameter("anulacion", SourceTransactionType.Cancellation.getValue());
            query.setParameter("devolucion", SourceTransactionType.Refund.getValue());
            query.setParameter("numero", numero);
            query.setMaxResults(1);
            Transaccion tmp = (Transaccion) query.getSingleResult();
            result = fromJPA(tmp);
            em.clear();
        } catch (javax.persistence.NoResultException ex) {
            result = null;
        }

        return result;
    }

    /**
     * Method isAnulDev.
     * 
     * @param id long
     * @return boolean
     */
    public boolean isAnulDev(long id) {
        boolean result = false;

        try {
            EntityManager em = jpaTransactionController.getEntityManager();
            Query query = em
                    .createQuery("select t from Transaccion t where t.idAnulaciontransaccion.id = :id and (t.tipo =:anulacion or t.tipo =:devolucion) and t.estado = '"
                            + TransactionState.COMPLETE.getValue() + "'");
            query.setParameter("anulacion", SourceTransactionType.Cancellation.getValue());
            query.setParameter("devolucion", SourceTransactionType.Refund.getValue());
            query.setParameter("id", id);
            query.setMaxResults(1);
            Transaccion tmp = (Transaccion) query.getSingleResult();

            result = tmp != null;
            em.clear();
        } catch (javax.persistence.NoResultException ex) {
            result = false;
        }

        return result;
    }

    /**
     * Method findLastSynchronizedTransaction.
     * 
     * @return Transaction
     */
    public Transaction findLastSynchronizedTransaction() {
        Transaction result = null;

        try {

            EntityManager em = jpaTransactionController.getEntityManager();
            Query query = em
                    .createQuery("SELECT t FROM Transaccion t WHERE t.estasincronizado = 'S' AND t.tipo IN('VENTA','ANULACION','DEVOLUCION') ORDER BY t.numero DESC");
            query.setMaxResults(1);
            Transaccion tmp = (Transaccion) query.getSingleResult();
            result = fromJPA(tmp);
            em.clear();
        } catch (javax.persistence.NoResultException ex) {
            result = null;
        }

        return result;
    }

    /**
     * @param sync -> bandera true=devuelve el numero maximo de correlativo sincronizados,
     *        false = el numero maximo de correlativos no sincronizados
     * @param type -> tipo de transaccion a consultar
     * @param correlativo -> correlativo que usara como criterio de busqueda
     * 
     * 
     * @return numero de transaccion * @throws Exception
     */
    public long transactionTypeNumber(Boolean sync, int type, long correlativo) throws Exception {
        long numberOrQuantity = 0;
        String q1, q2, q3;
        q1 = q2 = q3 = null;
        String trTypes = "";
        EntityManager em = jpaTransactionController.getEntityManager();

        switch (type) {
            case TR:
                trTypes = SourceTransactionType.Sale.getValue() + "','"
                        + SourceTransactionType.Cancellation.getValue() + "','"
                        + SourceTransactionType.Refund.getValue() + "','"
                        + SourceTransactionType.NonAttendance.getValue();
                break;

            case PAYCREDIT:
                trTypes = SourceTransactionType.CreditsEpaPay.getValue() + "','"
                        + SourceTransactionType.CreditsEpaCancellation.getValue();
                break;
        }

        if (sync != null) {
            q1 = "SELECT max(t.numero) FROM Transaccion t ";
            if (sync) {
                q2 = "WHERE t.estasincronizado = 'S' AND t.tipo IN('" + trTypes + "') AND t.estado='"
                        + TransactionState.COMPLETE.getValue() + "'";
            } else {
                if (correlativo != 0) {
                    q2 = "WHERE t.estasincronizado = 'N' AND t.tipo IN('" + trTypes + "') AND t.estado='"
                            + TransactionState.COMPLETE.getValue() + "' AND t.numero < " + correlativo;
                } else {
                    q2 = "WHERE t.estasincronizado = 'N' AND t.tipo IN('" + trTypes + "') AND t.estado='"
                            + TransactionState.COMPLETE.getValue() + "'";
                }
            }
        } else {
            q1 = "SELECT count(t.id) FROM Transaccion t ";
            q2 = "WHERE t.estasincronizado = 'N' AND t.tipo IN('" + trTypes + "') AND t.estado='"
                    + TransactionState.COMPLETE.getValue() + "' AND t.numero >= " + correlativo;
        }

        try {
            q3 = q1 + q2;
            Query query = em.createQuery(q3, Long.class);
            numberOrQuantity = (Long) query.getSingleResult();
        } catch (NullPointerException e) {
            numberOrQuantity = 0;
        } catch (NoResultException e) {
            numberOrQuantity = 0;
        } catch (Exception e) {
            numberOrQuantity = 0;
            throw e;
        } finally {
            em.clear();
        }

        return numberOrQuantity;
    }

    /**
     * Method isTransactionCanceled.
     * 
     * @param transaccion Transaction
     * @return boolean
     */
    public boolean isTransactionCanceled(Transaction transaccion) {

        try {

            EntityManager em = jpaTransactionController.getEntityManager();
            Query query = em
                    .createQuery("select t from Transaccion t where t.idAnulaciontransaccion.id = :idAnulaciontransaccion "
                            +
                            // "and
                            // t.numeroIdentificacionCliente.numeroIdentificacionCliente =
                            // :idCliente "
                            // +
                            "and t.estado = '" + TransactionState.COMPLETE.getValue() + "'");

            query.setParameter("idAnulaciontransaccion", transaccion.getId());
            // query.setParameter("idCliente",
            // transaccion.getClient().getIdNumber());
            em.clear();
            return (query.getResultList().size() > 0);

        } catch (javax.persistence.NoResultException ex) {
            return true;
        }

    }

    /**
     * Method findByAuditRoll.
     * 
     * @param idPaperRoll long
     * @return List<Transaction>
     */
    public List<Transaction> findByAuditRoll(long idPaperRoll) {
        String q = "SELECT MIN(id), MAX(id), tipo FROM transaccion t " + "WHERE t.id_rolloauditoria = "
                + idPaperRoll + " AND t.estado = '" + TransactionState.COMPLETE.getValue() + "' GROUP BY tipo";

        List<Transaction> resultList = new ArrayList<Transaction>();
        try {
            EntityManager em = jpaTransactionController.getEntityManager();

            Query query = em.createNativeQuery(q);
            List<Object[]> tmp = query.getResultList();
            for (Object[] transTmp : tmp) {
                System.out.println("Tipo TR:" + transTmp[2].toString());
                resultList.add(fromJPA(jpaTransactionController.findTransaccion((Long) transTmp[0])));
                System.out.println("id min:" + (transTmp[0]));
                resultList.add(fromJPA(jpaTransactionController.findTransaccion((Long) transTmp[1])));
                System.out.println("id max:" + (transTmp[1]));
            }

            em.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * Method findFirstLastTr.
     * 
     * @param idPaperRoll long
     * @return List<Transaction>
     */
    public List<Transaction> findFirstLastTr(long idPaperRoll) {
        String q = "SELECT MIN(id), MAX(id) FROM transaccion t " + "WHERE t.id_rolloauditoria = " + idPaperRoll
                + " AND t.estado = '" + TransactionState.COMPLETE.getValue() + "'";

        List<Transaction> resultList = new ArrayList<Transaction>();
        try {
            EntityManager em = jpaTransactionController.getEntityManager();

            Query query = em.createNativeQuery(q);
            List<Object[]> tmp = query.getResultList();
            for (Object[] transTmp : tmp) {
                resultList.add(fromJPA(jpaTransactionController.findTransaccion((Long) transTmp[0])));
                System.out.println("id min:" + (transTmp[0]));
                resultList.add(fromJPA(jpaTransactionController.findTransaccion((Long) transTmp[1])));
                System.out.println("id max:" + (transTmp[1]));
            }

            em.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * Method isCancellationType.
     * 
     * @param transactionType SourceTransactionType
     * @return boolean
     */
    private boolean isCancellationType(SourceTransactionType transactionType) {

        SourceTransactionType transactionTypeList[] = {SourceTransactionType.Cancellation,
                SourceTransactionType.CreditsEpaCancellation,};

        for (SourceTransactionType i : transactionTypeList) {
            if (i == transactionType) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method commitChanges.
     * 
     * @param transaction Transaction
     * @param session Session
     * @return boolean
     */
    public boolean commitChanges(Transaction transaction, Session session) {

        return commit2(transaction, session);

    }

    /**
     * Method commit2.
     * 
     * @param transaction Transaction
     * @param session Session
     * @return boolean
     */
    private boolean commit2(Transaction transaction, Session session) {

        boolean response = false;
        Transaccion trans = toJPA2(transaction, session);
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            if (trans.getId() != null) {
                trans = em.merge(trans);
            } else {
                em.persist(trans);
            }
            em.getTransaction().commit();
            if (trans.getIdAnulaciontransaccion() != null
                    && trans.getEstado().equals(TransactionState.COMPLETE.getValue())) {
                em.getTransaction().begin();
                trans.getIdAnulaciontransaccion().setIdAnulaciontransaccion(trans);
                em.merge(trans.getIdAnulaciontransaccion());
                em.getTransaction().commit();
            }
            response = true;
            // Cual es el uso de esa linea que se comento que tambien se usa en commit2?
            updateIds(trans, transaction);
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            response = false;
        } finally {
            cleanCache(transaction);
            if (em != null) {
                em.clear();
                em.close();
            }
        }
        return response;
    }

    /**
     * Method updateIds.
     * 
     * @param trans Transaccion
     * @param transaction Transaction
     */
    private void updateIds(Transaccion trans, Transaction transaction) {
        for (int i = 0; i < transaction.getDocuments().size(); i++) {
            transaction.getDocuments().get(i).setId(trans.getTransacciondocumentoList().get(i).getId());
        }

        for (Iterator<Transaccionfase> iterator = trans.getTransaccionfaseList().iterator(); iterator.hasNext();) {
            Transaccionfase type = iterator.next();
            transaction.updatePhaseId(TransactionPhase.get(type.getFase()), type.getId());
        }

        for (int i = 0; i < transaction.getArticles().size(); i++) {
            ((List<Article>) transaction.getArticles()).get(i)
                    .setTransactionArticleId(trans.getTransaccionarticuloList().get(i).getId());
            for (Transaccionarticuloservicio serv : trans.getTransaccionarticuloList().get(i)
                    .getTransaccionarticuloservicioList()) {
                ((List<Article>) transaction.getArticles()).get(i).getServices().get(serv.getIdServicio().getId())
                        .setTransactionServiceId(serv.getId());
            }
        }
        if (trans.getTransaccionclienteList() != null && trans.getTransaccionclienteList().size() > 0) {
            transaction.getClient().setTransactionClientId(trans.getTransaccionclienteList().get(0).getId());
        }
        for (int i = 0; i < transaction.getPayments().size(); i++) {
            ((List<Payment>) transaction.getPayments()).get(i)
                    .setTransactionPaymentId(trans.getTransaccionformadepagoList().get(i).getId());
        }
        // if (transaction instanceof AnulDev && trans.getDevolucionList().size() > 0) {
        // ((AnulDev) transaction).getOriginalSale()
        // .setId(trans.getDevolucionList().get(trans.getDevolucionList().size() -
        // 1).getId());
        // }
        for (Transaccionauditoria jpaAudit : trans.getTransaccionauditoriaList()) {
            for (TransactionAudit audit : transaction.getTransactionAudits()) {
                if (jpaAudit.getFecha().compareTo(audit.getAuditDate()) == 0 && jpaAudit.getIdProceso().getId()
                        .longValue() == Long.valueOf(audit.getAuthorizedProcess().getId()).longValue()) {
                    audit.setId(jpaAudit.getId());
                    break;
                }
            }
        }
        transaction.setId(trans.getId());
    }

    /**
     * Method toJPA2.
     * 
     * @param transaction Transaction
     * @param session Session
     * @return Transaccion
     */
    private Transaccion toJPA2(Transaction transaction, Session session) {
        TransaccionDocumentoJpaController tdJpaController = new TransaccionDocumentoJpaController(this.emf);
        TransaccionArticuloJpaController articlecontroller = new TransaccionArticuloJpaController(this.emf);
        TransaccionClienteJpaController clientController = new TransaccionClienteJpaController(this.emf);
        TransaccionFaseJpaController phaseController = new TransaccionFaseJpaController(this.emf);
        TransaccionFormadepagoJpaController paymentMethodcontroller = new TransaccionFormadepagoJpaController(
                this.emf);
        TransaccionAuditoriaJpaController auditcontroller = new TransaccionAuditoriaJpaController(emf);

        Transaccion jpaTransaction = toJPA(transaction, session);

        Transaction originalTransaction = null;
        if (transaction instanceof AnulDev) {
            originalTransaction = ((AnulDev) transaction).getOriginalSale();
        }

        if (originalTransaction != null && transaction.getTransactionType() == SourceTransactionType.Refund) {
            Devolucion refund = DevolucionJpaController.toJPA(jpaTransaction, originalTransaction);
            if (refund.getId() < 0) {
                refund.setId(null);
            }
            // ArrayList<Devolucion> devList = new ArrayList<Devolucion>();
            // devList.add(refund);
            // jpaTransaction.setDevolucionList(devList);
        }
        if (originalTransaction != null && isCancellationType(transaction.getTransactionType())) {

            Transaccion transaccionOriginalJpa = jpaTransactionController
                    .findTransaccion(originalTransaction.getId());
            if (transaccionOriginalJpa != null) {
                System.out.println(transaccionOriginalJpa.getId());
                jpaTransaction.setIdAnulaciontransaccion(transaccionOriginalJpa);
            }
        }

        ArrayList<Transacciondocumento> docsList = new ArrayList<Transacciondocumento>();
        for (TransactionDocument transactionDocument : transaction.getDocuments()) {
            System.out.println("Tipo documento: " + transactionDocument.getDocumentType().getIdTipoDoc());
            Transacciondocumento doc = tdJpaController.toJPA(transactionDocument, jpaTransaction);
            if (doc.getId() < 0) {
                doc.setId(null);
            }
            docsList.add(doc);
        }
        jpaTransaction.setTransacciondocumentoList(docsList);
        // }

        ArrayList<Transaccionarticulo> artList = new ArrayList<Transaccionarticulo>();
        for (Article article : transaction.getArticles()) {
            Transaccionarticulo art = articlecontroller.toJPA(article, jpaTransaction);
            if (art.getId() < 0 || jpaTransaction.getNumero() == 0) {
                art.setId(null);
            }
            artList.add(art);
        }
        jpaTransaction.setTransaccionarticuloList(artList);

        ArrayList<Transaccioncliente> clientList = new ArrayList<Transaccioncliente>();
        if (jpaTransaction.getNumeroIdentificacionCliente() != null) {
            Transaccioncliente client = clientController.toJPA(jpaTransaction.getNumeroIdentificacionCliente(),
                                                               jpaTransaction,
                                                               transaction.getClient().getTransactionClientId());
            if (client.getId() < 0 || jpaTransaction.getNumero() == 0) {
                client.setId(null);
            }
            if (client != null) {
                clientList.add(client);
                jpaTransaction.setTransaccionclienteList(clientList);
            }
        }

        ArrayList<Transaccionfase> phaseList = new ArrayList<Transaccionfase>();
        for (Transaccionfase phase : phaseController.toJPA(transaction, jpaTransaction)) {
            phaseList.add(phase);
        }
        jpaTransaction.setTransaccionfaseList(phaseList);

        ArrayList<Transaccionformadepago> fdpList = new ArrayList<Transaccionformadepago>();
        short k = 1;
        for (Payment pay : transaction.getPayments()) {
            Transaccionformadepago jpaPaymentMethod = paymentMethodcontroller.toJPA(pay, jpaTransaction);
            jpaPaymentMethod.setItem(k);
            jpaPaymentMethod.setEstasincronizado("N");
            pay.setItem(k);
            k++;
            fdpList.add(jpaPaymentMethod);
        }
        jpaTransaction.setTransaccionformadepagoList(fdpList);

        ArrayList<Transaccionauditoria> auditList = new ArrayList<Transaccionauditoria>();
        for (TransactionAudit audit : transaction.getTransactionAudits()) {
            if (audit.getId() == null || jpaTransaction.getNumero() == 0) {
                auditList.add(auditcontroller.toJPA(audit, jpaTransaction));
            }
        }
        jpaTransaction.setTransaccionauditoriaList(auditList);

        return jpaTransaction;
    }

    /**
     * Method edit.
     * 
     * @param transaccion Transaction
     * @param currentSession Session
     * @throws Exception
     */
    public void edit(Transaction transaccion, Session currentSession) throws Exception {
        jpaTransactionController.edit(toJPA(transaccion, currentSession));
    }

    /**
     * Method findTransactionByStore_Pos_Id.
     * 
     * @param posId String
     * @param numero String
     * @param type String
     * @return Transaction
     */
    public Transaction findTransactionByStore_Pos_Id(String posId, String numero, String type) {
        Transaction result = null;
        try {

            EntityManager em = jpaTransactionController.getEntityManager();
            Query query = em
                    .createQuery("select t from Transaccion t where t.idSesion.idCaja = :posId and t.numero = :numero and t.estado = '"
                            + TransactionState.COMPLETE.getValue() + "' and t.tipo = :tipo");

            // query.setParameter("store", store);
            query.setParameter("posId", Integer.parseInt(posId));
            query.setParameter("numero", new Long(numero));
            query.setParameter("tipo", type);
            query.setMaxResults(1);
            Transaccion tmp = (Transaccion) query.getSingleResult();
            result = fromJPA(tmp);
            em.clear();
        } catch (javax.persistence.NoResultException ex) {
            result = null;
        }
        return result;
    }

    /**
     * Method findLastFiscalTransaction.
     * 
     * @return Transaction
     */
    public Transaction findLastFiscalTransaction() {
        return findLastTransactionToReprint();
    }

    /**
     * Method findTransactionByStore_Pos_Id_Date.
     * 
     * @param posId String
     * @param numero String
     * @param type String
     * @param value Date
     * @return Transaction
     */
    public Transaction findTransactionByStore_Pos_Id_Date(String posId, String numero, String type, Date value) {
        Transaction result = null;
        Date fechaInicial = JPAUtils.cleanDate(value, true);// cal.getTime();
        Date fechaFinal = JPAUtils.cleanDate(value, false);// cal.getTime();

        System.out.println(type);

        try {

            EntityManager em = jpaTransactionController.getEntityManager();
            Query query = em
                    .createQuery("select t from Transaccion t where t.idSesion.idCaja = :posId and t.numero = :numero and t.estado = '"
                            + TransactionState.COMPLETE.getValue() + "' " + " and t.tipo IN ( " + type
                            + " ) and t.fecha >= :fechaInicial AND t.fecha <= :fechaFinal");

            // query.setParameter("store", store);
            query.setParameter("posId", Integer.parseInt(posId));
            query.setParameter("numero", new Long(numero));
            // query.setParameter("tipo", type);
            query.setParameter("fechaInicial", fechaInicial);
            query.setParameter("fechaFinal", fechaFinal);
            query.setMaxResults(1);
            Transaccion tmp = (Transaccion) query.getSingleResult();
            result = fromJPA(tmp);
            em.clear();
        } catch (javax.persistence.NoResultException ex) {
            result = null;
        }
        return result;
    }

    /**
     * Method findTransaccionPorCajero.
     * 
     * @param ficha cashierEPAId
     * @param pmType int
     * @return CRBigDecimal
     * @throws Exception
     */
    public CRBigDecimal findTransaccionPorCajero(int cashierEPAId, int pmType) throws Exception {
        EntityManager em = jpaTransactionController.getEntityManager();
        CRBigDecimal result = CRBigDecimal.ZERO;
        String qPos, qNeg, qVuelto, creditPos, creditNeg, qFinal;

        creditPos = "SELECT COALESCE(SUM(A.monto_moneda_local),0) AS pos " + "FROM CRPOS.acreenciamovimiento AS A "
                + "INNER JOIN CRPOS.formadepago AS FDP ON FDP.id = A.id_formadepago " + "WHERE A.cajero = "
                + cashierEPAId
                + " AND A.fecha BETWEEN TIMESTAMP(CURDATE()) AND TIMESTAMP(DATE_ADD(CURDATE(), INTERVAL 1 DAY)) "
                + "AND A.id_operacionacreencia IN (" + CreditMovementType.Deposit.getValue() + ") "
                + "AND FDP.tipo_forma_de_pago = " + pmType;

        creditNeg = "SELECT COALESCE(SUM(A.monto_moneda_local),0) AS neg " + "FROM CRPOS.acreenciamovimiento AS A "
                + "INNER JOIN CRPOS.formadepago AS FDP ON FDP.id = A.id_formadepago " + "WHERE A.cajero = "
                + cashierEPAId
                + " AND A.fecha BETWEEN TIMESTAMP(CURDATE()) AND TIMESTAMP(DATE_ADD(CURDATE(), INTERVAL 1 DAY)) "
                + "AND A.id_operacionacreencia IN (" + CreditMovementType.Refund.getValue() + ","
                + CreditMovementType.Cancelation.getValue() + "," + CreditMovementType.RefundOp.getValue() + ") "
                + "AND FDP.tipo_forma_de_pago = " + pmType;

        qPos = "SELECT COALESCE(SUM(monto_moneda_local),0) AS pos " + "FROM CRPOS.transaccion AS TR "
                + "INNER JOIN CRPOS.transaccionformadepago AS TRF ON TR.id = TRF.id_transaccion "
                + "INNER JOIN CRPOS.usuario AS U ON U.id=TR.id_usuario "
                + "INNER JOIN CRPOS.formadepago AS FDP ON TRF.id_formadepago=FDP.id " + "WHERE U.ficha = "
                + cashierEPAId
                + " AND TR.fecha BETWEEN TIMESTAMP(CURDATE()) AND TIMESTAMP(DATE_ADD(CURDATE(), INTERVAL 1 DAY)) "
                + "AND TRF.estaactivo='S'" + "AND TR.estado='" + TransactionState.COMPLETE.getValue() + "' "
                + "AND tipo IN ('" + SourceTransactionType.Sale.getValue() + "','"
                + SourceTransactionType.Order.getValue() + "','" + SourceTransactionType.OrderDeposit.getValue()
                + "','" + SourceTransactionType.CreditsEpaPay.getValue() + "') " + "AND FDP.tipo_forma_de_pago="
                + pmType;

        qNeg = "SELECT COALESCE(SUM(monto_moneda_local),0) AS pos " + "FROM CRPOS.transaccion AS TR "
                + "INNER JOIN CRPOS.transaccionformadepago AS TRF ON TR.id = TRF.id_transaccion "
                + "INNER JOIN CRPOS.usuario AS U ON U.id=TR.id_usuario "
                + "INNER JOIN CRPOS.formadepago AS FDP ON TRF.id_formadepago=FDP.id " + "WHERE U.ficha = "
                + cashierEPAId
                + " AND TR.fecha BETWEEN TIMESTAMP(CURDATE()) AND TIMESTAMP(DATE_ADD(CURDATE(), INTERVAL 1 DAY)) "
                + "AND TRF.estaactivo='S'" + "AND TR.estado='" + TransactionState.COMPLETE.getValue() + "' "
                + "AND tipo IN ('" + SourceTransactionType.Cancellation.getValue() + "','"
                + SourceTransactionType.Refund.getValue() + "','"
                + SourceTransactionType.CreditsEpaCancellation.getValue() + "','"
                + SourceTransactionType.CancellationOrder.getValue() + "') " + "AND FDP.tipo_forma_de_pago="
                + pmType;

        qVuelto = "SELECT COALESCE(SUM(vuelto),0) AS vuelto " + "FROM CRPOS.transaccion AS TR "
                + "INNER JOIN CRPOS.usuario AS U ON U.id=TR.id_usuario " + "WHERE U.ficha = " + cashierEPAId
                + " AND TR.fecha BETWEEN TIMESTAMP(CURDATE()) AND TIMESTAMP(DATE_ADD(CURDATE(), INTERVAL 1 DAY)) "
                + "AND TR.estado='" + TransactionState.COMPLETE.getValue() + "'";

        qFinal = "SELECT (" + qPos + "),(" + qNeg + "),(" + qVuelto + "),(" + creditPos + "),(" + creditNeg + ") ";

        try {
            Query query = em.createNativeQuery(qFinal);
            Object[] tmp = (Object[]) query.getSingleResult();
            System.out.println("--> Positivo entrega: " + ((BigDecimal) tmp[0]).doubleValue());
            System.out.println("--> Negativo entrega: " + ((BigDecimal) tmp[1]).doubleValue());
            System.out.println("--> Vuelto entrega: " + ((BigDecimal) tmp[2]).doubleValue());
            System.out.println("--> Positivo acreencia: " + ((BigDecimal) tmp[3]).doubleValue());
            System.out.println("--> Negativo acreencia: " + ((BigDecimal) tmp[4]).doubleValue());
            double value = ((BigDecimal) tmp[0]).doubleValue();
            value -= ((BigDecimal) tmp[1]).doubleValue();
            value -= ((BigDecimal) tmp[2]).doubleValue();
            value += ((BigDecimal) tmp[3]).doubleValue();
            value -= ((BigDecimal) tmp[4]).doubleValue();
            result = new CRBigDecimal(value);

            em.clear();
        } catch (Exception e) {
            throw e;
            // e.printStackTrace();
        } finally {
            em.close();
        }
        return result;
    }

    /**
     * Method findLastTransactionByTransactionType.
     * 
     * @param transactionType SourceTransactionType
     * @param serialNumber String
     * @return Transaction
     */
    public Transaction findLastTransactionByTransactionType(SourceTransactionType transactionType,
                                                            String serialNumber) {
        Transaction result = null;

        try {

            EntityManager em = jpaTransactionController.getEntityManager();
            Query query = em
                    .createQuery("select t from Transaccion t where t.id = (select max(te.id) from Transaccion te where te.tipo = :tipo and te.idSerialimpresora.serial = :serial and te.estado = '"
                            + TransactionState.COMPLETE.getValue() + "')");
            query.setParameter("tipo", transactionType.getValue());
            query.setParameter("serial", serialNumber);
            query.setMaxResults(1);
            Transaccion tmp = (Transaccion) query.getSingleResult();
            result = fromJPA(tmp);
            em.clear();
        } catch (javax.persistence.NoResultException ex) {
            result = null;
        }
        return result;
    }

    /**
     * Method findLastTransactionAttempPrint.
     * 
     * @param transactionType SourceTransactionType
     * @param serialNumber String
     * @param fiscalId int
     * @return Transaction
     */
    public Transaction findLastTransactionAttempPrint(SourceTransactionType transactionType, String serialNumber,
                                                      int fiscalId) {
        Transaction result = null;

        try {

            EntityManager em = jpaTransactionController.getEntityManager();
            Query query = em
                    .createQuery("select t from Transaccion t where t.id = (select max(te.id) from Transaccion te where te.tipo = :tipo and te.numeroFiscal = :fiscalId and te.idSerialimpresora.serial = :serial and te.estado <> '"
                            + TransactionState.COMPLETE.getValue() + "')");
            query.setParameter("tipo", transactionType.getValue());
            query.setParameter("fiscalId", fiscalId);
            query.setParameter("serial", serialNumber);

            query.setMaxResults(1);
            Transaccion tmp = (Transaccion) query.getSingleResult();
            result = fromJPA(tmp);
            em.clear();
        } catch (javax.persistence.NoResultException ex) {
            result = null;
        }
        return result;
    }

    /**
     * Llamese transaccion rota a aquella que esta en I pero se imprimio y no actualizo
     * todo, Este metodo devuelve la cantidad de transacciones rotas con el mismo numero
     * fiscal.
     * 
     * @return la cantidad de transacciones rotas
     */
    public boolean areBrokenTransactions() {
        Integer result = 0;
        EntityManager em = jpaTransactionController.getEntityManager();
        String defaultTransactionNumber = "-1", activeResolution = "A",
                queryString = "SELECT COUNT(t.id) " + "FROM Transaccion t "
                        + "LEFT JOIN t.idComprobantefiscalpreimpreso cfpi " + "WHERE " + "cfpi.estado = '"
                        + activeResolution + "' AND t.numeroFiscal <> " + defaultTransactionNumber + " "
                        + "GROUP BY t.idComprobantefiscalpreimpreso, t.numeroFiscal " + "HAVING COUNT(t.id) > 1 "
                        + "ORDER BY COUNT(t.id) DESC";
        Query query = em.createQuery(queryString);
        System.out.println();
        result = query.getResultList().size();
        em.clear();
        em.close();

        return result > 0;
    }

    public boolean postPrintCommit(Transaction current, Session sesion) throws JpaException {
        EntityManager em = jpaTransactionController.getEntityManager();
        EntityTransaction dbTransaction = em.getTransaction();
        ComprobantefiscalpreimpresoJpaController resolutionJpa = new ComprobantefiscalpreimpresoJpaController(
                em.getEntityManagerFactory());

        boolean result = false;
        try {
            Transaccion actual = toJPA2(current, sesion), reverso = actual.getIdAnulaciontransaccion();
            Comprobantefiscalpreimpreso resolution = resolutionJpa.toJPA(current.getResolution());
            dbTransaction.begin();
            // Guardar la transaccion y su serial impresora
            mergeTransactionComponents(em, actual, reverso, resolution);
            dbTransaction.commit();
            // Cual es el uso de esto y por que si funciona en commit2
            // updateIds(actual,current);
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
            if (dbTransaction.isActive()) {
                dbTransaction.rollback();
            }
            throw new JpaException();
        } finally {
            cleanCache(current);
            if (em.isOpen()) {
                em.clear();
                em.close();
            }
        }
        return result;
    }

    private void mergeTransactionComponents(EntityManager em, Transaccion actual, Transaccion reverso,
                                            Comprobantefiscalpreimpreso resolution) {
        // Guardar la resolucion
        if (resolution != null) {
            em.merge(resolution);
        }
        if (actual.getId() != null) {
            em.merge(actual);
        } else {
            em.persist(actual);
        }
        if (reverso != null && reverso.getEstado().equals(TransactionState.COMPLETE.getValue())) {
            reverso.setIdAnulaciontransaccion(actual);
            em.merge(reverso);
        }

    }

    private void cleanCache(Transaction current) {
        this.emf.getCache().evict(Transaccion.class);
        Transaccion tmp = jpaTransactionController.findTransaccion(current.getId());
        if (tmp != null) {
            System.out.println("******** tr: " + current.getId());
            System.out.println("******** correlativo anterior: " + current.getNumber());
            System.out.println("******** seteando correlativo: " + tmp.getNumero());
            current.setNumber(String.valueOf(tmp.getNumero()));
        }
    }
}
