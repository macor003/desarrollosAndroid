/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eve0017909
 */
@Entity
@Table(name = "PUNTOAGILOPERACION")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Puntoagiloperacion.findAll",
                           query = "SELECT p FROM Puntoagiloperacion p"),
        @NamedQuery(name = "Puntoagiloperacion.findByNumtienda",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.puntoagiloperacionPK.numtienda = :numtienda"),
        @NamedQuery(name = "Puntoagiloperacion.findByNumcaja",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.puntoagiloperacionPK.numcaja = :numcaja"),
        @NamedQuery(name = "Puntoagiloperacion.findByVtid",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.puntoagiloperacionPK.vtid = :vtid"),
        @NamedQuery(name = "Puntoagiloperacion.findByNumseq",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.puntoagiloperacionPK.numseq = :numseq"),
        @NamedQuery(name = "Puntoagiloperacion.findByTipoproceso",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.tipoproceso = :tipoproceso"),
        @NamedQuery(name = "Puntoagiloperacion.findByCodformadepago",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.codformadepago = :codformadepago"),
        @NamedQuery(name = "Puntoagiloperacion.findByNumproceso",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.numproceso = :numproceso"),
        @NamedQuery(name = "Puntoagiloperacion.findByNumservicio",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.numservicio = :numservicio"),
        @NamedQuery(name = "Puntoagiloperacion.findByCorrelativopagoproceso",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.correlativopagoproceso = :correlativopagoproceso"),
        @NamedQuery(name = "Puntoagiloperacion.findByCodcajero",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.codcajero = :codcajero"),
        @NamedQuery(name = "Puntoagiloperacion.findByFecha",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.fecha = :fecha"),
        @NamedQuery(name = "Puntoagiloperacion.findByHorainicia",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.horainicia = :horainicia"),
        @NamedQuery(name = "Puntoagiloperacion.findByHorafinaliza",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.horafinaliza = :horafinaliza"),
        @NamedQuery(name = "Puntoagiloperacion.findByDeCedulacliente",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.deCedulacliente = :deCedulacliente"),
        @NamedQuery(name = "Puntoagiloperacion.findByDeMonto",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.deMonto = :deMonto"),
        @NamedQuery(name = "Puntoagiloperacion.findByDeTipocuenta",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.deTipocuenta = :deTipocuenta"),
        @NamedQuery(name = "Puntoagiloperacion.findByDeCtaespecial",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.deCtaespecial = :deCtaespecial"),
        @NamedQuery(name = "Puntoagiloperacion.findByDeNrocuenta",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.deNrocuenta = :deNrocuenta"),
        @NamedQuery(name = "Puntoagiloperacion.findByDeNrocheque",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.deNrocheque = :deNrocheque"),
        @NamedQuery(name = "Puntoagiloperacion.findByDeNumseqanular",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.deNumseqanular = :deNumseqanular"),
        @NamedQuery(name = "Puntoagiloperacion.findByDePlancreditoepa",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.dePlancreditoepa = :dePlancreditoepa"),
        @NamedQuery(name = "Puntoagiloperacion.findByDePorcentajeprovimillas",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.dePorcentajeprovimillas = :dePorcentajeprovimillas"),
        @NamedQuery(name = "Puntoagiloperacion.findByDtNumerotarjeta",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.dtNumerotarjeta = :dtNumerotarjeta"),
        @NamedQuery(name = "Puntoagiloperacion.findByDtNombrecliente",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.dtNombrecliente = :dtNombrecliente"),
        @NamedQuery(name = "Puntoagiloperacion.findByDtTipotarjeta",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.dtTipotarjeta = :dtTipotarjeta"),
        @NamedQuery(name = "Puntoagiloperacion.findByDoCodrespuesta",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.doCodrespuesta = :doCodrespuesta"),
        @NamedQuery(name = "Puntoagiloperacion.findByDoMensajerespuesta",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.doMensajerespuesta = :doMensajerespuesta"),
        @NamedQuery(name = "Puntoagiloperacion.findByDoMensajeerror",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.doMensajeerror = :doMensajeerror"),
        @NamedQuery(name = "Puntoagiloperacion.findByDoNombreautorizador",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.doNombreautorizador = :doNombreautorizador"),
        @NamedQuery(name = "Puntoagiloperacion.findByDoNumeroautorizacion",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.doNumeroautorizacion = :doNumeroautorizacion"),
        @NamedQuery(name = "Puntoagiloperacion.findByDoNombrevoucher",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.doNombrevoucher = :doNombrevoucher"),
        @NamedQuery(name = "Puntoagiloperacion.findByStatus",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.status = :status"),
        @NamedQuery(name = "Puntoagiloperacion.findByTipooperacion",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.tipooperacion = :tipooperacion"),
        @NamedQuery(name = "Puntoagiloperacion.findByRegactualizado",
                    query = "SELECT p FROM Puntoagiloperacion p WHERE p.regactualizado = :regactualizado")})
public class Puntoagiloperacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected PuntoagiloperacionPK puntoagiloperacionPK;

    @Basic(optional = false)
    @Column(name = "TIPOPROCESO")
    private short tipoproceso;

    @Column(name = "CODFORMADEPAGO")
    private String codformadepago;

    @Column(name = "NUMPROCESO")
    private Integer numproceso;

    @Column(name = "NUMSERVICIO")
    private Integer numservicio;

    @Column(name = "CORRELATIVOPAGOPROCESO")
    private Short correlativopagoproceso;

    @Basic(optional = false)
    @Column(name = "CODCAJERO")
    private String codcajero;

    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Basic(optional = false)
    @Column(name = "HORAINICIA")
    @Temporal(TemporalType.TIME)
    private Date horainicia;

    @Basic(optional = false)
    @Column(name = "HORAFINALIZA")
    @Temporal(TemporalType.TIME)
    private Date horafinaliza;

    @Column(name = "DE_CEDULACLIENTE")
    private String deCedulacliente;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Column(name = "DE_MONTO")
    private BigDecimal deMonto;

    @Column(name = "DE_TIPOCUENTA")
    private String deTipocuenta;

    @Column(name = "DE_CTAESPECIAL")
    private String deCtaespecial;

    @Column(name = "DE_NROCUENTA")
    private BigInteger deNrocuenta;

    @Column(name = "DE_NROCHEQUE")
    private Long deNrocheque;

    @Column(name = "DE_NUMSEQANULAR")
    private Integer deNumseqanular;

    @Column(name = "DE_PLANCREDITOEPA")
    private String dePlancreditoepa;

    @Column(name = "DE_PORCENTAJEPROVIMILLAS")
    private Short dePorcentajeprovimillas;

    @Column(name = "DT_NUMEROTARJETA")
    private String dtNumerotarjeta;

    @Column(name = "DT_NOMBRECLIENTE")
    private String dtNombrecliente;

    @Column(name = "DT_TIPOTARJETA")
    private Character dtTipotarjeta;

    @Column(name = "DO_CODRESPUESTA")
    private String doCodrespuesta;

    @Column(name = "DO_MENSAJERESPUESTA")
    private String doMensajerespuesta;

    @Column(name = "DO_MENSAJEERROR")
    private String doMensajeerror;

    @Column(name = "DO_NOMBREAUTORIZADOR")
    private String doNombreautorizador;

    @Column(name = "DO_NUMEROAUTORIZACION")
    private String doNumeroautorizacion;

    @Column(name = "DO_NOMBREVOUCHER")
    private String doNombrevoucher;

    @Basic(optional = false)
    @Column(name = "STATUS")
    private Character status;

    @Basic(optional = false)
    @Column(name = "TIPOOPERACION")
    private short tipooperacion;

    @Basic(optional = false)
    @Column(name = "REGACTUALIZADO")
    private Character regactualizado;

    public Puntoagiloperacion() {
    }

    public Puntoagiloperacion(PuntoagiloperacionPK puntoagiloperacionPK) {
        this.puntoagiloperacionPK = puntoagiloperacionPK;
    }

    public Puntoagiloperacion(PuntoagiloperacionPK puntoagiloperacionPK, short tipoproceso, String codcajero,
                              Date fecha, Date horainicia, Date horafinaliza, Character status,
                              short tipooperacion, Character regactualizado) {
        this.puntoagiloperacionPK = puntoagiloperacionPK;
        this.tipoproceso = tipoproceso;
        this.codcajero = codcajero;
        this.fecha = fecha;
        this.horainicia = horainicia;
        this.horafinaliza = horafinaliza;
        this.status = status;
        this.tipooperacion = tipooperacion;
        this.regactualizado = regactualizado;
    }

    public Puntoagiloperacion(short numtienda, short numcaja, String vtid, int numseq) {
        this.puntoagiloperacionPK = new PuntoagiloperacionPK(numtienda, numcaja, vtid, numseq);
    }

    public PuntoagiloperacionPK getPuntoagiloperacionPK() {
        return puntoagiloperacionPK;
    }

    public void setPuntoagiloperacionPK(PuntoagiloperacionPK puntoagiloperacionPK) {
        this.puntoagiloperacionPK = puntoagiloperacionPK;
    }

    public short getTipoproceso() {
        return tipoproceso;
    }

    public void setTipoproceso(short tipoproceso) {
        this.tipoproceso = tipoproceso;
    }

    public String getCodformadepago() {
        return codformadepago;
    }

    public void setCodformadepago(String codformadepago) {
        this.codformadepago = codformadepago;
    }

    public Integer getNumproceso() {
        return numproceso;
    }

    public void setNumproceso(Integer numproceso) {
        this.numproceso = numproceso;
    }

    public Integer getNumservicio() {
        return numservicio;
    }

    public void setNumservicio(Integer numservicio) {
        this.numservicio = numservicio;
    }

    public Short getCorrelativopagoproceso() {
        return correlativopagoproceso;
    }

    public void setCorrelativopagoproceso(Short correlativopagoproceso) {
        this.correlativopagoproceso = correlativopagoproceso;
    }

    public String getCodcajero() {
        return codcajero;
    }

    public void setCodcajero(String codcajero) {
        this.codcajero = codcajero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHorainicia() {
        return horainicia;
    }

    public void setHorainicia(Date horainicia) {
        this.horainicia = horainicia;
    }

    public Date getHorafinaliza() {
        return horafinaliza;
    }

    public void setHorafinaliza(Date horafinaliza) {
        this.horafinaliza = horafinaliza;
    }

    public String getDeCedulacliente() {
        return deCedulacliente;
    }

    public void setDeCedulacliente(String deCedulacliente) {
        this.deCedulacliente = deCedulacliente;
    }

    public BigDecimal getDeMonto() {
        return deMonto;
    }

    public void setDeMonto(BigDecimal deMonto) {
        this.deMonto = deMonto;
    }

    public String getDeTipocuenta() {
        return deTipocuenta;
    }

    public void setDeTipocuenta(String deTipocuenta) {
        this.deTipocuenta = deTipocuenta;
    }

    public String getDeCtaespecial() {
        return deCtaespecial;
    }

    public void setDeCtaespecial(String deCtaespecial) {
        this.deCtaespecial = deCtaespecial;
    }

    public BigInteger getDeNrocuenta() {
        return deNrocuenta;
    }

    public void setDeNrocuenta(BigInteger deNrocuenta) {
        this.deNrocuenta = deNrocuenta;
    }

    public Long getDeNrocheque() {
        return deNrocheque;
    }

    public void setDeNrocheque(Long deNrocheque) {
        this.deNrocheque = deNrocheque;
    }

    public Integer getDeNumseqanular() {
        return deNumseqanular;
    }

    public void setDeNumseqanular(Integer deNumseqanular) {
        this.deNumseqanular = deNumseqanular;
    }

    public String getDePlancreditoepa() {
        return dePlancreditoepa;
    }

    public void setDePlancreditoepa(String dePlancreditoepa) {
        this.dePlancreditoepa = dePlancreditoepa;
    }

    public Short getDePorcentajeprovimillas() {
        return dePorcentajeprovimillas;
    }

    public void setDePorcentajeprovimillas(Short dePorcentajeprovimillas) {
        this.dePorcentajeprovimillas = dePorcentajeprovimillas;
    }

    public String getDtNumerotarjeta() {
        return dtNumerotarjeta;
    }

    public void setDtNumerotarjeta(String dtNumerotarjeta) {
        this.dtNumerotarjeta = dtNumerotarjeta;
    }

    public String getDtNombrecliente() {
        return dtNombrecliente;
    }

    public void setDtNombrecliente(String dtNombrecliente) {
        this.dtNombrecliente = dtNombrecliente;
    }

    public Character getDtTipotarjeta() {
        return dtTipotarjeta;
    }

    public void setDtTipotarjeta(Character dtTipotarjeta) {
        this.dtTipotarjeta = dtTipotarjeta;
    }

    public String getDoCodrespuesta() {
        return doCodrespuesta;
    }

    public void setDoCodrespuesta(String doCodrespuesta) {
        this.doCodrespuesta = doCodrespuesta;
    }

    public String getDoMensajerespuesta() {
        return doMensajerespuesta;
    }

    public void setDoMensajerespuesta(String doMensajerespuesta) {
        this.doMensajerespuesta = doMensajerespuesta;
    }

    public String getDoMensajeerror() {
        return doMensajeerror;
    }

    public void setDoMensajeerror(String doMensajeerror) {
        this.doMensajeerror = doMensajeerror;
    }

    public String getDoNombreautorizador() {
        return doNombreautorizador;
    }

    public void setDoNombreautorizador(String doNombreautorizador) {
        this.doNombreautorizador = doNombreautorizador;
    }

    public String getDoNumeroautorizacion() {
        return doNumeroautorizacion;
    }

    public void setDoNumeroautorizacion(String doNumeroautorizacion) {
        this.doNumeroautorizacion = doNumeroautorizacion;
    }

    public String getDoNombrevoucher() {
        return doNombrevoucher;
    }

    public void setDoNombrevoucher(String doNombrevoucher) {
        this.doNombrevoucher = doNombrevoucher;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public short getTipooperacion() {
        return tipooperacion;
    }

    public void setTipooperacion(short tipooperacion) {
        this.tipooperacion = tipooperacion;
    }

    public Character getRegactualizado() {
        return regactualizado;
    }

    public void setRegactualizado(Character regactualizado) {
        this.regactualizado = regactualizado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (puntoagiloperacionPK != null ? puntoagiloperacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puntoagiloperacion)) {
            return false;
        }
        Puntoagiloperacion other = (Puntoagiloperacion) object;
        if ((this.puntoagiloperacionPK == null && other.puntoagiloperacionPK != null)
                || (this.puntoagiloperacionPK != null
                        && !this.puntoagiloperacionPK.equals(other.puntoagiloperacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Puntoagiloperacion[ puntoagiloperacionPK=" + puntoagiloperacionPK
                + " ]";
    }

}
