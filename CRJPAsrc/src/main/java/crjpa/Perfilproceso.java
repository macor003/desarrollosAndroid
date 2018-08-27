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
@Table(name = "perfilproceso")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Perfilproceso.findAll", query = "SELECT p FROM Perfilproceso p"),
		@NamedQuery(name = "Perfilproceso.findById", query = "SELECT p FROM Perfilproceso p WHERE p.id = :id"),
		@NamedQuery(name = "Perfilproceso.findByFecha", query = "SELECT p FROM Perfilproceso p WHERE p.fecha = :fecha"),
		@NamedQuery(name = "Perfilproceso.findByEstaactivo", query = "SELECT p FROM Perfilproceso p WHERE p.estaactivo = :estaactivo") })
public class Perfilproceso implements Serializable {
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
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	/**
	 * Field idPerfil.
	 */
	@JoinColumn(name = "id_perfil", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Perfil idPerfil;
	/**
	 * Field idProceso.
	 */
	@JoinColumn(name = "id_proceso", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Proceso idProceso;

	/**
	 * Constructor for Perfilproceso.
	 */
	public Perfilproceso() {
	}

	/**
	 * Constructor for Perfilproceso.
	 * 
	 * @param id
	 *            Long
	 */
	public Perfilproceso(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Perfilproceso.
	 * 
	 * @param id
	 *            Long
	 * @param fecha
	 *            Date
	 * @param estaactivo
	 *            String
	 */
	public Perfilproceso(Long id, Date fecha, String estaactivo) {
		this.id = id;
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
	 * Method getIdPerfil.
	 * 
	 * @return Perfil
	 */
	public Perfil getIdPerfil() {
		return idPerfil;
	}

	/**
	 * Method setIdPerfil.
	 * 
	 * @param idPerfil
	 *            Perfil
	 */
	public void setIdPerfil(Perfil idPerfil) {
		this.idPerfil = idPerfil;
	}

	/**
	 * Method getIdProceso.
	 * 
	 * @return Proceso
	 */
	public Proceso getIdProceso() {
		return idProceso;
	}

	/**
	 * Method setIdProceso.
	 * 
	 * @param idProceso
	 *            Proceso
	 */
	public void setIdProceso(Proceso idProceso) {
		this.idProceso = idProceso;
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
		if (!(object instanceof Perfilproceso)) {
			return false;
		}
		Perfilproceso other = (Perfilproceso) object;
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

		sb.append(idPerfil.getId());
		sb.append(separator);

		sb.append(idProceso.getId());
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(endStr);
		return sb.toString();
	}

}
