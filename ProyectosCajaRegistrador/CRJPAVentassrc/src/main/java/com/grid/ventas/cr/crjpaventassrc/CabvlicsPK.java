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
public class CabvlicsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "CABVNLIC")
    private String cabvnlic;

    @Basic(optional = false)
    @Column(name = "CABVNTDA")
    private String cabvntda;

    public CabvlicsPK() {
    }

    public CabvlicsPK(String cabvnlic, String cabvntda) {
        this.cabvnlic = cabvnlic;
        this.cabvntda = cabvntda;
    }

    public String getCabvnlic() {
        return cabvnlic;
    }

    public void setCabvnlic(String cabvnlic) {
        this.cabvnlic = cabvnlic;
    }

    public String getCabvntda() {
        return cabvntda;
    }

    public void setCabvntda(String cabvntda) {
        this.cabvntda = cabvntda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cabvnlic != null ? cabvnlic.hashCode() : 0);
        hash += (cabvntda != null ? cabvntda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CabvlicsPK)) {
            return false;
        }
        CabvlicsPK other = (CabvlicsPK) object;
        if ((this.cabvnlic == null && other.cabvnlic != null)
                || (this.cabvnlic != null && !this.cabvnlic.equals(other.cabvnlic))) {
            return false;
        }
        if ((this.cabvntda == null && other.cabvntda != null)
                || (this.cabvntda != null && !this.cabvntda.equals(other.cabvntda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.CabvlicsPK[ cabvnlic=" + cabvnlic + ", cabvntda=" + cabvntda
                + " ]";
    }

}
