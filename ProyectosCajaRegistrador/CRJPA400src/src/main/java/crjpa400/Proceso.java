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
@Table(name = "PROCESO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Proceso.findAll", query = "SELECT p FROM Proceso p"),
		@NamedQuery(name = "Proceso.findById", query = "SELECT p FROM Proceso p WHERE p.id = :id"),
		@NamedQuery(name = "Proceso.findByDescripcion", query = "SELECT p FROM Proceso p WHERE p.descripcion = :descripcion"),
		@NamedQuery(name = "Proceso.findByFecha", query = "SELECT p FROM Proceso p WHERE p.fecha = :fecha"),
		@NamedQuery(name = "Proceso.findByTipoCaja", query = "SELECT p FROM Proceso p WHERE p.tipoCaja = :tipoCaja"),
		@NamedQuery(name = "Proceso.findByIdOriginal", query = "SELECT p FROM Proceso p WHERE p.idOriginal = :idOriginal"),
		@NamedQuery(name = "Proceso.findBySerequiereautorizacion", query = "SELECT p FROM Proceso p WHERE p.serequiereautorizacion = :serequiereautorizacion"),
		@NamedQuery(name = "Proceso.findByUltimasincronizacion", query = "SELECT p FROM Proceso p WHERE p.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Proceso.findByEsautoautorizado", query = "SELECT p FROM Proceso p WHERE p.esautoautorizado = :esautoautorizado"),
		@NamedQuery(name = "Proceso.findByEstreplica", query = "SELECT p FROM Proceso p WHERE p.estreplica = :estreplica") })
public class Proceso extends CrjpaInterface implements Serializable {
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
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
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
	 * Field serequiereautorizacion.
	 */
	@Basic(optional = false)
	@Column(name = "SEREQUIEREAUTORIZACION", nullable = false)
	private char serequiereautorizacion;
	/**
	 * Field ultimasincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ULTIMASINCRONIZACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
	/**
	 * Field esautoautorizado.
	 */
	@Basic(optional = false)
	@Column(name = "ESAUTOAUTORIZADO", nullable = false)
	private char esautoautorizado;
	/**
	 * Field permiteconciliacion.
	 */
	@Basic(optional = false)
	@Column(name = "PERMITECONCILIACION", nullable = false)
	private String permiteconciliacion;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field procesopropertyList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idProceso")
	private List<Procesoproperty> procesopropertyList;
	/**
	 * Field perfilprocesoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idProceso")
	private List<Perfilproceso> perfilprocesoList;

	/**
	 * Constructor for Proceso.
	 */
	public Proceso() {
	}

	/**
	 * Constructor for Proceso.
	 * 
	 * @param id
	 *            Long
	 */
	public Proceso(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Proceso.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param fecha
	 *            Date
	 * @param serequiereautorizacion
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param esautoautorizado
	 *            char
	 * @param estreplica
	 *            char
	 */
	public Proceso(Long id, String descripcion, Date fecha,
			char serequiereautorizacion, Calendar ultimasincronizacion,
			char esautoautorizado, char estreplica) {
		this.id = id;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.serequiereautorizacion = serequiereautorizacion;
		this.ultimasincronizacion = ultimasincronizacion;
		this.esautoautorizado = esautoautorizado;
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
	 * Method getSerequiereautorizacion.
	 * 
	 * @return char
	 */
	public char getSerequiereautorizacion() {
		return serequiereautorizacion;
	}

	/**
	 * Method setSerequiereautorizacion.
	 * 
	 * @param serequiereautorizacion
	 *            char
	 */
	public void setSerequiereautorizacion(char serequiereautorizacion) {
		this.serequiereautorizacion = serequiereautorizacion;
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
	 * Method getEsautoautorizado.
	 * 
	 * @return char
	 */
	public char getEsautoautorizado() {
		return esautoautorizado;
	}

	/**
	 * Method setEsautoautorizado.
	 * 
	 * @param esautoautorizado
	 *            char
	 */
	public void setEsautoautorizado(char esautoautorizado) {
		this.esautoautorizado = esautoautorizado;
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
	 * Method getProcesopropertyList.
	 * 
	 * @return List<Procesoproperty>
	 */
	@XmlTransient
	public List<Procesoproperty> getProcesopropertyList() {
		return procesopropertyList;
	}

	/**
	 * Method setProcesopropertyList.
	 * 
	 * @param procesopropertyList
	 *            List<Procesoproperty>
	 */
	public void setProcesopropertyList(List<Procesoproperty> procesopropertyList) {
		this.procesopropertyList = procesopropertyList;
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
		if (!(object instanceof Proceso)) {
			return false;
		}
		Proceso other = (Proceso) object;
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
		return "crjpa400.Proceso[ id=" + id + " ]";
	}

	public String getPermiteconciliacion() {
		return permiteconciliacion;
	}

	public void setPermiteconciliacion(String permiteconciliacion) {
		this.permiteconciliacion = permiteconciliacion;
	}

}
