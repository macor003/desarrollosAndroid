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
@Table(name = "promocioncategoria")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Promocioncategoria.findAll", query = "SELECT p FROM Promocioncategoria p"),
		@NamedQuery(name = "Promocioncategoria.findById", query = "SELECT p FROM Promocioncategoria p WHERE p.id = :id"),
		@NamedQuery(name = "Promocioncategoria.findByPorcentajeDescuento", query = "SELECT p FROM Promocioncategoria p WHERE p.porcentajeDescuento = :porcentajeDescuento"),
		@NamedQuery(name = "Promocioncategoria.findByEstaactivo", query = "SELECT p FROM Promocioncategoria p WHERE p.estaactivo = :estaactivo") })
public class Promocioncategoria implements Serializable {
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
	 * Field porcentajeDescuento.
	 */
	@Basic(optional = false)
	@Column(name = "porcentaje_descuento")
	private BigDecimal porcentajeDescuento;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	/**
	 * Field idPromocion.
	 */
	@JoinColumn(name = "id_promocion", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Promocion idPromocion;
	/**
	 * Field idCategoria.
	 */
	@JoinColumn(name = "id_categoria", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Categoria idCategoria;

	/**
	 * Constructor for Promocioncategoria.
	 */
	public Promocioncategoria() {
	}

	/**
	 * Constructor for Promocioncategoria.
	 * 
	 * @param id
	 *            Long
	 */
	public Promocioncategoria(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Promocioncategoria.
	 * 
	 * @param id
	 *            Long
	 * @param porcentajeDescuento
	 *            BigDecimal
	 * @param estaactivo
	 *            String
	 */
	public Promocioncategoria(Long id, BigDecimal porcentajeDescuento, String estaactivo) {
		this.id = id;
		this.porcentajeDescuento = porcentajeDescuento;
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
	 * Method getPorcentajeDescuento.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	/**
	 * Method setPorcentajeDescuento.
	 * 
	 * @param porcentajeDescuento
	 *            BigDecimal
	 */
	public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
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
	 * Method getIdCategoria.
	 * 
	 * @return Categoria
	 */
	public Categoria getIdCategoria() {
		return idCategoria;
	}

	/**
	 * Method setIdCategoria.
	 * 
	 * @param idCategoria
	 *            Categoria
	 */
	public void setIdCategoria(Categoria idCategoria) {
		this.idCategoria = idCategoria;
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
		if (!(object instanceof Promocioncategoria)) {
			return false;
		}
		Promocioncategoria other = (Promocioncategoria) object;
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

		sb.append(idCategoria.getId());
		sb.append(separator);

		sb.append(idPromocion.getId());
		sb.append(separator);

		sb.append(porcentajeDescuento);
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(endStr);
		return sb.toString();
	}

}
