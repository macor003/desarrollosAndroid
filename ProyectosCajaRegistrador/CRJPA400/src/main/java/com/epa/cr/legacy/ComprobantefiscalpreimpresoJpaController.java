/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.PrePrintedFiscalReceipt;

import crjpa400.Comprobantefiscalpreimpreso;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class ComprobantefiscalpreimpresoJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private EntityManagerFactory emf = null;

    /**
     * Field entityName.
     */
    private static String entityName = "Comprobantefiscalpreimpreso";

    /**
     * Constructor for ComprobantefiscalpreimpresoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public ComprobantefiscalpreimpresoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.emf = emf;
    }

    public PrePrintedFiscalReceipt fromJPA(Comprobantefiscalpreimpreso serverResolution) {
        PrePrintedFiscalReceipt resolution = new PrePrintedFiscalReceipt(serverResolution.getId());
        TipodocumentoJpaController documentController = new TipodocumentoJpaController(emf);
        resolution.setSerie(serverResolution.getSerie());
        resolution.setIdTipodocumento(documentController.fromJPA(serverResolution.getIdTipodocumento()));
        resolution.setTipoDocumento(serverResolution.getTipoDocumento());
        resolution.setInicioSerie(java.math.BigInteger.valueOf(serverResolution.getInicioSerie()));
        resolution.setFinalSerie(java.math.BigInteger.valueOf(serverResolution.getFinalSerie()));
        resolution.setEstado(serverResolution.getEstado());
        resolution.setObservacion(serverResolution.getObservacion());
        resolution.setResolucion(serverResolution.getResolucion());
        resolution.setFechaVencimiento(serverResolution.getFechaVencimiento());
        resolution.setFechaIngreso(serverResolution.getFechaIngreso());
        resolution.setFechaAutorizacion(serverResolution.getFechaAutorizacion());
        resolution.setPorcenateConsumido(serverResolution.getPorcentajeAlertaConsumo());
        BigInteger numCompactual = serverResolution.getNumCompactual();
        if (numCompactual == null) {
            numCompactual = BigInteger.ZERO;
        }
        resolution.setComprobanteActual(numCompactual);
        return resolution;
    }

    /**
     * <p>
     * 
     * @param idTipoDocumento
     * @param idCaja
     * @param fechaActual
     * @return
     */
    public List<PrePrintedFiscalReceipt> getNext(Long idTipoDocumento, Long idCaja) {
        List<PrePrintedFiscalReceipt> resolutions = new ArrayList<PrePrintedFiscalReceipt>();
        try {
            String jpql = new StringBuilder("SELECT r FROM Comprobantefiscalpreimpreso r ")
                    .append("WHERE r.idTipodocumento.id = :idTipoDocumento AND r.idCaja.id = :idCaja AND ")
                    .append("(:actualDate BETWEEN r.fechaAutorizacion AND r.fechaVencimiento) ")
                    .append("AND (r.estado = :statusT OR r.estado = :statusA) ")
                    .append("AND r.porcentajeAlertaConsumo = 0 ORDER BY r.estado ASC, r.fechaVencimiento ASC")
                    .toString();
            EntityManager manager = emf.createEntityManager();
            TypedQuery<Comprobantefiscalpreimpreso> query = manager.createQuery(jpql,
                                                                                Comprobantefiscalpreimpreso.class);

            query.setParameter("idTipoDocumento", idTipoDocumento);
            query.setParameter("idCaja", idCaja);
            query.setParameter("actualDate", new Date());
            query.setParameter("statusT", 'T');
            query.setParameter("statusA", 'A');
            List<Comprobantefiscalpreimpreso> jpaResolutions = query.getResultList();
            System.out.println("[INFO] se han encontrado " + jpaResolutions.size() + " resoluciones");
            for (Comprobantefiscalpreimpreso jpaResolution : jpaResolutions) {
                System.out.println(fromJPA(jpaResolution));
                resolutions.add(fromJPA(jpaResolution));
            }
            return resolutions;
        } catch (Exception e) {
            System.err.println("ComprobanteFiscalPreImpresoJpaController.getNext - [ERROR] ");
            System.err.println(e);
        }
        return resolutions;
    }
}
