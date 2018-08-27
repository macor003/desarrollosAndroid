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
@Table(name = "FAMILIA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Familia.findAll", query = "SELECT f FROM Familia f"),
		@NamedQuery(name = "Familia.findById", query = "SELECT f FROM Familia f WHERE f.id = :id"),
		@NamedQuery(name = "Familia.findByDescripcion", query = "SELECT f FROM Familia f WHERE f.descripcion = :descripcion"),
		@NamedQuery(name = "Familia.findByUltimasincronizacion", query = "SELECT f FROM Familia f WHERE f.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Familia.findByEstreplica", query = "SELECT f FROM Familia f WHERE f.estreplica = :estreplica") })
public class Familia extends CrjpaInterface implements Serializable {
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
	 * Field promocionfamiliaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFamilia")
	private List<Promocionfamilia> promocionfamiliaList;
	/**
	 * Field idLinea.
	 */
	@JoinColumn(name = "ID_LINEA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Linea idLinea;
	/**
	 * Field articuloList.
	 */
	@OneToMany(mappedBy = "idFamilia")
	private List<Articulo> articuloList;

	/**
	 * Constructor for Familia.
	 */
	public Familia() {
	}

	/**
	 * Constructor for Familia.
	 * 
	 * @param id
	 *            Long
	 */
	public Familia(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Familia.
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
	public Familia(Long id, String descripcion, Calendar ultimasincronizacion,
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
	 * Method getPromocionfamiliaList.
	 * 
	 * @return List<Promocionfamilia>
	 */
	@XmlTransient
	public List<Promocionfamilia> getPromocionfamiliaList() {
		return promocionfamiliaList;
	}

	/**
	 * Method setPromocionfamiliaList.
	 * 
	 * @param promocionfamiliaList
	 *            List<Promocionfamilia>
	 */
	public void setPromocionfamiliaList(
			List<Promocionfamilia> promocionfamiliaList) {
		this.promocionfamiliaList = promocionfamiliaList;
	}

	/**
	 * Method getIdLinea.
	 * 
	 * @return Linea
	 */
	public Linea getIdLinea() {
		return idLinea;
	}

	/**
	 * Method setIdLinea.
	 * 
	 * @param idLinea
	 *            Linea
	 */
	public void setIdLinea(Linea idLinea) {
		this.idLinea = idLinea;
	}

	/**
	 * Method getArticuloList.
	 * 
	 * @return List<Articulo>
	 */
	@XmlTransient
	public List<Articulo> getArticuloList() {
		return articuloList;
	}

	/**
	 * Method setArticuloList.
	 * 
	 * @param articuloList
	 *            List<Articulo>
	 */
	public void setArticuloList(List<Articulo> articuloList) {
		this.articuloList = articuloList;
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
		if (!(object instanceof Familia)) {
			return false;
		}
		Familia other = (Familia) object;
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
		return "crjpa400.Familia[ id=" + id + " ]";
	}

}
