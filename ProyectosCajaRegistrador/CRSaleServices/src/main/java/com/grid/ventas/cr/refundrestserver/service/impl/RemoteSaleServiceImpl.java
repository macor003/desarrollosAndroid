/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.grid.ventas.cr.refundrestserver.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.becoblohm.cr.models.AnulDev;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.net.request.ServicesRequest;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.epa.cr.legacy.DevolucionRemotaJpaController;
import com.epa.replicador.agil.data.access.DAOFactory;
import com.epa.replicador.agil.data.access.exceptions.JpaException;
import com.epa.replicador.agil.data.access.interfaces.ventas.IVEMaececDAO;
import com.epa.replicador.agil.data.access.interfaces.ventas.IVEMaetdaDAO;
import com.epa.replicador.agil.data.access.interfaces.ventas.IVEOpevtscDAO;
import com.epa.replicador.agil.data.access.interfaces.ventas.IVETrcmovDAO;
import com.epa.replicador.agil.data.access.interfaces.ventas.IVETrctovDAO;
import com.epa.replicador.agil.data.access.interfaces.ventas.IVETrdmovDAO;
import com.epa.replicador.agil.data.access.model.ventas.VEMaecec;
import com.epa.replicador.agil.data.access.model.ventas.VEMaececPK;
import com.epa.replicador.agil.data.access.model.ventas.VEOpevtsc;
import com.epa.replicador.agil.data.access.model.ventas.VEOpevtscPK;
import com.epa.replicador.agil.data.access.model.ventas.VETrcmov;
import com.epa.replicador.agil.data.access.model.ventas.VETrcmovPK;
import com.epa.replicador.agil.data.access.model.ventas.VETrctov;
import com.epa.replicador.agil.data.access.model.ventas.VETrdmov;
import com.grid.ventas.cr.refundrestclient.EPASalesServicesDispatcher;
import com.grid.ventas.cr.refundrestserver.conf.ServicesConf;
import com.grid.ventas.cr.refundrestserver.service.RemoteSaleService;
import com.grid.ventas.cr.refundrestserver.util.RemoteSaleException;
import com.grid.ventas.cr.refundrestserver.util.xmlrpc.comun.xmlrpc.XmlRpcCode;

import crjpa400.Devolucionremota;

/**
 * Created by eve0017280 on 27/02/16.
 */
@Service
public class RemoteSaleServiceImpl implements RemoteSaleService {

    private static final Logger logger = LoggerFactory.getLogger(RemoteSaleServiceImpl.class);
    // private RestTemplate restTemplateForRemoteStore = new RestTemplate();

    private static EPASalesServicesDispatcher epaSalesServicesDispatcher = new EPASalesServicesDispatcher(false,
            90, 120);

    @Autowired
    ServicesConf servicesConf;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Map<String, Object> getVenta(String storeId, String posId, String id, Date date)
            throws RemoteSaleException {

        info("Inicia consulta venta");

        IVEMaetdaDAO maetdaDAO = null;
        IVETrcmovDAO trcmovDAO = null;
        IVETrdmovDAO trdmovDAO = null;
        IVETrctovDAO trctovDAO = null;
        IVEMaececDAO maececDAO = null;
        IVEOpevtscDAO opevtscDAO = null;

        DAOFactory daoFactory = null;

        BigDecimal tienda = new BigDecimal(storeId);
        BigDecimal caja = new BigDecimal(posId);
        BigDecimal trans = new BigDecimal(id);

        Map<String, Object> response = new HashMap<String, Object>();

        try {
            daoFactory = DAOFactory.getDAOFactory(DAOFactory.DAOTYPE.JPADAOFACTORY);
        } catch (JpaException e) {
            throw new RemoteSaleException(XmlRpcCode.SERVER_ERROR, e.getMessage());
        }

        maetdaDAO = daoFactory.getMaetdaDAO();
        trcmovDAO = daoFactory.getTrcmovDAO();
        trdmovDAO = daoFactory.getTrdmovDAO();
        trctovDAO = daoFactory.getTrctovDAO();
        maececDAO = daoFactory.getMaececDAO();
        opevtscDAO = daoFactory.getOpevtscDAO();

        BigDecimal tda = null;
        try {
            tda = maetdaDAO.getNumtie();
        } catch (javax.persistence.NoResultException e) {
            throw new RemoteSaleException(XmlRpcCode.NOT_FOUND, e.getMessage());
        }
        if (tda.compareTo(tienda) != 0)
            throw new RemoteSaleException(XmlRpcCode.FORBIDDEN, "WRONG STORE");

        VETrcmov trcmov = null;
        VEMaecec maecec = null;
        VEOpevtsc opevtsc = null;
        List<VETrctov> trctovList = null;
        List<VETrdmov> trdmovList = null;

        try {
            trcmov = trcmovDAO.findById(new VETrcmovPK(caja, tienda, trans));
        } catch (JpaException e) {
            throw new RemoteSaleException(XmlRpcCode.NOT_FOUND, "TRCMOV\n" + e.getMessage());
        }
        try {
            maecec = maececDAO.findById(new VEMaececPK(tienda.longValue(), caja.longValue(), trans.longValue()));
        } catch (JpaException e) {
            throw new RemoteSaleException(XmlRpcCode.NOT_FOUND, "MAECEC\n" + e.getMessage());
        }

        try {
            opevtsc = opevtscDAO
                    .findById(new VEOpevtscPK(tienda.longValue(), caja.longValue(), trans.longValue()));
        } catch (JpaException e) {
            throw new RemoteSaleException(XmlRpcCode.NOT_FOUND, "OPEVTSC\n" + e.getMessage());
        }

        try {
            trctovList = trctovDAO.getFromTrcmov(trcmov);
            trdmovList = trdmovDAO.getFromTrcmov(trcmov);
        } catch (javax.persistence.NoResultException e) {
            throw new RemoteSaleException(XmlRpcCode.NOT_FOUND, e.getMessage());
        }

        // Procesar Pagos
        List<Map<String, Object>> trctovListMap = new ArrayList<Map<String, Object>>();
        for (VETrctov trctov : trctovList) {
            Map<String, Object> a = trctovDAO.toHash(trctov);
            trctovListMap.add(a);
        }

        // Procesar Detalles
        List<Map<String, Object>> trdmovListMap = new ArrayList<Map<String, Object>>();
        for (VETrdmov trdmov : trdmovList) {
            Map<String, Object> a = trdmovDAO.toHash(trdmov);
            trdmovListMap.add(a);
        }

        // Armar respuesta
        response.put("TRCMOV", trcmovDAO.toHash(trcmov));
        response.put("MAECEC", maececDAO.toHash(maecec));
        response.put("OPEVTSC", opevtscDAO.toHash(opevtsc));
        response.put("TRCTOV", trctovListMap);
        response.put("TRDMOV", trdmovListMap);

        info("Finaliza consulta venta");
        return response;
    }

    /*
     * @Override public ServicesResponse getVentaRemota(String storeId, String posId,
     * String id, Date date) { String address =
     * servicesConf.getServicesURLs().get(Integer.parseInt(posId)); ResponseEntity<Map>
     * mapResponseEntity = restTemplateForRemoteStore.getForEntity(address, null, new
     * HashMap<String, Object>()); // return mapResponseEntity.getBody(); return null; }
     */

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

    /*
     * public RemoteRefund searchOldRefunds(RemoteRefund remoteRefundRequest) throws
     * Exception { DevolucionRemotaJpaController devolucionRemotaJpaController = new
     * DevolucionRemotaJpaController(entityManager); List<Devolucionremota> refundsInBD =
     * devolucionRemotaJpaController.findByTransaction( remoteRefundRequest.getTienda(),
     * remoteRefundRequest.getCaja(), remoteRefundRequest.getTransaccion() ); RemoteRefund
     * remoteRefundResponse = createRemoteRefund(refundsInBD);
     * //remoteRefundResponse.setMsgCode(RemoteRefund.VALIDATED);
     * remoteRefundResponse.setTienda(remoteRefundRequest.getTienda());
     * remoteRefundResponse.setCaja(remoteRefundRequest.getCaja());
     * remoteRefundResponse.setTransaccion(remoteRefundRequest.getTransaccion()); return
     * remoteRefundResponse; } private RemoteRefund
     * createRemoteRefund(List<Devolucionremota> refundsInBD) { RemoteRefund remoteRefund
     * = new RemoteRefund(); List<RemoteRefundArticleInfo> articleList = new
     * ArrayList<RemoteRefundArticleInfo>(); for (int i = 0; i < refundsInBD.size(); i++)
     * { Devolucionremota devolucionremota = refundsInBD.get(i); RemoteRefundArticleInfo
     * articleInfo = new RemoteRefundArticleInfo(); articleList.add(articleInfo); }
     * remoteRefund.setRemoteRefundArticleInfoList(articleList); return remoteRefund; }
     * public RemoteRefund registerRemoteRefund(RemoteRefund remoteRefundRequest) {
     * boolean error = false; DevolucionRemotaJpaController devolucionRemotaJpaController
     * = new DevolucionRemotaJpaController(entityManager); List<Devolucionremota>
     * refundToSave = createDevolucionRemotaList(remoteRefundRequest); try {
     * devolucionRemotaJpaController.save(refundToSave); } catch (Exception e) {
     * e.printStackTrace(); error = true; } List<Devolucionremota> refundsInBD = null; try
     * { refundsInBD = devolucionRemotaJpaController.findByTransaction(
     * remoteRefundRequest.getTienda(), remoteRefundRequest.getCaja(),
     * remoteRefundRequest.getTransaccion() ); } catch (Exception e) {
     * e.printStackTrace(); error = true; } RemoteRefund remoteRefundResponse =
     * createRemoteRefund(refundsInBD);
     * remoteRefundResponse.setTienda(remoteRefundRequest.getTienda());
     * remoteRefundResponse.setCaja(remoteRefundRequest.getCaja());
     * remoteRefundResponse.setTransaccion(remoteRefundRequest.getTransaccion()); if
     * (!error) { remoteRefundResponse.setMsgCode(RemoteRefund.REGISTERED); } else {
     * remoteRefundResponse.setMsgCode(RemoteRefund.ERROR); } return remoteRefundResponse;
     * } private List<Devolucionremota> createDevolucionRemotaList(RemoteRefund
     * remoteRefundRequest) { long date = System.currentTimeMillis();
     * List<Devolucionremota> devolucionremotaList = new ArrayList<Devolucionremota>(); if
     * (remoteRefundRequest != null) { List<RemoteRefundArticleInfo>
     * remoteRefundArticleInfoList = remoteRefundRequest.getRemoteRefundArticleInfoList();
     * for (int i = 0; i < remoteRefundArticleInfoList.size(); i++) {
     * RemoteRefundArticleInfo refundArticleInfo = remoteRefundArticleInfoList.get(i);
     * Devolucionremota devolucionremota = new Devolucionremota();
     * devolucionremota.setTiendaventa(remoteRefundRequest.getTienda());
     * devolucionremota.setCajaventa(remoteRefundRequest.getCaja());
     * devolucionremota.setIdTransaccion(new
     * BigInteger(remoteRefundRequest.getTransaccion()+""));
     * devolucionremota.setIdArticulo(refundArticleInfo.getId());
     * devolucionremota.setCantidad(refundArticleInfo.getItems().getValue().longValueExact
     * ()); devolucionremota.setFecha(new Date(date));
     * devolucionremotaList.add(devolucionremota); } } return devolucionremotaList; }
     * public List<RemoteRefundArticleInfo> extractArticlesInfo(Collection<Article>
     * articles) { List<RemoteRefundArticleInfo> remoteRefundSaleInfoList = new
     * ArrayList<RemoteRefundArticleInfo>(); for (Iterator<Article> iterator =
     * articles.iterator(); iterator.hasNext(); ) { Article next = iterator.next(); Long
     * id = next.getId(); CRBigDecimal items = next.getItems();
     * remoteRefundSaleInfoList.add(new RemoteRefundArticleInfo(id, items)); } return
     * remoteRefundSaleInfoList; } //Elimina los articulos que estan en la lista
     * returnedArticles de la lista originalArticles public List<RemoteRefundArticleInfo>
     * discardArticles(List<RemoteRefundArticleInfo> originalArticles,
     * List<RemoteRefundArticleInfo> returnedArticles) throws Exception { for (int i = 0;
     * i < returnedArticles.size(); i++) { RemoteRefundArticleInfo refundArticleInfo =
     * returnedArticles.get(i); boolean found = false; for
     * (Iterator<RemoteRefundArticleInfo> iterator = originalArticles.iterator();
     * iterator.hasNext(); ) { RemoteRefundArticleInfo originalArticleInfo =
     * iterator.next(); if (refundArticleInfo.getId().equals(originalArticleInfo.getId()))
     * { found = true; originalArticleInfo.setItems(
     * originalArticleInfo.getItems().subtract(refundArticleInfo.getItems()) ); if
     * (originalArticleInfo.getItems().compareTo(CRBigDecimal.ZERO) < 0) { Exception
     * exception = new Exception(
     * "Fatal. La cantidad de articulos a devolver (o devueltos) excede los de la factura."
     * ); throw exception; } if
     * (originalArticleInfo.getItems().compareTo(CRBigDecimal.ZERO) == 0) {
     * iterator.remove(); } //break; } if (!found) { Exception exception = new Exception(
     * "Fatal. El articulo a devolver no se encuentra en la factura (o ya fue devuelto)."
     * ); throw exception; } } } return originalArticles; } // Ejecutado apartir de una
     * peticion remota public ServicesResponse updateRemoteRefund(AnulDev anulDev) {
     * String isRegisteredMsg = "0"; Transaction tr = anulDev.getOriginalSale();
     * RemoteRefund refundToRegister = new RemoteRefund(tr.getStore(),
     * Long.parseLong(tr.getPosId()), Long.parseLong(tr.getNumber())); RemoteRefund
     * registerBeforeRefund = null; try { registerBeforeRefund =
     * searchOldRefunds(refundToRegister); } catch (Exception e) { e.printStackTrace();
     * return new ServicesResponse("0" ,null); } List<RemoteRefundArticleInfo>
     * originalArticles = extractArticlesInfo(tr.getArticles());
     * List<RemoteRefundArticleInfo> returnedArticles =
     * registerBeforeRefund.getRemoteRefundArticleInfoList();
     * List<RemoteRefundArticleInfo> availiableArticles = null; try { // Crear una lista
     * con los articulos disponibles para devolver en la tr availiableArticles =
     * discardArticles(originalArticles, returnedArticles); } catch (Exception e) {
     * e.printStackTrace(); return new ServicesResponse("0" ,null); } try { // Se intenta
     * eliminar de la lista de articulos disponibles // los articulos a devolver. Si da
     * error, no se registra la devolucion discardArticles(availiableArticles,
     * extractArticlesInfo(anulDev.getArticles())); } catch (Exception e) {
     * e.printStackTrace(); return new ServicesResponse("0" ,null); } RemoteRefund
     * registerAfterRefund = registerRemoteRefund(refundToRegister); if
     * (registerAfterRefund.getMsgCode() == RemoteRefund.REGISTERED) { return new
     * ServicesResponse("1" ,null); } return new ServicesResponse("0" ,null); }
     */

    // Envia peticion a otro servidor
    @Override
    public boolean sendUpdateRemoteRefundRequest(AnulDev data) {
        boolean valid = false;
        if (servicesConf.isRemoteSearchActive()) {
            int nro = Integer.parseInt(data.getOriginalSale().getStore());
            logger.info("Enviado peticion de actializacion de devolucion en tienda remota " + nro);
            String ip = servicesConf.getServicesURLs().get(nro);
            if (ip == null || "".equals(ip)) {
                logger.error("No se encuentra la IP de la tienda " + nro);
            }
            ServicesRequest rmiServerRequest = new ServicesRequest(ServicesRequest.UPDATE_REMOTE_REFUND, data,
                    null);
            RMIServerResponse rmiServerResponse = null;
            try {
                rmiServerResponse = epaSalesServicesDispatcher
                        .dispatch(rmiServerRequest, ip, servicesConf.getServerPort(), ServicesResponse.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ServicesResponse rmiServerResponse1 = (ServicesResponse) rmiServerResponse;
            if (rmiServerResponse1 != null) {
                String msg = rmiServerResponse1.getMsg();
                valid = ("1".equals(msg));
            }
        } else {
            valid = true;
        }
        return valid;
    }

    // Ejecutado a partir de una peticion remota
    @Override
    public ServicesResponse updateRemoteRefund(AnulDev anulDev) {

        // Se buscan las devoluciones previas para esa factura
        List<Devolucionremota> refundsInBD = new ArrayList<Devolucionremota>();
        DevolucionRemotaJpaController devolucionRemotaJpaController = new DevolucionRemotaJpaController(
                entityManager);
        try {
            refundsInBD = devolucionRemotaJpaController
                    .findByTransaction(anulDev.getOriginalSale().getStore(),
                                       Long.parseLong(anulDev.getOriginalSale().getPosId()),
                                       Long.parseLong(anulDev.getOriginalSale().getNumber()));
        } catch (Exception e) {
            return new ServicesResponse("0", null);
        }

        // Se agrupan las devoluciones previas por articulo
        HashMap<Long, BigDecimal> priorRefunds = new HashMap<Long, BigDecimal>();
        for (Devolucionremota devolucionremota : refundsInBD) {
            Long idArticulo = devolucionremota.getIdArticulo();
            BigDecimal cantidad = devolucionremota.getCantidad();
            if (priorRefunds.containsKey(idArticulo)) {
                BigDecimal quantity = priorRefunds.get(idArticulo);
                priorRefunds.put(idArticulo, quantity.add(cantidad));
            } else {
                priorRefunds.put(idArticulo, cantidad);
            }
        }

        // Se agrupan la nueva devolucion por articulo (es necesario?)
        HashMap<Long, BigDecimal> newRefund = new HashMap<Long, BigDecimal>();
        Collection<Article> devArticles = anulDev.getArticles();
        for (Article article : devArticles) {
            Long idArticulo = article.getId();
            BigDecimal cantidad = article.getItems().getValue();
            if (newRefund.containsKey(idArticulo)) {
                BigDecimal quantity = newRefund.get(idArticulo);
                newRefund.put(idArticulo, quantity.add(cantidad));
            } else {
                newRefund.put(idArticulo, cantidad);
            }
        }

        // Se agrupan la tr original por articulo (es necesario?)
        HashMap<Long, BigDecimal> tr = new HashMap<Long, BigDecimal>();
        Collection<Article> trArticles = anulDev.getOriginalSale().getArticles();
        for (Article article : trArticles) {
            Long idArticulo = article.getId();
            BigDecimal cantidad = article.getItems().getValue();
            if (tr.containsKey(idArticulo)) {
                BigDecimal quantity = tr.get(idArticulo);
                tr.put(idArticulo, quantity.add(cantidad));
            } else {
                tr.put(idArticulo, cantidad);
            }
        }

        // Se le resta las devoluciones previas al articulo
        HashMap<Long, BigDecimal> disponibleParaDevolver = new HashMap<Long, BigDecimal>();
        for (Map.Entry<Long, BigDecimal> refundArt : priorRefunds.entrySet()) {
            for (Map.Entry<Long, BigDecimal> trArt : tr.entrySet()) {
                if (refundArt.getKey().equals(trArt.getKey())) {
                    BigDecimal resta = trArt.getValue().subtract(refundArt.getValue());
                    disponibleParaDevolver.put(trArt.getKey(), resta);
                    break;
                }
            }
        }

        // Se le resta la nueva devolucion a la cantidad disponible para devolver
        for (Map.Entry<Long, BigDecimal> refundArt : newRefund.entrySet()) {
            boolean found = false;
            for (Map.Entry<Long, BigDecimal> disponibleArt : disponibleParaDevolver.entrySet()) {
                if (refundArt.getKey().equals(disponibleArt.getKey())) {
                    BigDecimal resta = disponibleArt.getValue().subtract(refundArt.getValue());
                    if (resta.compareTo(BigDecimal.ZERO) >= 0) {
                        // NO se puede devolver porque no hay suficiente
                        return new ServicesResponse("0", null);
                    }
                    found = true;
                }
            }
            if (!found) {
                // NO se puede devolver porque no se consiguio el articulo
                return new ServicesResponse("0", null);
            }
        }

        // Se actualiza la BD con la nueva devolucion
        List<Devolucionremota> refundToSave = createDevolucionRemotaList(anulDev);
        try {
            devolucionRemotaJpaController.save(refundToSave);
        } catch (Exception e) {
            e.printStackTrace();
            // NO se puede devolver, acurrio un error al actualizar BD
            return new ServicesResponse("0", null);
        }
        return new ServicesResponse("1", null);
    }

    private List<Devolucionremota> createDevolucionRemotaList(AnulDev anulDev) {
        List<Devolucionremota> devolucionremotaList = new ArrayList<Devolucionremota>();
        if (anulDev != null) {
            Collection<Article> articles = anulDev.getArticles();
            for (Article article : articles) {
                Devolucionremota devolucionremota = new Devolucionremota();
                devolucionremota.setTiendaventa(anulDev.getOriginalSale().getStore());
                devolucionremota.setCajaventa(Long.parseLong(anulDev.getOriginalSale().getPosId()));
                devolucionremota.setIdTransaccion(BigInteger.valueOf(anulDev.getOriginalSale().getId()));
                devolucionremota.setIdArticulo(article.getId());
                devolucionremota.setCantidad(article.getItems().getValue());
                devolucionremota.setFecha(new Date());
                devolucionremotaList.add(devolucionremota);
            }
        }
        return devolucionremotaList;
    }
}
