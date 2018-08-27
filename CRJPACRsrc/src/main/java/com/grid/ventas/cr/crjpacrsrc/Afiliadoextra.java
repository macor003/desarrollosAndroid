/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

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
@Table(name = "AFILIADOEXTRA")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Afiliadoextra.findAll",
                           query = "SELECT a FROM Afiliadoextra a"),
        @NamedQuery(name = "Afiliadoextra.findByCodafiliado",
                    query = "SELECT a FROM Afiliadoextra a WHERE a.codafiliado = :codafiliado"),
        @NamedQuery(name = "Afiliadoextra.findByExentoimpuesto",
                    query = "SELECT a FROM Afiliadoextra a WHERE a.exentoimpuesto = :exentoimpuesto"),
        @NamedQuery(name = "Afiliadoextra.findByFecha",
                    query = "SELECT a FROM Afiliadoextra a WHERE a.fecha = :fecha"),
        @NamedQuery(name = "Afiliadoextra.findByFicha",
                    query = "SELECT a FROM Afiliadoextra a WHERE a.ficha = :ficha")})
public class Afiliadoextra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "CODAFILIADO")
    private String codafiliado;

    @Basic(optional = false)
    @Column(name = "EXENTOIMPUESTO")
    private Character exentoimpuesto;

    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Basic(optional = false)
    @Column(name = "FICHA")
    private String ficha;

    public Afiliadoextra() {
    }

    public Afiliadoextra(String codafiliado) {
        this.codafiliado = codafiliado;
    }

    public Afiliadoextra(String codafiliado, Character exentoimpuesto, Date fecha, String ficha) {
        this.codafiliado = codafiliado;
        this.exentoimpuesto = exentoimpuesto;
        this.fecha = fecha;
        this.ficha = ficha;
    }

    public String getCodafiliado() {
        return codafiliado;
    }

    public void setCodafiliado(String codafiliado) {
        this.codafiliado = codafiliado;
    }

    public Character getExentoimpuesto() {
        return exentoimpuesto;
    }

    public void setExentoimpuesto(Character exentoimpuesto) {
        this.exentoimpuesto = exentoimpuesto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFicha() {
        return ficha;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codafiliado != null ? codafiliado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Afiliadoextra)) {
            return false;
        }
        Afiliadoextra other = (Afiliadoextra) object;
        if ((this.codafiliado == null && other.codafiliado != null)
                || (this.codafiliado != null && !this.codafiliado.equals(other.codafiliado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Afiliadoextra[ codafiliado=" + codafiliado + " ]";
    }

}
