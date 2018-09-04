/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "PUNTOAGILFORMADEPAGO")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Puntoagilformadepago.findAll",
                           query = "SELECT p FROM Puntoagilformadepago p"),
        @NamedQuery(name = "Puntoagilformadepago.findByCodformadepago",
                    query = "SELECT p FROM Puntoagilformadepago p WHERE p.codformadepago = :codformadepago"),
        @NamedQuery(name = "Puntoagilformadepago.findByPermitepuntoagil",
                    query = "SELECT p FROM Puntoagilformadepago p WHERE p.permitepuntoagil = :permitepuntoagil"),
        @NamedQuery(name = "Puntoagilformadepago.findByRequierelecturabanda",
                    query = "SELECT p FROM Puntoagilformadepago p WHERE p.requierelecturabanda = :requierelecturabanda"),
        @NamedQuery(name = "Puntoagilformadepago.findByImprimirvoucher",
                    query = "SELECT p FROM Puntoagilformadepago p WHERE p.imprimirvoucher = :imprimirvoucher"),
        @NamedQuery(name = "Puntoagilformadepago.findByIndicarplan",
                    query = "SELECT p FROM Puntoagilformadepago p WHERE p.indicarplan = :indicarplan"),
        @NamedQuery(name = "Puntoagilformadepago.findByIndicarcuentaespecial",
                    query = "SELECT p FROM Puntoagilformadepago p WHERE p.indicarcuentaespecial = :indicarcuentaespecial"),
        @NamedQuery(name = "Puntoagilformadepago.findByIndicartipocuenta",
                    query = "SELECT p FROM Puntoagilformadepago p WHERE p.indicartipocuenta = :indicartipocuenta"),
        @NamedQuery(name = "Puntoagilformadepago.findByTipotarjetapuntoagil",
                    query = "SELECT p FROM Puntoagilformadepago p WHERE p.tipotarjetapuntoagil = :tipotarjetapuntoagil"),
        @NamedQuery(name = "Puntoagilformadepago.findByFechaactualizacion",
                    query = "SELECT p FROM Puntoagilformadepago p WHERE p.fechaactualizacion = :fechaactualizacion"),
        @NamedQuery(name = "Puntoagilformadepago.findByLongitudcodigoseguridad",
                    query = "SELECT p FROM Puntoagilformadepago p WHERE p.longitudcodigoseguridad = :longitudcodigoseguridad")})
public class Puntoagilformadepago implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "CODFORMADEPAGO")
    private String codformadepago;

    @Basic(optional = false)
    @Column(name = "PERMITEPUNTOAGIL")
    private Character permitepuntoagil;

    @Basic(optional = false)
    @Column(name = "REQUIERELECTURABANDA")
    private Character requierelecturabanda;

    @Basic(optional = false)
    @Column(name = "IMPRIMIRVOUCHER")
    private Character imprimirvoucher;

    @Basic(optional = false)
    @Column(name = "INDICARPLAN")
    private Character indicarplan;

    @Basic(optional = false)
    @Column(name = "INDICARCUENTAESPECIAL")
    private Character indicarcuentaespecial;

    @Basic(optional = false)
    @Column(name = "INDICARTIPOCUENTA")
    private Character indicartipocuenta;

    @Column(name = "TIPOTARJETAPUNTOAGIL")
    private Character tipotarjetapuntoagil;

    @Basic(optional = false)
    @Column(name = "FECHAACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaactualizacion;

    @Column(name = "LONGITUDCODIGOSEGURIDAD")
    private Short longitudcodigoseguridad;

    public Puntoagilformadepago() {
    }

    public Puntoagilformadepago(String codformadepago) {
        this.codformadepago = codformadepago;
    }

    public Puntoagilformadepago(String codformadepago, Character permitepuntoagil, Character requierelecturabanda,
                                Character imprimirvoucher, Character indicarplan, Character indicarcuentaespecial,
                                Character indicartipocuenta, Date fechaactualizacion) {
        this.codformadepago = codformadepago;
        this.permitepuntoagil = permitepuntoagil;
        this.requierelecturabanda = requierelecturabanda;
        this.imprimirvoucher = imprimirvoucher;
        this.indicarplan = indicarplan;
        this.indicarcuentaespecial = indicarcuentaespecial;
        this.indicartipocuenta = indicartipocuenta;
        this.fechaactualizacion = fechaactualizacion;
    }

    public String getCodformadepago() {
        return codformadepago;
    }

    public void setCodformadepago(String codformadepago) {
        this.codformadepago = codformadepago;
    }

    public Character getPermitepuntoagil() {
        return permitepuntoagil;
    }

    public void setPermitepuntoagil(Character permitepuntoagil) {
        this.permitepuntoagil = permitepuntoagil;
    }

    public Character getRequierelecturabanda() {
        return requierelecturabanda;
    }

    public void setRequierelecturabanda(Character requierelecturabanda) {
        this.requierelecturabanda = requierelecturabanda;
    }

    public Character getImprimirvoucher() {
        return imprimirvoucher;
    }

    public void setImprimirvoucher(Character imprimirvoucher) {
        this.imprimirvoucher = imprimirvoucher;
    }

    public Character getIndicarplan() {
        return indicarplan;
    }

    public void setIndicarplan(Character indicarplan) {
        this.indicarplan = indicarplan;
    }

    public Character getIndicarcuentaespecial() {
        return indicarcuentaespecial;
    }

    public void setIndicarcuentaespecial(Character indicarcuentaespecial) {
        this.indicarcuentaespecial = indicarcuentaespecial;
    }

    public Character getIndicartipocuenta() {
        return indicartipocuenta;
    }

    public void setIndicartipocuenta(Character indicartipocuenta) {
        this.indicartipocuenta = indicartipocuenta;
    }

    public Character getTipotarjetapuntoagil() {
        return tipotarjetapuntoagil;
    }

    public void setTipotarjetapuntoagil(Character tipotarjetapuntoagil) {
        this.tipotarjetapuntoagil = tipotarjetapuntoagil;
    }

    public Date getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(Date fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }

    public Short getLongitudcodigoseguridad() {
        return longitudcodigoseguridad;
    }

    public void setLongitudcodigoseguridad(Short longitudcodigoseguridad) {
        this.longitudcodigoseguridad = longitudcodigoseguridad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codformadepago != null ? codformadepago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puntoagilformadepago)) {
            return false;
        }
        Puntoagilformadepago other = (Puntoagilformadepago) object;
        if ((this.codformadepago == null && other.codformadepago != null)
                || (this.codformadepago != null && !this.codformadepago.equals(other.codformadepago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Puntoagilformadepago[ codformadepago=" + codformadepago + " ]";
    }

}
