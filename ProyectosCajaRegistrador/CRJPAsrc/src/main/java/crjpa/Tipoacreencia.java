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
@Table(name = "tipoacreencia")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tipoacreencia.findAll", query = "SELECT t FROM Tipoacreencia t"),
		@NamedQuery(name = "Tipoacreencia.findById", query = "SELECT t FROM Tipoacreencia t WHERE t.id = :id"),
		@NamedQuery(name = "Tipoacreencia.findByDescripcion", query = "SELECT t FROM Tipoacreencia t WHERE t.descripcion = :descripcion"),
		@NamedQuery(name = "Tipoacreencia.findByRequiereclave", query = "SELECT t FROM Tipoacreencia t WHERE t.requiereclave = :requiereclave") })
public class Tipoacreencia implements Serializable {
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
	 * Field requiereclave.
	 */
	@Basic(optional = false)
	@Column(name = "requiereclave")
	private String requiereclave;
	/**
	 * Field tipoacreenciaoperacionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoacreencia")
	private List<Tipoacreenciaoperacion> tipoacreenciaoperacionList;

	/**
	 * Constructor for Tipoacreencia.
	 */
	public Tipoacreencia() {
	}

	/**
	 * Constructor for Tipoacreencia.
	 * 
	 * @param id
	 *            Long
	 */
	public Tipoacreencia(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Tipoacreencia.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param requiereclave
	 *            String
	 */
	public Tipoacreencia(Long id, String descripcion, String requiereclave) {
		this.id = id;
		this.descripcion = descripcion;
		this.requiereclave = requiereclave;
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
	 * Method getRequiereclave.
	 * 
	 * @return String
	 */
	public String getRequiereclave() {
		return requiereclave;
	}

	/**
	 * Method setRequiereclave.
	 * 
	 * @param requiereclave
	 *            String
	 */
	public void setRequiereclave(String requiereclave) {
		this.requiereclave = requiereclave;
	}

	/**
	 * Method getTipoacreenciaoperacionList.
	 * 
	 * @return List<Tipoacreenciaoperacion>
	 */
	@XmlTransient
	public List<Tipoacreenciaoperacion> getTipoacreenciaoperacionList() {
		return tipoacreenciaoperacionList;
	}

	/**
	 * Method setTipoacreenciaoperacionList.
	 * 
	 * @param tipoacreenciaoperacionList
	 *            List<Tipoacreenciaoperacion>
	 */
	public void setTipoacreenciaoperacionList(List<Tipoacreenciaoperacion> tipoacreenciaoperacionList) {
		this.tipoacreenciaoperacionList = tipoacreenciaoperacionList;
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
		if (!(object instanceof Tipoacreencia)) {
			return false;
		}
		Tipoacreencia other = (Tipoacreencia) object;
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

		sb.append(requiereclave);

		sb.append(endStr);

		return sb.toString();
	}

}
