/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "depuracion")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Depuracion.findAll", query = "SELECT d FROM Depuracion d"),
		@NamedQuery(name = "Depuracion.findById", query = "SELECT d FROM Depuracion d WHERE d.id = :id"),
		@NamedQuery(name = "Depuracion.findByDescripcion", query = "SELECT d FROM Depuracion d WHERE d.descripcion = :descripcion"),
		@NamedQuery(name = "Depuracion.findByPrioridad", query = "SELECT d FROM Depuracion d WHERE d.prioridad = :prioridad") })
public class Depuracion implements Serializable {
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
	 * Field query.
	 */
	@Basic(optional = false)
	@Lob
	@Column(name = "query")
	private String query;
	/**
	 * Field prioridad.
	 */
	@Basic(optional = false)
	@Column(name = "prioridad")
	private int prioridad;

	/**
	 * Constructor for Depuracion.
	 */
	public Depuracion() {
	}

	/**
	 * Constructor for Depuracion.
	 * 
	 * @param id
	 *            Long
	 */
	public Depuracion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Depuracion.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param query
	 *            String
	 * @param prioridad
	 *            int
	 */
	public Depuracion(Long id, String descripcion, String query, int prioridad) {
		this.id = id;
		this.descripcion = descripcion;
		this.query = query;
		this.prioridad = prioridad;
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
	 * Method getQuery.
	 * 
	 * @return String
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Method setQuery.
	 * 
	 * @param query
	 *            String
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * Method getPrioridad.
	 * 
	 * @return int
	 */
	public int getPrioridad() {
		return prioridad;
	}

	/**
	 * Method setPrioridad.
	 * 
	 * @param prioridad
	 *            int
	 */
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
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
		if (!(object instanceof Depuracion)) {
			return false;
		}
		Depuracion other = (Depuracion) object;
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

		sb.append(enclosed);
		sb.append(descripcion);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(query);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(prioridad);
		sb.append(endStr);

		return sb.toString();
	}

}
