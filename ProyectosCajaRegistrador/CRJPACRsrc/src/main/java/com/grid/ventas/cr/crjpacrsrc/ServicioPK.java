/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author eve0017909
 */
@Embeddable
public class ServicioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMTIENDA")
    private short numtienda;

    @Basic(optional = false)
    @Column(name = "CODTIPOSERVICIO")
    private String codtiposervicio;

    @Basic(optional = false)
    @Column(name = "NUMSERVICIO")
    private int numservicio;

    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public ServicioPK() {
    }

    public ServicioPK(short numtienda, String codtiposervicio, int numservicio, Date fecha) {
        this.numtienda = numtienda;
        this.codtiposervicio = codtiposervicio;
        this.numservicio = numservicio;
        this.fecha = fecha;
    }

    public short getNumtienda() {
        return numtienda;
    }

    public void setNumtienda(short numtienda) {
        this.numtienda = numtienda;
    }

    public String getCodtiposervicio() {
        return codtiposervicio;
    }

    public void setCodtiposervicio(String codtiposervicio) {
        this.codtiposervicio = codtiposervicio;
    }

    public int getNumservicio() {
        return numservicio;
    }

    public void setNumservicio(int numservicio) {
        this.numservicio = numservicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numtienda;
        hash += (codtiposervicio != null ? codtiposervicio.hashCode() : 0);
        hash += (int) numservicio;
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioPK)) {
            return false;
        }
        ServicioPK other = (ServicioPK) object;
        if (this.numtienda != other.numtienda) {
            return false;
        }
        if ((this.codtiposervicio == null && other.codtiposervicio != null)
                || (this.codtiposervicio != null && !this.codtiposervicio.equals(other.codtiposervicio))) {
            return false;
        }
        if (this.numservicio != other.numservicio) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null)
                || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.ServicioPK[ numtienda=" + numtienda + ", codtiposervicio="
                + codtiposervicio + ", numservicio=" + numservicio + ", fecha=" + fecha + " ]";
    }

}
