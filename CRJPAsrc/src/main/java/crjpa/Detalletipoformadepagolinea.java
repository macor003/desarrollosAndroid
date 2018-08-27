/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "detalletipoformadepagolinea")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Detalletipoformadepagolinea.findAll", query = "SELECT d FROM Detalletipoformadepagolinea d"),
		@NamedQuery(name = "Detalletipoformadepagolinea.findById", query = "SELECT d FROM Detalletipoformadepagolinea d WHERE d.id = :id"),
		@NamedQuery(name = "Detalletipoformadepagolinea.findByDescripcion", query = "SELECT d FROM Detalletipoformadepagolinea d WHERE d.descripcion = :descripcion"),
		@NamedQuery(name = "Detalletipoformadepagolinea.findByMonto", query = "SELECT d FROM Detalletipoformadepagolinea d WHERE d.monto = :monto"),
		@NamedQuery(name = "Detalletipoformadepagolinea.findByEstasincronizado", query = "SELECT d FROM Detalletipoformadepagolinea d WHERE d.estasincronizado = :estasincronizado") })
public class Detalletipoformadepagolinea implements Serializable {
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
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "descripcion")
	private String descripcion;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field monto.
	 */
	@Basic(optional = false)
	@Column(name = "monto")
	private BigDecimal monto;
	/**
	 * Field estasincronizado.
	 */
	@Basic(optional = false)
	@Column(name = "estasincronizado")
	private String estasincronizado;
	/**
	 * Field idDetalletipoformadepago.
	 */
	@JoinColumn(name = "id_detalletipoformadepago", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Detalletipoformadepago idDetalletipoformadepago;

	/**
	 * Constructor for Detalletipoformadepagolinea.
	 */
	public Detalletipoformadepagolinea() {
	}

	/**
	 * Constructor for Detalletipoformadepagolinea.
	 * 
	 * @param id
	 *            Long
	 */
	public Detalletipoformadepagolinea(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Detalletipoformadepagolinea.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param monto
	 *            BigDecimal
	 * @param estasincronizado
	 *            String
	 */
	public Detalletipoformadepagolinea(Long id, String descripcion, BigDecimal monto, String estasincronizado) {
		this.id = id;
		this.descripcion = descripcion;
		this.monto = monto;
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
	 * Method getMonto.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMonto() {
		return monto;
	}

	/**
	 * Method setMonto.
	 * 
	 * @param monto
	 *            BigDecimal
	 */
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
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
	 * Method getIdDetalletipoformadepago.
	 * 
	 * @return Detalletipoformadepago
	 */
	public Detalletipoformadepago getIdDetalletipoformadepago() {
		return idDetalletipoformadepago;
	}

	/**
	 * Method setIdDetalletipoformadepago.
	 * 
	 * @param idDetalletipoformadepago
	 *            Detalletipoformadepago
	 */
	public void setIdDetalletipoformadepago(Detalletipoformadepago idDetalletipoformadepago) {
		this.idDetalletipoformadepago = idDetalletipoformadepago;
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
		if (!(object instanceof Detalletipoformadepagolinea)) {
			return false;
		}
		Detalletipoformadepagolinea other = (Detalletipoformadepagolinea) object;
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
		return "crjpa.Detalletipoformadepagolinea[ id=" + id + " ]";
	}

}
