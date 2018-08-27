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
@Table(name = "tasaimpuestotipo")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tasaimpuestotipo.findAll", query = "SELECT t FROM Tasaimpuestotipo t"),
		@NamedQuery(name = "Tasaimpuestotipo.findById", query = "SELECT t FROM Tasaimpuestotipo t WHERE t.id = :id"),
		@NamedQuery(name = "Tasaimpuestotipo.findByNombre", query = "SELECT t FROM Tasaimpuestotipo t WHERE t.nombre = :nombre"),
		@NamedQuery(name = "Tasaimpuestotipo.findByEstavigente", query = "SELECT t FROM Tasaimpuestotipo t WHERE t.estavigente = :estavigente"),
		@NamedQuery(name = "Tasaimpuestotipo.findByFecha", query = "SELECT t FROM Tasaimpuestotipo t WHERE t.fecha = :fecha"),
		@NamedQuery(name = "Tasaimpuestotipo.findByCodigo", query = "SELECT t FROM Tasaimpuestotipo t WHERE t.codigo = :codigo") })
public class Tasaimpuestotipo implements Serializable {
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
	 * Field estavigente.
	 */
	@Basic(optional = false)
	@Column(name = "estavigente")
	private String estavigente;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field codigo.
	 */
	@Basic(optional = false)
	@Column(name = "codigo")
	private String codigo;
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
	 *            String
	 * @param fecha
	 *            Date
	 * @param codigo
	 *            String
	 */
	public Tasaimpuestotipo(Long id, String nombre, String estavigente, Date fecha, String codigo) {
		this.id = id;
		this.nombre = nombre;
		this.estavigente = estavigente;
		this.fecha = fecha;
		this.codigo = codigo;
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
	 * @return String
	 */
	public String getEstavigente() {
		return estavigente;
	}

	/**
	 * Method setEstavigente.
	 * 
	 * @param estavigente
	 *            String
	 */
	public void setEstavigente(String estavigente) {
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
	public void setTasaimpuestovalorList(List<Tasaimpuestovalor> tasaimpuestovalorList) {
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

		sb.append(estavigente);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(codigo);

		sb.append(endStr);

		return sb.toString();
	}

}
