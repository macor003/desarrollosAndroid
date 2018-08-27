/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "articulounidadventa")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Articulounidadventa.findAll", query = "SELECT a FROM Articulounidadventa a"),
		@NamedQuery(name = "Articulounidadventa.findById", query = "SELECT a FROM Articulounidadventa a WHERE a.id = :id"),
		@NamedQuery(name = "Articulounidadventa.findByPrecio", query = "SELECT a FROM Articulounidadventa a WHERE a.precio = :precio"),
		@NamedQuery(name = "Articulounidadventa.findByFecha", query = "SELECT a FROM Articulounidadventa a WHERE a.fecha = :fecha") })
public class Articulounidadventa implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field precio.
	 */
	@Basic(optional = false)
	@Column(name = "precio")
	private BigDecimal precio;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field idUnidadventa.
	 */
	@JoinColumn(name = "id_unidadventa", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Unidadventa idUnidadventa;
	/**
	 * Field idArticulo.
	 */
	@JoinColumn(name = "id_articulo", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Articulo idArticulo;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo", columnDefinition = "default 'S'")
	private String estaactivo = "S";

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
	 */
	public Articulounidadventa(Long id, BigDecimal precio, Date fecha) {
		this.id = id;
		this.precio = precio;
		this.fecha = fecha;
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
	 * @return String
	 */
	public String getEstaactivo() {
		return estaactivo;
	}

	/**
	 * Method setEstaactivo.
	 * 
	 * @param estaactivo
	 *            String
	 */
	public void setEstaactivo(String estaactivo) {
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
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
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
		String separator = "@@";
		return id + separator + (idArticulo != null ? idArticulo.getId() : "\\N") + separator
				+ (idUnidadventa != null ? idUnidadventa.getId() : "\\N") + separator + precio + separator
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fecha) + separator + estaactivo + "\r\n";
	}

}
