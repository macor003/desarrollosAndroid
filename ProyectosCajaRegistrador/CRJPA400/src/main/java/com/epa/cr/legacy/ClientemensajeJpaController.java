/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Message;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Clientemensaje;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ClientemensajeJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Clientemensaje";

	/**
	 * Constructor for ClientemensajeJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ClientemensajeJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method fromJPA.
	 * 
	 * @param jpaMessage
	 *            Clientemensaje
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
	 * @param jpaMessages
	 *            List<Clientemensaje>
	 * @return ArrayList<Message>
	 */
	public ArrayList<Message> fromJPA(List<Clientemensaje> jpaMessages) {
		ArrayList<Message> messages = new ArrayList<Message>();
		if (jpaMessages != null) {
			for (Clientemensaje jpaMessage : jpaMessages) {
				if (ActiveValues.get(String.valueOf(jpaMessage.getEstaactivo())).getValue()) {
					Message m = new Message();
					m.setMessageText(jpaMessage.getMensaje());
					messages.add(m);
				}
			}
		}
		return messages;

	}
}
