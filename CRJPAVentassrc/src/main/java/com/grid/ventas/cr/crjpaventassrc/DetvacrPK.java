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
public class DetvacrPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TDAADACR")
    private short tdaadacr;

    @Basic(optional = false)
    @Column(name = "NUMADACR")
    private long numadacr;

    @Basic(optional = false)
    @Column(name = "FECADACR")
    @Temporal(TemporalType.DATE)
    private Date fecadacr;

    @Basic(optional = false)
    @Column(name = "CLIENTADACR")
    private String clientadacr;

    @Basic(optional = false)
    @Column(name = "NUMOPVDACR")
    private long numopvdacr;

    @Basic(optional = false)
    @Column(name = "CRTVOVACR")
    private long crtvovacr;

    public DetvacrPK() {
    }

    public DetvacrPK(short tdaadacr, long numadacr, Date fecadacr, String clientadacr, long numopvdacr,
                     long crtvovacr) {
        this.tdaadacr = tdaadacr;
        this.numadacr = numadacr;
        this.fecadacr = fecadacr;
        this.clientadacr = clientadacr;
        this.numopvdacr = numopvdacr;
        this.crtvovacr = crtvovacr;
    }

    public short getTdaadacr() {
        return tdaadacr;
    }

    public void setTdaadacr(short tdaadacr) {
        this.tdaadacr = tdaadacr;
    }

    public long getNumadacr() {
        return numadacr;
    }

    public void setNumadacr(long numadacr) {
        this.numadacr = numadacr;
    }

    public Date getFecadacr() {
        return fecadacr;
    }

    public void setFecadacr(Date fecadacr) {
        this.fecadacr = fecadacr;
    }

    public String getClientadacr() {
        return clientadacr;
    }

    public void setClientadacr(String clientadacr) {
        this.clientadacr = clientadacr;
    }

    public long getNumopvdacr() {
        return numopvdacr;
    }

    public void setNumopvdacr(long numopvdacr) {
        this.numopvdacr = numopvdacr;
    }

    public long getCrtvovacr() {
        return crtvovacr;
    }

    public void setCrtvovacr(long crtvovacr) {
        this.crtvovacr = crtvovacr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tdaadacr;
        hash += (int) numadacr;
        hash += (fecadacr != null ? fecadacr.hashCode() : 0);
        hash += (clientadacr != null ? clientadacr.hashCode() : 0);
        hash += (int) numopvdacr;
        hash += (int) crtvovacr;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetvacrPK)) {
            return false;
        }
        DetvacrPK other = (DetvacrPK) object;
        if (this.tdaadacr != other.tdaadacr) {
            return false;
        }
        if (this.numadacr != other.numadacr) {
            return false;
        }
        if ((this.fecadacr == null && other.fecadacr != null)
                || (this.fecadacr != null && !this.fecadacr.equals(other.fecadacr))) {
            return false;
        }
        if ((this.clientadacr == null && other.clientadacr != null)
                || (this.clientadacr != null && !this.clientadacr.equals(other.clientadacr))) {
            return false;
        }
        if (this.numopvdacr != other.numopvdacr) {
            return false;
        }
        if (this.crtvovacr != other.crtvovacr) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.DetvacrPK[ tdaadacr=" + tdaadacr + ", numadacr=" + numadacr
                + ", fecadacr=" + fecadacr + ", clientadacr=" + clientadacr + ", numopvdacr=" + numopvdacr
                + ", crtvovacr=" + crtvovacr + " ]";
    }

}
