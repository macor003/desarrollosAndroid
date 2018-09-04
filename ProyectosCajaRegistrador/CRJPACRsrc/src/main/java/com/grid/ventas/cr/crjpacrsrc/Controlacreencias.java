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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "CONTROLACREENCIAS")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Controlacreencias.findAll",
                           query = "SELECT c FROM Controlacreencias c"),
        @NamedQuery(name = "Controlacreencias.findByCnumacreencia",
                    query = "SELECT c FROM Controlacreencias c WHERE c.cnumacreencia = :cnumacreencia"),
        @NamedQuery(name = "Controlacreencias.findByCcodcliente",
                    query = "SELECT c FROM Controlacreencias c WHERE c.ccodcliente = :ccodcliente"),
        @NamedQuery(name = "Controlacreencias.findByCfecha",
                    query = "SELECT c FROM Controlacreencias c WHERE c.cfecha = :cfecha"),
        @NamedQuery(name = "Controlacreencias.findByCestatus",
                    query = "SELECT c FROM Controlacreencias c WHERE c.cestatus = :cestatus")})
public class Controlacreencias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CNUMACREENCIA")
    private Integer cnumacreencia;

    @Basic(optional = false)
    @Column(name = "CCODCLIENTE")
    private String ccodcliente;

    @Basic(optional = false)
    @Column(name = "CFECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cfecha;

    @Basic(optional = false)
    @Column(name = "CESTATUS")
    private Character cestatus;

    public Controlacreencias() {
    }

    public Controlacreencias(Integer cnumacreencia) {
        this.cnumacreencia = cnumacreencia;
    }

    public Controlacreencias(Integer cnumacreencia, String ccodcliente, Date cfecha, Character cestatus) {
        this.cnumacreencia = cnumacreencia;
        this.ccodcliente = ccodcliente;
        this.cfecha = cfecha;
        this.cestatus = cestatus;
    }

    public Integer getCnumacreencia() {
        return cnumacreencia;
    }

    public void setCnumacreencia(Integer cnumacreencia) {
        this.cnumacreencia = cnumacreencia;
    }

    public String getCcodcliente() {
        return ccodcliente;
    }

    public void setCcodcliente(String ccodcliente) {
        this.ccodcliente = ccodcliente;
    }

    public Date getCfecha() {
        return cfecha;
    }

    public void setCfecha(Date cfecha) {
        this.cfecha = cfecha;
    }

    public Character getCestatus() {
        return cestatus;
    }

    public void setCestatus(Character cestatus) {
        this.cestatus = cestatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cnumacreencia != null ? cnumacreencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Controlacreencias)) {
            return false;
        }
        Controlacreencias other = (Controlacreencias) object;
        if ((this.cnumacreencia == null && other.cnumacreencia != null)
                || (this.cnumacreencia != null && !this.cnumacreencia.equals(other.cnumacreencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Controlacreencias[ cnumacreencia=" + cnumacreencia + " ]";
    }

}
