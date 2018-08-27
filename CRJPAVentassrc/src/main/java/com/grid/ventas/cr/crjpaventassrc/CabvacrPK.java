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
public class CabvacrPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMACACR")
    private long numacacr;

    @Basic(optional = false)
    @Column(name = "CLIENTACACR")
    private String clientacacr;

    public CabvacrPK() {
    }

    public CabvacrPK(long numacacr, String clientacacr) {
        this.numacacr = numacacr;
        this.clientacacr = clientacacr;
    }

    public long getNumacacr() {
        return numacacr;
    }

    public void setNumacacr(long numacacr) {
        this.numacacr = numacacr;
    }

    public String getClientacacr() {
        return clientacacr;
    }

    public void setClientacacr(String clientacacr) {
        this.clientacacr = clientacacr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numacacr;
        hash += (clientacacr != null ? clientacacr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CabvacrPK)) {
            return false;
        }
        CabvacrPK other = (CabvacrPK) object;
        if (this.numacacr != other.numacacr) {
            return false;
        }
        if ((this.clientacacr == null && other.clientacacr != null)
                || (this.clientacacr != null && !this.clientacacr.equals(other.clientacacr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.CabvacrPK[ numacacr=" + numacacr + ", clientacacr=" + clientacacr
                + " ]";
    }

}
