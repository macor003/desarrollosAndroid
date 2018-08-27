/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "proceso")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Proceso.findAll", query = "SELECT p FROM Proceso p"),
		@NamedQuery(name = "Proceso.findById", query = "SELECT p FROM Proceso p WHERE p.id = :id"),
		@NamedQuery(name = "Proceso.findByDescripcion", query = "SELECT p FROM Proceso p WHERE p.descripcion = :descripcion"),
		@NamedQuery(name = "Proceso.findBySerequiereautorizacion", query = "SELECT p FROM Proceso p WHERE p.serequiereautorizacion = :serequiereautorizacion"),
		@NamedQuery(name = "Proceso.findByFecha", query = "SELECT p FROM Proceso p WHERE p.fecha = :fecha"),
		@NamedQuery(name = "Proceso.findByEsautoautorizado", query = "SELECT p FROM Proceso p WHERE p.esautoautorizado = :esautoautorizado") })
public class Proceso implements Serializable {
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
	 * Field serequiereautorizacion.
	 */
	@Basic(optional = false)
	@Column(name = "serequiereautorizacion")
	private String serequiereautorizacion;
	/**
	 * Field permiteconciliacion.
	 */
	@Basic(optional = false)
	@Column(name = "permiteconciliacion")
	private String permiteconciliacion;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field esautoautorizado.
	 */
	@Basic(optional = false)
	@Column(name = "esautoautorizado")
	private String esautoautorizado;
	/**
	 * Field procesopropertyList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idProceso")
	private List<Procesoproperty> procesopropertyList;
	/**
	 * Field perfilprocesoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idProceso")
	private List<Perfilproceso> perfilprocesoList;

	/**
	 * Constructor for Proceso.
	 */
	public Proceso() {
	}

	/**
	 * Constructor for Proceso.
	 * 
	 * @param id
	 *            Long
	 */
	public Proceso(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Proceso.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param serequiereautorizacion
	 *            String
	 * @param fecha
	 *            Date
	 * @param esautoautorizado
	 *            String
	 */
	public Proceso(Long id, String descripcion, String serequiereautorizacion, Date fecha, String esautoautorizado) {
		this.id = id;
		this.descripcion = descripcion;
		this.serequiereautorizacion = serequiereautorizacion;
		this.fecha = fecha;
		this.esautoautorizado = esautoautorizado;
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
	 * Method getSerequiereautorizacion.
	 * 
	 * @return String
	 */
	public String getSerequiereautorizacion() {
		return serequiereautorizacion;
	}

	/**
	 * Method setSerequiereautorizacion.
	 * 
	 * @param serequiereautorizacion
	 *            String
	 */
	public void setSerequiereautorizacion(String serequiereautorizacion) {
		this.serequiereautorizacion = serequiereautorizacion;
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
	 * Method getEsautoautorizado.
	 * 
	 * @return String
	 */
	public String getEsautoautorizado() {
		return esautoautorizado;
	}

	/**
	 * Method setEsautoautorizado.
	 * 
	 * @param esautoautorizado
	 *            String
	 */
	public void setEsautoautorizado(String esautoautorizado) {
		this.esautoautorizado = esautoautorizado;
	}

	/**
	 * Method getProcesopropertyList.
	 * 
	 * @return List<Procesoproperty>
	 */
	@XmlTransient
	public List<Procesoproperty> getProcesopropertyList() {
		return procesopropertyList;
	}

	/**
	 * Method setProcesopropertyList.
	 * 
	 * @param procesopropertyList
	 *            List<Procesoproperty>
	 */
	public void setProcesopropertyList(List<Procesoproperty> procesopropertyList) {
		this.procesopropertyList = procesopropertyList;
	}

	/**
	 * Method getPerfilprocesoList.
	 * 
	 * @return List<Perfilproceso>
	 */
	@XmlTransient
	public List<Perfilproceso> getPerfilprocesoList() {
		return perfilprocesoList;
	}

	/**
	 * Method setPerfilprocesoList.
	 * 
	 * @param perfilprocesoList
	 *            List<Perfilproceso>
	 */
	public void setPerfilprocesoList(List<Perfilproceso> perfilprocesoList) {
		this.perfilprocesoList = perfilprocesoList;
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
		if (!(object instanceof Proceso)) {
			return false;
		}
		Proceso other = (Proceso) object;
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

		sb.append(serequiereautorizacion);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(esautoautorizado);
		sb.append(separator);
		
		sb.append(permiteconciliacion);
		sb.append(endStr);
		return sb.toString();
	}

	public String getPermiteconciliacion() {
		return permiteconciliacion;
	}

	public void setPermiteconciliacion(String permiteconciliacion) {
		this.permiteconciliacion = permiteconciliacion;
	}

}
