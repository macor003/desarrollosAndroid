/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Service;

import crjpa.Servicio;

/**
 */
public class ServicioJpaController extends AbstractJPAController {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Servicio";

    /**
     * Constructor for ServicioJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public ServicioJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
    }

    /**
     * Method fromJPA.
     * 
     * @param jpaServices List<Servicio>
     * @return ArrayList<Service>
     */
    public ArrayList<Service> fromJPA(List<Servicio> jpaServices) {
        ArrayList<Service> services = new ArrayList<Service>();
        for (Servicio serv : jpaServices) {
            Service service = fromJPA(serv);
            services.add(service);
        }
        return services;
    }

    /**
     * Method fromJPA.
     * 
     * @param jpaService Servicio
     * @return Service
     */
    public Service fromJPA(Servicio jpaService) {
        Service service = new Service();
        service.setId(jpaService.getId());
        service.setName(jpaService.getDescripcion());
        return service;
    }
}
