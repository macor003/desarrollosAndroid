/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.Date;

import com.becoblohm.cr.types.CRBigDecimal;

/**
 */
public class CreditsMovement400 {

    /**
     * Field idAcreenciaMovimientoSaldo.
     */
    private long idAcreenciaMovimientoSaldo;

    /**
     * Field idAcreenciaMovimientoFormaDePago.
     */
    private long idAcreenciaMovimientoFormaDePago;

    /**
     * Field idAcreencia.
     */
    private long idAcreencia;

    /**
     * Field idTipoAcreencia.
     */
    private long idTipoAcreencia;

    /**
     * Field idOperacionAcreencia.
     */
    private long idOperacionAcreencia;

    /**
     * Field idFormadePago.
     */
    private long idFormadePago;

    /**
     * Field idMoneda.
     */
    private long idMoneda;

    /**
     * Field sessionId.
     */
    private long sessionId;

    /**
     * Field documentoFormadePago.
     */
    private String documentoFormadePago;

    /**
     * Field fecha.
     */
    private Date fecha;

    /**
     * Field nombreUnidadNegocio.
     */
    private String nombreUnidadNegocio;

    /**
     * Field nombreUnidadOperativa.
     */
    private String nombreUnidadOperativa;

    /**
     * Field tienda.
     */
    private int tienda;

    /**
     * Field operacion.
     */
    private int operacion;

    /**
     * Field anulaOperacion.
     */
    private int anulaOperacion;

    /**
     * Field caja.
     */
    private int caja;

    /**
     * Field transaccion.
     */
    private long transaccion;

    /**
     * Field cajero.
     */
    private int cajero;

    /**
     * Field reciboDeCaja.
     */
    private int reciboDeCaja;

    /**
     * Field montoMonedaLocal.
     */
    private CRBigDecimal montoMonedaLocal;

    /**
     * Field montoMoneda.
     */
    private CRBigDecimal montoMoneda;

    /**
     * Field vuelto.
     */
    private CRBigDecimal vuelto;

    /**
     * Field estado.
     */
    private String estado;

    /**
     * Field controlReplicacion.
     */
    private char controlReplicacion;

    /**
     * Field numeroIdentificacionCliente.
     */
    private String numeroIdentificacionCliente;

    /**
     * Field correlativo.
     */
    private long correlativo;

    /**
     * Field start. Hold the starting timestamp of the request
     */
    private Long start;

    /**
     * Field timeout. Hold the response time available to the server
     */
    private Long timeout;

    /**
     * Field ipaid. Hold a unique ID for all attempts of processing the same movement
     */
    private Long ipaid;

    /**
     * Field ipaStatus. Hold the processing status
     */
    private char ipaStatus;

    /**
     * Method getIdAcreenciaMovimientoSaldo.
     * 
     * @return long
     */
    public long getIdAcreenciaMovimientoSaldo() {
        return idAcreenciaMovimientoSaldo;
    }

    /**
     * Method setIdAcreenciaMovimientoSaldo.
     * 
     * @param idAcreenciaMovimientoSaldo long
     */
    public void setIdAcreenciaMovimientoSaldo(long idAcreenciaMovimientoSaldo) {
        this.idAcreenciaMovimientoSaldo = idAcreenciaMovimientoSaldo;
    }

    /**
     * Method getIdAcreenciaMovimientoFormaDePago.
     * 
     * @return long
     */
    public long getIdAcreenciaMovimientoFormaDePago() {
        return idAcreenciaMovimientoFormaDePago;
    }

    /**
     * Method setIdAcreenciaMovimientoFormaDePago.
     * 
     * @param idAcreenciaMovimientoFormaDePago long
     */
    public void setIdAcreenciaMovimientoFormaDePago(long idAcreenciaMovimientoFormaDePago) {
        this.idAcreenciaMovimientoFormaDePago = idAcreenciaMovimientoFormaDePago;
    }

    /**
     * Method getIdAcreencia.
     * 
     * @return long
     */
    public long getIdAcreencia() {
        return idAcreencia;
    }

    /**
     * Method setIdAcreencia.
     * 
     * @param idAcreencia long
     */
    public void setIdAcreencia(long idAcreencia) {
        this.idAcreencia = idAcreencia;
    }

    /**
     * Method getIdTipoAcreencia.
     * 
     * @return long
     */
    public long getIdTipoAcreencia() {
        return idTipoAcreencia;
    }

    /**
     * Method setIdTipoAcreencia.
     * 
     * @param idTipoAcreencia long
     */
    public void setIdTipoAcreencia(long idTipoAcreencia) {
        this.idTipoAcreencia = idTipoAcreencia;
    }

    /**
     * Method getIdOperacionAcreencia.
     * 
     * @return long
     */
    public long getIdOperacionAcreencia() {
        return idOperacionAcreencia;
    }

    /**
     * Method setIdOperacionAcreencia.
     * 
     * @param idOperacionAcreencia long
     */
    public void setIdOperacionAcreencia(long idOperacionAcreencia) {
        this.idOperacionAcreencia = idOperacionAcreencia;
    }

    /**
     * Method getIdFormadePago.
     * 
     * @return long
     */
    public long getIdFormadePago() {
        return idFormadePago;
    }

    /**
     * Method setIdFormadePago.
     * 
     * @param idFormadePago long
     */
    public void setIdFormadePago(long idFormadePago) {
        this.idFormadePago = idFormadePago;
    }

    /**
     * Method getIdMoneda.
     * 
     * @return long
     */
    public long getIdMoneda() {
        return idMoneda;
    }

    /**
     * Method setIdMoneda.
     * 
     * @param idMoneda long
     */
    public void setIdMoneda(long idMoneda) {
        this.idMoneda = idMoneda;
    }

    /**
     * Method getDocumentoFormadePago.
     * 
     * @return String
     */
    public String getDocumentoFormadePago() {
        return documentoFormadePago;
    }

    /**
     * Method setDocumentoFormadePago.
     * 
     * @param documentoFormadePago String
     */
    public void setDocumentoFormadePago(String documentoFormadePago) {
        this.documentoFormadePago = documentoFormadePago;
    }

    /**
     * Method getFecha.
     * 
     * @return Date
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Method setFecha.
     * 
     * @param fecha Date
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Method getNombreUnidadNegocio.
     * 
     * @return String
     */
    public String getNombreUnidadNegocio() {
        return nombreUnidadNegocio;
    }

    /**
     * Method setNombreUnidadNegocio.
     * 
     * @param nombreUnidadNegocio String
     */
    public void setNombreUnidadNegocio(String nombreUnidadNegocio) {
        this.nombreUnidadNegocio = nombreUnidadNegocio;
    }

    /**
     * Method getNombreUnidadOperativa.
     * 
     * @return String
     */
    public String getNombreUnidadOperativa() {
        return nombreUnidadOperativa;
    }

    /**
     * Method setNombreUnidadOperativa.
     * 
     * @param nombreUnidadOperativa String
     */
    public void setNombreUnidadOperativa(String nombreUnidadOperativa) {
        this.nombreUnidadOperativa = nombreUnidadOperativa;
    }

    /**
     * Method getTienda.
     * 
     * @return int
     */
    public int getTienda() {
        return tienda;
    }

    /**
     * Method setTienda.
     * 
     * @param tienda int
     */
    public void setTienda(int tienda) {
        this.tienda = tienda;
    }

    /**
     * Method getOperacion.
     * 
     * @return int
     */
    public int getOperacion() {
        return operacion;
    }

    /**
     * Method setOperacion.
     * 
     * @param operacion int
     */
    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }

    /**
     * Method getAnulaOperacion.
     * 
     * @return int
     */
    public int getAnulaOperacion() {
        return anulaOperacion;
    }

    /**
     * Method setAnulaOperacion.
     * 
     * @param anulaOperacion int
     */
    public void setAnulaOperacion(int anulaOperacion) {
        this.anulaOperacion = anulaOperacion;
    }

    /**
     * Method getCaja.
     * 
     * @return int
     */
    public int getCaja() {
        return caja;
    }

    /**
     * Method setCaja.
     * 
     * @param caja int
     */
    public void setCaja(int caja) {
        this.caja = caja;
    }

    /**
     * Method getTransaccion.
     * 
     * @return long
     */
    public long getTransaccion() {
        return transaccion;
    }

    /**
     * Method setTransaccion.
     * 
     * @param transaccion long
     */
    public void setTransaccion(long transaccion) {
        this.transaccion = transaccion;
    }

    /**
     * Method getCajero.
     * 
     * @return int
     */
    public int getCajero() {
        return cajero;
    }

    /**
     * Method setCajero.
     * 
     * @param cajero int
     */
    public void setCajero(int cajero) {
        this.cajero = cajero;
    }

    /**
     * Method getReciboDeCaja.
     * 
     * @return int
     */
    public int getReciboDeCaja() {
        return reciboDeCaja;
    }

    /**
     * Method setReciboDeCaja.
     * 
     * @param reciboDeCaja int
     */
    public void setReciboDeCaja(int reciboDeCaja) {
        this.reciboDeCaja = reciboDeCaja;
    }

    /**
     * Method getMontoMonedaLocal.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getMontoMonedaLocal() {
        return montoMonedaLocal;
    }

    /**
     * Method setMontoMonedaLocal.
     * 
     * @param montoMonedaLocal CRBigDecimal
     */
    public void setMontoMonedaLocal(CRBigDecimal montoMonedaLocal) {
        this.montoMonedaLocal = montoMonedaLocal;
    }

    /**
     * Method getMontoMoneda.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getMontoMoneda() {
        return montoMoneda;
    }

    /**
     * Method setMontoMoneda.
     * 
     * @param montoMoneda CRBigDecimal
     */
    public void setMontoMoneda(CRBigDecimal montoMoneda) {
        this.montoMoneda = montoMoneda;
    }

    /**
     * Method getVuelto.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getVuelto() {
        return vuelto;
    }

    /**
     * Method setVuelto.
     * 
     * @param vuelto CRBigDecimal
     */
    public void setVuelto(CRBigDecimal vuelto) {
        this.vuelto = vuelto;
    }

    /**
     * Method getEstado.
     * 
     * @return String
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Method setEstado.
     * 
     * @param estado String
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Method getControlReplicacion.
     * 
     * @return char
     */
    public char getControlReplicacion() {
        return controlReplicacion;
    }

    /**
     * Method setControlReplicacion.
     * 
     * @param controlReplicacion char
     */
    public void setControlReplicacion(char controlReplicacion) {
        this.controlReplicacion = controlReplicacion;
    }

    /**
     * Method getNumeroIdentificacionCliente.
     * 
     * @return String
     */
    public String getNumeroIdentificacionCliente() {
        return numeroIdentificacionCliente;
    }

    /**
     * Method setNumeroIdentificacionCliente.
     * 
     * @param numeroIdentificacionCliente String
     */
    public void setNumeroIdentificacionCliente(String numeroIdentificacionCliente) {
        this.numeroIdentificacionCliente = numeroIdentificacionCliente;
    }

    /**
     * @param correlativo the correlativo to set
     */
    public void setCorrelativo(long correlativo) {
        this.correlativo = correlativo;
    }

    /**
     * 
     * @return the correlativo
     */
    public long getCorrelativo() {
        return correlativo;
    }

    /**
     * Method getSessionId.
     * 
     * @return long
     */
    public long getSessionId() {
        return sessionId;
    }

    /**
     * Method setSessionId.
     * 
     * @param sessionId long
     */
    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Getter of start.
     * 
     * @return start.
     */
    public Long getStart() {
        return start;
    }

    /**
     * Getter of timeout.
     * 
     * @return timeout
     */
    public Long getTimeout() {
        return timeout;
    }

    /**
     * Getter of ipaid.
     * 
     * @return ipaid
     */
    public Long getIpaid() {
        return ipaid;
    }

    /**
     * Getter of ipaStatus.
     * 
     * @return ipaStatus
     */
    public char getIpaStatus() {
        return ipaStatus;
    }

    /**
     * Setter of start.
     * 
     * @param start
     */
    public void setStart(Long start) {
        this.start = start;
    }

    /**
     * Setter of timeout.
     * 
     * @param timeout
     */
    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    /**
     * Setter of ipaid.
     * 
     * @param ipaid
     */
    public void setIpaid(Long ipaid) {
        this.ipaid = ipaid;
    }

    /**
     * Setter of ipaStatus.
     * 
     * @param ipaStatus
     */
    public void setIpaStatus(char ipaStatus) {
        this.ipaStatus = ipaStatus;
    }

}
