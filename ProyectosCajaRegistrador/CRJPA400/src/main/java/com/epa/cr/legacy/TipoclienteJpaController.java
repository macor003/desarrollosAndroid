/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.ClientType;
import com.becoblohm.cr.models.DocumentType;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Tipocliente;
import crjpa400.Tipoclientetipodocumento;
import crjpa400.Tipoidentificacioncliente;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TipoclienteJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Tipocliente";
	/**
	 * Field emf.
	 */
	private final EntityManagerFactory emf;
	/**
	 * Field jpacontroller.
	 */
	crjpa400.TipoclienteJpaController jpacontroller;

	/**
	 * Constructor for TipoclienteJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TipoclienteJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
		jpacontroller = new crjpa400.TipoclienteJpaController(emf);

	}

	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method toJPA.
	 * 
	 * @param idTipocliente
	 *            Tipocliente
	 * @return ClientType
	 */
	public ClientType toJPA(Tipocliente idTipocliente) {
		TipodocumentoJpaController docController = new TipodocumentoJpaController(this.emf);
		ClientType type = new ClientType();
		type.setId(idTipocliente.getId().intValue());
		type.setMask(idTipocliente.getMascara());
		type.setName(idTipocliente.getNombre());
		type.setSymbol(String.valueOf(idTipocliente.getSimbolo()));
		type.setTaxPayer(ActiveValues.get(String.valueOf(idTipocliente.getEscontribuyente())).getValue());
		type.setActive(ActiveValues.get(String.valueOf(idTipocliente.getEstaactivo())).getValue());
		type.setValidateID((int) idTipocliente.getConstanteValidacion());
		ArrayList<DocumentType> allowedDocs = new ArrayList<DocumentType>();
		for (Tipoclientetipodocumento doc : idTipocliente.getTipoclientetipodocumentoList()) {
			allowedDocs.add(docController.fromJPA(doc.getIdTipodocumento()));
		}
		ArrayList<String> idTypesMasks = new ArrayList<String>();
		for (Tipoidentificacioncliente clientIdType : idTipocliente.getTipoidentificacionclienteList()) {
			idTypesMasks.add(clientIdType.getMascara());
		}
		type.setValidationMasks(idTypesMasks);
		type.setAllowedDocumentTypes(allowedDocs);
		return type;
	}

	/**
	 * Method findTipocliente.
	 * 
	 * @param code
	 *            long
	 * @return Tipocliente
	 */
	public Tipocliente findTipocliente(long code) {
		return jpacontroller.findTipocliente(code);
	}
}
