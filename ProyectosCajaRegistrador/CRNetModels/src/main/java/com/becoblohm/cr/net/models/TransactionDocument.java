/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.net.models;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author Yelitza Farfan (programador11)
 * 
 * @version $Revision: 1.0 $
 */
public class TransactionDocument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4148663810241312021L;
	/**
	 * Field id.
	 */
	private long id = (long) -1;
	/**
	 * Field transactionId.
	 */
	private long transactionId;
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
	
	 * @return the id */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	
	 * @return the transactionId */
	public long getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId
	 *            the transactionId to set
	 */
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	/**
	
	 * @return the isPrinted */
	public boolean getIsPrinted() {
		return isPrinted;
	}

	/**
	 * @param isPrinted
	 *            the isPrinted to set
	 */
	public void setIsPrinted(boolean isPrinted) {
		this.isPrinted = isPrinted;
	}

	/**
	
	 * @return the documentNumber */
	public BigInteger getDocumentNumber() {
		return documentNumber;
	}

	/**
	 * @param documentNumber
	 *            the documentNumber to set
	 */
	public void setDocumentNumber(BigInteger documentNumber) {
		this.documentNumber = documentNumber;
	}

	/**
	
	 * @return the documentText */
	public String getDocumentText() {
		return documentText;
	}

	/**
	 * @param documentText
	 *            the documentText to set
	 */
	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}

	/**
	 * Method setDocumentType.
	 * @param documentType DocumentType
	 */
	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	/**
	 * Method getDocumentType.
	 * @return DocumentType
	 */
	public DocumentType getDocumentType() {
		return documentType;
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
