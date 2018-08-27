/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "sincronizacion")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Sincronizacion.findAll", query = "SELECT s FROM Sincronizacion s"),
		@NamedQuery(name = "Sincronizacion.findById", query = "SELECT s FROM Sincronizacion s WHERE s.id = :id"),
		@NamedQuery(name = "Sincronizacion.findByIdGrupo", query = "SELECT s FROM Sincronizacion s WHERE s.idGrupo = :idGrupo"),
		@NamedQuery(name = "Sincronizacion.findByNombre", query = "SELECT s FROM Sincronizacion s WHERE s.nombre = :nombre"),
		@NamedQuery(name = "Sincronizacion.findByCantidad", query = "SELECT s FROM Sincronizacion s WHERE s.cantidad = :cantidad"),
		@NamedQuery(name = "Sincronizacion.findByEspera", query = "SELECT s FROM Sincronizacion s WHERE s.espera = :espera"),
		@NamedQuery(name = "Sincronizacion.findByEscarga", query = "SELECT s FROM Sincronizacion s WHERE s.escarga = :escarga"),
		@NamedQuery(name = "Sincronizacion.findByEsdescarga", query = "SELECT s FROM Sincronizacion s WHERE s.esdescarga = :esdescarga"),
		@NamedQuery(name = "Sincronizacion.findByUltimasincronizacion", query = "SELECT s FROM Sincronizacion s WHERE s.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Sincronizacion.findByPrioridad", query = "SELECT s FROM Sincronizacion s WHERE s.prioridad = :prioridad"),
		@NamedQuery(name = "Sincronizacion.findByTipo", query = "SELECT s FROM Sincronizacion s WHERE s.tipo = :tipo") })
public class Sincronizacion implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	/**
	 * Field idGrupo.
	 */
	@Basic(optional = false)
	@Column(name = "id_grupo")
	private long idGrupo;
	/**
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	/**
	 * Field cantidad.
	 */
	@Basic(optional = false)
	@Column(name = "cantidad")
	private int cantidad;
	/**
	 * Field espera.
	 */
	@Basic(optional = false)
	@Column(name = "espera")
	private long espera;
	/**
	 * Field escarga.
	 */
	@Basic(optional = false)
	@Column(name = "escarga")
	private String escarga;
	/**
	 * Field esdescarga.
	 */
	@Basic(optional = false)
	@Column(name = "esdescarga")
	private String esdescarga;
	/**
	 * Field ultimasincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ultimasincronizacion")
	private BigDecimal ultimasincronizacion;
	/**
	 * Field prioridad.
	 */
	@Basic(optional = false)
	@Column(name = "prioridad")
	private int prioridad;
	/**
	 * Field tipo.
	 */
	@Basic(optional = false)
	@Column(name = "tipo")
	private String tipo;

	/**
	 * Field jpqlQuery.
	 */
	@Basic(optional = false)
	@Column(name = "jpql_query", nullable = true)
	private String jpqlQuery;
	/**
	 * Field estasincronizado.
	 */
	@Basic(optional = false)
	@Column(name = "estasincronizado", nullable = false, length = 2)
	private String estasincronizado;

	/**
	 * Field mensaje_error
	 */
	@Basic(optional = false)
	@Column(name = "mensaje_error", nullable = true)
	private String mensajeError;

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
	 * @param nombre
	 *            String
	 * @param cantidad
	 *            int
	 * @param espera
	 *            long
	 * @param escarga
	 *            String
	 * @param esdescarga
	 *            String
	 * @param ultimasincronizacion
	 *            BigDecimal
	 * @param prioridad
	 *            int
	 * @param tipo
	 *            String
	 * @param estasincronizado
	 *            String
	 */
	public Sincronizacion(Long id, long idGrupo, String nombre, int cantidad, long espera, String escarga,
			String esdescarga, BigDecimal ultimasincronizacion, int prioridad, String tipo, String estasincronizado) {
		this.id = id;
		this.idGrupo = idGrupo;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.espera = espera;
		this.escarga = escarga;
		this.esdescarga = esdescarga;
		this.ultimasincronizacion = ultimasincronizacion;
		this.prioridad = prioridad;
		this.tipo = tipo;
		this.estasincronizado = estasincronizado;
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
	 * Method getEscarga.
	 * 
	 * @return String
	 */
	public String getEscarga() {
		return escarga;
	}

	/**
	 * Method setEscarga.
	 * 
	 * @param escarga
	 *            String
	 */
	public void setEscarga(String escarga) {
		this.escarga = escarga;
	}

	/**
	 * Method getEsdescarga.
	 * 
	 * @return String
	 */
	public String getEsdescarga() {
		return esdescarga;
	}

	/**
	 * Method setEsdescarga.
	 * 
	 * @param esdescarga
	 *            String
	 */
	public void setEsdescarga(String esdescarga) {
		this.esdescarga = esdescarga;
	}

	/**
	 * Method getUltimasincronizacion.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getUltimasincronizacion() {
		return ultimasincronizacion;
	}

	/**
	 * Method setUltimasincronizacion.
	 * 
	 * @param ultimasincronizacion
	 *            BigDecimal
	 */
	public void setUltimasincronizacion(BigDecimal ultimasincronizacion) {
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
	 * @return String
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Method setTipo.
	 * 
	 * @param tipo
	 *            String
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * Method getEstasincronizado.
	 * 
	 * @return String
	 */
	public String getEstasincronizado() {
		return estasincronizado;
	}

	/**
	 * Method setEstasincronizado.
	 * 
	 * @param estasincronizado
	 *            String
	 */
	public void setEstasincronizado(String estasincronizado) {
		this.estasincronizado = estasincronizado;
	}

	/**
	 * Method getMensajeError.
	 * 
	 * @return String
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * Method setMensajeError.
	 * 
	 * @param estasincronizado
	 *            String
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
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
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
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
		String separator = "@@";
		String enclosed = "|";
		String nullStr = "\\N";
		String endStr = "\r\n";

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(idGrupo);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(nombre);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(cantidad);
		sb.append(separator);

		sb.append(espera);
		sb.append(separator);

		sb.append(escarga);
		sb.append(separator);

		sb.append(esdescarga);
		sb.append(separator);

		sb.append(ultimasincronizacion);
		sb.append(separator);

		sb.append(prioridad);
		sb.append(separator);

		sb.append(tipo);
		sb.append(separator);

		if (jpqlQuery == null) {
			sb.append(nullStr);
		} else {
			sb.append(enclosed);
			sb.append(jpqlQuery);
			sb.append(enclosed);
		}
		sb.append(separator);

		sb.append(estasincronizado);
		sb.append(separator);
		
		sb.append(nullStr);
		sb.append(endStr);

		return sb.toString();
	}

}
