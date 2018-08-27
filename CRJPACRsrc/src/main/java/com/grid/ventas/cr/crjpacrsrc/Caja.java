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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "CAJA")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Caja.findAll",
                           query = "SELECT c FROM Caja c"),
        @NamedQuery(name = "Caja.findByNumtienda",
                    query = "SELECT c FROM Caja c WHERE c.cajaPK.numtienda = :numtienda"),
        @NamedQuery(name = "Caja.findByNumcaja",
                    query = "SELECT c FROM Caja c WHERE c.cajaPK.numcaja = :numcaja"),
        @NamedQuery(name = "Caja.findByCodusuario",
                    query = "SELECT c FROM Caja c WHERE c.codusuario = :codusuario"),
        @NamedQuery(name = "Caja.findByNumtransaccion",
                    query = "SELECT c FROM Caja c WHERE c.numtransaccion = :numtransaccion"),
        @NamedQuery(name = "Caja.findByNumnofiscal",
                    query = "SELECT c FROM Caja c WHERE c.numnofiscal = :numnofiscal"),
        @NamedQuery(name = "Caja.findByNumregistro",
                    query = "SELECT c FROM Caja c WHERE c.numregistro = :numregistro"),
        @NamedQuery(name = "Caja.findByNumseqmerchant",
                    query = "SELECT c FROM Caja c WHERE c.numseqmerchant = :numseqmerchant"),
        @NamedQuery(name = "Caja.findByNivelauditoria",
                    query = "SELECT c FROM Caja c WHERE c.nivelauditoria = :nivelauditoria"),
        @NamedQuery(name = "Caja.findByFechareportez",
                    query = "SELECT c FROM Caja c WHERE c.fechareportez = :fechareportez"),
        @NamedQuery(name = "Caja.findByVersionsistema",
                    query = "SELECT c FROM Caja c WHERE c.versionsistema = :versionsistema"),
        @NamedQuery(name = "Caja.findByModelo",
                    query = "SELECT c FROM Caja c WHERE c.modelo = :modelo"),
        @NamedQuery(name = "Caja.findByNumserial",
                    query = "SELECT c FROM Caja c WHERE c.numserial = :numserial"),
        @NamedQuery(name = "Caja.findByMontorecaudado",
                    query = "SELECT c FROM Caja c WHERE c.montorecaudado = :montorecaudado"),
        @NamedQuery(name = "Caja.findByCierrecajero",
                    query = "SELECT c FROM Caja c WHERE c.cierrecajero = :cierrecajero"),
        @NamedQuery(name = "Caja.findByIpcaja",
                    query = "SELECT c FROM Caja c WHERE c.ipcaja = :ipcaja"),
        @NamedQuery(name = "Caja.findByFechaipcaja",
                    query = "SELECT c FROM Caja c WHERE c.fechaipcaja = :fechaipcaja"),
        @NamedQuery(name = "Caja.findByAutofacturacion",
                    query = "SELECT c FROM Caja c WHERE c.autofacturacion = :autofacturacion"),
        @NamedQuery(name = "Caja.findByFechareportezmensual",
                    query = "SELECT c FROM Caja c WHERE c.fechareportezmensual = :fechareportezmensual"),
        @NamedQuery(name = "Caja.findByNumreportex",
                    query = "SELECT c FROM Caja c WHERE c.numreportex = :numreportex"),
        @NamedQuery(name = "Caja.findByNumreportez",
                    query = "SELECT c FROM Caja c WHERE c.numreportez = :numreportez"),
        @NamedQuery(name = "Caja.findByNumreportezmensual",
                    query = "SELECT c FROM Caja c WHERE c.numreportezmensual = :numreportezmensual")})
public class Caja implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected CajaPK cajaPK;

    @Column(name = "CODUSUARIO")
    private String codusuario;

    @Basic(optional = false)
    @Column(name = "NUMTRANSACCION")
    private int numtransaccion;

    @Basic(optional = false)
    @Column(name = "NUMNOFISCAL")
    private int numnofiscal;

    @Basic(optional = false)
    @Column(name = "NUMREGISTRO")
    private int numregistro;

    @Basic(optional = false)
    @Column(name = "NUMSEQMERCHANT")
    private int numseqmerchant;

    @Basic(optional = false)
    @Column(name = "NIVELAUDITORIA")
    private Character nivelauditoria;

    @Basic(optional = false)
    @Column(name = "FECHAREPORTEZ")
    @Temporal(TemporalType.DATE)
    private Date fechareportez;

    @Basic(optional = false)
    @Column(name = "VERSIONSISTEMA")
    private String versionsistema;

    @Column(name = "MODELO")
    private String modelo;

    @Column(name = "NUMSERIAL")
    private String numserial;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MONTORECAUDADO")
    private BigDecimal montorecaudado;

    @Column(name = "CIERRECAJERO")
    private Character cierrecajero;

    @Column(name = "IPCAJA")
    private String ipcaja;

    @Column(name = "FECHAIPCAJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaipcaja;

    @Basic(optional = false)
    @Column(name = "AUTOFACTURACION")
    private Character autofacturacion;

    @Basic(optional = false)
    @Column(name = "FECHAREPORTEZMENSUAL")
    @Temporal(TemporalType.DATE)
    private Date fechareportezmensual;

    @Basic(optional = false)
    @Column(name = "NUMREPORTEX")
    private long numreportex;

    @Basic(optional = false)
    @Column(name = "NUMREPORTEZ")
    private long numreportez;

    @Basic(optional = false)
    @Column(name = "NUMREPORTEZMENSUAL")
    private long numreportezmensual;

    @JoinColumn(name = "IDESTADOCAJA",
                referencedColumnName = "IDESTADO")
    @ManyToOne(optional = false,
               fetch = FetchType.LAZY)
    private Estadodecaja idestadocaja;

    @JoinColumn(name = "NUMTIENDA",
                referencedColumnName = "NUMTIENDA",
                insertable = false,
                updatable = false)
    @ManyToOne(optional = false,
               fetch = FetchType.LAZY)
    private Tienda tienda;

    public Caja() {
    }

    public Caja(CajaPK cajaPK) {
        this.cajaPK = cajaPK;
    }

    public Caja(CajaPK cajaPK, int numtransaccion, int numnofiscal, int numregistro, int numseqmerchant,
                Character nivelauditoria, Date fechareportez, String versionsistema, BigDecimal montorecaudado,
                Character autofacturacion, Date fechareportezmensual, long numreportex, long numreportez,
                long numreportezmensual) {
        this.cajaPK = cajaPK;
        this.numtransaccion = numtransaccion;
        this.numnofiscal = numnofiscal;
        this.numregistro = numregistro;
        this.numseqmerchant = numseqmerchant;
        this.nivelauditoria = nivelauditoria;
        this.fechareportez = fechareportez;
        this.versionsistema = versionsistema;
        this.montorecaudado = montorecaudado;
        this.autofacturacion = autofacturacion;
        this.fechareportezmensual = fechareportezmensual;
        this.numreportex = numreportex;
        this.numreportez = numreportez;
        this.numreportezmensual = numreportezmensual;
    }

    public Caja(short numtienda, short numcaja) {
        this.cajaPK = new CajaPK(numtienda, numcaja);
    }

    public CajaPK getCajaPK() {
        return cajaPK;
    }

    public void setCajaPK(CajaPK cajaPK) {
        this.cajaPK = cajaPK;
    }

    public String getCodusuario() {
        return codusuario;
    }

    public void setCodusuario(String codusuario) {
        this.codusuario = codusuario;
    }

    public int getNumtransaccion() {
        return numtransaccion;
    }

    public void setNumtransaccion(int numtransaccion) {
        this.numtransaccion = numtransaccion;
    }

    public int getNumnofiscal() {
        return numnofiscal;
    }

    public void setNumnofiscal(int numnofiscal) {
        this.numnofiscal = numnofiscal;
    }

    public int getNumregistro() {
        return numregistro;
    }

    public void setNumregistro(int numregistro) {
        this.numregistro = numregistro;
    }

    public int getNumseqmerchant() {
        return numseqmerchant;
    }

    public void setNumseqmerchant(int numseqmerchant) {
        this.numseqmerchant = numseqmerchant;
    }

    public Character getNivelauditoria() {
        return nivelauditoria;
    }

    public void setNivelauditoria(Character nivelauditoria) {
        this.nivelauditoria = nivelauditoria;
    }

    public Date getFechareportez() {
        return fechareportez;
    }

    public void setFechareportez(Date fechareportez) {
        this.fechareportez = fechareportez;
    }

    public String getVersionsistema() {
        return versionsistema;
    }

    public void setVersionsistema(String versionsistema) {
        this.versionsistema = versionsistema;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumserial() {
        return numserial;
    }

    public void setNumserial(String numserial) {
        this.numserial = numserial;
    }

    public BigDecimal getMontorecaudado() {
        return montorecaudado;
    }

    public void setMontorecaudado(BigDecimal montorecaudado) {
        this.montorecaudado = montorecaudado;
    }

    public Character getCierrecajero() {
        return cierrecajero;
    }

    public void setCierrecajero(Character cierrecajero) {
        this.cierrecajero = cierrecajero;
    }

    public String getIpcaja() {
        return ipcaja;
    }

    public void setIpcaja(String ipcaja) {
        this.ipcaja = ipcaja;
    }

    public Date getFechaipcaja() {
        return fechaipcaja;
    }

    public void setFechaipcaja(Date fechaipcaja) {
        this.fechaipcaja = fechaipcaja;
    }

    public Character getAutofacturacion() {
        return autofacturacion;
    }

    public void setAutofacturacion(Character autofacturacion) {
        this.autofacturacion = autofacturacion;
    }

    public Date getFechareportezmensual() {
        return fechareportezmensual;
    }

    public void setFechareportezmensual(Date fechareportezmensual) {
        this.fechareportezmensual = fechareportezmensual;
    }

    public long getNumreportex() {
        return numreportex;
    }

    public void setNumreportex(long numreportex) {
        this.numreportex = numreportex;
    }

    public long getNumreportez() {
        return numreportez;
    }

    public void setNumreportez(long numreportez) {
        this.numreportez = numreportez;
    }

    public long getNumreportezmensual() {
        return numreportezmensual;
    }

    public void setNumreportezmensual(long numreportezmensual) {
        this.numreportezmensual = numreportezmensual;
    }

    public Estadodecaja getIdestadocaja() {
        return idestadocaja;
    }

    public void setIdestadocaja(Estadodecaja idestadocaja) {
        this.idestadocaja = idestadocaja;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cajaPK != null ? cajaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caja)) {
            return false;
        }
        Caja other = (Caja) object;
        if ((this.cajaPK == null && other.cajaPK != null)
                || (this.cajaPK != null && !this.cajaPK.equals(other.cajaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Caja[ cajaPK=" + cajaPK + " ]";
    }

}
