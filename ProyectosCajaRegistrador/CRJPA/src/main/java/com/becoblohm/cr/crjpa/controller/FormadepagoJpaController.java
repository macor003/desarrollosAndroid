/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CommitmentCurrency;
import com.becoblohm.cr.models.CommitmentCurrencyDetails;
import com.becoblohm.cr.models.Money;
import com.becoblohm.cr.models.PaymentMethod;
import com.becoblohm.cr.models.PaymentMethodDetail;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Formadepago;
import crjpa.Formadepagomoneda;
import crjpa.Moneda;
import crjpa.Monedadenominacion;

/**
 * @version $Revision: 1.0 $
 */
public class FormadepagoJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private final EntityManagerFactory emf;

	/**
	 * Field jpacontroller.
	 */
	private final crjpa.FormadepagoJpaController jpacontroller;

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
		super(AbstractJPAController.POSSOURCE, entityName);
		this.emf = emf;
		this.jpacontroller = new crjpa.FormadepagoJpaController(emf);
	}

	/**
	 * Field imgPath. (value is ""com/becoblohm/cr/init/resources/"")
	 */
	private static final String imgPath = "com/becoblohm/cr/init/resources/";

	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Metodos propios implementados
	 */

	/**
	 * Method findFormadepagoById.
	 * 
	 * @param cod
	 *            long
	 * @return PaymentMethod
	 */
	public PaymentMethod findFormadepagoById(long cod) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Formadepago.findById");
		query.setParameter("id", cod);
		try {
			return fromJPA((Formadepago) query.getSingleResult());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Method findFormadepagoByCode.
	 * 
	 * @param cod
	 *            long
	 * @return PaymentMethod
	 */
	@Deprecated
	public PaymentMethod findFormadepagoByCode(long cod) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Formadepago.findByCodigo");
		query.setParameter("codigo", cod);
		try {
			return fromJPA((Formadepago) query.getSingleResult());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Method findFormadepagoByCodeActive.
	 * 
	 * @param cod
	 *            long
	 * @return PaymentMethod
	 */
	public PaymentMethod findFormadepagoByCodeActive(long cod) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT f FROM Formadepago f WHERE f.codigo = :codigo");
		query.setParameter("codigo", cod);
		try {
			return fromJPA((Formadepago) query.getSingleResult());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Method findFormadepagoByIdActive.
	 * 
	 * @param id
	 *            long
	 * @return PaymentMethod
	 */
	public PaymentMethod findFormadepagoByIdActive(long id) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT f FROM Formadepago f WHERE f.id = :id AND f.estaactivo = :estaactivo");
		query.setParameter("id", id);
		query.setParameter("estaactivo", "S");
		try {
			return fromJPA((Formadepago) query.getSingleResult());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Method findFormadePagoById.
	 * 
	 * @param fdpId
	 *            long
	 * @return Formadepago
	 */
	public Formadepago findFormadePagoById(long fdpId) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Formadepago.findById");
		query.setParameter("id", fdpId);
		try {
			return (Formadepago) query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		} finally {
			em.close();
		}

	}

	/**
	 * Method findFormadepagoByStatus.
	 * 
	 * @param status
	 *            String
	 * @return List<PaymentMethod>
	 */
	public List<PaymentMethod> findFormadepagoByStatus(String status) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Formadepago.findByEstaactivo");
		query.setParameter("estaactivo", status);
		try {
			List<PaymentMethod> result = new ArrayList<PaymentMethod>();
			List<PaymentMethod> tmp = query.getResultList();
			for (Iterator iterator = tmp.iterator(); iterator.hasNext();) {
				PaymentMethod object = (PaymentMethod) iterator.next();
				result.add(object);
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	public Formadepago toJPA(PaymentMethod paymentMethod) {

		Formadepago formaDePago = new Formadepago();

		formaDePago = fromModel(paymentMethod);

		formaDePago.setIdContrapartida(fromModel(paymentMethod.getCounterPart()));

		return formaDePago;

	}

	private Formadepago fromModel(PaymentMethod paymentMethod) {
		Formadepago formaDePago = new Formadepago();

		formaDePago.setCodigo((int) paymentMethod.getCode());
		formaDePago.setDescripcion(paymentMethod.getDescription());
		formaDePago.setTipoFormaDePago(paymentMethod.getPaymentType());
		formaDePago.setNombre(paymentMethod.getName());
		formaDePago.setPrioridadDonacion(paymentMethod.getDonationPriority());
		formaDePago.setIconoFormaDePago(paymentMethod.getPaymentIcon());
		formaDePago.setTipoTarjeta(paymentMethod.getCardType());

		if (paymentMethod.isReverseAccept()) {
			formaDePago.setPermiteReverso("S");
		} else
			formaDePago.setPermiteReverso("N");

		formaDePago.setId(paymentMethod.getId());

		if (paymentMethod.isActive()) {
			formaDePago.setEstaactivo("S");
		} else
			formaDePago.setEstaactivo("N");

		if (paymentMethod.isAllowsChange()) {
			formaDePago.setPermiteVuelto("S");
		} else
			formaDePago.setPermiteVuelto("N");

		if (paymentMethod.isAllowsClientChange()) {
			formaDePago.setPermiteReasignarCliente("S");
		} else
			formaDePago.setPermiteReasignarCliente("N");

		if (paymentMethod.allowsChange()) {
			formaDePago.setPermiteVuelto("S");
		} else
			formaDePago.setPermiteVuelto("N");

		if (paymentMethod.requiresAuthorization()) {
			formaDePago.setRequiereAutorizacion("S");
		} else
			formaDePago.setRequiereAutorizacion("N");

		if (!paymentMethod.getMinAmount().isZero()) {
			formaDePago.setMontoMinimo(paymentMethod.getMinAmount().getValue());
		}

		if (!paymentMethod.getMaxAmount().isZero()) {
			formaDePago.setMontoMaximo(paymentMethod.getMaxAmount().getValue());
		}

		return formaDePago;
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
	public PaymentMethod fromJPA(Formadepago jpaPaymentMethod, Moneda jpaMoney) {
		MonedatasacambioJpaController exchangecontroller = new MonedatasacambioJpaController(this.emf);
		// Monedatasacambio
		// exchange=exchangecontroller.findMonedaTasacambioEntities(jpaMoney.getId());

		PaymentMethod paymentMethod = fromJPA(jpaPaymentMethod);

		// jpaPaymentMethod.setNombre(jpaPaymentMethod.getNombre());//+"
		// "+jpaMoney.getSimbolo());
		jpaPaymentMethod.setDescripcion(jpaPaymentMethod.getDescripcion() + " en " + jpaMoney.getSimbolo());

		paymentMethod.setPaymentType(jpaPaymentMethod.getTipoFormaDePago());

		// PromocionFormadepagoJpaController promotionPayment = new
		// PromocionFormadepagoJpaController(emf);

		// paymentMethod.setDonationpriority(jpaPaymentMethod.getPrioridadDonacion());
		// paymentMethod.setPaymentIcon(imgPath+jpaPaymentMethod.getIconoFormaDePago());
		// paymentMethod.setCardType(jpaPaymentMethod.getTipoTarjeta());
		//
		// paymentMethod.setReverseAccept(ActiveValues.get(jpaPaymentMethod.getPermiteReverso()).getValue());
		// paymentMethod.setDonationpriority(jpaPaymentMethod.getPrioridadDonacion());
		// paymentMethod.setId(jpaPaymentMethod.getId());
		paymentMethod.setName(jpaPaymentMethod.getNombre());
		paymentMethod.setMoneySymbol(jpaMoney.getSimbolo());
		paymentMethod.setMoneyId(jpaMoney.getId());
		paymentMethod.setCardType(jpaPaymentMethod.getTipoTarjeta());
		// paymentMethod.setExchangeRate(new
		// CRBigDecimal(exchange.getCambio().doubleValue()));
		// paymentMethod.setActive(ActiveValues.get(jpaPaymentMethod.getEstaactivo()).getValue());
		// paymentMethod.setPaymentIcon(Images.getImgpath()+jpaPaymentMethod.getIconoFdp());
		paymentMethod.setMoneyIcon(imgPath + jpaMoney.getIconoMoneda());// Images.getImgpath()+jpaMoney.getIconoMoneda());
		// paymentMethod.setPm(promotionPayment.fromJPA(jpaPaymentMethod.getPromocionFormadepagoList()));
		// paymentMethod.setAllowsChange(ActiveValues.get(jpaPaymentMethod.getPermiteVuelto()).getValue());
		//
		// if(jpaPaymentMethod.getMontoMinimo()!=null)
		// paymentMethod.setMinAmount(new
		// CRBigDecimal(jpaPaymentMethod.getMontoMinimo().doubleValue()));
		// else
		// paymentMethod.setMinAmount(CRBigDecimal.ZERO);
		//
		// if(jpaPaymentMethod.getMontoMaximo()!=null)
		// paymentMethod.setMaxAmount(new
		// CRBigDecimal(jpaPaymentMethod.getMontoMaximo().doubleValue()));
		// else
		// paymentMethod.setMinAmount(CRBigDecimal.ZERO);
		return paymentMethod;
	}

	// TODO fromJpa sin moneda devuelve todas las formas de pago separadas por
	// moneda (devuelve un arraylist)
	/**
	 * Method fromJPA.
	 * 
	 * @param jpaPaymentMethod
	 *            Formadepago
	 * @return PaymentMethod
	 */
	public PaymentMethod fromJPA(Formadepago jpaPaymentMethod) {
		PaymentMethod paymentMethod = new PaymentMethod(jpaPaymentMethod.getCodigo(), "",
				jpaPaymentMethod.getDescripcion(), jpaPaymentMethod.getTipoFormaDePago());

		paymentMethod.setPaymentType(jpaPaymentMethod.getTipoFormaDePago());
		paymentMethod.setName(jpaPaymentMethod.getNombre());
		paymentMethod.setDonationpriority(jpaPaymentMethod.getPrioridadDonacion());
		paymentMethod.setPaymentIcon(imgPath + jpaPaymentMethod.getIconoFormaDePago());
		paymentMethod.setCardType(jpaPaymentMethod.getTipoTarjeta());
		paymentMethod.setReverseAccept(ActiveValues.get(jpaPaymentMethod.getPermiteReverso()).getValue());
		paymentMethod.setCode(jpaPaymentMethod.getCodigo());
		paymentMethod.setId(jpaPaymentMethod.getId());
		paymentMethod.setActive(ActiveValues.get(jpaPaymentMethod.getEstaactivo()).getValue());
		paymentMethod.setAllowsChange(ActiveValues.get(jpaPaymentMethod.getPermiteVuelto()).getValue());

		if (jpaPaymentMethod.getMontoMinimo() != null)
			paymentMethod.setMinAmount(new CRBigDecimal(jpaPaymentMethod.getMontoMinimo().doubleValue()));
		else
			paymentMethod.setMinAmount(CRBigDecimal.ZERO);

		if (jpaPaymentMethod.getMontoMaximo() != null)
			paymentMethod.setMaxAmount(new CRBigDecimal(jpaPaymentMethod.getMontoMaximo().doubleValue()));
		else
			paymentMethod.setMinAmount(CRBigDecimal.ZERO);

		PaymentMethod tmp = new PaymentMethod();
		tmp.setPaymentType(jpaPaymentMethod.getIdContrapartida().getTipoFormaDePago());
		tmp.setName(jpaPaymentMethod.getIdContrapartida().getNombre());
		tmp.setDonationpriority(jpaPaymentMethod.getIdContrapartida().getPrioridadDonacion());
		tmp.setPaymentIcon(imgPath + jpaPaymentMethod.getIdContrapartida().getIconoFormaDePago());
		tmp.setCardType(jpaPaymentMethod.getIdContrapartida().getTipoTarjeta());
		tmp.setReverseAccept(ActiveValues.get(jpaPaymentMethod.getIdContrapartida().getPermiteReverso()).getValue());
		tmp.setCode(jpaPaymentMethod.getIdContrapartida().getCodigo());
		tmp.setId(jpaPaymentMethod.getIdContrapartida().getId());
		tmp.setActive(ActiveValues.get(jpaPaymentMethod.getIdContrapartida().getEstaactivo()).getValue());
		tmp.setAllowsChange(ActiveValues.get(jpaPaymentMethod.getIdContrapartida().getPermiteVuelto()).getValue());

		if (jpaPaymentMethod.getIdContrapartida().getMontoMinimo() != null)
			tmp.setMinAmount(new CRBigDecimal(jpaPaymentMethod.getIdContrapartida().getMontoMinimo().doubleValue()));
		else
			tmp.setMinAmount(CRBigDecimal.ZERO);

		if (jpaPaymentMethod.getIdContrapartida().getMontoMaximo() != null)
			tmp.setMaxAmount(new CRBigDecimal(jpaPaymentMethod.getIdContrapartida().getMontoMaximo().doubleValue()));
		else
			tmp.setMinAmount(CRBigDecimal.ZERO);

		tmp.setCounterPart(paymentMethod);

		paymentMethod.setCounterPart(tmp);

		return paymentMethod;
	}

	/**
	 * 
	 * @param fdpId
	 *            long
	 * @return Collection<CommitmentCurrency>
	 */
	public Collection<CommitmentCurrency> getCurrencyListFromJPA(long fdpId) {
		FormadepagoJpaController jpaPMController = new FormadepagoJpaController(this.emf);
		MonedaJpaController jpamoneda = new MonedaJpaController(emf);
		Formadepago jpaPaymentMethod = jpaPMController.findFormadePagoById(fdpId);
		Collection<CommitmentCurrency> currencyList = new ArrayList<CommitmentCurrency>();

		for (Formadepagomoneda fdpmoneda : jpaPaymentMethod.getFormadepagomonedaList()) {
			if (fdpmoneda.getEstaactivo().equals("S")) {
				CommitmentCurrency currency = new CommitmentCurrency();
				currency.setIdCurrency(fdpmoneda.getIdMoneda().getId());
				currency.setNameCurrency(fdpmoneda.getIdMoneda().getNombre());
				currency.setSymbolCurrency(fdpmoneda.getIdMoneda().getSimbolo());

				Map<BigDecimal, CommitmentCurrencyDetails> currencyDetailsList = new TreeMap<BigDecimal, CommitmentCurrencyDetails>();
				for (Monedadenominacion monedaDenominacion : fdpmoneda.getIdMoneda().getMonedadenominacionList()) {

					CommitmentCurrencyDetails currencyDetails = new CommitmentCurrencyDetails();
				
					/*
					 * Programador: Roberto Sumoza
					 * 
					 * Esta condición se agrega para saltar aquellos registro de "monedadenominacion",
					 * que pertenezcan a una forma de pago distinta a efectivo o dolares, ya que en CRv3 
					 * se modificó la estructura de la tabla "monedadenominacion", y se amplio para la entrega total.
					 */
					if (monedaDenominacion.getIdFormadepago()==null || monedaDenominacion.getIdFormadepago().getId()!=fdpId) {
						continue;
					}
					
					// Mostrar solo denominaciones activas
					if (monedaDenominacion.getEstaactivo().equals("S")) {
						currencyDetails.setIdCurrencyDetail(monedaDenominacion.getId());
						currencyDetails.setCodeCurrency(monedaDenominacion.getCodigo());
						// currencyDetails.setName(monedaDenominacion.getNombre());
						
						String name;
						
						//temporal
						name=monedaDenominacion.getNombre();
						//proximos  cambios
//						name = monedaDenominacion.getNombre().replaceAll("[a-zA-Z]+[\\.]|[^\\w\\.@-]+[\\.]", "");
//						Number tmp = null;
//						try {
//							tmp = new DecimalFormat("########,###").parse(name.trim());
//
//						} catch (ParseException e) {
//							e.printStackTrace();
//						}
//
//						if (tmp != null) {
//							name=tmp.toString().trim();
//						} else {
//							name="0";
//						}
						currencyDetails.setName(CRBigDecimal.valueOf(Double.valueOf(name)));
						currencyDetails.setMoney(jpamoneda.fromJpa(monedaDenominacion.getIdMoneda()));
						currencyDetails.setParent(currency);
						currencyDetailsList.put(new BigDecimal(name), currencyDetails);
					}

				}
				currency.setCurrencyDetails(currencyDetailsList);
				currencyList.add(currency);
			}

		}

		return currencyList;
	}

	/**
	 * Method getPaymentMethodList.
	 * 
	 * @return List<PaymentMethod>
	 */
	public List<PaymentMethod> getPaymentMethodList() {
		List<PaymentMethod> payMethodList = new ArrayList<PaymentMethod>();

		List<Formadepago> tmpFormadepago = jpacontroller.findFormadepagoEntities();

		TreeMap<Integer, PaymentMethod> orderTreeTmp = new TreeMap<Integer, PaymentMethod>();

		for (Formadepago jpaPaymentMethod : tmpFormadepago) {
			/*
			 * Voy a buscar en la tabla formadepago_moneda para ver si existe mas de una
			 * moneda para la FDP
			 */
			if (ActiveValues.valueOf(jpaPaymentMethod.getEstaactivo()).getValue()) {
				for (Formadepagomoneda money : jpaPaymentMethod.getFormadepagomonedaList()) {

					MonedaJpaController monedaJpa = new MonedaJpaController(emf);

					PaymentMethod paymentMethod = fromJPA(jpaPaymentMethod, money.getIdMoneda());
					paymentMethod.setPriority(money.getPrioridadFormaDePago());
					paymentMethod.setActive(ActiveValues.valueOf(money.getEstaactivo()).getValue());
					paymentMethod.setRequiresAuthorization(
							ActiveValues.valueOf(jpaPaymentMethod.getRequiereAutorizacion()).getValue());
					paymentMethod.setAllowsClientChange(
							ActiveValues.valueOf(jpaPaymentMethod.getPermiteReasignarCliente()).getValue());
					paymentMethod.setCardType(jpaPaymentMethod.getTipoTarjeta());
					Money moneyCR = monedaJpa.findDefaultMoney(money.getIdMoneda().getId());
					paymentMethod.setExchangeRate(new CRBigDecimal(moneyCR.getExchangeRate().doubleValue(), 3));

					orderTreeTmp.put(paymentMethod.getPriority(), paymentMethod);
				}
			}
		}

		payMethodList.addAll(orderTreeTmp.values());

		return payMethodList;
	}

	/**
	 * 
	 * @return Collection<PaymentMethod>
	 */
	public Collection<PaymentMethod> findPaymentMethodEntities() {
		Collection<PaymentMethod> resultList = new ArrayList<PaymentMethod>();
		List<Formadepago> tmp = this.jpacontroller.findFormadepagoEntities();

		for (Iterator iterator = tmp.iterator(); iterator.hasNext();) {
			Formadepago fdp = (Formadepago) iterator.next();
			resultList.add(fromJPA(fdp));
		}
		return resultList;
	}

	/**
	 * Method getDefaultPaymentMethod.
	 * 
	 * @param fdp
	 *            Long
	 * @param currency
	 *            long
	 * @return PaymentMethod
	 */
	public PaymentMethod getDefaultPaymentMethod(Long fdp, long currency) {
		MonedaJpaController moneyController = new MonedaJpaController(this.emf);
		Moneda m = moneyController.findMoneda(currency);
		Formadepago jpaDefaultPaymentMethod = findFormadePagoById(fdp.intValue());
		return fromJPA(jpaDefaultPaymentMethod, m);
	}

	/**
	 * Method findActiveFormadepagoByType.
	 * 
	 * @param tipoformadepago
	 *            int
	 * @return List<PaymentMethod>
	 */
	public List<PaymentMethod> findActiveFormadepagoByType(int tipoformadepago) {

		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Formadepago.findByTipoFormaDePago");
		query.setParameter("tipoFormaDePago", tipoformadepago);
		List<PaymentMethod> result = new ArrayList<PaymentMethod>();
		try {

			List<PaymentMethod> tmp = query.getResultList();
			for (Iterator iterator = tmp.iterator(); iterator.hasNext();) {
				PaymentMethod object = fromJPA((Formadepago) iterator.next());
				if (object.isActive()) {
					result.add(object);
				}

			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} finally {
			em.close();
		}
	}
}
