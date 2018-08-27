/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CreditsMovement;
import com.becoblohm.cr.models.Money;
import com.becoblohm.cr.models.PayMethodCreditsMovement;
import com.becoblohm.cr.models.Payment;
import com.becoblohm.cr.models.PaymentMethod;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Formadepago;
import crjpa400.Formadepagomovacreencia;
import crjpa400.Moneda;

/**
 */
public class FormadepagomovacreenciaJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private static EntityManagerFactory emf;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Formadepagomovacreencia";

	/**
	 * Constructor for FormadepagomovacreenciaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public FormadepagomovacreenciaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param payment
	 *            Formadepagomovacreencia
	 * @return Payment
	 */
	public Payment fromJPA(Formadepagomovacreencia payment) {
		MonedaJpaController monedaJpa = new MonedaJpaController(emf);
		Payment pay = new Payment();
		Money money = monedaJpa.findDefaultMoney(payment.getIdMoneda().getId());
		money.setCurrencyId(payment.getIdMoneda().getId());
		money.setLocalAmmount(new CRBigDecimal(payment.getMonto().doubleValue()));
		// money.setCurrencyAmmount(new
		// CRBigDecimal(payment.getMonto().doubleValue()));

		// Money moneyCR =
		// monedaJpa.findDefaultMoney(payment.getIdMoneda().getId());
		// money.setExchangeRate(new
		// CRBigDecimal(moneyCR.getExchangeRate().doubleValue(),3));

		pay.setMoney(money);
		PaymentMethod fdp = new PaymentMethod();

		fdp.setId(payment.getIdFormadepago().getId());
		fdp.setPaymentType(payment.getIdFormadepago().getTipoFormaDePago());
		fdp.setCode(payment.getIdFormadepago().getCodigo());
		pay.setPayMethod(fdp);
		if (payment.getEstaactivo() == 'S') {
			pay.setDone(Payment.PAY_DONE);
		} else {
			pay.setDone(Payment.PAY_FAIL);
		}
		pay.setCardHolder(payment.getTitular());
		return pay;
	}

	/**
	 * Method toJPA.
	 * 
	 * @param type
	 *            Payment
	 * @return Formadepagomovacreencia
	 */
	protected static Formadepagomovacreencia toJPA(Payment type) {
		Formadepagomovacreencia fdp = new Formadepagomovacreencia();

		fdp.setConformacion(type.getConformationNumber());
		fdp.setCuenta(type.getAccountNumber());
		fdp.setDocumento(type.getDocumentNumber());
		fdp.setTitular(type.getCardHolder());
		if (type.getDone() == Payment.PAY_DONE) {
			fdp.setEstaactivo(new Character('S'));
		} else {
			fdp.setEstaactivo(new Character('N'));
		}

		fdp.setFecha(new Date());

		fdp.setIdMoneda(new Moneda(type.getMoney().getCurrencyId()));
		fdp.setMonto(type.getMoney().getLocalAmmount().getValue());
		fdp.setMontoMoneda(type.getMoney().getCurrencyAmmount().getValue());
		fdp.setTitular(type.getCardHolder());
		fdp.setIdFormadepago(new Formadepago(type.getPayMethod().getId()));

		return fdp;
	}

	// TODO modificacion para acreencias

	/**
	 * Method toPayMethodCreditsMovement.
	 * 
	 * @param type
	 *            Payment
	 * @param mov
	 *            CreditsMovement
	 * @return PayMethodCreditsMovement
	 */
	public static PayMethodCreditsMovement toPayMethodCreditsMovement(Payment type, CreditsMovement mov) {
		PayMethodCreditsMovement fdp = new PayMethodCreditsMovement();

		List<Formadepagomovacreencia> fdpList = new ArrayList<Formadepagomovacreencia>();
		long idmovimiento = mov.getId();
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em
					.createQuery("SELECT f FROM Formadepagomovacreencia f WHERE f.idmovimiento.id = :idmovimiento");
			query.setParameter("idmovimiento", idmovimiento);
			fdpList = (List<Formadepagomovacreencia>) query.getResultList();
		} catch (Exception ex) {
			fdpList = null;
			ex.printStackTrace();
		} finally {
			em.close();
		}
		return (PayMethodCreditsMovement) fdpList;

	}

	/**
	 * Method findFormadePagoMovacreenciaByIdMovimiento.
	 * 
	 * @param idmovimiento
	 *            long
	 * @return List<Formadepagomovacreencia>
	 */
	public List<Formadepagomovacreencia> findFormadePagoMovacreenciaByIdMovimiento(long idmovimiento) {

		List<Formadepagomovacreencia> fdpList = new ArrayList<Formadepagomovacreencia>();

		EntityManager em = emf.createEntityManager();
		try {
			Query query = em
					.createQuery("SELECT f FROM Formadepagomovacreencia f WHERE f.idMovimientoacreencia.id = :idmovimiento");
			query.setParameter("idmovimiento", idmovimiento);
			fdpList = (List<Formadepagomovacreencia>) query.getResultList();
		} catch (Exception ex) {
			fdpList = null;
			ex.printStackTrace();
		} finally {
			em.close();
		}

		return fdpList;

	}

}
