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
import javax.persistence.Lob;
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
@Table(name = "opcion")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Opcion.findAll", query = "SELECT o FROM Opcion o"),
		@NamedQuery(name = "Opcion.findById", query = "SELECT o FROM Opcion o WHERE o.id = :id"),
		@NamedQuery(name = "Opcion.findByDescripcion", query = "SELECT o FROM Opcion o WHERE o.descripcion = :descripcion"),
		@NamedQuery(name = "Opcion.findByFecha", query = "SELECT o FROM Opcion o WHERE o.fecha = :fecha"),
		@NamedQuery(name = "Opcion.findByEstaactivo", query = "SELECT o FROM Opcion o WHERE o.estaactivo = :estaactivo") })
public class Opcion implements Serializable {
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
	 * Field valor.
	 */
	@Basic(optional = false)
	@Lob
	@Column(name = "valor")
	private String valor;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;

	/**
	 * Constructor for Opcion.
	 */
	public Opcion() {
	}

	/**
	 * Constructor for Opcion.
	 * 
	 * @param id
	 *            Long
	 */
	public Opcion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Opcion.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param valor
	 *            String
	 * @param fecha
	 *            Date
	 * @param estaactivo
	 *            String
	 */
	public Opcion(Long id, String descripcion, String valor, Date fecha, String estaactivo) {
		this.id = id;
		this.descripcion = descripcion;
		this.valor = valor;
		this.fecha = fecha;
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
	 * Method getValor.
	 * 
	 * @return String
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Method setValor.
	 * 
	 * @param valor
	 *            String
	 */
	public void setValor(String valor) {
		this.valor = valor;
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
		if (!(object instanceof Opcion)) {
			return false;
		}
		Opcion other = (Opcion) object;
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
		String nullStr = "\\N";
		String endStr = "\r\n";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(descripcion);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(valor);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(endStr);

		return sb.toString();
	}

}
