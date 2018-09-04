/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "unidadventa")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Unidadventa.findAll", query = "SELECT u FROM Unidadventa u"),
		@NamedQuery(name = "Unidadventa.findById", query = "SELECT u FROM Unidadventa u WHERE u.id = :id"),
		@NamedQuery(name = "Unidadventa.findByNombre", query = "SELECT u FROM Unidadventa u WHERE u.nombre = :nombre"),
		@NamedQuery(name = "Unidadventa.findBySimbolo", query = "SELECT u FROM Unidadventa u WHERE u.simbolo = :simbolo"),
		@NamedQuery(name = "Unidadventa.findByEsfraccion", query = "SELECT u FROM Unidadventa u WHERE u.esfraccion = :esfraccion"),
		@NamedQuery(name = "Unidadventa.findByFecha", query = "SELECT u FROM Unidadventa u WHERE u.fecha = :fecha"),
		@NamedQuery(name = "Unidadventa.findByEstaactivo", query = "SELECT u FROM Unidadventa u WHERE u.estaactivo = :estaactivo") })
public class Unidadventa implements Serializable {
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
	 * Field esfraccion.
	 */
	@Basic(optional = false)
	@Column(name = "esfraccion")
	private String esfraccion;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;

	// @OneToMany(mappedBy = "idUnidadventa")
	// private List<Transaccionarticulo> transaccionarticuloList;
	// @OneToMany(mappedBy = "idUnidadventa")
	// private List<Articulounidadventa> articulounidadventaList;

	/**
	 * Constructor for Unidadventa.
	 */
	public Unidadventa() {
	}

	/**
	 * Constructor for Unidadventa.
	 * 
	 * @param id
	 *            Long
	 */
	public Unidadventa(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Unidadventa.
	 * 
	 * @param id
	 *            Long
	 * @param nombre
	 *            String
	 * @param simbolo
	 *            String
	 * @param esfraccion
	 *            String
	 * @param fecha
	 *            Date
	 * @param estaactivo
	 *            String
	 */
	public Unidadventa(Long id, String nombre, String simbolo, String esfraccion, Date fecha, String estaactivo) {
		this.id = id;
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.esfraccion = esfraccion;
		this.fecha = fecha;
		this.estaactivo = estaactivo;
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
	 * Method getEsfraccion.
	 * 
	 * @return String
	 */
	public String getEsfraccion() {
		return esfraccion;
	}

	/**
	 * Method setEsfraccion.
	 * 
	 * @param esfraccion
	 *            String
	 */
	public void setEsfraccion(String esfraccion) {
		this.esfraccion = esfraccion;
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
	 * @return String
	 */
	public String getEstaactivo() {
		return estaactivo;
	}

	/**
	 * Method setEstaactivo.
	 * 
	 * @param estaactivo
	 *            String
	 */
	public void setEstaactivo(String estaactivo) {
		this.estaactivo = estaactivo;
	}

	// @XmlTransient
	// public List<Transaccionarticulo> getTransaccionarticuloList() {
	// return transaccionarticuloList;
	// }
	//
	// public void setTransaccionarticuloList(List<Transaccionarticulo>
	// transaccionarticuloList) {
	// this.transaccionarticuloList = transaccionarticuloList;
	// }
	//
	// @XmlTransient
	// public List<Articulounidadventa> getArticulounidadventaList() {
	// return articulounidadventaList;
	// }
	//
	// public void setArticulounidadventaList(List<Articulounidadventa>
	// articulounidadventaList) {
	// this.articulounidadventaList = articulounidadventaList;
	// }

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
		if (!(object instanceof Unidadventa)) {
			return false;
		}
		Unidadventa other = (Unidadventa) object;
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
		String endStr = "\r\n";
		String nullStr = "\\N";
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

		sb.append(esfraccion);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(estaactivo);

		sb.append(endStr);

		return sb.toString();
	}

}
