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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "SERVICIO")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Servicio.findAll",
                           query = "SELECT s FROM Servicio s"),
        @NamedQuery(name = "Servicio.findByNumtienda",
                    query = "SELECT s FROM Servicio s WHERE s.servicioPK.numtienda = :numtienda"),
        @NamedQuery(name = "Servicio.findByCodtiposervicio",
                    query = "SELECT s FROM Servicio s WHERE s.servicioPK.codtiposervicio = :codtiposervicio"),
        @NamedQuery(name = "Servicio.findByNumservicio",
                    query = "SELECT s FROM Servicio s WHERE s.servicioPK.numservicio = :numservicio"),
        @NamedQuery(name = "Servicio.findByFecha",
                    query = "SELECT s FROM Servicio s WHERE s.servicioPK.fecha = :fecha"),
        @NamedQuery(name = "Servicio.findByCodcliente",
                    query = "SELECT s FROM Servicio s WHERE s.codcliente = :codcliente"),
        @NamedQuery(name = "Servicio.findByMontobase",
                    query = "SELECT s FROM Servicio s WHERE s.montobase = :montobase"),
        @NamedQuery(name = "Servicio.findByMontoimpuesto",
                    query = "SELECT s FROM Servicio s WHERE s.montoimpuesto = :montoimpuesto"),
        @NamedQuery(name = "Servicio.findByLineasfacturacion",
                    query = "SELECT s FROM Servicio s WHERE s.lineasfacturacion = :lineasfacturacion"),
        @NamedQuery(name = "Servicio.findByCondicionabono",
                    query = "SELECT s FROM Servicio s WHERE s.condicionabono = :condicionabono"),
        @NamedQuery(name = "Servicio.findByCodcajero",
                    query = "SELECT s FROM Servicio s WHERE s.codcajero = :codcajero"),
        @NamedQuery(name = "Servicio.findByNumtransaccionventa",
                    query = "SELECT s FROM Servicio s WHERE s.numtransaccionventa = :numtransaccionventa"),
        @NamedQuery(name = "Servicio.findByFechatransaccionvnta",
                    query = "SELECT s FROM Servicio s WHERE s.fechatransaccionvnta = :fechatransaccionvnta"),
        @NamedQuery(name = "Servicio.findByDirecciondespacho",
                    query = "SELECT s FROM Servicio s WHERE s.direcciondespacho = :direcciondespacho"),
        @NamedQuery(name = "Servicio.findByCambiadireccion",
                    query = "SELECT s FROM Servicio s WHERE s.cambiadireccion = :cambiadireccion"),
        @NamedQuery(name = "Servicio.findByHorainicia",
                    query = "SELECT s FROM Servicio s WHERE s.horainicia = :horainicia"),
        @NamedQuery(name = "Servicio.findByHorafinaliza",
                    query = "SELECT s FROM Servicio s WHERE s.horafinaliza = :horafinaliza"),
        @NamedQuery(name = "Servicio.findByEstadoservicio",
                    query = "SELECT s FROM Servicio s WHERE s.estadoservicio = :estadoservicio"),
        @NamedQuery(name = "Servicio.findByNumcajaventa",
                    query = "SELECT s FROM Servicio s WHERE s.numcajaventa = :numcajaventa"),
        @NamedQuery(name = "Servicio.findByRegactualizado",
                    query = "SELECT s FROM Servicio s WHERE s.regactualizado = :regactualizado")})
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ServicioPK servicioPK;

    @Column(name = "CODCLIENTE")
    private String codcliente;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MONTOBASE")
    private BigDecimal montobase;

    @Basic(optional = false)
    @Column(name = "MONTOIMPUESTO")
    private BigDecimal montoimpuesto;

    @Basic(optional = false)
    @Column(name = "LINEASFACTURACION")
    private short lineasfacturacion;

    @Column(name = "CONDICIONABONO")
    private Character condicionabono;

    @Column(name = "CODCAJERO")
    private String codcajero;

    @Column(name = "NUMTRANSACCIONVENTA")
    private Integer numtransaccionventa;

    @Column(name = "FECHATRANSACCIONVNTA")
    @Temporal(TemporalType.DATE)
    private Date fechatransaccionvnta;

    @Column(name = "DIRECCIONDESPACHO")
    private String direcciondespacho;

    @Column(name = "CAMBIADIRECCION")
    private Character cambiadireccion;

    @Basic(optional = false)
    @Column(name = "HORAINICIA")
    @Temporal(TemporalType.TIME)
    private Date horainicia;

    @Column(name = "HORAFINALIZA")
    @Temporal(TemporalType.TIME)
    private Date horafinaliza;

    @Basic(optional = false)
    @Column(name = "ESTADOSERVICIO")
    private Character estadoservicio;

    @Column(name = "NUMCAJAVENTA")
    private Short numcajaventa;

    @Basic(optional = false)
    @Column(name = "REGACTUALIZADO")
    private Character regactualizado;

    public Servicio() {
    }

    public Servicio(ServicioPK servicioPK) {
        this.servicioPK = servicioPK;
    }

    public Servicio(ServicioPK servicioPK, BigDecimal montobase, BigDecimal montoimpuesto, short lineasfacturacion,
                    Date horainicia, Character estadoservicio, Character regactualizado) {
        this.servicioPK = servicioPK;
        this.montobase = montobase;
        this.montoimpuesto = montoimpuesto;
        this.lineasfacturacion = lineasfacturacion;
        this.horainicia = horainicia;
        this.estadoservicio = estadoservicio;
        this.regactualizado = regactualizado;
    }

    public Servicio(short numtienda, String codtiposervicio, int numservicio, Date fecha) {
        this.servicioPK = new ServicioPK(numtienda, codtiposervicio, numservicio, fecha);
    }

    public ServicioPK getServicioPK() {
        return servicioPK;
    }

    public void setServicioPK(ServicioPK servicioPK) {
        this.servicioPK = servicioPK;
    }

    public String getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(String codcliente) {
        this.codcliente = codcliente;
    }

    public BigDecimal getMontobase() {
        return montobase;
    }

    public void setMontobase(BigDecimal montobase) {
        this.montobase = montobase;
    }

    public BigDecimal getMontoimpuesto() {
        return montoimpuesto;
    }

    public void setMontoimpuesto(BigDecimal montoimpuesto) {
        this.montoimpuesto = montoimpuesto;
    }

    public short getLineasfacturacion() {
        return lineasfacturacion;
    }

    public void setLineasfacturacion(short lineasfacturacion) {
        this.lineasfacturacion = lineasfacturacion;
    }

    public Character getCondicionabono() {
        return condicionabono;
    }

    public void setCondicionabono(Character condicionabono) {
        this.condicionabono = condicionabono;
    }

    public String getCodcajero() {
        return codcajero;
    }

    public void setCodcajero(String codcajero) {
        this.codcajero = codcajero;
    }

    public Integer getNumtransaccionventa() {
        return numtransaccionventa;
    }

    public void setNumtransaccionventa(Integer numtransaccionventa) {
        this.numtransaccionventa = numtransaccionventa;
    }

    public Date getFechatransaccionvnta() {
        return fechatransaccionvnta;
    }

    public void setFechatransaccionvnta(Date fechatransaccionvnta) {
        this.fechatransaccionvnta = fechatransaccionvnta;
    }

    public String getDirecciondespacho() {
        return direcciondespacho;
    }

    public void setDirecciondespacho(String direcciondespacho) {
        this.direcciondespacho = direcciondespacho;
    }

    public Character getCambiadireccion() {
        return cambiadireccion;
    }

    public void setCambiadireccion(Character cambiadireccion) {
        this.cambiadireccion = cambiadireccion;
    }

    public Date getHorainicia() {
        return horainicia;
    }

    public void setHorainicia(Date horainicia) {
        this.horainicia = horainicia;
    }

    public Date getHorafinaliza() {
        return horafinaliza;
    }

    public void setHorafinaliza(Date horafinaliza) {
        this.horafinaliza = horafinaliza;
    }

    public Character getEstadoservicio() {
        return estadoservicio;
    }

    public void setEstadoservicio(Character estadoservicio) {
        this.estadoservicio = estadoservicio;
    }

    public Short getNumcajaventa() {
        return numcajaventa;
    }

    public void setNumcajaventa(Short numcajaventa) {
        this.numcajaventa = numcajaventa;
    }

    public Character getRegactualizado() {
        return regactualizado;
    }

    public void setRegactualizado(Character regactualizado) {
        this.regactualizado = regactualizado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (servicioPK != null ? servicioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.servicioPK == null && other.servicioPK != null)
                || (this.servicioPK != null && !this.servicioPK.equals(other.servicioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Servicio[ servicioPK=" + servicioPK + " ]";
    }

}
