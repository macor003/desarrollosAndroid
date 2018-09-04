/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

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
@Table(name = "DETALLETIPOFORMADEPAGOLINEA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Detalletipoformadepagolinea.findAll", query = "SELECT d FROM Detalletipoformadepagolinea d"),
		@NamedQuery(name = "Detalletipoformadepagolinea.findById", query = "SELECT d FROM Detalletipoformadepagolinea d WHERE d.id = :id"),
		@NamedQuery(name = "Detalletipoformadepagolinea.findByDescripcion", query = "SELECT d FROM Detalletipoformadepagolinea d WHERE d.descripcion = :descripcion"),
		@NamedQuery(name = "Detalletipoformadepagolinea.findByMonto", query = "SELECT d FROM Detalletipoformadepagolinea d WHERE d.monto = :monto"),
		@NamedQuery(name = "Detalletipoformadepagolinea.findByEstreplica", query = "SELECT d FROM Detalletipoformadepagolinea d WHERE d.estreplica = :estreplica") })
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
	@Column(name = "ID", nullable = false)
	private Long id;
	/**
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "DESCRIPCION", nullable = false, length = 255)
	private String descripcion;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field monto.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO", nullable = false, precision = 13, scale = 2)
	private BigDecimal monto;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idDetalletipoformadepago.
	 */
	@JoinColumn(name = "ID_DETALLETIPOFORMADEPAGO", referencedColumnName = "ID", nullable = false)
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
	 * @param estreplica
	 *            char
	 */
	public Detalletipoformadepagolinea(Long id, String descripcion,
			BigDecimal monto, char estreplica) {
		this.id = id;
		this.descripcion = descripcion;
		this.monto = monto;
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
	public void setIdDetalletipoformadepago(
			Detalletipoformadepago idDetalletipoformadepago) {
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
		return "crjpa400.Detalletipoformadepagolinea[ id=" + id + " ]";
	}

}
