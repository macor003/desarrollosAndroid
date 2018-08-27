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
@Table(name = "GIROCONTRIBUYENTE")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Girocontribuyente.findAll",
                           query = "SELECT g FROM Girocontribuyente g"),
        @NamedQuery(name = "Girocontribuyente.findByCodigo",
                    query = "SELECT g FROM Girocontribuyente g WHERE g.codigo = :codigo"),
        @NamedQuery(name = "Girocontribuyente.findByDescripcion",
                    query = "SELECT g FROM Girocontribuyente g WHERE g.descripcion = :descripcion"),
        @NamedQuery(name = "Girocontribuyente.findByFecha",
                    query = "SELECT g FROM Girocontribuyente g WHERE g.fecha = :fecha"),
        @NamedQuery(name = "Girocontribuyente.findByHora",
                    query = "SELECT g FROM Girocontribuyente g WHERE g.hora = :hora"),
        @NamedQuery(name = "Girocontribuyente.findByCategoria",
                    query = "SELECT g FROM Girocontribuyente g WHERE g.categoria = :categoria")})
public class Girocontribuyente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Long codigo;

    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Basic(optional = false)
    @Column(name = "HORA")
    @Temporal(TemporalType.TIME)
    private Date hora;

    @Basic(optional = false)
    @Column(name = "CATEGORIA")
    private short categoria;

    public Girocontribuyente() {
    }

    public Girocontribuyente(Long codigo) {
        this.codigo = codigo;
    }

    public Girocontribuyente(Long codigo, String descripcion, Date fecha, Date hora, short categoria) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.categoria = categoria;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public short getCategoria() {
        return categoria;
    }

    public void setCategoria(short categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Girocontribuyente)) {
            return false;
        }
        Girocontribuyente other = (Girocontribuyente) object;
        if ((this.codigo == null && other.codigo != null)
                || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Girocontribuyente[ codigo=" + codigo + " ]";
    }

}
