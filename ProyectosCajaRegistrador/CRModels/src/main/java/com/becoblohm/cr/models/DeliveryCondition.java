/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class DeliveryCondition extends AbstractModel {

    /**
     * Field conditions.
     */
    private static HashMap<String, DeliveryCondition> conditions;

    /**
     * Field id.
     */
    int id;

    /**
     * Field name.
     */
    String name;

    /**
     * Field info.
     */
    private Map info;

    /**
     * Field contact.
     */
    private DeliverInfo contact;

    /**
     * Field type.
     */
    private String type;

    /**
     * Field documentId.
     */
    private int documentId;

    static {
        conditions = new HashMap<String, DeliveryCondition>();
        conditions.put("C", new DeliveryCondition(1, "Caja", "C"));
        conditions.put("D", new DeliveryCondition(2, "Despacho", "D"));
        DeliveryCondition tmp = new DeliveryCondition(3, "Domicilio", "E",
                DocumentType.DOCUMENT_DISPATCH_NOTE_OPTION_KEY);
        Map<String, String> info = new LinkedHashMap<String, String>();
        String id, name, phone, address, recibe;
        id = name = phone = address = recibe = "";
        info.put("Doc. Identidad", id);
        info.put("Cliente", name);
        info.put("Teléfono", phone);
        info.put("<html>Dirección<br>Max. 250 Caracteres</html>", address);
        tmp.setInfo(info);
        conditions.put("E", tmp);
        tmp = new DeliveryCondition(4, "Cliente Retira", "R", DocumentType.DOCUMENT_CUSTOMER_SERVING_OPTION_KEY);
        info = new LinkedHashMap<String, String>();
        info.put("Doc. Identidad", id);
        info.put("Cliente", name);
        info.put("Teléfono", phone);
        tmp.setInfo(info);
        conditions.put("R", tmp);
    }

    public DeliveryCondition() {
        super();
    }

    /**
     * Constructor for DeliveryCondition.
     * 
     * @param id int
     * @param name String
     * @param type String
     */
    public DeliveryCondition(int id, String name, String type) {
        super();
        this.id = id;
        this.name = name;
        this.setType(type);
        this.contact = new DeliverInfo();
    }

    /**
     * Constructor for DeliveryCondition.
     * 
     * @param id int
     * @param name String
     * @param type String
     * @param documentId int
     */
    public DeliveryCondition(int id, String name, String type, int documentId) {
        this(id, name, type);
        this.setDocumentId(documentId);
    }

    /**
     * Method createDefault.
     * 
     * @return DeliveryCondition
     */
    public static DeliveryCondition createDefault() {
        DeliveryCondition dc = new DeliveryCondition(1, "Caja", "C");
        return dc;
    }

    /**
     * 
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @param info the info to set
     */
    public void setInfo(Map<String, String> info) {
        this.info = info;
        if (info != null && this.getContact() != null) {
            this.getContact().setAddress((String) this.info.get("<html>Dirección<br>Max. 250 Caracteres</html>"));
            this.getContact().setIddoc((String) this.info.get("Doc. Identidad"));
            this.getContact().setName((String) this.info.get("Cliente"));
            this.getContact().setPhone((String) this.info.get("Teléfono"));
        }

    }

    /**
     * 
     * @return the info
     */
    public Map<String, String> getInfo() {
        return info;
    }

    /**
     * Method getDeliveryConditionsHash.
     * 
     * @return HashMap<String,DeliveryCondition>
     */
    public static HashMap<String, DeliveryCondition> getDeliveryConditionsHash() {
        return conditions;
    }

    /*
     * public static HashMap<Integer,DeliveryCondition> getDeliveryConditions(){
     * HashMap<Integer,DeliveryCondition> result = new
     * HashMap<Integer,DeliveryCondition>(); result.put(1,new
     * DeliveryCondition(1,"Caja","C")); result.put(2,new
     * DeliveryCondition(2,"Despacho","D")); DeliveryCondition tmp = new
     * DeliveryCondition(3,"Domicilio","E"); Map<String, String> info = new
     * LinkedHashMap<String, String>(); info.put("ID",""); info.put("Cliente","");
     * info.put("Telefono",""); info.put("Dirección",""); info.put("Recibe","");
     * tmp.setInfo(info); result.put(3,tmp); tmp = new DeliveryCondition(4,
     * "Cliente Retira","R"); info = new LinkedHashMap<String, String>();
     * info.put("ID",""); info.put("Cliente",""); info.put("Telefono","");
     * tmp.setInfo(info); result.put(4,tmp); return result; }
     */

    /**
     * Method setType.
     * 
     * @param type String
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method getType.
     * 
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Method setDocumentId.
     * 
     * @param documentId int
     */
    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    /**
     * Method getDocumentId.
     * 
     * @return int
     */
    public int getDocumentId() {
        return documentId;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(DeliverInfo contact) {
        this.contact = contact;
    }

    /**
     * 
     * @return the contact
     */
    public DeliverInfo getContact() {
        return contact;
    }

}
