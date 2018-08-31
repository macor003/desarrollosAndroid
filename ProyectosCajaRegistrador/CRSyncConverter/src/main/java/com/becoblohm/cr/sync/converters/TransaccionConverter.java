/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.sync.converters;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.becoblohm.cr.interfaces.ConverterException;
import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.models.Transaction.SourceTransactionType;
import com.becoblohm.cr.models.Transaction.TransactionState;
import com.becoblohm.cr.sync.models.SyncWrapper;
import com.becoblohm.cr.sync.singleton.ConverterSingleton;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Transaccion;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TransaccionConverter implements ConverterInterface {

	private static final Logger logger = LoggerFactory.getLogger(TransaccionConverter.class);

	/**
	 * Field converterError.
	 */
	private String converterError;

	/**
	 * Field TOLERANCE.
	 */
	private static final CRBigDecimal TOLERANCE = new CRBigDecimal(0.02);

	/**
	 * 
	 */
	public TransaccionConverter() {

	}

	/**
	 * Method fromServer.
	 * 
	 * @param obj
	 *            Object
	 * @param posId
	 *            String
	 * @return Object
	 * @throws ConverterException
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#fromServer(Object,
	 *      String)
	 */
	@Override
	public Object fromServer(Object obj, String posId) throws ConverterException {

		return new crjpa.Transaccion();
	}

	/**
	 * Method toServer.
	 * 
	 * @param obj
	 *            Object
	 * @return Object
	 * @throws ConverterException
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#toServer(Object)
	 */
	@Override
	public SyncWrapper toServer(Object obj) throws ConverterException {

		DevolucionConverter refundConverter = new DevolucionConverter();
		PagocreditoConverter creditPaymentConverter = new PagocreditoConverter();
		TransaccionarticuloConverter articleConverter = new TransaccionarticuloConverter();
		TransaccionclienteConverter clientConverter = new TransaccionclienteConverter();
		TransacciondocumentoConverter docConverter = new TransacciondocumentoConverter();
		TransaccionfaseConverter phaseConverter = new TransaccionfaseConverter();
		TransaccionformadepagoConverter paymentConverter = new TransaccionformadepagoConverter();
		RolloAuditoriaConverter auditPAperConverter = new RolloAuditoriaConverter();
		TransaccionauditoriaConverter auditConverter = new TransaccionauditoriaConverter();

		crjpa.Transaccion transaccionCaja = (crjpa.Transaccion) obj;
		crjpa400.Transaccion transaccion400 = new crjpa400.Transaccion(transaccionCaja.getId());

		SyncWrapper syncObject = new SyncWrapper();

		try {
			transaccion400.setCajero(transaccionCaja.getCajero());
			transaccion400.setDonacion(transaccionCaja.getDonacion());
			transaccion400.setEstado(transaccionCaja.getEstado().charAt(0));

			transaccion400.setFecha(transaccionCaja.getFecha());
			if (transaccionCaja.getIdAnulaciontransaccion() != null) {
				crjpa400.Transaccion tmp = new Transaccion();
				if (transaccionCaja.getTipo().toLowerCase().contains("anula")
						|| transaccionCaja.getTipo().toLowerCase().contains("devol")) {
					tmp = (Transaccion) toServer(transaccionCaja.getIdAnulaciontransaccion()).getEntity();
				} else {
					tmp = null;
				}
				transaccion400.setIdAnulaciontransaccion(tmp);
			}
			if (transaccionCaja.getNumeroIdentificacionCliente() != null) {
				transaccion400.setNumeroIdentificacionCliente(new crjpa400.Cliente(
						transaccionCaja.getNumeroIdentificacionCliente().getNumeroIdentificacionCliente()));
			}
			transaccion400.setIdSesion(new crjpa400.Sesion(transaccionCaja.getIdSesion().getId()));
			transaccion400.setTotalBase(transaccionCaja.getTotalBase());
			transaccion400.setTotalRebaja(transaccionCaja.getTotalRebaja());
			transaccion400.setTotalImpuesto(transaccionCaja.getTotalImpuesto());
			transaccion400.setTotalTransaccion(transaccionCaja.getTotalTransaccion());
			transaccion400.setNumero(transaccionCaja.getNumero());
			transaccion400.setNumeroFiscal(transaccionCaja.getNumeroFiscal());
			transaccion400.setOrigenTransaccion(transaccionCaja.getOrigenTransaccion());
			if (transaccionCaja.getIdRolloauditoria() != null) {
				transaccion400.setIdRolloauditoria((crjpa400.Rolloauditoria) auditPAperConverter
						.toServer(transaccionCaja.getIdRolloauditoria()).getEntity());
			}
			if (transaccionCaja.getIdSerialimpresora() != null) {
				transaccion400.setIdSerialimpresora(
						new crjpa400.Serialimpresora(transaccionCaja.getIdSerialimpresora().getId()));
			}
			if (transaccionCaja.getTipo().equalsIgnoreCase(SourceTransactionType.NonAttendance.getValue())) {
				transaccion400.setTipo(SourceTransactionType.Sale.getValue());
			} else {
				transaccion400.setTipo(transaccionCaja.getTipo());
			}

			transaccion400.setVuelto(transaccionCaja.getVuelto());
			transaccion400.setIdUsuario(new crjpa400.Usuario(transaccionCaja.getIdUsuario().getId()));
			ArrayList<crjpa400.Devolucion> refundList = new ArrayList<crjpa400.Devolucion>();

			// for (crjpa.Devolucion tmp1 : transaccionCaja.getDevolucionList()) {
			// refundList.add((crjpa400.Devolucion)
			// refundConverter.toServer(tmp1).getEntity());
			// }
			// transaccion400.setDevolucionList(refundList);

			ArrayList<crjpa400.Pagocredito> creditPaymentList = new ArrayList<crjpa400.Pagocredito>();
			if (transaccionCaja.getPagocreditoList() != null && transaccionCaja.getPagocreditoList().size() > 0) {
				creditPaymentList.add((crjpa400.Pagocredito) creditPaymentConverter
						.toServer(transaccionCaja.getPagocreditoList().get(0)).getEntity());
			}
			transaccion400.setPagocreditoList(creditPaymentList);

			ArrayList<crjpa400.Transaccionarticulo> articleList = new ArrayList<crjpa400.Transaccionarticulo>();
			for (crjpa.Transaccionarticulo tmp2 : transaccionCaja.getTransaccionarticuloList()) {
				articleList.add((crjpa400.Transaccionarticulo) articleConverter.toServer(tmp2).getEntity());
			}
			transaccion400.setTransaccionarticuloList(articleList);
			transaccion400.setRenglones(transaccionCaja.getRenglones());

			ArrayList<crjpa400.Transaccioncliente> clientList = new ArrayList<crjpa400.Transaccioncliente>();
			for (crjpa.Transaccioncliente tmp2 : transaccionCaja.getTransaccionclienteList()) {
				clientList.add((crjpa400.Transaccioncliente) clientConverter.toServer(tmp2).getEntity());
			}
			transaccion400.setTransaccionclienteList(clientList);

			ArrayList<crjpa400.Transacciondocumento> docList = new ArrayList<crjpa400.Transacciondocumento>();
			for (crjpa.Transacciondocumento tmp2 : transaccionCaja.getTransacciondocumentoList()) {
				docList.add((crjpa400.Transacciondocumento) docConverter.toServer(tmp2).getEntity());
			}
			transaccion400.setTransacciondocumentoList(docList);

			ArrayList<crjpa400.Transaccionfase> phaseList = new ArrayList<crjpa400.Transaccionfase>();
			for (crjpa.Transaccionfase tmp2 : transaccionCaja.getTransaccionfaseList()) {
				phaseList.add((crjpa400.Transaccionfase) phaseConverter.toServer(tmp2).getEntity());
			}
			transaccion400.setTransaccionfaseList(phaseList);

			ArrayList<crjpa400.Transaccionformadepago> paymentList = new ArrayList<crjpa400.Transaccionformadepago>();
			for (crjpa.Transaccionformadepago tmp2 : transaccionCaja.getTransaccionformadepagoList()) {
				if (tmp2.getEstaactivo().equals(ActiveValues.S.getString())) {
					paymentList.add((crjpa400.Transaccionformadepago) paymentConverter.toServer(tmp2).getEntity());
				}
			}
			transaccion400.setTransaccionformadepagoList(paymentList);

			ArrayList<crjpa400.Transaccionauditoria> auditList = new ArrayList<crjpa400.Transaccionauditoria>();
			for (crjpa.Transaccionauditoria audit : transaccionCaja.getTransaccionauditoriaList()) {
				auditList.add((crjpa400.Transaccionauditoria) auditConverter.toServer(audit).getEntity());
			}
			transaccion400.setTransaccionauditoriaList(auditList);

			transaccion400.setEstreplica(' ');

			if (transaccionCaja.getTipo().equalsIgnoreCase("venta")) {
				transaccion400.setIdAnulaciontransaccion(null);
			}
			if (transaccionCaja.getIdComprobantefiscalpreimpreso() != null) {
				transaccion400.setIdComprobantefiscalpreimpreso(new crjpa400.Comprobantefiscalpreimpreso(
						transaccionCaja.getIdComprobantefiscalpreimpreso().getId()));
			}
			if (!validateTransaction(transaccion400)) {
				transaccion400 = null;
				throw new ConverterException("Transaccion", converterError, transaccionCaja.getId().toString());
			}
			syncObject.setEntity(transaccion400);
			syncObject.setId(transaccionCaja.getId());

		} catch (Exception e) {
			logger.error("Error " + this.getClass().getName() + " -> ", e);
			throw new ConverterException("Transaccion ", e, transaccionCaja.getId().toString());
		}
		return syncObject;
	}

	/**
	 * Method validateTransaction.
	 * 
	 * @param transaction
	 *            crjpa400.Transaccion
	 * @return boolean
	 */
	private boolean validateTransaction(crjpa400.Transaccion transaction) {
		boolean isValid = false;
		if (String.valueOf(transaction.getEstado()).equals(TransactionState.COMPLETE.getValue())) {
			isValid = true;
		} else {
			isValid = false;
			converterError = "La transaccion " + transaction.getId() + " no tiene estatus finalizado";
		}

		if (isValid) {
			if ((transaction.getTransaccionformadepagoList() == null
					|| (transaction.getTransaccionformadepagoList() != null
							&& transaction.getTransaccionformadepagoList().size() < 1))) {
				converterError = "La transaccion " + transaction.getId() + " no tiene formas de pago";
				isValid = false;
			} else {
				CRBigDecimal totalPmAmmount = CRBigDecimal.ZERO;
				for (crjpa400.Transaccionformadepago payMethod : transaction.getTransaccionformadepagoList()) {
					if (String.valueOf(payMethod.getEstaactivo()).equals(ActiveValues.S.getString())) {
						totalPmAmmount = totalPmAmmount
								.add(new CRBigDecimal(payMethod.getMontoMonedaLocal().doubleValue()));
					}

				}
				CRBigDecimal transactionAmount = new CRBigDecimal(transaction.getTotalTransaccion().doubleValue())
						.add(new CRBigDecimal(transaction.getDonacion().doubleValue()))
						.add(new CRBigDecimal(transaction.getVuelto().doubleValue()));

				CRBigDecimal difference = totalPmAmmount.abs().subtract(transactionAmount).abs();

				if (difference.abs().compareTo(TOLERANCE) <= 0) {
					isValid = true;
				} else {

					converterError = "Los montos de la transaccion no son correctos (formas de pago vs total de transaccion) difference: "
							+ difference + " totalPmAmmount: " + totalPmAmmount + " transactionAmount: "
							+ transactionAmount;
					isValid = false;
				}
			}
		}

		if (isValid) {
			if (transaction.getTipo().equalsIgnoreCase("pagocredito")
					|| transaction.getTipo().equalsIgnoreCase("anulacredito")) {
				isValid = true;
			} else {
				if (transaction.getTransaccionarticuloList() == null
						|| (transaction.getTransaccionarticuloList() != null
								&& transaction.getTransaccionarticuloList().size() < 1)) {
					converterError = "La transaccion " + transaction.getId() + " no tiene articulos";
					isValid = false;
				} else {
					isValid = true;
				}
				if (transaction.getRenglones() == transaction.getTransaccionarticuloList().size()) {
					isValid = true;
				} else {
					isValid = false;
				}
			}
		}
		logger.error(converterError);
		return isValid;
	}

	/**
	 * Method getPosEmf.
	 * 
	 * @return EntityManagerFactory
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#getPosEmf()
	 */
	@Override
	public EntityManagerFactory getPosEmf() {
		return ConverterSingleton.getPosEMF();
	}

	/**
	 * Method getServerEmf.
	 * 
	 * @return EntityManagerFactory
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#getServerEmf()
	 */
	@Override
	public EntityManagerFactory getServerEmf() {
		return ConverterSingleton.getServerEMF();
	}

	/**
	 * Method setPosNumber.
	 * 
	 * @param posNumber
	 *            String
	 * @see com.becoblohm.cr.interfaces.ConverterInterface#setPosNumber(String)
	 */
	@Override
	public void setPosNumber(String posNumber) {
	}
}
