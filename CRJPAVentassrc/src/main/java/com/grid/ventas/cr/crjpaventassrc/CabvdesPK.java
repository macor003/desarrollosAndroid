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
public class CabvdesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TIENCDES")
    private String tiencdes;

    @Basic(optional = false)
    @Column(name = "NCORCDES")
    private int ncorcdes;

    @Basic(optional = false)
    @Column(name = "FEMICDES")
    @Temporal(TemporalType.DATE)
    private Date femicdes;

    @Basic(optional = false)
    @Column(name = "TRANCDES")
    private int trancdes;

    @Basic(optional = false)
    @Column(name = "TDESCDES")
    private Character tdescdes;

    public CabvdesPK() {
    }

    public CabvdesPK(String tiencdes, int ncorcdes, Date femicdes, int trancdes, Character tdescdes) {
        this.tiencdes = tiencdes;
        this.ncorcdes = ncorcdes;
        this.femicdes = femicdes;
        this.trancdes = trancdes;
        this.tdescdes = tdescdes;
    }

    public String getTiencdes() {
        return tiencdes;
    }

    public void setTiencdes(String tiencdes) {
        this.tiencdes = tiencdes;
    }

    public int getNcorcdes() {
        return ncorcdes;
    }

    public void setNcorcdes(int ncorcdes) {
        this.ncorcdes = ncorcdes;
    }

    public Date getFemicdes() {
        return femicdes;
    }

    public void setFemicdes(Date femicdes) {
        this.femicdes = femicdes;
    }

    public int getTrancdes() {
        return trancdes;
    }

    public void setTrancdes(int trancdes) {
        this.trancdes = trancdes;
    }

    public Character getTdescdes() {
        return tdescdes;
    }

    public void setTdescdes(Character tdescdes) {
        this.tdescdes = tdescdes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tiencdes != null ? tiencdes.hashCode() : 0);
        hash += (int) ncorcdes;
        hash += (femicdes != null ? femicdes.hashCode() : 0);
        hash += (int) trancdes;
        hash += (tdescdes != null ? tdescdes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CabvdesPK)) {
            return false;
        }
        CabvdesPK other = (CabvdesPK) object;
        if ((this.tiencdes == null && other.tiencdes != null)
                || (this.tiencdes != null && !this.tiencdes.equals(other.tiencdes))) {
            return false;
        }
        if (this.ncorcdes != other.ncorcdes) {
            return false;
        }
        if ((this.femicdes == null && other.femicdes != null)
                || (this.femicdes != null && !this.femicdes.equals(other.femicdes))) {
            return false;
        }
        if (this.trancdes != other.trancdes) {
            return false;
        }
        if ((this.tdescdes == null && other.tdescdes != null)
                || (this.tdescdes != null && !this.tdescdes.equals(other.tdescdes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.CabvdesPK[ tiencdes=" + tiencdes + ", ncorcdes=" + ncorcdes
                + ", femicdes=" + femicdes + ", trancdes=" + trancdes + ", tdescdes=" + tdescdes + " ]";
    }

}
