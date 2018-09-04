/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Service;

import crjpa.Servicio;
import crjpa.Transaccionarticuloservicio;

/**
 */
public class TransaccionarticuloServicioJpaController extends AbstractJPAController {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field jpaController.
     */
    private crjpa.TransaccionarticuloservicioJpaController jpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Transaccionarticuloservicio";

    /**
     * Constructor for TransaccionarticuloServicioJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TransaccionarticuloServicioJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.TransaccionarticuloservicioJpaController(emf);
    }

    /**
     * Method toJPA.
     * 
     * @param service Service
     * @return Transaccionarticuloservicio
     */
    public Transaccionarticuloservicio toJPA(Service service) {
        Servicio tmpJpaService = new Servicio();
        tmpJpaService.setId(service.getId());
        Transaccionarticuloservicio jpaService = new Transaccionarticuloservicio();
        jpaService.setCorreoElectronico(service.getEmail());
        jpaService.setTelefono(service.getPhoneNumber());
        jpaService.setIdServicio(tmpJpaService);
        jpaService.setId(service.getTransactionServiceId());
        if (service.getTransactionServiceId() < 0) {
            jpaService.setId(null);
        }
        jpaService.setEstasincronizado("N");
        if (service.getEmail() == null && service.getPhoneNumber() == null) {
            jpaService = null;
        }
        return jpaService;
    }

    // public void createOrEdit(Transaccionarticuloservicio
    // transaccionarticuloServicio) throws NonexistentEntityException,
    // Exception{
    // if(transaccionarticuloServicio.getId()<0){
    // this.jpaController.create(transaccionarticuloServicio);
    // }else{
    // this.jpaController.edit(transaccionarticuloServicio);
    // }
    // }

}
