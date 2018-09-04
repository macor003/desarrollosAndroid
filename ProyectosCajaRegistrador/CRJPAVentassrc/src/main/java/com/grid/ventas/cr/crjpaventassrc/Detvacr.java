/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "DETVACR")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Detvacr.findAll",
                           query = "SELECT d FROM Detvacr d"),
        @NamedQuery(name = "Detvacr.findByTdaadacr",
                    query = "SELECT d FROM Detvacr d WHERE d.detvacrPK.tdaadacr = :tdaadacr"),
        @NamedQuery(name = "Detvacr.findByNumadacr",
                    query = "SELECT d FROM Detvacr d WHERE d.detvacrPK.numadacr = :numadacr"),
        @NamedQuery(name = "Detvacr.findByFecadacr",
                    query = "SELECT d FROM Detvacr d WHERE d.detvacrPK.fecadacr = :fecadacr"),
        @NamedQuery(name = "Detvacr.findBySaldadacr",
                    query = "SELECT d FROM Detvacr d WHERE d.saldadacr = :saldadacr"),
        @NamedQuery(name = "Detvacr.findByClientadacr",
                    query = "SELECT d FROM Detvacr d WHERE d.detvacrPK.clientadacr = :clientadacr"),
        @NamedQuery(name = "Detvacr.findByEdoadacr",
                    query = "SELECT d FROM Detvacr d WHERE d.edoadacr = :edoadacr"),
        @NamedQuery(name = "Detvacr.findByRgadacr",
                    query = "SELECT d FROM Detvacr d WHERE d.rgadacr = :rgadacr"),
        @NamedQuery(name = "Detvacr.findByNumopvdacr",
                    query = "SELECT d FROM Detvacr d WHERE d.detvacrPK.numopvdacr = :numopvdacr"),
        @NamedQuery(name = "Detvacr.findByCrtvovacr",
                    query = "SELECT d FROM Detvacr d WHERE d.detvacrPK.crtvovacr = :crtvovacr"),
        @NamedQuery(name = "Detvacr.findByConfrvacr",
                    query = "SELECT d FROM Detvacr d WHERE d.confrvacr = :confrvacr"),
        @NamedQuery(name = "Detvacr.findByTipvdacr",
                    query = "SELECT d FROM Detvacr d WHERE d.tipvdacr = :tipvdacr"),
        @NamedQuery(name = "Detvacr.findByFdpvdacr",
                    query = "SELECT d FROM Detvacr d WHERE d.fdpvdacr = :fdpvdacr"),
        @NamedQuery(name = "Detvacr.findByCondcdacr",
                    query = "SELECT d FROM Detvacr d WHERE d.condcdacr = :condcdacr"),
        @NamedQuery(name = "Detvacr.findByNummcpvdacr",
                    query = "SELECT d FROM Detvacr d WHERE d.nummcpvdacr = :nummcpvdacr"),
        @NamedQuery(name = "Detvacr.findByNumtrdacr",
                    query = "SELECT d FROM Detvacr d WHERE d.numtrdacr = :numtrdacr"),
        @NamedQuery(name = "Detvacr.findByCajadacr",
                    query = "SELECT d FROM Detvacr d WHERE d.cajadacr = :cajadacr"),
        @NamedQuery(name = "Detvacr.findByRepldacr",
                    query = "SELECT d FROM Detvacr d WHERE d.repldacr = :repldacr")})
public class Detvacr implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected DetvacrPK detvacrPK;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "SALDADACR")
    private BigDecimal saldadacr;

    @Basic(optional = false)
    @Column(name = "EDOADACR")
    private Character edoadacr;

    @Basic(optional = false)
    @Column(name = "RGADACR")
    private Character rgadacr;

    @Basic(optional = false)
    @Column(name = "CONFRVACR")
    private Character confrvacr;

    @Basic(optional = false)
    @Column(name = "TIPVDACR")
    private Character tipvdacr;

    @Basic(optional = false)
    @Column(name = "FDPVDACR")
    private String fdpvdacr;

    @Basic(optional = false)
    @Column(name = "CONDCDACR")
    private Character condcdacr;

    @Column(name = "NUMMCPVDACR")
    private Long nummcpvdacr;

    @Basic(optional = false)
    @Column(name = "NUMTRDACR")
    private long numtrdacr;

    @Basic(optional = false)
    @Column(name = "CAJADACR")
    private short cajadacr;

    @Basic(optional = false)
    @Column(name = "REPLDACR")
    private Character repldacr;

    public Detvacr() {
    }

    public Detvacr(DetvacrPK detvacrPK) {
        this.detvacrPK = detvacrPK;
    }

    public Detvacr(DetvacrPK detvacrPK, BigDecimal saldadacr, Character edoadacr, Character rgadacr,
                   Character confrvacr, Character tipvdacr, String fdpvdacr, Character condcdacr, long numtrdacr,
                   short cajadacr, Character repldacr) {
        this.detvacrPK = detvacrPK;
        this.saldadacr = saldadacr;
        this.edoadacr = edoadacr;
        this.rgadacr = rgadacr;
        this.confrvacr = confrvacr;
        this.tipvdacr = tipvdacr;
        this.fdpvdacr = fdpvdacr;
        this.condcdacr = condcdacr;
        this.numtrdacr = numtrdacr;
        this.cajadacr = cajadacr;
        this.repldacr = repldacr;
    }

    public Detvacr(short tdaadacr, long numadacr, Date fecadacr, String clientadacr, long numopvdacr,
                   long crtvovacr) {
        this.detvacrPK = new DetvacrPK(tdaadacr, numadacr, fecadacr, clientadacr, numopvdacr, crtvovacr);
    }

    public DetvacrPK getDetvacrPK() {
        return detvacrPK;
    }

    public void setDetvacrPK(DetvacrPK detvacrPK) {
        this.detvacrPK = detvacrPK;
    }

    public BigDecimal getSaldadacr() {
        return saldadacr;
    }

    public void setSaldadacr(BigDecimal saldadacr) {
        this.saldadacr = saldadacr;
    }

    public Character getEdoadacr() {
        return edoadacr;
    }

    public void setEdoadacr(Character edoadacr) {
        this.edoadacr = edoadacr;
    }

    public Character getRgadacr() {
        return rgadacr;
    }

    public void setRgadacr(Character rgadacr) {
        this.rgadacr = rgadacr;
    }

    public Character getConfrvacr() {
        return confrvacr;
    }

    public void setConfrvacr(Character confrvacr) {
        this.confrvacr = confrvacr;
    }

    public Character getTipvdacr() {
        return tipvdacr;
    }

    public void setTipvdacr(Character tipvdacr) {
        this.tipvdacr = tipvdacr;
    }

    public String getFdpvdacr() {
        return fdpvdacr;
    }

    public void setFdpvdacr(String fdpvdacr) {
        this.fdpvdacr = fdpvdacr;
    }

    public Character getCondcdacr() {
        return condcdacr;
    }

    public void setCondcdacr(Character condcdacr) {
        this.condcdacr = condcdacr;
    }

    public Long getNummcpvdacr() {
        return nummcpvdacr;
    }

    public void setNummcpvdacr(Long nummcpvdacr) {
        this.nummcpvdacr = nummcpvdacr;
    }

    public long getNumtrdacr() {
        return numtrdacr;
    }

    public void setNumtrdacr(long numtrdacr) {
        this.numtrdacr = numtrdacr;
    }

    public short getCajadacr() {
        return cajadacr;
    }

    public void setCajadacr(short cajadacr) {
        this.cajadacr = cajadacr;
    }

    public Character getRepldacr() {
        return repldacr;
    }

    public void setRepldacr(Character repldacr) {
        this.repldacr = repldacr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detvacrPK != null ? detvacrPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detvacr)) {
            return false;
        }
        Detvacr other = (Detvacr) object;
        if ((this.detvacrPK == null && other.detvacrPK != null)
                || (this.detvacrPK != null && !this.detvacrPK.equals(other.detvacrPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Detvacr[ detvacrPK=" + detvacrPK + " ]";
    }

}
