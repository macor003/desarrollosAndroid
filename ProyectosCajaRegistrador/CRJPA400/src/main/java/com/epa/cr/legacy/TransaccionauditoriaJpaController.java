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
import com.becoblohm.cr.models.TransactionAudit;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Proceso;
import crjpa400.Transaccion;
import crjpa400.Transaccionauditoria;
import crjpa400.Usuario;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TransaccionauditoriaJpaController extends AbstractJPAController {

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
	public TransaccionauditoriaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param jpaTransaccionAudit
	 *            crjpa400.Transacciondocumento
	 * @return TransactionDocument
	 */
	public TransactionAudit fromJPA(crjpa400.Transaccionauditoria jpaTransaccionAudit) {
		TransactionAudit audit = new TransactionAudit();
		audit.setAuditDate(jpaTransaccionAudit.getFecha());
		audit.setAuthorizedProcess(ProcesoJpaController.fromJPA(jpaTransaccionAudit.getIdProceso()));
		audit.setAuthorizer(UsuarioJpaController.fromJpa(jpaTransaccionAudit.getIdUsuario()));
		return audit;
	}
	
	public Transaccionauditoria toJPA(TransactionAudit audit, Transaccion jpaTransaction) {
		
		Transaccionauditoria jpaObject = new Transaccionauditoria();
		jpaObject.setFecha(audit.getAuditDate());
		jpaObject.setIdProceso(new Proceso(Long.valueOf(audit.getAuthorizedProcess().getId())));
		jpaObject.setIdTransaccion(jpaTransaction);
		jpaObject.setIdUsuario(new Usuario(audit.getAuthorizer().getId()));
		
		return jpaObject;
	}
}
