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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.StatusOrder;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Estadoordendeventa;

/**
 * 
 * @author Marco Nuñez (analista12)
 * 
 * @version $Revision: 1.0 $
 */
public class EstadoordendeventaJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Estadoordendeventa";
	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;

	/**
	 * Constructor for EstadoordendeventaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public EstadoordendeventaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * Method create.
	 * 
	 * @param statusOrder
	 *            StatusOrder
	 * @throws JpaException
	 */
	public void create(StatusOrder statusOrder) throws JpaException {
		Estadoordendeventa estadoordendeventa = toJPA(statusOrder);
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(estadoordendeventa);
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
		statusOrder.setId(estadoordendeventa.getId());
	}

	/**
	 * Method toJPA.
	 * 
	 * @param statusOrder
	 *            StatusOrder
	 * @return Estadoordendeventa
	 */
	public Estadoordendeventa toJPA(StatusOrder statusOrder) {
		Estadoordendeventa estadoordendeventa = new Estadoordendeventa();

		if (statusOrder.getId() > 0) {
			estadoordendeventa.setId(statusOrder.getId());
		} else {
			estadoordendeventa.setId(null);
		}

		estadoordendeventa.setDescripcion(statusOrder.getDescription());
		estadoordendeventa.setOrdenVisible(ActiveValues.get(statusOrder.isOrderVisible()).getString().charAt(0));
		estadoordendeventa.setRecibeAbonos(ActiveValues.get(statusOrder.isReceivesDeposits()).getString().charAt(0));
		estadoordendeventa.setRecibeAnulacionAbonos(ActiveValues.get(statusOrder.isReceivesCancellationDeposits())
				.getString().charAt(0));
		estadoordendeventa.setOrdenPuedeAnularse(ActiveValues.get(statusOrder.isOrderCanBeCancelled()).getString()
				.charAt(0));
		estadoordendeventa.setOrdenPuedeFacturarse(ActiveValues.get(statusOrder.isOrderCanBeBilled()).getString()
				.charAt(0));
		estadoordendeventa.setPermiteRetencionImp(ActiveValues.get(statusOrder.isTaxRetentionPermited()).getString()
				.charAt(0));
		estadoordendeventa.setPermiteCambioCondicionEntrega(ActiveValues
				.get(statusOrder.isChangeDeliveryConditionPermited()).getString().charAt(0));
		estadoordendeventa.setDescripcionCorta(statusOrder.getShortDescription());

		return estadoordendeventa;
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param tmp
	 *            Estadoordendeventa
	 * @return StatusOrder
	 */
	public StatusOrder fromJPA(Estadoordendeventa tmp) {
		StatusOrder statusOrder = new StatusOrder();

		statusOrder.setId(tmp.getId());
		statusOrder.setDescription(tmp.getDescripcion());
		statusOrder.setOrderVisible(ActiveValues.get(String.valueOf(tmp.getOrdenVisible())).getValue());
		statusOrder.setReceivesDeposits(ActiveValues.get(String.valueOf(tmp.getRecibeAbonos())).getValue());
		statusOrder.setReceivesCancellationDeposits(ActiveValues.get(String.valueOf(tmp.getRecibeAnulacionAbonos()))
				.getValue());
		statusOrder.setOrderCanBeCancelled(ActiveValues.get(String.valueOf(tmp.getOrdenPuedeAnularse())).getValue());
		statusOrder.setOrderCanBeBilled(ActiveValues.get(String.valueOf(tmp.getOrdenPuedeFacturarse())).getValue());
		statusOrder.setTaxRetentionPermited(ActiveValues.get(String.valueOf(tmp.getPermiteRetencionImp())).getValue());
		statusOrder.setChangeDeliveryConditionPermited(ActiveValues.get(
				String.valueOf(tmp.getPermiteCambioCondicionEntrega())).getValue());
		statusOrder.setShortDescription(tmp.getDescripcionCorta());
		return statusOrder;
	}

	/**
	 * Method edit.
	 * 
	 * @param statusOrder
	 *            StatusOrder
	 * @throws JpaException
	 */
	public void edit(StatusOrder statusOrder) throws JpaException {
		Estadoordendeventa estadoordendeventa = new Estadoordendeventa();
		estadoordendeventa = toJPA(statusOrder);
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(estadoordendeventa);
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

	/**
	 * Method toJPA.
	 * 
	 * @param statusOrder
	 *            StatusOrder
	 * @param fromUpdateStatus
	 *            boolean
	 * @return Estadoordendeventa
	 */
	public Estadoordendeventa toJPA(StatusOrder statusOrder, boolean fromUpdateStatus) {
		Estadoordendeventa estadoordendeventa = new Estadoordendeventa();

		if (statusOrder.getId() > 0) {
			estadoordendeventa.setId(statusOrder.getId());
		} else {
			estadoordendeventa.setId(null);
		}

		return estadoordendeventa;
	}

	/**
	 * Method findEstadosOrdendeVentaEntities.
	 * 
	 * @return List<StatusOrder>
	 */
	public List<StatusOrder> findEstadosOrdendeVentaEntities() {

		List<crjpa400.Estadoordendeventa> listResult = null;
		List<StatusOrder> result = null;
		EntityManager em = this.emf.createEntityManager();
		Query query = em.createNamedQuery("Estadoordendeventa.findAll");

		try {
			listResult = query.getResultList();
			result = new ArrayList<StatusOrder>();
			for (Estadoordendeventa estadoordendeventa : listResult) {
				result.add(fromJPA(estadoordendeventa));
			}
			return result;
		} catch (NoResultException ex) {
			return null;
		} finally {
			em.clear();
			em.close();
		}
	}

}
