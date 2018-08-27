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
@Table(name = "MOTIVOREBAJA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Motivorebaja.findAll", query = "SELECT m FROM Motivorebaja m"),
		@NamedQuery(name = "Motivorebaja.findById", query = "SELECT m FROM Motivorebaja m WHERE m.id = :id"),
		@NamedQuery(name = "Motivorebaja.findByDescripcion", query = "SELECT m FROM Motivorebaja m WHERE m.descripcion = :descripcion"),
		@NamedQuery(name = "Motivorebaja.findByEstaactivo", query = "SELECT m FROM Motivorebaja m WHERE m.estaactivo = :estaactivo"),
		@NamedQuery(name = "Motivorebaja.findByUltimasincronizacion", query = "SELECT m FROM Motivorebaja m WHERE m.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Motivorebaja.findByPorcentajeMaximo", query = "SELECT m FROM Motivorebaja m WHERE m.porcentajeMaximo = :porcentajeMaximo"),
		@NamedQuery(name = "Motivorebaja.findByEstreplica", query = "SELECT m FROM Motivorebaja m WHERE m.estreplica = :estreplica") })
public class Motivorebaja extends CrjpaInterface implements Serializable {
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
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "DESCRIPCION", nullable = false, length = 30)
	private String descripcion;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;
	/**
	 * Field ultimasincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ULTIMASINCRONIZACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field porcentajeMaximo.
	 */
	@Basic(optional = false)
	@Column(name = "PORCENTAJE_MAXIMO", nullable = false, precision = 4, scale = 2)
	private BigDecimal porcentajeMaximo;
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
	 * Constructor for Motivorebaja.
	 */
	public Motivorebaja() {
	}

	/**
	 * Constructor for Motivorebaja.
	 * 
	 * @param id
	 *            Long
	 */
	public Motivorebaja(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Motivorebaja.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param estaactivo
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param porcentajeMaximo
	 *            BigDecimal
	 * @param estreplica
	 *            char
	 */
	public Motivorebaja(Long id, String descripcion, char estaactivo,
			Calendar ultimasincronizacion, BigDecimal porcentajeMaximo,
			char estreplica) {
		this.id = id;
		this.descripcion = descripcion;
		this.estaactivo = estaactivo;
		this.ultimasincronizacion = ultimasincronizacion;
		this.porcentajeMaximo = porcentajeMaximo;
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
	 * Method getDescripcion.
	 * 
	 * @return String
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Method setDescripcion.
	 * 
	 * @param descripcion
	 *            String
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * Method getUltimasincronizacion.
	 * 
	 * @return Calendar
	 */
	@Override
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
	 * Method getPorcentajeMaximo.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getPorcentajeMaximo() {
		return porcentajeMaximo;
	}

	/**
	 * Method setPorcentajeMaximo.
	 * 
	 * @param porcentajeMaximo
	 *            BigDecimal
	 */
	public void setPorcentajeMaximo(BigDecimal porcentajeMaximo) {
		this.porcentajeMaximo = porcentajeMaximo;
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
		if (!(object instanceof Motivorebaja)) {
			return false;
		}
		Motivorebaja other = (Motivorebaja) object;
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
		return "crjpa400.Motivorebaja[ id=" + id + " ]";
	}

}
