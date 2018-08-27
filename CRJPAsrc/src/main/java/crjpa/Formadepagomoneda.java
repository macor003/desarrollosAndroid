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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "formadepagomoneda")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Formadepagomoneda.findAll", query = "SELECT f FROM Formadepagomoneda f"),
		@NamedQuery(name = "Formadepagomoneda.findById", query = "SELECT f FROM Formadepagomoneda f WHERE f.id = :id"),
		@NamedQuery(name = "Formadepagomoneda.findByPrioridadFormaDePago", query = "SELECT f FROM Formadepagomoneda f WHERE f.prioridadFormaDePago = :prioridadFormaDePago"),
		@NamedQuery(name = "Formadepagomoneda.findByEstaactivo", query = "SELECT f FROM Formadepagomoneda f WHERE f.estaactivo = :estaactivo") })
public class Formadepagomoneda implements Serializable {
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
	 * Field prioridadFormaDePago.
	 */
	@Basic(optional = false)
	@Column(name = "prioridad_forma_de_pago")
	private int prioridadFormaDePago;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	/**
	 * Field idMoneda.
	 */
	@JoinColumn(name = "id_moneda", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Moneda idMoneda;
	/**
	 * Field idFormadepago.
	 */
	@JoinColumn(name = "id_formadepago", referencedColumnName = "id")
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
	 *            String
	 */
	public Formadepagomoneda(Long id, int prioridadFormaDePago, String estaactivo) {
		this.id = id;
		this.prioridadFormaDePago = prioridadFormaDePago;
		this.estaactivo = estaactivo;
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
	 * @return String
	 */
	public String getEstaactivo() {
		return estaactivo;
	}

	/**
	 * Method setEstaactivo.
	 * 
	 * @param estaactivo
	 *            String
	 */
	public void setEstaactivo(String estaactivo) {
		this.estaactivo = estaactivo;
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
		String endStr = "\r\n";

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(idFormadepago.getId());
		sb.append(separator);

		sb.append(idMoneda.getId());
		sb.append(separator);

		sb.append(prioridadFormaDePago);
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(endStr);

		return sb.toString();
	}

}
