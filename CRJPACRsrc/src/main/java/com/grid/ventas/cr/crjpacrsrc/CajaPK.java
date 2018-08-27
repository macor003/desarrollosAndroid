/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author eve0017909
 */
@Embeddable
public class CajaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMTIENDA")
    private short numtienda;

    @Basic(optional = false)
    @Column(name = "NUMCAJA")
    private short numcaja;

    public CajaPK() {
    }

    public CajaPK(short numtienda, short numcaja) {
        this.numtienda = numtienda;
        this.numcaja = numcaja;
    }

    public short getNumtienda() {
        return numtienda;
    }

    public void setNumtienda(short numtienda) {
        this.numtienda = numtienda;
    }

    public short getNumcaja() {
        return numcaja;
    }

    public void setNumcaja(short numcaja) {
        this.numcaja = numcaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numtienda;
        hash += (int) numcaja;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CajaPK)) {
            return false;
        }
        CajaPK other = (CajaPK) object;
        if (this.numtienda != other.numtienda) {
            return false;
        }
        if (this.numcaja != other.numcaja) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.CajaPK[ numtienda=" + numtienda + ", numcaja=" + numcaja + " ]";
    }

}
