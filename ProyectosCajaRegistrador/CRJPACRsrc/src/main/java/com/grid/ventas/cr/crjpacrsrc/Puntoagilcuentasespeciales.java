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
@Table(name = "PUNTOAGILCUENTASESPECIALES")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Puntoagilcuentasespeciales.findAll",
                           query = "SELECT p FROM Puntoagilcuentasespeciales p"),
        @NamedQuery(name = "Puntoagilcuentasespeciales.findByIdpuntoagilcuentasespeciales",
                    query = "SELECT p FROM Puntoagilcuentasespeciales p WHERE p.idpuntoagilcuentasespeciales = :idpuntoagilcuentasespeciales"),
        @NamedQuery(name = "Puntoagilcuentasespeciales.findByCuentaespecial",
                    query = "SELECT p FROM Puntoagilcuentasespeciales p WHERE p.cuentaespecial = :cuentaespecial"),
        @NamedQuery(name = "Puntoagilcuentasespeciales.findByDescripcion",
                    query = "SELECT p FROM Puntoagilcuentasespeciales p WHERE p.descripcion = :descripcion"),
        @NamedQuery(name = "Puntoagilcuentasespeciales.findByTipo",
                    query = "SELECT p FROM Puntoagilcuentasespeciales p WHERE p.tipo = :tipo"),
        @NamedQuery(name = "Puntoagilcuentasespeciales.findByRegvigente",
                    query = "SELECT p FROM Puntoagilcuentasespeciales p WHERE p.regvigente = :regvigente"),
        @NamedQuery(name = "Puntoagilcuentasespeciales.findByFechaactualizacion",
                    query = "SELECT p FROM Puntoagilcuentasespeciales p WHERE p.fechaactualizacion = :fechaactualizacion"),
        @NamedQuery(name = "Puntoagilcuentasespeciales.findByCodbanco",
                    query = "SELECT p FROM Puntoagilcuentasespeciales p WHERE p.codbanco = :codbanco")})
public class Puntoagilcuentasespeciales implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "IDPUNTOAGILCUENTASESPECIALES")
    private Integer idpuntoagilcuentasespeciales;

    @Basic(optional = false)
    @Column(name = "CUENTAESPECIAL")
    private String cuentaespecial;

    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "TIPO")
    private Character tipo;

    @Basic(optional = false)
    @Column(name = "REGVIGENTE")
    private Character regvigente;

    @Basic(optional = false)
    @Column(name = "FECHAACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaactualizacion;

    @Column(name = "CODBANCO")
    private String codbanco;

    public Puntoagilcuentasespeciales() {
    }

    public Puntoagilcuentasespeciales(Integer idpuntoagilcuentasespeciales) {
        this.idpuntoagilcuentasespeciales = idpuntoagilcuentasespeciales;
    }

    public Puntoagilcuentasespeciales(Integer idpuntoagilcuentasespeciales, String cuentaespecial,
                                      String descripcion, Character tipo, Character regvigente,
                                      Date fechaactualizacion) {
        this.idpuntoagilcuentasespeciales = idpuntoagilcuentasespeciales;
        this.cuentaespecial = cuentaespecial;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.regvigente = regvigente;
        this.fechaactualizacion = fechaactualizacion;
    }

    public Integer getIdpuntoagilcuentasespeciales() {
        return idpuntoagilcuentasespeciales;
    }

    public void setIdpuntoagilcuentasespeciales(Integer idpuntoagilcuentasespeciales) {
        this.idpuntoagilcuentasespeciales = idpuntoagilcuentasespeciales;
    }

    public String getCuentaespecial() {
        return cuentaespecial;
    }

    public void setCuentaespecial(String cuentaespecial) {
        this.cuentaespecial = cuentaespecial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
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

    public String getCodbanco() {
        return codbanco;
    }

    public void setCodbanco(String codbanco) {
        this.codbanco = codbanco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpuntoagilcuentasespeciales != null ? idpuntoagilcuentasespeciales.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puntoagilcuentasespeciales)) {
            return false;
        }
        Puntoagilcuentasespeciales other = (Puntoagilcuentasespeciales) object;
        if ((this.idpuntoagilcuentasespeciales == null && other.idpuntoagilcuentasespeciales != null)
                || (this.idpuntoagilcuentasespeciales != null
                        && !this.idpuntoagilcuentasespeciales.equals(other.idpuntoagilcuentasespeciales))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Puntoagilcuentasespeciales[ idpuntoagilcuentasespeciales="
                + idpuntoagilcuentasespeciales + " ]";
    }

}
