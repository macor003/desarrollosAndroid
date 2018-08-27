/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.Date;
import java.util.TreeMap;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 */
public class ArticleCategory extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field id.
     */
    protected long id;

    /**
     * Field code.
     */
    protected String code;

    /**
     * Field name.
     */
    protected String name;

    /**
     * Field date.
     */
    protected Date date;

    /**
     * Field isActive.
     */
    protected boolean isActive;

    /**
     * Field promoCategory.
     */
    private TreeMap<CRBigDecimal, PromotionsMethod> promoCategory;

    /**
     * Constructor for ArticleCategory.
     */
    public ArticleCategory() {
        super();
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
     * Method getIsActive.
     * 
     * @return boolean
     */
    public boolean getIsActive() {
        return isActive;
    }

    /**
     * Method setIsActive.
     * 
     * @param isActive boolean
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Method getPromoCategory.
     * 
     * @return TreeMap<CRBigDecimal,PromotionsMethod>
     */
    public TreeMap<CRBigDecimal, PromotionsMethod> getPromoCategory() {
        return promoCategory;
    }

    /**
     * Method setPromoCategory.
     * 
     * @param promoCategory TreeMap<CRBigDecimal,PromotionsMethod>
     */
    public void setPromoCategory(TreeMap<CRBigDecimal, PromotionsMethod> promoCategory) {
        this.promoCategory = promoCategory;
    }

}
