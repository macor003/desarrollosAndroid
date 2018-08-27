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
@Table(name = "MONEDA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Moneda.findAll", query = "SELECT m FROM Moneda m"),
		@NamedQuery(name = "Moneda.findById", query = "SELECT m FROM Moneda m WHERE m.id = :id"),
		@NamedQuery(name = "Moneda.findByNombre", query = "SELECT m FROM Moneda m WHERE m.nombre = :nombre"),
		@NamedQuery(name = "Moneda.findBySimbolo", query = "SELECT m FROM Moneda m WHERE m.simbolo = :simbolo"),
		@NamedQuery(name = "Moneda.findByIconoMoneda", query = "SELECT m FROM Moneda m WHERE m.iconoMoneda = :iconoMoneda"),
		@NamedQuery(name = "Moneda.findByEsprincipal", query = "SELECT m FROM Moneda m WHERE m.esprincipal = :esprincipal"),
		@NamedQuery(name = "Moneda.findByFecha", query = "SELECT m FROM Moneda m WHERE m.fecha = :fecha"),
		@NamedQuery(name = "Moneda.findByUltimasincronizacion", query = "SELECT m FROM Moneda m WHERE m.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Moneda.findByEstreplica", query = "SELECT m FROM Moneda m WHERE m.estreplica = :estreplica") })
public class Moneda extends CrjpaInterface implements Serializable {
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
	 * Field simbolo.
	 */
	@Basic(optional = false)
	@Column(name = "SIMBOLO", nullable = false, length = 4)
	private String simbolo;
	/**
	 * Field iconoMoneda.
	 */
	@Column(name = "ICONO_MONEDA", length = 255)
	private String iconoMoneda;
	/**
	 * Field esprincipal.
	 */
	@Basic(optional = false)
	@Column(name = "ESPRINCIPAL", nullable = false)
	private char esprincipal;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
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
	 * Field formadepagomovacreenciaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMoneda")
	private List<Formadepagomovacreencia> formadepagomovacreenciaList;
	/**
	 * Field transaccionformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMoneda")
	private List<Transaccionformadepago> transaccionformadepagoList;
	/**
	 * Field monedadenominacionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMoneda")
	private List<Monedadenominacion> monedadenominacionList;
	/**
	 * Field monedatasacambioList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMoneda")
	private List<Monedatasacambio> monedatasacambioList;
	/**
	 * Field acreenciamovimientoformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMoneda")
	private List<Acreenciamovimientoformadepago> acreenciamovimientoformadepagoList;
	/**
	 * Field abonoformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMoneda")
	private List<Abonoformadepago> abonoformadepagoList;
	/**
	 * Field acreenciamovimientoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMoneda")
	private List<Acreenciamovimiento> acreenciamovimientoList;
	/**
	 * Field formadepagomonedaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMoneda")
	private List<Formadepagomoneda> formadepagomonedaList;

	/**
	 * Constructor for Moneda.
	 */
	public Moneda() {
	}

	/**
	 * Constructor for Moneda.
	 * 
	 * @param id
	 *            Long
	 */
	public Moneda(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Moneda.
	 * 
	 * @param id
	 *            Long
	 * @param nombre
	 *            String
	 * @param simbolo
	 *            String
	 * @param esprincipal
	 *            char
	 * @param fecha
	 *            Date
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Moneda(Long id, String nombre, String simbolo, char esprincipal,
			Date fecha, Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.esprincipal = esprincipal;
		this.fecha = fecha;
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
	 * Method getSimbolo.
	 * 
	 * @return String
	 */
	public String getSimbolo() {
		return simbolo;
	}

	/**
	 * Method setSimbolo.
	 * 
	 * @param simbolo
	 *            String
	 */
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	/**
	 * Method getIconoMoneda.
	 * 
	 * @return String
	 */
	public String getIconoMoneda() {
		return iconoMoneda;
	}

	/**
	 * Method setIconoMoneda.
	 * 
	 * @param iconoMoneda
	 *            String
	 */
	public void setIconoMoneda(String iconoMoneda) {
		this.iconoMoneda = iconoMoneda;
	}

	/**
	 * Method getEsprincipal.
	 * 
	 * @return char
	 */
	public char getEsprincipal() {
		return esprincipal;
	}

	/**
	 * Method setEsprincipal.
	 * 
	 * @param esprincipal
	 *            char
	 */
	public void setEsprincipal(char esprincipal) {
		this.esprincipal = esprincipal;
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
	 * Method getFormadepagomovacreenciaList.
	 * 
	 * @return List<Formadepagomovacreencia>
	 */
	@XmlTransient
	public List<Formadepagomovacreencia> getFormadepagomovacreenciaList() {
		return formadepagomovacreenciaList;
	}

	/**
	 * Method setFormadepagomovacreenciaList.
	 * 
	 * @param formadepagomovacreenciaList
	 *            List<Formadepagomovacreencia>
	 */
	public void setFormadepagomovacreenciaList(
			List<Formadepagomovacreencia> formadepagomovacreenciaList) {
		this.formadepagomovacreenciaList = formadepagomovacreenciaList;
	}

	/**
	 * Method getTransaccionformadepagoList.
	 * 
	 * @return List<Transaccionformadepago>
	 */
	@XmlTransient
	public List<Transaccionformadepago> getTransaccionformadepagoList() {
		return transaccionformadepagoList;
	}

	/**
	 * Method setTransaccionformadepagoList.
	 * 
	 * @param transaccionformadepagoList
	 *            List<Transaccionformadepago>
	 */
	public void setTransaccionformadepagoList(
			List<Transaccionformadepago> transaccionformadepagoList) {
		this.transaccionformadepagoList = transaccionformadepagoList;
	}

	/**
	 * Method getMonedadenominacionList.
	 * 
	 * @return List<Monedadenominacion>
	 */
	@XmlTransient
	public List<Monedadenominacion> getMonedadenominacionList() {
		return monedadenominacionList;
	}

	/**
	 * Method setMonedadenominacionList.
	 * 
	 * @param monedadenominacionList
	 *            List<Monedadenominacion>
	 */
	public void setMonedadenominacionList(
			List<Monedadenominacion> monedadenominacionList) {
		this.monedadenominacionList = monedadenominacionList;
	}

	/**
	 * Method getMonedatasacambioList.
	 * 
	 * @return List<Monedatasacambio>
	 */
	@XmlTransient
	public List<Monedatasacambio> getMonedatasacambioList() {
		return monedatasacambioList;
	}

	/**
	 * Method setMonedatasacambioList.
	 * 
	 * @param monedatasacambioList
	 *            List<Monedatasacambio>
	 */
	public void setMonedatasacambioList(
			List<Monedatasacambio> monedatasacambioList) {
		this.monedatasacambioList = monedatasacambioList;
	}

	/**
	 * Method getAcreenciamovimientoformadepagoList.
	 * 
	 * @return List<Acreenciamovimientoformadepago>
	 */
	@XmlTransient
	public List<Acreenciamovimientoformadepago> getAcreenciamovimientoformadepagoList() {
		return acreenciamovimientoformadepagoList;
	}

	/**
	 * Method setAcreenciamovimientoformadepagoList.
	 * 
	 * @param acreenciamovimientoformadepagoList
	 *            List<Acreenciamovimientoformadepago>
	 */
	public void setAcreenciamovimientoformadepagoList(
			List<Acreenciamovimientoformadepago> acreenciamovimientoformadepagoList) {
		this.acreenciamovimientoformadepagoList = acreenciamovimientoformadepagoList;
	}

	/**
	 * Method getAbonoformadepagoList.
	 * 
	 * @return List<Abonoformadepago>
	 */
	@XmlTransient
	public List<Abonoformadepago> getAbonoformadepagoList() {
		return abonoformadepagoList;
	}

	/**
	 * Method setAbonoformadepagoList.
	 * 
	 * @param abonoformadepagoList
	 *            List<Abonoformadepago>
	 */
	public void setAbonoformadepagoList(
			List<Abonoformadepago> abonoformadepagoList) {
		this.abonoformadepagoList = abonoformadepagoList;
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
	 * Method getFormadepagomonedaList.
	 * 
	 * @return List<Formadepagomoneda>
	 */
	@XmlTransient
	public List<Formadepagomoneda> getFormadepagomonedaList() {
		return formadepagomonedaList;
	}

	/**
	 * Method setFormadepagomonedaList.
	 * 
	 * @param formadepagomonedaList
	 *            List<Formadepagomoneda>
	 */
	public void setFormadepagomonedaList(
			List<Formadepagomoneda> formadepagomonedaList) {
		this.formadepagomonedaList = formadepagomonedaList;
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
		if (!(object instanceof Moneda)) {
			return false;
		}
		Moneda other = (Moneda) object;
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
		return "crjpa400.Moneda[ id=" + id + " ]";
	}

}
