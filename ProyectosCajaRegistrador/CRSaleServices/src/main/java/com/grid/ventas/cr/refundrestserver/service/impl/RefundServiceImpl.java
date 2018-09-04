/**
 * **************************************************************************** © 2012
 * Global Retail Information Ltd.
 * ****************************************************************************
 */

package com.grid.ventas.cr.refundrestserver.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.becoblohm.cr.models.AnulDev;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.models.RefundHistory;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.models.Transaction.Source;
import com.becoblohm.cr.models.Transaction.SourceTransactionType;
import com.becoblohm.cr.net.models.Session;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.becoblohm.cr.response.CRServiceResponse;
import com.becoblohm.cr.sync.converters.ClienteConverter;
import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.cr.legacy.DevolucionJpaController;
import com.epa.cr.legacy.DevolucionarticuloJpaController;
import com.epa.cr.legacy.HistorialdevolucionJpaController;
import com.epa.cr.legacy.MotivodevolucionJpaController;
import com.epa.ventas.dao.CABVDEVDAO;
import com.epa.ventas.dao.DETVDEVDAO;
import com.epa.ventas.dao.TRCMOVDAO;
import com.epa.ventas.dto.CABVDEV;
import com.epa.ventas.dto.DETVDEV;
// import com.epa.ventas.dto.MAEVMOT;
import com.epa.ventas.dto.TRCMOV;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grid.ventas.cr.refundrestserver.conf.ServicesConf;
import com.grid.ventas.cr.refundrestserver.service.RefundService;
import com.grid.ventas.cr.refundrestserver.service.SaleService;
import com.grid.ventas.cr.refundrestserver.service.SpecialOrderService;

import crjpa400.Devolucion;
import crjpa400.Devolucionarticulo;
import crjpa400.Motivodevolucion;

@Service
public class RefundServiceImpl implements RefundService {

    private static final Logger logger = LoggerFactory.getLogger(RefundServiceImpl.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SaleService saleService;

    @Autowired
    private ServicesConf servicesConf;

    @Autowired
    private SpecialOrderService specialOrderService;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private Date startDate = new Date();

    private Date endDate;

    public ServicesResponse checkServicesRefundCompleted(AnulDev data) {
        debug("Actualizando devolucion procesada");
        AnulDev anulDev = null;
        anulDev = executeDevCompleted(data);
        ServicesResponse response = null;
        if (anulDev != null) {
            response = new ServicesResponse("", anulDev);
            response.setCode(0);
        }
        return response;
    }

    public AnulDev executeDevInProcess(AnulDev dev) {
        /**
         * Se consulta si aun se usa las tablas de legacy, si se usan se actualizan el
         * estatus de la devolucion. Este cambio se hizo para la apertura de T4 GT, ya que
         * T4 es una base de datos MySQL se crearon dos nuevas tablas de devoluciones que
         * se homologan para T2 y T3
         */
        if (this.servicesConf.isUseLegacy()) {
            changeDevStatus(dev, "X", "P");
        }

        return changeDevStatusNew(dev, Character.valueOf('I'), Character.valueOf('P'));
    }

    public AnulDev executeDevCompleted(AnulDev dev) {
        HistorialdevolucionJpaController history = new HistorialdevolucionJpaController(entityManagerFactory);
        crjpa400.HistorialdevolucionJpaController cr400 = new crjpa400.HistorialdevolucionJpaController(
                entityManagerFactory);
        cr400.create(history.toJPA(dev));

        /**
         * Se consulta si aun se usa las tablas de legacy, si se usan se actualizan el
         * estatus de la devolucion. Este cambio se hizo para la apertura de T4 GT, ya que
         * T4 es una base de datos MySQL se crearon dos nuevas tablas de devoluciones que
         * se homologan para T2 y T3
         */
        if (this.servicesConf.isUseLegacy()) {
            changeDevStatus(dev, "P", "C");
        }
        return changeDevStatusNew(dev, Character.valueOf('P'), Character.valueOf('D'));
    }

    public AnulDev changeDevStatusNew(AnulDev dev, Character fromStatus, Character toStatus) {

        info("Cambiando estatus de devolucion de " + fromStatus + " a " + toStatus);
        Devolucion devHeaders = null;
        boolean devHeadersUpdate = false;

        DevolucionJpaController persistenceHeader = new DevolucionJpaController(entityManagerFactory);

        boolean updateStatus = false;
        try {
            devHeaders = searchDevHeader(dev.getOriginalSale().getStore(), dev.getOriginalSale().getPosId(),
                                         dev.getOriginalSale().getNumber(), fromStatus);

            for (Devolucionarticulo devArticle : devHeaders.getDevolucionarticuloList()) {
                devArticle.setEstatusDev(toStatus);
            }

            devHeaders.setIdTransaccion(BigInteger.valueOf(dev.getId()));
            devHeaders.setEstatusDev(toStatus);

            updateStatus = persistenceHeader.updateStatusHeader(fromStatus, toStatus, devHeaders);
        } catch (Exception e) {
            logger.error("ERROR -->> ", e);
        }

        if (updateStatus) {
            return new AnulDev();
        } else {
            return null;
        }

    }

    public AnulDev changeDevStatus(AnulDev dev, String fromStatus, String toStatus) {
        CABVDEV devHeaders = null;
        try {
            devHeaders = searchDevCabvdev(dev.getOriginalSale().getStore(), dev.getOriginalSale().getPosId(),
                                          dev.getOriginalSale().getNumber(), fromStatus);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if (devHeaders == null) {
            return null;
        }

        String storeId = dev.getStore();
        for (int i = storeId.length(); i < 3; i++) {
            storeId = "0" + storeId;
        }

        Collection<Article> articles = dev.getArticles();
        StringBuilder artCodes = new StringBuilder();
        for (Iterator<Article> iterator = articles.iterator(); iterator.hasNext();) {
            Article article = iterator.next();
            artCodes.append(article.getCode());
            if (iterator.hasNext()) {
                artCodes.append(",");
            }
        }

        DETVDEVDAO persistenceDetvdev = new DETVDEVDAO(dataSource);

        debug("Cambiando estado de devolucion de " + fromStatus + " a " + toStatus);

        try {
            boolean updateStatus = persistenceDetvdev
                    .updateStatddevByArticles(devHeaders.getCorrcdev(), dev.getOriginalSale().getStore(),
                                              dev.getOriginalSale().getPosId(), dev.getOriginalSale().getNumber(),
                                              artCodes.toString(), fromStatus, toStatus);
            if (updateStatus) {
                return new AnulDev();
            }

            return null;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }

    public ServicesResponse checkServicesRefund(Transaction subject, com.becoblohm.cr.net.models.Session session) {
        debug("Chequeando historial de transacción");

        ServicesResponse response = null;

        CRServiceResponse<Transaction> crResponse = this.saleService.searchOriginSale(subject, session);

        if (crResponse.isOk()) {

            AnulDev anulDev = null;
            ObjectMapper objMapper = new ObjectMapper();
            Transaction tr = objMapper.convertValue(crResponse.getPayload(), Transaction.class);

            if (servicesConf.isNewRefundTable()) {
                anulDev = searchDevOnNewTable(tr, subject.getTransactionType());
            } else {
                anulDev = searchDev(tr, subject.getTransactionType());
            }

            debug("Respondiendo peticion historial de transacción");
            response = new ServicesResponse("", anulDev);
            if (anulDev != null) {

                // 01/03/2018
                // Se comenta la busqueda en CBVPEA ya que la funcionalidad de Pedidos
                // especiales no esta culminada, es una busqueda innecesaria
                //
                // if
                // (this.specialOrderService.searchTransactionInCabvpea(anulDev.getOriginalSale())
                // != null) {
                // anulDev.setFromSpecialOrder(true);
                // } else {
                // anulDev.setFromSpecialOrder(false);
                // }

                // 14-01-2013 Se agrega implementacion de metodos SynConverter
                // para mandar la data homologada a la caja
                ClienteConverter converter = new ClienteConverter();
                try {
                    anulDev.setClient(converter.fromServer(anulDev.getClient(), entityManagerFactory, null));
                } catch (Exception e) {
                    response.setCode(-1);
                    response.setMsg("Error interno del servicio de devoluciones");
                    logger.error("Problemas al convertir cliente con SyncConverter->", e);
                }
                response.setCode(0);
            } else {
                CRBigDecimal amount = CRBigDecimal.ZERO;
                amount = validateIfReturned(tr);
                debug("Validating if the transaction tr: " + tr.getNumber() + " pos: " + tr.getPosId() + " store: "
                        + tr.getStore() + " was returned");
                if (amount != null) {
                    debug("The transaction was returned");
                    if (amount.equals(CRBigDecimal.ZERO)) {
                        response.setCode(-1);
                        response.setMsg("La transacción ya fue devuelta en su totalidad.");
                    }
                } else {
                    response.setCode(-1);
                    response.setMsg("No se encontró devolución cargada para esta transacción");
                }
            }

        } else {
            response = new ServicesResponse(crResponse.getMessage(), null);
        }
        return response;
    }

    private AnulDev searchDev(Transaction originalSale, SourceTransactionType sourceTransactionType) {

        info("Ejecutando searchDev para una transaccion del tipo " + sourceTransactionType.getValue());
        AnulDev result = null;
        HashMap<Integer, String> type = new HashMap<Integer, String>();

        String status = "X";
        if (sourceTransactionType.equals(SourceTransactionType.Cancellation)) {
            status = null;
        }

        CABVDEV devHeaders = searchDevCabvdev(originalSale.getStore(), originalSale.getPosId(),
                                              originalSale.getNumber(), status);
        List devDetails = null;
        if (devHeaders != null) {

            devDetails = searchDevDetvdev(originalSale.getStore(), originalSale.getPosId(),
                                          originalSale.getNumber(), devHeaders.getCorrcdev().toString().trim(),
                                          false, sourceTransactionType);

            if (!devDetails.isEmpty()) {
                result = buildAnulDev(originalSale, devHeaders, devDetails, type);
            } else {
                info("No se encontraron articulos para devolver en esta venta");
                return null;
            }
        }
        return result;
    }

    /**
     * Search dev on new table.
     *
     * @param originalSale the original sale
     * @param sourceTransactionType the source transaction type
     * @return the anul dev
     */
    private AnulDev searchDevOnNewTable(Transaction originalSale, SourceTransactionType sourceTransactionType) {
        info("Ejecutando searchDevOnNewTable para una transaccion del tipo " + sourceTransactionType.getValue());
        AnulDev result = null;
        Map<Integer, String> type = new HashMap<Integer, String>();
        Character status = Character.valueOf('I');

        Devolucion devHeader = searchDevHeader(originalSale.getStore(), originalSale.getPosId(),
                                               originalSale.getNumber(), status);
        List<Devolucionarticulo> devDetails = null;
        if (devHeader != null) {
            devDetails = searchDevDetails(devHeader, status, sourceTransactionType);
        }

        if (!devDetails.isEmpty()) {
            result = buildAnulDevFromNewTable(originalSale, devDetails, type);
        } else {
            debug("No se encontraron articulos para devolver en esta venta");
            return null;
        }

        return result;
    }

    /**
     * Este método busca la cabecera de la transacción que se va a devolver en tabla
     * CR400.DEVOLUCION. La devolución debe estar en estatus "I" de
     * <strong>Inicial</strong> para poder ser recuperada y devuelta en la caja.
     * <strong>Estatus:</strong> "I" Inicial, "P" Proceso, "D" Devuelta.
     * 
     * @author eve0018536@epa.com (Mario Ortega)
     * 
     */
    private Devolucion searchDevHeader(String storeId, String posId, String id, Character status) {
        info("Consultando info sobre devolucion en CR400.DEVOLUCION ---> ");
        info("con datos: tienda --> " + storeId + " caja --> " + posId + " tr --> " + id);

        Devolucion resultRefund = new Devolucion();
        resultRefund.setId(Long.valueOf(0));

        int store = Integer.parseInt(storeId);
        int pos = Integer.parseInt(posId);
        int tr = Integer.parseInt(id);

        List<Devolucion> resultListRefund = new ArrayList<Devolucion>();

        try {
            DevolucionJpaController persistenceDev = new DevolucionJpaController(entityManagerFactory);
            resultListRefund = persistenceDev.findTransactionToRefund(store, pos, tr, status);
            info("Cantidad de Devoluciones Cargadas -> " + resultListRefund.size());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if (!resultListRefund.isEmpty()) {

            for (int i = 0; i < resultListRefund.size(); i++) {

                if (resultRefund.getId().compareTo(resultListRefund.get(i).getId()) < 0) {
                    resultRefund = resultListRefund.get(i);
                }

            }

        } else {
            info("No se encontro devolución cargada en CR400.DEVOLUCION");
        }

        return resultRefund;
    }

    /**
     * Este método busca el detalle de la transacción que se va a devolver en tabla
     * CR400.DEVOLUCIONARTICULO. Los detalles de la devolución deben estar en estatus "I"
     * de <strong>Inicial</strong> para poder ser recuperada y devuelta en la caja.
     * <strong>Estatus:</strong> "I" Inicial, "P" Proceso, "D" Devuelta.
     * 
     * @author eve0018536@epa.com (Mario Ortega)
     */
    private List<Devolucionarticulo> searchDevDetails(Devolucion id, Character status,
                                                      SourceTransactionType source) {

        info("Consultando info sobre devolucion en CR400.DEVOLUCIONARTICULO ");

        DevolucionarticuloJpaController persistenceDetails = new DevolucionarticuloJpaController(
                entityManagerFactory);
        List<Devolucionarticulo> resultListDetails = null;

        if (source == SourceTransactionType.Cancellation) {
            status = Character.valueOf('D');
        }

        try {
            resultListDetails = persistenceDetails.findRefundDetails(id, status);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        if (resultListDetails != null) {
            info("Cantidad de articulos: " + resultListDetails.size());
            return resultListDetails;
        } else {
            return Collections.emptyList();
        }

    }

    /**
     * Este método se encarga de la construcción del objeto AnulDev que sera retornado a
     * la caja con los datos de la transacción original y los articulos a devolver. Existe
     * otro método similar pero la diferencia es que este nuevo arma los detalles a
     * devolver de las nuevas tablas de devolución en el servidor.
     * <strong>CR400.DEVOLUCION y CR400.DEVOLUCIONARTICULO</strong>
     * 
     * @author eve0018536@epa.com (Mario Ortega)
     */
    public AnulDev buildAnulDevFromNewTable(Transaction originalSale, List<Devolucionarticulo> devDetails,
                                            Map<Integer, String> type) {

        AnulDev result = new AnulDev();
        result.setDate(new Date());
        result.setSaleOrigin(Source.Refund);
        CRBigDecimal articlesCount = CRBigDecimal.ZERO;
        ArrayList<Article> auxArticleList = new ArrayList<Article>();

        result.setAmountToRefund(searchAmountToRefund(originalSale));

        for (int i = 0; i < devDetails.size(); i++) {
            articlesCount = makeArticlesListFromNewTable(originalSale, type, devDetails, articlesCount,
                                                         auxArticleList, i);
        }

        result.setMotiveDescription("");
        for (Iterator<Integer> iterator = type.keySet().iterator(); iterator.hasNext();) {
            int desc = iterator.next();

            result.setMotiveDescription(result.getMotiveDescription() + type.get(desc));
            if (type.size() > 1 && !type.get(desc).equalsIgnoreCase(type.get(type.size()))) {
                result.setMotiveDescription(result.getMotiveDescription() + ", ");
            }
        }

        info("Motivo(s) de devolución: '" + result.getMotiveDescription() + "' ");

        result.setClient(originalSale.getClient());
        result.setArticles(auxArticleList);
        result.setArticlesCount(articlesCount);
        result.setOriginalSale(originalSale);

        return result;

    }

    /**
     * Este método se encarga de armar la lista de los articulos que se van a devolver y
     * retorna la cantidad de articulos en <strong>CRBigDecimal</strong>
     * 
     * @author eve0018536@epa.com (Mario Ortega)
     */
    private CRBigDecimal makeArticlesListFromNewTable(Transaction originalSale, Map<Integer, String> type,
                                                      List<Devolucionarticulo> devDetails,
                                                      CRBigDecimal articlesCount,
                                                      ArrayList<Article> auxArticleList, int i) {

        Article auxArticle = new Article();
        Devolucionarticulo actualDetails = devDetails.get(i);

        info("Armando detalle para el articulo cod. " + actualDetails.getCodigoArticulo());
        articlesCount = new CRBigDecimal(
                articlesCount.getValue().add(new BigDecimal(actualDetails.getCantidadArticulo())));

        auxArticle.setDeliveryCondition(originalSale.getDeliveryCondition());
        auxArticle.setCode(Long.toString(actualDetails.getCodigoArticulo()));
        auxArticle.setDiscountType(originalSale
                .getArticleByTransactionArticleId(actualDetails.getIdTransaccionarticulo()).getDiscountType());
        auxArticle.setTax(originalSale.getArticleByTransactionArticleId(actualDetails.getIdTransaccionarticulo())
                .getTax());
        auxArticle.setOriginalItemCost(originalSale
                .getArticleByTransactionArticleId(actualDetails.getIdTransaccionarticulo()).getOriginalItemCost());
        auxArticle.setItemCost(originalSale
                .getArticleByTransactionArticleId(actualDetails.getIdTransaccionarticulo()).getItemCost());
        auxArticle.setItems(new CRBigDecimal(actualDetails.getCantidadArticulo()));
        auxArticle.setRebateAmountNoCalcule(originalSale
                .getArticleByTransactionArticleId(actualDetails.getIdTransaccionarticulo()).getRebateAmount());
        auxArticle.setMotive((int) actualDetails.getIdMotivodevolucion());

        auxArticle
                .setDescriptionMotive(getMotiveDescription(Long.toString(actualDetails.getIdMotivodevolucion())));

        if (!type.containsKey(auxArticle.getMotive())) {
            info("Se agrega al listado de motivos de la devolución el motivo: "
                    + auxArticle.getDescriptionMotive());
            type.put(auxArticle.getMotive(), auxArticle.getDescriptionMotive());
        }
        auxArticleList.add(auxArticle);

        return articlesCount;

    }

    @Override
    public AnulDev buildAnulDev(Transaction originalSale, CABVDEV devHeaders, List devDetails,
                                HashMap<Integer, String> type) {
        AnulDev result = new AnulDev();
        result.setDate(new Date());
        result.setSaleOrigin(Source.Refund);
        CRBigDecimal articlesCount = CRBigDecimal.ZERO;
        ArrayList<Article> auxArticleList = new ArrayList<Article>();

        result.setAmountToRefund(searchAmountToRefund(originalSale));

        for (int i = 0; i < devDetails.size(); i++) {
            articlesCount = makeArticlesList(originalSale, type, devDetails, articlesCount, auxArticleList, i);
        }

        result.setMotiveDescription("");
        for (Iterator<Integer> iterator = type.keySet().iterator(); iterator.hasNext();) {
            int desc = iterator.next();

            result.setMotiveDescription(result.getMotiveDescription() + type.get(desc));
            if (type.size() > 1 && !type.get(desc).equalsIgnoreCase(type.get(type.size()))) {
                result.setMotiveDescription(result.getMotiveDescription() + ", ");
            }
        }
        debug("---> '" + result.getMotiveDescription() + "'");

        if (originalSale.getClient() == null || originalSale.getClient().getIdNumber() == null) {
            debug("transaccion original viene sin cliente, se le asigna el cliente cargado en la devolucion por AS400 --> "
                    + devHeaders.getAfilcdev());
            Client client = new Client("", devHeaders.getAfilcdev(), "", "", 0);
            originalSale.setClient(client);
        }

        result.setClient(originalSale.getClient());
        result.setArticles(auxArticleList);
        result.setArticlesCount(articlesCount);
        result.setOriginalSale(originalSale);
        return result;
    }

    private CRBigDecimal makeArticlesList(Transaction originalSale, HashMap<Integer, String> type, List devDetails,
                                          CRBigDecimal articlesCount, ArrayList<Article> auxArticleList, int i) {
        Article auxArticle;
        DETVDEV actualDetail = (DETVDEV) devDetails.get(i);

        debug("armando detalle para articulo cod. " + actualDetail.getArtiddev().trim());
        articlesCount = new CRBigDecimal(articlesCount.getValue().add(actualDetail.getCantddev()).doubleValue());

        auxArticle = new Article();

        auxArticle.setDeliveryCondition(originalSale.getDeliveryCondition());
        auxArticle.setCode(actualDetail.getArtiddev().trim());

        auxArticle.setDiscountType(originalSale.getArticleByCode(auxArticle.getCode()).getDiscountType());

        auxArticle.setTax(originalSale.getArticleByCode(auxArticle.getCode()).getTax());
        auxArticle.setOriginalItemCost(originalSale.getArticleByCode(auxArticle.getCode()).getOriginalItemCost());
        auxArticle.setItemCost(originalSale.getArticleByCode(auxArticle.getCode()).getItemCost());
        auxArticle.setItems(new CRBigDecimal(actualDetail.getCantddev().doubleValue()));

        auxArticle.setRebateAmountNoCalcule(originalSale.getArticleByCode(auxArticle.getCode()).getRebateAmount());

        auxArticle.setMotive(Integer.parseInt(actualDetail.getMotiddev().toString().trim()));

        auxArticle.setDescriptionMotive(getMotiveDescription(actualDetail.getMotiddev().toString().trim()));
        if (!type.containsKey(auxArticle.getMotive())) {
            debug("agregando nuevo motivo encontrado en la devolucion al listado --> "
                    + auxArticle.getDescriptionMotive());
            type.put(auxArticle.getMotive(), auxArticle.getDescriptionMotive());
        }

        auxArticleList.add(auxArticle);
        return articlesCount;
    }

    private CABVDEV searchDevCabvdev(String storeId, String posId, String id) {
        return searchDevCabvdev(storeId, posId, id, "X");
    }

    @Override
    public CABVDEV searchDevCabvdev(String storeId, String posId, String id, String status) {

        for (int i = storeId.length(); i < 3; i++)
            storeId = "0" + storeId;

        for (int i = posId.length(); i < 3; i++)
            posId = "0" + posId;

        info("Consultando info sobre devolucion en CABVDEV ---> ");
        info("con datos: tienda --> " + storeId + " caja --> " + posId + " tr --> " + id);

        CABVDEV resultCabvdev = new CABVDEV();
        resultCabvdev.setCorrcdev(BigDecimal.ZERO);

        List resultListCabvdev = null;
        try {
            CABVDEVDAO persistenceCabvdev = new CABVDEVDAO(dataSource);
            resultListCabvdev = persistenceCabvdev.getDataToRefundById(storeId, posId, id, status);
            info("Cantidad de resultados -> " + resultListCabvdev.size());
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if (resultListCabvdev != null) {
            for (int i = 0; i < resultListCabvdev.size(); i++) {

                info(resultCabvdev.getCorrcdev() + " " + resultCabvdev.getTiedcdev() + " "
                        + resultCabvdev.getCajacdev() + " " + resultCabvdev.getTrancdev());
                if (resultCabvdev.getCorrcdev()
                        .compareTo(((CABVDEV) resultListCabvdev.get(i)).getCorrcdev()) < 0) {
                    info("Se usa: " + ((CABVDEV) resultListCabvdev.get(i)).getCorrcdev());
                    resultCabvdev = (CABVDEV) resultListCabvdev.get(i);
                }
            }

        } else {
            info("Resultado NULL - No se encontro devolución de CABVDEV");
        }
        return resultCabvdev;
    }

    @Override
    public List<DETVDEV> searchDevDetvdev(String storeId, String posId, String id, String corrc,
                                          boolean isFinishProcess, SourceTransactionType source) {

        boolean statddevUpdated = true;

        for (int i = storeId.length(); i < 3; i++)
            storeId = "0" + storeId;

        for (int i = posId.length(); i < 3; i++)
            posId = "0" + posId;

        debug("Consultando info sobre devolucion en DETVDEV ---> ");

        DETVDEVDAO persistenceDetvdev = new DETVDEVDAO(dataSource);
        DETVDEV resultDetvdev = null;
        List<DETVDEV> resultListDetvdev = null;
        try {
            if (source == SourceTransactionType.Cancellation) {
                resultListDetvdev = persistenceDetvdev.getDataById_CorrddevToCancell(storeId, posId, id, corrc);
            }
            if (source == SourceTransactionType.Refund) {
                resultListDetvdev = persistenceDetvdev.getDataById_CorrddevToRefund(storeId, posId, id, corrc);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        if (resultListDetvdev != null) {

            if (!resultListDetvdev.isEmpty()) {
                for (int i = 0; i < resultListDetvdev.size(); i++) {
                    resultDetvdev = resultListDetvdev.get(i);
                    debug(resultDetvdev.getTranddev() + " " + resultDetvdev.getArtiddev() + " "
                            + resultDetvdev.getStatddev());

                    if (isFinishProcess) {
                        statddevUpdated = persistenceDetvdev.updateStatddev(resultDetvdev);
                    }
                }
            } else {
                return Collections.emptyList();
            }

        } else {
            return Collections.emptyList();
        }

        if (statddevUpdated) {
            return resultListDetvdev;
        }
        return Collections.emptyList();
    }

    // private String getMotiveDescription(String id) {
    // info("Buscando descripcion del motivo id: " + id);
    // MAEVMOTDAO persistenceMotivo = new MAEVMOTDAO(dataSource);
    //
    // List<MAEVMOT> response = null;
    // try {
    // response = persistenceMotivo.getDataById(id);
    // } catch (SQLException e) {
    // logger.error(e.getMessage(), e);
    // }
    //
    // if (response != null && !response.isEmpty() && response.get(response.size() -
    // 1) !=
    // null) {
    // info("Descripcion encontrada -> " + response.get(response.size() -
    // 1).getDesmot());
    // return response.get(response.size() - 1).getDesmot().trim();
    // }
    //
    // info("Descripcion no encontrada");
    // return "";
    //
    // }

    private String getMotiveDescription(String id) {
        info("Buscando descripcion del motivo id: " + id);

        MotivodevolucionJpaController persistenceMotivo = new MotivodevolucionJpaController();
        int codigo = Integer.parseInt(id);
        Motivodevolucion response = null;

        response = persistenceMotivo.findMotiveDescription(codigo, entityManagerFactory);

        if (response != null) {
            info("Descripcion encontrada -> " + response.getDescripcionMotivo());
            return response.getDescripcionMotivo().trim();
        }

        info("Descripcion no encontrada");
        return "";

    }

    public Collection<AnulDev> getRefunds(Client client, Session session) {
        Collection<AnulDev> resultanudev = new ArrayList<AnulDev>();
        try {
            info("Buscando devoluciones disponibles para el cliente --> " + client.getIdNumber());
            CABVDEVDAO persistenceCabvdev = new CABVDEVDAO(dataSource);

            HashMap<Integer, String> type = new HashMap<Integer, String>();

            info("Buscando informacion en CABVDEV");
            List<CABVDEV> resultListCabvdev = persistenceCabvdev
                    .getDataToRefundByClientId(client.getIdNumber(), client.getEmployeeId(),
                                               servicesConf.isRefuntOnlyCurrentDate());

            for (Iterator<CABVDEV> iterator = resultListCabvdev.iterator(); iterator.hasNext();) {
                CABVDEV object = iterator.next();
                List devDetails = null;

                info("Inicializando nueva venta con datos conseguidos");
                Transaction data = new Transaction();
                data.setStore(String.valueOf(Integer.parseInt(object.getTievcdev())));
                data.setPosId(object.getCajacdev().toString());
                data.setNumber(object.getTrancdev().toString());

                info("Buscando la fecha para la transaccion cargada");

                TRCMOVDAO persistenceTrcmov = null;
                if (object.getTievcdev().equalsIgnoreCase(Integer.toString(servicesConf.getStoreNumber()))) {
                    info("utilizando DS de la tienda");
                    persistenceTrcmov = new TRCMOVDAO(dataSource);
                } else {
                    info("utilizando DS de otra Tienda");
                    try {
                        persistenceTrcmov = new TRCMOVDAO(dataSource);
                    } catch (NumberFormatException e) {
                        logger.error(e.getMessage(), e);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }

                List tmpTransList = persistenceTrcmov.getDataById(data.getStore(), data.getPosId(),
                                                                  data.getNumber());
                Transaction originalSale = null;
                CRServiceResponse<Transaction> originalSaleTmp = null;
                if (tmpTransList != null && !tmpTransList.isEmpty()) {
                    info("Transaccion encontrada para asignar fecha");
                    TRCMOV tmpTrans = (TRCMOV) tmpTransList.get(0);
                    data.setDate(tmpTrans.getFeccmv());

                    info("Buscando venta original");
                    originalSaleTmp = this.saleService.searchOriginSale(data, session);
                    ObjectMapper objMapper = new ObjectMapper();
                    originalSale = (Transaction) objMapper.convertValue(originalSaleTmp.getPayload(),
                                                                        Transaction.class);

                    devDetails = searchDevDetvdev(object.getTiedcdev(), object.getCajacdev().toString(),
                                                  object.getTrancdev().toString(),
                                                  object.getCorrcdev().toString().trim(), false,
                                                  SourceTransactionType.Refund);

                }

                if (originalSale != null && !devDetails.isEmpty()) {

                    info("Detalles conseguidos en DETVDEV");
                    AnulDev result = new AnulDev();
                    result.setDate(new Date());
                    result.setSaleOrigin(Source.Refund);
                    CRBigDecimal articlesCount = CRBigDecimal.ZERO;
                    ArrayList<Article> auxArticleList = new ArrayList<Article>();
                    Article auxArticle = null;
                    for (int i = 0; i < devDetails.size(); i++) {
                        articlesCount = makeArticlesList(originalSale, type, devDetails, articlesCount,
                                                         auxArticleList, i);
                    }

                    result.setMotiveDescription("");
                    for (Iterator<Integer> iterator2 = type.keySet().iterator(); iterator2.hasNext();) {
                        int desc = iterator2.next();

                        result.setMotiveDescription(result.getMotiveDescription() + type.get(desc));
                        if (type.size() > 1 && !type.get(desc).equalsIgnoreCase(type.get(type.size()))) {
                            result.setMotiveDescription(result.getMotiveDescription() + ", ");
                        }
                    }
                    info("Descripción del motivo ---> '" + result.getMotiveDescription() + "'");

                    if (originalSale.getClient() == null || originalSale.getClient().getIdNumber() == null) {
                        info("transaccion original viene sin cliente, se le asigna el cliente cargado en la devolucion por AS400 --> "
                                + client.getIdNumber());
                        Client client2 = new Client("", client.getIdNumber(), "", "", 0);
                        originalSale.setClient(client2);
                    }

                    result.setClient(originalSale.getClient());
                    result.setArticles(auxArticleList);
                    result.setArticlesCount(articlesCount);
                    result.setOriginalSale(originalSale);
                    resultanudev.add(result);

                } else {
                    info("No se encontraron articulos para devolver en esta venta");
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return resultanudev;
    }

    public Collection<AnulDev> getRefundsFromNewTables(Client client, Session session) {
        Collection<AnulDev> resultanudev = new ArrayList<AnulDev>();

        try {
            info("Buscando devoluciones disponibles para el cliente --> " + client.getIdNumber());

            DevolucionJpaController persistenceHeader = new DevolucionJpaController(entityManagerFactory);
            HashMap<Integer, String> type = new HashMap<Integer, String>();

            Calendar cal = new GregorianCalendar();
            cal.setTime(startDate);
            cal.add(Calendar.DATE, 0);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 1);
            startDate = cal.getTime();

            if (endDate != null) {
                cal.setTime(endDate);
            } else {
                cal.setTime(startDate);
            }

            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
            endDate = cal.getTime();

            info("Consultando devoluciones en CR400.DEVOLUCION");
            List<Devolucion> resultListDev = persistenceHeader
                    .findTransactionToRefundByClientAndDate(client.getIdNumber(), startDate, endDate);

            for (Iterator<Devolucion> iterator = resultListDev.iterator(); iterator.hasNext();) {
                Devolucion actualDev = iterator.next();
                List<Devolucionarticulo> devDetails = new ArrayList<Devolucionarticulo>();

                info("Inicializando nueva venta con datos conseguidos");
                Transaction data = new Transaction();
                data.setStore(String.valueOf(actualDev.getTiendaVenta()));
                data.setPosId(String.valueOf(actualDev.getCajaVenta()));
                data.setNumber(String.valueOf(actualDev.getTransaccionVenta()));

                info("Buscando la fecha para la transacción cargada y Transacción original");

                Transaction originalSale = this.saleService.searchOriginSaleOnCR400(data);

                if (originalSale != null) {
                    data.setDate(originalSale.getDate());

                    Character status = Character.valueOf('I');
                    devDetails = searchDevDetails(actualDev, status, SourceTransactionType.Refund);

                }

                if (!devDetails.isEmpty()) {
                    info("Articulos encontrados para devolver");

                    resultanudev.add(buildAnulDevFromNewTable(originalSale, devDetails, type));

                } else {
                    info("No se encontraron articulos para devolver");
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        return resultanudev;

    }

    public RMIServerResponse checkServicesRefundInProcess(AnulDev data) {
        info("Actualizando devolucion en proceso");
        AnulDev anulDev = null;
        anulDev = executeDevInProcess(data);
        ServicesResponse response = null;
        if (anulDev != null) {
            response = new ServicesResponse("", anulDev);
            response.setCode(0);
        }
        return response;
    }

    public CRBigDecimal searchAmountToRefund(Transaction originalSale) {
        CRBigDecimal amount = CRBigDecimal.ZERO;
        HistorialdevolucionJpaController historialDevolucionJpaController = new HistorialdevolucionJpaController(
                entityManagerFactory);
        RefundHistory history = historialDevolucionJpaController
                .findLastRefundHistory(originalSale.getStore(), originalSale.getPosId(), originalSale.getNumber());

        if (history != null) {
            amount = history.getRemainingAmount();
        } else {
            amount = originalSale.getTotalPay();
        }

        return amount;
    }

    public CRBigDecimal validateIfReturned(Transaction originalSale) {
        CRBigDecimal amount = CRBigDecimal.ZERO;
        HistorialdevolucionJpaController historialDevolucionJpaController = new HistorialdevolucionJpaController(
                entityManagerFactory);
        RefundHistory history = historialDevolucionJpaController
                .findLastRefundHistory(originalSale.getStore(), originalSale.getPosId(), originalSale.getNumber());

        if (history != null) {
            amount = history.getRemainingAmount();
        } else {
            amount = null;
        }

        return amount;
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