/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eve0017909
 */
@Entity
@Table(name = "ESTADODECAJA")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Estadodecaja.findAll",
                           query = "SELECT e FROM Estadodecaja e"),
        @NamedQuery(name = "Estadodecaja.findByIdestado",
                    query = "SELECT e FROM Estadodecaja e WHERE e.idestado = :idestado"),
        @NamedQuery(name = "Estadodecaja.findByDescripcion",
                    query = "SELECT e FROM Estadodecaja e WHERE e.descripcion = :descripcion")})
public class Estadodecaja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "IDESTADO")
    private String idestado;

    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL,
               mappedBy = "idestadocaja",
               fetch = FetchType.LAZY)
    private List<Caja> cajaList;

    @OneToOne(cascade = CascadeType.ALL,
              mappedBy = "estadodecaja",
              fetch = FetchType.LAZY)
    private Puntoagilprocesoestadocaja puntoagilprocesoestadocaja;

    public Estadodecaja() {
    }

    public Estadodecaja(String idestado) {
        this.idestado = idestado;
    }

    public Estadodecaja(String idestado, String descripcion) {
        this.idestado = idestado;
        this.descripcion = descripcion;
    }

    public String getIdestado() {
        return idestado;
    }

    public void setIdestado(String idestado) {
        this.idestado = idestado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Caja> getCajaList() {
        return cajaList;
    }

    public void setCajaList(List<Caja> cajaList) {
        this.cajaList = cajaList;
    }

    public Puntoagilprocesoestadocaja getPuntoagilprocesoestadocaja() {
        return puntoagilprocesoestadocaja;
    }

    public void setPuntoagilprocesoestadocaja(Puntoagilprocesoestadocaja puntoagilprocesoestadocaja) {
        this.puntoagilprocesoestadocaja = puntoagilprocesoestadocaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestado != null ? idestado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadodecaja)) {
            return false;
        }
        Estadodecaja other = (Estadodecaja) object;
        if ((this.idestado == null && other.idestado != null)
                || (this.idestado != null && !this.idestado.equals(other.idestado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Estadodecaja[ idestado=" + idestado + " ]";
    }

}
