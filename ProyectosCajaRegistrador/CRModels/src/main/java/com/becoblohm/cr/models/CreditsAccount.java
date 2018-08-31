/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 */
public class CreditsAccount implements Serializable /* extends AbstractModel */ {

    /**
     * 
     */
    private static final long serialVersionUID = 255277495738607526L;

    /**
     * Field creditsId.
     */
    private String creditsId;/**/

    /**
     * Field client.
     */
    private Client client;/* Id Cliente */

    /**
     * Field password.
     */
    private String password;

    /**
     * Field changePassword.
     */
    private boolean changePassword;

    /**
     * Field idAutorized.
     */
    private String idAutorized;

    /**
     * Field creditsBalance.
     */
    private ArrayList<CreditsBalance> creditsBalance;

    /**
     * Field creditsMovement.
     */
    private ArrayList<CreditsMovement> creditsMovement;

    /**
     * Field status.
     */
    private String status;

    /**
     * Field processNumber.
     */
    private int processNumber;

    /**
     * Constructor for CreditsAccount.
     * 
     * @param netModel com.becoblohm.cr.net.models.CreditsAccount
     */
    public CreditsAccount(com.becoblohm.cr.net.models.CreditsAccount netModel) {
        this.changePassword = netModel.isChangePassword();
        this.client = new Client(netModel.getClient());
        this.creditsId = netModel.getCreditsId();
        this.idAutorized = netModel.getIdAutorized();
        this.password = netModel.getPassword();
        this.status = netModel.getStatus();

        if (netModel.getCreditsBalance() != null) {
            this.creditsBalance = new ArrayList<CreditsBalance>();
            for (Iterator iterator = netModel.getCreditsBalance().iterator(); iterator.hasNext();) {
                com.becoblohm.cr.net.models.CreditsBalance type = (com.becoblohm.cr.net.models.CreditsBalance) iterator
                        .next();
                this.creditsBalance.add(new CreditsBalance(type));
            }
        }

        if (netModel.getCreditsMovement() != null) {
            this.creditsMovement = new ArrayList<CreditsMovement>();
            for (Iterator iterator = netModel.getCreditsMovement().iterator(); iterator.hasNext();) {
                com.becoblohm.cr.net.models.CreditsMovement type = (com.becoblohm.cr.net.models.CreditsMovement) iterator
                        .next();
                this.creditsMovement.add(new CreditsMovement(type));
            }
        }

    }

    /**
     * Constructor for CreditsAccount.
     * 
     * @param client Client
     * @param password String
     */
    public CreditsAccount(Client client, String password) {
        super();
        this.client = client;
        this.password = password;
    }

    /**
     * Constructor for CreditsAccount.
     */
    public CreditsAccount() {
        super();
        this.client = new Client();
        this.password = "";
    }

    /**
     * Method getCreditsId.
     * 
     * @return String
     */
    public String getCreditsId() {
        return creditsId;
    }

    /**
     * Method setCreditsId.
     * 
     * @param creditsId String
     */
    public void setCreditsId(String creditsId) {
        this.creditsId = creditsId;
    }

    /**
     * Method getClient.
     * 
     * @return Client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Method setClient.
     * 
     * @param client Client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Method getPassword.
     * 
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method setPassword.
     * 
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method getSerialversionuid.
     * 
     * @return long
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @param changePassword the changePassword to set
     */
    public void setChangePassword(boolean changePassword) {
        this.changePassword = changePassword;
    }

    /**
     * 
     * @return the changePassword
     */
    public boolean isChangePassword() {
        return changePassword;
    }

    /**
     * Method getIdAutorized.
     * 
     * @return String
     */
    public String getIdAutorized() {
        return idAutorized;
    }

    /**
     * Method setIdAutorized.
     * 
     * @param idAutorized String
     */
    public void setIdAutorized(String idAutorized) {
        this.idAutorized = idAutorized;
    }

    /**
     * Method setCreditsBalance.
     * 
     * @param creditsBalance ArrayList<CreditsBalance>
     */
    public void setCreditsBalance(ArrayList<CreditsBalance> creditsBalance) {
        this.creditsBalance = creditsBalance;
    }

    /**
     * Method getCreditsBalance.
     * 
     * @return ArrayList<CreditsBalance>
     */
    public ArrayList<CreditsBalance> getCreditsBalance() {
        return creditsBalance;
    }

    /**
     * Method setCreditsMovement.
     * 
     * @param creditsMovement ArrayList<CreditsMovement>
     */
    public void setCreditsMovement(ArrayList<CreditsMovement> creditsMovement) {
        this.creditsMovement = creditsMovement;
    }

    /**
     * Method getCreditsMovement.
     * 
     * @return ArrayList<CreditsMovement>
     */
    public ArrayList<CreditsMovement> getCreditsMovement() {
        return creditsMovement;
    }

    /**
     * Method setStatus.
     * 
     * @param status String
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Method getStatus.
     * 
     * @return String
     */
    public String getStatus() {
        return status;
    }

    /**
     * Method toNetModel.
     * 
     * @return com.becoblohm.cr.net.models.CreditsAccount
     */
    public com.becoblohm.cr.net.models.CreditsAccount toNetModel() {
        com.becoblohm.cr.net.models.CreditsAccount account = new com.becoblohm.cr.net.models.CreditsAccount();

        account.setChangePassword(changePassword);
        account.setClient(client.toNetModel());

        account.setCreditsId(creditsId);

        account.setIdAutorized(idAutorized);
        account.setPassword(password);
        account.setStatus(status);

        account.setCreditsBalance(new ArrayList<CreditsBalance>());
        if (creditsBalance != null) {
            for (Iterator iterator = creditsBalance.iterator(); iterator.hasNext();) {
                CreditsBalance type = (CreditsBalance) iterator.next();
                account.getCreditsBalance().add(type.toNetModel());
            }
        }

        account.setCreditsMovement(new ArrayList<CreditsMovement>());
        if (creditsMovement != null) {
            for (Iterator iterator = creditsMovement.iterator(); iterator.hasNext();) {
                CreditsMovement type = (CreditsMovement) iterator.next();
                account.getCreditsMovement().add(type.toNetModel(false));
            }
        }

        return account;
    }

    /**
     * Method getProcessNumber.
     * 
     * @return int
     */
    public int getProcessNumber() {
        return processNumber;
    }

    /**
     * Method setProcessNumber.
     * 
     * @param processNumber int
     */
    public void setProcessNumber(int processNumber) {
        this.processNumber = processNumber;
    }

}
