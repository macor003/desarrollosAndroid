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
@Table(name = "PERFIL")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
		@NamedQuery(name = "Perfil.findById", query = "SELECT p FROM Perfil p WHERE p.id = :id"),
		@NamedQuery(name = "Perfil.findByNombre", query = "SELECT p FROM Perfil p WHERE p.nombre = :nombre"),
		@NamedQuery(name = "Perfil.findByFecha", query = "SELECT p FROM Perfil p WHERE p.fecha = :fecha"),
		@NamedQuery(name = "Perfil.findByEstaactivo", query = "SELECT p FROM Perfil p WHERE p.estaactivo = :estaactivo"),
		@NamedQuery(name = "Perfil.findByUltimasincronizacion", query = "SELECT p FROM Perfil p WHERE p.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Perfil.findByEstreplica", query = "SELECT p FROM Perfil p WHERE p.estreplica = :estreplica") })
public class Perfil extends CrjpaInterface implements Serializable {
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
	@Column(name = "NOMBRE", nullable = false, length = 40)
	private String nombre;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
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
	 * Field perfilprocesoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerfil")
	private List<Perfilproceso> perfilprocesoList;
	/**
	 * Field usuarioperfilList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerfil")
	private List<Usuarioperfil> usuarioperfilList;

	/**
	 * Constructor for Perfil.
	 */
	public Perfil() {
	}

	/**
	 * Constructor for Perfil.
	 * 
	 * @param id
	 *            Long
	 */
	public Perfil(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Perfil.
	 * 
	 * @param id
	 *            Long
	 * @param nombre
	 *            String
	 * @param fecha
	 *            Date
	 * @param estaactivo
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Perfil(Long id, String nombre, Date fecha, char estaactivo,
			Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
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
	 * Method getPerfilprocesoList.
	 * 
	 * @return List<Perfilproceso>
	 */
	@XmlTransient
	public List<Perfilproceso> getPerfilprocesoList() {
		return perfilprocesoList;
	}

	/**
	 * Method setPerfilprocesoList.
	 * 
	 * @param perfilprocesoList
	 *            List<Perfilproceso>
	 */
	public void setPerfilprocesoList(List<Perfilproceso> perfilprocesoList) {
		this.perfilprocesoList = perfilprocesoList;
	}

	/**
	 * Method getUsuarioperfilList.
	 * 
	 * @return List<Usuarioperfil>
	 */
	@XmlTransient
	public List<Usuarioperfil> getUsuarioperfilList() {
		return usuarioperfilList;
	}

	/**
	 * Method setUsuarioperfilList.
	 * 
	 * @param usuarioperfilList
	 *            List<Usuarioperfil>
	 */
	public void setUsuarioperfilList(List<Usuarioperfil> usuarioperfilList) {
		this.usuarioperfilList = usuarioperfilList;
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
		if (!(object instanceof Perfil)) {
			return false;
		}
		Perfil other = (Perfil) object;
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
		return "crjpa400.Perfil[ id=" + id + " ]";
	}

}
