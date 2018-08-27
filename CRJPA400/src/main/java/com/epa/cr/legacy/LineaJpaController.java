/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.ArticleLine;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Categorialineaincluye;
import crjpa400.Linea;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class LineaJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Linea";

	/**
	 * Constructor for LineaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public LineaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method fromJPA.
	 * 
	 * @param articleLine
	 *            Linea
	 * @return ArticleLine
	 */
	public ArticleLine fromJPA(Linea articleLine) {
		CategoriaJpaController categoryController = new CategoriaJpaController(this.emf);
		PromocionlineaJpaController promotionLine = new PromocionlineaJpaController(this.emf);

		ArticleLine artLine = new ArticleLine();
		artLine.setCode(articleLine.getCodigo());
		artLine.setDate(articleLine.getFecha());
		artLine.setId(articleLine.getId());
		artLine.setIdCategory(categoryController.fromJPA(articleLine.getIdCategoria()));
		artLine.setIsActive(ActiveValues.get(new Character(articleLine.getEstaactivo()).toString()).getValue());
		artLine.setName(articleLine.getNombre());
		artLine.setRebateLine(ActiveValues.get(new Character(articleLine.getPermiterebaja()).toString()).getValue());
		artLine.setPromoLine(promotionLine.fromJPA(articleLine.getPromocionlineaList()));

		ArrayList<Long> economicActivities = new ArrayList<Long>();
		for (Categorialineaincluye includedLine : articleLine.getCategorialineaincluyeList()) {
			economicActivities.add(includedLine.getIdGiroactividadeconomica().getId());
		}
		artLine.setEconomicActivitiesList(economicActivities);
		return artLine;
	}

}
