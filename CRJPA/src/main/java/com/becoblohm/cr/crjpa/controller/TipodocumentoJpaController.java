/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.DocumentType;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Tipodocumento;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TipodocumentoJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.TipodocumentoJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Tipodocumento";

    /**
     * Field props.
     */
    private static Properties props;

    /**
     * Field IDSTIPOSDEDOCUMENTOS. (value is ""134"")
     */
    private static final String IDSTIPOSDEDOCUMENTOS = "134";

    /**
     * Field emf.
     */
    private static EntityManagerFactory emf;

    /**
     * Constructor for TipodocumentoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TipodocumentoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        jpacontroller = new crjpa.TipodocumentoJpaController(emf);
    }

    /*
     * Sino esta definido en opcion regresa el mismo valor de la tabla
     */
    /**
     * Method getDocumentTypeId.
     * 
     * @param documentIdKey long
     * @return long
     */
    public static long getDocumentTypeId(long documentIdKey) {
        // HashMap<String, String> documentsId = loadDocumentTypeIds();
        // long documentId = documentIdKey;
        //
        // if (documentsId != null) {
        //
        // String value = documentsId.get(String.valueOf(documentIdKey));
        // if(value!=null){
        // documentId = Long.parseLong(value);
        // }else{
        // documentId = documentIdKey;
        // }
        // }
        // return documentId;
        return documentIdKey;
    }

    /**
     * Method getDocumentDataBaseId.
     * 
     * @param documentIdKey long
     * @return long
     */
    public static long getDocumentDataBaseId(long documentIdKey) {
        HashMap<String, String> documentsId = loadDocumentTypeIds();
        long documentId = documentIdKey;

        if (documentsId != null) {

            String value = documentsId.get(String.valueOf(documentIdKey));
            if (value != null) {
                documentId = Long.parseLong(value);
            } else {
                documentId = documentIdKey;
            }
        }
        return documentId;
    }

    // public long getDocumentTypeIdKey(long documentId) {
    // HashMap<String, String> documentsId = loadDocumentTypeIds();
    // long documentIdKey = documentId;
    // for (Iterator<Entry<String, String>> iterator =
    // documentsId.entrySet().iterator(); iterator.hasNext();) {
    // Entry<String, String> next = iterator.next();
    // if (next.getValue().compareTo(String.valueOf(documentId)) == 0) {
    // documentIdKey = Long.parseLong(next.getKey());
    // break;
    // }
    //
    // }
    // return documentIdKey;
    // }

    /**
     * Method loadDocumentTypeIds.
     * 
     * @return HashMap<String,String>
     */
    public static HashMap<String, String> loadDocumentTypeIds() {
        props = OpcionJpaController.getOpciones();
        return buildPropertiesHashDecision(props.getProperty(IDSTIPOSDEDOCUMENTOS));
    }

    /**
     * Method buildPropertiesHashDecision.
     * 
     * @param properties String
     * @return HashMap
     */
    public static HashMap buildPropertiesHashDecision(String properties) {
        StringTokenizer tk = new StringTokenizer(properties, ",");
        HashMap result = new HashMap();

        while (tk.hasMoreTokens()) {
            String tmp = tk.nextToken();
            StringTokenizer keys = new StringTokenizer(tmp, "@");
            String key = null;
            String value = null;
            if (keys.hasMoreTokens()) {
                key = keys.nextToken();
            }
            if (keys.hasMoreTokens()) {
                value = keys.nextToken();
            }
            if (key != null && value != null) {
                result.put(key.trim(), value.trim());
            }

        }

        return result;
    }

    /**
     * Method fromJPA.
     * 
     * @param jpaDocType Tipodocumento
     * @return DocumentType
     */
    public DocumentType fromJPA(Tipodocumento jpaDocType) {

        DocumentType docType = new DocumentType();
        docType.setIdTipoDoc(jpaDocType.getId());
        docType.setName(jpaDocType.getNombre());
        docType.setFiscal(ActiveValues.valueOf(jpaDocType.getEsfiscal()).getValue());
        docType.setIsActive(ActiveValues.valueOf(jpaDocType.getEstaactivo()).getValue());
        docType.setPrintStation(jpaDocType.getEstacionImpresion());
        docType.setDuplicateAud(jpaDocType.getDuplicadoAud());
        docType.setEconomicActivity(ActiveValues.valueOf(jpaDocType.getActividadEconomica()).getValue());
        docType.setDate(jpaDocType.getFecha());
        docType.setRequiresClient(ActiveValues.valueOf(jpaDocType.getRequiereCliente()).getValue());
        docType.setRequiresNewDoc(ActiveValues.valueOf(jpaDocType.getRequiereNuevoDoc()).getValue());
        docType.setAllowsReprint(ActiveValues.valueOf(jpaDocType.getPermiteReimpresion()).getValue());
        docType.setPartialRefundAvailable(true); // TODO agregar el campo en la
                                                 // base de datos. Falta
                                                 // tambien la contrapartida
                                                 // del documento.
        docType.setRequiresSerial(ActiveValues.valueOf(jpaDocType.getValidaSerial()).getValue());
        docType.setSaleSupport(ActiveValues.valueOf(jpaDocType.getEssoporteventa()).getValue());
        docType.setShowTax(ActiveValues.valueOf(jpaDocType.getMuestraImpuesto()).getValue());
        if (jpaDocType.getIdContrapartida() != null
                && jpaDocType.getIdContrapartida().getId() != jpaDocType.getId()) {

            DocumentType tmp = new DocumentType();
            tmp.setIdTipoDoc(jpaDocType.getIdContrapartida().getId());
            tmp.setName(jpaDocType.getIdContrapartida().getNombre());
            tmp.setPrintStation(jpaDocType.getIdContrapartida().getEstacionImpresion());
            docType.setCounterPart(tmp);

        }
        // TODO Al modificar esto en base de datos cambiara por
        // idContraparttidaDevolucion
        if (jpaDocType.getIdContrapartidadevolucion() != null
                && jpaDocType.getIdContrapartidadevolucion().getId() != jpaDocType.getId()) {
            DocumentType tmp = new DocumentType();
            tmp.setIdTipoDoc(jpaDocType.getIdContrapartidadevolucion().getId());
            tmp.setName(jpaDocType.getIdContrapartida().getNombre());
            tmp.setPrintStation(jpaDocType.getIdContrapartida().getEstacionImpresion());
            docType.setRefundCounterPart(tmp);

        }
        return docType;

    }

    /**
     * Method toJPA.
     * 
     * @param docType DocumentType
     * @return Tipodocumento
     */
    public Tipodocumento toJPA(DocumentType docType) {

        Tipodocumento jpaDocType = new Tipodocumento();
        jpaDocType.setId(getDocumentTypeId(docType.getIdTipoDoc()));
        return jpaDocType;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method findTipodocumentoSoporteVenta.
     * 
     * @return List<DocumentType>
     */
    public List<DocumentType> findTipodocumentoSoporteVenta() {
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em.createNamedQuery("Tipodocumento.findByEssoporteventa");
        query.setParameter("essoporteventa", "S");
        try {
            List tmp = query.getResultList();
            List<DocumentType> result = new ArrayList<DocumentType>();
            for (Iterator iterator = tmp.iterator(); iterator.hasNext();) {
                Tipodocumento object = (Tipodocumento) iterator.next();
                result.add(fromJPA(object));

            }

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<DocumentType>();
        } finally {
            em.close();
        }
    }

    /**
     * Method findTipodocumento.
     * 
     * @param idTipoDoc long
     * @return DocumentType
     */
    public DocumentType findTipodocumento(long idTipoDoc) {

        return fromJPA(jpacontroller.findTipodocumento(getDocumentTypeId(idTipoDoc)));
    }

    /**
     * Method getDefaultDocumentType.
     * 
     * @param APP_DEFAULT_DOCUMENTYPE long
     * @return DocumentType
     */
    public DocumentType getDefaultDocumentType(long APP_DEFAULT_DOCUMENTYPE) {
        Tipodocumento jpaDocumentType = jpacontroller
                .findTipodocumento(getDocumentTypeId(APP_DEFAULT_DOCUMENTYPE));
        DocumentType documentType = fromJPA(jpaDocumentType);
        return documentType;
    }

    /**
     * Method findTipodocumentoEntities.
     * 
     * @return List<DocumentType>
     */
    public List<DocumentType> findTipodocumentoEntities() {
        List<DocumentType> result = new ArrayList<DocumentType>();
        List<Tipodocumento> tmp = jpacontroller.findTipodocumentoEntities();
        for (Iterator iterator = tmp.iterator(); iterator.hasNext();) {
            Tipodocumento tipodocumento = (Tipodocumento) iterator.next();
            result.add(fromJPA(tipodocumento));
        }

        return result;
    }

    /**
     * Method findTipodocumentoEntitiesJPA.
     * 
     * @return List<Tipodocumento>
     */
    public List<Tipodocumento> findTipodocumentoEntitiesJPA() {
        List<Tipodocumento> result = jpacontroller.findTipodocumentoEntities();
        return result;
    }

}
