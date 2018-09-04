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
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "ORDENDEVENTAARTICULO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Ordendeventaarticulo.findAll", query = "SELECT o FROM Ordendeventaarticulo o"),
		@NamedQuery(name = "Ordendeventaarticulo.findById", query = "SELECT o FROM Ordendeventaarticulo o WHERE o.id = :id"),
		@NamedQuery(name = "Ordendeventaarticulo.findByCantidad", query = "SELECT o FROM Ordendeventaarticulo o WHERE o.cantidad = :cantidad"),
		@NamedQuery(name = "Ordendeventaarticulo.findByMontoUnidad", query = "SELECT o FROM Ordendeventaarticulo o WHERE o.montoUnidad = :montoUnidad"),
		@NamedQuery(name = "Ordendeventaarticulo.findByMontoFinal", query = "SELECT o FROM Ordendeventaarticulo o WHERE o.montoFinal = :montoFinal"),
		@NamedQuery(name = "Ordendeventaarticulo.findByMontoImpuesto", query = "SELECT o FROM Ordendeventaarticulo o WHERE o.montoImpuesto = :montoImpuesto"),
		@NamedQuery(name = "Ordendeventaarticulo.findByEstreplica", query = "SELECT o FROM Ordendeventaarticulo o WHERE o.estreplica = :estreplica") })
public class Ordendeventaarticulo implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field cantidad.
	 */
	@Basic(optional = false)
	@Column(name = "CANTIDAD", nullable = false, precision = 13, scale = 2)
	private BigDecimal cantidad;
	/**
	 * Field montoUnidad.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_UNIDAD", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoUnidad;
	/**
	 * Field montoFinal.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_FINAL", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoFinal;
	/**
	 * Field montoImpuesto.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_IMPUESTO", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoImpuesto;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idTipodescuento.
	 */
	@JoinColumn(name = "ID_TIPODESCUENTO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Tipodescuento idTipodescuento;
	/**
	 * Field idTasaimpuestovalor.
	 */
	@JoinColumn(name = "ID_TASAIMPUESTOVALOR", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Tasaimpuestovalor idTasaimpuestovalor;
	/**
	 * Field idUnidadventa.
	 */
	@JoinColumn(name = "ID_UNIDADVENTA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Unidadventa idUnidadventa;
	/**
	 * Field idOrdendeventa.
	 */
	@JoinColumn(name = "ID_ORDENDEVENTA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Ordendeventa idOrdendeventa;
	/**
	 * Field idArticulo.
	 */
	@JoinColumn(name = "ID_ARTICULO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Articulo idArticulo;
	/**
	 * Field ordendeventaarticulocondicionentregaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrdendeventaarticulo")
	private List<Ordendeventaarticulocondicionentrega> ordendeventaarticulocondicionentregaList;
	/**
	 * Field idEstadoordendenventa.
	 */
	@JoinColumn(name = "ID_ESTADOORDENDEVENTA", referencedColumnName = "ID")
	@ManyToOne
	private Estadoordendeventa idEstadoordendenventa;

	/**
	 * Constructor for Ordendeventaarticulo.
	 */
	public Ordendeventaarticulo() {
	}

	/**
	 * Constructor for Ordendeventaarticulo.
	 * 
	 * @param id
	 *            Long
	 */
	public Ordendeventaarticulo(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Ordendeventaarticulo.
	 * 
	 * @param id
	 *            Long
	 * @param cantidad
	 *            BigDecimal
	 * @param montoUnidad
	 *            BigDecimal
	 * @param montoFinal
	 *            BigDecimal
	 * @param montoImpuesto
	 *            BigDecimal
	 * @param idEstadoordendenventa
	 *            Estadoordendeventa
	 * @param estreplica
	 *            char
	 */
	public Ordendeventaarticulo(Long id, BigDecimal cantidad,
			BigDecimal montoUnidad, BigDecimal montoFinal,
			BigDecimal montoImpuesto, Estadoordendeventa idEstadoordendenventa,
			char estreplica) {
		this.id = id;
		this.cantidad = cantidad;
		this.montoUnidad = montoUnidad;
		this.montoFinal = montoFinal;
		this.montoImpuesto = montoImpuesto;
		this.idEstadoordendenventa = idEstadoordendenventa;
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
	 * Method getCantidad.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getCantidad() {
		return cantidad;
	}

	/**
	 * Method setCantidad.
	 * 
	 * @param cantidad
	 *            BigDecimal
	 */
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Method getMontoUnidad.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoUnidad() {
		return montoUnidad;
	}

	/**
	 * Method setMontoUnidad.
	 * 
	 * @param montoUnidad
	 *            BigDecimal
	 */
	public void setMontoUnidad(BigDecimal montoUnidad) {
		this.montoUnidad = montoUnidad;
	}

	/**
	 * Method getMontoFinal.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoFinal() {
		return montoFinal;
	}

	/**
	 * Method setMontoFinal.
	 * 
	 * @param montoFinal
	 *            BigDecimal
	 */
	public void setMontoFinal(BigDecimal montoFinal) {
		this.montoFinal = montoFinal;
	}

	/**
	 * Method getMontoImpuesto.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoImpuesto() {
		return montoImpuesto;
	}

	/**
	 * Method setMontoImpuesto.
	 * 
	 * @param montoImpuesto
	 *            BigDecimal
	 */
	public void setMontoImpuesto(BigDecimal montoImpuesto) {
		this.montoImpuesto = montoImpuesto;
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
	 * Method getIdTipodescuento.
	 * 
	 * @return Tipodescuento
	 */
	public Tipodescuento getIdTipodescuento() {
		return idTipodescuento;
	}

	/**
	 * Method setIdTipodescuento.
	 * 
	 * @param idTipodescuento
	 *            Tipodescuento
	 */
	public void setIdTipodescuento(Tipodescuento idTipodescuento) {
		this.idTipodescuento = idTipodescuento;
	}

	/**
	 * Method getIdTasaimpuestovalor.
	 * 
	 * @return Tasaimpuestovalor
	 */
	public Tasaimpuestovalor getIdTasaimpuestovalor() {
		return idTasaimpuestovalor;
	}

	/**
	 * Method setIdTasaimpuestovalor.
	 * 
	 * @param idTasaimpuestovalor
	 *            Tasaimpuestovalor
	 */
	public void setIdTasaimpuestovalor(Tasaimpuestovalor idTasaimpuestovalor) {
		this.idTasaimpuestovalor = idTasaimpuestovalor;
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
	 * Method getIdOrdendeventa.
	 * 
	 * @return Ordendeventa
	 */
	public Ordendeventa getIdOrdendeventa() {
		return idOrdendeventa;
	}

	/**
	 * Method setIdOrdendeventa.
	 * 
	 * @param idOrdendeventa
	 *            Ordendeventa
	 */
	public void setIdOrdendeventa(Ordendeventa idOrdendeventa) {
		this.idOrdendeventa = idOrdendeventa;
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
	 * Method getOrdendeventaarticulocondicionentregaList.
	 * 
	 * @return List<Ordendeventaarticulocondicionentrega>
	 */
	public List<Ordendeventaarticulocondicionentrega> getOrdendeventaarticulocondicionentregaList() {
		return ordendeventaarticulocondicionentregaList;
	}

	/**
	 * Method setOrdendeventaarticulocondicionentregaList.
	 * 
	 * @param ordendeventaarticulocondicionentregaList
	 *            List<Ordendeventaarticulocondicionentrega>
	 */
	public void setOrdendeventaarticulocondicionentregaList(
			List<Ordendeventaarticulocondicionentrega> ordendeventaarticulocondicionentregaList) {
		this.ordendeventaarticulocondicionentregaList = ordendeventaarticulocondicionentregaList;
	}

	/**
	 * Method getIdEstadoordendenventa.
	 * 
	 * @return Estadoordendeventa
	 */
	public Estadoordendeventa getIdEstadoordendenventa() {
		return idEstadoordendenventa;
	}

	/**
	 * Method setIdEstadoordendenventa.
	 * 
	 * @param idEstadoordendenventa
	 *            Estadoordendeventa
	 */
	public void setIdEstadoordendenventa(
			Estadoordendeventa idEstadoordendenventa) {
		this.idEstadoordendenventa = idEstadoordendenventa;
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
		if (!(object instanceof Ordendeventaarticulo)) {
			return false;
		}
		Ordendeventaarticulo other = (Ordendeventaarticulo) object;
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
		return "crjpa400.Ordendeventaarticulo[ id=" + id + " ]";
	}

}
