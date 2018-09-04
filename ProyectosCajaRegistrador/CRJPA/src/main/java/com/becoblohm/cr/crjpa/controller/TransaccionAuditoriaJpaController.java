/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.TransactionAudit;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Proceso;
import crjpa.Transaccion;
import crjpa.Transaccionauditoria;
import crjpa.Usuario;

/**
 */
public class TransaccionAuditoriaJpaController extends AbstractJPAController {

    /**
     * Field entityName.
     */
    private static String entityName = "Transaccionauditoria";

    private EntityManagerFactory emf;

    /**
     * Constructor for TransaccionFaseJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TransaccionAuditoriaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
    }

    /**
     * Method toJPA.
     * 
     * @param transaction Transaction
     * @param jpatr Transaccion
     * @return List<Transaccionfase>
     */
    public Transaccionauditoria toJPA(TransactionAudit audit, Transaccion jpaTransaction) {

        Transaccionauditoria jpaObject = new Transaccionauditoria();
        jpaObject.setEstasincronizado(ActiveValues.N.getString());
        jpaObject.setFecha(audit.getAuditDate());
        jpaObject.setIdProceso(new Proceso(Long.valueOf(audit.getAuthorizedProcess().getId())));
        jpaObject.setIdTransaccion(jpaTransaction);
        jpaObject.setIdUsuario(new Usuario(audit.getAuthorizer().getId()));

        return jpaObject;
    }

    public TransactionAudit fromJPA(Transaccionauditoria jpaAudit) {
        UsuarioJpaController userController = new UsuarioJpaController(emf);
        TransactionAudit audit = new TransactionAudit();
        audit.setId(jpaAudit.getId());
        audit.setAuditDate(jpaAudit.getFecha());
        audit.setAuthorizedProcess(ProcesoJpaController.fromJPA(jpaAudit.getIdProceso()));
        audit.setAuthorizer(userController.fromJPA(jpaAudit.getIdUsuario()));
        return audit;
    }

}
