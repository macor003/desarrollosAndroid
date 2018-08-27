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
@Table(name = "motivorebaja")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Motivorebaja.findAll", query = "SELECT m FROM Motivorebaja m"),
		@NamedQuery(name = "Motivorebaja.findById", query = "SELECT m FROM Motivorebaja m WHERE m.id = :id"),
		@NamedQuery(name = "Motivorebaja.findByDescripcion", query = "SELECT m FROM Motivorebaja m WHERE m.descripcion = :descripcion"),
		@NamedQuery(name = "Motivorebaja.findByEstaactivo", query = "SELECT m FROM Motivorebaja m WHERE m.estaactivo = :estaactivo"),
		@NamedQuery(name = "Motivorebaja.findByPorcentajeMaximo", query = "SELECT m FROM Motivorebaja m WHERE m.porcentajeMaximo = :porcentajeMaximo") })
public class Motivorebaja implements Serializable {
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
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field porcentajeMaximo.
	 */
	@Basic(optional = false)
	@Column(name = "porcentaje_maximo")
	private BigDecimal porcentajeMaximo;
	/**
	 * Field idTipodescuento.
	 */
	@JoinColumn(name = "id_tipodescuento", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Tipodescuento idTipodescuento;

	/**
	 * Constructor for Motivorebaja.
	 */
	public Motivorebaja() {
	}

	/**
	 * Constructor for Motivorebaja.
	 * 
	 * @param id
	 *            Long
	 */
	public Motivorebaja(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Motivorebaja.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param estaactivo
	 *            String
	 * @param porcentajeMaximo
	 *            BigDecimal
	 */
	public Motivorebaja(Long id, String descripcion, String estaactivo, BigDecimal porcentajeMaximo) {
		this.id = id;
		this.descripcion = descripcion;
		this.estaactivo = estaactivo;
		this.porcentajeMaximo = porcentajeMaximo;
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
	 * Method getPorcentajeMaximo.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getPorcentajeMaximo() {
		return porcentajeMaximo;
	}

	/**
	 * Method setPorcentajeMaximo.
	 * 
	 * @param porcentajeMaximo
	 *            BigDecimal
	 */
	public void setPorcentajeMaximo(BigDecimal porcentajeMaximo) {
		this.porcentajeMaximo = porcentajeMaximo;
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
		if (!(object instanceof Motivorebaja)) {
			return false;
		}
		Motivorebaja other = (Motivorebaja) object;
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
		String enclosed = "|";
		String endStr = "\r\n";

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(idTipodescuento.getId());
		sb.append(separator);

		sb.append(enclosed);
		sb.append(descripcion);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(separator);

		sb.append(porcentajeMaximo);
		sb.append(endStr);

		return sb.toString();
	}

}
