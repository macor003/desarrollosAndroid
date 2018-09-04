/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.models;

import java.io.Serializable;
import java.util.Date;

/**
 */
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6438809252376710094L;

	// private Long id;
	/**
	 * Field clientType.
	 */
	private ClientType clientType;
	/**
	 * Field economicActivity.
	 */
	private EconomicActivity economicActivity;
	/**
	 * Field idNumber.
	 */
	private String idNumber;
	/**
	 * Field isTaxPayer.
	 */
	private boolean isTaxPayer;
	/**
	 * Field registerDate.
	 */
	private Date registerDate;
	/**
	 * Field updateDate.
	 */
	private Date updateDate;
	/**
	 * Field name.
	 */
	private String name;
	/**
	 * Field fiscalId.
	 */
	private String fiscalId;
	/**
	 * Field address.
	 */
	private String address;
	/**
	 * Field fiscalAddress.
	 */
	private String fiscalAddress;
	/**
	 * Field phone.
	 */
	private String phone;
	/**
	 * Field email.
	 */
	private String email;
	/**
	 * Field bigClient.
	 */
	private String bigClient;

	/**
	 * Field employeeId.
	 */
	private long employeeId;
	/**
	 * Field transactionClientId.
	 */
	protected long transactionClientId = -1;

	/**
	 * Field employeeDiscount.
	 */
	private boolean employeeDiscount = false;
	// TODO Crear campo en la tabla tipocliente y agregar en fromJPA de tipo
	// cliente
	/**
	 * Field diplomatic.
	 */
	private boolean diplomatic = false;

	/**
	 * Field retentionAgent.
	 */
	private String retentionAgent = "N";
	/**
	 * Field isSinc.
	 */
	private String isSinc = "N";

	/**
	 * Method getIsSinc.
	 * @return String
	 */
	public String getIsSinc() {
		return isSinc;
	}

	/**
	 * Method setIsSinc.
	 * @param isSinc String
	 */
	public void setIsSinc(String isSinc) {
		this.isSinc = isSinc;
	}

	/**
	 * Constructor for Client.
	 */
	public Client() {
		this.clientType = new ClientType();
		// this.exonerationType=new ExonerationType();
	}

	/**
	 * Constructor for Client.
	 * @param name String
	 * @param idNumber String
	 * @param address String
	 * @param phone String
	 * @param economicActivity EconomicActivity
	 */
	public Client(String name, String idNumber, String address, String phone, EconomicActivity economicActivity) {
		super();
		this.name = name;
		this.idNumber = idNumber;
		this.address = address;
		this.phone = phone;
		this.economicActivity = economicActivity;
	}

	/*
	 * public Long getId() { return id; } public void setId(Long id) { this.id =
	 * id; }
	 */
	/**
	 * Method getClientType.
	 * @return ClientType
	 */
	public ClientType getClientType() {
		return clientType;
	}

	/**
	 * Method setClientType.
	 * @param clientType ClientType
	 */
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	/**
	 * Method getEconomicActivity.
	 * @return EconomicActivity
	 */
	public EconomicActivity getEconomicActivity() {
		return economicActivity;
	}

	/**
	 * Method setEconomicActivity.
	 * @param economicActivity EconomicActivity
	 */
	public void setEconomicActivity(EconomicActivity economicActivity) {
		this.economicActivity = economicActivity;
	}

	/**
	 * Method getIdNumber.
	 * @return String
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * Method setIdNumber.
	 * @param idNumber String
	 */
	public void setIdNumber(String idNumber) {

		this.idNumber = idNumber;
	}

	/*
	 * Si el cliente es juridico, se usa solo para el registro de clientes
	 */
	/**
	 * Method isTaxPayer.
	 * @return boolean
	 */
	public boolean isTaxPayer() {
		return isTaxPayer;
	}

	/**
	 * Method setTaxPayer.
	 * @param isTaxPayer boolean
	 */
	public void setTaxPayer(boolean isTaxPayer) {
		boolean tmp = this.isTaxPayer;
		this.isTaxPayer = isTaxPayer;

	}

	/**
	 * Method getRegisterDate.
	 * @return Date
	 */
	public Date getRegisterDate() {
		return registerDate;
	}

	/**
	 * Method setRegisterDate.
	 * @param registerDate Date
	 */
	public void setRegisterDate(Date registerDate) {
		Date tmp = this.registerDate;
		this.registerDate = registerDate;

	}

	/**
	 * Method getUpdateDate.
	 * @return Date
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * Method setUpdateDate.
	 * @param updateDate Date
	 */
	public void setUpdateDate(Date updateDate) {
		Date tmp = this.updateDate;
		this.updateDate = updateDate;

	}

	/**
	 * Method getName.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method setName.
	 * @param name String
	 */
	public void setName(String name) {
		String tmp = this.name;
		this.name = name;

	}

	/**
	 * Method getFiscalId.
	 * @return String
	 */
	public String getFiscalId() {
		return fiscalId;
	}

	/**
	 * Method setFiscalId.
	 * @param fiscalId String
	 */
	public void setFiscalId(String fiscalId) {
		String tmp = this.fiscalId;
		this.fiscalId = fiscalId;

	}

	/**
	 * Method getAddress.
	 * @return String
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Method setAddress.
	 * @param address String
	 */
	public void setAddress(String address) {
		String tmp = this.address;
		this.address = address;

	}

	/**
	 * Method getFiscalAddress.
	 * @return String
	 */
	public String getFiscalAddress() {
		return fiscalAddress;
	}

	/**
	 * Method setFiscalAddress.
	 * @param fiscalAddress String
	 */
	public void setFiscalAddress(String fiscalAddress) {
		String tmp = this.fiscalAddress;
		this.fiscalAddress = fiscalAddress;

	}

	/**
	 * Method getPhone.
	 * @return String
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Method setPhone.
	 * @param phone String
	 */
	public void setPhone(String phone) {
		String tmp = this.phone;
		this.phone = phone;

	}

	/**
	 * Method getEmail.
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method setEmail.
	 * @param email String
	 */
	public void setEmail(String email) {
		String tmp = this.email;
		this.email = email;

	}

	/**
	 * Method getRetentionAgent.
	 * @return String
	 */
	public String getRetentionAgent() {
		return retentionAgent;
	}

	/**
	 * Method setRetentionAgent.
	 * @param retentionAgent String
	 */
	public void setRetentionAgent(String retentionAgent) {
		String tmp = this.retentionAgent;
		this.retentionAgent = retentionAgent;

	}

	/**
	 * Method getEmployeeId.
	 * @return long
	 */
	public long getEmployeeId() {
		return employeeId;
	}

	/**
	 * Method setEmployeeId.
	 * @param employeeId long
	 */
	public void setEmployeeId(long employeeId) {
		long tmp = this.employeeId;
		this.employeeId = employeeId;

	}

	/**
	 * Method getTransactionClientId.
	 * @return long
	 */
	public long getTransactionClientId() {
		return transactionClientId;
	}

	/**
	 * Method setTransactionClientId.
	 * @param transactionClientId long
	 */
	public void setTransactionClientId(long transactionClientId) {
		long tmp = this.transactionClientId;
		this.transactionClientId = transactionClientId;

	}

	/**
	 * Method isEmployeeDiscount.
	 * @return boolean
	 */
	public boolean isEmployeeDiscount() {
		return employeeDiscount;
	}

	/**
	 * Method setEmployeeDiscount.
	 * @param employeeDiscount boolean
	 */
	public void setEmployeeDiscount(boolean employeeDiscount) {
		boolean tmp = this.employeeDiscount;
		this.employeeDiscount = employeeDiscount;

	}

	/**
	 * @param diplomatic
	 *            the diplomatic to set
	 */
	public void setDiplomatic(boolean diplomatic) {
		this.diplomatic = diplomatic;
	}

	/**
	
	 * @return the diplomatic */
	public boolean getDiplomatic() {
		return diplomatic;
	}

	/**
	 * @param bigClient
	 *            the bigClient to set
	 */
	public void setBigClient(String bigClient) {
		this.bigClient = bigClient;
	}

	/**
	
	 * @return the bigClient */
	public String getBigClient() {
		return bigClient;
	}

}
