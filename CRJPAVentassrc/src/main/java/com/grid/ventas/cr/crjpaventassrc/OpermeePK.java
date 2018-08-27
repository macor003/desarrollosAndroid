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
public class OpermeePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TIENOMEE")
    private String tienomee;

    @Basic(optional = false)
    @Column(name = "NRECOMEE")
    private int nrecomee;

    public OpermeePK() {
    }

    public OpermeePK(String tienomee, int nrecomee) {
        this.tienomee = tienomee;
        this.nrecomee = nrecomee;
    }

    public String getTienomee() {
        return tienomee;
    }

    public void setTienomee(String tienomee) {
        this.tienomee = tienomee;
    }

    public int getNrecomee() {
        return nrecomee;
    }

    public void setNrecomee(int nrecomee) {
        this.nrecomee = nrecomee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tienomee != null ? tienomee.hashCode() : 0);
        hash += (int) nrecomee;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpermeePK)) {
            return false;
        }
        OpermeePK other = (OpermeePK) object;
        if ((this.tienomee == null && other.tienomee != null)
                || (this.tienomee != null && !this.tienomee.equals(other.tienomee))) {
            return false;
        }
        if (this.nrecomee != other.nrecomee) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.OpermeePK[ tienomee=" + tienomee + ", nrecomee=" + nrecomee
                + " ]";
    }

}
