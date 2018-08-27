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
@Table(name = "MONEDATASACAMBIO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Monedatasacambio.findAll", query = "SELECT m FROM Monedatasacambio m"),
		@NamedQuery(name = "Monedatasacambio.findById", query = "SELECT m FROM Monedatasacambio m WHERE m.id = :id"),
		@NamedQuery(name = "Monedatasacambio.findByCambio", query = "SELECT m FROM Monedatasacambio m WHERE m.cambio = :cambio"),
		@NamedQuery(name = "Monedatasacambio.findByFecha", query = "SELECT m FROM Monedatasacambio m WHERE m.fecha = :fecha"),
		@NamedQuery(name = "Monedatasacambio.findByUltimasincronizacion", query = "SELECT m FROM Monedatasacambio m WHERE m.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Monedatasacambio.findByEstaactivo", query = "SELECT m FROM Monedatasacambio m WHERE m.estaactivo = :estaactivo"),
		@NamedQuery(name = "Monedatasacambio.findByEstreplica", query = "SELECT m FROM Monedatasacambio m WHERE m.estreplica = :estreplica") })
public class Monedatasacambio extends CrjpaInterface implements Serializable {
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
	 * Field cambio.
	 */
	@Basic(optional = false)
	@Column(name = "CAMBIO", nullable = false, precision = 13, scale = 5)
	private BigDecimal cambio;
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
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;
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
	 * Constructor for Monedatasacambio.
	 */
	public Monedatasacambio() {
	}

	/**
	 * Constructor for Monedatasacambio.
	 * 
	 * @param id
	 *            Long
	 */
	public Monedatasacambio(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Monedatasacambio.
	 * 
	 * @param id
	 *            Long
	 * @param cambio
	 *            BigDecimal
	 * @param fecha
	 *            Date
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estaactivo
	 *            char
	 * @param estreplica
	 *            char
	 */
	public Monedatasacambio(Long id, BigDecimal cambio, Date fecha,
			Calendar ultimasincronizacion, char estaactivo, char estreplica) {
		this.id = id;
		this.cambio = cambio;
		this.fecha = fecha;
		this.ultimasincronizacion = ultimasincronizacion;
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
	 * Method getCambio.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getCambio() {
		return cambio;
	}

	/**
	 * Method setCambio.
	 * 
	 * @param cambio
	 *            BigDecimal
	 */
	public void setCambio(BigDecimal cambio) {
		this.cambio = cambio;
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
		if (!(object instanceof Monedatasacambio)) {
			return false;
		}
		Monedatasacambio other = (Monedatasacambio) object;
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
		return "crjpa400.Monedatasacambio[ id=" + id + " ]";
	}

}
