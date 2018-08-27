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
public class HisvcprPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TIENHCPR")
    private String tienhcpr;

    @Basic(optional = false)
    @Column(name = "CDADHCPR")
    private int cdadhcpr;

    @Basic(optional = false)
    @Column(name = "TIPDHCPR")
    private String tipdhcpr;

    @Basic(optional = false)
    @Column(name = "NORCHCPR")
    private int norchcpr;

    @Basic(optional = false)
    @Column(name = "FECPHCPR")
    @Temporal(TemporalType.DATE)
    private Date fecphcpr;

    @Basic(optional = false)
    @Column(name = "HORAHCPR")
    @Temporal(TemporalType.TIME)
    private Date horahcpr;

    public HisvcprPK() {
    }

    public HisvcprPK(String tienhcpr, int cdadhcpr, String tipdhcpr, int norchcpr, Date fecphcpr, Date horahcpr) {
        this.tienhcpr = tienhcpr;
        this.cdadhcpr = cdadhcpr;
        this.tipdhcpr = tipdhcpr;
        this.norchcpr = norchcpr;
        this.fecphcpr = fecphcpr;
        this.horahcpr = horahcpr;
    }

    public String getTienhcpr() {
        return tienhcpr;
    }

    public void setTienhcpr(String tienhcpr) {
        this.tienhcpr = tienhcpr;
    }

    public int getCdadhcpr() {
        return cdadhcpr;
    }

    public void setCdadhcpr(int cdadhcpr) {
        this.cdadhcpr = cdadhcpr;
    }

    public String getTipdhcpr() {
        return tipdhcpr;
    }

    public void setTipdhcpr(String tipdhcpr) {
        this.tipdhcpr = tipdhcpr;
    }

    public int getNorchcpr() {
        return norchcpr;
    }

    public void setNorchcpr(int norchcpr) {
        this.norchcpr = norchcpr;
    }

    public Date getFecphcpr() {
        return fecphcpr;
    }

    public void setFecphcpr(Date fecphcpr) {
        this.fecphcpr = fecphcpr;
    }

    public Date getHorahcpr() {
        return horahcpr;
    }

    public void setHorahcpr(Date horahcpr) {
        this.horahcpr = horahcpr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tienhcpr != null ? tienhcpr.hashCode() : 0);
        hash += (int) cdadhcpr;
        hash += (tipdhcpr != null ? tipdhcpr.hashCode() : 0);
        hash += (int) norchcpr;
        hash += (fecphcpr != null ? fecphcpr.hashCode() : 0);
        hash += (horahcpr != null ? horahcpr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HisvcprPK)) {
            return false;
        }
        HisvcprPK other = (HisvcprPK) object;
        if ((this.tienhcpr == null && other.tienhcpr != null)
                || (this.tienhcpr != null && !this.tienhcpr.equals(other.tienhcpr))) {
            return false;
        }
        if (this.cdadhcpr != other.cdadhcpr) {
            return false;
        }
        if ((this.tipdhcpr == null && other.tipdhcpr != null)
                || (this.tipdhcpr != null && !this.tipdhcpr.equals(other.tipdhcpr))) {
            return false;
        }
        if (this.norchcpr != other.norchcpr) {
            return false;
        }
        if ((this.fecphcpr == null && other.fecphcpr != null)
                || (this.fecphcpr != null && !this.fecphcpr.equals(other.fecphcpr))) {
            return false;
        }
        if ((this.horahcpr == null && other.horahcpr != null)
                || (this.horahcpr != null && !this.horahcpr.equals(other.horahcpr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.HisvcprPK[ tienhcpr=" + tienhcpr + ", cdadhcpr=" + cdadhcpr
                + ", tipdhcpr=" + tipdhcpr + ", norchcpr=" + norchcpr + ", fecphcpr=" + fecphcpr + ", horahcpr="
                + horahcpr + " ]";
    }

}
