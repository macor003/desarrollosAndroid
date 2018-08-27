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
public class HisvdndPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMDHDND")
    private String numdhdnd;

    @Basic(optional = false)
    @Column(name = "TDADHDND")
    private String tdadhdnd;

    @Basic(optional = false)
    @Column(name = "TIPDHDND")
    private short tipdhdnd;

    @Basic(optional = false)
    @Column(name = "COPRHDND")
    private int coprhdnd;

    public HisvdndPK() {
    }

    public HisvdndPK(String numdhdnd, String tdadhdnd, short tipdhdnd, int coprhdnd) {
        this.numdhdnd = numdhdnd;
        this.tdadhdnd = tdadhdnd;
        this.tipdhdnd = tipdhdnd;
        this.coprhdnd = coprhdnd;
    }

    public String getNumdhdnd() {
        return numdhdnd;
    }

    public void setNumdhdnd(String numdhdnd) {
        this.numdhdnd = numdhdnd;
    }

    public String getTdadhdnd() {
        return tdadhdnd;
    }

    public void setTdadhdnd(String tdadhdnd) {
        this.tdadhdnd = tdadhdnd;
    }

    public short getTipdhdnd() {
        return tipdhdnd;
    }

    public void setTipdhdnd(short tipdhdnd) {
        this.tipdhdnd = tipdhdnd;
    }

    public int getCoprhdnd() {
        return coprhdnd;
    }

    public void setCoprhdnd(int coprhdnd) {
        this.coprhdnd = coprhdnd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numdhdnd != null ? numdhdnd.hashCode() : 0);
        hash += (tdadhdnd != null ? tdadhdnd.hashCode() : 0);
        hash += (int) tipdhdnd;
        hash += (int) coprhdnd;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HisvdndPK)) {
            return false;
        }
        HisvdndPK other = (HisvdndPK) object;
        if ((this.numdhdnd == null && other.numdhdnd != null)
                || (this.numdhdnd != null && !this.numdhdnd.equals(other.numdhdnd))) {
            return false;
        }
        if ((this.tdadhdnd == null && other.tdadhdnd != null)
                || (this.tdadhdnd != null && !this.tdadhdnd.equals(other.tdadhdnd))) {
            return false;
        }
        if (this.tipdhdnd != other.tipdhdnd) {
            return false;
        }
        if (this.coprhdnd != other.coprhdnd) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.HisvdndPK[ numdhdnd=" + numdhdnd + ", tdadhdnd=" + tdadhdnd
                + ", tipdhdnd=" + tipdhdnd + ", coprhdnd=" + coprhdnd + " ]";
    }

}
