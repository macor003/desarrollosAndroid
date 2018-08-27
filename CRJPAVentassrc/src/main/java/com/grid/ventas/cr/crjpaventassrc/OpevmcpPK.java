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
public class OpevmcpPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TDAMVMCP")
    private String tdamvmcp;

    @Basic(optional = false)
    @Column(name = "TPDMVMCP")
    private String tpdmvmcp;

    @Basic(optional = false)
    @Column(name = "NMDIVMCP")
    private long nmdivmcp;

    public OpevmcpPK() {
    }

    public OpevmcpPK(String tdamvmcp, String tpdmvmcp, long nmdivmcp) {
        this.tdamvmcp = tdamvmcp;
        this.tpdmvmcp = tpdmvmcp;
        this.nmdivmcp = nmdivmcp;
    }

    public String getTdamvmcp() {
        return tdamvmcp;
    }

    public void setTdamvmcp(String tdamvmcp) {
        this.tdamvmcp = tdamvmcp;
    }

    public String getTpdmvmcp() {
        return tpdmvmcp;
    }

    public void setTpdmvmcp(String tpdmvmcp) {
        this.tpdmvmcp = tpdmvmcp;
    }

    public long getNmdivmcp() {
        return nmdivmcp;
    }

    public void setNmdivmcp(long nmdivmcp) {
        this.nmdivmcp = nmdivmcp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tdamvmcp != null ? tdamvmcp.hashCode() : 0);
        hash += (tpdmvmcp != null ? tpdmvmcp.hashCode() : 0);
        hash += (int) nmdivmcp;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpevmcpPK)) {
            return false;
        }
        OpevmcpPK other = (OpevmcpPK) object;
        if ((this.tdamvmcp == null && other.tdamvmcp != null)
                || (this.tdamvmcp != null && !this.tdamvmcp.equals(other.tdamvmcp))) {
            return false;
        }
        if ((this.tpdmvmcp == null && other.tpdmvmcp != null)
                || (this.tpdmvmcp != null && !this.tpdmvmcp.equals(other.tpdmvmcp))) {
            return false;
        }
        if (this.nmdivmcp != other.nmdivmcp) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.OpevmcpPK[ tdamvmcp=" + tdamvmcp + ", tpdmvmcp=" + tpdmvmcp
                + ", nmdivmcp=" + nmdivmcp + " ]";
    }

}
