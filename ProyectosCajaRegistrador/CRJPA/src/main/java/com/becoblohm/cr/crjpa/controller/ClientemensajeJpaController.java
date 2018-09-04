/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Message;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Clientemensaje;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ClientemensajeJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.ClientemensajeJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Clientemensaje";

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for ClientemensajeJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public ClientemensajeJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        jpacontroller = new crjpa.ClientemensajeJpaController(emf);
    }

    /**
     * Method fromJPA.
     * 
     * @param jpaMessage Clientemensaje
     * @return Message
     */
    public Message fromJPA(Clientemensaje jpaMessage) {
        Message m = new Message();
        m.setMessageText(jpaMessage.getMensaje());
        return m;
    }

    /**
     * Method fromJPA.
     * 
     * @param jpaMessages List<Clientemensaje>
     * @return ArrayList<Message>
     */
    public ArrayList<Message> fromJPA(List<Clientemensaje> jpaMessages) {
        ArrayList<Message> messages = new ArrayList<Message>();
        if (jpaMessages != null) {
            for (Clientemensaje jpaMessage : jpaMessages) {
                if (jpaMessage.getEstaactivo().equalsIgnoreCase(ActiveValues.S.name())) {
                    Message m = new Message();
                    m.setMessageText(jpaMessage.getMensaje());
                    messages.add(m);
                }
            }
        }
        return messages;

    }
}
