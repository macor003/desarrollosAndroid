/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "familia")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Familia.findAll", query = "SELECT f FROM Familia f"),
		@NamedQuery(name = "Familia.findById", query = "SELECT f FROM Familia f WHERE f.id = :id"),
		@NamedQuery(name = "Familia.findByDescripcion", query = "SELECT f FROM Familia f WHERE f.descripcion = :descripcion") })
public class Familia implements Serializable {
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
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "descripcion")
	private String descripcion;
	/**
	 * Field idLinea.
	 */
	@JoinColumn(name = "id_linea", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Linea idLinea;
	/**
	 * Field articuloList.
	 */
	@OneToMany(mappedBy = "idFamilia")
	private List<Articulo> articuloList;
	/**
	 * Field promocionfamiliaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFamilia")
	private List<Promocionfamilia> promocionfamiliaList;

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
	 */
	public Familia(Long id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
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
	public void setPromocionfamiliaList(List<Promocionfamilia> promocionfamiliaList) {
		this.promocionfamiliaList = promocionfamiliaList;
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

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(idLinea.getId());
		sb.append(separator);

		sb.append(enclosed);
		sb.append(descripcion);
		sb.append(enclosed);
		sb.append(endStr);

		return sb.toString();
	}

}
