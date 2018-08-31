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
@Table(name = "OPERACIONACREENCIA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Operacionacreencia.findAll", query = "SELECT o FROM Operacionacreencia o"),
		@NamedQuery(name = "Operacionacreencia.findById", query = "SELECT o FROM Operacionacreencia o WHERE o.id = :id"),
		@NamedQuery(name = "Operacionacreencia.findByDescripcion", query = "SELECT o FROM Operacionacreencia o WHERE o.descripcion = :descripcion"),
		@NamedQuery(name = "Operacionacreencia.findByUltimasincronizacion", query = "SELECT o FROM Operacionacreencia o WHERE o.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Operacionacreencia.findByEstreplica", query = "SELECT o FROM Operacionacreencia o WHERE o.estreplica = :estreplica") })
public class Operacionacreencia extends CrjpaInterface implements Serializable {
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
	@Column(name = "DESCRIPCION", nullable = false, length = 45)
	private String descripcion;
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
	 * Field tipoacreenciaoperacionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperacionacreencia")
	private List<Tipoacreenciaoperacion> tipoacreenciaoperacionList;
	/**
	 * Field acreenciamovimientosaldoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperacionacreencia")
	private List<Acreenciamovimientosaldo> acreenciamovimientosaldoList;
	/**
	 * Field acreenciamovimientoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperacionacreencia")
	private List<Acreenciamovimiento> acreenciamovimientoList;

	/**
	 * Constructor for Operacionacreencia.
	 */
	public Operacionacreencia() {
	}

	/**
	 * Constructor for Operacionacreencia.
	 * 
	 * @param id
	 *            Long
	 */
	public Operacionacreencia(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Operacionacreencia.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Operacionacreencia(Long id, String descripcion,
			Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.descripcion = descripcion;
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
		if (!(object instanceof Operacionacreencia)) {
			return false;
		}
		Operacionacreencia other = (Operacionacreencia) object;
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
		return "crjpa400.Operacionacreencia[ id=" + id + " ]";
	}

}
