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
@Table(name = "OPEVRTC")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Opevrtc.findAll",
                           query = "SELECT o FROM Opevrtc o"),
        @NamedQuery(name = "Opevrtc.findByTdavrct",
                    query = "SELECT o FROM Opevrtc o WHERE o.opevrtcPK.tdavrct = :tdavrct"),
        @NamedQuery(name = "Opevrtc.findByCajvrct",
                    query = "SELECT o FROM Opevrtc o WHERE o.opevrtcPK.cajvrct = :cajvrct"),
        @NamedQuery(name = "Opevrtc.findByTrsvrct",
                    query = "SELECT o FROM Opevrtc o WHERE o.opevrtcPK.trsvrct = :trsvrct"),
        @NamedQuery(name = "Opevrtc.findByFtrvrct",
                    query = "SELECT o FROM Opevrtc o WHERE o.ftrvrct = :ftrvrct"),
        @NamedQuery(name = "Opevrtc.findByCodvrct",
                    query = "SELECT o FROM Opevrtc o WHERE o.codvrct = :codvrct"),
        @NamedQuery(name = "Opevrtc.findByMtbvrct",
                    query = "SELECT o FROM Opevrtc o WHERE o.mtbvrct = :mtbvrct"),
        @NamedQuery(name = "Opevrtc.findByMtevrct",
                    query = "SELECT o FROM Opevrtc o WHERE o.mtevrct = :mtevrct"),
        @NamedQuery(name = "Opevrtc.findByMtivrct",
                    query = "SELECT o FROM Opevrtc o WHERE o.mtivrct = :mtivrct"),
        @NamedQuery(name = "Opevrtc.findByImrvrct",
                    query = "SELECT o FROM Opevrtc o WHERE o.imrvrct = :imrvrct"),
        @NamedQuery(name = "Opevrtc.findByComvrct",
                    query = "SELECT o FROM Opevrtc o WHERE o.comvrct = :comvrct"),
        @NamedQuery(name = "Opevrtc.findByMesvrct",
                    query = "SELECT o FROM Opevrtc o WHERE o.mesvrct = :mesvrct"),
        @NamedQuery(name = "Opevrtc.findByAnovrct",
                    query = "SELECT o FROM Opevrtc o WHERE o.anovrct = :anovrct"),
        @NamedQuery(name = "Opevrtc.findByFcovrct",
                    query = "SELECT o FROM Opevrtc o WHERE o.fcovrct = :fcovrct")})
public class Opevrtc implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected OpevrtcPK opevrtcPK;

    @Basic(optional = false)
    @Column(name = "FTRVRCT")
    @Temporal(TemporalType.DATE)
    private Date ftrvrct;

    @Basic(optional = false)
    @Column(name = "CODVRCT")
    private String codvrct;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MTBVRCT")
    private BigDecimal mtbvrct;

    @Basic(optional = false)
    @Column(name = "MTEVRCT")
    private BigDecimal mtevrct;

    @Basic(optional = false)
    @Column(name = "MTIVRCT")
    private BigDecimal mtivrct;

    @Basic(optional = false)
    @Column(name = "IMRVRCT")
    private BigDecimal imrvrct;

    @Basic(optional = false)
    @Column(name = "COMVRCT")
    private String comvrct;

    @Basic(optional = false)
    @Column(name = "MESVRCT")
    private short mesvrct;

    @Basic(optional = false)
    @Column(name = "ANOVRCT")
    private short anovrct;

    @Basic(optional = false)
    @Column(name = "FCOVRCT")
    @Temporal(TemporalType.DATE)
    private Date fcovrct;

    public Opevrtc() {
    }

    public Opevrtc(OpevrtcPK opevrtcPK) {
        this.opevrtcPK = opevrtcPK;
    }

    public Opevrtc(OpevrtcPK opevrtcPK, Date ftrvrct, String codvrct, BigDecimal mtbvrct, BigDecimal mtevrct,
                   BigDecimal mtivrct, BigDecimal imrvrct, String comvrct, short mesvrct, short anovrct,
                   Date fcovrct) {
        this.opevrtcPK = opevrtcPK;
        this.ftrvrct = ftrvrct;
        this.codvrct = codvrct;
        this.mtbvrct = mtbvrct;
        this.mtevrct = mtevrct;
        this.mtivrct = mtivrct;
        this.imrvrct = imrvrct;
        this.comvrct = comvrct;
        this.mesvrct = mesvrct;
        this.anovrct = anovrct;
        this.fcovrct = fcovrct;
    }

    public Opevrtc(short tdavrct, short cajvrct, int trsvrct) {
        this.opevrtcPK = new OpevrtcPK(tdavrct, cajvrct, trsvrct);
    }

    public OpevrtcPK getOpevrtcPK() {
        return opevrtcPK;
    }

    public void setOpevrtcPK(OpevrtcPK opevrtcPK) {
        this.opevrtcPK = opevrtcPK;
    }

    public Date getFtrvrct() {
        return ftrvrct;
    }

    public void setFtrvrct(Date ftrvrct) {
        this.ftrvrct = ftrvrct;
    }

    public String getCodvrct() {
        return codvrct;
    }

    public void setCodvrct(String codvrct) {
        this.codvrct = codvrct;
    }

    public BigDecimal getMtbvrct() {
        return mtbvrct;
    }

    public void setMtbvrct(BigDecimal mtbvrct) {
        this.mtbvrct = mtbvrct;
    }

    public BigDecimal getMtevrct() {
        return mtevrct;
    }

    public void setMtevrct(BigDecimal mtevrct) {
        this.mtevrct = mtevrct;
    }

    public BigDecimal getMtivrct() {
        return mtivrct;
    }

    public void setMtivrct(BigDecimal mtivrct) {
        this.mtivrct = mtivrct;
    }

    public BigDecimal getImrvrct() {
        return imrvrct;
    }

    public void setImrvrct(BigDecimal imrvrct) {
        this.imrvrct = imrvrct;
    }

    public String getComvrct() {
        return comvrct;
    }

    public void setComvrct(String comvrct) {
        this.comvrct = comvrct;
    }

    public short getMesvrct() {
        return mesvrct;
    }

    public void setMesvrct(short mesvrct) {
        this.mesvrct = mesvrct;
    }

    public short getAnovrct() {
        return anovrct;
    }

    public void setAnovrct(short anovrct) {
        this.anovrct = anovrct;
    }

    public Date getFcovrct() {
        return fcovrct;
    }

    public void setFcovrct(Date fcovrct) {
        this.fcovrct = fcovrct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opevrtcPK != null ? opevrtcPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opevrtc)) {
            return false;
        }
        Opevrtc other = (Opevrtc) object;
        if ((this.opevrtcPK == null && other.opevrtcPK != null)
                || (this.opevrtcPK != null && !this.opevrtcPK.equals(other.opevrtcPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Opevrtc[ opevrtcPK=" + opevrtcPK + " ]";
    }

}
