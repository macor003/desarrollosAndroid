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
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "UNIDADVENTA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Unidadventa.findAll", query = "SELECT u FROM Unidadventa u"),
		@NamedQuery(name = "Unidadventa.findById", query = "SELECT u FROM Unidadventa u WHERE u.id = :id"),
		@NamedQuery(name = "Unidadventa.findByNombre", query = "SELECT u FROM Unidadventa u WHERE u.nombre = :nombre"),
		@NamedQuery(name = "Unidadventa.findBySimbolo", query = "SELECT u FROM Unidadventa u WHERE u.simbolo = :simbolo"),
		@NamedQuery(name = "Unidadventa.findByEsfraccion", query = "SELECT u FROM Unidadventa u WHERE u.esfraccion = :esfraccion"),
		@NamedQuery(name = "Unidadventa.findByEstaactivo", query = "SELECT u FROM Unidadventa u WHERE u.estaactivo = :estaactivo"),
		@NamedQuery(name = "Unidadventa.findByFecha", query = "SELECT u FROM Unidadventa u WHERE u.fecha = :fecha"),
		@NamedQuery(name = "Unidadventa.findByUltimasincronizacion", query = "SELECT u FROM Unidadventa u WHERE u.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Unidadventa.findByEstreplica", query = "SELECT u FROM Unidadventa u WHERE u.estreplica = :estreplica") })
public class Unidadventa extends CrjpaInterface implements Serializable {
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
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "NOMBRE", nullable = false, length = 20)
	private String nombre;
	/**
	 * Field simbolo.
	 */
	@Basic(optional = false)
	@Column(name = "SIMBOLO", nullable = false, length = 4)
	private String simbolo;
	/**
	 * Field esfraccion.
	 */
	@Basic(optional = false)
	@Column(name = "ESFRACCION", nullable = false)
	private char esfraccion;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;
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
	 * Field ordendeventaarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadventa")
	private List<Ordendeventaarticulo> ordendeventaarticuloList;
	/**
	 * Field transaccionarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadventa")
	private List<Transaccionarticulo> transaccionarticuloList;
	/**
	 * Field articulounidadventaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadventa")
	private List<Articulounidadventa> articulounidadventaList;

	/**
	 * Constructor for Unidadventa.
	 */
	public Unidadventa() {
	}

	/**
	 * Constructor for Unidadventa.
	 * 
	 * @param id
	 *            Long
	 */
	public Unidadventa(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Unidadventa.
	 * 
	 * @param id
	 *            Long
	 * @param nombre
	 *            String
	 * @param simbolo
	 *            String
	 * @param esfraccion
	 *            char
	 * @param estaactivo
	 *            char
	 * @param fecha
	 *            Date
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Unidadventa(Long id, String nombre, String simbolo, char esfraccion,
			char estaactivo, Date fecha, Calendar ultimasincronizacion,
			char estreplica) {
		this.id = id;
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.esfraccion = esfraccion;
		this.estaactivo = estaactivo;
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
	 * Method getNombre.
	 * 
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Method setNombre.
	 * 
	 * @param nombre
	 *            String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Method getSimbolo.
	 * 
	 * @return String
	 */
	public String getSimbolo() {
		return simbolo;
	}

	/**
	 * Method setSimbolo.
	 * 
	 * @param simbolo
	 *            String
	 */
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	/**
	 * Method getEsfraccion.
	 * 
	 * @return char
	 */
	public char getEsfraccion() {
		return esfraccion;
	}

	/**
	 * Method setEsfraccion.
	 * 
	 * @param esfraccion
	 *            char
	 */
	public void setEsfraccion(char esfraccion) {
		this.esfraccion = esfraccion;
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
	 * Method getArticulounidadventaList.
	 * 
	 * @return List<Articulounidadventa>
	 */
	@XmlTransient
	public List<Articulounidadventa> getArticulounidadventaList() {
		return articulounidadventaList;
	}

	/**
	 * Method setArticulounidadventaList.
	 * 
	 * @param articulounidadventaList
	 *            List<Articulounidadventa>
	 */
	public void setArticulounidadventaList(
			List<Articulounidadventa> articulounidadventaList) {
		this.articulounidadventaList = articulounidadventaList;
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
		if (!(object instanceof Unidadventa)) {
			return false;
		}
		Unidadventa other = (Unidadventa) object;
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
		return "crjpa400.Unidadventa[ id=" + id + " ]";
	}

}
