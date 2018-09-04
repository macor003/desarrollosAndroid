/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.models;

import java.util.Date;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class TransactionAudit extends AbstractModel implements Cloneable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Id del registro, es autogenerado por la base de datos
     */
    private Long id;

    /**
     * Proceso autorizado
     */
    private Process authorizedProcess;

    /**
     * Usuario que autorizo el proceso
     */
    private User authorizer;

    /**
     * Fecha y hora en que se autorizo el proceso
     */
    private Date auditDate;

    /**
     * Constructor for TransactionDocument.
     */
    public TransactionAudit() {

    }

    // /**
    // * Constructor for TransactionDocument.
    // * @param t com.becoblohm.cr.net.models.TransactionDocument
    // */
    // public TransactionAudit(com.becoblohm.cr.net.models.TransactionDocument t) {
    // this.documentNumber = t.getDocumentNumber();
    // this.documentText = t.getDocumentText();
    // this.documentType = new DocumentType(t.getDocumentType());
    // this.id = t.getId();
    // this.isPrinted = t.getIsPrinted();
    // this.transactionId = t.getTransactionId();
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Process getAuthorizedProcess() {
        return authorizedProcess;
    }

    public void setAuthorizedProcess(Process authorizedProcess) {
        this.authorizedProcess = authorizedProcess;
    }

    public User getAuthorizer() {
        return authorizer;
    }

    public void setAuthorizer(User authorizer) {
        this.authorizer = authorizer;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    /**
     * Method clone.
     * 
     * @return Object
     */
    @Override
    public Object clone() {
        TransactionAudit tdoc = new TransactionAudit();

        tdoc.setId(this.id);
        tdoc.setAuthorizedProcess(this.authorizedProcess);
        tdoc.setAuthorizer(this.authorizer);
        tdoc.setAuditDate(this.auditDate);

        return tdoc;
    }

    // /**
    // * Method toNetTransactionDocument.
    // * @return com.becoblohm.cr.net.models.TransactionDocument
    // */
    // public com.becoblohm.cr.net.models.TransactionDocument toNetTransactionDocument() {
    // com.becoblohm.cr.net.models.TransactionDocument tmp = new
    // com.becoblohm.cr.net.models.TransactionDocument();
    //
    // tmp.setDocumentNumber(documentNumber);
    // tmp.setDocumentText(documentText);
    //
    // tmp.setDocumentType(documentType.toNetModel());
    // tmp.setId(id);
    // tmp.setIsPrinted(isPrinted);
    // if (transactionId != null) {
    // tmp.setTransactionId(transactionId);
    // }
    //
    // return tmp;
    // }
}
