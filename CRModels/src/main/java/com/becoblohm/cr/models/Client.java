/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.ArrayList;
import java.util.Date;

import com.becoblohm.cr.interfaces.AbstractAuthorizableModel;
import com.becoblohm.cr.types.AuditTables;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Client extends AbstractAuthorizableModel {

    /**
     * 
     */
    protected static final long serialVersionUID = 1L;

    // private Long id;
    /**
     * Field clientType.
     */
    private ClientType clientType;

    /**
     * Field economicActivity2.
     */
    private long economicActivity2;

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
    private String bigClient = "N";

    /**
     * Field employeeId.
     */
    private long employeeId;

    /**
     * Field transactionClientId.
     */
    protected long transactionClientId = -1;

    /**
     * Field exonerationType.
     */
    protected ExonerationType exonerationType;

    /**
     * Field messages.
     */
    protected ArrayList<Message> messages;

    /**
     * Field nonAllowedPayments.
     */
    private ArrayList<Long> nonAllowedPayments;

    /**
     * Field employeeDiscount.
     */
    private boolean employeeDiscount = false;

    /**
     * Field employeeDiscountApply.
     */
    private boolean employeeDiscountApply = true;

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
     * 
     * @return String
     */
    public String getIsSinc() {
        return isSinc;
    }

    /**
     * Method setIsSinc.
     * 
     * @param isSinc String
     */
    public void setIsSinc(String isSinc) {
        this.isSinc = isSinc;
    }

    /**
     * Constructor for Client.
     */
    public Client() {
        super(AuditTables.CLIENT);
        this.clientType = new ClientType();
        // this.exonerationType=new ExonerationType();
    }

    /**
     * Constructor for Client.
     * 
     * @param client com.becoblohm.cr.net.models.Client
     */
    public Client(com.becoblohm.cr.net.models.Client client) {
        super(AuditTables.CLIENT);
        com.becoblohm.cr.net.models.EconomicActivity economicActivity = client.getEconomicActivity();
        if (economicActivity != null) {
            EconomicActivity economicActivityNetModel = new EconomicActivity(economicActivity.getId());
            economicActivityNetModel.setCategory(economicActivity.getCategory());
            economicActivityNetModel.setCode(economicActivity.getCode());
            economicActivityNetModel.setName(economicActivity.getName());
            this.setEconomicActivity(economicActivityNetModel);
        }

        this.setAddress(client.getAddress());
        this.setClientType(new ClientType(client.getClientType()));
        this.setDiplomatic(client.getDiplomatic());
        this.setEmail(client.getEmail());
        this.setEmployeeDiscount(client.isEmployeeDiscount());
        this.setEmployeeId(client.getEmployeeId());
        this.setExonerationType(null);
        this.setFiscalAddress(client.getFiscalAddress());
        this.setFiscalId(client.getFiscalId());
        this.setIdNumber(client.getIdNumber());
        this.setIsSinc(client.getIsSinc());
        // this.setMessages(null);
        this.setName(client.getName());
        // this.setNonAllowedPayments(null);
        this.setPhone(client.getPhone());
        this.setRegisterDate(client.getRegisterDate());
        this.setRetentionAgent(client.getRetentionAgent());
        this.setTaxPayer(client.isTaxPayer());
        this.setTransactionClientId(client.getTransactionClientId());
        this.setUpdateDate(client.getUpdateDate());
        this.setBigClient(client.getBigClient());
    }

    /**
     * Constructor for Client.
     * 
     * @param name String
     * @param idNumber String
     * @param address String
     * @param phone String
     * @param economicActivity long
     */
    public Client(String name, String idNumber, String address, String phone, long economicActivity) {
        super(AuditTables.CLIENT);
        this.name = name;
        this.idNumber = idNumber;
        this.address = address;
        this.phone = phone;
        this.economicActivity2 = economicActivity;
    }

    /*
     * public Long getId() { return id; } public void setId(Long id) { this.id = id; }
     */
    /**
     * Method getClientType.
     * 
     * @return ClientType
     */
    public ClientType getClientType() {
        return clientType;
    }

    /**
     * Method setClientType.
     * 
     * @param clientType ClientType
     */
    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    /**
     * Method getEconomicActivity.
     * 
     * @return EconomicActivity
     */
    public EconomicActivity getEconomicActivity() {
        return economicActivity;
    }

    /**
     * Method setEconomicActivity.
     * 
     * @param economicActivity EconomicActivity
     */
    public void setEconomicActivity(EconomicActivity economicActivity) {
        this.economicActivity = economicActivity;
    }

    /**
     * Method getIdNumber.
     * 
     * @return String
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * Method setIdNumber.
     * 
     * @param idNumber String
     */
    public void setIdNumber(String idNumber) {
        String tmp = this.idNumber;
        this.idNumber = idNumber;
        this.fire("idNumber", tmp, idNumber);
    }

    /*
     * Si el cliente es juridico, se usa solo para el registro de clientes
     */
    /**
     * Method isTaxPayer.
     * 
     * @return boolean
     */
    public boolean isTaxPayer() {
        return isTaxPayer;
    }

    /**
     * Method setTaxPayer.
     * 
     * @param isTaxPayer boolean
     */
    public void setTaxPayer(boolean isTaxPayer) {
        boolean tmp = this.isTaxPayer;
        this.isTaxPayer = isTaxPayer;
        this.fire("isTaxPayer", tmp, isTaxPayer);
    }

    /**
     * Method getRegisterDate.
     * 
     * @return Date
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * Method setRegisterDate.
     * 
     * @param registerDate Date
     */
    public void setRegisterDate(Date registerDate) {
        Date tmp = this.registerDate;
        this.registerDate = registerDate;
        this.fire("registerDate", tmp, registerDate);
    }

    /**
     * Method getUpdateDate.
     * 
     * @return Date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Method setUpdateDate.
     * 
     * @param updateDate Date
     */
    public void setUpdateDate(Date updateDate) {
        Date tmp = this.updateDate;
        this.updateDate = updateDate;
        this.fire("updateDate", tmp, updateDate);
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
        String tmp = this.name;
        this.name = name;
        this.fire("name", tmp, name);
    }

    /**
     * Method getFiscalId.
     * 
     * @return String
     */
    public String getFiscalId() {
        return fiscalId;
    }

    /**
     * Method setFiscalId.
     * 
     * @param fiscalId String
     */
    public void setFiscalId(String fiscalId) {
        String tmp = this.fiscalId;
        this.fiscalId = fiscalId;
        this.fire("fiscalId", tmp, fiscalId);
    }

    /**
     * Method getAddress.
     * 
     * @return String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method setAddress.
     * 
     * @param address String
     */
    public void setAddress(String address) {
        String tmp = this.address;
        this.address = address;
        this.fire("address", tmp, address);
    }

    /**
     * Method getFiscalAddress.
     * 
     * @return String
     */
    public String getFiscalAddress() {
        return fiscalAddress;
    }

    /**
     * Method setFiscalAddress.
     * 
     * @param fiscalAddress String
     */
    public void setFiscalAddress(String fiscalAddress) {
        String tmp = this.fiscalAddress;
        this.fiscalAddress = fiscalAddress;
        this.fire("fiscalAddress", tmp, fiscalAddress);
    }

    /**
     * Method getPhone.
     * 
     * @return String
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Method setPhone.
     * 
     * @param phone String
     */
    public void setPhone(String phone) {
        String tmp = this.phone;
        this.phone = phone;
        this.fire("phone", tmp, phone);
    }

    /**
     * Method getEmail.
     * 
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method setEmail.
     * 
     * @param email String
     */
    public void setEmail(String email) {
        String tmp = this.email;
        this.email = email;
        this.fire("email", tmp, email);
    }

    /**
     * Method getRetentionAgent.
     * 
     * @return String
     */
    public String getRetentionAgent() {
        return retentionAgent;
    }

    /**
     * Method setRetentionAgent.
     * 
     * @param retentionAgent String
     */
    public void setRetentionAgent(String retentionAgent) {
        String tmp = this.retentionAgent;
        this.retentionAgent = retentionAgent;
        this.fire("retentionAgent", tmp, retentionAgent);
    }

    /**
     * Method getEmployeeId.
     * 
     * @return long
     */
    public long getEmployeeId() {
        return employeeId;
    }

    /**
     * Method setEmployeeId.
     * 
     * @param employeeId long
     */
    public void setEmployeeId(long employeeId) {
        long tmp = this.employeeId;
        this.employeeId = employeeId;
        this.fire("employeeId", tmp, employeeId);
    }

    /**
     * Method getTransactionClientId.
     * 
     * @return long
     */
    public long getTransactionClientId() {
        return transactionClientId;
    }

    /**
     * Method setTransactionClientId.
     * 
     * @param transactionClientId long
     */
    public void setTransactionClientId(long transactionClientId) {
        long tmp = this.transactionClientId;
        this.transactionClientId = transactionClientId;
        this.fire("transactionClientId", tmp, transactionClientId);
    }

    /**
     * Method getExonerationType.
     * 
     * @return ExonerationType
     */
    public ExonerationType getExonerationType() {
        return exonerationType;
    }

    /**
     * Method setExonerationType.
     * 
     * @param exonerationType ExonerationType
     */
    public void setExonerationType(ExonerationType exonerationType) {
        ExonerationType tmp = this.exonerationType;
        this.exonerationType = exonerationType;
        this.fire("exonerationType", tmp, exonerationType);
    }

    /**
     * Method getMessages.
     * 
     * @return ArrayList<Message>
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }

    /**
     * Method setMessages.
     * 
     * @param messages ArrayList<Message>
     */
    public void setMessages(ArrayList<Message> messages) {
        ArrayList<Message> tmp = this.messages;
        this.messages = messages;
        this.fire("messages", tmp, messages);
    }

    /**
     * Method getNonAllowedPayments.
     * 
     * @return ArrayList<Long>
     */
    public ArrayList<Long> getNonAllowedPayments() {
        return nonAllowedPayments;
    }

    /**
     * Method setNonAllowedPayments.
     * 
     * @param nonAllowedPayments ArrayList<Long>
     */
    public void setNonAllowedPayments(ArrayList<Long> nonAllowedPayments) {
        ArrayList<Long> tmp = this.nonAllowedPayments;
        this.nonAllowedPayments = nonAllowedPayments;
        this.fire("nonAllowedPayments2", tmp, nonAllowedPayments);
    }

    /**
     * Method isEmployeeDiscount.
     * 
     * @return boolean
     */
    public boolean isEmployeeDiscount() {
        return employeeDiscount;
    }

    /**
     * Method setEmployeeDiscount.
     * 
     * @param employeeDiscount boolean
     */
    public void setEmployeeDiscount(boolean employeeDiscount) {
        boolean tmp = this.employeeDiscount;
        this.employeeDiscount = employeeDiscount;
        this.fire("employeeDiscount", tmp, employeeDiscount);
    }

    /**
     * @param diplomatic the diplomatic to set
     */
    public void setDiplomatic(boolean diplomatic) {
        this.diplomatic = diplomatic;
    }

    /**
     * 
     * @return the diplomatic
     */
    public boolean getDiplomatic() {
        return diplomatic;
    }

    /**
     * Method toNetModel.
     * 
     * @return com.becoblohm.cr.net.models.Client
     */
    public com.becoblohm.cr.net.models.Client toNetModel() {
        com.becoblohm.cr.net.models.Client client = new com.becoblohm.cr.net.models.Client();
        client.setAddress(this.getAddress());
        if (this.getClientType() != null) {
            client.setClientType(this.getClientType().toNetModel());
        }
        client.setDiplomatic(this.getDiplomatic());
        EconomicActivity economicActivity = this.getEconomicActivity();
        if (economicActivity != null) {
            client.setEconomicActivity(economicActivity.toNetModel());
        }
        client.setEmail(this.getEmail());
        client.setEmployeeDiscount(this.isEmployeeDiscount());
        client.setEmployeeId(this.getEmployeeId());
        client.setFiscalAddress(this.getFiscalAddress());
        client.setFiscalId(this.getFiscalId());
        client.setIdNumber(this.getIdNumber());
        client.setIsSinc(this.getIsSinc());
        client.setName(this.getName());
        client.setPhone(this.getPhone());
        client.setRegisterDate(this.getRegisterDate());
        client.setRetentionAgent(this.getRetentionAgent());
        client.setTaxPayer(this.isTaxPayer());
        client.setTransactionClientId(this.getTransactionClientId());
        client.setUpdateDate(this.getUpdateDate());
        client.setBigClient(this.getBigClient());
        return client;
    }

    /**
     * @param bigClient the bigClient to set
     */
    public void setBigClient(String bigClient) {
        String tmp = this.bigClient;
        this.bigClient = bigClient;
        this.fire("bigClient", tmp, bigClient);
    }

    /**
     * 
     * @return the bigClient
     */
    public String getBigClient() {
        return bigClient;
    }

    /**
     * Method isEmployeeDiscountApply.
     * 
     * @return boolean
     */
    public boolean isEmployeeDiscountApply() {
        return employeeDiscountApply;
    }

    /**
     * Method setEmployeeDiscountApply.
     * 
     * @param employeeDiscountApply boolean
     */
    public void setEmployeeDiscountApply(boolean employeeDiscountApply) {
        this.employeeDiscountApply = employeeDiscountApply;
    }

    @Override
    public String getAuditID() {
        return this.idNumber;
    }

}
