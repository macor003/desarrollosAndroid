/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "FORMADEPAGO")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Formadepago.findAll",
                           query = "SELECT f FROM Formadepago f"),
        @NamedQuery(name = "Formadepago.findByCodformadepago",
                    query = "SELECT f FROM Formadepago f WHERE f.codformadepago = :codformadepago"),
        @NamedQuery(name = "Formadepago.findByTipoformadepago",
                    query = "SELECT f FROM Formadepago f WHERE f.tipoformadepago = :tipoformadepago"),
        @NamedQuery(name = "Formadepago.findByNombre",
                    query = "SELECT f FROM Formadepago f WHERE f.nombre = :nombre"),
        @NamedQuery(name = "Formadepago.findByCodbanco",
                    query = "SELECT f FROM Formadepago f WHERE f.codbanco = :codbanco"),
        @NamedQuery(name = "Formadepago.findByIndicarbanco",
                    query = "SELECT f FROM Formadepago f WHERE f.indicarbanco = :indicarbanco"),
        @NamedQuery(name = "Formadepago.findByIndicarnumdocumento",
                    query = "SELECT f FROM Formadepago f WHERE f.indicarnumdocumento = :indicarnumdocumento"),
        @NamedQuery(name = "Formadepago.findByIndicarnumcuenta",
                    query = "SELECT f FROM Formadepago f WHERE f.indicarnumcuenta = :indicarnumcuenta"),
        @NamedQuery(name = "Formadepago.findByIndicarnumconforma",
                    query = "SELECT f FROM Formadepago f WHERE f.indicarnumconforma = :indicarnumconforma"),
        @NamedQuery(name = "Formadepago.findByIndicarnumreferencia",
                    query = "SELECT f FROM Formadepago f WHERE f.indicarnumreferencia = :indicarnumreferencia"),
        @NamedQuery(name = "Formadepago.findByIndicarcedulatitular",
                    query = "SELECT f FROM Formadepago f WHERE f.indicarcedulatitular = :indicarcedulatitular"),
        @NamedQuery(name = "Formadepago.findByValidarsaldocliente",
                    query = "SELECT f FROM Formadepago f WHERE f.validarsaldocliente = :validarsaldocliente"),
        @NamedQuery(name = "Formadepago.findByMontominimo",
                    query = "SELECT f FROM Formadepago f WHERE f.montominimo = :montominimo"),
        @NamedQuery(name = "Formadepago.findByMontomaximo",
                    query = "SELECT f FROM Formadepago f WHERE f.montomaximo = :montomaximo"),
        @NamedQuery(name = "Formadepago.findByMontocomision",
                    query = "SELECT f FROM Formadepago f WHERE f.montocomision = :montocomision"),
        @NamedQuery(name = "Formadepago.findByPermitevuelto",
                    query = "SELECT f FROM Formadepago f WHERE f.permitevuelto = :permitevuelto"),
        @NamedQuery(name = "Formadepago.findByEntregaparcial",
                    query = "SELECT f FROM Formadepago f WHERE f.entregaparcial = :entregaparcial"),
        @NamedQuery(name = "Formadepago.findByRequiereautorizacion",
                    query = "SELECT f FROM Formadepago f WHERE f.requiereautorizacion = :requiereautorizacion"),
        @NamedQuery(name = "Formadepago.findByPrioridad",
                    query = "SELECT f FROM Formadepago f WHERE f.prioridad = :prioridad"),
        @NamedQuery(name = "Formadepago.findByRegvigente",
                    query = "SELECT f FROM Formadepago f WHERE f.regvigente = :regvigente"),
        @NamedQuery(name = "Formadepago.findByNombrecorto",
                    query = "SELECT f FROM Formadepago f WHERE f.nombrecorto = :nombrecorto")})
public class Formadepago implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "CODFORMADEPAGO")
    private String codformadepago;

    @Basic(optional = false)
    @Column(name = "TIPOFORMADEPAGO")
    private short tipoformadepago;

    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "CODBANCO")
    private String codbanco;

    @Basic(optional = false)
    @Column(name = "INDICARBANCO")
    private Character indicarbanco;

    @Basic(optional = false)
    @Column(name = "INDICARNUMDOCUMENTO")
    private Character indicarnumdocumento;

    @Basic(optional = false)
    @Column(name = "INDICARNUMCUENTA")
    private Character indicarnumcuenta;

    @Basic(optional = false)
    @Column(name = "INDICARNUMCONFORMA")
    private Character indicarnumconforma;

    @Basic(optional = false)
    @Column(name = "INDICARNUMREFERENCIA")
    private Character indicarnumreferencia;

    @Basic(optional = false)
    @Column(name = "INDICARCEDULATITULAR")
    private Character indicarcedulatitular;

    @Basic(optional = false)
    @Column(name = "VALIDARSALDOCLIENTE")
    private Character validarsaldocliente;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Column(name = "MONTOMINIMO")
    private BigDecimal montominimo;

    @Column(name = "MONTOMAXIMO")
    private BigDecimal montomaximo;

    @Column(name = "MONTOCOMISION")
    private BigDecimal montocomision;

    @Basic(optional = false)
    @Column(name = "PERMITEVUELTO")
    private Character permitevuelto;

    @Basic(optional = false)
    @Column(name = "ENTREGAPARCIAL")
    private Character entregaparcial;

    @Basic(optional = false)
    @Column(name = "REQUIEREAUTORIZACION")
    private Character requiereautorizacion;

    @Basic(optional = false)
    @Column(name = "PRIORIDAD")
    private short prioridad;

    @Basic(optional = false)
    @Column(name = "REGVIGENTE")
    private Character regvigente;

    @Basic(optional = false)
    @Column(name = "NOMBRECORTO")
    private String nombrecorto;

    public Formadepago() {
    }

    public Formadepago(String codformadepago) {
        this.codformadepago = codformadepago;
    }

    public Formadepago(String codformadepago, short tipoformadepago, String nombre, Character indicarbanco,
                       Character indicarnumdocumento, Character indicarnumcuenta, Character indicarnumconforma,
                       Character indicarnumreferencia, Character indicarcedulatitular,
                       Character validarsaldocliente, Character permitevuelto, Character entregaparcial,
                       Character requiereautorizacion, short prioridad, Character regvigente, String nombrecorto) {
        this.codformadepago = codformadepago;
        this.tipoformadepago = tipoformadepago;
        this.nombre = nombre;
        this.indicarbanco = indicarbanco;
        this.indicarnumdocumento = indicarnumdocumento;
        this.indicarnumcuenta = indicarnumcuenta;
        this.indicarnumconforma = indicarnumconforma;
        this.indicarnumreferencia = indicarnumreferencia;
        this.indicarcedulatitular = indicarcedulatitular;
        this.validarsaldocliente = validarsaldocliente;
        this.permitevuelto = permitevuelto;
        this.entregaparcial = entregaparcial;
        this.requiereautorizacion = requiereautorizacion;
        this.prioridad = prioridad;
        this.regvigente = regvigente;
        this.nombrecorto = nombrecorto;
    }

    public String getCodformadepago() {
        return codformadepago;
    }

    public void setCodformadepago(String codformadepago) {
        this.codformadepago = codformadepago;
    }

    public short getTipoformadepago() {
        return tipoformadepago;
    }

    public void setTipoformadepago(short tipoformadepago) {
        this.tipoformadepago = tipoformadepago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodbanco() {
        return codbanco;
    }

    public void setCodbanco(String codbanco) {
        this.codbanco = codbanco;
    }

    public Character getIndicarbanco() {
        return indicarbanco;
    }

    public void setIndicarbanco(Character indicarbanco) {
        this.indicarbanco = indicarbanco;
    }

    public Character getIndicarnumdocumento() {
        return indicarnumdocumento;
    }

    public void setIndicarnumdocumento(Character indicarnumdocumento) {
        this.indicarnumdocumento = indicarnumdocumento;
    }

    public Character getIndicarnumcuenta() {
        return indicarnumcuenta;
    }

    public void setIndicarnumcuenta(Character indicarnumcuenta) {
        this.indicarnumcuenta = indicarnumcuenta;
    }

    public Character getIndicarnumconforma() {
        return indicarnumconforma;
    }

    public void setIndicarnumconforma(Character indicarnumconforma) {
        this.indicarnumconforma = indicarnumconforma;
    }

    public Character getIndicarnumreferencia() {
        return indicarnumreferencia;
    }

    public void setIndicarnumreferencia(Character indicarnumreferencia) {
        this.indicarnumreferencia = indicarnumreferencia;
    }

    public Character getIndicarcedulatitular() {
        return indicarcedulatitular;
    }

    public void setIndicarcedulatitular(Character indicarcedulatitular) {
        this.indicarcedulatitular = indicarcedulatitular;
    }

    public Character getValidarsaldocliente() {
        return validarsaldocliente;
    }

    public void setValidarsaldocliente(Character validarsaldocliente) {
        this.validarsaldocliente = validarsaldocliente;
    }

    public BigDecimal getMontominimo() {
        return montominimo;
    }

    public void setMontominimo(BigDecimal montominimo) {
        this.montominimo = montominimo;
    }

    public BigDecimal getMontomaximo() {
        return montomaximo;
    }

    public void setMontomaximo(BigDecimal montomaximo) {
        this.montomaximo = montomaximo;
    }

    public BigDecimal getMontocomision() {
        return montocomision;
    }

    public void setMontocomision(BigDecimal montocomision) {
        this.montocomision = montocomision;
    }

    public Character getPermitevuelto() {
        return permitevuelto;
    }

    public void setPermitevuelto(Character permitevuelto) {
        this.permitevuelto = permitevuelto;
    }

    public Character getEntregaparcial() {
        return entregaparcial;
    }

    public void setEntregaparcial(Character entregaparcial) {
        this.entregaparcial = entregaparcial;
    }

    public Character getRequiereautorizacion() {
        return requiereautorizacion;
    }

    public void setRequiereautorizacion(Character requiereautorizacion) {
        this.requiereautorizacion = requiereautorizacion;
    }

    public short getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(short prioridad) {
        this.prioridad = prioridad;
    }

    public Character getRegvigente() {
        return regvigente;
    }

    public void setRegvigente(Character regvigente) {
        this.regvigente = regvigente;
    }

    public String getNombrecorto() {
        return nombrecorto;
    }

    public void setNombrecorto(String nombrecorto) {
        this.nombrecorto = nombrecorto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codformadepago != null ? codformadepago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formadepago)) {
            return false;
        }
        Formadepago other = (Formadepago) object;
        if ((this.codformadepago == null && other.codformadepago != null)
                || (this.codformadepago != null && !this.codformadepago.equals(other.codformadepago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Formadepago[ codformadepago=" + codformadepago + " ]";
    }

}
