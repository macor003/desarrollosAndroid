/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.models;

import java.math.BigInteger;

import com.epa.mvc.core.AbstractModel;

/**
 * @author Yelitza Farfan (programador11)
 * 
 * @version $Revision: 1.0 $
 */
public class TransactionDocument extends AbstractModel implements Cloneable {

    /**
     * 
     */
    private static final long serialVersionUID = -4148663810241312021L;

    /**
     * Field id.
     */
    private Long id = (long) -1;

    /**
     * Field transactionId.
     */
    private Long transactionId;

    /**
     * Field documentType.
     */
    private DocumentType documentType;

    /**
     * Field isPrinted.
     */
    private boolean isPrinted;

    /**
     * Field documentNumber.
     */
    private BigInteger documentNumber;

    /**
     * Field documentText.
     */
    private String documentText;

    /**
     * Field clientData.
     */
    private String clientData;

    /**
     * Constructor for TransactionDocument.
     */
    public TransactionDocument() {

    }

    /**
     * Constructor for TransactionDocument.
     * 
     * @param t com.becoblohm.cr.net.models.TransactionDocument
     */
    public TransactionDocument(com.becoblohm.cr.net.models.TransactionDocument t) {
        this.documentNumber = t.getDocumentNumber();
        this.documentText = t.getDocumentText();
        this.documentType = new DocumentType(t.getDocumentType());
        this.id = t.getId();
        this.isPrinted = t.getIsPrinted();
        this.transactionId = t.getTransactionId();
        this.clientData = t.getClientData();
    }

    /**
     * 
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 
     * @return the transactionId
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * 
     * @return the isPrinted
     */
    public boolean getIsPrinted() {
        return isPrinted;
    }

    /**
     * @param isPrinted the isPrinted to set
     */
    public void setIsPrinted(boolean isPrinted) {
        this.isPrinted = isPrinted;
    }

    /**
     * 
     * @return the documentNumber
     */
    public BigInteger getDocumentNumber() {
        return documentNumber;
    }

    /**
     * @param documentNumber the documentNumber to set
     */
    public void setDocumentNumber(BigInteger documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * 
     * @return the documentText
     */
    public String getDocumentText() {
        return documentText;
    }

    /**
     * @param documentText the documentText to set
     */
    public void setDocumentText(String documentText) {
        this.documentText = documentText;
    }

    /**
     * Method setDocumentType.
     * 
     * @param documentType DocumentType
     */
    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    /**
     * Method getDocumentType.
     * 
     * @return DocumentType
     */
    public DocumentType getDocumentType() {
        return documentType;
    }

    /**
     * Method clone.
     * 
     * @return Object
     */
    @Override
    public Object clone() {
        TransactionDocument tdoc = new TransactionDocument();

        tdoc.setDocumentNumber(documentNumber);
        tdoc.setDocumentText(documentText);
        tdoc.setDocumentType(documentType);
        tdoc.setId(id);
        tdoc.setIsPrinted(isPrinted);
        tdoc.setTransactionId(transactionId);
        tdoc.setClientData(clientData);
        return tdoc;
    }

    /**
     * Method toNetTransactionDocument.
     * 
     * @return com.becoblohm.cr.net.models.TransactionDocument
     */
    public com.becoblohm.cr.net.models.TransactionDocument toNetTransactionDocument() {
        com.becoblohm.cr.net.models.TransactionDocument tmp = new com.becoblohm.cr.net.models.TransactionDocument();

        tmp.setDocumentNumber(documentNumber);
        tmp.setDocumentText(documentText);

        tmp.setDocumentType(documentType.toNetModel());
        tmp.setId(id);
        tmp.setIsPrinted(isPrinted);
        tmp.setClientData(clientData);
        if (transactionId != null) {
            tmp.setTransactionId(transactionId);
        }

        return tmp;
    }

    /**
     * @return the clientData
     */
    public String getClientData() {
        return clientData;
    }

    /**
     * @param clientData the clientData to set
     */
    public void setClientData(String clientData) {
        this.clientData = clientData;
    }
}
