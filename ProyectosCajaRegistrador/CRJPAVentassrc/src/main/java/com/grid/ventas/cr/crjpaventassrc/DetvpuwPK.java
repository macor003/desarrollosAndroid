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
public class DetvpuwPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "IDTPDNTC")
    private int idtpdntc;

    @Column(name = "TIENDNTC")
    private String tiendntc;

    public DetvpuwPK() {
    }

    public DetvpuwPK(int idtpdntc, String tiendntc) {
        this.idtpdntc = idtpdntc;
        this.tiendntc = tiendntc;
    }

    public int getIdtpdntc() {
        return idtpdntc;
    }

    public void setIdtpdntc(int idtpdntc) {
        this.idtpdntc = idtpdntc;
    }

    public String getTiendntc() {
        return tiendntc;
    }

    public void setTiendntc(String tiendntc) {
        this.tiendntc = tiendntc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idtpdntc;
        hash += (tiendntc != null ? tiendntc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetvpuwPK)) {
            return false;
        }
        DetvpuwPK other = (DetvpuwPK) object;
        if (this.idtpdntc != other.idtpdntc) {
            return false;
        }
        if ((this.tiendntc == null && other.tiendntc != null)
                || (this.tiendntc != null && !this.tiendntc.equals(other.tiendntc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.DetvpuwPK[ idtpdntc=" + idtpdntc + ", tiendntc=" + tiendntc
                + " ]";
    }

}
