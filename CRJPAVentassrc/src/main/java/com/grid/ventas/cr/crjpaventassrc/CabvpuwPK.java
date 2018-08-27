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
public class CabvpuwPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "IDTCCNTC")
    private int idtccntc;

    @Basic(optional = false)
    @Column(name = "TIENCNTC")
    private String tiencntc;

    public CabvpuwPK() {
    }

    public CabvpuwPK(int idtccntc, String tiencntc) {
        this.idtccntc = idtccntc;
        this.tiencntc = tiencntc;
    }

    public int getIdtccntc() {
        return idtccntc;
    }

    public void setIdtccntc(int idtccntc) {
        this.idtccntc = idtccntc;
    }

    public String getTiencntc() {
        return tiencntc;
    }

    public void setTiencntc(String tiencntc) {
        this.tiencntc = tiencntc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idtccntc;
        hash += (tiencntc != null ? tiencntc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CabvpuwPK)) {
            return false;
        }
        CabvpuwPK other = (CabvpuwPK) object;
        if (this.idtccntc != other.idtccntc) {
            return false;
        }
        if ((this.tiencntc == null && other.tiencntc != null)
                || (this.tiencntc != null && !this.tiencntc.equals(other.tiencntc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.CabvpuwPK[ idtccntc=" + idtccntc + ", tiencntc=" + tiencntc
                + " ]";
    }

}
