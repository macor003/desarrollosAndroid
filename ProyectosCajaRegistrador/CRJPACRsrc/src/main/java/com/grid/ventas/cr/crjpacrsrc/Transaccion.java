/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "TRANSACCION")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Transaccion.findAll",
                           query = "SELECT t FROM Transaccion t"),
        @NamedQuery(name = "Transaccion.findByNumtienda",
                    query = "SELECT t FROM Transaccion t WHERE t.transaccionPK.numtienda = :numtienda"),
        @NamedQuery(name = "Transaccion.findByFecha",
                    query = "SELECT t FROM Transaccion t WHERE t.transaccionPK.fecha = :fecha"),
        @NamedQuery(name = "Transaccion.findByNumcajainicia",
                    query = "SELECT t FROM Transaccion t WHERE t.transaccionPK.numcajainicia = :numcajainicia"),
        @NamedQuery(name = "Transaccion.findByNumregcajainicia",
                    query = "SELECT t FROM Transaccion t WHERE t.transaccionPK.numregcajainicia = :numregcajainicia"),
        @NamedQuery(name = "Transaccion.findByNumcajafinaliza",
                    query = "SELECT t FROM Transaccion t WHERE t.numcajafinaliza = :numcajafinaliza"),
        @NamedQuery(name = "Transaccion.findByNumtransaccion",
                    query = "SELECT t FROM Transaccion t WHERE t.numtransaccion = :numtransaccion"),
        @NamedQuery(name = "Transaccion.findByTipotransaccion",
                    query = "SELECT t FROM Transaccion t WHERE t.tipotransaccion = :tipotransaccion"),
        @NamedQuery(name = "Transaccion.findByNumcomprobantefiscal",
                    query = "SELECT t FROM Transaccion t WHERE t.numcomprobantefiscal = :numcomprobantefiscal"),
        @NamedQuery(name = "Transaccion.findByHorainicia",
                    query = "SELECT t FROM Transaccion t WHERE t.horainicia = :horainicia"),
        @NamedQuery(name = "Transaccion.findByHorafinaliza",
                    query = "SELECT t FROM Transaccion t WHERE t.horafinaliza = :horafinaliza"),
        @NamedQuery(name = "Transaccion.findByCodcliente",
                    query = "SELECT t FROM Transaccion t WHERE t.codcliente = :codcliente"),
        @NamedQuery(name = "Transaccion.findByCodcajero",
                    query = "SELECT t FROM Transaccion t WHERE t.codcajero = :codcajero"),
        @NamedQuery(name = "Transaccion.findByCodigofacturaespera",
                    query = "SELECT t FROM Transaccion t WHERE t.codigofacturaespera = :codigofacturaespera"),
        @NamedQuery(name = "Transaccion.findByMontobase",
                    query = "SELECT t FROM Transaccion t WHERE t.montobase = :montobase"),
        @NamedQuery(name = "Transaccion.findByMontoimpuesto",
                    query = "SELECT t FROM Transaccion t WHERE t.montoimpuesto = :montoimpuesto"),
        @NamedQuery(name = "Transaccion.findByVueltocliente",
                    query = "SELECT t FROM Transaccion t WHERE t.vueltocliente = :vueltocliente"),
        @NamedQuery(name = "Transaccion.findByMontoremanente",
                    query = "SELECT t FROM Transaccion t WHERE t.montoremanente = :montoremanente"),
        @NamedQuery(name = "Transaccion.findByLineasfacturacion",
                    query = "SELECT t FROM Transaccion t WHERE t.lineasfacturacion = :lineasfacturacion"),
        @NamedQuery(name = "Transaccion.findByCajaenlinea",
                    query = "SELECT t FROM Transaccion t WHERE t.cajaenlinea = :cajaenlinea"),
        @NamedQuery(name = "Transaccion.findBySerialcaja",
                    query = "SELECT t FROM Transaccion t WHERE t.serialcaja = :serialcaja"),
        @NamedQuery(name = "Transaccion.findByDuracionventa",
                    query = "SELECT t FROM Transaccion t WHERE t.duracionventa = :duracionventa"),
        @NamedQuery(name = "Transaccion.findByDuracionpago",
                    query = "SELECT t FROM Transaccion t WHERE t.duracionpago = :duracionpago"),
        @NamedQuery(name = "Transaccion.findByChecksum",
                    query = "SELECT t FROM Transaccion t WHERE t.checksum = :checksum"),
        @NamedQuery(name = "Transaccion.findByEstadotransaccion",
                    query = "SELECT t FROM Transaccion t WHERE t.estadotransaccion = :estadotransaccion"),
        @NamedQuery(name = "Transaccion.findByRegactualizado",
                    query = "SELECT t FROM Transaccion t WHERE t.regactualizado = :regactualizado"),
        @NamedQuery(name = "Transaccion.findByCodautorizante",
                    query = "SELECT t FROM Transaccion t WHERE t.codautorizante = :codautorizante"),
        @NamedQuery(name = "Transaccion.findByNumimpnotaentrega",
                    query = "SELECT t FROM Transaccion t WHERE t.numimpnotaentrega = :numimpnotaentrega"),
        @NamedQuery(name = "Transaccion.findByNumimpnotadespacho",
                    query = "SELECT t FROM Transaccion t WHERE t.numimpnotadespacho = :numimpnotadespacho"),
        @NamedQuery(name = "Transaccion.findByTipodocumento",
                    query = "SELECT t FROM Transaccion t WHERE t.tipodocumento = :tipodocumento"),
        @NamedQuery(name = "Transaccion.findByNombrecliente",
                    query = "SELECT t FROM Transaccion t WHERE t.nombrecliente = :nombrecliente"),
        @NamedQuery(name = "Transaccion.findBySerie",
                    query = "SELECT t FROM Transaccion t WHERE t.serie = :serie")})
public class Transaccion implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected TransaccionPK transaccionPK;

    @Column(name = "NUMCAJAFINALIZA")
    private Short numcajafinaliza;

    @Column(name = "NUMTRANSACCION")
    private Integer numtransaccion;

    @Basic(optional = false)
    @Column(name = "TIPOTRANSACCION")
    private Character tipotransaccion;

    @Column(name = "NUMCOMPROBANTEFISCAL")
    private Integer numcomprobantefiscal;

    @Basic(optional = false)
    @Column(name = "HORAINICIA")
    @Temporal(TemporalType.TIME)
    private Date horainicia;

    @Basic(optional = false)
    @Column(name = "HORAFINALIZA")
    @Temporal(TemporalType.TIME)
    private Date horafinaliza;

    @Column(name = "CODCLIENTE")
    private String codcliente;

    @Basic(optional = false)
    @Column(name = "CODCAJERO")
    private String codcajero;

    @Column(name = "CODIGOFACTURAESPERA")
    private String codigofacturaespera;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MONTOBASE")
    private BigDecimal montobase;

    @Basic(optional = false)
    @Column(name = "MONTOIMPUESTO")
    private BigDecimal montoimpuesto;

    @Basic(optional = false)
    @Column(name = "VUELTOCLIENTE")
    private BigDecimal vueltocliente;

    @Basic(optional = false)
    @Column(name = "MONTOREMANENTE")
    private BigDecimal montoremanente;

    @Basic(optional = false)
    @Column(name = "LINEASFACTURACION")
    private short lineasfacturacion;

    @Basic(optional = false)
    @Column(name = "CAJAENLINEA")
    private Character cajaenlinea;

    @Column(name = "SERIALCAJA")
    private String serialcaja;

    @Basic(optional = false)
    @Column(name = "DURACIONVENTA")
    private short duracionventa;

    @Basic(optional = false)
    @Column(name = "DURACIONPAGO")
    private short duracionpago;

    @Column(name = "CHECKSUM")
    private Integer checksum;

    @Basic(optional = false)
    @Column(name = "ESTADOTRANSACCION")
    private Character estadotransaccion;

    @Basic(optional = false)
    @Column(name = "REGACTUALIZADO")
    private Character regactualizado;

    @Column(name = "CODAUTORIZANTE")
    private String codautorizante;

    @Basic(optional = false)
    @Column(name = "NUMIMPNOTAENTREGA")
    private int numimpnotaentrega;

    @Basic(optional = false)
    @Column(name = "NUMIMPNOTADESPACHO")
    private int numimpnotadespacho;

    @Basic(optional = false)
    @Column(name = "TIPODOCUMENTO")
    private String tipodocumento;

    @Basic(optional = false)
    @Column(name = "NOMBRECLIENTE")
    private String nombrecliente;

    @Basic(optional = false)
    @Column(name = "SERIE")
    private String serie;

    public Transaccion() {
    }

    public Transaccion(TransaccionPK transaccionPK) {
        this.transaccionPK = transaccionPK;
    }

    public Transaccion(TransaccionPK transaccionPK, Character tipotransaccion, Date horainicia, Date horafinaliza,
                       String codcajero, BigDecimal montobase, BigDecimal montoimpuesto, BigDecimal vueltocliente,
                       BigDecimal montoremanente, short lineasfacturacion, Character cajaenlinea,
                       short duracionventa, short duracionpago, Character estadotransaccion,
                       Character regactualizado, int numimpnotaentrega, int numimpnotadespacho,
                       String tipodocumento, String nombrecliente, String serie) {
        this.transaccionPK = transaccionPK;
        this.tipotransaccion = tipotransaccion;
        this.horainicia = horainicia;
        this.horafinaliza = horafinaliza;
        this.codcajero = codcajero;
        this.montobase = montobase;
        this.montoimpuesto = montoimpuesto;
        this.vueltocliente = vueltocliente;
        this.montoremanente = montoremanente;
        this.lineasfacturacion = lineasfacturacion;
        this.cajaenlinea = cajaenlinea;
        this.duracionventa = duracionventa;
        this.duracionpago = duracionpago;
        this.estadotransaccion = estadotransaccion;
        this.regactualizado = regactualizado;
        this.numimpnotaentrega = numimpnotaentrega;
        this.numimpnotadespacho = numimpnotadespacho;
        this.tipodocumento = tipodocumento;
        this.nombrecliente = nombrecliente;
        this.serie = serie;
    }

    public Transaccion(short numtienda, Date fecha, short numcajainicia, int numregcajainicia) {
        this.transaccionPK = new TransaccionPK(numtienda, fecha, numcajainicia, numregcajainicia);
    }

    public TransaccionPK getTransaccionPK() {
        return transaccionPK;
    }

    public void setTransaccionPK(TransaccionPK transaccionPK) {
        this.transaccionPK = transaccionPK;
    }

    public Short getNumcajafinaliza() {
        return numcajafinaliza;
    }

    public void setNumcajafinaliza(Short numcajafinaliza) {
        this.numcajafinaliza = numcajafinaliza;
    }

    public Integer getNumtransaccion() {
        return numtransaccion;
    }

    public void setNumtransaccion(Integer numtransaccion) {
        this.numtransaccion = numtransaccion;
    }

    public Character getTipotransaccion() {
        return tipotransaccion;
    }

    public void setTipotransaccion(Character tipotransaccion) {
        this.tipotransaccion = tipotransaccion;
    }

    public Integer getNumcomprobantefiscal() {
        return numcomprobantefiscal;
    }

    public void setNumcomprobantefiscal(Integer numcomprobantefiscal) {
        this.numcomprobantefiscal = numcomprobantefiscal;
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

    public String getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(String codcliente) {
        this.codcliente = codcliente;
    }

    public String getCodcajero() {
        return codcajero;
    }

    public void setCodcajero(String codcajero) {
        this.codcajero = codcajero;
    }

    public String getCodigofacturaespera() {
        return codigofacturaespera;
    }

    public void setCodigofacturaespera(String codigofacturaespera) {
        this.codigofacturaespera = codigofacturaespera;
    }

    public BigDecimal getMontobase() {
        return montobase;
    }

    public void setMontobase(BigDecimal montobase) {
        this.montobase = montobase;
    }

    public BigDecimal getMontoimpuesto() {
        return montoimpuesto;
    }

    public void setMontoimpuesto(BigDecimal montoimpuesto) {
        this.montoimpuesto = montoimpuesto;
    }

    public BigDecimal getVueltocliente() {
        return vueltocliente;
    }

    public void setVueltocliente(BigDecimal vueltocliente) {
        this.vueltocliente = vueltocliente;
    }

    public BigDecimal getMontoremanente() {
        return montoremanente;
    }

    public void setMontoremanente(BigDecimal montoremanente) {
        this.montoremanente = montoremanente;
    }

    public short getLineasfacturacion() {
        return lineasfacturacion;
    }

    public void setLineasfacturacion(short lineasfacturacion) {
        this.lineasfacturacion = lineasfacturacion;
    }

    public Character getCajaenlinea() {
        return cajaenlinea;
    }

    public void setCajaenlinea(Character cajaenlinea) {
        this.cajaenlinea = cajaenlinea;
    }

    public String getSerialcaja() {
        return serialcaja;
    }

    public void setSerialcaja(String serialcaja) {
        this.serialcaja = serialcaja;
    }

    public short getDuracionventa() {
        return duracionventa;
    }

    public void setDuracionventa(short duracionventa) {
        this.duracionventa = duracionventa;
    }

    public short getDuracionpago() {
        return duracionpago;
    }

    public void setDuracionpago(short duracionpago) {
        this.duracionpago = duracionpago;
    }

    public Integer getChecksum() {
        return checksum;
    }

    public void setChecksum(Integer checksum) {
        this.checksum = checksum;
    }

    public Character getEstadotransaccion() {
        return estadotransaccion;
    }

    public void setEstadotransaccion(Character estadotransaccion) {
        this.estadotransaccion = estadotransaccion;
    }

    public Character getRegactualizado() {
        return regactualizado;
    }

    public void setRegactualizado(Character regactualizado) {
        this.regactualizado = regactualizado;
    }

    public String getCodautorizante() {
        return codautorizante;
    }

    public void setCodautorizante(String codautorizante) {
        this.codautorizante = codautorizante;
    }

    public int getNumimpnotaentrega() {
        return numimpnotaentrega;
    }

    public void setNumimpnotaentrega(int numimpnotaentrega) {
        this.numimpnotaentrega = numimpnotaentrega;
    }

    public int getNumimpnotadespacho() {
        return numimpnotadespacho;
    }

    public void setNumimpnotadespacho(int numimpnotadespacho) {
        this.numimpnotadespacho = numimpnotadespacho;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transaccionPK != null ? transaccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaccion)) {
            return false;
        }
        Transaccion other = (Transaccion) object;
        if ((this.transaccionPK == null && other.transaccionPK != null)
                || (this.transaccionPK != null && !this.transaccionPK.equals(other.transaccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Transaccion[ transaccionPK=" + transaccionPK + " ]";
    }

}
