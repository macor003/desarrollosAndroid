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
import com.becoblohm.cr.models.Service;

import crjpa400.Transaccionarticuloservicio;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class TransaccionarticuloServicioJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Transaccionarticuloservicio";

	/**
	 * Constructor for TransaccionarticuloServicioJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TransaccionarticuloServicioJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * Method toJPA.
	 * 
	 * @param service
	 *            Service
	 * @return crjpa400.Transaccionarticuloservicio
	 */
	public crjpa400.Transaccionarticuloservicio toJPA(Service service) {
		crjpa400.Servicio tmpJpaService = new crjpa400.Servicio();
		tmpJpaService.setId(service.getId());
		Transaccionarticuloservicio jpaService = new Transaccionarticuloservicio();
		jpaService.setCorreoElectronico(service.getEmail());
		jpaService.setTelefono(service.getPhoneNumber());
		jpaService.setIdServicio(tmpJpaService);
		jpaService.setId(service.getTransactionServiceId());
		if (service.getEmail() == null && service.getPhoneNumber() == null) {
			jpaService = null;
		}
		return jpaService;
	}
}
