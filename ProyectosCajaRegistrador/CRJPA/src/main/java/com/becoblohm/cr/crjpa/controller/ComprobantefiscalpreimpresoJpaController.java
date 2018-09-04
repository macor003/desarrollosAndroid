/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.FiscalReceiptNotUsed;
import com.becoblohm.cr.models.PrePrintedFiscalReceipt;

import crjpa.Comprobantefiscalpreimpreso;

public class ComprobantefiscalpreimpresoJpaController extends AbstractJPAController {

    private static final long serialVersionUID = 1L;

    crjpa.ComprobantefiscalpreimpresoJpaController jpacontroller;

    private static String entityName = "Comprobantefiscalpreimpreso";

    EntityManagerFactory emf;

    public ComprobantefiscalpreimpresoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        jpacontroller = new crjpa.ComprobantefiscalpreimpresoJpaController(emf);
        this.emf = emf;
    }

    // TODO ¿deberia manejar arrojar una excepcion?

    /**
     * <p>
     * Metodo que se encarga de activar una resolcion.
     * </p>
     * 
     * @return <b>Verdadero</b> si el comprobante fiscal pre-impreso pudo ser impreso,
     *         <b>Falso</b> en caso contrario que el comprobante no cumpla con las
     *         condiciones para ser habilitado.
     */
    public boolean activateIt(PrePrintedFiscalReceipt resolution) {
        if (!resolution.canBeActivated()) {
            return false;
        }

        resolution.setEstado(PrePrintedFiscalReceipt.ACTIVE);
        resolution.setComprobanteActual(resolution.getInicioSerie());
        try {
            edit(resolution);
        } catch (JpaException e1) {
            e1.printStackTrace();
            return false;
        }
        return true;
    }

    public int getPrePrintedNumberPart(String serial) {
        char[] serialChars = serial.toCharArray();
        String serialPart = "";
        String numberPart = "";
        for (int i = serialChars.length - 1; i >= 0; i--) {
            if (!Character.isDigit(serialChars[i])) {
                serialPart = serial.substring(0, i + 1);
                numberPart = serial.substring(i + 1);
                break;
            }
        }
        return Integer.valueOf(numberPart);
    }

    public String getPrePrintedSerialPart(String serial) {
        char[] serialChars = serial.toCharArray();
        String serialPart = "";
        String numberPart = "";
        for (int i = serialChars.length - 1; i >= 0; i--) {
            if (!Character.isDigit(serialChars[i])) {
                serialPart = serial.substring(0, i + 1);
                numberPart = serial.substring(i + 1);
                break;
            }
        }
        return serialPart;
    }

    public PrePrintedFiscalReceipt findSerialEntities(String serial) {

        String serialPart = getPrePrintedSerialPart(serial);
        String numberPart = String.valueOf(getPrePrintedNumberPart(serial));

        System.out.println("serial " + serialPart);
        System.out.println("valor " + numberPart);
        EntityManager em = jpacontroller.getEntityManager();
        try {
            Query query = em
                    .createQuery("SELECT c FROM Comprobantefiscalpreimpreso c WHERE ((CONCAT(c.serie,c.tipoDocumento) = :serial) AND (:valor BETWEEN c.inicioSerie AND c.finalSerie))");
            query.setParameter("serial", serialPart);
            query.setParameter("valor", Long.valueOf(numberPart));
            query.setMaxResults(1);
            Comprobantefiscalpreimpreso list = (Comprobantefiscalpreimpreso) query.getSingleResult();

            PrePrintedFiscalReceipt result = fromJPA(list);

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public PrePrintedFiscalReceipt findSerialEntitiesByDocumentType(long tipodoc) {

        EntityManager em = jpacontroller.getEntityManager();
        try {
            Query query = em
                    .createQuery("SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.idTipodocumento.id = :idDocumentType ");
            query.setParameter("idDocumentType", tipodoc);

            query.setMaxResults(1);
            Comprobantefiscalpreimpreso list = (Comprobantefiscalpreimpreso) query.getSingleResult();
            PrePrintedFiscalReceipt result = fromJPA(list);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public PrePrintedFiscalReceipt fromJPA(Comprobantefiscalpreimpreso list) {
        PrePrintedFiscalReceipt res = new PrePrintedFiscalReceipt();
        TipodocumentoJpaController jpa = new TipodocumentoJpaController(emf);
        res.setFinalSerie(BigInteger.valueOf(list.getFinalSerie()));
        res.setId(list.getId());
        res.setIdTipodocumento(jpa.fromJPA(list.getIdTipodocumento()));
        res.setInicioSerie(BigInteger.valueOf(list.getInicioSerie()));
        res.setFinalSerie(BigInteger.valueOf(list.getFinalSerie()));
        res.setSerie(list.getSerie());
        res.setTipoDocumento(list.getTipoDocumento());
        res.setComprobanteActual(list.getNumCompactual());
        res.setEstado(list.getEstado());
        res.setObservacion(list.getObservacion());
        res.setResolucion(list.getResolucion());
        res.setFechaAutorizacion(list.getFechaAutorizacion());
        res.setFechaIngreso(list.getFechaIngreso());
        res.setFechaVencimiento(list.getFechaVencimiento());
        res.setPorcenateConsumido(list.getPorcentajeAlertaConsumo());
        res.setEstaSincronizado(list.getEstasincronizado().equalsIgnoreCase("S"));

        return res;
    }

    public Comprobantefiscalpreimpreso toJPA(PrePrintedFiscalReceipt preimpreso) {
        Comprobantefiscalpreimpreso result = new Comprobantefiscalpreimpreso();
        TipodocumentoJpaController jpa = new TipodocumentoJpaController(emf);

        result.setId(preimpreso.getId());
        result.setIdTipodocumento(jpa.toJPA(preimpreso.getIdTipodocumento()));
        result.setSerie(preimpreso.getSerie());
        result.setTipoDocumento(preimpreso.getTipoDocumento());
        result.setInicioSerie(preimpreso.getInicioSerie().longValue());
        result.setFinalSerie(preimpreso.getFinalSerie().longValue());
        BigInteger comprobanteActual = preimpreso.getComprobanteActual();
        if (comprobanteActual == null) {
            comprobanteActual = BigInteger.ZERO;
        }
        result.setNumCompactual(comprobanteActual);
        result.setEstado(preimpreso.getEstado());
        result.setObservacion(preimpreso.getObservacion());
        result.setResolucion(preimpreso.getResolucion());
        result.setFechaIngreso(preimpreso.getFechaIngreso());
        result.setFechaAutorizacion(preimpreso.getFechaAutorizacion());
        result.setFechaVencimiento(preimpreso.getFechaVencimiento());
        result.setPorcentajeAlertaConsumo(preimpreso.getPorcenateConsumido());
        result.setEstasincronizado("N");

        return result;
    }

    public long validateSerial(String serial) {
        long isNext = -1;
        PrePrintedFiscalReceipt prePrintedReceipt = this.findSerialEntities(serial);
        if (prePrintedReceipt != null) {
            TransaccionDocumentoJpaController docController = new TransaccionDocumentoJpaController(this.emf);
            isNext = docController.isNextSerial(prePrintedReceipt, serial);
            // isNext = 1;
        }
        return isNext;
    }

    public boolean invalidateNonUsedReceipts(String serial, long isNext) throws JpaException {
        boolean isInvalidated = false;
        PrePrintedFiscalReceipt prePrintedReceipt = this.findSerialEntities(serial);
        ComprobantefiscalnoutilizadoJpaController nonUsedController = new ComprobantefiscalnoutilizadoJpaController(
                this.emf);
        long numberPart = getPrePrintedNumberPart(serial);
        FiscalReceiptNotUsed nonUsedSerialNumber;
        long i;
        for (i = isNext - 1; i > 0; i--) {
            nonUsedSerialNumber = new FiscalReceiptNotUsed();
            nonUsedSerialNumber.setIdComprobantefiscalpreimpreso(prePrintedReceipt);
            BigInteger valueOf = BigInteger.valueOf(numberPart - i);
            System.out.println("invalidando comprobante " + valueOf + " del tipo "
                    + prePrintedReceipt.getIdTipodocumento().getIdTipoDoc());
            nonUsedSerialNumber.setNumeroComprobante(valueOf);
            nonUsedController.create(nonUsedSerialNumber);
        }
        return i == 1;
    }

    public void updateCurrentReceipt(long idTipoDoc, int fiscalId) throws JpaException {
        Comprobantefiscalpreimpreso tmp = toJPA(this.findSerialEntitiesByDocumentType(idTipoDoc));
        EntityManager em = emf.createEntityManager();
        try {
            tmp.setNumCompactual(new BigInteger(String.valueOf(fiscalId)));
            em.getTransaction().begin();
            em.merge(tmp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new JpaException();
        }

    }

    /**
     * <p>
     * Metodo que se encarga de obtener una lista de los comprobantes activos, esta lista
     * puede contener comprobantes vencidos.
     * </p>
     * 
     * @return
     */
    public ArrayList<PrePrintedFiscalReceipt> findAllActive() {
        EntityManager manager = jpacontroller.getEntityManager();
        ArrayList<PrePrintedFiscalReceipt> result = new ArrayList<PrePrintedFiscalReceipt>();
        try {
            String jpql = "SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.estado = :status";
            TypedQuery<Comprobantefiscalpreimpreso> query = manager.createQuery(jpql,
                                                                                Comprobantefiscalpreimpreso.class);
            query.setParameter("status", PrePrintedFiscalReceipt.ACTIVE);
            List<Comprobantefiscalpreimpreso> list = query.getResultList();
            for (Comprobantefiscalpreimpreso aux : list) {
                result.add(fromJPA(aux));
            }

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            manager.close();
        }
    }

    // TODO metodo candidato para manejar excepciones

    /**
     * <p>
     * Se encarga de validar y procesar las resoluciones que pueda requerir la impresora
     * al momento de imprimir.
     * </p>
     * 
     * @param resolution la resolucion a procesar
     * @return la resolucion necesaria para seguir el proceso de la caja, <b>Nulo</b> en
     *         caso de que no exista una resolucion valida.
     */
    public PrePrintedFiscalReceipt processIt(PrePrintedFiscalReceipt resolution) {
        if (resolution.isActive() && resolution.isInRange() && resolution.isInDateRange()) {
            resolution.setComprobanteActual(resolution.getComprobanteActual().add(BigInteger.ONE));
            return resolution;
        }

        if (resolution.isTransitory() && activateIt(resolution)) {
            return resolution;
        }

        if (resolution.isActive() && !resolution.isInDateRange()) {
            closeIt(resolution);
        }

        PrePrintedFiscalReceipt nextResolutionToActive = resolution.getNextResolutionToActive();
        if (resolution.isClosed() && nextResolutionToActive != null) {
            return processIt(nextResolutionToActive);
        }

        return null;
    }

    public void closeIt(PrePrintedFiscalReceipt resolution) {
        resolution.setEstado(PrePrintedFiscalReceipt.CLOSED);
        try {
            edit(resolution);
        } catch (JpaException e1) {
            e1.printStackTrace();
        }
    }

    public boolean updateDocNumber(long idTipoDoc, int fiscalId) throws JpaException {

        PrePrintedFiscalReceipt prePrintedFiscalReceipt = findByDocumentTypeOnlyActive(idTipoDoc);
        if (prePrintedFiscalReceipt != null) {

            prePrintedFiscalReceipt.setComprobanteActual(BigInteger.valueOf(fiscalId));

            BigInteger total = prePrintedFiscalReceipt.getFinalSerie()
                    .subtract(prePrintedFiscalReceipt.getInicioSerie()).add(BigInteger.ONE);
            BigInteger consumido = prePrintedFiscalReceipt.getComprobanteActual()
                    .subtract(prePrintedFiscalReceipt.getInicioSerie()).add(BigInteger.ONE);
            BigInteger cien = BigInteger.valueOf(100);
            BigInteger porcentajeConsumido = consumido.multiply(cien).divide(total);
            prePrintedFiscalReceipt.setPorcenateConsumido(porcentajeConsumido.intValue());

            if (prePrintedFiscalReceipt.getComprobanteActual().compareTo(prePrintedFiscalReceipt.getFinalSerie()) >= 0) {
                prePrintedFiscalReceipt.setEstado(PrePrintedFiscalReceipt.CLOSED);
            }

            edit(prePrintedFiscalReceipt);

            return true;
        } else {
            return false;
        }
    }

    public void edit(PrePrintedFiscalReceipt prePrintedFiscalReceipt) throws JpaException {
        Comprobantefiscalpreimpreso comprobanteFiscalPreimpreso = new Comprobantefiscalpreimpreso();
        comprobanteFiscalPreimpreso = toJPA(prePrintedFiscalReceipt);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(comprobanteFiscalPreimpreso);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new JpaException();
        } finally {
            if (em.isOpen()) {
                em.clear();
                em.close();
            }
        }
    }

    public PrePrintedFiscalReceipt findByDocumentTypeOnlyActive(long tipodoc) {
        EntityManager manager = jpacontroller.getEntityManager();
        try {
            String jpql = new StringBuilder("SELECT c FROM Comprobantefiscalpreimpreso c WHERE ")
                    .append("c.estado = :status AND c.idTipodocumento.id = :idDocumentType").toString();
            TypedQuery<Comprobantefiscalpreimpreso> query = manager.createQuery(jpql,
                                                                                Comprobantefiscalpreimpreso.class);

            query.setParameter("idDocumentType", tipodoc);
            query.setParameter("status", PrePrintedFiscalReceipt.ACTIVE);
            query.setMaxResults(1);
            Comprobantefiscalpreimpreso list = query.getSingleResult();
            return fromJPA(list);
        } catch (NoResultException e) {
            // Do nothing...
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            manager.close();
        }
    }

    public PrePrintedFiscalReceipt getNextToActivate(long tipodoc) {
        EntityManager manager = jpacontroller.getEntityManager();
        String sql = new StringBuilder("SELECT c FROM Comprobantefiscalpreimpreso c ")
                .append("WHERE c.estado = :status ")
                .append("AND (:currentDate BETWEEN c.fechaAutorizacion AND c.fechaVencimiento) ")
                .append("AND c.idTipodocumento.id = :idDocumentType order by c.fechaVencimiento asc").toString();
        try {
            TypedQuery<Comprobantefiscalpreimpreso> query = manager.createQuery(sql,
                                                                                Comprobantefiscalpreimpreso.class);
            query.setParameter("idDocumentType", tipodoc);
            query.setParameter("status", PrePrintedFiscalReceipt.TRANSITORY);
            query.setParameter("currentDate", new Date());
            query.setMaxResults(1);
            Comprobantefiscalpreimpreso resolution = query.getSingleResult();
            return fromJPA(resolution);
        } catch (NoResultException e) {
            // Do nothing...
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            manager.close();
        }
    }

    public ArrayList<PrePrintedFiscalReceipt> findDuplicatedActive() {
        EntityManager em = jpacontroller.getEntityManager();
        ArrayList<PrePrintedFiscalReceipt> result = new ArrayList<PrePrintedFiscalReceipt>();

        try {
            Query query = em
                    .createQuery("select c from Comprobantefiscalpreimpreso c where c.estado = 'A' group by c.idTipodocumento having count(c.idTipodocumento) > 1");

            List<Comprobantefiscalpreimpreso> list = (List<Comprobantefiscalpreimpreso>) query.getResultList();

            for (Comprobantefiscalpreimpreso aux : list) {
                result.add(fromJPA(aux));
            }

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public PrePrintedFiscalReceipt findById(Long id) {
        return fromJPA(jpacontroller.findComprobantefiscalpreimpreso(id));
    }

    /**
     * <p>
     * Metodo que se encarga de guardar un conjunto de comprobantes fiscales pre impresos.
     * </p>
     * 
     * @param resolutions los comprobantes a guardar.
     * @return <b>Verdadero</b> si la operacion ocurrio satisfactoriamente, <b>falso</b>
     *         en caso contrario.
     */
    public boolean saveAll(List<PrePrintedFiscalReceipt> resolutions) {
        boolean wasSuccessful = true;
        EntityManager manager = jpacontroller.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            for (PrePrintedFiscalReceipt resolution : resolutions) {
                manager.persist(toJPA(resolution));
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            wasSuccessful = false;
        } finally {
            if (manager.isOpen()) {
                manager.clear();
                manager.close();
            }
        }

        return wasSuccessful;
    }
}
