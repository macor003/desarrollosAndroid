/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author eve0017909
 */
@Embeddable
public class UsuarioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMTIENDA")
    private short numtienda;

    @Basic(optional = false)
    @Column(name = "NUMFICHA")
    private String numficha;

    public UsuarioPK() {
    }

    public UsuarioPK(short numtienda, String numficha) {
        this.numtienda = numtienda;
        this.numficha = numficha;
    }

    public short getNumtienda() {
        return numtienda;
    }

    public void setNumtienda(short numtienda) {
        this.numtienda = numtienda;
    }

    public String getNumficha() {
        return numficha;
    }

    public void setNumficha(String numficha) {
        this.numficha = numficha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numtienda;
        hash += (numficha != null ? numficha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioPK)) {
            return false;
        }
        UsuarioPK other = (UsuarioPK) object;
        if (this.numtienda != other.numtienda) {
            return false;
        }
        if ((this.numficha == null && other.numficha != null)
                || (this.numficha != null && !this.numficha.equals(other.numficha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.UsuarioPK[ numtienda=" + numtienda + ", numficha=" + numficha + " ]";
    }

}
