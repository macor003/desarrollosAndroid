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
public class MaevclaacrPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMACR")
    private int numacr;

    @Basic(optional = false)
    @Column(name = "CLIENTACR")
    private String clientacr;

    public MaevclaacrPK() {
    }

    public MaevclaacrPK(int numacr, String clientacr) {
        this.numacr = numacr;
        this.clientacr = clientacr;
    }

    public int getNumacr() {
        return numacr;
    }

    public void setNumacr(int numacr) {
        this.numacr = numacr;
    }

    public String getClientacr() {
        return clientacr;
    }

    public void setClientacr(String clientacr) {
        this.clientacr = clientacr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numacr;
        hash += (clientacr != null ? clientacr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaevclaacrPK)) {
            return false;
        }
        MaevclaacrPK other = (MaevclaacrPK) object;
        if (this.numacr != other.numacr) {
            return false;
        }
        if ((this.clientacr == null && other.clientacr != null)
                || (this.clientacr != null && !this.clientacr.equals(other.clientacr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.MaevclaacrPK[ numacr=" + numacr + ", clientacr=" + clientacr
                + " ]";
    }

}
