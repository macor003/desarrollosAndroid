/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "moneda")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Moneda.findAll", query = "SELECT m FROM Moneda m"),
		@NamedQuery(name = "Moneda.findById", query = "SELECT m FROM Moneda m WHERE m.id = :id"),
		@NamedQuery(name = "Moneda.findByNombre", query = "SELECT m FROM Moneda m WHERE m.nombre = :nombre"),
		@NamedQuery(name = "Moneda.findBySimbolo", query = "SELECT m FROM Moneda m WHERE m.simbolo = :simbolo"),
		@NamedQuery(name = "Moneda.findByEsprincipal", query = "SELECT m FROM Moneda m WHERE m.esprincipal = :esprincipal"),
		@NamedQuery(name = "Moneda.findByFecha", query = "SELECT m FROM Moneda m WHERE m.fecha = :fecha"),
		@NamedQuery(name = "Moneda.findByIconoMoneda", query = "SELECT m FROM Moneda m WHERE m.iconoMoneda = :iconoMoneda") })
public class Moneda implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	/**
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	/**
	 * Field simbolo.
	 */
	@Basic(optional = false)
	@Column(name = "simbolo")
	private String simbolo;
	/**
	 * Field esprincipal.
	 */
	@Basic(optional = false)
	@Column(name = "esprincipal")
	private String esprincipal;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field iconoMoneda.
	 */
	@Column(name = "icono_moneda")
	private String iconoMoneda;
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
	 * Field formadepagomonedaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMoneda")
	private List<Formadepagomoneda> formadepagomonedaList;
	/**
	 * Field transaccionformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMoneda")
	private List<Transaccionformadepago> transaccionformadepagoList;

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
	 *            String
	 * @param fecha
	 *            Date
	 */
	public Moneda(Long id, String nombre, String simbolo, String esprincipal, Date fecha) {
		this.id = id;
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.esprincipal = esprincipal;
		this.fecha = fecha;
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
	 * Method getEsprincipal.
	 * 
	 * @return String
	 */
	public String getEsprincipal() {
		return esprincipal;
	}

	/**
	 * Method setEsprincipal.
	 * 
	 * @param esprincipal
	 *            String
	 */
	public void setEsprincipal(String esprincipal) {
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
	public void setMonedadenominacionList(List<Monedadenominacion> monedadenominacionList) {
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
	public void setMonedatasacambioList(List<Monedatasacambio> monedatasacambioList) {
		this.monedatasacambioList = monedatasacambioList;
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
	public void setFormadepagomonedaList(List<Formadepagomoneda> formadepagomonedaList) {
		this.formadepagomonedaList = formadepagomonedaList;
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
	public void setTransaccionformadepagoList(List<Transaccionformadepago> transaccionformadepagoList) {
		this.transaccionformadepagoList = transaccionformadepagoList;
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
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
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
		String separator = "@@";
		String enclosed = "|";
		String nullStr = "\\N";
		String endStr = "\r\n";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(nombre);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(simbolo);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(esprincipal);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		if (iconoMoneda == null) {
			sb.append(nullStr);
		} else {
			sb.append(iconoMoneda);
		}
		sb.append(endStr);
		return sb.toString();
	}

}
