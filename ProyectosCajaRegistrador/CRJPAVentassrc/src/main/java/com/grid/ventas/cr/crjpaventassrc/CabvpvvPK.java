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
public class CabvpvvPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TIENCPVV")
    private String tiencpvv;

    @Basic(optional = false)
    @Column(name = "NPEDCPVV")
    private int npedcpvv;

    public CabvpvvPK() {
    }

    public CabvpvvPK(String tiencpvv, int npedcpvv) {
        this.tiencpvv = tiencpvv;
        this.npedcpvv = npedcpvv;
    }

    public String getTiencpvv() {
        return tiencpvv;
    }

    public void setTiencpvv(String tiencpvv) {
        this.tiencpvv = tiencpvv;
    }

    public int getNpedcpvv() {
        return npedcpvv;
    }

    public void setNpedcpvv(int npedcpvv) {
        this.npedcpvv = npedcpvv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tiencpvv != null ? tiencpvv.hashCode() : 0);
        hash += (int) npedcpvv;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CabvpvvPK)) {
            return false;
        }
        CabvpvvPK other = (CabvpvvPK) object;
        if ((this.tiencpvv == null && other.tiencpvv != null)
                || (this.tiencpvv != null && !this.tiencpvv.equals(other.tiencpvv))) {
            return false;
        }
        if (this.npedcpvv != other.npedcpvv) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.CabvpvvPK[ tiencpvv=" + tiencpvv + ", npedcpvv=" + npedcpvv
                + " ]";
    }

}
