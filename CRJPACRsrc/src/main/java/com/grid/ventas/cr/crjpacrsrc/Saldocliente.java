/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eve0017909
 */
@Entity
@Table(name = "SALDOCLIENTE")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Saldocliente.findAll",
                           query = "SELECT s FROM Saldocliente s"),
        @NamedQuery(name = "Saldocliente.findByCodcliente",
                    query = "SELECT s FROM Saldocliente s WHERE s.codcliente = :codcliente"),
        @NamedQuery(name = "Saldocliente.findBySaldo",
                    query = "SELECT s FROM Saldocliente s WHERE s.saldo = :saldo"),
        @NamedQuery(name = "Saldocliente.findBySaldobloqueado",
                    query = "SELECT s FROM Saldocliente s WHERE s.saldobloqueado = :saldobloqueado"),
        @NamedQuery(name = "Saldocliente.findByActualizacion",
                    query = "SELECT s FROM Saldocliente s WHERE s.actualizacion = :actualizacion"),
        @NamedQuery(name = "Saldocliente.findByCodcajero",
                    query = "SELECT s FROM Saldocliente s WHERE s.codcajero = :codcajero")})
public class Saldocliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "CODCLIENTE")
    private String codcliente;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "SALDO")
    private BigDecimal saldo;

    @Basic(optional = false)
    @Column(name = "SALDOBLOQUEADO")
    private Character saldobloqueado;

    @Basic(optional = false)
    @Column(name = "ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizacion;

    @Basic(optional = false)
    @Column(name = "CODCAJERO")
    private String codcajero;

    @JoinColumn(name = "CODCLIENTE",
                referencedColumnName = "CODAFILIADO",
                insertable = false,
                updatable = false)
    @OneToOne(optional = false,
              fetch = FetchType.LAZY)
    private Afiliado afiliado;

    public Saldocliente() {
    }

    public Saldocliente(String codcliente) {
        this.codcliente = codcliente;
    }

    public Saldocliente(String codcliente, BigDecimal saldo, Character saldobloqueado, Date actualizacion,
                        String codcajero) {
        this.codcliente = codcliente;
        this.saldo = saldo;
        this.saldobloqueado = saldobloqueado;
        this.actualizacion = actualizacion;
        this.codcajero = codcajero;
    }

    public String getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(String codcliente) {
        this.codcliente = codcliente;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Character getSaldobloqueado() {
        return saldobloqueado;
    }

    public void setSaldobloqueado(Character saldobloqueado) {
        this.saldobloqueado = saldobloqueado;
    }

    public Date getActualizacion() {
        return actualizacion;
    }

    public void setActualizacion(Date actualizacion) {
        this.actualizacion = actualizacion;
    }

    public String getCodcajero() {
        return codcajero;
    }

    public void setCodcajero(String codcajero) {
        this.codcajero = codcajero;
    }

    public Afiliado getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(Afiliado afiliado) {
        this.afiliado = afiliado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcliente != null ? codcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Saldocliente)) {
            return false;
        }
        Saldocliente other = (Saldocliente) object;
        if ((this.codcliente == null && other.codcliente != null)
                || (this.codcliente != null && !this.codcliente.equals(other.codcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Saldocliente[ codcliente=" + codcliente + " ]";
    }

}
