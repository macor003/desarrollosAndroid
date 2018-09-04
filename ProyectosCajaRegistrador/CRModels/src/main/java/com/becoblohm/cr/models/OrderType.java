/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class OrderType extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 8929021206548667374L;

    /**
     * Field id.
     */
    private long id;

    /**
     * Field description.
     */
    private String description;

    /**
     * Field canChangeQuantityArticles.
     */
    private boolean canChangeQuantityArticles;

    /**
     * Field printOrderNumberInBill.
     */
    private boolean printOrderNumberInBill;

    /**
     * Field canDoRebateInPos.
     */
    private boolean canDoRebateInPos;

    /**
     * Field canAddArticlesItems.
     */
    private boolean canAddArticlesItems;

    /**
     * Field findBetterCostInPos.
     */
    private boolean findBetterCostInPos;

    /**
     * Field originFDPDeposit.
     */
    private boolean originFDPDeposit;

    /**
     * Field originFDPDefault.
     */
    private boolean originFDPDefault;

    /**
     * Field originFDPPos.
     */
    private boolean originFDPPos;

    /**
     * Field Origin.
     */
    private Source Origin;

    /**
     * Constructor for OrderType.
     * 
     * @param id int
     * @param desc String
     */
    public OrderType(int id, String desc) {
        this.id = id;
        this.description = desc;
    }

    /**
     * Constructor for OrderType.
     */
    public OrderType() {
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
     * Method isCanChangeQuantityArticles.
     * 
     * @return boolean
     */
    public boolean isCanChangeQuantityArticles() {
        return canChangeQuantityArticles;
    }

    /**
     * Method isPrintOrderNumberInBill.
     * 
     * @return boolean
     */
    public boolean isPrintOrderNumberInBill() {
        return printOrderNumberInBill;
    }

    /**
     * Method isCanDoRebateInPos.
     * 
     * @return boolean
     */
    public boolean isCanDoRebateInPos() {
        return canDoRebateInPos;
    }

    /**
     * Method isCanAddArticlesItems.
     * 
     * @return boolean
     */
    public boolean isCanAddArticlesItems() {
        return canAddArticlesItems;
    }

    /**
     * Method isFindBetterCostInPos.
     * 
     * @return boolean
     */
    public boolean isFindBetterCostInPos() {
        return findBetterCostInPos;
    }

    /**
     * Method isOriginFDPDeposit.
     * 
     * @return boolean
     */
    public boolean isOriginFDPDeposit() {
        return originFDPDeposit;
    }

    /**
     * Method isOriginFDPDefault.
     * 
     * @return boolean
     */
    public boolean isOriginFDPDefault() {
        return originFDPDefault;
    }

    /**
     * Method isOriginFDPPos.
     * 
     * @return boolean
     */
    public boolean isOriginFDPPos() {
        return originFDPPos;
    }

    /**
     * Method setCanChangeQuantityArticles.
     * 
     * @param canChangeQuantityArticles boolean
     */
    public void setCanChangeQuantityArticles(boolean canChangeQuantityArticles) {
        this.canChangeQuantityArticles = canChangeQuantityArticles;
    }

    /**
     * Method setPrintOrderNumberInBill.
     * 
     * @param printOrderNumberInBill boolean
     */
    public void setPrintOrderNumberInBill(boolean printOrderNumberInBill) {
        this.printOrderNumberInBill = printOrderNumberInBill;
    }

    /**
     * Method setCanDoRebateInPos.
     * 
     * @param canDoRebateInPos boolean
     */
    public void setCanDoRebateInPos(boolean canDoRebateInPos) {
        this.canDoRebateInPos = canDoRebateInPos;
    }

    /**
     * Method setCanAddArticlesItems.
     * 
     * @param canAddArticlesItems boolean
     */
    public void setCanAddArticlesItems(boolean canAddArticlesItems) {
        this.canAddArticlesItems = canAddArticlesItems;
    }

    /**
     * Method setFindBetterCostInPos.
     * 
     * @param findBetterCostInPos boolean
     */
    public void setFindBetterCostInPos(boolean findBetterCostInPos) {
        this.findBetterCostInPos = findBetterCostInPos;
    }

    /**
     * Method setOriginFDPDeposit.
     * 
     * @param originFDPDeposit boolean
     */
    public void setOriginFDPDeposit(boolean originFDPDeposit) {
        this.originFDPDeposit = originFDPDeposit;
    }

    /**
     * Method setOriginFDPDefault.
     * 
     * @param originFDPDefault boolean
     */
    public void setOriginFDPDefault(boolean originFDPDefault) {
        this.originFDPDefault = originFDPDefault;
    }

    /**
     * Method setOriginFDPPos.
     * 
     * @param originFDPPos boolean
     */
    public void setOriginFDPPos(boolean originFDPPos) {
        this.originFDPPos = originFDPPos;
    }

    /**
     * Method equals.
     * 
     * @param tmp OrderType
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof OrderType))
            return false;
        OrderType tmp = (OrderType) o;
        if (this.id == tmp.id)
            return true;
        else
            return false;
    }

    public Source getOrigin() {
        return Origin;
    }

    public void setOrigin(Source origin) {
        Origin = origin;
    }

    /**
     */
    public enum Source {
        AS400("1"),

        SALES_COMPANY("2");

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
}
