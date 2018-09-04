/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.DocumentType;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Tipodocumento;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TipodocumentoJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Tipodocumento";
	/**
	 * Field jpaController.
	 */
	private crjpa400.TipodocumentoJpaController jpaController;
	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;
	/**
	 * Field props.
	 */
	private static Properties props;

	// Opcion de Tipos de Documentos Internos Caja
	/**
	 * Field IDSTIPOSDEDOCUMENTOS. (value is ""134"")
	 */
	private static final String IDSTIPOSDEDOCUMENTOS = "134";

	// /////////////////////////////////////////////////////

	/**
	 * Constructor for TipodocumentoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TipodocumentoJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		jpaController = new crjpa400.TipodocumentoJpaController(emf);
		this.emf = emf;
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param jpaDocType
	 *            Tipodocumento
	 * @return DocumentType
	 */
	public DocumentType fromJPA(Tipodocumento jpaDocType) {

		DocumentType docType = new DocumentType();
		// docType.setIdTipoDoc(getDocumentTypeIdKey(jpaDocType.getId()));
		docType.setIdTipoDoc(jpaDocType.getId());
		docType.setName(jpaDocType.getNombre());
		docType.setFiscal(ActiveValues.valueOf(String.valueOf(jpaDocType.getEsfiscal())).getValue());
		docType.setIsActive(ActiveValues.valueOf(String.valueOf(jpaDocType.getEstaactivo())).getValue());
		docType.setPrintStation(String.valueOf(jpaDocType.getEstacionImpresion()));
		docType.setDuplicateAud(String.valueOf(jpaDocType.getDuplicadoAud()));
		docType.setEconomicActivity(ActiveValues.valueOf(String.valueOf(jpaDocType.getActividadEconomica())).getValue());
		docType.setDate(jpaDocType.getFecha());
		docType.setRequiresClient(ActiveValues.valueOf(String.valueOf(jpaDocType.getRequiereCliente())).getValue());
		docType.setRequiresNewDoc(ActiveValues.valueOf(String.valueOf(jpaDocType.getRequiereNuevoDoc())).getValue());
		docType.setAllowsReprint(ActiveValues.valueOf(String.valueOf(jpaDocType.getPermiteReimpresion())).getValue());
		docType.setPartialRefundAvailable(ActiveValues.valueOf(
				new Character(jpaDocType.getEsdevolucionparcial()).toString()).getValue()); // TODO
																							// Falta
																							// tambien
																							// la
																							// contrapartida
																							// del
																							// documento.
		docType.setRequiresSerial(ActiveValues.valueOf(new Character(jpaDocType.getValidaSerial()).toString())
				.getValue());
		docType.setSaleSupport(ActiveValues.valueOf(String.valueOf(jpaDocType.getEssoporteventa())).getValue());
		docType.setShowTax(ActiveValues.valueOf(String.valueOf(jpaDocType.getMuestraImpuesto())).getValue());

		Tipodocumento idContrapartida = jpaDocType.getIdContrapartida();
		if (idContrapartida != null && idContrapartida.getId() != jpaDocType.getId()) {
			DocumentType tmp = new DocumentType();
			tmp.setIdTipoDoc(idContrapartida.getId());
			tmp.setName(idContrapartida.getNombre());
			tmp.setFiscal(ActiveValues.get(String.valueOf(idContrapartida.getEsfiscal())).getValue());
			tmp.setPrintStation(String.valueOf(idContrapartida.getEstacionImpresion()));
			tmp.setAllowsReprint(ActiveValues.get(String.valueOf(idContrapartida.getPermiteReimpresion())).getValue());
			tmp.setEconomicActivity(ActiveValues.get(String.valueOf(idContrapartida.getActividadEconomica()))
					.getValue());
			docType.setCounterPart(tmp);
		}
		if (jpaDocType.getIdContrapartidadevolucion() != null
				&& jpaDocType.getIdContrapartidadevolucion().getId() != jpaDocType.getId()) {
			DocumentType tmp = new DocumentType();
			tmp.setIdTipoDoc(jpaDocType.getIdContrapartidadevolucion().getId());
			tmp.setName(jpaDocType.getIdContrapartidadevolucion().getNombre());
			tmp.setFiscal(ActiveValues.get(String.valueOf(jpaDocType.getIdContrapartidadevolucion().getEsfiscal()))
					.getValue());
			tmp.setPrintStation(String.valueOf(jpaDocType.getIdContrapartidadevolucion().getEstacionImpresion()));
			tmp.setAllowsReprint(ActiveValues.get(
					String.valueOf(jpaDocType.getIdContrapartidadevolucion().getPermiteReimpresion())).getValue());
			tmp.setEconomicActivity(ActiveValues.get(
					String.valueOf(jpaDocType.getIdContrapartidadevolucion().getActividadEconomica())).getValue());
			docType.setRefundCounterPart(tmp);
		}

		return docType;
	}

	/**
	 * Method toJPA.
	 * 
	 * @param docType
	 *            DocumentType
	 * @return Tipodocumento
	 */
	public Tipodocumento toJPA(DocumentType docType) {

		Tipodocumento jpaDocType = new Tipodocumento();
		jpaDocType.setId(docType.getIdTipoDoc());
		return jpaDocType;
	}

	/**
	 * Method findById.
	 * 
	 * @param id
	 *            long
	 * @return DocumentType
	 */
	public DocumentType findById(long id) {
		Tipodocumento result = jpaController.findTipodocumento(id);
		return fromJPA(result);
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
	//
	// private HashMap<String, String> loadDocumentTypeIds() {
	//
	// OpcionJpaController opciones = new OpcionJpaController(emf);
	// props = opciones.loadOptions();
	// return
	// buildPropertiesHashDecision(props.getProperty(IDSTIPOSDEDOCUMENTOS));
	// }
	//
	// public static HashMap buildPropertiesHashDecision(String properties) {
	// StringTokenizer tk = new StringTokenizer(properties, ",");
	// HashMap result = new HashMap();
	//
	// while (tk.hasMoreTokens()) {
	// String tmp = tk.nextToken();
	// StringTokenizer keys = new StringTokenizer(tmp, "@");
	// String key = null;
	// String value = null;
	// if (keys.hasMoreTokens()) {
	// key = keys.nextToken();
	// }
	// if (keys.hasMoreTokens()) {
	// value = keys.nextToken();
	// }
	// if (key != null && value != null) {
	// result.put(key.trim(), value.trim());
	// }
	//
	// }
	//
	// return result;
	// }

}
