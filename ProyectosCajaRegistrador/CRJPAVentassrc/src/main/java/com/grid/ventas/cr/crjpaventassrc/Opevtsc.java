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
@Table(name = "OPEVTSC")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Opevtsc.findAll",
                           query = "SELECT o FROM Opevtsc o"),
        @NamedQuery(name = "Opevtsc.findByTienotsc",
                    query = "SELECT o FROM Opevtsc o WHERE o.opevtscPK.tienotsc = :tienotsc"),
        @NamedQuery(name = "Opevtsc.findByCajaotsc",
                    query = "SELECT o FROM Opevtsc o WHERE o.opevtscPK.cajaotsc = :cajaotsc"),
        @NamedQuery(name = "Opevtsc.findByTranotsc",
                    query = "SELECT o FROM Opevtsc o WHERE o.opevtscPK.tranotsc = :tranotsc"),
        @NamedQuery(name = "Opevtsc.findBySeriotsc",
                    query = "SELECT o FROM Opevtsc o WHERE o.seriotsc = :seriotsc"),
        @NamedQuery(name = "Opevtsc.findByEstaotsc",
                    query = "SELECT o FROM Opevtsc o WHERE o.estaotsc = :estaotsc"),
        @NamedQuery(name = "Opevtsc.findByNumcotsc",
                    query = "SELECT o FROM Opevtsc o WHERE o.numcotsc = :numcotsc"),
        @NamedQuery(name = "Opevtsc.findByTmstotsc",
                    query = "SELECT o FROM Opevtsc o WHERE o.tmstotsc = :tmstotsc"),
        @NamedQuery(name = "Opevtsc.findByDis1otsc",
                    query = "SELECT o FROM Opevtsc o WHERE o.dis1otsc = :dis1otsc"),
        @NamedQuery(name = "Opevtsc.findByDis2otsc",
                    query = "SELECT o FROM Opevtsc o WHERE o.dis2otsc = :dis2otsc"),
        @NamedQuery(name = "Opevtsc.findByDis3otsc",
                    query = "SELECT o FROM Opevtsc o WHERE o.dis3otsc = :dis3otsc"),
        @NamedQuery(name = "Opevtsc.findBySereotsc",
                    query = "SELECT o FROM Opevtsc o WHERE o.sereotsc = :sereotsc")})
public class Opevtsc implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected OpevtscPK opevtscPK;

    @Basic(optional = false)
    @Column(name = "SERIOTSC")
    private String seriotsc;

    @Basic(optional = false)
    @Column(name = "ESTAOTSC")
    private Character estaotsc;

    @Basic(optional = false)
    @Column(name = "NUMCOTSC")
    private long numcotsc;

    @Basic(optional = false)
    @Column(name = "TMSTOTSC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmstotsc;

    @Basic(optional = false)
    @Column(name = "DIS1OTSC")
    private String dis1otsc;

    @Basic(optional = false)
    @Column(name = "DIS2OTSC")
    private long dis2otsc;

    @Basic(optional = false)
    @Column(name = "DIS3OTSC")
    private Character dis3otsc;

    @Basic(optional = false)
    @Column(name = "SEREOTSC")
    private String sereotsc;

    public Opevtsc() {
    }

    public Opevtsc(OpevtscPK opevtscPK) {
        this.opevtscPK = opevtscPK;
    }

    public Opevtsc(OpevtscPK opevtscPK, String seriotsc, Character estaotsc, long numcotsc, Date tmstotsc,
                   String dis1otsc, long dis2otsc, Character dis3otsc, String sereotsc) {
        this.opevtscPK = opevtscPK;
        this.seriotsc = seriotsc;
        this.estaotsc = estaotsc;
        this.numcotsc = numcotsc;
        this.tmstotsc = tmstotsc;
        this.dis1otsc = dis1otsc;
        this.dis2otsc = dis2otsc;
        this.dis3otsc = dis3otsc;
        this.sereotsc = sereotsc;
    }

    public Opevtsc(short tienotsc, short cajaotsc, int tranotsc) {
        this.opevtscPK = new OpevtscPK(tienotsc, cajaotsc, tranotsc);
    }

    public OpevtscPK getOpevtscPK() {
        return opevtscPK;
    }

    public void setOpevtscPK(OpevtscPK opevtscPK) {
        this.opevtscPK = opevtscPK;
    }

    public String getSeriotsc() {
        return seriotsc;
    }

    public void setSeriotsc(String seriotsc) {
        this.seriotsc = seriotsc;
    }

    public Character getEstaotsc() {
        return estaotsc;
    }

    public void setEstaotsc(Character estaotsc) {
        this.estaotsc = estaotsc;
    }

    public long getNumcotsc() {
        return numcotsc;
    }

    public void setNumcotsc(long numcotsc) {
        this.numcotsc = numcotsc;
    }

    public Date getTmstotsc() {
        return tmstotsc;
    }

    public void setTmstotsc(Date tmstotsc) {
        this.tmstotsc = tmstotsc;
    }

    public String getDis1otsc() {
        return dis1otsc;
    }

    public void setDis1otsc(String dis1otsc) {
        this.dis1otsc = dis1otsc;
    }

    public long getDis2otsc() {
        return dis2otsc;
    }

    public void setDis2otsc(long dis2otsc) {
        this.dis2otsc = dis2otsc;
    }

    public Character getDis3otsc() {
        return dis3otsc;
    }

    public void setDis3otsc(Character dis3otsc) {
        this.dis3otsc = dis3otsc;
    }

    public String getSereotsc() {
        return sereotsc;
    }

    public void setSereotsc(String sereotsc) {
        this.sereotsc = sereotsc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opevtscPK != null ? opevtscPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opevtsc)) {
            return false;
        }
        Opevtsc other = (Opevtsc) object;
        if ((this.opevtscPK == null && other.opevtscPK != null)
                || (this.opevtscPK != null && !this.opevtscPK.equals(other.opevtscPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Opevtsc[ opevtscPK=" + opevtscPK + " ]";
    }

}
