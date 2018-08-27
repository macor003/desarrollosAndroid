/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CashierCommitment;
import com.becoblohm.cr.models.CommitmentCurrency;
import com.becoblohm.cr.models.CommitmentCurrencyDetails;
import com.becoblohm.cr.models.CommitmentPayment;
import com.becoblohm.cr.models.User;

import crjpa400.Entrega;
import crjpa400.Entregaformadepago;
import crjpa400.Formadepago;
import crjpa400.Monedadenominacion;
import crjpa400.Sesion;
import crjpa400.Usuario;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class EntregaJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Entrega";

	/**
	 * Constructor for EntregaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public EntregaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * Method findLastDelivery.
	 * 
	 * @param caja
	 *            long
	 * @return long
	 */
	public long findLastDelivery(long caja) {
		long idDelivery;
		try {
			EntityManager em = emf.createEntityManager();
			String queryString = "SELECT MAX(t.id) FROM " + entityName + " t WHERE t.idSesion.idCaja.id = :id";
			Query query = em.createQuery(queryString);
			query.setParameter("id", caja);
			query.setMaxResults(1);
			idDelivery = (Long) query.getSingleResult();
		} catch (NoResultException ex) {
			idDelivery = 0;
		} catch (Exception ex) {
			idDelivery = 0;
		}
		return idDelivery;
	}

	/**
	 * Method create.
	 * 
	 * @param cashierModel
	 *            CashierCommitment
	 * @return long
	 * @throws JpaException
	 */
	public long create(CashierCommitment cashierModel) throws JpaException {
		EntityManager em = null;

		crjpa400.Entrega entrega = toJPA(cashierModel);

		List<Entregaformadepago> entregaFDPList = new ArrayList<Entregaformadepago>();

		for (Iterator<CommitmentPayment> iteratorPM = cashierModel.getPaymentMethodList().iterator(); iteratorPM
				.hasNext();) {
			CommitmentPayment paymentMethod = iteratorPM.next();

			for (Iterator<CommitmentCurrency> iterator = paymentMethod.getCurrencies().iterator(); iterator.hasNext();) {
				CommitmentCurrency tmpCurrency = iterator.next();

				for (Iterator<CommitmentCurrencyDetails> iterator2 = tmpCurrency.getCurrencyDetails().values()
						.iterator(); iterator2.hasNext();) {
					CommitmentCurrencyDetails tmpCurrencyDetails = iterator2.next();

					Entregaformadepago entregaFDP = toJPA(entrega, paymentMethod, tmpCurrencyDetails);
					entregaFDP.setIdEntrega(entrega);
					entregaFDPList.add(entregaFDP);
				}
			}
		}

		if (entregaFDPList.size() != 0) {
			entrega.setEntregaformadepagoList(entregaFDPList);
		} else {
			return -1;
		}

		em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entrega);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new JpaException();
		} finally {
			if (em != null) {
				em.clear();
				em.close();
			}
		}

		return entrega.getId();
	}

	/**
	 * Method toJPA.
	 * 
	 * @param cashierModel
	 *            CashierCommitment
	 * @return Entrega
	 */
	private Entrega toJPA(CashierCommitment cashierModel) {

		Entrega entrega = new Entrega();
		entrega.setId(cashierModel.getId());
		entrega.setIdSesion(new Sesion(cashierModel.getId_sesion()));
		entrega.setMontoRecaudado(cashierModel.getMonto_recaudado().getValue());
		entrega.setMontoFondo(cashierModel.getMonto_fondo().getValue());
		entrega.setFecha(new Date());
		entrega.setEstaactivo('S');
		if (cashierModel.isOnLine()) {
			entrega.setEnlinea('S');
		} else {
			entrega.setEnlinea('N');
		}

		UsuarioJpaController jpau = new UsuarioJpaController(emf);

		User user = jpau.findUsuario(Long.valueOf(cashierModel.getIdAuth()));

		entrega.setIdUsuarioautorizante(new Usuario(Long.valueOf(user.getId())));

		entrega.setTipoEntrega(cashierModel.getDeliveryType().charAt(0));
		entrega.setEntregaformadepagoList(new ArrayList<Entregaformadepago>());
		Long idTransExceed = 0L;
		if (cashierModel.getIdTransExceeded() != "") {
			String cadena = cashierModel.getIdTransExceeded();
			idTransExceed = Long.valueOf(cadena);
		}
		entrega.setTransaccionExcedida(BigInteger.valueOf(idTransExceed));
		entrega.setNumero(cashierModel.getNumber());
		return entrega;
	}

	/**
	 * Method toJPA.
	 * 
	 * @param entrega
	 *            Entrega
	 * @param paymentMethod
	 *            CommitmentPayment
	 * @param tmpCurrencyDetails
	 *            CommitmentCurrencyDetails
	 * @return Entregaformadepago
	 */
	private Entregaformadepago toJPA(Entrega entrega, CommitmentPayment paymentMethod,
			CommitmentCurrencyDetails tmpCurrencyDetails) {

		Entregaformadepago entregaFDP;
		Monedadenominacion monedaDenominacion;

		monedaDenominacion = new Monedadenominacion(tmpCurrencyDetails.getIdCurrencyDetail());

		entregaFDP = new Entregaformadepago();
		// entregaFDP.setId(null);
		entregaFDP.setIdEntrega(entrega);
		entregaFDP.setIdMonedadenominacion(monedaDenominacion);
		entregaFDP.setBilletes((int) tmpCurrencyDetails.getBills());
		entregaFDP.setCodigoMonedaDenominacion((int) tmpCurrencyDetails.getCodeCurrency());
		entregaFDP.setIdFormadepago(new Formadepago(Long.valueOf(paymentMethod.getCode())));
		entregaFDP.setEstaactivo('S');
		entregaFDP.setMonto(tmpCurrencyDetails.getAmount().getValue());
		entregaFDP.setFecha(entrega.getFecha());
		return entregaFDP;
	}
}
