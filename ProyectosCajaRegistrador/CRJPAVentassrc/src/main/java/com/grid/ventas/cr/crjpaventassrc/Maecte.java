/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "MAECTE")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Maecte.findAll",
                           query = "SELECT m FROM Maecte m"),
        @NamedQuery(name = "Maecte.findByRifcte",
                    query = "SELECT m FROM Maecte m WHERE m.rifcte = :rifcte"),
        @NamedQuery(name = "Maecte.findByNitcte",
                    query = "SELECT m FROM Maecte m WHERE m.nitcte = :nitcte"),
        @NamedQuery(name = "Maecte.findByNombre",
                    query = "SELECT m FROM Maecte m WHERE m.nombre = :nombre"),
        @NamedQuery(name = "Maecte.findByDirec1",
                    query = "SELECT m FROM Maecte m WHERE m.direc1 = :direc1"),
        @NamedQuery(name = "Maecte.findByDirec2",
                    query = "SELECT m FROM Maecte m WHERE m.direc2 = :direc2"),
        @NamedQuery(name = "Maecte.findByDomfis",
                    query = "SELECT m FROM Maecte m WHERE m.domfis = :domfis"),
        @NamedQuery(name = "Maecte.findByCodar1",
                    query = "SELECT m FROM Maecte m WHERE m.codar1 = :codar1"),
        @NamedQuery(name = "Maecte.findByTlfno1",
                    query = "SELECT m FROM Maecte m WHERE m.tlfno1 = :tlfno1"),
        @NamedQuery(name = "Maecte.findByTlfno2",
                    query = "SELECT m FROM Maecte m WHERE m.tlfno2 = :tlfno2"),
        @NamedQuery(name = "Maecte.findByCodfax",
                    query = "SELECT m FROM Maecte m WHERE m.codfax = :codfax"),
        @NamedQuery(name = "Maecte.findByNumfax",
                    query = "SELECT m FROM Maecte m WHERE m.numfax = :numfax"),
        @NamedQuery(name = "Maecte.findByTipcte",
                    query = "SELECT m FROM Maecte m WHERE m.tipcte = :tipcte"),
        @NamedQuery(name = "Maecte.findByCta01",
                    query = "SELECT m FROM Maecte m WHERE m.cta01 = :cta01"),
        @NamedQuery(name = "Maecte.findByCta02",
                    query = "SELECT m FROM Maecte m WHERE m.cta02 = :cta02"),
        @NamedQuery(name = "Maecte.findByStafi",
                    query = "SELECT m FROM Maecte m WHERE m.stafi = :stafi"),
        @NamedQuery(name = "Maecte.findByFecins",
                    query = "SELECT m FROM Maecte m WHERE m.fecins = :fecins"),
        @NamedQuery(name = "Maecte.findByFecact",
                    query = "SELECT m FROM Maecte m WHERE m.fecact = :fecact"),
        @NamedQuery(name = "Maecte.findByUsuari",
                    query = "SELECT m FROM Maecte m WHERE m.usuari = :usuari")})
public class Maecte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "RIFCTE")
    private String rifcte;

    @Basic(optional = false)
    @Column(name = "NITCTE")
    private String nitcte;

    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "DIREC1")
    private String direc1;

    @Basic(optional = false)
    @Column(name = "DIREC2")
    private String direc2;

    @Basic(optional = false)
    @Column(name = "DOMFIS")
    private String domfis;

    @Basic(optional = false)
    @Column(name = "CODAR1")
    private short codar1;

    @Basic(optional = false)
    @Column(name = "TLFNO1")
    private long tlfno1;

    @Basic(optional = false)
    @Column(name = "TLFNO2")
    private long tlfno2;

    @Basic(optional = false)
    @Column(name = "CODFAX")
    private short codfax;

    @Basic(optional = false)
    @Column(name = "NUMFAX")
    private long numfax;

    @Basic(optional = false)
    @Column(name = "TIPCTE")
    private Character tipcte;

    @Basic(optional = false)
    @Column(name = "CTA01")
    private String cta01;

    @Basic(optional = false)
    @Column(name = "CTA02")
    private String cta02;

    @Basic(optional = false)
    @Column(name = "STAFI")
    private Character stafi;

    @Basic(optional = false)
    @Column(name = "FECINS")
    @Temporal(TemporalType.DATE)
    private Date fecins;

    @Basic(optional = false)
    @Column(name = "FECACT")
    @Temporal(TemporalType.DATE)
    private Date fecact;

    @Basic(optional = false)
    @Column(name = "USUARI")
    private String usuari;

    public Maecte() {
    }

    public Maecte(String rifcte) {
        this.rifcte = rifcte;
    }

    public Maecte(String rifcte, String nitcte, String nombre, String direc1, String direc2, String domfis,
                  short codar1, long tlfno1, long tlfno2, short codfax, long numfax, Character tipcte,
                  String cta01, String cta02, Character stafi, Date fecins, Date fecact, String usuari) {
        this.rifcte = rifcte;
        this.nitcte = nitcte;
        this.nombre = nombre;
        this.direc1 = direc1;
        this.direc2 = direc2;
        this.domfis = domfis;
        this.codar1 = codar1;
        this.tlfno1 = tlfno1;
        this.tlfno2 = tlfno2;
        this.codfax = codfax;
        this.numfax = numfax;
        this.tipcte = tipcte;
        this.cta01 = cta01;
        this.cta02 = cta02;
        this.stafi = stafi;
        this.fecins = fecins;
        this.fecact = fecact;
        this.usuari = usuari;
    }

    public String getRifcte() {
        return rifcte;
    }

    public void setRifcte(String rifcte) {
        this.rifcte = rifcte;
    }

    public String getNitcte() {
        return nitcte;
    }

    public void setNitcte(String nitcte) {
        this.nitcte = nitcte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirec1() {
        return direc1;
    }

    public void setDirec1(String direc1) {
        this.direc1 = direc1;
    }

    public String getDirec2() {
        return direc2;
    }

    public void setDirec2(String direc2) {
        this.direc2 = direc2;
    }

    public String getDomfis() {
        return domfis;
    }

    public void setDomfis(String domfis) {
        this.domfis = domfis;
    }

    public short getCodar1() {
        return codar1;
    }

    public void setCodar1(short codar1) {
        this.codar1 = codar1;
    }

    public long getTlfno1() {
        return tlfno1;
    }

    public void setTlfno1(long tlfno1) {
        this.tlfno1 = tlfno1;
    }

    public long getTlfno2() {
        return tlfno2;
    }

    public void setTlfno2(long tlfno2) {
        this.tlfno2 = tlfno2;
    }

    public short getCodfax() {
        return codfax;
    }

    public void setCodfax(short codfax) {
        this.codfax = codfax;
    }

    public long getNumfax() {
        return numfax;
    }

    public void setNumfax(long numfax) {
        this.numfax = numfax;
    }

    public Character getTipcte() {
        return tipcte;
    }

    public void setTipcte(Character tipcte) {
        this.tipcte = tipcte;
    }

    public String getCta01() {
        return cta01;
    }

    public void setCta01(String cta01) {
        this.cta01 = cta01;
    }

    public String getCta02() {
        return cta02;
    }

    public void setCta02(String cta02) {
        this.cta02 = cta02;
    }

    public Character getStafi() {
        return stafi;
    }

    public void setStafi(Character stafi) {
        this.stafi = stafi;
    }

    public Date getFecins() {
        return fecins;
    }

    public void setFecins(Date fecins) {
        this.fecins = fecins;
    }

    public Date getFecact() {
        return fecact;
    }

    public void setFecact(Date fecact) {
        this.fecact = fecact;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rifcte != null ? rifcte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maecte)) {
            return false;
        }
        Maecte other = (Maecte) object;
        if ((this.rifcte == null && other.rifcte != null)
                || (this.rifcte != null && !this.rifcte.equals(other.rifcte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Maecte[ rifcte=" + rifcte + " ]";
    }

}
