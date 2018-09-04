/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "PAGOACREENCIA")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Pagoacreencia.findAll",
                           query = "SELECT p FROM Pagoacreencia p"),
        @NamedQuery(name = "Pagoacreencia.findByNumtienda",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.pagoacreenciaPK.numtienda = :numtienda"),
        @NamedQuery(name = "Pagoacreencia.findByNumcaja",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.numcaja = :numcaja"),
        @NamedQuery(name = "Pagoacreencia.findByNumoperacion",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.pagoacreenciaPK.numoperacion = :numoperacion"),
        @NamedQuery(name = "Pagoacreencia.findByNumacreencia",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.pagoacreenciaPK.numacreencia = :numacreencia"),
        @NamedQuery(name = "Pagoacreencia.findByFecha",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.pagoacreenciaPK.fecha = :fecha"),
        @NamedQuery(name = "Pagoacreencia.findByCodformadepago",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.pagoacreenciaPK.codformadepago = :codformadepago"),
        @NamedQuery(name = "Pagoacreencia.findByCorrelativoitems",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.pagoacreenciaPK.correlativoitems = :correlativoitems"),
        @NamedQuery(name = "Pagoacreencia.findByMonto",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.monto = :monto"),
        @NamedQuery(name = "Pagoacreencia.findByCodbanco",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.codbanco = :codbanco"),
        @NamedQuery(name = "Pagoacreencia.findByNumdocumento",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.numdocumento = :numdocumento"),
        @NamedQuery(name = "Pagoacreencia.findByNumcuenta",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.numcuenta = :numcuenta"),
        @NamedQuery(name = "Pagoacreencia.findByCodcliente",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.pagoacreenciaPK.codcliente = :codcliente"),
        @NamedQuery(name = "Pagoacreencia.findByRegactualizado",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.regactualizado = :regactualizado"),
        @NamedQuery(name = "Pagoacreencia.findByNumconformacion",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.numconformacion = :numconformacion"),
        @NamedQuery(name = "Pagoacreencia.findByNumreferencia",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.numreferencia = :numreferencia"),
        @NamedQuery(name = "Pagoacreencia.findByCedtitular",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.cedtitular = :cedtitular"),
        @NamedQuery(name = "Pagoacreencia.findByTipoacreencia",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.tipoacreencia = :tipoacreencia"),
        @NamedQuery(name = "Pagoacreencia.findByConfirmado",
                    query = "SELECT p FROM Pagoacreencia p WHERE p.confirmado = :confirmado")})
public class Pagoacreencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected PagoacreenciaPK pagoacreenciaPK;

    @Basic(optional = false)
    @Column(name = "NUMCAJA")
    private int numcaja;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MONTO")
    private BigDecimal monto;

    @Column(name = "CODBANCO")
    private String codbanco;

    @Column(name = "NUMDOCUMENTO")
    private String numdocumento;

    @Column(name = "NUMCUENTA")
    private String numcuenta;

    @Basic(optional = false)
    @Column(name = "REGACTUALIZADO")
    private Character regactualizado;

    @Column(name = "NUMCONFORMACION")
    private Integer numconformacion;

    @Column(name = "NUMREFERENCIA")
    private Integer numreferencia;

    @Column(name = "CEDTITULAR")
    private String cedtitular;

    @Basic(optional = false)
    @Column(name = "TIPOACREENCIA")
    private Character tipoacreencia;

    @Basic(optional = false)
    @Column(name = "CONFIRMADO")
    private Character confirmado;

    public Pagoacreencia() {
    }

    public Pagoacreencia(PagoacreenciaPK pagoacreenciaPK) {
        this.pagoacreenciaPK = pagoacreenciaPK;
    }

    public Pagoacreencia(PagoacreenciaPK pagoacreenciaPK, int numcaja, BigDecimal monto, Character regactualizado,
                         Character tipoacreencia, Character confirmado) {
        this.pagoacreenciaPK = pagoacreenciaPK;
        this.numcaja = numcaja;
        this.monto = monto;
        this.regactualizado = regactualizado;
        this.tipoacreencia = tipoacreencia;
        this.confirmado = confirmado;
    }

    public Pagoacreencia(short numtienda, int numoperacion, int numacreencia, String fecha, String codformadepago,
                         int correlativoitems, String codcliente) {
        this.pagoacreenciaPK = new PagoacreenciaPK(numtienda, numoperacion, numacreencia, fecha, codformadepago,
                correlativoitems, codcliente);
    }

    public PagoacreenciaPK getPagoacreenciaPK() {
        return pagoacreenciaPK;
    }

    public void setPagoacreenciaPK(PagoacreenciaPK pagoacreenciaPK) {
        this.pagoacreenciaPK = pagoacreenciaPK;
    }

    public int getNumcaja() {
        return numcaja;
    }

    public void setNumcaja(int numcaja) {
        this.numcaja = numcaja;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getCodbanco() {
        return codbanco;
    }

    public void setCodbanco(String codbanco) {
        this.codbanco = codbanco;
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

    public Character getRegactualizado() {
        return regactualizado;
    }

    public void setRegactualizado(Character regactualizado) {
        this.regactualizado = regactualizado;
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

    public Character getTipoacreencia() {
        return tipoacreencia;
    }

    public void setTipoacreencia(Character tipoacreencia) {
        this.tipoacreencia = tipoacreencia;
    }

    public Character getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Character confirmado) {
        this.confirmado = confirmado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pagoacreenciaPK != null ? pagoacreenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagoacreencia)) {
            return false;
        }
        Pagoacreencia other = (Pagoacreencia) object;
        if ((this.pagoacreenciaPK == null && other.pagoacreenciaPK != null)
                || (this.pagoacreenciaPK != null && !this.pagoacreenciaPK.equals(other.pagoacreenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Pagoacreencia[ pagoacreenciaPK=" + pagoacreenciaPK + " ]";
    }

}
