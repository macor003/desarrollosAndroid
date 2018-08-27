/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;
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
@Table(name = "PUNTOAGILCAJA")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Puntoagilcaja.findAll",
                           query = "SELECT p FROM Puntoagilcaja p"),
        @NamedQuery(name = "Puntoagilcaja.findByNumtienda",
                    query = "SELECT p FROM Puntoagilcaja p WHERE p.puntoagilcajaPK.numtienda = :numtienda"),
        @NamedQuery(name = "Puntoagilcaja.findByNumcaja",
                    query = "SELECT p FROM Puntoagilcaja p WHERE p.puntoagilcajaPK.numcaja = :numcaja"),
        @NamedQuery(name = "Puntoagilcaja.findByFechacierremerchant",
                    query = "SELECT p FROM Puntoagilcaja p WHERE p.fechacierremerchant = :fechacierremerchant")})
public class Puntoagilcaja implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected PuntoagilcajaPK puntoagilcajaPK;

    @Basic(optional = false)
    @Column(name = "FECHACIERREMERCHANT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacierremerchant;

    public Puntoagilcaja() {
    }

    public Puntoagilcaja(PuntoagilcajaPK puntoagilcajaPK) {
        this.puntoagilcajaPK = puntoagilcajaPK;
    }

    public Puntoagilcaja(PuntoagilcajaPK puntoagilcajaPK, Date fechacierremerchant) {
        this.puntoagilcajaPK = puntoagilcajaPK;
        this.fechacierremerchant = fechacierremerchant;
    }

    public Puntoagilcaja(short numtienda, short numcaja) {
        this.puntoagilcajaPK = new PuntoagilcajaPK(numtienda, numcaja);
    }

    public PuntoagilcajaPK getPuntoagilcajaPK() {
        return puntoagilcajaPK;
    }

    public void setPuntoagilcajaPK(PuntoagilcajaPK puntoagilcajaPK) {
        this.puntoagilcajaPK = puntoagilcajaPK;
    }

    public Date getFechacierremerchant() {
        return fechacierremerchant;
    }

    public void setFechacierremerchant(Date fechacierremerchant) {
        this.fechacierremerchant = fechacierremerchant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (puntoagilcajaPK != null ? puntoagilcajaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puntoagilcaja)) {
            return false;
        }
        Puntoagilcaja other = (Puntoagilcaja) object;
        if ((this.puntoagilcajaPK == null && other.puntoagilcajaPK != null)
                || (this.puntoagilcajaPK != null && !this.puntoagilcajaPK.equals(other.puntoagilcajaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Puntoagilcaja[ puntoagilcajaPK=" + puntoagilcajaPK + " ]";
    }

}
