/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.PromotionsMethod;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Articulounidadventa;
import crjpa400.Promocion;
import crjpa400.Promocionarticulo;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class PromocionarticuloJpaController extends AbstractJPAController {

	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field emf.
	 */
	EntityManagerFactory emf;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Promocionarticulo";

	/**
	 * Constructor for PromocionarticuloJpaController.
	 * 
	 * @param p_emf
	 *            EntityManagerFactory
	 */
	public PromocionarticuloJpaController(EntityManagerFactory p_emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
	}

	/**
	 * Method findPromocionesArticulos.
	 * 
	 * @param idArt
	 *            crjpa400.Articulo
	 * @return Promocion
	 */
	@SuppressWarnings("finally")
	public Promocion findPromocionesArticulos(crjpa400.Articulo idArt) {
		Promocionarticulo idPromocion = null;
		Promocion promo = null;
		EntityManager em = this.emf.createEntityManager();
		Query query = em
				.createQuery("SELECT DISTINCT a  FROM PromocionArticulo a WHERE a.idArticulo = :idArt AND a.montoDescuento = "
						+ "(SELECT MAX(p.montoDescuento) FROM PromocionArticulo p WHERE p.idArticulo = :idArt AND p.status='A')");
		query.setParameter("idArt", idArt);
		query.setMaxResults(1);
		try {
			idPromocion = (Promocionarticulo) query.getSingleResult();
			promo = idPromocion.getIdPromocion();
		} catch (Exception e) {
			e.printStackTrace();
			promo = null;
		} finally {
			em.close();
			return promo;
		}
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param promocionArticulo
	 *            List<Promocionarticulo>
	 * @return TreeMap<CRBigDecimal,PromotionsMethod>
	 */
	public TreeMap<CRBigDecimal, PromotionsMethod> fromJPA(List<Promocionarticulo> promocionArticulo) {

		TreeMap<CRBigDecimal, PromotionsMethod> arrayPomotion = new TreeMap<CRBigDecimal, PromotionsMethod>();
		for (Promocionarticulo pa : promocionArticulo) {
			// //System.err.println("6------->"+pa.getIdPromocion().getEstaactivo()+"<-----------");
			if (ActiveValues.get(String.valueOf(pa.getEstaactivo())).getValue()) {

				PromotionsMethod pmTmp = this.fromJPA(pa);

				// //System.err.println("1-----> "+pmTmp.getInitDate()+"<------");
				// //System.err.println("2-----> "+pmTmp.getEndDate()+"<------");
				// //System.err.println("3-----> "+new Date()+"<------");
				// //System.err.println("4-----> "+pmTmp.getInitDate().compareTo(new
				// Date())+"<------");
				// //System.err.println("5-----> "+pmTmp.getEndDate().compareTo(new
				// Date())+"<------");

				if (pmTmp != null && (ActiveValues.get(pmTmp.getActive()).getValue())
						&& pmTmp.getInitDate().compareTo(new Date()) <= 0
						&& pmTmp.getEndDate().compareTo(new Date()) >= 0) {

					Articulounidadventa tmp = pa.getIdArticulo().getArticulounidadventaList()
							.get(pa.getIdArticulo().getArticulounidadventaList().size() - 1);
					CRBigDecimal discount = new CRBigDecimal(tmp.getPrecio()
							.subtract(pmTmp.getDiscountAmount().getValue()).doubleValue());

					if (pa.getIdPromocion().getPorcentajeDescuento().compareTo(BigDecimal.ZERO) > 0) {

                        discount = new CRBigDecimal(
                                tmp.getPrecio().multiply(pa.getIdPromocion().getPorcentajeDescuento())
                                        .divide(new BigDecimal(100)).doubleValue());
                        // pmTmp.setDiscountAmount(result);
                    } else if (pa.getIdPromocion().getCodigo() == 14) {
                        discount = new CRBigDecimal(tmp.getPrecio().multiply(pmTmp.getDiscountAmount().getValue())
                                .divide(new BigDecimal(100)).doubleValue());
                    }/// HERE MAN

					arrayPomotion.put(discount, pmTmp);
				}
			}
		}

		return arrayPomotion;

	}

	/**
	 * Method fromJPA.
	 * 
	 * @param promocionArticulo
	 *            Promocionarticulo
	 * @return PromotionsMethod
	 */
	public PromotionsMethod fromJPA(Promocionarticulo promocionArticulo) {

		PromotionsMethod pm = new PromotionsMethod();

		PromocionformadepagoJpaController promPaymentMethodJPA = new PromocionformadepagoJpaController(emf);
		PromociontipodescuentoJpaController promDiscountTypeJPA = new PromociontipodescuentoJpaController(emf);
		PromocionJpaController promoc = new PromocionJpaController(emf);

		pm.setActive(ActiveValues.get(String.valueOf(promocionArticulo.getEstaactivo())).getValue());
		pm.setCod(promocionArticulo.getIdPromocion().getCodigo());
		pm.setDiscountAmount(new CRBigDecimal(promocionArticulo.getMontoDescuento().doubleValue()));
		pm.setDiscountRate(promoc.fromJPA(promocionArticulo.getIdPromocion()).getDiscountRate());
		pm.setEndDate(promocionArticulo.getIdPromocion().getFechaFin());
		pm.setId(promocionArticulo.getIdPromocion().getId());
		pm.setInitDate(promocionArticulo.getIdPromocion().getFechaInicio());
		pm.setMaximumValue(new CRBigDecimal(promocionArticulo.getIdPromocion().getValorMaximo()));
		pm.setMessage(promocionArticulo.getIdPromocion().getMensaje());
		pm.setMinimumValue(new CRBigDecimal(promocionArticulo.getIdPromocion().getValorMinimo()));
		pm.setName(promocionArticulo.getIdPromocion().getNombre());
		pm.setNumberOfTransactions(new CRBigDecimal(promocionArticulo.getIdPromocion().getCantidadTransacciones()));
		pm.setIsAcumulative(String.valueOf(promocionArticulo.getIdPromocion().getEsmultiple()));
		pm.setPaymentMethod(promPaymentMethodJPA.fromJPA(promocionArticulo.getIdPromocion()
				.getPromocionformadepagoList()));
		pm.setDiscountType(promDiscountTypeJPA.fromJPA(promocionArticulo.getIdPromocion()
				.getPromociontipodescuentoList()));
		pm.setGiftAmount(CRBigDecimal.valueOf(promocionArticulo.getCantidadRegalo()));
		if (pm.getActive() == true) {
			return pm;
		} else {
			return null;
		}

	}
}
