package com.grid.ventas.cr.refundrestserver.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.becoblohm.cr.crjpa400.controller.exceptions.IllegalOrphanException;
import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.crjpa400.controller.exceptions.NonexistentEntityException;
import com.becoblohm.cr.crjpa400.controller.exceptions.PreexistingEntityException;
import com.becoblohm.cr.models.AnulDev;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.models.Deposit;
import com.becoblohm.cr.models.Deposit.SourceStatus;
import com.becoblohm.cr.models.Deposit.SourceType;
import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.models.OrderArticle;
import com.becoblohm.cr.models.OrderTransaction;
import com.becoblohm.cr.models.OrderType;
import com.becoblohm.cr.models.Payment;
import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.models.Transaction.Source;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.becoblohm.cr.response.SpecialOrderResponse;
import com.becoblohm.cr.sync.converters.ClienteConverter;
import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.cr.legacy.AbonoFormadepagoJpaController;
import com.epa.cr.legacy.AbonoJpaController;
import com.epa.cr.legacy.CacheEPA;
import com.epa.cr.legacy.OrdendeventaArticuloJpaController;
import com.epa.cr.legacy.OrdendeventaJpaController;
import com.epa.cr.legacy.OrdendeventaTransaccionJpaController;
import com.epa.cr.legacy.SesionJpaController;
import com.epa.ventas.dao.CABVPEADAO;
import com.epa.ventas.dto.CABVPEA;
import com.grid.ventas.cr.refundrestserver.conf.ServicesConf;
import com.grid.ventas.cr.refundrestserver.service.RefundService;
import com.grid.ventas.cr.refundrestserver.service.SpecialOrderService;

/**
 * Created by eve0017280 on 10/02/16.
 */

@Service
public class SpecialOrderServiceImpl implements SpecialOrderService {

    private static final Logger logger = LoggerFactory.getLogger(SpecialOrderServiceImpl.class);

    private static final Long ID_REGISTRO_NULL_EXO_TIPO_400 = 142L;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    private ServicesConf servicesConf;

    @Autowired
    private RefundService refundService;

    @Autowired
    private DataSource datasource;

    @Autowired
    private Long idExoneradotipoNull;

    @Deprecated
    public ServicesResponse ValidateIfTransactionisFromSpecialOrder(Transaction data) {
        debug("Buscando datos de transaccion en Pedidos Especiales");
        ServicesResponse result = new ServicesResponse("", searchTransactionInCabvpea(data));

        if (result.getData() != null) {
            result.setCode(0);
        }
        return result;
    }

    public Transaction searchTransactionInCabvpea(Transaction originalSale) {

        Transaction result = null;
        debug("Consultando en CABVPEA ---> ");
        CABVPEADAO PersistenceCabvpea = new CABVPEADAO(datasource);
        CABVPEA resultCabvpea = null;
        String storeId = originalSale.getStore();
        String posId = originalSale.getPosId();

        for (int i = storeId.length(); i < 3; i++)
            storeId = "0" + storeId;

        for (int i = posId.length(); i < 2; i++)
            posId = "0" + posId;

        List resultListCabvpea = null;
        try {
            resultListCabvpea = PersistenceCabvpea
                    .getDataStorePosTransactionServiceType(storeId, posId, originalSale.getNumber(),
                                                           this.servicesConf.getOrdersTypeSpecialOrders());
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        if (resultListCabvpea != null && resultListCabvpea.size() > 0) {

            for (int i = 0; i < resultListCabvpea.size(); i++) {
                resultCabvpea = (CABVPEA) resultListCabvpea.get(i);
                debug(resultCabvpea.getNUMPCPEA().toString());
            }

            result = new Transaction();
            result.setNumber(resultCabvpea.getTRANCPEA().toString());
            result.setStore(resultCabvpea.getTIENCPEA().toString());
            result.setPosId(resultCabvpea.getCAJACPEA().toString());

        } else {
            debug("resultado CABVPEA null");
        }
        return result;
    }

    public Deposit getLastDeposit(ArrayList<Deposit> depositsList) {
        Deposit maxDep = null;
        Deposit abono = new Deposit();
        Date date = new Date(315532800);
        for (Iterator<Deposit> iterator = depositsList.iterator(); iterator.hasNext();) {
            abono = iterator.next();
            if (abono.getDate().after(date)) {
                maxDep = abono;
                date = abono.getDate();
            }
        }
        debug("Max fecha: " + date);
        return maxDep;

    }

    public SpecialOrderResponse requestLastOrderDeposit(Order order, Session session) {

        Long orderNumber = Long.parseLong(order.getNumber());
        SpecialOrderResponse result = new SpecialOrderResponse();
        debug("Buscando pedido especial #" + orderNumber + " en base de datos para obtener el abono");
        OrdendeventaJpaController ordenJpa = new OrdendeventaJpaController(entityManagerFactory);
        Order ordenDeVenta = ordenJpa.findOrdenDeVentaByNumber(orderNumber, idExoneradotipoNull);

        if (ordenDeVenta != null) {

            debug("Encontrado pedido a reimprimir");
            ArrayList<Deposit> abonos = new ArrayList<Deposit>();
            List<Deposit> abonosJpa = ordenDeVenta.getDeposits();
            debug("Cantidad de abonos del pedido :" + abonosJpa.size());
            for (int i = 0; i < abonosJpa.size(); i++) {
                abonos.add(abonosJpa.get(i));
            }

            if (abonos.size() > 0) {
                Deposit lastDeposit = getLastDeposit(abonos);
                if (lastDeposit != null) {

                    result.setData(lastDeposit);
                    result.setCode(SpecialOrderResponse.OK);
                    result.setMsg("Abono" + lastDeposit.getNumber());
                }

                // TODO getLastDeposit, compare the session, create voucher.
            }
        }
        return result;
    }

    @Override
    public SpecialOrderResponse requestSpecialOrder(Client client, com.becoblohm.cr.net.models.Session session) {

        SpecialOrderResponse result = new SpecialOrderResponse();
        debug("Buscando los datos del cliente idNumero: " + client.getIdNumber());

        if (client != null) {
            debug("Buscando los pedidos especiales en base de datos para el cliente: " + client.getIdNumber());
            OrdendeventaJpaController ordenJpa = new OrdendeventaJpaController(entityManagerFactory);
            List<Order> ordenDeVentaList = ordenJpa
                    .findOrdenDeVentaActivaByClientId_OnlyToShow(client, idExoneradotipoNull);
            ArrayList<Order> ordersList = new ArrayList<Order>();

            if (ordenDeVentaList != null && ordenDeVentaList.size() > 0) {

                debug("Pedidos especiales encontrados");
                for (Order ordendeventa : ordenDeVentaList) {
                    Order tmoOrder = ordendeventa;
                    articlesCancelledValidator(tmoOrder);
                    ordersList.add(tmoOrder);
                    debug("Agregando a lista de pedidos el pedido #" + tmoOrder.getNumber() + " id#"
                            + tmoOrder.getOrderNumber() + " para el cliente " + client.getName());
                    debug("Origen: " + ordendeventa.getOrderType().getOrigin());

                }

                result.setData(ordersList);
                // result.setMsgCode(SpecialOrderResponse.OK);
            } else {
                debug("Pedidos especiales no encontrados");
                // result.setMsgCode(SpecialOrderResponse.SPECIAL_ORDERS_NOT_FOUND);
            }

            /*
             * Busqueda de devoluciones para el cliente con fecha de hoy
             */
            Collection<AnulDev> refunds = new ArrayList<AnulDev>();

            refunds = this.refundService.getRefundsFromNewTables(client, session);
            if (refunds != null && refunds.size() > 0) {
                for (Iterator iterator = refunds.iterator(); iterator.hasNext();) {
                    AnulDev anulDev = (AnulDev) iterator.next();
                    Order tmp = new Order();
                    tmp.initTransaction(anulDev.getOriginalSale());
                    tmp.setDate(anulDev.getOriginalSale().getDate());
                    tmp.setSaleOrigin(Source.Refund);
                    tmp.setNumber(anulDev.getOriginalSale().getNumber());
                    // tmp.setStatusOrder(this.servicesConf.PENDING_FOR_FIRST_DEPOSIT);
                    tmp.setStatusOrder(this.servicesConf.findStatusOrder(ServicesConf.PENDING_FOR_FIRST_DEPOSIT));
                    debug("Agregando devolucion de tr " + anulDev.getOriginalSale().getNumber());
                    ordersList.add(tmp);
                }
                result.setData(ordersList);

            } else {
                debug("Devoluciones no encontrados");
            }

            if (ordersList.size() > 0) {
                // 14-01-2013 Se agrega implementacion de metodos SynConverter
                // para mandar la data homologada a la caja
                for (Order orderTmp : ordersList) {
                    ClienteConverter converter = new ClienteConverter();
                    try {
                        orderTmp.setClient(converter.fromServer(client, entityManagerFactory));
                    } catch (Exception e) {
                        logger.error("Problemas al convertir cliente con SyncConverter->", e);

                    }
                }
                result.setCode(SpecialOrderResponse.OK);
            } else {
                result.setCode(SpecialOrderResponse.SPECIAL_ORDERS_NOT_FOUND);
            }
        }
        return result;
    }

    private void articlesCancelledValidator(Order tmoOrder) {
        // busca en el detalle de articulos si alguno fue anulado y lo saca del
        // pedido ajustando el monto total

        debug("Pedido numero: " + tmoOrder.getOrderNumber());
        ArrayList<Article> tmpArticles = new ArrayList<Article>();
        tmpArticles.addAll(tmoOrder.getArticles());

        boolean removePays = false;
        CRBigDecimal tmpAmount = CRBigDecimal.ZERO;
        for (Article art : tmpArticles) {
            OrderArticle orderArticle = (OrderArticle) art;
            debug("Precio del articulo (totalCost): " + orderArticle.getTotalCost());
            // if (orderArticle.getStatus() == this.servicesConf.CANCELED_WITH_CHARGE
            // || orderArticle.getStatus() == this.servicesConf.CANCELED_WITHOUT_CHARGE) {
            if (orderArticle.getStatus() == this.servicesConf.findStatusOrder(ServicesConf.CANCELED_WITH_CHARGE)
                    || orderArticle.getStatus() == this.servicesConf
                            .findStatusOrder(ServicesConf.CANCELED_WITHOUT_CHARGE)) {
                tmoOrder.removeArticle(orderArticle);
                removePays = true;
                tmpAmount = tmpAmount.add(orderArticle.getTotalCost());
            }
        }

        if (removePays) {
            tmoOrder.setTotalPay(tmoOrder.getTotalPay().subtract(tmpAmount));
        }

    }

    public SpecialOrderResponse registerOperationDeposit(Deposit deposit) {
        SpecialOrderResponse result = new SpecialOrderResponse();
        debug("Buscando pedido especial #" + deposit.getOrderNumber()
                + " en base de datos para registrar el abono");
        OrdendeventaJpaController ordenJpa = new OrdendeventaJpaController(entityManagerFactory);
        Order ordenDeVenta = ordenJpa.findOrdenDeVentaByNumber(deposit.getOrderNumber(), idExoneradotipoNull);

        if (ordenDeVenta != null) {

            debug("Pedido especial encontrado");
            debug("Cantidad de abonos realizados en este pedido especial: " + ordenDeVenta.getDeposits().size());
            debug("Buscando Session id #" + deposit.getSession().getId());
            SesionJpaController sessionController = new SesionJpaController(entityManagerFactory);
            Session session;
            try {
                session = sessionController.findSesion(new Long(deposit.getSession().getId()));
            } catch (Exception e) {
                session = null;
            }

            if (session == null) {
                debug("No se encontro sesion para registrar el abono");
                try {
                    debug("Registrando sesion no encontrada en base de datos id #" + deposit.getSession().getId());
                    session = sessionController.create(deposit.getSession());
                } catch (Exception e) {
                    debug("Error al registrar session");
                    debug(e.getMessage());
                }

            } else {
                debug("Sesion encontrada");
            }

            AbonoJpaController abonoJpa = new AbonoJpaController(entityManagerFactory);

            boolean dataComplete = true;
            Deposit abonoOriginal = null;
            if (deposit.getOriginalDeposit() != null) {
                debug("Proceso de anulacion de abono detectado" + System.getProperty("line.separator")
                        + " Buscando abono original #" + deposit.getOriginalDeposit().getId());
                abonoOriginal = abonoJpa.findAbono(deposit.getOriginalDeposit().getId());

                if (abonoOriginal != null) {
                    debug("Abono original encontrado");
                    abonoOriginal.setStatus(SourceStatus.Cancelleted);
                    try {
                        debug("marcando abono original como inactivo");
                        abonoJpa.edit(ordenDeVenta, session, abonoOriginal);
                        debug("Abono original marcado como inactivo");
                    } catch (IllegalOrphanException e) {
                        dataComplete = false;
                        debug("Error al tratar de marcar abono original como anulado");
                        debug(e.getMessage());
                    } catch (NonexistentEntityException e) {
                        dataComplete = false;
                        debug("Error al tratar de marcar abono original como anulado");
                        debug(e.getMessage());
                    } catch (Exception e) {
                        dataComplete = false;
                        debug("Error al tratar de marcar abono original como anulado");
                        debug(e.getMessage());
                    }
                }

            }

            if (dataComplete) {
                boolean headerRegistered = false;
                try {
                    debug("Registrando cabecera del abono");
                    abonoJpa.create(ordenDeVenta, session, deposit);
                    headerRegistered = true;
                    debug("Cabecera del Abono registrado correctamente");
                } catch (PreexistingEntityException e) {
                    result.setCode(SpecialOrderResponse.DEPOSITHEADERNOREGISTERED);
                    debug("Error al intentar registrar abono");
                    debug(e.getMessage());
                } catch (Exception e) {
                    result.setCode(SpecialOrderResponse.DEPOSITHEADERNOREGISTERED);
                    debug("Error al intentar registrar abono");
                    debug(e.getMessage());
                }

                if (headerRegistered) {
                    AbonoFormadepagoJpaController abonoFormaDePagoJpa = new AbonoFormadepagoJpaController(
                            entityManagerFactory);

                    short item = 1;
                    for (Payment payment : deposit.getPayments()) {
                        try {
                            debug("Registrando formas de pago para el abono #" + payment.getPayMethod().getId()
                                    + " " + payment.getPayMethod().getName() + " por "
                                    + payment.getMoney().getLocalAmmount());
                            payment.setItem(item);
                            abonoFormaDePagoJpa.create(ordenDeVenta, session, payment, deposit);
                            debug("Formas de Abono registrado correctamente");
                            item++;
                        } catch (PreexistingEntityException e) {
                            debug("Error al intentar registrar forma de pago abono");
                            debug(e.getMessage());
                        } catch (Exception e) {
                            debug("Error al intentar registrar forma de pago abono");
                            debug(e.getMessage());
                        }
                    }

                    debug("Cantidad de pagos registrados -->  " + (item - 1));
                    debug("Cantidad de pagos totales -->  " + deposit.getPayments().size());

                    if (item - 1 == deposit.getPayments().size()) {
                        result.setCode(SpecialOrderResponse.OK);
                        result.setData(updateStatusOrder(ordenDeVenta.getServerOrderId()));
                    } else {
                        result.setCode(SpecialOrderResponse.PAYMENTSINCOMPLETE);
                        try {
                            debug("Borrando abono de la base de datos");
                            abonoJpa.destroy(deposit.getId());
                            debug("Abono borrado");
                        } catch (PreexistingEntityException e) {
                            debug("Error al intentar borrar abono");
                        } catch (Exception e) {
                            debug("Error al intentar borrar abono");
                        }
                    }
                }
            }
        } else {
            result.setCode(SpecialOrderResponse.SPECIAL_ORDERS_NOT_FOUND);
            debug("Pedido especial no encontrado");
        }
        return result;
    }

    private Order updateStatusOrder(Long id) {

        debug("Actualizando Status de Pedido Especial");
        OrdendeventaJpaController ordenDeVentaJpa = new OrdendeventaJpaController(entityManagerFactory);
        debug("Buscando Pedido especial Id# " + id);
        Order ordenDeVenta = ordenDeVentaJpa.findOrderById(id, idExoneradotipoNull);

        if (ordenDeVenta != null) {
            debug("Pedido Especial encontrado");
            debug("Calculando el total Abonado");
            CRBigDecimal totalPay = CRBigDecimal.ZERO;
            for (Deposit abono : ordenDeVenta.getDeposits()) {
                debug("Abono #" + abono.getNumber() + "Tipo:" + abono.getType() + " Monto: "
                        + (abono.getAmount().subtract(abono.getDifference())));
                if (abono.getType() == SourceType.Deposit) {
                    totalPay = totalPay.add(abono.getAmount());
                } else {
                    totalPay = totalPay.subtract(abono.getAmount());
                }
            }
            debug("Total Abonado: " + totalPay);
            debug("Costo Total de la orden de venta: " + ordenDeVenta.getTotalCost());

            if (totalPay.compareTo(ordenDeVenta.getTotalCost()) == 0) {
                if (ordenDeVenta.isArticlesInStore()) {
                    debug("Pedido Especial Pagada");
                    if (ordenDeVenta.getOrderType().getOrigin() == OrderType.Source.AS400) {
                        ordenDeVenta
                                .setStatusOrder(this.servicesConf.findStatusOrder(ServicesConf.PENDING_FOR_BILL));
                    } else if ((ordenDeVenta.getOrderType().getOrigin() == OrderType.Source.SALES_COMPANY)) {
                        ordenDeVenta.setStatusOrder(this.servicesConf
                                .findStatusOrder(ServicesConf.PENDING_FOR_BILL_SALES_COMPANY));
                    }
                } else {
                    debug("Pedido Especial Pagada pero pendiente por confirmar");
                    ordenDeVenta.setStatusOrder(this.servicesConf
                            .findStatusOrder(ServicesConf.PENDING_FOR_CONFIRM_BILL));
                }
            } else if (totalPay.compareTo(CRBigDecimal.ZERO) == 0) {
                debug("Pedido Especial Pendiente por abonar");
                ordenDeVenta
                        .setStatusOrder(this.servicesConf.findStatusOrder(ServicesConf.PENDING_FOR_FIRST_DEPOSIT));
            } else {
                debug("Pedido Especial abonado");
                ordenDeVenta.setStatusOrder(this.servicesConf.findStatusOrder(ServicesConf.DEPOSITED));
            }
            try {
                debug("Actualizando status de Pedido Especial");
                ordenDeVentaJpa.edit(ordenDeVenta);
                debug("Actualizado...");

                debug("Actualizando status de articulos del Pedido Especial");
                updateStatusArticle(ordenDeVenta);
                debug("Actualizado...");

            } catch (IllegalOrphanException e) {
                debug("Error al intentar actualizar status", e);
                debug(e.getMessage());
            } catch (NonexistentEntityException e) {
                debug("Error al intentar actualizar status", e);
                debug(e.getMessage());
            } catch (Exception e) {
                debug("Error al intentar actualizar status", e);
                debug(e.getMessage());
            }

        } else {
            debug("Pedido Especial no encontrado");
        }
        return ordenDeVenta;
    }

    private void updateStatusArticle(Order ordenDeVenta) {

        if (ordenDeVenta.getArticlesCount().compareTo(CRBigDecimal.ZERO) > 0) {
            OrdendeventaArticuloJpaController ordenDeVentaArticuloJpaController = new OrdendeventaArticuloJpaController(
                    entityManagerFactory);
            for (Article article : ordenDeVenta.getArticles()) {
                OrderArticle orderArticle = (OrderArticle) article;
                if (orderArticle.getStatus() != this.servicesConf
                        .findStatusOrder(ServicesConf.CANCELED_WITH_CHARGE)) {
                    orderArticle.setStatus(ordenDeVenta.getStatusOrder());
                    try {
                        ordenDeVentaArticuloJpaController.edit(orderArticle);
                    } catch (NonexistentEntityException e) {
                        debug("Error al intentar actualizar status del articulo");
                        debug(e.getMessage());
                    } catch (Exception e) {
                        debug("Error al intentar actualizar status del articulo");
                        debug(e.getMessage());
                    }
                }
            }
        }
    }

    private void updateStatusTransaction(Order ordenDeVenta, Transaction trans) {

        OrdendeventaTransaccionJpaController ordenDeVentaTransaccionJpaController = new OrdendeventaTransaccionJpaController(
                entityManagerFactory);
        try {
            ordenDeVentaTransaccionJpaController.updateStatus(ordenDeVenta.getId(), trans.getStatus(),
                                                              trans.getNumber());
        } catch (JpaException e) {
            e.printStackTrace();
            logger.error("Error al actualizar orden de venta");
        }

    }

    public SpecialOrderResponse updateSpecialOrderStatus(Order order) {
        SpecialOrderResponse result = new SpecialOrderResponse();

        debug("Buscando pedido especial id: " + order.getServerOrderId());
        OrdendeventaJpaController ordendeventaJpa = new OrdendeventaJpaController(entityManagerFactory);
        Order ordendeventa = ordendeventaJpa.findOrderById(order.getServerOrderId(), idExoneradotipoNull);

        if (ordendeventa != null) {
            debug("Pedido Especial encontrado");
            debug("OrderID: " + ordendeventa.getId());
            debug("Estado: " + order.getStatusOrder().getDescription());
            debug("Origen: " + order.getOrderType().getOrigin().getValue());
            if (ordendeventa.isArticlesInStore() && order.getStatusOrder()
                    .equals(this.servicesConf.findStatusOrder(ServicesConf.PENDING_FOR_CONFIRM_BILL))) {

                if (order.getOrderType().getOrigin() == OrderType.Source.AS400) {
                    order.setStatusOrder(this.servicesConf.findStatusOrder(ServicesConf.PENDING_FOR_BILL));
                } else if (order.getOrderType().getOrigin() == OrderType.Source.SALES_COMPANY) {
                    order.setStatusOrder(this.servicesConf
                            .findStatusOrder(ServicesConf.PENDING_FOR_BILL_SALES_COMPANY));
                }
            }
            debug("Estatus a asignar: " + order.getStatusOrder().getDescription());
            ordendeventa.setStatusOrder(order.getStatusOrder());

            try {
                ordendeventaJpa.editWithQuery(ordendeventa);
                debug("Pedido especial actualizado con el status " + order.getStatusOrder().getDescription());
                debug("actualizando status de articulos");
                updateStatusArticleWithQuery(ordendeventa);
                debug("status de articulos actualizados");

                if (order.getTransactionsSale() != null && order.getTransactionsSale().size() > 0) {
                    debug("Agregando registros en ordendeventatransaccion");
                    OrdendeventaTransaccionJpaController ordenDeVentaTransaccionJpaController = new OrdendeventaTransaccionJpaController(
                            entityManagerFactory);
                    for (Transaction trans : order.getTransactionsSale()) {
                        debug("agregando la transaccion #" + trans.getNumber() + " a la orden de venta #"
                                + order.getId());
                        debug("transaccion realizada por la caja #" + trans.getPosId());
                        if (order.getStatusOrder()
                                .equals(this.servicesConf.findStatusOrder(ServicesConf.BILLED))) {
                            trans.setStatus("S");
                            ordenDeVentaTransaccionJpaController.create(ordendeventa, trans);
                        } else if (order.getStatusOrder()
                                .equals(this.servicesConf.findStatusOrder(ServicesConf.PENDING_FOR_BILL))
                                || order.getStatusOrder()
                                        .equals(this.servicesConf
                                                .findStatusOrder(ServicesConf.PENDING_FOR_CONDITIONALLY_BILL))
                                || order.getStatusOrder().equals(this.servicesConf
                                        .findStatusOrder(ServicesConf.PENDING_FOR_BILL_SALES_COMPANY))) {
                            trans.setStatus("N");
                            updateStatusTransaction(ordendeventa, trans);
                        }
                    }
                }

                result.setCode(SpecialOrderResponse.OK);
            } catch (IllegalOrphanException e) {
                result.setCode(SpecialOrderResponse.ERROR);
                debug("Error al intentar actualizar el status del pedido especial", e);
                e.printStackTrace();
            } catch (NonexistentEntityException e) {
                result.setCode(SpecialOrderResponse.ERROR);
                debug("Error al intentar actualizar el status del pedido especial", e);
                e.printStackTrace();
            } catch (Exception e) {
                result.setCode(SpecialOrderResponse.ERROR);
                debug("Error al intentar actualizar el status del pedido especial", e);
                e.printStackTrace();
            }
        } else {
            result.setCode(SpecialOrderResponse.SPECIAL_ORDERS_NOT_FOUND);
            debug("Pedido especial no encontrado");
        }

        return result;
    }

    public SpecialOrderResponse requestOrderId(Transaction data, Session session) {

        SpecialOrderResponse result = new SpecialOrderResponse();
        debug("Buscando Id de order con transaccion #" + data.getNumber() + " en ordendeventatransaccion");

        OrdendeventaTransaccionJpaController ordendeventatransaccionjpacontroller = new OrdendeventaTransaccionJpaController(
                entityManagerFactory);
        OrderTransaction orderTransaccion = ordendeventatransaccionjpacontroller
                .findByTransaction(data, idExoneradotipoNull);

        if (orderTransaccion != null) {
            debug("Id encontrado -> #" + orderTransaccion.getOrder().getId());
            result.setData(orderTransaccion.getOrder());
            result.setData(Arrays.asList(orderTransaccion.getOrder()));
            result.setCode(SpecialOrderResponse.OK);
        } else {
            debug("no se encontro el registro en ordendeventatransaccion");
        }
        return result;
    }

    public RMIServerResponse requestTransactionNumbers(Long orderId, Session session) {

        RMIServerResponse result = new RMIServerResponse();
        // logger.debug("Buscando Id de order con transaccion #"+data.getNumber()+" en
        // ordendeventatransaccion");
        OrdendeventaTransaccionJpaController ordendeventatransaccionjpacontroller = new OrdendeventaTransaccionJpaController(
                entityManagerFactory);
        ArrayList<Long> numbers = ordendeventatransaccionjpacontroller.findTransactionNumbers(orderId);

        if (numbers != null) {
            debug(numbers.size() + " transacciones encontradas");
            result.setData(numbers);
            result.setCode(SpecialOrderResponse.OK);
            debug("requestTransactionNumbers " + RMIServerResponse.class + " " + result.getData());
        } else {
            debug("No se encontraron transacciones");
        }
        return result;
    }

    private void debug(String msg) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        }
    }

    private void debug(String msg, Exception e) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg, e);
        }
    }

    private void info(String msg) {
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }
    }

    private void updateStatusArticleWithQuery(Order ordenDeVenta) {

        if (ordenDeVenta.getArticlesCount().compareTo(CRBigDecimal.ZERO) > 0) {
            OrdendeventaArticuloJpaController ordenDeVentaArticuloJpaController = new OrdendeventaArticuloJpaController(
                    entityManagerFactory);
            for (Article article : ordenDeVenta.getArticles()) {
                OrderArticle orderArticle = (OrderArticle) article;
                if (orderArticle.getStatus() != this.servicesConf
                        .findStatusOrder(ServicesConf.CANCELED_WITH_CHARGE)) {
                    orderArticle.setStatus(ordenDeVenta.getStatusOrder());
                    try {
                        ordenDeVentaArticuloJpaController.editWithQuery(orderArticle);
                    } catch (NonexistentEntityException e) {
                        debug("Error al intentar actualizar status del articulo");
                        debug(e.getMessage());
                    } catch (Exception e) {
                        debug("Error al intentar actualizar status del articulo");
                        debug(e.getMessage());
                    }
                }
            }
        }
    }

    @PostConstruct
    public void init() {
        new CacheEPA(entityManagerFactory, idExoneradotipoNull);
    }

}
