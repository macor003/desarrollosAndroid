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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "DETALLETIPOFORMADEPAGO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Detalletipoformadepago.findAll", query = "SELECT d FROM Detalletipoformadepago d"),
		@NamedQuery(name = "Detalletipoformadepago.findById", query = "SELECT d FROM Detalletipoformadepago d WHERE d.id = :id"),
		@NamedQuery(name = "Detalletipoformadepago.findByDescripcion", query = "SELECT d FROM Detalletipoformadepago d WHERE d.descripcion = :descripcion"),
		@NamedQuery(name = "Detalletipoformadepago.findByEstaactivo", query = "SELECT d FROM Detalletipoformadepago d WHERE d.estaactivo = :estaactivo"),
		@NamedQuery(name = "Detalletipoformadepago.findByUltimasincronizacion", query = "SELECT d FROM Detalletipoformadepago d WHERE d.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Detalletipoformadepago.findByEstreplica", query = "SELECT d FROM Detalletipoformadepago d WHERE d.estreplica = :estreplica") })
public class Detalletipoformadepago extends CrjpaInterface implements
		Serializable {
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
	@Column(name = "DESCRIPCION", nullable = false, length = 255)
	private String descripcion;
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
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field detalletipoformadepagolineaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalletipoformadepago")
	private List<Detalletipoformadepagolinea> detalletipoformadepagolineaList;
	/**
	 * Field idFormadepago.
	 */
	@JoinColumn(name = "ID_FORMADEPAGO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Formadepago idFormadepago;

	/**
	 * Constructor for Detalletipoformadepago.
	 */
	public Detalletipoformadepago() {
	}

	/**
	 * Constructor for Detalletipoformadepago.
	 * 
	 * @param id
	 *            Long
	 */
	public Detalletipoformadepago(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Detalletipoformadepago.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param estaactivo
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Detalletipoformadepago(Long id, String descripcion, char estaactivo,
			Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.descripcion = descripcion;
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
	 * Method getDetalletipoformadepagolineaList.
	 * 
	 * @return List<Detalletipoformadepagolinea>
	 */
	@XmlTransient
	public List<Detalletipoformadepagolinea> getDetalletipoformadepagolineaList() {
		return detalletipoformadepagolineaList;
	}

	/**
	 * Method setDetalletipoformadepagolineaList.
	 * 
	 * @param detalletipoformadepagolineaList
	 *            List<Detalletipoformadepagolinea>
	 */
	public void setDetalletipoformadepagolineaList(
			List<Detalletipoformadepagolinea> detalletipoformadepagolineaList) {
		this.detalletipoformadepagolineaList = detalletipoformadepagolineaList;
	}

	/**
	 * Method getIdFormadepago.
	 * 
	 * @return Formadepago
	 */
	public Formadepago getIdFormadepago() {
		return idFormadepago;
	}

	/**
	 * Method setIdFormadepago.
	 * 
	 * @param idFormadepago
	 *            Formadepago
	 */
	public void setIdFormadepago(Formadepago idFormadepago) {
		this.idFormadepago = idFormadepago;
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
		if (!(object instanceof Detalletipoformadepago)) {
			return false;
		}
		Detalletipoformadepago other = (Detalletipoformadepago) object;
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
		return "crjpa400.Detalletipoformadepago[ id=" + id + " ]";
	}

}
