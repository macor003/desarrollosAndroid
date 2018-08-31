/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa.Cliente;
import crjpa.Transaccion;
import crjpa.Transaccioncliente;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TransaccionClienteJpaController extends AbstractJPAController {

    /**
     * Field jpaController.
     */
    private final crjpa.TransaccionclienteJpaController jpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Transaccioncliente";

    /**
     * Constructor for TransaccionClienteJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TransaccionClienteJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.TransaccionclienteJpaController(emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method toJPA.
     * 
     * @param client Cliente
     * @param transaction Transaccion
     * @param id long
     * @return Transaccioncliente
     */
    public Transaccioncliente toJPA(Cliente client, Transaccion transaction, long id) {
        Transaccioncliente obj = new Transaccioncliente();
        if (client.getDireccion() == null) {
            obj.setDireccion("");
        } else {
            obj.setDireccion(client.getDireccion());
        }
        if (client.getDireccionFiscal() == null) {
            obj.setDireccionFiscal("");
        } else {
            obj.setDireccionFiscal(client.getDireccionFiscal());
        }
        obj.setId(id);
        obj.setNumeroIdentificacionCliente(client);
        obj.setIdGiroactividadeconomica(client.getIdGiroactividadeconomica());
        obj.setIdTransaccion(transaction);
        obj.setNombre(client.getNombre());
        obj.setTelefono(client.getTelefono());
        obj.setEstasincronizado("N");
        return obj;
    }

    // public void createOrEdit(Transaccioncliente transaccionCliente) throws
    // IllegalOrphanException, NonexistentEntityException, Exception{
    // if(transaccionCliente.getId()<0){
    // this.jpaController.create(transaccionCliente);
    // }else{
    // this.jpaController.edit(transaccionCliente);
    // }
    // }

}
