/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.models.OrderTransaction;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Ordendeventatransaccion;

/**
 */
public class OrdendeventaTransaccionJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Ordendeventatransaccion";

    /**
     * Field jpaController.
     */
    private crjpa400.OrdendeventatransaccionJpaController jpaController;

    /**
     * Constructor for OrdendeventaTransaccionJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public OrdendeventaTransaccionJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.emf = emf;
        this.jpaController = new crjpa400.OrdendeventatransaccionJpaController(this.emf);
    }

    /**
     * Method create.
     * 
     * @param ordendeventa Order
     * @param transaction Transaction
     */
    public void create(Order ordendeventa, Transaction transaction) {
        Ordendeventatransaccion ordendeventatransaccion = toJpa(ordendeventa, transaction);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ordendeventatransaccion);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error --> " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

    }

    /**
     * Method toJpa.
     * 
     * @param ordendeventa Order
     * @param transaction Transaction
     * @return Ordendeventatransaccion
     */
    private Ordendeventatransaccion toJpa(Order ordendeventa, Transaction transaction) {
        Ordendeventatransaccion result = new Ordendeventatransaccion();
        OrdendeventaJpaController ordendeventaJpaController = new OrdendeventaJpaController(this.emf);
        result.setIdOrdendeventa(ordendeventaJpaController.toJpa(ordendeventa));
        result.setIdTransaccion(new Long(transaction.getNumber()));
        result.setCaja(Integer.parseInt(transaction.getPosId()));
        result.setEstaactivo(transaction.getStatus().charAt(0));

        return result;
    }

    private OrderTransaction fromJpa(Ordendeventatransaccion ordendeventaTransaccion,
                                     Long getIdExoneradotipoNull) {
        OrderTransaction result = new OrderTransaction();

        OrdendeventaJpaController ordenDeVentaJpaController = new OrdendeventaJpaController(this.emf);
        result.setId(ordendeventaTransaccion.getId());
        result.setIdTransaccion(ordendeventaTransaccion.getIdTransaccion());
        result.setOrder(ordenDeVentaJpaController.fromJpa(ordendeventaTransaccion.getIdOrdendeventa(),
                                                          getIdExoneradotipoNull));
        result.setStoreId(ordendeventaTransaccion.getCaja());

        return result;
    }

    public OrderTransaction findByTransaction(Transaction data, Long getIdExoneradotipoNull) {

        OrderTransaction result = null;
        EntityManager em = jpaController.getEntityManager();
        try {
            Query query = em
                    .createQuery("SELECT o FROM Ordendeventatransaccion o WHERE o.caja = :caja and o.idTransaccion = :idTransaccion");
            query.setParameter("caja", Integer.parseInt(data.getPosId()));
            query.setParameter("idTransaccion", new Long(data.getNumber()));
            query.setMaxResults(1);
            Ordendeventatransaccion tmp = (Ordendeventatransaccion) query.getSingleResult();
            result = fromJpa(tmp, getIdExoneradotipoNull);
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
     * Method findTransactionNumbers.
     * 
     * @param orderId Long
     * @return ArrayList<Long>
     */
    public ArrayList<Long> findTransactionNumbers(Long orderId) {
        ArrayList<Long> numbers = null;
        EntityManager em = jpaController.getEntityManager();
        try {
            Query query = em
                    .createQuery("SELECT o FROM Ordendeventatransaccion o WHERE o.idOrdendeventa.id = :id AND o.estaactivo='S'");
            query.setParameter("id", orderId);
            query.setMaxResults(1);
            Ordendeventatransaccion tmp = (Ordendeventatransaccion) query.getSingleResult();
            if (tmp != null) {
                numbers = new ArrayList<Long>();
                for (Ordendeventatransaccion tr : tmp.getIdOrdendeventa().getOrdendeventatransaccionList()) {
                    if (ActiveValues.S.getString().charAt(0) == tr.getEstaactivo()) {
                        numbers.add(tr.getIdTransaccion());
                    }
                }
            }
        } catch (javax.persistence.NoResultException ex) {
            numbers = null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return numbers;
    }

    /**
     * Method updateStatus.
     * 
     * @param orderId Long
     * @param newStatus String
     * @param number String
     * @return boolean
     * @throws JpaException
     */
    public boolean updateStatus(Long orderId, String newStatus, String number) throws JpaException {
        boolean updated = false;
        EntityManager em = jpaController.getEntityManager();

        try {
            Query query = em
                    .createQuery("SELECT o FROM Ordendeventatransaccion o WHERE o.idOrdendeventa.id = :id AND o.estaactivo = 'S' AND o.idTransaccion = :idtrans");
            query.setParameter("id", orderId);
            query.setParameter("idtrans", Long.valueOf(number));
            query.setMaxResults(1);
            Ordendeventatransaccion tmp = (Ordendeventatransaccion) query.getSingleResult();
            if (tmp != null) {
                tmp.setEstaactivo(newStatus.charAt(0));
                try {
                    this.jpaController.edit(tmp);
                    updated = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new JpaException();
                }
            }

        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return updated;
    }

}
