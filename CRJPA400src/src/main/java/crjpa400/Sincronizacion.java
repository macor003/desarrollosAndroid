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
@Table(name = "SINCRONIZACION")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Sincronizacion.findAll", query = "SELECT s FROM Sincronizacion s"),
		@NamedQuery(name = "Sincronizacion.findById", query = "SELECT s FROM Sincronizacion s WHERE s.id = :id"),
		@NamedQuery(name = "Sincronizacion.findByIdGrupo", query = "SELECT s FROM Sincronizacion s WHERE s.idGrupo = :idGrupo"),
		@NamedQuery(name = "Sincronizacion.findByEspera", query = "SELECT s FROM Sincronizacion s WHERE s.espera = :espera"),
		@NamedQuery(name = "Sincronizacion.findByCantidad", query = "SELECT s FROM Sincronizacion s WHERE s.cantidad = :cantidad"),
		@NamedQuery(name = "Sincronizacion.findByNombre", query = "SELECT s FROM Sincronizacion s WHERE s.nombre = :nombre"),
		@NamedQuery(name = "Sincronizacion.findByEscarga", query = "SELECT s FROM Sincronizacion s WHERE s.escarga = :escarga"),
		@NamedQuery(name = "Sincronizacion.findByEsdescarga", query = "SELECT s FROM Sincronizacion s WHERE s.esdescarga = :esdescarga"),
		@NamedQuery(name = "Sincronizacion.findByUltimasincronizacion", query = "SELECT s FROM Sincronizacion s WHERE s.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Sincronizacion.findByPrioridad", query = "SELECT s FROM Sincronizacion s WHERE s.prioridad = :prioridad"),
		@NamedQuery(name = "Sincronizacion.findByTipo", query = "SELECT s FROM Sincronizacion s WHERE s.tipo = :tipo"),
		@NamedQuery(name = "Sincronizacion.findByEstreplica", query = "SELECT s FROM Sincronizacion s WHERE s.estreplica = :estreplica") })
public class Sincronizacion extends CrjpaInterface implements Serializable {
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
	 * Field idGrupo.
	 */
	@Basic(optional = false)
	@Column(name = "ID_GRUPO", nullable = false)
	private long idGrupo;
	/**
	 * Field espera.
	 */
	@Basic(optional = false)
	@Column(name = "ESPERA", nullable = false)
	private long espera;
	/**
	 * Field cantidad.
	 */
	@Basic(optional = false)
	@Column(name = "CANTIDAD", nullable = false)
	private int cantidad;
	/**
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "NOMBRE", nullable = false, length = 255)
	private String nombre;
	/**
	 * Field escarga.
	 */
	@Basic(optional = false)
	@Column(name = "ESCARGA", nullable = false)
	private char escarga;
	/**
	 * Field esdescarga.
	 */
	@Basic(optional = false)
	@Column(name = "ESDESCARGA", nullable = false)
	private char esdescarga;
	/**
	 * Field ultimasincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ULTIMASINCRONIZACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
	/**
	 * Field prioridad.
	 */
	@Basic(optional = false)
	@Column(name = "PRIORIDAD", nullable = false)
	private int prioridad;
	/**
	 * Field tipo.
	 */
	@Basic(optional = false)
	@Column(name = "TIPO", nullable = false)
	private char tipo;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;

	/**
	 * Field jpqlQuery.
	 */
	@Basic(optional = false)
	@Column(name = "JPQL_QUERY", nullable = true)
	private String jpqlQuery;

	/**
	 * Constructor for Sincronizacion.
	 */
	public Sincronizacion() {
	}

	/**
	 * Constructor for Sincronizacion.
	 * 
	 * @param id
	 *            Long
	 */
	public Sincronizacion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Sincronizacion.
	 * 
	 * @param id
	 *            Long
	 * @param idGrupo
	 *            long
	 * @param espera
	 *            long
	 * @param cantidad
	 *            int
	 * @param nombre
	 *            String
	 * @param escarga
	 *            char
	 * @param esdescarga
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param prioridad
	 *            int
	 * @param tipo
	 *            char
	 * @param estreplica
	 *            char
	 */
	public Sincronizacion(Long id, long idGrupo, long espera, int cantidad,
			String nombre, char escarga, char esdescarga,
			Calendar ultimasincronizacion, int prioridad, char tipo,
			char estreplica) {
		this.id = id;
		this.idGrupo = idGrupo;
		this.espera = espera;
		this.cantidad = cantidad;
		this.nombre = nombre;
		this.escarga = escarga;
		this.esdescarga = esdescarga;
		this.ultimasincronizacion = ultimasincronizacion;
		this.prioridad = prioridad;
		this.tipo = tipo;
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
	 * Method getIdGrupo.
	 * 
	 * @return long
	 */
	public long getIdGrupo() {
		return idGrupo;
	}

	/**
	 * Method setIdGrupo.
	 * 
	 * @param idGrupo
	 *            long
	 */
	public void setIdGrupo(long idGrupo) {
		this.idGrupo = idGrupo;
	}

	/**
	 * Method getEspera.
	 * 
	 * @return long
	 */
	public long getEspera() {
		return espera;
	}

	/**
	 * Method setEspera.
	 * 
	 * @param espera
	 *            long
	 */
	public void setEspera(long espera) {
		this.espera = espera;
	}

	/**
	 * Method getCantidad.
	 * 
	 * @return int
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Method setCantidad.
	 * 
	 * @param cantidad
	 *            int
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
	 * Method getEscarga.
	 * 
	 * @return char
	 */
	public char getEscarga() {
		return escarga;
	}

	/**
	 * Method setEscarga.
	 * 
	 * @param escarga
	 *            char
	 */
	public void setEscarga(char escarga) {
		this.escarga = escarga;
	}

	/**
	 * Method getEsdescarga.
	 * 
	 * @return char
	 */
	public char getEsdescarga() {
		return esdescarga;
	}

	/**
	 * Method setEsdescarga.
	 * 
	 * @param esdescarga
	 *            char
	 */
	public void setEsdescarga(char esdescarga) {
		this.esdescarga = esdescarga;
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
	 * Method getTipo.
	 * 
	 * @return char
	 */
	public char getTipo() {
		return tipo;
	}

	/**
	 * Method setTipo.
	 * 
	 * @param tipo
	 *            char
	 */
	public void setTipo(char tipo) {
		this.tipo = tipo;
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
	 * Method getJpqlQuery.
	 * 
	 * @return String
	 */
	public String getJpqlQuery() {
		return jpqlQuery;
	}

	/**
	 * Method setJpqlQuery.
	 * 
	 * @param sqlQuery
	 *            String
	 */
	public void setJpqlQuery(String sqlQuery) {
		this.jpqlQuery = sqlQuery;
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
		if (!(object instanceof Sincronizacion)) {
			return false;
		}
		Sincronizacion other = (Sincronizacion) object;
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
		return "crjpa400.Sincronizacion[ id=" + id + " ]";
	}

}
