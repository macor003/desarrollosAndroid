/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;
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
@Table(name = "MAEVCLAACR")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Maevclaacr.findAll",
                           query = "SELECT m FROM Maevclaacr m"),
        @NamedQuery(name = "Maevclaacr.findByNumacr",
                    query = "SELECT m FROM Maevclaacr m WHERE m.maevclaacrPK.numacr = :numacr"),
        @NamedQuery(name = "Maevclaacr.findByClientacr",
                    query = "SELECT m FROM Maevclaacr m WHERE m.maevclaacrPK.clientacr = :clientacr"),
        @NamedQuery(name = "Maevclaacr.findByClavacr",
                    query = "SELECT m FROM Maevclaacr m WHERE m.clavacr = :clavacr"),
        @NamedQuery(name = "Maevclaacr.findByFechacr",
                    query = "SELECT m FROM Maevclaacr m WHERE m.fechacr = :fechacr"),
        @NamedQuery(name = "Maevclaacr.findByCajecr",
                    query = "SELECT m FROM Maevclaacr m WHERE m.cajecr = :cajecr"),
        @NamedQuery(name = "Maevclaacr.findByTiendcr",
                    query = "SELECT m FROM Maevclaacr m WHERE m.tiendcr = :tiendcr"),
        @NamedQuery(name = "Maevclaacr.findByFechaact",
                    query = "SELECT m FROM Maevclaacr m WHERE m.fechaact = :fechaact"),
        @NamedQuery(name = "Maevclaacr.findByCajeact",
                    query = "SELECT m FROM Maevclaacr m WHERE m.cajeact = :cajeact"),
        @NamedQuery(name = "Maevclaacr.findByTendact",
                    query = "SELECT m FROM Maevclaacr m WHERE m.tendact = :tendact"),
        @NamedQuery(name = "Maevclaacr.findByReplicr",
                    query = "SELECT m FROM Maevclaacr m WHERE m.replicr = :replicr"),
        @NamedQuery(name = "Maevclaacr.findByActcr",
                    query = "SELECT m FROM Maevclaacr m WHERE m.actcr = :actcr")})
public class Maevclaacr implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected MaevclaacrPK maevclaacrPK;

    @Column(name = "CLAVACR")
    private String clavacr;

    @Basic(optional = false)
    @Column(name = "FECHACR")
    @Temporal(TemporalType.DATE)
    private Date fechacr;

    @Column(name = "CAJECR")
    private String cajecr;

    @Column(name = "TIENDCR")
    private Integer tiendcr;

    @Basic(optional = false)
    @Column(name = "FECHAACT")
    @Temporal(TemporalType.DATE)
    private Date fechaact;

    @Column(name = "CAJEACT")
    private String cajeact;

    @Column(name = "TENDACT")
    private Integer tendact;

    @Basic(optional = false)
    @Column(name = "REPLICR")
    private Character replicr;

    @Basic(optional = false)
    @Column(name = "ACTCR")
    private Character actcr;

    public Maevclaacr() {
    }

    public Maevclaacr(MaevclaacrPK maevclaacrPK) {
        this.maevclaacrPK = maevclaacrPK;
    }

    public Maevclaacr(MaevclaacrPK maevclaacrPK, Date fechacr, Date fechaact, Character replicr, Character actcr) {
        this.maevclaacrPK = maevclaacrPK;
        this.fechacr = fechacr;
        this.fechaact = fechaact;
        this.replicr = replicr;
        this.actcr = actcr;
    }

    public Maevclaacr(int numacr, String clientacr) {
        this.maevclaacrPK = new MaevclaacrPK(numacr, clientacr);
    }

    public MaevclaacrPK getMaevclaacrPK() {
        return maevclaacrPK;
    }

    public void setMaevclaacrPK(MaevclaacrPK maevclaacrPK) {
        this.maevclaacrPK = maevclaacrPK;
    }

    public String getClavacr() {
        return clavacr;
    }

    public void setClavacr(String clavacr) {
        this.clavacr = clavacr;
    }

    public Date getFechacr() {
        return fechacr;
    }

    public void setFechacr(Date fechacr) {
        this.fechacr = fechacr;
    }

    public String getCajecr() {
        return cajecr;
    }

    public void setCajecr(String cajecr) {
        this.cajecr = cajecr;
    }

    public Integer getTiendcr() {
        return tiendcr;
    }

    public void setTiendcr(Integer tiendcr) {
        this.tiendcr = tiendcr;
    }

    public Date getFechaact() {
        return fechaact;
    }

    public void setFechaact(Date fechaact) {
        this.fechaact = fechaact;
    }

    public String getCajeact() {
        return cajeact;
    }

    public void setCajeact(String cajeact) {
        this.cajeact = cajeact;
    }

    public Integer getTendact() {
        return tendact;
    }

    public void setTendact(Integer tendact) {
        this.tendact = tendact;
    }

    public Character getReplicr() {
        return replicr;
    }

    public void setReplicr(Character replicr) {
        this.replicr = replicr;
    }

    public Character getActcr() {
        return actcr;
    }

    public void setActcr(Character actcr) {
        this.actcr = actcr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maevclaacrPK != null ? maevclaacrPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maevclaacr)) {
            return false;
        }
        Maevclaacr other = (Maevclaacr) object;
        if ((this.maevclaacrPK == null && other.maevclaacrPK != null)
                || (this.maevclaacrPK != null && !this.maevclaacrPK.equals(other.maevclaacrPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Maevclaacr[ maevclaacrPK=" + maevclaacrPK + " ]";
    }

}
