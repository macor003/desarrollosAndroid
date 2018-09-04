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
@Table(name = "TASAIMPUESTOVALOR")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tasaimpuestovalor.findAll", query = "SELECT t FROM Tasaimpuestovalor t"),
		@NamedQuery(name = "Tasaimpuestovalor.findById", query = "SELECT t FROM Tasaimpuestovalor t WHERE t.id = :id"),
		@NamedQuery(name = "Tasaimpuestovalor.findByFechaInicio", query = "SELECT t FROM Tasaimpuestovalor t WHERE t.fechaInicio = :fechaInicio"),
		@NamedQuery(name = "Tasaimpuestovalor.findByTasa", query = "SELECT t FROM Tasaimpuestovalor t WHERE t.tasa = :tasa"),
		@NamedQuery(name = "Tasaimpuestovalor.findByUltimasincronizacion", query = "SELECT t FROM Tasaimpuestovalor t WHERE t.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Tasaimpuestovalor.findByEstreplica", query = "SELECT t FROM Tasaimpuestovalor t WHERE t.estreplica = :estreplica") })
public class Tasaimpuestovalor extends CrjpaInterface implements Serializable {
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
	 * Field fechaInicio.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA_INICIO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field tasa.
	 */
	@Basic(optional = false)
	@Column(name = "TASA", nullable = false, precision = 13, scale = 2)
	private BigDecimal tasa;
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
	 * Field ordendeventaarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTasaimpuestovalor")
	private List<Ordendeventaarticulo> ordendeventaarticuloList;
	/**
	 * Field transaccionarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTasaimpuestovalor")
	private List<Transaccionarticulo> transaccionarticuloList;
	/**
	 * Field idTasaimpuestotipo.
	 */
	@JoinColumn(name = "ID_TASAIMPUESTOTIPO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Tasaimpuestotipo idTasaimpuestotipo;

	/**
	 * Constructor for Tasaimpuestovalor.
	 */
	public Tasaimpuestovalor() {
	}

	/**
	 * Constructor for Tasaimpuestovalor.
	 * 
	 * @param id
	 *            Long
	 */
	public Tasaimpuestovalor(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Tasaimpuestovalor.
	 * 
	 * @param id
	 *            Long
	 * @param fechaInicio
	 *            Date
	 * @param tasa
	 *            BigDecimal
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Tasaimpuestovalor(Long id, Date fechaInicio, BigDecimal tasa,
			Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.tasa = tasa;
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
	 * Method getFechaInicio.
	 * 
	 * @return Date
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Method setFechaInicio.
	 * 
	 * @param fechaInicio
	 *            Date
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Method getTasa.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getTasa() {
		return tasa;
	}

	/**
	 * Method setTasa.
	 * 
	 * @param tasa
	 *            BigDecimal
	 */
	public void setTasa(BigDecimal tasa) {
		this.tasa = tasa;
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
	 * Method getOrdendeventaarticuloList.
	 * 
	 * @return List<Ordendeventaarticulo>
	 */
	@XmlTransient
	public List<Ordendeventaarticulo> getOrdendeventaarticuloList() {
		return ordendeventaarticuloList;
	}

	/**
	 * Method setOrdendeventaarticuloList.
	 * 
	 * @param ordendeventaarticuloList
	 *            List<Ordendeventaarticulo>
	 */
	public void setOrdendeventaarticuloList(
			List<Ordendeventaarticulo> ordendeventaarticuloList) {
		this.ordendeventaarticuloList = ordendeventaarticuloList;
	}

	/**
	 * Method getTransaccionarticuloList.
	 * 
	 * @return List<Transaccionarticulo>
	 */
	@XmlTransient
	public List<Transaccionarticulo> getTransaccionarticuloList() {
		return transaccionarticuloList;
	}

	/**
	 * Method setTransaccionarticuloList.
	 * 
	 * @param transaccionarticuloList
	 *            List<Transaccionarticulo>
	 */
	public void setTransaccionarticuloList(
			List<Transaccionarticulo> transaccionarticuloList) {
		this.transaccionarticuloList = transaccionarticuloList;
	}

	/**
	 * Method getIdTasaimpuestotipo.
	 * 
	 * @return Tasaimpuestotipo
	 */
	public Tasaimpuestotipo getIdTasaimpuestotipo() {
		return idTasaimpuestotipo;
	}

	/**
	 * Method setIdTasaimpuestotipo.
	 * 
	 * @param idTasaimpuestotipo
	 *            Tasaimpuestotipo
	 */
	public void setIdTasaimpuestotipo(Tasaimpuestotipo idTasaimpuestotipo) {
		this.idTasaimpuestotipo = idTasaimpuestotipo;
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
		if (!(object instanceof Tasaimpuestovalor)) {
			return false;
		}
		Tasaimpuestovalor other = (Tasaimpuestovalor) object;
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
		return "crjpa400.Tasaimpuestovalor[ id=" + id + " ]";
	}

}
