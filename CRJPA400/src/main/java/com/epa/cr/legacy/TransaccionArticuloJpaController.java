/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Service;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Unidadventa;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TransaccionArticuloJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Transaccionarticulo";
	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;

	/**
	 * Constructor for TransaccionArticuloJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TransaccionArticuloJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * Method toJPA.
	 * 
	 * @param article
	 *            Article
	 * @param transaction
	 *            crjpa400.Transaccion
	 * @return crjpa400.Transaccionarticulo
	 */
	public crjpa400.Transaccionarticulo toJPA(Article article, crjpa400.Transaccion transaction) {
		TasaimpuestovalorJpaController taxValuecontroller = new TasaimpuestovalorJpaController(this.emf);
		TransaccionarticuloServicioJpaController serviceController = new TransaccionarticuloServicioJpaController(
				this.emf);
		TipodescuentoJpaController tipoDescuentoJpaController = new TipodescuentoJpaController(this.emf);

		String correlativo = new String("" + transaction.getIdSesion().getIdCaja().getId()
				+ article.getTransactionArticleId());

		crjpa400.Transaccionarticulo transArt = new crjpa400.Transaccionarticulo();
		if (transaction.getNumero() == 0) {
			transArt.setId(-1l);
		}
		// transArt.setId(Long.valueOf(transaction.getIdSesion().getId()+""+article.getTransactionArticleId()));
		transArt.setCantidad(article.getItems().getValue());
		transArt.setCaptura(article.getCaptureMode());
		transArt.setCondicionEntrega(article.getDeliveryCondition().getType().charAt(0));
		transArt.setFechaInicioGarantia(new Date());
		transArt.setFechaVenta(new Date());
		crjpa400.Articulo tmpArt = new crjpa400.Articulo();
		tmpArt.setId(article.getId());
		transArt.setIdArticulo(tmpArt);
		if (article.getSelectedPromo() != null) {
			crjpa400.Promocion selectedPromo = new crjpa400.Promocion();
			selectedPromo.setId(article.getSelectedPromo().getId());
			transArt.setIdPromocion(selectedPromo);
		}
		transArt.setIdTasaimpuestovalor(taxValuecontroller.findTasaivaValorByidTipo(article.getTax().getId()));
		System.out.println("tipo descuento " + article.getDiscountType().getId());
		transArt.setIdTipodescuento(tipoDescuentoJpaController.toJpa(article.getDiscountType()));
		transArt.setIdTransaccion(transaction);
		crjpa400.Unidadventa saleUnit = new Unidadventa();
		saleUnit.setId(article.getSaleUnit().getId());
		transArt.setIdUnidadventa(saleUnit);
		ArrayList<crjpa400.Transaccionarticuloservicio> servicesList = new ArrayList<crjpa400.Transaccionarticuloservicio>();
		for (Entry<Long, Service> entry : article.getServices().entrySet()) {
			crjpa400.Transaccionarticuloservicio tmp = serviceController.toJPA(entry.getValue());
			if (tmp != null) {
				servicesList.add(tmp);
			}
		}
		transArt.setTransaccionarticuloservicioList(servicesList);// TODO setear
																	// los
																	// servicios

		transArt.setTotalRebaja(article.getOriginalItemCost().subtract(article.getItemCost()).getValue());
		transArt.setMontoUnitario(article.getOriginalItemCost().getValue());
		transArt.setMontoBase(transArt.getMontoUnitario().subtract(transArt.getTotalRebaja()));
		transArt.setTotalArticulo(article.getTotalCost().getValue());
		transArt.setTotalBase(article.getNonTaxedTotalCost().getValue());
		transArt.setMontoImpuesto(article.getTax().getTaxRate()
				.multiply(new CRBigDecimal(transArt.getMontoBase().doubleValue()).divide(CRBigDecimal.HUNDRED))
				.getValue());
		transArt.setTotalImpuesto(article.getTotalTaxAmount().getValue());
		transArt.setCaptura(article.getCaptureMode());
		// transArt.setMontoFinal(article.getTotalCost().getValue());
		// transArt.setMontoUnidadOriginal(article.getItemCost().getValue());
		// transArt.setMontoFinalSinimpuesto(article.getTotalCost().getValue());
		return transArt;
	}

}
