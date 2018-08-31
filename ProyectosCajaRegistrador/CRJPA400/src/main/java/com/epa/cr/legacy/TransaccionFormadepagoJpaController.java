/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Money;
import com.becoblohm.cr.models.Payment;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Monedatasacambio;
import crjpa400.Transaccionformadepago;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TransaccionFormadepagoJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Transaccionformadepago";
	/**
	 * Field emf.
	 */
	private final EntityManagerFactory emf;
	/**
	 * Field jpaFDPController.
	 */
	private final FormadepagoJpaController jpaFDPController;
	/**
	 * Field jpaController.
	 */
	private final crjpa400.TransaccionformadepagoJpaController jpaController;

	
	/**
	 * Constructor for TransaccionFormadepagoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TransaccionFormadepagoJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.POSSOURCE, entityName);
		this.emf = emf;
		jpaFDPController = new FormadepagoJpaController(this.emf);
		jpaController = new crjpa400.TransaccionformadepagoJpaController(this.emf);
	}
	
	
	/**
	 * Method fromJPA.
	 * 
	 * @param jpaPaymentMethod
	 *            Transaccionformadepago
	 * @return Payment
	 */
	public Payment fromJPA(Transaccionformadepago jpaPaymentMethod) {
		Payment payment = new Payment();
		payment.setDonation(CRBigDecimal.ZERO);
		MonedatasacambioJpaController exchangeController = new MonedatasacambioJpaController(this.emf);
		Monedatasacambio exchange = exchangeController.findLastByDate(jpaPaymentMethod.getIdTransaccion().getFecha(),
				jpaPaymentMethod.getIdMoneda().getId());
		Money money = new Money();
		money.setExchangeRate(new CRBigDecimal(exchange.getCambio().doubleValue(), 3));
		money.setCurrencyAmmount(new CRBigDecimal(jpaPaymentMethod.getMontoMoneda().doubleValue()));
		money.setLocalAmmount(new CRBigDecimal(jpaPaymentMethod.getMontoMonedaLocal().doubleValue()));
		money.setCurrencyId(jpaPaymentMethod.getIdMoneda().getId());
		money.setName(jpaPaymentMethod.getIdMoneda().getNombre());
		payment.setMoney(money);
		payment.setId(String.valueOf(jpaPaymentMethod.getId()));
		payment.setPayMethod(jpaFDPController.fromJPA(jpaPaymentMethod.getIdFormadepago(),
				jpaPaymentMethod.getIdMoneda()));
		payment.setTransactionPaymentId(jpaPaymentMethod.getId());
		payment.setActive(ActiveValues.get(String.valueOf(jpaPaymentMethod.getIdFormadepago().getEstaactivo())).getValue());
		payment.setDocumentNumber(jpaPaymentMethod.getDocumento());
		payment.setCardHolder(jpaPaymentMethod.getTitular());
		payment.setAccountNumber(jpaPaymentMethod.getCuenta());
		payment.setConformationNumber(jpaPaymentMethod.getConformacion());
		// payment.setName(payment.getPayMethod().getName());//jpaPaymentMethod.getIdFormadepago().getNombre()+" "+jpaPaymentMethod.getIdMoneda().getNombre());
		payment.setRetentionPercentage(new CRBigDecimal(jpaPaymentMethod.getPorcentajeRetencion().doubleValue()));
		
		PorcentajeimpuestoretencionJpaController pir = new PorcentajeimpuestoretencionJpaController(this.emf);
		
		if (jpaPaymentMethod.getIdPorcentajeimpuestoretencion() != null){
			payment.setRetencion(pir.fromJPA(jpaPaymentMethod.getIdPorcentajeimpuestoretencion()));
		}
		
		return payment;
	}

}
