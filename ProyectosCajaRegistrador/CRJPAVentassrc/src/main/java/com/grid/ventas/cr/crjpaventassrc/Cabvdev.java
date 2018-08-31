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
@Table(name = "CABVDEV")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Cabvdev.findAll",
                           query = "SELECT c FROM Cabvdev c"),
        @NamedQuery(name = "Cabvdev.findByCorrcdev",
                    query = "SELECT c FROM Cabvdev c WHERE c.cabvdevPK.corrcdev = :corrcdev"),
        @NamedQuery(name = "Cabvdev.findByTrancdev",
                    query = "SELECT c FROM Cabvdev c WHERE c.cabvdevPK.trancdev = :trancdev"),
        @NamedQuery(name = "Cabvdev.findByAfilcdev",
                    query = "SELECT c FROM Cabvdev c WHERE c.cabvdevPK.afilcdev = :afilcdev"),
        @NamedQuery(name = "Cabvdev.findByCajacdev",
                    query = "SELECT c FROM Cabvdev c WHERE c.cabvdevPK.cajacdev = :cajacdev"),
        @NamedQuery(name = "Cabvdev.findByTievcdev",
                    query = "SELECT c FROM Cabvdev c WHERE c.cabvdevPK.tievcdev = :tievcdev"),
        @NamedQuery(name = "Cabvdev.findByTiedcdev",
                    query = "SELECT c FROM Cabvdev c WHERE c.tiedcdev = :tiedcdev"),
        @NamedQuery(name = "Cabvdev.findByFecdcdev",
                    query = "SELECT c FROM Cabvdev c WHERE c.fecdcdev = :fecdcdev"),
        @NamedQuery(name = "Cabvdev.findByStatcdev",
                    query = "SELECT c FROM Cabvdev c WHERE c.statcdev = :statcdev")})
public class Cabvdev implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected CabvdevPK cabvdevPK;

    @Basic(optional = false)
    @Column(name = "TIEDCDEV")
    private String tiedcdev;

    @Basic(optional = false)
    @Column(name = "FECDCDEV")
    @Temporal(TemporalType.DATE)
    private Date fecdcdev;

    @Basic(optional = false)
    @Column(name = "STATCDEV")
    private Character statcdev;

    public Cabvdev() {
    }

    public Cabvdev(CabvdevPK cabvdevPK) {
        this.cabvdevPK = cabvdevPK;
    }

    public Cabvdev(CabvdevPK cabvdevPK, String tiedcdev, Date fecdcdev, Character statcdev) {
        this.cabvdevPK = cabvdevPK;
        this.tiedcdev = tiedcdev;
        this.fecdcdev = fecdcdev;
        this.statcdev = statcdev;
    }

    public Cabvdev(int corrcdev, int trancdev, String afilcdev, short cajacdev, String tievcdev) {
        this.cabvdevPK = new CabvdevPK(corrcdev, trancdev, afilcdev, cajacdev, tievcdev);
    }

    public CabvdevPK getCabvdevPK() {
        return cabvdevPK;
    }

    public void setCabvdevPK(CabvdevPK cabvdevPK) {
        this.cabvdevPK = cabvdevPK;
    }

    public String getTiedcdev() {
        return tiedcdev;
    }

    public void setTiedcdev(String tiedcdev) {
        this.tiedcdev = tiedcdev;
    }

    public Date getFecdcdev() {
        return fecdcdev;
    }

    public void setFecdcdev(Date fecdcdev) {
        this.fecdcdev = fecdcdev;
    }

    public Character getStatcdev() {
        return statcdev;
    }

    public void setStatcdev(Character statcdev) {
        this.statcdev = statcdev;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cabvdevPK != null ? cabvdevPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cabvdev)) {
            return false;
        }
        Cabvdev other = (Cabvdev) object;
        if ((this.cabvdevPK == null && other.cabvdevPK != null)
                || (this.cabvdevPK != null && !this.cabvdevPK.equals(other.cabvdevPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Cabvdev[ cabvdevPK=" + cabvdevPK + " ]";
    }

}
