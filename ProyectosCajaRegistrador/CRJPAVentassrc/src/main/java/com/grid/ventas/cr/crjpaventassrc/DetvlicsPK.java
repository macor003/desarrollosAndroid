/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author eve0017909
 */
@Embeddable
public class DetvlicsPK implements Serializable {

    @Column(name = "DETVNLIC")
    private String detvnlic;

    @Column(name = "DETVNATC")
    private String detvnatc;

    public DetvlicsPK() {
    }

    public DetvlicsPK(String detvnlic, String detvnatc) {
        this.detvnlic = detvnlic;
        this.detvnatc = detvnatc;
    }

    public String getDetvnlic() {
        return detvnlic;
    }

    public void setDetvnlic(String detvnlic) {
        this.detvnlic = detvnlic;
    }

    public String getDetvnatc() {
        return detvnatc;
    }

    public void setDetvnatc(String detvnatc) {
        this.detvnatc = detvnatc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detvnlic != null ? detvnlic.hashCode() : 0);
        hash += (detvnatc != null ? detvnatc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetvlicsPK)) {
            return false;
        }
        DetvlicsPK other = (DetvlicsPK) object;
        if ((this.detvnlic == null && other.detvnlic != null)
                || (this.detvnlic != null && !this.detvnlic.equals(other.detvnlic))) {
            return false;
        }
        if ((this.detvnatc == null && other.detvnatc != null)
                || (this.detvnatc != null && !this.detvnatc.equals(other.detvnatc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.DetvlicsPK[ detvnlic=" + detvnlic + ", detvnatc=" + detvnatc
                + " ]";
    }

}
