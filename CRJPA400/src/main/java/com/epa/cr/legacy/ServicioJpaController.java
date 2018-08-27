/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Service;

import crjpa400.Articuloservicio;

/**
 */
public class ServicioJpaController extends AbstractJPAController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Servicio";

	/**
	 * Constructor for ServicioJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ServicioJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param jpaServices
	 *            List<Articuloservicio>
	 * @return ArrayList<Service>
	 */
	public static ArrayList<Service> fromJPA(List<Articuloservicio> jpaServices) {
		ArrayList<Service> services = new ArrayList<Service>();
		for (Articuloservicio serv : jpaServices) {
			Service service = fromJPA(serv);
			services.add(service);
		}
		return services;
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param jpaService
	 *            Articuloservicio
	 * @return Service
	 */
	public static Service fromJPA(Articuloservicio jpaService) {
		Service service = new Service();
		service.setId(jpaService.getServicio().getId());
		service.setName(jpaService.getServicio().getDescripcion());
		return service;
	}
}
