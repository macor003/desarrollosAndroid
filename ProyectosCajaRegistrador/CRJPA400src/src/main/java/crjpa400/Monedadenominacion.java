/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import crjpa400.interfaces.CrjpaInterface;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "MONEDADENOMINACION")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Monedadenominacion.findAll", query = "SELECT m FROM Monedadenominacion m"),
		@NamedQuery(name = "Monedadenominacion.findById", query = "SELECT m FROM Monedadenominacion m WHERE m.id = :id"),
		@NamedQuery(name = "Monedadenominacion.findByCodigo", query = "SELECT m FROM Monedadenominacion m WHERE m.codigo = :codigo"),
		@NamedQuery(name = "Monedadenominacion.findByNombre", query = "SELECT m FROM Monedadenominacion m WHERE m.nombre = :nombre"),
		@NamedQuery(name = "Monedadenominacion.findByMultiplo", query = "SELECT m FROM Monedadenominacion m WHERE m.multiplo = :multiplo"),
		@NamedQuery(name = "Monedadenominacion.findByEstaactivo", query = "SELECT m FROM Monedadenominacion m WHERE m.estaactivo = :estaactivo"),
		@NamedQuery(name = "Monedadenominacion.findByFecha", query = "SELECT m FROM Monedadenominacion m WHERE m.fecha = :fecha"),
		@NamedQuery(name = "Monedadenominacion.findByUltimasincronizacion", query = "SELECT m FROM Monedadenominacion m WHERE m.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Monedadenominacion.findByEstreplica", query = "SELECT m FROM Monedadenominacion m WHERE m.estreplica = :estreplica")
	    , @NamedQuery(name = "Monedadenominacion.findByTienda", query = "SELECT m FROM Monedadenominacion m WHERE m.tienda = :tienda")
	    , @NamedQuery(name = "Monedadenominacion.findByPorcentajeComision", query = "SELECT m FROM Monedadenominacion m WHERE m.porcentajeComision = :porcentajeComision")
	    , @NamedQuery(name = "Monedadenominacion.findByPorcentajeImpuesto", query = "SELECT m FROM Monedadenominacion m WHERE m.porcentajeImpuesto = :porcentajeImpuesto")
	    , @NamedQuery(name = "Monedadenominacion.findByCuentaContableImpuesto", query = "SELECT m FROM Monedadenominacion m WHERE m.cuentaContableImpuesto = :cuentaContableImpuesto")
	    , @NamedQuery(name = "Monedadenominacion.findByCuentaContable", query = "SELECT m FROM Monedadenominacion m WHERE m.cuentaContable = :cuentaContable")
	    , @NamedQuery(name = "Monedadenominacion.findByCuentaContableGastos", query = "SELECT m FROM Monedadenominacion m WHERE m.cuentaContableGastos = :cuentaContableGastos")
	    , @NamedQuery(name = "Monedadenominacion.findByEstadoRegistro", query = "SELECT m FROM Monedadenominacion m WHERE m.estadoRegistro = :estadoRegistro")
	    , @NamedQuery(name = "Monedadenominacion.findByAuxiliar", query = "SELECT m FROM Monedadenominacion m WHERE m.auxiliar = :auxiliar")})

public class Monedadenominacion extends CrjpaInterface implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;
	/**
	 * Field codigo.
	 */
	@Basic(optional = false)
	@Column(name = "CODIGO", nullable = false)
	private int codigo;
	/**
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "NOMBRE", nullable = false, length = 12)
	private String nombre;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field multiplo.
	 */
	@Basic(optional = false)
	@Column(name = "MULTIPLO", nullable = false, precision = 13, scale = 2)
	private BigDecimal multiplo;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field ultimasincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ULTIMASINCRONIZACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field tienda.
	 */
	@Basic(optional = false)
	@Column(name = "TIENDA")
    private String tienda;
	/**
	 * Field porcentajeComision.
	 */
	@Basic(optional = false)
    @Column(name = "PORCENTAJE_COMISION")
    private BigDecimal porcentajeComision;
    /**
	 * Field porcentajeImpuesto.
	 */
	@Basic(optional = false)
    @Column(name = "PORCENTAJE_IMPUESTO")
    private BigDecimal porcentajeImpuesto;
    /**
	 * Field cuentaContableImpuesto.
	 */
	@Basic(optional = false)
    @Column(name = "CUENTA_CONTABLE_IMPUESTO")
    private String cuentaContableImpuesto;
    /**
	 * Field cuentaContable.
	 */
	@Basic(optional = false)
    @Column(name = "CUENTA_CONTABLE")
    private String cuentaContable;
    /**
	 * Field cuentaContableGastos.
	 */
	@Basic(optional = false)
    @Column(name = "CUENTA_CONTABLE_GASTOS")
    private String cuentaContableGastos;
    /**
	 * Field estadoRegistro.
	 */
	@Basic(optional = false)
    @Column(name = "ESTADO_REGISTRO")
    private Character estadoRegistro;
    /**
	 * Field auxiliar.
	 */
	@Basic(optional = false)
    @Column(name = "AUXILIAR")
    private String auxiliar;
	/**
	 * Field entregaformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMonedadenominacion")
	private List<Entregaformadepago> entregaformadepagoList;
	/**
	 * Field idMoneda.
	 */
	@JoinColumn(name = "ID_MONEDA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Moneda idMoneda;
    /**
	 * Field idFormadepago.
	 */
    @JoinColumn(name = "ID_FORMADEPAGO", referencedColumnName = "id")
    @ManyToOne(optional=false)
    private Formadepago idFormadepago;

	/**
	 * Constructor for Monedadenominacion.
	 */
	public Monedadenominacion() {
	}

	/**
	 * Constructor for Monedadenominacion.
	 * 
	 * @param id
	 *            Long
	 */
	public Monedadenominacion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Monedadenominacion.
	 * 
	 * @param id
	 *            Long
	 * @param codigo
	 *            int
	 * @param nombre
	 *            String
	 * @param multiplo
	 *            BigDecimal
	 * @param estaactivo
	 *            char
	 * @param fecha
	 *            Date
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Monedadenominacion(Long id, int codigo, String nombre,
			BigDecimal multiplo, char estaactivo, Date fecha,
			Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.multiplo = multiplo;
		this.estaactivo = estaactivo;
		this.fecha = fecha;
		this.ultimasincronizacion = ultimasincronizacion;
		this.estreplica = estreplica;
	}

	/**
	 * Method getId.
	 * 
	 * @return Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Method setId.
	 * 
	 * @param id
	 *            Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method getCodigo.
	 * 
	 * @return int
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Method setCodigo.
	 * 
	 * @param codigo
	 *            int
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Method getNombre.
	 * 
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Method setNombre.
	 * 
	 * @param nombre
	 *            String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Method getMultiplo.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMultiplo() {
		return multiplo;
	}

	/**
	 * Method setMultiplo.
	 * 
	 * @param multiplo
	 *            BigDecimal
	 */
	public void setMultiplo(BigDecimal multiplo) {
		this.multiplo = multiplo;
	}

	/**
	 * Method getEstaactivo.
	 * 
	 * @return char
	 */
	public char getEstaactivo() {
		return estaactivo;
	}

	/**
	 * Method setEstaactivo.
	 * 
	 * @param estaactivo
	 *            char
	 */
	public void setEstaactivo(char estaactivo) {
		this.estaactivo = estaactivo;
	}

	/**
	 * Method getFecha.
	 * 
	 * @return Date
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Method setFecha.
	 * 
	 * @param fecha
	 *            Date
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Method getUltimasincronizacion.
	 * 
	 * @return Calendar
	 */
	public Calendar getUltimasincronizacion() {
		return ultimasincronizacion;
	}

	/**
	 * Method setUltimasincronizacion.
	 * 
	 * @param ultimasincronizacion
	 *            Calendar
	 */
	public void setUltimasincronizacion(Calendar ultimasincronizacion) {
		this.ultimasincronizacion = ultimasincronizacion;
	}

	/**
	 * Method getEstreplica.
	 * 
	 * @return char
	 */
	public char getEstreplica() {
		return estreplica;
	}

	/**
	 * Method setEstreplica.
	 * 
	 * @param estreplica
	 *            char
	 */
	public void setEstreplica(char estreplica) {
		this.estreplica = estreplica;
	}

	/**
	 * Method getEntregaformadepagoList.
	 * 
	 * @return List<Entregaformadepago>
	 */
	@XmlTransient
	public List<Entregaformadepago> getEntregaformadepagoList() {
		return entregaformadepagoList;
	}

	/**
	 * Method setEntregaformadepagoList.
	 * 
	 * @param entregaformadepagoList
	 *            List<Entregaformadepago>
	 */
	public void setEntregaformadepagoList(
			List<Entregaformadepago> entregaformadepagoList) {
		this.entregaformadepagoList = entregaformadepagoList;
	}

	/**
	 * Method getIdMoneda.
	 * 
	 * @return Moneda
	 */
	public Moneda getIdMoneda() {
		return idMoneda;
	}

	/**
	 * Method setIdMoneda.
	 * 
	 * @param idMoneda
	 *            Moneda
	 */
	public void setIdMoneda(Moneda idMoneda) {
		this.idMoneda = idMoneda;
	}

	/**
	 *  Method getTienda.
	 * 
	 * @return the tienda
	 */
	public String getTienda() {
		return tienda;
	}

	/**
	 *   Method setTienda.
	 * 
	 * @param tienda the tienda to set
	 */
	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	/**
	 *   Method getPorcentajeComision.
	 * 
	 * @return the porcentajeComision
	 */
	public BigDecimal getPorcentajeComision() {
		return porcentajeComision;
	}

	/**
	 *   Method setPorcentajeComision.
	 * 
	 * @param porcentajeComision the porcentajeComision to set
	 */
	public void setPorcentajeComision(BigDecimal porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	/**
	 * 
	 *   Method getPorcentajeImpuesto.
	 * 
	 * @return the porcentajeImpuesto
	 */
	public BigDecimal getPorcentajeImpuesto() {
		return porcentajeImpuesto;
	}

	/**
	 * 
	 * Method setPorcentajeImpuesto.
	 * 
	 * @param porcentajeImpuesto the porcentajeImpuesto to set
	 */
	public void setPorcentajeImpuesto(BigDecimal porcentajeImpuesto) {
		this.porcentajeImpuesto = porcentajeImpuesto;
	}

	/**
	 * 
	 * Method getCuentaContableImpuesto.
	 * 
	 * @return the cuentaContableImpuesto
	 */
	public String getCuentaContableImpuesto() {
		return cuentaContableImpuesto;
	}

	/**
	 * 
	 * Method setCuentaContableImpuesto.
	 * 
	 * @param cuentaContableImpuesto the cuentaContableImpuesto to set
	 */
	public void setCuentaContableImpuesto(String cuentaContableImpuesto) {
		this.cuentaContableImpuesto = cuentaContableImpuesto;
	}

	/**
	 * 
	 * 	Method getCuentaContable.
	 * 
	 * @return the cuentaContable
	 */
	public String getCuentaContable() {
		return cuentaContable;
	}

	/**
	 * 
	 * Method setCuentaContable.
	 * 
	 * @param cuentaContable the cuentaContable to set
	 */
	public void setCuentaContable(String cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	/**
	 * 
	 * Method getCuentaContableGastos.
	 * 
	 * @return the cuentaContableGastos
	 */
	public String getCuentaContableGastos() {
		return cuentaContableGastos;
	}

	/**
	 * 
	 * Method setCuentaContableGastos.
	 * 
	 * @param cuentaContableGastos the cuentaContableGastos to set
	 */
	public void setCuentaContableGastos(String cuentaContableGastos) {
		this.cuentaContableGastos = cuentaContableGastos;
	}

	/**
	 * 
	 * Method getEstadoRegistro.
	 * 
	 * @return the estadoRegistro
	 */
	public Character getEstadoRegistro() {
		return estadoRegistro;
	}

	/**
	 * Method setEstadoRegistro.
	 * 
	 * @param estadoRegistro the estadoRegistro to set
	 */
	public void setEstadoRegistro(Character estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	/**
	 * 
	 * Method getAuxiliar.
	 * 
	 * @return the auxiliar
	 */
	public String getAuxiliar() {
		return auxiliar;
	}

	/**
	 * 
	 * Method setAuxiliar.
	 * 
	 * @param auxiliar the auxiliar to set
	 */
	public void setAuxiliar(String auxiliar) {
		this.auxiliar = auxiliar;
	}

	/**
	 * 
	 * Method getIdFormadepago.
	 * 
	 * @return the idFormadepago
	 */
	public Formadepago getIdFormadepago() {
		return idFormadepago;
	}

	/**
	 * 
	 * Method setIdFormadepago.
	 * 
	 * @param idFormadepago the idFormadepago to set
	 */
	public void setIdFormadepago(Formadepago idFormadepago) {
		this.idFormadepago = idFormadepago;
	}

	/**
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	/**
	 * Method equals.
	 * 
	 * @param object
	 *            Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Monedadenominacion)) {
			return false;
		}
		Monedadenominacion other = (Monedadenominacion) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	/**
	 * Method toString.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "crjpa400.Monedadenominacion[ id=" + id + " ]";
	}

}
