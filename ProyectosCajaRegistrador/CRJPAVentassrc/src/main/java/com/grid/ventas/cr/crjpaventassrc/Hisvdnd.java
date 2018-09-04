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
@Table(name = "HISVDND")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Hisvdnd.findAll",
                           query = "SELECT h FROM Hisvdnd h"),
        @NamedQuery(name = "Hisvdnd.findByStathdnd",
                    query = "SELECT h FROM Hisvdnd h WHERE h.stathdnd = :stathdnd"),
        @NamedQuery(name = "Hisvdnd.findByNumdhdnd",
                    query = "SELECT h FROM Hisvdnd h WHERE h.hisvdndPK.numdhdnd = :numdhdnd"),
        @NamedQuery(name = "Hisvdnd.findByTdadhdnd",
                    query = "SELECT h FROM Hisvdnd h WHERE h.hisvdndPK.tdadhdnd = :tdadhdnd"),
        @NamedQuery(name = "Hisvdnd.findByTdarhdnd",
                    query = "SELECT h FROM Hisvdnd h WHERE h.tdarhdnd = :tdarhdnd"),
        @NamedQuery(name = "Hisvdnd.findByTipdhdnd",
                    query = "SELECT h FROM Hisvdnd h WHERE h.hisvdndPK.tipdhdnd = :tipdhdnd"),
        @NamedQuery(name = "Hisvdnd.findByCoprhdnd",
                    query = "SELECT h FROM Hisvdnd h WHERE h.hisvdndPK.coprhdnd = :coprhdnd"),
        @NamedQuery(name = "Hisvdnd.findByFirehdnd",
                    query = "SELECT h FROM Hisvdnd h WHERE h.firehdnd = :firehdnd"),
        @NamedQuery(name = "Hisvdnd.findByNubuhdnd",
                    query = "SELECT h FROM Hisvdnd h WHERE h.nubuhdnd = :nubuhdnd"),
        @NamedQuery(name = "Hisvdnd.findByNrochdnd",
                    query = "SELECT h FROM Hisvdnd h WHERE h.nrochdnd = :nrochdnd"),
        @NamedQuery(name = "Hisvdnd.findByFerehdnd",
                    query = "SELECT h FROM Hisvdnd h WHERE h.ferehdnd = :ferehdnd"),
        @NamedQuery(name = "Hisvdnd.findByFeenhdnd",
                    query = "SELECT h FROM Hisvdnd h WHERE h.feenhdnd = :feenhdnd"),
        @NamedQuery(name = "Hisvdnd.findByFeprhdnd",
                    query = "SELECT h FROM Hisvdnd h WHERE h.feprhdnd = :feprhdnd"),
        @NamedQuery(name = "Hisvdnd.findByOperhdnd",
                    query = "SELECT h FROM Hisvdnd h WHERE h.operhdnd = :operhdnd"),
        @NamedQuery(name = "Hisvdnd.findByFil1hdnd",
                    query = "SELECT h FROM Hisvdnd h WHERE h.fil1hdnd = :fil1hdnd"),
        @NamedQuery(name = "Hisvdnd.findByFil2hdnd",
                    query = "SELECT h FROM Hisvdnd h WHERE h.fil2hdnd = :fil2hdnd")})
public class Hisvdnd implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected HisvdndPK hisvdndPK;

    @Basic(optional = false)
    @Column(name = "STATHDND")
    private Character stathdnd;

    @Basic(optional = false)
    @Column(name = "TDARHDND")
    private String tdarhdnd;

    @Basic(optional = false)
    @Column(name = "FIREHDND")
    private int firehdnd;

    @Basic(optional = false)
    @Column(name = "NUBUHDND")
    private short nubuhdnd;

    @Basic(optional = false)
    @Column(name = "NROCHDND")
    private long nrochdnd;

    @Basic(optional = false)
    @Column(name = "FEREHDND")
    @Temporal(TemporalType.DATE)
    private Date ferehdnd;

    @Basic(optional = false)
    @Column(name = "FEENHDND")
    @Temporal(TemporalType.DATE)
    private Date feenhdnd;

    @Basic(optional = false)
    @Column(name = "FEPRHDND")
    @Temporal(TemporalType.DATE)
    private Date feprhdnd;

    @Basic(optional = false)
    @Column(name = "OPERHDND")
    private String operhdnd;

    @Basic(optional = false)
    @Column(name = "FIL1HDND")
    private String fil1hdnd;

    @Basic(optional = false)
    @Column(name = "FIL2HDND")
    private long fil2hdnd;

    public Hisvdnd() {
    }

    public Hisvdnd(HisvdndPK hisvdndPK) {
        this.hisvdndPK = hisvdndPK;
    }

    public Hisvdnd(HisvdndPK hisvdndPK, Character stathdnd, String tdarhdnd, int firehdnd, short nubuhdnd,
                   long nrochdnd, Date ferehdnd, Date feenhdnd, Date feprhdnd, String operhdnd, String fil1hdnd,
                   long fil2hdnd) {
        this.hisvdndPK = hisvdndPK;
        this.stathdnd = stathdnd;
        this.tdarhdnd = tdarhdnd;
        this.firehdnd = firehdnd;
        this.nubuhdnd = nubuhdnd;
        this.nrochdnd = nrochdnd;
        this.ferehdnd = ferehdnd;
        this.feenhdnd = feenhdnd;
        this.feprhdnd = feprhdnd;
        this.operhdnd = operhdnd;
        this.fil1hdnd = fil1hdnd;
        this.fil2hdnd = fil2hdnd;
    }

    public Hisvdnd(String numdhdnd, String tdadhdnd, short tipdhdnd, int coprhdnd) {
        this.hisvdndPK = new HisvdndPK(numdhdnd, tdadhdnd, tipdhdnd, coprhdnd);
    }

    public HisvdndPK getHisvdndPK() {
        return hisvdndPK;
    }

    public void setHisvdndPK(HisvdndPK hisvdndPK) {
        this.hisvdndPK = hisvdndPK;
    }

    public Character getStathdnd() {
        return stathdnd;
    }

    public void setStathdnd(Character stathdnd) {
        this.stathdnd = stathdnd;
    }

    public String getTdarhdnd() {
        return tdarhdnd;
    }

    public void setTdarhdnd(String tdarhdnd) {
        this.tdarhdnd = tdarhdnd;
    }

    public int getFirehdnd() {
        return firehdnd;
    }

    public void setFirehdnd(int firehdnd) {
        this.firehdnd = firehdnd;
    }

    public short getNubuhdnd() {
        return nubuhdnd;
    }

    public void setNubuhdnd(short nubuhdnd) {
        this.nubuhdnd = nubuhdnd;
    }

    public long getNrochdnd() {
        return nrochdnd;
    }

    public void setNrochdnd(long nrochdnd) {
        this.nrochdnd = nrochdnd;
    }

    public Date getFerehdnd() {
        return ferehdnd;
    }

    public void setFerehdnd(Date ferehdnd) {
        this.ferehdnd = ferehdnd;
    }

    public Date getFeenhdnd() {
        return feenhdnd;
    }

    public void setFeenhdnd(Date feenhdnd) {
        this.feenhdnd = feenhdnd;
    }

    public Date getFeprhdnd() {
        return feprhdnd;
    }

    public void setFeprhdnd(Date feprhdnd) {
        this.feprhdnd = feprhdnd;
    }

    public String getOperhdnd() {
        return operhdnd;
    }

    public void setOperhdnd(String operhdnd) {
        this.operhdnd = operhdnd;
    }

    public String getFil1hdnd() {
        return fil1hdnd;
    }

    public void setFil1hdnd(String fil1hdnd) {
        this.fil1hdnd = fil1hdnd;
    }

    public long getFil2hdnd() {
        return fil2hdnd;
    }

    public void setFil2hdnd(long fil2hdnd) {
        this.fil2hdnd = fil2hdnd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hisvdndPK != null ? hisvdndPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hisvdnd)) {
            return false;
        }
        Hisvdnd other = (Hisvdnd) object;
        if ((this.hisvdndPK == null && other.hisvdndPK != null)
                || (this.hisvdndPK != null && !this.hisvdndPK.equals(other.hisvdndPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Hisvdnd[ hisvdndPK=" + hisvdndPK + " ]";
    }

}
