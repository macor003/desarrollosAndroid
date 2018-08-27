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
@Table(name = "perfil")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
		@NamedQuery(name = "Perfil.findById", query = "SELECT p FROM Perfil p WHERE p.id = :id"),
		@NamedQuery(name = "Perfil.findByNombre", query = "SELECT p FROM Perfil p WHERE p.nombre = :nombre"),
		@NamedQuery(name = "Perfil.findByFecha", query = "SELECT p FROM Perfil p WHERE p.fecha = :fecha"),
		@NamedQuery(name = "Perfil.findByEstaactivo", query = "SELECT p FROM Perfil p WHERE p.estaactivo = :estaactivo") })
public class Perfil implements Serializable {
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
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
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
	 * Field usuarioperfilList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerfil")
	private List<Usuarioperfil> usuarioperfilList;
	/**
	 * Field perfilprocesoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPerfil")
	private List<Perfilproceso> perfilprocesoList;

	/**
	 * Constructor for Perfil.
	 */
	public Perfil() {
	}

	/**
	 * Constructor for Perfil.
	 * 
	 * @param id
	 *            Long
	 */
	public Perfil(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Perfil.
	 * 
	 * @param id
	 *            Long
	 * @param nombre
	 *            String
	 * @param fecha
	 *            Date
	 * @param estaactivo
	 *            String
	 */
	public Perfil(Long id, String nombre, Date fecha, String estaactivo) {
		this.id = id;
		this.nombre = nombre;
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
	 * Method getNombre.
	 * 
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Method setNombre.
	 * 
	 * @param nombre
	 *            String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * Method getUsuarioperfilList.
	 * 
	 * @return List<Usuarioperfil>
	 */
	@XmlTransient
	public List<Usuarioperfil> getUsuarioperfilList() {
		return usuarioperfilList;
	}

	/**
	 * Method setUsuarioperfilList.
	 * 
	 * @param usuarioperfilList
	 *            List<Usuarioperfil>
	 */
	public void setUsuarioperfilList(List<Usuarioperfil> usuarioperfilList) {
		this.usuarioperfilList = usuarioperfilList;
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
		if (!(object instanceof Perfil)) {
			return false;
		}
		Perfil other = (Perfil) object;
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
		sb.append(nombre);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(endStr);
		return sb.toString();
	}

}
