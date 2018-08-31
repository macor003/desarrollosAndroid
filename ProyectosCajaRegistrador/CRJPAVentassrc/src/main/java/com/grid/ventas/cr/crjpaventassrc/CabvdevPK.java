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
public class CabvdevPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "CORRCDEV")
    private int corrcdev;

    @Basic(optional = false)
    @Column(name = "TRANCDEV")
    private int trancdev;

    @Basic(optional = false)
    @Column(name = "AFILCDEV")
    private String afilcdev;

    @Basic(optional = false)
    @Column(name = "CAJACDEV")
    private short cajacdev;

    @Basic(optional = false)
    @Column(name = "TIEVCDEV")
    private String tievcdev;

    public CabvdevPK() {
    }

    public CabvdevPK(int corrcdev, int trancdev, String afilcdev, short cajacdev, String tievcdev) {
        this.corrcdev = corrcdev;
        this.trancdev = trancdev;
        this.afilcdev = afilcdev;
        this.cajacdev = cajacdev;
        this.tievcdev = tievcdev;
    }

    public int getCorrcdev() {
        return corrcdev;
    }

    public void setCorrcdev(int corrcdev) {
        this.corrcdev = corrcdev;
    }

    public int getTrancdev() {
        return trancdev;
    }

    public void setTrancdev(int trancdev) {
        this.trancdev = trancdev;
    }

    public String getAfilcdev() {
        return afilcdev;
    }

    public void setAfilcdev(String afilcdev) {
        this.afilcdev = afilcdev;
    }

    public short getCajacdev() {
        return cajacdev;
    }

    public void setCajacdev(short cajacdev) {
        this.cajacdev = cajacdev;
    }

    public String getTievcdev() {
        return tievcdev;
    }

    public void setTievcdev(String tievcdev) {
        this.tievcdev = tievcdev;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) corrcdev;
        hash += (int) trancdev;
        hash += (afilcdev != null ? afilcdev.hashCode() : 0);
        hash += (int) cajacdev;
        hash += (tievcdev != null ? tievcdev.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CabvdevPK)) {
            return false;
        }
        CabvdevPK other = (CabvdevPK) object;
        if (this.corrcdev != other.corrcdev) {
            return false;
        }
        if (this.trancdev != other.trancdev) {
            return false;
        }
        if ((this.afilcdev == null && other.afilcdev != null)
                || (this.afilcdev != null && !this.afilcdev.equals(other.afilcdev))) {
            return false;
        }
        if (this.cajacdev != other.cajacdev) {
            return false;
        }
        if ((this.tievcdev == null && other.tievcdev != null)
                || (this.tievcdev != null && !this.tievcdev.equals(other.tievcdev))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.CabvdevPK[ corrcdev=" + corrcdev + ", trancdev=" + trancdev
                + ", afilcdev=" + afilcdev + ", cajacdev=" + cajacdev + ", tievcdev=" + tievcdev + " ]";
    }

}
