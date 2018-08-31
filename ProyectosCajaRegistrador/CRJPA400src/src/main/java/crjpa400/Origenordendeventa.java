/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "ORIGENORDENDEVENTA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Origenordendeventa.findAll", query = "SELECT o FROM Origenordendeventa o"),
		@NamedQuery(name = "Origenordendeventa.findById", query = "SELECT o FROM Origenordendeventa o WHERE o.id = :id"),
		@NamedQuery(name = "Origenordendeventa.findByDescripcion", query = "SELECT o FROM Origenordendeventa o WHERE o.descripcion = :descripcion")})
		
public class Origenordendeventa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9181431550580530927L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;
	/**
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "DESCRIPCION", nullable = false, length = 30)
	private String descripcion;
	
	/**
	 * Constructor for Tipoordendeventa.
	 */
	public Origenordendeventa() {
	}

	/**
	 * Constructor for Tipoordendeventa.
	 * 
	 * @param id
	 *            Long
	 */
	public Origenordendeventa(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Tipoordendeventa.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param estreplica
	 *            char
	 */
	public Origenordendeventa(Long id, String descripcion) {
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
		if (!(object instanceof Origenordendeventa)) {
			return false;
		}
		Origenordendeventa other = (Origenordendeventa) object;
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
		return "crjpa400.Origenordendeventa[ id=" + id + " ]";
	}

}
