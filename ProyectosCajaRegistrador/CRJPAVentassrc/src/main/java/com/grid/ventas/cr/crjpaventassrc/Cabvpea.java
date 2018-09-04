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
@Table(name = "CABVPEA")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Cabvpea.findAll",
                           query = "SELECT c FROM Cabvpea c"),
        @NamedQuery(name = "Cabvpea.findByTiencpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.cabvpeaPK.tiencpea = :tiencpea"),
        @NamedQuery(name = "Cabvpea.findByNumpcpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.cabvpeaPK.numpcpea = :numpcpea"),
        @NamedQuery(name = "Cabvpea.findByTsercpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.cabvpeaPK.tsercpea = :tsercpea"),
        @NamedQuery(name = "Cabvpea.findByNrifcpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.nrifcpea = :nrifcpea"),
        @NamedQuery(name = "Cabvpea.findByConpcpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.conpcpea = :conpcpea"),
        @NamedQuery(name = "Cabvpea.findByMontcpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.montcpea = :montcpea"),
        @NamedQuery(name = "Cabvpea.findBySaldcpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.saldcpea = :saldcpea"),
        @NamedQuery(name = "Cabvpea.findByTipocpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.tipocpea = :tipocpea"),
        @NamedQuery(name = "Cabvpea.findByStatcpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.statcpea = :statcpea"),
        @NamedQuery(name = "Cabvpea.findByCajacpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.cajacpea = :cajacpea"),
        @NamedQuery(name = "Cabvpea.findByTrancpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.trancpea = :trancpea"),
        @NamedQuery(name = "Cabvpea.findByStabcpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.stabcpea = :stabcpea"),
        @NamedQuery(name = "Cabvpea.findByFechcpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.fechcpea = :fechcpea"),
        @NamedQuery(name = "Cabvpea.findByCam1cpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.cam1cpea = :cam1cpea"),
        @NamedQuery(name = "Cabvpea.findByCam2cpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.cam2cpea = :cam2cpea"),
        @NamedQuery(name = "Cabvpea.findByCam3cpea",
                    query = "SELECT c FROM Cabvpea c WHERE c.cam3cpea = :cam3cpea")})
public class Cabvpea implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected CabvpeaPK cabvpeaPK;

    @Basic(optional = false)
    @Column(name = "NRIFCPEA")
    private String nrifcpea;

    @Basic(optional = false)
    @Column(name = "CONPCPEA")
    private short conpcpea;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MONTCPEA")
    private BigDecimal montcpea;

    @Basic(optional = false)
    @Column(name = "SALDCPEA")
    private BigDecimal saldcpea;

    @Basic(optional = false)
    @Column(name = "TIPOCPEA")
    private short tipocpea;

    @Basic(optional = false)
    @Column(name = "STATCPEA")
    private short statcpea;

    @Basic(optional = false)
    @Column(name = "CAJACPEA")
    private short cajacpea;

    @Basic(optional = false)
    @Column(name = "TRANCPEA")
    private int trancpea;

    @Basic(optional = false)
    @Column(name = "STABCPEA")
    private short stabcpea;

    @Basic(optional = false)
    @Column(name = "FECHCPEA")
    private int fechcpea;

    @Basic(optional = false)
    @Column(name = "CAM1CPEA")
    private String cam1cpea;

    @Basic(optional = false)
    @Column(name = "CAM2CPEA")
    private BigDecimal cam2cpea;

    @Basic(optional = false)
    @Column(name = "CAM3CPEA")
    @Temporal(TemporalType.DATE)
    private Date cam3cpea;

    public Cabvpea() {
    }

    public Cabvpea(CabvpeaPK cabvpeaPK) {
        this.cabvpeaPK = cabvpeaPK;
    }

    public Cabvpea(CabvpeaPK cabvpeaPK, String nrifcpea, short conpcpea, BigDecimal montcpea, BigDecimal saldcpea,
                   short tipocpea, short statcpea, short cajacpea, int trancpea, short stabcpea, int fechcpea,
                   String cam1cpea, BigDecimal cam2cpea, Date cam3cpea) {
        this.cabvpeaPK = cabvpeaPK;
        this.nrifcpea = nrifcpea;
        this.conpcpea = conpcpea;
        this.montcpea = montcpea;
        this.saldcpea = saldcpea;
        this.tipocpea = tipocpea;
        this.statcpea = statcpea;
        this.cajacpea = cajacpea;
        this.trancpea = trancpea;
        this.stabcpea = stabcpea;
        this.fechcpea = fechcpea;
        this.cam1cpea = cam1cpea;
        this.cam2cpea = cam2cpea;
        this.cam3cpea = cam3cpea;
    }

    public Cabvpea(String tiencpea, int numpcpea, String tsercpea) {
        this.cabvpeaPK = new CabvpeaPK(tiencpea, numpcpea, tsercpea);
    }

    public CabvpeaPK getCabvpeaPK() {
        return cabvpeaPK;
    }

    public void setCabvpeaPK(CabvpeaPK cabvpeaPK) {
        this.cabvpeaPK = cabvpeaPK;
    }

    public String getNrifcpea() {
        return nrifcpea;
    }

    public void setNrifcpea(String nrifcpea) {
        this.nrifcpea = nrifcpea;
    }

    public short getConpcpea() {
        return conpcpea;
    }

    public void setConpcpea(short conpcpea) {
        this.conpcpea = conpcpea;
    }

    public BigDecimal getMontcpea() {
        return montcpea;
    }

    public void setMontcpea(BigDecimal montcpea) {
        this.montcpea = montcpea;
    }

    public BigDecimal getSaldcpea() {
        return saldcpea;
    }

    public void setSaldcpea(BigDecimal saldcpea) {
        this.saldcpea = saldcpea;
    }

    public short getTipocpea() {
        return tipocpea;
    }

    public void setTipocpea(short tipocpea) {
        this.tipocpea = tipocpea;
    }

    public short getStatcpea() {
        return statcpea;
    }

    public void setStatcpea(short statcpea) {
        this.statcpea = statcpea;
    }

    public short getCajacpea() {
        return cajacpea;
    }

    public void setCajacpea(short cajacpea) {
        this.cajacpea = cajacpea;
    }

    public int getTrancpea() {
        return trancpea;
    }

    public void setTrancpea(int trancpea) {
        this.trancpea = trancpea;
    }

    public short getStabcpea() {
        return stabcpea;
    }

    public void setStabcpea(short stabcpea) {
        this.stabcpea = stabcpea;
    }

    public int getFechcpea() {
        return fechcpea;
    }

    public void setFechcpea(int fechcpea) {
        this.fechcpea = fechcpea;
    }

    public String getCam1cpea() {
        return cam1cpea;
    }

    public void setCam1cpea(String cam1cpea) {
        this.cam1cpea = cam1cpea;
    }

    public BigDecimal getCam2cpea() {
        return cam2cpea;
    }

    public void setCam2cpea(BigDecimal cam2cpea) {
        this.cam2cpea = cam2cpea;
    }

    public Date getCam3cpea() {
        return cam3cpea;
    }

    public void setCam3cpea(Date cam3cpea) {
        this.cam3cpea = cam3cpea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cabvpeaPK != null ? cabvpeaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cabvpea)) {
            return false;
        }
        Cabvpea other = (Cabvpea) object;
        if ((this.cabvpeaPK == null && other.cabvpeaPK != null)
                || (this.cabvpeaPK != null && !this.cabvpeaPK.equals(other.cabvpeaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Cabvpea[ cabvpeaPK=" + cabvpeaPK + " ]";
    }

}
