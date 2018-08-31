/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eve0017909
 */
@Entity
@Table(name = "PUNTOAGILPROCESOESTADOCAJA")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Puntoagilprocesoestadocaja.findAll",
                           query = "SELECT p FROM Puntoagilprocesoestadocaja p"),
        @NamedQuery(name = "Puntoagilprocesoestadocaja.findByIdestado",
                    query = "SELECT p FROM Puntoagilprocesoestadocaja p WHERE p.idestado = :idestado"),
        @NamedQuery(name = "Puntoagilprocesoestadocaja.findByTipoproceso",
                    query = "SELECT p FROM Puntoagilprocesoestadocaja p WHERE p.tipoproceso = :tipoproceso")})
public class Puntoagilprocesoestadocaja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "IDESTADO")
    private String idestado;

    @Basic(optional = false)
    @Column(name = "TIPOPROCESO")
    private short tipoproceso;

    @JoinColumn(name = "IDESTADO",
                referencedColumnName = "IDESTADO",
                insertable = false,
                updatable = false)
    @OneToOne(optional = false,
              fetch = FetchType.LAZY)
    private Estadodecaja estadodecaja;

    public Puntoagilprocesoestadocaja() {
    }

    public Puntoagilprocesoestadocaja(String idestado) {
        this.idestado = idestado;
    }

    public Puntoagilprocesoestadocaja(String idestado, short tipoproceso) {
        this.idestado = idestado;
        this.tipoproceso = tipoproceso;
    }

    public String getIdestado() {
        return idestado;
    }

    public void setIdestado(String idestado) {
        this.idestado = idestado;
    }

    public short getTipoproceso() {
        return tipoproceso;
    }

    public void setTipoproceso(short tipoproceso) {
        this.tipoproceso = tipoproceso;
    }

    public Estadodecaja getEstadodecaja() {
        return estadodecaja;
    }

    public void setEstadodecaja(Estadodecaja estadodecaja) {
        this.estadodecaja = estadodecaja;
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
        if (!(object instanceof Puntoagilprocesoestadocaja)) {
            return false;
        }
        Puntoagilprocesoestadocaja other = (Puntoagilprocesoestadocaja) object;
        if ((this.idestado == null && other.idestado != null)
                || (this.idestado != null && !this.idestado.equals(other.idestado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Puntoagilprocesoestadocaja[ idestado=" + idestado + " ]";
    }

}
