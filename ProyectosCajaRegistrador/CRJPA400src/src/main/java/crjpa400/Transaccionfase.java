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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TRANSACCIONFASE")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Transaccionfase.findAll", query = "SELECT t FROM Transaccionfase t"),
		@NamedQuery(name = "Transaccionfase.findById", query = "SELECT t FROM Transaccionfase t WHERE t.id = :id"),
		@NamedQuery(name = "Transaccionfase.findByFase", query = "SELECT t FROM Transaccionfase t WHERE t.fase = :fase"),
		@NamedQuery(name = "Transaccionfase.findByDuracion", query = "SELECT t FROM Transaccionfase t WHERE t.duracion = :duracion"),
		@NamedQuery(name = "Transaccionfase.findByEstreplica", query = "SELECT t FROM Transaccionfase t WHERE t.estreplica = :estreplica") })
public class Transaccionfase implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;
	/**
	 * Field fase.
	 */
	@Basic(optional = false)
	@Column(name = "FASE", nullable = false, length = 5)
	private String fase;
	/**
	 * Field duracion.
	 */
	@Basic(optional = false)
	@Column(name = "DURACION", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date duracion;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idTransaccion.
	 */
	@JoinColumn(name = "ID_TRANSACCION", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Transaccion idTransaccion;

	/**
	 * Constructor for Transaccionfase.
	 */
	public Transaccionfase() {
	}

	/**
	 * Constructor for Transaccionfase.
	 * 
	 * @param id
	 *            Long
	 */
	public Transaccionfase(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Transaccionfase.
	 * 
	 * @param id
	 *            Long
	 * @param fase
	 *            String
	 * @param duracion
	 *            Date
	 * @param estreplica
	 *            char
	 */
	public Transaccionfase(Long id, String fase, Date duracion, char estreplica) {
		this.id = id;
		this.fase = fase;
		this.duracion = duracion;
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
	 * Method getFase.
	 * 
	 * @return String
	 */
	public String getFase() {
		return fase;
	}

	/**
	 * Method setFase.
	 * 
	 * @param fase
	 *            String
	 */
	public void setFase(String fase) {
		this.fase = fase;
	}

	/**
	 * Method getDuracion.
	 * 
	 * @return Date
	 */
	public Date getDuracion() {
		return duracion;
	}

	/**
	 * Method setDuracion.
	 * 
	 * @param duracion
	 *            Date
	 */
	public void setDuracion(Date duracion) {
		this.duracion = duracion;
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
	 * Method getIdTransaccion.
	 * 
	 * @return Transaccion
	 */
	public Transaccion getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Method setIdTransaccion.
	 * 
	 * @param idTransaccion
	 *            Transaccion
	 */
	public void setIdTransaccion(Transaccion idTransaccion) {
		this.idTransaccion = idTransaccion;
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
		if (!(object instanceof Transaccionfase)) {
			return false;
		}
		Transaccionfase other = (Transaccionfase) object;
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
		return "crjpa400.Transaccionfase[ id=" + id + " ]";
	}

}
