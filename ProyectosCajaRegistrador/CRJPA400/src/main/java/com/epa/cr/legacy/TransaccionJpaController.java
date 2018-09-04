/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.DeliveryCondition;
import com.becoblohm.cr.models.Donation;
import com.becoblohm.cr.models.Payment;
import com.becoblohm.cr.models.SerialPrint;
import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.models.Transaction.Source;
import com.becoblohm.cr.models.Transaction.SourceTransactionType;
import com.becoblohm.cr.models.Transaction.TransactionState;
import com.becoblohm.cr.models.TransactionAudit;
import com.becoblohm.cr.models.TransactionDocument;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Comprobantefiscalpreimpreso;
import crjpa400.Sesion;
import crjpa400.Tipodocumento;
import crjpa400.Transaccion;
import crjpa400.Transaccionarticulo;
import crjpa400.Transaccionauditoria;
import crjpa400.Transacciondocumento;
import crjpa400.Transaccionformadepago;

/**
 */
public class TransaccionJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Transaccion";

	/**
	 * Field jpaTransactionController.
	 */
	private crjpa400.TransaccionJpaController jpaTransactionController;

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;

	/**
	 * Field jpaArticleController.
	 */
	private ArticuloJpaController jpaArticleController;

	/**
	 * Field jpaTransactionDocument.
	 */
	private TransaccionDocumentoJpaController jpaTransactionDocument;

	/**
	 * Field jpaClientController.
	 */
	private ClienteJpaController jpaClientController;

	/**
	 * Field jpaPaymentMethod.
	 */
	private final TransaccionFormadepagoJpaController jpaPaymentMethod;

	private TransaccionauditoriaJpaController jpaAuditController;

	/**
	 * Constructor for TransaccionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TransaccionJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
		this.jpaTransactionController = new crjpa400.TransaccionJpaController(emf);
		jpaArticleController = new ArticuloJpaController(this.emf);
		jpaClientController = new ClienteJpaController(this.emf);
		jpaTransactionDocument = new TransaccionDocumentoJpaController(this.emf);
		jpaPaymentMethod = new TransaccionFormadepagoJpaController(this.emf);
		jpaAuditController = new TransaccionauditoriaJpaController(emf);
	}

	/**
	 * Method fromJPAWaitingSale.
	 * 
	 * @param obj
	 *            crjpa400.Transaccion
	 * @return Transaction
	 */
	public Transaction fromJPAWaitingSale(crjpa400.Transaccion obj) {

		TipodocumentoJpaController docJpaController = new TipodocumentoJpaController(emf);
		Transaction trans = new Transaction();

		trans.setPosId(obj.getIdSesion().getIdCaja().getId().toString());

		if (obj != null && obj.getId() > 0) {
			trans.setDate(obj.getFecha());
			trans.setDeliveryInfo(null);

			crjpa400.Tipodocumento supportSaleDocType = null;
			for (crjpa400.Transacciondocumento doc : obj.getTransacciondocumentoList()) {
				if (ActiveValues.get(String.valueOf(doc.getIdTipodocumento().getEssoporteventa())).getValue()) {
					supportSaleDocType = doc.getIdTipodocumento();
					break;
				}
			}
			if (supportSaleDocType != null) {
				trans.setDocument(docJpaController.fromJPA(supportSaleDocType));
			}

			trans.setDonation(new Donation(new CRBigDecimal(obj.getDonacion().doubleValue())));
			trans.setFiscalId(obj.getNumeroFiscal());
			trans.setNumber(String.valueOf(obj.getNumero()));
			trans.setId(obj.getId());

			ArrayList<Payment> paymentsList = new ArrayList<Payment>();
			trans.setPayments(paymentsList);
			trans.setPosId(String.valueOf(obj.getIdSesion().getIdCaja().getId()));

			if (obj.getIdSerialimpresora() != null) {
				trans.setPrinterSerial(obj.getIdSerialimpresora().getSerial());
			}

			trans.setSaleOrigin(Source.get(obj.getOrigenTransaccion()));
			trans.setStatus(String.valueOf(obj.getEstado()));

			OpcionJpaController opcionJpaController = new OpcionJpaController(this.emf);
			trans.setStore(opcionJpaController.findById(new Long(1)));
			trans.setTransactionType(SourceTransactionType.get(obj.getTipo()));
			if (obj.getNumeroIdentificacionCliente() != null) {
				trans.setClient(jpaClientController.fromJpa(obj.getNumeroIdentificacionCliente()));
			}
			trans.setUser(UsuarioJpaController.fromJpa(obj.getIdUsuario()));

			HashMap<Integer, DeliveryCondition> deliveryInfo = new HashMap<Integer, DeliveryCondition>();
			TipodescuentoJpaController tipoDescuentoJpaController = new TipodescuentoJpaController(this.emf);
			TasaimpuestotipoJpaController taxController = new TasaimpuestotipoJpaController(this.emf);

			for (crjpa400.Transaccionarticulo transaccionArticulo : obj.getTransaccionarticuloList()) {
				Article article = jpaArticleController.fromJPA(transaccionArticulo.getIdArticulo());
				article.setDeliveryCondition(DeliveryCondition.getDeliveryConditionsHash()
						.get(String.valueOf(transaccionArticulo.getCondicionEntrega())));
				DeliveryCondition cond = article.getDeliveryCondition();
				article.setDiscountType(tipoDescuentoJpaController.fromJpa(transaccionArticulo.getIdTipodescuento()));

				if (transaccionArticulo.getIdPromocion() == null
						&& !transaccionArticulo.getIdTipodescuento().getNombre().equalsIgnoreCase("N")) {
					article.getDiscountType().setPromotion(false);
				}

				article.setItems(new CRBigDecimal(transaccionArticulo.getCantidad().doubleValue()));
				article.setOriginalItemCost(new CRBigDecimal(transaccionArticulo.getMontoUnitario().doubleValue()));
				article.setItemCost(new CRBigDecimal(transaccionArticulo.getMontoBase().doubleValue()));
				article.setTax(
						taxController.fromJPA(transaccionArticulo.getIdTasaimpuestovalor().getIdTasaimpuestotipo()));
				trans.addArticle(article);
				if (cond != null) {
					deliveryInfo.put(cond.getId(), cond);
				}
			}

			trans.setDeliveryInfo(deliveryInfo);

			List<crjpa400.Transacciondocumento> documentList = obj.getTransacciondocumentoList();
			for (crjpa400.Transacciondocumento transaccionDocumento : documentList) {
				TransactionDocument transDocument = jpaTransactionDocument.fromJPA(transaccionDocumento);
				trans.getDocuments().add(transDocument);
			}

			List<crjpa400.Transaccionauditoria> auditList = obj.getTransaccionauditoriaList();
			for (crjpa400.Transaccionauditoria audit : auditList) {
				trans.addTransactionAudit(jpaAuditController.fromJPA(audit));
			}

			trans.setTaxAmount(new CRBigDecimal(obj.getTotalImpuesto().doubleValue()));
			trans.setTaxes(new HashMap());
			trans.setTotalCost(new CRBigDecimal(obj.getTotalTransaccion().doubleValue()));

		} else {
			trans = null;
		}
		return trans;
	}

	/**
	 * Method findTransaccionWatingSale.
	 * 
	 * @param value
	 *            Date
	 * @param estado
	 *            String
	 * @return List<Transaction>
	 */
	public List<Transaction> findTransaccionWatingSale(Date value, String estado) {
		Date fechaInicial = cleanDate(value, true);// cal.getTime();
		Date fechaFinal = cleanDate(value, false);// cal.getTime();
		EntityManager em = jpaTransactionController.getEntityManager();

		Query query = em.createQuery(
				"select t from Transaccion t where t.estado = :estado and t.fecha >= :fechaInicial AND t.fecha <= :fechaFinal");

		query.setParameter("fechaInicial", fechaInicial);
		query.setParameter("fechaFinal", fechaFinal);
		query.setParameter("estado", estado.charAt(0));

		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		List<crjpa400.Transaccion> transList = query.getResultList();
		for (crjpa400.Transaccion tmp : transList) {
			transactionList.add(fromJPAWaitingSale(tmp));
		}
		em.close();
		return transactionList;
	}

	/**
	 * Method cleanDate.
	 * 
	 * @param p_getxReportDate
	 *            Date
	 * @param param
	 *            boolean
	 * @return Date
	 */
	private Date cleanDate(Date p_getxReportDate, boolean param) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(p_getxReportDate);
		if (param) {
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 1);
		} else {
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 59);
		}
		return cal.getTime();
	}

	// public boolean releaseWaitingSale(Transaction tr, char status) throws
	// JpaException {
	// boolean isEdited = false;
	// crjpa400.Transaccion tmp =
	// jpaTransactionController.findTransaccion(tr.getId());
	// if (tmp != null) {
	// tmp.setEstado(status);
	// try {
	// jpaTransactionController.edit(tmp);
	// isEdited = true;
	// } catch (Exception e) {
	// e.printStackTrace();
	// throw new JpaException();
	// }
	// }
	// return isEdited;
	// }
	/**
	 * Method releaseWaitingSale.
	 * 
	 * @param tr
	 *            Transaction
	 * @return boolean
	 * @throws JpaException
	 */
	public boolean releaseWaitingSale(Transaction tr) throws JpaException {
		boolean isReleased = false;
		EntityManager em = null;

		try {
			em = emf.createEntityManager();
			crjpa400.Transaccion tmp = em.find(crjpa400.Transaccion.class, tr.getId());
			if (tmp.getId() != null) {
				em.getTransaction().begin();
				em.remove(tmp);
				em.getTransaction().commit();
				isReleased = true;
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			isReleased = false;
			throw new JpaException(e);
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return isReleased;
	}

	/**
	 * Method create.
	 * 
	 * @param tr
	 *            Transaction
	 * @param session
	 *            Session
	 * @return boolean
	 * @throws JpaException
	 */
	public boolean create(Transaction tr, Session session) throws JpaException {
		boolean isCreated = false;
		crjpa400.Transaccion tmp = toJPA(tr, session);
		try {
			jpaTransactionController.create(tmp);
			isCreated = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new JpaException();
		}
		return isCreated;
	}

	/**
	 * Method persist.
	 * 
	 * @param tr
	 *            Transaction
	 * @param session
	 *            Session
	 * @return boolean
	 * @throws JpaException
	 */
	public boolean persist(Transaction tr, Session session) throws JpaException {
		boolean isCreated = false;
		EntityManager em = emf.createEntityManager();
		em.find(Transaccionarticulo.class, 0l);
		crjpa400.Transaccion tmp = toJPA(tr, session);
		try {
			em.getTransaction().begin();
			em.persist(tmp);
			em.getTransaction().commit();
			isCreated = true;
		} catch (Exception e) {
			throw new JpaException(e);
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return isCreated;
	}

	/**
	 * Method toJPA.
	 * 
	 * @param obj
	 *            Transaction
	 * @param session
	 *            Session
	 * @return crjpa400.Transaccion
	 */
	public crjpa400.Transaccion toJPA(Transaction obj, Session session) {
		ClienteJpaController clientJpaController = new ClienteJpaController(this.emf);
		TransaccionArticuloJpaController transArtcontroller = new TransaccionArticuloJpaController(this.emf);
		SesionJpaController sesionjpa = new SesionJpaController(emf);
		TransaccionauditoriaJpaController auditController = new TransaccionauditoriaJpaController(emf);

		String correlativo = new String("" + obj.getId());
		crjpa400.Transaccion trans = new crjpa400.Transaccion(Long.valueOf(correlativo));

		crjpa400.Rolloauditoria auditRollId = null;

		if (obj.getPrinterSerial() != null) {
			crjpa400.RolloauditoriaJpaController jpa = new crjpa400.RolloauditoriaJpaController(emf);
			if (jpa.getRolloauditoriaCount() > 0) {
				auditRollId = jpa.findRolloauditoriaEntities().get(jpa.getRolloauditoriaCount() - 1);
				trans.setIdRolloauditoria(auditRollId);
			}
		}

		crjpa400.Cliente client = null;
		if (obj.getClient().getIdNumber() != null) {
			client = clientJpaController.toJpa(obj.getClient(), false);
		}

		trans.setNumeroIdentificacionCliente(client);

		Session tmpSession = sesionjpa.findSesion(session.getId());

		trans.setIdSesion(sesionjpa.toJpa(session));

		if (obj instanceof Transaction) {
			trans.setIdAnulaciontransaccion(null);
		} else {
			trans.setIdAnulaciontransaccion(jpaTransactionController.findTransaccion(new Long(obj.getId())));
		}
		trans.setIdUsuario(trans.getIdSesion().getIdUsuario());

		if (obj.getFiscalId() >= 0) {
			trans.setNumeroFiscal(1);
		} else {
			trans.setNumeroFiscal(obj.getFiscalId());
		}
		trans.setCajero(session.getCashier().getIdEPA());
		trans.setTipo(obj.getTransactionType().getValue());
		trans.setEstado(obj.getStatus().charAt(0));
		trans.setFecha(obj.getDate());
		trans.setTotalBase(obj.getBaseAmount().getValue());
		trans.setTotalImpuesto(obj.getTaxAmount().getValue());

		if (obj.getTotalRebate() != null) {
			trans.setTotalRebaja(obj.getTotalRebate().getValue());
		}

		trans.setVuelto(obj.getDiference().getValue());
		trans.setDonacion(obj.getDonation().getAmount().getValue());
		if (obj.getPrinterSerial() == null) {
			trans.setIdSerialimpresora(null);
		} else {
			SerialimpresoraJpaController serial = new SerialimpresoraJpaController(emf);
			SerialPrint tmpserial = serial.findSerialImpresoraBySerial(obj.getPrinterSerial());
			trans.setIdSerialimpresora(serial.toJPA(tmpserial));
		}
		trans.setOrigenTransaccion(obj.getSaleOrigin().getValue());
		trans.setTotalTransaccion(trans.getTotalBase().add(trans.getTotalImpuesto()));
		trans.setDonacion(obj.getDonation().getAmount().getValue());
		trans.setRenglones(obj.getArticlesIndex().size());

		ArrayList<Transaccionarticulo> tmp = new ArrayList<Transaccionarticulo>();
		for (Article art : obj.getArticles()) {
			tmp.add(transArtcontroller.toJPA(art, trans));
		}
		trans.setTransaccionarticuloList(tmp);

		ArrayList<Transaccionauditoria> jpaAuditList = new ArrayList<Transaccionauditoria>();
		for (TransactionAudit audit : obj.getTransactionAudits()) {
			jpaAuditList.add(auditController.toJPA(audit, trans));
		}
		trans.setTransaccionauditoriaList(jpaAuditList);

		if (tmpSession != null) {
			trans.setIdSesion(new Sesion(tmpSession.getId()));
		}

		if (obj.getId() > 0) {
			crjpa400.Transaccion oldTransaction = jpaTransactionController.findTransaccion(obj.getId());
			if (oldTransaction != null) {
				// trans.setDevolucionList(oldTransaction.getDevolucionList());
				trans.setTransaccionarticuloList(oldTransaction.getTransaccionarticuloList());
				trans.setTransaccionclienteList(oldTransaction.getTransaccionclienteList());
				trans.setTransacciondocumentoList(oldTransaction.getTransacciondocumentoList());
				trans.setTransaccionfaseList(oldTransaction.getTransaccionfaseList());
				trans.setTransaccionformadepagoList(oldTransaction.getTransaccionformadepagoList());
				trans.setTransaccionList(oldTransaction.getTransaccionList());
				trans.setPagocreditoList(oldTransaction.getPagocreditoList());
			}
		}

		if (obj.getResolution() == null) {
			trans.setIdComprobantefiscalpreimpreso(null);
		} else {
			trans.setIdComprobantefiscalpreimpreso(new Comprobantefiscalpreimpreso(obj.getResolution().getId()));
		}

		return trans;
	}

	/**
	 * Method findLastTransaction.
	 * 
	 * @param caja
	 *            long
	 * @return long
	 */
	public long findLastTransaction(long caja) {
		long idTr;
		EntityManager em = emf.createEntityManager();
		try {
			String queryString = "SELECT MAX(t.id) FROM " + entityName + " t WHERE t.idSesion.idCaja.id = :id";
			Query query = em.createQuery(queryString);
			query.setParameter("id", caja);
			query.setMaxResults(1);
			idTr = (Long) query.getSingleResult();
		} catch (NoResultException ex) {
			idTr = 0;
		} catch (Exception ex) {
			idTr = 0;
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return idTr;
	}

	/**
	 * Busca el numero maximo de transacciones que son distintas a pago credito y a
	 * acreencias ya que estas suben a legacy a tablas distintas de las
	 * transacciones
	 * 
	 * @param caja
	 *            numero de caja
	 * 
	 * @return long numero de transaccion, 0 si no encuentra ningun reusultado, - 1
	 *         si la consulta falla
	 */
	public long findLastTransactionNumber(long caja) {
		long number = 0L;
		EntityManager em = emf.createEntityManager();
		try {
			String queryString = "SELECT MAX(t.numero) FROM " + entityName
					+ " t WHERE t.idSesion.idCaja.id = :id and t.tipo <> '"
					+ Transaction.SourceTransactionType.CreditsEpaPay.getValue() + "' and t.tipo <> '"
					+ Transaction.SourceTransactionType.Credits.getValue() + "'";
			Query query = em.createQuery(queryString);
			query.setParameter("id", caja);
			query.setMaxResults(1);
			Object singleResult = query.getSingleResult();
			if (singleResult != null) {
				number = (Long) query.getSingleResult();
			}
		} catch (NoResultException ex) {
			logger.error("Error -> ", ex);
			number = 0;
		} catch (Exception ex) {
			logger.error("Error -> ", ex);
			number = 0;
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return number;
	}

	/**
	 * busca el numero maximo de una transaccion filtrando por el tipo
	 * 
	 * @param caja
	 *            numero de caja
	 * @param type
	 *            tipo de transaccion (Transaction.SourceTransactionType)
	 * 
	 * @return long numero de transaccion, 0 si no encuentra ningun reusultado, - 1
	 *         si la consulta falla
	 */
	public long findLastTransactionNumberByType(long caja, Transaction.SourceTransactionType type) {
		long number = 0L;
		EntityManager em = emf.createEntityManager();
		try {
			String queryString = "SELECT MAX(t.numero) FROM " + entityName
					+ " t WHERE t.idSesion.idCaja.id = :id and t.tipo = :tipo";
			Query query = em.createQuery(queryString);
			query.setParameter("id", caja);
			query.setParameter("tipo", type.getValue());
			query.setMaxResults(1);
			Object singleResult = query.getSingleResult();
			if (singleResult != null) {
				number = (Long) query.getSingleResult();
			}
		} catch (NoResultException ex) {
			logger.error("Error -> ", ex);
			number = 0;
		} catch (Exception ex) {
			logger.error("Error -> ", ex);
			number = 0;
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return number;
	}

	/**
	 * Method findTransactionByStore_Pos_Id_Date.
	 * 
	 * @param posId
	 *            String
	 * @param numero
	 *            String
	 * @param type
	 *            String
	 * @param value
	 *            Date
	 * @return Transaction
	 */
	public Transaction findTransactionByPos_Id(String posId, String numero) {
		Transaction result = null;
		EntityManager em = jpaTransactionController.getEntityManager();
		try {

			Query query = em.createQuery(
					"select t from Transaccion t where t.idSesion.idCaja.id = :posId and t.numero = :numero and t.estado = '"
							+ TransactionState.COMPLETE.getValue() + " ' and t.tipo='"
							+ SourceTransactionType.Sale.getValue() + "'");

			query.setParameter("posId", new Long(posId));
			query.setParameter("numero", new Long(numero));

			query.setMaxResults(1);
			Transaccion tmp = (Transaccion) query.getSingleResult();
			result = fromJPA(tmp);
			em.clear();
		} catch (javax.persistence.NoResultException ex) {
			result = null;
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return result;
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param obj
	 *            Transaccion
	 * @return Transaction
	 */
	public Transaction fromJPA(Transaccion obj) {

		TipodocumentoJpaController docJpaController = new TipodocumentoJpaController(emf);
		Transaction trans = new Transaction();
		TipodescuentoJpaController discountJpaController = new TipodescuentoJpaController(this.emf);
		ComprobantefiscalpreimpresoJpaController resolutionController = new ComprobantefiscalpreimpresoJpaController(
				this.emf);
		String rbtdsc = "";
		if (obj != null && obj.getId() > 0) {
			trans.setDate(obj.getFecha());
			trans.setPosId(String.valueOf(obj.getIdSesion().getIdCaja().getId()));
			trans.setDeliveryInfo(null);
			Tipodocumento supportSaleDocType = null;
			for (Transacciondocumento doc : obj.getTransacciondocumentoList()) {
				if (ActiveValues.get(String.valueOf(doc.getIdTipodocumento().getEssoporteventa())).getValue()) {
					supportSaleDocType = doc.getIdTipodocumento();
					break;
				}
			}

			if (supportSaleDocType != null) {
				trans.setDocument(docJpaController.fromJPA(supportSaleDocType));
			} else if (obj.getTransacciondocumentoList().size() > 0) {
				trans.setDocument(
						docJpaController.fromJPA(obj.getTransacciondocumentoList().get(0).getIdTipodocumento()));
			}

			trans.setDonation(new Donation(new CRBigDecimal(obj.getDonacion().doubleValue())));
			trans.setFiscalId(obj.getNumeroFiscal());
			trans.setNumber(String.valueOf(obj.getNumero()));
			trans.setId(obj.getId());
			ArrayList<Payment> paymentsList = new ArrayList<Payment>();
			BigDecimal totalpagado = BigDecimal.ZERO;
			for (Transaccionformadepago paymentMethod : obj.getTransaccionformadepagoList()) {
				if (ActiveValues.get(String.valueOf(paymentMethod.getEstaactivo())).getValue()) {
					totalpagado = totalpagado.add(paymentMethod.getMontoMonedaLocal());
					paymentsList.add(jpaPaymentMethod.fromJPA(paymentMethod));
				}
			}
			trans.setPayments(paymentsList);
			trans.setPosId(String.valueOf(obj.getIdSesion().getIdCaja().getId()));
			if (obj.getIdSerialimpresora() != null) {
				trans.setPrinterSerial(obj.getIdSerialimpresora().getSerial());
			}
			if (obj.getIdRolloauditoria() != null) {
				trans.setIdPaperRoll(obj.getIdRolloauditoria().getId());
			}
			trans.setSaleOrigin(Source.get(obj.getOrigenTransaccion()));
			trans.setStatus(String.valueOf(obj.getEstado()));

			trans.setTransactionType(SourceTransactionType.get(obj.getTipo()));
			if (obj.getNumeroIdentificacionCliente() != null) {
				trans.setClient(jpaClientController.fromJpa(obj.getNumeroIdentificacionCliente()));
			}
			trans.setUser(UsuarioJpaController.fromJpa(obj.getIdUsuario()));

			HashMap<Integer, DeliveryCondition> deliveryInfo = new HashMap<Integer, DeliveryCondition>();
			TipodescuentoJpaController tipoDescuentoJpaController = new TipodescuentoJpaController(this.emf);

			Article tmpArticle = null;
			for (Transaccionarticulo transaccionArticulo : obj.getTransaccionarticuloList()) {

				Article article = jpaArticleController.fromJPA(transaccionArticulo);
				tmpArticle = article;
				trans.addArticle(article);
				if (article.getDeliveryCondition() != null) {
					deliveryInfo.put(article.getDeliveryCondition().getId(), article.getDeliveryCondition());
				}
			}
			trans.setDeliveryInfo(deliveryInfo);
			List<Transacciondocumento> documentList = obj.getTransacciondocumentoList();
			for (Transacciondocumento transaccionDocumento : documentList) {
				TransactionDocument transDocument = jpaTransactionDocument.fromJPA(transaccionDocumento);
				trans.getDocuments().add(transDocument);
			}
			trans.setTaxAmount(new CRBigDecimal(obj.getTotalImpuesto().doubleValue()));
			trans.setTaxes(new HashMap());
			trans.setTotalCost(new CRBigDecimal(obj.getTotalTransaccion().doubleValue()));
			trans.setTotalPay(new CRBigDecimal(totalpagado.doubleValue()));// +obj.getVuelto().doubleValue()+obj.getDonacion().doubleValue()));
			if (obj.getIdAnulaciontransaccion() != null) {
				trans.setIdCancellation(obj.getIdAnulaciontransaccion().getId());
			}
			if (tmpArticle != null) {
				trans.setDeliveryCondition(tmpArticle.getDeliveryCondition());
			} else {
				trans.setDeliveryCondition(new DeliveryCondition(1, "CAJA", "C"));
			}

			if (obj.getIdComprobantefiscalpreimpreso() != null) {
				trans.setResolution(resolutionController.fromJPA(obj.getIdComprobantefiscalpreimpreso()));
			}
		} else {
			trans = null;
		}
		return trans;
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param obj
	 *            Transaccion
	 * @param getIdExoneradotipoNull
	 * @return Transaction
	 */
	public Transaction fromJPA(Transaccion obj, long getIdExoneradotipoNull) {

		TipodocumentoJpaController docJpaController = new TipodocumentoJpaController(emf);
		Transaction trans = new Transaction();
		TipodescuentoJpaController discountJpaController = new TipodescuentoJpaController(this.emf);
		ComprobantefiscalpreimpresoJpaController resolutionController = new ComprobantefiscalpreimpresoJpaController(
				this.emf);
		String rbtdsc = "";
		if (obj != null && obj.getId() > 0) {
			trans.setDate(obj.getFecha());
			trans.setPosId(String.valueOf(obj.getIdSesion().getIdCaja().getId()));
			trans.setDeliveryInfo(null);
			Tipodocumento supportSaleDocType = null;
			for (Transacciondocumento doc : obj.getTransacciondocumentoList()) {
				if (ActiveValues.get(String.valueOf(doc.getIdTipodocumento().getEssoporteventa())).getValue()) {
					supportSaleDocType = doc.getIdTipodocumento();
					break;
				}
			}

			if (supportSaleDocType != null) {
				trans.setDocument(docJpaController.fromJPA(supportSaleDocType));
			} else if (obj.getTransacciondocumentoList().size() > 0) {
				trans.setDocument(
						docJpaController.fromJPA(obj.getTransacciondocumentoList().get(0).getIdTipodocumento()));
			}

			trans.setDonation(new Donation(new CRBigDecimal(obj.getDonacion().doubleValue())));
			trans.setFiscalId(obj.getNumeroFiscal());
			trans.setNumber(String.valueOf(obj.getNumero()));
			trans.setId(obj.getId());
			ArrayList<Payment> paymentsList = new ArrayList<Payment>();
			BigDecimal totalpagado = BigDecimal.ZERO;
			for (Transaccionformadepago paymentMethod : obj.getTransaccionformadepagoList()) {
				if (ActiveValues.get(String.valueOf(paymentMethod.getEstaactivo())).getValue()) {
					totalpagado = totalpagado.add(paymentMethod.getMontoMonedaLocal());
					paymentsList.add(jpaPaymentMethod.fromJPA(paymentMethod));
				}
			}
			trans.setPayments(paymentsList);
			trans.setPosId(String.valueOf(obj.getIdSesion().getIdCaja().getId()));
			if (obj.getIdSerialimpresora() != null) {
				trans.setPrinterSerial(obj.getIdSerialimpresora().getSerial());
			}
			if (obj.getIdRolloauditoria() != null) {
				trans.setIdPaperRoll(obj.getIdRolloauditoria().getId());
			}
			trans.setSaleOrigin(Source.get(obj.getOrigenTransaccion()));
			trans.setStatus(String.valueOf(obj.getEstado()));

			trans.setTransactionType(SourceTransactionType.get(obj.getTipo()));
			if (obj.getNumeroIdentificacionCliente() != null) {
				trans.setClient(
						jpaClientController.fromJpa(obj.getNumeroIdentificacionCliente(), getIdExoneradotipoNull));
			}
			trans.setUser(UsuarioJpaController.fromJpa(obj.getIdUsuario()));

			HashMap<Integer, DeliveryCondition> deliveryInfo = new HashMap<Integer, DeliveryCondition>();
			TipodescuentoJpaController tipoDescuentoJpaController = new TipodescuentoJpaController(this.emf);

			Article tmpArticle = null;
			for (Transaccionarticulo transaccionArticulo : obj.getTransaccionarticuloList()) {

				Article article = jpaArticleController.fromJPA(transaccionArticulo);
				tmpArticle = article;
				trans.addArticle(article);
				if (article.getDeliveryCondition() != null) {
					deliveryInfo.put(article.getDeliveryCondition().getId(), article.getDeliveryCondition());
				}
			}
			// //System.err.println(trans.getRebateDesc());
			trans.setDeliveryInfo(deliveryInfo);
			List<Transacciondocumento> documentList = obj.getTransacciondocumentoList();
			for (Transacciondocumento transaccionDocumento : documentList) {
				TransactionDocument transDocument = jpaTransactionDocument.fromJPA(transaccionDocumento);
				// System.out.println("Añandiendo documento --*-->
				// "+transDocument.getDocumentText());
				trans.getDocuments().add(transDocument);
			}
			trans.setTaxAmount(new CRBigDecimal(obj.getTotalImpuesto().doubleValue()));
			trans.setTaxes(new HashMap());
			trans.setTotalCost(new CRBigDecimal(obj.getTotalTransaccion().doubleValue()));
			// trans.setTotalPay(new
			// CRBigDecimal(obj.getTotalTransaccion().doubleValue()+obj.getVuelto().doubleValue()+obj.getDonacion().doubleValue()));
			trans.setTotalPay(new CRBigDecimal(totalpagado.doubleValue()));// +obj.getVuelto().doubleValue()+obj.getDonacion().doubleValue()));
			if (obj.getIdAnulaciontransaccion() != null) {
				trans.setIdCancellation(obj.getIdAnulaciontransaccion().getId());
			}
			if (tmpArticle != null) {
				trans.setDeliveryCondition(tmpArticle.getDeliveryCondition());
			} else {
				trans.setDeliveryCondition(new DeliveryCondition(1, "CAJA", "C"));
			}

			if (obj.getIdComprobantefiscalpreimpreso() != null) {
				trans.setResolution(resolutionController.fromJPA(obj.getIdComprobantefiscalpreimpreso()));
			}
		} else {
			trans = null;
		}
		return trans;
	}

	/**
	 * Method findTransactionByStore_Pos_Id_Date.
	 * 
	 * @param posId
	 *            String
	 * @param numero
	 * @param getIdExoneradotipoNull
	 */
	public Transaction findTransactionByPos_Id(String posId, String numero, long getIdExoneradotipoNull) {
		Transaction result = null;

		EntityManager em = jpaTransactionController.getEntityManager();
		try {

			Query query = em.createQuery(
					"select t from Transaccion t where t.idSesion.idCaja.id = :posId and t.numero = :numero and t.estado = '"
							+ TransactionState.COMPLETE.getValue() + " ' and t.tipo='"
							+ SourceTransactionType.Sale.getValue() + "'");

			query.setParameter("posId", new Long(posId));
			query.setParameter("numero", new Long(numero));

			query.setMaxResults(1);
			Transaccion tmp = (Transaccion) query.getSingleResult();
			result = fromJPA(tmp, getIdExoneradotipoNull);
			em.clear();
		} catch (javax.persistence.NoResultException ex) {
			result = null;
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return result;
	}

	/**
	 * Method findTransaccionWatingSale.
	 * 
	 * @param value
	 *            Date
	 * @param estado
	 *            String
	 * @param getIdExoneradotipoNull
	 * @return List<Transaction>
	 */
	public List<Transaction> findTransaccionWatingSale(Date value, String estado, long getIdExoneradotipoNull) {
		Date fechaInicial = cleanDate(value, true);// cal.getTime();
		Date fechaFinal = cleanDate(value, false);// cal.getTime();
		EntityManager em = jpaTransactionController.getEntityManager();
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		try {
			Query query = em.createQuery(
					"select t from Transaccion t where t.estado = :estado and t.fecha >= :fechaInicial AND t.fecha <= :fechaFinal");

			query.setParameter("fechaInicial", fechaInicial);
			query.setParameter("fechaFinal", fechaFinal);
			query.setParameter("estado", estado.charAt(0));

			List<crjpa400.Transaccion> transList = query.getResultList();
			for (crjpa400.Transaccion tmp : transList) {
				transactionList.add(fromJPAWaitingSale(tmp, getIdExoneradotipoNull));
			}
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return transactionList;
	}

	/**
	 * Method fromJPAWaitingSale.
	 * 
	 * @param obj
	 *            crjpa400.Transaccion
	 * @param getIdExoneradotipoNull
	 * @return Transaction
	 */
	public Transaction fromJPAWaitingSale(Transaccion obj, long getIdExoneradotipoNull) {

		TipodocumentoJpaController docJpaController = new TipodocumentoJpaController(emf);
		Transaction trans = new Transaction();

		trans.setPosId(obj.getIdSesion().getIdCaja().getId().toString());

		if (obj != null && obj.getId() > 0) {
			trans.setDate(obj.getFecha());
			trans.setDeliveryInfo(null);

			crjpa400.Tipodocumento supportSaleDocType = null;
			for (crjpa400.Transacciondocumento doc : obj.getTransacciondocumentoList()) {
				if (ActiveValues.get(String.valueOf(doc.getIdTipodocumento().getEssoporteventa())).getValue()) {
					supportSaleDocType = doc.getIdTipodocumento();
					break;
				}
			}
			if (supportSaleDocType != null) {
				trans.setDocument(docJpaController.fromJPA(supportSaleDocType));
			}

			trans.setDonation(new Donation(new CRBigDecimal(obj.getDonacion().doubleValue())));
			trans.setFiscalId(obj.getNumeroFiscal());
			trans.setNumber(String.valueOf(obj.getNumero()));
			trans.setId(obj.getId());

			ArrayList<Payment> paymentsList = new ArrayList<Payment>();
			trans.setPayments(paymentsList);
			trans.setPosId(String.valueOf(obj.getIdSesion().getIdCaja().getId()));

			if (obj.getIdSerialimpresora() != null) {
				trans.setPrinterSerial(obj.getIdSerialimpresora().getSerial());
			}

			trans.setSaleOrigin(Source.get(obj.getOrigenTransaccion()));
			trans.setStatus(String.valueOf(obj.getEstado()));

			OpcionJpaController opcionJpaController = new OpcionJpaController(this.emf);
			trans.setStore(opcionJpaController.findById(new Long(1)).trim());
			trans.setTransactionType(SourceTransactionType.get(obj.getTipo()));
			if (obj.getNumeroIdentificacionCliente() != null) {
				trans.setClient(
						jpaClientController.fromJpa(obj.getNumeroIdentificacionCliente(), getIdExoneradotipoNull));
			}
			trans.setUser(UsuarioJpaController.fromJpa(obj.getIdUsuario()));

			HashMap<Integer, DeliveryCondition> deliveryInfo = new HashMap<Integer, DeliveryCondition>();
			TipodescuentoJpaController tipoDescuentoJpaController = new TipodescuentoJpaController(this.emf);
			TasaimpuestotipoJpaController taxController = new TasaimpuestotipoJpaController(this.emf);

			for (crjpa400.Transaccionarticulo transaccionArticulo : obj.getTransaccionarticuloList()) {
				Article article = jpaArticleController.fromJPA(transaccionArticulo.getIdArticulo());
				article.setDeliveryCondition(DeliveryCondition.getDeliveryConditionsHash()
						.get(String.valueOf(transaccionArticulo.getCondicionEntrega())));
				DeliveryCondition cond = article.getDeliveryCondition();
				article.setDiscountType(tipoDescuentoJpaController.fromJpa(transaccionArticulo.getIdTipodescuento()));

				if (transaccionArticulo.getIdPromocion() == null
						&& !transaccionArticulo.getIdTipodescuento().getNombre().equalsIgnoreCase("N")) {
					article.getDiscountType().setPromotion(false);
				}

				article.setItems(new CRBigDecimal(transaccionArticulo.getCantidad().doubleValue()));
				article.setOriginalItemCost(new CRBigDecimal(transaccionArticulo.getMontoUnitario().doubleValue()));
				article.setItemCost(new CRBigDecimal(transaccionArticulo.getMontoBase().doubleValue()));
				article.setTaxAmount(new CRBigDecimal(transaccionArticulo.getMontoImpuesto()));
				article.setTax(
						taxController.fromJPA(transaccionArticulo.getIdTasaimpuestovalor().getIdTasaimpuestotipo()));
				trans.addArticle(article);
				if (cond != null) {
					deliveryInfo.put(cond.getId(), cond);
				}
			}

			trans.setDeliveryInfo(deliveryInfo);

			List<crjpa400.Transacciondocumento> documentList = obj.getTransacciondocumentoList();
			for (crjpa400.Transacciondocumento transaccionDocumento : documentList) {
				TransactionDocument transDocument = jpaTransactionDocument.fromJPA(transaccionDocumento);
				trans.getDocuments().add(transDocument);
			}

			List<crjpa400.Transaccionauditoria> auditList = obj.getTransaccionauditoriaList();
			for (crjpa400.Transaccionauditoria audit : auditList) {
				trans.addTransactionAudit(jpaAuditController.fromJPA(audit));
			}

			trans.setTaxAmount(new CRBigDecimal(obj.getTotalImpuesto().doubleValue()));
			trans.setTaxes(new HashMap());
			trans.setTotalCost(new CRBigDecimal(obj.getTotalTransaccion().doubleValue()));

		} else {
			trans = null;
		}
		return trans;
	}
}
