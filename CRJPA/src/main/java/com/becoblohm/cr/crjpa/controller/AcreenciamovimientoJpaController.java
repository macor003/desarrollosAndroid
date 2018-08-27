/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CreditsMovement400;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Acreenciamovimiento;
import crjpa.Formadepago;
import crjpa.Moneda;
import crjpa.Operacionacreencia;
import crjpa.Tipoacreencia;

public class AcreenciamovimientoJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private final EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Acreenciamovimiento";

    /**
     * Constructor for AcreenciamovimientoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public AcreenciamovimientoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.emf = emf;
    }

    /**
     * Method toJpa.
     * 
     * @param movimiento CreditsMovement400
     * @param isFromCreate boolean
     * @return Acreenciamovimiento
     */
    private Acreenciamovimiento toJpa(CreditsMovement400 movimiento, boolean isFromCreate) {

        Acreenciamovimiento result = new Acreenciamovimiento();

        result.setIdAcreenciamovimientoformadepago(movimiento.getIdAcreenciaMovimientoFormaDePago());
        result.setIdAcreenciamovimientosaldo(movimiento.getIdAcreenciaMovimientoSaldo());
        result.setIpaId(movimiento.getIpaid());
        result.setIpaStatus(movimiento.getIpaStatus());

        result.setIdAcreencia(movimiento.getIdAcreencia());
        Tipoacreencia idTipoacreencia = new Tipoacreencia(movimiento.getIdTipoAcreencia());
        result.setIdTipoacreencia(idTipoacreencia);
        Operacionacreencia idOperacionacreencia = new Operacionacreencia(movimiento.getIdOperacionAcreencia());
        result.setIdOperacionacreencia(idOperacionacreencia);
        Formadepago idFormadepago = new Formadepago(movimiento.getIdFormadePago());
        result.setIdFormadepago(idFormadepago);
        Moneda idMoneda = new Moneda(movimiento.getIdMoneda());
        result.setIdMoneda(idMoneda);
        result.setDocumentoformadepago(movimiento.getDocumentoFormadePago());
        result.setFecha(movimiento.getFecha());

        result.setIdSesion(movimiento.getSessionId());
        result.setNombreunidadnegocio(movimiento.getNombreUnidadNegocio());
        result.setNombreunidadoperativa(movimiento.getNombreUnidadOperativa());
        result.setTienda(movimiento.getTienda());
        result.setOperacion(movimiento.getOperacion());
        result.setAnulaoperacion(movimiento.getAnulaOperacion());
        result.setCaja(movimiento.getCaja());
        result.setCorrelativo(new BigDecimal(movimiento.getCorrelativo()));
        result.setTransaccion(movimiento.getTransaccion());
        result.setCajero(movimiento.getCajero());
        result.setRecibodecaja(movimiento.getReciboDeCaja());
        result.setMontoMonedaLocal(new BigDecimal(String.valueOf(movimiento.getMontoMonedaLocal().doubleValue())));
        result.setMontoMoneda(new BigDecimal(String.valueOf(movimiento.getMontoMoneda().doubleValue())));
        if (movimiento.getVuelto() != null) {
            result.setVuelto(new BigDecimal(movimiento.getVuelto().doubleValue()));
        } else {
            result.setVuelto(BigDecimal.ZERO);
        }

        result.setEstado(movimiento.getEstado());
        result.setControlreplicacion(String.valueOf(movimiento.getControlReplicacion()));
        result.setNumeroIdentificacionCliente(movimiento.getNumeroIdentificacionCliente());
        if (movimiento.getTransaccion() > 0) {
            result.setTransaccion(movimiento.getTransaccion());
        }

        if (isFromCreate) {
            result.setEstasincronizado("N");
        }

        return result;
    }

    public List<Acreenciamovimiento> findByDate(Date date1, Date date2, long ficha, boolean reporteZ) {
        Date fechaInicial = JPAUtils.cleanDate(date1, true);
        Date fechaFinal = JPAUtils.cleanDate(date2, false);
        EntityManager em = emf.createEntityManager();

        try {
            String zReportQuery = "SELECT m FROM Acreenciamovimiento m WHERE m.fecha >= :fechaInicial AND m.fecha <= :fechaFinal AND (m.estado = 'APLICADO' OR m.estado = 'ANULADO')";
            String xReportQuery = "SELECT m FROM Acreenciamovimiento m WHERE m.fecha >= :fechaInicial AND m.fecha <= :fechaFinal AND (m.estado = 'APLICADO' OR m.estado = 'ANULADO') AND m.cajero =:ficha";
            Query query = null;

            if (reporteZ) {
                query = em.createQuery(zReportQuery);
            } else {
                fechaFinal = JPAUtils.cleanDate(date1, false);
                query = em.createQuery(xReportQuery);
                query.setParameter("ficha", ficha);

            }

            query.setParameter("fechaInicial", fechaInicial);
            query.setParameter("fechaFinal", fechaFinal);

            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<Acreenciamovimiento>();
        } finally {
            em.clear();
            em.close();
        }
    }

    public List<Acreenciamovimiento> findMovementsByDate(Date date1, Date date2) {
        Date fechaInicial = JPAUtils.cleanDate(date1, true);
        Date fechaFinal = JPAUtils.cleanDate(date2, false);
        EntityManager em = emf.createEntityManager();

        try {
            String q = "SELECT m FROM Acreenciamovimiento m WHERE m.fecha >= :fechaInicial AND m.fecha <= :fechaFinal AND (m.estado = 'APLICADO' OR m.estado = 'ANULADO')";

            Query query = null;

            fechaFinal = JPAUtils.cleanDate(date1, false);
            query = em.createQuery(q);
            query.setParameter("fechaInicial", fechaInicial);
            query.setParameter("fechaFinal", fechaFinal);

            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<Acreenciamovimiento>();
        } finally {
            em.clear();
            em.close();
        }
    }

    public List<Acreenciamovimiento> findCreditByDateSessionOp(Date date, long sesion, Operacionacreencia idOp) {

        EntityManager em = emf.createEntityManager();

        if (date == null) {
            try {
                String creditMovementQuery = "SELECT m FROM Acreenciamovimiento m WHERE m.idSesion = :sesion AND m.idOperacionacreencia = :idOperacion AND m.estado = :status";
                Query query = null;
                query = em.createQuery(creditMovementQuery);
                query.setParameter("sesion", sesion);
                query.setParameter("idOperacion", idOp);
                query.setParameter("status", "APLICADO");
                return query.getResultList();

            } catch (Exception ex) {

                return new ArrayList<Acreenciamovimiento>();
            } finally {
                em.clear();
                em.close();
            }
        } else {
            try {
                String creditMovementQuery = "SELECT m FROM Acreenciamovimiento m WHERE m.fecha >= :fechaInicial AND m.idSesion = :sesion AND m.idOperacionacreencia = :idOperacion AND m.estado = :status";
                Date fechaInicial = JPAUtils.cleanDate(date, false);
                Query query = null;
                query = em.createQuery(creditMovementQuery);
                query.setParameter("fechaInicial", fechaInicial);
                query.setParameter("sesion", sesion);
                query.setParameter("idOperacion", idOp);
                query.setParameter("status", "APLICADO");
                return query.getResultList();

            } catch (Exception ex) {
                ex.printStackTrace();
                return new ArrayList<Acreenciamovimiento>();
            } finally {
                em.clear();
                em.close();
            }
        }

    }

    public List<Acreenciamovimiento> findCancelByDateSession(Date date, long sesion) {

        EntityManager em = emf.createEntityManager();

        if (date == null) {
            try {
                String creditMovementQuery = "SELECT m FROM Acreenciamovimiento m WHERE m.idSesion = :sesion AND m.idOperacionacreencia =:idOperacion AND m.estado = :status";
                Query query = null;
                query = em.createQuery(creditMovementQuery);
                query.setParameter("sesion", sesion);
                query.setParameter("idOperacion", new Operacionacreencia(2l));
                query.setParameter("status", "ANULADO");
                return query.getResultList();

            } catch (Exception ex) {
                ex.printStackTrace();
                return new ArrayList<Acreenciamovimiento>();
            } finally {
                em.clear();
                em.close();
            }
        } else {
            try {
                String creditMovementQuery = "SELECT m FROM Acreenciamovimiento m WHERE m.fecha >= :fechaInicial AND m.idSesion = :sesion AND m.idOperacionacreencia =:idOperacion AND m.estado = :status";
                Date fechaInicial = JPAUtils.cleanDate(date, false);
                Query query = null;
                query = em.createQuery(creditMovementQuery);
                query.setParameter("fechaInicial", fechaInicial);
                query.setParameter("sesion", sesion);
                query.setParameter("idOperacion", new Operacionacreencia(2l));
                query.setParameter("status", "ANULADO");

                return query.getResultList();

            } catch (Exception ex) {
                ex.printStackTrace();
                return new ArrayList<Acreenciamovimiento>();
            } finally {
                em.clear();
                em.close();
            }
        }

    }

    /**
     * Method findProcessToAnul.
     * 
     * @param idNumber String
     * @param transaccion long
     * @return Collection<CreditsMovement400>
     */
    public Collection<CreditsMovement400> findProcessToAnul(String idNumber, long transaccion) {

        Collection<CreditsMovement400> movement = new ArrayList<CreditsMovement400>();
        EntityManager em = emf.createEntityManager();
        Vector<Acreenciamovimiento> result = null;
        try {

            Query query = em
                    .createQuery("SELECT a FROM Acreenciamovimiento a WHERE a.numeroIdentificacionCliente = :numeroIdentificacionCliente and a.transaccion = :transaccion");
            query.setParameter("numeroIdentificacionCliente", idNumber);
            query.setParameter("transaccion", transaccion);
            result = (Vector<Acreenciamovimiento>) query.getResultList();

            if (result.size() > 0) {
                for (Acreenciamovimiento acreenciamovimiento : result) {
                    movement.add(fromJpa(acreenciamovimiento));
                }
            }
        } catch (Exception ex) {
            System.out.println("Error buscando cliente " + idNumber);
            ex.printStackTrace();
            result = null;
        } finally {
            em.close();
        }
        return movement;
    }

    /**
     * Method fromJpa.
     * 
     * @param acreenciaMovimiento Acreenciamovimiento
     * @return CreditsMovement400
     */
    private CreditsMovement400 fromJpa(Acreenciamovimiento acreenciaMovimiento) {

        CreditsMovement400 result = new CreditsMovement400();

        result.setIdAcreencia(acreenciaMovimiento.getIdAcreencia());
        result.setOperacion(acreenciaMovimiento.getOperacion());
        result.setIdFormadePago(acreenciaMovimiento.getIdFormadepago().getId());
        result.setIdMoneda(acreenciaMovimiento.getIdMoneda().getId());
        result.setMontoMoneda(new CRBigDecimal(acreenciaMovimiento.getMontoMoneda().doubleValue()));
        result.setMontoMonedaLocal(new CRBigDecimal(acreenciaMovimiento.getMontoMonedaLocal().doubleValue()));

        return result;
    }

    /**
     * Method create.
     * 
     * @param creditsMovement CreditsMovement400
     * @return long
     * @throws JpaException
     */
    public long create(CreditsMovement400 creditsMovement) throws JpaException {

        Acreenciamovimiento objeto = toJpa(creditsMovement, true);

        EntityManager em = null;
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new JpaException(e);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
        return 0;
    }

    /**
     * Method merge.
     * 
     * @param creditsMovement CreditsMovement400
     * @return long
     * @throws JpaException
     */
    public long merge(CreditsMovement400 creditsMovement) throws JpaException {

        Acreenciamovimiento objeto = toJpa(creditsMovement, true);

        EntityManager em = null;
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new JpaException(e);
        } finally {
            if (em != null) {
                em.clear();
                em.close();
            }
        }
        return 0;
    }

}
