/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.DeliveryConditionCR400;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Condicionentrega;

/**
 * 
 * @author Marco Nuñez (analista12)
 * 
 * @version $Revision: 1.0 $
 */
public class CondicionentregaJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Condicionentrega";
	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;

	/**
	 * Constructor for CondicionentregaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public CondicionentregaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * Method create.
	 * 
	 * @param deliveryCondition
	 *            DeliveryConditionCR400
	 * @throws JpaException
	 */
	public void create(DeliveryConditionCR400 deliveryCondition) throws JpaException {
		Condicionentrega condicionentrega = toJPA(deliveryCondition);
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(condicionentrega);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			throw new JpaException();
		} finally {
			if (em.isOpen()) {
				em.clear();
				em.close();
			}
		}
		deliveryCondition.setId(condicionentrega.getId());
	}

	/**
	 * Method toJPA.
	 * 
	 * @param deliveryCondition
	 *            DeliveryConditionCR400
	 * @return Condicionentrega
	 */
	public Condicionentrega toJPA(DeliveryConditionCR400 deliveryCondition) {
		Condicionentrega condicionentrega = new Condicionentrega();

		if (deliveryCondition.getId() > 0) {
			condicionentrega.setId(deliveryCondition.getId());
		} else {
			condicionentrega.setId(null);
		}

		condicionentrega.setTipo(deliveryCondition.getType().charAt(0));
		condicionentrega.setDescripcion(deliveryCondition.getDescription());
		condicionentrega.setRequiereIdClienteRecibe(ActiveValues.get(deliveryCondition.isClientIdRequired())
				.getString().charAt(0));
		condicionentrega.setRequiereTelefono(ActiveValues.get(deliveryCondition.isPhoneNumberRequired()).getString()
				.charAt(0));
		condicionentrega.setRequiereDireccion(ActiveValues.get(deliveryCondition.isAddressRequired()).getString()
				.charAt(0));
		if (deliveryCondition.getDocumentType() != null && deliveryCondition.getDocumentType().getIdTipoDoc() > 0) {
			TipodocumentoJpaController tipoDocumentoJpaController = new TipodocumentoJpaController(emf);
			condicionentrega.setTipodocumento(tipoDocumentoJpaController.toJPA(deliveryCondition.getDocumentType()));
		}

		return condicionentrega;
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param tmp
	 *            Condicionentrega
	 * @return DeliveryConditionCR400
	 */
	public DeliveryConditionCR400 fromJPA(Condicionentrega tmp) {
		DeliveryConditionCR400 deliveryCondition = new DeliveryConditionCR400();

		deliveryCondition.setId(tmp.getId());
		deliveryCondition.setType(String.valueOf(tmp.getTipo()));
		deliveryCondition.setDescription(tmp.getDescripcion());
		deliveryCondition.setClientIdRequired(ActiveValues.get(String.valueOf(tmp.getRequiereIdClienteRecibe()))
				.getValue());
		deliveryCondition
				.setPhoneNumberRequired(ActiveValues.get(String.valueOf(tmp.getRequiereTelefono())).getValue());
		deliveryCondition.setAddressRequired(ActiveValues.get(String.valueOf(tmp.getRequiereDireccion())).getValue());
		if (tmp.getTipodocumento() != null && tmp.getTipodocumento().getId() > 0) {
			TipodocumentoJpaController tipoDocumentoJpaController = new TipodocumentoJpaController(emf);
			deliveryCondition.setDocumentType(tipoDocumentoJpaController.fromJPA(tmp.getTipodocumento()));
		}

		return deliveryCondition;
	}

	/**
	 * Method edit.
	 * 
	 * @param deliveryCondition
	 *            DeliveryConditionCR400
	 * @throws JpaException
	 */
	public void edit(DeliveryConditionCR400 deliveryCondition) throws JpaException {
		Condicionentrega condicionentrega = new Condicionentrega();
		condicionentrega = toJPA(deliveryCondition);
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(condicionentrega);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
			throw new JpaException();
		} finally {
			if (em.isOpen()) {
				em.clear();
				em.close();
			}
		}
	}

}
