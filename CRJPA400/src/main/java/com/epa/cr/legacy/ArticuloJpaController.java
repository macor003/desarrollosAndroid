/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.DeliveryCondition;
import com.becoblohm.cr.models.Service;
import com.becoblohm.cr.models.Tax;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Articulo;
import crjpa400.Articulounidadventa;
import crjpa400.Transaccionarticulo;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ArticuloJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Articulo";
	/**
	 * Field jpaController.
	 */
	private crjpa400.ArticuloJpaController jpaController;

	/**
	 * Constructor for ArticuloJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ArticuloJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
		jpaController = new crjpa400.ArticuloJpaController(emf);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method findArticuloByCode.
	 * 
	 * @param codigo
	 *            long
	 * @return crjpa400.Articulo
	 */
	public crjpa400.Articulo findArticuloByCode(long codigo) {
		EntityManager em = emf.createEntityManager();
		crjpa400.Articulo singleResult = null;
		try {
			Query query = em.createNamedQuery("Articulo.findByCodigo");
			query.setParameter("codigo", codigo);
			query.setMaxResults(1);
			singleResult = (crjpa400.Articulo) query.getSingleResult();
		} catch (Exception ex) {
			singleResult = null;
		} finally {
			em.close();
		}
		return singleResult;
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param jpaArticle
	 *            crjpa400.Articulo
	 * @return Article
	 */
	public Article fromJPA(crjpa400.Articulo jpaArticle) {

		LineaJpaController lineController = new LineaJpaController(this.emf);
		// PromocionArticuloJpaController promoController=new
		// PromocionArticuloJpaController(this.emf);
		ServicioJpaController serviceController = new ServicioJpaController(this.emf);
		TasaimpuestotipoJpaController taxController = new TasaimpuestotipoJpaController(this.emf);
		// FamiliaJpaController familyController=new
		// FamiliaJpaController(this.emf);
		ArticulounidadventaJpaController saleUnitController = new ArticulounidadventaJpaController(this.emf);

		Article art = new Article();
		art.setId(jpaArticle.getId());
		art.setCode(String.valueOf(jpaArticle.getCodigo()));

		if (jpaArticle.getIdTasaimpuestotipo() == null) {
			art.setTax(null);
		} else {
			art.setTax(taxController.fromJPA(jpaArticle.getIdTasaimpuestotipo()));
		}

		Articulounidadventa saleUnit = jpaArticle.getArticulounidadventaList().get(
				jpaArticle.getArticulounidadventaList().size() - 1);
		art.setCode(String.valueOf(jpaArticle.getCodigo()));
		art.setName(jpaArticle.getNombre());
		if (jpaArticle.getIdTasaimpuestotipo() == null) {
			art.setTax(null);
			art.setOriginalItemTax(null);
		} else {
			art.setTax(taxController.fromJPA(jpaArticle.getIdTasaimpuestotipo()));
			art.setOriginalItemTax(taxController.fromJPA(jpaArticle.getIdTasaimpuestotipo()));
		}

		art.setItemCost(new CRBigDecimal(saleUnit.getPrecio().doubleValue()));
		art.setSaleUnit(saleUnitController.fromJPA(saleUnit));
		art.setOriginalItemCost(art.getSaleUnit().getPrice());
		art.setItems(art.getSaleUnit().getDefaultQuantity());
		art.setLine(lineController.fromJPA(jpaArticle.getIdLinea()));
		
		art.setAmountMaxPeriod(new CRBigDecimal(jpaArticle.getCantidadMaximaPeriodo().doubleValue()));
		art.setAmountMaxTransaction(new CRBigDecimal(jpaArticle.getCantidadMaximaTransaccion().doubleValue()));
		art.setBuyPeriod(jpaArticle.getPeriodoCompra());
		
		HashMap<Long, Service> artServices = new HashMap<Long, Service>();
		
		art.setServices(artServices);
		PromocionarticuloJpaController promotionArticle = new PromocionarticuloJpaController(this.emf);
		art.setPromoArticle(promotionArticle.fromJPA(jpaArticle.getPromocionarticuloList()));
		return art;
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param transaccionArticuloList
	 *            List<Transaccionarticulo>
	 * @return Collection<Article>
	 */
	public Collection<Article> fromJPA(List<Transaccionarticulo> transaccionArticuloList) {
		ArrayList<Article> articles = new ArrayList<Article>();
		for (Transaccionarticulo art : transaccionArticuloList) {
			articles.add(fromJPA(art.getIdArticulo()));
		}
		return articles;
	}

	/**
	 * Method findArticuloByCategorizedCode.
	 * 
	 * @param codigo
	 *            long
	 * @return Articulo
	 */
	public Articulo findArticuloByCategorizedCode(long codigo) {
		Articulo searchedArt = findArticuloByCode(codigo);
		Articulo categorizedArticle = findCategorizedArticle(searchedArt);
		return categorizedArticle;
	}

	/**
	 * Method findCategorizedArticle.
	 * 
	 * @param art
	 *            Articulo
	 * @return Articulo
	 */
	private Articulo findCategorizedArticle(Articulo art) {
		Articulo categorizedArticle = null;
		if (art.getIdArticulocategorizado() != null) {
			categorizedArticle = findCategorizedArticle(art);
		} else {
			categorizedArticle = art;
		}
		return categorizedArticle;
	}

	/**
	 * Method findArticle.
	 * 
	 * @param codigo
	 *            long
	 * @return Article
	 */
	public Article findArticle(long codigo) {
		Article art = null;
		ArticulocodigoexternoJpaController externalCodeJpaController = new ArticulocodigoexternoJpaController(this.emf);
		Articulo tmp = externalCodeJpaController.findArticuloEntitiesByCodigoext(codigo);
		if (tmp == null) {
			Articulo jpaArticle = findJpaArticle(codigo);
			if (jpaArticle != null && jpaArticle.getIdArticulocategorizado() != null) {
				jpaArticle = findCategorizedArticle(jpaArticle);
				art = fromJPA(jpaArticle);
			} else if (jpaArticle != null) {
				art = fromJPA(jpaArticle);
			}
		} else {
			System.out.println("codigo ext" + tmp);
			art = fromJPA(tmp);
		}

		if (art != null && art.getItemCost().doubleValue() <= 0) {
			art = null;
		}

		return art;
	}

	/**
	 * Method findJpaArticle.
	 * 
	 * @param code
	 *            long
	 * @return Articulo
	 */
	private Articulo findJpaArticle(long code) {
		EntityManager em = jpaController.getEntityManager();
		Articulo singleResult = null;
		try {
			Query query = em.createNamedQuery("Articulo.findByCodigo");
			query.setParameter("codigo", code);
			query.setMaxResults(1);
			singleResult = (Articulo) query.getSingleResult();
		} catch (Exception ex) {
			singleResult = null;
		} finally {
			em.close();
		}
		return singleResult;
	}
	
	
	/**
	 * Method fromJPA.
	 * 
	 * @param jpaArticle
	 *            crjpa.Transaccionarticulo
	 * @return Article
	 */
	public Article fromJPA(crjpa400.Transaccionarticulo jpaArticle) {
		Article article = fromJPA(jpaArticle.getIdArticulo());
		TipodescuentoJpaController tipoDescuentoJpaController = new TipodescuentoJpaController(this.emf);
		TasaimpuestotipoJpaController taxController = new TasaimpuestotipoJpaController(this.emf);
		article.setDeliveryCondition(DeliveryCondition.getDeliveryConditionsHash().get(
				String.valueOf(jpaArticle.getCondicionEntrega())));
		article.setDiscountType(tipoDescuentoJpaController.fromJpa(jpaArticle.getIdTipodescuento()));
		article.setItems(new CRBigDecimal(jpaArticle.getCantidad().doubleValue()));
		article.setOriginalItemCost(new CRBigDecimal(jpaArticle.getMontoUnitario().doubleValue()));
		article.setItemCost(new CRBigDecimal(jpaArticle.getMontoBase().doubleValue()));

		Tax tax = taxController.fromJPA(jpaArticle.getIdTasaimpuestovalor().getIdTasaimpuestotipo());
		article.setTax(tax);

		if (jpaArticle.getId() > 0) {
			article.setTransactionArticleId(jpaArticle.getId());
		}

		return article;
	}
	
	
}
