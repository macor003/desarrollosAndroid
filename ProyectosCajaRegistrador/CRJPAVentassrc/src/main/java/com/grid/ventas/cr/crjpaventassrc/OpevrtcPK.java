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
public class OpevrtcPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TDAVRCT")
    private short tdavrct;

    @Basic(optional = false)
    @Column(name = "CAJVRCT")
    private short cajvrct;

    @Basic(optional = false)
    @Column(name = "TRSVRCT")
    private int trsvrct;

    public OpevrtcPK() {
    }

    public OpevrtcPK(short tdavrct, short cajvrct, int trsvrct) {
        this.tdavrct = tdavrct;
        this.cajvrct = cajvrct;
        this.trsvrct = trsvrct;
    }

    public short getTdavrct() {
        return tdavrct;
    }

    public void setTdavrct(short tdavrct) {
        this.tdavrct = tdavrct;
    }

    public short getCajvrct() {
        return cajvrct;
    }

    public void setCajvrct(short cajvrct) {
        this.cajvrct = cajvrct;
    }

    public int getTrsvrct() {
        return trsvrct;
    }

    public void setTrsvrct(int trsvrct) {
        this.trsvrct = trsvrct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tdavrct;
        hash += (int) cajvrct;
        hash += (int) trsvrct;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpevrtcPK)) {
            return false;
        }
        OpevrtcPK other = (OpevrtcPK) object;
        if (this.tdavrct != other.tdavrct) {
            return false;
        }
        if (this.cajvrct != other.cajvrct) {
            return false;
        }
        if (this.trsvrct != other.trsvrct) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.OpevrtcPK[ tdavrct=" + tdavrct + ", cajvrct=" + cajvrct
                + ", trsvrct=" + trsvrct + " ]";
    }

}
