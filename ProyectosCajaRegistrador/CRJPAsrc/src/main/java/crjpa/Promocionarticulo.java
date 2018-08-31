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
@Table(name = "promocionarticulo")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Promocionarticulo.findAll", query = "SELECT p FROM Promocionarticulo p"),
		@NamedQuery(name = "Promocionarticulo.findById", query = "SELECT p FROM Promocionarticulo p WHERE p.id = :id"),
		@NamedQuery(name = "Promocionarticulo.findByMontoDescuento", query = "SELECT p FROM Promocionarticulo p WHERE p.montoDescuento = :montoDescuento"),
		@NamedQuery(name = "Promocionarticulo.findByCantidadRegalo", query = "SELECT p FROM Promocionarticulo p WHERE p.cantidadRegalo = :cantidadRegalo"),
		@NamedQuery(name = "Promocionarticulo.findByEstaactivo", query = "SELECT p FROM Promocionarticulo p WHERE p.estaactivo = :estaactivo") })
public class Promocionarticulo implements Serializable {
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
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field montoDescuento.
	 */
	@Basic(optional = false)
	@Column(name = "monto_descuento")
	private BigDecimal montoDescuento;
	/**
	 * Field cantidadRegalo.
	 */
	@Basic(optional = false)
	@Column(name = "cantidad_regalo")
	private long cantidadRegalo;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	/**
	 * Field idArticulo.
	 */
	@JoinColumn(name = "id_articulo", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Articulo idArticulo;
	/**
	 * Field idPromocion.
	 */
	@JoinColumn(name = "id_promocion", referencedColumnName = "id")
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
	 *            String
	 */
	public Promocionarticulo(Long id, BigDecimal montoDescuento, long cantidadRegalo, String estaactivo) {
		this.id = id;
		this.montoDescuento = montoDescuento;
		this.cantidadRegalo = cantidadRegalo;
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

		sb.append(idArticulo.getId());
		sb.append(separator);

		sb.append(idPromocion.getId());
		sb.append(separator);

		sb.append(montoDescuento);
		sb.append(separator);

		sb.append(cantidadRegalo);
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(endStr);
		return sb.toString();
	}

}
