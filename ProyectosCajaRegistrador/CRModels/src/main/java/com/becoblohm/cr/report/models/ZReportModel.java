/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.report.models;

import java.util.TreeMap;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class ZReportModel extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field posID.
     */
    private String posID;

    /**
     * Field storeID.
     */
    private String storeID;

    /**
     * Field date.
     */
    private String date;

    /**
     * Field time.
     */
    private String time;

    /**
     * Field details.
     */
    private TreeMap<Long, DetailsZReportModel> details;

    /**
     * Method getPosID.
     * 
     * @return String
     */
    public String getPosID() {
        return posID;
    }

    /**
     * Method setPosID.
     * 
     * @param posID String
     */
    public void setPosID(String posID) {
        this.posID = posID;
    }

    /**
     * Method getStoreID.
     * 
     * @return String
     */
    public String getStoreID() {
        return storeID;
    }

    /**
     * Method setStoreID.
     * 
     * @param storeID String
     */
    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    /**
     * Method getDate.
     * 
     * @return String
     */
    public String getDate() {
        return date;
    }

    /**
     * Method setDate.
     * 
     * @param date String
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Method getTime.
     * 
     * @return String
     */
    public String getTime() {
        return time;
    }

    /**
     * Method setTime.
     * 
     * @param time String
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Method getDetails.
     * 
     * @return TreeMap<Long,DetailsZReportModel>
     */
    public TreeMap<Long, DetailsZReportModel> getDetails() {
        return details;
    }

    /**
     * Method setDetails.
     * 
     * @param details TreeMap<Long,DetailsZReportModel>
     */
    public void setDetails(TreeMap<Long, DetailsZReportModel> details) {
        this.details = details;
    }

}
