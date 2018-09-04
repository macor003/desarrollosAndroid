/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
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
@Table(name = "tipoclientetipodocumento")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tipoclientetipodocumento.findAll", query = "SELECT t FROM Tipoclientetipodocumento t"),
		@NamedQuery(name = "Tipoclientetipodocumento.findById", query = "SELECT t FROM Tipoclientetipodocumento t WHERE t.id = :id"),
		@NamedQuery(name = "Tipoclientetipodocumento.findByFecha", query = "SELECT t FROM Tipoclientetipodocumento t WHERE t.fecha = :fecha") })
public class Tipoclientetipodocumento implements Serializable {
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
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field idTipocliente.
	 */
	@JoinColumn(name = "id_tipocliente", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Tipocliente idTipocliente;
	/**
	 * Field idTipodocumento.
	 */
	@JoinColumn(name = "id_tipodocumento", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Tipodocumento idTipodocumento;

	/**
	 * Constructor for Tipoclientetipodocumento.
	 */
	public Tipoclientetipodocumento() {
	}

	/**
	 * Constructor for Tipoclientetipodocumento.
	 * 
	 * @param id
	 *            Long
	 */
	public Tipoclientetipodocumento(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Tipoclientetipodocumento.
	 * 
	 * @param id
	 *            Long
	 * @param fecha
	 *            Date
	 */
	public Tipoclientetipodocumento(Long id, Date fecha) {
		this.id = id;
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
	 * Method getIdTipocliente.
	 * 
	 * @return Tipocliente
	 */
	public Tipocliente getIdTipocliente() {
		return idTipocliente;
	}

	/**
	 * Method setIdTipocliente.
	 * 
	 * @param idTipocliente
	 *            Tipocliente
	 */
	public void setIdTipocliente(Tipocliente idTipocliente) {
		this.idTipocliente = idTipocliente;
	}

	/**
	 * Method getIdTipodocumento.
	 * 
	 * @return Tipodocumento
	 */
	public Tipodocumento getIdTipodocumento() {
		return idTipodocumento;
	}

	/**
	 * Method setIdTipodocumento.
	 * 
	 * @param idTipodocumento
	 *            Tipodocumento
	 */
	public void setIdTipodocumento(Tipodocumento idTipodocumento) {
		this.idTipodocumento = idTipodocumento;
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
		if (!(object instanceof Tipoclientetipodocumento)) {
			return false;
		}
		Tipoclientetipodocumento other = (Tipoclientetipodocumento) object;
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
		String nullStr = "\\N";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(idTipodocumento.getId());
		sb.append(separator);

		sb.append(idTipocliente.getId());
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));

		sb.append(endStr);

		return sb.toString();
	}

}
