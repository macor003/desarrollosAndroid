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
@Table(name = "CABVPVV")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Cabvpvv.findAll",
                           query = "SELECT c FROM Cabvpvv c"),
        @NamedQuery(name = "Cabvpvv.findByStatcpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.statcpvv = :statcpvv"),
        @NamedQuery(name = "Cabvpvv.findByTiencpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.cabvpvvPK.tiencpvv = :tiencpvv"),
        @NamedQuery(name = "Cabvpvv.findByNpedcpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.cabvpvvPK.npedcpvv = :npedcpvv"),
        @NamedQuery(name = "Cabvpvv.findByNcotcpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.ncotcpvv = :ncotcpvv"),
        @NamedQuery(name = "Cabvpvv.findByCliecpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.cliecpvv = :cliecpvv"),
        @NamedQuery(name = "Cabvpvv.findByNombcpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.nombcpvv = :nombcpvv"),
        @NamedQuery(name = "Cabvpvv.findBySubtcpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.subtcpvv = :subtcpvv"),
        @NamedQuery(name = "Cabvpvv.findByImppcpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.imppcpvv = :imppcpvv"),
        @NamedQuery(name = "Cabvpvv.findByTotacpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.totacpvv = :totacpvv"),
        @NamedQuery(name = "Cabvpvv.findBySaldcpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.saldcpvv = :saldcpvv"),
        @NamedQuery(name = "Cabvpvv.findByFecrcpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.fecrcpvv = :fecrcpvv"),
        @NamedQuery(name = "Cabvpvv.findByOpcrcpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.opcrcpvv = :opcrcpvv"),
        @NamedQuery(name = "Cabvpvv.findByFichcpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.fichcpvv = :fichcpvv"),
        @NamedQuery(name = "Cabvpvv.findByFactcpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.factcpvv = :factcpvv"),
        @NamedQuery(name = "Cabvpvv.findByHactcpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.hactcpvv = :hactcpvv"),
        @NamedQuery(name = "Cabvpvv.findByUactcpvv",
                    query = "SELECT c FROM Cabvpvv c WHERE c.uactcpvv = :uactcpvv")})
public class Cabvpvv implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected CabvpvvPK cabvpvvPK;

    @Basic(optional = false)
    @Column(name = "STATCPVV")
    private Character statcpvv;

    @Basic(optional = false)
    @Column(name = "NCOTCPVV")
    private int ncotcpvv;

    @Basic(optional = false)
    @Column(name = "CLIECPVV")
    private String cliecpvv;

    @Basic(optional = false)
    @Column(name = "NOMBCPVV")
    private String nombcpvv;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "SUBTCPVV")
    private BigDecimal subtcpvv;

    @Basic(optional = false)
    @Column(name = "IMPPCPVV")
    private BigDecimal imppcpvv;

    @Basic(optional = false)
    @Column(name = "TOTACPVV")
    private BigDecimal totacpvv;

    @Basic(optional = false)
    @Column(name = "SALDCPVV")
    private BigDecimal saldcpvv;

    @Basic(optional = false)
    @Column(name = "FECRCPVV")
    @Temporal(TemporalType.DATE)
    private Date fecrcpvv;

    @Basic(optional = false)
    @Column(name = "OPCRCPVV")
    private String opcrcpvv;

    @Basic(optional = false)
    @Column(name = "FICHCPVV")
    private String fichcpvv;

    @Basic(optional = false)
    @Column(name = "FACTCPVV")
    @Temporal(TemporalType.DATE)
    private Date factcpvv;

    @Basic(optional = false)
    @Column(name = "HACTCPVV")
    @Temporal(TemporalType.TIME)
    private Date hactcpvv;

    @Basic(optional = false)
    @Column(name = "UACTCPVV")
    private String uactcpvv;

    public Cabvpvv() {
    }

    public Cabvpvv(CabvpvvPK cabvpvvPK) {
        this.cabvpvvPK = cabvpvvPK;
    }

    public Cabvpvv(CabvpvvPK cabvpvvPK, Character statcpvv, int ncotcpvv, String cliecpvv, String nombcpvv,
                   BigDecimal subtcpvv, BigDecimal imppcpvv, BigDecimal totacpvv, BigDecimal saldcpvv,
                   Date fecrcpvv, String opcrcpvv, String fichcpvv, Date factcpvv, Date hactcpvv,
                   String uactcpvv) {
        this.cabvpvvPK = cabvpvvPK;
        this.statcpvv = statcpvv;
        this.ncotcpvv = ncotcpvv;
        this.cliecpvv = cliecpvv;
        this.nombcpvv = nombcpvv;
        this.subtcpvv = subtcpvv;
        this.imppcpvv = imppcpvv;
        this.totacpvv = totacpvv;
        this.saldcpvv = saldcpvv;
        this.fecrcpvv = fecrcpvv;
        this.opcrcpvv = opcrcpvv;
        this.fichcpvv = fichcpvv;
        this.factcpvv = factcpvv;
        this.hactcpvv = hactcpvv;
        this.uactcpvv = uactcpvv;
    }

    public Cabvpvv(String tiencpvv, int npedcpvv) {
        this.cabvpvvPK = new CabvpvvPK(tiencpvv, npedcpvv);
    }

    public CabvpvvPK getCabvpvvPK() {
        return cabvpvvPK;
    }

    public void setCabvpvvPK(CabvpvvPK cabvpvvPK) {
        this.cabvpvvPK = cabvpvvPK;
    }

    public Character getStatcpvv() {
        return statcpvv;
    }

    public void setStatcpvv(Character statcpvv) {
        this.statcpvv = statcpvv;
    }

    public int getNcotcpvv() {
        return ncotcpvv;
    }

    public void setNcotcpvv(int ncotcpvv) {
        this.ncotcpvv = ncotcpvv;
    }

    public String getCliecpvv() {
        return cliecpvv;
    }

    public void setCliecpvv(String cliecpvv) {
        this.cliecpvv = cliecpvv;
    }

    public String getNombcpvv() {
        return nombcpvv;
    }

    public void setNombcpvv(String nombcpvv) {
        this.nombcpvv = nombcpvv;
    }

    public BigDecimal getSubtcpvv() {
        return subtcpvv;
    }

    public void setSubtcpvv(BigDecimal subtcpvv) {
        this.subtcpvv = subtcpvv;
    }

    public BigDecimal getImppcpvv() {
        return imppcpvv;
    }

    public void setImppcpvv(BigDecimal imppcpvv) {
        this.imppcpvv = imppcpvv;
    }

    public BigDecimal getTotacpvv() {
        return totacpvv;
    }

    public void setTotacpvv(BigDecimal totacpvv) {
        this.totacpvv = totacpvv;
    }

    public BigDecimal getSaldcpvv() {
        return saldcpvv;
    }

    public void setSaldcpvv(BigDecimal saldcpvv) {
        this.saldcpvv = saldcpvv;
    }

    public Date getFecrcpvv() {
        return fecrcpvv;
    }

    public void setFecrcpvv(Date fecrcpvv) {
        this.fecrcpvv = fecrcpvv;
    }

    public String getOpcrcpvv() {
        return opcrcpvv;
    }

    public void setOpcrcpvv(String opcrcpvv) {
        this.opcrcpvv = opcrcpvv;
    }

    public String getFichcpvv() {
        return fichcpvv;
    }

    public void setFichcpvv(String fichcpvv) {
        this.fichcpvv = fichcpvv;
    }

    public Date getFactcpvv() {
        return factcpvv;
    }

    public void setFactcpvv(Date factcpvv) {
        this.factcpvv = factcpvv;
    }

    public Date getHactcpvv() {
        return hactcpvv;
    }

    public void setHactcpvv(Date hactcpvv) {
        this.hactcpvv = hactcpvv;
    }

    public String getUactcpvv() {
        return uactcpvv;
    }

    public void setUactcpvv(String uactcpvv) {
        this.uactcpvv = uactcpvv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cabvpvvPK != null ? cabvpvvPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cabvpvv)) {
            return false;
        }
        Cabvpvv other = (Cabvpvv) object;
        if ((this.cabvpvvPK == null && other.cabvpvvPK != null)
                || (this.cabvpvvPK != null && !this.cabvpvvPK.equals(other.cabvpvvPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Cabvpvv[ cabvpvvPK=" + cabvpvvPK + " ]";
    }

}
