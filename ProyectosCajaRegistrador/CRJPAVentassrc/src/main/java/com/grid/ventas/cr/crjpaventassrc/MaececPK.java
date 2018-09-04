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
public class MaececPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMTIE")
    private short numtie;

    @Basic(optional = false)
    @Column(name = "CAJA")
    private short caja;

    @Basic(optional = false)
    @Column(name = "TRANSA")
    private int transa;

    public MaececPK() {
    }

    public MaececPK(short numtie, short caja, int transa) {
        this.numtie = numtie;
        this.caja = caja;
        this.transa = transa;
    }

    public short getNumtie() {
        return numtie;
    }

    public void setNumtie(short numtie) {
        this.numtie = numtie;
    }

    public short getCaja() {
        return caja;
    }

    public void setCaja(short caja) {
        this.caja = caja;
    }

    public int getTransa() {
        return transa;
    }

    public void setTransa(int transa) {
        this.transa = transa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numtie;
        hash += (int) caja;
        hash += (int) transa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaececPK)) {
            return false;
        }
        MaececPK other = (MaececPK) object;
        if (this.numtie != other.numtie) {
            return false;
        }
        if (this.caja != other.caja) {
            return false;
        }
        if (this.transa != other.transa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.MaececPK[ numtie=" + numtie + ", caja=" + caja + ", transa="
                + transa + " ]";
    }

}
