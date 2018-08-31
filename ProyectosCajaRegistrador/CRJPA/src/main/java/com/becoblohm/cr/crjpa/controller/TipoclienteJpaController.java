/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.ClientType;
import com.becoblohm.cr.models.DocumentType;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Tipocliente;
import crjpa.Tipoclientetipodocumento;
import crjpa.Tipoidentificacioncliente;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TipoclienteJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.TipoclienteJpaController jpacontroller;

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Tipocliente";

    /**
     * Constructor for TipoclienteJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TipoclienteJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        jpacontroller = new crjpa.TipoclienteJpaController(emf);
        this.emf = emf;

    }

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method findTipoclienteEntities.
     * 
     * @return List<ClientType>
     */
    public List<ClientType> findTipoclienteEntities() {

        List<ClientType> result = new ArrayList<ClientType>();
        List<Tipocliente> tmp = jpacontroller.findTipoclienteEntities();
        for (Iterator iterator = tmp.iterator(); iterator.hasNext();) {
            Tipocliente tipocliente = (Tipocliente) iterator.next();
            result.add(fromJPA(tipocliente));

        }
        return result;
    }

    /**
     * Method findTipoclienteByCode.
     * 
     * @param code long
     * @return ClientType
     */
    public ClientType findTipoclienteByCode(long code) {
        return fromJPA(jpacontroller.findTipocliente(code));
    }

    /**
     * Method findTipocliente.
     * 
     * @param code long
     * @return Tipocliente
     */
    public Tipocliente findTipocliente(long code) {
        return jpacontroller.findTipocliente(code);
    }

    /**
     * Method fromJPA.
     * 
     * @param idTipocliente Tipocliente
     * @return ClientType
     */
    public ClientType fromJPA(Tipocliente idTipocliente) {
        TipodocumentoJpaController docController = new TipodocumentoJpaController(this.emf);
        ClientType type = new ClientType();
        type.setId(idTipocliente.getId().intValue());
        type.setMask(idTipocliente.getMascara());
        type.setName(idTipocliente.getNombre());
        type.setSymbol(String.valueOf(idTipocliente.getSimbolo()));
        type.setTaxPayer(ActiveValues.get(idTipocliente.getEscontribuyente()).getValue());
        type.setActive(ActiveValues.get(idTipocliente.getEstaactivo()).getValue());
        type.setValidateID((int) idTipocliente.getConstanteValidacion());
        ArrayList<DocumentType> allowedDocs = new ArrayList<DocumentType>();
        if (idTipocliente.getTipoclientetipodocumentoList() != null) {
            for (Tipoclientetipodocumento doc : idTipocliente.getTipoclientetipodocumentoList()) {
                allowedDocs.add(docController.fromJPA(doc.getIdTipodocumento()));
            }
        }
        ArrayList<String> idTypesMasks = new ArrayList<String>();
        if (idTipocliente.getTipoidentificacionclienteList() != null) {
            for (Tipoidentificacioncliente clientIdType : idTipocliente.getTipoidentificacionclienteList()) {
                idTypesMasks.add(clientIdType.getMascara());
            }
        }
        type.setValidationMasks(idTypesMasks);
        type.setAllowedDocumentTypes(allowedDocs);
        return type;

    }

    /**
     * Method toJPA.
     * 
     * @param idTipocliente Tipocliente
     * @return ClientType
     */
    public ClientType toJPA(Tipocliente idTipocliente) {
        ClientType result = new ClientType(String.valueOf(idTipocliente.getSimbolo()),
                idTipocliente.getId().intValue(), idTipocliente.getMascara(),
                ActiveValues.get(idTipocliente.getEscontribuyente()).getValue(),
                (int) idTipocliente.getConstanteValidacion());
        return result;
    }
}
