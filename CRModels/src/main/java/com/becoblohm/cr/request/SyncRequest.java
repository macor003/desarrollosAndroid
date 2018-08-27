/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.request;

import com.becoblohm.cr.net.request.RMIServerRequest;
import com.becoblohm.cr.sync.models.IdContainer;
import com.becoblohm.cr.types.CRBigDecimal;

/**
 */
public class SyncRequest extends RMIServerRequest {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field DOWNLOAD. (value is 1)
     */
    public final static int DOWNLOAD = 1;

    /**
     * Field UPLOAD. (value is 2)
     */
    public final static int UPLOAD = 2;

    /**
     * Field UPLOAD. (value is 2)
     */
    public final static int DOWNLOAD_FILTERED = 3;

    // public static final int GETGROUPS = 3;

    /**
     * Field query.
     */
    private String query;

    /**
     * Field idColumnName.
     */
    private String idColumnName;

    /**
     * Field lastSync.
     */
    private String lastSync;

    /**
     * Field maxRecords.
     */
    private int maxRecords;

    /**
     * Field lastId.
     */
    private IdContainer lastId;

    /**
     * Field posNumber.
     */
    private String posNumber;

    /**
     * Constructor for SyncRequest.
     * 
     * @param type int
     * @param data Object
     * @param columnName String
     * @param posNumber String
     */
    public SyncRequest(int type, Object data, String columnName, String posNumber) {
        super();
        super.setType(type);
        super.setData(data);
        this.setIdColumnName(columnName);
        this.setPosNumber(posNumber);
    }

    /**
     * Constructor for SyncRequest.
     */
    public SyncRequest() {
        super();
    }

    /**
     * Method getQuery.
     * 
     * @return String
     */
    public String getQuery() {
        return query;
    }

    /**
     * Method setQuery.
     * 
     * @param query String
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Method getIdColumnName.
     * 
     * @return String
     */
    public String getIdColumnName() {
        return idColumnName;
    }

    /**
     * Method setIdColumnName.
     * 
     * @param idColumnName String
     */
    public void setIdColumnName(String idColumnName) {
        this.idColumnName = idColumnName;
    }

    /**
     * Method getLastSync.
     * 
     * @return String
     */
    public String getLastSync() {
        return lastSync;
    }

    /**
     * Method setLastSync.
     * 
     * @param lastSync CRBigDecimal
     */
    public void setLastSync(CRBigDecimal lastSync) {
        this.lastSync = bigdecimalToCalendar(lastSync);
    }

    /**
     * Method getMaxRecords.
     * 
     * @return int
     */
    public int getMaxRecords() {
        return maxRecords;
    }

    /**
     * Method setMaxRecords.
     * 
     * @param maxRecords int
     */
    public void setMaxRecords(int maxRecords) {
        this.maxRecords = maxRecords;
    }

    /**
     * Method getLastId.
     * 
     * @return IdContainer
     */
    public IdContainer getLastId() {
        return lastId;
    }

    /**
     * Method setLastId.
     * 
     * @param lastId2 IdContainer
     */
    public void setLastId(IdContainer lastId2) {
        this.lastId = lastId2;
    }

    /**
     * Method bigdecimalToCalendar.
     * 
     * @param date CRBigDecimal
     * @return String
     */
    public String bigdecimalToCalendar(CRBigDecimal date) {

        // Calendar calendar = Calendar.getInstance();
        // String dateStr = String.valueOf(date.getValue());
        // String year = dateStr.substring(0,4);
        // String month = dateStr.substring(4,6);
        // String day = dateStr.substring(6,8);
        // String hour = dateStr.substring(8,10);
        // String minute = dateStr.substring(10,12);
        // String second = dateStr.substring(12,14);
        // String milli = dateStr.substring(15,dateStr.length());
        //
        // calendar.set(Calendar.YEAR, Integer.parseInt(year));
        // calendar.set(Calendar.MONTH,Integer.parseInt(month)-1);
        // // if(Integer.parseInt(hour)>=12){
        // // calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(day)-1);
        // // }else{
        // calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(day));
        // // }
        // calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        // calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
        // calendar.set(Calendar.SECOND, Integer.parseInt(second));
        // calendar.set(Calendar.MILLISECOND, Integer.parseInt(milli));

        // SimpleDateFormat sdf = new
        // SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SS");
        // String fecha = sdf.format(calendar.getTime());
        // logger.debug(fecha);

        return String.valueOf(date.getValue());
    }

    /**
     * @param posNumber the posNumber to set
     */
    public void setPosNumber(String posNumber) {
        this.posNumber = posNumber;
    }

    /**
     * 
     * @return the posNumber
     */
    public String getPosNumber() {
        return posNumber;
    }
}
