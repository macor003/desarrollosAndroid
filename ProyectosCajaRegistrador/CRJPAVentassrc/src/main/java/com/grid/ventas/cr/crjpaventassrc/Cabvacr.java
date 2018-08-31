/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eve0017909
 */
@Entity
@Table(name = "CABVACR")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Cabvacr.findAll",
                           query = "SELECT c FROM Cabvacr c"),
        @NamedQuery(name = "Cabvacr.findByNumacacr",
                    query = "SELECT c FROM Cabvacr c WHERE c.cabvacrPK.numacacr = :numacacr"),
        @NamedQuery(name = "Cabvacr.findByClientacacr",
                    query = "SELECT c FROM Cabvacr c WHERE c.cabvacrPK.clientacacr = :clientacacr"),
        @NamedQuery(name = "Cabvacr.findBySaldacacr",
                    query = "SELECT c FROM Cabvacr c WHERE c.saldacacr = :saldacacr"),
        @NamedQuery(name = "Cabvacr.findByEdoacacr",
                    query = "SELECT c FROM Cabvacr c WHERE c.edoacacr = :edoacacr"),
        @NamedQuery(name = "Cabvacr.findBySaldtrcacr",
                    query = "SELECT c FROM Cabvacr c WHERE c.saldtrcacr = :saldtrcacr"),
        @NamedQuery(name = "Cabvacr.findByReplcacr",
                    query = "SELECT c FROM Cabvacr c WHERE c.replcacr = :replcacr")})
public class Cabvacr implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected CabvacrPK cabvacrPK;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "SALDACACR")
    private BigDecimal saldacacr;

    @Basic(optional = false)
    @Column(name = "EDOACACR")
    private Character edoacacr;

    @Basic(optional = false)
    @Column(name = "SALDTRCACR")
    private BigDecimal saldtrcacr;

    @Column(name = "REPLCACR")
    private Character replcacr;

    public Cabvacr() {
    }

    public Cabvacr(CabvacrPK cabvacrPK) {
        this.cabvacrPK = cabvacrPK;
    }

    public Cabvacr(CabvacrPK cabvacrPK, BigDecimal saldacacr, Character edoacacr, BigDecimal saldtrcacr) {
        this.cabvacrPK = cabvacrPK;
        this.saldacacr = saldacacr;
        this.edoacacr = edoacacr;
        this.saldtrcacr = saldtrcacr;
    }

    public Cabvacr(long numacacr, String clientacacr) {
        this.cabvacrPK = new CabvacrPK(numacacr, clientacacr);
    }

    public CabvacrPK getCabvacrPK() {
        return cabvacrPK;
    }

    public void setCabvacrPK(CabvacrPK cabvacrPK) {
        this.cabvacrPK = cabvacrPK;
    }

    public BigDecimal getSaldacacr() {
        return saldacacr;
    }

    public void setSaldacacr(BigDecimal saldacacr) {
        this.saldacacr = saldacacr;
    }

    public Character getEdoacacr() {
        return edoacacr;
    }

    public void setEdoacacr(Character edoacacr) {
        this.edoacacr = edoacacr;
    }

    public BigDecimal getSaldtrcacr() {
        return saldtrcacr;
    }

    public void setSaldtrcacr(BigDecimal saldtrcacr) {
        this.saldtrcacr = saldtrcacr;
    }

    public Character getReplcacr() {
        return replcacr;
    }

    public void setReplcacr(Character replcacr) {
        this.replcacr = replcacr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cabvacrPK != null ? cabvacrPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cabvacr)) {
            return false;
        }
        Cabvacr other = (Cabvacr) object;
        if ((this.cabvacrPK == null && other.cabvacrPK != null)
                || (this.cabvacrPK != null && !this.cabvacrPK.equals(other.cabvacrPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Cabvacr[ cabvacrPK=" + cabvacrPK + " ]";
    }

}
