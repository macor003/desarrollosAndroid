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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eve0017909
 */
@Entity
@Table(name = "DONACION")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Donacion.findAll",
                           query = "SELECT d FROM Donacion d"),
        @NamedQuery(name = "Donacion.findByNumtienda",
                    query = "SELECT d FROM Donacion d WHERE d.donacionPK.numtienda = :numtienda"),
        @NamedQuery(name = "Donacion.findByNumcaja",
                    query = "SELECT d FROM Donacion d WHERE d.donacionPK.numcaja = :numcaja"),
        @NamedQuery(name = "Donacion.findByNumtransaccion",
                    query = "SELECT d FROM Donacion d WHERE d.donacionPK.numtransaccion = :numtransaccion"),
        @NamedQuery(name = "Donacion.findByTipoformadepago",
                    query = "SELECT d FROM Donacion d WHERE d.tipoformadepago = :tipoformadepago"),
        @NamedQuery(name = "Donacion.findByCodformadepago",
                    query = "SELECT d FROM Donacion d WHERE d.donacionPK.codformadepago = :codformadepago"),
        @NamedQuery(name = "Donacion.findByCorrelativoitem",
                    query = "SELECT d FROM Donacion d WHERE d.donacionPK.correlativoitem = :correlativoitem"),
        @NamedQuery(name = "Donacion.findByFecha",
                    query = "SELECT d FROM Donacion d WHERE d.donacionPK.fecha = :fecha"),
        @NamedQuery(name = "Donacion.findByMonto",
                    query = "SELECT d FROM Donacion d WHERE d.monto = :monto"),
        @NamedQuery(name = "Donacion.findByNumdocumento",
                    query = "SELECT d FROM Donacion d WHERE d.numdocumento = :numdocumento"),
        @NamedQuery(name = "Donacion.findByNumcuenta",
                    query = "SELECT d FROM Donacion d WHERE d.numcuenta = :numcuenta"),
        @NamedQuery(name = "Donacion.findByNumconformacion",
                    query = "SELECT d FROM Donacion d WHERE d.numconformacion = :numconformacion"),
        @NamedQuery(name = "Donacion.findByNumreferencia",
                    query = "SELECT d FROM Donacion d WHERE d.numreferencia = :numreferencia"),
        @NamedQuery(name = "Donacion.findByCedtitular",
                    query = "SELECT d FROM Donacion d WHERE d.cedtitular = :cedtitular"),
        @NamedQuery(name = "Donacion.findByCodbanco",
                    query = "SELECT d FROM Donacion d WHERE d.codbanco = :codbanco"),
        @NamedQuery(name = "Donacion.findByRegactualizado",
                    query = "SELECT d FROM Donacion d WHERE d.regactualizado = :regactualizado"),
        @NamedQuery(name = "Donacion.findByCodautorizante",
                    query = "SELECT d FROM Donacion d WHERE d.codautorizante = :codautorizante")})
public class Donacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected DonacionPK donacionPK;

    @Basic(optional = false)
    @Column(name = "TIPOFORMADEPAGO")
    private short tipoformadepago;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MONTO")
    private BigDecimal monto;

    @Column(name = "NUMDOCUMENTO")
    private String numdocumento;

    @Column(name = "NUMCUENTA")
    private String numcuenta;

    @Column(name = "NUMCONFORMACION")
    private Integer numconformacion;

    @Column(name = "NUMREFERENCIA")
    private Integer numreferencia;

    @Column(name = "CEDTITULAR")
    private String cedtitular;

    @Column(name = "CODBANCO")
    private String codbanco;

    @Basic(optional = false)
    @Column(name = "REGACTUALIZADO")
    private Character regactualizado;

    @Column(name = "CODAUTORIZANTE")
    private String codautorizante;

    public Donacion() {
    }

    public Donacion(DonacionPK donacionPK) {
        this.donacionPK = donacionPK;
    }

    public Donacion(DonacionPK donacionPK, short tipoformadepago, BigDecimal monto, Character regactualizado) {
        this.donacionPK = donacionPK;
        this.tipoformadepago = tipoformadepago;
        this.monto = monto;
        this.regactualizado = regactualizado;
    }

    public Donacion(short numtienda, short numcaja, int numtransaccion, String codformadepago,
                    short correlativoitem, Date fecha) {
        this.donacionPK = new DonacionPK(numtienda, numcaja, numtransaccion, codformadepago, correlativoitem,
                fecha);
    }

    public DonacionPK getDonacionPK() {
        return donacionPK;
    }

    public void setDonacionPK(DonacionPK donacionPK) {
        this.donacionPK = donacionPK;
    }

    public short getTipoformadepago() {
        return tipoformadepago;
    }

    public void setTipoformadepago(short tipoformadepago) {
        this.tipoformadepago = tipoformadepago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getNumdocumento() {
        return numdocumento;
    }

    public void setNumdocumento(String numdocumento) {
        this.numdocumento = numdocumento;
    }

    public String getNumcuenta() {
        return numcuenta;
    }

    public void setNumcuenta(String numcuenta) {
        this.numcuenta = numcuenta;
    }

    public Integer getNumconformacion() {
        return numconformacion;
    }

    public void setNumconformacion(Integer numconformacion) {
        this.numconformacion = numconformacion;
    }

    public Integer getNumreferencia() {
        return numreferencia;
    }

    public void setNumreferencia(Integer numreferencia) {
        this.numreferencia = numreferencia;
    }

    public String getCedtitular() {
        return cedtitular;
    }

    public void setCedtitular(String cedtitular) {
        this.cedtitular = cedtitular;
    }

    public String getCodbanco() {
        return codbanco;
    }

    public void setCodbanco(String codbanco) {
        this.codbanco = codbanco;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (donacionPK != null ? donacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Donacion)) {
            return false;
        }
        Donacion other = (Donacion) object;
        if ((this.donacionPK == null && other.donacionPK != null)
                || (this.donacionPK != null && !this.donacionPK.equals(other.donacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Donacion[ donacionPK=" + donacionPK + " ]";
    }

}
