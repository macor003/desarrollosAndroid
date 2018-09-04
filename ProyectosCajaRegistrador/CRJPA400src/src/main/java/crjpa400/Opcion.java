/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

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
@Table(name = "OPCION")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Opcion.findAll", query = "SELECT o FROM Opcion o"),
		@NamedQuery(name = "Opcion.findById", query = "SELECT o FROM Opcion o WHERE o.id = :id"),
		@NamedQuery(name = "Opcion.findByDescripcion", query = "SELECT o FROM Opcion o WHERE o.descripcion = :descripcion"),
		@NamedQuery(name = "Opcion.findByValor", query = "SELECT o FROM Opcion o WHERE o.valor = :valor"),
		@NamedQuery(name = "Opcion.findByFecha", query = "SELECT o FROM Opcion o WHERE o.fecha = :fecha"),
		@NamedQuery(name = "Opcion.findByEstaactivo", query = "SELECT o FROM Opcion o WHERE o.estaactivo = :estaactivo"),
		@NamedQuery(name = "Opcion.findByUltimasincronizacion", query = "SELECT o FROM Opcion o WHERE o.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Opcion.findByIdOriginal", query = "SELECT o FROM Opcion o WHERE o.idOriginal = :idOriginal"),
		@NamedQuery(name = "Opcion.findByTipoCaja", query = "SELECT o FROM Opcion o WHERE o.tipoCaja = :tipoCaja"),
		@NamedQuery(name = "Opcion.findByEstreplica", query = "SELECT o FROM Opcion o WHERE o.estreplica = :estreplica"),
		@NamedQuery(name = "Opcion.findMinimalOptions", query = "SELECT COUNT(o) FROM Opcion o WHERE o.tipoCaja IS NULL") })
public class Opcion extends CrjpaInterface implements Serializable {
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
	@Column(name = "DESCRIPCION", nullable = false, length = 40)
	private String descripcion;
	/**
	 * Field valor.
	 */
	@Basic(optional = false)
	@Column(name = "VALOR", nullable = false, length = 1000)
	private String valor;
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
	 * Field idOriginal.
	 */
	@Column(name = "ID_ORIGINAL")
	private BigInteger idOriginal;
	/**
	 * Field tipoCaja.
	 */
	@Column(name = "TIPO_CAJA")
	private Integer tipoCaja;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;

	/**
	 * Constructor for Opcion.
	 */
	public Opcion() {
	}

	/**
	 * Constructor for Opcion.
	 * 
	 * @param id
	 *            Long
	 */
	public Opcion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Opcion.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param valor
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
	public Opcion(Long id, String descripcion, String valor, Date fecha,
			char estaactivo, Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.descripcion = descripcion;
		this.valor = valor;
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
	 * Method getValor.
	 * 
	 * @return String
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Method setValor.
	 * 
	 * @param valor
	 *            String
	 */
	public void setValor(String valor) {
		this.valor = valor;
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
	 * Method getIdOriginal.
	 * 
	 * @return BigInteger
	 */
	public BigInteger getIdOriginal() {
		return idOriginal;
	}

	/**
	 * Method setIdOriginal.
	 * 
	 * @param idOriginal
	 *            BigInteger
	 */
	public void setIdOriginal(BigInteger idOriginal) {
		this.idOriginal = idOriginal;
	}

	/**
	 * Method getTipoCaja.
	 * 
	 * @return Integer
	 */
	public Integer getTipoCaja() {
		return tipoCaja;
	}

	/**
	 * Method setTipoCaja.
	 * 
	 * @param tipoCaja
	 *            Integer
	 */
	public void setTipoCaja(Integer tipoCaja) {
		this.tipoCaja = tipoCaja;
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
		if (!(object instanceof Opcion)) {
			return false;
		}
		Opcion other = (Opcion) object;
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
		return "crjpa400.Opcion[ id=" + id + " ]";
	}

}
