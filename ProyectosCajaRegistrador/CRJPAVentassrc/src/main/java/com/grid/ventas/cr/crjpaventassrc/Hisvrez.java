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
@Table(name = "HISVREZ")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Hisvrez.findAll",
                           query = "SELECT h FROM Hisvrez h"),
        @NamedQuery(name = "Hisvrez.findByTienhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.hisvrezPK.tienhrez = :tienhrez"),
        @NamedQuery(name = "Hisvrez.findByCajahrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.cajahrez = :cajahrez"),
        @NamedQuery(name = "Hisvrez.findByFetrhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.hisvrezPK.fetrhrez = :fetrhrez"),
        @NamedQuery(name = "Hisvrez.findByNurehrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.hisvrezPK.nurehrez = :nurehrez"),
        @NamedQuery(name = "Hisvrez.findBySerihrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.hisvrezPK.serihrez = :serihrez"),
        @NamedQuery(name = "Hisvrez.findByCafehrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.cafehrez = :cafehrez"),
        @NamedQuery(name = "Hisvrez.findByUlfahrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.ulfahrez = :ulfahrez"),
        @NamedQuery(name = "Hisvrez.findByFeufhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.feufhrez = :feufhrez"),
        @NamedQuery(name = "Hisvrez.findByHrufhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.hrufhrez = :hrufhrez"),
        @NamedQuery(name = "Hisvrez.findByMexfhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.mexfhrez = :mexfhrez"),
        @NamedQuery(name = "Hisvrez.findByMbgfhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.mbgfhrez = :mbgfhrez"),
        @NamedQuery(name = "Hisvrez.findByMigfhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.migfhrez = :migfhrez"),
        @NamedQuery(name = "Hisvrez.findByPigfhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.pigfhrez = :pigfhrez"),
        @NamedQuery(name = "Hisvrez.findByMbrfhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.mbrfhrez = :mbrfhrez"),
        @NamedQuery(name = "Hisvrez.findByMirfhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.mirfhrez = :mirfhrez"),
        @NamedQuery(name = "Hisvrez.findByPirfhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.pirfhrez = :pirfhrez"),
        @NamedQuery(name = "Hisvrez.findByMbafhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.mbafhrez = :mbafhrez"),
        @NamedQuery(name = "Hisvrez.findByMiafhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.miafhrez = :miafhrez"),
        @NamedQuery(name = "Hisvrez.findByPiafhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.piafhrez = :piafhrez"),
        @NamedQuery(name = "Hisvrez.findByCanchrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.canchrez = :canchrez"),
        @NamedQuery(name = "Hisvrez.findByUlnchrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.ulnchrez = :ulnchrez"),
        @NamedQuery(name = "Hisvrez.findByFeunhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.feunhrez = :feunhrez"),
        @NamedQuery(name = "Hisvrez.findByHrunhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.hrunhrez = :hrunhrez"),
        @NamedQuery(name = "Hisvrez.findByMexnhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.mexnhrez = :mexnhrez"),
        @NamedQuery(name = "Hisvrez.findByMbgnhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.mbgnhrez = :mbgnhrez"),
        @NamedQuery(name = "Hisvrez.findByMignhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.mignhrez = :mignhrez"),
        @NamedQuery(name = "Hisvrez.findByPignhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.pignhrez = :pignhrez"),
        @NamedQuery(name = "Hisvrez.findByMbrnhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.mbrnhrez = :mbrnhrez"),
        @NamedQuery(name = "Hisvrez.findByMirnhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.mirnhrez = :mirnhrez"),
        @NamedQuery(name = "Hisvrez.findByPirnhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.pirnhrez = :pirnhrez"),
        @NamedQuery(name = "Hisvrez.findByMbanhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.mbanhrez = :mbanhrez"),
        @NamedQuery(name = "Hisvrez.findByMianhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.mianhrez = :mianhrez"),
        @NamedQuery(name = "Hisvrez.findByPianhrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.pianhrez = :pianhrez"),
        @NamedQuery(name = "Hisvrez.findByCocahrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.cocahrez = :cocahrez"),
        @NamedQuery(name = "Hisvrez.findByFerehrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.ferehrez = :ferehrez"),
        @NamedQuery(name = "Hisvrez.findByHorehrez",
                    query = "SELECT h FROM Hisvrez h WHERE h.horehrez = :horehrez")})
public class Hisvrez implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected HisvrezPK hisvrezPK;

    @Basic(optional = false)
    @Column(name = "CAJAHREZ")
    private int cajahrez;

    @Basic(optional = false)
    @Column(name = "CAFEHREZ")
    private int cafehrez;

    @Basic(optional = false)
    @Column(name = "ULFAHREZ")
    private int ulfahrez;

    @Basic(optional = false)
    @Column(name = "FEUFHREZ")
    @Temporal(TemporalType.DATE)
    private Date feufhrez;

    @Basic(optional = false)
    @Column(name = "HRUFHREZ")
    @Temporal(TemporalType.TIME)
    private Date hrufhrez;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MEXFHREZ")
    private BigDecimal mexfhrez;

    @Basic(optional = false)
    @Column(name = "MBGFHREZ")
    private BigDecimal mbgfhrez;

    @Basic(optional = false)
    @Column(name = "MIGFHREZ")
    private BigDecimal migfhrez;

    @Basic(optional = false)
    @Column(name = "PIGFHREZ")
    private BigDecimal pigfhrez;

    @Basic(optional = false)
    @Column(name = "MBRFHREZ")
    private BigDecimal mbrfhrez;

    @Basic(optional = false)
    @Column(name = "MIRFHREZ")
    private BigDecimal mirfhrez;

    @Basic(optional = false)
    @Column(name = "PIRFHREZ")
    private BigDecimal pirfhrez;

    @Basic(optional = false)
    @Column(name = "MBAFHREZ")
    private BigDecimal mbafhrez;

    @Basic(optional = false)
    @Column(name = "MIAFHREZ")
    private BigDecimal miafhrez;

    @Basic(optional = false)
    @Column(name = "PIAFHREZ")
    private BigDecimal piafhrez;

    @Basic(optional = false)
    @Column(name = "CANCHREZ")
    private int canchrez;

    @Basic(optional = false)
    @Column(name = "ULNCHREZ")
    private int ulnchrez;

    @Basic(optional = false)
    @Column(name = "FEUNHREZ")
    @Temporal(TemporalType.DATE)
    private Date feunhrez;

    @Basic(optional = false)
    @Column(name = "HRUNHREZ")
    @Temporal(TemporalType.TIME)
    private Date hrunhrez;

    @Basic(optional = false)
    @Column(name = "MEXNHREZ")
    private BigDecimal mexnhrez;

    @Basic(optional = false)
    @Column(name = "MBGNHREZ")
    private BigDecimal mbgnhrez;

    @Basic(optional = false)
    @Column(name = "MIGNHREZ")
    private BigDecimal mignhrez;

    @Basic(optional = false)
    @Column(name = "PIGNHREZ")
    private BigDecimal pignhrez;

    @Basic(optional = false)
    @Column(name = "MBRNHREZ")
    private BigDecimal mbrnhrez;

    @Basic(optional = false)
    @Column(name = "MIRNHREZ")
    private BigDecimal mirnhrez;

    @Basic(optional = false)
    @Column(name = "PIRNHREZ")
    private BigDecimal pirnhrez;

    @Basic(optional = false)
    @Column(name = "MBANHREZ")
    private BigDecimal mbanhrez;

    @Basic(optional = false)
    @Column(name = "MIANHREZ")
    private BigDecimal mianhrez;

    @Basic(optional = false)
    @Column(name = "PIANHREZ")
    private BigDecimal pianhrez;

    @Basic(optional = false)
    @Column(name = "COCAHREZ")
    private int cocahrez;

    @Basic(optional = false)
    @Column(name = "FEREHREZ")
    @Temporal(TemporalType.DATE)
    private Date ferehrez;

    @Basic(optional = false)
    @Column(name = "HOREHREZ")
    @Temporal(TemporalType.TIME)
    private Date horehrez;

    public Hisvrez() {
    }

    public Hisvrez(HisvrezPK hisvrezPK) {
        this.hisvrezPK = hisvrezPK;
    }

    public Hisvrez(HisvrezPK hisvrezPK, int cajahrez, int cafehrez, int ulfahrez, Date feufhrez, Date hrufhrez,
                   BigDecimal mexfhrez, BigDecimal mbgfhrez, BigDecimal migfhrez, BigDecimal pigfhrez,
                   BigDecimal mbrfhrez, BigDecimal mirfhrez, BigDecimal pirfhrez, BigDecimal mbafhrez,
                   BigDecimal miafhrez, BigDecimal piafhrez, int canchrez, int ulnchrez, Date feunhrez,
                   Date hrunhrez, BigDecimal mexnhrez, BigDecimal mbgnhrez, BigDecimal mignhrez,
                   BigDecimal pignhrez, BigDecimal mbrnhrez, BigDecimal mirnhrez, BigDecimal pirnhrez,
                   BigDecimal mbanhrez, BigDecimal mianhrez, BigDecimal pianhrez, int cocahrez, Date ferehrez,
                   Date horehrez) {
        this.hisvrezPK = hisvrezPK;
        this.cajahrez = cajahrez;
        this.cafehrez = cafehrez;
        this.ulfahrez = ulfahrez;
        this.feufhrez = feufhrez;
        this.hrufhrez = hrufhrez;
        this.mexfhrez = mexfhrez;
        this.mbgfhrez = mbgfhrez;
        this.migfhrez = migfhrez;
        this.pigfhrez = pigfhrez;
        this.mbrfhrez = mbrfhrez;
        this.mirfhrez = mirfhrez;
        this.pirfhrez = pirfhrez;
        this.mbafhrez = mbafhrez;
        this.miafhrez = miafhrez;
        this.piafhrez = piafhrez;
        this.canchrez = canchrez;
        this.ulnchrez = ulnchrez;
        this.feunhrez = feunhrez;
        this.hrunhrez = hrunhrez;
        this.mexnhrez = mexnhrez;
        this.mbgnhrez = mbgnhrez;
        this.mignhrez = mignhrez;
        this.pignhrez = pignhrez;
        this.mbrnhrez = mbrnhrez;
        this.mirnhrez = mirnhrez;
        this.pirnhrez = pirnhrez;
        this.mbanhrez = mbanhrez;
        this.mianhrez = mianhrez;
        this.pianhrez = pianhrez;
        this.cocahrez = cocahrez;
        this.ferehrez = ferehrez;
        this.horehrez = horehrez;
    }

    public Hisvrez(int tienhrez, Date fetrhrez, int nurehrez, String serihrez) {
        this.hisvrezPK = new HisvrezPK(tienhrez, fetrhrez, nurehrez, serihrez);
    }

    public HisvrezPK getHisvrezPK() {
        return hisvrezPK;
    }

    public void setHisvrezPK(HisvrezPK hisvrezPK) {
        this.hisvrezPK = hisvrezPK;
    }

    public int getCajahrez() {
        return cajahrez;
    }

    public void setCajahrez(int cajahrez) {
        this.cajahrez = cajahrez;
    }

    public int getCafehrez() {
        return cafehrez;
    }

    public void setCafehrez(int cafehrez) {
        this.cafehrez = cafehrez;
    }

    public int getUlfahrez() {
        return ulfahrez;
    }

    public void setUlfahrez(int ulfahrez) {
        this.ulfahrez = ulfahrez;
    }

    public Date getFeufhrez() {
        return feufhrez;
    }

    public void setFeufhrez(Date feufhrez) {
        this.feufhrez = feufhrez;
    }

    public Date getHrufhrez() {
        return hrufhrez;
    }

    public void setHrufhrez(Date hrufhrez) {
        this.hrufhrez = hrufhrez;
    }

    public BigDecimal getMexfhrez() {
        return mexfhrez;
    }

    public void setMexfhrez(BigDecimal mexfhrez) {
        this.mexfhrez = mexfhrez;
    }

    public BigDecimal getMbgfhrez() {
        return mbgfhrez;
    }

    public void setMbgfhrez(BigDecimal mbgfhrez) {
        this.mbgfhrez = mbgfhrez;
    }

    public BigDecimal getMigfhrez() {
        return migfhrez;
    }

    public void setMigfhrez(BigDecimal migfhrez) {
        this.migfhrez = migfhrez;
    }

    public BigDecimal getPigfhrez() {
        return pigfhrez;
    }

    public void setPigfhrez(BigDecimal pigfhrez) {
        this.pigfhrez = pigfhrez;
    }

    public BigDecimal getMbrfhrez() {
        return mbrfhrez;
    }

    public void setMbrfhrez(BigDecimal mbrfhrez) {
        this.mbrfhrez = mbrfhrez;
    }

    public BigDecimal getMirfhrez() {
        return mirfhrez;
    }

    public void setMirfhrez(BigDecimal mirfhrez) {
        this.mirfhrez = mirfhrez;
    }

    public BigDecimal getPirfhrez() {
        return pirfhrez;
    }

    public void setPirfhrez(BigDecimal pirfhrez) {
        this.pirfhrez = pirfhrez;
    }

    public BigDecimal getMbafhrez() {
        return mbafhrez;
    }

    public void setMbafhrez(BigDecimal mbafhrez) {
        this.mbafhrez = mbafhrez;
    }

    public BigDecimal getMiafhrez() {
        return miafhrez;
    }

    public void setMiafhrez(BigDecimal miafhrez) {
        this.miafhrez = miafhrez;
    }

    public BigDecimal getPiafhrez() {
        return piafhrez;
    }

    public void setPiafhrez(BigDecimal piafhrez) {
        this.piafhrez = piafhrez;
    }

    public int getCanchrez() {
        return canchrez;
    }

    public void setCanchrez(int canchrez) {
        this.canchrez = canchrez;
    }

    public int getUlnchrez() {
        return ulnchrez;
    }

    public void setUlnchrez(int ulnchrez) {
        this.ulnchrez = ulnchrez;
    }

    public Date getFeunhrez() {
        return feunhrez;
    }

    public void setFeunhrez(Date feunhrez) {
        this.feunhrez = feunhrez;
    }

    public Date getHrunhrez() {
        return hrunhrez;
    }

    public void setHrunhrez(Date hrunhrez) {
        this.hrunhrez = hrunhrez;
    }

    public BigDecimal getMexnhrez() {
        return mexnhrez;
    }

    public void setMexnhrez(BigDecimal mexnhrez) {
        this.mexnhrez = mexnhrez;
    }

    public BigDecimal getMbgnhrez() {
        return mbgnhrez;
    }

    public void setMbgnhrez(BigDecimal mbgnhrez) {
        this.mbgnhrez = mbgnhrez;
    }

    public BigDecimal getMignhrez() {
        return mignhrez;
    }

    public void setMignhrez(BigDecimal mignhrez) {
        this.mignhrez = mignhrez;
    }

    public BigDecimal getPignhrez() {
        return pignhrez;
    }

    public void setPignhrez(BigDecimal pignhrez) {
        this.pignhrez = pignhrez;
    }

    public BigDecimal getMbrnhrez() {
        return mbrnhrez;
    }

    public void setMbrnhrez(BigDecimal mbrnhrez) {
        this.mbrnhrez = mbrnhrez;
    }

    public BigDecimal getMirnhrez() {
        return mirnhrez;
    }

    public void setMirnhrez(BigDecimal mirnhrez) {
        this.mirnhrez = mirnhrez;
    }

    public BigDecimal getPirnhrez() {
        return pirnhrez;
    }

    public void setPirnhrez(BigDecimal pirnhrez) {
        this.pirnhrez = pirnhrez;
    }

    public BigDecimal getMbanhrez() {
        return mbanhrez;
    }

    public void setMbanhrez(BigDecimal mbanhrez) {
        this.mbanhrez = mbanhrez;
    }

    public BigDecimal getMianhrez() {
        return mianhrez;
    }

    public void setMianhrez(BigDecimal mianhrez) {
        this.mianhrez = mianhrez;
    }

    public BigDecimal getPianhrez() {
        return pianhrez;
    }

    public void setPianhrez(BigDecimal pianhrez) {
        this.pianhrez = pianhrez;
    }

    public int getCocahrez() {
        return cocahrez;
    }

    public void setCocahrez(int cocahrez) {
        this.cocahrez = cocahrez;
    }

    public Date getFerehrez() {
        return ferehrez;
    }

    public void setFerehrez(Date ferehrez) {
        this.ferehrez = ferehrez;
    }

    public Date getHorehrez() {
        return horehrez;
    }

    public void setHorehrez(Date horehrez) {
        this.horehrez = horehrez;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hisvrezPK != null ? hisvrezPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hisvrez)) {
            return false;
        }
        Hisvrez other = (Hisvrez) object;
        if ((this.hisvrezPK == null && other.hisvrezPK != null)
                || (this.hisvrezPK != null && !this.hisvrezPK.equals(other.hisvrezPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Hisvrez[ hisvrezPK=" + hisvrezPK + " ]";
    }

}
