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
public class HisvrezPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TIENHREZ")
    private int tienhrez;

    @Basic(optional = false)
    @Column(name = "FETRHREZ")
    @Temporal(TemporalType.DATE)
    private Date fetrhrez;

    @Basic(optional = false)
    @Column(name = "NUREHREZ")
    private int nurehrez;

    @Column(name = "SERIHREZ")
    private String serihrez;

    public HisvrezPK() {
    }

    public HisvrezPK(int tienhrez, Date fetrhrez, int nurehrez, String serihrez) {
        this.tienhrez = tienhrez;
        this.fetrhrez = fetrhrez;
        this.nurehrez = nurehrez;
        this.serihrez = serihrez;
    }

    public int getTienhrez() {
        return tienhrez;
    }

    public void setTienhrez(int tienhrez) {
        this.tienhrez = tienhrez;
    }

    public Date getFetrhrez() {
        return fetrhrez;
    }

    public void setFetrhrez(Date fetrhrez) {
        this.fetrhrez = fetrhrez;
    }

    public int getNurehrez() {
        return nurehrez;
    }

    public void setNurehrez(int nurehrez) {
        this.nurehrez = nurehrez;
    }

    public String getSerihrez() {
        return serihrez;
    }

    public void setSerihrez(String serihrez) {
        this.serihrez = serihrez;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tienhrez;
        hash += (fetrhrez != null ? fetrhrez.hashCode() : 0);
        hash += (int) nurehrez;
        hash += (serihrez != null ? serihrez.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HisvrezPK)) {
            return false;
        }
        HisvrezPK other = (HisvrezPK) object;
        if (this.tienhrez != other.tienhrez) {
            return false;
        }
        if ((this.fetrhrez == null && other.fetrhrez != null)
                || (this.fetrhrez != null && !this.fetrhrez.equals(other.fetrhrez))) {
            return false;
        }
        if (this.nurehrez != other.nurehrez) {
            return false;
        }
        if ((this.serihrez == null && other.serihrez != null)
                || (this.serihrez != null && !this.serihrez.equals(other.serihrez))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.HisvrezPK[ tienhrez=" + tienhrez + ", fetrhrez=" + fetrhrez
                + ", nurehrez=" + nurehrez + ", serihrez=" + serihrez + " ]";
    }

}
