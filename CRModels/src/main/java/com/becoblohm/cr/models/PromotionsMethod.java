/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.Date;
import java.util.List;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 */
public class PromotionsMethod extends AbstractModel {

    /**
     * Field id.
     */
    protected long id;

    /**
     * Field name.
     */
    protected String name;

    /**
     * Field active.
     */
    protected boolean active;

    /**
     * Field initDate.
     */
    protected Date initDate;

    /**
     * Field endDate.
     */
    protected Date endDate;

    /**
     * Field discountRate.
     */
    protected CRBigDecimal discountRate;

    /**
     * Field discountAmount.
     */
    protected CRBigDecimal discountAmount;

    /**
     * Field paymentMethod.
     */
    protected List paymentMethod;

    /**
     * Field message.
     */
    protected String message;

    /**
     * Field minimumValue.
     */
    protected CRBigDecimal minimumValue;

    /**
     * Field maximumValue.
     */
    protected CRBigDecimal maximumValue;

    /**
     * Field numberOfTransactions.
     */
    protected CRBigDecimal numberOfTransactions;

    /**
     * Field cod.
     */
    protected long cod;

    /**
     * Field discountType.
     */
    protected DiscountType discountType;

    /**
     * Field isAcumulative.
     */
    protected String isAcumulative;

    /**
     * Field mutiple.
     */
    private boolean mutiple;

    /**
     * Field giftAmount.
     */
    private CRBigDecimal giftAmount;

    /**
     * Method getCod.
     * 
     * @return long
     */
    public long getCod() {
        return cod;
    }

    /**
     * Method setCod.
     * 
     * @param cod long
     */
    public void setCod(long cod) {
        this.cod = cod;
    }

    /**
     * Constructor for PromotionsMethod.
     */
    public PromotionsMethod() {
        super();
    }

    /**
     * Method initialize.
     * 
     * @param promotion PromotionsMethod
     */
    public void initialize(PromotionsMethod promotion) {

        this.id = promotion.getId();
        this.initDate = promotion.getInitDate();
        this.endDate = promotion.getEndDate();
        this.active = promotion.getActive();
        this.discountRate = promotion.getDiscountRate();
        this.discountAmount = promotion.getDiscountAmount();
        this.discountType = promotion.getDiscountType();
        this.message = promotion.getMessage();
        this.paymentMethod = promotion.getPaymentMethod();
        this.minimumValue = promotion.getMinimumValue();
        this.maximumValue = promotion.getMaximumValue();
        this.numberOfTransactions = promotion.getNumberOfTransactions();
        this.giftAmount = promotion.getGiftAmount();

    }

    /**
     * Method doPromotion.
     * 
     * @param article Object
     * @param transaction Object
     * @return boolean
     */
    public boolean doPromotion(Object article, Object transaction) {

        return true;
    }

    /**
     * Method doDiscount.
     * 
     * @param trans Object
     * @return Object
     */
    public Object doDiscount(Object trans) {

        return trans;
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
     * Method getName.
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method setName.
     * 
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method getActive.
     * 
     * @return boolean
     */
    public boolean getActive() {
        return active;
    }

    /**
     * Method setActive.
     * 
     * @param active boolean
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Method getInitDate.
     * 
     * @return Date
     */
    public Date getInitDate() {
        return initDate;
    }

    /**
     * Method setInitDate.
     * 
     * @param initDate Date
     */
    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    /**
     * Method getEndDate.
     * 
     * @return Date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Method setEndDate.
     * 
     * @param endDate Date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Method getPaymentMethod.
     * 
     * @return List
     */
    public List getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Method setPaymentMethod.
     * 
     * @param paymentMethod List
     */
    public void setPaymentMethod(List paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Method getMessage.
     * 
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Method setMessage.
     * 
     * @param message String
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Method getDiscountRate.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getDiscountRate() {
        return discountRate;
    }

    /**
     * Method setDiscountRate.
     * 
     * @param discountRate CRBigDecimal
     */
    public void setDiscountRate(CRBigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * Method getDiscountAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Method setDiscountAmount.
     * 
     * @param discountAmount CRBigDecimal
     */
    public void setDiscountAmount(CRBigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * Method getMinimumValue.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getMinimumValue() {
        return minimumValue;
    }

    /**
     * Method setMinimumValue.
     * 
     * @param minimumValue CRBigDecimal
     */
    public void setMinimumValue(CRBigDecimal minimumValue) {
        this.minimumValue = minimumValue;
    }

    /**
     * Method getMaximumValue.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getMaximumValue() {
        return maximumValue;
    }

    /**
     * Method setMaximumValue.
     * 
     * @param maximumValue CRBigDecimal
     */
    public void setMaximumValue(CRBigDecimal maximumValue) {
        this.maximumValue = maximumValue;
    }

    /**
     * Method getNumberOfTransactions.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getNumberOfTransactions() {
        return numberOfTransactions;
    }

    /**
     * Method setNumberOfTransactions.
     * 
     * @param numberOfTransactions CRBigDecimal
     */
    public void setNumberOfTransactions(CRBigDecimal numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }

    /**
     * Method getDiscountType.
     * 
     * @return DiscountType
     */
    public DiscountType getDiscountType() {
        return discountType;
    }

    /**
     * Method setDiscountType.
     * 
     * @param discountType DiscountType
     */
    public void setDiscountType(DiscountType discountType) {
        Object tmp = this.discountType;
        this.discountType = discountType;
        this.fire("discountType", tmp, discountType);
    }

    /**
     * Method getIsAcumulative.
     * 
     * @return String
     */
    public String getIsAcumulative() {
        return isAcumulative;
    }

    /**
     * Method setIsAcumulative.
     * 
     * @param isAcumulative String
     */
    public void setIsAcumulative(String isAcumulative) {
        this.isAcumulative = isAcumulative;
    }

    /**
     * Method setMutiple.
     * 
     * @param mutiple boolean
     */
    public void setMutiple(boolean mutiple) {
        this.mutiple = mutiple;
    }

    /**
     * Method isMutiple.
     * 
     * @return boolean
     */
    public boolean isMutiple() {
        return mutiple;
    }

    /**
     * Method getGiftAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getGiftAmount() {
        return giftAmount;
    }

    /**
     * Method setGiftAmount.
     * 
     * @param giftAmount CRBigDecimal
     */
    public void setGiftAmount(CRBigDecimal giftAmount) {
        this.giftAmount = giftAmount;
    }

}
