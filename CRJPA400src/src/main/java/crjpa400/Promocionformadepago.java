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
@Table(name = "PROMOCIONFORMADEPAGO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Promocionformadepago.findAll", query = "SELECT p FROM Promocionformadepago p"),
		@NamedQuery(name = "Promocionformadepago.findById", query = "SELECT p FROM Promocionformadepago p WHERE p.id = :id"),
		@NamedQuery(name = "Promocionformadepago.findByPorcentajeDescuento", query = "SELECT p FROM Promocionformadepago p WHERE p.porcentajeDescuento = :porcentajeDescuento"),
		@NamedQuery(name = "Promocionformadepago.findByEstaactivo", query = "SELECT p FROM Promocionformadepago p WHERE p.estaactivo = :estaactivo"),
		@NamedQuery(name = "Promocionformadepago.findByUltimasincronizacion", query = "SELECT p FROM Promocionformadepago p WHERE p.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Promocionformadepago.findByEstreplica", query = "SELECT p FROM Promocionformadepago p WHERE p.estreplica = :estreplica") })
public class Promocionformadepago extends CrjpaInterface implements
		Serializable {
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
	 * Field porcentajeDescuento.
	 */
	@Basic(optional = false)
	@Column(name = "PORCENTAJE_DESCUENTO", nullable = false, precision = 13, scale = 2)
	private BigDecimal porcentajeDescuento;
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
	 * Field idPromocion.
	 */
	@JoinColumn(name = "ID_PROMOCION", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Promocion idPromocion;
	/**
	 * Field idFormadepago.
	 */
	@JoinColumn(name = "ID_FORMADEPAGO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Formadepago idFormadepago;

	/**
	 * Constructor for Promocionformadepago.
	 */
	public Promocionformadepago() {
	}

	/**
	 * Constructor for Promocionformadepago.
	 * 
	 * @param id
	 *            Long
	 */
	public Promocionformadepago(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Promocionformadepago.
	 * 
	 * @param id
	 *            Long
	 * @param porcentajeDescuento
	 *            BigDecimal
	 * @param estaactivo
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Promocionformadepago(Long id, BigDecimal porcentajeDescuento,
			char estaactivo, Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.porcentajeDescuento = porcentajeDescuento;
		this.estaactivo = estaactivo;
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
	 * Method getPorcentajeDescuento.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	/**
	 * Method setPorcentajeDescuento.
	 * 
	 * @param porcentajeDescuento
	 *            BigDecimal
	 */
	public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
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
		if (!(object instanceof Promocionformadepago)) {
			return false;
		}
		Promocionformadepago other = (Promocionformadepago) object;
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
		return "crjpa400.Promocionformadepago[ id=" + id + " ]";
	}

}
