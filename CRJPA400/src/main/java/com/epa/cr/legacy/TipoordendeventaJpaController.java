/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.OrderType;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Origenordendeventa;
import crjpa400.Tipoordendeventa;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TipoordendeventaJpaController extends AbstractJPAController {

	/**
	 * Field serialVersionUID. (value is 8228354676630127907)
	 */
	private static final long serialVersionUID = 8228354676630127907L;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Tipoordendeventa";
	/**
	 * Field jpaController.
	 */
	private crjpa400.TipoordendeventaJpaController jpaController;
	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;

	/**
	 * Constructor for TipoordendeventaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TipoordendeventaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
		jpaController = new crjpa400.TipoordendeventaJpaController(this.emf);
	}

	/**
	 * Method findTipoordendeventaByIdJPA.
	 * 
	 * @param id
	 *            long
	 * @return Tipoordendeventa
	 */
	public Tipoordendeventa findTipoordendeventaByIdJPA(long id) {
		return jpaController.findTipoordendeventa(id);
	}

	/**
	 * Method toJpa.
	 * 
	 * @param orderType
	 *            OrderType
	 * @return Tipoordendeventa
	 */
	public Tipoordendeventa toJpa(OrderType orderType) {
		Tipoordendeventa tipoOrdenDeVenta = new Tipoordendeventa();
		tipoOrdenDeVenta.setId(orderType.getId());
		tipoOrdenDeVenta.setPuedeCambiarCantidadArticulos(ActiveValues.get(orderType.isCanChangeQuantityArticles())
				.getString().charAt(0));
		tipoOrdenDeVenta.setImprimirNumeroOrdenFactura(ActiveValues.get(orderType.isPrintOrderNumberInBill())
				.getString().charAt(0));
		tipoOrdenDeVenta
				.setPermiteRebajaPorCaja(ActiveValues.get(orderType.isCanDoRebateInPos()).getString().charAt(0));
		tipoOrdenDeVenta.setPermiteAgregarNuevosArticulos(ActiveValues.get(orderType.isCanAddArticlesItems())
				.getString().charAt(0));
		tipoOrdenDeVenta.setBuscarMejorPrecioEnCaja(ActiveValues.get(orderType.isFindBetterCostInPos()).getString()
				.charAt(0));
		tipoOrdenDeVenta.setOrigenFDPAbonos(ActiveValues.get(orderType.isOriginFDPDeposit()).getString().charAt(0));
		tipoOrdenDeVenta.setOrigenFDPDefecto(ActiveValues.get(orderType.isOriginFDPDefault()).getString().charAt(0));
		tipoOrdenDeVenta.setOrigenFDPCaja(ActiveValues.get(orderType.isOriginFDPPos()).getString().charAt(0));
		//tipoOrdenDeVenta.setIdOrigenordendeventa(new Origenordendeventa((long) Integer.parseInt(orderType.getOrigin().getValue())));
		tipoOrdenDeVenta.setIdOrigenordendeventa((long) Integer.parseInt(orderType.getOrigin().getValue()));

		return tipoOrdenDeVenta;
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param tmp
	 *            Tipoordendeventa
	 * @return OrderType
	 */
	public OrderType fromJPA(Tipoordendeventa tmp) {
		OrderType orderType = new OrderType();

		orderType.setId(tmp.getId());
		orderType.setDescription(tmp.getDescripcion());
		orderType.setCanChangeQuantityArticles(ActiveValues.get(String.valueOf(tmp.getPuedeCambiarCantidadArticulos()))
				.getValue());
		orderType.setPrintOrderNumberInBill(ActiveValues.get(String.valueOf(tmp.getImprimirNumeroOrdenFactura()))
				.getValue());
		orderType.setCanDoRebateInPos(ActiveValues.get(String.valueOf(tmp.getPermiteRebajaPorCaja())).getValue());
		orderType.setCanAddArticlesItems(ActiveValues.get(String.valueOf(tmp.getPermiteAgregarNuevosArticulos()))
				.getValue());
		orderType.setFindBetterCostInPos(ActiveValues.get(String.valueOf(tmp.getBuscarMejorPrecioEnCaja())).getValue());
		orderType.setOriginFDPDeposit(ActiveValues.get(String.valueOf(tmp.getOrigenFDPAbonos())).getValue());
		orderType.setOriginFDPDefault(ActiveValues.get(String.valueOf(tmp.getOrigenFDPDefecto())).getValue());
		orderType.setOriginFDPPos(ActiveValues.get(String.valueOf(tmp.getOrigenFDPCaja())).getValue());
		//orderType.setOrigin(OrderType.Source.get(String.valueOf(tmp.getIdOrigenordendeventa().getId())));
		orderType.setOrigin(OrderType.Source.get(String.valueOf(tmp.getIdOrigenordendeventa())));

		return orderType;
	}

}
