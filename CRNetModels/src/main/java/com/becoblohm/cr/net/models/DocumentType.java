/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.models;

import java.io.Serializable;
import java.util.Date;

/**
 */
public class DocumentType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7639136246183538722L;

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
	 * Constructor for DocumentType.
	 */
	public DocumentType() {
	}

	/**
	 * Constructor for DocumentType.
	 * @param name String
	 * @param requiresClient boolean
	 * @param requiresSerial boolean
	 */
	public DocumentType(String name, boolean requiresClient, boolean requiresSerial) {
		super();
		// this.idTipoDoc=id;
		this.name = name;
		this.requiresClient = requiresClient;
		this.requiresSerial = requiresSerial;
	}

	/**
	
	 * @return the idTipoDoc */
	public long getIdTipoDoc() {
		return idTipoDoc;
	}

	/**
	 * @param idTipoDoc
	 *            the idTipoDoc to set
	 */
	public void setIdTipoDoc(long idTipoDoc) {
		this.idTipoDoc = idTipoDoc;
	}

	/**
	
	 * @return the name */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	
	 * @return the requiresClient */
	public boolean isRequiresClient() {
		return requiresClient;
	}

	/**
	 * @param requiresClient
	 *            the requiresClient to set
	 */
	public void setRequiresClient(boolean requiresClient) {
		this.requiresClient = requiresClient;
	}

	/**
	
	 * @return the requiresNewDoc */
	public boolean isRequiresNewDoc() {
		return requiresNewDoc;
	}

	/**
	 * @param requiresNewDoc
	 *            the requiresNewDoc to set
	 */
	public void setRequiresNewDoc(boolean requiresNewDoc) {
		this.requiresNewDoc = requiresNewDoc;
	}

	/**
	
	 * @return the allowsReprint */
	public boolean isAllowsReprint() {
		return allowsReprint;
	}

	/**
	 * @param allowsReprint
	 *            the allowsReprint to set
	 */
	public void setAllowsReprint(boolean allowsReprint) {
		this.allowsReprint = allowsReprint;
	}

	/**
	 * Method setFiscal.
	 * @param isFiscal boolean
	 */
	public void setFiscal(boolean isFiscal) {
		this.isFiscal = isFiscal;
	}

	/**
	 * Method isFiscal.
	 * @return boolean
	 */
	public boolean isFiscal() {
		return isFiscal;
	}

	/**
	
	 * @return the isActive */
	public boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	
	 * @return the printStation */
	public String getPrintStation() {
		return printStation;
	}

	/**
	 * @param printStation
	 *            the printStation to set
	 */
	public void setPrintStation(String printStation) {
		this.printStation = printStation;
	}

	/**
	
	 * @return the duplicateAud */
	public String getDuplicateAud() {
		return duplicateAud;
	}

	/**
	 * @param duplicateAud
	 *            the duplicateAud to set
	 */
	public void setDuplicateAud(String duplicateAud) {
		this.duplicateAud = duplicateAud;
	}

	/**
	
	 * @return the economicActivity */
	public boolean getEconomicActivity() {
		return economicActivity;
	}

	/**
	 * @param economicActivity
	 *            the economicActivity to set
	 */
	public void setEconomicActivity(boolean economicActivity) {
		this.economicActivity = economicActivity;
	}

	/**
	
	 * @return the date */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	
	 * @return the requiresClient */

	/**
	 * @return the requiresSerial
	 */
	public boolean getRequiresSerial() {
		return requiresSerial;
	}

	/**
	 * @param requiresSerial
	 *            the requiresSerial to set
	 */
	public void setRequiresSerial(boolean requiresSerial) {
		this.requiresSerial = requiresSerial;
	}

	/**
	
	 * @return the documentSerial */
	public String getDocumentSerial() {
		return documentSerial;
	}

	/**
	 * @param documentSerial
	 *            the documentSerial to set
	 */
	public void setDocumentSerial(String documentSerial) {
		this.documentSerial = documentSerial;
	}

	/**
	
	 * @return the partialRefundAvailable */
	public boolean isPartialRefundAvailable() {
		return partialRefundAvailable;
	}

	/**
	 * @param partialRefundAvailable
	 *            the partialRefundAvailable to set
	 */
	public void setPartialRefundAvailable(boolean partialRefundAvailable) {
		this.partialRefundAvailable = partialRefundAvailable;
	}

	/**
	 * Method setSaleSupport.
	 * @param isSaleSupport boolean
	 */
	public void setSaleSupport(boolean isSaleSupport) {
		this.isSaleSupport = isSaleSupport;
	}

	/**
	 * Method isSaleSupport.
	 * @return boolean
	 */
	public boolean isSaleSupport() {
		return isSaleSupport;
	}

	/**
	 * Method setCounterPart.
	 * @param fromJPA DocumentType
	 */
	public void setCounterPart(DocumentType fromJPA) {
		this.counterPart = fromJPA;

	}

	/**
	
	 * @return the counterPart */
	public DocumentType getCounterPart() {
		return counterPart;
	}

	/**
	 * Method setShowTax.
	 * @param showTax boolean
	 */
	public void setShowTax(boolean showTax) {
		this.showTax = showTax;
	}

	/**
	 * Method isShowTax.
	 * @return boolean
	 */
	public boolean isShowTax() {
		return showTax;
	}

}
