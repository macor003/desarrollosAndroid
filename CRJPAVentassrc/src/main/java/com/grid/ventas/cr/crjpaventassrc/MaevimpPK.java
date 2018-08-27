/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author eve0017909
 */
@Embeddable
public class MaevimpPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TIENMIMP")
    private String tienmimp;

    @Basic(optional = false)
    @Column(name = "FECIMIMP")
    @Temporal(TemporalType.DATE)
    private Date fecimimp;

    public MaevimpPK() {
    }

    public MaevimpPK(String tienmimp, Date fecimimp) {
        this.tienmimp = tienmimp;
        this.fecimimp = fecimimp;
    }

    public String getTienmimp() {
        return tienmimp;
    }

    public void setTienmimp(String tienmimp) {
        this.tienmimp = tienmimp;
    }

    public Date getFecimimp() {
        return fecimimp;
    }

    public void setFecimimp(Date fecimimp) {
        this.fecimimp = fecimimp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tienmimp != null ? tienmimp.hashCode() : 0);
        hash += (fecimimp != null ? fecimimp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaevimpPK)) {
            return false;
        }
        MaevimpPK other = (MaevimpPK) object;
        if ((this.tienmimp == null && other.tienmimp != null)
                || (this.tienmimp != null && !this.tienmimp.equals(other.tienmimp))) {
            return false;
        }
        if ((this.fecimimp == null && other.fecimimp != null)
                || (this.fecimimp != null && !this.fecimimp.equals(other.fecimimp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.MaevimpPK[ tienmimp=" + tienmimp + ", fecimimp=" + fecimimp
                + " ]";
    }

}
