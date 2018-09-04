/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eve0017909
 */
@Entity
@Table(name = "TIENDA")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Tienda.findAll",
                           query = "SELECT t FROM Tienda t"),
        @NamedQuery(name = "Tienda.findByNumtienda",
                    query = "SELECT t FROM Tienda t WHERE t.numtienda = :numtienda"),
        @NamedQuery(name = "Tienda.findByNombresucursal",
                    query = "SELECT t FROM Tienda t WHERE t.nombresucursal = :nombresucursal"),
        @NamedQuery(name = "Tienda.findByRazonsocial",
                    query = "SELECT t FROM Tienda t WHERE t.razonsocial = :razonsocial"),
        @NamedQuery(name = "Tienda.findByRif",
                    query = "SELECT t FROM Tienda t WHERE t.rif = :rif"),
        @NamedQuery(name = "Tienda.findByNit",
                    query = "SELECT t FROM Tienda t WHERE t.nit = :nit"),
        @NamedQuery(name = "Tienda.findByDireccion",
                    query = "SELECT t FROM Tienda t WHERE t.direccion = :direccion"),
        @NamedQuery(name = "Tienda.findByCodarea",
                    query = "SELECT t FROM Tienda t WHERE t.codarea = :codarea"),
        @NamedQuery(name = "Tienda.findByNumtelefono",
                    query = "SELECT t FROM Tienda t WHERE t.numtelefono = :numtelefono"),
        @NamedQuery(name = "Tienda.findByCodareafax",
                    query = "SELECT t FROM Tienda t WHERE t.codareafax = :codareafax"),
        @NamedQuery(name = "Tienda.findByNumfax",
                    query = "SELECT t FROM Tienda t WHERE t.numfax = :numfax"),
        @NamedQuery(name = "Tienda.findByDireccionfiscal",
                    query = "SELECT t FROM Tienda t WHERE t.direccionfiscal = :direccionfiscal"),
        @NamedQuery(name = "Tienda.findByCodareafiscal",
                    query = "SELECT t FROM Tienda t WHERE t.codareafiscal = :codareafiscal"),
        @NamedQuery(name = "Tienda.findByNumtelefonofiscal",
                    query = "SELECT t FROM Tienda t WHERE t.numtelefonofiscal = :numtelefonofiscal"),
        @NamedQuery(name = "Tienda.findByCodareafaxfiscal",
                    query = "SELECT t FROM Tienda t WHERE t.codareafaxfiscal = :codareafaxfiscal"),
        @NamedQuery(name = "Tienda.findByNumfaxfiscal",
                    query = "SELECT t FROM Tienda t WHERE t.numfaxfiscal = :numfaxfiscal"),
        @NamedQuery(name = "Tienda.findByMonedabase",
                    query = "SELECT t FROM Tienda t WHERE t.monedabase = :monedabase"),
        @NamedQuery(name = "Tienda.findByCodregion",
                    query = "SELECT t FROM Tienda t WHERE t.codregion = :codregion"),
        @NamedQuery(name = "Tienda.findByLimiteentregacaja",
                    query = "SELECT t FROM Tienda t WHERE t.limiteentregacaja = :limiteentregacaja"),
        @NamedQuery(name = "Tienda.findByTipoimpuestoaplicar",
                    query = "SELECT t FROM Tienda t WHERE t.tipoimpuestoaplicar = :tipoimpuestoaplicar"),
        @NamedQuery(name = "Tienda.findByIndicadesctoempleado",
                    query = "SELECT t FROM Tienda t WHERE t.indicadesctoempleado = :indicadesctoempleado"),
        @NamedQuery(name = "Tienda.findByDesctoventaempleado",
                    query = "SELECT t FROM Tienda t WHERE t.desctoventaempleado = :desctoventaempleado"),
        @NamedQuery(name = "Tienda.findByCambioprecioencaja",
                    query = "SELECT t FROM Tienda t WHERE t.cambioprecioencaja = :cambioprecioencaja"),
        @NamedQuery(name = "Tienda.findByFechatienda",
                    query = "SELECT t FROM Tienda t WHERE t.fechatienda = :fechatienda"),
        @NamedQuery(name = "Tienda.findByUtilizarvendedor",
                    query = "SELECT t FROM Tienda t WHERE t.utilizarvendedor = :utilizarvendedor"),
        @NamedQuery(name = "Tienda.findByDesctosacumulativos",
                    query = "SELECT t FROM Tienda t WHERE t.desctosacumulativos = :desctosacumulativos"),
        @NamedQuery(name = "Tienda.findByDbclase",
                    query = "SELECT t FROM Tienda t WHERE t.dbclase = :dbclase"),
        @NamedQuery(name = "Tienda.findByDburlservidor",
                    query = "SELECT t FROM Tienda t WHERE t.dburlservidor = :dburlservidor"),
        @NamedQuery(name = "Tienda.findByDbusuario",
                    query = "SELECT t FROM Tienda t WHERE t.dbusuario = :dbusuario"),
        @NamedQuery(name = "Tienda.findByDbclave",
                    query = "SELECT t FROM Tienda t WHERE t.dbclave = :dbclave"),
        @NamedQuery(name = "Tienda.findByVersionsistema",
                    query = "SELECT t FROM Tienda t WHERE t.versionsistema = :versionsistema")})
public class Tienda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "NUMTIENDA")
    private Short numtienda;

    @Basic(optional = false)
    @Column(name = "NOMBRESUCURSAL")
    private String nombresucursal;

    @Basic(optional = false)
    @Column(name = "RAZONSOCIAL")
    private String razonsocial;

    @Basic(optional = false)
    @Column(name = "RIF")
    private String rif;

    @Basic(optional = false)
    @Column(name = "NIT")
    private String nit;

    @Basic(optional = false)
    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "CODAREA")
    private String codarea;

    @Column(name = "NUMTELEFONO")
    private String numtelefono;

    @Column(name = "CODAREAFAX")
    private String codareafax;

    @Column(name = "NUMFAX")
    private String numfax;

    @Basic(optional = false)
    @Column(name = "DIRECCIONFISCAL")
    private String direccionfiscal;

    @Column(name = "CODAREAFISCAL")
    private String codareafiscal;

    @Column(name = "NUMTELEFONOFISCAL")
    private String numtelefonofiscal;

    @Column(name = "CODAREAFAXFISCAL")
    private String codareafaxfiscal;

    @Column(name = "NUMFAXFISCAL")
    private String numfaxfiscal;

    @Column(name = "MONEDABASE")
    private String monedabase;

    @Basic(optional = false)
    @Column(name = "CODREGION")
    private String codregion;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "LIMITEENTREGACAJA")
    private BigDecimal limiteentregacaja;

    @Basic(optional = false)
    @Column(name = "TIPOIMPUESTOAPLICAR")
    private Character tipoimpuestoaplicar;

    @Basic(optional = false)
    @Column(name = "INDICADESCTOEMPLEADO")
    private Character indicadesctoempleado;

    @Basic(optional = false)
    @Column(name = "DESCTOVENTAEMPLEADO")
    private BigDecimal desctoventaempleado;

    @Basic(optional = false)
    @Column(name = "CAMBIOPRECIOENCAJA")
    private Character cambioprecioencaja;

    @Basic(optional = false)
    @Column(name = "FECHATIENDA")
    @Temporal(TemporalType.DATE)
    private Date fechatienda;

    @Basic(optional = false)
    @Column(name = "UTILIZARVENDEDOR")
    private Character utilizarvendedor;

    @Basic(optional = false)
    @Column(name = "DESCTOSACUMULATIVOS")
    private Character desctosacumulativos;

    @Column(name = "DBCLASE")
    private String dbclase;

    @Column(name = "DBURLSERVIDOR")
    private String dburlservidor;

    @Column(name = "DBUSUARIO")
    private String dbusuario;

    @Column(name = "DBCLAVE")
    private String dbclave;

    @Basic(optional = false)
    @Column(name = "VERSIONSISTEMA")
    private String versionsistema;

    @OneToMany(cascade = CascadeType.ALL,
               mappedBy = "tienda",
               fetch = FetchType.LAZY)
    private List<Caja> cajaList;

    public Tienda() {
    }

    public Tienda(Short numtienda) {
        this.numtienda = numtienda;
    }

    public Tienda(Short numtienda, String nombresucursal, String razonsocial, String rif, String nit,
                  String direccion, String direccionfiscal, String codregion, BigDecimal limiteentregacaja,
                  Character tipoimpuestoaplicar, Character indicadesctoempleado, BigDecimal desctoventaempleado,
                  Character cambioprecioencaja, Date fechatienda, Character utilizarvendedor,
                  Character desctosacumulativos, String versionsistema) {
        this.numtienda = numtienda;
        this.nombresucursal = nombresucursal;
        this.razonsocial = razonsocial;
        this.rif = rif;
        this.nit = nit;
        this.direccion = direccion;
        this.direccionfiscal = direccionfiscal;
        this.codregion = codregion;
        this.limiteentregacaja = limiteentregacaja;
        this.tipoimpuestoaplicar = tipoimpuestoaplicar;
        this.indicadesctoempleado = indicadesctoempleado;
        this.desctoventaempleado = desctoventaempleado;
        this.cambioprecioencaja = cambioprecioencaja;
        this.fechatienda = fechatienda;
        this.utilizarvendedor = utilizarvendedor;
        this.desctosacumulativos = desctosacumulativos;
        this.versionsistema = versionsistema;
    }

    public Short getNumtienda() {
        return numtienda;
    }

    public void setNumtienda(Short numtienda) {
        this.numtienda = numtienda;
    }

    public String getNombresucursal() {
        return nombresucursal;
    }

    public void setNombresucursal(String nombresucursal) {
        this.nombresucursal = nombresucursal;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getCodareafax() {
        return codareafax;
    }

    public void setCodareafax(String codareafax) {
        this.codareafax = codareafax;
    }

    public String getNumfax() {
        return numfax;
    }

    public void setNumfax(String numfax) {
        this.numfax = numfax;
    }

    public String getDireccionfiscal() {
        return direccionfiscal;
    }

    public void setDireccionfiscal(String direccionfiscal) {
        this.direccionfiscal = direccionfiscal;
    }

    public String getCodareafiscal() {
        return codareafiscal;
    }

    public void setCodareafiscal(String codareafiscal) {
        this.codareafiscal = codareafiscal;
    }

    public String getNumtelefonofiscal() {
        return numtelefonofiscal;
    }

    public void setNumtelefonofiscal(String numtelefonofiscal) {
        this.numtelefonofiscal = numtelefonofiscal;
    }

    public String getCodareafaxfiscal() {
        return codareafaxfiscal;
    }

    public void setCodareafaxfiscal(String codareafaxfiscal) {
        this.codareafaxfiscal = codareafaxfiscal;
    }

    public String getNumfaxfiscal() {
        return numfaxfiscal;
    }

    public void setNumfaxfiscal(String numfaxfiscal) {
        this.numfaxfiscal = numfaxfiscal;
    }

    public String getMonedabase() {
        return monedabase;
    }

    public void setMonedabase(String monedabase) {
        this.monedabase = monedabase;
    }

    public String getCodregion() {
        return codregion;
    }

    public void setCodregion(String codregion) {
        this.codregion = codregion;
    }

    public BigDecimal getLimiteentregacaja() {
        return limiteentregacaja;
    }

    public void setLimiteentregacaja(BigDecimal limiteentregacaja) {
        this.limiteentregacaja = limiteentregacaja;
    }

    public Character getTipoimpuestoaplicar() {
        return tipoimpuestoaplicar;
    }

    public void setTipoimpuestoaplicar(Character tipoimpuestoaplicar) {
        this.tipoimpuestoaplicar = tipoimpuestoaplicar;
    }

    public Character getIndicadesctoempleado() {
        return indicadesctoempleado;
    }

    public void setIndicadesctoempleado(Character indicadesctoempleado) {
        this.indicadesctoempleado = indicadesctoempleado;
    }

    public BigDecimal getDesctoventaempleado() {
        return desctoventaempleado;
    }

    public void setDesctoventaempleado(BigDecimal desctoventaempleado) {
        this.desctoventaempleado = desctoventaempleado;
    }

    public Character getCambioprecioencaja() {
        return cambioprecioencaja;
    }

    public void setCambioprecioencaja(Character cambioprecioencaja) {
        this.cambioprecioencaja = cambioprecioencaja;
    }

    public Date getFechatienda() {
        return fechatienda;
    }

    public void setFechatienda(Date fechatienda) {
        this.fechatienda = fechatienda;
    }

    public Character getUtilizarvendedor() {
        return utilizarvendedor;
    }

    public void setUtilizarvendedor(Character utilizarvendedor) {
        this.utilizarvendedor = utilizarvendedor;
    }

    public Character getDesctosacumulativos() {
        return desctosacumulativos;
    }

    public void setDesctosacumulativos(Character desctosacumulativos) {
        this.desctosacumulativos = desctosacumulativos;
    }

    public String getDbclase() {
        return dbclase;
    }

    public void setDbclase(String dbclase) {
        this.dbclase = dbclase;
    }

    public String getDburlservidor() {
        return dburlservidor;
    }

    public void setDburlservidor(String dburlservidor) {
        this.dburlservidor = dburlservidor;
    }

    public String getDbusuario() {
        return dbusuario;
    }

    public void setDbusuario(String dbusuario) {
        this.dbusuario = dbusuario;
    }

    public String getDbclave() {
        return dbclave;
    }

    public void setDbclave(String dbclave) {
        this.dbclave = dbclave;
    }

    public String getVersionsistema() {
        return versionsistema;
    }

    public void setVersionsistema(String versionsistema) {
        this.versionsistema = versionsistema;
    }

    @XmlTransient
    public List<Caja> getCajaList() {
        return cajaList;
    }

    public void setCajaList(List<Caja> cajaList) {
        this.cajaList = cajaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numtienda != null ? numtienda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tienda)) {
            return false;
        }
        Tienda other = (Tienda) object;
        if ((this.numtienda == null && other.numtienda != null)
                || (this.numtienda != null && !this.numtienda.equals(other.numtienda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Tienda[ numtienda=" + numtienda + " ]";
    }

}
