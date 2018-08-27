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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eve0017909
 */
@Entity
@Table(name = "DETVPVV")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Detvpvv.findAll",
                           query = "SELECT d FROM Detvpvv d"),
        @NamedQuery(name = "Detvpvv.findByStatdpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.statdpvv = :statdpvv"),
        @NamedQuery(name = "Detvpvv.findByTiendpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.detvpvvPK.tiendpvv = :tiendpvv"),
        @NamedQuery(name = "Detvpvv.findByNpeddpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.detvpvvPK.npeddpvv = :npeddpvv"),
        @NamedQuery(name = "Detvpvv.findByDeptdpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.deptdpvv = :deptdpvv"),
        @NamedQuery(name = "Detvpvv.findByCoardpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.detvpvvPK.coardpvv = :coardpvv"),
        @NamedQuery(name = "Detvpvv.findByCantdpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.cantdpvv = :cantdpvv"),
        @NamedQuery(name = "Detvpvv.findByPvpudpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.pvpudpvv = :pvpudpvv"),
        @NamedQuery(name = "Detvpvv.findByPimpdpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.pimpdpvv = :pimpdpvv"),
        @NamedQuery(name = "Detvpvv.findByCoprdpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.coprdpvv = :coprdpvv"),
        @NamedQuery(name = "Detvpvv.findByGrupdpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.grupdpvv = :grupdpvv"),
        @NamedQuery(name = "Detvpvv.findByNcordpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.ncordpvv = :ncordpvv"),
        @NamedQuery(name = "Detvpvv.findByDispdpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.dispdpvv = :dispdpvv"),
        @NamedQuery(name = "Detvpvv.findByDentdpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.dentdpvv = :dentdpvv"),
        @NamedQuery(name = "Detvpvv.findByNtocdpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.ntocdpvv = :ntocdpvv"),
        @NamedQuery(name = "Detvpvv.findByNottdpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.nottdpvv = :nottdpvv"),
        @NamedQuery(name = "Detvpvv.findByExisdpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.exisdpvv = :exisdpvv"),
        @NamedQuery(name = "Detvpvv.findByOrcodpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.orcodpvv = :orcodpvv"),
        @NamedQuery(name = "Detvpvv.findByFentdpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.fentdpvv = :fentdpvv"),
        @NamedQuery(name = "Detvpvv.findByFactdpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.factdpvv = :factdpvv"),
        @NamedQuery(name = "Detvpvv.findByHactdpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.hactdpvv = :hactdpvv"),
        @NamedQuery(name = "Detvpvv.findByUactdpvv",
                    query = "SELECT d FROM Detvpvv d WHERE d.uactdpvv = :uactdpvv")})
public class Detvpvv implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected DetvpvvPK detvpvvPK;

    @Basic(optional = false)
    @Column(name = "STATDPVV")
    private Character statdpvv;

    @Basic(optional = false)
    @Column(name = "DEPTDPVV")
    private String deptdpvv;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "CANTDPVV")
    private BigDecimal cantdpvv;

    @Basic(optional = false)
    @Column(name = "PVPUDPVV")
    private BigDecimal pvpudpvv;

    @Basic(optional = false)
    @Column(name = "PIMPDPVV")
    private BigDecimal pimpdpvv;

    @Basic(optional = false)
    @Column(name = "COPRDPVV")
    private int coprdpvv;

    @Basic(optional = false)
    @Column(name = "GRUPDPVV")
    private short grupdpvv;

    @Basic(optional = false)
    @Column(name = "NCORDPVV")
    private int ncordpvv;

    @Basic(optional = false)
    @Column(name = "DISPDPVV")
    private Character dispdpvv;

    @Basic(optional = false)
    @Column(name = "DENTDPVV")
    private short dentdpvv;

    @Basic(optional = false)
    @Column(name = "NTOCDPVV")
    private String ntocdpvv;

    @Basic(optional = false)
    @Column(name = "NOTTDPVV")
    private int nottdpvv;

    @Basic(optional = false)
    @Column(name = "EXISDPVV")
    private BigDecimal exisdpvv;

    @Basic(optional = false)
    @Column(name = "ORCODPVV")
    private long orcodpvv;

    @Basic(optional = false)
    @Column(name = "FENTDPVV")
    @Temporal(TemporalType.DATE)
    private Date fentdpvv;

    @Basic(optional = false)
    @Column(name = "FACTDPVV")
    @Temporal(TemporalType.DATE)
    private Date factdpvv;

    @Basic(optional = false)
    @Column(name = "HACTDPVV")
    @Temporal(TemporalType.TIME)
    private Date hactdpvv;

    @Basic(optional = false)
    @Column(name = "UACTDPVV")
    private String uactdpvv;

    public Detvpvv() {
    }

    public Detvpvv(DetvpvvPK detvpvvPK) {
        this.detvpvvPK = detvpvvPK;
    }

    public Detvpvv(DetvpvvPK detvpvvPK, Character statdpvv, String deptdpvv, BigDecimal cantdpvv,
                   BigDecimal pvpudpvv, BigDecimal pimpdpvv, int coprdpvv, short grupdpvv, int ncordpvv,
                   Character dispdpvv, short dentdpvv, String ntocdpvv, int nottdpvv, BigDecimal exisdpvv,
                   long orcodpvv, Date fentdpvv, Date factdpvv, Date hactdpvv, String uactdpvv) {
        this.detvpvvPK = detvpvvPK;
        this.statdpvv = statdpvv;
        this.deptdpvv = deptdpvv;
        this.cantdpvv = cantdpvv;
        this.pvpudpvv = pvpudpvv;
        this.pimpdpvv = pimpdpvv;
        this.coprdpvv = coprdpvv;
        this.grupdpvv = grupdpvv;
        this.ncordpvv = ncordpvv;
        this.dispdpvv = dispdpvv;
        this.dentdpvv = dentdpvv;
        this.ntocdpvv = ntocdpvv;
        this.nottdpvv = nottdpvv;
        this.exisdpvv = exisdpvv;
        this.orcodpvv = orcodpvv;
        this.fentdpvv = fentdpvv;
        this.factdpvv = factdpvv;
        this.hactdpvv = hactdpvv;
        this.uactdpvv = uactdpvv;
    }

    public Detvpvv(String tiendpvv, int npeddpvv, int coardpvv) {
        this.detvpvvPK = new DetvpvvPK(tiendpvv, npeddpvv, coardpvv);
    }

    public DetvpvvPK getDetvpvvPK() {
        return detvpvvPK;
    }

    public void setDetvpvvPK(DetvpvvPK detvpvvPK) {
        this.detvpvvPK = detvpvvPK;
    }

    public Character getStatdpvv() {
        return statdpvv;
    }

    public void setStatdpvv(Character statdpvv) {
        this.statdpvv = statdpvv;
    }

    public String getDeptdpvv() {
        return deptdpvv;
    }

    public void setDeptdpvv(String deptdpvv) {
        this.deptdpvv = deptdpvv;
    }

    public BigDecimal getCantdpvv() {
        return cantdpvv;
    }

    public void setCantdpvv(BigDecimal cantdpvv) {
        this.cantdpvv = cantdpvv;
    }

    public BigDecimal getPvpudpvv() {
        return pvpudpvv;
    }

    public void setPvpudpvv(BigDecimal pvpudpvv) {
        this.pvpudpvv = pvpudpvv;
    }

    public BigDecimal getPimpdpvv() {
        return pimpdpvv;
    }

    public void setPimpdpvv(BigDecimal pimpdpvv) {
        this.pimpdpvv = pimpdpvv;
    }

    public int getCoprdpvv() {
        return coprdpvv;
    }

    public void setCoprdpvv(int coprdpvv) {
        this.coprdpvv = coprdpvv;
    }

    public short getGrupdpvv() {
        return grupdpvv;
    }

    public void setGrupdpvv(short grupdpvv) {
        this.grupdpvv = grupdpvv;
    }

    public int getNcordpvv() {
        return ncordpvv;
    }

    public void setNcordpvv(int ncordpvv) {
        this.ncordpvv = ncordpvv;
    }

    public Character getDispdpvv() {
        return dispdpvv;
    }

    public void setDispdpvv(Character dispdpvv) {
        this.dispdpvv = dispdpvv;
    }

    public short getDentdpvv() {
        return dentdpvv;
    }

    public void setDentdpvv(short dentdpvv) {
        this.dentdpvv = dentdpvv;
    }

    public String getNtocdpvv() {
        return ntocdpvv;
    }

    public void setNtocdpvv(String ntocdpvv) {
        this.ntocdpvv = ntocdpvv;
    }

    public int getNottdpvv() {
        return nottdpvv;
    }

    public void setNottdpvv(int nottdpvv) {
        this.nottdpvv = nottdpvv;
    }

    public BigDecimal getExisdpvv() {
        return exisdpvv;
    }

    public void setExisdpvv(BigDecimal exisdpvv) {
        this.exisdpvv = exisdpvv;
    }

    public long getOrcodpvv() {
        return orcodpvv;
    }

    public void setOrcodpvv(long orcodpvv) {
        this.orcodpvv = orcodpvv;
    }

    public Date getFentdpvv() {
        return fentdpvv;
    }

    public void setFentdpvv(Date fentdpvv) {
        this.fentdpvv = fentdpvv;
    }

    public Date getFactdpvv() {
        return factdpvv;
    }

    public void setFactdpvv(Date factdpvv) {
        this.factdpvv = factdpvv;
    }

    public Date getHactdpvv() {
        return hactdpvv;
    }

    public void setHactdpvv(Date hactdpvv) {
        this.hactdpvv = hactdpvv;
    }

    public String getUactdpvv() {
        return uactdpvv;
    }

    public void setUactdpvv(String uactdpvv) {
        this.uactdpvv = uactdpvv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detvpvvPK != null ? detvpvvPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detvpvv)) {
            return false;
        }
        Detvpvv other = (Detvpvv) object;
        if ((this.detvpvvPK == null && other.detvpvvPK != null)
                || (this.detvpvvPK != null && !this.detvpvvPK.equals(other.detvpvvPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Detvpvv[ detvpvvPK=" + detvpvvPK + " ]";
    }

}
