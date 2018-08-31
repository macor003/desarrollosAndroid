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
@Table(name = "TASAIMPUESTOTIPO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tasaimpuestotipo.findAll", query = "SELECT t FROM Tasaimpuestotipo t"),
		@NamedQuery(name = "Tasaimpuestotipo.findById", query = "SELECT t FROM Tasaimpuestotipo t WHERE t.id = :id"),
		@NamedQuery(name = "Tasaimpuestotipo.findByNombre", query = "SELECT t FROM Tasaimpuestotipo t WHERE t.nombre = :nombre"),
		@NamedQuery(name = "Tasaimpuestotipo.findByEstavigente", query = "SELECT t FROM Tasaimpuestotipo t WHERE t.estavigente = :estavigente"),
		@NamedQuery(name = "Tasaimpuestotipo.findByFecha", query = "SELECT t FROM Tasaimpuestotipo t WHERE t.fecha = :fecha"),
		@NamedQuery(name = "Tasaimpuestotipo.findByCodigo", query = "SELECT t FROM Tasaimpuestotipo t WHERE t.codigo = :codigo"),
		@NamedQuery(name = "Tasaimpuestotipo.findByUltimasincronizacion", query = "SELECT t FROM Tasaimpuestotipo t WHERE t.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Tasaimpuestotipo.findByEstreplica", query = "SELECT t FROM Tasaimpuestotipo t WHERE t.estreplica = :estreplica") })
public class Tasaimpuestotipo extends CrjpaInterface implements Serializable {
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
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "NOMBRE", nullable = false, length = 40)
	private String nombre;
	/**
	 * Field estavigente.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAVIGENTE", nullable = false)
	private char estavigente;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field codigo.
	 */
	@Basic(optional = false)
	@Column(name = "CODIGO")
	private String codigo;
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
	 * Field articuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTasaimpuestotipo")
	private List<Articulo> articuloList;
	/**
	 * Field tasaimpuestovalorList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTasaimpuestotipo")
	private List<Tasaimpuestovalor> tasaimpuestovalorList;

	/**
	 * Constructor for Tasaimpuestotipo.
	 */
	public Tasaimpuestotipo() {
	}

	/**
	 * Constructor for Tasaimpuestotipo.
	 * 
	 * @param id
	 *            Long
	 */
	public Tasaimpuestotipo(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Tasaimpuestotipo.
	 * 
	 * @param id
	 *            Long
	 * @param nombre
	 *            String
	 * @param estavigente
	 *            char
	 * @param fecha
	 *            Date
	 * @param codigo
	 *            String
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Tasaimpuestotipo(Long id, String nombre, char estavigente,
			Date fecha, String codigo, Calendar ultimasincronizacion,
			char estreplica) {
		this.id = id;
		this.nombre = nombre;
		this.estavigente = estavigente;
		this.fecha = fecha;
		this.codigo = codigo;
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
	 * Method getEstavigente.
	 * 
	 * @return char
	 */
	public char getEstavigente() {
		return estavigente;
	}

	/**
	 * Method setEstavigente.
	 * 
	 * @param estavigente
	 *            char
	 */
	public void setEstavigente(char estavigente) {
		this.estavigente = estavigente;
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
	 * Method getCodigo.
	 * 
	 * @return String
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Method setCodigo.
	 * 
	 * @param codigo
	 *            String
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 * Method getTasaimpuestovalorList.
	 * 
	 * @return List<Tasaimpuestovalor>
	 */
	@XmlTransient
	public List<Tasaimpuestovalor> getTasaimpuestovalorList() {
		return tasaimpuestovalorList;
	}

	/**
	 * Method setTasaimpuestovalorList.
	 * 
	 * @param tasaimpuestovalorList
	 *            List<Tasaimpuestovalor>
	 */
	public void setTasaimpuestovalorList(
			List<Tasaimpuestovalor> tasaimpuestovalorList) {
		this.tasaimpuestovalorList = tasaimpuestovalorList;
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
		if (!(object instanceof Tasaimpuestotipo)) {
			return false;
		}
		Tasaimpuestotipo other = (Tasaimpuestotipo) object;
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
		return "crjpa400.Tasaimpuestotipo[ id=" + id + " ]";
	}

}
