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
public class OpevcprPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "EMPROCPR")
    private String emprocpr;

    @Basic(optional = false)
    @Column(name = "CTRLOCPR")
    private String ctrlocpr;

    @Basic(optional = false)
    @Column(name = "TIENOCPR")
    private String tienocpr;

    @Basic(optional = false)
    @Column(name = "CDADOCPR")
    private int cdadocpr;

    public OpevcprPK() {
    }

    public OpevcprPK(String emprocpr, String ctrlocpr, String tienocpr, int cdadocpr) {
        this.emprocpr = emprocpr;
        this.ctrlocpr = ctrlocpr;
        this.tienocpr = tienocpr;
        this.cdadocpr = cdadocpr;
    }

    public String getEmprocpr() {
        return emprocpr;
    }

    public void setEmprocpr(String emprocpr) {
        this.emprocpr = emprocpr;
    }

    public String getCtrlocpr() {
        return ctrlocpr;
    }

    public void setCtrlocpr(String ctrlocpr) {
        this.ctrlocpr = ctrlocpr;
    }

    public String getTienocpr() {
        return tienocpr;
    }

    public void setTienocpr(String tienocpr) {
        this.tienocpr = tienocpr;
    }

    public int getCdadocpr() {
        return cdadocpr;
    }

    public void setCdadocpr(int cdadocpr) {
        this.cdadocpr = cdadocpr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emprocpr != null ? emprocpr.hashCode() : 0);
        hash += (ctrlocpr != null ? ctrlocpr.hashCode() : 0);
        hash += (tienocpr != null ? tienocpr.hashCode() : 0);
        hash += (int) cdadocpr;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpevcprPK)) {
            return false;
        }
        OpevcprPK other = (OpevcprPK) object;
        if ((this.emprocpr == null && other.emprocpr != null)
                || (this.emprocpr != null && !this.emprocpr.equals(other.emprocpr))) {
            return false;
        }
        if ((this.ctrlocpr == null && other.ctrlocpr != null)
                || (this.ctrlocpr != null && !this.ctrlocpr.equals(other.ctrlocpr))) {
            return false;
        }
        if ((this.tienocpr == null && other.tienocpr != null)
                || (this.tienocpr != null && !this.tienocpr.equals(other.tienocpr))) {
            return false;
        }
        if (this.cdadocpr != other.cdadocpr) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.OpevcprPK[ emprocpr=" + emprocpr + ", ctrlocpr=" + ctrlocpr
                + ", tienocpr=" + tienocpr + ", cdadocpr=" + cdadocpr + " ]";
    }

}
