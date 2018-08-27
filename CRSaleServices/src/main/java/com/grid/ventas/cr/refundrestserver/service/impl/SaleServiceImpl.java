/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.grid.ventas.cr.refundrestserver.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.CategoryLineRetention;
import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.models.DiscountType;
import com.becoblohm.cr.models.DocumentType;
import com.becoblohm.cr.models.Money;
import com.becoblohm.cr.models.Payment;
import com.becoblohm.cr.models.PaymentMethod;
import com.becoblohm.cr.models.Retention;
import com.becoblohm.cr.models.Tax;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.models.Transaction.Source;
import com.becoblohm.cr.models.Transaction.TransactionState;
import com.becoblohm.cr.models.User;
import com.becoblohm.cr.net.models.Session;
import com.becoblohm.cr.net.request.ServicesRequest;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.becoblohm.cr.response.CRServiceResponse;
import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.cr.legacy.ClienteJpaController;
import com.epa.cr.legacy.PorcentajeimpuestoretencionJpaController;
import com.epa.cr.legacy.TasaimpuestovalorJpaController;
import com.epa.cr.legacy.TipodescuentoJpaController;
import com.epa.cr.legacy.TipodocumentoJpaController;
import com.epa.cr.legacy.TransaccionJpaController;
import com.epa.ventas.dao.CABVPTCDAO;
import com.epa.ventas.dao.ConvertidorTipoDescuentoDAO;
import com.epa.ventas.dao.MAECECDAO;
import com.epa.ventas.dao.MAEVIMPDAO;
import com.epa.ventas.dao.OPEVTSCDAO;
import com.epa.ventas.dao.TRCMOVDAO;
import com.epa.ventas.dao.TRCTOVDAO;
import com.epa.ventas.dao.TRDMOVDAO;
import com.epa.ventas.dto.CABVPTC;
import com.epa.ventas.dto.MAECEC;
import com.epa.ventas.dto.MAEVIMP;
import com.epa.ventas.dto.OPEVTSC;
import com.epa.ventas.dto.TRCMOV;
import com.epa.ventas.dto.TRCTOV;
import com.epa.ventas.dto.TRDMOV;
import com.grid.ventas.cr.refundrestclient.EPASalesServicesDispatcher;
import com.grid.ventas.cr.refundrestserver.conf.ServicesConf;
import com.grid.ventas.cr.refundrestserver.service.SaleService;

import crjpa400.Tipodescuento;

@Service
public class SaleServiceImpl implements SaleService {

    private RestTemplate restTemplateForRemoteStore = new RestTemplate();

    private static final String RESULT_NULL = "Resultado null";

    private static final Logger logger = LoggerFactory.getLogger(SaleServiceImpl.class);

    @Autowired
    private EPASalesServicesDispatcher epaSalesServicesDispatcher;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private ServicesConf servicesConf;

    @Autowired
    private Long idExoneradotipoNull;

    private OPEVTSC searchFiscalInfo(String storeId, String posId, String id) {

        OPEVTSC resultOpevtsc = null;
        try {

            info("Consultando info sobre impresora fiscal en OPEVTSC ---> ");

            if (storeId.trim().length() <= 2) {
                for (int i = storeId.trim().length(); i < 3; i++) {
                    storeId = "0" + storeId;
                }
            }

            OPEVTSCDAO PersistenceOpevtsc = null;

            if (storeId.equalsIgnoreCase(Integer.toString(servicesConf.getStoreNumber()))) {
                debug("Utilizando DS de la tienda");
                PersistenceOpevtsc = new OPEVTSCDAO(dataSource);
            } else {
                debug("utilizando DS de otra tienda");
                try {
                    PersistenceOpevtsc = new OPEVTSCDAO(dataSource);
                } catch (NumberFormatException e) {
                    logger.error(e.getMessage(), e);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }

            List resultListOpevtsc = PersistenceOpevtsc.getDataById(storeId, posId, id);

            if (resultListOpevtsc != null) {

                for (int i = 0; i < resultListOpevtsc.size(); i++) {
                    resultOpevtsc = (OPEVTSC) resultListOpevtsc.get(i);
                    info(resultOpevtsc.getTranotsc() + " " + resultOpevtsc.getSeriotsc() + " "
                            + resultOpevtsc.getNumcotsc());
                }

            } else {
                info(RESULT_NULL);
            }
            info("Saliendo de método --> searchFiscalInfo - Resultado --> " + resultOpevtsc);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return resultOpevtsc;

    }

    private MAECEC searchTransactionMaecec(String storeId, String posId, String id) {

        info("Consultando info sobre cliente asignado a la venta original en MAECEC ---> ");
        MAECEC resultMaecec = null;

        try {

            MAECECDAO PersistenceMaecec = null;

            if (storeId.equalsIgnoreCase(Integer.toString(servicesConf.getStoreNumber()))) {
                debug("utilizando DS de la tienda");
                PersistenceMaecec = new MAECECDAO(dataSource);
            } else {
                debug("utilizando DS de central");
                try {
                    PersistenceMaecec = new MAECECDAO(dataSource);
                } catch (NumberFormatException e) {
                    logger.error(e.getMessage(), e);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }

            List resultListMaecec = PersistenceMaecec.getDataById(storeId, posId, id);

            if (resultListMaecec != null) {

                for (int i = 0; i < resultListMaecec.size(); i++) {
                    resultMaecec = (MAECEC) resultListMaecec.get(i);
                    info(resultMaecec.getTransa() + " " + resultMaecec.getTipo() + " " + resultMaecec.getCodcte());
                }

                if (resultMaecec != null && resultMaecec.getTipo().equals(new BigDecimal(2))) {
                    ClienteJpaController clienteJpaController = new ClienteJpaController(entityManagerFactory);
                    Client cliente = clienteJpaController.findClienteByCashierId(resultMaecec.getCodcte().trim(),
                                                                                 idExoneradotipoNull);
                    resultMaecec.setCodcte(cliente.getIdNumber());
                }

            } else {
                info(RESULT_NULL);
            }
            info("Saliendo de método --> searchTransactionMaecec - Resultado --> " + resultMaecec);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return resultMaecec;
    }

    public CRServiceResponse<Transaction> searchOriginSale(Transaction data,
                                                           com.becoblohm.cr.net.models.Session session) {

        debug("Buscando transaccion original");
        Transaction transaction = null;
        CRServiceResponse<Transaction> result = new CRServiceResponse<Transaction>();

        if (data.getStore().equalsIgnoreCase(Integer.toString(servicesConf.getStoreNumber()))) {

            // Se busca en CR400
            transaction = searchOriginSaleOnCR400(data);

            if (transaction == null && this.servicesConf.isUseLegacy()) {

                info("Transacción no encontrada en CR400 - Se busca en Lecagy");
                transaction = searchOriginSaleOnLegacy(data);
                if (transaction == null) {
                    info("Transacción no encontrada en Legacy");
                }
            }

            if (transaction != null) {
                info("Transacción original encontrada en CR400");
                result = new CRServiceResponse<Transaction>(0, "", transaction);
            } else {
                info("Transacción no encontrada en base de datos de la tienda");
                result.setMessage("Transacción no encontrada en base de datos de la tienda");
            }

        } else {

            if (servicesConf.isRemoteSearchActive()) {
                info("-> Nro. de tienda de tr != Nro. de tienda de la sesion. Se realizara busqueda remota");
                result = searchRemoteOriginSale(data, session);
                info("-> La respuesta de la busqueda remota tiene codigo: " + result.getCode());
            }
        }
        return result;
    }

    @Override
    public CRServiceResponse<Transaction> searchRemoteOriginSale(Transaction data, Session session) {
        int nro = Integer.parseInt(data.getStore());
        info("Buscando venta original en tienda remota " + nro);
        String ip = servicesConf.getServicesURLs().get(nro);
        if (ip == null || "".equals(ip)) {
            logger.error("No se encuentra la IP de la tienda " + nro);
        }
        ServicesRequest rmiServerRequest = new ServicesRequest(ServicesRequest.ORIGINAL_SALE, data, session);
        CRServiceResponse<Transaction> response = null;
        try {
            response = (CRServiceResponse<Transaction>) epaSalesServicesDispatcher
                    .dispatch(rmiServerRequest, ip, this.servicesConf.getServerPort(), CRServiceResponse.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return response;
    }

    public Transaction searchOriginSaleOnCR400(String number, String store, String posId) {

        info("buscando transa original en CR400, tienda: " + store + " caja: " + posId + " transaccion: "
                + number);
        Transaction result = null;
        TransaccionJpaController transaccionJpaController = new TransaccionJpaController(entityManagerFactory);
        result = transaccionJpaController.findTransactionByPos_Id(posId, number, idExoneradotipoNull);
        if (result != null) {
            // result.clearIds();
            result.setStatus(TransactionState.COMPLETE.getValue());
            result.setNumber(number);
            result.setStore(store);
        }
        return result;
    }

    public Transaction searchOriginSaleOnCR400(Transaction data) {
        String number = data.getNumber();
        String store = data.getStore();
        String posId = data.getPosId();
        long code0 = 0l, code1 = 0l;
        Transaction transaction = searchOriginSaleOnCR400(number, store, posId);

        if (transaction != null) {
            for (int i = 0; i < this.servicesConf.getFdpCounterPartNotCash().size(); i++) {
                if (i == 0) {
                    code0 = Long.parseLong(this.servicesConf.getFdpCounterPartNotCash().get(i));
                } else {
                    code1 = Long.parseLong(this.servicesConf.getFdpCounterPartNotCash().get(i));
                }

            }

            for (Payment pay : transaction.getPayments()) {
                if (pay.getPayMethod().getCode() == code0) {
                    pay.getMoney().setLocalAmmount(pay.getMoney().getLocalAmmount()
                            .subtract(transaction.getDonation().getAmount()));
                    break;
                }
                if (pay.getPayMethod().getCode() == code1) {
                    pay.getMoney().setLocalAmmount(pay.getMoney().getLocalAmmount()
                            .subtract(transaction.getDonation().getAmount()));
                    break;
                }
            }
        }

        return transaction;
    }

    @Override
    public Transaction searchOriginSaleOnLegacy(Transaction data) {
        Transaction result = null;
        TRCMOV transactionHeaders = searchTransactionTrcmov(data.getStore(), data.getPosId(), data.getNumber(),
                                                            data.getDate());
        OPEVTSC fiscalInfo = searchFiscalInfo(data.getStore(), data.getPosId(), data.getNumber());
        info("buscando transa original en legacy, tienda: " + data.getStore() + " caja: " + data.getPosId()
                + " transaccion: " + data.getNumber());
        List transactionDetails = searchTransactionTrdmov(data.getStore(), data.getPosId(), data.getNumber());
        List transactionPays = searchTransactionTrctov(data.getStore(), data.getPosId(), data.getNumber());
        MAECEC transactionClient = searchTransactionMaecec(data.getStore(), data.getPosId(), data.getNumber());
        info("TransactionHeaders -> " + transactionHeaders);
        info("FiscalInfo ---------> " + fiscalInfo);
        info("TransactionDetails -> " + transactionDetails);
        info("TransactionPays ----> " + transactionPays);
        info("TransactionClient --> " + transactionClient);
        boolean clientFound = transactionClient != null;
        if (!clientFound && !this.servicesConf.isClientRequired()) {
            info("el cliente no es requerido");
            clientFound = true;
        }
        if (transactionHeaders != null && fiscalInfo != null && transactionDetails != null
                && transactionPays != null && clientFound) {
            info("Datos encontrados, iniciando recopilacion de informacion para armar venta original");
            result = new Transaction();
            try {
                // Process to search original Sale in Legacy
                result.initTransaction();
                result.setSaleOrigin(Source.Pos);
                result.setNumber(transactionHeaders.getTrscmv().toString().trim());
                result.setFiscalId(fiscalInfo.getNumcotsc().intValue());
                result.setPrinterSerial(fiscalInfo.getSeriotsc());
                Calendar date = Calendar.getInstance();
                date.setTime(transactionHeaders.getFeccmv());
                date.set(Calendar.HOUR, Integer.parseInt(transactionHeaders.getHorcmv().substring(0, 2)));
                date.set(Calendar.MINUTE, Integer.parseInt(transactionHeaders.getHorcmv().substring(2, 4)));
                date.set(Calendar.SECOND, Integer.parseInt(transactionHeaders.getHorcmv().substring(4, 6)));
                result.setDate(date.getTime());
                result.setTransactionType(Transaction.SourceTransactionType.Sale);
                result.setStore(transactionHeaders.getTdacmv().toString().trim());
                result.setPosId(transactionHeaders.getCajcmv().toString().trim());
                CRBigDecimal totalCost = CRBigDecimal.ZERO;
                CRBigDecimal totalPay = CRBigDecimal.ZERO;
                CRBigDecimal totalItems = CRBigDecimal.ZERO;
                Article auxArticle = null;
                ArrayList<Article> auxArticleList = new ArrayList<Article>();
                for (int i = 0; i < transactionDetails.size(); i++) {
                    TRDMOV actualDetail = (TRDMOV) transactionDetails.get(i);
                    totalCost = new CRBigDecimal(
                            totalCost.getValue().add((actualDetail.getPvpdmv().subtract(actualDetail.getPrvdmv()))
                                    .multiply(actualDetail.getCandmv())).doubleValue());
                    totalCost = new CRBigDecimal(totalCost.getValue()
                            .add(actualDetail.getImpdmv().multiply(actualDetail.getCandmv())).doubleValue());
                    auxArticle = new Article();
                    auxArticle.setCode(actualDetail.getCoddmv().trim());
                    auxArticle.setOriginalItemCost(new CRBigDecimal(actualDetail.getPvpdmv().doubleValue()));
                    auxArticle.setItemCost(new CRBigDecimal(
                            actualDetail.getPvpdmv().subtract(actualDetail.getPrvdmv()).doubleValue()));
                    auxArticle.setItems(new CRBigDecimal(actualDetail.getCandmv().doubleValue()));
                    auxArticle.setItemTaxedCost(new CRBigDecimal(actualDetail.getImpdmv().doubleValue()));
                    DiscountType discountType = getCR400DiscountType(actualDetail.getTipdmv());
                    auxArticle.setDiscountType(discountType);
                    totalItems = new CRBigDecimal(
                            totalItems.getValue().add(actualDetail.getCandmv()).doubleValue());
                    if (actualDetail.getImpdmv().doubleValue() == 0d) {
                        TasaimpuestovalorJpaController tasaIvaValorJpaController = new TasaimpuestovalorJpaController(
                                entityManagerFactory);
                        info("Buscando etasa impuesto excento");
                        try {
                            Tax tax = tasaIvaValorJpaController.findTasaImpExcento();
                            auxArticle.setTax(tax);
                        } catch (Exception e) {
                            info("Error al buscar la tasa de impuesto excento\n" + e.getMessage());
                        }
                    } else {
                        Tax tax = getTax(result.getStore(), auxArticle, result.getDate());
                        if (tax != null && tax.getTaxRate() != null) {
                            auxArticle.setTax(tax);
                        } else {
                            throw new Exception("no se consiguio tasa impuesto");
                        }
                    }
                    auxArticleList.add(auxArticle);
                }
                result.setTotalCost(totalCost);
                result.setArticlesCount(totalItems);
                ArrayList<Payment> auxPaymentList = new ArrayList<Payment>();
                for (int j = 0; j < transactionPays.size(); j++) {
                    TRCTOV actualPayLegacy = (TRCTOV) transactionPays.get(j);
                    totalPay = new CRBigDecimal(
                            totalPay.getValue().add(actualPayLegacy.getMontov()).doubleValue());
                    Payment actualPay = new Payment();
                    Money money = new Money();
                    money.setLocalAmmount(new CRBigDecimal(actualPayLegacy.getMontov().doubleValue()));
                    money.setCurrencyAmmount(new CRBigDecimal(actualPayLegacy.getMontov().doubleValue()));
                    actualPay.setMoney(money);
                    actualPay.setPayMethod(new PaymentMethod(actualPayLegacy.getTiptov().intValue(), "", "", 0));
                    actualPay.setDocumentNumber(String.valueOf(actualPayLegacy.getNurtov().intValue()));
                    PorcentajeimpuestoretencionJpaController retentionController = new PorcentajeimpuestoretencionJpaController(
                            entityManagerFactory);
                    Retention appliedRetention = retentionController
                            .findByPaymentMethodAndDate(actualPayLegacy.getTiptov(), date.getTime());
                    if (appliedRetention != null) {
                        info("Devolucion con retencion");
                        actualPay.setRetentionPercentage(calculateRetentionPercentage(appliedRetention,
                                                                                      auxArticleList, money));
                        actualPay.setRetencion(appliedRetention);
                        info("Porcentaje de retencion aplicado-->"
                                + actualPay.getRetentionPercentage().toString());
                    }
                    auxPaymentList.add(actualPay);
                }
                result.setTotalPay(totalPay);
                if (transactionClient != null) {
                    ClienteJpaController cjc = new ClienteJpaController(entityManagerFactory);
                    Client client = cjc.findClienteByNumberId(transactionClient.getCodcte(), idExoneradotipoNull);
                    result.setClient(client);
                }
                User user = new User(new Long(0),
                        Integer.parseInt(transactionHeaders.getCjecmv().toString().trim()), "", "");
                result.setUser(user);
                result.setArticles(auxArticleList);
                result.setPayments(auxPaymentList);
                TipodocumentoJpaController tipoDocumentoJpaController = new TipodocumentoJpaController(
                        entityManagerFactory);
                if (fiscalInfo.getDis2otsc().longValue() != 0) {
                    DocumentType documentType = tipoDocumentoJpaController
                            .findById(fiscalInfo.getDis2otsc().longValue());
                    info("Tipo documento de la venta --> " + documentType.getName());
                    result.setDocument(documentType);
                } else {
                    DocumentType documentType = tipoDocumentoJpaController.findById(1);
                    // TODO Falta arreglarlo para SV
                    info("Tipo documento de la venta --> " + documentType.getName());
                    result.setDocument(documentType);
                }

                info("Armada la venta - Saliendo del método --> searchOriginSaleOnLegacy");
            } catch (Exception e) {

                logger.error(e.getMessage(), e);
                result = null;
            }

        } else {
            info("Error al buscar la transaccion");
        }

        return result;

    }

    private DiscountType getCR400DiscountType(BigDecimal tdadmv) {
        try {

            ConvertidorTipoDescuentoDAO dao = new ConvertidorTipoDescuentoDAO();
            Long idTipoDescuento = dao.getDataByIdTrdmovJPA(String.valueOf(tdadmv.longValue()),
                                                            entityManagerFactory);
            TipodescuentoJpaController tipodescuentoJpaController = new TipodescuentoJpaController(
                    entityManagerFactory);
            Tipodescuento findTipodescuento = tipodescuentoJpaController.findTipodescuentoJPA(idTipoDescuento);
            return tipodescuentoJpaController.fromJpa(findTipodescuento);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    private CRBigDecimal calculateRetentionPercentage(Retention appliedRetention,
                                                      ArrayList<Article> transactionArticles,
                                                      Money paymentAmmount) {
        CRBigDecimal appliedPercentage = CRBigDecimal.ZERO;
        if (appliedRetention.getPorcemaxret().equals(appliedRetention.getPorcenminret())) {
            info("Porcentaje de retencion-->" + appliedRetention.getPorcemaxret().toString());
            appliedPercentage = appliedRetention.getPorcemaxret();
        } else {
            CRBigDecimal typeIValue = CRBigDecimal.ZERO;
            CRBigDecimal typeSValue = CRBigDecimal.ZERO;
            CRBigDecimal typeTValue = CRBigDecimal.ZERO;

            if (appliedRetention.getCategoryLineRetencion().size() > 0) {
                for (CategoryLineRetention categoryLine : appliedRetention.getCategoryLineRetencion()) {
                    for (Article art : transactionArticles) {
                        if (art.getLine().getId() == categoryLine.getIdLinea().getId()) {
                            typeIValue = typeIValue.add(art.getTotalTaxAmount());
                            if (art.getTotalTaxAmount().compareTo(CRBigDecimal.ZERO) > 0) {
                                typeSValue = typeSValue.add(art.getNonTaxedTotalCost());
                            }
                            typeTValue = typeTValue.add(art.getNonTaxedTotalCost());
                        }
                    }
                }
            } else {
                for (Article art : transactionArticles) {
                    typeIValue = typeIValue.add(art.getTotalTaxAmount());
                    if (art.getTotalTaxAmount().compareTo(CRBigDecimal.ZERO) > 0) {
                        typeSValue = typeSValue.add(art.getNonTaxedTotalCost());
                    }
                    typeTValue = typeTValue.add(art.getNonTaxedTotalCost());
                }
            }

            info("Tipo de retencion de retencion-->" + appliedRetention.getTipoartretencion());
            try {
                if (appliedRetention.getTipoartretencion().equalsIgnoreCase("i")) {
                    appliedPercentage = paymentAmmount.getLocalAmmount().multiply(new CRBigDecimal(100d))
                            .divide(typeIValue);
                } else if (appliedRetention.getTipoartretencion().equalsIgnoreCase("s")) {
                    appliedPercentage = paymentAmmount.getLocalAmmount().multiply(new CRBigDecimal(100d))
                            .divide(typeSValue);
                } else {
                    appliedPercentage = paymentAmmount.getLocalAmmount().multiply(new CRBigDecimal(100d))
                            .divide(typeTValue);
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            info("Porcentaje de retencion-->" + appliedRetention.getPorcemaxret().toString());
        }
        return appliedPercentage;
    }

    @Override
    public RMIServerResponse searchLastTransactionTrcmov(String storeId, String posId) {
        long trNumber = 0;

        TRCMOVDAO PersistenceTrcmov = new TRCMOVDAO(dataSource);

        List resultListTrcmov = null;
        try {
            resultListTrcmov = PersistenceTrcmov.getDataByLastTrNumberEpa(storeId, posId);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        TRCMOV resultTrcmov = null;
        if (resultListTrcmov != null) {

            for (int i = 0; i < resultListTrcmov.size(); i++) {
                resultTrcmov = (TRCMOV) resultListTrcmov.get(i);
                info(resultTrcmov.getTrscmv() + " " + resultTrcmov.getCjecmv() + " " + resultTrcmov.getTipcmv()
                        + " " + resultTrcmov.getHorcmv());
                trNumber = resultTrcmov.getTrscmv().longValue();
            }

        } else {
            info(RESULT_NULL);
        }
        info("Saliendo de método --> searchLastTransactionTrcmov - Resultado --> " + resultTrcmov);
        RMIServerResponse result = new RMIServerResponse("", trNumber, 0);
        return result;

    }

    public RMIServerResponse searchLastNumberTCCabvptc(String storeId, String posId) {
        long tcPayNumber = 0;
        debug("parametros recibidos --> tienda:" + storeId + " caja: " + posId);

        debug("Consultando Cabecera en CABVPTC ---> ");

        CABVPTCDAO PersistenceCabvptc = new CABVPTCDAO(dataSource);

        List resultListCabvptc = null;
        try {
            resultListCabvptc = PersistenceCabvptc.getDataByLastTrNumberEpa(storeId, posId);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        CABVPTC resultCabvptc = null;
        if (resultListCabvptc != null) {
            for (int i = 0; i < resultListCabvptc.size(); i++) {
                resultCabvptc = (CABVPTC) resultListCabvptc.get(i);
                info(resultCabvptc.getTRANCPTC() + " " + resultCabvptc.getCAJECPTC() + " "
                        + resultCabvptc.getCAJACPTC() + " " + resultCabvptc.getFECHCPTC());
                tcPayNumber = resultCabvptc.getTRANCPTC().longValue();
            }

        } else {
            info(RESULT_NULL);
        }
        info("Saliendo de método --> searchLastNumberTCCabvptc - Resultado --> " + resultCabvptc);
        RMIServerResponse result = new RMIServerResponse("", tcPayNumber, 0);
        return result;

    }

    private TRCMOV searchTransactionTrcmov(String storeId, String posId, String id, Date date) {

        info("Consultando Cabecera en TRCMOV ---> ");

        info("Parametros recibidos --> tienda:" + storeId + " caja: " + posId + " numero: " + id + " fecha: "
                + date);

        TRCMOV resultTrcmov = null;

        try {

            TRCMOVDAO PersistenceTrcmov = null;

            if (storeId.equalsIgnoreCase(Integer.toString(servicesConf.getStoreNumber()))) {
                debug("utilizando DS de la tienda");
                PersistenceTrcmov = new TRCMOVDAO(dataSource);
            } else {
                debug("utilizando DS de central");
                try {
                    PersistenceTrcmov = new TRCMOVDAO(dataSource);
                } catch (NumberFormatException e) {
                    logger.error(e.getMessage(), e);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }

            List resultListTrcmov = PersistenceTrcmov.getDataById_Date(storeId, posId, id, date);

            if (resultListTrcmov != null) {
                for (int i = 0; i < resultListTrcmov.size(); i++) {
                    resultTrcmov = (TRCMOV) resultListTrcmov.get(i);
                    debug(resultTrcmov.getTrscmv() + " " + resultTrcmov.getCjecmv() + " "
                            + resultTrcmov.getTipcmv() + " " + resultTrcmov.getHorcmv());
                }

            } else {
                info(RESULT_NULL);
            }
            info("Saliendo de método --> searchTransactionTrcmov - Resultado --> " + resultTrcmov);

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return resultTrcmov;
    }

    private List searchTransactionTrdmov(String storeId, String posId, String id) {

        info("Consultando Detalle en TRDMOV ---> ");
        List resultListTrdmov = null;
        try {

            TRDMOVDAO PersistenceTrdmov = null;

            if (storeId.equalsIgnoreCase(Integer.toString(servicesConf.getStoreNumber()))) {
                info("utilizando DS de la tienda");
                PersistenceTrdmov = new TRDMOVDAO(dataSource);
            } else {
                info("utilizando DS de central");
                try {
                    PersistenceTrdmov = new TRDMOVDAO(dataSource);
                } catch (NumberFormatException e) {
                    logger.error(e.getMessage(), e);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }

            TRDMOV resultTrdmov = null;

            resultListTrdmov = PersistenceTrdmov.getDataById(storeId, posId, id);

            if (resultListTrdmov != null) {

                for (int i = 0; i < resultListTrdmov.size(); i++) {
                    resultTrdmov = (TRDMOV) resultListTrdmov.get(i);
                    info(resultTrdmov.getTrsdmv() + " " + resultTrdmov.getCoddmv() + " " + resultTrdmov.getCandmv()
                            + " " + resultTrdmov.getImpdmv());
                }

            } else {
                info(RESULT_NULL);
            }
            info("Saliendo de método --> searchTransactionTrdmov - Resultado --> " + resultTrdmov);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        return resultListTrdmov;
    }

    private List searchTransactionTrctov(String storeId, String posId, String id) {

        info("Consultando Pagos en TRCTOV ---> ");
        List resultListTrctov = null;

        try {
            TRCTOVDAO PersistenceTrctov = null;
            if (storeId.equalsIgnoreCase(Integer.toString(servicesConf.getStoreNumber()))) {
                info("utilizando DS de la tienda");
                PersistenceTrctov = new TRCTOVDAO(dataSource);
            } else {
                info("utilizando DS de central");
                try {
                    PersistenceTrctov = new TRCTOVDAO(dataSource);
                } catch (NumberFormatException e) {
                    logger.error(e.getMessage(), e);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }

            TRCTOV resultTrctov = null;

            resultListTrctov = PersistenceTrctov.getDataById(storeId, posId, id);

            if (resultListTrctov != null) {

                for (int i = 0; i < resultListTrctov.size(); i++) {
                    resultTrctov = (TRCTOV) resultListTrctov.get(i);
                    info(resultTrctov.getTrstov() + " " + resultTrctov.getTiptov() + " " + resultTrctov.getBantov()
                            + " " + resultTrctov.getMontov());
                }

            } else {
                info(RESULT_NULL);
            }
            info("Saliendo de método --> searchTransactionTrctov - Resultado --> " + resultTrctov);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return resultListTrctov;
    }

    @Override
    public Tax getTax(String store, Article auxArticle, Date date) {

        Tax result = null;

        try {

            MAEVIMPDAO persistenceMaevimp = null;

            if (store.equalsIgnoreCase(Integer.toString(servicesConf.getStoreNumber()))) {
                debug("utilizando DS de la tienda");
                persistenceMaevimp = new MAEVIMPDAO(dataSource);
            } else {
                debug("utilizando DS de Central");
                try {
                    persistenceMaevimp = new MAEVIMPDAO(dataSource);
                } catch (NumberFormatException e) {
                    logger.error(e.getMessage(), e);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }

            info("Articulo code -> " + auxArticle.getCode());

            info("Buscando taza de impuesto activa para la fecha: " + date);

            List<MAEVIMP> impList = persistenceMaevimp.getDataBetweenDate(date);

            if (impList != null && !impList.isEmpty()) {
                info("Impuestos encontrados -> " + impList.size());
                if (impList.size() != 1) {
                    info("Varios impuestos activos para la misma fecha, se procede a evaluar por ingenieria inversa la alicuota mas cercana");

                    CRBigDecimal tmpTaxRate = auxArticle.getItemTaxedCost().multiply(new CRBigDecimal(100))
                            .divide(auxArticle.getItemCost());

                    CRBigDecimal minDiference = new CRBigDecimal(1000);

                    Tax tmpTax = null;

                    for (MAEVIMP maevimp : impList) {
                        CRBigDecimal tmpRate = new CRBigDecimal(maevimp.getAivamimp().doubleValue());

                        CRBigDecimal tmpDiference = tmpRate.subtract(tmpTaxRate);
                        if (tmpDiference.compareTo(CRBigDecimal.ZERO) < 0) {
                            tmpDiference = tmpDiference.negate();
                        }

                        if (tmpDiference.compareTo(minDiference) < 0) {
                            minDiference = tmpDiference;
                            tmpTax = findTaxJPA(maevimp.getFecimimp(),
                                                new CRBigDecimal(maevimp.getAivamimp().doubleValue()));
                        }

                    }

                    if (tmpTax != null) {
                        result = tmpTax;
                    }

                } else {
                    CRBigDecimal taxRate = new CRBigDecimal(
                            impList.get(impList.size() - 1).getAivamimp().doubleValue());
                    Date tmpDate = impList.get(impList.size() - 1).getFecimimp();
                    info("Se encontro una sola alicuota de impuesto ->" + taxRate + " con fecha " + tmpDate);

                    Tax tax = findTaxJPA(tmpDate, taxRate);

                    if (tax != null) {
                        result = tax;
                    } else {
                        result = this.servicesConf.getDefaultTax();
                    }

                }
            } else {
                result = this.servicesConf.getDefaultTax();
            }

            if (result != null && result.getTaxRate() != null) {
                info("Tax encontrado -> " + result.getId());
                info("Tax rate -> " + result.getTaxRate());
            } else {
                info("Tax nulo");
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    private Tax findTaxJPA(Date date, CRBigDecimal taxRate) {
        Tax result = new Tax();

        TasaimpuestovalorJpaController tasaIvaValorJpaController = new TasaimpuestovalorJpaController(
                entityManagerFactory);
        info("Buscando en JPA fecha:" + date + " rate: " + taxRate.getValue());
        try {
            result = tasaIvaValorJpaController.findTasaivaValorByDateAndRate(date, taxRate.getValue());
        } catch (Exception e) {
            info("Error al buscar la tasa de impuesto\n" + e.getMessage());
        }

        return result;
    }

    private void debug(String msg) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        }
    }

    private void info(String msg) {
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }
    }
}
