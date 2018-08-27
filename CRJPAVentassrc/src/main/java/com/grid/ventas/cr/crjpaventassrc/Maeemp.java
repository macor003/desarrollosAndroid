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
@Table(name = "MAEEMP")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Maeemp.findAll",
                           query = "SELECT m FROM Maeemp m"),
        @NamedQuery(name = "Maeemp.findByCodemp",
                    query = "SELECT m FROM Maeemp m WHERE m.codemp = :codemp"),
        @NamedQuery(name = "Maeemp.findByNombre",
                    query = "SELECT m FROM Maeemp m WHERE m.nombre = :nombre"),
        @NamedQuery(name = "Maeemp.findByApelli",
                    query = "SELECT m FROM Maeemp m WHERE m.apelli = :apelli"),
        @NamedQuery(name = "Maeemp.findByCedula",
                    query = "SELECT m FROM Maeemp m WHERE m.cedula = :cedula"),
        @NamedQuery(name = "Maeemp.findByDirecc",
                    query = "SELECT m FROM Maeemp m WHERE m.direcc = :direcc"),
        @NamedQuery(name = "Maeemp.findByDomici",
                    query = "SELECT m FROM Maeemp m WHERE m.domici = :domici"),
        @NamedQuery(name = "Maeemp.findByFecnac",
                    query = "SELECT m FROM Maeemp m WHERE m.fecnac = :fecnac"),
        @NamedQuery(name = "Maeemp.findByCredit",
                    query = "SELECT m FROM Maeemp m WHERE m.credit = :credit"),
        @NamedQuery(name = "Maeemp.findByDispon",
                    query = "SELECT m FROM Maeemp m WHERE m.dispon = :dispon"),
        @NamedQuery(name = "Maeemp.findByCodar1",
                    query = "SELECT m FROM Maeemp m WHERE m.codar1 = :codar1"),
        @NamedQuery(name = "Maeemp.findByTlfno1",
                    query = "SELECT m FROM Maeemp m WHERE m.tlfno1 = :tlfno1"),
        @NamedQuery(name = "Maeemp.findByFecact",
                    query = "SELECT m FROM Maeemp m WHERE m.fecact = :fecact"),
        @NamedQuery(name = "Maeemp.findByUsuari",
                    query = "SELECT m FROM Maeemp m WHERE m.usuari = :usuari")})
public class Maeemp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "CODEMP")
    private Integer codemp;

    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "APELLI")
    private String apelli;

    @Basic(optional = false)
    @Column(name = "CEDULA")
    private String cedula;

    @Basic(optional = false)
    @Column(name = "DIRECC")
    private String direcc;

    @Basic(optional = false)
    @Column(name = "DOMICI")
    private String domici;

    @Basic(optional = false)
    @Column(name = "FECNAC")
    @Temporal(TemporalType.DATE)
    private Date fecnac;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "CREDIT")
    private BigDecimal credit;

    @Basic(optional = false)
    @Column(name = "DISPON")
    private BigDecimal dispon;

    @Basic(optional = false)
    @Column(name = "CODAR1")
    private short codar1;

    @Basic(optional = false)
    @Column(name = "TLFNO1")
    private long tlfno1;

    @Basic(optional = false)
    @Column(name = "FECACT")
    @Temporal(TemporalType.DATE)
    private Date fecact;

    @Basic(optional = false)
    @Column(name = "USUARI")
    private String usuari;

    public Maeemp() {
    }

    public Maeemp(Integer codemp) {
        this.codemp = codemp;
    }

    public Maeemp(Integer codemp, String nombre, String apelli, String cedula, String direcc, String domici,
                  Date fecnac, BigDecimal credit, BigDecimal dispon, short codar1, long tlfno1, Date fecact,
                  String usuari) {
        this.codemp = codemp;
        this.nombre = nombre;
        this.apelli = apelli;
        this.cedula = cedula;
        this.direcc = direcc;
        this.domici = domici;
        this.fecnac = fecnac;
        this.credit = credit;
        this.dispon = dispon;
        this.codar1 = codar1;
        this.tlfno1 = tlfno1;
        this.fecact = fecact;
        this.usuari = usuari;
    }

    public Integer getCodemp() {
        return codemp;
    }

    public void setCodemp(Integer codemp) {
        this.codemp = codemp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApelli() {
        return apelli;
    }

    public void setApelli(String apelli) {
        this.apelli = apelli;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDirecc() {
        return direcc;
    }

    public void setDirecc(String direcc) {
        this.direcc = direcc;
    }

    public String getDomici() {
        return domici;
    }

    public void setDomici(String domici) {
        this.domici = domici;
    }

    public Date getFecnac() {
        return fecnac;
    }

    public void setFecnac(Date fecnac) {
        this.fecnac = fecnac;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getDispon() {
        return dispon;
    }

    public void setDispon(BigDecimal dispon) {
        this.dispon = dispon;
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
        hash += (codemp != null ? codemp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maeemp)) {
            return false;
        }
        Maeemp other = (Maeemp) object;
        if ((this.codemp == null && other.codemp != null)
                || (this.codemp != null && !this.codemp.equals(other.codemp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Maeemp[ codemp=" + codemp + " ]";
    }

}
