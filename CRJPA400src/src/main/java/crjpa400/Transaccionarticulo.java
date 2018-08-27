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
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "TRANSACCIONARTICULO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Transaccionarticulo.findAll", query = "SELECT t FROM Transaccionarticulo t"),
		@NamedQuery(name = "Transaccionarticulo.findById", query = "SELECT t FROM Transaccionarticulo t WHERE t.id = :id"),
		@NamedQuery(name = "Transaccionarticulo.findByCantidad", query = "SELECT t FROM Transaccionarticulo t WHERE t.cantidad = :cantidad"),
		@NamedQuery(name = "Transaccionarticulo.findByMontoUnitario", query = "SELECT t FROM Transaccionarticulo t WHERE t.montoUnitario = :montoUnitario"),
		@NamedQuery(name = "Transaccionarticulo.findByMontoBase", query = "SELECT t FROM Transaccionarticulo t WHERE t.montoBase = :montoBase"),
		@NamedQuery(name = "Transaccionarticulo.findByMontoImpuesto", query = "SELECT t FROM Transaccionarticulo t WHERE t.montoImpuesto = :montoImpuesto"),
		@NamedQuery(name = "Transaccionarticulo.findByTotalBase", query = "SELECT t FROM Transaccionarticulo t WHERE t.totalBase = :totalBase"),
		@NamedQuery(name = "Transaccionarticulo.findByTotalImpuesto", query = "SELECT t FROM Transaccionarticulo t WHERE t.totalImpuesto = :totalImpuesto"),
		@NamedQuery(name = "Transaccionarticulo.findByTotalArticulo", query = "SELECT t FROM Transaccionarticulo t WHERE t.totalArticulo = :totalArticulo"),
		@NamedQuery(name = "Transaccionarticulo.findByCondicionEntrega", query = "SELECT t FROM Transaccionarticulo t WHERE t.condicionEntrega = :condicionEntrega"),
		@NamedQuery(name = "Transaccionarticulo.findByFechaVenta", query = "SELECT t FROM Transaccionarticulo t WHERE t.fechaVenta = :fechaVenta"),
		@NamedQuery(name = "Transaccionarticulo.findByFechaInicioGarantia", query = "SELECT t FROM Transaccionarticulo t WHERE t.fechaInicioGarantia = :fechaInicioGarantia"),
		@NamedQuery(name = "Transaccionarticulo.findByCaptura", query = "SELECT t FROM Transaccionarticulo t WHERE t.captura = :captura"),
		@NamedQuery(name = "Transaccionarticulo.findByTotalRebaja", query = "SELECT t FROM Transaccionarticulo t WHERE t.totalRebaja = :totalRebaja"),
		@NamedQuery(name = "Transaccionarticulo.findByEstreplica", query = "SELECT t FROM Transaccionarticulo t WHERE t.estreplica = :estreplica") })
public class Transaccionarticulo implements Serializable {
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
	 * Field montoUnitario.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_UNITARIO", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoUnitario;
	/**
	 * Field montoBase.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_BASE", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoBase;
	/**
	 * Field montoImpuesto.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_IMPUESTO", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoImpuesto;
	/**
	 * Field totalBase.
	 */
	@Basic(optional = false)
	@Column(name = "TOTAL_BASE", nullable = false, precision = 13, scale = 2)
	private BigDecimal totalBase;
	/**
	 * Field totalImpuesto.
	 */
	@Basic(optional = false)
	@Column(name = "TOTAL_IMPUESTO", nullable = false, precision = 13, scale = 2)
	private BigDecimal totalImpuesto;
	/**
	 * Field totalArticulo.
	 */
	@Basic(optional = false)
	@Column(name = "TOTAL_ARTICULO", nullable = false, precision = 13, scale = 2)
	private BigDecimal totalArticulo;
	/**
	 * Field condicionEntrega.
	 */
	@Basic(optional = false)
	@Column(name = "CONDICION_ENTREGA", nullable = false)
	private char condicionEntrega;
	/**
	 * Field fechaVenta.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA_VENTA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaVenta;
	/**
	 * Field fechaInicioGarantia.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA_INICIO_GARANTIA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicioGarantia;
	/**
	 * Field captura.
	 */
	@Column(name = "CAPTURA", length = 50)
	private String captura;
	/**
	 * Field totalRebaja.
	 */
	@Basic(optional = false)
	@Column(name = "TOTAL_REBAJA", nullable = false, precision = 13, scale = 2)
	private BigDecimal totalRebaja;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idUnidadventa.
	 */
	@JoinColumn(name = "ID_UNIDADVENTA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Unidadventa idUnidadventa;
	/**
	 * Field idTransaccion.
	 */
	@JoinColumn(name = "ID_TRANSACCION", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Transaccion idTransaccion;
	/**
	 * Field idTipodescuento.
	 */
	@JoinColumn(name = "ID_TIPODESCUENTO", referencedColumnName = "ID")
	@ManyToOne
	private Tipodescuento idTipodescuento;
	/**
	 * Field idTasaimpuestovalor.
	 */
	@JoinColumn(name = "ID_TASAIMPUESTOVALOR", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Tasaimpuestovalor idTasaimpuestovalor;
	/**
	 * Field idPromocion.
	 */
	@JoinColumn(name = "ID_PROMOCION", referencedColumnName = "ID")
	@ManyToOne
	private Promocion idPromocion;
	/**
	 * Field idArticulo.
	 */
	@JoinColumn(name = "ID_ARTICULO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Articulo idArticulo;
	/**
	 * Field transaccionarticuloservicioList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransaccionarticulo")
	private List<Transaccionarticuloservicio> transaccionarticuloservicioList;

	/**
	 * Constructor for Transaccionarticulo.
	 */
	public Transaccionarticulo() {
	}

	/**
	 * Constructor for Transaccionarticulo.
	 * 
	 * @param id
	 *            Long
	 */
	public Transaccionarticulo(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Transaccionarticulo.
	 * 
	 * @param id
	 *            Long
	 * @param cantidad
	 *            BigDecimal
	 * @param montoUnitario
	 *            BigDecimal
	 * @param montoBase
	 *            BigDecimal
	 * @param montoImpuesto
	 *            BigDecimal
	 * @param totalBase
	 *            BigDecimal
	 * @param totalImpuesto
	 *            BigDecimal
	 * @param totalArticulo
	 *            BigDecimal
	 * @param condicionEntrega
	 *            char
	 * @param fechaVenta
	 *            Date
	 * @param fechaInicioGarantia
	 *            Date
	 * @param totalRebaja
	 *            BigDecimal
	 * @param estreplica
	 *            char
	 */
	public Transaccionarticulo(Long id, BigDecimal cantidad,
			BigDecimal montoUnitario, BigDecimal montoBase,
			BigDecimal montoImpuesto, BigDecimal totalBase,
			BigDecimal totalImpuesto, BigDecimal totalArticulo,
			char condicionEntrega, Date fechaVenta, Date fechaInicioGarantia,
			BigDecimal totalRebaja, char estreplica) {
		this.id = id;
		this.cantidad = cantidad;
		this.montoUnitario = montoUnitario;
		this.montoBase = montoBase;
		this.montoImpuesto = montoImpuesto;
		this.totalBase = totalBase;
		this.totalImpuesto = totalImpuesto;
		this.totalArticulo = totalArticulo;
		this.condicionEntrega = condicionEntrega;
		this.fechaVenta = fechaVenta;
		this.fechaInicioGarantia = fechaInicioGarantia;
		this.totalRebaja = totalRebaja;
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
	 * Method getMontoUnitario.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoUnitario() {
		return montoUnitario;
	}

	/**
	 * Method setMontoUnitario.
	 * 
	 * @param montoUnitario
	 *            BigDecimal
	 */
	public void setMontoUnitario(BigDecimal montoUnitario) {
		this.montoUnitario = montoUnitario;
	}

	/**
	 * Method getMontoBase.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoBase() {
		return montoBase;
	}

	/**
	 * Method setMontoBase.
	 * 
	 * @param montoBase
	 *            BigDecimal
	 */
	public void setMontoBase(BigDecimal montoBase) {
		this.montoBase = montoBase;
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
	 * Method getTotalBase.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getTotalBase() {
		return totalBase;
	}

	/**
	 * Method setTotalBase.
	 * 
	 * @param totalBase
	 *            BigDecimal
	 */
	public void setTotalBase(BigDecimal totalBase) {
		this.totalBase = totalBase;
	}

	/**
	 * Method getTotalImpuesto.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getTotalImpuesto() {
		return totalImpuesto;
	}

	/**
	 * Method setTotalImpuesto.
	 * 
	 * @param totalImpuesto
	 *            BigDecimal
	 */
	public void setTotalImpuesto(BigDecimal totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	/**
	 * Method getTotalArticulo.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getTotalArticulo() {
		return totalArticulo;
	}

	/**
	 * Method setTotalArticulo.
	 * 
	 * @param totalArticulo
	 *            BigDecimal
	 */
	public void setTotalArticulo(BigDecimal totalArticulo) {
		this.totalArticulo = totalArticulo;
	}

	/**
	 * Method getCondicionEntrega.
	 * 
	 * @return char
	 */
	public char getCondicionEntrega() {
		return condicionEntrega;
	}

	/**
	 * Method setCondicionEntrega.
	 * 
	 * @param condicionEntrega
	 *            char
	 */
	public void setCondicionEntrega(char condicionEntrega) {
		this.condicionEntrega = condicionEntrega;
	}

	/**
	 * Method getFechaVenta.
	 * 
	 * @return Date
	 */
	public Date getFechaVenta() {
		return fechaVenta;
	}

	/**
	 * Method setFechaVenta.
	 * 
	 * @param fechaVenta
	 *            Date
	 */
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	/**
	 * Method getFechaInicioGarantia.
	 * 
	 * @return Date
	 */
	public Date getFechaInicioGarantia() {
		return fechaInicioGarantia;
	}

	/**
	 * Method setFechaInicioGarantia.
	 * 
	 * @param fechaInicioGarantia
	 *            Date
	 */
	public void setFechaInicioGarantia(Date fechaInicioGarantia) {
		this.fechaInicioGarantia = fechaInicioGarantia;
	}

	/**
	 * Method getCaptura.
	 * 
	 * @return String
	 */
	public String getCaptura() {
		return captura;
	}

	/**
	 * Method setCaptura.
	 * 
	 * @param captura
	 *            String
	 */
	public void setCaptura(String captura) {
		this.captura = captura;
	}

	/**
	 * Method getTotalRebaja.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getTotalRebaja() {
		return totalRebaja;
	}

	/**
	 * Method setTotalRebaja.
	 * 
	 * @param totalRebaja
	 *            BigDecimal
	 */
	public void setTotalRebaja(BigDecimal totalRebaja) {
		this.totalRebaja = totalRebaja;
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
	 * Method getIdTransaccion.
	 * 
	 * @return Transaccion
	 */
	public Transaccion getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Method setIdTransaccion.
	 * 
	 * @param idTransaccion
	 *            Transaccion
	 */
	public void setIdTransaccion(Transaccion idTransaccion) {
		this.idTransaccion = idTransaccion;
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
	 * Method getIdPromocion.
	 * 
	 * @return Promocion
	 */
	public Promocion getIdPromocion() {
		return idPromocion;
	}

	/**
	 * Method setIdPromocion.
	 * 
	 * @param idPromocion
	 *            Promocion
	 */
	public void setIdPromocion(Promocion idPromocion) {
		this.idPromocion = idPromocion;
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
	 * Method getTransaccionarticuloservicioList.
	 * 
	 * @return List<Transaccionarticuloservicio>
	 */
	@XmlTransient
	public List<Transaccionarticuloservicio> getTransaccionarticuloservicioList() {
		return transaccionarticuloservicioList;
	}

	/**
	 * Method setTransaccionarticuloservicioList.
	 * 
	 * @param transaccionarticuloservicioList
	 *            List<Transaccionarticuloservicio>
	 */
	public void setTransaccionarticuloservicioList(
			List<Transaccionarticuloservicio> transaccionarticuloservicioList) {
		this.transaccionarticuloservicioList = transaccionarticuloservicioList;
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
		if (!(object instanceof Transaccionarticulo)) {
			return false;
		}
		Transaccionarticulo other = (Transaccionarticulo) object;
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
		return "crjpa400.Transaccionarticulo[ id=" + id + " ]";
	}

}
