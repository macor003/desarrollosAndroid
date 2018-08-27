/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.sql.Date;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/* PAGO */
/**
 */
public class RebateMotive extends AbstractModel {

    /**
     * Field id.
     */
    long id = 0;

    /**
     * Field discountType.
     */
    DiscountType discountType;

    /**
     * Field description.
     */
    String description = "";

    /**
     * Field date.
     */
    Date date;

    /**
     * Field isActive.
     */
    boolean isActive;

    /**
     * Field quantity.
     */
    CRBigDecimal quantity;

    /**
     * Field amount.
     */
    CRBigDecimal amount;

    /**
     * Field percent.
     */
    CRBigDecimal percent;

    /**
     * Field changeQuantity.
     */
    boolean changeQuantity;

    /**
     * Field name.
     */
    private String name;

    /**
     * Field codigo.
     */
    private long codigo;

    /**
     * Field maxPercent.
     */
    CRBigDecimal maxPercent;

    /**
     * Field totalPrice.
     */
    CRBigDecimal totalPrice;

    /**
     * Field articlePrice.
     */
    CRBigDecimal articlePrice;

    /**
     * Constructor for RebateMotive.
     */
    public RebateMotive() {
        quantity = CRBigDecimal.ZERO;
        amount = CRBigDecimal.ZERO;
        percent = CRBigDecimal.ZERO;
        totalPrice = CRBigDecimal.ZERO;
        articlePrice = CRBigDecimal.ZERO;
        this.changeQuantity = true;
    }

    /**
     * Method getArticlePrice.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getArticlePrice() {
        return articlePrice;
    }

    /**
     * Method setArticlePrice.
     * 
     * @param articlePrice CRBigDecimal
     */
    public void setArticlePrice(CRBigDecimal articlePrice) {
        CRBigDecimal oldValue = this.articlePrice;
        this.articlePrice = articlePrice;
        this.fire("articlePrice", oldValue, articlePrice);
    }

    /**
     * Method getTotalPrice.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Method setTotalPrice.
     * 
     * @param totalPrice CRBigDecimal
     */
    public void setTotalPrice(CRBigDecimal totalPrice) {
        CRBigDecimal oldValue = this.totalPrice;
        this.totalPrice = totalPrice;
        this.fire("totalPrice", oldValue, totalPrice);
    }

    /**
     * Method getPercent.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getPercent() {
        return percent;
    }

    /**
     * Method getMaxPercent.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getMaxPercent() {
        return maxPercent;
    }

    /**
     * Method setMaxPercent.
     * 
     * @param maxPercent CRBigDecimal
     */
    public void setMaxPercent(CRBigDecimal maxPercent) {
        CRBigDecimal oldValue = this.maxPercent;
        this.maxPercent = maxPercent;
        this.fire("maxPercent", oldValue, maxPercent);
    }

    /**
     * Method setPercent.
     * 
     * @param percent CRBigDecimal
     */
    public void setPercent(CRBigDecimal percent) {
        CRBigDecimal oldValue = this.percent;
        this.percent = percent;
        this.fire("percent", oldValue, percent);
    }

    /**
     * Method getStringValue.
     * 
     * @return String
     */
    public String getStringValue() {
        return description;
    }

    /**
     * Method setStringValue.
     * 
     * @param newValue String
     */
    public void setStringValue(String newValue) {
        String oldValue = description;
        this.description = newValue;
        this.fire("stringValue", oldValue, newValue);
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
     * Method toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * Method setName.
     * 
     * @param newName String
     */
    public void setName(String newName) {
        String oldName = name;
        this.name = newName;
        this.fire("name", oldName, newName);
    }

    /**
     * Method getCodigo.
     * 
     * @return long
     */
    public long getCodigo() {
        return codigo;
    }

    /**
     * Method setCodigo.
     * 
     * @param newValue long
     */
    public void setCodigo(long newValue) {
        long oldValue = codigo;
        this.codigo = newValue;
        this.fire("codigo", oldValue, newValue);
    }

    /*
     * public RebateMotive(long id, String description, DiscountType discountType) {
     * this.id=id; this.description=description; this.discountType=discountType; }
     */

    /**
     * Method getQuantity.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getQuantity() {
        return quantity;
    }

    /**
     * Method getDescription.
     * 
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method setDescription.
     * 
     * @param description String
     */
    public void setDescription(String description) {
        String tmp = this.description;
        this.description = description;
        this.fire("description", tmp, description);
    }

    /**
     * Method setQuantity.
     * 
     * @param quantity CRBigDecimal
     */
    public void setQuantity(CRBigDecimal quantity) {
        CRBigDecimal tmp = this.quantity;
        this.quantity = quantity;
        this.fire("quantity", tmp, quantity);
    }

    /**
     * Method getAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getAmount() {
        return amount;
    }

    /**
     * Method setAmount.
     * 
     * @param amount CRBigDecimal
     */
    public void setAmount(CRBigDecimal amount) {
        CRBigDecimal tmp = this.amount;
        this.amount = amount;
        this.fire("amount", tmp, amount);
    }

    /**
     * Constructor for RebateMotive.
     * 
     * @param descripcion String
     */
    public RebateMotive(String descripcion) {
        this.description = descripcion;
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
        this.date = date;
    }

    /**
     * Method isActive.
     * 
     * @return boolean
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Method setActive.
     * 
     * @param isActive boolean
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Method setId.
     * 
     * @param id long
     */
    public void setId(long id) {
        long tmp = this.id;
        this.id = id;
        this.fire("id", tmp, id);
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
        this.discountType = discountType;
    }

    /**
     * 
     * @return the changeQuantity
     */
    public boolean isChangeQuantity() {
        return changeQuantity;
    }

    /**
     * @param changeQuantity the changeQuantity to set
     */
    public void setChangeQuantity(boolean changeQuantity) {
        this.changeQuantity = changeQuantity;
    }
}
