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
@Table(name = "PUNTOAGILTIPOCUENTA")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Puntoagiltipocuenta.findAll",
                           query = "SELECT p FROM Puntoagiltipocuenta p"),
        @NamedQuery(name = "Puntoagiltipocuenta.findByIdpuntoagiltipocuenta",
                    query = "SELECT p FROM Puntoagiltipocuenta p WHERE p.idpuntoagiltipocuenta = :idpuntoagiltipocuenta"),
        @NamedQuery(name = "Puntoagiltipocuenta.findByTipocuentapuntoagil",
                    query = "SELECT p FROM Puntoagiltipocuenta p WHERE p.tipocuentapuntoagil = :tipocuentapuntoagil"),
        @NamedQuery(name = "Puntoagiltipocuenta.findByDescripcion",
                    query = "SELECT p FROM Puntoagiltipocuenta p WHERE p.descripcion = :descripcion"),
        @NamedQuery(name = "Puntoagiltipocuenta.findByAbrtipocuenta",
                    query = "SELECT p FROM Puntoagiltipocuenta p WHERE p.abrtipocuenta = :abrtipocuenta"),
        @NamedQuery(name = "Puntoagiltipocuenta.findByRegvigente",
                    query = "SELECT p FROM Puntoagiltipocuenta p WHERE p.regvigente = :regvigente"),
        @NamedQuery(name = "Puntoagiltipocuenta.findByFechaactualizacion",
                    query = "SELECT p FROM Puntoagiltipocuenta p WHERE p.fechaactualizacion = :fechaactualizacion")})
public class Puntoagiltipocuenta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "IDPUNTOAGILTIPOCUENTA")
    private Integer idpuntoagiltipocuenta;

    @Basic(optional = false)
    @Column(name = "TIPOCUENTAPUNTOAGIL")
    private Character tipocuentapuntoagil;

    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "ABRTIPOCUENTA")
    private Character abrtipocuenta;

    @Basic(optional = false)
    @Column(name = "REGVIGENTE")
    private Character regvigente;

    @Basic(optional = false)
    @Column(name = "FECHAACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaactualizacion;

    public Puntoagiltipocuenta() {
    }

    public Puntoagiltipocuenta(Integer idpuntoagiltipocuenta) {
        this.idpuntoagiltipocuenta = idpuntoagiltipocuenta;
    }

    public Puntoagiltipocuenta(Integer idpuntoagiltipocuenta, Character tipocuentapuntoagil, String descripcion,
                               Character abrtipocuenta, Character regvigente, Date fechaactualizacion) {
        this.idpuntoagiltipocuenta = idpuntoagiltipocuenta;
        this.tipocuentapuntoagil = tipocuentapuntoagil;
        this.descripcion = descripcion;
        this.abrtipocuenta = abrtipocuenta;
        this.regvigente = regvigente;
        this.fechaactualizacion = fechaactualizacion;
    }

    public Integer getIdpuntoagiltipocuenta() {
        return idpuntoagiltipocuenta;
    }

    public void setIdpuntoagiltipocuenta(Integer idpuntoagiltipocuenta) {
        this.idpuntoagiltipocuenta = idpuntoagiltipocuenta;
    }

    public Character getTipocuentapuntoagil() {
        return tipocuentapuntoagil;
    }

    public void setTipocuentapuntoagil(Character tipocuentapuntoagil) {
        this.tipocuentapuntoagil = tipocuentapuntoagil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Character getAbrtipocuenta() {
        return abrtipocuenta;
    }

    public void setAbrtipocuenta(Character abrtipocuenta) {
        this.abrtipocuenta = abrtipocuenta;
    }

    public Character getRegvigente() {
        return regvigente;
    }

    public void setRegvigente(Character regvigente) {
        this.regvigente = regvigente;
    }

    public Date getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(Date fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpuntoagiltipocuenta != null ? idpuntoagiltipocuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puntoagiltipocuenta)) {
            return false;
        }
        Puntoagiltipocuenta other = (Puntoagiltipocuenta) object;
        if ((this.idpuntoagiltipocuenta == null && other.idpuntoagiltipocuenta != null)
                || (this.idpuntoagiltipocuenta != null
                        && !this.idpuntoagiltipocuenta.equals(other.idpuntoagiltipocuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Puntoagiltipocuenta[ idpuntoagiltipocuenta=" + idpuntoagiltipocuenta
                + " ]";
    }

}
