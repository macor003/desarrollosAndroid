/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.Date;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 */
public class SaleUnit extends AbstractModel {

    /**
     * Field id.
     */
    private long id;

    /**
     * Field allowFraction.
     */
    private boolean allowFraction;

    /**
     * Field price.
     */
    private CRBigDecimal price = CRBigDecimal.ZERO;

    /**
     * Field defaultQuantity.
     */
    private CRBigDecimal defaultQuantity = CRBigDecimal.ONE;

    /**
     * Field isActive.
     */
    private boolean isActive;

    /**
     * Field date.
     */
    private Date date;

    /**
     * Field name.
     */
    private String name;

    /**
     * Field symbol.
     */
    private String symbol;

    /**
     * Method getSymbol.
     * 
     * @return String
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Method setSymbol.
     * 
     * @param symbol String
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
     * Method isAllowFraction.
     * 
     * @return boolean
     */
    public boolean isAllowFraction() {
        return allowFraction;
    }

    /**
     * Method setAllowFraction.
     * 
     * @param allowFraction boolean
     */
    public void setAllowFraction(boolean allowFraction) {
        this.allowFraction = allowFraction;
    }

    /**
     * Method getPrice.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getPrice() {
        return price;
    }

    /**
     * Method setPrice.
     * 
     * @param price CRBigDecimal
     */
    public void setPrice(CRBigDecimal price) {
        this.price = price;
    }

    /**
     * Method getDefaultQuantity.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getDefaultQuantity() {
        return defaultQuantity;
    }

    /**
     * Method setDefaultQuantity.
     * 
     * @param defaultQuantity CRBigDecimal
     */
    public void setDefaultQuantity(CRBigDecimal defaultQuantity) {
        this.defaultQuantity = defaultQuantity;
    }

    // public static SaleUnit fromJPA(ArticuloUnidadventa jpaSaleUnit){
    // CRBigDecimal defaultQuantity = new CRBigDecimal(1);
    // SaleUnit saleUnit=new SaleUnit();
    // saleUnit.setAllowFraction(ActiveValues.valueOf(jpaSaleUnit.getIdUnidadventa().getEsfraccion()).getValue());
    // saleUnit.setDefaultQuantity(defaultQuantity);
    // saleUnit.setId(jpaSaleUnit.getIdUnidadventa().getId());
    // saleUnit.setPrice(jpaSaleUnit.getPrecio());
    // return saleUnit;
    // }
}
