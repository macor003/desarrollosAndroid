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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "FORMADEPAGOMOVACREENCIA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Formadepagomovacreencia.findAll", query = "SELECT f FROM Formadepagomovacreencia f"),
		@NamedQuery(name = "Formadepagomovacreencia.findById", query = "SELECT f FROM Formadepagomovacreencia f WHERE f.id = :id"),
		@NamedQuery(name = "Formadepagomovacreencia.findByFecha", query = "SELECT f FROM Formadepagomovacreencia f WHERE f.fecha = :fecha"),
		@NamedQuery(name = "Formadepagomovacreencia.findByTitular", query = "SELECT f FROM Formadepagomovacreencia f WHERE f.titular = :titular"),
		@NamedQuery(name = "Formadepagomovacreencia.findByMonto", query = "SELECT f FROM Formadepagomovacreencia f WHERE f.monto = :monto"),
		@NamedQuery(name = "Formadepagomovacreencia.findByEstaactivo", query = "SELECT f FROM Formadepagomovacreencia f WHERE f.estaactivo = :estaactivo"),
		@NamedQuery(name = "Formadepagomovacreencia.findByConformacion", query = "SELECT f FROM Formadepagomovacreencia f WHERE f.conformacion = :conformacion"),
		@NamedQuery(name = "Formadepagomovacreencia.findByCuenta", query = "SELECT f FROM Formadepagomovacreencia f WHERE f.cuenta = :cuenta"),
		@NamedQuery(name = "Formadepagomovacreencia.findByDocumento", query = "SELECT f FROM Formadepagomovacreencia f WHERE f.documento = :documento"),
		@NamedQuery(name = "Formadepagomovacreencia.findByMontoMoneda", query = "SELECT f FROM Formadepagomovacreencia f WHERE f.montoMoneda = :montoMoneda"),
		@NamedQuery(name = "Formadepagomovacreencia.findByEstreplica", query = "SELECT f FROM Formadepagomovacreencia f WHERE f.estreplica = :estreplica") })
public class Formadepagomovacreencia implements Serializable {
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
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field titular.
	 */
	@Column(name = "TITULAR", length = 45)
	private String titular;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field monto.
	 */
	@Column(name = "MONTO", precision = 13, scale = 2)
	private BigDecimal monto;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;
	/**
	 * Field conformacion.
	 */
	@Column(name = "CONFORMACION", length = 11)
	private String conformacion;
	/**
	 * Field cuenta.
	 */
	@Column(name = "CUENTA", length = 20)
	private String cuenta;
	/**
	 * Field documento.
	 */
	@Column(name = "DOCUMENTO", length = 25)
	private String documento;
	/**
	 * Field montoMoneda.
	 */
	@Column(name = "MONTO_MONEDA", precision = 13, scale = 2)
	private BigDecimal montoMoneda;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idMoneda.
	 */
	@JoinColumn(name = "ID_MONEDA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Moneda idMoneda;
	/**
	 * Field idMovimientoacreencia.
	 */
	@JoinColumn(name = "ID_MOVIMIENTOACREENCIA", referencedColumnName = "ID")
	@ManyToOne
	private Movimientoacreencia idMovimientoacreencia;
	/**
	 * Field idFormadepago.
	 */
	@JoinColumn(name = "ID_FORMADEPAGO", referencedColumnName = "ID")
	@ManyToOne
	private Formadepago idFormadepago;

	/**
	 * Constructor for Formadepagomovacreencia.
	 */
	public Formadepagomovacreencia() {
	}

	/**
	 * Constructor for Formadepagomovacreencia.
	 * 
	 * @param id
	 *            Long
	 */
	public Formadepagomovacreencia(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Formadepagomovacreencia.
	 * 
	 * @param id
	 *            Long
	 * @param fecha
	 *            Date
	 * @param estaactivo
	 *            char
	 * @param estreplica
	 *            char
	 */
	public Formadepagomovacreencia(Long id, Date fecha, char estaactivo,
			char estreplica) {
		this.id = id;
		this.fecha = fecha;
		this.estaactivo = estaactivo;
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
	 * Method getTitular.
	 * 
	 * @return String
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Method setTitular.
	 * 
	 * @param titular
	 *            String
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Method getMonto.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMonto() {
		return monto;
	}

	/**
	 * Method setMonto.
	 * 
	 * @param monto
	 *            BigDecimal
	 */
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
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
	 * Method getConformacion.
	 * 
	 * @return String
	 */
	public String getConformacion() {
		return conformacion;
	}

	/**
	 * Method setConformacion.
	 * 
	 * @param conformacion
	 *            String
	 */
	public void setConformacion(String conformacion) {
		this.conformacion = conformacion;
	}

	/**
	 * Method getCuenta.
	 * 
	 * @return String
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Method setCuenta.
	 * 
	 * @param cuenta
	 *            String
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Method getDocumento.
	 * 
	 * @return String
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * Method setDocumento.
	 * 
	 * @param documento
	 *            String
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}

	/**
	 * Method getMontoMoneda.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoMoneda() {
		return montoMoneda;
	}

	/**
	 * Method setMontoMoneda.
	 * 
	 * @param montoMoneda
	 *            BigDecimal
	 */
	public void setMontoMoneda(BigDecimal montoMoneda) {
		this.montoMoneda = montoMoneda;
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
	 * Method getIdMovimientoacreencia.
	 * 
	 * @return Movimientoacreencia
	 */
	public Movimientoacreencia getIdMovimientoacreencia() {
		return idMovimientoacreencia;
	}

	/**
	 * Method setIdMovimientoacreencia.
	 * 
	 * @param idMovimientoacreencia
	 *            Movimientoacreencia
	 */
	public void setIdMovimientoacreencia(
			Movimientoacreencia idMovimientoacreencia) {
		this.idMovimientoacreencia = idMovimientoacreencia;
	}

	/**
	 * Method getIdFormadepago.
	 * 
	 * @return Formadepago
	 */
	public Formadepago getIdFormadepago() {
		return idFormadepago;
	}

	/**
	 * Method setIdFormadepago.
	 * 
	 * @param idFormadepago
	 *            Formadepago
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
		if (!(object instanceof Formadepagomovacreencia)) {
			return false;
		}
		Formadepagomovacreencia other = (Formadepagomovacreencia) object;
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
		return "crjpa400.Formadepagomovacreencia[ id=" + id + " ]";
	}

}
