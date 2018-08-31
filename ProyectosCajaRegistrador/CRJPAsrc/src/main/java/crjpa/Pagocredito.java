/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "pagocredito")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Pagocredito.findAll", query = "SELECT p FROM Pagocredito p"),
		@NamedQuery(name = "Pagocredito.findById", query = "SELECT p FROM Pagocredito p WHERE p.id = :id"),
		@NamedQuery(name = "Pagocredito.findByEstasincronizado", query = "SELECT p FROM Pagocredito p WHERE p.estasincronizado = :estasincronizado") })
public class Pagocredito implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "4")
	@TableGenerator(name = "4", table = "correlativo", pkColumnName = "id", valueColumnName = "valor", pkColumnValue = "4", allocationSize = 1)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
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
	 * Field idCuentacredito.
	 */
	@JoinColumn(name = "id_cuentacredito", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Cuentacredito idCuentacredito;

	/**
	 * Constructor for Pagocredito.
	 */
	public Pagocredito() {
	}

	/**
	 * Constructor for Pagocredito.
	 * 
	 * @param id
	 *            Long
	 */
	public Pagocredito(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Pagocredito.
	 * 
	 * @param id
	 *            Long
	 * @param estasincronizado
	 *            String
	 */
	public Pagocredito(Long id, String estasincronizado) {
		this.id = id;
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
	 * Method getIdCuentacredito.
	 * 
	 * @return Cuentacredito
	 */
	public Cuentacredito getIdCuentacredito() {
		return idCuentacredito;
	}

	/**
	 * Method setIdCuentacredito.
	 * 
	 * @param idCuentacredito
	 *            Cuentacredito
	 */
	public void setIdCuentacredito(Cuentacredito idCuentacredito) {
		this.idCuentacredito = idCuentacredito;
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
		if (!(object instanceof Pagocredito)) {
			return false;
		}
		Pagocredito other = (Pagocredito) object;
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
		return "crjpa.Pagocredito[ id=" + id + " ]";
	}

}
