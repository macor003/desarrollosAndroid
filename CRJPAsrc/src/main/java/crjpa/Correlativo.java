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
@Table(name = "correlativo")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Correlativo.findAll", query = "SELECT c FROM Correlativo c"),
		@NamedQuery(name = "Correlativo.findById", query = "SELECT c FROM Correlativo c WHERE c.id = :id"),
		@NamedQuery(name = "Correlativo.findByDescripcion", query = "SELECT c FROM Correlativo c WHERE c.descripcion = :descripcion"),
		@NamedQuery(name = "Correlativo.findByValor", query = "SELECT c FROM Correlativo c WHERE c.valor = :valor"),
		@NamedQuery(name = "Correlativo.findByEstasincronizado", query = "SELECT c FROM Correlativo c WHERE c.estasincronizado = :estasincronizado") })
public class Correlativo implements Serializable {
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
	 * Field valor.
	 */
	@Basic(optional = false)
	@Column(name = "valor")
	private long valor;
	/**
	 * Field estasincronizado.
	 */
	@Column(name = "estasincronizado")
	private String estasincronizado;

	/**
	 * Constructor for Correlativo.
	 */
	public Correlativo() {
	}

	/**
	 * Constructor for Correlativo.
	 * 
	 * @param id
	 *            Long
	 */
	public Correlativo(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Correlativo.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param valor
	 *            long
	 */
	public Correlativo(Long id, String descripcion, long valor) {
		this.id = id;
		this.descripcion = descripcion;
		this.valor = valor;
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
	 * Method getValor.
	 * 
	 * @return long
	 */
	public long getValor() {
		return valor;
	}

	/**
	 * Method setValor.
	 * 
	 * @param valor
	 *            long
	 */
	public void setValor(long valor) {
		this.valor = valor;
	}

	/**
	 * Method getEstasincronizado.
	 * 
	 * @return String
	 */
	public String getEstasincronizado() {
		return estasincronizado;
	}

	/**
	 * Method setEstasincronizado.
	 * 
	 * @param estasincronizado
	 *            String
	 */
	public void setEstasincronizado(String estasincronizado) {
		this.estasincronizado = estasincronizado;
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
		if (!(object instanceof Correlativo)) {
			return false;
		}
		Correlativo other = (Correlativo) object;
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
		return "crjpa.Correlativo[ id=" + id + " ]";
	}

}
