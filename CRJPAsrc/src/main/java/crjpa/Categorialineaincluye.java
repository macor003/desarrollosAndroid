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
@Table(name = "categorialineaincluye")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Categorialineaincluye.findAll", query = "SELECT c FROM Categorialineaincluye c"),
		@NamedQuery(name = "Categorialineaincluye.findById", query = "SELECT c FROM Categorialineaincluye c WHERE c.id = :id"),
		@NamedQuery(name = "Categorialineaincluye.findByFecha", query = "SELECT c FROM Categorialineaincluye c WHERE c.fecha = :fecha") })
public class Categorialineaincluye implements Serializable {
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
	 * Field idLinea.
	 */
	@JoinColumn(name = "id_linea", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Linea idLinea;
	/**
	 * Field idGiroactividadeconomica.
	 */
	@JoinColumn(name = "id_giroactividadeconomica", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Giroactividadeconomica idGiroactividadeconomica;
	/**
	 * Field idTipodocumento.
	 */
	@JoinColumn(name = "id_tipodocumento", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Tipodocumento idTipodocumento;

	/**
	 * Constructor for Categorialineaincluye.
	 */
	public Categorialineaincluye() {
	}

	/**
	 * Constructor for Categorialineaincluye.
	 * 
	 * @param id
	 *            Long
	 */
	public Categorialineaincluye(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Categorialineaincluye.
	 * 
	 * @param id
	 *            Long
	 * @param fecha
	 *            Date
	 */
	public Categorialineaincluye(Long id, Date fecha) {
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
	 * Method getIdLinea.
	 * 
	 * @return Linea
	 */
	public Linea getIdLinea() {
		return idLinea;
	}

	/**
	 * Method setIdLinea.
	 * 
	 * @param idLinea
	 *            Linea
	 */
	public void setIdLinea(Linea idLinea) {
		this.idLinea = idLinea;
	}

	/**
	 * Method getIdGiroactividadeconomica.
	 * 
	 * @return Giroactividadeconomica
	 */
	public Giroactividadeconomica getIdGiroactividadeconomica() {
		return idGiroactividadeconomica;
	}

	/**
	 * Method setIdGiroactividadeconomica.
	 * 
	 * @param idGiroactividadeconomica
	 *            Giroactividadeconomica
	 */
	public void setIdGiroactividadeconomica(Giroactividadeconomica idGiroactividadeconomica) {
		this.idGiroactividadeconomica = idGiroactividadeconomica;
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
		if (!(object instanceof Categorialineaincluye)) {
			return false;
		}
		Categorialineaincluye other = (Categorialineaincluye) object;
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

		sb.append(idLinea.getId());
		sb.append(separator);

		sb.append(idGiroactividadeconomica.getId());
		sb.append(separator);

		sb.append(idTipodocumento.getId());
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(endStr);

		return sb.toString();
	}

}
