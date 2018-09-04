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
@Table(name = "ACREENCIAS")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Acreencias.findAll",
                           query = "SELECT a FROM Acreencias a"),
        @NamedQuery(name = "Acreencias.findByNumtienda",
                    query = "SELECT a FROM Acreencias a WHERE a.acreenciasPK.numtienda = :numtienda"),
        @NamedQuery(name = "Acreencias.findByNumcaja",
                    query = "SELECT a FROM Acreencias a WHERE a.acreenciasPK.numcaja = :numcaja"),
        @NamedQuery(name = "Acreencias.findByFecha",
                    query = "SELECT a FROM Acreencias a WHERE a.acreenciasPK.fecha = :fecha"),
        @NamedQuery(name = "Acreencias.findByNumoperacion",
                    query = "SELECT a FROM Acreencias a WHERE a.acreenciasPK.numoperacion = :numoperacion"),
        @NamedQuery(name = "Acreencias.findByNumacreencia",
                    query = "SELECT a FROM Acreencias a WHERE a.acreenciasPK.numacreencia = :numacreencia"),
        @NamedQuery(name = "Acreencias.findByTipoacreencia",
                    query = "SELECT a FROM Acreencias a WHERE a.acreenciasPK.tipoacreencia = :tipoacreencia"),
        @NamedQuery(name = "Acreencias.findByHorainicia",
                    query = "SELECT a FROM Acreencias a WHERE a.horainicia = :horainicia"),
        @NamedQuery(name = "Acreencias.findByHorafinaliza",
                    query = "SELECT a FROM Acreencias a WHERE a.horafinaliza = :horafinaliza"),
        @NamedQuery(name = "Acreencias.findByMonto",
                    query = "SELECT a FROM Acreencias a WHERE a.monto = :monto"),
        @NamedQuery(name = "Acreencias.findByVuelto",
                    query = "SELECT a FROM Acreencias a WHERE a.vuelto = :vuelto"),
        @NamedQuery(name = "Acreencias.findByCajaenlinea",
                    query = "SELECT a FROM Acreencias a WHERE a.cajaenlinea = :cajaenlinea"),
        @NamedQuery(name = "Acreencias.findBySerialcaja",
                    query = "SELECT a FROM Acreencias a WHERE a.serialcaja = :serialcaja"),
        @NamedQuery(name = "Acreencias.findByFechadevolucion",
                    query = "SELECT a FROM Acreencias a WHERE a.fechadevolucion = :fechadevolucion"),
        @NamedQuery(name = "Acreencias.findByCajadevolucion",
                    query = "SELECT a FROM Acreencias a WHERE a.cajadevolucion = :cajadevolucion"),
        @NamedQuery(name = "Acreencias.findByTransdevolucion",
                    query = "SELECT a FROM Acreencias a WHERE a.transdevolucion = :transdevolucion"),
        @NamedQuery(name = "Acreencias.findByCodcliente",
                    query = "SELECT a FROM Acreencias a WHERE a.acreenciasPK.codcliente = :codcliente"),
        @NamedQuery(name = "Acreencias.findByCampofecha",
                    query = "SELECT a FROM Acreencias a WHERE a.campofecha = :campofecha"),
        @NamedQuery(name = "Acreencias.findByCamponumerico",
                    query = "SELECT a FROM Acreencias a WHERE a.camponumerico = :camponumerico"),
        @NamedQuery(name = "Acreencias.findByCampocaracter",
                    query = "SELECT a FROM Acreencias a WHERE a.campocaracter = :campocaracter"),
        @NamedQuery(name = "Acreencias.findByRegactualizado",
                    query = "SELECT a FROM Acreencias a WHERE a.regactualizado = :regactualizado"),
        @NamedQuery(name = "Acreencias.findByCodcajero",
                    query = "SELECT a FROM Acreencias a WHERE a.codcajero = :codcajero"),
        @NamedQuery(name = "Acreencias.findByEstadoacreencia",
                    query = "SELECT a FROM Acreencias a WHERE a.estadoacreencia = :estadoacreencia"),
        @NamedQuery(name = "Acreencias.findByAnulaoperacion",
                    query = "SELECT a FROM Acreencias a WHERE a.anulaoperacion = :anulaoperacion"),
        @NamedQuery(name = "Acreencias.findByReversado",
                    query = "SELECT a FROM Acreencias a WHERE a.reversado = :reversado"),
        @NamedQuery(name = "Acreencias.findByNumtransaccion",
                    query = "SELECT a FROM Acreencias a WHERE a.numtransaccion = :numtransaccion"),
        @NamedQuery(name = "Acreencias.findByNumcajatrans",
                    query = "SELECT a FROM Acreencias a WHERE a.numcajatrans = :numcajatrans")})
public class Acreencias implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected AcreenciasPK acreenciasPK;

    @Basic(optional = false)
    @Column(name = "HORAINICIA")
    @Temporal(TemporalType.TIME)
    private Date horainicia;

    @Basic(optional = false)
    @Column(name = "HORAFINALIZA")
    @Temporal(TemporalType.TIME)
    private Date horafinaliza;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MONTO")
    private BigDecimal monto;

    @Basic(optional = false)
    @Column(name = "VUELTO")
    private BigDecimal vuelto;

    @Basic(optional = false)
    @Column(name = "CAJAENLINEA")
    private Character cajaenlinea;

    @Column(name = "SERIALCAJA")
    private String serialcaja;

    @Column(name = "FECHADEVOLUCION")
    private String fechadevolucion;

    @Column(name = "CAJADEVOLUCION")
    private Integer cajadevolucion;

    @Column(name = "TRANSDEVOLUCION")
    private Integer transdevolucion;

    @Column(name = "CAMPOFECHA")
    private String campofecha;

    @Column(name = "CAMPONUMERICO")
    private Integer camponumerico;

    @Column(name = "CAMPOCARACTER")
    private String campocaracter;

    @Basic(optional = false)
    @Column(name = "REGACTUALIZADO")
    private Character regactualizado;

    @Basic(optional = false)
    @Column(name = "CODCAJERO")
    private String codcajero;

    @Basic(optional = false)
    @Column(name = "ESTADOACREENCIA")
    private Character estadoacreencia;

    @Basic(optional = false)
    @Column(name = "ANULAOPERACION")
    private int anulaoperacion;

    @Basic(optional = false)
    @Column(name = "REVERSADO")
    private Character reversado;

    @Column(name = "NUMTRANSACCION")
    private Integer numtransaccion;

    @Column(name = "NUMCAJATRANS")
    private Integer numcajatrans;

    public Acreencias() {
    }

    public Acreencias(AcreenciasPK acreenciasPK) {
        this.acreenciasPK = acreenciasPK;
    }

    public Acreencias(AcreenciasPK acreenciasPK, Date horainicia, Date horafinaliza, BigDecimal monto,
                      BigDecimal vuelto, Character cajaenlinea, Character regactualizado, String codcajero,
                      Character estadoacreencia, int anulaoperacion, Character reversado) {
        this.acreenciasPK = acreenciasPK;
        this.horainicia = horainicia;
        this.horafinaliza = horafinaliza;
        this.monto = monto;
        this.vuelto = vuelto;
        this.cajaenlinea = cajaenlinea;
        this.regactualizado = regactualizado;
        this.codcajero = codcajero;
        this.estadoacreencia = estadoacreencia;
        this.anulaoperacion = anulaoperacion;
        this.reversado = reversado;
    }

    public Acreencias(short numtienda, int numcaja, String fecha, int numoperacion, int numacreencia,
                      Character tipoacreencia, String codcliente) {
        this.acreenciasPK = new AcreenciasPK(numtienda, numcaja, fecha, numoperacion, numacreencia, tipoacreencia,
                codcliente);
    }

    public AcreenciasPK getAcreenciasPK() {
        return acreenciasPK;
    }

    public void setAcreenciasPK(AcreenciasPK acreenciasPK) {
        this.acreenciasPK = acreenciasPK;
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

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getVuelto() {
        return vuelto;
    }

    public void setVuelto(BigDecimal vuelto) {
        this.vuelto = vuelto;
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

    public String getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(String fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    public Integer getCajadevolucion() {
        return cajadevolucion;
    }

    public void setCajadevolucion(Integer cajadevolucion) {
        this.cajadevolucion = cajadevolucion;
    }

    public Integer getTransdevolucion() {
        return transdevolucion;
    }

    public void setTransdevolucion(Integer transdevolucion) {
        this.transdevolucion = transdevolucion;
    }

    public String getCampofecha() {
        return campofecha;
    }

    public void setCampofecha(String campofecha) {
        this.campofecha = campofecha;
    }

    public Integer getCamponumerico() {
        return camponumerico;
    }

    public void setCamponumerico(Integer camponumerico) {
        this.camponumerico = camponumerico;
    }

    public String getCampocaracter() {
        return campocaracter;
    }

    public void setCampocaracter(String campocaracter) {
        this.campocaracter = campocaracter;
    }

    public Character getRegactualizado() {
        return regactualizado;
    }

    public void setRegactualizado(Character regactualizado) {
        this.regactualizado = regactualizado;
    }

    public String getCodcajero() {
        return codcajero;
    }

    public void setCodcajero(String codcajero) {
        this.codcajero = codcajero;
    }

    public Character getEstadoacreencia() {
        return estadoacreencia;
    }

    public void setEstadoacreencia(Character estadoacreencia) {
        this.estadoacreencia = estadoacreencia;
    }

    public int getAnulaoperacion() {
        return anulaoperacion;
    }

    public void setAnulaoperacion(int anulaoperacion) {
        this.anulaoperacion = anulaoperacion;
    }

    public Character getReversado() {
        return reversado;
    }

    public void setReversado(Character reversado) {
        this.reversado = reversado;
    }

    public Integer getNumtransaccion() {
        return numtransaccion;
    }

    public void setNumtransaccion(Integer numtransaccion) {
        this.numtransaccion = numtransaccion;
    }

    public Integer getNumcajatrans() {
        return numcajatrans;
    }

    public void setNumcajatrans(Integer numcajatrans) {
        this.numcajatrans = numcajatrans;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acreenciasPK != null ? acreenciasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acreencias)) {
            return false;
        }
        Acreencias other = (Acreencias) object;
        if ((this.acreenciasPK == null && other.acreenciasPK != null)
                || (this.acreenciasPK != null && !this.acreenciasPK.equals(other.acreenciasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Acreencias[ acreenciasPK=" + acreenciasPK + " ]";
    }

}
