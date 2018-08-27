/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "sincronizaciondetalle")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Sincronizaciondetalle.findAll", query = "SELECT s FROM Sincronizaciondetalle s"),
		@NamedQuery(name = "Sincronizaciondetalle.findById", query = "SELECT s FROM Sincronizaciondetalle s WHERE s.id = :id"),
		@NamedQuery(name = "Sincronizaciondetalle.findByIdSincronizacion", query = "SELECT s FROM Sincronizaciondetalle s WHERE s.idSincronizacion = :idSincronizacion"),
		@NamedQuery(name = "Sincronizaciondetalle.findByNombreTablaDestino", query = "SELECT s FROM Sincronizaciondetalle s WHERE s.nombreTablaDestino = :nombreTablaDestino"),
		@NamedQuery(name = "Sincronizaciondetalle.findByNombreColumnaId", query = "SELECT s FROM Sincronizaciondetalle s WHERE s.nombreColumnaId = :nombreColumnaId"),
		@NamedQuery(name = "Sincronizaciondetalle.findByEscarga", query = "SELECT s FROM Sincronizaciondetalle s WHERE s.escarga = :escarga"),
		@NamedQuery(name = "Sincronizaciondetalle.findByEsdescarga", query = "SELECT s FROM Sincronizaciondetalle s WHERE s.esdescarga = :esdescarga"),
		@NamedQuery(name = "Sincronizaciondetalle.findByUltimasincronizacion", query = "SELECT s FROM Sincronizaciondetalle s WHERE s.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Sincronizaciondetalle.findByEscabecera", query = "SELECT s FROM Sincronizaciondetalle s WHERE s.escabecera = :escabecera"),
		@NamedQuery(name = "Sincronizaciondetalle.findByEsdetalle", query = "SELECT s FROM Sincronizaciondetalle s WHERE s.esdetalle = :esdetalle") })
public class Sincronizaciondetalle implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
	/**
	 * Field idSincronizacion.
	 */
	@Column(name = "id_sincronizacion")
	private BigInteger idSincronizacion;
	/**
	 * Field nombreTablaDestino.
	 */
	@Column(name = "nombre_tabla_destino", length = 45)
	private String nombreTablaDestino;
	/**
	 * Field queryCarga.
	 */
	@Lob
	@Column(name = "query_carga", length = 65535)
	private String queryCarga;
	/**
	 * Field queryDescarga.
	 */
	@Lob
	@Column(name = "query_descarga", length = 65535)
	private String queryDescarga;
	/**
	 * Field queryActualizaEstado.
	 */
	@Lob
	@Column(name = "query_actualiza_estado", length = 65535)
	private String queryActualizaEstado;
	/**
	 * Field nombreColumnaId.
	 */
	@Column(name = "nombre_columna_id", length = 45)
	private String nombreColumnaId;
	/**
	 * Field escarga.
	 */
	@Column(name = "escarga", length = 2)
	private String escarga;
	/**
	 * Field esdescarga.
	 */
	@Column(name = "esdescarga", length = 2)
	private String esdescarga;
	/**
	 * Field ultimasincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ultimasincronizacion", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
	/**
	 * Field queryDepuracion.
	 */
	@Lob
	@Column(name = "query_depuracion", length = 65535)
	private String queryDepuracion;
	/**
	 * Field escabecera.
	 */
	@Column(name = "escabecera", length = 2)
	private String escabecera;
	/**
	 * Field esdetalle.
	 */
	@Column(name = "esdetalle", length = 2)
	private String esdetalle;

	/**
	 * Constructor for Sincronizaciondetalle.
	 */
	public Sincronizaciondetalle() {
	}

	/**
	 * Constructor for Sincronizaciondetalle.
	 * 
	 * @param id
	 *            Long
	 */
	public Sincronizaciondetalle(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Sincronizaciondetalle.
	 * 
	 * @param id
	 *            Long
	 * @param ultimasincronizacion
	 *            Calendar
	 */
	public Sincronizaciondetalle(Long id, Calendar ultimasincronizacion) {
		this.id = id;
		this.ultimasincronizacion = ultimasincronizacion;
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
	 * Method getIdSincronizacion.
	 * 
	 * @return BigInteger
	 */
	public BigInteger getIdSincronizacion() {
		return idSincronizacion;
	}

	/**
	 * Method setIdSincronizacion.
	 * 
	 * @param idSincronizacion
	 *            BigInteger
	 */
	public void setIdSincronizacion(BigInteger idSincronizacion) {
		this.idSincronizacion = idSincronizacion;
	}

	/**
	 * Method getNombreTablaDestino.
	 * 
	 * @return String
	 */
	public String getNombreTablaDestino() {
		return nombreTablaDestino;
	}

	/**
	 * Method setNombreTablaDestino.
	 * 
	 * @param nombreTablaDestino
	 *            String
	 */
	public void setNombreTablaDestino(String nombreTablaDestino) {
		this.nombreTablaDestino = nombreTablaDestino;
	}

	/**
	 * Method getQueryCarga.
	 * 
	 * @return String
	 */
	public String getQueryCarga() {
		return queryCarga;
	}

	/**
	 * Method setQueryCarga.
	 * 
	 * @param queryCarga
	 *            String
	 */
	public void setQueryCarga(String queryCarga) {
		this.queryCarga = queryCarga;
	}

	/**
	 * Method getQueryDescarga.
	 * 
	 * @return String
	 */
	public String getQueryDescarga() {
		return queryDescarga;
	}

	/**
	 * Method setQueryDescarga.
	 * 
	 * @param queryDescarga
	 *            String
	 */
	public void setQueryDescarga(String queryDescarga) {
		this.queryDescarga = queryDescarga;
	}

	/**
	 * Method getQueryActualizaEstado.
	 * 
	 * @return String
	 */
	public String getQueryActualizaEstado() {
		return queryActualizaEstado;
	}

	/**
	 * Method setQueryActualizaEstado.
	 * 
	 * @param queryActualizaEstado
	 *            String
	 */
	public void setQueryActualizaEstado(String queryActualizaEstado) {
		this.queryActualizaEstado = queryActualizaEstado;
	}

	/**
	 * Method getNombreColumnaId.
	 * 
	 * @return String
	 */
	public String getNombreColumnaId() {
		return nombreColumnaId;
	}

	/**
	 * Method setNombreColumnaId.
	 * 
	 * @param nombreColumnaId
	 *            String
	 */
	public void setNombreColumnaId(String nombreColumnaId) {
		this.nombreColumnaId = nombreColumnaId;
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
	 * Method getQueryDepuracion.
	 * 
	 * @return String
	 */
	public String getQueryDepuracion() {
		return queryDepuracion;
	}

	/**
	 * Method setQueryDepuracion.
	 * 
	 * @param queryDepuracion
	 *            String
	 */
	public void setQueryDepuracion(String queryDepuracion) {
		this.queryDepuracion = queryDepuracion;
	}

	/**
	 * Method getEscabecera.
	 * 
	 * @return String
	 */
	public String getEscabecera() {
		return escabecera;
	}

	/**
	 * Method setEscabecera.
	 * 
	 * @param escabecera
	 *            String
	 */
	public void setEscabecera(String escabecera) {
		this.escabecera = escabecera;
	}

	/**
	 * Method getEsdetalle.
	 * 
	 * @return String
	 */
	public String getEsdetalle() {
		return esdetalle;
	}

	/**
	 * Method setEsdetalle.
	 * 
	 * @param esdetalle
	 *            String
	 */
	public void setEsdetalle(String esdetalle) {
		this.esdetalle = esdetalle;
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
		if (!(object instanceof Sincronizaciondetalle)) {
			return false;
		}
		Sincronizaciondetalle other = (Sincronizaciondetalle) object;
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
		return "crjpa.Sincronizaciondetalle[ id=" + id + " ]";
	}

}
