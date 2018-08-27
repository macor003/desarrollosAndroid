/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

import com.becoblohm.cr.models.Transaction.Source;
import com.becoblohm.cr.types.CRBigDecimal;
import com.becoblohm.cr.types.TransactionCalculator;
import com.epa.mvc.core.AbstractModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
              include = JsonTypeInfo.As.PROPERTY,
              property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = OrderArticle.class,
                                  name = "orderarticle")})
public class Article extends AbstractModel {

    private Long id;

    private long transactionArticleId = -1;

    private static final long serialVersionUID = -2408889580722792351L;

    public final static String manualCapture = "MANUAL";

    public final static String scannerCapture = "SCANNER";

    private String code;

    private String name;

    private CRBigDecimal items = CRBigDecimal.ZERO;

    /**
     * Costo total de los articulos (costo con impuesto x cantidad)
     */
    private CRBigDecimal totalCost = CRBigDecimal.ZERO;

    private CRBigDecimal nonTaxedTotalCost = CRBigDecimal.ZERO;

    /**
     * Costo unitario original sin impuesto
     */
    private CRBigDecimal originalItemCost = CRBigDecimal.ZERO;

    /**
     * Costo unitario del articulo sin impuesto luego de las rebajas
     */
    private CRBigDecimal itemCost = CRBigDecimal.ZERO;

    /**
     * Costo unitario con impuesto luego de las rebajas
     */
    private CRBigDecimal itemTaxedCost = CRBigDecimal.ZERO;

    /**
     * Costo unitario original con impuesto
     */
    private CRBigDecimal originalItemTaxedCost = CRBigDecimal.ZERO;

    private CRBigDecimal rebateAmount = CRBigDecimal.ZERO;

    public CRBigDecimal getRebateAmountNoCalcule() {
        return rebateAmount;
    }

    public void setRebateAmountNoCalcule(CRBigDecimal rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    /**
     * Impuesto unitario original
     */
    private Tax originalItemTax;

    private Tax tax;

    private ArticleLine line;

    private DeliveryCondition deliveryCondition;

    private DiscountType discountType;

    private SaleUnit saleUnit;

    private int motive;

    private String descriptionMotive;

    private Vector<Integer> processMotive = new Vector<Integer>();

    private CRBigDecimal totalTaxAmount = CRBigDecimal.ZERO;

    private HashMap<Long, Service> services = new HashMap<Long, Service>();

    private String captureMode;

    private TreeMap<CRBigDecimal, PromotionsMethod> promoArticle;

    private TreeMap<CRBigDecimal, PromotionsMethod> promoFamily;

    private TreeMap<CRBigDecimal, PromotionsMethod> promoLine;

    private TreeMap<CRBigDecimal, PromotionsMethod> promoCategory;

    private PromotionsMethod selectedPromo;

    private Family family;

    private String epaCode;

    private boolean rebate = false;

    private Source source = Source.Pos;

    private CRBigDecimal taxAmount = CRBigDecimal.ZERO;// TODO Precio Final;

    private CRBigDecimal amountMaxTransaction = CRBigDecimal.ZERO;;

    private CRBigDecimal amountMaxPeriod = CRBigDecimal.ZERO;;

    private int buyPeriod = 0;

    public Article() {

    }

    public Source getSource() {
        return source;
    }

    public boolean isRebate() {
        return this.rebate;
    }

    public void setRebate(boolean rebate) {
        this.rebate = rebate;
    }

    public Article(String code, String name, CRBigDecimal items, CRBigDecimal totalCost, CRBigDecimal itemCost,
                   Tax tax, ArticleLine line, DeliveryCondition delivery, DiscountType discountType) {
        super();
        this.setCode(code);
        this.name = name;
        this.items = items;
        this.totalCost = totalCost;
        this.itemCost = itemCost;
        this.tax = tax;
        this.line = line;
        // this.taxCategory=taxCategory;Inicial
        this.deliveryCondition = delivery;
        this.discountType = discountType;
        updateCost();
    }

    public Article(Article article) {
        this.code = article.getCode();
        this.epaCode = article.getEpaCode();
        this.name = article.getName();
        this.items = article.getItems();
        this.totalCost = article.getTotalCost();
        this.itemCost = article.getItemCost();
        this.tax = article.getTax();
        this.line = article.getLine();
        this.deliveryCondition = article.getDeliveryCondition();
        this.discountType = article.getDiscountType();
        this.id = article.getId();
        this.nonTaxedTotalCost = article.getNonTaxedTotalCost();
        this.originalItemCost = article.getOriginalItemCost();
        this.originalItemTax = article.getOriginalItemTax();
        this.tax = article.getTax();
        this.line = article.getLine();
        this.saleUnit = article.getSaleUnit();
        this.motive = article.getMotive();
        this.processMotive = article.getProcessMotive();
        this.totalTaxAmount = article.getTotalTaxAmount();
        this.services = article.getServices();
        this.captureMode = article.getCaptureMode();
        this.processMotive = article.getProcessMotive();
        this.promoArticle = article.getPromoArticle();
        this.promoCategory = article.getPromoCategory();
        this.promoFamily = article.getPromoFamily();
        this.promoLine = article.getPromoLine();
        this.amountMaxPeriod = article.getAmountMaxPeriod();
        this.amountMaxTransaction = article.getAmountMaxTransaction();
        this.buyPeriod = article.getBuyPeriod();
        this.rebate = article.isRebate();
        this.rebateAmount = article.getRebateAmount();
    }

    public Article clone() {

        Article art = new Article();
        art.setDeliveryCondition(DeliveryCondition.createDefault());
        art.setDiscountType(discountType.clone());
        art.setItemCost(CRBigDecimal.ZERO);
        art.setItems(CRBigDecimal.ZERO);

        art.setSaleUnit(saleUnit);

        art.setTax(tax.clone());
        art.setSource(Source.Pos);

        art.setAmountMaxPeriod(this.amountMaxPeriod);
        art.setAmountMaxTransaction(this.amountMaxTransaction);
        art.setBuyPeriod(this.buyPeriod);
        return art;
    }

    private void updateCost() {
        TransactionCalculator.getInstance().getCalculator().updateArticleCosts(this);
    }

    private void updateCostFiscal() {
        CRBigDecimal taxRate;
        if (this.getTax() == null) {
            taxRate = CRBigDecimal.ZERO;
        } else if (this.getTax().getTaxRate().compareTo(CRBigDecimal.ZERO) != 0) {
            taxRate = tax.getTaxRate();
        } else {
            taxRate = CRBigDecimal.ZERO;
        }
        double tmp = this.items.getValue().multiply(getTaxAmount().getValue()).doubleValue();
        CRBigDecimal taxtmp = new CRBigDecimal(tmp, 5); // (itemCost*(taxRate/100));
        this.itemTaxedCost = this.itemCost.add(itemCost.multiply(taxRate.divide(CRBigDecimal.HUNDRED)));

        this.itemTaxedCost = new CRBigDecimal(this.itemCost.getValue().add(getTaxAmount().getValue())
                .setScale(2, RoundingMode.DOWN).doubleValue());
        this.nonTaxedTotalCost = this.items.multiply(itemCost);
        this.totalTaxAmount = taxtmp;
        this.totalCost = taxtmp.add(items.multiply(itemCost));//
        this.rebateAmount = getOriginalItemCost().subtract(getItemCost());
    }

    public CRBigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(CRBigDecimal totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String tmp = this.name;
        this.name = name;
        this.fire("name", tmp, name);
    }

    public CRBigDecimal getItems() {
        return items;
    }

    public void setItems(CRBigDecimal items) {
        CRBigDecimal tmp = this.items;
        this.items = items;
        updateCost();
        this.fire("items", CRBigDecimal.ZERO, items);
    }

    public CRBigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(CRBigDecimal totalCost) {
        CRBigDecimal tmp = this.totalCost;
        this.totalCost = totalCost;
        this.fire("totalCost", tmp, totalCost);
    }

    public CRBigDecimal getItemCost() {
        return itemCost;
    }

    public void setItemCost(CRBigDecimal itemCost) {
        CRBigDecimal tmp = this.itemCost;
        this.itemCost = itemCost;
        updateCost();
        this.fire("itemCost", tmp, itemCost);
    }

    @JsonIgnore
    public CRBigDecimal getReducedCost() {
        return this.getOriginalItemCost().subtract(this.getRebateAmount());
    }

    public Tax getTax() {
        return tax;
    }

    public CRBigDecimal getOriginalItemTaxedCost() {
        return originalItemTaxedCost;
    }

    public void setOriginalItemTaxedCost(CRBigDecimal originalItemTaxedCost) {
        this.originalItemTaxedCost = originalItemTaxedCost;
    }

    public Tax getOriginalItemTax() {
        return originalItemTax;
    }

    public void setOriginalItemTax(Tax originalItemTax) {
        Tax tmp = this.originalItemTax;
        this.originalItemTax = originalItemTax;
        updateCost();
        this.fire("originalItemTax", tmp, originalItemTax);
    }

    public void setTax(Tax tax) {
        this.tax = tax;
        updateCost();
    }

    public void setCode(String code) {
        String tmp = this.code;
        this.code = code;
        this.fire("code", tmp, code);
        if (code != null && !code.equals("") && code.length() <= String.valueOf(Integer.MAX_VALUE).length()) {
            setEpaCode(new DecimalFormat("0000000").format(Long.valueOf(this.code)));
        } else {
            setEpaCode("");
        }
    }

    public String getCode() {
        return code;
    }

    public DeliveryCondition getDeliveryCondition() {
        return deliveryCondition;
    }

    public void setDeliveryCondition(DeliveryCondition deliveryCondition) {
        Object tmp = this.deliveryCondition;
        this.deliveryCondition = deliveryCondition;
        this.fire("deliveryCondition", tmp, deliveryCondition);
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        Object tmp = this.discountType;
        this.discountType = discountType;
        this.fire("discountType", tmp, discountType);
    }

    public SaleUnit getSaleUnit() {
        return saleUnit;
    }

    public void setSaleUnit(SaleUnit saleUnit) {
        this.saleUnit = saleUnit;
    }

    public int getMotive() {
        return motive;
    }

    public void setMotive(int motive) {
        this.motive = motive;
    }

    public String getDescriptionMotive() {
        return descriptionMotive;
    }

    public void setDescriptionMotive(String descriptionMotive) {
        this.descriptionMotive = descriptionMotive;
    }

    public Vector<Integer> getProcessMotive() {
        return processMotive;
    }

    public void setProcessMotive(Vector<Integer> processMotive) {
        this.processMotive = processMotive;
    }

    public CRBigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(CRBigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public long getTransactionArticleId() {
        return transactionArticleId;
    }

    public void setTransactionArticleId(long transactionArticleId) {
        this.transactionArticleId = transactionArticleId;
    }

    public void setServices(HashMap<Long, Service> services) {
        this.services = services;
    }

    public HashMap<Long, Service> getServices() {
        return services;
    }

    public ArticleLine getLine() {
        return line;
    }

    public void setLine(ArticleLine line) {
        ArticleLine tmp = this.line;
        this.line = line;
        this.fire("line", tmp, line);
    }

    public PromotionsMethod getSelectedPromo() {
        return selectedPromo;
    }

    public void setSelectedPromo(PromotionsMethod selectedPromo) {
        this.selectedPromo = selectedPromo;
    }

    public TreeMap<CRBigDecimal, PromotionsMethod> getPromoArticle() {
        return promoArticle;
    }

    public void setPromoArticle(TreeMap<CRBigDecimal, PromotionsMethod> promoArticle) {
        this.promoArticle = promoArticle;
    }

    public CRBigDecimal getNonTaxedTotalCost() {
        return nonTaxedTotalCost;
    }

    public void setNonTaxedTotalCost(CRBigDecimal nonTaxedTotalCost) {
        this.nonTaxedTotalCost = nonTaxedTotalCost;
    }

    public CRBigDecimal getOriginalItemCost() {
        return originalItemCost;
    }

    public void setOriginalItemCost(CRBigDecimal originalItemCost) {
        this.originalItemCost = originalItemCost;
    }

    public void setFamily(Family family) {
        this.family = family;

    }

    public Family getFamily() {
        return family;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        String tmp = "";
        if (this.tax != null) {
            tmp = this.tax.getTaxRate().getValue().toString();
        }
        return "ToString: " + this.code + " " + this.totalCost + " " + this.nonTaxedTotalCost + " "
                + this.totalTaxAmount + " " + tmp;
    }

    public void setEpaCode(String epaCode) {
        String tmp = this.epaCode;
        this.epaCode = epaCode;
        this.fire("epaCode", tmp, epaCode);

    }

    public String getEpaCode() {
        return this.epaCode;
    }

    public String getCaptureMode() {
        return captureMode;
    }

    public void setCaptureMode(String captureMode) {
        this.captureMode = captureMode;
    }

    public TreeMap<CRBigDecimal, PromotionsMethod> getPromoFamily() {
        return promoFamily;
    }

    public void setPromoFamily(TreeMap<CRBigDecimal, PromotionsMethod> promoFamily) {
        this.promoFamily = promoFamily;
    }

    public TreeMap<CRBigDecimal, PromotionsMethod> getPromoLine() {
        return promoLine;
    }

    public void setPromoLine(TreeMap<CRBigDecimal, PromotionsMethod> promoLine) {
        this.promoLine = promoLine;
    }

    public TreeMap<CRBigDecimal, PromotionsMethod> getPromoCategory() {
        return promoCategory;
    }

    public void setPromoCategory(TreeMap<CRBigDecimal, PromotionsMethod> promoCategory) {
        this.promoCategory = promoCategory;
    }

    public void setItemTaxedCost(CRBigDecimal itemTaxedCost) {
        this.itemTaxedCost = itemTaxedCost;
    }

    public CRBigDecimal getItemTaxedCost() {
        return itemTaxedCost;
    }

    public void setRebateAmount(CRBigDecimal rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    @JsonIgnore
    public CRBigDecimal getRebateAmount() {
        return this.getOriginalItemCost().subtract(this.getItemCost());
    }

    @JsonIgnore
    public CRBigDecimal getPlainRebateAmount() {
        return getRebateAmount();
    }

    @JsonIgnore
    public CRBigDecimal getTotalRebateAmount() {
        return getRebateAmount().multiply(this.getItems());
    }

    @JsonIgnore
    public String getArticlesTableIndex() {
        String articlesTableIndex = this.getCode() + "_" + this.getItemCost().toString() + "_"
                + this.getTax().getTaxRate().toString() + "_";
        return articlesTableIndex;
    }

    public void setSource(Source origin) {
        this.source = origin;

    }

    public CRBigDecimal getAmountMaxTransaction() {
        return amountMaxTransaction;
    }

    public void setAmountMaxTransaction(CRBigDecimal cantidadMaximaTransaccion) {
        this.amountMaxTransaction = cantidadMaximaTransaccion;
    }

    public CRBigDecimal getAmountMaxPeriod() {
        return amountMaxPeriod;
    }

    public void setAmountMaxPeriod(CRBigDecimal cantidadMaximaPeriodo) {
        this.amountMaxPeriod = cantidadMaximaPeriodo;
    }

    public int getBuyPeriod() {
        return buyPeriod;
    }

    public void setBuyPeriod(int periodoCompra) {
        this.buyPeriod = periodoCompra;
    }
}
