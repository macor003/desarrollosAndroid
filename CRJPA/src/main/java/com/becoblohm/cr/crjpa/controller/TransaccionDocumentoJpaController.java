/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.DocumentType;
import com.becoblohm.cr.models.PrePrintedFiscalReceipt;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.models.TransactionDocument;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Comprobantefiscalnoutilizado;
import crjpa.Comprobantefiscalpreimpreso;
import crjpa.Tipodocumento;
import crjpa.Transaccion;
import crjpa.Transacciondocumento;
import crjpa.TransacciondocumentoJpaController;
import crjpa.exceptions.IllegalOrphanException;
import crjpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TransaccionDocumentoJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private final EntityManagerFactory emf;

    /**
     * Field jpacontroller.
     */
    crjpa.TransacciondocumentoJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Transacciondocumento";

    /**
     * Constructor for TransaccionDocumentoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TransaccionDocumentoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        jpacontroller = new TransacciondocumentoJpaController(emf);
        this.emf = emf;
    }

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method isNextSerial.
     * 
     * @param serial2 PrePrintedFiscalReceipt
     * @param serialNumber String
     * @return long
     */
    public long isNextSerial(PrePrintedFiscalReceipt serial2, String serialNumber) {
        Long difference;
        ComprobantefiscalpreimpresoJpaController jpa = new ComprobantefiscalpreimpresoJpaController(emf);
        Comprobantefiscalpreimpreso serial = jpa.toJPA(serial2);
        // Tipodocumento tmp = serial.getIdTipodocumento();
        // TipodocumentoJpaController docJPA = new
        // TipodocumentoJpaController(emf);
        // Tipodocumento doc =
        // docJPA.toJPA(docJPA.findTipodocumento(tmp.getId()));
        // List<Transacciondocumento> docs =
        // findLastTransaccionDocumentobyDocumentTypeId(doc);

        Comprobantefiscalnoutilizado lastNonUsed = null;
        // if(serial.getComprobantefiscalnoutilizadoList().size()>0){
        // lastNonUsed=serial.getComprobantefiscalnoutilizadoList().get(serial.getComprobantefiscalnoutilizadoList().size()-1);
        // }

        // /Hacer query que se traiga el ultimo transaccion documento de ese
        // tipo de document
        int serialRead = jpa.getPrePrintedNumberPart(serialNumber);
        if (serial.getNumCompactual().intValue() + 1 == serialRead) {
            difference = (long) 1;
        } else {
            difference = Long.valueOf(serialRead) - Long.valueOf(serial.getNumCompactual().intValue());
        }

        return difference;
    }

    /**
     * Method toJPA.
     * 
     * @param doc DocumentType
     * @param transaction Transaccion
     * @return Transacciondocumento
     */
    public Transacciondocumento toJPA(DocumentType doc, Transaccion transaction) {
        Tipodocumento jpaDoc = new Tipodocumento();
        jpaDoc.setId(TipodocumentoJpaController.getDocumentTypeId(doc.getIdTipoDoc()));
        Transacciondocumento transactionDoc = new Transacciondocumento();
        transactionDoc.setIdTipodocumento(jpaDoc);
        transactionDoc.setEsimpreso("S");
        transactionDoc.setIdTransaccion(transaction);

        return transactionDoc;
    }

    /**
     * Method toJPA.
     * 
     * @param doc TransactionDocument
     * @param transaccion Transaccion
     * @return Transacciondocumento
     */
    public Transacciondocumento toJPA(TransactionDocument doc, Transaccion transaccion) {
        Transacciondocumento transactionDoc = new Transacciondocumento();

        TipodocumentoJpaController tipoDocJpaController = new TipodocumentoJpaController(emf);
        // TransaccionJpaController transJpaController = new
        // TransaccionJpaController(emf);
        TransaccionDocumentoJpaController docController = new TransaccionDocumentoJpaController(emf);
        // Tipodocumento jpaDoc=new Tipodocumento();
        transactionDoc.setDocumento(doc.getDocumentText()
                .substring(0, doc.getDocumentText().length() >= 32739 ? 32738 : doc.getDocumentText().length()));
        transactionDoc.setIdTransaccion(transaccion);
        transactionDoc.setEsimpreso("S");
        transactionDoc.setId(doc.getId());
        // transactionDoc.setTransaccionarticuloList(new
        // ArrayList<Transaccionarticulo>());
        if (doc.getDocumentType() != null && doc.getDocumentType().getIdTipoDoc() > 0) {
            transactionDoc.setIdTipodocumento(tipoDocJpaController
                    .toJPA(tipoDocJpaController.findTipodocumento(doc.getDocumentType().getIdTipoDoc())));
        }
        // if(doc.getTransactionId()!= null &&
        // transJpaController.findTransaccion(doc.getTransactionId().getId())!=null){
        // transactionDoc.setIdTransaccion(transJpaController.findTransaccion(doc.getTransactionId().getId()));
        // }
        transactionDoc.setNumeroDocumento(BigInteger.valueOf(transaccion.getNumeroFiscal()));
        transactionDoc.setEstasincronizado("N");
        transactionDoc.setCliente(doc.getClientData());
        return transactionDoc;
    }

    /**
     * Method createOrEdit.
     * 
     * @param transaccionDocumento Transacciondocumento
     * @throws IllegalOrphanException
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void createOrEdit(Transacciondocumento transaccionDocumento)
            throws IllegalOrphanException, NonexistentEntityException, Exception {
        if (transaccionDocumento.getId() < 0) {
            jpacontroller.create(transaccionDocumento);
        } else {
            jpacontroller.edit(transaccionDocumento);
        }
    }

    // public List<Transacciondocumento>
    // findTransaccionDocumentobyIdTransaccion(Transaccion idTransaccion) {
    // EntityManager em = jpacontroller.getEntityManager();
    // Query query =
    // em.createQuery("SELECT p FROM TransaccionDocumento p WHERE p.idTransaccion =
    // :idTransaccion");
    // query.setParameter("idTransaccion", idTransaccion);
    // try {
    // return query.getResultList();
    // } finally {
    // em.close();
    // }
    // }

    // public List<TransactionDocument>
    // findLastTransaccionDocumentobyDocumentTypeId(DocumentType idDocumentType)
    // {
    //
    // EntityManager em = jpacontroller.getEntityManager();
    // TipodocumentoJpaController jpaController = new
    // TipodocumentoJpaController(emf);
    // Tipodocumento doc = jpaController.toJPA(idDocumentType);
    // Query query =
    // em.createQuery("select t from Transacciondocumento t where t.idTipodocumento =
    // :idTipoDocumento order by t.id desc");
    // query.setMaxResults(1);
    // query.setParameter("idTipoDocumento", doc);
    // try {
    // List<TransactionDocument> result = new ArrayList<TransactionDocument>();
    // List<Transacciondocumento> tmp = query.getResultList();
    // for (Iterator iterator = tmp.iterator(); iterator.hasNext();) {
    // Transacciondocumento transactionDocument = (Transacciondocumento)
    // iterator
    // .next();
    // result.add(fromJPA(transactionDocument));
    // }
    //
    //
    // return result;
    // } finally {
    // em.close();
    // }
    // }

    /**
     * Method findLastTransaccionDocumentobyDocumentTypeId.
     * 
     * @param idDocumentType Tipodocumento
     * @return List<Transacciondocumento>
     */
    private List<Transacciondocumento> findLastTransaccionDocumentobyDocumentTypeId(Tipodocumento idDocumentType) {

        EntityManager em = jpacontroller.getEntityManager();
        TipodocumentoJpaController jpaController = new TipodocumentoJpaController(emf);
        Query query = em
                .createQuery("select t from Transacciondocumento t where t.idTipodocumento = :idTipoDocumento order by t.id desc");
        query.setMaxResults(1);
        query.setParameter("idTipoDocumento", idDocumentType);
        try {
            List<Transacciondocumento> tmp = query.getResultList();
            return tmp;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * Method fromJPA.
     * 
     * @param jpaTransaccionDocumento Transacciondocumento
     * @return TransactionDocument
     */
    public TransactionDocument fromJPA(Transacciondocumento jpaTransaccionDocumento) {
        TipodocumentoJpaController tipoDocController = new TipodocumentoJpaController(emf);
        TransactionDocument transactionDocument = new TransactionDocument();
        transactionDocument.setId(jpaTransaccionDocumento.getId());
        transactionDocument.setTransactionId(jpaTransaccionDocumento.getIdTransaccion().getId());
        transactionDocument
                .setDocumentType(tipoDocController.fromJPA(jpaTransaccionDocumento.getIdTipodocumento()));
        transactionDocument.setIsPrinted(ActiveValues.valueOf(jpaTransaccionDocumento.getEsimpreso()).getValue());
        transactionDocument.setDocumentText(jpaTransaccionDocumento.getDocumento());

        return transactionDocument;
    }

    /**
     * Method searchLastTransactionDocumentByClientId.
     * 
     * @param documentId long
     * @param clientId String
     * @return ArrayList<TransactionDocument>
     */
    public ArrayList<TransactionDocument> searchLastTransactionDocumentByClientId(long documentId,
                                                                                  String clientId) {
        ArrayList<TransactionDocument> doc = null;
        EntityManager em = this.emf.createEntityManager();
        String q;
        try {
            Query query;
            if (clientId != null && clientId.trim().length() > 0) {
                q = "SELECT trans FROM Transacciondocumento trans WHERE "
                        + " trans.idTransaccion.id = (SELECT MAX(t.idTransaccion.id) FROM Transacciondocumento t WHERE "
                        + " t.idTipodocumento.id=:idTipoDocumento AND "
                        + " t.idTransaccion.numeroIdentificacionCliente.numeroIdentificacionCliente=:idCliente) "
                        + " and trans.idTipodocumento.id=:idTipoDocumento2 ";
                query = em.createQuery(q);

                query.setParameter("idCliente", clientId);
            } else {
                q = "SELECT trans FROM Transacciondocumento trans WHERE "
                        + " trans.idTransaccion.id = (SELECT MAX(t.idTransaccion.id) FROM Transacciondocumento t WHERE "
                        + " t.idTipodocumento.id=:idTipoDocumento) "
                        + " and trans.idTipodocumento.id=:idTipoDocumento2 ";
                query = em.createQuery(q);
            }

            em.clear();
            query.setParameter("idTipoDocumento", documentId);
            query.setParameter("idTipoDocumento2", documentId);

            Vector<Transacciondocumento> tmp = (Vector<Transacciondocumento>) query.getResultList();
            if (tmp != null) {
                doc = new ArrayList<TransactionDocument>();
                for (int i = 0; i < tmp.size(); i++) {
                    doc.add(fromJPA(tmp.get(i)));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return doc;
    }

    /**
     * Method remove.
     * 
     * @param doc TransactionDocument
     * @param sale Transaction
     */
    public void remove(TransactionDocument doc, Transaction sale) {
        Transacciondocumento trDoc = toJPA(doc, new Transaccion(sale.getId()));
        EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(trDoc);
        em.getTransaction().commit();

    }

}
