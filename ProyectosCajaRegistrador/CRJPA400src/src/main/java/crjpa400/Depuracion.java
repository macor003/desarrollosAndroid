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
@Table(name = "DEPURACION")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Depuracion.findAll", query = "SELECT d FROM Depuracion d"),
		@NamedQuery(name = "Depuracion.findById", query = "SELECT d FROM Depuracion d WHERE d.id = :id"),
		@NamedQuery(name = "Depuracion.findByNombretabla", query = "SELECT d FROM Depuracion d WHERE d.nombretabla = :nombretabla"),
		@NamedQuery(name = "Depuracion.findByQuery", query = "SELECT d FROM Depuracion d WHERE d.query = :query"),
		@NamedQuery(name = "Depuracion.findByPrioridad", query = "SELECT d FROM Depuracion d WHERE d.prioridad = :prioridad"),
		@NamedQuery(name = "Depuracion.findByUltimasincronizacion", query = "SELECT d FROM Depuracion d WHERE d.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Depuracion.findByEstreplica", query = "SELECT d FROM Depuracion d WHERE d.estreplica = :estreplica") })
public class Depuracion extends CrjpaInterface implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	private Long id;
	/**
	 * Field nombretabla.
	 */
	@Basic(optional = false)
	@Column(name = "NOMBRETABLA")
	private String nombretabla;
	/**
	 * Field query.
	 */
	@Basic(optional = false)
	@Column(name = "QUERY")
	private String query;
	/**
	 * Field prioridad.
	 */
	@Basic(optional = false)
	@Column(name = "PRIORIDAD")
	private int prioridad;
	/**
	 * Field ultimasincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ULTIMASINCRONIZACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;

	/**
	 * Constructor for Depuracion.
	 */
	public Depuracion() {
	}

	/**
	 * Constructor for Depuracion.
	 * 
	 * @param id
	 *            Long
	 */
	public Depuracion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Depuracion.
	 * 
	 * @param id
	 *            Long
	 * @param nombretabla
	 *            String
	 * @param query
	 *            String
	 * @param prioridad
	 *            int
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Depuracion(Long id, String nombretabla, String query, int prioridad,
			Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.nombretabla = nombretabla;
		this.query = query;
		this.prioridad = prioridad;
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
	 * Method getNombretabla.
	 * 
	 * @return String
	 */
	public String getNombretabla() {
		return nombretabla;
	}

	/**
	 * Method setNombretabla.
	 * 
	 * @param nombretabla
	 *            String
	 */
	public void setNombretabla(String nombretabla) {
		this.nombretabla = nombretabla;
	}

	/**
	 * Method getQuery.
	 * 
	 * @return String
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Method setQuery.
	 * 
	 * @param query
	 *            String
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * Method getPrioridad.
	 * 
	 * @return int
	 */
	public int getPrioridad() {
		return prioridad;
	}

	/**
	 * Method setPrioridad.
	 * 
	 * @param prioridad
	 *            int
	 */
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
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
		if (!(object instanceof Depuracion)) {
			return false;
		}
		Depuracion other = (Depuracion) object;
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
		return "crjpa400.Depuracion[ id=" + id + " ]";
	}

}
