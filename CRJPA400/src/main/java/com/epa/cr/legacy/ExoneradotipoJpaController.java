/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.epa.cr.legacy;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.ExonerationType;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Exoneradoarticulo;
import crjpa400.Exoneradotipo;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ExoneradotipoJpaController extends AbstractJPAController {

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
	private static String entityName = "Exoneradotipo";
	/**
	 * Field jpa.
	 */
	private static crjpa400.ExoneradotipoJpaController jpa;

	/**
	 * @param p_emf
	 */
	public ExoneradotipoJpaController(EntityManagerFactory p_emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = p_emf;
		jpa = new crjpa400.ExoneradotipoJpaController(emf);
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param exonerationTypeJPA
	 *            Exoneradotipo
	 * @return ExonerationType
	 */
	public ExonerationType fromJPA(Exoneradotipo exonerationTypeJPA) {
		ArticuloJpaController artController = new ArticuloJpaController(this.emf);
		ExonerationType exonerationType = new ExonerationType();
		exonerationType.setCalculateTax(ActiveValues.get(
				new Character(exonerationTypeJPA.getCalculaImpuesto()).toString()).getValue());
		exonerationType.setExonerationName(exonerationTypeJPA.getDescripcion());
		exonerationType.setId(exonerationTypeJPA.getId());
		exonerationType.setPartial(ActiveValues.get(new Character(exonerationTypeJPA.getAplicaParcial()).toString())
				.getValue());
		ArrayList<Article> articles = new ArrayList<Article>();
		for (Exoneradoarticulo exoneratedArticle : exonerationTypeJPA.getExoneradoarticuloList()) {
			articles.add(artController.fromJPA(exoneratedArticle.getArticulo()));
		}
		exonerationType.setExoneratedArticles(articles);
		return exonerationType;
	}

	/**
	 * Method findByCode.
	 * 
	 * @param id
	 *            long
	 * @return ExonerationType
	 */
	public ExonerationType findByCode(long id) {
		System.out.println("Buscando Tipo de exoneracion id ---> " + id);
		return fromJPA(jpa.findExoneradotipo(id));
	}

}
