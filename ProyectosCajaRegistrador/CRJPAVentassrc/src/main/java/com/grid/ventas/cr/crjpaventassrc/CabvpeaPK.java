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
public class CabvpeaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TIENCPEA")
    private String tiencpea;

    @Basic(optional = false)
    @Column(name = "NUMPCPEA")
    private int numpcpea;

    @Basic(optional = false)
    @Column(name = "TSERCPEA")
    private String tsercpea;

    public CabvpeaPK() {
    }

    public CabvpeaPK(String tiencpea, int numpcpea, String tsercpea) {
        this.tiencpea = tiencpea;
        this.numpcpea = numpcpea;
        this.tsercpea = tsercpea;
    }

    public String getTiencpea() {
        return tiencpea;
    }

    public void setTiencpea(String tiencpea) {
        this.tiencpea = tiencpea;
    }

    public int getNumpcpea() {
        return numpcpea;
    }

    public void setNumpcpea(int numpcpea) {
        this.numpcpea = numpcpea;
    }

    public String getTsercpea() {
        return tsercpea;
    }

    public void setTsercpea(String tsercpea) {
        this.tsercpea = tsercpea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tiencpea != null ? tiencpea.hashCode() : 0);
        hash += (int) numpcpea;
        hash += (tsercpea != null ? tsercpea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CabvpeaPK)) {
            return false;
        }
        CabvpeaPK other = (CabvpeaPK) object;
        if ((this.tiencpea == null && other.tiencpea != null)
                || (this.tiencpea != null && !this.tiencpea.equals(other.tiencpea))) {
            return false;
        }
        if (this.numpcpea != other.numpcpea) {
            return false;
        }
        if ((this.tsercpea == null && other.tsercpea != null)
                || (this.tsercpea != null && !this.tsercpea.equals(other.tsercpea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.CabvpeaPK[ tiencpea=" + tiencpea + ", numpcpea=" + numpcpea
                + ", tsercpea=" + tsercpea + " ]";
    }

}
