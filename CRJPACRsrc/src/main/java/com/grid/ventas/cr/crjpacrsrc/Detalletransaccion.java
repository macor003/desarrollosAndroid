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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eve0017909
 */
@Entity
@Table(name = "DETALLETRANSACCION")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Detalletransaccion.findAll",
                           query = "SELECT d FROM Detalletransaccion d"),
        @NamedQuery(name = "Detalletransaccion.findByNumtienda",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.detalletransaccionPK.numtienda = :numtienda"),
        @NamedQuery(name = "Detalletransaccion.findByFecha",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.detalletransaccionPK.fecha = :fecha"),
        @NamedQuery(name = "Detalletransaccion.findByNumcajainicia",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.detalletransaccionPK.numcajainicia = :numcajainicia"),
        @NamedQuery(name = "Detalletransaccion.findByNumregcajainicia",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.detalletransaccionPK.numregcajainicia = :numregcajainicia"),
        @NamedQuery(name = "Detalletransaccion.findByCodproducto",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.detalletransaccionPK.codproducto = :codproducto"),
        @NamedQuery(name = "Detalletransaccion.findByCodcondicionventa",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.detalletransaccionPK.codcondicionventa = :codcondicionventa"),
        @NamedQuery(name = "Detalletransaccion.findByCorrelativoitem",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.detalletransaccionPK.correlativoitem = :correlativoitem"),
        @NamedQuery(name = "Detalletransaccion.findByNumcajafinaliza",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.numcajafinaliza = :numcajafinaliza"),
        @NamedQuery(name = "Detalletransaccion.findByNumtransaccion",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.numtransaccion = :numtransaccion"),
        @NamedQuery(name = "Detalletransaccion.findByCantidad",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.cantidad = :cantidad"),
        @NamedQuery(name = "Detalletransaccion.findByCodvendedor",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.codvendedor = :codvendedor"),
        @NamedQuery(name = "Detalletransaccion.findByPrecioregular",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.precioregular = :precioregular"),
        @NamedQuery(name = "Detalletransaccion.findByPreciofinal",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.preciofinal = :preciofinal"),
        @NamedQuery(name = "Detalletransaccion.findByMontoimpuesto",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.montoimpuesto = :montoimpuesto"),
        @NamedQuery(name = "Detalletransaccion.findByDesctoempleado",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.desctoempleado = :desctoempleado"),
        @NamedQuery(name = "Detalletransaccion.findByDespacharproducto",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.despacharproducto = :despacharproducto"),
        @NamedQuery(name = "Detalletransaccion.findByCodpromocion",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.codpromocion = :codpromocion"),
        @NamedQuery(name = "Detalletransaccion.findByCodtipocaptura",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.codtipocaptura = :codtipocaptura"),
        @NamedQuery(name = "Detalletransaccion.findByRegactualizado",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.regactualizado = :regactualizado"),
        @NamedQuery(name = "Detalletransaccion.findByTasadeiva",
                    query = "SELECT d FROM Detalletransaccion d WHERE d.tasadeiva = :tasadeiva")})
public class Detalletransaccion implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected DetalletransaccionPK detalletransaccionPK;

    @Column(name = "NUMCAJAFINALIZA")
    private Short numcajafinaliza;

    @Column(name = "NUMTRANSACCION")
    private Integer numtransaccion;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;

    @Column(name = "CODVENDEDOR")
    private String codvendedor;

    @Basic(optional = false)
    @Column(name = "PRECIOREGULAR")
    private BigDecimal precioregular;

    @Basic(optional = false)
    @Column(name = "PRECIOFINAL")
    private BigDecimal preciofinal;

    @Basic(optional = false)
    @Column(name = "MONTOIMPUESTO")
    private BigDecimal montoimpuesto;

    @Basic(optional = false)
    @Column(name = "DESCTOEMPLEADO")
    private BigDecimal desctoempleado;

    @Basic(optional = false)
    @Column(name = "DESPACHARPRODUCTO")
    private Character despacharproducto;

    @Column(name = "CODPROMOCION")
    private Integer codpromocion;

    @Basic(optional = false)
    @Column(name = "CODTIPOCAPTURA")
    private String codtipocaptura;

    @Basic(optional = false)
    @Column(name = "REGACTUALIZADO")
    private Character regactualizado;

    @Basic(optional = false)
    @Column(name = "TASADEIVA")
    private BigDecimal tasadeiva;

    @JoinColumns({@JoinColumn(name = "NUMTIENDA",
                              referencedColumnName = "NUMTIENDA",
                              insertable = false,
                              updatable = false),
            @JoinColumn(name = "CODSUPERVISOR",
                        referencedColumnName = "NUMFICHA")})
    @ManyToOne(optional = false,
               fetch = FetchType.LAZY)
    private Usuario usuario;

    public Detalletransaccion() {
    }

    public Detalletransaccion(DetalletransaccionPK detalletransaccionPK) {
        this.detalletransaccionPK = detalletransaccionPK;
    }

    public Detalletransaccion(DetalletransaccionPK detalletransaccionPK, BigDecimal cantidad,
                              BigDecimal precioregular, BigDecimal preciofinal, BigDecimal montoimpuesto,
                              BigDecimal desctoempleado, Character despacharproducto, String codtipocaptura,
                              Character regactualizado, BigDecimal tasadeiva) {
        this.detalletransaccionPK = detalletransaccionPK;
        this.cantidad = cantidad;
        this.precioregular = precioregular;
        this.preciofinal = preciofinal;
        this.montoimpuesto = montoimpuesto;
        this.desctoempleado = desctoempleado;
        this.despacharproducto = despacharproducto;
        this.codtipocaptura = codtipocaptura;
        this.regactualizado = regactualizado;
        this.tasadeiva = tasadeiva;
    }

    public Detalletransaccion(short numtienda, Date fecha, short numcajainicia, int numregcajainicia,
                              String codproducto, String codcondicionventa, short correlativoitem) {
        this.detalletransaccionPK = new DetalletransaccionPK(numtienda, fecha, numcajainicia, numregcajainicia,
                codproducto, codcondicionventa, correlativoitem);
    }

    public DetalletransaccionPK getDetalletransaccionPK() {
        return detalletransaccionPK;
    }

    public void setDetalletransaccionPK(DetalletransaccionPK detalletransaccionPK) {
        this.detalletransaccionPK = detalletransaccionPK;
    }

    public Short getNumcajafinaliza() {
        return numcajafinaliza;
    }

    public void setNumcajafinaliza(Short numcajafinaliza) {
        this.numcajafinaliza = numcajafinaliza;
    }

    public Integer getNumtransaccion() {
        return numtransaccion;
    }

    public void setNumtransaccion(Integer numtransaccion) {
        this.numtransaccion = numtransaccion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodvendedor() {
        return codvendedor;
    }

    public void setCodvendedor(String codvendedor) {
        this.codvendedor = codvendedor;
    }

    public BigDecimal getPrecioregular() {
        return precioregular;
    }

    public void setPrecioregular(BigDecimal precioregular) {
        this.precioregular = precioregular;
    }

    public BigDecimal getPreciofinal() {
        return preciofinal;
    }

    public void setPreciofinal(BigDecimal preciofinal) {
        this.preciofinal = preciofinal;
    }

    public BigDecimal getMontoimpuesto() {
        return montoimpuesto;
    }

    public void setMontoimpuesto(BigDecimal montoimpuesto) {
        this.montoimpuesto = montoimpuesto;
    }

    public BigDecimal getDesctoempleado() {
        return desctoempleado;
    }

    public void setDesctoempleado(BigDecimal desctoempleado) {
        this.desctoempleado = desctoempleado;
    }

    public Character getDespacharproducto() {
        return despacharproducto;
    }

    public void setDespacharproducto(Character despacharproducto) {
        this.despacharproducto = despacharproducto;
    }

    public Integer getCodpromocion() {
        return codpromocion;
    }

    public void setCodpromocion(Integer codpromocion) {
        this.codpromocion = codpromocion;
    }

    public String getCodtipocaptura() {
        return codtipocaptura;
    }

    public void setCodtipocaptura(String codtipocaptura) {
        this.codtipocaptura = codtipocaptura;
    }

    public Character getRegactualizado() {
        return regactualizado;
    }

    public void setRegactualizado(Character regactualizado) {
        this.regactualizado = regactualizado;
    }

    public BigDecimal getTasadeiva() {
        return tasadeiva;
    }

    public void setTasadeiva(BigDecimal tasadeiva) {
        this.tasadeiva = tasadeiva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalletransaccionPK != null ? detalletransaccionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalletransaccion)) {
            return false;
        }
        Detalletransaccion other = (Detalletransaccion) object;
        if ((this.detalletransaccionPK == null && other.detalletransaccionPK != null)
                || (this.detalletransaccionPK != null
                        && !this.detalletransaccionPK.equals(other.detalletransaccionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Detalletransaccion[ detalletransaccionPK=" + detalletransaccionPK
                + " ]";
    }

}
