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
public class OpevtscPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TIENOTSC")
    private short tienotsc;

    @Basic(optional = false)
    @Column(name = "CAJAOTSC")
    private short cajaotsc;

    @Basic(optional = false)
    @Column(name = "TRANOTSC")
    private int tranotsc;

    public OpevtscPK() {
    }

    public OpevtscPK(short tienotsc, short cajaotsc, int tranotsc) {
        this.tienotsc = tienotsc;
        this.cajaotsc = cajaotsc;
        this.tranotsc = tranotsc;
    }

    public short getTienotsc() {
        return tienotsc;
    }

    public void setTienotsc(short tienotsc) {
        this.tienotsc = tienotsc;
    }

    public short getCajaotsc() {
        return cajaotsc;
    }

    public void setCajaotsc(short cajaotsc) {
        this.cajaotsc = cajaotsc;
    }

    public int getTranotsc() {
        return tranotsc;
    }

    public void setTranotsc(int tranotsc) {
        this.tranotsc = tranotsc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tienotsc;
        hash += (int) cajaotsc;
        hash += (int) tranotsc;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpevtscPK)) {
            return false;
        }
        OpevtscPK other = (OpevtscPK) object;
        if (this.tienotsc != other.tienotsc) {
            return false;
        }
        if (this.cajaotsc != other.cajaotsc) {
            return false;
        }
        if (this.tranotsc != other.tranotsc) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.OpevtscPK[ tienotsc=" + tienotsc + ", cajaotsc=" + cajaotsc
                + ", tranotsc=" + tranotsc + " ]";
    }

}
