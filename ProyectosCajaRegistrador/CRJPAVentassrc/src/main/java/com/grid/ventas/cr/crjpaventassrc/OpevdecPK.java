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
public class OpevdecPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NTIEVDEC")
    private short ntievdec;

    @Basic(optional = false)
    @Column(name = "NCAJVDEC")
    private short ncajvdec;

    @Basic(optional = false)
    @Column(name = "NOPEVDEC")
    private int nopevdec;

    @Basic(optional = false)
    @Column(name = "NACRVDEC")
    private long nacrvdec;

    @Basic(optional = false)
    @Column(name = "FECHVDEC")
    @Temporal(TemporalType.DATE)
    private Date fechvdec;

    @Basic(optional = false)
    @Column(name = "CDFPVDEC")
    private String cdfpvdec;

    @Basic(optional = false)
    @Column(name = "CORRVDEC")
    private short corrvdec;

    @Basic(optional = false)
    @Column(name = "CCLIVDEC")
    private String cclivdec;

    public OpevdecPK() {
    }

    public OpevdecPK(short ntievdec, short ncajvdec, int nopevdec, long nacrvdec, Date fechvdec, String cdfpvdec,
                     short corrvdec, String cclivdec) {
        this.ntievdec = ntievdec;
        this.ncajvdec = ncajvdec;
        this.nopevdec = nopevdec;
        this.nacrvdec = nacrvdec;
        this.fechvdec = fechvdec;
        this.cdfpvdec = cdfpvdec;
        this.corrvdec = corrvdec;
        this.cclivdec = cclivdec;
    }

    public short getNtievdec() {
        return ntievdec;
    }

    public void setNtievdec(short ntievdec) {
        this.ntievdec = ntievdec;
    }

    public short getNcajvdec() {
        return ncajvdec;
    }

    public void setNcajvdec(short ncajvdec) {
        this.ncajvdec = ncajvdec;
    }

    public int getNopevdec() {
        return nopevdec;
    }

    public void setNopevdec(int nopevdec) {
        this.nopevdec = nopevdec;
    }

    public long getNacrvdec() {
        return nacrvdec;
    }

    public void setNacrvdec(long nacrvdec) {
        this.nacrvdec = nacrvdec;
    }

    public Date getFechvdec() {
        return fechvdec;
    }

    public void setFechvdec(Date fechvdec) {
        this.fechvdec = fechvdec;
    }

    public String getCdfpvdec() {
        return cdfpvdec;
    }

    public void setCdfpvdec(String cdfpvdec) {
        this.cdfpvdec = cdfpvdec;
    }

    public short getCorrvdec() {
        return corrvdec;
    }

    public void setCorrvdec(short corrvdec) {
        this.corrvdec = corrvdec;
    }

    public String getCclivdec() {
        return cclivdec;
    }

    public void setCclivdec(String cclivdec) {
        this.cclivdec = cclivdec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ntievdec;
        hash += (int) ncajvdec;
        hash += (int) nopevdec;
        hash += (int) nacrvdec;
        hash += (fechvdec != null ? fechvdec.hashCode() : 0);
        hash += (cdfpvdec != null ? cdfpvdec.hashCode() : 0);
        hash += (int) corrvdec;
        hash += (cclivdec != null ? cclivdec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpevdecPK)) {
            return false;
        }
        OpevdecPK other = (OpevdecPK) object;
        if (this.ntievdec != other.ntievdec) {
            return false;
        }
        if (this.ncajvdec != other.ncajvdec) {
            return false;
        }
        if (this.nopevdec != other.nopevdec) {
            return false;
        }
        if (this.nacrvdec != other.nacrvdec) {
            return false;
        }
        if ((this.fechvdec == null && other.fechvdec != null)
                || (this.fechvdec != null && !this.fechvdec.equals(other.fechvdec))) {
            return false;
        }
        if ((this.cdfpvdec == null && other.cdfpvdec != null)
                || (this.cdfpvdec != null && !this.cdfpvdec.equals(other.cdfpvdec))) {
            return false;
        }
        if (this.corrvdec != other.corrvdec) {
            return false;
        }
        if ((this.cclivdec == null && other.cclivdec != null)
                || (this.cclivdec != null && !this.cclivdec.equals(other.cclivdec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.OpevdecPK[ ntievdec=" + ntievdec + ", ncajvdec=" + ncajvdec
                + ", nopevdec=" + nopevdec + ", nacrvdec=" + nacrvdec + ", fechvdec=" + fechvdec + ", cdfpvdec="
                + cdfpvdec + ", corrvdec=" + corrvdec + ", cclivdec=" + cclivdec + " ]";
    }

}
