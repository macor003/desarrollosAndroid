/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.Date;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class DocumentType extends AbstractModel {

    /**
     * 85@3,86@4,89@6,90@7,91@8,92@9,93@13
     */
    // public static final int DOCUMENT_INVOICE_OPTION_KEY = 81;
    // public static final int DOCUMENT_CCF_OPTION_KEY = 82;
    // public static final int DOCUMENT_TICKET_OPTION_KEY = 83;
    // public static final int DOCUMENT_CREDIT_NOTE_OPTION_KEY = 84;
    public static final int DOCUMENT_CUSTOMER_SERVING_OPTION_KEY = 85;

    /**
     * Field DOCUMENT_DISPATCH_NOTE_OPTION_KEY. (value is 86)
     */
    public static final int DOCUMENT_DISPATCH_NOTE_OPTION_KEY = 86;

    // public static final int DOCUMENT_PARTIAL_DELIVERY_OPTION_KEY = 88;
    /**
     * Field DOCUMENT_EPA_CREDITS_DOCUMENT_OPTION_KEY. (value is 89)
     */
    public static final int DOCUMENT_EPA_CREDITS_DOCUMENT_OPTION_KEY = 89;

    /**
     * Field DOCUMENT_CREDITS_DOCUMENT_OPTION_KEY. (value is 90)
     */
    public static final int DOCUMENT_CREDITS_DOCUMENT_OPTION_KEY = 90;

    /**
     * Field DOCUMENT_ORDERSDEPOSIT_DOCUMENT_OPTION_KEY. (value is 91)
     */
    public static final int DOCUMENT_ORDERSDEPOSIT_DOCUMENT_OPTION_KEY = 91;

    /**
     * Field DOCUMENT_CHARGE_TO_ACCOUNT_OPTION_KEY. (value is 92)
     */
    public static final int DOCUMENT_CHARGE_TO_ACCOUNT_OPTION_KEY = 92;

    /**
     * Field DOCUMENT_CORPORATES_CREDIT_OPTION_KEY. (value is 93)
     */
    public static final int DOCUMENT_CORPORATES_CREDIT_OPTION_KEY = 93;
    // public static final int DOCUMENT_CANCELLATION_OPTION_KEY = 94;
    // public static final int DOCUMENT_REFUND_OPTION_KEY = 95;

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field idTipoDoc.
     */
    private long idTipoDoc;

    /**
     * Field name.
     */
    private String name;

    /**
     * Field isFiscal.
     */
    private boolean isFiscal;

    /**
     * Field isActive.
     */
    private boolean isActive;

    /**
     * Field printStation.
     */
    private String printStation;

    /**
     * Field duplicateAud.
     */
    private String duplicateAud;

    /**
     * Field economicActivity.
     */
    private boolean economicActivity;

    /**
     * Field date.
     */
    private Date date;

    /**
     * Field requiresClient.
     */
    private boolean requiresClient;

    /**
     * Field requiresNewDoc.
     */
    private boolean requiresNewDoc;

    /**
     * Field allowsReprint.
     */
    private boolean allowsReprint;

    /**
     * Field requiresSerial.
     */
    private boolean requiresSerial;

    /**
     * Field documentSerial.
     */
    private String documentSerial;

    /**
     * Field partialRefundAvailable.
     */
    private boolean partialRefundAvailable;

    /**
     * Field isSaleSupport.
     */
    private boolean isSaleSupport;

    /**
     * Field showTax.
     */
    private boolean showTax;

    /**
     * Field counterPart.
     */
    private DocumentType counterPart;

    /**
     * Field refundCounterPart.
     */
    private DocumentType refundCounterPart;

    /**
     * Constructor for DocumentType.
     */
    public DocumentType() {
    }

    /**
     * Constructor for DocumentType.
     * 
     * @param d com.becoblohm.cr.net.models.DocumentType
     */
    public DocumentType(com.becoblohm.cr.net.models.DocumentType d) {

        if (d != null) {
            this.setAllowsReprint(d.isAllowsReprint());
            this.setCounterPart(new DocumentType(d.getCounterPart()));
            this.setDate(d.getDate());
            this.setDocumentSerial(d.getDocumentSerial());
            this.setDuplicateAud(d.getDuplicateAud());
            this.setEconomicActivity(d.getEconomicActivity());
            this.setFiscal(d.isFiscal());
            this.setIdTipoDoc(d.getIdTipoDoc());
            this.setIsActive(d.getIsActive());
            this.setName(d.getName());
            this.setPartialRefundAvailable(d.isPartialRefundAvailable());
            this.setPrintStation(d.getPrintStation());
            this.setRequiresClient(d.isRequiresClient());
            this.setRequiresNewDoc(d.isRequiresNewDoc());
            this.setRequiresSerial(d.getRequiresSerial());
            this.setSaleSupport(d.isSaleSupport());
            this.setShowTax(d.isShowTax());
        }

    }

    /**
     * Constructor for DocumentType.
     * 
     * @param name String
     * @param requiresClient boolean
     * @param requiresSerial Boolean
     */
    public DocumentType(String name, boolean requiresClient, Boolean requiresSerial) {
        super();
        // this.idTipoDoc=id;
        this.name = name;
        this.requiresClient = requiresClient;
        this.requiresSerial = requiresSerial;
    }

    /**
     * 
     * @return the idTipoDoc
     */
    public long getIdTipoDoc() {
        return idTipoDoc;
    }

    /**
     * @param idTipoDoc the idTipoDoc to set
     */
    public void setIdTipoDoc(long idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    /**
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return the requiresClient
     */
    public boolean isRequiresClient() {
        return requiresClient;
    }

    /**
     * @param requiresClient the requiresClient to set
     */
    public void setRequiresClient(boolean requiresClient) {
        this.requiresClient = requiresClient;
    }

    /**
     * 
     * @return the requiresNewDoc
     */
    public boolean isRequiresNewDoc() {
        return requiresNewDoc;
    }

    /**
     * @param requiresNewDoc the requiresNewDoc to set
     */
    public void setRequiresNewDoc(boolean requiresNewDoc) {
        this.requiresNewDoc = requiresNewDoc;
    }

    /**
     * 
     * @return the allowsReprint
     */
    public boolean isAllowsReprint() {
        return allowsReprint;
    }

    /**
     * @param allowsReprint the allowsReprint to set
     */
    public void setAllowsReprint(boolean allowsReprint) {
        this.allowsReprint = allowsReprint;
    }

    /**
     * Method setFiscal.
     * 
     * @param isFiscal boolean
     */
    public void setFiscal(boolean isFiscal) {
        this.isFiscal = isFiscal;
    }

    /**
     * Method isFiscal.
     * 
     * @return boolean
     */
    public boolean isFiscal() {
        return isFiscal;
    }

    /**
     * 
     * @return the isActive
     */
    public boolean getIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * 
     * @return the printStation
     */
    public String getPrintStation() {
        return printStation;
    }

    /**
     * @param printStation the printStation to set
     */
    public void setPrintStation(String printStation) {
        this.printStation = printStation;
    }

    /**
     * 
     * @return the duplicateAud
     */
    public String getDuplicateAud() {
        return duplicateAud;
    }

    /**
     * @param duplicateAud the duplicateAud to set
     */
    public void setDuplicateAud(String duplicateAud) {
        this.duplicateAud = duplicateAud;
    }

    /**
     * 
     * @return the economicActivity
     */
    public boolean getEconomicActivity() {
        return economicActivity;
    }

    /**
     * @param economicActivity the economicActivity to set
     */
    public void setEconomicActivity(boolean economicActivity) {
        this.economicActivity = economicActivity;
    }

    /**
     * 
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 
     * @return the requiresClient
     */

    /**
     * @return the requiresSerial
     */
    public Boolean getRequiresSerial() {
        return requiresSerial;
    }

    /**
     * @param requiresSerial the requiresSerial to set
     */
    public void setRequiresSerial(Boolean requiresSerial) {
        this.requiresSerial = requiresSerial;
    }

    /**
     * 
     * @return the documentSerial
     */
    public String getDocumentSerial() {
        return documentSerial;
    }

    /**
     * @param documentSerial the documentSerial to set
     */
    public void setDocumentSerial(String documentSerial) {
        this.documentSerial = documentSerial;
    }

    /**
     * 
     * @return the partialRefundAvailable
     */
    public boolean isPartialRefundAvailable() {
        return partialRefundAvailable;
    }

    /**
     * @param partialRefundAvailable the partialRefundAvailable to set
     */
    public void setPartialRefundAvailable(boolean partialRefundAvailable) {
        this.partialRefundAvailable = partialRefundAvailable;
    }

    /**
     * Method setSaleSupport.
     * 
     * @param isSaleSupport boolean
     */
    public void setSaleSupport(boolean isSaleSupport) {
        this.isSaleSupport = isSaleSupport;
    }

    /**
     * Method isSaleSupport.
     * 
     * @return boolean
     */
    public boolean isSaleSupport() {
        return isSaleSupport;
    }

    /**
     * Method setCounterPart.
     * 
     * @param fromJPA DocumentType
     */
    public void setCounterPart(DocumentType fromJPA) {
        this.counterPart = fromJPA;

    }

    /**
     * 
     * @return the counterPart
     */
    public DocumentType getCounterPart() {
        return counterPart;
    }

    /**
     * Method setShowTax.
     * 
     * @param showTax boolean
     */
    public void setShowTax(boolean showTax) {
        this.showTax = showTax;
    }

    /**
     * Method isShowTax.
     * 
     * @return boolean
     */
    public boolean isShowTax() {
        return showTax;
    }

    /**
     * 
     * @return la contrapartida para devolucion
     */
    public DocumentType getRefundCounterPart() {
        return refundCounterPart;
    }

    /**
     * @param refundCounterPart la contrapartida para devolucion de este tipo de documento
     */
    public void setRefundCounterPart(DocumentType refundCounterPart) {
        this.refundCounterPart = refundCounterPart;
    }

    /**
     * Method toNetModel.
     * 
     * @return com.becoblohm.cr.net.models.DocumentType
     */
    public com.becoblohm.cr.net.models.DocumentType toNetModel() {
        com.becoblohm.cr.net.models.DocumentType tmp = new com.becoblohm.cr.net.models.DocumentType();

        tmp.setAllowsReprint(allowsReprint);
        if (counterPart != null) {
            tmp.setCounterPart(counterPart.toNetModel());
        }
        tmp.setDate(date);
        tmp.setDocumentSerial(documentSerial);
        tmp.setDuplicateAud(duplicateAud);
        tmp.setEconomicActivity(economicActivity);
        tmp.setFiscal(isFiscal);
        tmp.setIdTipoDoc(idTipoDoc);
        tmp.setIsActive(isActive);
        tmp.setName(name);
        tmp.setPartialRefundAvailable(partialRefundAvailable);
        tmp.setPrintStation(printStation);
        tmp.setRequiresClient(requiresClient);
        tmp.setRequiresNewDoc(requiresNewDoc);
        tmp.setRequiresSerial(requiresSerial);
        tmp.setSaleSupport(isSaleSupport);
        tmp.setShowTax(showTax);

        return tmp;
    }
}
