/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.epa.cr.legacy;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.PaymentMethod;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Formadepago;
import crjpa400.Formadepagomoneda;
import crjpa400.Moneda;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class FormadepagoJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private final EntityManagerFactory emf;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Formadepago";

	/**
	 * Constructor for FormadepagoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public FormadepagoJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * Field imgPath. (value is ""com/becoblohm/cr/init/resources/"")
	 */
	private static final String imgPath = "com/becoblohm/cr/init/resources/";

	/*
	 * Metodos propios implementados
	 */

	/**
	 * Method findFormadepagoEntitiesOwn.
	 * 
	 * @param cod
	 *            int
	 * @return List<Formadepago>
	 */
	private List<Formadepago> findFormadepagoEntitiesOwn(int cod) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Formadepago.findEntitiesOwm");
		query.setParameter("codigo", cod);
		List list = query.getResultList();
		return list;
	}

	/**
	 * Method findFormadepagoById.
	 * 
	 * @param idFormadePago
	 *            long
	 * @param IdMoneda
	 *            long
	 * @return PaymentMethod
	 */
	public PaymentMethod findFormadepagoById(long idFormadePago, long IdMoneda) {

		System.out.println("Buscando idFormadePago: " + idFormadePago + " ---- IdMoneda: " + IdMoneda);
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Formadepago.findById");
		query.setParameter("id", idFormadePago);

		EntityManager emMoney = emf.createEntityManager();
		Query queryMoney = emMoney.createNamedQuery("Moneda.findById");
		queryMoney.setParameter("id", IdMoneda);

		try {
			return fromJPA((Formadepago) query.getSingleResult(), (Moneda) queryMoney.getSingleResult());
		} finally {
			em.close();
		}
	}

	/**
	 * Method findFormadepagoByCode.
	 * 
	 * @param codeFormadePago
	 *            long
	 * @param IdMoneda
	 *            long
	 * @return PaymentMethod
	 */
	@Deprecated
	public PaymentMethod findFormadepagoByCode(long codeFormadePago, long IdMoneda) {

		System.out.println("Buscando idFormadePago: " + codeFormadePago + " ---- IdMoneda: " + IdMoneda);
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Formadepago.findByCodigo");
		query.setParameter("codigo", codeFormadePago);

		EntityManager emMoney = emf.createEntityManager();
		Query queryMoney = emMoney.createNamedQuery("Moneda.findById");
		queryMoney.setParameter("id", IdMoneda);

		try {
			return fromJPA((Formadepago) query.getSingleResult(), (Moneda) queryMoney.getSingleResult());
		} finally {
			em.close();
		}
	}

	/**
	 * Method findFormadepagoByIdJpa.
	 * 
	 * @param l
	 *            long
	 * @return Formadepago
	 */
	public Formadepago findFormadepagoByIdJpa(long l) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Formadepago.findById");
		query.setParameter("id", l);
		try {
			return (Formadepago) query.getSingleResult();
		} finally {
			em.close();
		}
	}

	/**
	 * Method findFormadepago.
	 * 
	 * @param value
	 *            String
	 * @return Formadepago
	 */
	public Formadepago findFormadepago(String value) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Formadepago.findByNombre");
		query.setParameter("nombre", value);
		try {
			return (Formadepago) query.getSingleResult();
		} finally {
			if (em != null) {
				em.clear();
				em.close();
			}
		}
	}

	/**
	 * Method findFormadepagoByStatus.
	 * 
	 * @param status
	 *            String
	 * @return List<Formadepago>
	 */
	public List<Formadepago> findFormadepagoByStatus(String status) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Formadepago.findByEstaactivo");
		query.setParameter("estaactivo", status);
		try {
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param jpaPaymentMethod
	 *            Formadepago
	 * @param jpaMoney
	 *            Moneda
	 * @return PaymentMethod
	 */
	public static PaymentMethod fromJPA(Formadepago jpaPaymentMethod, Moneda jpaMoney) {

		PaymentMethod paymentMethod;

		if (jpaMoney != null) {
			paymentMethod = new PaymentMethod(jpaPaymentMethod.getCodigo(), jpaPaymentMethod.getNombre() + " "
					+ jpaMoney.getSimbolo(), jpaPaymentMethod.getDescripcion() + " en " + jpaMoney.getSimbolo(),
					jpaPaymentMethod.getTipoFormaDePago());

			paymentMethod.setMoneySymbol(jpaMoney.getSimbolo());
			paymentMethod.setMoneyId(jpaMoney.getId());
			paymentMethod.setMoneyIcon(imgPath + jpaMoney.getIconoMoneda());
			paymentMethod.setMoneySymbol(jpaMoney.getSimbolo());
		} else {
			paymentMethod = new PaymentMethod(jpaPaymentMethod.getCodigo(), jpaPaymentMethod.getNombre(),
					jpaPaymentMethod.getDescripcion(), jpaPaymentMethod.getTipoFormaDePago());
		}

		paymentMethod.setReverseAccept(ActiveValues.get(String.valueOf(jpaPaymentMethod.getPermiteReverso()))
				.getValue());

		paymentMethod.setId(jpaPaymentMethod.getId());

		paymentMethod.setAllowsChange(ActiveValues.get(String.valueOf(jpaPaymentMethod.getPermiteVuelto())).getValue());
		paymentMethod.setActive(ActiveValues.get(String.valueOf(jpaPaymentMethod.getEstaactivo())).getValue());
		if (jpaPaymentMethod.getMontoMinimo() != null)
			paymentMethod.setMinAmount(new CRBigDecimal(jpaPaymentMethod.getMontoMinimo().doubleValue()));
		else
			paymentMethod.setMinAmount(CRBigDecimal.ZERO);

		if (jpaPaymentMethod.getMontoMaximo() != null)
			paymentMethod.setMaxAmount(new CRBigDecimal(jpaPaymentMethod.getMontoMaximo().doubleValue()));
		else
			paymentMethod.setMinAmount(CRBigDecimal.ZERO);

		PaymentMethod paymentMethodTmp;

		if (jpaMoney != null) {
			paymentMethodTmp = new PaymentMethod(jpaPaymentMethod.getIdContrapartida().getCodigo(), jpaPaymentMethod
					.getIdContrapartida().getNombre() + " " + jpaMoney.getSimbolo(), jpaPaymentMethod
					.getIdContrapartida().getDescripcion() + " en " + jpaMoney.getSimbolo(), jpaPaymentMethod
					.getIdContrapartida().getTipoFormaDePago());

			paymentMethodTmp.setMoneySymbol(jpaMoney.getSimbolo());
			paymentMethodTmp.setMoneyId(jpaMoney.getId());
			paymentMethodTmp.setMoneyIcon(imgPath + jpaMoney.getIconoMoneda());
			paymentMethodTmp.setMoneySymbol(jpaMoney.getSimbolo());
		} else {
			paymentMethodTmp = new PaymentMethod(jpaPaymentMethod.getIdContrapartida().getCodigo(), jpaPaymentMethod
					.getIdContrapartida().getNombre(), jpaPaymentMethod.getIdContrapartida().getDescripcion(),
					jpaPaymentMethod.getIdContrapartida().getTipoFormaDePago());
		}

		paymentMethodTmp.setReverseAccept(ActiveValues.get(
				String.valueOf(jpaPaymentMethod.getIdContrapartida().getPermiteReverso())).getValue());

		paymentMethodTmp.setId(jpaPaymentMethod.getIdContrapartida().getId());

		paymentMethodTmp.setAllowsChange(ActiveValues.get(
				String.valueOf(jpaPaymentMethod.getIdContrapartida().getPermiteVuelto())).getValue());
		paymentMethodTmp.setActive(ActiveValues.get(
				String.valueOf(jpaPaymentMethod.getIdContrapartida().getEstaactivo())).getValue());
		if (jpaPaymentMethod.getIdContrapartida().getMontoMinimo() != null)
			paymentMethodTmp.setMinAmount(new CRBigDecimal(jpaPaymentMethod.getIdContrapartida().getMontoMinimo()
					.doubleValue()));
		else
			paymentMethodTmp.setMinAmount(CRBigDecimal.ZERO);

		if (jpaPaymentMethod.getIdContrapartida().getMontoMaximo() != null){
			paymentMethodTmp.setMaxAmount(new CRBigDecimal(jpaPaymentMethod.getIdContrapartida().getMontoMaximo()
					.doubleValue()));
		}
		else{
			paymentMethodTmp.setMinAmount(CRBigDecimal.ZERO);
		}
		paymentMethodTmp.setCounterPart(paymentMethod);
		paymentMethod.setCounterPart(paymentMethodTmp);

		return paymentMethod;
	}

	/**
	 * Method getPaymentMethodList.
	 * 
	 * @return Map<Integer,PaymentMethod>
	 */
	public Map<Integer, PaymentMethod> getPaymentMethodList() {

		EntityManagerFactory emf = this.emf;
		crjpa400.FormadepagoJpaController jpacontroller = new crjpa400.FormadepagoJpaController(emf);
		FormadepagomonedaJpaController fdpMonedaJpa = new FormadepagomonedaJpaController(emf);

		List<Formadepagomoneda> fdpMonedaList = fdpMonedaJpa.findFormadepagoMonedaEntitiesByStatus("S");
		TreeMap<Integer, PaymentMethod> payMethodList = new TreeMap<Integer, PaymentMethod>();

		List<Formadepago> tmpFormadepago = jpacontroller.findFormadepagoEntities();

		for (Formadepago jpaPaymentMethod : tmpFormadepago) {
			/*
			 * Voy a buscar en la tabla formadepago_moneda para ver si existe
			 * mas de una moneda para la FDP
			 */
			for (Formadepagomoneda money : jpaPaymentMethod.getFormadepagomonedaList()) {
				String estaactivo = String.valueOf(money.getEstaactivo());
				if (estaactivo.equals("S")) {
					PaymentMethod paymentMethod = fromJPA(jpaPaymentMethod, money.getIdMoneda());
					paymentMethod.setPriority(money.getPrioridadFormaDePago());
					paymentMethod.setPaymentIcon(imgPath + jpaPaymentMethod.getIconoFormaDePago());
					paymentMethod.setCardType(String.valueOf(jpaPaymentMethod.getTipoTarjeta()));
					paymentMethod.setReverseAccept(ActiveValues.get(
							String.valueOf(jpaPaymentMethod.getPermiteReverso())).getValue());
					payMethodList.put(paymentMethod.getPriority(), paymentMethod);
				}
			}
		}

		return payMethodList;
	}

}
