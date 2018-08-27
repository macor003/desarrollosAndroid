/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.becoblohm.cr.interfaces.AbstractAuthorizableModel;
import com.becoblohm.cr.types.AuditTables;
import com.becoblohm.cr.types.CRBigDecimal;
import com.becoblohm.cr.types.TaxAmountWrapper;
import com.becoblohm.cr.types.TransactionCalculator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction extends AbstractAuthorizableModel {

    private static final long serialVersionUID = 1L;

    /**
     * Field number.
     */
    private String number;

    /**
     * Field fiscalId.
     */
    private int fiscalId;

    /**
     * Field date.
     */
    private Date date = new Date();

    /**
     * Field hour.
     */
    private String hour;

    /**
     * Field saleOrigin.
     */
    private Source saleOrigin;

    /**
     * Field transactionType.
     */
    private SourceTransactionType transactionType;

    /**
     * Field transactionState.
     */
    private TransactionState transactionState;

    /**
     * Field donation.
     */
    private Donation donation;

    /**
     * Field store.
     */
    private String store;

    /**
     * Field posId.
     */
    private String posId;

    /**
     * Field id.
     */
    private long id = -1;

    /**
     * Field discountTypeId.
     */
    private long discountTypeId = 0;

    /**
     * Field taxes.
     */
    private Map<Tax, TaxAmountWrapper> taxes;

    /**
     * Field totalCost.
     */
    private CRBigDecimal totalCost = CRBigDecimal.ZERO;

    /**
     * Field totalCharge.
     */
    private CRBigDecimal totalCharge = CRBigDecimal.ZERO;

    /**
     * Field totalPaid.
     */
    private CRBigDecimal totalPaid = CRBigDecimal.ZERO;

    /**
     * Field diference.
     */
    private CRBigDecimal diference = CRBigDecimal.ZERO;

    /**
     * Field change.
     */
    private CRBigDecimal change = CRBigDecimal.ZERO;

    /**
     * Field taxAmount.
     */
    private CRBigDecimal taxAmount = CRBigDecimal.ZERO;

    /**
     * Field baseAmount.
     */
    private CRBigDecimal baseAmount = CRBigDecimal.ZERO;

    /**
     * Field totalTaxAmount.
     */
    private CRBigDecimal totalTaxAmount = CRBigDecimal.ZERO;

    /**
     * Field totalRebate.
     */
    private CRBigDecimal totalRebate = CRBigDecimal.ZERO;

    /**
     * Field active.
     */
    private boolean active;

    /**
     * Field client.
     */
    private Client client;

    /**
     * Field user.
     */
    private User user;

    /**
     * Field articles.
     */
    private Collection<Article> articles;

    /**
     * Field payments.
     */
    private Collection<Payment> payments;

    /**
     * Field rebates.
     */
    private Collection<Rebate> rebates;

    /**
     * Field articlesIndex.
     */
    private Map<String, Integer> articlesIndex;

    /**
     * Field articlesCount.
     */
    private CRBigDecimal articlesCount = CRBigDecimal.ZERO;

    /**
     * Field document.
     */
    private DocumentType document;

    /**
     * Field selectedArticles.
     */
    private Collection<Article> selectedArticles = new ArrayList<Article>();

    /**
     * Field status.
     */
    private String status = TransactionState.INCOMPLETE.getValue();

    /**
     * Field printerSerial.
     */
    private String printerSerial;

    /**
     * Field code.
     */
    private String code;

    /**
     * Field transactionServices.
     */
    private final HashMap<Long, Service> transactionServices = new HashMap<Long, Service>();

    /**
     * Field deliveryInfo.
     */
    private HashMap<Integer, DeliveryCondition> deliveryInfo;

    /**
     * Field deliveryCondition.
     */
    private DeliveryCondition deliveryCondition;

    /**
     * Field OriginalArticle.
     */
    private HashMap<String, Object[]> OriginalArticle;

    /**
     * Field promotion.
     */
    private Collection<PromotionsMethod> promotion;

    /**
     * Field phases.
     */
    private Map<TransactionPhase, Phase> phases = new HashMap<Transaction.TransactionPhase, Transaction.Phase>();

    /**
     * Field documents.
     */
    private List<TransactionDocument> documents;

    /**
     * Field idCancellation.
     */
    private long idCancellation;

    /**
     * Field idCreditsEpaAccount.
     */
    private long idCreditsEpaAccount;

    /**
     * Field idPaperRoll.
     */
    private long idPaperRoll;

    /**
     * Field rebateDesc.
     */
    private String rebateDesc;

    /**
     * Field teleMarketing.
     */
    private boolean teleMarketing;

    /**
     * Field oldDonation.
     */
    private CRBigDecimal oldDonation;

    /**
     * Field credit.
     */
    private PaymentConditions credit = PaymentConditions.CASH;

    /**
     * Field fromElectronicCommand.
     */
    private boolean fromElectronicCommand;

    /**
     * Field isDocumentPrinted.
     */
    private boolean isDocumentPrinted = false;

    /**
     * Field resolution.
     */
    private PrePrintedFiscalReceipt resolution;

    private List<TransactionAudit> transactionAudits = new ArrayList<TransactionAudit>();

    /**
     * Method isDocumentPrinted.
     * 
     * @return boolean
     */
    public boolean isDocumentPrinted() {
        return isDocumentPrinted;
    }

    /**
     * Method setDocumentPrinted.
     * 
     * @param isDocumentPrinted boolean
     */
    public void setDocumentPrinted(boolean isDocumentPrinted) {
        this.isDocumentPrinted = isDocumentPrinted;
    }

    /**
     * Method isFromElectronicCommand.
     * 
     * @return boolean
     */
    public boolean isFromElectronicCommand() {
        return fromElectronicCommand;
    }

    /**
     * Method setFromElectronicCommand.
     * 
     * @param fromElectronicCommand boolean
     */
    public void setFromElectronicCommand(boolean fromElectronicCommand) {
        this.fromElectronicCommand = fromElectronicCommand;
    }

    /**
     * Constructor for Transaction.
     * 
     * @param number String
     * @param fiscalId int
     * @param date Date
     * @param taxes Map
     * @param totalCost CRBigDecimal
     * @param totalPay CRBigDecimal
     * @param client Client
     * @param user User
     * @param articles Collection<Article>
     * @param payments Collection<Payment>
     * @param rebates Collection<Rebate>
     * @param articlesCount CRBigDecimal
     * @param saleSource Source
     * @param transactionType SourceTransactionType
     */
    public Transaction(String number, int fiscalId, Date date, Map taxes, CRBigDecimal totalCost,
                       CRBigDecimal totalPay, Client client, User user, Collection<Article> articles,
                       Collection<Payment> payments, Collection<Rebate> rebates, CRBigDecimal articlesCount,
                       Source saleSource, SourceTransactionType transactionType) {
        super(AuditTables.TRANSACTION);
        this.number = number;
        this.fiscalId = fiscalId;
        this.date = date;
        this.taxes = taxes;
        this.totalCost = totalCost;
        this.totalPaid = totalPay;
        this.totalCharge = totalCost;
        this.client = client;
        this.user = user;
        this.articles = articles;
        this.payments = payments;
        this.rebates = rebates;
        this.active = true;
        this.articlesCount = articlesCount;
        this.saleOrigin = saleSource;
        this.donation = new Donation(CRBigDecimal.valueOf(0));
        this.deliveryInfo = new HashMap<Integer, DeliveryCondition>();
        this.transactionType = transactionType;
        this.setDocuments(new ArrayList<TransactionDocument>());
        this.oldDonation = CRBigDecimal.ZERO;
    }

    /**
     * Method getIdCreditsEpaAccount.
     * 
     * @return long
     */
    public long getIdCreditsEpaAccount() {
        return idCreditsEpaAccount;
    }

    /**
     * Method setIdCreditsEpaAccount.
     * 
     * @param idCreditsEpaAccount long
     */
    public void setIdCreditsEpaAccount(long idCreditsEpaAccount) {
        this.idCreditsEpaAccount = idCreditsEpaAccount;
    }

    /**
     * Constructor for Transaction.
     */
    public Transaction() {
        super(AuditTables.TRANSACTION);
        this.client = new Client();
        this.user = new User();
        this.articles = new ArrayList<Article>();
        this.payments = new Vector<Payment>();
        this.rebates = new Vector<Rebate>();
        this.taxes = new HashMap();
        this.articlesIndex = new ConcurrentHashMap<String, Integer>();
        this.donation = new Donation(CRBigDecimal.valueOf(0));
        this.deliveryInfo = new HashMap<Integer, DeliveryCondition>();
        this.transactionType = SourceTransactionType.Sale;
        this.setDocuments(new ArrayList<TransactionDocument>());
    }

    /**
     * Method addPayments.
     * 
     * @param pay Payment
     */
    public void addPayments(Payment pay) {
        Collection<Payment> tmp = new ArrayList<Payment>();
        updatePayments(pay);
        this.setTotalPay(this.totalPaid.add(pay.getMoney().getLocalAmmount()));
        this.setDiference(this.totalCharge.subtract(this.totalPaid));
        this.fire("payments", tmp, payments);
    }

    /**
     * Method updatePayments.
     * 
     * @param pay Payment
     */
    private void updatePayments(Payment pay) {
        boolean exists = false;
        CRBigDecimal tmp;

        if (pay.isAllowAcumulate()) {
            for (Payment p : payments) {
                if ((pay.getMoney().getCurrencyId() == p.getMoney().getCurrencyId())
                        && (pay.getPayMethod().getCode() == p.getPayMethod().getCode())) {

                    tmp = new CRBigDecimal(p.getMoney().getCurrencyAmmount().doubleValue());
                    tmp = tmp.add(pay.getMoney().getCurrencyAmmount());
                    p.getMoney().setCurrencyAmmount(tmp);

                    exists = true;
                }
            }
        }

        if (!exists) {
            this.payments.add(pay);
        }
    }

    /**
     * Method removePayments.
     * 
     * @param pay Payment
     */
    public void removePayments(Payment pay) {
        Collection<Payment> tmp = new ArrayList<Payment>();
        tmp.addAll(this.payments);
        this.payments.remove(pay);
        this.setTotalPay(this.totalPaid.subtract(pay.getMoney().getLocalAmmount()));
        this.setDiference(this.totalCharge.subtract(this.totalPaid));
        this.fire("payments", tmp, payments);
    }

    /**
     * Method addArticle.
     * 
     * @param art Article
     */
    public void addArticle(Article art) {
        if (!getArticlesIndex().containsKey(art.getArticlesTableIndex())) {
            Collection<Article> tmp = new ArrayList<Article>();
            tmp.addAll(this.articles);
            this.articles.add(art);
            updateCosts();
            this.active = true;
            // buildDeliveryInfo();
            this.fireArticlesListChange(tmp);
        }
    }

    /**
     * Method removeArticle.
     * 
     * @param art Article
     */
    public void removeArticle(Article art) {
        this.articles.remove(art);

        CRBigDecimal tmpArticlesCount = this.articlesCount;
        tmpArticlesCount = tmpArticlesCount.subtract(art.getItems());
        this.setArticlesCount(tmpArticlesCount);
        buildDeliveryInfo();
        updateCosts();
    }

    /**
     * Method fireArticlesListChange.
     * 
     * @param tmp Collection<Article>
     */
    private void fireArticlesListChange(Collection<Article> tmp) {
        this.fire("articlesChanged", tmp, articles);
    }

    /**
     * Method updateCosts.
     */
    public void updateCosts() {
        this.recalculateArticlesIndex();
        TransactionCalculator.getInstance().getCalculator().updateTransactionCosts(this);
        buildCode();
        ArrayList<Article> forceupdate = new ArrayList<Article>();
        forceupdate.add(new Article());
        this.fireArticlesListChange(forceupdate);
    }

    /**
     * Method updateArticle.
     * 
     * @param article Article
     * @param items CRBigDecimal
     */
    public void updateArticle(Article article, CRBigDecimal items) {
        ArrayList<Article> tmp = new ArrayList<Article>();
        tmp.addAll(this.articles);
        int articleIndex = 0;
        if (articlesIndex.get(article.getArticlesTableIndex()) != null) {
            articleIndex = articlesIndex.get(article.getArticlesTableIndex());
        }
        Article art = ((ArrayList<Article>) this.getArticles()).get(articleIndex);
        art.setItems(article.getItems().add(items));
        updateCosts();
        this.getArticles().clear();
        this.setArticles(tmp);
        this.fireSelectedArticle(articleIndex);
    }

    /**
     * Method updateArticlePrice.
     * 
     * @param article Article
     */
    public void updateArticlePrice(Article article) {
        ArrayList<Article> tmp = new ArrayList<Article>();
        tmp.addAll(this.articles);
        int articleIndex = articlesIndex.get(article.getArticlesTableIndex());
        Article art = ((ArrayList<Article>) this.getArticles()).get(articleIndex);
        art.setItemCost(article.getItemCost());
        art.setItems(article.getItems());
        updateCosts();
        this.getArticles().clear();
        this.setArticles(tmp);
        this.fireSelectedArticle(articleIndex);
    }

    /**
     * Method fireSelectedArticle.
     * 
     * @param index int
     */
    private void fireSelectedArticle(int index) {
        this.fire("selected", -1, index);
    }

    /**
     * Method addPayment.
     * 
     * @param pay Payment
     */
    public void addPayment(Payment pay) {
        addPayments(pay);
    }

    /**
     * Method getClient.
     * 
     * @return Client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Method setClient.
     * 
     * @param client Client
     */
    public void setClient(Client client) {
        Client tmp = this.client;
        this.client = client;
        if (client.getIdNumber() != null && !client.getIdNumber().equals("0")) {
            this.active = true;
        }
        this.fire("client", tmp, client);
    }

    /**
     * Method getUser.
     * 
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Method setUser.
     * 
     * @param user User
     */
    public void setUser(User user) {
        User tmp = this.user;
        this.user = user;
        this.fire("user", tmp, user);
    }

    /**
     * Method getTotalCost.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getTotalCost() {
        return totalCost;
    }

    /**
     * Method setTotalCost.
     * 
     * @param totalCost CRBigDecimal
     */
    public void setTotalCost(CRBigDecimal totalCost) {
        CRBigDecimal tmp = this.totalCost;
        this.totalCost = totalCost;
        setTotalCharge(getTotalCost().add(getDonation().getAmount()));
        CRBigDecimal tmpDiference = this.totalCharge.subtract(this.totalPaid);
        this.setDiference(tmpDiference);
        this.fire("totalCost", tmp, totalCost);
    }

    /**
     * Method getTotalPay.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getTotalPay() {
        return totalPaid;
    }

    /**
     * Method setTotalPay.
     * 
     * @param totalPay CRBigDecimal
     */
    public void setTotalPay(CRBigDecimal totalPay) {
        CRBigDecimal tmp = this.totalPaid;
        this.totalPaid = totalPay;
        CRBigDecimal tmpDiference = this.totalCharge.subtract(this.totalPaid);
        this.setDiference(tmpDiference);
        fire("totalPay", tmp, this.totalPaid);
    }

    /**
     * Method getArticles.
     * 
     * @return Collection<Article>
     */
    public Collection<Article> getArticles() {
        return articles;
    }

    /**
     * Method setArticles.
     * 
     * @param articles Collection<Article>
     */
    public void setArticles(Collection<Article> articles) {
        Collection<Article> tmp = this.articles;
        this.articles = articles;
        this.fire("articles", tmp, articles);
    }

    /**
     * Method getPayments.
     * 
     * @return Collection<Payment>
     */
    public Collection<Payment> getPayments() {
        return this.payments;
    }

    /**
     * Method setPayments.
     * 
     * @param payments Collection<Payment>
     */
    public void setPayments(Collection<Payment> payments) {
        this.payments = payments;
    }

    /**
     * @param active the active to set
     */
    private void setActive(boolean active) {
        this.active = active;
    }

    /**
     * 
     * @return the active
     */
    public boolean isActive() {
        // return active;
        boolean result = false;

        if ((this.client.getIdNumber() != null && !client.getIdNumber().equals("0"))
                || this.articlesCount.compareTo(CRBigDecimal.ZERO) > 0) {
            result = true;
            this.updatePhase(TransactionPhase.TRANSTIME, true);
        }

        return result;

    }

    /**
     */
    public static class Phase implements Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = -1187739445292744613L;

        /**
         * Field elapsed.
         */
        private long elapsed = 0;

        /**
         * Field last.
         */
        private long last;

        /**
         * Field id.
         */
        private long id;

        /**
         * Constructor for Phase.
         */
        public Phase() {
            this.setLast(System.nanoTime());
        }

        /**
         * @param elapsed the elapsed to set
         */
        public void setElapsed(long elapsed) {
            this.elapsed = elapsed;
        }

        /**
         * 
         * @return the elapsed
         */
        public long getElapsed() {
            return elapsed;
        }

        /**
         * @param last the last to set
         */
        public void setLast(long last) {
            this.last = last;
        }

        /**
         * 
         * @return the last
         */
        public long getLast() {
            return last;
        }

        /**
         * Method setId.
         * 
         * @param id long
         */
        public void setId(long id) {
            this.id = id;

        }

        /**
         * Method getId.
         * 
         * @return Long
         */
        public Long getId() {
            return id;
        }

    }

    /**
     * Method updatePhase.
     * 
     * @param transtime TransactionPhase
     * @param isStart boolean
     */
    public void updatePhase(TransactionPhase transtime, boolean isStart) {
        if (this.getPhases().containsKey(transtime)) {

            Phase tmptime = this.getPhases().get(transtime);

            if (isStart) {

                Phase tmp = new Phase();
                tmp.setElapsed(tmptime.getElapsed());
                tmp.setId(tmptime.getId());
                this.getPhases().put(transtime, tmp);

            } else {

                // duration
                long elapsed = tmptime.getElapsed();
                // last sample
                long previous = tmptime.getLast();

                long last = System.nanoTime();

                tmptime.setElapsed(elapsed + (last - previous));

                tmptime.setLast(last);

                this.getPhases().put(transtime, tmptime);
            }

        } else {
            this.getPhases().put(transtime, new Phase());
        }

    }

    /**
     * Method getArticlesCount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getArticlesCount() {
        return articlesCount;
    }

    /**
     * Method setArticlesCount.
     * 
     * @param articlesCount CRBigDecimal
     */
    public void setArticlesCount(CRBigDecimal articlesCount) {
        CRBigDecimal tmp = this.articlesCount;
        this.articlesCount = articlesCount;
        this.fire("articlesCount", tmp, articlesCount);
    }

    /**
     * Method getDocument.
     * 
     * @return DocumentType
     */
    public DocumentType getDocument() {
        return document;
    }

    /**
     * Method setDocument.
     * 
     * @param document DocumentType
     */
    public void setDocument(DocumentType document) {
        this.document = document;
    }

    /**
     * Method getSelectedArticles.
     * 
     * @return Collection<Article>
     */
    public Collection<Article> getSelectedArticles() {
        return selectedArticles;
    }

    /**
     * Method getSelectedPayments.
     * 
     * @return Collection<Payment>
     */
    public Collection<Payment> getSelectedPayments() {
        return payments;
    }

    /**
     * Method addAllSelectedArticles.
     * 
     * @param selectedArticles Collection<Article>
     */
    public void addAllSelectedArticles(Collection<Article> selectedArticles) {
        this.selectedArticles.clear();
        this.selectedArticles.addAll(selectedArticles);
        this.recalculateArticlesIndex();
    }

    /**
     * Method getArticlesIndex.
     * 
     * @return Map<String,Integer>
     */
    public synchronized Map<String, Integer> getArticlesIndex() {
        return articlesIndex;
    }

    public enum PaymentConditions {
        CREDIT("CREDITO"), CASH("CONTADO");

        private static final Map<String, PaymentConditions> lookup = new HashMap<String, PaymentConditions>();

        static {
            for (PaymentConditions s : EnumSet.allOf(PaymentConditions.class))
                lookup.put(s.getValue(), s);
        }

        private String value;

        PaymentConditions(String value) {
            this.setValue(value);
        }

        public static PaymentConditions get(String value) {
            return lookup.get(value);
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     */
    public enum Source {
        /**
         * Field Pos.
         */
        Pos("CAJA"),
        /**
         * Field OnlyAuthorizedThirdParty.
         */
        OnlyAuthorizedThirdParty("CAJA"),
        /**
         * Field OnlySaleBetweenStore.
         */
        OnlySaleBetweenStore("CAJA"),
        /**
         * Field OnlyCreditsClient.
         */
        OnlyCreditsClient("CAJA"),
        /**
         * Field Waiting.
         */
        Waiting("VENTA EN ESPERA"),
        /**
         * Field PreOrder.
         */
        PreOrder("PEDIDO ESPECIAL"),
        /**
         * Field ElectronicCommand.
         */
        ElectronicCommand("COMANDA ELECTRONICA"),
        /**
         * Field Cancellation.
         */
        Cancellation("CAJA"),
        /**
         * Field Quotation.
         */
        Quotation("COTIZACION"),
        /**
         * Field Refund.
         */
        Refund("DEVOLUCION"),
        /**
         * Field OnlyDeliveryCondition.
         */
        OnlyDeliveryCondition("CAMBIOCONDICIONENTREGA"),
        /**
         * Field OnlyClient.
         */
        OnlyClient("CAMBIOCLIENTE"),
        /**
         * Field CreditsEpa.
         */
        CreditsEpa("CREDITOEPA"),
        /**
         * Field Corporate.
         */
        Corporate("CORPORATIVO"),
        /**
         * Field PosSC.
         */
        PosSC("AUTOPAGO");

        /**
         * Field lookup.
         */
        private static final Map<String, Source> lookup = new HashMap<String, Source>();

        static {
            for (Source s : EnumSet.allOf(Source.class))
                lookup.put(s.getValue(), s);
        }

        /**
         * Field value.
         */
        private String value;

        /**
         * Constructor for Source.
         * 
         * @param value String
         */
        Source(String value) {
            this.setValue(value);
        }

        /**
         * Method get.
         * 
         * @param value String
         * @return Source
         */
        public static Source get(String value) {
            return lookup.get(value);
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * 
         * @return the value
         */
        public String getValue() {
            return value;
        }

    }

    /**
     */
    public enum SourceTransactionType {
        /**
         * Field Sale.
         */
        Sale("VENTA"),
        /**
         * Field Cancellation.
         */
        Cancellation("ANULACION"),
        /**
         * Field Refund.
         */
        Refund("DEVOLUCION"),
        /**
         * Field CancellationOrder.
         */
        CancellationOrder("ANULAORDENDEVENTA"),
        /**
         * Field Order.
         */
        Order("ORDENDEVENTA"),
        /**
         * Field CreditsEpaPay.
         */
        CreditsEpaPay("PAGOCREDITO"),
        /**
         * Field CreditsEpaCancellation.
         */
        CreditsEpaCancellation("ANULACREDITO"),
        /**
         * Field Credits.
         */
        Credits("ACREENCIAS"),
        /**
         * Field NonAttendance.
         */
        NonAttendance("NOPRESENCIAL"),
        /**
         * Field OrderDeposit.
         */
        OrderDeposit("ORDENDEVENTA"),
        /**
         * Field CancellationCredits.
         */
        CancellationCredits("ANULACIONACREENCIA"),
        /**
         * Field RefundCredits.
         */
        RefundCredits("DEVOLUCIONACREENCIA");

        /**
         * Field lookup.
         */
        private static final Map<String, SourceTransactionType> lookup = new HashMap<String, SourceTransactionType>();

        static {
            for (SourceTransactionType s : EnumSet.allOf(SourceTransactionType.class))
                lookup.put(s.getValue(), s);
        }

        /**
         * Field value.
         */
        private String value;

        /**
         * Constructor for SourceTransactionType.
         * 
         * @param value String
         */
        SourceTransactionType(String value) {
            this.setValue(value);
        }

        /**
         * Method get.
         * 
         * @param value String
         * @return SourceTransactionType
         */
        public static SourceTransactionType get(String value) {
            return lookup.get(value);
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * 
         * @return the value
         */
        public String getValue() {
            return value;
        }
    }

    /**
     */
    public enum TransactionState {
        /**
         * Field INCOMPLETE.
         */
        INCOMPLETE("I"),
        /**
         * Field COMPLETE.
         */
        COMPLETE("F"),
        /**
         * Field ABORTED.
         */
        ABORTED("A"),
        /**
         * Field WAITING.
         */
        WAITING("E");

        /**
         * Field lookup.
         */
        private static final Map<String, TransactionState> lookup = new HashMap<String, TransactionState>();

        static {
            for (TransactionState s : EnumSet.allOf(TransactionState.class))
                lookup.put(s.getValue(), s);
        }

        /**
         * Field value.
         */
        private String value;

        /**
         * Constructor for TransactionState.
         * 
         * @param value String
         */
        TransactionState(String value) {
            this.setValue(value);
        }

        /**
         * Method get.
         * 
         * @param value String
         * @return TransactionState
         */
        public static TransactionState get(String value) {
            return lookup.get(value);
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * 
         * @return the value
         */
        public String getValue() {
            return value;
        }
    }

    /**
     */
    public enum TransactionPhase {
        /**
         * Field ARTICLE.
         */
        ARTICLE("ART"),
        /**
         * Field CUSTOMER.
         */
        CUSTOMER("CLT"),
        /**
         * Field DONATION.
         */
        DONATION("DNC"),
        /**
         * Field PAYMENTS.
         */
        PAYMENTS("PAG"),
        /**
         * Field PRINTING.
         */
        PRINTING("IMP"),
        /**
         * Field TRANSTIME.
         */
        TRANSTIME("TRN");

        /**
         * Field lookup.
         */
        private static final Map<String, TransactionPhase> lookup = new HashMap<String, TransactionPhase>();

        static {
            for (TransactionPhase s : EnumSet.allOf(TransactionPhase.class))
                lookup.put(s.getValue(), s);
        }

        /**
         * Field value.
         */
        private String value;

        /**
         * Constructor for TransactionPhase.
         * 
         * @param value String
         */
        TransactionPhase(String value) {
            this.setValue(value);
        }

        /**
         * Method get.
         * 
         * @param value String
         * @return TransactionPhase
         */
        public static TransactionPhase get(String value) {
            return lookup.get(value);
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * 
         * @return the value
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * Method getSaleOrigin.
     * 
     * @return Source
     */
    public Source getSaleOrigin() {
        return saleOrigin;
    }

    /**
     * Method setSaleOrigin.
     * 
     * @param so Source
     */
    public void setSaleOrigin(Source so) {
        Source tmp = saleOrigin;
        saleOrigin = so;
        this.fire("saleOrigin", tmp, so);
    }

    /**
     * Method getTransactionType.
     * 
     * @return SourceTransactionType
     */
    public SourceTransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Method setTransactionType.
     * 
     * @param tt SourceTransactionType
     */
    public void setTransactionType(SourceTransactionType tt) {
        transactionType = tt;
    }

    /**
     * Method getDiference.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getDiference() {
        return diference;
    }

    /**
     * Method setDiference.
     * 
     * @param diference CRBigDecimal
     */
    public void setDiference(CRBigDecimal diference) {
        CRBigDecimal tmp = this.diference;
        this.diference = diference;
        if (this.diference.compareTo(CRBigDecimal.ZERO) < 0
                && !(this.getTransactionType() == SourceTransactionType.Refund)) {
            this.change = this.diference.abs();
        } else {
            this.change = CRBigDecimal.ZERO;
        }
        this.fire("diference", tmp, diference);
    }

    /**
     * 
     * @return the totalCharge
     */
    public CRBigDecimal getTotalCharge() {
        return totalCharge;
    }

    /**
     * @param totalCharge the totalCharge to set
     */
    private void setTotalCharge(CRBigDecimal totalCharge) {
        CRBigDecimal tmp = this.totalCharge;
        this.totalCharge = totalCharge;
        this.fire("totalCharge", tmp, totalCharge);
    }

    /**
     * Method recalculateArticlesIndex.
     */
    private synchronized void recalculateArticlesIndex() {

        articlesIndex.clear();
        ArrayList<Article> tmp = new ArrayList<Article>(articles);
        for (Article art : tmp) {
            // articlesIndex.put(art.getCategorizedCode(),tmp.indexOf(art));
            if (articlesIndex.containsKey(art.getArticlesTableIndex())) {
                Article sibling = ((ArrayList<Article>) articles)
                        .get(articlesIndex.get(art.getArticlesTableIndex()));
                // sibling.setItems(sibling.getItems().add(art.getItems()));
                this.articles.remove(art);
                // this.removeArticle(art);
                int articleIndex = 0;
                if (articlesIndex.get(sibling.getArticlesTableIndex()) != null) {
                    articleIndex = articlesIndex.get(sibling.getArticlesTableIndex());
                }
                Article artinSale = ((ArrayList<Article>) this.getArticles()).get(articleIndex);

                artinSale.setItems(sibling.getItems().add(art.getItems()));

                // this.updateArticle(sibling, art.getItems());
            } else {
                articlesIndex.put(art.getArticlesTableIndex(), tmp.indexOf(art));
            }

        }
    }

    /**
     * @param deliveryInfo the deliveryInfo to set
     */
    public void setDeliveryInfo(HashMap<Integer, DeliveryCondition> deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    /**
     * 
     * @return the deliveryInfo
     */
    public HashMap<Integer, DeliveryCondition> getDeliveryInfo() {
        return deliveryInfo;
    }

    /**
     * Method getNumber.
     * 
     * @return String
     */
    public String getNumber() {
        return number;
    }

    /**
     * Method setNumber.
     * 
     * @param number String
     */
    public void setNumber(String number) {
        String tmp = this.number;
        this.number = number;
        buildCode();
        this.fire("id", tmp, number);
    }

    /**
     * Method buildCode.
     */
    private void buildCode() {

        int tiendatmp = (this.store == null) ? 0 : Integer.valueOf(this.store);
        int cajatmp = (this.posId == null) ? 0 : Integer.valueOf(this.posId);
        int transtmp = (this.number == null) ? 0 : Integer.valueOf(this.number);

        DecimalFormat storeformatter = new DecimalFormat("000");
        DecimalFormat posformatter = new DecimalFormat("000");
        DecimalFormat transformatter = new DecimalFormat("000000");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String tienda = storeformatter.format(tiendatmp);
        String caja = posformatter.format(cajatmp);
        String trans = transformatter.format(transtmp);

        this.code = tienda + sdf.format(date) + caja + trans;

        SimpleDateFormat sdfhour = new SimpleDateFormat("HH:mm");
        this.setHour(sdfhour.format(date));

    }

    /**
     * Method getFiscalId.
     * 
     * @return int
     */
    public int getFiscalId() {
        return fiscalId;
    }

    /**
     * Method setFiscalId.
     * 
     * @param i int
     */
    public void setFiscalId(int i) {
        int tmp = this.fiscalId;
        this.fiscalId = i;
        this.fire("fiscalId", tmp, i);
    }

    /**
     * Method getDate.
     * 
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Method setDate.
     * 
     * @param date Date
     */
    public void setDate(Date date) {
        Date tmp = this.date;
        this.date = date;
        SimpleDateFormat sdfhour = new SimpleDateFormat("HH:mm");
        this.setHour(sdfhour.format(date));
        this.fire("date", tmp, date);
    }

    /**
     * Method setDonation.
     * 
     * @param donation Donation
     */
    public void setDonation(Donation donation) {

        this.donation = donation;
        setTotalCharge(getTotalCost().add(getDonation().getAmount()));
        setDiference(getTotalCharge().subtract(getTotalPay()));
    }

    /**
     * 
     * @return the donation
     */
    public Donation getDonation() {
        return donation;
    }

    /**
     * Method getStore.
     * 
     * @return String
     */
    public String getStore() {
        return store;
    }

    /**
     * Method setStore.
     * 
     * @param store String
     */
    public void setStore(String store) {
        this.store = store;
        buildCode();
    }

    /**
     * Method getPosId.
     * 
     * @return String
     */
    public String getPosId() {
        return posId;
    }

    /**
     * Method setPosId.
     * 
     * @param posId String
     */
    public void setPosId(String posId) {
        this.posId = posId;
        buildCode();
    }

    /**
     * Method getRebates.
     * 
     * @return Collection<Rebate>
     */
    public Collection<Rebate> getRebates() {
        return rebates;
    }

    /**
     * Method setRebates.
     * 
     * @param rebates Collection<Rebate>
     */
    public void setRebates(Collection<Rebate> rebates) {
        this.rebates = rebates;
    }

    /**
     * Method getTaxes.
     * 
     * @return Map
     */
    public Map<Tax, TaxAmountWrapper> getTaxes() {
        return taxes;
    }

    /**
     * Method setTaxes.
     * 
     * @param taxes Map
     */
    public void setTaxes(Map<Tax, TaxAmountWrapper> taxes) {
        this.taxes = taxes;
    }

    /**
     * Method initTransaction.
     */
    public void initTransaction() {
        this.setActive(false);
        this.setArticles(new ArrayList<Article>());
        this.setArticlesCount(CRBigDecimal.valueOf(0));
        this.articlesIndex.clear();
        this.client.setAddress("");
        this.client.setEconomicActivity(new EconomicActivity(0));
        this.client.setIdNumber(null);
        this.client.setName(null);
        this.client.setPhone(null);
        this.setDate(getDate());
        this.deliveryInfo.clear();
        this.setDiference(CRBigDecimal.valueOf(0));
        this.donation.setAmount(CRBigDecimal.valueOf(0));
        this.setFiscalId(0);
        this.setNumber("0");
        this.payments.clear();
        this.rebates.clear();
        this.setSaleOrigin(Source.Pos);
        this.selectedArticles.clear();
        this.taxes.clear();
        this.setTotalCharge(CRBigDecimal.valueOf(0));
        this.setTotalCost(CRBigDecimal.valueOf(0));
        this.setTotalPay(CRBigDecimal.valueOf(0));
    }

    /**
     * Method initTransaction.
     * 
     * @param originTransaccion Transaction
     */
    public void initTransaction(Transaction originTransaccion) {
        this.setActive(originTransaccion.isActive());
        this.setArticles(originTransaccion.getArticles());
        this.setArticlesCount(originTransaccion.getArticlesCount());
        this.articlesIndex = new ConcurrentHashMap<String, Integer>();
        this.articlesIndex.putAll(originTransaccion.getArticlesIndex());

        this.document = originTransaccion.getDocument();
        this.client = originTransaccion.getClient();
        this.user = originTransaccion.getUser();
        this.setDate(new Date());
        this.deliveryInfo = originTransaccion.getDeliveryInfo();
        this.setDiference(originTransaccion.getDiference());
        this.donation.setAmount(originTransaccion.getDonation().getAmount());
        this.setFiscalId(originTransaccion.getFiscalId());
        this.setId(originTransaccion.getId());
        if (originTransaccion.getPayments() != null && originTransaccion.getPayments().size() > 0) {
            Collection<Payment> paymentsFromOriginTransaccion = new ArrayList<Payment>();
            paymentsFromOriginTransaccion.addAll(originTransaccion.getPayments());
            this.payments = paymentsFromOriginTransaccion;
        }
        this.rebates = originTransaccion.getRebates();
        this.setSaleOrigin(originTransaccion.getSaleOrigin());
        this.selectedArticles = originTransaccion.getSelectedArticles();
        this.taxes = originTransaccion.getTaxes();
        this.setTotalCharge(originTransaccion.getTotalCharge());
        this.setTotalCost(originTransaccion.getTotalCost());
        this.setTotalPay(originTransaccion.getTotalPay());
        this.setBaseAmount(originTransaccion.getBaseAmount());
        this.setTaxAmount(originTransaccion.getTaxAmount());
        this.setPosId(originTransaccion.getPosId());
        this.setStore(originTransaccion.getStore());
        this.setTransactionAudits(originTransaccion.getTransactionAudits());
    }

    /**
     * Method getId.
     * 
     * @return long
     */
    public long getId() {
        return id;
    }

    /**
     * Method setId.
     * 
     * @param id long
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Method getStatus.
     * 
     * @return String
     */
    public String getStatus() {
        return status;
    }

    /**
     * Method setStatus.
     * 
     * @param status String
     */
    public void setStatus(String status) {
        if (status.equalsIgnoreCase(TransactionState.COMPLETE.getValue())) {
            updatePhase(TransactionPhase.TRANSTIME, false);
        }

        this.status = status;
    }

    /**
     * Method getPrinterSerial.
     * 
     * @return String
     */
    public String getPrinterSerial() {
        return printerSerial;
    }

    /**
     * Method setPrinterSerial.
     * 
     * @param printerSerial String
     */
    public void setPrinterSerial(String printerSerial) {
        this.printerSerial = printerSerial;
        buildCode();
    }

    /**
     * Method getBaseAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getBaseAmount() {
        return baseAmount;
    }

    /**
     * Method setBaseAmount.
     * 
     * @param exemptAmount CRBigDecimal
     */
    public void setBaseAmount(CRBigDecimal exemptAmount) {
        this.baseAmount = exemptAmount;
    }

    /**
     * Method getTaxAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * Method setTaxAmount.
     * 
     * @param taxAmount CRBigDecimal
     */
    public void setTaxAmount(CRBigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    /**
     * @param totalTaxAmount the totalTaxAmount to set
     */
    public void setTotalTaxAmount(CRBigDecimal totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    /**
     * 
     * @return the totalTaxAmount
     */
    public CRBigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    /**
     * Method getDiscountTypeId.
     * 
     * @return long
     */
    public long getDiscountTypeId() {
        return discountTypeId;
    }

    /**
     * Method setDiscountTypeId.
     * 
     * @param discountTypeId long
     */
    public void setDiscountTypeId(long discountTypeId) {
        this.discountTypeId = discountTypeId;
    }

    /**
     * Method clearPayments.
     */
    public void clearPayments() {

        this.payments.clear();
        this.totalPaid = CRBigDecimal.ZERO;
        this.setDiference(this.totalCost);
    }

    /**
     * Method setDocuments.
     * 
     * @param documents List<TransactionDocument>
     */
    public void setDocuments(List<TransactionDocument> documents) {
        this.documents = documents;
    }

    /**
     * Method getDocuments.
     * 
     * @return List<TransactionDocument>
     */
    public List<TransactionDocument> getDocuments() {
        return documents;
    }

    /**
     * Method addDocument.
     * 
     * @param document TransactionDocument
     */
    public void addDocument(TransactionDocument document) {
        this.documents.add(document);
    }

    /**
     * Method putOriginalArticle.
     * 
     * @param code String
     * @param obj Object[]
     */
    public void putOriginalArticle(String code, Object obj[]) {
        if (this.OriginalArticle == null) {
            this.OriginalArticle = new HashMap<String, Object[]>();
        }
        this.OriginalArticle.put(code, obj);
    }

    /**
     * Method removeOriginalArticle.
     * 
     * @param code String
     */
    public void removeOriginalArticle(String code) {
        this.OriginalArticle.remove(code);
    }

    /**
     * Method getOriginalArticle.
     * 
     * @return HashMap<String,Object[]>
     */
    @JsonIgnore
    public HashMap<String, Object[]> getOriginalArticle() {
        return OriginalArticle;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Method getIdCancellation.
     * 
     * @return long
     */
    public long getIdCancellation() {
        return idCancellation;
    }

    /**
     * Method setIdCancellation.
     * 
     * @param idCancellation long
     */
    public void setIdCancellation(long idCancellation) {
        this.idCancellation = idCancellation;
    }

    /**
     * Method getTotalRebate.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getTotalRebate() {
        return totalRebate;
    }

    /**
     * @param totalRebate the totalRebate to set
     */
    public void setTotalRebate(CRBigDecimal totalRebate) {
        this.totalRebate = totalRebate;
    }

    /**
     * @param deliveryCondition the deliveryCondition to set
     */
    public void setDeliveryCondition(DeliveryCondition deliveryCondition) {
        this.deliveryCondition = deliveryCondition;
    }

    /**
     * 
     * @return the deliveryCondition
     */
    public DeliveryCondition getDeliveryCondition() {
        return deliveryCondition;
    }

    /**
     * <p> Metodo que retorna las condiciones de entrega de los articulos en una
     * transacción.
     * 
     * @return {@code null} si la transaccion no posee articulos, en caso contrario
     *         devuelve un conjunto de las condiciones de entrega.
     */
    @JsonIgnore
    public HashSet<Integer> getArticlesDeliveryCondition() {
        HashSet<Integer> list = new HashSet<Integer>();
        for (Article article : getArticles()) {
            list.add(article.getDeliveryCondition().getId());
        }
        return (list.size() > 0) ? list : null;
    }

    /**
     * Method getIdPaperRoll.
     * 
     * @return long
     */
    public long getIdPaperRoll() {
        return idPaperRoll;
    }

    /**
     * Method setIdPaperRoll.
     * 
     * @param idPaperRoll long
     */
    public void setIdPaperRoll(long idPaperRoll) {
        this.idPaperRoll = idPaperRoll;
    }

    /**
     * @param hour the hour to set
     */
    public void setHour(String hour) {
        this.hour = hour;
    }

    /**
     * 
     * @return the hour
     */
    public String getHour() {
        return hour;
    }

    /**
     * Method getRebateDesc.
     * 
     * @return String
     */
    public String getRebateDesc() {
        return rebateDesc;
    }

    /**
     * Method setRebateDesc.
     * 
     * @param rebateDesc String
     */
    public void setRebateDesc(String rebateDesc) {
        this.rebateDesc = rebateDesc;
    }

    @JsonIgnore
    public void setRebateDesc(Map<String, String> rebateMap) {
        String rebateDesc = null;
        if (rebateMap.size() > 0) {
            rebateDesc = "";
            for (String name : rebateMap.keySet()) {
                rebateDesc = rebateDesc + name + ":" + rebateMap.get(name) + " ";
            }
        }
        setRebateDesc(rebateDesc);
    }

    /**
     * Method isTeleMarketing.
     * 
     * @return boolean
     */
    public boolean isTeleMarketing() {
        return teleMarketing;
    }

    /**
     * Method setTeleMarketing.
     * 
     * @param teleMarketing boolean
     */
    public void setTeleMarketing(boolean teleMarketing) {
        boolean tmp = this.teleMarketing;
        this.teleMarketing = teleMarketing;
        this.fire("teleMarketing", tmp, teleMarketing);
    }

    /**
     * Method getOldDonation.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getOldDonation() {
        return oldDonation;
    }

    /**
     * Method setOldDonation.
     * 
     * @param oldDonation CRBigDecimal
     */
    public void setOldDonation(CRBigDecimal oldDonation) {
        this.oldDonation = oldDonation;
    }

    /**
     * 
     * @return the change
     */
    public CRBigDecimal getChange() {
        return change;
    }

    /**
     * Method getArticleByCode.
     * 
     * @param code String
     * @return Article
     */
    public Article getArticleByCode(String code) {

        if (this.articles != null && this.articles.size() > 0) {

            for (Article article : this.articles) {
                if (Integer.parseInt(article.getCode().trim()) == Integer.parseInt(code.trim())) {
                    return article;
                }
            }

        } else {
            return null;
        }
        return null;
    }

    public Article getArticleByTransactionArticleId(long id) {

        if (this.articles != null && this.articles.size() > 0) {

            for (Article article : this.articles) {
                if (article.getTransactionArticleId() == id) {
                    return article;
                }
            }

        } else {
            return null;
        }
        return null;
    }

    /**
     * Method setCredit.
     * 
     * @param b boolean
     */
    public void setCredit(PaymentConditions b) {
        this.credit = b;

    }

    /**
     * 
     * @return the credit
     */
    public PaymentConditions getCredit() {
        return this.credit;
    }

    /**
     * @param phases the phases to set
     */
    public void setPhases(Map<TransactionPhase, Phase> phases) {
        this.phases = phases;
    }

    /**
     * 
     * @return the phases
     */
    public Map<TransactionPhase, Phase> getPhases() {
        return phases;
    }

    /**
     * Method getPhaseTime.
     * 
     * @param transaccionfase TransactionPhase
     * @return Date
     */
    public Date getPhaseTime(TransactionPhase transaccionfase) {
        Date result = new Date(0);

        if (phases.containsKey(transaccionfase)) {
            Phase tmp = this.phases.get(transaccionfase);
            if (tmp.getElapsed() > 0) {
                result = new Date(TimeUnit.NANOSECONDS.toMillis(tmp.getElapsed()));
            }
        }

        return result;
    }

    /**
     * Method updatePhaseId.
     * 
     * @param transaccionfase TransactionPhase
     * @param id long
     */
    public void updatePhaseId(TransactionPhase transaccionfase, long id) {

        if (phases.containsKey(transaccionfase)) {
            Phase tmp = this.phases.get(transaccionfase);
            tmp.setId(id);
        }

    }

    /**
     * Method getPhaseId.
     * 
     * @param transaccionfase TransactionPhase
     * @return Long
     */
    public Long getPhaseId(TransactionPhase transaccionfase) {
        if (phases.containsKey(transaccionfase)) {
            Phase tmp = this.phases.get(transaccionfase);
            return tmp.getId();
        }
        return (long) -1;
    }

    public void clearIds() {
        clearIds(false);
    }

    public void clearIds(boolean isFromCancellation) {
        this.setNumber(null);
        this.setId(-1);
        this.setStatus(TransactionState.INCOMPLETE.getValue());

        for (Iterator<TransactionDocument> iterator = this.getDocuments().iterator(); iterator.hasNext();) {
            TransactionDocument type = iterator.next();
            type.setTransactionId(new Long(-1));
            type.setId(-1);

        }

        for (Iterator<Article> iterator = this.getArticles().iterator(); iterator.hasNext();) {
            Article type = iterator.next();
            type.setTransactionArticleId(-1);

        }

        for (Iterator<Payment> iterator = this.getPayments().iterator(); iterator.hasNext();) {
            Payment type = iterator.next();
            type.setTransactionPaymentId(-1);

        }

        for (Iterator<TransactionPhase> iterator = this.getPhases().keySet().iterator(); iterator.hasNext();) {
            TransactionPhase type = iterator.next();
            this.updatePhaseId(type, -1);

        }

        if (!isFromCancellation) {
            if (this instanceof AnulDev) {
                ((AnulDev) this).getOriginalSale().setId(-1);
            }
        }

        this.getClient().setTransactionClientId(-1);

    }

    @JsonIgnore
    public Map<Long, Article> getRestrictedArticles() {
        Map<Long, Article> restrictedArticles = new HashMap<Long, Article>();
        for (Article art : getArticles()) {
            if (art.getBuyPeriod() == 0) {
                continue;
            }

            Article resArticle = restrictedArticles.get(art.getId());
            if (resArticle != null) {
                resArticle.setItems(resArticle.getItems().add(art.getItems()));
            } else {
                Article article = new Article();
                article.setId(art.getId());
                article.setItems(art.getItems());
                article.setCode(art.getCode());
                article.setAmountMaxTransaction(art.getAmountMaxTransaction());
                article.setAmountMaxPeriod(art.getAmountMaxPeriod());
                article.setBuyPeriod(art.getBuyPeriod());

                restrictedArticles.put(art.getId(), article);
            }
        }

        return restrictedArticles;
    }

    @Override
    public String getAuditID() {
        return String.valueOf(this.id);
    }

    public List<TransactionAudit> getTransactionAudits() {
        return transactionAudits;
    }

    public void setTransactionAudits(List<TransactionAudit> transactionAudits) {
        this.transactionAudits = transactionAudits;
    }

    public void addTransactionAudit(TransactionAudit transactionAudit) {
        if (this.transactionAudits == null) {
            this.transactionAudits = new ArrayList<TransactionAudit>();
        }
        this.transactionAudits.add(transactionAudit);
    }

    /**
     * @return the resolution
     */
    public PrePrintedFiscalReceipt getResolution() {
        return resolution;
    }

    /**
     * @param resolution the resolution to set
     */
    public void setResolution(PrePrintedFiscalReceipt resolution) {
        this.resolution = resolution;
    }

    @JsonIgnore
    public boolean isValidClientName() {
        return !getClient().getName().isEmpty();
    }

    @JsonIgnore
    public boolean isValidClientIdn() {
        return !getClient().getIdNumber().isEmpty();
    }

    public void buildDeliveryInfo() {
        if (deliveryInfo != null) {
            deliveryInfo.clear();
        } else {
            deliveryInfo = new HashMap<Integer, DeliveryCondition>();
        }
        if (articles != null && !articles.isEmpty()) {
            for (Article article : articles) {
                DeliveryCondition delivery = article.getDeliveryCondition();
                deliveryInfo.put(delivery.getId(), delivery);
            }
        }
    }

    public Collection<Payment> getPaymentsByDonationPriority() {
        List<Payment> ordered = new LinkedList<Payment>(payments);
        Collections.sort(ordered, new Payment.OrderByDonationPriority());
        return ordered;
    }
}
