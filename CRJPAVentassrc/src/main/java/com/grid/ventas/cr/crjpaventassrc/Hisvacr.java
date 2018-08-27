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
@Table(name = "HISVACR")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Hisvacr.findAll",
                           query = "SELECT h FROM Hisvacr h"),
        @NamedQuery(name = "Hisvacr.findByMeshacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.hisvacrPK.meshacr = :meshacr"),
        @NamedQuery(name = "Hisvacr.findByAnohacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.hisvacrPK.anohacr = :anohacr"),
        @NamedQuery(name = "Hisvacr.findByNumacreenciahacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.hisvacrPK.numacreenciahacr = :numacreenciahacr"),
        @NamedQuery(name = "Hisvacr.findByIdclientehacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.hisvacrPK.idclientehacr = :idclientehacr"),
        @NamedQuery(name = "Hisvacr.findBySaldohacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.saldohacr = :saldohacr"),
        @NamedQuery(name = "Hisvacr.findByUsuariohacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.usuariohacr = :usuariohacr"),
        @NamedQuery(name = "Hisvacr.findByActualizacionhacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.actualizacionhacr = :actualizacionhacr"),
        @NamedQuery(name = "Hisvacr.findByEstatushacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.estatushacr = :estatushacr"),
        @NamedQuery(name = "Hisvacr.findByAuxnumhacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.auxnumhacr = :auxnumhacr"),
        @NamedQuery(name = "Hisvacr.findByClientehacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.clientehacr = :clientehacr"),
        @NamedQuery(name = "Hisvacr.findBySalbqhacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.salbqhacr = :salbqhacr"),
        @NamedQuery(name = "Hisvacr.findByFeachacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.feachacr = :feachacr"),
        @NamedQuery(name = "Hisvacr.findByFeadhacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.feadhacr = :feadhacr"),
        @NamedQuery(name = "Hisvacr.findBySaahhacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.saahhacr = :saahhacr"),
        @NamedQuery(name = "Hisvacr.findBySapehacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.sapehacr = :sapehacr"),
        @NamedQuery(name = "Hisvacr.findByDis1hacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.dis1hacr = :dis1hacr"),
        @NamedQuery(name = "Hisvacr.findByEssmhacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.essmhacr = :essmhacr"),
        @NamedQuery(name = "Hisvacr.findByEsblhacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.esblhacr = :esblhacr"),
        @NamedQuery(name = "Hisvacr.findByDis2hacr",
                    query = "SELECT h FROM Hisvacr h WHERE h.dis2hacr = :dis2hacr")})
public class Hisvacr implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected HisvacrPK hisvacrPK;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "SALDOHACR")
    private BigDecimal saldohacr;

    @Basic(optional = false)
    @Column(name = "USUARIOHACR")
    private String usuariohacr;

    @Basic(optional = false)
    @Column(name = "ACTUALIZACIONHACR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizacionhacr;

    @Basic(optional = false)
    @Column(name = "ESTATUSHACR")
    private Character estatushacr;

    @Basic(optional = false)
    @Column(name = "AUXNUMHACR")
    private BigDecimal auxnumhacr;

    @Basic(optional = false)
    @Column(name = "CLIENTEHACR")
    private String clientehacr;

    @Basic(optional = false)
    @Column(name = "SALBQHACR")
    private BigDecimal salbqhacr;

    @Basic(optional = false)
    @Column(name = "FEACHACR")
    @Temporal(TemporalType.DATE)
    private Date feachacr;

    @Basic(optional = false)
    @Column(name = "FEADHACR")
    @Temporal(TemporalType.DATE)
    private Date feadhacr;

    @Basic(optional = false)
    @Column(name = "SAAHHACR")
    private BigDecimal saahhacr;

    @Basic(optional = false)
    @Column(name = "SAPEHACR")
    private BigDecimal sapehacr;

    @Basic(optional = false)
    @Column(name = "DIS1HACR")
    private BigDecimal dis1hacr;

    @Basic(optional = false)
    @Column(name = "ESSMHACR")
    private Character essmhacr;

    @Basic(optional = false)
    @Column(name = "ESBLHACR")
    private Character esblhacr;

    @Basic(optional = false)
    @Column(name = "DIS2HACR")
    private Character dis2hacr;

    public Hisvacr() {
    }

    public Hisvacr(HisvacrPK hisvacrPK) {
        this.hisvacrPK = hisvacrPK;
    }

    public Hisvacr(HisvacrPK hisvacrPK, BigDecimal saldohacr, String usuariohacr, Date actualizacionhacr,
                   Character estatushacr, BigDecimal auxnumhacr, String clientehacr, BigDecimal salbqhacr,
                   Date feachacr, Date feadhacr, BigDecimal saahhacr, BigDecimal sapehacr, BigDecimal dis1hacr,
                   Character essmhacr, Character esblhacr, Character dis2hacr) {
        this.hisvacrPK = hisvacrPK;
        this.saldohacr = saldohacr;
        this.usuariohacr = usuariohacr;
        this.actualizacionhacr = actualizacionhacr;
        this.estatushacr = estatushacr;
        this.auxnumhacr = auxnumhacr;
        this.clientehacr = clientehacr;
        this.salbqhacr = salbqhacr;
        this.feachacr = feachacr;
        this.feadhacr = feadhacr;
        this.saahhacr = saahhacr;
        this.sapehacr = sapehacr;
        this.dis1hacr = dis1hacr;
        this.essmhacr = essmhacr;
        this.esblhacr = esblhacr;
        this.dis2hacr = dis2hacr;
    }

    public Hisvacr(short meshacr, short anohacr, long numacreenciahacr, String idclientehacr) {
        this.hisvacrPK = new HisvacrPK(meshacr, anohacr, numacreenciahacr, idclientehacr);
    }

    public HisvacrPK getHisvacrPK() {
        return hisvacrPK;
    }

    public void setHisvacrPK(HisvacrPK hisvacrPK) {
        this.hisvacrPK = hisvacrPK;
    }

    public BigDecimal getSaldohacr() {
        return saldohacr;
    }

    public void setSaldohacr(BigDecimal saldohacr) {
        this.saldohacr = saldohacr;
    }

    public String getUsuariohacr() {
        return usuariohacr;
    }

    public void setUsuariohacr(String usuariohacr) {
        this.usuariohacr = usuariohacr;
    }

    public Date getActualizacionhacr() {
        return actualizacionhacr;
    }

    public void setActualizacionhacr(Date actualizacionhacr) {
        this.actualizacionhacr = actualizacionhacr;
    }

    public Character getEstatushacr() {
        return estatushacr;
    }

    public void setEstatushacr(Character estatushacr) {
        this.estatushacr = estatushacr;
    }

    public BigDecimal getAuxnumhacr() {
        return auxnumhacr;
    }

    public void setAuxnumhacr(BigDecimal auxnumhacr) {
        this.auxnumhacr = auxnumhacr;
    }

    public String getClientehacr() {
        return clientehacr;
    }

    public void setClientehacr(String clientehacr) {
        this.clientehacr = clientehacr;
    }

    public BigDecimal getSalbqhacr() {
        return salbqhacr;
    }

    public void setSalbqhacr(BigDecimal salbqhacr) {
        this.salbqhacr = salbqhacr;
    }

    public Date getFeachacr() {
        return feachacr;
    }

    public void setFeachacr(Date feachacr) {
        this.feachacr = feachacr;
    }

    public Date getFeadhacr() {
        return feadhacr;
    }

    public void setFeadhacr(Date feadhacr) {
        this.feadhacr = feadhacr;
    }

    public BigDecimal getSaahhacr() {
        return saahhacr;
    }

    public void setSaahhacr(BigDecimal saahhacr) {
        this.saahhacr = saahhacr;
    }

    public BigDecimal getSapehacr() {
        return sapehacr;
    }

    public void setSapehacr(BigDecimal sapehacr) {
        this.sapehacr = sapehacr;
    }

    public BigDecimal getDis1hacr() {
        return dis1hacr;
    }

    public void setDis1hacr(BigDecimal dis1hacr) {
        this.dis1hacr = dis1hacr;
    }

    public Character getEssmhacr() {
        return essmhacr;
    }

    public void setEssmhacr(Character essmhacr) {
        this.essmhacr = essmhacr;
    }

    public Character getEsblhacr() {
        return esblhacr;
    }

    public void setEsblhacr(Character esblhacr) {
        this.esblhacr = esblhacr;
    }

    public Character getDis2hacr() {
        return dis2hacr;
    }

    public void setDis2hacr(Character dis2hacr) {
        this.dis2hacr = dis2hacr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hisvacrPK != null ? hisvacrPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hisvacr)) {
            return false;
        }
        Hisvacr other = (Hisvacr) object;
        if ((this.hisvacrPK == null && other.hisvacrPK != null)
                || (this.hisvacrPK != null && !this.hisvacrPK.equals(other.hisvacrPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Hisvacr[ hisvacrPK=" + hisvacrPK + " ]";
    }

}
