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
public class TransaccionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMTIENDA")
    private short numtienda;

    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Basic(optional = false)
    @Column(name = "NUMCAJAINICIA")
    private short numcajainicia;

    @Basic(optional = false)
    @Column(name = "NUMREGCAJAINICIA")
    private int numregcajainicia;

    public TransaccionPK() {
    }

    public TransaccionPK(short numtienda, Date fecha, short numcajainicia, int numregcajainicia) {
        this.numtienda = numtienda;
        this.fecha = fecha;
        this.numcajainicia = numcajainicia;
        this.numregcajainicia = numregcajainicia;
    }

    public short getNumtienda() {
        return numtienda;
    }

    public void setNumtienda(short numtienda) {
        this.numtienda = numtienda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public short getNumcajainicia() {
        return numcajainicia;
    }

    public void setNumcajainicia(short numcajainicia) {
        this.numcajainicia = numcajainicia;
    }

    public int getNumregcajainicia() {
        return numregcajainicia;
    }

    public void setNumregcajainicia(int numregcajainicia) {
        this.numregcajainicia = numregcajainicia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numtienda;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (int) numcajainicia;
        hash += (int) numregcajainicia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaccionPK)) {
            return false;
        }
        TransaccionPK other = (TransaccionPK) object;
        if (this.numtienda != other.numtienda) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null)
                || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if (this.numcajainicia != other.numcajainicia) {
            return false;
        }
        if (this.numregcajainicia != other.numregcajainicia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.TransaccionPK[ numtienda=" + numtienda + ", fecha=" + fecha
                + ", numcajainicia=" + numcajainicia + ", numregcajainicia=" + numregcajainicia + " ]";
    }

}
