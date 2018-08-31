/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author eve0017909
 */
@Embeddable
public class DetvpvvPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TIENDPVV")
    private String tiendpvv;

    @Basic(optional = false)
    @Column(name = "NPEDDPVV")
    private int npeddpvv;

    @Basic(optional = false)
    @Column(name = "COARDPVV")
    private int coardpvv;

    public DetvpvvPK() {
    }

    public DetvpvvPK(String tiendpvv, int npeddpvv, int coardpvv) {
        this.tiendpvv = tiendpvv;
        this.npeddpvv = npeddpvv;
        this.coardpvv = coardpvv;
    }

    public String getTiendpvv() {
        return tiendpvv;
    }

    public void setTiendpvv(String tiendpvv) {
        this.tiendpvv = tiendpvv;
    }

    public int getNpeddpvv() {
        return npeddpvv;
    }

    public void setNpeddpvv(int npeddpvv) {
        this.npeddpvv = npeddpvv;
    }

    public int getCoardpvv() {
        return coardpvv;
    }

    public void setCoardpvv(int coardpvv) {
        this.coardpvv = coardpvv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tiendpvv != null ? tiendpvv.hashCode() : 0);
        hash += (int) npeddpvv;
        hash += (int) coardpvv;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetvpvvPK)) {
            return false;
        }
        DetvpvvPK other = (DetvpvvPK) object;
        if ((this.tiendpvv == null && other.tiendpvv != null)
                || (this.tiendpvv != null && !this.tiendpvv.equals(other.tiendpvv))) {
            return false;
        }
        if (this.npeddpvv != other.npeddpvv) {
            return false;
        }
        if (this.coardpvv != other.coardpvv) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.DetvpvvPK[ tiendpvv=" + tiendpvv + ", npeddpvv=" + npeddpvv
                + ", coardpvv=" + coardpvv + " ]";
    }

}
