/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CreditsType;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Tipoacreencia;

/**
 */
public class TipoacreenciaJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Tipoacreencia";
	/**
	 * Field controller.
	 */
	crjpa400.TipoacreenciaJpaController controller;
	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;

	/**
	 * Constructor for TipoacreenciaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TipoacreenciaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
		controller = new crjpa400.TipoacreenciaJpaController(emf);

	}

	/**
	 * Field serialVersionUID. (value is -5815260428879218730)
	 */
	private static final long serialVersionUID = -5815260428879218730L;

	/**
	 * Method fromJPA.
	 * 
	 * @param idtipo
	 *            Tipoacreencia
	 * @return CreditsType
	 */
	public CreditsType fromJPA(Tipoacreencia idtipo) {

		CreditsType creditsType = new CreditsType();
		creditsType.setDescription(idtipo.getDescripcion());
		creditsType.setId(idtipo.getId());
		creditsType.setPasswordRequired(ActiveValues.valueOf(String.valueOf(idtipo.getRequiereclave())).getValue());
		return creditsType;
	}

	/**
	 * Method toJPA.
	 * 
	 * @param creditsTypeId
	 *            CreditsType
	 * @return Tipoacreencia
	 */
	public Tipoacreencia toJPA(CreditsType creditsTypeId) {

		Tipoacreencia ta = new Tipoacreencia();
		ta.setDescripcion(creditsTypeId.getDescription());
		ta.setId((long) creditsTypeId.getId());
		return ta;
	}

}
