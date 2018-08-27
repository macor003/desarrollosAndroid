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
@Table(name = "TIPOACREENCIA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tipoacreencia.findAll", query = "SELECT t FROM Tipoacreencia t"),
		@NamedQuery(name = "Tipoacreencia.findById", query = "SELECT t FROM Tipoacreencia t WHERE t.id = :id"),
		@NamedQuery(name = "Tipoacreencia.findByDescripcion", query = "SELECT t FROM Tipoacreencia t WHERE t.descripcion = :descripcion"),
		@NamedQuery(name = "Tipoacreencia.findByRequiereclave", query = "SELECT t FROM Tipoacreencia t WHERE t.requiereclave = :requiereclave"),
		@NamedQuery(name = "Tipoacreencia.findByUltimasincronizacion", query = "SELECT t FROM Tipoacreencia t WHERE t.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Tipoacreencia.findByCuentacontable", query = "SELECT t FROM Tipoacreencia t WHERE t.cuentacontable = :cuentacontable"),
		@NamedQuery(name = "Tipoacreencia.findByEstreplica", query = "SELECT t FROM Tipoacreencia t WHERE t.estreplica = :estreplica") })
public class Tipoacreencia extends CrjpaInterface implements Serializable {
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
	@Column(name = "DESCRIPCION", nullable = false, length = 50)
	private String descripcion;
	/**
	 * Field requiereclave.
	 */
	@Basic(optional = false)
	@Column(name = "REQUIERECLAVE", nullable = false)
	private char requiereclave;
	/**
	 * Field ultimasincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ULTIMASINCRONIZACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
	/**
	 * Field cuentacontable.
	 */
	@Basic(optional = false)
	@Column(name = "CUENTACONTABLE", nullable = false, length = 20)
	private String cuentacontable;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field tipoacreenciaoperacionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoacreencia")
	private List<Tipoacreenciaoperacion> tipoacreenciaoperacionList;
	/**
	 * Field acreenciamovimientosaldoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoacreencia")
	private List<Acreenciamovimientosaldo> acreenciamovimientosaldoList;
	/**
	 * Field acreenciamovimientoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoacreencia")
	private List<Acreenciamovimiento> acreenciamovimientoList;
	/**
	 * Field saldoacreenciaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoacreencia")
	private List<Saldoacreencia> saldoacreenciaList;

	/**
	 * Constructor for Tipoacreencia.
	 */
	public Tipoacreencia() {
	}

	/**
	 * Constructor for Tipoacreencia.
	 * 
	 * @param id
	 *            Long
	 */
	public Tipoacreencia(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Tipoacreencia.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param requiereclave
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param cuentacontable
	 *            String
	 * @param estreplica
	 *            char
	 */
	public Tipoacreencia(Long id, String descripcion, char requiereclave,
			Calendar ultimasincronizacion, String cuentacontable,
			char estreplica) {
		this.id = id;
		this.descripcion = descripcion;
		this.requiereclave = requiereclave;
		this.ultimasincronizacion = ultimasincronizacion;
		this.cuentacontable = cuentacontable;
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
	 * Method getRequiereclave.
	 * 
	 * @return char
	 */
	public char getRequiereclave() {
		return requiereclave;
	}

	/**
	 * Method setRequiereclave.
	 * 
	 * @param requiereclave
	 *            char
	 */
	public void setRequiereclave(char requiereclave) {
		this.requiereclave = requiereclave;
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
	 * Method getCuentacontable.
	 * 
	 * @return String
	 */
	public String getCuentacontable() {
		return cuentacontable;
	}

	/**
	 * Method setCuentacontable.
	 * 
	 * @param cuentacontable
	 *            String
	 */
	public void setCuentacontable(String cuentacontable) {
		this.cuentacontable = cuentacontable;
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
	 * Method getTipoacreenciaoperacionList.
	 * 
	 * @return List<Tipoacreenciaoperacion>
	 */
	@XmlTransient
	public List<Tipoacreenciaoperacion> getTipoacreenciaoperacionList() {
		return tipoacreenciaoperacionList;
	}

	/**
	 * Method setTipoacreenciaoperacionList.
	 * 
	 * @param tipoacreenciaoperacionList
	 *            List<Tipoacreenciaoperacion>
	 */
	public void setTipoacreenciaoperacionList(
			List<Tipoacreenciaoperacion> tipoacreenciaoperacionList) {
		this.tipoacreenciaoperacionList = tipoacreenciaoperacionList;
	}

	/**
	 * Method getAcreenciamovimientosaldoList.
	 * 
	 * @return List<Acreenciamovimientosaldo>
	 */
	@XmlTransient
	public List<Acreenciamovimientosaldo> getAcreenciamovimientosaldoList() {
		return acreenciamovimientosaldoList;
	}

	/**
	 * Method setAcreenciamovimientosaldoList.
	 * 
	 * @param acreenciamovimientosaldoList
	 *            List<Acreenciamovimientosaldo>
	 */
	public void setAcreenciamovimientosaldoList(
			List<Acreenciamovimientosaldo> acreenciamovimientosaldoList) {
		this.acreenciamovimientosaldoList = acreenciamovimientosaldoList;
	}

	/**
	 * Method getAcreenciamovimientoList.
	 * 
	 * @return List<Acreenciamovimiento>
	 */
	@XmlTransient
	public List<Acreenciamovimiento> getAcreenciamovimientoList() {
		return acreenciamovimientoList;
	}

	/**
	 * Method setAcreenciamovimientoList.
	 * 
	 * @param acreenciamovimientoList
	 *            List<Acreenciamovimiento>
	 */
	public void setAcreenciamovimientoList(
			List<Acreenciamovimiento> acreenciamovimientoList) {
		this.acreenciamovimientoList = acreenciamovimientoList;
	}

	/**
	 * Method getSaldoacreenciaList.
	 * 
	 * @return List<Saldoacreencia>
	 */
	@XmlTransient
	public List<Saldoacreencia> getSaldoacreenciaList() {
		return saldoacreenciaList;
	}

	/**
	 * Method setSaldoacreenciaList.
	 * 
	 * @param saldoacreenciaList
	 *            List<Saldoacreencia>
	 */
	public void setSaldoacreenciaList(List<Saldoacreencia> saldoacreenciaList) {
		this.saldoacreenciaList = saldoacreenciaList;
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
		if (!(object instanceof Tipoacreencia)) {
			return false;
		}
		Tipoacreencia other = (Tipoacreencia) object;
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
		return "crjpa400.Tipoacreencia[ id=" + id + " ]";
	}

}
