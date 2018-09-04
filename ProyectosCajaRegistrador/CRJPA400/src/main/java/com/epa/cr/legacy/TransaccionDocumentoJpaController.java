/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.TransactionDocument;
import com.becoblohm.cr.types.ActiveValues;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TransaccionDocumentoJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Transacciondocumento";
	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;

	/**
	 * Constructor for TransaccionDocumentoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TransaccionDocumentoJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param jpaTransaccionDocumento
	 *            crjpa400.Transacciondocumento
	 * @return TransactionDocument
	 */
	public TransactionDocument fromJPA(crjpa400.Transacciondocumento jpaTransaccionDocumento) {
		TipodocumentoJpaController tipoDocController = new TipodocumentoJpaController(emf);
		TransactionDocument transactionDocument = new TransactionDocument();
		transactionDocument.setId(jpaTransaccionDocumento.getId());
		transactionDocument.setTransactionId(jpaTransaccionDocumento.getIdTransaccion().getId());
		transactionDocument.setDocumentType(tipoDocController.fromJPA(jpaTransaccionDocumento.getIdTipodocumento()));
		transactionDocument.setIsPrinted(ActiveValues.valueOf(String.valueOf(jpaTransaccionDocumento.getEsimpreso()))
				.getValue());
		transactionDocument.setDocumentText(jpaTransaccionDocumento.getDocumento());

		return transactionDocument;
	}
}
