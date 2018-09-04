/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.util.List;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.PromotionsMethod;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Promocioncategoria;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class PromocioncategoriaJpaController extends AbstractJPAController {

	/**
     * 
     */
	private static String entityName = "Promocioncategoria";
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field emf.
	 */
	EntityManagerFactory emf;

	/**
	 * @param p_emf
	 */
	public PromocioncategoriaJpaController(EntityManagerFactory p_emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * Method findPromocionesCategoria.
	 * 
	 * @param idCat
	 *            long
	 * @param idPromoc
	 *            long
	 * @return List<Promocioncategoria>
	 */
	public List<Promocioncategoria> findPromocionesCategoria(long idCat, long idPromoc) {
		List<Promocioncategoria> result = null;
		EntityManager em = this.emf.createEntityManager();
		Query query = em
				.createQuery("SELECT c FROM PromocionCategoria c WHERE c.idCategoria = :idCat and c.idPromocion  = idPromoc");
		query.setParameter("idCategoria", idCat);
		query.setParameter("idPromocion", idPromoc);
		try {
			result = query.getResultList();
			return result;
		} finally {
			em.close();
		}
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param promocionCategorias
	 *            List<Promocioncategoria>
	 * @return TreeMap<CRBigDecimal,PromotionsMethod>
	 */
	public TreeMap<CRBigDecimal, PromotionsMethod> fromJPA(List<Promocioncategoria> promocionCategorias) {

		TreeMap<CRBigDecimal, PromotionsMethod> arrayPomotion = new TreeMap<CRBigDecimal, PromotionsMethod>();
		for (Promocioncategoria pa : promocionCategorias) {
			if (ActiveValues.get(String.valueOf(pa.getIdPromocion().getEstaactivo())).getValue()) {
				PromotionsMethod pmTmp = this.fromJPA(pa);
				if (pmTmp != null) {
					arrayPomotion.put(pmTmp.getDiscountAmount(), pmTmp);
				}
			}
		}

		return arrayPomotion;

	}

	/**
	 * Method fromJPA.
	 * 
	 * @param promocionCategoria
	 *            Promocioncategoria
	 * @return PromotionsMethod
	 */
	public PromotionsMethod fromJPA(Promocioncategoria promocionCategoria) {

		PromotionsMethod pm = new PromotionsMethod();
		PromocionformadepagoJpaController promPaymentMethodJPA = new PromocionformadepagoJpaController(emf);
		PromociontipodescuentoJpaController promRebateTypeJPA = new PromociontipodescuentoJpaController(emf);

		pm.setActive(ActiveValues.get(String.valueOf(promocionCategoria.getEstaactivo())).getValue());
		pm.setCod(promocionCategoria.getIdPromocion().getCodigo());
		pm.setDiscountAmount(new CRBigDecimal(promocionCategoria.getPorcentajeDescuento().doubleValue()));
		pm.setDiscountRate(new CRBigDecimal(promocionCategoria.getPorcentajeDescuento().doubleValue()));
		pm.setEndDate(promocionCategoria.getIdPromocion().getFechaFin());
		pm.setId(promocionCategoria.getId());
		pm.setInitDate(promocionCategoria.getIdPromocion().getFechaInicio());
		pm.setMaximumValue(new CRBigDecimal(promocionCategoria.getIdPromocion().getValorMaximo()));
		pm.setMessage(promocionCategoria.getIdPromocion().getMensaje());
		pm.setMinimumValue(new CRBigDecimal(promocionCategoria.getIdPromocion().getValorMinimo()));
		pm.setName(promocionCategoria.getIdPromocion().getNombre());
		pm.setNumberOfTransactions(new CRBigDecimal(promocionCategoria.getIdPromocion().getCantidadTransacciones()));
		pm.setIsAcumulative(String.valueOf(promocionCategoria.getIdPromocion().getEsmultiple()));
		pm.setPaymentMethod(promPaymentMethodJPA.fromJPA(promocionCategoria.getIdPromocion()
				.getPromocionformadepagoList()));

		pm.setDiscountType(promRebateTypeJPA.fromJPA(promocionCategoria.getIdPromocion()
				.getPromociontipodescuentoList()));

		if (pm.getActive() == true) {
			return pm;
		} else
			return null;

	}

}
