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
@Table(name = "PERFILPROCESO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Perfilproceso.findAll", query = "SELECT p FROM Perfilproceso p"),
		@NamedQuery(name = "Perfilproceso.findById", query = "SELECT p FROM Perfilproceso p WHERE p.id = :id"),
		@NamedQuery(name = "Perfilproceso.findByFecha", query = "SELECT p FROM Perfilproceso p WHERE p.fecha = :fecha"),
		@NamedQuery(name = "Perfilproceso.findByEstaactivo", query = "SELECT p FROM Perfilproceso p WHERE p.estaactivo = :estaactivo"),
		@NamedQuery(name = "Perfilproceso.findByUltimasincronizacion", query = "SELECT p FROM Perfilproceso p WHERE p.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Perfilproceso.findByTipoCaja", query = "SELECT p FROM Perfilproceso p WHERE p.tipoCaja = :tipoCaja"),
		@NamedQuery(name = "Perfilproceso.findByIdOriginal", query = "SELECT p FROM Perfilproceso p WHERE p.idOriginal = :idOriginal"),
		@NamedQuery(name = "Perfilproceso.findByEstreplica", query = "SELECT p FROM Perfilproceso p WHERE p.estreplica = :estreplica") })
public class Perfilproceso extends CrjpaInterface implements Serializable {
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
	 * Field tipoCaja.
	 */
	@Column(name = "TIPO_CAJA")
	private Integer tipoCaja;
	/**
	 * Field idOriginal.
	 */
	@Column(name = "ID_ORIGINAL")
	private BigInteger idOriginal;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idProceso.
	 */
	@JoinColumn(name = "ID_PROCESO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Proceso idProceso;
	/**
	 * Field idPerfil.
	 */
	@JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Perfil idPerfil;

	/**
	 * Constructor for Perfilproceso.
	 */
	public Perfilproceso() {
	}

	/**
	 * Constructor for Perfilproceso.
	 * 
	 * @param id
	 *            Long
	 */
	public Perfilproceso(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Perfilproceso.
	 * 
	 * @param id
	 *            Long
	 * @param fecha
	 *            Date
	 * @param estaactivo
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Perfilproceso(Long id, Date fecha, char estaactivo,
			Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
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
	 * Method getIdProceso.
	 * 
	 * @return Proceso
	 */
	public Proceso getIdProceso() {
		return idProceso;
	}

	/**
	 * Method setIdProceso.
	 * 
	 * @param idProceso
	 *            Proceso
	 */
	public void setIdProceso(Proceso idProceso) {
		this.idProceso = idProceso;
	}

	/**
	 * Method getIdPerfil.
	 * 
	 * @return Perfil
	 */
	public Perfil getIdPerfil() {
		return idPerfil;
	}

	/**
	 * Method setIdPerfil.
	 * 
	 * @param idPerfil
	 *            Perfil
	 */
	public void setIdPerfil(Perfil idPerfil) {
		this.idPerfil = idPerfil;
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
		if (!(object instanceof Perfilproceso)) {
			return false;
		}
		Perfilproceso other = (Perfilproceso) object;
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
		return "crjpa400.Perfilproceso[ id=" + id + " ]";
	}

}
