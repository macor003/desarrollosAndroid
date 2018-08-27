/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 */
public class ArticleLine extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field id.
     */
    protected long id;

    /**
     * Field category.
     */
    protected ArticleCategory category;

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
     * Field economicActivitiesList.
     */
    protected ArrayList<Long> economicActivitiesList;

    /**
     * Field promoLine.
     */
    private TreeMap<CRBigDecimal, PromotionsMethod> promoLine;

    // private List<Article> articuloList;
    /**
     * Field rebateLine.
     */
    private boolean rebateLine;

    // public void setArticuloList(List<Article> articuloList) {
    // this.articuloList = articuloList;
    // }

    /**
     * Constructor for ArticleLine.
     */
    public ArticleLine() {
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
     * Method getIdCategory.
     * 
     * @return ArticleCategory
     */
    public ArticleCategory getIdCategory() {
        return category;
    }

    /**
     * Method setIdCategory.
     * 
     * @param idCategory ArticleCategory
     */
    public void setIdCategory(ArticleCategory idCategory) {
        this.category = idCategory;
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
     * Method getEconomicActivitiesList.
     * 
     * @return ArrayList<Long>
     */
    public ArrayList<Long> getEconomicActivitiesList() {
        return economicActivitiesList;
    }

    /**
     * Method setEconomicActivitiesList.
     * 
     * @param economicActivitiesList ArrayList<Long>
     */
    public void setEconomicActivitiesList(ArrayList<Long> economicActivitiesList) {
        this.economicActivitiesList = economicActivitiesList;
    }

    /**
     * Method getCategory.
     * 
     * @return ArticleCategory
     */
    public ArticleCategory getCategory() {
        return category;
    }

    /**
     * Method setCategory.
     * 
     * @param category ArticleCategory
     */
    public void setCategory(ArticleCategory category) {
        this.category = category;
    }

    /**
     * Method getPromoLine.
     * 
     * @return TreeMap<CRBigDecimal,PromotionsMethod>
     */
    public TreeMap<CRBigDecimal, PromotionsMethod> getPromoLine() {
        return promoLine;
    }

    /**
     * Method setPromoLine.
     * 
     * @param promoLine TreeMap<CRBigDecimal,PromotionsMethod>
     */
    public void setPromoLine(TreeMap<CRBigDecimal, PromotionsMethod> promoLine) {
        this.promoLine = promoLine;
    }

    // public List<Article> getArticuloList() {
    //
    // return articuloList;
    // }

    /**
     * Method isRebateLine.
     * 
     * @return boolean
     */
    public boolean isRebateLine() {
        return rebateLine;
    }

    /**
     * Method setRebateLine.
     * 
     * @param rebateLine boolean
     */
    public void setRebateLine(boolean rebateLine) {
        this.rebateLine = rebateLine;
    }

}
