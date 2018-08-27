/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
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
@Table(name = "PROMOCIONTIPODESCUENTO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Promociontipodescuento.findAll", query = "SELECT p FROM Promociontipodescuento p"),
		@NamedQuery(name = "Promociontipodescuento.findById", query = "SELECT p FROM Promociontipodescuento p WHERE p.id = :id"),
		@NamedQuery(name = "Promociontipodescuento.findByUltimasincronizacion", query = "SELECT p FROM Promociontipodescuento p WHERE p.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Promociontipodescuento.findByEstreplica", query = "SELECT p FROM Promociontipodescuento p WHERE p.estreplica = :estreplica") })
public class Promociontipodescuento extends CrjpaInterface implements
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
	 * Field idTipodescuento.
	 */
	@JoinColumn(name = "ID_TIPODESCUENTO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Tipodescuento idTipodescuento;
	/**
	 * Field idPromocion.
	 */
	@JoinColumn(name = "ID_PROMOCION", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Promocion idPromocion;

	/**
	 * Constructor for Promociontipodescuento.
	 */
	public Promociontipodescuento() {
	}

	/**
	 * Constructor for Promociontipodescuento.
	 * 
	 * @param id
	 *            Long
	 */
	public Promociontipodescuento(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Promociontipodescuento.
	 * 
	 * @param id
	 *            Long
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Promociontipodescuento(Long id, Calendar ultimasincronizacion,
			char estreplica) {
		this.id = id;
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
		if (!(object instanceof Promociontipodescuento)) {
			return false;
		}
		Promociontipodescuento other = (Promociontipodescuento) object;
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
		return "crjpa400.Promociontipodescuento[ id=" + id + " ]";
	}

}
