/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

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
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "transaccionfase")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Transaccionfase.findAll", query = "SELECT t FROM Transaccionfase t"),
		@NamedQuery(name = "Transaccionfase.findById", query = "SELECT t FROM Transaccionfase t WHERE t.id = :id"),
		@NamedQuery(name = "Transaccionfase.findByFase", query = "SELECT t FROM Transaccionfase t WHERE t.fase = :fase"),
		@NamedQuery(name = "Transaccionfase.findByDuracion", query = "SELECT t FROM Transaccionfase t WHERE t.duracion = :duracion"),
		@NamedQuery(name = "Transaccionfase.findByEstasincronizado", query = "SELECT t FROM Transaccionfase t WHERE t.estasincronizado = :estasincronizado") })
public class Transaccionfase implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "8")
	@TableGenerator(name = "8", table = "correlativo", pkColumnName = "id", valueColumnName = "valor", pkColumnValue = "8", allocationSize = 1)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
	/**
	 * Field fase.
	 */
	@Basic(optional = false)
	@Column(name = "fase", nullable = false, length = 5)
	private String fase;
	/**
	 * Field duracion.
	 */
	@Basic(optional = false)
	@Column(name = "duracion", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date duracion;
	/**
	 * Field estasincronizado.
	 */
	@Basic(optional = false)
	@Column(name = "estasincronizado", nullable = false, length = 2)
	private String estasincronizado;
	/**
	 * Field idTransaccion.
	 */
	@JoinColumn(name = "id_transaccion", referencedColumnName = "id", nullable = false)
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
	 * @param estasincronizado
	 *            String
	 */
	public Transaccionfase(Long id, String fase, Date duracion, String estasincronizado) {
		this.id = id;
		this.fase = fase;
		this.duracion = duracion;
		this.estasincronizado = estasincronizado;
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
		return "crjpa.Transaccionfase[ id=" + id + " ]";
	}

}
