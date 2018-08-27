/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eve0017909
 */
@Entity
@Table(name = "CABVFDP")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Cabvfdp.findAll",
                           query = "SELECT c FROM Cabvfdp c"),
        @NamedQuery(name = "Cabvfdp.findByCodfcfdp",
                    query = "SELECT c FROM Cabvfdp c WHERE c.codfcfdp = :codfcfdp"),
        @NamedQuery(name = "Cabvfdp.findByDesfcfdp",
                    query = "SELECT c FROM Cabvfdp c WHERE c.desfcfdp = :desfcfdp"),
        @NamedQuery(name = "Cabvfdp.findByEsrgcfdp",
                    query = "SELECT c FROM Cabvfdp c WHERE c.esrgcfdp = :esrgcfdp")})
public class Cabvfdp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "CODFCFDP")
    private Short codfcfdp;

    @Basic(optional = false)
    @Column(name = "DESFCFDP")
    private String desfcfdp;

    @Basic(optional = false)
    @Column(name = "ESRGCFDP")
    private Character esrgcfdp;

    public Cabvfdp() {
    }

    public Cabvfdp(Short codfcfdp) {
        this.codfcfdp = codfcfdp;
    }

    public Cabvfdp(Short codfcfdp, String desfcfdp, Character esrgcfdp) {
        this.codfcfdp = codfcfdp;
        this.desfcfdp = desfcfdp;
        this.esrgcfdp = esrgcfdp;
    }

    public Short getCodfcfdp() {
        return codfcfdp;
    }

    public void setCodfcfdp(Short codfcfdp) {
        this.codfcfdp = codfcfdp;
    }

    public String getDesfcfdp() {
        return desfcfdp;
    }

    public void setDesfcfdp(String desfcfdp) {
        this.desfcfdp = desfcfdp;
    }

    public Character getEsrgcfdp() {
        return esrgcfdp;
    }

    public void setEsrgcfdp(Character esrgcfdp) {
        this.esrgcfdp = esrgcfdp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codfcfdp != null ? codfcfdp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cabvfdp)) {
            return false;
        }
        Cabvfdp other = (Cabvfdp) object;
        if ((this.codfcfdp == null && other.codfcfdp != null)
                || (this.codfcfdp != null && !this.codfcfdp.equals(other.codfcfdp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Cabvfdp[ codfcfdp=" + codfcfdp + " ]";
    }

}
