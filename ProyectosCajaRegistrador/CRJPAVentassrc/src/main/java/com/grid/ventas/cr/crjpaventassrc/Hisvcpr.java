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
@Table(name = "HISVCPR")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Hisvcpr.findAll",
                           query = "SELECT h FROM Hisvcpr h"),
        @NamedQuery(name = "Hisvcpr.findByStathcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.stathcpr = :stathcpr"),
        @NamedQuery(name = "Hisvcpr.findByEmprhcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.emprhcpr = :emprhcpr"),
        @NamedQuery(name = "Hisvcpr.findByCtrlhcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.ctrlhcpr = :ctrlhcpr"),
        @NamedQuery(name = "Hisvcpr.findByTienhcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.hisvcprPK.tienhcpr = :tienhcpr"),
        @NamedQuery(name = "Hisvcpr.findByCdadhcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.hisvcprPK.cdadhcpr = :cdadhcpr"),
        @NamedQuery(name = "Hisvcpr.findByTipdhcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.hisvcprPK.tipdhcpr = :tipdhcpr"),
        @NamedQuery(name = "Hisvcpr.findByNorchcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.hisvcprPK.norchcpr = :norchcpr"),
        @NamedQuery(name = "Hisvcpr.findByFecphcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.hisvcprPK.fecphcpr = :fecphcpr"),
        @NamedQuery(name = "Hisvcpr.findByHorahcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.hisvcprPK.horahcpr = :horahcpr"),
        @NamedQuery(name = "Hisvcpr.findByCanthcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.canthcpr = :canthcpr"),
        @NamedQuery(name = "Hisvcpr.findByCoslhcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.coslhcpr = :coslhcpr"),
        @NamedQuery(name = "Hisvcpr.findByTotahcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.totahcpr = :totahcpr"),
        @NamedQuery(name = "Hisvcpr.findByExiahcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.exiahcpr = :exiahcpr"),
        @NamedQuery(name = "Hisvcpr.findByCopahcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.copahcpr = :copahcpr"),
        @NamedQuery(name = "Hisvcpr.findByCopthcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.copthcpr = :copthcpr"),
        @NamedQuery(name = "Hisvcpr.findByA\u00f1omhcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.a\u00f1omhcpr = :a\u00f1omhcpr"),
        @NamedQuery(name = "Hisvcpr.findByMesmhcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.mesmhcpr = :mesmhcpr"),
        @NamedQuery(name = "Hisvcpr.findByCorehcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.corehcpr = :corehcpr"),
        @NamedQuery(name = "Hisvcpr.findByUsuahcpr",
                    query = "SELECT h FROM Hisvcpr h WHERE h.usuahcpr = :usuahcpr")})
public class Hisvcpr implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected HisvcprPK hisvcprPK;

    @Basic(optional = false)
    @Column(name = "STATHCPR")
    private Character stathcpr;

    @Basic(optional = false)
    @Column(name = "EMPRHCPR")
    private String emprhcpr;

    @Basic(optional = false)
    @Column(name = "CTRLHCPR")
    private String ctrlhcpr;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "CANTHCPR")
    private BigDecimal canthcpr;

    @Basic(optional = false)
    @Column(name = "COSLHCPR")
    private BigDecimal coslhcpr;

    @Basic(optional = false)
    @Column(name = "TOTAHCPR")
    private BigDecimal totahcpr;

    @Basic(optional = false)
    @Column(name = "EXIAHCPR")
    private BigDecimal exiahcpr;

    @Basic(optional = false)
    @Column(name = "COPAHCPR")
    private BigDecimal copahcpr;

    @Basic(optional = false)
    @Column(name = "COPTHCPR")
    private BigDecimal copthcpr;

    @Basic(optional = false)
    @Column(name = "A\u00d1OMHCPR")
    private short añomhcpr;

    @Basic(optional = false)
    @Column(name = "MESMHCPR")
    private short mesmhcpr;

    @Basic(optional = false)
    @Column(name = "COREHCPR")
    private int corehcpr;

    @Basic(optional = false)
    @Column(name = "USUAHCPR")
    private String usuahcpr;

    public Hisvcpr() {
    }

    public Hisvcpr(HisvcprPK hisvcprPK) {
        this.hisvcprPK = hisvcprPK;
    }

    public Hisvcpr(HisvcprPK hisvcprPK, Character stathcpr, String emprhcpr, String ctrlhcpr, BigDecimal canthcpr,
                   BigDecimal coslhcpr, BigDecimal totahcpr, BigDecimal exiahcpr, BigDecimal copahcpr,
                   BigDecimal copthcpr, short añomhcpr, short mesmhcpr, int corehcpr, String usuahcpr) {
        this.hisvcprPK = hisvcprPK;
        this.stathcpr = stathcpr;
        this.emprhcpr = emprhcpr;
        this.ctrlhcpr = ctrlhcpr;
        this.canthcpr = canthcpr;
        this.coslhcpr = coslhcpr;
        this.totahcpr = totahcpr;
        this.exiahcpr = exiahcpr;
        this.copahcpr = copahcpr;
        this.copthcpr = copthcpr;
        this.añomhcpr = añomhcpr;
        this.mesmhcpr = mesmhcpr;
        this.corehcpr = corehcpr;
        this.usuahcpr = usuahcpr;
    }

    public Hisvcpr(String tienhcpr, int cdadhcpr, String tipdhcpr, int norchcpr, Date fecphcpr, Date horahcpr) {
        this.hisvcprPK = new HisvcprPK(tienhcpr, cdadhcpr, tipdhcpr, norchcpr, fecphcpr, horahcpr);
    }

    public HisvcprPK getHisvcprPK() {
        return hisvcprPK;
    }

    public void setHisvcprPK(HisvcprPK hisvcprPK) {
        this.hisvcprPK = hisvcprPK;
    }

    public Character getStathcpr() {
        return stathcpr;
    }

    public void setStathcpr(Character stathcpr) {
        this.stathcpr = stathcpr;
    }

    public String getEmprhcpr() {
        return emprhcpr;
    }

    public void setEmprhcpr(String emprhcpr) {
        this.emprhcpr = emprhcpr;
    }

    public String getCtrlhcpr() {
        return ctrlhcpr;
    }

    public void setCtrlhcpr(String ctrlhcpr) {
        this.ctrlhcpr = ctrlhcpr;
    }

    public BigDecimal getCanthcpr() {
        return canthcpr;
    }

    public void setCanthcpr(BigDecimal canthcpr) {
        this.canthcpr = canthcpr;
    }

    public BigDecimal getCoslhcpr() {
        return coslhcpr;
    }

    public void setCoslhcpr(BigDecimal coslhcpr) {
        this.coslhcpr = coslhcpr;
    }

    public BigDecimal getTotahcpr() {
        return totahcpr;
    }

    public void setTotahcpr(BigDecimal totahcpr) {
        this.totahcpr = totahcpr;
    }

    public BigDecimal getExiahcpr() {
        return exiahcpr;
    }

    public void setExiahcpr(BigDecimal exiahcpr) {
        this.exiahcpr = exiahcpr;
    }

    public BigDecimal getCopahcpr() {
        return copahcpr;
    }

    public void setCopahcpr(BigDecimal copahcpr) {
        this.copahcpr = copahcpr;
    }

    public BigDecimal getCopthcpr() {
        return copthcpr;
    }

    public void setCopthcpr(BigDecimal copthcpr) {
        this.copthcpr = copthcpr;
    }

    public short getAñomhcpr() {
        return añomhcpr;
    }

    public void setAñomhcpr(short añomhcpr) {
        this.añomhcpr = añomhcpr;
    }

    public short getMesmhcpr() {
        return mesmhcpr;
    }

    public void setMesmhcpr(short mesmhcpr) {
        this.mesmhcpr = mesmhcpr;
    }

    public int getCorehcpr() {
        return corehcpr;
    }

    public void setCorehcpr(int corehcpr) {
        this.corehcpr = corehcpr;
    }

    public String getUsuahcpr() {
        return usuahcpr;
    }

    public void setUsuahcpr(String usuahcpr) {
        this.usuahcpr = usuahcpr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hisvcprPK != null ? hisvcprPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hisvcpr)) {
            return false;
        }
        Hisvcpr other = (Hisvcpr) object;
        if ((this.hisvcprPK == null && other.hisvcprPK != null)
                || (this.hisvcprPK != null && !this.hisvcprPK.equals(other.hisvcprPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Hisvcpr[ hisvcprPK=" + hisvcprPK + " ]";
    }

}
