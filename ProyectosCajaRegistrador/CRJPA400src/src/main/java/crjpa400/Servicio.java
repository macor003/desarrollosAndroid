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
@Table(name = "SERVICIO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s"),
		@NamedQuery(name = "Servicio.findById", query = "SELECT s FROM Servicio s WHERE s.id = :id"),
		@NamedQuery(name = "Servicio.findByDescripcion", query = "SELECT s FROM Servicio s WHERE s.descripcion = :descripcion"),
		@NamedQuery(name = "Servicio.findByUltimasincronizacion", query = "SELECT s FROM Servicio s WHERE s.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Servicio.findByEstreplica", query = "SELECT s FROM Servicio s WHERE s.estreplica = :estreplica") })
public class Servicio extends CrjpaInterface implements Serializable {
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
	@Column(name = "DESCRIPCION", nullable = false, length = 80)
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
	 * Field articuloservicioList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "servicio")
	private List<Articuloservicio> articuloservicioList;
	/**
	 * Field transaccionarticuloservicioList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idServicio")
	private List<Transaccionarticuloservicio> transaccionarticuloservicioList;

	/**
	 * Constructor for Servicio.
	 */
	public Servicio() {
	}

	/**
	 * Constructor for Servicio.
	 * 
	 * @param id
	 *            Long
	 */
	public Servicio(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Servicio.
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
	public Servicio(Long id, String descripcion, Calendar ultimasincronizacion,
			char estreplica) {
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
	 * Method getArticuloservicioList.
	 * 
	 * @return List<Articuloservicio>
	 */
	@XmlTransient
	public List<Articuloservicio> getArticuloservicioList() {
		return articuloservicioList;
	}

	/**
	 * Method setArticuloservicioList.
	 * 
	 * @param articuloservicioList
	 *            List<Articuloservicio>
	 */
	public void setArticuloservicioList(
			List<Articuloservicio> articuloservicioList) {
		this.articuloservicioList = articuloservicioList;
	}

	/**
	 * Method getTransaccionarticuloservicioList.
	 * 
	 * @return List<Transaccionarticuloservicio>
	 */
	@XmlTransient
	public List<Transaccionarticuloservicio> getTransaccionarticuloservicioList() {
		return transaccionarticuloservicioList;
	}

	/**
	 * Method setTransaccionarticuloservicioList.
	 * 
	 * @param transaccionarticuloservicioList
	 *            List<Transaccionarticuloservicio>
	 */
	public void setTransaccionarticuloservicioList(
			List<Transaccionarticuloservicio> transaccionarticuloservicioList) {
		this.transaccionarticuloservicioList = transaccionarticuloservicioList;
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
		if (!(object instanceof Servicio)) {
			return false;
		}
		Servicio other = (Servicio) object;
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
		return "crjpa400.Servicio[ id=" + id + " ]";
	}

}
