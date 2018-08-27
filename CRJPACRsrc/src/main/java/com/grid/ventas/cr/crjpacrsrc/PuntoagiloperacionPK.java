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
public class PuntoagiloperacionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMTIENDA")
    private short numtienda;

    @Basic(optional = false)
    @Column(name = "NUMCAJA")
    private short numcaja;

    @Basic(optional = false)
    @Column(name = "VTID")
    private String vtid;

    @Basic(optional = false)
    @Column(name = "NUMSEQ")
    private int numseq;

    public PuntoagiloperacionPK() {
    }

    public PuntoagiloperacionPK(short numtienda, short numcaja, String vtid, int numseq) {
        this.numtienda = numtienda;
        this.numcaja = numcaja;
        this.vtid = vtid;
        this.numseq = numseq;
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

    public String getVtid() {
        return vtid;
    }

    public void setVtid(String vtid) {
        this.vtid = vtid;
    }

    public int getNumseq() {
        return numseq;
    }

    public void setNumseq(int numseq) {
        this.numseq = numseq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numtienda;
        hash += (int) numcaja;
        hash += (vtid != null ? vtid.hashCode() : 0);
        hash += (int) numseq;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuntoagiloperacionPK)) {
            return false;
        }
        PuntoagiloperacionPK other = (PuntoagiloperacionPK) object;
        if (this.numtienda != other.numtienda) {
            return false;
        }
        if (this.numcaja != other.numcaja) {
            return false;
        }
        if ((this.vtid == null && other.vtid != null) || (this.vtid != null && !this.vtid.equals(other.vtid))) {
            return false;
        }
        if (this.numseq != other.numseq) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.PuntoagiloperacionPK[ numtienda=" + numtienda + ", numcaja="
                + numcaja + ", vtid=" + vtid + ", numseq=" + numseq + " ]";
    }

}
