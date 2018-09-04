/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.epa.mvc.core.AbstractModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscountType extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field id.
     */
    private long id;

    /**
     * Field code.
     */
    private String code;

    /**
     * Field name.
     */
    private String name;

    /**
     * Field description.
     */
    private String description;

    /**
     * Field date.
     */
    private Date date;

    /**
     * Field isActive.
     */
    private boolean isActive;

    /**
     * Field isPercent.
     */
    private boolean isPercent;

    /**
     * Field isAutomaticSale.
     */
    private boolean isAutomaticSale;

    /**
     * Field isPromotion.
     */
    private boolean isPromotion;

    /**
     * Field rebateType.
     */
    private RebateType rebateType = RebateType.NONE;

    /**
     * Method clone.
     * 
     * @return DiscountType
     */
    public DiscountType clone() {
        DiscountType dt = new DiscountType();
        dt.setId(Long.valueOf(id));
        dt.setCode(String.valueOf(code));
        dt.setName(String.valueOf(name));
        dt.setDescription(String.valueOf(description));
        dt.setDate(new Date(date.getTime()));
        dt.setActive(Boolean.valueOf(isActive));
        dt.setPercent(Boolean.valueOf(isPercent));
        dt.setAutomaticSale(Boolean.valueOf(isAutomaticSale));
        dt.setPromotion(Boolean.valueOf(isPromotion));
        dt.rebateType = rebateType;
        return dt;
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
     * Method getCode.
     * 
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Method setCode.
     * 
     * @param code String
     */
    public void setCode(String code) {
        this.code = code;
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
        this.description = description;
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
     * Method isPercent.
     * 
     * @return boolean
     */
    public boolean isPercent() {
        return isPercent;
    }

    /**
     * Method setPercent.
     * 
     * @param isPercent boolean
     */
    public void setPercent(boolean isPercent) {
        this.isPercent = isPercent;
    }

    /**
     * Method isAutomaticSale.
     * 
     * @return boolean
     */
    public boolean isAutomaticSale() {
        return isAutomaticSale;
    }

    /**
     * Method setAutomaticSale.
     * 
     * @param isAutomaticSale boolean
     */
    public void setAutomaticSale(boolean isAutomaticSale) {
        this.isAutomaticSale = isAutomaticSale;
    }

    /**
     * Method isPromotion.
     * 
     * @return boolean
     */
    public boolean isPromotion() {
        // return isPromotion;
        return (this.rebateType == RebateType.PROMOTION);
    }

    /**
     * Method setPromotion.
     * 
     * @param isPromotion boolean
     */
    public void setPromotion(boolean isPromotion) {
        if (isPromotion) {
            this.rebateType = RebateType.PROMOTION;
        } else {
            this.rebateType = RebateType.REBATE;
        }
        this.isPromotion = isPromotion;
    }

    /**
     * Method isRebate.
     * 
     * @return boolean
     */
    public boolean isRebate() {
        return (this.rebateType == RebateType.REBATE);
    }

    /**
     */
    public enum RebateType {
        /**
         * Field PROMOTION.
         */
        PROMOTION("PROMO"),
        /**
         * Field REBATE.
         */
        REBATE("REBATE"),
        /**
         * Field NONE.
         */
        NONE("NONE");

        /**
         * Field lookup.
         */
        private static final Map<String, RebateType> lookup = new HashMap<String, RebateType>();

        static {
            for (RebateType s : EnumSet.allOf(RebateType.class))
                lookup.put(s.getValue(), s);
        }

        /**
         * Field value.
         */
        private String value;

        /**
         * Constructor for RebateType.
         * 
         * @param value String
         */
        RebateType(String value) {
            this.setValue(value);
        }

        /**
         * Method get.
         * 
         * @param value String
         * @return RebateType
         */
        public static RebateType get(String value) {
            return lookup.get(value);
        }

        /**
         * Method setValue.
         * 
         * @param value String
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Method getValue.
         * 
         * @return String
         */
        public String getValue() {
            return value;
        }
    }

}
