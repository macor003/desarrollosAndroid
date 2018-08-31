/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import crjpa400.interfaces.CrjpaInterface;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "FORMADEPAGOMONEDA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Formadepagomoneda.findAll", query = "SELECT f FROM Formadepagomoneda f"),
		@NamedQuery(name = "Formadepagomoneda.findById", query = "SELECT f FROM Formadepagomoneda f WHERE f.id = :id"),
		@NamedQuery(name = "Formadepagomoneda.findByPrioridadFormaDePago", query = "SELECT f FROM Formadepagomoneda f WHERE f.prioridadFormaDePago = :prioridadFormaDePago"),
		@NamedQuery(name = "Formadepagomoneda.findByEstaactivo", query = "SELECT f FROM Formadepagomoneda f WHERE f.estaactivo = :estaactivo"),
		@NamedQuery(name = "Formadepagomoneda.findByUltimasincronizacion", query = "SELECT f FROM Formadepagomoneda f WHERE f.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Formadepagomoneda.findByEstreplica", query = "SELECT f FROM Formadepagomoneda f WHERE f.estreplica = :estreplica") })
public class Formadepagomoneda extends CrjpaInterface implements Serializable {
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
	 * Field prioridadFormaDePago.
	 */
	@Basic(optional = false)
	@Column(name = "PRIORIDAD_FORMA_DE_PAGO")
	private int prioridadFormaDePago;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO")
	private char estaactivo;
	/**
	 * Field ultimasincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ULTIMASINCRONIZACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idMoneda.
	 */
	@JoinColumn(name = "ID_MONEDA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Moneda idMoneda;
	/**
	 * Field idFormadepago.
	 */
	@JoinColumn(name = "ID_FORMADEPAGO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Formadepago idFormadepago;

	/**
	 * Constructor for Formadepagomoneda.
	 */
	public Formadepagomoneda() {
	}

	/**
	 * Constructor for Formadepagomoneda.
	 * 
	 * @param id
	 *            Long
	 */
	public Formadepagomoneda(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Formadepagomoneda.
	 * 
	 * @param id
	 *            Long
	 * @param prioridadFormaDePago
	 *            int
	 * @param estaactivo
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Formadepagomoneda(Long id, int prioridadFormaDePago,
			char estaactivo, Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.prioridadFormaDePago = prioridadFormaDePago;
		this.estaactivo = estaactivo;
		this.ultimasincronizacion = ultimasincronizacion;
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
	 * Method getPrioridadFormaDePago.
	 * 
	 * @return int
	 */
	public int getPrioridadFormaDePago() {
		return prioridadFormaDePago;
	}

	/**
	 * Method setPrioridadFormaDePago.
	 * 
	 * @param prioridadFormaDePago
	 *            int
	 */
	public void setPrioridadFormaDePago(int prioridadFormaDePago) {
		this.prioridadFormaDePago = prioridadFormaDePago;
	}

	/**
	 * Method getEstaactivo.
	 * 
	 * @return char
	 */
	public char getEstaactivo() {
		return estaactivo;
	}

	/**
	 * Method setEstaactivo.
	 * 
	 * @param estaactivo
	 *            char
	 */
	public void setEstaactivo(char estaactivo) {
		this.estaactivo = estaactivo;
	}

	/**
	 * Method getUltimasincronizacion.
	 * 
	 * @return Calendar
	 */
	@Override
	public Calendar getUltimasincronizacion() {
		return ultimasincronizacion;
	}

	/**
	 * Method setUltimasincronizacion.
	 * 
	 * @param ultimasincronizacion
	 *            Calendar
	 */
	public void setUltimasincronizacion(Calendar ultimasincronizacion) {
		this.ultimasincronizacion = ultimasincronizacion;
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
	 * Method getIdMoneda.
	 * 
	 * @return Moneda
	 */
	public Moneda getIdMoneda() {
		return idMoneda;
	}

	/**
	 * Method setIdMoneda.
	 * 
	 * @param idMoneda
	 *            Moneda
	 */
	public void setIdMoneda(Moneda idMoneda) {
		this.idMoneda = idMoneda;
	}

	/**
	 * Method getIdFormadepago.
	 * 
	 * @return Formadepago
	 */
	public Formadepago getIdFormadepago() {
		return idFormadepago;
	}

	/**
	 * Method setIdFormadepago.
	 * 
	 * @param idFormadepago
	 *            Formadepago
	 */
	public void setIdFormadepago(Formadepago idFormadepago) {
		this.idFormadepago = idFormadepago;
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
		if (!(object instanceof Formadepagomoneda)) {
			return false;
		}
		Formadepagomoneda other = (Formadepagomoneda) object;
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
		return "crjpa400.Formadepagomoneda[ id=" + id + " ]";
	}

}
