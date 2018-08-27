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
@Table(name = "PUNTOAGILPLANCREDITOEPA")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Puntoagilplancreditoepa.findAll",
                           query = "SELECT p FROM Puntoagilplancreditoepa p"),
        @NamedQuery(name = "Puntoagilplancreditoepa.findByIdpuntoagilplancreditoepa",
                    query = "SELECT p FROM Puntoagilplancreditoepa p WHERE p.idpuntoagilplancreditoepa = :idpuntoagilplancreditoepa"),
        @NamedQuery(name = "Puntoagilplancreditoepa.findByPlancredito",
                    query = "SELECT p FROM Puntoagilplancreditoepa p WHERE p.plancredito = :plancredito"),
        @NamedQuery(name = "Puntoagilplancreditoepa.findByDescripcion",
                    query = "SELECT p FROM Puntoagilplancreditoepa p WHERE p.descripcion = :descripcion"),
        @NamedQuery(name = "Puntoagilplancreditoepa.findByRequieredatoadicional",
                    query = "SELECT p FROM Puntoagilplancreditoepa p WHERE p.requieredatoadicional = :requieredatoadicional"),
        @NamedQuery(name = "Puntoagilplancreditoepa.findByAbrplancredito",
                    query = "SELECT p FROM Puntoagilplancreditoepa p WHERE p.abrplancredito = :abrplancredito"),
        @NamedQuery(name = "Puntoagilplancreditoepa.findByRegvigente",
                    query = "SELECT p FROM Puntoagilplancreditoepa p WHERE p.regvigente = :regvigente"),
        @NamedQuery(name = "Puntoagilplancreditoepa.findByFechaactualizacion",
                    query = "SELECT p FROM Puntoagilplancreditoepa p WHERE p.fechaactualizacion = :fechaactualizacion")})
public class Puntoagilplancreditoepa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "IDPUNTOAGILPLANCREDITOEPA")
    private Integer idpuntoagilplancreditoepa;

    @Basic(optional = false)
    @Column(name = "PLANCREDITO")
    private String plancredito;

    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "REQUIEREDATOADICIONAL")
    private Character requieredatoadicional;

    @Basic(optional = false)
    @Column(name = "ABRPLANCREDITO")
    private Character abrplancredito;

    @Basic(optional = false)
    @Column(name = "REGVIGENTE")
    private Character regvigente;

    @Basic(optional = false)
    @Column(name = "FECHAACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaactualizacion;

    public Puntoagilplancreditoepa() {
    }

    public Puntoagilplancreditoepa(Integer idpuntoagilplancreditoepa) {
        this.idpuntoagilplancreditoepa = idpuntoagilplancreditoepa;
    }

    public Puntoagilplancreditoepa(Integer idpuntoagilplancreditoepa, String plancredito, String descripcion,
                                   Character requieredatoadicional, Character abrplancredito, Character regvigente,
                                   Date fechaactualizacion) {
        this.idpuntoagilplancreditoepa = idpuntoagilplancreditoepa;
        this.plancredito = plancredito;
        this.descripcion = descripcion;
        this.requieredatoadicional = requieredatoadicional;
        this.abrplancredito = abrplancredito;
        this.regvigente = regvigente;
        this.fechaactualizacion = fechaactualizacion;
    }

    public Integer getIdpuntoagilplancreditoepa() {
        return idpuntoagilplancreditoepa;
    }

    public void setIdpuntoagilplancreditoepa(Integer idpuntoagilplancreditoepa) {
        this.idpuntoagilplancreditoepa = idpuntoagilplancreditoepa;
    }

    public String getPlancredito() {
        return plancredito;
    }

    public void setPlancredito(String plancredito) {
        this.plancredito = plancredito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Character getRequieredatoadicional() {
        return requieredatoadicional;
    }

    public void setRequieredatoadicional(Character requieredatoadicional) {
        this.requieredatoadicional = requieredatoadicional;
    }

    public Character getAbrplancredito() {
        return abrplancredito;
    }

    public void setAbrplancredito(Character abrplancredito) {
        this.abrplancredito = abrplancredito;
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
        hash += (idpuntoagilplancreditoepa != null ? idpuntoagilplancreditoepa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puntoagilplancreditoepa)) {
            return false;
        }
        Puntoagilplancreditoepa other = (Puntoagilplancreditoepa) object;
        if ((this.idpuntoagilplancreditoepa == null && other.idpuntoagilplancreditoepa != null)
                || (this.idpuntoagilplancreditoepa != null
                        && !this.idpuntoagilplancreditoepa.equals(other.idpuntoagilplancreditoepa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Puntoagilplancreditoepa[ idpuntoagilplancreditoepa="
                + idpuntoagilplancreditoepa + " ]";
    }

}
