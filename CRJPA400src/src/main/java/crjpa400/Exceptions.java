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
@Table(name = "EXCEPTIONS")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Exceptions.findAll", query = "SELECT e FROM Exceptions e"),
		@NamedQuery(name = "Exceptions.findById", query = "SELECT e FROM Exceptions e WHERE e.id = :id"),
		@NamedQuery(name = "Exceptions.findByNameException", query = "SELECT e FROM Exceptions e WHERE e.nameException = :nameException"),
		@NamedQuery(name = "Exceptions.findByValueException", query = "SELECT e FROM Exceptions e WHERE e.valueException = :valueException"),
		@NamedQuery(name = "Exceptions.findByEstreplica", query = "SELECT e FROM Exceptions e WHERE e.estreplica = :estreplica"),
		@NamedQuery(name = "Exceptions.findByFecha", query = "SELECT e FROM Exceptions e WHERE e.fecha = :fecha") })
public class Exceptions implements Serializable {
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
	 * Field nameException.
	 */
	@Basic(optional = false)
	@Column(name = "NAME_EXCEPTION", nullable = false, length = 30)
	private String nameException;
	/**
	 * Field valueException.
	 */
	@Basic(optional = false)
	@Column(name = "VALUE_EXCEPTION")
	private String valueException;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field transaccionexceptionsList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idException")
	private List<Transaccionexceptions> transaccionexceptionsList;

	/**
	 * Constructor for Exceptions.
	 */
	public Exceptions() {
	}

	/**
	 * Constructor for Exceptions.
	 * 
	 * @param id
	 *            Long
	 */
	public Exceptions(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Exceptions.
	 * 
	 * @param id
	 *            Long
	 * @param nameException
	 *            String
	 * @param valueException
	 *            String
	 * @param estreplica
	 *            char
	 * @param fecha
	 *            Date
	 */
	public Exceptions(Long id, String nameException, String valueException,
			char estreplica, Date fecha) {
		this.id = id;
		this.nameException = nameException;
		this.valueException = valueException;
		this.estreplica = estreplica;
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
	 * Method getNameException.
	 * 
	 * @return String
	 */
	public String getNameException() {
		return nameException;
	}

	/**
	 * Method setNameException.
	 * 
	 * @param nameException
	 *            String
	 */
	public void setNameException(String nameException) {
		this.nameException = nameException;
	}

	/**
	 * Method getValueException.
	 * 
	 * @return String
	 */
	public String getValueException() {
		return valueException;
	}

	/**
	 * Method setValueException.
	 * 
	 * @param valueException
	 *            String
	 */
	public void setValueException(String valueException) {
		this.valueException = valueException;
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
	 * Method getTransaccionexceptionsList.
	 * 
	 * @return List<Transaccionexceptions>
	 */
	@XmlTransient
	public List<Transaccionexceptions> getTransaccionexceptionsList() {
		return transaccionexceptionsList;
	}

	/**
	 * Method setTransaccionexceptionsList.
	 * 
	 * @param transaccionexceptionsList
	 *            List<Transaccionexceptions>
	 */
	public void setTransaccionexceptionsList(
			List<Transaccionexceptions> transaccionexceptionsList) {
		this.transaccionexceptionsList = transaccionexceptionsList;
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
		if (!(object instanceof Exceptions)) {
			return false;
		}
		Exceptions other = (Exceptions) object;
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
		return "crjpa400.Exceptions[ id=" + id + " ]";
	}

}
