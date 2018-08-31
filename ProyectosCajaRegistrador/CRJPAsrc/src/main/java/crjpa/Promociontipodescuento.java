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
@Table(name = "promociontipodescuento")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Promociontipodescuento.findAll", query = "SELECT p FROM Promociontipodescuento p"),
		@NamedQuery(name = "Promociontipodescuento.findById", query = "SELECT p FROM Promociontipodescuento p WHERE p.id = :id") })
public class Promociontipodescuento implements Serializable {
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
	 * Field idTipodescuento.
	 */
	@JoinColumn(name = "id_tipodescuento", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Tipodescuento idTipodescuento;
	/**
	 * Field idPromocion.
	 */
	@JoinColumn(name = "id_promocion", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Promocion idPromocion;

	/**
	 * Constructor for Promociontipodescuento.
	 */
	public Promociontipodescuento() {
	}

	/**
	 * Constructor for Promociontipodescuento.
	 * 
	 * @param id
	 *            Long
	 */
	public Promociontipodescuento(Long id) {
		this.id = id;
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
	 * Method getIdTipodescuento.
	 * 
	 * @return Tipodescuento
	 */
	public Tipodescuento getIdTipodescuento() {
		return idTipodescuento;
	}

	/**
	 * Method setIdTipodescuento.
	 * 
	 * @param idTipodescuento
	 *            Tipodescuento
	 */
	public void setIdTipodescuento(Tipodescuento idTipodescuento) {
		this.idTipodescuento = idTipodescuento;
	}

	/**
	 * Method getIdPromocion.
	 * 
	 * @return Promocion
	 */
	public Promocion getIdPromocion() {
		return idPromocion;
	}

	/**
	 * Method setIdPromocion.
	 * 
	 * @param idPromocion
	 *            Promocion
	 */
	public void setIdPromocion(Promocion idPromocion) {
		this.idPromocion = idPromocion;
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
		if (!(object instanceof Promociontipodescuento)) {
			return false;
		}
		Promociontipodescuento other = (Promociontipodescuento) object;
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

		sb.append(idTipodescuento.getId());
		sb.append(separator);

		sb.append(idPromocion.getId());
		sb.append(endStr);
		return sb.toString();
	}

}
