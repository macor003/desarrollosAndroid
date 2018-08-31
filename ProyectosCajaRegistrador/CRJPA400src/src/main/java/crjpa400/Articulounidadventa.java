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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import crjpa400.interfaces.CrjpaInterface;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "ARTICULOUNIDADVENTA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Articulounidadventa.findAll", query = "SELECT a FROM Articulounidadventa a"),
		@NamedQuery(name = "Articulounidadventa.findById", query = "SELECT a FROM Articulounidadventa a WHERE a.id = :id"),
		@NamedQuery(name = "Articulounidadventa.findByPrecio", query = "SELECT a FROM Articulounidadventa a WHERE a.precio = :precio"),
		@NamedQuery(name = "Articulounidadventa.findByFecha", query = "SELECT a FROM Articulounidadventa a WHERE a.fecha = :fecha"),
		@NamedQuery(name = "Articulounidadventa.findByUltimasincronizacion", query = "SELECT a FROM Articulounidadventa a WHERE a.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Articulounidadventa.findByEstreplica", query = "SELECT a FROM Articulounidadventa a WHERE a.estreplica = :estreplica") })
public class Articulounidadventa extends CrjpaInterface implements Serializable {
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
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field precio.
	 */
	@Basic(optional = false)
	@Column(name = "PRECIO", nullable = false, precision = 13, scale = 2)
	private BigDecimal precio;
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
	 * Field idUnidadventa.
	 */
	@JoinColumn(name = "ID_UNIDADVENTA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Unidadventa idUnidadventa;
	/**
	 * Field idArticulo.
	 */
	@JoinColumn(name = "ID_ARTICULO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Articulo idArticulo;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;

	/**
	 * Constructor for Articulounidadventa.
	 */
	public Articulounidadventa() {
	}

	/**
	 * Constructor for Articulounidadventa.
	 * 
	 * @param id
	 *            Long
	 */
	public Articulounidadventa(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Articulounidadventa.
	 * 
	 * @param id
	 *            Long
	 * @param precio
	 *            BigDecimal
	 * @param fecha
	 *            Date
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Articulounidadventa(Long id, BigDecimal precio, Date fecha,
			Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.precio = precio;
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
	 * Method getPrecio.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getPrecio() {
		return precio;
	}

	/**
	 * Method setPrecio.
	 * 
	 * @param precio
	 *            BigDecimal
	 */
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
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
	 * Method getIdUnidadventa.
	 * 
	 * @return Unidadventa
	 */
	public Unidadventa getIdUnidadventa() {
		return idUnidadventa;
	}

	/**
	 * Method setIdUnidadventa.
	 * 
	 * @param idUnidadventa
	 *            Unidadventa
	 */
	public void setIdUnidadventa(Unidadventa idUnidadventa) {
		this.idUnidadventa = idUnidadventa;
	}

	/**
	 * Method getIdArticulo.
	 * 
	 * @return Articulo
	 */
	public Articulo getIdArticulo() {
		return idArticulo;
	}

	/**
	 * Method setIdArticulo.
	 * 
	 * @param idArticulo
	 *            Articulo
	 */
	public void setIdArticulo(Articulo idArticulo) {
		this.idArticulo = idArticulo;
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
		if (!(object instanceof Articulounidadventa)) {
			return false;
		}
		Articulounidadventa other = (Articulounidadventa) object;
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
		return "crjpa400.Articulounidadventa[ id=" + id + " ]";
	}

}
