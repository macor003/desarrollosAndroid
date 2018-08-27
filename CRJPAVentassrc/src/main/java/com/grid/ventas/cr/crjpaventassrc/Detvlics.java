/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;

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
@Table(name = "DETVLICS")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Detvlics.findAll",
                           query = "SELECT d FROM Detvlics d"),
        @NamedQuery(name = "Detvlics.findByDetvnlic",
                    query = "SELECT d FROM Detvlics d WHERE d.detvlicsPK.detvnlic = :detvnlic"),
        @NamedQuery(name = "Detvlics.findByDetvnatc",
                    query = "SELECT d FROM Detvlics d WHERE d.detvlicsPK.detvnatc = :detvnatc"),
        @NamedQuery(name = "Detvlics.findByDetvcant",
                    query = "SELECT d FROM Detvlics d WHERE d.detvcant = :detvcant"),
        @NamedQuery(name = "Detvlics.findByDetvproc",
                    query = "SELECT d FROM Detvlics d WHERE d.detvproc = :detvproc"),
        @NamedQuery(name = "Detvlics.findByDetvaror",
                    query = "SELECT d FROM Detvlics d WHERE d.detvaror = :detvaror")})
public class Detvlics implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected DetvlicsPK detvlicsPK;

    @Column(name = "DETVCANT")
    private Integer detvcant;

    @Column(name = "DETVPROC")
    private String detvproc;

    @Column(name = "DETVAROR")
    private String detvaror;

    public Detvlics() {
    }

    public Detvlics(DetvlicsPK detvlicsPK) {
        this.detvlicsPK = detvlicsPK;
    }

    public Detvlics(String detvnlic, String detvnatc) {
        this.detvlicsPK = new DetvlicsPK(detvnlic, detvnatc);
    }

    public DetvlicsPK getDetvlicsPK() {
        return detvlicsPK;
    }

    public void setDetvlicsPK(DetvlicsPK detvlicsPK) {
        this.detvlicsPK = detvlicsPK;
    }

    public Integer getDetvcant() {
        return detvcant;
    }

    public void setDetvcant(Integer detvcant) {
        this.detvcant = detvcant;
    }

    public String getDetvproc() {
        return detvproc;
    }

    public void setDetvproc(String detvproc) {
        this.detvproc = detvproc;
    }

    public String getDetvaror() {
        return detvaror;
    }

    public void setDetvaror(String detvaror) {
        this.detvaror = detvaror;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detvlicsPK != null ? detvlicsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detvlics)) {
            return false;
        }
        Detvlics other = (Detvlics) object;
        if ((this.detvlicsPK == null && other.detvlicsPK != null)
                || (this.detvlicsPK != null && !this.detvlicsPK.equals(other.detvlicsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Detvlics[ detvlicsPK=" + detvlicsPK + " ]";
    }

}
