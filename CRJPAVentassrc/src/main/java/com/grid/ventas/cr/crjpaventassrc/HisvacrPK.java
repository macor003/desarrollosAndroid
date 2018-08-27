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
public class HisvacrPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "MESHACR")
    private short meshacr;

    @Basic(optional = false)
    @Column(name = "ANOHACR")
    private short anohacr;

    @Basic(optional = false)
    @Column(name = "NUMACREENCIAHACR")
    private long numacreenciahacr;

    @Basic(optional = false)
    @Column(name = "IDCLIENTEHACR")
    private String idclientehacr;

    public HisvacrPK() {
    }

    public HisvacrPK(short meshacr, short anohacr, long numacreenciahacr, String idclientehacr) {
        this.meshacr = meshacr;
        this.anohacr = anohacr;
        this.numacreenciahacr = numacreenciahacr;
        this.idclientehacr = idclientehacr;
    }

    public short getMeshacr() {
        return meshacr;
    }

    public void setMeshacr(short meshacr) {
        this.meshacr = meshacr;
    }

    public short getAnohacr() {
        return anohacr;
    }

    public void setAnohacr(short anohacr) {
        this.anohacr = anohacr;
    }

    public long getNumacreenciahacr() {
        return numacreenciahacr;
    }

    public void setNumacreenciahacr(long numacreenciahacr) {
        this.numacreenciahacr = numacreenciahacr;
    }

    public String getIdclientehacr() {
        return idclientehacr;
    }

    public void setIdclientehacr(String idclientehacr) {
        this.idclientehacr = idclientehacr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) meshacr;
        hash += (int) anohacr;
        hash += (int) numacreenciahacr;
        hash += (idclientehacr != null ? idclientehacr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HisvacrPK)) {
            return false;
        }
        HisvacrPK other = (HisvacrPK) object;
        if (this.meshacr != other.meshacr) {
            return false;
        }
        if (this.anohacr != other.anohacr) {
            return false;
        }
        if (this.numacreenciahacr != other.numacreenciahacr) {
            return false;
        }
        if ((this.idclientehacr == null && other.idclientehacr != null)
                || (this.idclientehacr != null && !this.idclientehacr.equals(other.idclientehacr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.HisvacrPK[ meshacr=" + meshacr + ", anohacr=" + anohacr
                + ", numacreenciahacr=" + numacreenciahacr + ", idclientehacr=" + idclientehacr + " ]";
    }

}
