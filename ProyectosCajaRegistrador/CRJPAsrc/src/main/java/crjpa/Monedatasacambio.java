/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

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

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "monedatasacambio")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Monedatasacambio.findAll", query = "SELECT m FROM Monedatasacambio m"),
		@NamedQuery(name = "Monedatasacambio.findById", query = "SELECT m FROM Monedatasacambio m WHERE m.id = :id"),
		@NamedQuery(name = "Monedatasacambio.findByCambio", query = "SELECT m FROM Monedatasacambio m WHERE m.cambio = :cambio"),
		@NamedQuery(name = "Monedatasacambio.findByFecha", query = "SELECT m FROM Monedatasacambio m WHERE m.fecha = :fecha") })
public class Monedatasacambio implements Serializable {
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
	 * Field cambio.
	 */
	@Basic(optional = false)
	@Column(name = "cambio")
	private BigDecimal cambio;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field idMoneda.
	 */
	@JoinColumn(name = "id_moneda", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Moneda idMoneda;

	/**
	 * Constructor for Monedatasacambio.
	 */
	public Monedatasacambio() {
	}

	/**
	 * Constructor for Monedatasacambio.
	 * 
	 * @param id
	 *            Long
	 */
	public Monedatasacambio(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Monedatasacambio.
	 * 
	 * @param id
	 *            Long
	 * @param cambio
	 *            BigDecimal
	 * @param fecha
	 *            Date
	 */
	public Monedatasacambio(Long id, BigDecimal cambio, Date fecha) {
		this.id = id;
		this.cambio = cambio;
		this.fecha = fecha;
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
	 * Method getCambio.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getCambio() {
		return cambio;
	}

	/**
	 * Method setCambio.
	 * 
	 * @param cambio
	 *            BigDecimal
	 */
	public void setCambio(BigDecimal cambio) {
		this.cambio = cambio;
	}

	/**
	 * Method getFecha.
	 * 
	 * @return Date
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Method setFecha.
	 * 
	 * @param fecha
	 *            Date
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
		if (!(object instanceof Monedatasacambio)) {
			return false;
		}
		Monedatasacambio other = (Monedatasacambio) object;
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
		String nullStr = "\\N";
		String endStr = "\r\n";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(idMoneda.getId());
		sb.append(separator);

		sb.append(cambio);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(endStr);

		return sb.toString();
	}

}
