/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
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
@Table(name = "AUDITORIASP")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Auditoriasp.findAll", query = "SELECT a FROM Auditoriasp a"),
		@NamedQuery(name = "Auditoriasp.findById", query = "SELECT a FROM Auditoriasp a WHERE a.id = :id"),
		@NamedQuery(name = "Auditoriasp.findByValor", query = "SELECT a FROM Auditoriasp a WHERE a.valor = :valor"),
		@NamedQuery(name = "Auditoriasp.findByFecha", query = "SELECT a FROM Auditoriasp a WHERE a.fecha = :fecha"),
		@NamedQuery(name = "Auditoriasp.findByEstreplica", query = "SELECT a FROM Auditoriasp a WHERE a.estreplica = :estreplica") })
public class Auditoriasp implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	private Long id;
	/**
	 * Field valor.
	 */
	@Basic(optional = false)
	@Column(name = "VALOR")
	private String valor;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;

	/**
	 * Constructor for Auditoriasp.
	 */
	public Auditoriasp() {
	}

	/**
	 * Constructor for Auditoriasp.
	 * 
	 * @param id
	 *            Long
	 */
	public Auditoriasp(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Auditoriasp.
	 * 
	 * @param id
	 *            Long
	 * @param valor
	 *            String
	 * @param fecha
	 *            Date
	 * @param estreplica
	 *            char
	 */
	public Auditoriasp(Long id, String valor, Date fecha, char estreplica) {
		this.id = id;
		this.valor = valor;
		this.fecha = fecha;
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
	 * Method getValor.
	 * 
	 * @return String
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Method setValor.
	 * 
	 * @param valor
	 *            String
	 */
	public void setValor(String valor) {
		this.valor = valor;
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
		if (!(object instanceof Auditoriasp)) {
			return false;
		}
		Auditoriasp other = (Auditoriasp) object;
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
		return "crjpa400.Auditoriasp[ id=" + id + " ]";
	}

}
