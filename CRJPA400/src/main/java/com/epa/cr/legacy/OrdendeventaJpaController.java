/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa400.controller.exceptions.IllegalOrphanException;
import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.crjpa400.controller.exceptions.NonexistentEntityException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.models.Deposit;
import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.models.Transaction.Source;
import com.becoblohm.cr.models.Transaction.SourceTransactionType;
import com.becoblohm.cr.models.User;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Abono;
import crjpa400.Cliente;
import crjpa400.Estadoordendeventa;
import crjpa400.Ordendeventa;
import crjpa400.Ordendeventaarticulo;

/**
 */
public class OrdendeventaJpaController extends AbstractJPAController {

    /**
     * 
     */
    private static String entityName = "Cliente";

    /**
     * Field serialVersionUID. (value is -291285747962452722)
     */
    private static final long serialVersionUID = -291285747962452722L;

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field jpaController.
     */
    private crjpa400.OrdendeventaJpaController jpaController;

    /**
     * Field estadoordendeventaJpa.
     */
    EstadoordendeventaJpaController estadoordendeventaJpa = new EstadoordendeventaJpaController(this.emf);

    /**
     * Constructor for OrdendeventaJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public OrdendeventaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.emf = emf;
        this.jpaController = new crjpa400.OrdendeventaJpaController(this.emf);
    }

    /**
     * Method findOrdenDeVentaActivaByClientId_Status.
     * 
     * @param customer Client
     * @param toShow List<String>
     * @return Vector<Order>
     */
    public Vector<Order> findOrdenDeVentaActivaByClientId_Status(Client customer, List<String> toShow) {
        EntityManager em = emf.createEntityManager();
        Vector<Ordendeventa> resultList = null;
        Vector<Order> orders = null;
        try {
            Query query = em
                    .createQuery("SELECT o FROM Ordendeventa o WHERE o.numeroIdentificacionCliente.numeroIdentificacionCliente = :clientId and o.estado IN :names order by o.idTipoordendeventa.id, o.numero");
            query.setParameter("clientId", customer.getIdNumber());
            query.setParameter("names", toShow);

            resultList = (Vector<Ordendeventa>) query.getResultList();
            if (resultList != null) {
                orders = new Vector<Order>(resultList.size());
                for (Ordendeventa order : resultList) {
                    orders.add(fromJpa(order));
                }
            }

        } catch (Exception ex) {
            resultList = null;
            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
        return orders;
    }

    /**
     * Method findOrdenDeVentaActivaByClientId_OnlyToShow.
     * 
     * @param customer Client
     * @return Vector<Order>
     */
    public Vector<Order> findOrdenDeVentaActivaByClientId_OnlyToShow(Client customer) {
        EntityManager em = emf.createEntityManager();
        Vector<Ordendeventa> resultList = null;
        Vector<Order> orders = null;
        try {
            Query query = em
                    .createQuery("SELECT o FROM Ordendeventa o WHERE o.numeroIdentificacionCliente.numeroIdentificacionCliente = :clientId and o.idEstadoordendenventa.ordenVisible ='S' order by o.idTipoordendeventa.id, o.numero");
            query.setParameter("clientId", customer.getIdNumber());

            resultList = (Vector<Ordendeventa>) query.getResultList();
            if (resultList != null) {
                orders = new Vector<Order>(resultList.size());
                for (Ordendeventa order : resultList) {
                    orders.add(fromJpa(order));
                }
            }

        } catch (Exception ex) {
            resultList = null;
            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
        return orders;
    }

    /**
     * Method findOrdenDeVentaActivaByClientId_TypeOrder.
     * 
     * @param customer Client
     * @param typeOrderId Long
     * @param date Date
     * @return Vector<Order>
     */
    public Vector<Order> findOrdenDeVentaActivaByClientId_TypeOrder(Client customer, Long typeOrderId, Date date) {
        EntityManager em = emf.createEntityManager();
        Vector<Ordendeventa> resultList = null;
        Vector<Order> orders = null;
        try {
            Query query = em
                    .createQuery("SELECT o FROM Ordendeventa o WHERE o.numeroIdentificacionCliente.numeroIdentificacionCliente = :clientId and o.idTipoordendeventa.id = :typeOrderId and o.fecha = :fecha");
            query.setParameter("clientId", customer.getIdNumber());
            query.setParameter("typeOrderId", typeOrderId);
            query.setParameter("fecha", date);

            resultList = (Vector<Ordendeventa>) query.getResultList();
            if (resultList != null) {
                orders = new Vector<Order>(resultList.size());
                for (Ordendeventa order : resultList) {
                    orders.add(fromJpa(order));
                }
            }

        } catch (Exception ex) {
            resultList = null;
            System.out.println(ex.getMessage());
        } finally {
            em.close();
        }
        return orders;
    }

    /**
     * Method findOrdenDeVentaByNumber.
     * 
     * @param number long
     * @return Order
     */
    public Order findOrdenDeVentaByNumber(long number) {

        Order order = null;
        EntityManager em = emf.createEntityManager();
        Ordendeventa result = null;
        try {
            Query query = em.createNamedQuery("Ordendeventa.findByNumero");
            query.setParameter("numero", number);
            query.setMaxResults(1);
            result = (Ordendeventa) query.getSingleResult();
            order = fromJpa(result);
        } catch (Exception ex) {
            result = null;
        } finally {
            em.close();
        }
        return order;
    }

    /**
     * Method fromJpa.
     * 
     * @param ordendeventa Ordendeventa
     * @return Order
     */
    public Order fromJpa(Ordendeventa ordendeventa) {
        Order specialOrder = new Order();

        ClienteJpaController clienteJpa = new ClienteJpaController(this.emf);

        specialOrder.setId(ordendeventa.getId());
        specialOrder.setServerOrderId(ordendeventa.getId());
        specialOrder.setNumber(String.valueOf(ordendeventa.getNumero()));
        specialOrder.setOrderNumber(String.valueOf(ordendeventa.getNumero()));
        specialOrder.setStatusOrder(estadoordendeventaJpa.fromJPA(ordendeventa.getIdEstadoordendenventa()));
        specialOrder.setDate(ordendeventa.getFecha());

        specialOrder.setTotalTaxAmount(new CRBigDecimal(ordendeventa.getMontoImpuesto().doubleValue()));
        if (ordendeventa.getTienda() != null) {
            specialOrder.setStoreIdSale(ordendeventa.getTienda());
            specialOrder.setStore(String.valueOf(ordendeventa.getTienda()));
        }
        TipoordendeventaJpaController tipoOrdenJpa = new TipoordendeventaJpaController(emf);
        specialOrder.setOrderType(tipoOrdenJpa.fromJPA(ordendeventa.getIdTipoordendeventa()));
        specialOrder.setClient(clienteJpa.fromJpa(ordendeventa.getNumeroIdentificacionCliente()));
        specialOrder.setOrderUser(ordendeventa.getUsuario());
        specialOrder.setSaleOrigin(Source.PreOrder);
        specialOrder.setTransactionType(SourceTransactionType.Order);
        specialOrder.setTotalCost(new CRBigDecimal(
                ordendeventa.getMontoBase().add(ordendeventa.getMontoImpuesto()).doubleValue()));

        specialOrder.setPurchaseOrderId(ordendeventa.getIdOrdenDeCompra());
        specialOrder.setCreditsDays(ordendeventa.getDiasCredito());
        BigInteger fichaResponsableEpa = ordendeventa.getFichaResponsableEpa();
        if (fichaResponsableEpa != null && fichaResponsableEpa.longValue() > 0) {
            specialOrder.setEPALiableId(fichaResponsableEpa.longValue());
            specialOrder.setEPALiableName(ordendeventa.getNombreResponsableEpa());
        }

        if (ordendeventa.getIdOrdenDeVentaPadre() != null) {
            specialOrder.setFatherOrder(fromJpa(ordendeventa.getIdOrdenDeVentaPadre()));
        }
        specialOrder.setPenaltyAmount(new CRBigDecimal(ordendeventa.getMontoPenalizacion().doubleValue()));
        specialOrder.setFirstDepositAmount(new CRBigDecimal(ordendeventa.getMontoPrimerAbono().doubleValue()));
        specialOrder.setArticlesInStore(ActiveValues.get(String.valueOf(ordendeventa.getArticulosEnTienda()))
                .getValue());
        specialOrder.setValidity(ordendeventa.getVigencia());

        specialOrder.setArticles(new ArrayList<Article>());
        OrdendeventaArticuloJpaController ordendeventaArticuloJpaController = new OrdendeventaArticuloJpaController(
                this.emf);
        for (Ordendeventaarticulo ordendeventaArticulo : ordendeventa.getOrdendeventaarticuloList()) {
            specialOrder.addArticle(ordendeventaArticuloJpaController.fromJpa(ordendeventaArticulo));
        }

        TreeMap<Integer, Abono> abonoList = new TreeMap<Integer, Abono>();
        for (Abono abono : ordendeventa.getAbonoList()) {
            abonoList.put(abono.getId().intValue(), abono);
        }

        specialOrder.setDeposits(new ArrayList<Deposit>());
        AbonoJpaController abonoJpa = new AbonoJpaController(this.emf);
        for (Abono abono : abonoList.values()) {
            specialOrder.addDeposit(abonoJpa.fromJpa(abono));
        }

        if (ordendeventa.getUsuario() != null && ordendeventa.getUsuario().trim().length() > 0) {
            String[] tmpUsuario = ordendeventa.getUsuario().split("%");

            specialOrder.setPosId(tmpUsuario[0]);

            UsuarioJpaController usuarioJpa = new UsuarioJpaController(this.emf);
            User user = usuarioJpa.findUsuario(new Long(tmpUsuario[0]));

            if (user != null) {
                specialOrder.setUser(user);
            }

        }

        return specialOrder;
    }

    public Order fromJpa(Ordendeventa ordendeventa, Long getIdExoneradotipoNull) {
        Order specialOrder = new Order();

        ClienteJpaController clienteJpa = new ClienteJpaController(this.emf);

        specialOrder.setId(ordendeventa.getId());
        specialOrder.setServerOrderId(ordendeventa.getId());
        specialOrder.setNumber(String.valueOf(ordendeventa.getNumero()));
        specialOrder.setOrderNumber(String.valueOf(ordendeventa.getNumero()));
        specialOrder.setStatusOrder(estadoordendeventaJpa.fromJPA(ordendeventa.getIdEstadoordendenventa()));
        specialOrder.setDate(ordendeventa.getFecha());

        specialOrder.setTotalTaxAmount(new CRBigDecimal(ordendeventa.getMontoImpuesto().doubleValue()));
        if (ordendeventa.getTienda() != null) {
            specialOrder.setStoreIdSale(ordendeventa.getTienda());
            specialOrder.setStore(String.valueOf(ordendeventa.getTienda()));
        }
        TipoordendeventaJpaController tipoOrdenJpa = new TipoordendeventaJpaController(emf);
        specialOrder.setOrderType(tipoOrdenJpa.fromJPA(ordendeventa.getIdTipoordendeventa()));
        specialOrder.setClient(clienteJpa.fromJpa(ordendeventa.getNumeroIdentificacionCliente(),
                                                  getIdExoneradotipoNull));
        specialOrder.setOrderUser(ordendeventa.getUsuario());
        specialOrder.setSaleOrigin(Source.PreOrder);
        specialOrder.setTransactionType(SourceTransactionType.Order);
        specialOrder.setTotalCost(new CRBigDecimal(
                ordendeventa.getMontoBase().add(ordendeventa.getMontoImpuesto()).doubleValue()));

        specialOrder.setPurchaseOrderId(ordendeventa.getIdOrdenDeCompra());
        specialOrder.setCreditsDays(ordendeventa.getDiasCredito());
        BigInteger fichaResponsableEpa = ordendeventa.getFichaResponsableEpa();
        if (fichaResponsableEpa != null && fichaResponsableEpa.longValue() > 0) {
            specialOrder.setEPALiableId(fichaResponsableEpa.longValue());
            specialOrder.setEPALiableName(ordendeventa.getNombreResponsableEpa());
        }

        if (ordendeventa.getIdOrdenDeVentaPadre() != null) {
            specialOrder.setFatherOrder(fromJpa(ordendeventa.getIdOrdenDeVentaPadre(), getIdExoneradotipoNull));
        }
        specialOrder.setPenaltyAmount(new CRBigDecimal(ordendeventa.getMontoPenalizacion().doubleValue()));
        specialOrder.setFirstDepositAmount(new CRBigDecimal(ordendeventa.getMontoPrimerAbono().doubleValue()));
        specialOrder.setArticlesInStore(ActiveValues.get(String.valueOf(ordendeventa.getArticulosEnTienda()))
                .getValue());
        specialOrder.setValidity(ordendeventa.getVigencia());

        specialOrder.setArticles(new ArrayList<Article>());
        OrdendeventaArticuloJpaController ordendeventaArticuloJpaController = new OrdendeventaArticuloJpaController(
                this.emf);
        for (Ordendeventaarticulo ordendeventaArticulo : ordendeventa.getOrdendeventaarticuloList()) {
            specialOrder.addArticle(ordendeventaArticuloJpaController.fromJpa(ordendeventaArticulo));
        }

        TreeMap<Integer, Abono> abonoList = new TreeMap<Integer, Abono>();
        for (Abono abono : ordendeventa.getAbonoList()) {
            abonoList.put(abono.getId().intValue(), abono);
        }

        specialOrder.setDeposits(new ArrayList<Deposit>());
        AbonoJpaController abonoJpa = new AbonoJpaController(this.emf);
        for (Abono abono : abonoList.values()) {
            specialOrder.addDeposit(abonoJpa.fromJpa(abono));
        }

        if (ordendeventa.getUsuario() != null && ordendeventa.getUsuario().trim().length() > 0) {
            String[] tmpUsuario = ordendeventa.getUsuario().split("%");

            specialOrder.setPosId(tmpUsuario[0]);

            UsuarioJpaController usuarioJpa = new UsuarioJpaController(this.emf);
            User user = usuarioJpa.findUsuario(new Long(tmpUsuario[0]));

            if (user != null) {
                specialOrder.setUser(user);
            }

        }

        return specialOrder;
    }

    /**
     * Method findOrderById.
     * 
     * @param idSpecialOrder long
     * @return Order
     */
    public Order findOrderById(long idSpecialOrder) {

        EntityManager em = emf.createEntityManager();
        Ordendeventa result = null;
        Order result2 = null;
        try {
            Query query = em.createNamedQuery("Ordendeventa.findById");
            query.setParameter("id", idSpecialOrder);
            query.setMaxResults(1);
            result = (Ordendeventa) query.getSingleResult();
            result2 = fromJpa(result);
        } catch (Exception ex) {
            result = null;
        } finally {
            em.close();
        }

        return result2;
    }

    /**
     * Method findOrdenDeVentaByIdJpa.
     * 
     * @param idSpecialOrder long
     * @return Ordendeventa
     */
    public Ordendeventa findOrdenDeVentaByIdJpa(long idSpecialOrder) {

        EntityManager em = emf.createEntityManager();
        Ordendeventa result = null;
        try {
            Query query = em.createNamedQuery("Ordendeventa.findById");
            query.setParameter("id", idSpecialOrder);
            query.setMaxResults(1);
            result = (Ordendeventa) query.getSingleResult();
        } catch (Exception ex) {
            result = null;
        } finally {
            em.close();
        }

        return result;
    }

    /**
     * Method edit.
     * 
     * @param ordenDeVenta Order
     * @throws IllegalOrphanException
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Order ordenDeVenta) throws IllegalOrphanException, NonexistentEntityException, Exception {

        Ordendeventa orden = toJpa(ordenDeVenta);
        merge(orden);

    }

    /**
     * Method merge.
     * 
     * @param orden Ordendeventa
     * @throws JpaException
     */
    private void merge(Ordendeventa orden) throws JpaException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(orden);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new JpaException(e);
        } finally {
            if (em.isOpen()) {
                em.clear();
                em.close();
            }
        }

    }

    /**
     * Method edit.
     * 
     * @param ordenDeVenta Order
     * @param fromUpdateStatus boolean
     * @throws IllegalOrphanException
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Order ordenDeVenta, boolean fromUpdateStatus) throws IllegalOrphanException,
            NonexistentEntityException,
            Exception {
        Ordendeventa orden = toJpa(ordenDeVenta, fromUpdateStatus);
        merge(orden);
    }

    /**
     * Method toJpa.
     * 
     * @param ordenDeVenta Order
     * @param fromUpdateStatus boolean
     * @return Ordendeventa
     */
    private Ordendeventa toJpa(Order ordenDeVenta, boolean fromUpdateStatus) {
        Ordendeventa result = new Ordendeventa();

        result.setId(ordenDeVenta.getId());
        result.setIdEstadoordendenventa(estadoordendeventaJpa.toJPA(ordenDeVenta.getStatusOrder(),
                                                                    fromUpdateStatus));

        return result;
    }

    /**
     * Method toJpa.
     * 
     * @param ordenDeVenta Order
     * @return Ordendeventa
     */
    public Ordendeventa toJpa(Order ordenDeVenta) {
        Ordendeventa result = new Ordendeventa();

        result.setId(ordenDeVenta.getId());
        result.setAbonoList(new ArrayList<Abono>());

        if (result.getId() > 0) {
            Ordendeventa oldOrdenDeVenta = findOrdenDeVentaByIdJpa(result.getId());
            result.setAbonoList(oldOrdenDeVenta.getAbonoList());
            result.setOrdendeventaarticuloList(oldOrdenDeVenta.getOrdendeventaarticuloList());
        } else {
            result.setOrdendeventaarticuloList(new ArrayList<Ordendeventaarticulo>());
            for (Article article : ordenDeVenta.getArticles()) {
                OrdendeventaArticuloJpaController ordenDeVentaArticuloJpaController = new OrdendeventaArticuloJpaController(
                        this.emf);
                result.getOrdendeventaarticuloList().add(ordenDeVentaArticuloJpaController.toJpa(article, false));
            }
        }

        result.setIdEstadoordendenventa(estadoordendeventaJpa.toJPA(ordenDeVenta.getStatusOrder()));
        result.setFecha(ordenDeVenta.getDate());

        TipoordendeventaJpaController tipoOrderDeVentaJpaController = new TipoordendeventaJpaController(this.emf);
        result.setIdTipoordendeventa(tipoOrderDeVentaJpaController.toJpa(ordenDeVenta.getOrderType()));
        result.setMontoBase(ordenDeVenta.getBaseAmount().getValue());
        result.setMontoImpuesto(ordenDeVenta.getTaxAmount().getValue());
        result.setNumero(Integer.parseInt(ordenDeVenta.getNumber()));
        result.setNumeroIdentificacionCliente(new Cliente(ordenDeVenta.getClient().getIdNumber()));

        if (ordenDeVenta.getId() > 0) {
            Ordendeventa oldOrderDeVenta = jpaController.findOrdendeventa(ordenDeVenta.getId());
            result.setAbonoList(oldOrderDeVenta.getAbonoList());
            result.setOrdendeventatransaccionList(oldOrderDeVenta.getOrdendeventatransaccionList());

        }
        result.setTienda(Integer.parseInt(ordenDeVenta.getStore()));
        result.setUsuario(ordenDeVenta.getOrderUser());
        result.setIdOrdenDeCompra(ordenDeVenta.getPurchaseOrderId());
        result.setDiasCredito(ordenDeVenta.getCreditsDays());
        if (ordenDeVenta.getEPALiableId() > 0) {
            result.setFichaResponsableEpa(new BigInteger(String.valueOf(ordenDeVenta.getEPALiableId())));
            result.setNombreResponsableEpa(ordenDeVenta.getEPALiableName());
        } else {
            result.setFichaResponsableEpa(BigInteger.ZERO);
            result.setNombreResponsableEpa("");
        }

        if (ordenDeVenta.getFatherOrder() != null) {
            result.setIdOrdenDeVentaPadre(toJpa(ordenDeVenta.getFatherOrder()));
        }

        result.setMontoPenalizacion(ordenDeVenta.getPenaltyAmount().getValue());
        result.setMontoPrimerAbono(ordenDeVenta.getFirstDepositAmount().getValue());
        result.setArticulosEnTienda(ActiveValues.get(ordenDeVenta.isArticlesInStore()).getString().charAt(0));
        result.setVigencia(ordenDeVenta.getValidity());

        return result;
    }

    /**
     * Method create.
     * 
     * @param order Order
     * @throws JpaException
     */
    public void create(Order order) throws JpaException {

        Ordendeventa orderJpa = toJpa(order);
        try {
            jpaController.create(orderJpa);
        } catch (Exception e) {
            System.out.println("Error --> " + e.getMessage());
            throw new JpaException();
        }

        order.setId(orderJpa.getId());
    }

    /**
     * Method findOrdenDeVentaByType_Status.
     * 
     * @param orderTypeIDWaitingSale long
     * @param statusOrderStarted char
     * @param date Date
     * @return List<Order>
     */
    public List<Order> findOrdenDeVentaByType_Status(long orderTypeIDWaitingSale, String statusOrderStarted,
                                                     Date date) {

        EntityManager em = emf.createEntityManager();
        Vector<Ordendeventa> result = new Vector<Ordendeventa>();
        try {
            Query query = em
                    .createQuery("SELECT o FROM Ordendeventa o WHERE o.idTipoordendeventa.id = :id_tipoOrdenDeVenta and o.fecha = :fecha");
            query.setParameter("id_tipoOrdenDeVenta", orderTypeIDWaitingSale);
            query.setParameter("fecha", date);

            result = (Vector<Ordendeventa>) query.getResultList();
        } catch (Exception ex) {
            System.out.println("Error -> " + ex.getMessage());
        } finally {
            em.close();
        }

        if (result.size() > 0) {
            ArrayList<Order> resultReturn = new ArrayList<Order>();
            for (Ordendeventa ordendeventa : result) {
                resultReturn.add(fromJpa(ordendeventa));
            }
            return resultReturn;
        } else {
            return null;
        }
    }

    /**
     * Method findOrdenDeVentaList.
     * 
     * @return Vector<Order>
     */
    public Vector<Order> findOrdenDeVentaList() {
        Vector result = new Vector<Order>();
        List<Ordendeventa> tmp = jpaController.findOrdendeventaEntities();
        for (Iterator iterator = tmp.iterator(); iterator.hasNext();) {
            Ordendeventa ordendeventa = (Ordendeventa) iterator.next();
            result.add(fromJpa(ordendeventa));
        }

        return result;
    }

    /**
     * Method findOrdenDeVentaActivaByClientId_TypeOrder.
     * 
     * @param customer Client
     * @param typeOrderId Long
     * @param date Date
     * @return List<Order>
     */
    public List<Order> findOrdenDeVentaActivaByClientId_TypeOrder(Client customer, Long typeOrderId, Date date,
                                                                  Long getIdExoneradotipoNull) {
        EntityManager em = emf.createEntityManager();
        List<Ordendeventa> resultList = null;
        List<Order> orders = null;
        try {
            Query query = em
                    .createQuery("SELECT o FROM Ordendeventa o WHERE o.numeroIdentificacionCliente.numeroIdentificacionCliente = :clientId and o.idTipoordendeventa.id = :typeOrderId and o.fecha = :fecha");
            query.setParameter("clientId", customer.getIdNumber());
            query.setParameter("typeOrderId", typeOrderId);
            query.setParameter("fecha", date);

            resultList = (List<Ordendeventa>) query.getResultList();
            if (resultList != null) {
                orders = new ArrayList<Order>(resultList.size());
                for (Ordendeventa order : resultList) {
                    orders.add(fromJpa(order, getIdExoneradotipoNull));
                }
            }

        } catch (Exception ex) {
            resultList = null;
            System.out.println(ex.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return orders;
    }

    /**
     * Method findOrdenDeVentaByNumber.
     * 
     * @param number long
     * @return Order
     */
    public Order findOrdenDeVentaByNumber(long number, Long getIdExoneradotipoNull) {

        Order order = null;
        EntityManager em = emf.createEntityManager();
        Ordendeventa result = null;
        try {
            Query query = em.createNamedQuery("Ordendeventa.findByNumero");
            query.setParameter("numero", number);
            query.setMaxResults(1);
            result = (Ordendeventa) query.getSingleResult();
            order = fromJpa(result, getIdExoneradotipoNull);
        } catch (Exception ex) {
            result = null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return order;
    }

    /**
     * Method findOrdenDeVentaActivaByClientId_OnlyToShow.
     * 
     * @param customer Client
     * @return List<Order>
     */
    public List<Order> findOrdenDeVentaActivaByClientId_OnlyToShow(Client customer, Long getIdExoneradotipoNull) {
        String idNumber = customer.getIdNumber().trim();
        List<Order> orders = CacheEPA.get(idNumber);
        if (orders == null) {
            orders = findOrdenDeVentaActivaByClientId_OnlyToShow(idNumber, getIdExoneradotipoNull);
            if (orders != null && orders.size() > 0) {
                CacheEPA.put(idNumber, orders);
            }
        }
        return orders;

    }

    // @Cacheable("orders")
    public List<Order> findOrdenDeVentaActivaByClientId_OnlyToShow(String customergetIdNumber,
                                                                   Long getIdExoneradotipoNull) {
        EntityManager em = emf.createEntityManager();
        List<Ordendeventa> resultList = null;
        List<Order> orders = null;
        try {
            Query query = em
                    .createQuery("SELECT o FROM Ordendeventa o WHERE o.numeroIdentificacionCliente.numeroIdentificacionCliente = :clientId and o.idEstadoordendenventa.ordenVisible ='S'");
            // .createQuery("SELECT o FROM Ordendeventa o WHERE
            // o.numeroIdentificacionCliente.numeroIdentificacionCliente =
            // :clientId and o.idEstadoordendenventa.ordenVisible ='S' order by
            // o.idTipoordendeventa.id, o.numero");
            query.setParameter("clientId", customergetIdNumber);

            resultList = query.getResultList();
            if (resultList != null) {
                orders = new ArrayList<Order>(resultList.size());
                for (Ordendeventa order : resultList) {
                    orders.add(fromJpa(order, getIdExoneradotipoNull));
                }
            }

        } catch (Exception ex) {
            resultList = null;
            System.out.println(ex );
        } finally {
            em.close();
        }
        return orders;
    }

    /**
     * Method findOrderById.
     * 
     * @param idSpecialOrder long
     * @return Order
     */
    public Order findOrderById(long idSpecialOrder, Long getIdExoneradotipoNull) {

        EntityManager em = emf.createEntityManager();
        Ordendeventa result = null;
        Order result2 = null;
        try {
            Query query = em.createNamedQuery("Ordendeventa.findById");
            query.setParameter("id", idSpecialOrder);
            query.setMaxResults(1);
            result = (Ordendeventa) query.getSingleResult();
            result2 = fromJpa(result, getIdExoneradotipoNull);
        } catch (Exception ex) {
            result = null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return result2;
    }

    public void editWithQuery(Order ordenDeVenta) throws IllegalOrphanException,
            NonexistentEntityException,
            Exception {

        Ordendeventa orden = toJpa(ordenDeVenta);
        mergeWithQuery(orden);

    }

    private void mergeWithQuery(Ordendeventa orden) throws JpaException {
        EntityManager em = emf.createEntityManager();
        try {
            /*
             * Cliente cliente = em.find(Cliente.class,
             * orden.getNumeroIdentificacionCliente ().getNumeroIdentificacionCliente());
             * orden.setNumeroIdentificacionCliente(cliente);
             */
            em.getTransaction().begin();
            // em.merge(orden);
            String cliente = orden.getNumeroIdentificacionCliente().getNumeroIdentificacionCliente();

            Estadoordendeventa estadoordendeventa = em.find(Estadoordendeventa.class, 5l);
            Cliente clienteEntity = em.find(Cliente.class, cliente);

            Query query = em.createQuery("UPDATE Ordendeventa SET idEstadoordendenventa = :ID_ESTADOORDENDEVENTA "
                    + "WHERE id = :ID AND numeroIdentificacionCliente = :CLIENTE");

            Long id = orden.getId();

            query.setParameter("ID_ESTADOORDENDEVENTA", estadoordendeventa);
            query.setParameter("ID", id);
            query.setParameter("CLIENTE", clienteEntity);
            int i = query.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new JpaException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.clear();
                em.close();
            }
        }

    }
}
