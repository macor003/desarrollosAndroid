/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.crjpa400.controller.exceptions.NonexistentEntityException;
import com.becoblohm.cr.crjpa400.controller.exceptions.PreexistingEntityException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Deposit;
import com.becoblohm.cr.models.Money;
import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.models.Payment;
import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Abonoformadepago;
import crjpa400.Formadepago;
import crjpa400.Moneda;
import crjpa400.MonedaJpaController;

/**
 */
public class AbonoFormadepagoJpaController extends AbstractJPAController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6427799694686174774L;
	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;
	/**
	 * Field jpaController.
	 */
	private crjpa400.AbonoformadepagoJpaController jpaController;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Abonoformadepago";

	/**
	 * Constructor for AbonoFormadepagoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public AbonoFormadepagoJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
		this.jpaController = new crjpa400.AbonoformadepagoJpaController(emf);

	}

	/**
	 * Method fromJPA.
	 * 
	 * @param abonoFormaDePago
	 *            Abonoformadepago
	 * @return Payment
	 */
	public Payment fromJPA(Abonoformadepago abonoFormaDePago) {
		Payment payment = new Payment();
		payment.setDonation(CRBigDecimal.ZERO);
		Money money = new Money();
		money.setExchangeRate(CRBigDecimal.ONE);// TODO crear columna en tabla
												// transaccionformadepago
		money.setCurrencyAmmount(new CRBigDecimal(abonoFormaDePago.getMontoMoneda().doubleValue()));
		money.setLocalAmmount(new CRBigDecimal(abonoFormaDePago.getMontoMonedaLocal().doubleValue()));
		money.setCurrencyId(abonoFormaDePago.getIdMoneda().getId());
		money.setName(abonoFormaDePago.getIdMoneda().getNombre());
		payment.setMoney(money);
		payment.setPayMethod(FormadepagoJpaController.fromJPA(abonoFormaDePago.getIdFormadepago(),
				abonoFormaDePago.getIdMoneda()));
		payment.setActive(true);

		return payment;
	}

	/**
	 * Method toJpa.
	 * 
	 * @param orden
	 *            Order
	 * @param session
	 *            Session
	 * @param payment
	 *            Payment
	 * @param deposit
	 *            Deposit
	 * @return Abonoformadepago
	 */
	protected Abonoformadepago toJpa(Order orden, Session session, Payment payment, Deposit deposit) {
		Abonoformadepago abonoFormaDepago = new Abonoformadepago();
		abonoFormaDepago.setConformacion(payment.getConformationNumber());
		abonoFormaDepago.setCuenta(payment.getAccountNumber());
		abonoFormaDepago.setDocumento(payment.getDocumentNumber());

		AbonoJpaController abonoJpaController = new AbonoJpaController(this.emf);

		abonoFormaDepago.setIdAbono(abonoJpaController.findAbonoJpa(deposit.getId()));

		FormadepagoJpaController formaDePagoJpa = new FormadepagoJpaController(this.emf);
		Formadepago formadepago = formaDePagoJpa.findFormadepagoByIdJpa(payment.getPayMethod().getId());
		abonoFormaDepago.setIdFormadepago(formadepago);

		MonedaJpaController monedaJpa = new MonedaJpaController(this.emf);
		Moneda moneda = monedaJpa.findMoneda(payment.getMoney().getCurrencyId());
		abonoFormaDepago.setIdMoneda(moneda);

		abonoFormaDepago.setItem(payment.getItem());
		abonoFormaDepago.setMontoMoneda(payment.getMoney().getCurrencyAmmount().getValue());
		abonoFormaDepago.setMontoMonedaLocal(payment.getMoney().getLocalAmmount().getValue());
		abonoFormaDepago.setTitular(payment.getCardHolder());
		return abonoFormaDepago;
	}

	/**
	 * Method create.
	 * 
	 * @param order
	 *            Order
	 * @param session
	 *            Session
	 * @param payment
	 *            Payment
	 * @param deposit
	 *            Deposit
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Order order, Session session, Payment payment, Deposit deposit)
			throws PreexistingEntityException, Exception {
		AbonoFormadepagoJpaController abonoFormaDePagoJpa = new AbonoFormadepagoJpaController(this.emf);
		Abonoformadepago abonoFormaDePago = abonoFormaDePagoJpa.toJpa(order, session, payment, deposit);
		jpaController.create(abonoFormaDePago);
	}

	/**
	 * Method edit.
	 * 
	 * @param obj
	 *            Abonoformadepago
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Abonoformadepago obj) throws NonexistentEntityException, Exception {
		jpaController.edit(obj);
	}
}
