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
@Table(name = "tipoidentificacioncliente")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tipoidentificacioncliente.findAll", query = "SELECT t FROM Tipoidentificacioncliente t"),
		@NamedQuery(name = "Tipoidentificacioncliente.findById", query = "SELECT t FROM Tipoidentificacioncliente t WHERE t.id = :id"),
		@NamedQuery(name = "Tipoidentificacioncliente.findByMascara", query = "SELECT t FROM Tipoidentificacioncliente t WHERE t.mascara = :mascara"),
		@NamedQuery(name = "Tipoidentificacioncliente.findByFecha", query = "SELECT t FROM Tipoidentificacioncliente t WHERE t.fecha = :fecha") })
public class Tipoidentificacioncliente implements Serializable {
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
	 * Field mascara.
	 */
	@Basic(optional = false)
	@Column(name = "mascara")
	private String mascara;
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
	 * Constructor for Tipoidentificacioncliente.
	 */
	public Tipoidentificacioncliente() {
	}

	/**
	 * Constructor for Tipoidentificacioncliente.
	 * 
	 * @param id
	 *            Long
	 */
	public Tipoidentificacioncliente(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Tipoidentificacioncliente.
	 * 
	 * @param id
	 *            Long
	 * @param mascara
	 *            String
	 * @param fecha
	 *            Date
	 */
	public Tipoidentificacioncliente(Long id, String mascara, Date fecha) {
		this.id = id;
		this.mascara = mascara;
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
	 * Method getMascara.
	 * 
	 * @return String
	 */
	public String getMascara() {
		return mascara;
	}

	/**
	 * Method setMascara.
	 * 
	 * @param mascara
	 *            String
	 */
	public void setMascara(String mascara) {
		this.mascara = mascara;
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
		if (!(object instanceof Tipoidentificacioncliente)) {
			return false;
		}
		Tipoidentificacioncliente other = (Tipoidentificacioncliente) object;
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
		String nullStr = "\\N";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(idTipocliente.getId());
		sb.append(separator);

		sb.append(enclosed);
		sb.append(mascara);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));

		sb.append(endStr);

		return sb.toString();
	}

}
