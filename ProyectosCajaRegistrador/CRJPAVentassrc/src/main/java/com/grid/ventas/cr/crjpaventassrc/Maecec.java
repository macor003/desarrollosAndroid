/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;

import javax.persistence.Basic;
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
@Table(name = "MAECEC")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Maecec.findAll",
                           query = "SELECT m FROM Maecec m"),
        @NamedQuery(name = "Maecec.findByNumtie",
                    query = "SELECT m FROM Maecec m WHERE m.maececPK.numtie = :numtie"),
        @NamedQuery(name = "Maecec.findByCaja",
                    query = "SELECT m FROM Maecec m WHERE m.maececPK.caja = :caja"),
        @NamedQuery(name = "Maecec.findByTransa",
                    query = "SELECT m FROM Maecec m WHERE m.maececPK.transa = :transa"),
        @NamedQuery(name = "Maecec.findByTipo",
                    query = "SELECT m FROM Maecec m WHERE m.tipo = :tipo"),
        @NamedQuery(name = "Maecec.findByCodcte",
                    query = "SELECT m FROM Maecec m WHERE m.codcte = :codcte"),
        @NamedQuery(name = "Maecec.findByNomcte",
                    query = "SELECT m FROM Maecec m WHERE m.nomcte = :nomcte")})
public class Maecec implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected MaececPK maececPK;

    @Basic(optional = false)
    @Column(name = "TIPO")
    private short tipo;

    @Basic(optional = false)
    @Column(name = "CODCTE")
    private String codcte;

    @Basic(optional = false)
    @Column(name = "NOMCTE")
    private String nomcte;

    public Maecec() {
    }

    public Maecec(MaececPK maececPK) {
        this.maececPK = maececPK;
    }

    public Maecec(MaececPK maececPK, short tipo, String codcte, String nomcte) {
        this.maececPK = maececPK;
        this.tipo = tipo;
        this.codcte = codcte;
        this.nomcte = nomcte;
    }

    public Maecec(short numtie, short caja, int transa) {
        this.maececPK = new MaececPK(numtie, caja, transa);
    }

    public MaececPK getMaececPK() {
        return maececPK;
    }

    public void setMaececPK(MaececPK maececPK) {
        this.maececPK = maececPK;
    }

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
    }

    public String getCodcte() {
        return codcte;
    }

    public void setCodcte(String codcte) {
        this.codcte = codcte;
    }

    public String getNomcte() {
        return nomcte;
    }

    public void setNomcte(String nomcte) {
        this.nomcte = nomcte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maececPK != null ? maececPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maecec)) {
            return false;
        }
        Maecec other = (Maecec) object;
        if ((this.maececPK == null && other.maececPK != null)
                || (this.maececPK != null && !this.maececPK.equals(other.maececPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Maecec[ maececPK=" + maececPK + " ]";
    }

}
