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
@Table(name = "CONVERTIDORTIPODESCTO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Convertidortipodescto.findAll", query = "SELECT c FROM Convertidortipodescto c"),
		@NamedQuery(name = "Convertidortipodescto.findById", query = "SELECT c FROM Convertidortipodescto c WHERE c.id = :id"),
		@NamedQuery(name = "Convertidortipodescto.findByIdTrdmov", query = "SELECT c FROM Convertidortipodescto c WHERE c.idTrdmov = :idTrdmov"),
		@NamedQuery(name = "Convertidortipodescto.findByEstreplica", query = "SELECT c FROM Convertidortipodescto c WHERE c.estreplica = :estreplica") })
public class Convertidortipodescto implements Serializable {
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
	 * Field idTrdmov.
	 */
	@Basic(optional = false)
	@Column(name = "ID_TRDMOV", nullable = false)
	private short idTrdmov;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idTipodescuento.
	 */
	@JoinColumn(name = "ID_TIPODESCUENTO", referencedColumnName = "ID")
	@ManyToOne
	private Tipodescuento idTipodescuento;

	/**
	 * Constructor for Convertidortipodescto.
	 */
	public Convertidortipodescto() {
	}

	/**
	 * Constructor for Convertidortipodescto.
	 * 
	 * @param id
	 *            Long
	 */
	public Convertidortipodescto(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Convertidortipodescto.
	 * 
	 * @param id
	 *            Long
	 * @param idTrdmov
	 *            short
	 * @param estreplica
	 *            char
	 */
	public Convertidortipodescto(Long id, short idTrdmov, char estreplica) {
		this.id = id;
		this.idTrdmov = idTrdmov;
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
	 * Method getIdTrdmov.
	 * 
	 * @return short
	 */
	public short getIdTrdmov() {
		return idTrdmov;
	}

	/**
	 * Method setIdTrdmov.
	 * 
	 * @param idTrdmov
	 *            short
	 */
	public void setIdTrdmov(short idTrdmov) {
		this.idTrdmov = idTrdmov;
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
		if (!(object instanceof Convertidortipodescto)) {
			return false;
		}
		Convertidortipodescto other = (Convertidortipodescto) object;
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
		return "crjpa400.Convertidortipodescto[ id=" + id + " ]";
	}

}
