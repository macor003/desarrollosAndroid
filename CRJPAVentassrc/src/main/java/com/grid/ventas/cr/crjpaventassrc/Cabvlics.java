/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "CABVLICS")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Cabvlics.findAll",
                           query = "SELECT c FROM Cabvlics c"),
        @NamedQuery(name = "Cabvlics.findByCabvnlic",
                    query = "SELECT c FROM Cabvlics c WHERE c.cabvlicsPK.cabvnlic = :cabvnlic"),
        @NamedQuery(name = "Cabvlics.findByCabvntda",
                    query = "SELECT c FROM Cabvlics c WHERE c.cabvlicsPK.cabvntda = :cabvntda"),
        @NamedQuery(name = "Cabvlics.findByCabvfech",
                    query = "SELECT c FROM Cabvlics c WHERE c.cabvfech = :cabvfech"),
        @NamedQuery(name = "Cabvlics.findByCabvesta",
                    query = "SELECT c FROM Cabvlics c WHERE c.cabvesta = :cabvesta"),
        @NamedQuery(name = "Cabvlics.findByCabvproc",
                    query = "SELECT c FROM Cabvlics c WHERE c.cabvproc = :cabvproc"),
        @NamedQuery(name = "Cabvlics.findByCabvaror",
                    query = "SELECT c FROM Cabvlics c WHERE c.cabvaror = :cabvaror"),
        @NamedQuery(name = "Cabvlics.findByCabvnntr",
                    query = "SELECT c FROM Cabvlics c WHERE c.cabvnntr = :cabvnntr")})
public class Cabvlics implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected CabvlicsPK cabvlicsPK;

    @Column(name = "CABVFECH")
    @Temporal(TemporalType.DATE)
    private Date cabvfech;

    @Column(name = "CABVESTA")
    private Character cabvesta;

    @Column(name = "CABVPROC")
    private String cabvproc;

    @Column(name = "CABVAROR")
    private String cabvaror;

    @Column(name = "CABVNNTR")
    private String cabvnntr;

    public Cabvlics() {
    }

    public Cabvlics(CabvlicsPK cabvlicsPK) {
        this.cabvlicsPK = cabvlicsPK;
    }

    public Cabvlics(String cabvnlic, String cabvntda) {
        this.cabvlicsPK = new CabvlicsPK(cabvnlic, cabvntda);
    }

    public CabvlicsPK getCabvlicsPK() {
        return cabvlicsPK;
    }

    public void setCabvlicsPK(CabvlicsPK cabvlicsPK) {
        this.cabvlicsPK = cabvlicsPK;
    }

    public Date getCabvfech() {
        return cabvfech;
    }

    public void setCabvfech(Date cabvfech) {
        this.cabvfech = cabvfech;
    }

    public Character getCabvesta() {
        return cabvesta;
    }

    public void setCabvesta(Character cabvesta) {
        this.cabvesta = cabvesta;
    }

    public String getCabvproc() {
        return cabvproc;
    }

    public void setCabvproc(String cabvproc) {
        this.cabvproc = cabvproc;
    }

    public String getCabvaror() {
        return cabvaror;
    }

    public void setCabvaror(String cabvaror) {
        this.cabvaror = cabvaror;
    }

    public String getCabvnntr() {
        return cabvnntr;
    }

    public void setCabvnntr(String cabvnntr) {
        this.cabvnntr = cabvnntr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cabvlicsPK != null ? cabvlicsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cabvlics)) {
            return false;
        }
        Cabvlics other = (Cabvlics) object;
        if ((this.cabvlicsPK == null && other.cabvlicsPK != null)
                || (this.cabvlicsPK != null && !this.cabvlicsPK.equals(other.cabvlicsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Cabvlics[ cabvlicsPK=" + cabvlicsPK + " ]";
    }

}
