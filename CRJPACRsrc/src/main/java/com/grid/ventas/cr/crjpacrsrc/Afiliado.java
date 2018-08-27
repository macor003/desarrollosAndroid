/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "AFILIADO")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Afiliado.findAll",
                           query = "SELECT a FROM Afiliado a"),
        @NamedQuery(name = "Afiliado.findByCodafiliado",
                    query = "SELECT a FROM Afiliado a WHERE a.codafiliado = :codafiliado"),
        @NamedQuery(name = "Afiliado.findByTipoafiliado",
                    query = "SELECT a FROM Afiliado a WHERE a.tipoafiliado = :tipoafiliado"),
        @NamedQuery(name = "Afiliado.findByNombre",
                    query = "SELECT a FROM Afiliado a WHERE a.nombre = :nombre"),
        @NamedQuery(name = "Afiliado.findByNumtienda",
                    query = "SELECT a FROM Afiliado a WHERE a.numtienda = :numtienda"),
        @NamedQuery(name = "Afiliado.findByNumficha",
                    query = "SELECT a FROM Afiliado a WHERE a.numficha = :numficha"),
        @NamedQuery(name = "Afiliado.findByCoddepartamento",
                    query = "SELECT a FROM Afiliado a WHERE a.coddepartamento = :coddepartamento"),
        @NamedQuery(name = "Afiliado.findByCodcargo",
                    query = "SELECT a FROM Afiliado a WHERE a.codcargo = :codcargo"),
        @NamedQuery(name = "Afiliado.findByNitcliente",
                    query = "SELECT a FROM Afiliado a WHERE a.nitcliente = :nitcliente"),
        @NamedQuery(name = "Afiliado.findByDireccion",
                    query = "SELECT a FROM Afiliado a WHERE a.direccion = :direccion"),
        @NamedQuery(name = "Afiliado.findByDireccionfiscal",
                    query = "SELECT a FROM Afiliado a WHERE a.direccionfiscal = :direccionfiscal"),
        @NamedQuery(name = "Afiliado.findByCodarea",
                    query = "SELECT a FROM Afiliado a WHERE a.codarea = :codarea"),
        @NamedQuery(name = "Afiliado.findByNumtelefono",
                    query = "SELECT a FROM Afiliado a WHERE a.numtelefono = :numtelefono"),
        @NamedQuery(name = "Afiliado.findByFechaafiliacion",
                    query = "SELECT a FROM Afiliado a WHERE a.fechaafiliacion = :fechaafiliacion"),
        @NamedQuery(name = "Afiliado.findByExentoimpuesto",
                    query = "SELECT a FROM Afiliado a WHERE a.exentoimpuesto = :exentoimpuesto"),
        @NamedQuery(name = "Afiliado.findByRegistrado",
                    query = "SELECT a FROM Afiliado a WHERE a.registrado = :registrado"),
        @NamedQuery(name = "Afiliado.findByContactar",
                    query = "SELECT a FROM Afiliado a WHERE a.contactar = :contactar"),
        @NamedQuery(name = "Afiliado.findByCodregion",
                    query = "SELECT a FROM Afiliado a WHERE a.codregion = :codregion"),
        @NamedQuery(name = "Afiliado.findByEstadoafiliado",
                    query = "SELECT a FROM Afiliado a WHERE a.estadoafiliado = :estadoafiliado"),
        @NamedQuery(name = "Afiliado.findByEstadocolaborador",
                    query = "SELECT a FROM Afiliado a WHERE a.estadocolaborador = :estadocolaborador"),
        @NamedQuery(name = "Afiliado.findByActualizacion",
                    query = "SELECT a FROM Afiliado a WHERE a.actualizacion = :actualizacion"),
        @NamedQuery(name = "Afiliado.findByApellido",
                    query = "SELECT a FROM Afiliado a WHERE a.apellido = :apellido"),
        @NamedQuery(name = "Afiliado.findByTipocaliffiscal",
                    query = "SELECT a FROM Afiliado a WHERE a.tipocaliffiscal = :tipocaliffiscal"),
        @NamedQuery(name = "Afiliado.findByActcomercial",
                    query = "SELECT a FROM Afiliado a WHERE a.actcomercial = :actcomercial"),
        @NamedQuery(name = "Afiliado.findByTipodocidentif",
                    query = "SELECT a FROM Afiliado a WHERE a.tipodocidentif = :tipodocidentif"),
        @NamedQuery(name = "Afiliado.findByTipocliente",
                    query = "SELECT a FROM Afiliado a WHERE a.tipocliente = :tipocliente")})
public class Afiliado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "CODAFILIADO")
    private String codafiliado;

    @Basic(optional = false)
    @Column(name = "TIPOAFILIADO")
    private Character tipoafiliado;

    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "NUMTIENDA")
    private short numtienda;

    @Column(name = "NUMFICHA")
    private String numficha;

    @Column(name = "CODDEPARTAMENTO")
    private String coddepartamento;

    @Column(name = "CODCARGO")
    private String codcargo;

    @Column(name = "NITCLIENTE")
    private String nitcliente;

    @Basic(optional = false)
    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "DIRECCIONFISCAL")
    private String direccionfiscal;

    @Column(name = "CODAREA")
    private String codarea;

    @Column(name = "NUMTELEFONO")
    private String numtelefono;

    @Basic(optional = false)
    @Column(name = "FECHAAFILIACION")
    @Temporal(TemporalType.DATE)
    private Date fechaafiliacion;

    @Basic(optional = false)
    @Column(name = "EXENTOIMPUESTO")
    private Character exentoimpuesto;

    @Basic(optional = false)
    @Column(name = "REGISTRADO")
    private Character registrado;

    @Basic(optional = false)
    @Column(name = "CONTACTAR")
    private Character contactar;

    @Basic(optional = false)
    @Column(name = "CODREGION")
    private String codregion;

    @Basic(optional = false)
    @Column(name = "ESTADOAFILIADO")
    private Character estadoafiliado;

    @Basic(optional = false)
    @Column(name = "ESTADOCOLABORADOR")
    private Character estadocolaborador;

    @Basic(optional = false)
    @Column(name = "ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizacion;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "TIPOCALIFFISCAL")
    private String tipocaliffiscal;

    @Column(name = "ACTCOMERCIAL")
    private Integer actcomercial;

    @Column(name = "TIPODOCIDENTIF")
    private String tipodocidentif;

    @Column(name = "TIPOCLIENTE")
    private String tipocliente;

    @OneToOne(cascade = CascadeType.ALL,
              mappedBy = "afiliado",
              fetch = FetchType.LAZY)
    private Saldocliente saldocliente;

    public Afiliado() {
    }

    public Afiliado(String codafiliado) {
        this.codafiliado = codafiliado;
    }

    public Afiliado(String codafiliado, Character tipoafiliado, String nombre, short numtienda, String direccion,
                    Date fechaafiliacion, Character exentoimpuesto, Character registrado, Character contactar,
                    String codregion, Character estadoafiliado, Character estadocolaborador, Date actualizacion) {
        this.codafiliado = codafiliado;
        this.tipoafiliado = tipoafiliado;
        this.nombre = nombre;
        this.numtienda = numtienda;
        this.direccion = direccion;
        this.fechaafiliacion = fechaafiliacion;
        this.exentoimpuesto = exentoimpuesto;
        this.registrado = registrado;
        this.contactar = contactar;
        this.codregion = codregion;
        this.estadoafiliado = estadoafiliado;
        this.estadocolaborador = estadocolaborador;
        this.actualizacion = actualizacion;
    }

    public String getCodafiliado() {
        return codafiliado;
    }

    public void setCodafiliado(String codafiliado) {
        this.codafiliado = codafiliado;
    }

    public Character getTipoafiliado() {
        return tipoafiliado;
    }

    public void setTipoafiliado(Character tipoafiliado) {
        this.tipoafiliado = tipoafiliado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getNumtienda() {
        return numtienda;
    }

    public void setNumtienda(short numtienda) {
        this.numtienda = numtienda;
    }

    public String getNumficha() {
        return numficha;
    }

    public void setNumficha(String numficha) {
        this.numficha = numficha;
    }

    public String getCoddepartamento() {
        return coddepartamento;
    }

    public void setCoddepartamento(String coddepartamento) {
        this.coddepartamento = coddepartamento;
    }

    public String getCodcargo() {
        return codcargo;
    }

    public void setCodcargo(String codcargo) {
        this.codcargo = codcargo;
    }

    public String getNitcliente() {
        return nitcliente;
    }

    public void setNitcliente(String nitcliente) {
        this.nitcliente = nitcliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccionfiscal() {
        return direccionfiscal;
    }

    public void setDireccionfiscal(String direccionfiscal) {
        this.direccionfiscal = direccionfiscal;
    }

    public String getCodarea() {
        return codarea;
    }

    public void setCodarea(String codarea) {
        this.codarea = codarea;
    }

    public String getNumtelefono() {
        return numtelefono;
    }

    public void setNumtelefono(String numtelefono) {
        this.numtelefono = numtelefono;
    }

    public Date getFechaafiliacion() {
        return fechaafiliacion;
    }

    public void setFechaafiliacion(Date fechaafiliacion) {
        this.fechaafiliacion = fechaafiliacion;
    }

    public Character getExentoimpuesto() {
        return exentoimpuesto;
    }

    public void setExentoimpuesto(Character exentoimpuesto) {
        this.exentoimpuesto = exentoimpuesto;
    }

    public Character getRegistrado() {
        return registrado;
    }

    public void setRegistrado(Character registrado) {
        this.registrado = registrado;
    }

    public Character getContactar() {
        return contactar;
    }

    public void setContactar(Character contactar) {
        this.contactar = contactar;
    }

    public String getCodregion() {
        return codregion;
    }

    public void setCodregion(String codregion) {
        this.codregion = codregion;
    }

    public Character getEstadoafiliado() {
        return estadoafiliado;
    }

    public void setEstadoafiliado(Character estadoafiliado) {
        this.estadoafiliado = estadoafiliado;
    }

    public Character getEstadocolaborador() {
        return estadocolaborador;
    }

    public void setEstadocolaborador(Character estadocolaborador) {
        this.estadocolaborador = estadocolaborador;
    }

    public Date getActualizacion() {
        return actualizacion;
    }

    public void setActualizacion(Date actualizacion) {
        this.actualizacion = actualizacion;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipocaliffiscal() {
        return tipocaliffiscal;
    }

    public void setTipocaliffiscal(String tipocaliffiscal) {
        this.tipocaliffiscal = tipocaliffiscal;
    }

    public Integer getActcomercial() {
        return actcomercial;
    }

    public void setActcomercial(Integer actcomercial) {
        this.actcomercial = actcomercial;
    }

    public String getTipodocidentif() {
        return tipodocidentif;
    }

    public void setTipodocidentif(String tipodocidentif) {
        this.tipodocidentif = tipodocidentif;
    }

    public String getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(String tipocliente) {
        this.tipocliente = tipocliente;
    }

    public Saldocliente getSaldocliente() {
        return saldocliente;
    }

    public void setSaldocliente(Saldocliente saldocliente) {
        this.saldocliente = saldocliente;
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
        if (!(object instanceof Afiliado)) {
            return false;
        }
        Afiliado other = (Afiliado) object;
        if ((this.codafiliado == null && other.codafiliado != null)
                || (this.codafiliado != null && !this.codafiliado.equals(other.codafiliado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Afiliado[ codafiliado=" + codafiliado + " ]";
    }

}
