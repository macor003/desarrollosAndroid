/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Money;
import com.becoblohm.cr.models.PaymentMethod;
import com.becoblohm.cr.models.PaymentMethodDetail;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Formadepago;
import crjpa.Monedadenominacion;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class MonedadenominacionJpaController extends AbstractJPAController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Field emf.
	 */
	private final EntityManagerFactory emf;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Monedadenominacion";

	/**
	 * Constructor for MonedadenominacionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public MonedadenominacionJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.POSSOURCE, entityName);
		this.emf = emf;
	}

	public List<PaymentMethodDetail> findPaymentMethodDetailByFDP(long idFormadepago) {
		List<PaymentMethodDetail> result = new ArrayList<PaymentMethodDetail>();

		FormadepagoJpaController fdpController = new FormadepagoJpaController(emf);
		Formadepago fdp = fdpController.findFormadePagoById(idFormadepago);

		List<Monedadenominacion> fdpList = fdp.getMonedadenominacionList();

		for (Iterator iterator = fdpList.iterator(); iterator.hasNext();) {
			PaymentMethodDetail paymentMethodDetail = fromJPA((Monedadenominacion) iterator.next());
			result.add(paymentMethodDetail);
		}
		return result;

		// Formadepago formaDePago =fdpController.toJPA(paymentMethod);
		// EntityManager em = emf.createEntityManager();
		// Query query = em
		// .createQuery("SELECT m FROM Monedadenominacion m WHERE m.idFormadepago=
		// :idFormadepago");
		// query.setParameter("idFormadepago", formaDePago);
		//
		// try {
		//
		// List<Monedadenominacion> temp = query.getResultList();
		// for (Iterator iterator = temp.iterator(); iterator.hasNext();) {
		// PaymentMethodDetail paymentMethodDetail = fromJPA((Monedadenominacion)
		// iterator.next());
		// result.add(paymentMethodDetail);
		// }
		//
		// return result;
		// } catch (Exception e) {
		// e.printStackTrace();
		// return result;
		// } finally {
		// em.close();
		//
		// }
	}

	public PaymentMethodDetail fromJPA(Monedadenominacion monedaDenominacion) {

		MonedaJpaController monedaController = new MonedaJpaController(emf);

		PaymentMethodDetail paymentMethodDetail = new PaymentMethodDetail();

		paymentMethodDetail.setId(monedaDenominacion.getId());

		Money money = monedaController.fromJpa(monedaDenominacion.getIdMoneda());
		paymentMethodDetail.setIdMoney(money);

		paymentMethodDetail.setCode(monedaDenominacion.getCodigo());

		paymentMethodDetail.setName(monedaDenominacion.getNombre());

		paymentMethodDetail.setMultiple(new CRBigDecimal(monedaDenominacion.getMultiplo()));

		paymentMethodDetail.setIsActive(monedaDenominacion.getEstaactivo());

		paymentMethodDetail.setDate(monedaDenominacion.getFecha());

		paymentMethodDetail.setStore(monedaDenominacion.getTienda());

		paymentMethodDetail.setCommissionPercentage(new CRBigDecimal(monedaDenominacion.getPorcentajeComision()));

		paymentMethodDetail.setTaxPercentage(new CRBigDecimal(monedaDenominacion.getPorcentajeImpuesto()));

		paymentMethodDetail.setTaxesAccountingAccount(monedaDenominacion.getCuentaContableImpuesto());

		paymentMethodDetail.setAccountingAcount(monedaDenominacion.getCuentaContable());

		paymentMethodDetail.setExpensesAccountingAcount(monedaDenominacion.getCuentaContableGastos());

		paymentMethodDetail.setRecordState(monedaDenominacion.getEstadoRegistro());

		paymentMethodDetail.setAuxiliary(monedaDenominacion.getAuxiliar());

		FormadepagoJpaController formadpagoController = new FormadepagoJpaController(emf);
		PaymentMethod paymentMethod = formadpagoController.fromJPA(monedaDenominacion.getIdFormadepago());
		paymentMethodDetail.setIdPaymentMethod(paymentMethod);

		// if (!monedaDenominacion.getEntregaformadepagoList().isEmpty() ) {
		// }

		return paymentMethodDetail;

	}
}
