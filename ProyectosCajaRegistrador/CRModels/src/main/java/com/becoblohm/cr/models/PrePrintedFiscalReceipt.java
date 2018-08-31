/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PrePrintedFiscalReceipt implements Serializable {

    private static final long serialVersionUID = -6240269663265949410L;

    public static final char ACTIVE = 'A';

    public static final char TRANSITORY = 'T';

    public static final char CLOSED = 'C';

    private Long id;

    private String serie;

    private Character tipoDocumento;

    private BigInteger inicioSerie;

    private BigInteger finalSerie;

    private BigInteger comprobanteActual;

    private List<FiscalReceiptNotUsed> comprobantefiscalnoutilizadoList;

    private DocumentType idTipodocumento;

    private char estado;

    private String observacion;

    private String resolucion;

    private Date fechaAutorizacion;

    private Date fechaIngreso;

    private Date fechaVencimiento;

    private int porcenateConsumido;

    private boolean estaSincronizado;

    private PrePrintedFiscalReceipt nextResolutionToActive;

    public PrePrintedFiscalReceipt(Long id) {
        this.id = id;
    }

    public PrePrintedFiscalReceipt() {
        super();
    }

    /**
     * Method getId.
     * 
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Method setId.
     * 
     * @param id Long
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method getSerie.
     * 
     * @return String
     */
    public String getSerie() {
        return serie;
    }

    /**
     * Method setSerie.
     * 
     * @param serie String
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * Method getTipoDocumento.
     * 
     * @return Character
     */
    public Character getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Method setTipoDocumento.
     * 
     * @param tipoDocumento Character
     */
    public void setTipoDocumento(Character tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Method getInicioSerie.
     * 
     * @return BigInteger
     */
    public BigInteger getInicioSerie() {
        return inicioSerie;
    }

    /**
     * Method setInicioSerie.
     * 
     * @param inicioSerie BigInteger
     */
    public void setInicioSerie(BigInteger inicioSerie) {
        this.inicioSerie = inicioSerie;
    }

    /**
     * Method getFinalSerie.
     * 
     * @return BigInteger
     */
    public BigInteger getFinalSerie() {
        return finalSerie;
    }

    /**
     * Method setFinalSerie.
     * 
     * @param finalSerie BigInteger
     */
    public void setFinalSerie(BigInteger finalSerie) {
        this.finalSerie = finalSerie;
    }

    /**
     * Method getComprobantefiscalnoutilizadoList.
     * 
     * @return List<FiscalReceiptNotUsed>
     */
    public List<FiscalReceiptNotUsed> getComprobantefiscalnoutilizadoList() {
        return comprobantefiscalnoutilizadoList;
    }

    /**
     * Method setComprobantefiscalnoutilizadoList.
     * 
     * @param comprobantefiscalnoutilizadoList List<FiscalReceiptNotUsed>
     */
    public void setComprobantefiscalnoutilizadoList(List<FiscalReceiptNotUsed> comprobantefiscalnoutilizadoList) {
        this.comprobantefiscalnoutilizadoList = comprobantefiscalnoutilizadoList;
    }

    /**
     * Method getIdTipodocumento.
     * 
     * @return DocumentType
     */
    public DocumentType getIdTipodocumento() {
        return idTipodocumento;
    }

    /**
     * Method setIdTipodocumento.
     * 
     * @param idTipodocumento DocumentType
     */
    public void setIdTipodocumento(DocumentType idTipodocumento) {
        this.idTipodocumento = idTipodocumento;
    }

    /**
     * @param comprobanteActual the comprobanteActual to set
     */
    public void setComprobanteActual(BigInteger comprobanteActual) {
        this.comprobanteActual = comprobanteActual;
    }

    /**
     * 
     * @return the comprobanteActual
     */
    public BigInteger getComprobanteActual() {
        return comprobanteActual;
    }

    /**
     * <p>
     * Metodo que indica si la resolucion actual cumple con las condiciones necesarias
     * para activarse
     * 
     * @return <b>Verdadero</b> si la resolucion cumple las condiciones para ser activada,
     *         <b>Falso</b> en caso contrario
     */
    public boolean canBeActivated() {
        return isTransitory() && isInRange() && isInDateRange();
    }

    /**
     * <p>
     * Método que indica si el comprobante fiscal pre-impreso se encuentra activo.
     * </p>
     * 
     * @return <code>true</code> si la resolución se encuentra activa, <code>false</code>
     *         en caso contrario.
     */
    public boolean isActive() {
        return getEstado() == ACTIVE;
    }

    /**
     * <p>
     * Metodo que indica si el comprobante fiscal pre-impreso se encuentra cerrado.
     * </p>
     * 
     * @return <code>true</code> si la resolución se encuentra cerrada, <code>false</code>
     *         en caso contrario.
     */
    public boolean isClosed() {
        return getEstado() == CLOSED;
    }

    /**
     * <p>
     * Metodo que indica si el comprobante fiscal pre-impreso se encuentra en estado
     * transitorio.
     * </p>
     * 
     * @return <code>true</code> si la resolución se encuentra en estado transitorio,
     *         <code>false</code> en caso contrario.
     */
    public boolean isTransitory() {
        return getEstado() == TRANSITORY;
    }

    /**
     * <p>
     * Metodo que evalua si la resolución se encuentra en el rango de fecha valido.
     * 
     * @return <b>Verdadero</b> si la fecha actual (el día actual) esta en el rango de
     *         validez de la resolució, <b>Falso</b> en caso contrario.
     */
    public boolean isInDateRange() {
        return isInDateRange(new Date());
    }

    /**
     * <p>
     * Metodo que evalua si la resolución se encuentra en el rango de fecha valido.
     * </p>
     * 
     * @param date la fecha a evaluar.
     * @return <code>true</code> si la fecha del argumento ({@code date}) esta en el rango
     *         de validez de la resolució, <code>false</code> en caso contrario.
     */
    public boolean isInDateRange(Date date) {
        return date.compareTo(getFechaAutorizacion()) >= 0 && date.compareTo(getFechaVencimiento()) <= 0;
    }

    /**
     * <p>
     * Metodo que se encarga de verificar si el numero de comprobante actual de la
     * resolucion se encuentra en el rango
     * 
     * @return {@code true} si el comprobante actual se encuentra en el rango,
     *         {@code false} en caso contrario.
     */
    public boolean isInRange() {
        if (isClosed()) {
            return false;
        }
        return isInRange(BigInteger.ONE);
    }

    /**
     * <p>
     * metodo que se encarga de verificar si el numero de comprobante actual de la
     * resolucion es valida para procesar el argumento transaccion y sus distintas
     * condiciones de entrega
     * 
     * @param transaction
     * @return
     */
    public boolean isInRange(Transaction transaction) {
        if (!isInRange()) {
            return false;
        }

        Map<Integer, DeliveryCondition> deliveryConditions = transaction.getDeliveryInfo();
        if (deliveryConditions == null) {
            return false;
        }

        BigInteger salesQty = BigInteger.valueOf(deliveryConditions.size());
        if (isInRange(salesQty)) {
            return true;
        }

        PrePrintedFiscalReceipt nextResolution = getNextResolutionToActive();
        if (nextResolution == null) {
            return false;
        }

        BigInteger newSalesQty = salesQty.subtract(getQtyAvailable());
        return nextResolution.isInRange(newSalesQty);
    }

    /**
     * <p>
     * Metodo que se encarga de verificar que el argument ({@code BigInteger number}) se
     * encuentre en el rango de la resolucion
     * 
     * @param number el numero a evaluar
     * @return {@code true} si numero ({@code BigInteger}) se encuentra en el rango,
     *         {@code false} en caso contrario.
     */
    public boolean isInRange(BigInteger number) {
        if (number == null || number.compareTo(BigInteger.ZERO) < 0 || isClosed()) {
            return false;
        }
        return getQtyAvailable().compareTo(number) >= 0;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getPorcenateConsumido() {
        return porcenateConsumido;
    }

    public void setPorcenateConsumido(int porcenateAlertaConsumo) {
        this.porcenateConsumido = porcenateAlertaConsumo;
    }

    public boolean isEstaSincronizado() {
        return estaSincronizado;
    }

    public void setEstaSincronizado(boolean estaSincronizado) {
        this.estaSincronizado = estaSincronizado;
    }

    public PrePrintedFiscalReceipt getNextResolutionToActive() {
        return nextResolutionToActive;
    }

    public void setNextResolutionToActive(PrePrintedFiscalReceipt nextResolutionToActive) {
        this.nextResolutionToActive = nextResolutionToActive;
    }

    /**
     * <p>
     * Metodo que permite obtener la cantidad disponible de la resolución (Comprobante
     * Actual - Final de la serie), si la resolucion es transitiva
     * 
     * @return la disponibilidad de la resolucion.
     */
    public BigInteger getQtyAvailable() {
        if (isClosed()) {
            return BigInteger.ZERO;
        }

        BigInteger zero = BigInteger.ZERO;
        BigInteger actual = isTransitory() ? zero : isActive() ? getComprobanteActual() : getFinalSerie();
        return getFinalSerie().subtract(actual);
    }

    @Override
    public String toString() {
        return new StringBuilder("Resolution [").append(getResolucion()).append("], Tipo de Documento [")
                .append(getTipoDocumento()).append("], Comprobante Actual [").append(getComprobanteActual())
                .append("], Porcentaje Consumido [").append(getPorcenateConsumido()).append("], Estado[")
                .append(getEstado()).append("], Fecha Autorizacion [").append(getFechaAutorizacion())
                .append("], Fecha Vencimiento [").append(getFechaVencimiento()).append("]").toString();
    }

}
