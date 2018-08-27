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
@Table(name = "PROMOCIONARTICULO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Promocionarticulo.findAll", query = "SELECT p FROM Promocionarticulo p"),
		@NamedQuery(name = "Promocionarticulo.findById", query = "SELECT p FROM Promocionarticulo p WHERE p.id = :id"),
		@NamedQuery(name = "Promocionarticulo.findByMontoDescuento", query = "SELECT p FROM Promocionarticulo p WHERE p.montoDescuento = :montoDescuento"),
		@NamedQuery(name = "Promocionarticulo.findByCantidadRegalo", query = "SELECT p FROM Promocionarticulo p WHERE p.cantidadRegalo = :cantidadRegalo"),
		@NamedQuery(name = "Promocionarticulo.findByEstaactivo", query = "SELECT p FROM Promocionarticulo p WHERE p.estaactivo = :estaactivo"),
		@NamedQuery(name = "Promocionarticulo.findByUltimasincronizacion", query = "SELECT p FROM Promocionarticulo p WHERE p.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Promocionarticulo.findByEstreplica", query = "SELECT p FROM Promocionarticulo p WHERE p.estreplica = :estreplica") })
public class Promocionarticulo extends CrjpaInterface implements Serializable {
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
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field montoDescuento.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_DESCUENTO", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoDescuento;
	/**
	 * Field cantidadRegalo.
	 */
	@Basic(optional = false)
	@Column(name = "CANTIDAD_REGALO", nullable = false)
	private long cantidadRegalo;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;
	/**
	 * Field ultimasincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ULTIMASINCRONIZACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idArticulo.
	 */
	@JoinColumn(name = "ID_ARTICULO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Articulo idArticulo;
	/**
	 * Field idPromocion.
	 */
	@JoinColumn(name = "ID_PROMOCION", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Promocion idPromocion;

	/**
	 * Constructor for Promocionarticulo.
	 */
	public Promocionarticulo() {
	}

	/**
	 * Constructor for Promocionarticulo.
	 * 
	 * @param id
	 *            Long
	 */
	public Promocionarticulo(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Promocionarticulo.
	 * 
	 * @param id
	 *            Long
	 * @param montoDescuento
	 *            BigDecimal
	 * @param cantidadRegalo
	 *            long
	 * @param estaactivo
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Promocionarticulo(Long id, BigDecimal montoDescuento,
			long cantidadRegalo, char estaactivo,
			Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.montoDescuento = montoDescuento;
		this.cantidadRegalo = cantidadRegalo;
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
	 * Method getMontoDescuento.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoDescuento() {
		return montoDescuento;
	}

	/**
	 * Method setMontoDescuento.
	 * 
	 * @param montoDescuento
	 *            BigDecimal
	 */
	public void setMontoDescuento(BigDecimal montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	/**
	 * Method getCantidadRegalo.
	 * 
	 * @return long
	 */
	public long getCantidadRegalo() {
		return cantidadRegalo;
	}

	/**
	 * Method setCantidadRegalo.
	 * 
	 * @param cantidadRegalo
	 *            long
	 */
	public void setCantidadRegalo(long cantidadRegalo) {
		this.cantidadRegalo = cantidadRegalo;
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
	 * Method getIdArticulo.
	 * 
	 * @return Articulo
	 */
	public Articulo getIdArticulo() {
		return idArticulo;
	}

	/**
	 * Method setIdArticulo.
	 * 
	 * @param idArticulo
	 *            Articulo
	 */
	public void setIdArticulo(Articulo idArticulo) {
		this.idArticulo = idArticulo;
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
		if (!(object instanceof Promocionarticulo)) {
			return false;
		}
		Promocionarticulo other = (Promocionarticulo) object;
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
		return "crjpa400.Promocionarticulo[ id=" + id + " ]";
	}

}
